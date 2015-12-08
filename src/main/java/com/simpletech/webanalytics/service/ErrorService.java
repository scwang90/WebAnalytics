package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.constant.Ranking;
import com.simpletech.webanalytics.model.entity.*;

import java.util.List;

/**
 * 失败统计API Service
 * Created by 树朾 on 2015/9/25.
 */
public interface ErrorService {

    /**
     * 获取失败数据
     * 设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市
     *
     * @param limit    分页限制
     * @param skip     分页起始
     * @return 排行数据
     */
    List<ErrorValue> unknownError(Ranking ranking,int limit, int skip);
}
