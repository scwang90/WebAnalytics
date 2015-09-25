package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.model.constant.Norm;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.service.StatisticsService;
import com.simpletech.webanalytics.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据统计接口
 * Created by Administrator on 2015/9/25.
 */
@RestController
@RequestMapping("api")
public class StatisticsController{

    @Autowired
    StatisticsService statisticsService;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyyMMddHHmmss"), true));
    }

    /**
     * 灵活通用 Visit|PV|UV|IP统计数据获取API
     * @param siteId 网站ID
     * @param period 时段周期
     * @param start 开始时间 java时间long值 如new Date().getTime()
     * @param end 结束时间 java时间long值 如new Date().getTime()
     * @return PV统计数据 {status:[true|false],data:[{time,date,val},...]}
     */
    @RequestMapping("{norm:visit|pv|uv|ip}/{siteId}/{period:hour|day|week|month}")
    public Object norm(@PathVariable Norm norm,@PathVariable String siteId,@PathVariable Period period,@RequestParam Date start,@RequestParam Date end) throws Exception{
        switch (norm){
            case visit:
                return statisticsService.visit(siteId, period, start, end);
            case pv:
                return statisticsService.pageView(siteId, period, start, end);
            case uv:
                return statisticsService.uniqueVisitor(siteId, period, start, end);
            case ip:
                return statisticsService.internetProtocol(siteId, period, start, end);
        }
        throw new ServiceException("无效指标");
    }

    @RequestMapping("event/{siteId}")
    public Object event() throws Exception{
        return null;
    }

}
