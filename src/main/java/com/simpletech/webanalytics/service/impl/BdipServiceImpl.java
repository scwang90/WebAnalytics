package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.BdipDao;
import com.simpletech.webanalytics.model.Bdip;
import com.simpletech.webanalytics.model.base.ModelBase;
import com.simpletech.webanalytics.service.BdipService;
import com.simpletech.webanalytics.service.base.BaseServiceImpl;
import com.simpletech.webanalytics.util.Page;
import com.simpletech.webanalytics.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表t_bdip的Service接实现
 * @author 树朾
 * @date 2015-12-08 12:53:53 中国标准时间
 */
@Service
public class BdipServiceImpl extends BaseServiceImpl<Bdip> implements BdipService{

	@Autowired
	BdipDao dao;
	
	@Override
	public int insert(Bdip model){
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(Bdip model) {
		Bdip old = findById(getModelID(model));
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
	public Bdip findById(Object id){
		return dao.findById(id);
	}

	@Override
	public List<Bdip> findAll(){
		return dao.findAll();
	}

	@Override
	public int delete(String id){
		return dao.delete(id);
	}

	@Override
	public List<Bdip> findByPage(int limit, int start) {
		return dao.findByPage(limit,start);
	}

	@Override
	public Bdip findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Page<Bdip> listByPage(int pageSize, int pageNo){
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<Bdip> list = dao.findByPage(limit, start);
		
		return new Page<Bdip>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() {
		return dao.countAll();
	}
}
