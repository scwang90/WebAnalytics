package com.simpletech.webanalytics.service.impl;


import java.util.List;

import com.simpletech.webanalytics.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletech.webanalytics.util.Page;
import com.simpletech.webanalytics.util.ServiceException;
import com.simpletech.webanalytics.dao.TitleDao;
import com.simpletech.webanalytics.model.Title;
import com.simpletech.webanalytics.service.TitleService;

/**
 * 数据库表t_title的Service接实现
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Service
public class TitleServiceImpl extends BaseServiceImpl<Title> implements TitleService{

	@Autowired
	TitleDao dao;

	@Override
	public Title getTitle(int idsite, String _title) throws Exception {
		Title title = dao.getUrl(idsite,_title);
		if (title == null){
			title = new Title();
			title.setIdsite(idsite);
			title.setTitle(_title);
			title.setHash(_title.hashCode());
			dao.insert(title);
		}
		return title;
	}

}
