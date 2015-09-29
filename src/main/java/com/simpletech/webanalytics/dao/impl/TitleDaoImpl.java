package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.TitleDao;
import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.model.Title;
import com.simpletech.webanalytics.model.entity.PageValue;
import com.simpletech.webanalytics.util.AfStringUtil;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
			List<Title> titles = findWhere(String.format(where, builder.substring(1)));
			Map<String,Title> map = new LinkedHashMap<>();
			for (Title title : titles) {
				map.put(title.getId(),title);
			}
			for (PageValue pagevalue: pagetitle){
				Title title = map.get(pagevalue.getPid());
				if (title != null) {
					pagevalue.setName(title.getTitle());
				}
			}
		}
		return pagetitle;
	}
}

