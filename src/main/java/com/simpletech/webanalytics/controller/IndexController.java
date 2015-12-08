package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.model.entity.Spant;
import com.simpletech.webanalytics.service.StatisticsService;
import com.simpletech.webanalytics.util.AfReflecter;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

/**
 * 链接引导
 * Created by 树朾 on 2015/9/9.
 */
@Controller
public class IndexController {

    @XmlRootElement
    public static class SpantEx extends Spant {
        public String name = "Spant";
        private Object value = "132";
        public Object getValue() {
            return value;
        }
        public void setValue(Object value) {
            this.value = value;
        }
    }

    @ResponseBody
    @RequestMapping("xml/{skip:\\d*}")
    public Spant xml(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        SpantEx spant = new SpantEx();
        spant.setStart(new Date());
        spant.setEnd(new Date());
        model.addAttribute("spant", spant);
        return spant;
    }

    @RequestMapping("1.0/ds.png")
    public void ds1_0(HttpServletRequest request, HttpServletResponse response, JsDetect detect) throws ServletException, IOException {
        detect.bind(request, response);
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            System.out.println(name + "->" + request.getParameter(name));
        }
        System.out.println("<%-------------------------------------%>");
        Field[] fields = AfReflecter.getField(JsDetect.class);
        for (Field field : fields) {
            String name = field.getName();
            System.out.println(name + ":>" + AfReflecter.getMemberNoException(detect, name));
        }

        String referer = request.getHeader("referer");
        System.out.println("refer is" + referer);
        if (referer == null || !referer.contains(request.getServerName())) {
            // 如果链接地址来自其他网站，则返回错误图片
            request.getRequestDispatcher("/images/reffer.jpg").forward(request, response);
        } else {
            request.getRequestDispatcher("/images/11567071.jpg").forward(request, response);
        }
    }

    @RequestMapping("skip/{skip:\\d*}")
    public String skip(@PathVariable String skip) {
        return "index";
    }

    @Autowired
    StatisticsService service;

    @RequestMapping("sharemap/site/{siteId:\\d+}/{urlId}")
    public String sharemap(@PathVariable int siteId, @PathVariable String urlId, Model model) {
        Map<String, Object> root = (Map<String, Object>) service.shareMap(String.valueOf(siteId), urlId, new Date(-30136435200000l), new Date(33008486400000l));
        model.addAttribute("lines", JacksonUtil.toJson(root.get("lines")).replace("sts", "weight").replace("sId", "source").replace("eId", "target"));
        model.addAttribute("points", JacksonUtil.toJson(root.get("points")).replace("id", "name").replace("cl", "category").replace("pv", "value").replace("mk", "label"));
        return "sharemap";
    }

    @RequestMapping("{index}")
    public String index(@PathVariable String index) {
        return index;
    }

}
