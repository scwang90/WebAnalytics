package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.TrackShareDao;
import com.simpletech.webanalytics.mapper.TrackShareMapper;
import com.simpletech.webanalytics.model.SharePoint;
import com.simpletech.webanalytics.model.ShareUser;
import com.simpletech.webanalytics.util.AfReflecter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 分享传播分析 的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class TrackShareDaoImpl implements TrackShareDao {

    @Autowired
    TrackShareMapper mapper;

    @Override
    public ShareUser getShareUser(int siteId, String idvisitor) throws Exception {
        return mapper.getShareUser(siteId, idvisitor);
    }

    @Override
    public ShareUser getShareUser(int siteId, String idsubsite, String idvisitor) throws Exception {
        ShareUser user = mapper.getShareUser(siteId, idvisitor);
        if (user == null) {
            user = new ShareUser();
            user.setIdsite(siteId);
            user.setIdvisitor(idvisitor);
            user.fillNullID();
            user.setIdsubsite(idsubsite);
            AfReflecter.setMemberNoException(user, "createTime", new Date());
            AfReflecter.setMemberNoException(user, "updateTime", new Date());
            mapper.insertShareUser(user);
        }
        return user;
    }

//    @Override
//    public SharePoint getSharePoint(int siteId, String idsubsite, String idurl, String idfromtor, long fromts) throws Exception {
//        SharePoint point = mapper.getSharePoint(siteId, idurl, idfromtor);
//        if (point == null) {
//            point = new SharePoint();
//            point.setIdsite(siteId);
//            point.setIdrefervisitor("");
//            point.setIdvisitor(idfromtor);//创建上一个分享点
//            point.fillNullID();
//            point.setIdsubsite(idsubsite);
//            point.setShareTime(new Date(fromts));
//            point.setCountPv(1);
//            AfReflecter.setMemberNoException(point, "createTime", new Date());
//            AfReflecter.setMemberNoException(point, "updateTime", new Date());
//            mapper.insertSharePoint(point);
//        }
//        return point;
//    }

    @Override
    public SharePoint getSharePoint(int siteId, String idsubsite, String idurl, String idfromtor, String idvisitor) throws Exception {
        return mapper.getSharePoint(siteId, idurl, idfromtor, idvisitor);
    }

    @Override
    public int insertSharePoint(SharePoint point) throws Exception {
        return mapper.insertSharePoint(point);
    }

    @Override
    public int updateSharePoint(SharePoint tpoint) throws Exception {
        return mapper.updateSharePoint(tpoint);
    }

    @Override
    public int insertShareUser(ShareUser user) throws Exception {
        return mapper.insertShareUser(user);
    }

    @Override
    public int updateShareUser(ShareUser user) throws Exception {
        return mapper.updateShareUser(user);
    }
}

