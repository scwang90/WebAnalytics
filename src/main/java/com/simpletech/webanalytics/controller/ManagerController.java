package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.annotations.Intent;
import com.simpletech.webanalytics.controller.base.BaseController;
import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.model.Subsite;
import com.simpletech.webanalytics.service.ManagerService;
import com.simpletech.webanalytics.service.SiteService;
import com.simpletech.webanalytics.service.SubsiteService;
import com.simpletech.webanalytics.util.AfReflecter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 管理API
 * Created by 树朾 on 2015/10/9.
 */
@RestController
@RequestMapping("v1/manage/user/{userId}")
public class ManagerController extends BaseController {

    @Autowired
    ManagerService service;

    @Intent("获取网站列表")
    @RequestMapping("site/list/{limit:\\d+}/{start:\\d+}")
    public Object siteList(@PathVariable String userId,@PathVariable int limit, @PathVariable int start) {
        return mapExclude(service.findList(userId, limit, start), new String[]{"updateTime", "createTime"});
    }

    @Intent("获取子站列表")
    @RequestMapping("site/list/sub/{siteId:\\d+}/{limit:\\d+}/{start:\\d+}")
    public Object siteSublist(@PathVariable String userId,@PathVariable int siteId, @PathVariable int limit, @PathVariable int start) {
        return mapInclude(service.findSubList(userId, siteId, limit, start), new String[]{"name", "remark"});
    }

    @Intent("添加网站")
    @RequestMapping("site/add")
    public Object siteAdd(@PathVariable String userId,@RequestBody Site site) {
        return service.insertSite(userId, site);
    }

    @Intent("更新网站")
    @RequestMapping("site/update")
    public Object siteUpdate(@PathVariable String userId,@RequestBody Site site) {
        return service.updateSite(userId, site);
    }

    @Intent("获取探针代码")
    @RequestMapping("site/tracker/{siteId:\\d+}")
    public Object siteTracker(@PathVariable String userId,HttpServletRequest request, @PathVariable int siteId) {
        Matcher matcher = Pattern.compile("//(.+/)v1/manage", Pattern.CASE_INSENSITIVE).matcher(request.getRequestURL());
        matcher.find();
        String temp = "<!-- WebAnalytics -->\n" +
                "<script type=\"text/javascript\">\n" +
                "    var _wapaq = _wapaq || [];\n" +
                "    _wapaq.push(['trackPageView']);\n" +
                "    (function() {\n" +
                "        var u=\"//${domain}\";\n" +
                "        _wapaq.push(['setTrackerUrl', u+'tracker']);\n" +
                "        _wapaq.push(['setSiteId', ${siteId}]);\n" +
                "        _wapaq.push(['setDiscardUrlParams', true]);\n" +
                "        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];\n" +
                "        g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'js/ds.js'; s.parentNode.insertBefore(g,s);\n" +
                "    })();\n" +
                "</script>\n" +
                "<!-- End WebAnalytics Code -->";
        return temp.replace("${siteId}", "" + siteId).replace("${domain}", matcher.group(1));
    }

    @Intent("获取探针事件代码")
    @RequestMapping("site/tracker/event/{name}")
    public Object siteTrackerEvent(@PathVariable String userId,HttpServletRequest request, @PathVariable String name) {
        String temp = "_wapaq.push(['trackEvent','&{name}','${value}']);";
        return temp.replace("${name}", name).replace("${value}", "");
    }

    @Intent("获取探针事件代码")
    @RequestMapping("site/tracker/event/{name}/{value}")
    public Object siteTrackerEventValue(@PathVariable String userId,HttpServletRequest request, @PathVariable String name, @PathVariable String value) {
        String temp = "_wapaq.push(['trackEvent','&{name}','${value}']);";
        return temp.replace("${name}", name).replace("${value}", value);
    }
}
