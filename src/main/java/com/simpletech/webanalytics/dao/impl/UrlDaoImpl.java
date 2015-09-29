package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.UrlDao;
import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.model.Url;
import com.simpletech.webanalytics.model.Url;
import com.simpletech.webanalytics.model.entity.PageValue;
import com.simpletech.webanalytics.util.AfStringUtil;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<PageValue> fullName(List<PageValue> pagetitle) throws Exception {
		if (pagetitle.size() > 0){
			String where = "WHERE id IN(%s)";
			StringBuffer builder = new StringBuffer();
			for (PageValue pagevalue: pagetitle){
				builder.append(",'");
				builder.append(pagevalue.getPid());
				builder.append("'");
			}
			List<Url> titles = findWhere(String.format(where, builder.substring(1)));
			Map<String,Url> map = new LinkedHashMap<>();
			for (Url url : titles) {
				map.put(url.getId(),url);
			}
			for (PageValue pagevalue: pagetitle){
				Url url = map.get(pagevalue.getPid());
				if (url != null) {
					pagevalue.setName(url.getUrl());
				}
			}
		}
		return pagetitle;
	}
}

