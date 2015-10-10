package com.simpletech.webanalytics.dao.impl;

import java.util.Date;
import java.util.List;

import com.simpletech.webanalytics.mapper.StatisticsMapper;
import com.simpletech.webanalytics.model.entity.PageValue;
import com.simpletech.webanalytics.model.entity.RankingValue;
import com.webanalytics.useragent.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.ActionDao;
import com.simpletech.webanalytics.model.Action;

/**
 * 数据库表t_action的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class ActionDaoImpl extends BaseDaoImpl<Action> implements ActionDao {

    @Autowired
    StatisticsMapper mapper;

    @Override
    public List<PageValue> pagetitle(int idsite, Date start, Date end, int limit, int skip) throws Exception {
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
    public List<PageValue> pageurl(int idsite, Date start, Date end, int limit, int skip) throws Exception {
        PageValue count = mapper.coutpage(idsite, start, end);
        List<PageValue> list = mapper.pageurl(idsite, start, end, limit, skip);
        for (PageValue value : list) {
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRpv(1f * value.getPv() / count.getPv());
            value.setRvt(1f * value.getVt() / count.getVt());
        }
        return list;
    }

}

