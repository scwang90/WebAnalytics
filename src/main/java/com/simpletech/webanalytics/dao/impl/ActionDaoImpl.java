package com.simpletech.webanalytics.dao.impl;

import java.util.Date;
import java.util.List;

import com.simpletech.webanalytics.mapper.StatisticsMapper;
import com.simpletech.webanalytics.model.entity.PageValue;
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
    public List<PageValue> pagetitle(String siteId, Date start, Date end, int limit, int skip) throws Exception {
        return mapper.pagetitle(siteId, start, end, limit, skip);
    }

    @Override
    public List<PageValue> pageurl(String siteId, Date start, Date end, int limit, int skip) throws Exception {
        return mapper.pageurl(siteId, start, end, limit, skip);
    }

}

