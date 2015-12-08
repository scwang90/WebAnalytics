package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.ErrorDao;
import com.simpletech.webanalytics.dao.StatisticsDao;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.constant.Ranking;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.*;
import com.simpletech.webanalytics.service.ErrorService;
import com.simpletech.webanalytics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 统计API Service 实现
 * Created by 树朾 on 2015/9/25.
 */
@Service
public class ErrorServiceImpl implements ErrorService {

    @Autowired
    ErrorDao dao;



    public List<ErrorValue> unknownError(Ranking ranking,int limit, int skip) {
        switch (ranking) {
            case brand:
                return dao.brand(limit, skip);
            case appname:
                return dao.appname(limit, skip);
            case browser:
                return dao.browser(limit, skip);
            case city:
                return dao.city(limit, skip);
            case country:
                return dao.country(limit, skip);
            case depth:
                return dao.depth(limit, skip);
            case lang:
                return dao.lang( limit, skip);
            case model:
                return dao.model(limit, skip);
            case nettype:
                return dao.nettype(limit, skip);
            case province:
                return dao.province(limit, skip);
            case resolution:
                return dao.resolution(limit, skip);
            case system:
                return dao.system(limit, skip);
        }
        return new ArrayList<>();
    }

}
