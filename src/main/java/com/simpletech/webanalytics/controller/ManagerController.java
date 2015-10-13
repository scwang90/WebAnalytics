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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理API
 * Created by 树朾 on 2015/10/9.
 */
@RestController
@RequestMapping("api/manage")
public class ManagerController extends BaseController {

    @Autowired
    ManagerService service;

    @Intent("获取网站列表")
    @RequestMapping("site/list/{limit:\\d+}/{start:\\d+}")
    public Object siteList(@PathVariable int limit, @PathVariable int start) throws Exception {
        return mapExclude(service.findList(limit, start), new String[]{"updateTime", "createTime"});
    }

    @Intent("获取子站列表")
    @RequestMapping("site/list/sub/{siteId:\\d+}/{limit:\\d+}/{start:\\d+}")
    public Object siteSublist(@PathVariable int siteId, @PathVariable int limit, @PathVariable int start) throws Exception {
        return mapInclude(service.findSubList(siteId, limit, start), new String[]{"name", "remark"});
    }

    @Intent("添加网站")
    @RequestMapping("site/add")
    public Object siteAdd(@RequestBody Site site) throws Exception {
        return service.insertSite(site);
    }

    @Intent("更新网站")
    @RequestMapping("site/update")
    public Object siteUpdate(@RequestBody Site site) throws Exception {
        return service.updateSite(site);
    }

}
