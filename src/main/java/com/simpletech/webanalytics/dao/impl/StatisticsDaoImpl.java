package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.StatisticsDao;
import com.simpletech.webanalytics.mapper.api.StatisticsMapper;
import com.simpletech.webanalytics.model.*;
import com.simpletech.webanalytics.model.constant.BDIPIspType;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.constant.WxSexType;
import com.simpletech.webanalytics.model.entity.*;
import com.simpletech.webanalytics.util.AfStringUtil;
import com.useragent.target.*;
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
    public List<RankingValue> rankBrand(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankBrand(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankModel(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankModel(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankNettype(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankNettype(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankBrowser(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankBrowser(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankSystem(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankSystem(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankAppname(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankAppname(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankResolution(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankResolution(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankDepth(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankDepth(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankLang(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankLang(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankCountry(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankCountry(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankProvince(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankProvince(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankCity(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankCity(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankIp(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankIp(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> rankIsp(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.coutRanking(idsite, start, end);
        List<RankingValue> list = mapper.rankIsp(idsite, rankingtype.name(), start, end, limit, skip);
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
    public List<RankingValue> pageBrand(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageModel(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageNettype(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageBrowser(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageSystem(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageAppname(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageResolution(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageDepth(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageLang(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageCountry(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageProvince(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageCity(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageIp(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
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
    public List<RankingValue> pageIsp(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) {
        RankingValue count = mapper.pageCoutRank(idsite, idurl, start, end);
        List<RankingValue> list = mapper.pageIsp(idsite, idurl, rankingtype.name(), start, end, limit, skip);
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
            try {
                value.setName(BDIPIspType.valueOf(value.getName().toUpperCase()).value);
            } catch (IllegalArgumentException | NullPointerException ignored) {
            }

        }
        return list;
    }

    @Override
    public List<MapPointValue> fullShareUserInfo(List<MapPointValue> points, int max) {
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
            for (int i = 0; i < points.size(); i++) {
                MapPointValue point = points.get(i);
                ShareUser user = map.get(point.getId());
                if (user != null) {
                    point.setMk(user.getNickname());
                    if (i < max) {
                        point.setOpenid(user.getOpenid());
                        point.setName(user.getNickname());
                        point.setCity(user.getCity());
                        point.setCountry(user.getCountry());
                        point.setProvince(user.getProvince());
                        point.setHead(user.getHeadimgurl());
                        try {
                            point.setSex(WxSexType.values()[Integer.parseInt(user.getSex())].value);
                        } catch (Throwable ex) {
                            ex.printStackTrace();
                            point.setSex(WxSexType.nuknow.value);
                        }
                    }
                }
            }
        }
        return points;
    }

    @Override
    public List<ShareLinePoint> sharePoint(String idsite, String urlId, Date start, Date end) {
        return mapper.sharePoint(idsite, urlId, start, end);
    }

    @Override
    public List<ShareStartPoint> getShareStartPoint(String idsite, String urlId, Date start, Date end) {
        return mapper.getShareStartPoint(idsite, urlId, start, end);
    }

}

