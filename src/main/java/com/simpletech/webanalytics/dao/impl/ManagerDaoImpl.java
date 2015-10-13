package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.ManagerDao;
import com.simpletech.webanalytics.mapper.ManagerMapper;
import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.model.Subsite;
import com.simpletech.webanalytics.util.AfReflecter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 管理API 的Dao实现
 *
 * @author 树朾
 * @date 2015-10-13 10:15:55 中国标准时间
 */
@Repository
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    ManagerMapper mapper;

    @Override
    public int insertSite(Site site) throws Exception {
        site.fillNullID();
        AfReflecter.setMemberNoException(site, "createTime", new Date());
        AfReflecter.setMemberNoException(site, "updateTime", new Date());
        return mapper.insertSite(site);
    }

    @Override
    public int updateSite(Site site) throws Exception {
        AfReflecter.setMemberNoException(site, "updateTime", new Date());
        return mapper.updateSite(site);
    }

    @Override
    public List<Site> findList(int limit, int start) throws Exception {
        return mapper.findSiteList(limit, start);
    }

    @Override
    public List<Subsite> findSubList(int siteId, int limit, int start) throws Exception {
        return mapper.findSubSiteList(siteId, limit, start);
    }
}

