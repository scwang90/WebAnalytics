package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.model.constant.Ranking;
import com.simpletech.webanalytics.model.entity.TrendValue;
import com.simpletech.webanalytics.service.ErrorService;
import com.simpletech.webanalytics.util.AfReflecter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.*;

/**
 * 获取错误数据接口
 * Created by ChenQi on 2015/10/16 11:52.
 */
@RestController
@RequestMapping("api/error")
public class ErrorController {
    @Autowired
    ErrorService service;

    /**
     *  获取失败数据API http://[ip|域名][:端口]/api/error/[ranktype]/[limit]/[skip]
     * @param ranking  排行类型 brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city
     * @param limit 分页限制
     * @param skip  分页起始
     * @return
     * @throws Exception
     */
    @RequestMapping("{ranking:brand|model|nettype|browser|system|appname|resolution|depth|lang|country|province|city}/{limit:\\d+}/{skip:\\d+}")
    public Object unknownError( @PathVariable Ranking ranking, @PathVariable int limit, @PathVariable int skip) {
        return service.unknownError(ranking,limit, skip);
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

}
