package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.model.Subsite;

import java.util.List;

/**
 * 管理API 的Dao接口
 *
 * @author 树朾
 * @date 2015-10-12 15:00:31 中国标准时间
 */
public interface ManagerDao {

    /**
     * 添加网站
     *
     * @param site 网站
     * @return 改变行数
     */
    int insertSite(Site site);

    /**
     * 更新网站
     *
     * @param site 网站
     * @return 改变行数
     */
    int updateSite(Site site);

}
