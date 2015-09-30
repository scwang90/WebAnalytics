package com.simpletech.webanalytics.model.entity;

import com.ipmapping.IP;
import com.ipmapping.IPCatcherUtil;
import com.kumkee.userAgent.UserAgent;
import com.kumkee.userAgent.UserAgentParser;
import com.simpletech.webanalytics.annotations.Must;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.util.AfReflecter;
import com.simpletech.webanalytics.util.AfStringUtil;
import com.simpletech.webanalytics.util.ServiceException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * JS探针发送的数据格式+服务器获取数据
 */
public class JsDetect {

    /**
     * 访问者相关
     */
    @Must("网站ID")
    private String idsite;  //(必需) 网站ID
    @Must("访问者ID")
    private String idvtor;  //(必需) 访问者ID 16个字符的十六进制字符串
    private boolean idn;    //(推荐) 是否新的访问者
    @Must("访问者当前访问的次数")
    private Integer idvc;   //(必需) 访问者当前访问的次数
    @Must("访问者第一次访问时间")
    private long idts;      //(必需) 访问者第一次访问时间
    @Must("访问者本次次访问时间")
    private Long visits;    //(必需) 访问者本次次访问时间
    private Long lastts;    //(按需) 访问者上一次访问时间
    @Must("页面加载时间")
    private Integer gtms;   //(必需) 页面加载时间

    /**
     * 数据收集相关
     */
    @Must("当前页面的完整URL")
    private String url;     //(必需) 当前页面的完整URL
    private String title;   //(推荐) 当前页面的标题
    private String screen;  //(推荐) 屏幕分辨率
    private String color;   //(可选) 颜色深度
    private boolean java;   //(推荐) 是否支持java
    private boolean cookie; //(推荐) 是否支持cookie
    private String cset;    //(推荐) 浏览器编码
    private String lang;    //(推荐) 客户端语言
    private String refer;   //(推荐) 上一页面url

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
    private String netType;         //网络类型
    private String endType;         //终端类型

    /**
     * 检测是否满足必须参数
     */
    public void check() throws Exception{
        Field[] fields = AfReflecter.getFieldAnnotation(this.getClass(), Must.class);
        for (Field field:fields){
            String name = field.getName() + ":" + field.getAnnotation(Must.class).value();
            Object val = AfReflecter.getMemberNoException(this, field.getName());
            if (val == null || AfStringUtil.isEmpty(val.toString())){
                throw new ServiceException("缺少参数["+name+"]");
            }
        }
    }

    public void bind(HttpServletRequest request, HttpServletResponse response) {
        String agent = request.getHeader("user-agent");
        UserAgentParser userAgentParser = new UserAgentParser();
        UserAgent useragent = userAgentParser.parse(agent);

        this.brand = useragent.getBrand().getAcronym();
        this.model = useragent.getBrand().getModel();
        this.browser = useragent.getBrowser().getAcronym();
        this.version = useragent.getBrowser().getVersion();
        this.platform = useragent.getDevice().getAcronym();
        this.operateSystem = useragent.getOperateSystem().getAcronym();
        this.engine = useragent.getBrowserEngine().getAcronym();
        this.engineVersion = useragent.getBrowserEngine().getVersion();
        this.netType = useragent.getNetType().getAcronym();
        this.endType = useragent.getDevice().getAcronym();
        this.remoteAddr = request.getRemoteAddr();
        this.remoteAddr = IPCatcherUtil.getIpAddr(request);

        try {
            ServletContext application = request.getSession().getServletContext();
            IP.load(application.getRealPath("/WEB-INF/classes/17monipdb.dat"));
            String[] addrs = IP.find(this.remoteAddr);
            this.country = addrs[0];
            this.region = addrs[1];
            this.city = addrs[2];
        } catch (Throwable e) {

        }
    }

    public Visit build(String idsite) {
        Visit visit = new Visit();
        visit.setIdsite(idsite);
        visit.setIdvisitor(this.getIdvtor());
        visit.setBrowserName(this.getBrowser());
        visit.setBrowserVersion(this.getVersion());
        visit.setCountVisits(this.getIdvc());
        visit.setLocationIp(this.getRemoteAddr());
        visit.setVisitServertime(new Date());
        visit.setVisitLocaltime(new Date(this.getVisits()));
        visit.setOlduser(!this.isIdn());
        visit.setOperateSystem(this.getPlatform());
        visit.setOperateVersion(this.getOperateSystem());
        visit.setScreenDepth(this.getColor());
        visit.setScreenResolution(this.getScreen());
        visit.setUrlReferer(this.getRefer());
        visit.setLocationLang(this.getLang());
        visit.setLocationCountry(this.getCountry());
        visit.setLocationCity(this.getCity());
        visit.setLocationRegion(this.getRegion());
        visit.setEndType(this.getEndType());
        visit.setNetType(this.getNetType());
        visit.setCountEvents(0);
        visit.setVisitTotaltime(0);
        visit.setIdurl(null);
        visit.setIdtitle(null);
        return visit;
    }


    public String getIdsite() {
        return idsite;
    }

    public void setIdsite(String idsite) {
        this.idsite = idsite;
    }

    public String getIdvtor() {
        return idvtor;
    }

    public void setIdvtor(String idvtor) {
        this.idvtor = idvtor;
    }

    public boolean isIdn() {
        return idn;
    }

    public void setIdn(boolean idn) {
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
        this.gtms = gtms;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
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
        this.title = title;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getJava() {
        return java;
    }

    public void setJava(boolean java) {
        this.java = java;
    }

    public boolean getCookie() {
        return cookie;
    }

    public void setCookie(boolean cookie) {
        this.cookie = cookie;
    }

    public String getCset() {
        return cset;
    }

    public void setCset(String cset) {
        this.cset = cset;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
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
        this.remoteAddr = remoteAddr;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
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
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
}