package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.model.constant.Norm;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.entity.PeriodValue;
import com.simpletech.webanalytics.service.StatisticsService;
import com.simpletech.webanalytics.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 数据统计接口
 * Created by Administrator on 2015/9/25.
 */
@RestController
@RequestMapping("api")
public class StatisticsController {

    private static SimpleDateFormat fday = new SimpleDateFormat("yyMMdd");
    private static SimpleDateFormat fhour = new SimpleDateFormat("yyMMddHH");
    private static SimpleDateFormat fweek = new SimpleDateFormat("yy-ww");
    private static SimpleDateFormat fmonth = new SimpleDateFormat("yyMM");

    @Autowired
    StatisticsService statisticsService;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyyMMddHHmmss"), true));
    }

    /**
     * 灵活通用 Visit|PV|UV|IP统计数据获取API
     *
     * @param siteId 网站ID
     * @param period 时段周期
     * @param start  开始时间 java时间long值 如new Date().getTime()
     * @param end    结束时间 java时间long值 如new Date().getTime()
     * @return PV统计数据 {status:[true|false],data:[{time,date,val},...]}
     */
    @RequestMapping("{norm:visit|pv|uv|ip}/{siteId}/{period:hour|day|week|month}")
    public Object norm(@PathVariable Norm norm, @PathVariable String siteId, @PathVariable Period period, @RequestParam Date start, @RequestParam Date end) throws Exception {
        List<PeriodValue> list;
        switch (norm) {
            case visit:
                list = statisticsService.visit(siteId, period, start, end);
                break;
            case pv:
                list = statisticsService.pageView(siteId, period, start, end);
                break;
            case uv:
                list = statisticsService.uniqueVisitor(siteId, period, start, end);
                break;
            case ip:
                list = statisticsService.internetProtocol(siteId, period, start, end);
                break;
            default:
                throw new ServiceException("无效指标");
        }
        list = fulldata(list, period.getFormat(), period.getField(), start, end);
        return list;
    }


    @RequestMapping("{norm:visit|pv|uv|ip}/{siteId}/{period:hour|day|week|month}/{offset:\\d+}")
    public Object norm(@PathVariable Norm norm, @PathVariable String siteId, @PathVariable Period period,@PathVariable int offset) throws Exception {
        Date end = timeEnd(period, offset);
        Date start = timeStart(period, offset);
        List<PeriodValue> list;
        switch (norm) {
            case visit:
                list = statisticsService.visit(siteId, period, start, end);
                break;
            case pv:
                list = statisticsService.pageView(siteId, period, start, end);
                break;
            case uv:
                list = statisticsService.uniqueVisitor(siteId, period, start, end);
                break;
            case ip:
                list = statisticsService.internetProtocol(siteId, period, start, end);
                break;
            default:
                throw new ServiceException("无效指标");
        }
        list = fulldata(list, period.getFormat(), period.getField(), start, end);
        return list;
    }

    /**
     * 根据周期和便宜计算开始时间
     * @param period 时段周期
     * @param offset 偏移
     * @return 开始时间
     */
    private Date timeStart(Period period, int offset) throws ParseException {
        int field = period.getParentField();
        DateFormat format = period.getParentformat();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(format.format(calendar.getTime())));
        calendar.add(field,offset);
        return calendar.getTime();
    }

    /**
     * 根据周期和便宜计算结束时间
     * @param period 时段周期
     * @param offset 偏移
     * @return 结束时间
     */
    private Date timeEnd(Period period, int offset) throws ParseException {
        int field = period.getParentField();
        DateFormat format = period.getParentformat();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(format.format(calendar.getTime())));
        calendar.add(field,offset+1);
        return calendar.getTime();
    }

    /**
     * 按天来填充数据
     * @param list 数据库有效数据列表
     * @return 填充的数据
     */
    private List<PeriodValue> fulldata(List<PeriodValue> list, DateFormat format, int field, Date start, Date end) {
        Map<String,PeriodValue> map = tomap(list);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        List<PeriodValue> nlist = new ArrayList<>();
        while (calendar.getTime().before(end)) {
            String keytime = format.format(calendar.getTime());
            PeriodValue value = map.get(keytime);
            if (value == null) {
                value = new PeriodValue();
                value.setVal(0);
                value.setDate(keytime);
                value.setTime(calendar.getTime());
                nlist.add(value);
            }else {
                nlist.add(value);
                map.remove(keytime);
            }
            calendar.add(field, 1);
        }
        for (Map.Entry<String, PeriodValue> entry : map.entrySet()){
            nlist.add(entry.getValue());
        }
        return nlist;
    }

    /**
     * 把list转为map 方便查找
     * @param list 数据库有效数据列表
     * @return map
     */
    private Map<String, PeriodValue> tomap(List<PeriodValue> list) {
        Map<String, PeriodValue> map = new LinkedHashMap<>();
        for (PeriodValue value : list){
            map.put(value.getDate(),value);
        }
        return map;
    }

    @RequestMapping("event/{siteId}")
    public Object event(@PathVariable String siteId) throws Exception {
        return null;
    }

}
