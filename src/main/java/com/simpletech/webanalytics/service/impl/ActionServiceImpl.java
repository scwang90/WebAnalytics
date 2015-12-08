package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.ActionDao;
import com.simpletech.webanalytics.model.Action;
import com.simpletech.webanalytics.model.base.ModelBase;
import com.simpletech.webanalytics.service.ActionService;
import com.simpletech.webanalytics.service.base.BaseServiceImpl;
import com.simpletech.webanalytics.util.Page;
import com.simpletech.webanalytics.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表t_action的Service接实现
 * @author 树朾
 * @date 2015-10-15 12:18:28 中国标准时间
 */
@Service
public class ActionServiceImpl extends BaseServiceImpl<Action> implements ActionService{

	@Autowired
	ActionDao dao;
	
	@Override
	public int insert(Action model){
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(Action model) {
		Action old = findById(getModelID(model));
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
	public Action findById(Object id){
		return dao.findById(id);
	}

	@Override
	public List<Action> findAll(){
		return dao.findAll();
	}

	@Override
	public int delete(String id){
		return dao.delete(id);
	}

	@Override
	public List<Action> findByPage(int limit, int start) {
		return dao.findByPage(limit,start);
	}

	@Override
	public Action findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Page<Action> listByPage(int pageSize, int pageNo){
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<Action> list = dao.findByPage(limit, start);
		
		return new Page<Action>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() {
		return dao.countAll();
	}
}
