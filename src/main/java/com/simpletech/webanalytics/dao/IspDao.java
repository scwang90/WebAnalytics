package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.model.entity.IspValue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 数据库表t_visit的Dao接口
 * @author 树朾
 * @date 2015-10-12 15:00:31 中国标准时间
 */
public interface IspDao {

	/**
	 * 更新历史数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	int updateIsp(Visit model)throws Exception;

	/**
	 * 查找符合要求的数据
	 * @param where
	 * @return
	 * @throws Exception
	 */
	List<Visit> findWhereIsp(String where,int limit,int start);

	List<HashMap<String,Object>> ispBatch(String where,int limit,int start);
	/**
	 * 统计isp信息
	 * @param siteId   网站ID
	 * @param start    开始时间
	 * @param end      结束时间
	 * @return 获取出口页面列表
	 */
	List<IspValue> isp(String siteId, Date start, Date end);
}
