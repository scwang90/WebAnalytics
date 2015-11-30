package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.model.ShareLinePoint;
import com.simpletech.webanalytics.model.ShareStartPoint;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据统计 的Dao接口
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface StatisticsDao {

	/**
	 * 获取 idsite网站 start-end 的 [小时\日\周\月] [Visit|PV|UV|IP]
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 */
	List<VisitValue> visitHour(String idsite, Date start, Date end) throws Exception;
	List<VisitValue> visitDay(String idsite, Date start, Date end) throws Exception;
	List<VisitValue> visitWeek(String idsite, Date start, Date end) throws Exception;
	List<VisitValue> visitMonth(String idsite, Date start, Date end) throws Exception;

	/**
	 * 新老用户
	 *
	 * @param idsite 网站ID
	 * @param start  开始时间
	 * @param end    结束时间
	 * @return 新老用户
	 */
	List<VisitorValue> visitorHour(String idsite, Date start, Date end) throws Exception;
	List<VisitorValue> visitorDay(String idsite, Date start, Date end) throws Exception;
	List<VisitorValue> visitorWeek(String idsite, Date start, Date end) throws Exception;
	List<VisitorValue> visitorMonth(String idsite, Date start, Date end) throws Exception;

	/**
	 * 站点数据排行
	 *  设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市、ip地址
	 * @param idsite 网站ID
	 * @param rankingtype 排序类型 按 visit|uv|ip|pv
	 * @param start  开始时间
	 * @param end    结束时间
	 * @param limit  分页限制
	 * @param skip   分页起始
	 * @return 客户端设备品牌排行
	 */
	List<RankingValue> brand(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> model(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> nettype(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> browser(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> system(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> appname(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> resolution(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> depth(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> lang(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> country(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> province(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> city(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> ip(String idsite, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;

	/**
	 * 页面数据排行
	 *  设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市、ip地址
	 * @param idsite 网站ID
	 * @param idurl  页面ID
	 * @param rankingtype 排序类型 按 visit|uv|ip|pv
	 * @param start  开始时间
	 * @param end    结束时间
	 * @param limit  分页限制
	 * @param skip   分页起始
	 * @return 客户端设备品牌排行
	 */
	List<RankingValue> pageBrand(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageModel(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageNettype(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageBrowser(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageSystem(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageAppname(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageResolution(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageDepth(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageLang(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageCountry(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageProvince(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageCity(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
	List<RankingValue> pageIp(String idsite, String idurl, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;

	/**
	 * 统计 idsite网站 在 start-end 的 总 Visit
	 *
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 统计值
	 */
	Long countVisit(String idsite, Date start, Date end) throws Exception;

	/**
	 * 统计 idsite网站 在 start-end 的 总 用户（排重）
	 *
	 * @param idsite 网站ID
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 统计值
	 */
	Long countUsers(String idsite, Date start, Date end) throws Exception;

	/**
	 * 页面标题排行
	 *
	 * @param idsite 网站ID
	 * @param ranktype 排序类型 按 visit|uv|ip|pv
	 * @param start  开始时间
	 * @param end    结束时间
	 * @param limit  分页限制
	 * @param skip   分页起始
	 * @return 标题排行
	 */
	List<PageValue> pagetitle(String idsite, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception;

	/**
	 * 填充名称
	 * @param pagetitle PageValue 数据
	 * @return 带名称的 PageValue
	 */
	List<PageValue> fullTitleName(List<PageValue> pagetitle) throws Exception;

	/**
	 * 页面链接排行
	 *
	 * @param idsite 网站ID
	 * @param ranktype 排序类型 按 visit|uv|ip|pv
	 * @param start  开始时间
	 * @param end    结束时间
	 * @param limit  分页限制
	 * @param skip   分页起始
	 * @return 链接排行
	 */
	List<PageValue> pageurl(String idsite, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception;

	/**
	 * 填充名称
	 * @param pagetitle PageValue 数据
	 * @return 带名称的 PageValue
	 */
	List<PageValue> fullUrlName(List<PageValue> pagetitle) throws Exception;

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
	List<EventNameValue> event(String idsite, Date start, Date end, int limit, int skip) throws Exception;

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

	/**
	 * 分享传播点列表
	 *
	 * @param idsite   网站ID
	 * @param urlId    页面ID
	 * @param start    开始时间
	 * @param end      结束时间
	 * @return 分享传播点列表
	 */
	List<ShareLinePoint> sharePoint(String idsite, String urlId, Date start, Date end) throws Exception;

	/**
	 * 填充昵称
	 * @param points Map 数据
	 * @return 带昵称的 Map
	 */
	List<MapPointValue> fullNickName(List<MapPointValue> points) throws Exception;

	/**
	 * 页面分享排行
	 *
	 * @param idsite   网站ID
	 * @param start    开始时间
	 * @param end      结束时间
	 * @return 页面分享排行
	 */
	List<PageRankingValue> shareRanking(String idsite, Date start, Date end) throws Exception;

	/**
	 * 获取起始点列表
	 * @param idsite   网站ID
	 * @param urlId    页面ID
	 * @param start    开始时间
	 * @param end      结束时间
	 * @return 起始点列表
	 */
	List<ShareStartPoint>  getShareStartPoint(String idsite, String urlId, Date start, Date end) throws Exception;

	/**
	 * 获取入口页面列表
	 * @param idsite   网站ID
	 * @param start    开始时间
	 * @param end      结束时间
	 * @return 获取入口页面列表
	 */
	List<EnterCloseValue> entryUrls(String idsite, Date start, Date end) throws Exception;

	/**
	 * 获取出口页面列表
	 * @param idsite   网站ID
	 * @param start    开始时间
	 * @param end      结束时间
	 * @return 获取出口页面列表
	 */
	List<EnterCloseValue> exitUrls(String idsite, Date start, Date end) throws Exception;

	/**
	 * 获取出口页面列表
	 * @param siteId   网站ID
	 * @param start    开始时间
	 * @param end      结束时间
	 * @return 获取出口页面列表
	 */
	List<IspValue> isp(String siteId, Date start, Date end) throws Exception;
}
