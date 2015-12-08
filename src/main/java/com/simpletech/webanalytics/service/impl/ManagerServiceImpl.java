package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.ManagerDao;
import com.simpletech.webanalytics.mapper.api.ManagerMapper;
import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.model.Subsite;
import com.simpletech.webanalytics.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理API 的Service接实现
 *
 * @author 树朾
 * @date 2015-10-13 10:15:55 中国标准时间
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao dao;

    @Autowired
    ManagerMapper mapper;

    @Override
    public int insertSite(String userId, Site site) {
        site.check();
        site.setIduser(userId);
        return dao.insertSite(site);
    }

    @Override
    public int updateSite(String userId, Site site) {
        site.check();
        site.setIduser(userId);
        return dao.updateSite(site);
    }

    @Override
    public List<Site> findList(String userId, int limit, int start) {
        return mapper.findSiteList(userId, limit, start);
    }

    @Override
    public List<Subsite> findSubList(String userId, int siteId, int limit, int start) {
        return mapper.findSubSiteList(userId, siteId, limit, start);
    }
}
