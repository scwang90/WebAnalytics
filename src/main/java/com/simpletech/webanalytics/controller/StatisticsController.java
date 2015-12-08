package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.model.constant.*;
import com.simpletech.webanalytics.model.entity.*;
import com.simpletech.webanalytics.service.StatisticsService;
import com.simpletech.webanalytics.util.AfReflecter;
import com.simpletech.webanalytics.util.AfStringUtil;
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
 * Created by 树朾 on 2015/9/25.
 */
@RestController
@RequestMapping("v1/statistics/site/{siteId:\\d+}")
public class StatisticsController {

    @Autowired
    StatisticsService service;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyyMMddHHmmss"), true));
    }


    /**
     * 新老用户-趋势
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 新老用户
     */
    @RequestMapping("visitor/span")
    public Object visitorSpan(@PathVariable int siteId, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.visitorSpan(idsite, start, end);
    }

    /**
     * 新老用户-趋势
     *
     * @param siteId 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 新老用户
     */
    @RequestMapping("visitor/trend/{period:hour|day|week|month}")
    public Object visitorTrend(@PathVariable int siteId, @PathVariable Period period, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        this.doCheckPeriod(period, start, end);
        List<VisitorTrendValue> list = service.visitorTrend(idsite, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, VisitorTrendValue.class);
        return list;
    }

    /**
     * 进入
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 页面分享排行
     */
    @RequestMapping("share/rank/{limit:\\d+}/{skip:\\d+}")
    public Object shareRank(@PathVariable int siteId, String subsite, @PathVariable int limit, @PathVariable int skip, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.shareRank(idsite, start, end, limit, skip);
    }

    /**
     * 分享传播图点线列表
     *
     * @param siteId 网站ID
     * @param urlId  页面ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 分享图点线列表
     */
    @RequestMapping("share/map/page/{urlId}")
    public Object shareMap(@PathVariable int siteId, @PathVariable String urlId, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.shareMap(idsite, urlId, start, end);
    }

    /**
     * [入口|出口]页面
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return [入口|出口]页面
     */
    @RequestMapping("{type:entry|exit}/rank/{ranktype:pv|uv|vt|ip}/{limit:\\d+}/{skip:\\d+}")
    public Object enterexit(@PathVariable int siteId, @PathVariable EnterExit type, @PathVariable RankingType ranktype, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.enterexit(idsite, type, ranktype, start, end, limit, skip);
    }

    /**
     * 页面排行-标题和链接
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 标题排行
     */
    @RequestMapping("{type:title|url}/rank/{ranktype:vt|uv|pv}/{limit:\\d+}/{skip:\\d+}")
    public Object titleurl(@PathVariable int siteId, @PathVariable PageRank type, @PathVariable RankingType ranktype, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.titleurl(idsite, type, ranktype, start, end, limit, skip);
    }

    /**
     * 页面数据排行
     * 设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市、IP地址
     *
     * @param ranking  排行类型 brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city|ip
     * @param ranktype 排序类型 按 vt|uv|ip|pv
     * @param siteId   网站ID
     * @param urlId    页面ID
     * @param offset   偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span     跨度 [day|week|month|year]
     * @param start    开始时间 ("yyyyMMddHHmmss")
     * @param end      结束时间 ("yyyyMMddHHmmss")
     * @param limit    分页限制
     * @param skip     分页起始
     * @return 排行数据
     */
    @RequestMapping("page/{urlId}/{ranking:brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city|ip}/rank/{ranktype:vt|uv|ip|pv}/{limit:\\d+}/{skip:\\d+}")
    public Object pageRank(@PathVariable int siteId, @PathVariable String urlId, @PathVariable Ranking ranking, @PathVariable RankingType ranktype, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.pageRank(idsite, urlId, ranking, ranktype, start, end, limit, skip);
    }


    /**
     * 站点数据排行
     * 设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市、IP地址
     *
     * @param ranking  排行类型 brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city|ip
     * @param ranktype 排序类型 按 vt|uv|ip|pv
     * @param siteId   网站ID
     * @param offset   偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span     跨度 [day|week|month|year]
     * @param start    开始时间 ("yyyyMMddHHmmss")
     * @param end      结束时间 ("yyyyMMddHHmmss")
     * @param limit    分页限制
     * @param skip     分页起始
     * @return 排行数据
     */
    @RequestMapping("{ranking:brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city|ip|isp}/rank/{ranktype:vt|uv|ip|pv}/{limit:\\d+}/{skip:\\d+}")
    public Object ranking(@PathVariable int siteId, @PathVariable Ranking ranking, @PathVariable RankingType ranktype, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.ranking(idsite, ranking, ranktype, start, end, limit, skip);
    }

    /**
     * 事件详细-趋势
     *
     * @param siteId 网站ID
     * @param name   事件名称
     * @param period 时段周期 [时|日|周|月]
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 事件详细-趋势 {status:[true|false],data:[{time,date,num,rn,user,ru},...]}
     */
    @RequestMapping("event/name/{name}/trend/{period:hour|day|week|month}/{limit:\\d+}/{skip:\\d+}")
    public Object eventName(@PathVariable int siteId, @PathVariable String name, @PathVariable Period period, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.eventNameTrend(idsite, name, period, start, end, limit, skip);
    }

    /**
     * 事件详细-时段
     *
     * @param siteId 网站ID
     * @param name   事件名称
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 事件详细-趋势 {status:[true|false],data:[{time,date,num,rn,user,ru},...]}
     */
    @RequestMapping("event/name/{name}/span")
    public Object eventNameSpan(@PathVariable int siteId, @PathVariable String name, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.eventNameSpan(idsite, name, start, end);
    }

    /**
     * 事件详细-趋势
     *
     * @param siteId 网站ID
     * @param name   事件名称
     * @param period 时段周期 [时|日|周|月]
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 事件详细-趋势 {status:[true|false],data:[{time,date,num,rn,user,ru},...]}
     */
    @RequestMapping("event/name/{name}/trend/{period:hour|day|week|month}")
    public Object eventNameTrend(@PathVariable int siteId, @PathVariable String name, @PathVariable Period period, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        this.doCheckPeriod(period, start, end);
        List<EventNameTrendValue> list = service.eventNameTrend(idsite, name, period, start, end, 200, 0);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, EventNameTrendValue.class);
        return list;
    }

    /**
     * 事件统计-排行
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 事件统计-排行 {status:[true|false],data:[{name,num,rn,user,ru},...]}
     */
    @RequestMapping("event/rank/{limit:\\d+}/{skip:\\d+}")
    public Object eventRank(@PathVariable int siteId, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.eventRank(idsite, start, end, limit, skip);
    }

    /**
     * 事件统计-趋势
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 事件统计-排行 {status:[true|false],data:[{name,num,rn,user,ru},...]}
     */
    @RequestMapping("event/trend/{period:hour|day|week|month}")
    public Object eventTrend(@PathVariable int siteId, @PathVariable Period period, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        List<EventTrendValue> list = service.eventTrend(idsite, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, EventTrendValue.class);
        return list;
    }

    /**
     * 事件统计-时段
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 事件统计-排行 {status:[true|false],data:[{name,num,rn,user,ru},...]}
     */
    @RequestMapping("event/span")
    public Object eventSpan(@PathVariable int siteId, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.eventSpan(idsite, start, end);
    }

    /**
     * Visit|PV|UV|IP 趋势
     *
     * @param siteId 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/trend/{period:hour|day|week|month}")
    public Object visitTrend(@PathVariable int siteId, @PathVariable Period period, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        this.doCheckPeriod(period, start, end);
        String idsite = getIdSite(siteId, subsite);
        List<VisitTrendValue> list = service.visitTrend(idsite, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, VisitTrendValue.class);
        return list;
    }

    /**
     * Visit|PV|UV|IP 时段
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/span")
    public Object visitSpan(@PathVariable int siteId, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.visitSpan(idsite, start, end);
    }


    /**
     * 页面 Visit|PV|UV|IP 趋势
     *
     * @param siteId 网站ID
     * @param urlId  页面ID
     * @param period 时段周期 [时|日|周|月]
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("page/{urlId}/trend/{period:hour|day|week|month}")
    public Object pageTrend(@PathVariable int siteId, @PathVariable String urlId, @PathVariable Period period, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        this.doCheckPeriod(period, start, end);
        String idsite = getIdSite(siteId, subsite);
        List<VisitTrendValue> list = service.pageTrend(idsite, urlId, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, VisitTrendValue.class);
        return list;
    }

    /**
     * 页面 Visit|PV|UV|IP 时段
     *
     * @param siteId 网站ID
     * @param urlId  页面ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("page/{urlId}/span")
    public Object pageSpan(@PathVariable int siteId, @PathVariable String urlId, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.pageSpan(idsite, urlId, start, end);
    }

    /**
     * 访问时间-分布 （服务器时间，浏览器时间）
     *
     * @param siteId 网站ID
     * @param type   时间类型  server-服务器时间|local-浏览器时间
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/{type:server|local}/time/map")
    public Object visitTimeMap(@PathVariable int siteId, @PathVariable TimeType type, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.visitTimeMap(idsite, type, start, end);
    }


    /**
     * 页面时间-分布 （服务器时间，浏览器时间）
     *
     * @param siteId 网站ID
     * @param type   时间类型  server-服务器时间|local-浏览器时间
     * @param urlId  页面ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("page/{urlId}/{type:server|local}/time/map")
    public Object pageTimeMap(@PathVariable int siteId, @PathVariable String urlId, @PathVariable TimeType type, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.pageTimeMap(idsite, urlId, type, start, end);
    }

    /**
     * 访问页数-排行
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param type   时间类型  view-访问页数|unique-不同页数
     * @param map    分布格式 如格式 1,2,6,9 表示分布结果 1,2,3-6,7-9,大于9
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/page/{type:view:unique}/map")
    public Object visitPageMap(@PathVariable int siteId, @PathVariable VisitPageType type, @RequestParam String map, String subsite, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.visitPageMap(idsite, type, map, start, end);
    }

    /**
     * 检测时间分段合理性
     *
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     */
    private void doCheckPeriod(Period period, Date start, Date end) throws ServiceException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int count = 0, max = 200;
        while (calendar.getTime().before(end)) {
            if (count++ > max) {
                throw new ServiceException("数据量偏大，请调整时间跨度再试！");
            }
            calendar.add(period.getField(), 1);
        }
    }

    /**
     * 根据周期和便宜计算开始时间
     *
     * @param start  开始时间
     * @param span   时间跨度
     * @param offset 偏移
     * @return 开始时间
     */
    private Date timeStart(Date start, Period span, Integer offset) {
        if (span != null && offset != null /*&& !Period.hour.equals(span)*/) {
            DateFormat format = span.getFormat();
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(format.parse(format.format(calendar.getTime())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(span.getField(), offset);
            return calendar.getTime();
        }
        if (start == null) {
            return timeStart(new Date(), Period.year, -1000);
        }
        return start;
    }

    /**
     * 根据周期和便宜计算结束时间
     *
     * @param end    结束时间
     * @param span   时间跨度
     * @param offset 偏移
     * @return 结束时间
     */
    private Date timeEnd(Date end, Period span, Integer offset) {
        if (span != null && offset != null/* && !Period.hour.equals(span)*/) {
            DateFormat format = span.getFormat();
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(format.parse(format.format(calendar.getTime())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(span.getField(), offset + 1);
            return calendar.getTime();
        }
        if (end == null) {
            return timeEnd(new Date(), Period.year, 1000);
        }
        return end;
    }

    /**
     * 填充数据
     *
     * @param list 数据库有效数据列表
     * @return 填充的数据
     */
    private <T extends TrendValue> List<T> fulldata(List<T> list, DateFormat format, int field, Date start, Date end, Class<T> clazz) {
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
    private <T extends TrendValue> Map<String, T> tomap(List<T> list) {
        Map<String, T> map = new LinkedHashMap<>();
        for (T value : list) {
            map.put(value.getDate(), value);
        }
        return map;
    }

    /**
     * 把 int siteId 转成 string idsite
     *
     * @param siteId  网站ID
     * @param subsite 子项目
     * @return idsite
     */
    private String getIdSite(int siteId, String subsite) {
        if (AfStringUtil.isNotEmpty(subsite)) {
            String format = "%d AND idsubsite='%s'";
            return String.format(format, siteId, subsite);
        }
        return String.valueOf(siteId);
    }
}
