package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.constant.Norm;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.entity.EventPeriodValue;
import com.simpletech.webanalytics.model.entity.EventValue;
import com.simpletech.webanalytics.model.entity.PageValue;
import com.simpletech.webanalytics.model.entity.PeriodValue;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service
 * Created by Administrator on 2015/9/25.
 */
public interface StatisticsService {

    /**
     * Visit|PV|UV|IP统计数据获取API
     * 获取 idsite网站 在 start-end 的 [小时\日\周\月] [Visit|PV|UV|IP]统计数据
     *
     * @param idsite 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param norm   统计指标 [Visit|PV|UV|IP]
     * @param start  开始时间 
     * @param end    结束时间 
     * @return event 统计数据
     */
    List<PeriodValue> norm(String idsite, Period period, Norm norm, Date start, Date end) throws Exception;

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
    List<EventValue> event(String idsite, Date start, Date end, int limit, int skip) throws Exception;

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
    List<EventPeriodValue> event(String idsite, String name, Period period, Date start, Date end, int limit, int skip) throws Exception;

    /**
     * 页面标题排行
     *
     * @param siteId 网站ID
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 标题排行
     */
    List<PageValue> pagetitle(String siteId, Date start, Date end, int limit, int skip) throws Exception;

    /**
     * 页面链接排行 - 自定义时段
     *
     * @param siteId 网站ID
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 链接排行
     */
    List<PageValue> pageurl(String siteId, Date start, Date end, int limit, int skip) throws Exception;
}
