(function () {
    /**
     * 定义常量
     */
    var REFER_SHARETO = "shareto";
    var REFER_VISITORID = "wa_share_vtor";
    var REFER_TIMESTAMP = "wa_share_time";
    /**
     * 创建全局工具对象。
     * @param win {Window} 窗口对象。
     * @param doc {Document} 文档对象。
     */
    var global = new Global(window, window.document),
        screen = global.screen,
        location = global.location,
        navigator = global.navigator,
        document = global.document,
        encode = global.encode,
        decode = global.decode,
        empty = global.empty,
        performance = global.performance,
        jsverson = "1.0",
        server = "/tracker",
        path_track_event = "/" + jsverson + "/ten",
        path_track_pageview = "/" + jsverson + "/tpv",
        path_track_userinfo = "/" + jsverson + "/tur";

    /**
     * 客户端探测对象（如 饼干、分辨率...）
     */
    var tracker = new Tracker(server, "1");
    var detect = new Detect(global);
    window.WADS = {
        linkChange: tracker.linkChange,
        trackEvent: tracker.trackEvent,
    };

    // asynchronous tracker (or proxy)
    if (typeof _wapaq !== 'object') {
        _wapaq = [];
    }
    var applyFirst = ['setTrackerUrl', 'setSiteId'];
    _wapaq = applyMethodsInOrder(_wapaq, applyFirst);

    // apply the queue of actions
    for (var iterator = 0; iterator < _wapaq.length; iterator++) {
        if (_wapaq[iterator]) {
            apply(_wapaq[iterator]);
        }
    }

    // replace initialization array with proxy object
    _wapaq = new TrackerProxy();

    //tracker.trackPageView();
    //for(var p in detect){
    //    if(typeof(detect[p])!="function"){
    //        var val = detect[p] || empty;
    //        println(p + " = " + val)
    //    }
    //}
    //println("uid" + " = " + tracker.getVisitor().uuid);
    //println("cookie" + " : " + document.cookie);

    /************************************************************
     * Proxy object
     * - this allows the caller to continue push()'ing to _paq
     *   after the Tracker has been initialized and loaded
     ************************************************************/
    function TrackerProxy() {
        return {
            push: apply
        };
    }

    /**
     * Applies the given methods in the given order if they are present in paq.
     *
     * @param {Array} paq
     * @param {Array} methodsToApply an array containing method names in the order that they should be applied
     *                 eg ['setSiteId', 'setTrackerUrl']
     * @returns {Array} the modified paq array with the methods that were already applied set to undefined
     */
    function applyMethodsInOrder(paq, methodsToApply) {
        var appliedMethods = {};
        var index, iterator;
        for (index = 0; index < methodsToApply.length; index++) {
            var methodNameToApply = methodsToApply[index];
            appliedMethods[methodNameToApply] = 1;
            for (iterator = 0; iterator < paq.length; iterator++) {
                if (paq[iterator] && paq[iterator][0]) {
                    var methodName = paq[iterator][0];
                    if (methodNameToApply === methodName) {
                        apply(paq[iterator]);
                        delete paq[iterator];
                        if (appliedMethods[methodName] > 1) {
                            if (console !== undefined && console && console.error) {
                                console.error('The method ' + methodName + ' is registered more than once in "paq" variable. Only the last call has an effect. Please have a look at the multiple Piwik trackers documentation: http://developer.piwik.org/guides/tracking-javascript-guide#multiple-piwik-trackers');
                            }
                        }
                        appliedMethods[methodName]++;
                    }
                }
            }
        }
        return paq;
    }

    /**
     * apply wrapper
     *
     * @param array parameterArray An array comprising either:
     *      [ 'methodName', optional_parameters ]
     * or:
     *      [ functionObject, optional_parameters ]
     */
    function apply() {
        var i, f, parameterArray;

        for (i = 0; i < arguments.length; i += 1) {
            parameterArray = arguments[i];
            f = parameterArray.shift();

            if (isString(f)) {
                return tracker[f].apply(tracker, parameterArray);
            } else {
                return f.apply(tracker, parameterArray);
            }
        }
    }

    /**
     * Is property a string?
     */
    function isString(property) {
        return typeof property === 'string' || property instanceof String;
    }

    /**
     * 追踪器
     * @param {Object} global
     * @param {Object} detect
     */
    function Tracker(trackerUrl, siteId) {
        var
        // Life of the visitor cookie (in milliseconds)
            configVisitorCookieTimeout = 33955200000, // 13 months (365 days + 28days)
        // Tracker URL
            configTrackerUrl = trackerUrl || '',
        // Site ID
            configTrackerSiteId = siteId || '',

            configDiscardHashTag = false,

            configDiscardUrlParams = true;

        var cookie = global.cookie;
        var hash = global.hash.sha1;

        if (global.isDefined(siteId)) {
            setSiteId(configTrackerSiteId);
        }

        function setSiteId(siteId) {
            configTrackerSiteId = siteId;
            setVisitorIdCookie();
        }

        function setDiscardHashTag(config){
            configDiscardHashTag = config;
        }

        function setDiscardUrlParams(config){
            configDiscardUrlParams = config;
        }

        function getDiscardHashTag(){
            return configDiscardHashTag;
        }

        function getDiscardUrlParams(){
            return configDiscardUrlParams;
        }

        /*
         * Get cookie name with prefix and domain hash
         */
        function getCookieName(baseName) {
            // NOTE: If the cookie name is changed, we must also update the PiwikTracker.php which
            // will attempt to discover first party cookies. eg. See the PHP Client method getVisitorId()
            return "_wa_" + baseName + '.' + configTrackerSiteId;
        }

        function setTrackerCookie(name, value, expire) {
            return cookie.setCookie(getCookieName(name), value, expire);
        }

        function getTrackerCookie(name) {
            return cookie.getCookie(getCookieName(name));
        }

        function delTrackerCookie(name) {
            return cookie.delCookie(getCookieName(name));
        }

        /*
         * Sets the Visitor ID cookie
         */
        function setVisitorIdCookie(visitorIdCookieValues) {
            if (!global.isDefined(configTrackerSiteId)) {//if(!configTrackerSiteId) {
                // when called before Site ID was set
                return;
            }

            var now = new Date(),
                nowTs = Math.round(now.getTime());

            if (!global.isDefined(visitorIdCookieValues)) {
                visitorIdCookieValues = getValuesFromVisitorIdCookie();
            }

            var cookieValue = visitorIdCookieValues.uuid + '.' +
                visitorIdCookieValues.createTs + '.' +
                visitorIdCookieValues.visitCount + '.' +
                nowTs + '.' +
                visitorIdCookieValues.lastVisitTs;

            setTrackerCookie("id", cookieValue, getRemainingVisitorCookieTimeout());
        }

        function getRemainingVisitorCookieTimeout() {
            var now = new Date(),
                nowTs = now.getTime(),
                cookieCreatedTs = getValuesFromVisitorIdCookie().createTs;

            var createTs = parseInt(cookieCreatedTs, 10);
            var originalTimeout = (createTs) + configVisitorCookieTimeout - nowTs;
            return originalTimeout;
        }

        /*
         * 生成一个 伪-唯一ID 来标记当前用户
         * 16 位十六进制  = 64 比特
         * 注: 这不是 RFC4122-compliant UUID
         */
        function generateRandomUuid() {
            return hash(
                (navigator.userAgent || '') +
                (navigator.platform || '') +
                (new Date()).getTime() + Math.random()
            ).slice(0, 16);
        }

        /*
         * Load visitor ID cookie
         */
        function loadVisitorIdCookie() {
            var now = new Date(),
                nowTs = Math.round(now.getTime()),
                id = getTrackerCookie('id'),
                cookieValue,
                uuid;
            // Visitor ID cookie found
            if (id) {
                cookieValue = id.split('.');
                // returning visitor flag
                cookieValue.unshift('0');
                return cookieValue;
            }
            uuid = generateRandomUuid();
            // No visitor ID cookie, let's create a new one
            cookieValue = [
                // new visitor
                '1',
                // uuid
                uuid,
                // creation timestamp - seconds since Unix epoch
                nowTs,
                // visitCount - 0 = no previous visit
                0,
                // current visit timestamp
                nowTs,
                // last visit timestamp - blank = no previous visit
                '',
                // last ecommerce order timestamp
                ''
            ];
            return cookieValue;
        }

        /**
         * Loads the Visitor ID cookie and returns a named array of values
         */
        function getValuesFromVisitorIdCookie() {
            var cookieVisitorIdValue = loadVisitorIdCookie(),
                newVisitor = cookieVisitorIdValue[0],
                uuid = cookieVisitorIdValue[1],
                createTs = cookieVisitorIdValue[2],
                visitCount = cookieVisitorIdValue[3],
                currentVisitTs = cookieVisitorIdValue[4],
                lastVisitTs = cookieVisitorIdValue[5];
            return {
                newVisitor: newVisitor,
                uuid: uuid,
                createTs: createTs,
                visitCount: visitCount,
                currentVisitTs: currentVisitTs,
                lastVisitTs: lastVisitTs,
            };
        }

        /**
         *
         private String idvtor;  //(必需) 访问者ID 16个字符的十六进制字符串
         private boolean idn;    //(推荐) 是否新的访问者
         private Integer idvc;   //(必需) 访问者当前访问的次数
         private Long idts;      //(推荐) 访问者本次次访问时间
         private Long lasts;     //(可选) 访问者上一次访问时间
         * */
        function getPageviewParam() {
            var visitor = getValuesFromVisitorIdCookie();
            return "idsite=" + configTrackerSiteId
                + "&idvtor=" + visitor.uuid
                + "&idn=" + visitor.newVisitor
                + "&idvc=" + visitor.visitCount
                + "&idts=" + visitor.createTs
                + "&visits=" + Math.round(new Date().getTime())
                + "&lastts=" + visitor.lastVisitTs
                + detect.getParam();
        }

        /**
         private String ec;      //(必需) 事件类别 (如: 视频、音乐、游戏)
         private String ea;      //(必需) 事件动作 (如: 播放、暂停、持续时间、添加播放列表,下载,点击……)
         private String en;      //(可选) 事件名称 (如: 电影名字,歌曲名称或文件名称…)
         private Float ev;       //(可选) 事件的值 (必须是一个浮点数或整数值(数字),而不是一个字符串)
         * */
        function getEventParam(category, action, name, value) {
            var visitor = getValuesFromVisitorIdCookie();
            return "idsite=" + configTrackerSiteId
                + "&idvtor=" + visitor.uuid
                + "&idn=" + visitor.newVisitor
                + "&idvc=" + visitor.visitCount
                + "&idts=" + visitor.createTs
                + "&visits=" + Math.round(new Date().getTime())
                + "&lastts=" + visitor.lastVisitTs
                + "&ec=" + category
                + "&ea=" + action
                + (global.isDefined(name)?("&en=" + name):"")
                + (global.isDefined(value)?("&ev=" + value):"")
                + detect.getParam();
        }

        return {
            /**
             * Specify the Piwik server URL
             * @param string trackerUrl
             */
            setTrackerUrl: function (trackerUrl) {
                configTrackerUrl = trackerUrl;
            },
            /**
             * Specify the site ID
             * @param int|string siteId
             */
            setSiteId: function (siteId) {
                setSiteId(siteId);
            },
            setDiscardHashTag : function (config){
                setDiscardHashTag(config);
            },
            setDiscardUrlParams : function (config){
                setDiscardUrlParams(config);
            },
            getDiscardHashTag : function (){
                return getDiscardHashTag();
            },
            getDiscardUrlParams : function (){
                return getDiscardUrlParams();
            },
            getVisitor: function () {
                return getValuesFromVisitorIdCookie();
            },
            trackPageView: function () {
                var url = configTrackerUrl + path_track_pageview;
                global.ajax.send(url, (getPageviewParam()));
            },
            trackEvent: function (category, action, name, value) {
                var url = configTrackerUrl + path_track_event;
                if (global.isDefined(category) && global.isDefined(action)) {
                    global.ajax.send(url, getEventParam(category, action, name, value));
                }
            },
            linkChange: function (url) {
                var old = url;
                try {
                    var idtag = "",
                        visitor = getValuesFromVisitorIdCookie(),
                        visitorId = visitor.uuid,
                        timestampId = Math.round(new Date().getTime());
                    if (url.indexOf("#") != -1) {
                        idtag = url.split("#")[1];
                        url = url.split("#")[0];
                    }
                    if (url.indexOf(REFER_VISITORID) != -1) {
                        url = url.replace(new RegExp(REFER_VISITORID + "=[\\w]+"), REFER_VISITORID + "=" + visitorId);
                    } else {
                        url = global.url.putArg(url, REFER_VISITORID, visitorId)
                    }
                    if (url.indexOf(REFER_TIMESTAMP) != -1) {
                        url = url.replace(new RegExp(REFER_TIMESTAMP + "=[\\w]+"), REFER_TIMESTAMP + "=" + timestampId);
                    } else {
                        url = global.url.putArg(url, REFER_TIMESTAMP, timestampId)
                    }
                    if (idtag != "")url += "#" + idtag;
                    return url
                } catch (i) {
                    return old
                }
            },
            sendAuthUserInfo: function (user, appid) {
                if (user == undefined || user == null)return;
                var visitor = getValuesFromVisitorIdCookie(),
                    visitorId = visitor.uuid,
                    param = ("url=" + encodeURIComponent(detect.url) +
                    "&idvisitor=" + visitorId +
                    "&idsite=" + configTrackerSiteId +
                    "&openid=" + user.openid +
                    "&nickname=" + encodeURIComponent(encodeURIComponent(user.nickname)) +
                    "&sex=" + user.sex +
                    "&province=" + encodeURIComponent(encodeURIComponent(user.province)) +
                    "&city=" + encodeURIComponent(encodeURIComponent(user.city)) +
                    "&country=" + encodeURIComponent(encodeURIComponent(user.country)) +
                    "&headimgurl=" + user.headimgurl +
                    "&privilege=" + user.privilege +
                    "&unionid=" + user.unionid +
                    "&appid=" + appid);
                var url = configTrackerUrl + path_track_userinfo;
                global.ajax.send(url, param);
            }
        };
    }

    /**
     * 客户端探测对象（如 饼干、分辨率...）
     * rand=601128
     * &idsite=2
     * &idvtor=8be47625d3ce98f0
     * &idn=0
     * &idvc=0
     * &idts=1445397766661
     * &visits=1445397766674
     * &lastts=
     *
     * &java=false
     * &cookie=true
     * &color=24
     * &cset=UTF-8
     * &lang=zh-CN
     * &fromvid=
     * &fromvts=1445397766568
     * &title=%E8%B4%B5%E5%B7%9E%E7%9C%81%E7%9B%B4%E6%9C%BA%E5%85%B3%E2%80%9C%E6%A6%9C%E6%A0%B7%E9%9D%92%E5%B9%B4%C2%B7%E7%AC%AC%E4%B8%80%E4%B9%A6%E8%AE%B0%E2%80%9D%E5%BE%AE%E4%BF%A1%E6%8A%95%E7%A5%A8
     * &url=http://112.124.118.30:8080/dcvote/wx/index.jsp?isappinstalled=0,org.apache.catalina.connector.ResponseFacade@ca1f21a
     */
    function Detect() {
        var _this = this;
        this.java = (function () {
            return navigator.javaEnabled && navigator.javaEnabled();
        })();
        this.cookie = (function () {
            return navigator.cookieEnabled == true;
        })();
        this.color = (function () {
            return screen ? screen.colorDepth : empty;
        })();
        this.cset = (function () {
            return document ? document.characterSet : empty;
        })();
        this.lang = (function () {
            return navigator ? navigator.language : empty;
        })();
        this.shareto = global.url.getArg(REFER_SHARETO, "");
        this.fromvid = global.url.getArg(REFER_VISITORID, "");
        this.fromvts = global.url.getArg(REFER_TIMESTAMP, Math.round(new Date().getTime()));
        this.title = (function () {
            if (document) {
                var title = document.title;
                title = title && title.text ? title.text : title;
                if (!isString(title)) {
                    var tmp = documentAlias.getElementsByTagName('title');
                    if (tmp && global.isDefined(tmp[0])) {
                        title = tmp[0].text;
                    }
                }
                return title;
            }
            return empty;
        })();
        this.gtms = (function () {
            var dtiming = 1000,timing = dtiming;
            if (performance && performance.timing
                && performance.timing.requestStart && performance.timing.responseEnd) {
                timing = performance.timing.responseEnd - performance.timing.requestStart;
            }
            if (timing < 0) {
                timing = dtiming;
            }
            return timing;
        })();
        this.url = (function () {
            if (location && location.href) {
                var url = location.href;
                if (tracker.getDiscardHashTag()) {
                    var targetPattern = new RegExp('#.*');
                    url = url.replace(targetPattern, '');
                }
                if (tracker.getDiscardUrlParams()) {
                    var targetPattern = new RegExp('\\?.*');
                    url = url.replace(targetPattern, '');
                }
                return global.url.remArg(url, [REFER_VISITORID, REFER_TIMESTAMP])
            }
            return empty;
        })();
        this.refer = (function () {
            var referrer = document ? (document.referrer) : empty;
            return (referrer == location.href || _this.url == referrer) ? empty : referrer;
        })();
        this.screen = (function () {
            if (screen) {
                if ((screen.width < 1000 || screen.height < 1000) && window.devicePixelRatio) {
                    return Math.round(screen.width * window.devicePixelRatio) + 'x' + Math.round(screen.height * window.devicePixelRatio);
                }
                return Math.round(screen.width) + 'x' + Math.round(screen.height);
            }
            return empty;
        })();
        /**
         * lost-
         * &rand=322690
         * &idsite=2
         * &idvtor=1def74766ca0ddf1
         * &idn=1
         * &idvc=0
         * &idts=1445417788039
         * &visits=1445417788040
         * &lastts=
         *
         * &java=false
         * &cookie=false
         * &color=0
         * &cset=UTF-8
         * &lang=zh-CN
         * &fromvid=
         * &fromvts=1445417788034
         * &title=%E8%B4%B5%E5%B7%9E%E7%9C%81%E7%9B%B4%E6%9C%BA%E5%85%B
         * @returns {string}
         */
        this.getParam = function () {
            return "&java=" + detect.java +
            "&cookie=" + detect.cookie +
            "&color=" + detect.color +
            "&cset=" + detect.cset +
            "&lang=" + detect.lang +
            "&shareto=" + detect.shareto +
            "&fromvid=" + detect.fromvid +
            "&fromvts=" + detect.fromvts +
            "&refer=" + detect.refer +
            "&screen=" + detect.screen +
            "&gtms=" + detect.gtms +
            "&title=" + global.encode(detect.title) +
            "&url=" + global.encode(detect.url);
        };
    }

    /**
     * 全局工具对象。
     * @param win {Window} 窗口对象。
     * @param doc {Document} 文档对象。
     */
    function Global(win, doc) {
        var _this = this;
        this.window = win;
        this.document = doc;
        this.navigator = win.navigator;
        this.location = win.location;
        this.screen = win.screen;
        this.empty = ' ';
        this.encode = win.encodeURIComponent;
        this.decode = win.decodeURIComponent;
        this.performance = win.performance || win.mozPerformance || win.msPerformance || win.webkitPerformance;
        /*
         * Is property defined?
         */
        this.isDefined = function (property) {
            // workaround https://github.com/douglascrockford/JSLint/commit/24f63ada2f9d7ad65afc90e6d949f631935c2480
            var propertyType = typeof property;
            return propertyType !== 'undefined';
        }
        this.cookie = {
            setCookie: function (name, value, expire, path, domain, secure) {
                var expiryDate;
                // relative time to expire in milliseconds
                if (expire) {
                    expiryDate = new Date();
                    expiryDate.setTime(expiryDate.getTime() + expire);
                }
                document.cookie = name + '=' + encode(value) +
                    (expire ? ';expires=' + expiryDate.toGMTString() : '') +
                    ';path=' + (path || '/') +
                    (domain ? ';domain=' + domain : '') +
                    (secure ? ';secure' : '');
            },
            getCookie: function (name) {
                var pattern = new RegExp('(^|;)[ ]*' + name + '=([^;]*)'),
                    match = pattern.exec(document.cookie);
                return match ? decode(match[2]) : 0;
            },
            delCookie: function (name) {
                var exp = new Date();
                exp.setTime(exp.getTime() - 1);
                var cval = getCookie(name);
                if (cval != null)
                    document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
            },
        };
        this.storage = {
            setItem: function (name, value) {
                sessionStorage && sessionStorage.setItem(name, value);
                _this.cookie.setCookie(name, value)
            },
            getItem: function (name) {
                if (sessionStorage) {
                    return sessionStorage.getItem(name)
                } else {
                    return _this.cookie.getCookie(name)
                }
            }
        };
        this.url = {
            putArg: function (url, name, value) {
                if (value == undefined || value == null) {
                    value = "";
                }
                if (url.indexOf("?") != -1 || url.indexOf("=") != -1) {
                    return url + "&" + name + "=" + value;
                } else {
                    return url + "?" + name + "=" + value;
                }
            },
            getArg: function (name, def) {
                var n = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var t = window.location.search.substr(1).match(n);
                if (t != null)return unescape(t[2]);
                return def;
            },
            remArg: function (url, names) {
                var urls = url.split("?"), source = "", idtag = "";
                if (urls.length > 1) {
                    source = urls[0];
                    url = urls[1];
                }
                urls = url.split("#");
                if (urls.length > 1) {
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
        };
        this.ajax = {
            /**
             * 发送请求。
             * @param url {String} 发送请求的地址。
             * @param param {String} 发送请求的参数串。
             * @param mark {String} 请求的标识（包括版本号、随机整数ID、UA账号、域名哈希值）。
             * @param callback {Function} 回调函数。
             * @param _ioo {Boolean} 是否忽略参数长度溢出错误
             */
            send: function (url, param, mark, callback, _ioo) {
                if (param.length <= 2036 || _ioo) {
                    this.sendByImage(url, param, callback);
                } else {
                    mark = mark || "auto";
                    this.sendByImage(url, "&mark=" + mark + "&err=len&max=2036&len=" + param.length, callback);
                }
            },
            /**
             * 使用图片对象发出请求。
             * @param src {String} 组装完毕的图片的地址。
             * @param callback {Function} 回调函数。
             */
            sendByImage: function (src, param, callback) {
                var image = new Image(1, 1),wkey = "_wa_tangram_"+Math.floor(2147483648 * Math.random()).toString(36);
                if (param[0] == '?') {
                    param[0] = '&';
                }
                if (param[0] != '&') {
                    param = '&' + param;
                }
                window[wkey] = image;
                image.onload = image.onerror = image.onabort = function () {
                    image.onload = image.onerror = image.onabort = null;
                    image = window[wkey] = null;
                    (callback && callback());
                };
                image.src = src + "?rand=" + String(Math.random()).slice(2, 8) + param;
            }
        };
        this.hash = {
            /************************************************************
             * sha1
             ************************************************************/
            sha1: function (str) {
                var
                    rotate_left = function (n, s) {
                        return (n << s) | (n >>> (32 - s));
                    },
                    cvt_hex = function (val) {
                        var strout = '',
                            i,
                            v;
                        for (i = 7; i >= 0; i--) {
                            v = (val >>> (i * 4)) & 0x0f;
                            strout += v.toString(16);
                        }
                        return strout;
                    },
                    blockstart,
                    i,
                    j,
                    W = [],
                    H0 = 0x67452301,
                    H1 = 0xEFCDAB89,
                    H2 = 0x98BADCFE,
                    H3 = 0x10325476,
                    H4 = 0xC3D2E1F0,
                    A,
                    B,
                    C,
                    D,
                    E,
                    temp,
                    str_len,
                    word_array = [];

                str = window.encodeURIComponent(str);
                str_len = str.length;

                for (i = 0; i < str_len - 3; i += 4) {
                    j = str.charCodeAt(i) << 24 | str.charCodeAt(i + 1) << 16 |
                        str.charCodeAt(i + 2) << 8 | str.charCodeAt(i + 3);
                    word_array.push(j);
                }

                switch (str_len & 3) {
                    case 0:
                        i = 0x080000000;
                        break;
                    case 1:
                        i = str.charCodeAt(str_len - 1) << 24 | 0x0800000;
                        break;
                    case 2:
                        i = str.charCodeAt(str_len - 2) << 24 | str.charCodeAt(str_len - 1) << 16 | 0x08000;
                        break;
                    case 3:
                        i = str.charCodeAt(str_len - 3) << 24 | str.charCodeAt(str_len - 2) << 16 | str.charCodeAt(str_len - 1) << 8 | 0x80;
                        break;
                }

                word_array.push(i);

                while ((word_array.length & 15) !== 14) {
                    word_array.push(0);
                }

                word_array.push(str_len >>> 29);
                word_array.push((str_len << 3) & 0x0ffffffff);

                for (blockstart = 0; blockstart < word_array.length; blockstart += 16) {
                    for (i = 0; i < 16; i++) {
                        W[i] = word_array[blockstart + i];
                    }

                    for (i = 16; i <= 79; i++) {
                        W[i] = rotate_left(W[i - 3] ^ W[i - 8] ^ W[i - 14] ^ W[i - 16], 1);
                    }

                    A = H0;
                    B = H1;
                    C = H2;
                    D = H3;
                    E = H4;

                    for (i = 0; i <= 19; i++) {
                        temp = (rotate_left(A, 5) + ((B & C) | (~B & D)) + E + W[i] + 0x5A827999) & 0x0ffffffff;
                        E = D;
                        D = C;
                        C = rotate_left(B, 30);
                        B = A;
                        A = temp;
                    }

                    for (i = 20; i <= 39; i++) {
                        temp = (rotate_left(A, 5) + (B ^ C ^ D) + E + W[i] + 0x6ED9EBA1) & 0x0ffffffff;
                        E = D;
                        D = C;
                        C = rotate_left(B, 30);
                        B = A;
                        A = temp;
                    }

                    for (i = 40; i <= 59; i++) {
                        temp = (rotate_left(A, 5) + ((B & C) | (B & D) | (C & D)) + E + W[i] + 0x8F1BBCDC) & 0x0ffffffff;
                        E = D;
                        D = C;
                        C = rotate_left(B, 30);
                        B = A;
                        A = temp;
                    }

                    for (i = 60; i <= 79; i++) {
                        temp = (rotate_left(A, 5) + (B ^ C ^ D) + E + W[i] + 0xCA62C1D6) & 0x0ffffffff;
                        E = D;
                        D = C;
                        C = rotate_left(B, 30);
                        B = A;
                        A = temp;
                    }

                    H0 = (H0 + A) & 0x0ffffffff;
                    H1 = (H1 + B) & 0x0ffffffff;
                    H2 = (H2 + C) & 0x0ffffffff;
                    H3 = (H3 + D) & 0x0ffffffff;
                    H4 = (H4 + E) & 0x0ffffffff;
                }

                temp = cvt_hex(H0) + cvt_hex(H1) + cvt_hex(H2) + cvt_hex(H3) + cvt_hex(H4);

                return temp.toLowerCase();
            }
            /************************************************************
             * end sha1
             ************************************************************/
        };
    }
})();