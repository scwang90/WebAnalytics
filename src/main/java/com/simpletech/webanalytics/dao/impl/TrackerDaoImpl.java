package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.annotations.dbmodel.interpreter.Interpreter;
import com.simpletech.webanalytics.dao.TrackerDao;
import com.simpletech.webanalytics.mapper.TrackerMapper;
import com.simpletech.webanalytics.model.*;
import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.util.AfReflecter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

/**
 * js探针 的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class TrackerDaoImpl implements TrackerDao {

    @Autowired
    TrackerMapper mapper;


    @Override
    public Url getUrl(int siteId, String idsubsite, String url) throws Exception {
        Url _url = mapper.getUrl(siteId, url.hashCode());
        if (_url == null) {
            _url = new Url();
            _url.setIdsite(siteId);
            _url.setUrl(url);
            _url.setHash(url.hashCode());
            checkNullID(_url);
            _url.setIdsubsite(idsubsite);
            AfReflecter.setMemberNoException(_url, "createTime", new Date());
            AfReflecter.setMemberNoException(_url, "updateTime", new Date());
            mapper.insertUrl(_url);
        }
        return _url;
    }

    @Override
    public Title getTitle(int siteId, String idsubsite, String title) throws Exception {
        Title _title = mapper.getTitle(siteId, title.hashCode());
        if (_title == null) {
            _title = new Title();
            _title.setIdsite(siteId);
            _title.setTitle(title);
            _title.setHash(title.hashCode());
            checkNullID(_title);
            _title.setIdsubsite(idsubsite);
            AfReflecter.setMemberNoException(_title, "createTime", new Date());
            AfReflecter.setMemberNoException(_title, "updateTime", new Date());
            mapper.insertTitle(_title);
        }
        return _title;
    }

//    @Override
//    public Visit getVisit(int siteId, String idsubsite, JsDetect detect, Url url, Title title) throws Exception {
//        Visit visit = mapper.getVisit(siteId, detect.getIdvtor());
//        if (visit == null) {
//            visit = detect.build(siteId);
//            visit.setIdurlEntry(url.getId());
//            visit.setIdtitleEntry(title.getId());
//            visit.setIdurlExit(url.getId());
//            visit.setIdtitleExit(title.getId());
//            checkNullID(visit);
//            visit.setIdsubsite(idsubsite);
//            AfReflecter.setMemberNoException(visit, "createTime", new Date());
//            AfReflecter.setMemberNoException(visit, "updateTime", new Date());
//            mapper.insertVisit(visit);
//        }
//        return visit;
//    }

    @Override
    public Visit getVisitHalfHour(int siteId, String idsubsite, JsDetect detect, Url url, Title title) throws Exception {
        Visit visit = mapper.getVisitHalfHour(siteId, detect.getIdvtor());
        if (visit == null) {
            visit = detect.build(siteId);
            visit.setIdurlEntry(url.getId());
            visit.setIdtitleEntry(title.getId());
            visit.setIdurlExit(url.getId());
            visit.setIdtitleExit(title.getId());
            visit.setNewUser(!mapper.existVisitor(siteId, detect.getIdvtor()));
            checkNullID(visit);
            visit.setIdsubsite(idsubsite);
            AfReflecter.setMemberNoException(visit, "createTime", new Date());
            AfReflecter.setMemberNoException(visit, "updateTime", new Date());
            mapper.insertVisit(visit);
        }
        return visit;
    }

    @Override
    public Site findSiteById(int siteId) throws Exception {
        return mapper.findSiteById(siteId);
    }

    @Override
    public void updateVisit(String idsubsite, Visit visit) throws Exception {
        visit.setIdsubsite(idsubsite);
        AfReflecter.setMemberNoException(visit, "updateTime", new Date());
        mapper.updateVisit(visit);
    }

    @Override
    public void insertAction(String idsubsite, Action action) throws Exception {
        checkNullID(action);
        action.setIdsubsite(idsubsite);
        AfReflecter.setMemberNoException(action, "createTime", new Date());
        AfReflecter.setMemberNoException(action, "updateTime", new Date());
        mapper.insertAction(action);
    }

    @Override
    public void insertEvent(String idsubsite, Event event) throws Exception {
        checkNullID(event);
        event.setIdsubsite(idsubsite);
        AfReflecter.setMemberNoException(event, "createTime", new Date());
        AfReflecter.setMemberNoException(event, "updateTime", new Date());
        mapper.insertEvent(event);
    }

    /**
     * 检查ID字段是否为空，否则设置一个新ID
     * @param model
     * @throws Exception
     */
    protected void checkNullID(Object model) throws Exception {
        Class<?> clazz = model.getClass();
        Field field = Interpreter.getIdField(clazz);
        if (field != null) {
            field.setAccessible(true);
            Object id = field.get(model);
            if(id == null || id.toString().trim().length() == 0){
                field.set(model, UUID.randomUUID().toString());
            }
        }
    }
}

