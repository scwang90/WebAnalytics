package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.model.constant.Norm;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.constant.Ranking;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.PeriodValue;
import com.simpletech.webanalytics.model.entity.VisitValue;
import com.simpletech.webanalytics.model.entity.VisitorValue;
import com.simpletech.webanalytics.service.StatisticsService;
import com.simpletech.webanalytics.util.AfReflecter;
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
     * 数据排行 - 自定义时段
     * 设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市
     *
     * @param ranking  排行类型 brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city
     * @param ranktype 排序类型 按 visit|uv|ip|pv
     * @param siteId   网站ID
     * @param start    开始时间 ("yyyyMMddHHmmss")
     * @param end      结束时间 ("yyyyMMddHHmmss")
     * @param limit    分页限制
     * @param skip     分页起始
     * @return 排行数据
     */
    @RequestMapping("ranking/site/{siteId:\\d+}/{ranking:brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city}/ranktype/{ranktype:visit|uv|ip|pv}/{limit:\\d+}/{skip:\\d+}")
    public Object ranking(@PathVariable int siteId, @PathVariable Ranking ranking, @PathVariable RankingType ranktype, @PathVariable int limit, @PathVariable int skip, @RequestParam Date start, @RequestParam Date end) throws Exception {
        return service.ranking(siteId, ranking, ranktype, start, end, limit, skip);
    }

    /**
     * 数据排行 - 固定时段
     * 设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市
     *
     * @param ranking  排行类型 brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city
     * @param ranktype 排序类型 按 visit|uv|ip|pv
     * @param siteId   网站ID
     * @param offset   偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span     跨度 [day|week|month|year]
     * @param limit    分页限制
     * @param skip     分页起始
     * @return 排行数据
     */
    @RequestMapping("ranking/site/{siteId:\\d+}/{ranking:brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city}/ranktype/{ranktype:visit|uv|ip|pv}/{offset:-?\\d+}/{span:day|week|month|year}/{limit:\\d+}/{skip:\\d+}")
    public Object ranking(@PathVariable int siteId, @PathVariable Ranking ranking, @PathVariable RankingType ranktype, @PathVariable int offset, @PathVariable Period span, @PathVariable int limit, @PathVariable int skip) throws Exception {
        Date end = timeEnd(span, offset);
        Date start = timeStart(span, offset);
        return service.ranking(siteId, ranking, ranktype, start, end, limit, skip);
    }

    /**
     * 新老用户 - 自定义时段
     *
     * @param siteId 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 新老用户
     */
    @RequestMapping("visitor/site/{siteId:\\d+}/{period:hour|day|week|month}")
    public Object visitor(@PathVariable int siteId, @PathVariable Period period, @RequestParam Date start, @RequestParam Date end) throws Exception {
        List<VisitorValue> list = service.visitor(siteId, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, VisitorValue.class);
        return list;
    }

    /**
     * 新老用户 - 固定时段
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year] 注：要大于 period
     * @param period 时段周期 [时|日|周|月]
     * @return 新老用户
     */
    @RequestMapping("visitor/site/{siteId:\\d+}/{offset:-?\\d+}/{span:day|week|month|year}/{period:hour|day|week|month}")
    public Object visitor(@PathVariable int siteId, @PathVariable int offset, @PathVariable Period span, @PathVariable Period period) throws Exception {
        Date end = timeEnd(span, offset);
        Date start = timeStart(span, offset);
        List<VisitorValue> list = service.visitor(siteId, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, VisitorValue.class);
        return list;
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
    @RequestMapping("pagetitle/site/{siteId:\\d+}/{limit:\\d+}/{skip:\\d+}")
    public Object pagetitle(@PathVariable int siteId, @PathVariable int limit, @PathVariable int skip, @RequestParam Date start, @RequestParam Date end) throws Exception {
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
    @RequestMapping("pagetitle/site/{siteId:\\d+}/{offset:-?\\d+}/{span:day|week|month|year}/{limit:\\d+}/{skip:\\d+}")
    public Object pagetitle(@PathVariable int siteId, @PathVariable int offset, @PathVariable Period span, @PathVariable int limit, @PathVariable int skip) throws Exception {
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
    @RequestMapping("pageurl/site/{siteId:\\d+}/{limit:\\d+}/{skip:\\d+}")
    public Object pageurl(@PathVariable int siteId, @PathVariable int limit, @PathVariable int skip, @RequestParam Date start, @RequestParam Date end) throws Exception {
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
    @RequestMapping("pageurl/site/{siteId:\\d+}/{offset:-?\\d+}/{span:day|week|month|year}/{limit:\\d+}/{skip:\\d+}")
    public Object pageurl(@PathVariable int siteId, @PathVariable int offset, @PathVariable Period span, @PathVariable int limit, @PathVariable int skip) throws Exception {
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
    @RequestMapping("event/site/{siteId:\\d+}/name/{name}/{period:hour|day|week|month}/{limit:\\d+}/{skip:\\d+}")
    public Object event(@PathVariable int siteId, @PathVariable String name, @PathVariable Period period, @PathVariable int limit, @PathVariable int skip, @RequestParam Date start, @RequestParam Date end) throws Exception {
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
    @RequestMapping("event/site/{siteId:\\d+}/name/{name}/{offset:-?\\d+}/{span:day|week|month|year}/{period:hour|day|week|month}/{limit:\\d+}/{skip:\\d+}")
    public Object event(@PathVariable int siteId, @PathVariable String name, @PathVariable int offset, @PathVariable Period span, @PathVariable Period period, @PathVariable int limit, @PathVariable int skip) throws Exception {
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
    @RequestMapping("event/site/{siteId:\\d+}/{limit:\\d+}/{skip:\\d+}")
    public Object event(@PathVariable int siteId, @PathVariable int limit, @PathVariable int skip, @RequestParam Date start, @RequestParam Date end) throws Exception {
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
    @RequestMapping("event/site/{siteId:\\d+}/{offset:-?\\d+}/{span:day|week|month|year}/{limit:\\d+}/{skip:\\d+}")
    public Object event(@PathVariable int siteId, @PathVariable int offset, @PathVariable Period span, @PathVariable int limit, @PathVariable int skip) throws Exception {
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
    @RequestMapping("visit/site/{siteId:\\d+}/{period:hour|day|week|month}/{norm:visit|pv|uv|ip}")
    public Object norm(@PathVariable int siteId, @PathVariable Period period, @PathVariable Norm norm, @RequestParam Date start, @RequestParam Date end) throws Exception {
        List<VisitValue> list = service.norm(siteId, period, norm, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, VisitValue.class);
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
    @RequestMapping("visit/site/{siteId:\\d+}/{offset:-?\\d+}/{span:day|week|month|year}/{period:hour|day|week|month}/{norm:visit|pv|uv|ip}")
    public Object norm(@PathVariable int siteId, @PathVariable int offset, @PathVariable Period span, @PathVariable Period period, @PathVariable Norm norm) throws Exception {
        Date end = timeEnd(span, offset);
        Date start = timeStart(span, offset);
        List<VisitValue> list = service.norm(siteId, period, norm, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, VisitValue.class);
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
        DateFormat format = span.getFormat();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(format.format(calendar.getTime())));
        calendar.add(span.getField(), offset);
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
        DateFormat format = span.getFormat();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(format.format(calendar.getTime())));
        calendar.add(span.getField(), offset + 1);
        return calendar.getTime();
    }

    /**
     * 填充数据
     *
     * @param list 数据库有效数据列表
     * @return 填充的数据
     */
    private <T extends PeriodValue> List<T> fulldata(List<T> list, DateFormat format, int field, Date start, Date end, Class<T> clazz) {
        Map<String, T> map = tomap(list);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        List<T> nlist = new ArrayList<>();
        while (calendar.getTime().before(end)) {
            String keytime = format.format(calendar.getTime());
            T value = map.get(keytime);
            if (value == null) {
                value = AfReflecter.newInstance(clazz);
                value.setEmpty();
                value.setDate(keytime);
                value.setTime(calendar.getTime());
                nlist.add(value);
            } else {
                nlist.add(value);
                map.remove(keytime);
            }
            calendar.add(field, 1);
        }
        for (Map.Entry<String, T> entry : map.entrySet()) {
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
    private <T extends PeriodValue> Map<String, T> tomap(List<T> list) {
        Map<String, T> map = new LinkedHashMap<>();
        for (T value : list) {
            map.put(value.getDate(), value);
        }
        return map;
    }

}
