
(function() {
    /**
     * 创建全局工具对象。
     * @param win {Window} 窗口对象。
     * @param doc {Document} 文档对象。
     */
    var global = new Global(window,window.document),
        screen = global.screen,
        location = global.location,
        navigator = global.navigator,
        document = global.document,
        encode = global.encode,
        decode = global.decode,
        empty = global.empty,
        performance = global.performance,
        jsverson = "1.0",
        path_track_event = "/"+jsverson + "/ten",
        path_track_pageview = "/"+jsverson + "/tpv",
        server = "/tracker";

    /**
     * 客户端探测对象（如 饼干、分辨率...）
     */
    var detect = new Detect(global);
    var tracker = new Tracker(server,"1");

    // asynchronous tracker (or proxy)
    if (typeof _wapaq !== 'object') {
        _wapaq = [];
    }
    var applyFirst  = ['setTrackerUrl', 'setSiteId'];
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
    function applyMethodsInOrder(paq, methodsToApply)
    {
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
                tracker[f].apply(tracker, parameterArray);
            } else {
                f.apply(tracker, parameterArray);
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
    function Tracker (trackerUrl, siteId) {
        var
        // Life of the visitor cookie (in milliseconds)
            configVisitorCookieTimeout = 33955200000, // 13 months (365 days + 28days)
        // Tracker URL
            configTrackerUrl = trackerUrl || '',
        // Site ID
            configTrackerSiteId = siteId || '';

        var cookie = global.cookie;
        var hash = global.hash.sha1;

        if (global.isDefined(siteId)){
            setSiteId(configTrackerSiteId);
        }

        function setSiteId(siteId) {
            configTrackerSiteId = siteId;
            setVisitorIdCookie();
        }
        /*
         * Get cookie name with prefix and domain hash
         */
        function getCookieName(baseName) {
            // NOTE: If the cookie name is changed, we must also update the PiwikTracker.php which
            // will attempt to discover first party cookies. eg. See the PHP Client method getVisitorId()
            return "_wa_" + baseName + '.' + configTrackerSiteId;
        }
        function setTrackerCookie(name, value, expire){
            return cookie.setCookie(getCookieName(name),value,expire);
        }
        function getTrackerCookie(name){
            return cookie.getCookie(getCookieName(name));
        }
        function delTrackerCookie(name){
            return cookie.delCookie(getCookieName(name));
        }
        /*
         * Sets the Visitor ID cookie
         */
        function setVisitorIdCookie(visitorIdCookieValues) {
            if(!global.isDefined(configTrackerSiteId)) {//if(!configTrackerSiteId) {
                // when called before Site ID was set
                return;
            }

            var now = new Date(),
                nowTs = Math.round(now.getTime() / 1000);

            if(!global.isDefined(visitorIdCookieValues)) {
                visitorIdCookieValues = getValuesFromVisitorIdCookie();
            }

            var cookieValue = visitorIdCookieValues.uuid + '.' +
                visitorIdCookieValues.createTs + '.' +
                visitorIdCookieValues.visitCount + '.' +
                nowTs + '.' +
                visitorIdCookieValues.lastVisitTs;

            setTrackerCookie("id",cookieValue,getRemainingVisitorCookieTimeout());
        }
        function getRemainingVisitorCookieTimeout() {
            var now = new Date(),
                nowTs = now.getTime(),
                cookieCreatedTs = getValuesFromVisitorIdCookie().createTs;

            var createTs = parseInt(cookieCreatedTs, 10);
            var originalTimeout = (createTs * 1000) + configVisitorCookieTimeout - nowTs;
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
                nowTs = Math.round(now.getTime() / 1000),
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
        function getPageviewParam(){
            var visitor = getValuesFromVisitorIdCookie();
            return "idsite="+configTrackerSiteId
                + "&idvtor="+visitor.uuid
                + "&idn="+visitor.newVisitor
                + "&idvc="+visitor.visitCount
                + "&idts="+visitor.createTs
                + "&visits="+Math.round(new Date().getTime() / 1000)
                + "&lastts="+visitor.lastVisitTs
                + "&gtms="+((performance && performance.timing
                && performance.timing.requestStart && performance.timing.responseEnd)?(performance.timing.responseEnd - performance.timing.requestStart):1000)
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
            getVisitor : function(){
                return getValuesFromVisitorIdCookie();
            },
            trackPageView : function () {
                var url = configTrackerUrl + path_track_pageview;
                global.ajax.send(url,(getPageviewParam()));
            },
            trackEvent : function (category, action, name, value) {
                var url = configTrackerUrl + path_track_event;
                global.ajax.send(url,detect.getParam());
            },
        };
    }
    /**
     * 客户端探测对象（如 饼干、分辨率...）
     */
    function Detect() {
        this.java = (function () {return navigator.javaEnabled && navigator.javaEnabled();})();
        this.cookie = (function () {return navigator.cookieEnabled == true;})();
        this.color = (function () {return screen ? screen.colorDepth : empty;})();
        this.cset = (function () {return document ? document.characterSet : empty;})();
        this.lang = (function () {return navigator ? navigator.language : empty;})();
        this.url = (function () {return location ? location.href : empty;})();
        this.title = (function () {return document ? document.title : empty;})();
        this.screen = (function () {
            if(screen) {
                if((screen.width < 1000 || screen.height < 1000) && window.devicePixelRatio){
                    return screen.width*window.devicePixelRatio + 'x' + screen.height*window.devicePixelRatio;
                }
                return screen.width + 'x' + screen.height;
            }
            return empty;
        })();
        this.refer = (function () {
            var referrer = document ? (document.referrer) : empty;
            return referrer == location.href ? empty : referrer;
        })();
        this.getParam = function(){
            var param = "";
            for(var p in this){
                if (typeof(detect[p])!="function") {
                    param += "&" + p + "="+((global.isDefined(detect[p]))?detect[p]:empty);
                }
            }
            return param;
        };
    }
    /**
     * 全局工具对象。
     * @param win {Window} 窗口对象。
     * @param doc {Document} 文档对象。
     */
    function Global(win, doc) {
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
        this.isDefined = function(property) {
            // workaround https://github.com/douglascrockford/JSLint/commit/24f63ada2f9d7ad65afc90e6d949f631935c2480
            var propertyType = typeof property;
            return propertyType !== 'undefined';
        }
        this.cookie = {
            setCookie : function(name, value, expire, path, domain, secure) {
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
            getCookie : function(name) {
                var pattern = new RegExp('(^|;)[ ]*' + name + '=([^;]*)'),
                    match = pattern.exec(document.cookie);
                return match  ? decode(match[2]) : 0;
            },
            delCookie : function(name) {
                var exp = new Date();
                exp.setTime(exp.getTime() - 1);
                var cval = getCookie(name);
                if (cval != null)
                    document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
            },
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
            send : function(url, param, mark, callback, _ioo){
                if(param.length <= 2036 || _ioo){
                    this.sendByImage(url,param, callback);
                }else{
                    mark = mark || "auto";
                    this.sendByImage(url,"&mark=" + mark + "&err=len&max=2036&len=" + param.length, callback);
                }
            },
            /**
             * 使用图片对象发出请求。
             * @param src {String} 组装完毕的图片的地址。
             * @param callback {Function} 回调函数。
             */
            sendByImage : function(src, param, callback){
                var image = new Image(1, 1);
                if(param[0] == '?'){
                    param[0] = '&';
                }
                if(param[0] != '&'){
                    param = '&' + param;
                }
                image.src = src + "?rand=" + String(Math.random()).slice(2, 8) + param;
                image.onload = function(){
                    image.onload = null;
                    (callback && callback());
                };
            }
        };
        this.hash = {
            /************************************************************
             * sha1
             ************************************************************/
            sha1 : function (str) {
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
        /**
         * 处理网页来源页面的URL地址。
         * @return {String} 处理过的URL地址。
         */
        this.processSource = function (source) {
            if (!source) {
                return source;
            }
            source = source.replace(/\n|\r/g, " ");
            for (var i = 0, len = source[_length]; i < len; ++i) {
                var charCode = source.charCodeAt(i) & 255;
                if (charCode == 10 || charCode == 13) {
                    source = source[_substring](0, i) + "?" + source[_substring](i + 1);
                }
            }
            return source;
        };
    }
})();