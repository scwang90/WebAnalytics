package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.dao.base.BaseDao;
import com.simpletech.webanalytics.util.Page;

/**
 * 通用Service层接口
 * @param <T>
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface BaseService<T> extends BaseDao<T>{
	public int delete(String id) throws Exception;
	public T findById(String id) throws Exception;
	public Page<T> listByPage(int pageSize, int pageNo) throws Exception;
}
