package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.model.Title;
import com.simpletech.webanalytics.model.Url;
import com.simpletech.webanalytics.model.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 统计Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface StatisticsMapper {

    /**
     * 获取 idsite网站 start-end 的 [小时\日\周\月] [Visit|PV|UV|IP]
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @throws Exception
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d%H') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitValue> visitHour(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitValue> visitDay(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y-%u') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitValue> visitWeek(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitValue> visitMonth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

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
    @Select("SELECT category name, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY category  LIMIT ${skip},${limit}")
    List<EventNameValue> event(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;


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
    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d%H') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=${idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date DESC LIMIT ${skip},${limit}")
    List<EventPeriodValue> eventHour(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=${idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date DESC LIMIT ${skip},${limit}")
    List<EventPeriodValue> eventDay(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT DATE_FORMAT(create_time,'%y-%u') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=${idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date DESC LIMIT ${skip},${limit}")
    List<EventPeriodValue> eventWeek(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT DATE_FORMAT(create_time,'%y%m') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=${idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date DESC LIMIT ${skip},${limit}")
    List<EventPeriodValue> eventMonth(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    /**
     * 统计 idsite网站 在 start-end 的 总 Visit
     *
     * @param idsite 网站ID
     * @param start 开始时间
     * @param end 结束时间
     * @return 统计值
     */
    @Select("SELECT COUNT(id) FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end})")
    Long countVisit(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计 idsite网站 在 start-end 的 总 用户（排重）
     *
     * @param idsite 网站ID
     * @param start 开始时间
     * @param end 结束时间
     * @return 统计值
     */
    @Select("SELECT COUNT(DISTINCT idvisitor) FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end})")
    Long countUsers(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;


    /**
     * 页面 标题|链接 排行
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 标题排行
     */
    @Select("SELECT idtitle pid, COUNT(id) pv, COUNT(DISTINCT idvisit) vt, AVG(time_spent) ts, COUNT(DISTINCT idvisitor) uv FROM t_action WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY idtitle ORDER BY pv DESC LIMIT ${skip},${limit}")
    List<PageValue> pagetitle(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT idurl pid, COUNT(id) pv, COUNT(DISTINCT idvisit) vt, AVG(time_spent) ts, COUNT(DISTINCT idvisitor) uv FROM t_action WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY idurl ORDER BY pv DESC LIMIT ${skip},${limit}")
    List<PageValue> pageurl(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT id , idsite , create_time createTime , update_time updateTime , hash , title FROM t_title ${where} ${order}")
    List<Title> findTitleWhere(@Param("order") String order, @Param("where") String where) throws Exception;
    @Select("SELECT id , idsite , create_time createTime , update_time updateTime , hash , url FROM t_url ${where} ${order}")
    List<Url> findUrlWhere(@Param("order") String order, @Param("where") String where) throws Exception;

    /**
     * 统计排行总量（标题|链接 ）
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据总量
     */
    @Select("SELECT COUNT(id) pv, COUNT(DISTINCT idvisit) vt, AVG(time_spent) ts, COUNT(DISTINCT idvisitor) uv FROM t_action WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end})")
    PageValue coutpage(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 新老用户
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d%H') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorValue> visitorHour(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorValue> visitorDay(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y-%u') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorValue> visitorWeek(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorValue> visitorMonth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 数据排行
     *  设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 数据排行
     */
    @Select("SELECT end_brand name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> brand(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT end_model name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> model(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT net_type name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> nettype(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT browser_name name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> browser(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT operate_system name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> system(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT app_name name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> appname(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT screen_resolution name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> resolution(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT screen_depth name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> depth(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT location_lang name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> lang(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT location_country name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> country(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT location_region name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> province(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT location_city name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> city(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;

    /**
     * 统计排行总量（visit|uv|pv）
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据总量
     */
    @Select("SELECT COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) ")
    RankingValue coutranking(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计 idvtor 在 idsite网站的 visit 次数
     *
     * @param idsite 网站ID
     * @param idvtor 访问者ID
     * @return 统计数
     */
    @Select("SELECT COUNT(id) FROM t_visit WHERE idsite=${idsite} AND idvisitor=#{idvtor}")
    boolean countVisitor(@Param("idsite") String idsite,@Param("idvtor")  String idvtor) throws Exception;
}
