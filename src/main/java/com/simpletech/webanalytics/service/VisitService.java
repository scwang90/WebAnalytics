package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.model.Title;
import com.simpletech.webanalytics.model.Url;
import com.simpletech.webanalytics.model.Visit;

/**
 * 数据库表t_visit的Service接口层
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface VisitService extends BaseService<Visit>{

	/**
	 * 获取 idsite网站 访问者detect 30分钟内的 Visit（如果不存在则添加）
	 * @param idsite 网站ID
	 * @param detect 探针抓取对象
	 * @param url Url 对象
	 * @param title Title 对象
	 * @return 返回有效的 Visit 对象
	 */
	Visit getVisitHalfHour(int idsite, JsDetect detect, Url url, Title title) throws Exception;

	/**
	 * 获取 idsite网站 访问者detect Visit（如果不存在则添加）
	 * @param idsite 网站ID
	 * @param detect 探针抓取对象
	 * @param url Url 对象
	 * @param title Title 对象
	 * @return 返回有效的 Visit 对象
	 */
	Visit getVisit(int idsite, JsDetect detect, Url url, Title title) throws Exception;
}
