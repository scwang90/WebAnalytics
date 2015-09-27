package com.simpletech.webanalytics.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.newsint.cip.utilities.ua.CompositeUserAgentParser;
import uk.co.newsint.cip.utilities.ua.UserAgent;
import uk.co.newsint.cip.utilities.ua.UserAgentParser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 代理分析
 * Created by SCWANG on 2015-09-27.
 */
@RestController
public class UserAgentController {
    @RequestMapping("agent")
    public void agent(HttpServletRequest request,PrintWriter out) throws IOException {
        String agent = request.getHeader("User-Agent");
        UserAgentParser userAgentParser = new CompositeUserAgentParser();
        UserAgent userAgent = userAgentParser.parse(agent);
        out.println("<br>Application : " + userAgent.getApplication());
        out.println("<br>AppVersion : " + userAgent.getApplicationVersion());
        out.println("<br>DeviceType : " + userAgent.getDeviceType());
        out.println("<br>DeviceMaker : " + userAgent.getDeviceMaker());
        out.println("<br>DeviceModel : " + userAgent.getDeviceModel());
        out.println("<br>DeviceModelVersion : " + userAgent.getDeviceModelVersion());
        out.println("<br>OS : " + userAgent.getOS());
        out.println("<br>OSMaker : " + userAgent.getOSMaker());
        out.println("<br>OSVersion : " + userAgent.getOSVersion());
        out.println("<br>Browser : " + userAgent.getBrowser());
        out.println("<br>BrowserVersion : " + userAgent.getBrowserVersion());
        out.println("<br>agent : " + agent);

    }

}
