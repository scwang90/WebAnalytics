package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.UrlDao;
import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.model.Url;
import com.simpletech.webanalytics.util.AfStringUtil;
import org.springframework.stereotype.Repository;

/**
 * 数据库表t_url的Dao实现
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class UrlDaoImpl extends BaseDaoImpl<Url> implements UrlDao{

	@Override
	public Url getUrl(String idsite, String _url) throws Exception {
		for (Url url : super.findByPropertyName("hash",_url.hashCode())){
			if (AfStringUtil.equals(idsite,url.getIdsite())){
				return url;
			}
		}
		return null;
	}

}

