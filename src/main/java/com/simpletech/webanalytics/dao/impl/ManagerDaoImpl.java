package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.ManagerDao;
import com.simpletech.webanalytics.mapper.api.ManagerMapper;
import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.util.AfReflecter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

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
    public int insertSite(Site site) {
        site.fillNullID();
        AfReflecter.setMemberNoException(site, "createTime", new Date());
        AfReflecter.setMemberNoException(site, "updateTime", new Date());
        return mapper.insertSite(site);
    }

    @Override
    public int updateSite(Site site) {
        AfReflecter.setMemberNoException(site, "updateTime", new Date());
        return mapper.updateSite(site);
    }

}

