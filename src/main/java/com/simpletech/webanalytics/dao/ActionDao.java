package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.dao.base.BaseDao;
import com.simpletech.webanalytics.model.Action;
import com.simpletech.webanalytics.model.entity.PageValue;

import java.util.Date;
import java.util.List;

/**
 * 数据库表t_action的Dao接口
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface ActionDao extends BaseDao<Action> {

    /**
     * 页面标题排行
     *
     * @param siteId 网站ID
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 标题排行
     */
    List<PageValue> pagetitle(String siteId, Date start, Date end, int limit, int skip) throws Exception;

    /**
     * 页面链接排行 - 自定义时段
     *
     * @param siteId 网站ID
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 链接排行
     */
    List<PageValue> pageurl(String siteId, Date start, Date end, int limit, int skip) throws Exception;

}
