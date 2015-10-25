package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.*;
import com.simpletech.webanalytics.model.ShareLinePoint;
import com.simpletech.webanalytics.model.ShareStartPoint;
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
    public List<PageValue> pagetitle(String idsite, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception {
        List<PageValue> pagetitle = dao.pagetitle(idsite, ranktype, start, end, limit, skip);
        pagetitle = dao.fullTitleName(pagetitle);
        return pagetitle;
    }

    @Override
    public List<PageValue> pageurl(String idsite, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception {
        List<PageValue> pageurl = dao.pageurl(idsite, ranktype, start, end, limit, skip);
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
            case ip:
                return dao.ip(idsite, ranktype, start, end, limit, skip);
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
    public List<RankingValue> pageRanking(String idsite, String idurl, Ranking ranking, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception {
        switch (ranking) {
            case appname:
                return dao.pageAppname(idsite, idurl, ranktype, start, end, limit, skip);
            case brand:
                return dao.pageBrand(idsite, idurl, ranktype, start, end, limit, skip);
            case browser:
                return dao.pageBrowser(idsite, idurl, ranktype, start, end, limit, skip);
            case city:
                return dao.pageCity(idsite, idurl, ranktype, start, end, limit, skip);
            case ip:
                return dao.pageIp(idsite, idurl, ranktype, start, end, limit, skip);
            case country:
                return dao.pageCountry(idsite, idurl, ranktype, start, end, limit, skip);
            case depth:
                return dao.pageDepth(idsite, idurl, ranktype, start, end, limit, skip);
            case lang:
                return dao.pageLang(idsite, idurl, ranktype, start, end, limit, skip);
            case model:
                return dao.pageModel(idsite, idurl, ranktype, start, end, limit, skip);
            case nettype:
                return dao.pageNettype(idsite, idurl, ranktype, start, end, limit, skip);
            case province:
                return dao.pageProvince(idsite, idurl, ranktype, start, end, limit, skip);
            case resolution:
                return dao.pageResolution(idsite, idurl, ranktype, start, end, limit, skip);
            case system:
                return dao.pageSystem(idsite, idurl, ranktype, start, end, limit, skip);
        }
        return new ArrayList<>();
    }

    @Override
    public Map<String,Object> sharemap(String idsite, String urlId, Date start, Date end) throws Exception {
        List<ShareLinePoint> list = dao.sharePoint(idsite, urlId, start, end);
        List<MapLineValue> lines = new ArrayList<>();
        List<MapPointValue> points = new ArrayList<>();
        Map<String, MapPointValue> mpoints = new HashMap<>();
        //点排重
        for (ShareLinePoint sharePoint : list) {
            MapPointValue point = mpoints.get(sharePoint.getIdvisitor());
            if (point == null) {
                point = new MapPointValue();
                point.setId(sharePoint.getIdvisitor());
                point.setPv(sharePoint.getCountPv());
                point.setMk("用户" + (mpoints.size() + 1));
                point.setCl(2);//判断点类型 class 0 起始点 2 叶子点
                mpoints.put(sharePoint.getIdvisitor(), point);
            } else {
                point.setPv(point.getPv() + sharePoint.getCountPv());
            }
        }
        //生成线
        for (ShareLinePoint sharePoint : list) {
            //判断有前一节点并且前一节点存在才生成线
            if (AfStringUtil.isNotEmpty(sharePoint.getIdrefervisitor())) {
                MapLineValue line = new MapLineValue();
                line.setsId(sharePoint.getIdrefervisitor());
                line.seteId(sharePoint.getIdvisitor());
                line.setSts(sharePoint.getShareSpan());
                lines.add(line);

                MapPointValue referpoint = mpoints.get(sharePoint.getIdrefervisitor());
                if (referpoint != null) {
                    //改变叶子节点为中间节点
                    //判断点类型 class 0 起始点 2 叶子点 1 中间节点
                    referpoint.setCl(1);
                }
            }
        }
        //生成起始点
        for (ShareLinePoint sharePoint : list) {
            MapPointValue point = mpoints.get(sharePoint.getIdrefervisitor());
            if (point == null) {
                point = new MapPointValue();
                point.setId(sharePoint.getIdrefervisitor());
                point.setPv(1);
                point.setMk("用户" + (mpoints.size() + 1));
                point.setCl(0);//判断点类型 class 0 起始点 2 叶子点

                mpoints.put(sharePoint.getIdrefervisitor(), point);
            }
        }
        List<ShareStartPoint> startPoints = dao.getShareStartPoint(idsite, urlId, start, end);
        for (ShareStartPoint startPoint : startPoints) {
            MapPointValue point = mpoints.get(startPoint.getIdvisitor());
            if (point == null) {
                point.setCl(0);//判断点类型 class 0 起始点 2 叶子点
            }
        }
        for (Map.Entry<String, MapPointValue> entry : mpoints.entrySet()) {
            points.add(entry.getValue());
        }
        dao.fullNickName(points);
        Map<String, Object> map = new HashMap<>();
        map.put("lines", lines);
        map.put("points", points);
        return map;
    }

    @Override
    public List<PageRankingValue> shareRanking(String idsite, Date start, Date end) throws Exception {
        return dao.shareRanking(idsite, start, end);
    }
}
