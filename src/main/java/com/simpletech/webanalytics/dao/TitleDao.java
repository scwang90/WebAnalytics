package com.simpletech.webanalytics.dao;

import java.util.List;

import com.simpletech.webanalytics.dao.base.BaseDao;
import com.simpletech.webanalytics.model.Title;

/**
 * 数据库表t_title的Dao接口
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface TitleDao extends BaseDao<Title>{

	/**
	 * 根据 idsite 获取 title 的对象
	 * @param idsite
	 * @param title
	 * @return 返回有效的 Title 对象
	 * @throws Exception
	 */
	public Title getUrl(String idsite, String title) throws Exception;
}
