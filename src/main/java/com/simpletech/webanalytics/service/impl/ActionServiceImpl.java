package com.simpletech.webanalytics.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletech.webanalytics.util.Page;
import com.simpletech.webanalytics.util.ServiceException;
import com.simpletech.webanalytics.dao.ActionDao;
import com.simpletech.webanalytics.model.Action;
import com.simpletech.webanalytics.service.ActionService;

/**
 * 数据库表t_action的Service接实现
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Service
public class ActionServiceImpl extends BaseServiceImpl<Action> implements ActionService{

	@Autowired
	ActionDao dao;
	
	@Override
	public int insert(Action model) throws Exception{
		// TODO Auto-generated method stub
		checkNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(Action model) throws Exception {
		// TODO Auto-generated method stub
		Action old = findById(getModelID(model));
		if (old == null) {
			throw new ServiceException("请求更新记录不存在或已经被删除！");
		}
		model = checkNullField(old, model);
		return dao.update(model);
	}

	@Override
	public int delete(Object id) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public Action findById(Object id) throws Exception{
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Action> findAll() throws Exception{
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public int delete(String id) throws Exception{
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public List<Action> findByPage(int limit, int start) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByPage(limit,start);
	}

	@Override
	public Action findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
	
	@Override
	public Page<Action> listByPage(int pageSize, int pageNo) throws Exception{
		// TODO Auto-generated method stub
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<Action> list = dao.findByPage(limit, start);
		
		return new Page<Action>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.countAll();
	}
}
