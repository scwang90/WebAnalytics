package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.ErrorDao;
import com.simpletech.webanalytics.mapper.ErrorMapper;
import com.simpletech.webanalytics.model.entity.*;
import com.simpletech.webanalytics.util.AfStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据库表t_visit的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class ErrorDaoImpl implements ErrorDao {

    @Autowired
    ErrorMapper mapper;

    //ErrorValue value;
    @Override
    public List<ErrorValue> brand(int limit, int skip) {
        List<ErrorValue> list = mapper.brand(limit, skip);//ua,ip,brand
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("brand");
            }
        }
        return list;
    }

    @Override
    public List<ErrorValue> model(int limit, int skip) {
        List<ErrorValue> list = mapper.model(limit, skip);
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("model");
            }
        }
        return list;
    }

    @Override
    public List<ErrorValue> nettype(int limit, int skip) {
        List<ErrorValue> list = mapper.nettype(limit, skip);
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("nettype");
            }
        }
        return list;
    }

    @Override
    public List<ErrorValue> browser(int limit, int skip) {
        List<ErrorValue> list = mapper.browser(limit, skip);
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("browser");
            }
        }

        return list;
    }

    @Override
    public List<ErrorValue> system(int limit, int skip) {
        List<ErrorValue> list = mapper.system(limit, skip);
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("system");
            }
        }
        return list;
    }

    @Override
    public List<ErrorValue> appname(int limit, int skip) {
        List<ErrorValue> list = mapper.appname(limit, skip);
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("appname");
            }
        }
        return list;
    }

    @Override
    public List<ErrorValue> resolution(int limit, int skip) {
        List<ErrorValue> list = mapper.resolution(limit, skip);
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("resolution");
            }
        }
        return list;
    }

    @Override
    public List<ErrorValue> depth(int limit, int skip) {
        List<ErrorValue> list = mapper.depth(limit, skip);
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("depth");
            }
        }
        return list;
    }

    @Override
    public List<ErrorValue> lang(int limit, int skip) {
        List<ErrorValue> list = mapper.lang( limit, skip);
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("lang");
            }
        }
        return list;
    }

    @Override
    public List<ErrorValue> country(int limit, int skip) {
        List<ErrorValue> list = mapper.country(limit, skip);
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("country");
            }
        }
        return list;
    }

    @Override
    public List<ErrorValue> province(int limit, int skip) {
        List<ErrorValue> list = mapper.province(limit, skip);
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("province");
            }
        }
        return list;
    }

    @Override
    public List<ErrorValue> city(int limit, int skip) {
        List<ErrorValue> list = mapper.city(limit, skip);
        for (ErrorValue value : list) {
            if (AfStringUtil.isEmpty(value.getName())) {
                value.setName("city");
            }
        }
        return list;
    }
}

