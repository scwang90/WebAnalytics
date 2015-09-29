package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.*;
import com.simpletech.webanalytics.model.constant.Norm;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.entity.EventPeriodValue;
import com.simpletech.webanalytics.model.entity.EventValue;
import com.simpletech.webanalytics.model.entity.PageValue;
import com.simpletech.webanalytics.model.entity.PeriodValue;
import com.simpletech.webanalytics.service.SiteService;
import com.simpletech.webanalytics.service.StatisticsService;
import com.simpletech.webanalytics.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 统计API Service 实现
 * Created by Administrator on 2015/9/25.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    SiteService siteService;
    @Autowired
    VisitDao visitDao;
    @Autowired
    EventDao eventDao;
    @Autowired
    ActionDao actionDao;
    @Autowired
    TitleDao titleDao;
    @Autowired
    UrlDao urlDao;

    @Override
    public List<PeriodValue> norm(String idsite, Period period, Norm norm, Date start, Date end) throws Exception {
        switch (norm) {
            case visit:
                return this.visit(idsite, period, start, end);
            case pv:
                return this.pageView(idsite, period, start, end);
            case uv:
                return this.uniqueVisitor(idsite, period, start, end);
            case ip:
                return this.internetProtocol(idsite, period, start, end);
            default:
                throw new ServiceException("无效指标");
        }
    }

    @Override
    public List<EventValue> event(String idsite, Date start, Date end, int limit, int skip) throws Exception {
        Long visit = visitDao.countVisit(idsite, start, end);
        Long users = visitDao.countUsers(idsite, start, end);
        List<EventValue> events = eventDao.event(idsite, start, end, limit, skip);
        for (EventValue value : events) {
            value.setRn(value.getNum() * 1f / visit);
            value.setRu(value.getUser() * 1f / users);
        }
        return events;
    }

    @Override
    public List<EventPeriodValue> event(String idsite, String category, Period period, Date start, Date end, int limit, int skip) throws Exception {
        List<EventPeriodValue> events = new ArrayList<>();
        switch (period) {
            case hour:
                events = eventDao.eventHour(idsite, category, start, end, limit, skip);
                break;
            case day:
                events = eventDao.eventDay(idsite, category, start, end, limit, skip);
                break;
            case week:
                events = eventDao.eventWeek(idsite, category, start, end, limit, skip);
                break;
            case month:
                events = eventDao.eventMonth(idsite, category, start, end, limit, skip);
                break;
        }
        Long visit = visitDao.countVisit(idsite, start, end);
        Long users = visitDao.countUsers(idsite, start, end);
        for (EventPeriodValue value : events) {
            value.setRn(value.getNum() * 1f / visit);
            value.setRu(value.getUser() * 1f / users);
        }
        return events;
    }

    @Override
    public List<PageValue> pagetitle(String siteId, Date start, Date end, int limit, int skip) throws Exception {
        List<PageValue> pagetitle = actionDao.pagetitle(siteId, start, end, limit, skip);
        pagetitle = titleDao.fullName(pagetitle);
        return pagetitle;
    }

    @Override
    public List<PageValue> pageurl(String siteId, Date start, Date end, int limit, int skip) throws Exception {
        List<PageValue> pageurl = actionDao.pageurl(siteId, start, end, limit, skip);
        pageurl = urlDao.fullName(pageurl);
        return pageurl;
    }

    public List<PeriodValue> visit(String idsite, Period period, Date start, Date end) throws Exception {
        switch (period) {
            case hour:
                return visitDao.visitHour(idsite, start, end);
            case day:
                return visitDao.visitDay(idsite, start, end);
            case week:
                return visitDao.visitWeek(idsite, start, end);
            case month:
                return visitDao.visitMonth(idsite, start, end);
        }
        return new ArrayList<>();
    }

    public List<PeriodValue> pageView(String idsite, Period period, Date start, Date end) throws Exception {
        switch (period) {
            case hour:
                return visitDao.pageViewHour(idsite, start, end);
            case day:
                return visitDao.pageViewDay(idsite, start, end);
            case week:
                return visitDao.pageViewWeek(idsite, start, end);
            case month:
                return visitDao.pageViewMonth(idsite, start, end);
        }
        return new ArrayList<>();
    }

    public List<PeriodValue> uniqueVisitor(String idsite, Period period, Date start, Date end) throws Exception {
        switch (period) {
            case hour:
                return visitDao.uniqueVisitorHour(idsite, start, end);
            case day:
                return visitDao.uniqueVisitorDay(idsite, start, end);
            case week:
                return visitDao.uniqueVisitorWeek(idsite, start, end);
            case month:
                return visitDao.uniqueVisitorMonth(idsite, start, end);
        }
        return new ArrayList<>();
    }

    public List<PeriodValue> internetProtocol(String idsite, Period period, Date start, Date end) throws Exception {
        switch (period) {
            case hour:
                return visitDao.internetProtocolHour(idsite, start, end);
            case day:
                return visitDao.internetProtocolDay(idsite, start, end);
            case week:
                return visitDao.internetProtocolWeek(idsite, start, end);
            case month:
                return visitDao.internetProtocolMonth(idsite, start, end);
        }
        return new ArrayList<>();
    }
}
