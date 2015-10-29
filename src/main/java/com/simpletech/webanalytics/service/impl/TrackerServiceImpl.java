package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.TrackerDao;
import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.model.entity.JsEvent;
import com.simpletech.webanalytics.model.*;
import com.simpletech.webanalytics.model.entity.JsUser;
import com.simpletech.webanalytics.service.*;
import com.simpletech.webanalytics.util.AfReflecter;
import com.simpletech.webanalytics.util.AfStringUtil;
import com.simpletech.webanalytics.util.LruCache;
import com.simpletech.webanalytics.util.SynchronizedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JS探针 接收服务
 * Created by 树朾 on 2015/9/22.
 */
@Service
public class TrackerServiceImpl implements TrackerService {

    @Autowired
    TrackerDao dao;
    @Autowired
    TrackShareService trackShareService;


    private static SynchronizedLock<String> actionLocks = new SynchronizedLock<>(10000);

    @Override
    public void trackPageView(int siteId, JsDetect detect) throws Exception {
        Site site = dao.findSiteById(siteId);
        if (site != null) {
            String idsubsite = getIdSubSite(site, detect.getUrl());
            dao.getSubSite(siteId, idsubsite);

            Url url = dao.getUrl(siteId, idsubsite, detect.getUrl(), detect.getTitle());
            Title title = dao.getTitle(siteId, idsubsite, detect.getTitle());

            synchronized (actionLocks.getLock(siteId + detect.getIdvtor())) {
                Action naction = new Action();
                naction.setIdsite(siteId);
                naction.setServerTime(new Date());
                naction.setIdurl(url.getId());
                naction.setIdtitle(title.getId());
                naction.setTimeLoaded(detect.getGtms());
                naction.setIdvisitor(detect.getIdvtor());
                naction.setRefTimeSpent(0);

                Action action = dao.getActionHalfHour(siteId, idsubsite, detect, url, title);
                if (action != null) {
                    dao.updateVisitPageView(action.getIdvisit(), url.getId(), title.getId());
                    naction.setIdvisit(action.getIdvisit());
                    naction.setRefIdUrl(action.getIdurl());
                    naction.setRefIdTitle(action.getIdtitle());
                    naction.setRefTimeSpent((int)(new Date().getTime()-action.getCreateTime().getTime()));
                } else {
                    naction.setIdvisit(dao.newVisit(siteId, idsubsite, detect, url, title));
                }

                dao.insertAction(idsubsite, naction);
            }

            trackShareService.trackerShare(siteId, idsubsite, detect, url);
        }
    }


    @Override
    public void trackEvent(int siteId, JsEvent event) throws Exception {
        Site site = dao.findSiteById(siteId);
        if (site != null) {
            String idsubsite = getIdSubSite(site, event.getUrl());
            dao.insertEvent(idsubsite, event.buildEvent(site.getId()));
            dao.updateVisitEvent(siteId, idsubsite, event.getIdvtor());
        }
    }

    public void trackerPageView(int siteId, JsDetect detect) throws Exception {
        Site site = dao.findSiteById(siteId);
        if (site != null) {
            String idsubsite = getIdSubSite(site, detect.getUrl());
            dao.getSubSite(siteId, idsubsite);

            Url url = dao.getUrl(siteId, idsubsite, detect.getUrl(), detect.getTitle());
            Title title = dao.getTitle(siteId, idsubsite, detect.getTitle());
            Visit visit = dao.getVisitHalfHour(siteId, idsubsite, detect, url, title);
            visit.setIdurlExit(url.getId());
            visit.setIdtitleExit(title.getId());
            visit.setCountVisits(visit.getCountVisits() + 1);
            visit.setActionLastTime(new Date());
            dao.updateVisit(idsubsite, visit);
            Action action = new Action();
            action.setIdsite(siteId);
            action.setIdvisit(visit.getId());
            action.setServerTime(new Date());
            action.setIdurl(url.getId());
            action.setIdtitle(title.getId());
            action.setTimeLoaded(detect.getGtms());
            action.setIdvisitor(detect.getIdvtor());
            dao.insertAction(idsubsite, action);

            trackShareService.trackerShare(siteId, idsubsite, detect, url);
        }
    }

    public void trackerEvent(int siteId, JsEvent event) throws Exception {
        Site site = dao.findSiteById(siteId);
        if (site != null) {
            String idsubsite = getIdSubSite(site, event.getUrl());
            dao.getSubSite(siteId, idsubsite);

            Url url = dao.getUrl(siteId, idsubsite, event.getUrl(), event.getTitle());
            Title title = dao.getTitle(siteId, idsubsite, event.getTitle());
            Visit visit = dao.getVisitHalfHour(siteId, idsubsite, event, url, title);
            if (visit.getCountVisits() == 0) {
                visit.setIdurlExit(url.getId());
                visit.setIdtitleExit(title.getId());
                visit.setCountVisits(visit.getCountVisits() + 1);
                Action action = new Action();
                action.setIdsite(siteId);
                action.setIdvisit(visit.getId());
                action.setServerTime(new Date());
                action.setIdurl(url.getId());
                action.setIdtitle(title.getId());
                action.setTimeLoaded(0);
                action.setIdvisitor(event.getIdvtor());
                dao.insertAction(idsubsite, action);
            }
            visit.setCountEvents(visit.getCountEvents() + 1);
            dao.updateVisit(idsubsite, visit);
            dao.insertEvent(idsubsite, event.buildEvent(site.getId()));
        }
    }

    @Override
    public void trackerUser(int siteId, JsUser user) throws Exception {
        Site site = dao.findSiteById(siteId);
        if (site != null) {
            String idsubsite = getIdSubSite(site, user.getUrl());
            user.setIdsubsite(idsubsite);
            trackShareService.addOrUpdateUser(siteId, user);
        }
    }

    static LruCache<String, Pattern> cachePattern = new LruCache<>(10);

    private String getIdSubSite(Site site, String url) {
        String idsubsite = "";
        if (AfStringUtil.isNotEmpty(site.getRegex()) && url != null) {
            String cacheKey = site.getRegex();
            Pattern pattern = cachePattern.get(cacheKey);
            if (pattern == null) {
                pattern = Pattern.compile(site.getRegex(), Pattern.CASE_INSENSITIVE);
                cachePattern.put(cacheKey, pattern);
            }
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                int index = 0;
                while ((idsubsite == null || idsubsite.trim().length() == 0) && index++ < matcher.groupCount()) {
                    idsubsite = matcher.group(index);
                }
                idsubsite = (AfStringUtil.isEmpty(idsubsite)) ? "" : idsubsite;
            }
        }
        return idsubsite;
    }
}
