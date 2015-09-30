package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.model.constant.Norm;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.entity.PeriodValue;
import com.simpletech.webanalytics.service.StatisticsService;
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

    @Autowired
    StatisticsService service;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyyMMddHHmmss"), true));
    }

    /**
     * 新老用户 - 自定义时段
     *
     * @param siteId 网站ID
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 标题排行
     */
    @RequestMapping("user/site/{siteId}/")
    public Object user(@PathVariable String siteId, @PathVariable int limit, @PathVariable int skip, @RequestParam Date start, @RequestParam Date end) throws Exception{
        return service.pagetitle(siteId, start, end, limit, skip);
    }

    /**
     * 页面标题排行 - 自定义时段
     *
     * @param siteId 网站ID
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 标题排行
     */
    @RequestMapping("pagetitle/site/{siteId}/{limit:\\d+}/{skip:\\d+}")
    public Object pagetitle(@PathVariable String siteId, @PathVariable int limit, @PathVariable int skip, @RequestParam Date start, @RequestParam Date end) throws Exception{
        return service.pagetitle(siteId, start, end, limit, skip);
    }

    /**
     * 页面标题排行 - 固定时段
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year] 注：要大于 period
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 标题排行
     * @throws Exception
     */
    @RequestMapping("pagetitle/site/{siteId}/{offset:-?\\d+}/{span:day|week|month|year}/{limit:\\d+}/{skip:\\d+}")
    public Object pagetitle(@PathVariable String siteId,@PathVariable int offset, @PathVariable Period span, @PathVariable int limit, @PathVariable int skip) throws Exception{
        Date end = timeEnd(span, offset);
        Date start = timeStart(span, offset);
        return service.pagetitle(siteId, start, end, limit, skip);
    }


    /**
     * 页面链接排行 - 自定义时段
     *
     * @param siteId 网站ID
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 链接排行
     */
    @RequestMapping("pageurl/site/{siteId}/{limit:\\d+}/{skip:\\d+}")
    public Object pageurl(@PathVariable String siteId, @PathVariable int limit, @PathVariable int skip, @RequestParam Date start, @RequestParam Date end) throws Exception{
        return service.pageurl(siteId, start, end, limit, skip);
    }

    /**
     * 页面链接排行 - 固定时段
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year] 注：要大于 period
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 链接排行
     * @throws Exception
     */
    @RequestMapping("pageurl/site/{siteId}/{offset:-?\\d+}/{span:day|week|month|year}/{limit:\\d+}/{skip:\\d+}")
    public Object pageurl(@PathVariable String siteId,@PathVariable int offset, @PathVariable Period span, @PathVariable int limit, @PathVariable int skip) throws Exception{
        Date end = timeEnd(span, offset);
        Date start = timeStart(span, offset);
        return service.pageurl(siteId, start, end, limit, skip);
    }

    /**
     * 自定义时段 event 统计数据获取API
     *
     * @param siteId 网站ID
     * @param name   事件名称
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return PV统计数据 {status:[true|false],data:[{time,date,num,rn,user,ru},...]}
     */
    @RequestMapping("event/site/{siteId}/name/{name}/{period:hour|day|week|month}/{limit:\\d+}/{skip:\\d+}")
    public Object event(@PathVariable String siteId, @PathVariable String name, @PathVariable Period period, @PathVariable int limit, @PathVariable int skip, @RequestParam Date start, @RequestParam Date end) throws Exception {
        return service.event(siteId, name, period, start, end, limit, skip);
    }

    /**
     * 固定时段 event 统计数据获取API
     *
     * @param siteId 网站ID
     * @param name   事件名称
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year] 注：要大于 period
     * @param limit  分页限制
     * @param skip   分页起始
     * @return event统计数据 {status:[true|false],data:[{time,date,name,num,rn,user,ru},...]}
     */
    @RequestMapping("event/site/{siteId}/name/{name}/{offset:-?\\d+}/{span:day|week|month|year}/{period:hour|day|week|month}/{limit:\\d+}/{skip:\\d+}")
    public Object event(@PathVariable String siteId, @PathVariable String name, @PathVariable int offset, @PathVariable Period span, @PathVariable Period period, @PathVariable int limit, @PathVariable int skip) throws Exception {
        Date end = timeEnd(span, offset);
        Date start = timeStart(span, offset);
        return service.event(siteId, name, period, start, end, limit, skip);
    }


    /**
     * 自定义时段 event 统计数据获取API
     *
     * @param siteId 网站ID
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return PV统计数据 {status:[true|false],data:[{name,num,rn,user,ru},...]}
     */
    @RequestMapping("event/site/{siteId}/{limit:\\d+}/{skip:\\d+}")
    public Object event(@PathVariable String siteId, @PathVariable int limit, @PathVariable int skip, @RequestParam Date start, @RequestParam Date end) throws Exception {
        return service.event(siteId, start, end, limit, skip);
    }

    /**
     * 固定时段 event 统计数据获取API
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year] 注：要大于 period
     * @param limit  分页限制
     * @param skip   分页起始
     * @return event统计数据 {status:[true|false],data:[{name,num,rn,user,ru},...]}
     */
    @RequestMapping("event/site/{siteId}/{offset:-?\\d+}/{span:day|week|month|year}/{limit:\\d+}/{skip:\\d+}")
    public Object event(@PathVariable String siteId, @PathVariable int offset, @PathVariable Period span, @PathVariable int limit, @PathVariable int skip) throws Exception {
        Date end = timeEnd(span, offset);
        Date start = timeStart(span, offset);
        return service.event(siteId, start, end, limit, skip);
    }

    /**
     * 自定义时段 Visit|PV|UV|IP统计数据获取API
     *
     * @param siteId 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param norm   统计指标 [Visit|PV|UV|IP]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return event统计数据
     */
    @RequestMapping("visit/site/{siteId}/{period:hour|day|week|month}/{norm:visit|pv|uv|ip}")
    public Object norm(@PathVariable String siteId, @PathVariable Period period, @PathVariable Norm norm, @RequestParam Date start, @RequestParam Date end) throws Exception {
        List<PeriodValue> list = service.norm(siteId, period, norm, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end);
        return list;
    }

    /**
     * 固定时段 Visit|PV|UV|IP统计数据获取API
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year] 注：要大于 period
     * @param period 时段周期 [hour|day|week|month]=[时|日|周|月]
     * @param norm   统计指标 [Visit|PV|UV|IP]
     * @return PV统计数据 {status:[true|false],data:[{time,date,val},...]}
     */
    @RequestMapping("visit/site/{siteId}/{offset:-?\\d+}/{span:day|week|month|year}/{period:hour|day|week|month}/{norm:visit|pv|uv|ip}")
    public Object norm(@PathVariable String siteId, @PathVariable int offset, @PathVariable Period span, @PathVariable Period period, @PathVariable Norm norm) throws Exception {
        Date end = timeEnd(span, offset);
        Date start = timeStart(span, offset);
        List<PeriodValue> list = service.norm(siteId, period, norm, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end);
        return list;
    }

    /**
     * 根据周期和便宜计算开始时间
     *
     * @param span   时间跨度
     * @param offset 偏移
     * @return 开始时间
     */
    private Date timeStart(Period span, int offset) throws ParseException {
        int field = span.getField();
        DateFormat format = span.getFormat();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(format.format(calendar.getTime())));
        calendar.add(field, offset);
        return calendar.getTime();
    }

    /**
     * 根据周期和便宜计算结束时间
     *
     * @param span   时间跨度
     * @param offset 偏移
     * @return 结束时间
     */
    private Date timeEnd(Period span, int offset) throws ParseException {
        int field = span.getField();
        DateFormat format = span.getFormat();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(format.format(calendar.getTime())));
        calendar.add(field, offset + 1);
        return calendar.getTime();
    }

    /**
     * 填充数据
     *
     * @param list 数据库有效数据列表
     * @return 填充的数据
     */
    private List<PeriodValue> fulldata(List<PeriodValue> list, DateFormat format, int field, Date start, Date end) {
        Map<String, PeriodValue> map = tomap(list);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        List<PeriodValue> nlist = new ArrayList<>();
        while (calendar.getTime().before(end)) {
            String keytime = format.format(calendar.getTime());
            PeriodValue value = map.get(keytime);
            if (value == null) {
                value = new PeriodValue();
                value.setVal(0l);
                value.setDate(keytime);
                value.setTime(calendar.getTime());
                nlist.add(value);
            } else {
                nlist.add(value);
                map.remove(keytime);
            }
            calendar.add(field, 1);
        }
        for (Map.Entry<String, PeriodValue> entry : map.entrySet()) {
            nlist.add(entry.getValue());
        }
        return nlist;
    }

    /**
     * 把list转为map 方便查找
     *
     * @param list 数据库有效数据列表
     * @return map
     */
    private Map<String, PeriodValue> tomap(List<PeriodValue> list) {
        Map<String, PeriodValue> map = new LinkedHashMap<>();
        for (PeriodValue value : list) {
            map.put(value.getDate(), value);
        }
        return map;
    }

}
