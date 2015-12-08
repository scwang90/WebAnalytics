package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.model.entity.JsEvent;
import com.simpletech.webanalytics.model.entity.JsUser;
import com.simpletech.webanalytics.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * 探针数据接收 API
 * Created by 树朾 on 2015/9/21.
 */
@Controller
@RequestMapping("tracker")
public class TrackerController {

    @Autowired
    TrackerService service;

    /**
     * piwik监听测试
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception
     */
    @RequestMapping("tracker")
    public void tracker(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, Object> map = request.getParameterMap();
        for (Map.Entry<String, Object> param : map.entrySet()) {
            if ("action_name".equals(param.getKey())){
                String parameter = request.getParameter(param.getKey());
                if(!parameter.matches("[\\u4E00-\\u9FA5]+")){
                    parameter = new String(parameter.getBytes("ISO-8859-1"),"UTF-8");
                }
                System.out.println("paramr:" + param.getKey() + "=>" + parameter);
            } else {
                System.out.println("paramr:" + param.getKey() + "=>" + request.getParameter(param.getKey()));
            }
        }
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.println("header:" + name + "=>" + request.getHeader(name));
        }
        System.out.println("query:"+request.getQueryString());
        request.getRequestDispatcher("/images/pixel.jpg").forward(request, response);
    }


    /**
     * JS探针页面统计
     *
     * @param idsite 网站ID
     * @param detect 页面参数接收对象
     * @throws Exception
     */
    @RequestMapping("1.0/tpv")
    public void ptv(HttpServletRequest request, HttpServletResponse response, int idsite, JsDetect detect) throws Exception{
        detect.check();
        detect.bind(request, response);
        service.trackPageView(idsite, detect);
        request.getRequestDispatcher("/images/pixel.jpg").forward(request, response);
    }

    /**
     * JS探针事件统计
     *
     * @param idsite 网站ID
     * @param event  事件接收对象
     * @throws Exception
     */
    @RequestMapping("1.0/ten")
    public void ten(HttpServletRequest request, HttpServletResponse response, int idsite, JsEvent event) throws Exception{
        event.check();
        event.bind(request, response);
        service.trackEvent(idsite, event);
        request.getRequestDispatcher("/images/pixel.jpg").forward(request, response);
    }

    /**
     * JS探针用户信息
     *
     * @param idsite 网站ID
     * @param user  事件接收对象
     * @throws Exception
     */
    @RequestMapping("1.0/tur")
    public void tur(HttpServletRequest request, HttpServletResponse response, int idsite, JsUser user) throws Exception{
        user.check();
        user.bind(request, response);
        service.trackerUser(idsite, user);
        request.getRequestDispatcher("/images/pixel.jpg").forward(request, response);
    }
}
