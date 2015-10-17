package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.*;
import com.simpletech.webanalytics.model.SharePoint;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.constant.Ranking;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.*;
import com.simpletech.webanalytics.service.SiteService;
import com.simpletech.webanalytics.service.StatisticsService;
import com.simpletech.webanalytics.util.AfStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        //判断是否是主站
        boolean isSubSite = !idsite.matches("\\d+");
        for (VisitorValue value : list) {
            if (isSubSite) {
                value.setNv(value.getSubnv());
            }
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

    @Override
    public Object sharemap(String idsite, String urlId, Date start, Date end) throws Exception {
        List<SharePoint> list = dao.sharePoint(idsite, urlId, start, end);
        List<Map<String, Object>> lines = new ArrayList<>();
        List<Map<String, Object>> points = new ArrayList<>();
        Map<String, Map<String, Object>> mpoints = new HashMap<>();
        //点排重
        for (SharePoint sharePoint : list) {
            Map<String, Object> point = mpoints.get(sharePoint.getIdvisitor());
            if (point == null) {
                point = new HashMap<>();
                point.put("Id", sharePoint.getIdvisitor());
                point.put("pv", sharePoint.getCountPv());
                //判断点类型 class 0 起始点 2 叶子点
                point.put("cl", 2 );
                mpoints.put(sharePoint.getIdvisitor(), point);
            } else {
                point.put("pv", (Integer) point.get("pv") + sharePoint.getCountPv());
                //判断点类型 class 0 起始点 2 叶子点
                if (Integer.valueOf(0).equals(point.get("cl"))) {
                    point.put("cl", 2 );
                }
            }
        }
        //生成线
        for (SharePoint sharePoint : list) {
            //判断有前一节点并且前一节点存在才生成线
            if (AfStringUtil.isNotEmpty(sharePoint.getIdrefervisitor())) {
                Map<String, Object> line = new HashMap<>();
                line.put("sId", sharePoint.getIdrefervisitor());
                line.put("eId", sharePoint.getIdvisitor());
                line.put("sts", sharePoint.getShareSpan());
                lines.add(line);

                Map<String, Object> point = mpoints.get(sharePoint.getIdrefervisitor());
                if (point != null) {
                    //改变叶子节点为中间节点
                    //判断点类型 class 0 起始点 2 叶子点 1 中间节点
                    point.put("cl", 1);
                }
            }
        }
        //生成起始点
        for (SharePoint sharePoint : list) {
            Map<String, Object> point = mpoints.get(sharePoint.getIdrefervisitor());
            if (point == null) {
                point = new HashMap<>();
                point.put("Id", sharePoint.getIdrefervisitor());
                point.put("pv", 1);
                //判断点类型 class 0 起始点 2 叶子点
                point.put("cl", 0);
                mpoints.put(sharePoint.getIdvisitor(), point);
            }
        }
        for (Map.Entry<String, Map<String, Object>> entry : mpoints.entrySet()) {
            points.add(entry.getValue());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("lines", lines);
        map.put("lines", points);
        return map;
    }
}
