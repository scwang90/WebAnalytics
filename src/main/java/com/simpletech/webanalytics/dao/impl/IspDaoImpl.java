package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.IspDao;
import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.mapper.IspMapper;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.model.entity.IspValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 数据库表t_visit的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class IspDaoImpl implements IspDao {

    @Autowired
    IspMapper mapper;

    @Override
    public int updateIsp(Visit model){
        return mapper.updateIsp(model);
    }

    @Override
    public List<Visit> findWhereIsp(String where,int limit,int start) {
        return mapper.findWhereIsp(where, limit, start);
    }

    @Override
    public List<HashMap<String, Object>> ispBatch(String where, int limit,int start){
        return mapper.findWhere(where,limit,start);
    }

    @Override
    public List<IspValue> isp(String siteId,Date start,Date end) {

        return mapper.isp(siteId, start, end);
    }
}

