package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.model.Subsite;

import java.util.List;

/**
 * 管理API 的Service接口层
 *
 * @author 树朾
 * @date 2015-10-12 15:00:31 中国标准时间
 */
public interface ManagerService {

    /**
     * 添加网站
     *
     * @param site 网站
     * @return 改变行数
     */
    int insertSite(Site site) throws Exception;

    /**
     * 更新网站
     *
     * @param site 网站
     * @return 改变行数
     */
    int updateSite(Site site) throws Exception;

    /**
     * 获取网站列表
     *
     * @param limit 分页限制
     * @param start 分页开始
     * @return 网站列表
     */
    List<Site> findList(int limit, int start) throws Exception;

    /**
     * 获取子站列表
     *
     * @param siteId 网站ID
     * @param limit  分页限制
     * @param start  分页开始
     * @return 子站列表
     */
    List<Subsite> findSubList(int siteId, int limit, int start) throws Exception;
}
