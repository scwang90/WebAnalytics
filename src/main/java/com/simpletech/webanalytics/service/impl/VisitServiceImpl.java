package com.simpletech.webanalytics.service.impl;


import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.model.Title;
import com.simpletech.webanalytics.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletech.webanalytics.dao.VisitDao;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.service.VisitService;

/**
 * 数据库表t_visit的Service接实现
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Service
public class VisitServiceImpl extends BaseServiceImpl<Visit> implements VisitService{

	@Autowired
	VisitDao dao;

	@Override
	public Visit getVisitHalfHour(String idsite, JsDetect detect, Url url, Title title) throws Exception {
		Visit visit = dao.getVisitHalfHour(idsite, detect.getIdvtor());
		if (visit == null){
			visit = detect.build(idsite);
			visit.setIdurlEntry(url.getId());
			visit.setIdtitleEntry(title.getId());
			visit.setIdurlExit(url.getId());
			visit.setIdtitleExit(title.getId());
			dao.insert(visit);
		}
		return visit;
	}

	@Override
	public Visit getVisit(String idsite, JsDetect detect, Url url, Title title) throws Exception {
		Visit visit = dao.getVisit(idsite, detect.getIdvtor());
		if (visit == null){
			visit = detect.build(idsite);
			visit.setIdurlEntry(url.getId());
			visit.setIdtitleEntry(title.getId());
			visit.setIdurlExit(url.getId());
			visit.setIdtitleExit(title.getId());
			dao.insert(visit);
		}
		return visit;
	}
}
