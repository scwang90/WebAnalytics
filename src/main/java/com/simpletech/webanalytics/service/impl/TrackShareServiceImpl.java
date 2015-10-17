package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.TrackShareDao;
import com.simpletech.webanalytics.model.SharePoint;
import com.simpletech.webanalytics.model.ShareUser;
import com.simpletech.webanalytics.model.Url;
import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.service.TrackShareService;
import com.simpletech.webanalytics.util.AfReflecter;
import com.simpletech.webanalytics.util.AfStringUtil;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * JS探针 接收服务
 * Created by 树朾 on 2015/9/22.
 */
@Service
public class TrackShareServiceImpl implements TrackShareService {

    @Autowired
    TrackShareDao dao;

    @Override
    public void trackerShare(int siteId, String idsubsite, JsDetect detect, Url url) throws Exception {
        if (AfStringUtil.isNotEmpty(detect.getFromvid()) && detect.getFromvts() > 0) {
            String idVisitor = detect.getIdvtor();
            String idFromtor = detect.getFromvid();
            if (!idVisitor.equals(idFromtor)) {
                ShareUser formtor = dao.getShareUser(siteId, idsubsite, idFromtor);
                //获取上一个点（不存在则添加）
//                SharePoint lpoint = dao.getSharePoint(siteId, idsubsite, url.getId(), idFromtor, detect.getFromvts());
                //获取本次分享点
                SharePoint tpoint = dao.getSharePoint(siteId, idsubsite, url.getId(), idFromtor, idVisitor);
                if (tpoint == null) {
                    tpoint = new SharePoint();
                    tpoint.setIdsite(siteId);
                    tpoint.setIdrefervisitor(idFromtor);
                    tpoint.setIdvisitor(idVisitor);//创建上一个分享点
                    tpoint.fillNullID();
                    tpoint.setIdsubsite(idsubsite);
                    tpoint.setShareTime(new Date());
                    tpoint.setShareSpan(Math.abs(new Date().getTime() - detect.getFromvts()));
                    tpoint.setCountPv(1);
                    AfReflecter.setMemberNoException(tpoint, "createTime", new Date());
                    AfReflecter.setMemberNoException(tpoint, "updateTime", new Date());
                    dao.insertSharePoint(tpoint);
                } else {
                    tpoint.setCountPv(1 + tpoint.getCountPv());
                    dao.updateSharePoint(tpoint);
                }
            }
        }
    }

    @Override
    public void addOrUpdateUser(int siteId, ShareUser user) throws Exception {
        ShareUser old = dao.getShareUser(user.getIdsite(), user.getIdvisitor());
        if (old == null) {
            user.setIdsite(siteId);
            dao.insertShareUser(user);
        } else {
            dao.updateShareUser(checkNullField(old, user));
        }
    }

    /**
     * 检测非空字段
     *
     * @param old   老数据
     * @param model 新数据model
     */
    @SuppressWarnings("unchecked")
    protected <T> T checkNullField(T old, T model) {
        try {
            Class<?> clazz = old.getClass();
            old = (T) JacksonUtil.toObject(JacksonUtil.toJson(old), clazz);
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                Object nfield = field.get(model);
                if (nfield != null) {
                    field.set(old, nfield);
                }
            }
        } catch (Exception e) {

        }
        return old;
    }
}
