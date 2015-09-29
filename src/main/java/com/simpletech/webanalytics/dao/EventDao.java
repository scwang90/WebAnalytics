package com.simpletech.webanalytics.dao;

import java.util.Date;
import java.util.List;

import com.simpletech.webanalytics.dao.base.BaseDao;
import com.simpletech.webanalytics.model.Event;
import com.simpletech.webanalytics.model.entity.EventPeriodValue;
import com.simpletech.webanalytics.model.entity.EventValue;

/**
 * 数据库表t_event的Dao接口
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface EventDao extends BaseDao<Event> {

    /**
     * 获取 idsite网站 事件 在 start-end 的 小时\日\周\月 统计数据
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
     * 获取 idsite网站 事件name 在 start-end 的 小时\日\周\月 统计数据
     *
     * @param idsite   网站ID
     * @param category 事件名称
     * @param start    开始时间
     * @param end      结束时间
     * @param limit    分页限制
     * @param skip     分页起始
     * @return event统计数据
     */
    List<EventPeriodValue> eventMonth(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception;

    List<EventPeriodValue> eventWeek(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception;

    List<EventPeriodValue> eventDay(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception;

    List<EventPeriodValue> eventHour(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception;
}
