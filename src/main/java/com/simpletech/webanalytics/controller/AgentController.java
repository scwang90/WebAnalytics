package com.simpletech.webanalytics.controller;

import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 * 代理测试
 * Created by SCWANG on 2015-09-27.
 */
@RestController
public class AgentController {

    @RequestMapping("user")
    public void user(HttpServletRequest request,PrintWriter out){
        UserAgent agent = new UserAgent(request.getHeader("User-Agent"));
        out.println("<br>Browseer : "+agent.getBrowser());
        out.println("<br>BrowserVersion : " + agent.getBrowserVersion());
        out.println("<br>OsName : " + agent.getOperatingSystem().getName());
        out.println("<br>DeviceType : " + agent.getOperatingSystem().getDeviceType());
        out.println("<br>Manufacturer : " + agent.getOperatingSystem().getManufacturer());
        out.println("<br>OperatingSystem : " + agent.getOperatingSystem());
        out.println("<br>UserAgent : " + request.getHeader("User-Agent"));
    }
}
