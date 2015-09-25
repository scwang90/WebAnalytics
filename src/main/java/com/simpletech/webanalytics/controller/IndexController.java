package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.util.AfReflecter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Enumeration;

/**
 * Created by Administrator on 2015/9/9.
 */
@Controller
public class IndexController {

    @RequestMapping("2.0/ds.png")
    public void ds2_0(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    }
//    @ResponseBody
    @RequestMapping("1.0/ds.png")
    public void ds1_0(HttpServletRequest request,HttpServletResponse response,JsDetect detect) throws ServletException, IOException {
        detect.bind(request,response);
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            System.out.println(name+"->"+request.getParameter(name));
        }
        System.out.println("<%-------------------------------------%>");
        Field[] fields = AfReflecter.getField(JsDetect.class);
        for (Field field : fields){
            String name = field.getName();
            System.out.println(name+":>"+AfReflecter.getMemberNoException(detect,name));
        }

        String referer = request.getHeader("referer");
        System.out.println("refer is" + referer);
        if (referer == null || !referer.contains(request.getServerName())) {
            // 如果链接地址来自其他网站，则返回错误图片
            request.getRequestDispatcher("/images/reffer.jpg").forward(request, response);
        } else {
            request.getRequestDispatcher("/images/11567071.jpg").forward(request,response);
        }
    }

    @RequestMapping("{index}")
    public String index(@PathVariable String index){
        return index;
    }

}
