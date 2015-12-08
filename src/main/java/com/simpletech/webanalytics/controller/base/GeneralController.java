package com.simpletech.webanalytics.controller.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.simpletech.webanalytics.annotations.Intent;
import com.simpletech.webanalytics.service.base.BaseService;

/**
 * Controller 层通用处理事务基类
 * @param <T>
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public class GeneralController<T> extends BaseController{
	
	@Autowired
	BaseService<T> service;

	/**
	 * 添加信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@Intent("添加%s")
	public Object add(T model) {
		service.insert(model);
		return null;
	}
	/**
	 * 更新信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@Intent("更新%s")
	public Object update(T model) {
		service.update(model);
		return null;
	}
	/**
	 * 根据ID获取信息
	 * @param ID
	 * @return
	 * @throws Exception 
	 */
	@Intent("获取%s")
	public Object get(String ID) {
		Object model = service.findById(ID);
		if (model == null) {
			return "null";
		}
		return model;
	}
	/**
	 * 根据ID删除
	 * @return
	 * @throws Exception 
	 */
	@Intent("删除%s")
	public Object delete(final String ID) {
		service.delete(ID);
		return null;
	}

	/**
	 * 获取全部列表
	 * @return
	 * @throws Exception 
	 */
	@Intent("统计%s")
	public Object count() {
		return service.countAll();
	}

	/**
	 * 获取全部列表
	 * @return
	 * @throws Exception 
	 */
	@Intent("获取全部%s列表")
	public Object list() {
		return service.findAll();
	}

	/**
	 * 获取分页列表
	 * @param pageSize
	 * @param pageNo
	 * @return
	 * @throws Exception 
	 */
	@Intent("获取%s列表")
	public Object listByPage(int pageSize,int pageNo) {
		return service.listByPage(pageSize, pageNo);
	}
	
}
