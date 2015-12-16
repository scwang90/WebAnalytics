package com.simpletech.webanalytics.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ipmapping.BDIP;
import com.ipmapping.IP;
import com.ipmapping.IPCatcherUtil;
import com.ipmapping.txIP.IPTest;
import com.simpletech.webanalytics.annotations.Must;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.util.AfReflecter;
import com.simpletech.webanalytics.util.AfStringUtil;
import com.simpletech.webanalytics.util.ServiceException;
import com.webanalytics.useragent.UserAgent;
import com.webanalytics.useragent.UserAgentParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JS探针发送的数据格式+服务器获取数据
 */
public class JsDetect {

    /**
     * 访问者相关
     */
    @Must("网站ID")
    private int idsite;     //(必需) 网站ID
    @Must("访问者ID")
    private String idvtor;  //(必需) 访问者ID 16个字符的十六进制字符串
    private Boolean idn;    //(推荐) 是否新的访问者
    @Must("访问者当前访问的次数")
    private Integer idvc;   //(必需) 访问者当前访问的次数
    @Must("访问者第一次访问时间")
    private long idts;      //(必需) 访问者第一次访问时间
    @Must("访问者本次次访问时间")
    private Long visits;    //(必需) 访问者本次次访问时间
    private Long lastts;    //(按需) 访问者上一次访问时间

    /**
     * 数据收集相关
     */
    @Must("当前页面的完整URL")
    private String url;     //(必需) 当前页面的完整URL
    @Must("页面加载时间")
    private Integer gtms;   //(必需) 页面加载时间
    private String title;   //(推荐) 当前页面的标题
    private String screen;  //(推荐) 屏幕分辨率
    private String color;   //(可选) 颜色深度
    private Boolean java;   //(推荐) 是否支持java
    private Boolean cookie; //(推荐) 是否支持cookie
    private String cset;    //(推荐) 浏览器编码
    private String lang;    //(推荐) 客户端语言
    private String refer;   //(推荐) 上一页面url

    /**
     * 分享传播
     */
    private String shareto;  // 分享到
    private String fromvid;  // 分享者ID 16个字符的十六进制字符串
    private Long fromvts;    // 分享者分享时间


    /**
     * 服务器获取数据
     */
    private String brand;           //品牌
    private String model;           //型号
    private String browser;         //浏览器
    private String version;         //浏览器版本
    private String platform;        //平台 电脑|手机|平板
    private String operateSystem;   //操作系统
    private String engine;          //浏览器引擎
    private String engineVersion;   //浏览器引擎版本
    private String remoteAddr;      //客户端IP（外网）
    private String country;         //国家
    private String city;            //城市
    private String region;          //区域
    private String isp;             //运营商
    private String netType;         //网络类型
    private String endType;         //终端类型
    private String useragent;       //源 useragent
    private String appname;         //App 源


    @JsonIgnore
    HttpServletRequest request;

    static Pattern patternPiwikCookie = Pattern.compile("(\\w+)\\.",Pattern.CASE_INSENSITIVE);

