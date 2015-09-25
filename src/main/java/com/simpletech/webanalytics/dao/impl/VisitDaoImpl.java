package com.simpletech.webanalytics.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.simpletech.webanalytics.mapper.VisitMapper;
import com.simpletech.webanalytics.model.entity.PeriodValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.VisitDao;
import com.simpletech.webanalytics.model.Visit;

/**
 * 数据库表t_visit的Dao实现
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao{

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	VisitMapper mapper;

	@Override
	public Visit getVisit(String idsite, String idvistitor) throws Exception {
		String where = "where idsite=%s and idvistitor='%s'";
		for (Visit visit : findWhere(String.format(where,idsite,idvistitor))){
			return visit;
		}
		return null;
	}

	@Override
	public Visit getVisitHalfHour(String idsite, String idvistitor) throws Exception {
		Date time = new Date(new Date().getTime()-30*60*1000);
		String where = "where idsite=%s and idvistitor='%s' and visit_servertime > '%s'";
		for (Visit visit : findWhere(String.format(where,idsite,idvistitor,format.format(time)))){
			return visit;
		}
		return null;
	}

	@Override
	public List<PeriodValue> visitHour(String idsite, Date start, Date end) throws Exception {
		return mapper.visitHour(idsite, start, end);
	}

	@Override
	public List<PeriodValue> visitDay(String idsite, Date start, Date end) throws Exception {
		return mapper.visitDay(idsite, start, end);
	}

	@Override
	public List<PeriodValue> visitWeek(String idsite, Date start, Date end) throws Exception {
		return mapper.visitWeek(idsite, start, end);
	}

	@Override
	public List<PeriodValue> visitMonth(String idsite, Date start, Date end) throws Exception {
		return mapper.visitMonth(idsite, start, end);
	}

	@Override
	public List<PeriodValue> pageViewHour(String idsite, Date start, Date end) throws Exception {
		return mapper.pageViewHour(idsite,start,end);
	}

	@Override
	public List<PeriodValue> pageViewDay(String idsite, Date start, Date end) throws Exception {
		return mapper.pageViewDay(idsite, start, end);
	}

	@Override
	public List<PeriodValue> pageViewWeek(String idsite, Date start, Date end) throws Exception {
		return mapper.pageViewWeek(idsite, start, end);
	}

	@Override
	public List<PeriodValue> pageViewMonth(String idsite, Date start, Date end) throws Exception {
		return mapper.pageViewMonth(idsite, start, end);
	}

	@Override
	public List<PeriodValue> uniqueVisitorHour(String idsite, Date start, Date end) throws Exception {
		return mapper.uniqueVisitorHour(idsite, start, end);
	}

	@Override
	public List<PeriodValue> uniqueVisitorDay(String idsite, Date start, Date end) throws Exception {
		return mapper.uniqueVisitorDay(idsite, start,end);
	}

	@Override
	public List<PeriodValue> uniqueVisitorWeek(String idsite, Date start, Date end) throws Exception {
		return mapper.uniqueVisitorWeek(idsite, start,end);
	}

	@Override
	public List<PeriodValue> uniqueVisitorMonth(String idsite, Date start, Date end) throws Exception {
		return mapper.uniqueVisitorMonth(idsite, start,end);
	}

	@Override
	public List<PeriodValue> internetProtocolHour(String idsite, Date start, Date end) throws Exception {
		return mapper.internetProtocolHour(idsite, start,end);
	}

	@Override
	public List<PeriodValue> internetProtocolDay(String idsite, Date start, Date end) throws Exception {
		return mapper.internetProtocolDay(idsite, start,end);
	}

	@Override
	public List<PeriodValue> internetProtocolWeek(String idsite, Date start, Date end) throws Exception {
		return mapper.internetProtocolWeek(idsite, start,end);
	}

	@Override
	public List<PeriodValue> internetProtocolMonth(String idsite, Date start, Date end) throws Exception {
		return mapper.internetProtocolMonth(idsite, start,end);
	}
}

