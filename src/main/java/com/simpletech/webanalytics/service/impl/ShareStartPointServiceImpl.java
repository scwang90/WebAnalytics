package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.ShareStartPointDao;
import com.simpletech.webanalytics.model.ShareStartPoint;
import com.simpletech.webanalytics.model.base.ModelBase;
import com.simpletech.webanalytics.service.ShareStartPointService;
import com.simpletech.webanalytics.service.base.BaseServiceImpl;
import com.simpletech.webanalytics.util.Page;
import com.simpletech.webanalytics.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表t_share_start_point的Service接实现
 * @author 树朾
 * @date 2015-10-20 14:49:10 中国标准时间
 */
@Service
public class ShareStartPointServiceImpl extends BaseServiceImpl<ShareStartPoint> implements ShareStartPointService{

	@Autowired
	ShareStartPointDao dao;
	
	@Override
	public int insert(ShareStartPoint model){
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(ShareStartPoint model) {
		ShareStartPoint old = findById(getModelID(model));
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
	public ShareStartPoint findById(Object id){
		return dao.findById(id);
	}

	@Override
	public List<ShareStartPoint> findAll(){
		return dao.findAll();
	}

	@Override
	public int delete(String id){
		return dao.delete(id);
	}

	@Override
	public List<ShareStartPoint> findByPage(int limit, int start) {
		return dao.findByPage(limit,start);
	}

	@Override
	public ShareStartPoint findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Page<ShareStartPoint> listByPage(int pageSize, int pageNo){
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<ShareStartPoint> list = dao.findByPage(limit, start);
		
		return new Page<ShareStartPoint>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() {
		return dao.countAll();
	}
}
