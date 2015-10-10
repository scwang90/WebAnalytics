package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.SiteDao;
import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.model.Site;
import org.springframework.stereotype.Repository;

/**
 * 数据库表t_site的Dao实现
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class SiteDaoImpl extends BaseDaoImpl<Site> implements SiteDao{


	@Override
	public boolean isNameExist(String name) throws Exception {
		return super.countByPropertyName("name",name) > 0;
	}
}

