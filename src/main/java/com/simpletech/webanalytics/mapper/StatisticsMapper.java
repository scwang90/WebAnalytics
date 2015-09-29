package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.model.entity.EventPeriodValue;
import com.simpletech.webanalytics.model.entity.EventValue;
import com.simpletech.webanalytics.model.entity.PageValue;
import com.simpletech.webanalytics.model.entity.PeriodValue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 统计Mapper接口
 * Created by Administrator on 2015/9/29.
 */
public interface StatisticsMapper {

    /**
     * 获取 idsite网站 start-end 的 [小时\日\周\月]Visit
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @throws Exception
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d') date, COUNT(id) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> visitDay(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d%H') date, COUNT(id) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> visitHour(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y-%u') date, COUNT(id) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> visitWeek(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m') date, COUNT(id) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> visitMonth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;


    /**
     * 获取 idsite网站 start-end 的 [小时\日\周\月]PV
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @throws Exception
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d') date, SUM(count_visits) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> pageViewDay(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d%H') date, SUM(count_visits) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> pageViewHour(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y-%u') date, SUM(count_visits) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> pageViewWeek(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m') date, SUM(count_visits) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> pageViewMonth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;


    /**
     * 获取 idsite网站 start-end 的 [小时\日\周\月]UV
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @throws Exception
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d') date, COUNT(DISTINCT idvisitor) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> uniqueVisitorDay(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d%H') date, COUNT(DISTINCT idvisitor) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> uniqueVisitorHour(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y-%u') date, COUNT(DISTINCT idvisitor) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> uniqueVisitorWeek(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m') date, COUNT(DISTINCT idvisitor) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> uniqueVisitorMonth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 获取 idsite网站 start-end 的 [小时\日\周\月]IP
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @throws Exception
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d') date, COUNT(DISTINCT location_ip) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> internetProtocolDay(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d%H') date, COUNT(DISTINCT location_ip) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> internetProtocolHour(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y-%u') date, COUNT(DISTINCT location_ip) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> internetProtocolWeek(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m') date, COUNT(DISTINCT location_ip) val FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<PeriodValue> internetProtocolMonth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

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
    @Select("SELECT category name, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=#{idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY category  LIMIT ${skip},${limit}")
    List<EventValue> event(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;


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
    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=#{idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date DESC LIMIT ${skip},${limit}")
    List<EventPeriodValue> eventDay(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d%H') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=#{idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date DESC LIMIT ${skip},${limit}")
    List<EventPeriodValue> eventHour(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT DATE_FORMAT(create_time,'%y-%u') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=#{idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date DESC LIMIT ${skip},${limit}")
    List<EventPeriodValue> eventWeek(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT DATE_FORMAT(create_time,'%y%m') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=#{idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date DESC LIMIT ${skip},${limit}")
    List<EventPeriodValue> eventMonth(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    /**
     * 统计 idsite网站 在 start-end 的 总 Visit
     *
     * @param idsite 网站ID
     * @param start 开始时间
     * @param end 结束时间
     * @return 统计值
     */
    @Select("SELECT COUNT(id) FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end})")
    Long countVisit(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计 idsite网站 在 start-end 的 总 用户（排重）
     *
     * @param idsite 网站ID
     * @param start 开始时间
     * @param end 结束时间
     * @return 统计值
     */
    @Select("SELECT COUNT(DISTINCT idvisitor) FROM t_visit WHERE idsite=#{idsite} AND (visit_servertime BETWEEN #{start} AND #{end})")
    Long countUsers(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;


    /**
     * 页面标题排行
     *
     * @param idsite 网站ID
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 标题排行
     */
    @Select("SELECT idtitle pid, COUNT(id) pv, COUNT(DISTINCT idvisit) up, AVG(time_spent) ts, COUNT(DISTINCT idvisitor) uv FROM t_action WHERE idsite=#{idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY idtitle ORDER BY pv DESC LIMIT ${skip},${limit}")
    List<PageValue> pagetitle(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;

    /**
     * 页面链接排行 - 自定义时段
     *
     * @param idsite 网站ID
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 链接排行
     */
    @Select("SELECT idurl pid, COUNT(id) pv, COUNT(DISTINCT idvisit) up, AVG(time_spent) ts, COUNT(DISTINCT idvisitor) uv FROM t_action WHERE idsite=#{idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY idurl ORDER BY pv DESC LIMIT ${skip},${limit}")
    List<PageValue> pageurl(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
}
