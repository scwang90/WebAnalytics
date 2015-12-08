package com.simpletech.webanalytics.service.base;

import com.simpletech.webanalytics.dao.base.BaseDao;
import com.simpletech.webanalytics.util.Page;

/**
 * 通用Service层接口
 * @param <T>
 * @author 树朾
 * @date 2015-10-15 12:18:28 中国标准时间
 */
public interface BaseService<T> extends BaseDao<T>{
	int delete(String id);
	T findById(String id);
	Page<T> listByPage(int pageSize, int pageNo);
}
