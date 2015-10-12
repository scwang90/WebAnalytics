package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.*;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.constant.Ranking;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.*;
import com.simpletech.webanalytics.service.SiteService;
import com.simpletech.webanalytics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 统计API Service 实现
 * Created by 树朾 on 2015/9/25.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsDao dao;

    @Override
    public List<VisitValue> visit(String idsite, Period period, Date start, Date end) throws Exception {
        switch (period) {
            case hour:
                return dao.visitHour(idsite, start, end);
            case day:
                return dao.visitDay(idsite, start, end);
            case week:
                return dao.visitWeek(idsite, start, end);
            case month:
                return dao.visitMonth(idsite, start, end);
        }
        return new ArrayList<>();
    }

    @Override
    public List<EventNameValue> event(String idsite, Date start, Date end, int limit, int skip) throws Exception {
        Long visit = dao.countVisit(idsite, start, end);
        Long users = dao.countUsers(idsite, start, end);
        List<EventNameValue> events = dao.event(idsite, start, end, limit, skip);
        for (EventNameValue value : events) {
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
                events = dao.eventHour(idsite, category, start, end, limit, skip);
                break;
            case day:
                events = dao.eventDay(idsite, category, start, end, limit, skip);
                break;
            case week:
                events = dao.eventWeek(idsite, category, start, end, limit, skip);
                break;
            case month:
                events = dao.eventMonth(idsite, category, start, end, limit, skip);
                break;
        }
        Long visit = dao.countVisit(idsite, start, end);
        Long users = dao.countUsers(idsite, start, end);
        for (EventPeriodValue value : events) {
            value.setRn(value.getNum() * 1f / visit);
            value.setRu(value.getUser() * 1f / users);
        }
        return events;
    }

    @Override
    public List<PageValue> pagetitle(String idsite, Date start, Date end, int limit, int skip) throws Exception {
        List<PageValue> pagetitle = dao.pagetitle(idsite, start, end, limit, skip);
        pagetitle = dao.fullTitleName(pagetitle);
        return pagetitle;
    }

    @Override
    public List<PageValue> pageurl(String idsite, Date start, Date end, int limit, int skip) throws Exception {
        List<PageValue> pageurl = dao.pageurl(idsite, start, end, limit, skip);
        pageurl = dao.fullUrlName(pageurl);
        return pageurl;
    }

    @Override
    public List<VisitorValue> visitor(String idsite, Period period, Date start, Date end) throws Exception {
        List<VisitorValue> list = new ArrayList<>();
        switch (period) {
            case hour:
                list = dao.visitorHour(idsite, start, end);
                break;
            case day:
                list = dao.visitorDay(idsite, start, end);
                break;
            case week:
                list = dao.visitorWeek(idsite, start, end);
                break;
            case month:
                list = dao.visitorMonth(idsite, start, end);
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
    public List<RankingValue> ranking(String idsite, Ranking ranking, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception {
        switch (ranking) {
            case appname:
                return dao.appname(idsite, ranktype, start, end, limit, skip);
            case brand:
                return dao.brand(idsite, ranktype, start, end, limit, skip);
            case browser:
                return dao.browser(idsite, ranktype, start, end, limit, skip);
            case city:
                return dao.city(idsite, ranktype, start, end, limit, skip);
            case country:
                return dao.country(idsite, ranktype, start, end, limit, skip);
            case depth:
                return dao.depth(idsite, ranktype, start, end, limit, skip);
            case lang:
                return dao.lang(idsite, ranktype, start, end, limit, skip);
            case model:
                return dao.model(idsite, ranktype, start, end, limit, skip);
            case nettype:
                return dao.nettype(idsite, ranktype, start, end, limit, skip);
            case province:
                return dao.province(idsite, ranktype, start, end, limit, skip);
            case resolution:
                return dao.resolution(idsite, ranktype, start, end, limit, skip);
            case system:
                return dao.system(idsite, ranktype, start, end, limit, skip);
        }
        return new ArrayList<>();
    }

}
