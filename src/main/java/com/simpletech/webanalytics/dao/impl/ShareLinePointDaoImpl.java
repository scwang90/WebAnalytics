package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.ShareLinePointDao;
import com.simpletech.webanalytics.model.ShareLinePoint;

/**
 * 数据库表t_share_line_point的Dao实现
 * @author 树朾
 * @date 2015-10-20 14:49:10 中国标准时间
 */
@Repository
public class ShareLinePointDaoImpl extends BaseDaoImpl<ShareLinePoint> implements ShareLinePointDao{

	@Override
	public int insert(ShareLinePoint t) {
		return super.insert(t);
	}

	@Override
	public int update(ShareLinePoint t) {
		return super.update(t);
	}

	@Override
	public int delete(Object id) {
		return super.delete(id);
	}

	@Override
	public int countAll() {
		return super.countAll();
	}

	@Override
	public ShareLinePoint findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<ShareLinePoint> findAll() {
		return super.findAll();
	}

	@Override
	public List<ShareLinePoint> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

