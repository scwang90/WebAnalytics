<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/9/7
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.kumkee.userAgent.UserAgentParser" %>
<%@ page import="com.kumkee.userAgent.UserAgent" %>
<%@ page import="com.ipmapping.IPCatcherUtil" %>
<%@ page import="com.ipmapping.IP" %>
<%@ page import="java.util.Arrays" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>探针测试标题</title>
</head>
<body>
<%
    String agent = request.getHeader("user-agent");
    System.out.println(agent);
    UserAgentParser userAgentParser = new UserAgentParser();
    UserAgent useragent = userAgentParser.parse(agent);

    /*annotation ipmapping code*/
    String ipAddr = IPCatcherUtil.getIpAddr(request);
    IP.load(application.getRealPath("/WEB-INF/classes/17monipdb.dat"));
%>
<h1>通告</h1>
<p>根据9.16日发布的测试版本，修复分辨率识别和浏览器识别，重新发布测试</p>
<br>

<div>
    <h1>js获取客户端信息</h1>
    <div id="jsdata"></div>
    <%--<button onclick="send()">发送探针数据</button>--%>
</div>
<h1>jsp获取客户端信息</h1>
APP:<%=useragent.getApplication().getRemark()%><br>
平台:<%=useragent.getDevice().getRemark()%><br>
品牌:<%=useragent.getBrand().getRemark()%><br>
型号:<%=useragent.getBrand().getModel()%><br>
新浏览器:<%=useragent.getBrowser().getRemark()%><br>
新浏览器版本:<%=useragent.getBrowser().getVersion()%><br>
新浏览器引擎:<%=useragent.getBrowserEngine().getRemark()%><br>
新浏引擎版本:<%=useragent.getBrowserEngine().getVersion()%><br>
新操作系统:<%=useragent.getOperateSystem().getRemark()%><br>
新系统版本:<%=useragent.getOperateSystem().getVersion()%><br>
网络类型:<%=useragent.getNetType().getRemark()%><br>
浏览器:<%=useragent.getBrowseer()%><br>
浏览器版本:<%=useragent.getVersion()%><br>
平台:<%=useragent.getPlatform()%><br>
操作系统:<%=useragent.getOs()%><br>
引擎:<%=useragent.getEngine()%><br>
引擎版本:<%=useragent.getEngineVersion()%><br>
客户端IP:<%=request.getRemoteAddr()%>,<%=ipAddr%> &nbsp;（注：这是外网IP）<br>
地址信息:<%=Arrays.toString(IP.find(ipAddr))%><br>
<%--客户端主机:<%=request.getRemoteHost()%><br>--%>
源代理:<%=agent%><br>
<br>
<h1>JS探针数据抓取测试内容</h1>
<p>1.检查上面统计的情况，是否符合本机的配置</p>
<p>2.如果发现有不符合的数据，可以在
    <a href="https://www.teambition.com/project/55e69f537059eb6a737d5029/tasks/scrum/55f7a6c3581c33f2363aa35c">TeamBition</a>
    上添加相关BUG收集
</p>

</body>
<script src="/js/ds.js"></script>
<!-- Piwik -->
<%--<script type="text/javascript">
    var _paq = _paq || [];
    _paq.push(['trackPageView']);
    _paq.push(['enableLinkTracking']);
    (function() {
        var u="//118.118.118.6:8080/tracker/";
        _paq.push(['setTrackerUrl', u+'tracker']);
        _paq.push(['setSiteId', 2222222]);
        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
        g.type='text/javascript'; g.async=true; g.defer=true; g.src='/js/piwik.js'; s.parentNode.insertBefore(g,s);
    })();
</script>--%>
<!-- End Piwik Code -->

<script>
    function send(){
        var image = new Image(1, 1);
        var src = "//127.0.0.1:8080/1.0/ds.png?rand="+Math.random();
        for(var p in Detect){
            if(typeof(Detect[p])=="function"){
                src += "&" + p + "=" + Detect[p]();
            }else{
                src += "&" + p + "=" + Detect[p];
            }
        }
        image.src = src;
        image.onload = function(){
            image.onload = null;
            clear();
            for(var p in Detect){
                var val = Global.empty;
                if(typeof(Detect[p])=="function"){
                    val = Detect[p]();
                }else{
                    val = Detect[p];
                }
                println(p+"->"+val)
            }
        };
    }
    function clear() {
        document.getElementById("jsdata").innerHTML = "";
    }
    function print() {
        document.getElementById("jsdata").innerHTML += arguments[0];
    }
    function println() {
        print(arguments[0]);
        document.getElementById("jsdata").innerHTML += "<br>";
    }
    var windowAlias = window, documentAlias = document,
            screenAlias = screen,
            deviceScreen = screenAlias.width + 'x' + screenAlias.height;
    var referrertip = "&nbsp;&nbsp;&nbsp;&nbsp;(需要跳转才能有数据，<a href='skip/0'>点击跳转测试</a>)";

    if(screenAlias.width<1000 || screenAlias.height<1000){
        deviceScreen = screenAlias.width*windowAlias.devicePixelRatio + 'x' + screenAlias.height*windowAlias.devicePixelRatio;
    }

    setCookie("name", "_webanalytics_");
    println("java支持:"+(navigator.javaEnabled&&navigator.javaEnabled()));
    println("分辨率:" + deviceScreen);
    println("源页面:" + document.referrer+referrertip);
    println("位彩色:" + screenAlias.colorDepth);
    println("页标题:" + document.title);
    //println("页端口:" + window.location.host);//	设置或返回主机名和当前 URL 的端口号。
    //println("页主机:" + window.location.hostname);//	设置或返回当前 URL 的主机名。
    println("页路劲:" + window.location.pathname);//	设置或返回当前 URL 的路径部分。
    println("页链接:" + window.location.href);//	设置或返回完整的 URL。
    println("饼干:" + documentAlias.cookie);
//    println("饼name:" + getCookie("name"));
//    println("饼pwad:" + getCookie("pwad"));
//    delCookie("name");
//    setCookie("name", "scwang");


    function setCookie(name, value, expire, path, domain, secure) {
        var expiryDate;
        // relative time to expire in milliseconds
        if (expire) {
            expiryDate = new Date();
            expiryDate.setTime(expiryDate.getTime() + expire);
        }
        document.cookie = name + '=' + (value) +
                (expire ? ';expires=' + expiryDate.toGMTString() : '') +
                ';path=' + (path || '/') +
                (domain ? ';domain=' + domain : '') +
                (secure ? ';secure' : '');
    }
    function getCookie(name) {
        var pattern = new RegExp('(^|;)[ ]*' + name + '=([^;]*)'),
                match = pattern.exec(document.cookie);
        return match  ? (match[2]) : 0;
    }
    function delCookie(name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null)
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }
</script>
</html>
