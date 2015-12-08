package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.ShareUserDao;
import com.simpletech.webanalytics.model.ShareUser;
import com.simpletech.webanalytics.model.base.ModelBase;
import com.simpletech.webanalytics.service.ShareUserService;
import com.simpletech.webanalytics.service.base.BaseServiceImpl;
import com.simpletech.webanalytics.util.Page;
import com.simpletech.webanalytics.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表t_share_user的Service接实现
 * @author 树朾
 * @date 2015-10-15 12:18:28 中国标准时间
 */
@Service
public class ShareUserServiceImpl extends BaseServiceImpl<ShareUser> implements ShareUserService{

	@Autowired
	ShareUserDao dao;
	
	@Override
	public int insert(ShareUser model){
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(ShareUser model) {
		ShareUser old = findById(getModelID(model));
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
	public ShareUser findById(Object id){
		return dao.findById(id);
	}

	@Override
	public List<ShareUser> findAll(){
		return dao.findAll();
	}

	@Override
	public int delete(String id){
		return dao.delete(id);
	}

	@Override
	public List<ShareUser> findByPage(int limit, int start) {
		return dao.findByPage(limit,start);
	}

	@Override
	public ShareUser findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Page<ShareUser> listByPage(int pageSize, int pageNo){
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<ShareUser> list = dao.findByPage(limit, start);
		
		return new Page<ShareUser>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() {
		return dao.countAll();
	}
}
