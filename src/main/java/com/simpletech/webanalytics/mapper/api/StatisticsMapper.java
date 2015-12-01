package com.simpletech.webanalytics.mapper.api;

import com.simpletech.webanalytics.model.*;
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
     * @throws Exception
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d%H') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitValue> visitTrendHour(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitValue> visitTrendDay(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y-%u') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitValue> visitTrendWeek(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m') date, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitValue> visitTrendMonth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 获取 idsite网站 start-end 的 [小时\日\周\月] [Visit|PV|UV|IP] 时段
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @throws Exception
     */
    @Select("SELECT COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) ")
    VisitValue visitSpan(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

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
            "      FROM t_event\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY category\n" +
            "      ORDER BY num DESC) AS t1\n" +
            "  , (SELECT\n" +
            "       COUNT(id)                 vt,\n" +
            "       COUNT(DISTINCT idvisitor) uv\n" +
            "     FROM t_visit\n" +
            "     WHERE idsite = ${idsite}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "    ) AS t2")
    List<EventNameValue> eventRank(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;

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
            "      FROM t_event\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "    ) AS t1\n" +
            "  , (SELECT\n" +
            "       COUNT(id)                 vt,\n" +
            "       COUNT(DISTINCT idvisitor) uv\n" +
            "     FROM t_visit\n" +
            "     WHERE idsite = ${idsite}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "    ) AS t2")
    EventSpanValue eventSpan(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 获取 idsite网站 事件 在 start-end 的 趋势数据
     *
     * @param idsite   网站ID
     * @param start    开始时间
     * @param end      结束时间
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
            "      FROM t_event\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m%d%H') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY date) AS t2 ON t1.date=t2.date\n" +
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
            "      FROM t_event\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m%d') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY date) AS t2 ON t1.date=t2.date\n" +
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
            "      FROM t_event\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y-%u') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY date) AS t2 ON t1.date=t2.date\n" +
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
            "      FROM t_event\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY date) AS t2 ON t1.date=t2.date\n" +
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
            "      FROM t_event\n" +
            "      WHERE idsite = ${idsite}\n" +
            "            AND category=#{name}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY category\n" +
            "      ORDER BY num DESC) AS t1\n" +
            "  , (SELECT\n" +
            "       COUNT(id)                 vt,\n" +
            "       COUNT(DISTINCT idvisitor) uv\n" +
            "     FROM t_visit\n" +
            "     WHERE idsite = ${idsite}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "    ) AS t2")
    EventNameSpanValue eventNameSpan(@Param("idsite") String idsite, @Param("name") String name, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 获取 idsite网站 事件name 在 start-end 的 小时\日\周\月 统计数据
     *
     * @param idsite   网站ID
     * @param name     事件名称
     * @param start    开始时间
     * @param end      结束时间
     * @param limit    分页限制
     * @param skip     分页起始
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
            "      FROM t_event\n" +
            "      WHERE idsite = ${idsite}\n" +
            "           AND category=#{name}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m%d%H') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY date) AS t2 ON t1.date=t2.date\n" +
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
            "      FROM t_event\n" +
            "      WHERE idsite = ${idsite}\n" +
            "           AND category=#{name}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m%d') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY date) AS t2 ON t1.date=t2.date\n" +
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
            "      FROM t_event\n" +
            "      WHERE idsite = ${idsite}\n" +
            "           AND category=#{name}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y-%u') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY date) AS t2 ON t1.date=t2.date\n" +
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
            "      FROM t_event\n" +
            "      WHERE idsite = ${idsite}\n" +
            "           AND category=#{name}\n" +
            "           AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t1 LEFT JOIN\n" +
            "  (SELECT\n" +
            "     DATE_FORMAT(create_time, '%y%m') date,\n" +
            "     COUNT(id)                            vt,\n" +
            "     COUNT(DISTINCT idvisitor)            uv\n" +
            "   FROM t_visit\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY date) AS t2 ON t1.date=t2.date\n" +
            "ORDER BY date LIMIT ${skip},${limit}")
    List<EventNameTrendValue> eventNameTrendMonth(@Param("idsite") String idsite, @Param("name") String name, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);
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
    @Select("SELECT\n" +
            "  t1.id          id,\n" +
            "  title,\n" +
            "  t1.pv          pv,\n" +
            "  t1.vt          vt,\n" +
            "  t1.uv          uv,\n" +
            "  t1.pv / t2.tpv rpv,\n" +
            "  t1.vt / t2.tvt rvt,\n" +
            "  t1.uv / t2.tuv ruv,\n" +
            "  t1.spent / num spent,\n" +
            "  t1.loaded      loaded\n" +
            "FROM\n" +
            "  (SELECT\n" +
            "     idtitle                            id,\n" +
            "     COUNT(t_action.id)                 pv,\n" +
            "     COUNT(DISTINCT idvisit)            vt,\n" +
            "     AVG(time_loaded)                   loaded,\n" +
            "     COUNT(DISTINCT idvisitor)          uv,\n" +
            "     SUM((time_spent > 0) * time_spent) spent,\n" +
            "     COUNT((time_spent > 0))            num\n" +
            "   FROM t_action\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY idtitle\n" +
            "   ORDER BY ${type} DESC\n" +
            "   LIMIT ${skip}, ${limit}\n" +
            "  ) AS t1\n" +
            "  LEFT JOIN t_title ON t_title.id = t1.id,\n" +
            "  (SELECT\n" +
            "     SUM(count_visits)         tpv,\n" +
            "     COUNT(id)                 tvt,\n" +
            "     COUNT(DISTINCT idvisitor) tuv\n" +
            "   FROM t_visit\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "  ) AS t2")
    List<PageValue> pagetitle(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT\n" +
            "  t1.id          id,\n" +
            "  url,\n" +
            "  title,\n" +
            "  t1.pv          pv,\n" +
            "  t1.vt          vt,\n" +
            "  t1.uv          uv,\n" +
            "  t1.pv / t2.tpv rpv,\n" +
            "  t1.vt / t2.tvt rvt,\n" +
            "  t1.uv / t2.tuv ruv,\n" +
            "  t1.spent / num spent,\n" +
            "  t1.loaded      loaded\n" +
            "FROM\n" +
            "  (SELECT\n" +
            "     idurl                              id,\n" +
            "     idtitle                            tid,\n" +
            "     COUNT(t_action.id)                 pv,\n" +
            "     COUNT(DISTINCT idvisit)            vt,\n" +
            "     AVG(time_loaded)                   loaded,\n" +
            "     COUNT(DISTINCT idvisitor)          uv,\n" +
            "     SUM((time_spent > 0) * time_spent) spent,\n" +
            "     COUNT((time_spent > 0))            num\n" +
            "   FROM t_action\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY idurl\n" +
            "   ORDER BY ${type} DESC\n" +
            "   LIMIT ${skip}, ${limit}\n" +
            "  ) AS t1\n" +
            "  LEFT JOIN t_url ON t_url.id = t1.id\n" +
            "  LEFT JOIN t_title ON t_title.id = t1.tid,\n" +
            "  (SELECT\n" +
            "     SUM(count_visits)         tpv,\n" +
            "     COUNT(id)                 tvt,\n" +
            "     COUNT(DISTINCT idvisitor) tuv\n" +
            "   FROM t_visit\n" +
            "   WHERE idsite = ${idsite}\n" +
            "         AND (create_time BETWEEN #{start} AND #{end})\n" +
            "  ) AS t2")
    List<PageValue> pageurl(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;

    /**
     * 统计排行总量（标题|链接 ）
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据总量
     */
    @Select("SELECT COUNT(id) pv, COUNT(DISTINCT idvisit) vt, AVG(time_loaded) ts, COUNT(DISTINCT idvisitor) uv FROM t_action WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end})")
    PageValue coutpage(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;


    /**
     * 新老用户-时段
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    @Select("SELECT SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end})")
    VisitorSpanValue visitorSpan(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 新老用户-趋势
     *
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d%H') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorTrendValue> visitorTrendHour(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m%d') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorTrendValue> visitorTrendDay(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y-%u') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorTrendValue> visitorTrendWeek(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(visit_servertime,'%y%m') date,SUM(new_user) nv,SUM(new_sub_user) subnv, COUNT(DISTINCT idvisitor) uv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<VisitorTrendValue> visitorTrendMonth(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 站点数据排行
     *  设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市、IP地址
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
    @Select("SELECT end_app name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
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
    @Select("SELECT location_ip name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> ip(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;

    /**
     * 统计排行总量（visit|uv|pv）
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据总量
     */
    @Select("SELECT COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) ")
    RankingValue coutRanking(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 页面数据排行
     *  设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市、IP地址
     *  RIGHT JOIN (SELECT idvisit FROM	t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit
     *  RIGHT JOIN (SELECT idvisit FROM	t_action WHERE idurl = #{idurl} AND idsite=${idsite} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit
     * @param idsite 网站ID
     * @param idurl  页面ID
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 数据排行
     */
    @Select("SELECT end_brand name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageBrand(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT end_model name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageModel(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT net_type name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageNettype(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT browser_name name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageBrowser(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT operate_system name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageSystem(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT end_app name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageAppname(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT screen_resolution name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageResolution(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT screen_depth name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageDepth(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT location_lang name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageLang(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT location_country name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageCountry(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT location_region name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageProvince(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT location_city name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageCity(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
    @Select("SELECT location_ip name, COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<RankingValue> pageIp(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;

    /**
     * 页面统计排行总量（visit|uv|pv）
     *  RIGHT JOIN (SELECT idvisit FROM	t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit
     *  RIGHT JOIN (SELECT idvisit FROM	t_action WHERE idurl = #{idurl} AND idsite=${idsite} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit
     * @param idsite 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据总量
     */
    @Select("SELECT COUNT(id) vt,COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip,SUM(count_visits) pv FROM t_visit RIGHT JOIN (SELECT idvisit FROM t_action WHERE idurl = #{idurl} AND (server_time BETWEEN #{start} AND #{end}) GROUP BY idvisit) t ON t_visit.id=t.idvisit ")
    RankingValue pageCoutRank(@Param("idsite") String idsite, @Param("idurl") String idurl, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 分享传播点列表
     *
     * @param idsite   网站ID
     * @param urlId    页面ID
     * @param start    开始时间
     * @param end      结束时间
     * @return 分享传播点列表
     */
    @Select("SELECT id , idsite , idsubsite , idurl , idvisitor , idrefervisitor , count_pv countPv , share_span shareSpan , share_time shareTime , create_time createTime , update_time updateTime FROM t_share_line_point WHERE idsite=${idsite} AND idurl=#{urlId} AND (create_time BETWEEN #{start} AND #{end}) ")
    List<ShareLinePoint> sharePoint(@Param("idsite") String idsite, @Param("urlId") String urlId, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 选择性查询 ShareUser
     * @param where SQL条件语句
     * @return 符合条件的ShareUser列表数据
     */
    @Select("SELECT id , idsite , idsubsite , idvisitor , openid , unionid , nickname , headimgurl , sex , province , city , country , privilege , create_time createTime , update_time updateTime FROM t_share_user ${where} ${order}")
    List<ShareUser> findShareUserWhere(@Param("order") String order, @Param("where") String where) throws Exception;

    /**
     * 页面分享排行
     *
     * @param idsite   网站ID
     * @param start    开始时间
     * @param end      结束时间
     * @return 页面分享排行
     */
    @Select("SELECT id pid,num,url FROM t_url RIGHT JOIN (SELECT idurl,COUNT(id) num FROM t_share_line_point WHERE idsite=${idsite} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY idurl) t on t.idurl=t_url.id LIMIT ${skip},${limit}")
    List<PageRankValue> shareRank(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;

    /**
     * 获取起始点列表
     *
     * @param idsite   网站ID
     * @param urlId    页面ID
     * @param start    开始时间
     * @param end      结束时间
     * @return 起始点列表
     */
    @Select("SELECT id , idsite , idsubsite , idurl , idvisitor , create_time createTime , update_time updateTime FROM t_share_start_point WHERE idsite=${idsite} AND idurl=#{urlId} AND (create_time BETWEEN #{start} AND #{end}) ")
    List<ShareStartPoint> getShareStartPoint(@Param("idsite") String idsite, @Param("urlId") String urlId, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 获取入口页面列表
     * Piwik SQL
     * SELECT visit_exit_idaction_url,t_log_action.name FROM `t_log_visit` LEFT JOIN t_log_action ON t_log_visit.visit_exit_idaction_url=t_log_action.idaction WHERE idsite=2 AND DATE_FORMAT(`visit_first_action_time`,'%y%m%d')=DATE_FORMAT(NOW(),'%y%m%d') GROUP BY visit_exit_idaction_url
     * @param idsite   网站ID
     * @param start    开始时间
     * @param end      结束时间
     * @return 获取入口页面列表
     */
    @Select("SELECT t_url.url ,COUNT(t_visit.id) vt,COUNT(DISTINCT t_visit.idvisitor) uv, COUNT(DISTINCT t_visit.location_ip) ip,SUM(t_visit.count_visits) pv FROM t_visit LEFT JOIN t_url ON t_visit.idurl_entry=t_url.id WHERE t_visit.idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY idurl_entry ORDER BY ${type} DESC LIMIT ${skip},${limit}")
//    @Select("SELECT t_url.url name,t.num FROM t_url RIGHT JOIN (SELECT idurl_entry,COUNT(id) num FROM t_visit WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY idurl_entry ORDER BY num DESC) as t ON t.idurl_entry=t_url.id")
    List<EnterCloseValue> entryUrls(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;

    /**
     * 获取出口页面列表
     * @param idsite   网站ID
     * @param start    开始时间
     * @param end      结束时间
     * @return 获取出口页面列表
     */
    @Select("SELECT t_url.url name,COUNT(t_visit.id) vt,COUNT(DISTINCT t_visit.idvisitor) uv, COUNT(DISTINCT t_visit.location_ip) ip,SUM(t_visit.count_visits) pv FROM t_visit LEFT JOIN t_url ON t_visit.idurl_exit=t_url.id WHERE t_visit.idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY idurl_exit ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<EnterCloseValue> exitUrls(@Param("idsite") String idsite, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
}
