package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.dao.base.BaseDao;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.entity.VisitValue;
import com.simpletech.webanalytics.model.entity.VisitorValue;

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
	 * @param idvisitor 访问者
	 * @return Visit
	 */
	Visit getVisit(int idsite, String idvisitor) throws Exception;

	/**
	 * 获取 idsite网站 访问者30分钟内的 Visit
	 * @param idsite 网站ID
	 * @param idvisitor 访问者
	 * @return Visit
	 */
	Visit getVisitHalfHour(int idsite, String idvisitor) throws Exception;

	/**
	 * 获取 idsite网站 start-end 的 小时\日\周\月Visit
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 */
	List<VisitValue> visitHour(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> visitDay(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> visitWeek(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> visitMonth(int idsite, Date start, Date end) throws Exception;

	/**
	 * 获取 idsite网站 在 start-end 的 小时\日\周\月PV
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 */
	List<VisitValue> pageViewHour(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> pageViewDay(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> pageViewWeek(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> pageViewMonth(int idsite, Date start, Date end) throws Exception;

	/**
	 * 获取 idsite网站 在 start-end 的 小时\日\周\月UV
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 */
	List<VisitValue> uniqueVisitorHour(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> uniqueVisitorDay(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> uniqueVisitorWeek(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> uniqueVisitorMonth(int idsite, Date start, Date end) throws Exception;

	/**
	 * 获取 idsite网站 在 start-end 的 小时\日\周\月IP
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 */
	List<VisitValue> internetProtocolHour(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> internetProtocolDay(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> internetProtocolWeek(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> internetProtocolMonth(int idsite, Date start, Date end) throws Exception;

	/**
	 * 新老用户
	 *
	 * @param idsite 网站ID
	 * @param start  开始时间
	 * @param end    结束时间
	 * @return 新老用户
	 */
	List<VisitorValue> visitorHour(int idsite, Date start, Date end) throws Exception;
	List<VisitorValue> visitorDay(int idsite, Date start, Date end) throws Exception;
	List<VisitorValue> visitorWeek(int idsite, Date start, Date end) throws Exception;
	List<VisitorValue> visitorMonth(int idsite, Date start, Date end) throws Exception;

	/**
	 * 统计 idsite网站 在 start-end 的 总 Visit
	 *
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 统计值
	 */
	Long countVisit(int idsite, Date start, Date end) throws Exception;
	/**
	 * 统计 idsite网站 在 start-end 的 总 用户（排重）
	 *
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 统计值
	 */
	Long countUsers(int idsite, Date start, Date end) throws Exception;

	/**
	 * 判断是否已经存在  访问者 idvtor
	 *
	 * @param idsite 网站ID
	 * @param idvtor 访问者ID
	 * @return 存在true
	 */
	boolean existVisitor(int idsite, String idvtor) throws Exception;
}