    public void setRequest(HttpServletRequest request) {
        this.request = request;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().startsWith("_pk_id")) {
                    Matcher matcher = patternPiwikCookie.matcher(cookie.getValue());
                    if (matcher.find()) {
                        this.setRefer(matcher.group(1));
                    }
                }
            }
        }

        String agent = request.getHeader("user-agent");
        UserAgentParser userAgentParser = new UserAgentParser();
        UserAgent useragent = userAgentParser.parse(agent);

        this.setBrand(useragent.getBrand().getAcronym());
        this.setModel(useragent.getBrand().getModel());
        this.setAppname(useragent.getApplication().getAcronym());
        this.setBrowser(useragent.getBrowser().getAcronym());
        this.setVersion(useragent.getBrowser().getVersion());
        this.setPlatform(useragent.getOperateSystem().getVersion());
        this.setOperateSystem(useragent.getOperateSystem().getAcronym());
        this.setEngine(useragent.getBrowserEngine().getAcronym());
        this.setEngineVersion(useragent.getBrowserEngine().getVersion());
        this.setNetType(useragent.getNetType().getAcronym());
        this.setEndType(useragent.getPlatform().getAcronym());
        this.setRemoteAddr(request.getRemoteAddr());
        this.setUseragent(agent);

        this.remoteAddr = IPCatcherUtil.getIpAddr(request);

        try {
            ServletContext application = request.getSession().getServletContext();
            IP.load(application.getRealPath("/WEB-INF/classes/17monipdb.dat"));
            String[] addrs = IP.find(this.remoteAddr);
            this.country = addrs[0];
            this.region = addrs[1];
            this.city = addrs[2];

//            //通过纯真IP库取出运营商信息
//            IPTest txIp = new IPTest();
//            String[] tx_location = txIp.txIpParser(this.remoteAddr);
//            this.setIsp(tx_location[4]);

            //通过百度API获取运营商信息
//            BDIP bd=new BDIP();
//            String isp=bd.getIPXY(this.remoteAddr)[2];
//            this.setIsp(isp);

            if ("lost".equals(refer)){
                setRefer(refer + "-" + request.getQueryString());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 检测是否满足必须参数
     */
    public void check() {
        Field[] fields = AfReflecter.getFieldAnnotation(this.getClass(), Must.class);
        for (Field field:fields){
            String name = field.getName() + ":" + field.getAnnotation(Must.class).value();
            Object val = AfReflecter.getMemberNoException(this, field.getName());
            if (val == null || AfStringUtil.isEmpty(val.toString())){
                refer = "lost";
                gtms = 1000;
                new ServiceException("缺少参数["+name+"] url="+url).printStackTrace(System.err);
                //throw new ServiceException("缺少参数["+name+"]");
            }
        }
    }

    public void bind(HttpServletRequest request, HttpServletResponse response) {
        if (this.request == null) {
            setRequest(request);
        }
    }

    public Visit build(int idsite) {
        Visit visit = new Visit();
        visit.setIdsite(idsite);
        visit.setIdvisitor(this.getIdvtor());
        visit.setBrowserName(this.getBrowser());
        visit.setBrowserVersion(this.getVersion());
        visit.setCountVisits(this.getIdvc());
        visit.setLocationIp(this.getRemoteAddr());
        visit.setVisitServertime(new Date());
        visit.setVisitLocaltime(new Date(this.getVisits()));
        visit.setNewUser(this.isIdn());
        visit.setOperateSystem(this.getOperateSystem());
        visit.setOperateVersion(this.getPlatform());
        visit.setScreenDepth(this.getColor());
        visit.setScreenResolution(this.getScreen());
        visit.setUrlReferer(this.getRefer());
        visit.setLocationLang(this.getLang());
        visit.setLocationCountry(this.getCountry());
        visit.setLocationCity(this.getCity());
        visit.setLocationRegion(this.getRegion());
        visit.setLocationIsp(this.getIsp());
        visit.setEndModel(this.getModel());
        visit.setEndBrand(this.getBrand());
        visit.setEndType(this.getEndType());
        visit.setNetType(this.getNetType());
        visit.setUseragent(this.getUseragent());
        visit.setEndApp(this.getAppname());
        visit.setSpCookie(this.getCookie());
        visit.setSpJava(this.getJava());
        visit.setCountEvents(0);
        visit.setVisitTotaltime(0);
        visit.setIdurl(null);
        visit.setIdtitle(null);
        return visit;
    }


    public int getIdsite() {
        return idsite;
    }

    public void setIdsite(int idsite) {
        this.idsite = idsite;
    }

    public String getIdvtor() {
        return idvtor;
    }

    public void setIdvtor(String idvtor) {
        this.idvtor = idvtor;
    }

    public Boolean isIdn() {
        return idn;
    }

    public void setIdn(Boolean idn) {
        this.idn = idn;
    }

    public Integer getIdvc() {
        return idvc;
    }

    public void setIdvc(Integer idvc) {
        this.idvc = idvc;
    }

    public long getIdts() {
        return idts;
    }

    public void setIdts(long idts) {
        this.idts = idts;
    }

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

    public Long getLastts() {
        return lastts;
    }

    public void setLastts(Long lastts) {
        this.lastts = lastts;
    }

    public Integer getGtms() {
        return gtms;
    }

    public void setGtms(Integer gtms) {
        if (gtms < 0) {
            gtms = 10;
            new ServiceException("页面加载时间范围错误 gtms="+gtms).printStackTrace(System.err);
        }
        this.gtms = gtms;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (url != null && url.length() > 255) {
            url = url.substring(0, 255);
        }
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(!title.matches("[\\u4E00-\\u9FA5]+")){
            try {
                title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (title.length() > 127) {
            title = title.substring(0,127);
        }
        this.title = title;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        if (screen != null && screen.length() > 15) {
            screen = screen.substring(0, 15);
        }
        this.screen = screen;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getJava() {
        return java;
    }

    public void setJava(Boolean java) {
        this.java = java;
    }

    public Boolean getCookie() {
        return cookie;
    }

    public void setCookie(Boolean cookie) {
        this.cookie = cookie;
    }

    public String getCset() {
        return cset;
    }

    public void setCset(String cset) {
        if (cset != null && cset.length() > 9) {
            cset = cset.substring(0, 9);
        }
        this.cset = cset;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        if (lang != null && lang.length() > 9) {
            lang = lang.substring(0, 9);
        }
        this.lang = lang;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        if (refer != null && refer.length() > 255) {
            refer = refer.substring(0, 255);
        }
        this.refer = refer;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        if (version != null && version.length() > 31) {
            version = version.substring(0, 31);
        }
        this.version = version;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOperateSystem() {
        return operateSystem;
    }

    public void setOperateSystem(String operateSystem) {
        this.operateSystem = operateSystem;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        if (remoteAddr != null && remoteAddr.length() > 15) {
            remoteAddr = remoteAddr.substring(0, 15);
        }
        this.remoteAddr = remoteAddr;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model != null && model.length() > 31) {
            model = model.substring(0, 31);
        }
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city != null && city.length() > 31) {
            city = city.substring(0, 31);
        }
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country != null && country.length() > 15) {
            country = country.substring(0, 15);
        }
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if (region != null && region.length() > 31) {
            region = region.substring(0, 31);
        }
        this.region = region;
    }
    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
    public String getEndType() {
        return endType;
    }

    public void setEndType(String endType) {
        this.endType = endType;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        if (useragent != null && useragent.length() > 255) {
            useragent = useragent.substring(0, 255);
        }
        this.useragent = useragent;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getShareto() {
        return shareto;
    }

    public void setShareto(String shareto) {
        if (shareto != null && shareto.length() > 15) {
            shareto = shareto.substring(0, 15);
        }
        this.shareto = shareto;
    }

    public String getFromvid() {
        return fromvid;
    }

    public void setFromvid(String fromvid) {
        this.fromvid = fromvid;
    }

    public Long getFromvts() {
        if (fromvid == null) {
            return 0l;
        }
        return fromvts;
    }

    public void setFromvts(Long fromvts) {
        this.fromvts = fromvts;
    }
}