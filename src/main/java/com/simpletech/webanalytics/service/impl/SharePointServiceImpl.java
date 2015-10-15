package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.SharePointDao;
import com.simpletech.webanalytics.model.SharePoint;
import com.simpletech.webanalytics.model.base.ModelBase;
import com.simpletech.webanalytics.service.SharePointService;
import com.simpletech.webanalytics.service.base.BaseServiceImpl;
import com.simpletech.webanalytics.util.Page;
import com.simpletech.webanalytics.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表t_share_point的Service接实现
 * @author 树朾
 * @date 2015-10-15 12:18:28 中国标准时间
 */
@Service
public class SharePointServiceImpl extends BaseServiceImpl<SharePoint> implements SharePointService{

	@Autowired
	SharePointDao dao;
	
	@Override
	public int insert(SharePoint model) throws Exception{
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(SharePoint model) throws Exception {
		SharePoint old = findById(getModelID(model));
		if (old == null) {
			throw new ServiceException("请求更新记录不存在或已经被删除！");
		}
		model = checkNullField(old, model);
		return dao.update(model);
	}

	@Override
	public int delete(Object id) throws Exception {
		return dao.delete(id);
	}

	@Override
	public SharePoint findById(Object id) throws Exception{
		return dao.findById(id);
	}

	@Override
	public List<SharePoint> findAll() throws Exception{
		return dao.findAll();
	}

	@Override
	public int delete(String id) throws Exception{
		return dao.delete(id);
	}

	@Override
	public List<SharePoint> findByPage(int limit, int start) throws Exception {
		return dao.findByPage(limit,start);
	}

	@Override
	public SharePoint findById(String id) throws Exception {
		return dao.findById(id);
	}
	
	@Override
	public Page<SharePoint> listByPage(int pageSize, int pageNo) throws Exception{
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<SharePoint> list = dao.findByPage(limit, start);
		
		return new Page<SharePoint>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() throws Exception {
		return dao.countAll();
	}
}
