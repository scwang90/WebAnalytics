package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.SharePointDao;
import com.simpletech.webanalytics.model.SharePoint;

/**
 * 数据库表t_share_point的Dao实现
 * @author 树朾
 * @date 2015-10-15 11:32:44 中国标准时间
 */
@Repository
public class SharePointDaoImpl extends BaseDaoImpl<SharePoint> implements SharePointDao{

	@Override
	public int insert(SharePoint t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(SharePoint t) throws Exception {
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
	public SharePoint findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<SharePoint> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<SharePoint> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}
}

