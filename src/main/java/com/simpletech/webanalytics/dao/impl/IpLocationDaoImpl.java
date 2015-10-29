package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import com.simpletech.webanalytics.model.Visit;
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

	@Override
	public int insert(IpLocation t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(IpLocation t) throws Exception {
		return super.update(t);
	}

	@Override
	public int delete(Object id) throws Exception {
		return super.delete(id);
	}

	@Override
	public int countAll() throws Exception {
		return super.countAll();
	}

	@Override
	public IpLocation findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<IpLocation> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<IpLocation> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}

	@Override
	public List<IpLocation> findAllIp()throws Exception{
		return super.findAllIp();
	}

}

