package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.constant.Norm;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.constant.Ranking;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.*;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service
 * Created by 树朾 on 2015/9/25.
 */
public interface StatisticsService {

    /**
     * Visit|PV|UV|IP统计数据获取API
     * 获取 idsite网站 在 start-end 的 [小时\日\周\月] [Visit|PV|UV|IP]统计数据
     *
     * @param idsite 网站IDE
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return event 统计数据
     */
    List<VisitValue> visit(int idsite, Period period, Date start, Date end) throws Exception;

    /**
     * event 统计数据获取API
     * 获取 idsite网站 事件 在 start-end 的 [小时\日\周\月] 统计数据
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return event统计数据
     */
    List<EventNameValue> event(int idsite, Date start, Date end, int limit, int skip) throws Exception;

    /**
     * 指定 名称name event 统计数据获取API
     * 获取 idsite网站 事件name 在 start-end 的 [小时\日\周\月] 统计数据
     *
     * @param idsite 网站ID
     * @param name   事件名称
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @param period 截断周期
     * @return event统计数据
     */
    List<EventPeriodValue> event(int idsite, String name, Period period, Date start, Date end, int limit, int skip) throws Exception;

    /**
     * 页面标题排行
     *
     * @param siteId 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 标题排行
     */
    List<PageValue> pagetitle(int siteId, Date start, Date end, int limit, int skip) throws Exception;

    /**
     * 页面链接排行
     *
     * @param siteId 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 链接排行
     */
    List<PageValue> pageurl(int siteId, Date start, Date end, int limit, int skip) throws Exception;

    /**
     * 新老用户
     *
     * @param siteId 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    List<VisitorValue> visitor(int siteId, Period period, Date start, Date end) throws Exception;

    /**
     * 数据排行
     * 设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市
     *
     * @param ranking  排行类型 brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city
     * @param ranktype 排序类型 按 visit|uv|ip|pv
     * @param siteId   网站ID
     * @param start    开始时间
     * @param end      结束时间
     * @param limit    分页限制
     * @param skip     分页起始
     * @return 排行数据
     */
    List<RankingValue> ranking(int siteId, Ranking ranking, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception;
}
