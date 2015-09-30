package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.model.entity.JsEvent;
import com.simpletech.webanalytics.model.*;
import com.simpletech.webanalytics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * JS探针 接收服务
 * Created by Administrator on 2015/9/22.
 */
@Service
public class TrackerServiceImpl implements TrackerService{

    @Autowired
    SiteService siteService;
    @Autowired
    EventService eventService;
    @Autowired
    VisitService visitService;
    @Autowired
    TitleService titleService;
    @Autowired
    UrlService urlService;
    @Autowired
    ActionService actionService;

    public void trackerPageView(String idsite, JsDetect detect) throws Exception{
        Site site = siteService.findById(idsite);
        if (site != null){
            Url url = urlService.getUrl(idsite, detect.getUrl());
            Title title = titleService.getTitle(idsite, detect.getTitle());
            Visit visit = visitService.getVisitHalfHour(idsite, detect, url, title);
            visit.setIdurlExit(url.getId());
            visit.setIdtitleExit(title.getId());
            visit.setCountVisits(visit.getCountVisits() + 1);
            visitService.update(visit);
            Action action = new Action();
            action.setIdsite(idsite);
            action.setIdvisit(visit.getId());
            action.setServerTime(new Date());
            action.setIdurl(url.getId());
            action.setIdtitle(title.getId());
            action.setTimeSpent(detect.getGtms());
            action.setIdvisitor(detect.getIdvtor());
            actionService.insert(action);
        }
    }

    @Override
    public void trackerEvent(String idsite, JsEvent event) throws Exception {
        Site site = siteService.findById(idsite);
        if (site != null){
            Url url = urlService.getUrl(idsite, event.getUrl());
            Title title = titleService.getTitle(idsite, event.getTitle());
            Visit visit = visitService.getVisitHalfHour(idsite, event, url, title);
            if (visit.getCountVisits() == 0){
                visit.setIdurlExit(url.getId());
                visit.setIdtitleExit(title.getId());
                visit.setCountVisits(visit.getCountVisits() + 1);
                Action action = new Action();
                action.setIdsite(idsite);
                action.setIdvisit(visit.getId());
                action.setServerTime(new Date());
                action.setIdurl(url.getId());
                action.setIdtitle(title.getId());
                action.setTimeSpent(0);
                action.setIdvisitor(event.getIdvtor());
                actionService.insert(action);
            }
            visit.setCountEvents(visit.getCountEvents()+1);
            visitService.update(visit);
            eventService.trackerEvent(site,event);
        }
    }
}
