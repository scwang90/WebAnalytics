package com.simpletech.webanalytics.dao;

import java.util.List;

import com.simpletech.webanalytics.dao.base.BaseDao;
import com.simpletech.webanalytics.model.Url;
import com.simpletech.webanalytics.model.entity.PageValue;

/**
 * 数据库表t_url的Dao接口
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface UrlDao extends BaseDao<Url>{

	/**
	 * 根据 idsite 获取 url 的对象
	 * @param idsite 网站ID
	 * @param url 链接
	 * @return 返回有效的 Url 对象
	 * @throws Exception
	 */
	Url getUrl(String idsite, String url) throws Exception;

	/**
	 * 填充名称
	 * @param pagetitle PageValue 数据
	 * @return 带名称的 PageValue
	 * @throws Exception
	 */
	List<PageValue> fullName(List<PageValue> pagetitle) throws Exception;
}
