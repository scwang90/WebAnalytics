package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.model.entity.IspValue;
import com.simpletech.webanalytics.service.base.BaseService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 数据库表t_visit的Service接口层
 * @author 树朾
 * @date 2015-10-15 12:18:28 中国标准时间
 */
public interface IspService {

	/**
	 * 更新t_visit表中旧记录的运营商信息
	 * @param where
	 * @return
	 * @throws Exception
	 */
	List<Visit> findWhereIsp(String where,int limit,int start) throws Exception;

	/**
	 * 使用百度接口查找ISP
	 * @param where
	 * @param limit
	 * @param start
	 * @return
	 * @throws Exception
	 */
	List<HashMap<String,Object>> ispBatch(String where,int limit,int start) throws Exception;

	/**
	 * 使用国内ip数据库查找IP地址对应的ISP信息
	 * @param where
	 * @param limit
	 * @param start
	 * @return
	 * @throws Exception
	 */
	List<HashMap<String,Object>> ispLocalBatch(String where,int limit,int start) throws Exception;
	/**
	 * 统计店铺isp信息
	 * @param siteId
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	List<IspValue> isp(String siteId,Date start,Date end) throws Exception;
}
