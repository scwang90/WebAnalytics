package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.TrackerDao;
import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.model.entity.JsEvent;
import com.simpletech.webanalytics.model.*;
import com.simpletech.webanalytics.service.*;
import com.simpletech.webanalytics.util.AfStringUtil;
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
public class TrackerServiceImpl implements TrackerService{

    @Autowired
    TrackerDao dao;



    public void trackerPageView(int siteId, JsDetect detect) throws Exception{
        Site site = dao.findSiteById(siteId);
        if (site != null){
            String idsubsite = getIdSubSite(site,detect.getUrl());
            dao.getSubSite(siteId, idsubsite);

            Url url = dao.getUrl(siteId, idsubsite, detect.getUrl());
            Title title = dao.getTitle(siteId, idsubsite, detect.getTitle());
            Visit visit = dao.getVisitHalfHour(siteId, idsubsite, detect, url, title);
            visit.setIdurlExit(url.getId());
            visit.setIdtitleExit(title.getId());
            visit.setCountVisits(visit.getCountVisits() + 1);
            dao.updateVisit(idsubsite, visit);
            Action action = new Action();
            action.setIdsite(siteId);
            action.setIdvisit(visit.getId());
            action.setServerTime(new Date());
            action.setIdurl(url.getId());
            action.setIdtitle(title.getId());
            action.setTimeSpent(detect.getGtms());
            action.setIdvisitor(detect.getIdvtor());
            dao.insertAction(idsubsite, action);
        }
    }

    @Override
    public void trackerEvent(int siteId, JsEvent event) throws Exception {
        Site site = dao.findSiteById(siteId);
        if (site != null){
            String idsubsite = getIdSubSite(site,event.getUrl());
            dao.getSubSite(siteId, idsubsite);

            Url url = dao.getUrl(siteId, idsubsite, event.getUrl());
            Title title = dao.getTitle(siteId, idsubsite, event.getTitle());
            Visit visit = dao.getVisitHalfHour(siteId, idsubsite, event, url, title);
            if (visit.getCountVisits() == 0){
                visit.setIdurlExit(url.getId());
                visit.setIdtitleExit(title.getId());
                visit.setCountVisits(visit.getCountVisits() + 1);
                Action action = new Action();
                action.setIdsite(siteId);
                action.setIdvisit(visit.getId());
                action.setServerTime(new Date());
                action.setIdurl(url.getId());
                action.setIdtitle(title.getId());
                action.setTimeSpent(0);
                action.setIdvisitor(event.getIdvtor());
                dao.insertAction(idsubsite, action);
            }
            visit.setCountEvents(visit.getCountEvents() + 1);
            dao.updateVisit(idsubsite, visit);
            dao.insertEvent(idsubsite, event.buildEvent(site.getId()));
        }
    }

    private String getIdSubSite(Site site, String url) {
        String idsubsite = "";
        if (AfStringUtil.isNotEmpty(site.getRegex()) && url != null) {
            Pattern pattern = Pattern.compile(site.getRegex(), Pattern.CASE_INSENSITIVE);
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
