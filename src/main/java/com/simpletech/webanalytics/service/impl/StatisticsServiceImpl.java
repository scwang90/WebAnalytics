package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.*;
import com.simpletech.webanalytics.mapper.api.StatisticsMapper;
import com.simpletech.webanalytics.model.ShareLinePoint;
import com.simpletech.webanalytics.model.ShareStartPoint;
import com.simpletech.webanalytics.model.constant.*;
import com.simpletech.webanalytics.model.entity.*;
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
    @Autowired
    StatisticsMapper mapper;

    @Override
    public List<VisitTrendValue> visitTrend(String idsite, Period period, Date start, Date end) {
        switch (period) {
            case hour:
                return mapper.visitTrendHour(idsite, start, end);
            case day:
                return mapper.visitTrendDay(idsite, start, end);
            case week:
                return mapper.visitTrendWeek(idsite, start, end);
            case month:
                return mapper.visitTrendMonth(idsite, start, end);
        }
        return new ArrayList<>();
    }

    @Override
    public VisitSpanValue visitSpan(String idsite, Date start, Date end) {
        return mapper.visitSpan(idsite, start, end);
    }

    @Override
    public List<VisitTrendValue> pageTrend(String idsite, String idurl, Period period, Date start, Date end) {
        switch (period) {
            case hour:
                return mapper.pageTrendHour(idsite, idurl, start, end);
            case day:
                return mapper.pageTrendDay(idsite, idurl, start, end);
            case week:
                return mapper.pageTrendWeek(idsite, idurl, start, end);
            case month:
                return mapper.pageTrendMonth(idsite, idurl, start, end);
        }
        return new ArrayList<>();
    }

    @Override
    public VisitSpanValue pageSpan(String idsite, String idurl, Date start, Date end) {
        return mapper.pageSpan(idsite, idurl, start, end);
    }

    @Override
    public List<VisitTimeMapValue> visitTimeMap(String idsite, TimeType type, Date start, Date end) {
        List<VisitTimeMapValue> list = new ArrayList<>();
        switch (type) {
            case server:
                list = mapper.visitServerTimeMap(idsite, start, end);
                break;
            case local:
                list = mapper.visitLocalTimeMap(idsite, start, end);
                break;
        }
        return list;
    }
    @Override
    public List<VisitTimeMapValue> pageTimeMap(String idsite, String idurl, TimeType type, Date start, Date end) {
        List<VisitTimeMapValue> list = new ArrayList<>();
        switch (type) {
            case server:
                list = mapper.pageServerTimeMap(idsite, idurl, start, end);
                break;
            case local:
                list = mapper.pageLocalTimeMap(idsite, idurl, start, end);
                break;
        }
        return list;
    }

    @Override
    public List<MapValue> visitPageMap(String idsite, VisitPageType type, String _map, Date start, Date end) {
        List<MapValue> list = new ArrayList<>();

        _map = "" + _map;
        _map = _map.matches("(\\d+,)+\\d+") ? _map : "1,2,5,10";
        _map = _map + "," + Integer.MAX_VALUE;
        String[] maps = _map.split(",");
        int lastValue = 1;
        for (String map : maps) {
            MapValue value = null;
            switch (type) {
                case view:
                    value = mapper.visitPageViewMap(idsite, lastValue, Integer.parseInt(map), start, end);
                    break;
                case unique:
                    value = mapper.visitPageUniqueMap(idsite, lastValue, Integer.parseInt(map), start, end);
                    break;
            }
            value.map(lastValue, Integer.valueOf(map), "页");

            if (map.equals(String.valueOf(Integer.MAX_VALUE))) {
                if (!value.isEmpty()) {
                    list.add(value);
                }
            } else {
                list.add(value);
            }
            lastValue = Integer.parseInt(map) + 1;
        }
        return list;
    }

    @Override
    public EventSpanValue eventSpan(String idsite, Date start, Date end) {
        return mapper.eventSpan(idsite, start, end);
    }

    @Override
    public List<EventNameValue> eventRank(String idsite, Date start, Date end, int limit, int skip) {
        return mapper.eventRank(idsite, start, end, limit, skip);
    }

    @Override
    public List<EventTrendValue> eventTrend(String idsite, Period period, Date start, Date end) {
        List<EventTrendValue> events = new ArrayList<>();
        switch (period) {
            case hour:
                events = mapper.eventTrendHour(idsite, start, end);
                break;
            case day:
                events = mapper.eventTrendDay(idsite, start, end);
                break;
            case week:
                events = mapper.eventTrendWeek(idsite, start, end);
                break;
            case month:
                events = mapper.eventTrendMonth(idsite, start, end);
                break;
        }
        return events;
    }

    @Override
    public List<EventNameTrendValue> eventNameTrend(String idsite, String category, Period period, Date start, Date end, int limit, int skip) {
        List<EventNameTrendValue> events = new ArrayList<>();
        switch (period) {
            case hour:
                events = mapper.eventNameTrendHour(idsite, category, start, end, limit, skip);
                break;
            case day:
                events = mapper.eventNameTrendDay(idsite, category, start, end, limit, skip);
                break;
            case week:
                events = mapper.eventNameTrendWeek(idsite, category, start, end, limit, skip);
                break;
            case month:
                events = mapper.eventNameTrendMonth(idsite, category, start, end, limit, skip);
                break;
        }
//        Long visit = mapper.countVisit(idsite, start, end);
//        Long users = mapper.countUsers(idsite, start, end);
//        for (EventPeriodValue value : events) {
//            value.setRn(value.getNum() * 1f / visit);
//            value.setRu(value.getUser() * 1f / users);
//        }
        return events;
    }

    @Override
    public EventNameSpanValue eventNameSpan(String idsite, String name, Date start, Date end) {
        return mapper.eventNameSpan(idsite, name, start, end);
    }

    @Override
    public List<PageValue> titleurl(String idsite, PageRank type, RankingType ranktype, Date start, Date end, int limit, int skip) {
        List<PageValue> list = new ArrayList<>();
        switch (type) {
            case title:
                list = mapper.pagetitle(idsite, ranktype.name(), start, end, limit, skip);
                break;
            case url:
                list = mapper.pageurl(idsite, ranktype.name(), start, end, limit, skip);
                break;
        }
        return list;
    }

    @Override
    public VisitorSpanValue visitorSpan(String idsite, Date start, Date end) {
        VisitorSpanValue value = mapper.visitorSpan(idsite, start, end);
        //判断是否是主站
        boolean isSubSite = !idsite.matches("\\d+");
        if (isSubSite) {
            value.setNv(value.getSubnv());
        }
        value.setOv(value.getUv() - value.getNv());
        value.setNr(1f * value.getNv() / value.getUv());
        value.setOr(1f * value.getOv() / value.getUv());
        return value;
    }

    @Override
    public List<VisitorTrendValue> visitorTrend(String idsite, Period period, Date start, Date end) {
        List<VisitorTrendValue> list = new ArrayList<>();
        switch (period) {
            case hour:
                list = mapper.visitorTrendHour(idsite, start, end);
                break;
            case day:
                list = mapper.visitorTrendDay(idsite, start, end);
                break;
            case week:
                list = mapper.visitorTrendWeek(idsite, start, end);
                break;
            case month:
                list = mapper.visitorTrendMonth(idsite, start, end);
                break;
        }
        //判断是否是主站
        boolean isSubSite = !idsite.matches("\\d+");
        for (VisitorTrendValue value : list) {
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
    public List<RankingValue> ranking(String idsite, Ranking ranking, RankingType ranktype, Date start, Date end, int limit, int skip) {
        switch (ranking) {
            case appname:
                return dao.rankAppname(idsite, ranktype, start, end, limit, skip);
            case brand:
                return dao.rankBrand(idsite, ranktype, start, end, limit, skip);
            case browser:
                return dao.rankBrowser(idsite, ranktype, start, end, limit, skip);
            case city:
                return dao.rankCity(idsite, ranktype, start, end, limit, skip);
            case ip:
                return dao.rankIp(idsite, ranktype, start, end, limit, skip);
            case isp:
                return dao.rankIsp(idsite, ranktype, start, end, limit, skip);
            case country:
                return dao.rankCountry(idsite, ranktype, start, end, limit, skip);
            case depth:
                return dao.rankDepth(idsite, ranktype, start, end, limit, skip);
            case lang:
                return dao.rankLang(idsite, ranktype, start, end, limit, skip);
            case model:
                return dao.rankModel(idsite, ranktype, start, end, limit, skip);
            case nettype:
                return dao.rankNettype(idsite, ranktype, start, end, limit, skip);
            case province:
                return dao.rankProvince(idsite, ranktype, start, end, limit, skip);
            case resolution:
                return dao.rankResolution(idsite, ranktype, start, end, limit, skip);
            case system:
                return dao.rankSystem(idsite, ranktype, start, end, limit, skip);
        }
        return new ArrayList<>();
    }

    @Override
    public List<RankingValue> pageRank(String idsite, String idurl, Ranking rank, RankingType ranktype, Date start, Date end, int limit, int skip) {
        switch (rank) {
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
            case isp:
                return dao.pageIsp(idsite, idurl, ranktype, start, end, limit, skip);
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
    public Map<String, Object> shareMap(String idsite, String urlId, Date start, Date end) {
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
                    referpoint.setSh(referpoint.getSh() + 1);
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
            if (point != null) {
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
        Collections.sort(points, new Comparator<MapPointValue>() {
            @Override
            public int compare(MapPointValue o1, MapPointValue o2) {
                return Integer.compare(o2.getSh(), o1.getSh());
            }
        });
        return map;
    }

    @Override
    public List<PageRankValue> shareRank(String idsite, Date start, Date end, int limit, int skip) {
        return mapper.shareRank(idsite, start, end, limit, skip);
    }

    @Override
    public List<EnterCloseValue> enterexit(String idsite, EnterExit type, RankingType ranktype, Date start, Date end, int limit, int skip) {
        switch (type) {
            case entry:
                return mapper.entryUrls(idsite, ranktype.name(), start, end, limit, skip);
            case exit:
                return mapper.exitUrls(idsite, ranktype.name(), start, end, limit, skip);
        }
        return new ArrayList<>();
    }
}
