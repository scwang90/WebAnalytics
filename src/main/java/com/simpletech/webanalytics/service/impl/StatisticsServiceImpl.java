package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.*;
import com.simpletech.webanalytics.mapper.api.StatisticsMapper;
import com.simpletech.webanalytics.model.ShareLinePoint;
import com.simpletech.webanalytics.model.constant.*;
import com.simpletech.webanalytics.model.entity.*;
import com.simpletech.webanalytics.service.StatisticsService;
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
    public List<VisitTrendValue> pageVisitTrend(String idsite, String idurl, Period period, Date start, Date end) {
        switch (period) {
            case hour:
                return mapper.pageVisitTrendHour(idsite, idurl, start, end);
            case day:
                return mapper.pageVisitTrendDay(idsite, idurl, start, end);
            case week:
                return mapper.pageVisitTrendWeek(idsite, idurl, start, end);
            case month:
                return mapper.pageVisitTrendMonth(idsite, idurl, start, end);
        }
        return new ArrayList<>();
    }

    @Override
    public VisitSpanValue pageVisitSpan(String idsite, String idurl, Date start, Date end) {
        return mapper.pageVisitSpan(idsite, idurl, start, end);
    }

    @Override
    public List<VisitTimeMapValue> visitTimeMap(String idsite, TimeType type, int days, Date start, Date end) {
        List<VisitTimeMapValue> list = new ArrayList<>();
        switch (type) {
            case server:
                list = mapper.visitServerTimeMap(idsite, start, end);
                break;
            case local:
                list = mapper.visitLocalTimeMap(idsite, start, end);
                break;
        }
        int tip = 0, tpv = 0, tuv = 0, tvt = 0, count = days > 0 ? days : 1;
        for (VisitTimeMapValue value : list) {
            value.setAvgip((int) Math.rint(1f * value.getIp() / count + 0.4));
            value.setAvgvt((int) Math.rint(1f * value.getVt() / count + 0.4));
            value.setAvguv((int) Math.rint(1f * value.getUv() / count + 0.4));
            value.setAvgpv((int) Math.rint(1f * value.getPv() / count + 0.4));
            tip += value.getAvgip();
            tvt += value.getAvgvt();
            tuv += value.getAvguv();
            tpv += value.getAvgpv();
        }
        for (VisitTimeMapValue value : list) {
            value.setRip(1f * value.getAvgip() / tip);
            value.setRvt(1f * value.getAvgvt() / tvt);
            value.setRuv(1f * value.getAvguv() / tuv);
            value.setRpv(1f * value.getAvgpv() / tpv);
        }
        for (int i = 0, index = 0; i < 24; i++, index++) {
            if (index == list.size() || list.get(index).getTime() > i) {
                VisitTimeMapValue tmp = new VisitTimeMapValue();
                tmp.setTime(i);
                list.add(index, tmp);
            }
        }
        return list;
    }

    @Override
    public List<VisitTimeMapValue> pageTimeMap(String idsite, String idurl, TimeType type, int days, Date start, Date end) {
        List<VisitTimeMapValue> list = new ArrayList<>();
        switch (type) {
            case server:
                list = mapper.pageServerTimeMap(idsite, idurl, start, end);
                break;
            case local:
                list = mapper.pageLocalTimeMap(idsite, idurl, start, end);
                break;
        }
        int tip = 0, tpv = 0, tuv = 0, tvt = 0, count = days > 0 ? days : 1;
        for (VisitTimeMapValue value : list) {
            value.setAvgip((int) Math.rint(1f * value.getIp() / count + 0.4));
            value.setAvgvt((int) Math.rint(1f * value.getVt() / count + 0.4));
            value.setAvguv((int) Math.rint(1f * value.getUv() / count + 0.4));
            value.setAvgpv((int) Math.rint(1f * value.getPv() / count + 0.4));
            tip += value.getAvgip();
            tvt += value.getAvgvt();
            tuv += value.getAvguv();
            tpv += value.getAvgpv();
        }
        for (VisitTimeMapValue value : list) {
            value.setRip(1f * value.getAvgip() / tip);
            value.setRvt(1f * value.getAvgvt() / tvt);
            value.setRuv(1f * value.getAvguv() / tuv);
            value.setRpv(1f * value.getAvgpv() / tpv);
        }
        for (int i = 0, index = 0; i < 24; i++, index++) {
            if (index == list.size() || list.get(index).getTime() > i) {
                VisitTimeMapValue tmp = new VisitTimeMapValue();
                tmp.setTime(i);
                list.add(index, tmp);
            }
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
    public int eventRankCount(String idsite, Date start, Date end) {
        return mapper.eventRankCount(idsite, start, end);
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
    public List<PageValue> titleurlRank(String idsite, PageRank type, RankingType ranktype, Date start, Date end, int limit, int skip) {
        List<PageValue> list = new ArrayList<>();
        switch (type) {
            case title:
                list = mapper.pageTitleRank(idsite, ranktype.name(), start, end, limit, skip);
                break;
            case url:
                list = mapper.pageUrlRank(idsite, ranktype.name(), start, end, limit, skip);
                break;
        }
        return list;
    }

    @Override
    public int titleurlRankCount(String idsite, PageRank type, Date start, Date end) {
        switch (type) {
            case title:
                return mapper.pageTitleCount(idsite, start, end);
            case url:
                return mapper.pageUrlCount(idsite, start, end);
        }
        return 0;
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
        value.setNr((value.getNv() > 0) ? 1f * value.getNv() / value.getUv() : 0);
        value.setOr((value.getOv() > 0) ? 1f * value.getOv() / value.getUv() : 0);
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
    public List<RankingValue> siteRank(String idsite, Ranking ranking, RankingType ranktype, Date start, Date end, int limit, int skip) {
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
    public int siteRankCount(String idsite, Ranking ranking, Date start, Date end) {
        switch (ranking) {
            case appname:
                return mapper.rankCountAppname(idsite, start, end);
            case brand:
                return mapper.rankCountBrand(idsite, start, end);
            case browser:
                return mapper.rankCountBrowser(idsite, start, end);
            case city:
                return mapper.rankCountCity(idsite, start, end);
            case ip:
                return mapper.rankCountIp(idsite, start, end);
            case isp:
                return mapper.rankCountIsp(idsite, start, end);
            case country:
                return mapper.rankCountCountry(idsite, start, end);
            case depth:
                return mapper.rankCountDepth(idsite, start, end);
            case lang:
                return mapper.rankCountLang(idsite, start, end);
            case model:
                return mapper.rankCountModel(idsite, start, end);
            case nettype:
                return mapper.rankCountNettype(idsite, start, end);
            case province:
                return mapper.rankCountProvince(idsite, start, end);
            case resolution:
                return mapper.rankCountResolution(idsite, start, end);
            case system:
                return mapper.rankCountSystem(idsite, start, end);
        }
        return 0;
    }

    @Override
    public PageUserShare pageUserShare(String idsite, String urlId, String openid, Date start, Date end) {
        PageUserShare share = mapper.pageUserShare(idsite, urlId, openid, start, end);
        try {
            share.setSex(WxSexType.values()[Integer.parseInt(share.getSex())].value);
        } catch (Throwable ex) {
            share.setSex(WxSexType.nuknow.value);
        }
        return share;
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
    public int pageRankCount(String idsite, String idurl, Ranking rank, Date start, Date end) {
        switch (rank) {
            case appname:
                return mapper.pageCountAppname(idsite, idurl, start, end);
            case brand:
                return mapper.pageCountBrand(idsite, idurl, start, end);
            case browser:
                return mapper.pageCountBrowser(idsite, idurl, start, end);
            case city:
                return mapper.pageCountCity(idsite, idurl, start, end);
            case ip:
                return mapper.pageCountIp(idsite, idurl, start, end);
            case isp:
                return mapper.pageCountIsp(idsite, idurl, start, end);
            case country:
                return mapper.pageCountCountry(idsite, idurl, start, end);
            case depth:
                return mapper.pageCountDepth(idsite, idurl, start, end);
            case lang:
                return mapper.pageCountLang(idsite, idurl, start, end);
            case model:
                return mapper.pageCountModel(idsite, idurl, start, end);
            case nettype:
                return mapper.pageCountNettype(idsite, idurl, start, end);
            case province:
                return mapper.pageCountProvince(idsite, idurl, start, end);
            case resolution:
                return mapper.pageCountResolution(idsite, idurl, start, end);
            case system:
                return mapper.pageCountSystem(idsite, idurl, start, end);
        }
        return 0;
    }

    @Override
    public ShareSpanValue shareSpan(String idsite, String idurl, Date start, Date end) {
        return mapper.shareSpan(idsite, idurl, start, end);
    }

    @Override
    public List<ShareTrendValue> shareTrend(String idsite, String idurl, Period period, Date start, Date end) {
        List<ShareTrendValue> list = new ArrayList<>();
        switch (period) {
            case hour:
                list = mapper.shareTrendHour(idsite, idurl, start, end);
                break;
            case day:
                list = mapper.shareTrendDay(idsite, idurl, start, end);
                break;
            case week:
                list = mapper.shareTrendWeek(idsite, idurl, start, end);
                break;
            case month:
                list = mapper.shareTrendMonth(idsite, idurl, start, end);
                break;
        }
        return list;
    }

    @Override
    public List<ShareRankValue> shareRank(String idsite, Date start, Date end, int limit, int skip) {
        return mapper.shareRank(idsite, start, end, limit, skip);
    }

    @Override
    public int shareRankCount(String idsite, Date start, Date end) {
        return mapper.shareRankCount(idsite, start, end);
    }

    @Override
    public List<EnterCloseValue> enterexitRank(String idsite, EnterExit type, RankingType ranktype, Date start, Date end, int limit, int skip) {
        switch (type) {
            case entry:
                return mapper.entryUrlsRank(idsite, ranktype.name(), start, end, limit, skip);
            case exit:
                return mapper.exitUrlsRank(idsite, ranktype.name(), start, end, limit, skip);
        }
        return new ArrayList<>();
    }

    @Override
    public int enterexitRankCount(String idsite, EnterExit type, Date start, Date end) {
        switch (type) {
            case entry:
                return mapper.entryUrlsRankCount(idsite, start, end);
            case exit:
                return mapper.exitUrlsRankCount(idsite, start, end);
        }
        return 0;
    }

    @Override
    public List<ShareToRankValue> shareToRank(String idsite, String url, RankingType type, Date start, Date end) {
        List<ShareToRankValue> list = mapper.shareToRank(idsite, url, type.name(), start, end);
        for (ShareToRankValue value : list) {
            for (ShareToType shareToType : ShareToType.values()) {
                if (shareToType.name().equals(value.getName())) {
                    value.setName(shareToType.value);
                }
            }
        }
        return list;
    }

    @Override
    public List<ShareSexRankValue> siteSexRank(String idsite, RankingType ranktype, Date start, Date end) {
        List<ShareSexRankValue> list = mapper.siteSexRank(idsite, ranktype.name(), start, end);
        int tpv = 0, tuv = 0;
        for (ShareSexRankValue value : list) {
            for (WxSexType wxSexType : WxSexType.values()) {
                if (("" + value.getSex()).equals("" + wxSexType.ordinal())) {
                    value.setName(wxSexType.value);
                }
            }
            tpv += value.getPv();
            tuv += value.getUv();
        }
        tpv = tpv > 0 ? tpv : 1;
        tuv = tuv > 0 ? tuv : 1;
        for (ShareSexRankValue value : list) {
            value.setRpv(1f * value.getPv() / tpv);
            value.setRuv(1f * value.getUv() / tuv);
        }
        return list;
    }

    @Override
    public List<ShareSexRankValue> shareSexRank(String idsite, String urlId, RankingType ranktype, Date start, Date end) {
        List<ShareSexRankValue> list = mapper.shareSexRank(idsite, urlId, ranktype.name(), start, end);
        int tpv = 0, tuv = 0;
        for (ShareSexRankValue value : list) {
            for (WxSexType wxSexType : WxSexType.values()) {
                if (("" + value.getSex()).equals("" + wxSexType.ordinal())) {
                    value.setName(wxSexType.value);
                }
            }
            tpv += value.getPv();
            tuv += value.getUv();
        }
        tpv = tpv > 0 ? tpv : 1;
        tuv = tuv > 0 ? tuv : 1;
        for (ShareSexRankValue value : list) {
            value.setRpv(1f * value.getPv() / tpv);
            value.setRuv(1f * value.getUv() / tuv);
        }
        return list;
    }

    @Override
    public Map<String, Object> shareMap(String idsite, String urlId, Date start, Date end) {
        List<ShareLinePoint> list = dao.sharePoint(idsite, urlId, start, end);
        List<MapLineValue> lines = new ArrayList<>();
        List<MapPointValue> points = new ArrayList<>();
        List<Set<String>> levels = Arrays.asList(new HashSet<String>(), new HashSet<String>(), new HashSet<String>(), (Set<String>) new HashSet<String>());
        Map<String, MapPointValue> mpoints = new HashMap<>();
        //生成叶子点并排重
        for (ShareLinePoint sharePoint : list) {
            MapPointValue point = mpoints.get(sharePoint.getIdvisitor());
            if (point == null) {
                point = new MapPointValue();
                point.setId(sharePoint.getIdvisitor());
                point.setPv(sharePoint.getCountPv());
                point.setUv(1);
                point.setCl(2);//判断点类型 class 0 起始点 2 叶子点
                point.setMk("用户" + (mpoints.size() + 1));
                point.opv = point.getPv();
                point.ouv = point.getUv();
                mpoints.put(sharePoint.getIdvisitor(), point);
            } else {
                point.setPv(point.getPv() + sharePoint.getCountPv());
                point.setUv(point.getUv() + 1);
                point.opv = point.getPv();
                point.ouv = point.getUv();
            }
        }
        //生成线和起始节点
        for (ShareLinePoint sharePoint : list) {
            MapLineValue line = new MapLineValue();
            line.setsId(sharePoint.getIdrefervisitor());
            line.seteId(sharePoint.getIdvisitor());
            line.setSts(sharePoint.getShareSpan());
            lines.add(line);

            MapPointValue point = mpoints.get(sharePoint.getIdrefervisitor());
            if (point == null) {
                point = new MapPointValue();
                point.setId(sharePoint.getIdrefervisitor());
                point.setPv(1);
                point.setUv(1);
                point.setCl(0);//判断点类型 class 0 起始点 2 叶子点
                point.setMk("用户" + (mpoints.size() + 1));
                mpoints.put(sharePoint.getIdrefervisitor(), point);
                point.opv = point.getPv();
                point.ouv = point.getUv();
            }
            if (point.isStart || Boolean.valueOf(true).equals(sharePoint.getIsStartPoint())) {
                point.setCl(0);//判断点类型 class 0 起始点 2 叶子点
                point.isStart = true;
            }
            if (point.getCl() == 0) {
                levels.get(0).add(point.getId());
            }
        }
        //统计下层数据
        for (ShareLinePoint sharePoint : list) {
            MapPointValue referpoint = mpoints.get(sharePoint.getIdrefervisitor());
            MapPointValue currpoint = mpoints.get(sharePoint.getIdvisitor());
            if (referpoint != null) {
                //改变叶子节点为中间节点
                //判断点类型 class 0 起始点 2 叶子点 1 中间节点
                if (!referpoint.isStart) {
                    referpoint.setCl(1);
                }
                referpoint.setSh(referpoint.getSh() + 1);
                if (currpoint != null) {
                    referpoint.setPv(referpoint.getPv() + currpoint.opv);
                    referpoint.setUv(referpoint.getUv() + currpoint.ouv);
                } else {
                    referpoint.setPv(referpoint.getPv() + 1);
                    referpoint.setUv(referpoint.getUv() + 1);
                }
            }
        }
        //层级统计
        List<Map<String, Object>> levelmap = new ArrayList<>();
        for (int i = 0; i < levels.size() - 1; i++) {
            Set<String> level = levels.get(i);
            for (String id : level) {
                for (MapLineValue line : lines) {
                    if (line.getsId().equals(id)) {
                        levels.get(i + 1).add(line.geteId());
                    }
                }
            }
            HashMap map = new HashMap<>();
            map.put("level", i);
            map.put("pv", level.size());
            levelmap.add(map);
            if (i == levels.size() - 2) {
                map = new HashMap<>();
                map.put("level", i + 1);
                map.put("pv", levels.get(i + 1).size());
                levelmap.add(map);
            }
        }

        //装载点列表 然后清空点map
        for (Map.Entry<String, MapPointValue> entry : mpoints.entrySet()) {
            points.add(entry.getValue());
        }
        mpoints.clear();
        //点列表排序
        Collections.sort(points, new Comparator<MapPointValue>() {
            @Override
            public int compare(MapPointValue o1, MapPointValue o2) {
                return Integer.compare(o2.getSh(), o1.getSh());
            }
        });
        //填充用户信息
        dao.fullShareUserInfo(points, 20);
        Map<String, Object> map = new HashMap<>();
        map.put("lines", lines);
        map.put("points", points);
        map.put("levels", levelmap);
        return map;
    }
}
