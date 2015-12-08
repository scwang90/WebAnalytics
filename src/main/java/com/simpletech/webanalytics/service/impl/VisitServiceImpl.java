package com.simpletech.webanalytics.service.impl;

import com.ipmapping.BDIP;
import com.ipmapping.txIP.IPTest;
import com.simpletech.webanalytics.dao.VisitDao;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.model.base.ModelBase;
import com.simpletech.webanalytics.model.entity.IspValue;
import com.simpletech.webanalytics.service.VisitService;
import com.simpletech.webanalytics.service.base.BaseServiceImpl;
import com.simpletech.webanalytics.util.Page;
import com.simpletech.webanalytics.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据库表t_visit的Service接实现
 * @author 树朾
 * @date 2015-10-15 12:18:28 中国标准时间
 */
@Service
public class VisitServiceImpl extends BaseServiceImpl<Visit> implements VisitService{

	@Autowired
	VisitDao dao;
	
	@Override
	public int insert(Visit model){
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(Visit model) {
		Visit old = findById(getModelID(model));
		if (old == null) {
			throw new ServiceException("请求更新记录不存在或已经被删除！");
		}
		model = checkNullField(old, model);
		return dao.update(model);
	}

	@Override
	public int delete(Object id) {
		return dao.delete(id);
	}

	@Override
	public Visit findById(Object id){
		return dao.findById(id);
	}

	@Override
	public List<Visit> findAll(){
		return dao.findAll();
	}

	@Override
	public int delete(String id){
		return dao.delete(id);
	}

	@Override
	public List<Visit> findByPage(int limit, int start) {
		return dao.findByPage(limit, start);
	}

	@Override
	public Visit findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Page<Visit> listByPage(int pageSize, int pageNo){
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<Visit> list = dao.findByPage(limit, start);
		
		return new Page<Visit>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() {
		return dao.countAll();
	}

//	public List<Visit> findWhereByPage(String where,int limit, int start){
//		return dao.findWhereByPage(where,limit,start);
//	}

//	@Override
//	public int updateCompared(Visit model) {
//		Visit old = findById(getModelID(model));
//		if (old == null) {
//			throw new ServiceException("请求更新记录不存在或已经被删除！");
//		}
//		model = checkNullField(old, model);
//		return dao.updateCompared(model);
//	}
}
