package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.entity.PeriodValue;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service
 * Created by Administrator on 2015/9/25.
 */
public interface StatisticsService {


    /**
     * Visit统计数据获取API
     * @param idsite 网站ID
     * @param period 时段周期
     * @param start 开始时间 java时间long值 如new Date().getTime()
     * @param end 结束时间 java时间long值 如new Date().getTime()
     * @return PV统计数据 {status:[true|false],data:[{time,date,val},...]}
     */
    List<PeriodValue> visit(String idsite, Period period, Date start, Date end) throws Exception;
    /**
     * PV统计数据获取API
     * @param idsite 网站ID
     * @param period 时段周期
     * @param start 开始时间 java时间long值 如new Date().getTime()
     * @param end 结束时间 java时间long值 如new Date().getTime()
     * @return PV统计数据 {status:[true|false],data:[...]}
     */
    List<PeriodValue> pageView(String idsite, Period period, Date start, Date end) throws Exception;

    /**
     * UV统计数据获取API
     * @param idsite 网站ID
     * @param period 时段周期
     * @param start 开始时间 java时间long值 如new Date().getTime()
     * @param end 结束时间 java时间long值 如new Date().getTime()
     * @return PV统计数据 {status:[true|false],data:[...]}
     */
    List<PeriodValue> uniqueVisitor(String idsite, Period period, Date start, Date end) throws Exception;

    /**
     * IP统计数据获取API
     * @param idsite 网站ID
     * @param period 时段周期
     * @param start 开始时间 java时间long值 如new Date().getTime()
     * @param end 结束时间 java时间long值 如new Date().getTime()
     * @return IP统计数据 {status:[true|false],data:[{time,date,val},...]}
     */
    List<PeriodValue> internetProtocol(String idsite, Period period, Date start, Date end) throws Exception;

}
