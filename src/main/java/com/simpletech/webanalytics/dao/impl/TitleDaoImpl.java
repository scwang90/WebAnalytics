package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.TitleDao;
import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.model.Title;
import com.simpletech.webanalytics.util.AfStringUtil;
import org.springframework.stereotype.Repository;

/**
 * 数据库表t_title的Dao实现
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class TitleDaoImpl extends BaseDaoImpl<Title> implements TitleDao{

	@Override
	public Title getUrl(String idsite, String _title) throws Exception {
		for (Title title : super.findByPropertyName("hash",_title.hashCode())){
			if (AfStringUtil.equals(idsite,title.getIdsite())){
				return title;
			}
		}
		return null;
	}


}

