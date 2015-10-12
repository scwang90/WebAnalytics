package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.SiteDao;
import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.model.base.ModelBase;
import com.simpletech.webanalytics.service.SiteService;
import com.simpletech.webanalytics.util.Page;
import com.simpletech.webanalytics.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表t_site的Service接实现
 * @author 树朾
 * @date 2015-10-12 15:00:31 中国标准时间
 */
@Service
public class SiteServiceImpl extends BaseServiceImpl<Site> implements SiteService{

	@Autowired
	SiteDao dao;
	
	@Override
	public int insert(Site model) throws Exception{
		if (ModelBase.class.isInstance(model)) {
			ModelBase.class.cast(model).check();
		}
		checkNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(Site model) throws Exception {
		Site old = findById(getModelID(model));
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
	public Site findById(Object id) throws Exception{
		return dao.findById(id);
	}

	@Override
	public List<Site> findAll() throws Exception{
		return dao.findAll();
	}

	@Override
	public int delete(String id) throws Exception{
		return dao.delete(id);
	}

	@Override
	public List<Site> findByPage(int limit, int start) throws Exception {
		return dao.findByPage(limit,start);
	}

	@Override
	public Site findById(String id) throws Exception {
		return dao.findById(id);
	}
	
	@Override
	public Page<Site> listByPage(int pageSize, int pageNo) throws Exception{
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<Site> list = dao.findByPage(limit, start);
		
		return new Page<Site>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() throws Exception {
		return dao.countAll();
	}
}
