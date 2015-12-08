package com.simpletech.webanalytics.dao.base;

import java.util.List;

/**
 * 通用Dao层接口
 * @param <T>
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface BaseDao<T> {
	/**
	 * 插入一条新数据
	 * @param model
	 */
	public int insert(T model);
	/**
	 * 根据ID删除
	 * @param id
	 */
	public int delete(Object id);
	/**
	 * 更新一条数据
	 * @param model
	 */
	public int update(T model);
	/**
	 * 统计全部出数据
	 */
	public int countAll();
	/**
	 * 根据ID获取
	 * @param id
	 */
	public T findById(Object id);
	/**
	 * 获取全部数据
	 */
	public List<T> findAll();
	/**
	 * 分页查询数据
	 * @param limit
	 * @param start
	 */
	public List<T> findByPage(int limit, int start);

}
