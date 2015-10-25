package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.ShareStartPointDao;
import com.simpletech.webanalytics.model.ShareStartPoint;

/**
 * 数据库表t_share_start_point的Dao实现
 * @author 树朾
 * @date 2015-10-20 14:49:10 中国标准时间
 */
@Repository
public class ShareStartPointDaoImpl extends BaseDaoImpl<ShareStartPoint> implements ShareStartPointDao{

	@Override
	public int insert(ShareStartPoint t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(ShareStartPoint t) throws Exception {
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
	public ShareStartPoint findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<ShareStartPoint> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<ShareStartPoint> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}
}

