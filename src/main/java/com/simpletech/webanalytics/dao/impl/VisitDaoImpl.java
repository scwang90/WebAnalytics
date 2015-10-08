package com.simpletech.webanalytics.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.simpletech.webanalytics.mapper.StatisticsMapper;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.RankingValue;
import com.simpletech.webanalytics.model.entity.VisitValue;
import com.simpletech.webanalytics.model.entity.VisitorValue;
import com.webanalytics.useragent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.VisitDao;
import com.simpletech.webanalytics.model.Visit;

/**
 * 数据库表t_visit的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    StatisticsMapper mapper;

    @Override
    public Visit getVisit(int idsite, String idvisitor) throws Exception {
        String where = "where idsite=%s and idvisitor='%s'";
        for (Visit visit : findWhere(String.format(where, idsite, idvisitor))) {
            return visit;
        }
        return null;
    }

    @Override
    public Visit getVisitHalfHour(int idsite, String idvisitor) throws Exception {
        Date time = new Date(new Date().getTime() - 30 * 60 * 1000);
        String where = "where idsite=%s and idvisitor='%s' and visit_servertime > '%s'";
        for (Visit visit : findWhere(String.format(where, idsite, idvisitor, format.format(time)))) {
            return visit;
        }
        return null;
    }

    @Override
    public List<VisitValue> visitHour(int idsite, Date start, Date end) throws Exception {
        return mapper.visitHour(idsite, start, end);
    }

    @Override
    public List<VisitValue> visitDay(int idsite, Date start, Date end) throws Exception {
        return mapper.visitDay(idsite, start, end);
    }

    @Override
    public List<VisitValue> visitWeek(int idsite, Date start, Date end) throws Exception {
        return mapper.visitWeek(idsite, start, end);
    }

    @Override
    public List<VisitValue> visitMonth(int idsite, Date start, Date end) throws Exception {
        return mapper.visitMonth(idsite, start, end);
    }

    @Override
    public List<VisitValue> pageViewHour(int idsite, Date start, Date end) throws Exception {
        return mapper.pageViewHour(idsite, start, end);
    }

    @Override
    public List<VisitValue> pageViewDay(int idsite, Date start, Date end) throws Exception {
        return mapper.pageViewDay(idsite, start, end);
    }

    @Override
    public List<VisitValue> pageViewWeek(int idsite, Date start, Date end) throws Exception {
        return mapper.pageViewWeek(idsite, start, end);
    }

    @Override
    public List<VisitValue> pageViewMonth(int idsite, Date start, Date end) throws Exception {
        return mapper.pageViewMonth(idsite, start, end);
    }

    @Override
    public List<VisitValue> uniqueVisitorHour(int idsite, Date start, Date end) throws Exception {
        return mapper.uniqueVisitorHour(idsite, start, end);
    }

    @Override
    public List<VisitValue> uniqueVisitorDay(int idsite, Date start, Date end) throws Exception {
        return mapper.uniqueVisitorDay(idsite, start, end);
    }

    @Override
    public List<VisitValue> uniqueVisitorWeek(int idsite, Date start, Date end) throws Exception {
        return mapper.uniqueVisitorWeek(idsite, start, end);
    }

    @Override
    public List<VisitValue> uniqueVisitorMonth(int idsite, Date start, Date end) throws Exception {
        return mapper.uniqueVisitorMonth(idsite, start, end);
    }

    @Override
    public List<VisitValue> internetProtocolHour(int idsite, Date start, Date end) throws Exception {
        return mapper.internetProtocolHour(idsite, start, end);
    }

    @Override
    public List<VisitValue> internetProtocolDay(int idsite, Date start, Date end) throws Exception {
        return mapper.internetProtocolDay(idsite, start, end);
    }

    @Override
    public List<VisitValue> internetProtocolWeek(int idsite, Date start, Date end) throws Exception {
        return mapper.internetProtocolWeek(idsite, start, end);
    }

    @Override
    public List<VisitValue> internetProtocolMonth(int idsite, Date start, Date end) throws Exception {
        return mapper.internetProtocolMonth(idsite, start, end);
    }

    @Override
    public List<VisitorValue> visitorHour(int idsite, Date start, Date end) throws Exception {
        return mapper.visitorHour(idsite, start, end);
    }

    @Override
    public List<VisitorValue> visitorDay(int idsite, Date start, Date end) throws Exception {
        return mapper.visitorDay(idsite, start, end);
    }

    @Override
    public List<VisitorValue> visitorWeek(int idsite, Date start, Date end) throws Exception {
        return mapper.visitorWeek(idsite, start, end);
    }

    @Override
    public List<VisitorValue> visitorMonth(int idsite, Date start, Date end) throws Exception {
        return mapper.visitorMonth(idsite, start, end);
    }

    @Override
    public List<RankingValue> brand(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.brand(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            Brand venum = Brand.parserAcronym(value.getName());
            value.setName(venum.getName());
            value.setRemark(venum.getRemark());
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public List<RankingValue> model(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.model(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public List<RankingValue> nettype(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.nettype(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            Nettype venum = Nettype.parserAcronym(value.getName());
            value.setName(venum.getName());
            value.setRemark(venum.getRemark());
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public List<RankingValue> browser(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.browser(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            Browser venum = Browser.parserAcronym(value.getName());
            value.setName(venum.getName());
            value.setRemark(venum.getRemark());
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public List<RankingValue> system(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.system(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            OperateSystem venum = OperateSystem.parserAcronym(value.getName());
            value.setName(venum.getName());
            value.setRemark(venum.getRemark());
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public List<RankingValue> appname(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.appname(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            Application venum = Application.parserAcronym(value.getName());
            value.setName(venum.getName());
            value.setRemark(venum.getRemark());
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public List<RankingValue> resolution(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.resolution(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public List<RankingValue> depth(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.depth(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public List<RankingValue> lang(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.lang(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public List<RankingValue> country(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.country(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public List<RankingValue> province(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.province(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public List<RankingValue> city(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.city(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRip(1f * value.getIp() / count.getIp());
            value.setRvisit(1f * value.getVisit() / count.getVisit());
        }
        return list;
    }

    @Override
    public Long countVisit(int idsite, Date start, Date end) throws Exception {
        return mapper.countVisit(idsite, start, end);
    }

    @Override
    public Long countUsers(int idsite, Date start, Date end) throws Exception {
        return mapper.countUsers(idsite, start, end);
    }

    @Override
    public boolean existVisitor(int idsite, String idvtor) throws Exception {
        return mapper.countVisitor(idsite, idvtor);
    }
}

