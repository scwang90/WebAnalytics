package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.TrackShareDao;
import com.simpletech.webanalytics.mapper.api.TrackShareMapper;
import com.simpletech.webanalytics.model.ShareLinePoint;
import com.simpletech.webanalytics.model.ShareStartPoint;
import com.simpletech.webanalytics.model.ShareUser;
import com.simpletech.webanalytics.model.entity.JsDetect;
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
    public ShareUser getShareUser(int siteId, String idvisitor) {
        return mapper.getShareUser(siteId, idvisitor);
    }

    @Override
    public ShareUser getShareUser(int siteId, String idsubsite, String idvisitor, JsDetect detect) {
        ShareUser user = mapper.getShareUser(siteId, idvisitor);
        if (user == null) {
//            user = new ShareUser();
//            user.setIdsite(siteId);
//            user.setIdvisitor(idvisitor);
//            user.fillNullID();
//            user.setIdsubsite(idsubsite);
//            user.setNickname(Browser.parserAcronym(detect.getBrowser()).getRemark() + " - " + detect.getVersion());
//            AfReflecter.setMemberNoException(user, "createTime", new Date());
//            AfReflecter.setMemberNoException(user, "updateTime", new Date());
//            mapper.insertShareUser(user);
        }
        return user;
    }

//    @Override
//    public ShareLinePoint getShareLinePoint(int siteId, String idsubsite, String idurl, String idfromtor, long fromts) {
//        ShareLinePoint point = mapper.getShareLinePoint(siteId, idurl, idfromtor);
//        if (point == null) {
//            point = new ShareLinePoint();
//            point.setIdsite(siteId);
//            point.setIdrefervisitor("");
//            point.setIdvisitor(idfromtor);//创建上一个分享点
//            point.fillNullID();
//            point.setIdsubsite(idsubsite);
//            point.setShareTime(new Date(fromts));
//            point.setCountPv(1);
//            AfReflecter.setMemberNoException(point, "createTime", new Date());
//            AfReflecter.setMemberNoException(point, "updateTime", new Date());
//            mapper.insertShareLinePoint(point);
//        }
//        return point;
//    }

    @Override
    public ShareLinePoint getShareLinePoint(int siteId, String idsubsite, String idurl, String idfromtor, String idvisitor) {
        return mapper.getShareLinePoint(siteId, idurl, idfromtor, idvisitor);
    }

    @Override
    public int updateShareUser(ShareUser user) {
        return mapper.updateShareUser(user);
    }

    @Override
    public boolean makeSureStartPoint(int siteId, String idsubsite, String idurl, String idfromtor) {
//        if (mapper.isStartPoint(siteId, idurl, idfromtor) == 0 && mapper.existStartPoint(siteId, idurl, idfromtor) == 0){
        if (mapper.checkStartPoint(siteId, idurl, idfromtor) == 0){
            ShareStartPoint point = new ShareStartPoint();
            point.fillNullID();
            point.setIdurl(idurl);
            point.setIdsite(siteId);
            point.setIdvisitor(idfromtor);
            point.setIdsubsite(idsubsite);
            point.setUpdateTime(new Date());
            point.setCreateTime(new Date());
            return mapper.insertShareStartPoint(point) > 0;
        }
        return false;
    }
}

