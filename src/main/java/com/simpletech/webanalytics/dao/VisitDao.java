package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.dao.base.BaseDao;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.RankingValue;
import com.simpletech.webanalytics.model.entity.VisitValue;
import com.simpletech.webanalytics.model.entity.VisitorValue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
	 * 获取 idsite网站 start-end 的 [小时\日\周\月] [Visit|PV|UV|IP]
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 */
	List<VisitValue> visitHour(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> visitDay(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> visitWeek(int idsite, Date start, Date end) throws Exception;
	List<VisitValue> visitMonth(int idsite, Date start, Date end) throws Exception;

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
	 * 客户端排行
	 *  设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市
	 * @param idsite 网站ID
	 * @param rankingtype 排序类型 按 visit|uv|ip|pv
	 * @param start  开始时间
	 * @param end    结束时间
	 * @param limit  分页限制
	 * @param skip   分页起始
	 * @return 客户端设备品牌排行
	 */
	List<RankingValue> brand(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> model(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> nettype(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> browser(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> system(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> appname(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> resolution(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> depth(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> lang(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> country(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> province(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> city(int idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;

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
