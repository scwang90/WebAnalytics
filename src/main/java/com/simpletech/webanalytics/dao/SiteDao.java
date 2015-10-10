package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.dao.base.BaseDao;
import com.simpletech.webanalytics.model.Site;

/**
 * 数据库表t_site的Dao接口
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface SiteDao extends BaseDao<Site>{

	/**
	 * 验证名称存在
	 * @param name 名称
	 * @return true 存在
	 */
	boolean isNameExist(String name) throws Exception;
}
