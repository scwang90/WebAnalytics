package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.annotations.Intent;
import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理API
 * Created by 树朾 on 2015/10/9.
 */
@RestController
@RequestMapping("api/manage")
public class ManagerController {

    @Autowired
    SiteService siteService;

    @Intent("获取网站列表")
    @RequestMapping("site/list/{limit:\\d+}/{start:\\d+}")
    public Object sitelist(@PathVariable int limit,@PathVariable int start) throws Exception {
        return siteService.findByPage(limit, start);
    }

    @Intent("添加网站")
    @RequestMapping("site/add")
    public Object siteadd(@RequestBody Site site) throws Exception {
        return siteService.insert(site);
    }

}
