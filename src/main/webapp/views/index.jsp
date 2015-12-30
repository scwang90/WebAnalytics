<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/9/7
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ipmapping.IPCatcherUtil" %>
<%@ page import="com.ipmapping.IP" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.useragent.UserAgentParser" %>
<%@ page import="com.useragent.UserAgent" %>
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
<h1>测试改变链接</h1>
<%--<button onclick="location.href = ''+_wapaq.push(['linkChange', location.href]);">点击测试</button>--%>
<%--_wapaq.push(['sendAuthUserInfo', user,appid]);--%>
<button onclick="_wapaq.push(['sendAuthUserInfo', {openid:'132',nickname:'133'},'appid']);">点击测试授权信息</button>
<button onclick="location.href = WADS.linkChange(location.href);">点击测试</button>
<button onclick="location.href = remArg(location.href,['wa_share_vtor','wa_share_time']);">点击删除</button>
<br>
<h1>测试事件触发</h1>
<button onclick="_wapaq.push(['trackEvent','event1','test1']);">触发事件1</button>
<button onclick="_wapaq.push(['trackEvent','event2','test2']);">触发事件2</button>
<button onclick="_wapaq.push(['trackEvent','event3',Math.random()]);">触发事件3</button>
<button onclick="_wapaq.push(['trackEvent','event4',Math.random()]);">触发事件4</button>
<br>

<div>
    <h1>js获取客户端信息</h1>
    <div id="jsdata"></div>
    <%--<button onclick="send()">发送探针数据</button>--%>
</div>
<h1>jsp获取客户端信息</h1>
APP:<%=useragent.getApplication().getRemark()%><br>
APPV:<%=useragent.getApplication().getVersion()%><br>
平台:<%=useragent.getPlatform().getRemark()%><br>
品牌:<%=useragent.getBrand().getRemark()%><br>
型号:<%=useragent.getBrand().getModel()%><br>
浏览器:<%=useragent.getBrowser().getRemark()%><br>
浏览器版本:<%=useragent.getBrowser().getVersion()%><br>
浏览器引擎:<%=useragent.getBrowserEngine().getRemark()%><br>
浏引擎版本:<%=useragent.getBrowserEngine().getVersion()%><br>
操作系统:<%=useragent.getOperateSystem().getRemark()%><br>
系统版本:<%=useragent.getOperateSystem().getVersion()%><br>
网络类型:<%=useragent.getNetType().getRemark()%><br>
网络类型V:<%=useragent.getNetType().getValue()%><br>
<br>
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
<%--<script src="/js/ds.js"></script>--%>
<!-- WebAnalytics -->
<script type="text/javascript">
    var _wapaq = _wapaq || [];
    _wapaq.push(['trackPageView']);
    (function() {
        var u='<%=basePath%>';
        _wapaq.push(['setTrackerUrl', u+'tracker']);
        _wapaq.push(['setSiteId', 0]);
        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
        g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'js/ds.js'; s.parentNode.insertBefore(g,s);
    })();
</script>
<!-- End WebAnalytics Code -->
<!-- Piwik -->
<%--<script type="text/javascript">
    var _paq = _paq || [];
    _paq.push(['trackPageView']);
    _paq.push(['enableLinkTracking']);
    (function() {
        var u="//222.85.149.5:22345/piwik/";
        _paq.push(['setTrackerUrl', u+'piwik.php']);
        _paq.push(['setSiteId', 4]);
        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
        g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);
    })();
</script>
<noscript><p><img src="//222.85.149.5:22345/piwik/piwik.php?idsite=4" style="border:0;" alt="" /></p></noscript>--%>
<!-- End Piwik Code -->
<script>
    function remArg(url, names) {
        var urls = url.split("?"),source="",idtag = "";
        if(url.length > 1){
            source = urls[0];
            url = urls[1];
        }
        urls = url.split("#");
        if(url.length > 1){
            url = urls[0];
            idtag = "#" + urls[1];
        }
        var args = url.split("&");
        var nargs = [];
        for (var arg in args) {
            var find = false;
            for (var name in names) {
                if (args[arg].indexOf(names[name]) == 0) {
                    find = true;
                    break;
                }
            }
            if (!find) {
                nargs.push(args[arg]);
            }
        }
        if (source != "" && nargs.length > 0) {
            source = source + "?";
        }
        return source + nargs.join("&") + idtag;
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
