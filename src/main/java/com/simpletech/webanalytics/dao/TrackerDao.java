package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.model.*;
import com.simpletech.webanalytics.model.entity.JsDetect;

/**
 * js探针 的Dao接口
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface TrackerDao{

	/**
	 * 根据 siteId 获取 url 的对象
	 * @param siteId 网站ID
	 * @param url 链接
	 * @return 返回有效的 Url 对象
	 * @throws Exception
	 */
	Url getUrl(int siteId, String idsubsite, String url) throws Exception;

	/**
	 * 根据 siteId 获取 title 的对象
	 * @param siteId 网站ID
	 * @param title 标题
	 * @return 返回有效的 Title 对象
	 * @throws Exception
	 */
	Title getTitle(int siteId, String idsubsite, String title) throws Exception;

//	/**
//	 * 获取 siteId网站 访问者detect Visit（如果不存在则添加）
//	 * @param siteId 网站ID
//	 * @param detect 探针抓取对象
//	 * @param url Url 对象
//	 * @param title Title 对象
//	 * @return 返回有效的 Visit 对象
//	 */
//	Visit getVisit(int siteId, String idsubsite, JsDetect detect, Url url, Title title) throws Exception;

	/**
	 * 获取 siteId网站 访问者detect 30分钟内的 Visit（如果不存在则添加）
	 * @param siteId 网站ID
	 * @param detect 探针抓取对象
	 * @param url Url 对象
	 * @param title Title 对象
	 * @return 返回有效的 Visit 对象
	 */
	Visit getVisitHalfHour(int siteId, String idsubsite, JsDetect detect, Url url, Title title) throws Exception;

	/**
	 * 根据网站ID获取网站
	 * @param siteId 网站ID
	 * @return 网站
	 */
	Site findSiteById(int siteId) throws Exception;

	/**
	 * 更新 Visit
	 * @param visit Visit
	 */
	void updateVisit(String idsubsite, Visit visit) throws Exception;
	/**
	 * 添加 action
	 * @param action action
	 */
	void insertAction(String idsubsite, Action action) throws Exception;
	/**
	 * 添加事件
	 * @param event event
	 */
	void insertEvent(String idsubsite, Event event) throws Exception;
}
