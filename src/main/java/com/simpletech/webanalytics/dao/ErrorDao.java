package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.*;

import java.util.Date;
import java.util.List;

/**
 * 数据统计 的Dao接口
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface ErrorDao {

	/**
	 * 客户端排行
	 *  设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市
	 * @param limit  分页限制
	 * @param skip   分页起始
	 * @return 客户端设备品牌排行
	 */
	List<ErrorValue> brand(int limit, int skip);
	List<ErrorValue> model(int limit, int skip);
	List<ErrorValue> nettype(int limit, int skip);
	List<ErrorValue> browser(int limit, int skip);
	List<ErrorValue> system(int limit, int skip);
	List<ErrorValue> appname(int limit, int skip);
	List<ErrorValue> resolution(int limit, int skip);
	List<ErrorValue> depth(int limit, int skip);
	List<ErrorValue> lang(int limit, int skip);
	List<ErrorValue> country(int limit, int skip);
	List<ErrorValue> province(int limit, int skip);
	List<ErrorValue> city(int limit, int skip);

}
