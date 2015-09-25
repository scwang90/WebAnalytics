package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.dao.base.BaseDao;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.model.entity.PeriodValue;

import java.util.Date;
import java.util.List;

/**
 * 数据库表t_visit的Dao接口
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface VisitDao extends BaseDao<Visit>{

	/**
	 * 获取 idsite网站 的 Visit
	 * @param idsite 网站ID
	 * @param idvistitor 访问者
	 * @return Visit
	 */
	Visit getVisit(String idsite, String idvistitor) throws Exception;

	/**
	 * 获取 idsite网站 访问者30分钟内的 Visit
	 * @param idsite 网站ID
	 * @param idvistitor 访问者
	 * @return Visit
	 */
	Visit getVisitHalfHour(String idsite, String idvistitor) throws Exception;

	/**
	 * 获取 idsite网站 start-end 的 小时\日\周\月Visit
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 */
	List<PeriodValue> visitHour(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> visitDay(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> visitWeek(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> visitMonth(String idsite, Date start, Date end) throws Exception;

	/**
	 * 获取 idsite网站 start-end 的 小时\日\周\月PV
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 */
	List<PeriodValue> pageViewHour(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> pageViewDay(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> pageViewWeek(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> pageViewMonth(String idsite, Date start, Date end) throws Exception;

	/**
	 * 获取 idsite网站 start-end 的 小时\日\周\月UV
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 */
	List<PeriodValue> uniqueVisitorHour(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> uniqueVisitorDay(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> uniqueVisitorWeek(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> uniqueVisitorMonth(String idsite, Date start, Date end) throws Exception;

	/**
	 * 获取 idsite网站 start-end 的 小时\日\周\月IP
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 */
	List<PeriodValue> internetProtocolHour(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> internetProtocolDay(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> internetProtocolWeek(String idsite, Date start, Date end) throws Exception;
	List<PeriodValue> internetProtocolMonth(String idsite, Date start, Date end) throws Exception;

}
