package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.VisitDao;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.entity.PeriodValue;
import com.simpletech.webanalytics.service.SiteService;
import com.simpletech.webanalytics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 统计API Service 实现
 * Created by Administrator on 2015/9/25.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService{

    @Autowired
    SiteService siteService;
    @Autowired
    VisitDao visitDao;

    @Override
    public List<PeriodValue> visit(String idsite, Period period, Date start, Date end) throws Exception {
        switch (period){
            case hour:
                return visitDao.visitHour(idsite, start, end);
            case day:
                return visitDao.visitDay(idsite, start, end);
            case week:
                return visitDao.visitWeek(idsite, start, end);
            case month:
                return visitDao.visitMonth(idsite, start, end);
        }
        return new ArrayList<PeriodValue>();
    }

    @Override
    public List<PeriodValue> pageView(String idsite, Period period, Date start, Date end) throws Exception{
        switch (period){
            case hour:
                return visitDao.pageViewHour(idsite, start, end);
            case day:
                return visitDao.pageViewDay(idsite, start, end);
            case week:
                return visitDao.pageViewWeek(idsite, start, end);
            case month:
                return visitDao.pageViewMonth(idsite, start, end);
        }
        return new ArrayList<PeriodValue>();
    }

    @Override
    public List<PeriodValue> uniqueVisitor(String idsite, Period period, Date start, Date end) throws Exception {
        switch (period){
            case hour:
                return visitDao.uniqueVisitorHour(idsite, start, end);
            case day:
                return visitDao.uniqueVisitorDay(idsite, start, end);
            case week:
                return visitDao.uniqueVisitorWeek(idsite, start, end);
            case month:
                return visitDao.uniqueVisitorMonth(idsite, start, end);
        }
        return new ArrayList<PeriodValue>();
    }

    @Override
    public List<PeriodValue> internetProtocol(String idsite, Period period, Date start, Date end) throws Exception {
        switch (period){
            case hour:
                return visitDao.internetProtocolHour(idsite, start, end);
            case day:
                return visitDao.internetProtocolDay(idsite, start, end);
            case week:
                return visitDao.internetProtocolWeek(idsite, start, end);
            case month:
                return visitDao.internetProtocolMonth(idsite, start, end);
        }
        return new ArrayList<PeriodValue>();
    }

}
