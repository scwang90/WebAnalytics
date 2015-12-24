package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import com.simpletech.webanalytics.mapper.IpLocationMapper;
import com.simpletech.webanalytics.mapper.VisitMapper;
import com.simpletech.webanalytics.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.IpLocationDao;
import com.simpletech.webanalytics.model.IpLocation;

/**
 * 数据库表t_ip_location的Dao实现
 * @author 树朾
 * @date 2015-10-16 10:38:40 中国标准时间
 */
@Repository
public class IpLocationDaoImpl extends BaseDaoImpl<IpLocation> implements IpLocationDao{
	@Autowired
	IpLocationMapper ipLocationMapper;

	@Autowired
	VisitMapper visitMapper;
	@Override
	public int insert(IpLocation t) {
		return super.insert(t);
	}

	@Override
	public int update(IpLocation t) {
		return super.update(t);
	}

	@Override
	public int delete(Object id) {
		return super.delete(id);
	}

	@Override
	public int countAll() {
		return super.countAll();
	}

	@Override
	public IpLocation findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<IpLocation> findAll() {
		return super.findAll();
	}

	@Override
	public List<IpLocation> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}

//	@Override
//	public List<IpLocation> findVisitWhereByPage(String where,int limit, int start) {
//		return ipLocationMapper.findVisitWhereByPage(where, limit, start);
//	}

//	@Override
//	public List<IpLocation> findAllIp(){
//		List<IpLocation> ipLocations=ipLocationMapper.findAllIp();
//		return ipLocations;
//	}

}

