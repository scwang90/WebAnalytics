package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.TrackerDao;
import com.simpletech.webanalytics.mapper.api.TrackerMapper;
import com.simpletech.webanalytics.model.*;
import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.util.AfReflecter;
import com.simpletech.webanalytics.util.AfStringUtil;
import com.simpletech.webanalytics.util.LruCache;
import com.simpletech.webanalytics.util.SynchronizedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * js探针 的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class TrackerDaoImpl implements TrackerDao {

    @Autowired
    TrackerMapper mapper;

    private static SynchronizedLock<String> urlLocks = new SynchronizedLock<>(10000);

    private static LruCache<String, Url> urlLruCache = new LruCache<>(1000);

    @Override
    public synchronized Url getUrl(int siteId, String idsubsite, String url, String title) {
        String cacheKey = "" + siteId + url.hashCode();
        Url _url_ = urlLruCache.get(cacheKey);
        if (_url_ != null) {
            return _url_;
        }
        synchronized (urlLocks.getLock(cacheKey)) {
            Url _url = mapper.getUrl(siteId, url.hashCode());
            if (_url == null) {
                _url = new Url();
                _url.setIdsite(siteId);
                _url.setUrl(url);
                _url.setHash(url.hashCode());
                _url.fillNullID();
                _url.setIdsubsite(idsubsite);
                _url.setHashTitle(title.hashCode());
                AfReflecter.setMemberNoException(_url, "createTime", new Date());
                AfReflecter.setMemberNoException(_url, "updateTime", new Date());
                mapper.insertUrl(_url);
            }
            urlLruCache.put(cacheKey, _url);
            return _url;
        }
    }

    private static SynchronizedLock<String> titleLocks = new SynchronizedLock<>(10000);

    private static LruCache<String, Title> titleLruCache = new LruCache<>(1000);

    @Override
    public synchronized Title getTitle(int siteId, String idsubsite, String title) {
        String cacheKey = "" + siteId + title.hashCode();
        Title _title_ = titleLruCache.get(cacheKey);
        if (_title_ != null) {
            return _title_;
        }
        synchronized (titleLocks.getLock(cacheKey)) {
            Title _title = mapper.getTitle(siteId, title.hashCode());
            if (_title == null) {
                _title = new Title();
                _title.setIdsite(siteId);
                _title.setTitle(title);
                _title.setHash(title.hashCode());
                _title.fillNullID();
                _title.setIdsubsite(idsubsite);
                AfReflecter.setMemberNoException(_title, "createTime", new Date());
                AfReflecter.setMemberNoException(_title, "updateTime", new Date());
                mapper.insertTitle(_title);
            }
            titleLruCache.put(cacheKey, _title);
            return _title;
        }
    }

    private static SynchronizedLock<String> subsiteLocks = new SynchronizedLock<>(10000);

    private static LruCache<String, Subsite> subsiteLruCache = new LruCache<>(10000);

    @Override
    public synchronized Subsite getSubSite(int siteId, String idsubsite) {
        String cacheKey = siteId + idsubsite;
        Subsite subsite = subsiteLruCache.get(cacheKey);
        if (subsite != null) {
            return subsite;
        }
        synchronized (subsiteLocks.getLock(cacheKey)) {
            Subsite subSite = mapper.getSubSite(siteId, idsubsite);
            if (subSite == null && AfStringUtil.isNotEmpty(idsubsite)) {
                subSite = new Subsite();
                subSite.setIdsite(siteId);
                subSite.setName(idsubsite);
                subSite.setRemark("");
                subSite.fillNullID();
                AfReflecter.setMemberNoException(subSite, "createTime", new Date());
                AfReflecter.setMemberNoException(subSite, "updateTime", new Date());
                mapper.insertSubSite(subSite);
            }
            if (subSite != null) {
                subsiteLruCache.put(cacheKey, subSite);
            }
            return subSite;
        }
    }

    /**
     * 把 int siteId 转成 string idsite
     *
     * @param siteId  网站ID
     * @param subsite 子项目
     * @return idsite
     */
    private String getIdSite(int siteId, String subsite) {
        if (AfStringUtil.isNotEmpty(subsite)) {
            String format = "%d AND idsubsite='%s'";
            return String.format(format, siteId, subsite);
        }
        return String.valueOf(siteId);
    }

    @Override
    public Visit getVisitById(String idvisit) {
        return mapper.getVisitById(idvisit);
    }

    private static SynchronizedLock<String> visitLocks = new SynchronizedLock<>(10000);

    @Override
    public Visit getVisitHalfHour(int siteId, String idsubsite, JsDetect detect, Url url, Title title) {
        synchronized (visitLocks.getLock(siteId + detect.getIdvtor())) {
            Visit visit = mapper.getVisitHalfHour(getIdSite(siteId, idsubsite), detect.getIdvtor());
            if (visit == null) {
                visit = detect.build(siteId);
                visit.setIdurlEntry(url.getId());
                visit.setIdtitleEntry(title.getId());
                visit.setIdurlExit(url.getId());
                visit.setIdtitleExit(title.getId());
                visit.setNewUser(!mapper.existVisitor(siteId, detect.getIdvtor()));
                visit.setNewSubUser(AfStringUtil.isEmpty(idsubsite) ? visit.getNewUser() : !mapper.existSubVisitor(siteId, idsubsite, detect.getIdvtor()));
                visit.fillNullID();
                visit.setIdsubsite(idsubsite);
                AfReflecter.setMemberNoException(visit, "createTime", new Date());
                AfReflecter.setMemberNoException(visit, "updateTime", new Date());
                mapper.insertVisit(visit);
            }
            return visit;
        }
    }

    @Override
    public Action getActionHalfHour(int siteId, String idsubsite, JsDetect detect, Url url, Title title) {
        return mapper.getActionHalfHour(getIdSite(siteId, idsubsite), detect.getIdvtor());
    }

    @Override
    public Site findSiteById(int siteId) {
        return mapper.findSiteById(siteId);
    }

    @Override
    public void updateVisit(String idsubsite, Visit visit) {
        visit.setIdsubsite(idsubsite);
        AfReflecter.setMemberNoException(visit, "updateTime", new Date());
        mapper.updateVisit(visit);
    }

    @Override
    public void insertAction(String idsubsite, Action action) {
        action.fillNullID();
        action.setIdsubsite(idsubsite);
        AfReflecter.setMemberNoException(action, "createTime", new Date());
        AfReflecter.setMemberNoException(action, "updateTime", new Date());
        mapper.insertAction(action);
    }

    @Override
    public void insertEvent(String idsubsite, Event event) {
        event.fillNullID();
        event.setIdsubsite(idsubsite);
        AfReflecter.setMemberNoException(event, "createTime", new Date());
        AfReflecter.setMemberNoException(event, "updateTime", new Date());
        mapper.insertEvent(event);
    }

    @Override
    public int updateVisitEvent(int siteId, String idsubsite, String idvtor) {
        Visit visit = mapper.findLastVisit(getIdSite(siteId, idsubsite), idvtor);
        if (visit == null) {
            return 0;
        }
        return mapper.updateVisitEvent(visit.getId());
    }

    @Override
    public int updateVisitPageView(String idvisit, String idurlExit, String idtitleExit) {
        return mapper.updateVisitPageView(idvisit, idurlExit, idtitleExit);
    }

    @Override
    public String newVisit(int siteId, String idsubsite, JsDetect detect, Url url, Title title) {
        Visit visit = detect.build(siteId);
        visit.setIdurlEntry(url.getId());
        visit.setIdtitleEntry(title.getId());
        visit.setIdurlExit(url.getId());
        visit.setIdtitleExit(title.getId());
        visit.setNewUser(!mapper.existVisitor(siteId, detect.getIdvtor()));
        visit.setNewSubUser(AfStringUtil.isEmpty(idsubsite) ? visit.getNewUser() : !mapper.existSubVisitor(siteId, idsubsite, detect.getIdvtor()));
        visit.fillNullID();
        visit.setIdsubsite(idsubsite);
        visit.setActionLastTime(new Date());
        visit.setCountVisits(1);
        visit.setCountEvents(0);
        AfReflecter.setMemberNoException(visit, "createTime", new Date());
        AfReflecter.setMemberNoException(visit, "updateTime", new Date());
        mapper.insertVisit(visit);
        return visit.getId();
    }
}

