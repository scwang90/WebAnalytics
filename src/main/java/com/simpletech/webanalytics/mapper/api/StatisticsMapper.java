package com.simpletech.webanalytics.mapper.api;

import com.simpletech.webanalytics.model.*;
import com.simpletech.webanalytics.model.constant.RankingType;
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
     * Piwik SQL
     *
     * [Visit|PV|UV|IP]
     * SELECT COUNT(idvisit) vt,COUNT(DISTINCT idvisitor) uv,SUM(`visit_total_actions`) pv,COUNT(DISTINCT location_ip) ip FROM `t_log_visit` WHERE idsite=2 AND DATE_FORMAT(`visit_first_action_time`,'%y%m%d')=DATE_FORMAT(NOW(),'%y%m%d')
     *
     * 入口页|退出页
     * SELECT visit_exit_idaction_url,t_log_action.name FROM `t_log_visit` LEFT JOIN t_log_action ON t_log_visit.visit_exit_idaction_url=t_log_action.idaction WHERE idsite=2 AND DATE_FORMAT(`visit_first_action_time`,'%y%m%d')=DATE_FORMAT(NOW(),'%y%m%d') GROUP BY visit_exit_idaction_url
     */
    /**
     * 获取 idsite网站 start-end 的 [小时\日\周\月] [Visit|PV|UV|IP] 趋势
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d%H') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitTrendValue> visitTrendHour(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitTrendValue> visitTrendDay(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y-%u') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitTrendValue> visitTrendWeek(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitTrendValue> visitTrendMonth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取 idsite网站 start-end 的 [小时\日\周\月] [Visit|PV|UV|IP] 时段
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     */
    @Select("SELECT COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) ")
    VisitSpanValue visitSpan(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取 idsite网站 start-end 的 [小时\日\周\月] [Visit|PV|UV|IP] 趋势
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d%H') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) AND idurl=#{idurl} GROUP BY idvisit) AS t ON t.idvisit=t_visit.id GROUP BY date ORDER BY date ")
    List<VisitTrendValue> pageVisitTrendHour(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) AND idurl=#{idurl} GROUP BY idvisit) AS t ON t.idvisit=t_visit.id GROUP BY date ORDER BY date ")
    List<VisitTrendValue> pageVisitTrendDay(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y-%u') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) AND idurl=#{idurl} GROUP BY idvisit) AS t ON t.idvisit=t_visit.id GROUP BY date ORDER BY date ")
    List<VisitTrendValue> pageVisitTrendWeek(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) AND idurl=#{idurl} GROUP BY idvisit) AS t ON t.idvisit=t_visit.id GROUP BY date ORDER BY date ")
    List<VisitTrendValue> pageVisitTrendMonth(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取 idsite网站 start-end 的 [小时\日\周\月] [Visit|PV|UV|IP] 时段
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     */
//    @Select("SELECT COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) AND idurl=#{idurl} GROUP BY idvisit) AS t ON t.idvisit=t_visit.id ")
    @Select("SELECT\n" +
            "  COUNT(id)                   vt,\n" +
            "  SUM(count_visits)           pv,\n" +
            "  COUNT(DISTINCT idvisitor)   uv,\n" +
            "  COUNT(DISTINCT location_ip) ip,\n" +
            "  spent,\n" +
            "  `exit`,\n" +
            "  `exit` / total rexit\n" +
            "FROM t_visit\n" +
            "  RIGHT JOIN (SELECT idvisit\n" +
            "              FROM t_action\n" +
            "              WHERE (create_time BETWEEN #{start} AND #{end}) AND idurl = #{idurl}\n" +
            "              GROUP BY idvisit) AS t3 ON t3.idvisit = t_visit.id," +
            "  (SELECT AVG(ref_time_spent) spent\n" +
            "   FROM t_action\n" +
            "   WHERE ref_id_url = #{idurl}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})) AS t1,\n" +
            "  (SELECT\n" +
            "     SUM(idurl_exit = #{idurl})  `exit`,\n" +
            "     COUNT(id)                    total\n" +
            "   FROM t_visit AS t\n" +
            "   WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end})) AS t2\n")
    VisitSpanValue pageVisitSpan(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    /**
     * 访问时间 - 服务器时间 - 分布
     *
     * @param idsite 网站IDE
     * @param start  开始时间
     * @param end    结束时间
     * @return 访问时间
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%H') time, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY time ORDER BY time ")
    List<VisitTimeMapValue> visitServerTimeMap(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 浏览器时间 - 服务器时间 - 分布
     *
     * @param idsite 网站IDE
     * @param start  开始时间
     * @param end    结束时间
     * @return 访问时间
     */
    @Select("SELECT DATE_FORMAT(visit_localtime,'%H') time, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY time ORDER BY time ")
    List<VisitTimeMapValue> visitLocalTimeMap(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 访问时间 - 服务器时间 - 分布
     *
     * @param idsite 网站IDE
     * @param start  开始时间
     * @param end    结束时间
     * @return 访问时间
     */
    @Select("SELECT DATE_FORMAT(create_time,'%H') time, COUNT(id) pv, COUNT(DISTINCT idvisit) vt, COUNT(DISTINCT idvisitor) uv FROM t_action AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) AND idurl=#{idurl} GROUP BY time ORDER BY time ")
    List<VisitTimeMapValue> pageServerTimeMap(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    /**
     * 浏览器时间 - 服务器时间 - 分布
     *
     * @param idsite 网站IDE
     * @param start  开始时间
     * @param end    结束时间
     * @return 访问时间
     */
    @Select("SELECT DATE_FORMAT(visit_localtime,'%H') time, COUNT(t_visit.id) vt, SUM(count_visits) pv, COUNT(DISTINCT t_visit.idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY time ORDER BY time ")
    List<VisitTimeMapValue> pageLocalTimeMap(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取 idsite网站 事件 在 start-end 的 排行数据
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return event统计数据
     */
    @Select("SELECT\n" +
            "  name,\n" +
            "  num,\n" +
            "  user,\n" +
            "  num/vt rnum,\n" +
            "  user/uv ruser\n" +
            "FROM (SELECT\n" +
            "        category                  name,\n" +
            "        COUNT(id)                 num,\n" +
            "        COUNT(DISTINCT idvisitor) user\n" +
            "      FROM t_event AS t\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY category\n" +
            "      ORDER BY num DESC LIMIT ${skip},${limit}) AS t1\n" +
            "  , (SELECT\n" +
            "       COUNT(id)                 vt,\n" +
            "       COUNT(DISTINCT idvisitor) uv\n" +
            "     FROM t_visit AS t\n" +
            "     WHERE idsite = ${idsite}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "    ) AS t2")
    List<EventNameValue> eventRank(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT COUNT(*) FROM (SELECT category FROM t_event AS t WHERE idsite = ${idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY category) AS tt")
    int eventRankCount(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取 idsite网站 事件 在 start-end 的 时段数据
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return event统计数据
     */
    @Select("SELECT\n" +
            "  name,\n" +
            "  num,\n" +
            "  user,\n" +
            "  num/vt rnum,\n" +
            "  user/uv ruser\n" +
            "FROM (SELECT\n" +
            "        category                  name,\n" +
            "        COUNT(id)                 num,\n" +
            "        COUNT(DISTINCT idvisitor) user\n" +
            "      FROM t_event AS t\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "    ) AS t1\n" +
            "  , (SELECT\n" +
            "       COUNT(id)                 vt,\n" +
            "       COUNT(DISTINCT idvisitor) uv\n" +
            "     FROM t_visit AS t\n" +
            "     WHERE idsite = ${idsite}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "    ) AS t2")
    EventSpanValue eventSpan(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取 idsite网站 事件 在 start-end 的 趋势数据
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 事件趋势
     */
    @Select("SELECT t1.date date,\n" +
            "  num,\n" +
            "  user,\n" +
            "  num / vt  rnum,\n" +
            "  user / uv ruser\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d%H') date,\n" +
            "        COUNT(id)                            num,\n" +
            "        COUNT(DISTINCT idvisitor)            user\n" +
            "      FROM t_event AS t\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      ) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m%d%H') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   ) AS t2 ON t1.date=t2.date\n" +
            "ORDER BY date")
    List<EventTrendValue> eventTrendHour(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT t1.date date,\n" +
            "  num,\n" +
            "  user,\n" +
            "  num / vt  rnum,\n" +
            "  user / uv ruser\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d') date,\n" +
            "        COUNT(id)                            num,\n" +
            "        COUNT(DISTINCT idvisitor)            user\n" +
            "      FROM t_event AS t\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      ) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m%d') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   ) AS t2 ON t1.date=t2.date\n" +
            "ORDER BY date")
    List<EventTrendValue> eventTrendDay(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT t1.date date,\n" +
            "  num,\n" +
            "  user,\n" +
            "  num / vt  rnum,\n" +
            "  user / uv ruser\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y-%u') date,\n" +
            "        COUNT(id)                            num,\n" +
            "        COUNT(DISTINCT idvisitor)            user\n" +
            "      FROM t_event AS t\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      ) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y-%u') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   ) AS t2 ON t1.date=t2.date\n" +
            "ORDER BY date")
    List<EventTrendValue> eventTrendWeek(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT t1.date date,\n" +
            "  num,\n" +
            "  user,\n" +
            "  num / vt  rnum,\n" +
            "  user / uv ruser\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m') date,\n" +
            "        COUNT(id)                            num,\n" +
            "        COUNT(DISTINCT idvisitor)            user\n" +
            "      FROM t_event AS t\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      ) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   ) AS t2 ON t1.date=t2.date\n" +
            "ORDER BY date")
    List<EventTrendValue> eventTrendMonth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取 idsite网站 事件 在 start-end 的 排行数据
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return event统计数据
     */
    @Select("SELECT\n" +
            "  num,\n" +
            "  user,\n" +
            "  num/vt rnum,\n" +
            "  user/uv ruser\n" +
            "FROM (SELECT\n" +
            "        COUNT(id)                 num,\n" +
            "        COUNT(DISTINCT idvisitor) user\n" +
            "      FROM t_event AS t\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND category=#{name}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      ) AS t1\n" +
            "  , (SELECT\n" +
            "       COUNT(id)                 vt,\n" +
            "       COUNT(DISTINCT idvisitor) uv\n" +
            "     FROM t_visit AS t\n" +
            "     WHERE idsite = ${idsite}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "    ) AS t2")
    EventNameSpanValue eventNameSpan(@Param("idsite") String idsite, @Param("name") String name, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取 idsite网站 事件name 在 start-end 的 小时\日\周\月 统计数据
     *
     * @param idsite 网站ID
     * @param name   事件名称
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return event统计数据
     */
//    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d%H') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=${idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date LIMIT ${skip},${limit}")
//    List<EventPeriodValue> eventNameTrendHour(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);
//    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=${idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date LIMIT ${skip},${limit}")
//    List<EventPeriodValue> eventNameTrendDay(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);
//    @Select("SELECT DATE_FORMAT(create_time,'%y-%u') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=${idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date LIMIT ${skip},${limit}")
//    List<EventPeriodValue> eventNameTrendWeek(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);
//    @Select("SELECT DATE_FORMAT(create_time,'%y%m') date, COUNT(id) num, COUNT(DISTINCT idvisitor) user FROM t_event WHERE idsite=${idsite} AND category=#{category} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date LIMIT ${skip},${limit}")
//    List<EventPeriodValue> eventNameTrendMonth(@Param("idsite") String idsite, @Param("category") String category, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT t1.date date,\n" +
            "  num,\n" +
            "  user,\n" +
            "  num / vt  rnum,\n" +
            "  user / uv ruser\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d%H') date,\n" +
            "        COUNT(id)                            num,\n" +
            "        COUNT(DISTINCT idvisitor)            user\n" +
            "      FROM t_event AS t\n" +
            "      WHERE idsite = ${idsite}\n" +
            "           AND category=#{name}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      ) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m%d%H') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   ) AS t2 ON t1.date=t2.date\n" +
            "ORDER BY date LIMIT ${skip},${limit}")
    List<EventNameTrendValue> eventNameTrendHour(@Param("idsite") String idsite, @Param("name") String name, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT t1.date date,\n" +
            "  num,\n" +
            "  user,\n" +
            "  num / vt  rnum,\n" +
            "  user / uv ruser\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d') date,\n" +
            "        COUNT(id)                            num,\n" +
            "        COUNT(DISTINCT idvisitor)            user\n" +
            "      FROM t_event AS t\n" +
            "      WHERE idsite = ${idsite}\n" +
            "           AND category=#{name}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      ) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m%d') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   ) AS t2 ON t1.date=t2.date\n" +
            "ORDER BY date LIMIT ${skip},${limit}")
    List<EventNameTrendValue> eventNameTrendDay(@Param("idsite") String idsite, @Param("name") String name, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT t1.date date,\n" +
            "  num,\n" +
            "  user,\n" +
            "  num / vt  rnum,\n" +
            "  user / uv ruser\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y-%u') date,\n" +
            "        COUNT(id)                            num,\n" +
            "        COUNT(DISTINCT idvisitor)            user\n" +
            "      FROM t_event AS t\n" +
            "      WHERE idsite = ${idsite}\n" +
            "           AND category=#{name}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      ) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y-%u') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   ) AS t2 ON t1.date=t2.date\n" +
            "ORDER BY date LIMIT ${skip},${limit}")
    List<EventNameTrendValue> eventNameTrendWeek(@Param("idsite") String idsite, @Param("name") String name, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT t1.date date,\n" +
            "  num,\n" +
            "  user,\n" +
            "  num / vt  rnum,\n" +
            "  user / uv ruser\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m') date,\n" +
            "        COUNT(id)                            num,\n" +
            "        COUNT(DISTINCT idvisitor)            user\n" +
            "      FROM t_event AS t\n" +
            "      WHERE idsite = ${idsite}\n" +
            "           AND category=#{name}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      ) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   ) AS t2 ON t1.date=t2.date\n" +
            "ORDER BY date LIMIT ${skip},${limit}")
    List<EventNameTrendValue> eventNameTrendMonth(@Param("idsite") String idsite, @Param("name") String name, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    /**
     * 统计 idsite网站 在 start-end 的 总 Visit
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计值
     */
    @Select("SELECT COUNT(id) FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end})")
    Long countVisit(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 统计 idsite网站 在 start-end 的 总 用户（排重）
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计值
     */
    @Select("SELECT COUNT(DISTINCT idvisitor) FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end})")
    Long countUsers(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);


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
    @Select("SELECT\n" +
            "  t1.id          id,\n" +
            "  title,\n" +
            "  t1.pv          pv,\n" +
            "  t1.vt          vt,\n" +
            "  t1.uv          uv,\n" +
            "  t1.loaded      `load`,\n" +
            "  t3.spent       spent,\n" +
            "  t1.pv / t2.tpv rpv,\n" +
            "  t1.vt / t2.tvt rvt,\n" +
            "  t1.uv / t2.tuv ruv\n" +
            "FROM\n" +
            "  (SELECT\n" +
            "     idtitle                   id,\n" +
            "     COUNT(id)                 pv,\n" +
            "     COUNT(DISTINCT idvisit)   vt,\n" +
            "     COUNT(DISTINCT idvisitor) uv,\n" +
            "     AVG(time_loaded)          loaded\n" +
            "   FROM t_action AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY idtitle\n" +
            "   ORDER BY ${type} DESC\n" +
            "   LIMIT ${skip}, ${limit}\n" +
            "  ) AS t1\n" +
            "  LEFT JOIN (SELECT\n" +
            "               ref_id_title        idtitle,\n" +
            "               AVG(ref_time_spent) spent\n" +
            "             FROM t_action AS t\n" +
            "             WHERE idsite = ${idsite}\n" +
            "                   AND (create_time BETWEEN #{start} AND #{end})\n" +
            "             GROUP BY ref_id_title\n" +
            "            ) AS t3 ON t3.idtitle = t1.id\n" +
            "  LEFT JOIN t_title ON t_title.id = t1.id,\n" +
            "  (SELECT\n" +
            "     SUM(count_visits)         tpv,\n" +
            "     COUNT(id)                 tvt,\n" +
            "     COUNT(DISTINCT idvisitor) tuv\n" +
            "   FROM t_visit AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "  ) AS t2")
    List<PageValue> pageTitleRank(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT COUNT(*) FROM (SELECT idtitle FROM t_action AS t WHERE idsite = ${idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY idtitle) AS tt")
    int pageTitleCount(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  t1.id          id,\n" +
            "  url,\n" +
            "  title,\n" +
            "  t1.pv          pv,\n" +
            "  t1.vt          vt,\n" +
            "  t1.uv          uv,\n" +
            "  t1.loaded      `load`,\n" +
            "  t3.spent       spent,\n" +
            "  t1.pv / t2.tpv rpv,\n" +
            "  t1.vt / t2.tvt rvt,\n" +
            "  t1.uv / t2.tuv ruv\n" +
            "FROM\n" +
            "  (SELECT\n" +
            "     idtitle,\n" +
            "     idurl                     id,\n" +
            "     COUNT(id)                 pv,\n" +
            "     COUNT(DISTINCT idvisit)   vt,\n" +
            "     COUNT(DISTINCT idvisitor) uv,\n" +
            "     AVG(time_loaded)          loaded\n" +
            "   FROM t_action AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY idurl\n" +
            "   ORDER BY ${type} DESC\n" +
            "   LIMIT ${skip}, ${limit}\n" +
            "  ) AS t1\n" +
            "  LEFT JOIN (SELECT\n" +
            "               ref_id_url        idurl,\n" +
            "               AVG(ref_time_spent) spent\n" +
            "             FROM t_action AS t\n" +
            "             WHERE idsite = ${idsite}\n" +
            "                   AND (create_time BETWEEN #{start} AND #{end})\n" +
            "             GROUP BY ref_id_url\n" +
            "            ) AS t3 ON t3.idurl = t1.id\n" +
            "  LEFT JOIN t_title ON t_title.id = t1.idtitle\n" +
            "  LEFT JOIN t_url ON t_url.id = t1.id,\n" +
            "  (SELECT\n" +
            "     SUM(count_visits)         tpv,\n" +
            "     COUNT(id)                 tvt,\n" +
            "     COUNT(DISTINCT idvisitor) tuv\n" +
            "   FROM t_visit AS t\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "  ) AS t2")
    List<PageValue> pageUrlRank(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT COUNT(*) FROM (SELECT idurl FROM t_action AS t WHERE idsite = ${idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY idurl) AS tt")
    int pageUrlCount(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 新老用户-时段
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    @Select("SELECT SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end})")
    VisitorSpanValue visitorSpan(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 新老用户-趋势
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d%H') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorTrendValue> visitorTrendHour(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorTrendValue> visitorTrendDay(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y-%u') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorTrendValue> visitorTrendWeek(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorTrendValue> visitorTrendMonth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 站点数据排行
     * 设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市、IP地址
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 数据排行
     */
    @Select("SELECT end_brand name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankBrand(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT end_model name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankModel(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT net_type name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankNettype(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT browser_name name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankBrowser(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT operate_system name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankSystem(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT end_app name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankAppname(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT screen_resolution name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankResolution(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT screen_depth name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankDepth(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_lang name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankLang(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_country name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankCountry(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_region name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankProvince(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_city name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankCity(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_ip name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankIp(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_isp name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> rankIsp(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    /**
     * 站点数据排行
     * 设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市、IP地址
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据排行
     */
    @Select("SELECT COUNT(*) FROM (SELECT end_brand name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountBrand(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT end_model name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountModel(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT net_type name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountNettype(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT browser_name name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountBrowser(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT operate_system name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountSystem(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT end_app name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountAppname(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT screen_resolution name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountResolution(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT screen_depth name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountDepth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_lang name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountLang(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_country name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountCountry(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_region name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountProvince(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_city name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountCity(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_ip name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountIp(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_isp name FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name) AS tt")
    int rankCountIsp(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 统计排行总量（visit|uv|pv）
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据总量
     */
    @Select("SELECT COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) ")
    RankingValue coutRanking(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 页面数据排行
     * 设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市、IP地址
     * RIGHT JOIN (SELECT idvisit FROM	t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit
     * RIGHT JOIN (SELECT idvisit FROM	t_action WHERE idurl = #{idurl} AND idsite=${idsite} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit
     *
     * @param idsite 网站ID
     * @param idurl  页面ID
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 数据排行
     */
    @Select("SELECT end_brand name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageBrand(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT end_model name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageModel(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT net_type name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageNettype(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT browser_name name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageBrowser(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT operate_system name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageSystem(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT end_app name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageAppname(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT screen_resolution name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageResolution(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT screen_depth name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageDepth(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_lang name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageLang(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_country name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageCountry(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_region name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageProvince(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_city name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageCity(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_ip name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageIp(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT location_isp name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageIsp(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    /**
     * 页面数据排行总数
     * 设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市、IP地址
     * RIGHT JOIN (SELECT idvisit FROM	t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit
     * RIGHT JOIN (SELECT idvisit FROM	t_action WHERE idurl = #{idurl} AND idsite=${idsite} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit
     *
     * @param idsite 网站ID
     * @param idurl  页面ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据排行总数
     */
    @Select("SELECT COUNT(*) FROM (SELECT end_brand name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountBrand(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT end_model name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountModel(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT net_type name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountNettype(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT browser_name name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountBrowser(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT operate_system name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountSystem(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT end_app name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountAppname(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT screen_resolution name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountResolution(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT screen_depth name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountDepth(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_lang name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountLang(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_country name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountCountry(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_region name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountProvince(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_city name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountCity(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_ip name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountIp(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT COUNT(*) FROM (SELECT location_isp name FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name) AS tt")
    int pageCountIsp(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);


    /**
     * 页面统计排行总量（visit|uv|pv）
     * RIGHT JOIN (SELECT idvisit FROM	t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit
     * RIGHT JOIN (SELECT idvisit FROM	t_action WHERE idurl = #{idurl} AND idsite=${idsite} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据总量
     */
    @Select("SELECT COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit ")
    RankingValue pageCoutRank(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    /**
     * 分享到 - 排行
     *
     * @param idsite 网站ID
     * @param type   排序类型
     * @param start  开始时间
     * @param end    结束时间
     */
    @Select("SELECT share_to name,SUM(count_pv) pv,COUNT(DISTINCT idrefervisitor) ruv,COUNT(DISTINCT idvisitor) uv FROM t_share_line_point AS t WHERE share_to IS NOT NULL AND idurl = #{idurl} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY share_to ORDER BY ${type} DESC")
    List<ShareToRankValue> shareToRank(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end);

    /**
     * 单页性别-排行
     *
     * @param idsite 网站ID
     * @param type   排序类型 按 uv|pv
     * @param start  开始时间
     * @param end    结束时间
     * @return 分享去向-排行
     */
    @Select("SELECT sex,COUNT(*) uv,SUM(pv) pv\n" +
            "FROM (SELECT\n" +
            "        t1.sex,t.idvisitor,COUNT(t.id) pv\n" +
            "      FROM t_share_line_point AS t LEFT JOIN t_share_user AS t1 ON t.idvisitor = t1.idvisitor\n" +
            "      WHERE idurl = #{urlId} AND (t.create_time BETWEEN #{start} AND #{end}) \n" +
            "      GROUP BY t.idvisitor) AS t WHERE t.sex IS NOT NULL GROUP BY t.sex ORDER BY ${type} DESC")
    List<ShareSexRankValue> shareSexRank(@Param("idsite") String idsite, @Param("urlId") String urlId, @Param("type") String type, @Param("start") Date start, @Param("end") Date end);

    /**
     * 整站性别-排行
     *
     * @param idsite 网站ID
     * @param type   排序类型 按 uv|pv
     * @param start  开始时间
     * @param end    结束时间
     * @return 分享去向-排行
     */
    @Select("SELECT sex,COUNT(*) uv,SUM(pv) pv\n" +
            "FROM (SELECT\n" +
            "        t1.sex,t.idvisitor,COUNT(t.id) pv\n" +
            "      FROM t_share_line_point AS t LEFT JOIN t_share_user AS t1 ON t.idvisitor = t1.idvisitor\n" +
            "      WHERE t.idsite = ${idsite} AND (t.create_time BETWEEN #{start} AND #{end}) \n" +
            "      GROUP BY t.idvisitor) AS t WHERE t.sex IS NOT NULL GROUP BY t.sex ORDER BY ${type} DESC")
    List<ShareSexRankValue> siteSexRank(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end);

    /**
     * 分享传播点列表
     *
     * @param idsite 网站ID
     * @param urlId  页面ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 分享传播点列表
     */
    @Select("SELECT id , idsite , idsubsite , idurl , idvisitor , idrefervisitor , is_start_point isStartPoint , share_to shareTo , count_pv countPv , share_span shareSpan , share_time shareTime , create_time createTime , update_time updateTime FROM t_share_line_point WHERE idurl=#{urlId} AND (create_time BETWEEN #{start} AND #{end}) ")
    List<ShareLinePoint> sharePoint(@Param("idsite") String idsite, @Param("urlId") String urlId, @Param("start") Date start, @Param("end") Date end);

    /**
     * 选择性查询 ShareUser
     *
     * @param where SQL条件语句
     * @return 符合条件的ShareUser列表数据
     */
    @Select("SELECT id , idsite , idsubsite , idvisitor , openid , unionid , nickname , headimgurl , sex , province , city , country , privilege , create_time createTime , update_time updateTime FROM t_share_user ${where} ${order}")
    List<ShareUser> findShareUserWhere(@Param("order") String order, @Param("where") String where);


    /**
     * 单用户分享页码到情况
     *
     * @param idsite 网站ID
     * @param urlId  页面ID
     * @param openid 微信用户ID
     * @param start  开始时间
     * @param end    结束时间
     */
    @Select("SELECT COUNT(t.id) sh,COUNT(DISTINCT t.idvisitor) uv,SUM(t.count_pv) pv, openid , unionid , nickname name, headimgurl head, sex , province , city , country , privilege\n" +
            "FROM t_share_user AS tt\n" +
            "  LEFT JOIN t_share_line_point AS t ON t.idrefervisitor = tt.idvisitor\n" +
            "WHERE openid = #{openid} AND t.idurl=#{urlId} AND (t.create_time BETWEEN #{start} AND #{end})")
    PageUserShare pageUserShare(@Param("idsite") String idsite, @Param("urlId") String urlId, @Param("openid") String openid, @Param("start") Date start, @Param("end") Date end);

    /**
     * 分享传播-时段
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 分享传播
     */
    @Select("SELECT SUM(count_pv) pv,COUNT(DISTINCT idrefervisitor) ruv,COUNT(DISTINCT idvisitor) uv, COUNT(id) vt FROM t_share_line_point AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) AND idurl=#{idurl} ")
    ShareSpanValue shareSpan(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    /**
     * 分享传播-趋势
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 分享传播
     */
    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d%H') date,SUM(count_pv) pv,COUNT(DISTINCT idrefervisitor) ruv,COUNT(DISTINCT idvisitor) uv, COUNT(id) vt FROM t_share_line_point AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) AND idurl=#{idurl} GROUP BY date ORDER BY date ")
    List<ShareTrendValue> shareTrendHour(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d') date,SUM(count_pv) pv,COUNT(DISTINCT idrefervisitor) ruv,COUNT(DISTINCT idvisitor) uv, COUNT(id) vt FROM t_share_line_point AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) AND idurl=#{idurl} GROUP BY date ORDER BY date ")
    List<ShareTrendValue> shareTrendDay(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(create_time,'%y-%u') date,SUM(count_pv) pv,COUNT(DISTINCT idrefervisitor) ruv,COUNT(DISTINCT idvisitor) uv, COUNT(id) vt FROM t_share_line_point AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) AND idurl=#{idurl} GROUP BY date ORDER BY date ")
    List<ShareTrendValue> shareTrendWeek(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT DATE_FORMAT(create_time,'%y%m') date,SUM(count_pv) pv,COUNT(DISTINCT idrefervisitor) ruv,COUNT(DISTINCT idvisitor) uv, COUNT(id) vt FROM t_share_line_point AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) AND idurl=#{idurl} GROUP BY date ORDER BY date ")
    List<ShareTrendValue> shareTrendMonth(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end);

    /**
     * 页面分享排行
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 页面分享排行
     */
//    @Select("SELECT id pid,num,url FROM t_url RIGHT JOIN (SELECT idurl,COUNT(id) num FROM t_share_line_point WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY idurl) t on t.idurl=t_url.id LIMIT ${skip},${limit}")
    @Select("SELECT idurl id,url,title,COUNT(idurl) num FROM t_share_line_point AS t LEFT JOIN t_url ON t_url.id=idurl LEFT JOIN t_title ON t_title.id=idtitle WHERE t.idsite=${idsite} AND (t.create_time BETWEEN #{start} AND #{end}) GROUP BY idurl ORDER BY num DESC LIMIT ${skip},${limit}")
    List<ShareRankValue> shareRank(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT COUNT(*) FROM (SELECT idurl FROM t_share_line_point AS t WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY idurl) AS tt")
    int shareRankCount(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取起始点列表
     *
     * @param idsite 网站ID
     * @param urlId  页面ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 起始点列表
     */
    @Select("SELECT id , idsite , idsubsite , idurl , idvisitor , create_time createTime , update_time updateTime FROM t_share_start_point AS t WHERE idurl=#{urlId} AND (create_time BETWEEN #{start} AND #{end}) ")
    List<ShareStartPoint> getShareStartPoint(@Param("idsite") String idsite, @Param("urlId") String urlId, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取起始点列表ID
     *
     * @param idsite 网站ID
     * @param urlId  页面ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 起始点列表
     */
    @Select("SELECT idvisitor FROM t_share_start_point AS t WHERE idurl=#{urlId} AND (create_time BETWEEN #{start} AND #{end}) ")
    List<String> getShareStartPointIds(@Param("idsite") String idsite, @Param("urlId") String urlId, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取入口页面列表
     * Piwik SQL
     * SELECT visit_exit_idaction_url,t_log_action.name FROM `t_log_visit` LEFT JOIN t_log_action ON t_log_visit.visit_exit_idaction_url=t_log_action.idaction WHERE idsite=2 AND DATE_FORMAT(`visit_first_action_time`,'%y%m%d')=DATE_FORMAT(NOW(),'%y%m%d') GROUP BY visit_exit_idaction_url
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 获取入口页面列表
     */
    @Select("SELECT\n" +
            "  t_url.url                     url,\n" +
            "  t_title.title                 title,\n" +
            "  COUNT(t.id)                   vt,\n" +
            "  COUNT(DISTINCT t.idvisitor)   uv,\n" +
            "  COUNT(DISTINCT t.location_ip) ip,\n" +
            "  SUM(t.count_visits)           pv,\n" +
            "  t_load.loaded                `load`,\n" +
            "  t_spent.spent                 spent\n" +
            "FROM t_visit AS t\n" +
            "  LEFT JOIN t_url ON t.idurl_entry = t_url.id\n" +
            "  LEFT JOIN t_title ON t.idtitle_entry = t_title.id\n" +
            "  LEFT JOIN (SELECT\n" +
            "               idurl,\n" +
            "               AVG(time_loaded) loaded\n" +
            "             FROM t_action AS t\n" +
            "             WHERE idsite = ${idsite} AND (create_time BETWEEN #{start} AND #{end})\n" +
            "             GROUP BY idurl) AS t_load ON t.idurl_entry = t_load.idurl\n" +
            "  LEFT JOIN (SELECT\n" +
            "               ref_id_url          idurl,\n" +
            "               AVG(ref_time_spent) spent\n" +
            "             FROM t_action AS t\n" +
            "             WHERE idsite = ${idsite} AND ref_time_spent > 0 AND (create_time BETWEEN #{start} AND #{end})\n" +
            "             GROUP BY ref_id_url) AS t_spent ON t.idurl_entry = t_spent.idurl\n" +
            "WHERE t.idsite = ${idsite} AND (visit_servertime BETWEEN #{start} AND #{end})\n" +
            "GROUP BY idurl_entry\n" +
            "ORDER BY ${type} DESC\n" +
            "LIMIT ${skip}, ${limit}")
    List<EnterCloseValue> entryUrlsRank(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT COUNT(*) FROM (SELECT idurl_entry FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY idurl_entry) AS tt")
    int entryUrlsRankCount(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取出口页面列表
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 获取出口页面列表
     */
    @Select("SELECT\n" +
            "  t_url.url                     url,\n" +
            "  t_title.title                 title,\n" +
            "  COUNT(t.id)                   vt,\n" +
            "  COUNT(DISTINCT t.idvisitor)   uv,\n" +
            "  COUNT(DISTINCT t.location_ip) ip,\n" +
            "  SUM(t.count_visits)           pv,\n" +
            "  t_load.loaded                `load`,\n" +
            "  t_spent.spent                 spent\n" +
            "FROM t_visit AS t\n" +
            "  LEFT JOIN t_url ON t.idurl_exit = t_url.id\n" +
            "  LEFT JOIN t_title ON t.idtitle_exit = t_title.id\n" +
            "  LEFT JOIN (SELECT\n" +
            "               idurl,\n" +
            "               AVG(time_loaded)    loaded\n" +
            "             FROM t_action AS t\n" +
            "             WHERE idsite = ${idsite} AND (create_time BETWEEN #{start} AND #{end})\n" +
            "             GROUP BY idurl) AS t_load ON t.idurl_exit = t_load.idurl\n" +
            "  LEFT JOIN (SELECT\n" +
            "               ref_id_url          idurl,\n" +
            "               AVG(ref_time_spent) spent\n" +
            "             FROM t_action AS t\n" +
            "             WHERE idsite = ${idsite} AND ref_time_spent > 0 AND (create_time BETWEEN #{start} AND #{end})\n" +
            "             GROUP BY ref_id_url) AS t_spent ON t.idurl_exit = t_spent.idurl\n" +
            "WHERE t.idsite = ${idsite} AND (visit_servertime BETWEEN #{start} AND #{end})\n" +
            "GROUP BY idurl_exit\n" +
            "ORDER BY ${type} DESC\n" +
            "LIMIT ${skip}, ${limit}")
    List<EnterCloseValue> exitUrlsRank(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT COUNT(*) FROM (SELECT idurl_exit FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY idurl_exit) AS tt")
    int exitUrlsRankCount(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 访问页面数-分布
     *
     * @param idsite 网站ID
     * @param min    最小值
     * @param max    最大值
     * @param start  开始时间
     * @param end    结束时间
     * @return 分布值
     */
    @Select("SELECT\n" +
            "  COUNT(id)                   vt,\n" +
            "  COUNT(DISTINCT idvisitor)   uv,\n" +
            "  COUNT(DISTINCT location_ip) ip,\n" +
            "  SUM(count_visits)           pv\n" +
            "FROM t_visit AS t\n" +
            "WHERE idsite = ${idsite}\n" +
            "      AND (visit_servertime BETWEEN #{start} AND #{end})\n" +
            "      AND count_visits >= #{min}\n" +
            "      AND count_visits <= #{max}")
    VisitPageViewMapValue visitPageViewMap(@Param("idsite") String idsite, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

    /**
     * 用户忠诚度-访问深度-访问独立页面数-分布
     *
     * @param idsite 网站ID
     * @param min    最小值
     * @param max    最大值
     * @param start  开始时间
     * @param end    结束时间
     * @return 分布值
     */
    @Select("SELECT SUM(vt) vt,SUM(uv) uv,SUM(pv) pv\n" +
            "FROM (SELECT\n" +
            "        COUNT(DISTINCT idurl)     up,\n" +
            "        COUNT(DISTINCT idvisit)   vt,\n" +
            "        COUNT(DISTINCT idvisitor) uv,\n" +
            "        COUNT(id)                 pv\n" +
            "      FROM t_action AS t\n" +
            "      WHERE idsite = ${idsite} AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY idvisit) AS tt\n" +
            "  WHERE up>=#{min} AND up<=#{max}")
    VisitPageUniqueMapValue visitPageUniqueMap(@Param("idsite") String idsite, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

}
