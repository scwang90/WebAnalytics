package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.*;
import com.simpletech.webanalytics.model.constant.Norm;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.constant.Ranking;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.*;
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
    public List<VisitValue> norm(int idsite, Period period, Norm norm, Date start, Date end) throws Exception {
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
    public List<EventNameValue> event(int idsite, Date start, Date end, int limit, int skip) throws Exception {
        Long visit = visitDao.countVisit(idsite, start, end);
        Long users = visitDao.countUsers(idsite, start, end);
        List<EventNameValue> events = eventDao.event(idsite, start, end, limit, skip);
        for (EventNameValue value : events) {
            value.setRn(value.getNum() * 1f / visit);
            value.setRu(value.getUser() * 1f / users);
        }
        return events;
    }

    @Override
    public List<EventPeriodValue> event(int idsite, String category, Period period, Date start, Date end, int limit, int skip) throws Exception {
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
    public List<PageValue> pagetitle(int idsite, Date start, Date end, int limit, int skip) throws Exception {
        List<PageValue> pagetitle = actionDao.pagetitle(idsite, start, end, limit, skip);
        pagetitle = titleDao.fullName(pagetitle);
        return pagetitle;
    }

    @Override
    public List<PageValue> pageurl(int idsite, Date start, Date end, int limit, int skip) throws Exception {
        List<PageValue> pageurl = actionDao.pageurl(idsite, start, end, limit, skip);
        pageurl = urlDao.fullName(pageurl);
        return pageurl;
    }

    @Override
    public List<VisitorValue> visitor(int idsite, Period period, Date start, Date end) throws Exception {
        List<VisitorValue> list = new ArrayList<>();
        switch (period) {
            case hour:
                list = visitDao.visitorHour(idsite, start, end);
                break;
            case day:
                list = visitDao.visitorDay(idsite, start, end);
                break;
            case week:
                list = visitDao.visitorWeek(idsite, start, end);
                break;
            case month:
                list = visitDao.visitorMonth(idsite, start, end);
                break;
        }
        for (VisitorValue value : list) {
            value.setOv(value.getUv() - value.getNv());
            value.setNr(1f * value.getNv() / value.getUv());
            value.setOr(1f * value.getOv() / value.getUv());
        }
        return list;
    }

    @Override
    public List<RankingValue> ranking(int idsite, Ranking ranking, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        switch (ranking) {
            case appname:
                return visitDao.appname(idsite, rankingtype, start, end, limit, skip);
            case brand:
                return visitDao.brand(idsite, rankingtype, start, end, limit, skip);
            case browser:
                return visitDao.browser(idsite, rankingtype, start, end, limit, skip);
            case city:
                return visitDao.city(idsite, rankingtype, start, end, limit, skip);
            case country:
                return visitDao.country(idsite, rankingtype, start, end, limit, skip);
            case depth:
                return visitDao.depth(idsite, rankingtype, start, end, limit, skip);
            case lang:
                return visitDao.lang(idsite, rankingtype, start, end, limit, skip);
            case model:
                return visitDao.model(idsite, rankingtype, start, end, limit, skip);
            case nettype:
                return visitDao.nettype(idsite, rankingtype, start, end, limit, skip);
            case province:
                return visitDao.province(idsite, rankingtype, start, end, limit, skip);
            case resolution:
                return visitDao.resolution(idsite, rankingtype, start, end, limit, skip);
            case system:
                return visitDao.system(idsite, rankingtype, start, end, limit, skip);
        }
        return new ArrayList<>();
    }

    public List<VisitValue> visit(int idsite, Period period, Date start, Date end) throws Exception {
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

    public List<VisitValue> pageView(int idsite, Period period, Date start, Date end) throws Exception {
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

    public List<VisitValue> uniqueVisitor(int idsite, Period period, Date start, Date end) throws Exception {
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

    public List<VisitValue> internetProtocol(int idsite, Period period, Date start, Date end) throws Exception {
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
