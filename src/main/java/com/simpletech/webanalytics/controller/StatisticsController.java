package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.model.constant.EnterClose;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.constant.Ranking;
import com.simpletech.webanalytics.model.constant.RankingType;
import com.simpletech.webanalytics.model.entity.PeriodValue;
import com.simpletech.webanalytics.model.entity.VisitValue;
import com.simpletech.webanalytics.model.entity.VisitorValue;
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
@RequestMapping("api/statistics")
public class StatisticsController {

    @Autowired
    StatisticsService service;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyyMMddHHmmss"), true));
    }


    /**
     * [入口|出口]页面
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return  [入口|出口]页面
     */
    @RequestMapping("enterclose/site/{siteId:\\d+}/{type:entry|exit}")
    public Object enterclose(@PathVariable int siteId, EnterClose type, String subsite, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.enterclose(idsite, type, start, end);
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
    @RequestMapping("shareranking/site/{siteId:\\d+}")
    public Object shareRanking(@PathVariable int siteId, String subsite, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.shareRanking(idsite, start, end);
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
    @RequestMapping("sharemap/site/{siteId:\\d+}/page/{urlId}")
    public Object sharemap(@PathVariable int siteId, @PathVariable String urlId, String subsite, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.sharemap(idsite, urlId, start, end);
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
    @RequestMapping("pageranking/site/{siteId:\\d+}/page/{urlId}/{ranking:brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city|ip}/{ranktype:vt|uv|ip|pv}/{limit:\\d+}/{skip:\\d+}")
    public Object pageRanking(@PathVariable int siteId, @PathVariable String urlId, @PathVariable Ranking ranking, @PathVariable RankingType ranktype, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.pageRanking(idsite, urlId, ranking, ranktype, start, end, limit, skip);
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
    @RequestMapping("ranking/site/{siteId:\\d+}/{ranking:brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city|ip}/{ranktype:vt|uv|ip|pv}/{limit:\\d+}/{skip:\\d+}")
    public Object ranking(@PathVariable int siteId, @PathVariable Ranking ranking, @PathVariable RankingType ranktype, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.ranking(idsite, ranking, ranktype, start, end, limit, skip);
    }

    /**
     * 新老用户
     *
     * @param siteId 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 新老用户
     */
    @RequestMapping("visitor/site/{siteId:\\d+}/{period:hour|day|week|month}")
    public Object visitor(@PathVariable int siteId, @PathVariable Period period, String subsite, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        this.doCheckPeriod(period, start, end);
        List<VisitorValue> list = service.visitor(idsite, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, VisitorValue.class);
        return list;
    }

    /**
     * 页面标题排行
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
    @RequestMapping("pagetitle/site/{siteId:\\d+}/{ranktype:vt|uv|pv}/{limit:\\d+}/{skip:\\d+}")
    public Object pagetitle(@PathVariable int siteId, @PathVariable RankingType ranktype, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.pagetitle(idsite, ranktype, start, end, limit, skip);
    }


    /**
     * 页面链接排行
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 链接排行
     */
    @RequestMapping("pageurl/site/{siteId:\\d+}/{ranktype:vt|uv|pv}/{limit:\\d+}/{skip:\\d+}")
    public Object pageurl(@PathVariable int siteId, @PathVariable RankingType ranktype, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.pageurl(idsite, ranktype, start, end, limit, skip);
    }

    /**
     * event 统计数据获取API
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
     * @return PV统计数据 {status:[true|false],data:[{time,date,num,rn,user,ru},...]}
     */
    @RequestMapping("event/site/{siteId:\\d+}/{name}/{period:hour|day|week|month}/{limit:\\d+}/{skip:\\d+}")
    public Object event(@PathVariable int siteId, @PathVariable String name, @PathVariable Period period, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        this.doCheckPeriod(period, start, end);
        return service.event(idsite, name, period, start, end, limit, skip);
    }


    /**
     * event 统计数据获取API
     *
     * @param siteId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @param limit  分页限制
     * @param skip   分页起始
     * @return PV统计数据 {status:[true|false],data:[{name,num,rn,user,ru},...]}
     */
    @RequestMapping("event/site/{siteId:\\d+}/{limit:\\d+}/{skip:\\d+}")
    public Object event(@PathVariable int siteId, @PathVariable int limit, @PathVariable int skip, String subsite, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        String idsite = getIdSite(siteId, subsite);
        return service.event(idsite, start, end, limit, skip);
    }

    /**
     * 自定义时段 Visit|PV|UV|IP统计数据获取API
     *
     * @param siteId 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return event统计数据
     */
    @RequestMapping("visit/site/{siteId:\\d+}/{period:hour|day|week|month}")
    public Object visit(@PathVariable int siteId, @PathVariable Period period, String subsite, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        this.doCheckPeriod(period, start, end);
        String idsite = getIdSite(siteId, subsite);
        List<VisitValue> list = service.visit(idsite, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, VisitValue.class);
        return list;
    }

    /**
     * 检测时间分段合理性
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     */
    private void doCheckPeriod(Period period, Date start, Date end) throws ServiceException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int count = 0;
        while (calendar.getTime().before(end)) {
            count++;
        }
        if (count > 200) {
            throw new ServiceException("数据量偏大，请调整时间跨度再试！");
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
    private Date timeStart(Date start, Period span, Integer offset) throws ParseException {
        if (span != null && offset != null /*&& !Period.hour.equals(span)*/) {
            DateFormat format = span.getFormat();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(format.format(calendar.getTime())));
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
    private Date timeEnd(Date end, Period span, Integer offset) throws Exception {
        if (span != null && offset != null/* && !Period.hour.equals(span)*/) {
            DateFormat format = span.getFormat();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(format.format(calendar.getTime())));
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
