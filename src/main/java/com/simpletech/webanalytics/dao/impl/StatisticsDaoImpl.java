package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.StatisticsDao;
import com.simpletech.webanalytics.mapper.StatisticsMapper;
import com.simpletech.webanalytics.model.Title;
import com.simpletech.webanalytics.model.Url;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.*;
import com.simpletech.webanalytics.util.AfStringUtil;
import com.webanalytics.useragent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库表t_url的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class StatisticsDaoImpl implements StatisticsDao {

    @Autowired
    StatisticsMapper mapper;

    @Override
    public List<VisitValue> visitHour(String idsite, Date start, Date end) throws Exception {
        return mapper.visitHour(idsite, start, end);
    }

    @Override
    public List<VisitValue> visitDay(String idsite, Date start, Date end) throws Exception {
        return mapper.visitDay(idsite, start, end);
    }

    @Override
    public List<VisitValue> visitWeek(String idsite, Date start, Date end) throws Exception {
        return mapper.visitWeek(idsite, start, end);
    }

    @Override
    public List<VisitValue> visitMonth(String idsite, Date start, Date end) throws Exception {
        return mapper.visitMonth(idsite, start, end);
    }

    @Override
    public List<VisitorValue> visitorHour(String idsite, Date start, Date end) throws Exception {
        return mapper.visitorHour(idsite, start, end);
    }

    @Override
    public List<VisitorValue> visitorDay(String idsite, Date start, Date end) throws Exception {
        return mapper.visitorDay(idsite, start, end);
    }

    @Override
    public List<VisitorValue> visitorWeek(String idsite, Date start, Date end) throws Exception {
        return mapper.visitorWeek(idsite, start, end);
    }

    @Override
    public List<VisitorValue> visitorMonth(String idsite, Date start, Date end) throws Exception {
        return mapper.visitorMonth(idsite, start, end);
    }

    @Override
    public List<RankingValue> brand(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.brand(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            Brand venum = Brand.parserAcronym(value.getName());
            value.setName(venum.getName());
            value.setRemark(venum.getRemark());
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<RankingValue> model(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.model(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())){
                value.setName(Brand.Unknown.name());
                value.setRemark(Brand.Unknown.getRemark());
            } else {
                value.setRemark("");
            }
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<RankingValue> nettype(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.nettype(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            Nettype venum = Nettype.parserAcronym(value.getName());
            value.setName(venum.getName());
            value.setRemark(venum.getRemark());
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<RankingValue> browser(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.browser(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            Browser venum = Browser.parserAcronym(value.getName());
            value.setName(venum.getName());
            value.setRemark(venum.getRemark());
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<RankingValue> system(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.system(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            OperateSystem venum = OperateSystem.parserAcronym(value.getName());
            value.setName(venum.getName());
            value.setRemark(venum.getRemark());
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<RankingValue> appname(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.appname(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            Application venum = Application.parserAcronym(value.getName());
            value.setName(venum.getName());
            value.setRemark(venum.getRemark());
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<RankingValue> resolution(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.resolution(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())){
                value.setName(Brand.Unknown.name());
                value.setRemark(Brand.Unknown.getRemark());
            } else {
                value.setRemark("");
            }
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<RankingValue> depth(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.depth(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())){
                value.setName(Brand.Unknown.name());
                value.setRemark(Brand.Unknown.getRemark());
            } else {
                value.setRemark("");
            }
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<RankingValue> lang(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.lang(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())){
                value.setName(Brand.Unknown.name());
                value.setRemark(Brand.Unknown.getRemark());
            } else {
                value.setRemark("");
            }
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<RankingValue> country(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.country(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())){
                value.setName(Brand.Unknown.name());
                value.setRemark(Brand.Unknown.getRemark());
            } else {
                value.setRemark("");
            }
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<RankingValue> province(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.province(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())){
                value.setName(Brand.Unknown.name());
                value.setRemark(Brand.Unknown.getRemark());
            } else {
                value.setRemark("");
            }
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<RankingValue> city(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutranking(idsite, start, end);
        List<RankingValue> list = mapper.city(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())){
                value.setName(Brand.Unknown.name());
                value.setRemark(Brand.Unknown.getRemark());
            } else {
                value.setRemark("");
            }
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public Long countVisit(String idsite, Date start, Date end) throws Exception {
        return mapper.countVisit(idsite, start, end);
    }

    @Override
    public Long countUsers(String idsite, Date start, Date end) throws Exception {
        return mapper.countUsers(idsite, start, end);
    }

    @Override
    public List<PageValue> pagetitle(String idsite, Date start, Date end, int limit, int skip) throws Exception {
        PageValue count = mapper.coutpage(idsite, start, end);
        List<PageValue> list = mapper.pagetitle(idsite, start, end, limit, skip);
        for (PageValue value : list) {
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<PageValue> fullTitleName(List<PageValue> pagetitle) throws Exception {
        if (pagetitle.size() > 0){
            String where = "WHERE id IN(%s)";
            StringBuffer builder = new StringBuffer();
            for (PageValue pagevalue: pagetitle){
                builder.append(",'");
                builder.append(pagevalue.getPid());
                builder.append("'");
            }
            List<Title> titles = mapper.findTitleWhere("",String.format(where, builder.substring(1)));
            Map<String,Title> map = new LinkedHashMap<>();
            for (Title title : titles) {
                map.put(title.getId(),title);
            }
            for (PageValue pagevalue: pagetitle){
                Title title = map.get(pagevalue.getPid());
                if (title != null) {
                    pagevalue.setName(title.getTitle());
                }
            }
        }
        return pagetitle;
    }

    @Override
    public List<PageValue> pageurl(String idsite, Date start, Date end, int limit, int skip) throws Exception {
        PageValue count = mapper.coutpage(idsite, start, end);
        List<PageValue> list = mapper.pageurl(idsite, start, end, limit, skip);
        for (PageValue value : list) {
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

    @Override
    public List<PageValue> fullUrlName(List<PageValue> pageurl) throws Exception {
        if (pageurl.size() > 0){
            String where = "WHERE id IN(%s)";
            StringBuffer builder = new StringBuffer();
            for (PageValue pagevalue: pageurl){
                builder.append(",'");
                builder.append(pagevalue.getPid());
                builder.append("'");
            }
            List<Url> urls = mapper.findUrlWhere("",String.format(where, builder.substring(1)));
            Map<String,Url> map = new LinkedHashMap<>();
            for (Url url : urls) {
                map.put(url.getId(),url);
            }
            for (PageValue pagevalue: pageurl){
                Url url = map.get(pagevalue.getPid());
                if (url != null) {
                    pagevalue.setName(url.getUrl());
                }
            }
        }
        return pageurl;
    }

    @Override
    public List<EventNameValue> event(String idsite, Date start, Date end, int limit, int skip) throws Exception {
        return mapper.event(idsite, start, end, limit, skip);
    }

    @Override
    public List<EventPeriodValue> eventMonth(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception {
        return mapper.eventMonth(idsite, category, start, end, limit, skip);
    }

    @Override
    public List<EventPeriodValue> eventWeek(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception {
        return mapper.eventWeek(idsite, category, start, end, limit, skip);
    }

    @Override
    public List<EventPeriodValue> eventDay(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception {
        return mapper.eventDay(idsite, category, start, end, limit, skip);
    }

    @Override
    public List<EventPeriodValue> eventHour(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception {
        return mapper.eventHour(idsite, category, start, end, limit, skip);
    }
}

