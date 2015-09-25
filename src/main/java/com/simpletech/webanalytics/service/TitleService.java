package com.simpletech.webanalytics.service;

import java.util.List;

import com.simpletech.webanalytics.model.Title;
import com.simpletech.webanalytics.model.Url;

/**
 * 数据库表t_title的Service接口层
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface TitleService extends BaseService<Title>{

	/**
	 * 根据 idsite 获取 title 的对象(如果不存在则添加并返回)
	 * @param idsite
	 * @param title
	 * @return 返回有效的 Title 对象
	 * @throws Exception
	 */
	Title getTitle(String idsite, String title) throws Exception;

}
