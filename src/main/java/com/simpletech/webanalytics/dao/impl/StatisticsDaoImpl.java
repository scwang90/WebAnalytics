package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.StatisticsDao;
import com.simpletech.webanalytics.mapper.api.StatisticsMapper;
import com.simpletech.webanalytics.model.*;
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
    public List<RankingValue> brand(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
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
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.model(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
        RankingValue count = mapper.coutRanking(idsite, start, end);
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
        RankingValue count = mapper.coutRanking(idsite, start, end);
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
        RankingValue count = mapper.coutRanking(idsite, start, end);
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
        RankingValue count = mapper.coutRanking(idsite, start, end);
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
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.resolution(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.depth(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.lang(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.country(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.province(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.city(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
    public List<RankingValue> ip(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.ip(idsite, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
    public List<RankingValue> pageBrand(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageBrand(idsite, idurl, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> pageModel(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageModel(idsite, idurl, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
    public List<RankingValue> pageNettype(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageNettype(idsite, idurl, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> pageBrowser(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageBrowser(idsite, idurl, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> pageSystem(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageSystem(idsite, idurl, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> pageAppname(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageAppname(idsite, idurl, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> pageResolution(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageResolution(idsite, idurl, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
    public List<RankingValue> pageDepth(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageDepth(idsite, idurl, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
    public List<RankingValue> pageLang(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageLang(idsite, idurl, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
    public List<RankingValue> pageCountry(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageCountry(idsite, idurl, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
    public List<RankingValue> pageProvince(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageProvince(idsite, idurl, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
    public List<RankingValue> pageCity(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageCity(idsite, idurl, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
    public List<RankingValue> pageIp(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageIp(idsite, idurl, rankingtype.name(), start, end, limit, skip);
        for (RankingValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
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
    public List<MapPointValue> fullNickName(List<MapPointValue> points) throws Exception {
        if (points.size() > 0) {
            String where = "WHERE idvisitor IN(%s)";
            StringBuffer builder = new StringBuffer();
            for (MapPointValue point : points) {
                builder.append(",'");
                builder.append(point.getId());
                builder.append("'");
            }
            List<ShareUser> users = mapper.findShareUserWhere("", String.format(where, builder.substring(1)));
            Map<String, ShareUser> map = new LinkedHashMap<>();
            for (ShareUser user : users) {
                map.put(user.getIdvisitor(), user);
            }
            for (MapPointValue point : points) {
                ShareUser user = map.get(point.getId());
                if (user != null) {
                    point.setMk(user.getNickname());
                }
            }
        }
        return points;
    }

    @Override
    public List<ShareLinePoint> sharePoint(String idsite, String urlId, Date start, Date end) throws Exception {
        return mapper.sharePoint(idsite, urlId, start, end);
    }

    @Override
    public List<ShareStartPoint> getShareStartPoint(String idsite, String urlId, Date start, Date end) throws Exception {
        return mapper.getShareStartPoint(idsite, urlId, start, end);
    }

}

