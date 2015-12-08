package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.ShareUserDao;
import com.simpletech.webanalytics.model.ShareUser;

/**
 * 数据库表t_share_user的Dao实现
 * @author 树朾
 * @date 2015-10-15 11:32:44 中国标准时间
 */
@Repository
public class ShareUserDaoImpl extends BaseDaoImpl<ShareUser> implements ShareUserDao{

	@Override
	public int insert(ShareUser t) {
		return super.insert(t);
	}

	@Override
	public int update(ShareUser t) {
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
	public ShareUser findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<ShareUser> findAll() {
		return super.findAll();
	}

	@Override
	public List<ShareUser> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

