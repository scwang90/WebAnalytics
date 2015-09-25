package com.simpletech.webanalytics.service;

import java.util.List;

import com.simpletech.webanalytics.model.Url;

/**
 * 数据库表t_url的Service接口层
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface UrlService extends BaseService<Url>{

	/**
	 * 根据 idsite 获取 url 的对象 (如果不存在则添加并返回)
	 * @param idsite
	 * @param url
	 * @return 返回有效的 Url 对象
	 * @throws Exception
	 */
	Url getUrl(String idsite, String url) throws Exception;

}
