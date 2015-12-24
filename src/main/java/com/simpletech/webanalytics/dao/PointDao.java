package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.model.IP;
import com.simpletech.webanalytics.model.Point;

import java.io.File;
import java.util.List;

/**
 * 数据库表t_visit的Dao接口
 * @author 树朾
 * @date 2015-10-12 15:00:31 中国标准时间
 */
public interface PointDao {

	List<Point> findPoint(String id);
}
