package com.simpletech.webanalytics.service.impl;


import com.simpletech.webanalytics.dao.UrlDao;
import com.simpletech.webanalytics.model.Url;
import com.simpletech.webanalytics.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据库表t_url的Service接实现
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Service
public class UrlServiceImpl extends BaseServiceImpl<Url> implements UrlService{

	@Autowired
	UrlDao dao;

	@Override
	public Url getUrl(String idsite, String _url) throws Exception{
		Url url = dao.getUrl(idsite,_url);
		if (url == null){
			url = new Url();
			url.setIdsite(idsite);
			url.setUrl(_url);
			url.setHash(_url.hashCode());
			dao.insert(url);
		}
		return url;
	}

}
