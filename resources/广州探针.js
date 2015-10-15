(function () {
    var expiresTime = 60 * 30;
    var getArg = function (name) {
        var n = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var t = window.location.search.substr(1).match(n);
        if (t != null) return unescape(t[2]);
        return null
    };
    var getDomain = function (e) {
        if (!e) return "";
        if (e.indexOf("://") != -1) e = e.substr(e.indexOf("://") + 3);
        var n = ["com", "net", "org", "gov", "edu", "mil", "biz", "name", "info", "mobi", "pro", "travel", "museum", "int", "areo", "post", "rec", "im", "cn"];
        var t = e.split(".");
        if (t.length <= 1) return e;
        if (!isNaN(t[t.length - 1])) return e;
        var r = 0;
        while (r < n.length && n[r] != t[t.length - 1]) r++;
        if (r != n.length) return t[t.length - 2] + "." + t[t.length - 1];
        else {
            r = 0;
            while (r < n.length && n[r] != t[t.length - 2]) r++;
            if (r == n.length) return t[t.length - 2] + "." + t[t.length - 1];
            else return t[t.length - 3] + "." + t[t.length - 2] + "." + t[t.length - 1]
        }
    };
    var cookie = {
        get: function (e) {
            var n = document.cookie.match(new RegExp("(^| )" + e + "=([^;]*)(;|$)"));
            if (n != null) return unescape(n[2]);
            return null
        },
        add: function (e, n, r) {
            var i = e + "=" + n;
            if (r != 0) {
                var o = new Date;
                var a = r * 1e3;
                o.setTime(o.getTime() + a);
                i += "; expires=" + o.toGMTString()
            }
            var s = getDomain(location.hostname);
            if (s != "") {
                i += "; domain=" + s
            }
            i += ";path=/";
            document.cookie = i
        }
    };
    var storage = function () {
        if (storage) {
            return storage
        } else {
            var n = {
                setItem: function (n, t) {
                    cookie.add(n, t, expiresTime)
                },
                getItem: function (e) {
                    return cookie.get(e)
                }
            };
            return n
        }
    }();
    var readFrom = function () {
        var url = location.href;
        if (url.indexOf("DSCKID") != -1) {
            var dsckId = getArg("DSCKID");
            var dsTimeStamp = getArg("DSTIMESTAMP");
            storage.setItem("REFER_DSCKID", dsckId);
            storage.setItem("REFER_DSTIMESTAMP", dsTimeStamp)
        }
        if (url.indexOf("from") != -1) {
            var o = getArg("from");
            var a = {
                singlemessage: 1,
                groupmessage: 2,
                timeline: 3
            };
            if (a[o] == undefined) {
                storage.setItem("DS_FROM_TYPE", 0)
            } else {
                storage.setItem("DS_FROM_TYPE", a[o])
            }
        }
        if (document.referrer.indexOf("mp.weixin.qq.com") != -1) {
            storage.setItem("DS_FROM_TYPE", 4)
        }
    };
    /**
     * 加载第二个探针 dsTid
     */
    var loadSecondJs = function () {
        var dsTid = "";
        try {
            //获取第一个探针的 dsTid
            dsTid = document.getElementById("DS_PRE_JS").src.split("?")[1].split("=")[1]
        } catch (n) {
            console.log("please read DataStory api doc")
        }
        var script = document.createElement("script");
        script.src = document.location.protocol + "//tongji.datastory.com.cn/ds.js?dsTid=" + dsTid;
        var frscript = document.getElementsByTagName("script")[0];
        frscript.parentNode.insertBefore(script, frscript)
    };
    var Start = function () {
        try {
            if (window.DS == undefined) window.DS = {};
            DS.ready = function (callback) {
                var n = function () {
                    if (DS.linkChange == undefined || DS.sendRepost == undefined) {
                        setTimeout(n, 500)
                    } else {
                        try {
                            callback()
                        } catch (t) {
                        }
                    }
                };
                n()
            };
            readFrom();
            loadSecondJs()
        } catch (e) {
        }
    };
    Start()
})();

(function () {
    var scheme = "https:" == document.location.protocol ? " https://" : " http://";
    var service = scheme + "tongji.datastory.com.cn/";
    var extime = 60 * 30;//通用cookie过期时间
    var dsckId = "bc1ed049-0fc3-4f90-a37a-7555c5bb2df8";
    var o = "1";
    var taskId = "";
    var screem = (window.screen.width || 0) + "x" + (window.screen.height || 0);
    var timestampId = (new Date).getTime();
    var pageTimestampId = "";
    var rnd = function () {
        return parseInt(Math.random() * 1e7)
    };
    var getDomain = function (url) {
        if (!url) return "";
        if (url.indexOf("://") != -1) url = url.substr(url.indexOf("://") + 3);
        var suffixs = ["com", "net", "org", "gov", "edu", "mil", "biz", "name", "info", "mobi", "pro", "travel", "museum", "int", "areo", "post", "rec", "im", "cn"];
        var tokens = url.split(".");
        if (tokens.length <= 1) return url;
        if (!isNaN(tokens[tokens.length - 1])) return url;
        var i = 0;
        while (i < suffixs.length && suffixs[i] != tokens[tokens.length - 1]) i++;

        if (i != suffixs.length) return tokens[tokens.length - 2] + "." + tokens[tokens.length - 1];
        else {
            i = 0;
            while (i < suffixs.length && suffixs[i] != tokens[tokens.length - 2]) i++;
            if (i == suffixs.length) return tokens[tokens.length - 2] + "." + tokens[tokens.length - 1];
            else return tokens[tokens.length - 3] + "." + tokens[tokens.length - 2] + "." + tokens[tokens.length - 1]
        }
    };
    var sendByImage = function (url) {
        url = put(service + url, "rnd", rnd());
        var img = new Image;
        img.src = url
    };
    var set_cookie = {
        add: function (dsId, callback) {
            var iframe = document.createElement("iframe");
            iframe.src = "http://tongji.datastory.com.cn/set_cookie?dsId=" + dsId;
            iframe.style.display = "none";
            var script = document.getElementsByTagName("script")[0];
            script.parentNode.insertBefore(iframe, script);
            if (iframe.attachEvent) {
                iframe.attachEvent("onload", function () {
                    callback && callback()
                })
            } else {
                iframe.onload = function () {
                    callback && callback()
                }
            }
        }
    };
    var cookie = {
        get: function (name) {
            var n = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
            if (n != null) return unescape(n[2]);
            return null
        },
        add: function (name, value, expriesTime) {
            var ck = name + "=" + value;
            if (expriesTime != 0) {
                var o = new Date;
                var i = expriesTime * 1e3;
                o.setTime(o.getTime() + i);
                ck += "; expires=" + o.toGMTString()
            }
            var domain = getDomain(location.hostname);
            if (domain != "") {
                ck += "; domain=" + domain
            }
            ck += ";path=/";
            document.cookie = ck
        }
    };
    var storage = function () {
        if (sessionStorage) {
            return sessionStorage
        } else {
            var e = {
                setItem: function (name, value) {
                    cookie.add(name, value, extime)
                },
                getItem: function (name) {
                    return cookie.get(name)
                }
            };
            return e
        }
    }();
    var removearg = function (urlarg, names) {
        var args = urlarg.split("&");
        var nargs = [];
        for (var arg in args) {
            var find = false;
            for (var name in names) {
                if (args[arg].indexOf(names[name]) == 0) {
                    find = true;
                    break
                }
            }
            if (!find) {
                nargs.push(args[arg])
            }
        }
        return nargs.join("&")
    };
    var put = function (url, key, value) {
        if (value == undefined || value == null) {
            value = ""
        }
        if (url.indexOf("?") != -1 || url.indexOf("=") != -1) {
            return url + "&" + key + "=" + value
        } else {
            return url + "?" + key + "=" + value
        }
    };
    var getArg = function (name) {
        var n = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var t = window.location.search.substr(1).match(n);
        if (t != null) return unescape(t[2]);
        return null
    };
    var changeLink = function (url) {
        var old = url;
        try {
            var id = "";
            if (url.indexOf("#") != -1) {
                id = url.split("#")[1];
                url = url.split("#")[0]
            }
            if (url.indexOf("DSCKID") != -1) {//已经存在 DSCKID
                if (url.indexOf("?") != -1) {
                    //url = 源url +
                    url = url.split("?")[0] + removearg(url.split("?")[1], ["DSCKID", "DSTIMESTAMP"])
                } else {
                    url = removearg(url, ["DSCKID", "DSTIMESTAMP"])
                }
            }
            url = put(url, "DSCKID", dsckId);
            url = put(url, "DSTIMESTAMP", timestampId);
            if (id != "") url += "#" + id;
            return url
        } catch (o) {
            return old
        }
    };
    var Sender = {
        sendVisit: function () {
            var url = "visit.gif?url=" + encodeURIComponent(location.href);
            url = put(url, "taskId", taskId);
            url = put(url, "referer", encodeURIComponent(document.referrer));
            url = put(url, "screen", screem);
            var fromType = storage.getItem("DS_FROM_TYPE");
            url = put(url, "fromType", fromType != null ? fromType : 0);
            url = put(url, "dsId", dsckId);
            url = put(url, "timestampId", timestampId);
            url = put(url, "fromDsId", storage.getItem("REFER_DSCKID"));
            url = put(url, "fromTimestamp", storage.getItem("REFER_DSTIMESTAMP"));
            sendByImage(url)
        },
        sendRepost: function (share) {
            var url = "repost.gif?url=" + encodeURIComponent(location.href);
            url = put(url, "taskId", taskId);
            url = put(url, "share", share);
            url = put(url, "dsId", dsckId);
            url = put(url, "timestampId", timestampId);
            url = put(url, "fromDsId", storage.getItem("REFER_DSCKID"));
            url = put(url, "fromTimestamp", storage.getItem("REFER_DSTIMESTAMP"));
            sendByImage(url)
        },
        sendAuthUserInfo: function (user, appid) {
            if (user == undefined || user == null) return;
            var url = "authInfo.gif?url=" + encodeURIComponent(location.href);
            try {
                url = put(url, "dsId", dsckId);
                url = put(url, "openid", user.openid);
                url = put(url, "nickname", encodeURIComponent(encodeURIComponent(user.nickname)));
                url = put(url, "sex", user.sex);
                url = put(url, "province", encodeURIComponent(encodeURIComponent(user.province)));
                url = put(url, "city", encodeURIComponent(encodeURIComponent(user.city)));
                url = put(url, "country", encodeURIComponent(encodeURIComponent(user.country)));
                url = put(url, "headimgurl", user.headimgurl);
                url = put(url, "privilege", user.privilege ? user.privilege.join(",") : "");
                url = put(url, "unionid", user.unionid);
                url = put(url, "appid", appid);
                url = put(url, "taskId", taskId);
                sendByImage(url)
            } catch (o) {
                console.log(o);
                return
            }
        },
        sendBtnName: function (btnName) {
            var url = "button.gif?url=" + encodeURIComponent(location.href);
            try {
                url = put(url, "taskId", taskId);
                url = put(url, "dsId", dsckId);
                url = put(url, "timestampId", timestampId);
                url = put(url, "btnName", btnName);
                if (btnName.split(":")[0].match(/^page_.*_visit$/)) {
                    pageTimestampId = (new Date).getTime()
                }
                url = put(url, "pageTimestampId", pageTimestampId);
                if (btnName.split(":")[0].match(/^page_.*_unload$/)) {
                    pageTimestampId = ""
                }
                sendByImage(url)
            } catch (t) {
                console.log(t);
                return
            }
        },
        sendUnload: function () {
            var e = "unload.gif?url=" + encodeURIComponent(location.href);
            try {
                e = put(e, "taskId", taskId);
                e = put(e, "dsId", dsckId);
                e = put(e, "timestampId", timestampId);
                sendByImage(e)
            } catch (n) {
                console.log(n);
                return
            }
        }
    };
    var EventManager = {};
    EventManager.event = {};
    EventManager.event.add = function (target, eventname, callback) {
        if (target.addEventListener) {
            target.addEventListener(eventname, callback, false)
        } else {
        }
    };
    if (window.DS == undefined) window.DS = {};
    DS.linkChange = function (url) {
        try {
            return changeLink(url)
        } catch (n) {
        }
    };
    DS.sendRepost = function (url) {
        try {
            Sender.sendRepost(url)
        } catch (n) {
        }
    };
    DS.sendAuthUserInfo = function (user, appid) {
        try {
            Sender.sendAuthUserInfo(user, appid)
        } catch (t) {
        }
    };
    DS.sendBtnName = function (btnname) {
        try {
            Sender.sendBtnName(btnname)
        } catch (n) {
        }
    };
    var readFrom = function () {
        try {
            var url = location.href;
            if (url.indexOf("DSCKID") != -1) {
                var dsckId = getArg("DSCKID");
                var dsTimeStamp = getArg("DSTIMESTAMP");
                storage.setItem("REFER_DSCKID", dsckId);
                storage.setItem("REFER_DSTIMESTAMP", dsTimeStamp)
            }
            if (url.indexOf("from") != -1) {
                var from = getArg("from");
                var fromSource = {
                    singlemessage: 1,
                    groupmessage: 2,
                    timeline: 3
                };
                if (fromSource[from] == undefined) {
                    storage.setItem("DS_FROM_TYPE", 0)
                } else {
                    storage.setItem("DS_FROM_TYPE", fromSource[from])
                }
            }
            if (document.referrer.indexOf("mp.weixin.qq.com") != -1) {
                storage.setItem("DS_FROM_TYPE", 4)
            }
        } catch (i) {
        }
    };
    var Start = function () {
        try {
            if (o == 1) {
                set_cookie.add(dsckId)
            }
            readFrom();
            Sender.sendVisit();
            EventManager.event.add(window, "unload", function () {
                Sender.sendUnload()
            })
        } catch (e) {
        }
    };
    Start()
})();


(function () {
    var e = "https:" == document.location.protocol ? " https://" : " http://";
    var service = e + "tongji.datastory.com.cn/";
    var t = 60 * 30;
    var dsckId = "0b89602d-d7d4-4793-be0a-596c7a89e327";
    var o = "1";
    var taskId = "00d6d418-b2c4-49cf-a31a-06a0dbcdec18";
    var screen = (window.screen.width || 0) + "x" + (window.screen.height || 0);
    var timestampId = (new Date).getTime();
    var d = "";
    var c = {};
    var l = {};
    var rand = function () {
        return parseInt(Math.random() * 1e7)
    };
    var getDomainEx = function (e) {
        var n = /http:\/\/([^\/]+)\//i;
        var t = e.match(n);
        return t[1]
    };
    var getDomain = function (e) {
        if (!e)return "";
        if (e.indexOf("://") != -1)e = e.substr(e.indexOf("://") + 3);
        var n = ["com", "net", "org", "gov", "edu", "mil", "biz", "name", "info", "mobi", "pro", "travel", "museum", "int", "areo", "post", "rec", "im", "cn", "me"];
        var t = e.split(".");
        if (t.length <= 1)return e;
        if (!isNaN(t[t.length - 1]))return e;
        var r = ".com.cn";
        if (e.indexOf(r) != -1) {
            return t[t.length - 3] + "." + t[t.length - 2] + "." + t[t.length - 1]
        }
        var o = 0;
        while (o < n.length && n[o] != t[t.length - 1])o++;
        if (o != n.length)return t[t.length - 2] + "." + t[t.length - 1]; else {
            o = 0;
            while (o < n.length && n[o] != t[t.length - 2])o++;
            if (o == n.length)return t[t.length - 2] + "." + t[t.length - 1]; else return t[t.length - 3] + "." + t[t.length - 2] + "." + t[t.length - 1]
        }
    };
    var sendByImage = function (e) {
        e = putArg(service + e, "rnd", rand());
        var t = new Image;
        t.src = e
    };
    var set_cookie = {
        add: function (e, n) {
            var t = document.createElement("iframe");
            t.src = "http://tongji.datastory.com.cn/set_cookie?dsId=" + e;
            t.style.display = "none";
            var r = document.getElementsByTagName("script")[0];
            r.parentNode.insertBefore(t, r);
            if (t.attachEvent) {
                t.attachEvent("onload", function () {
                    n && n()
                })
            } else {
                t.onload = function () {
                    n && n()
                }
            }
        }
    };
    var cookie = {
        get: function (e) {
            var n = document.cookie.match(new RegExp("(^| )" + e + "=([^;]*)(;|$)"));
            if (n != null)return unescape(n[2]);
            return null
        },
        add: function (e, n, t) {
            var r = e + "=" + n;
            if (t != 0) {
                var o = new Date;
                var a = t * 1e3;
                o.setTime(o.getTime() + a);
                r += "; expires=" + o.toGMTString()
            }
            var i = getDomain(location.hostname);
            if (i != "") {
                r += "; domain=" + i
            }
            r += ";path=/";
            document.cookie = r
        }
    };
    var storage = function () {
        var scookie = {
            setItem: function (e, n) {
                cookie.add(e, n, t)
            },
            getItem: function (e) {
                return cookie.get(e)
            },
            removeItem: function (e) {
                cookie.add(e, "", -1)
            }
        };
        if (sessionStorage) {
            try {
                var argNames = ["REFER_DSCKID", "REFER_DSTIMESTAMP", "DS_FROM_TYPE"];
                var IsFromSelf = function () {
                    if (document.referrer != "") {
                        var refDomain = getDomain(getDomainEx(document.referrer));
                        var locDomain = getDomain(location.hostname);
                        return refDomain == locDomain
                    } else {
                        return false
                    }
                }();
                if (!IsFromSelf) {
                    for (var o = 0; o < argNames.length; o++) {
                        scookie.removeItem(argNames[o])
                    }
                } else {
                    for (var o = 0; o < argNames.length; o++) {
                        var a = sessionStorage.getItem(argNames[o]);
                        var i = scookie.getItem(argNames[o]);
                        if ((a == null || a == "") && (i != null && i != "")) {
                            sessionStorage.setItem(argNames[o], i)
                        }
                    }
                }
            } catch (s) {
            }
        }
        return {
            setItem: function (n, t) {
                sessionStorage && sessionStorage.setItem(n, t);
                scookie.setItem(n, t)
            },
            getItem: function (n) {
                if (sessionStorage) {
                    return sessionStorage.getItem(n)
                } else {
                    return scookie.getItem(n)
                }
            }
        }
    }();
    var putArg = function (e, n, t) {
        if (t == undefined || t == null) {
            t = ""
        }
        if (e.indexOf("?") != -1 || e.indexOf("=") != -1) {
            return e + "&" + n + "=" + t
        } else {
            return e + "?" + n + "=" + t
        }
    };
    var getArg = function (e) {
        var n = new RegExp("(^|&)" + e + "=([^&]*)(&|$)");
        var t = window.location.search.substr(1).match(n);
        if (t != null)return unescape(t[2]);
        return null
    };
    var ChangeLink = function (e) {
        var n = e;
        try {
            var t = "";
            if (e.indexOf("#") != -1) {
                t = e.split("#")[1];
                e = e.split("#")[0]
            }
            if (e.indexOf("DSCKID") != -1) {
                e = e.replace(/DSCKID=[a-zA-Z0-9-]{36}/, "DSCKID=" + dsckId)
            } else {
                e = putArg(e, "DSCKID", dsckId)
            }
            if (e.indexOf("DSTIMESTAMP") != -1) {
                e = e.replace(/DSTIMESTAMP=[a-zA-Z0-9-]{13}/, "DSTIMESTAMP=" + timestampId)
            } else {
                e = putArg(e, "DSTIMESTAMP", timestampId)
            }
            var o = storage.getItem("DS_C_ID");
            if (o != null) {
                var a = o.indexOf("ds_sub_") == 0 ? "" : "ds_sub_";
                o = a + o;
                if (e.indexOf("dskid=") != -1) {
                    e = e.replace(/dskid=[a-zA-Z0-9-_]{1,64}/, "dskid=" + o)
                } else {
                    e = putArg(e, "dskid", o)
                }
            }
            if (t != "")e += "#" + t;
            return e
        } catch (i) {
            return n
        }
    };
    var Sender = {
        sendVisit: function () {
            var e = "visit.gif?url=" + encodeURIComponent(location.href);
            e = putArg(e, "taskId", taskId);
            e = putArg(e, "referer", encodeURIComponent(document.referrer));
            e = putArg(e, "screen", screen);
            var n = storage.getItem("DS_FROM_TYPE");
            e = putArg(e, "fromType", n != null ? n : 0);
            e = putArg(e, "dsId", dsckId);
            e = putArg(e, "timestampId", timestampId);
            e = putArg(e, "fromDsId", storage.getItem("REFER_DSCKID"));
            e = putArg(e, "fromTimestamp", storage.getItem("REFER_DSTIMESTAMP"));
            e = putArg(e, "channelId", storage.getItem("DS_C_ID"));
            sendByImage(e)
        }, sendRepost: function (e) {
            var n = "repost.gif?url=" + encodeURIComponent(location.href);
            n = putArg(n, "taskId", taskId);
            n = putArg(n, "share", e);
            n = putArg(n, "dsId", dsckId);
            n = putArg(n, "timestampId", timestampId);
            n = putArg(n, "fromDsId", storage.getItem("REFER_DSCKID"));
            n = putArg(n, "fromTimestamp", storage.getItem("REFER_DSTIMESTAMP"));
            n = putArg(n, "channelId", storage.getItem("DS_C_ID"));
            sendByImage(n)
        }, sendAuthUserInfo: function (e, n) {
            if (e == undefined || e == null)return;
            var t = "authInfo.gif?url=" + encodeURIComponent(location.href);
            try {
                t = putArg(t, "dsId", dsckId);
                t = putArg(t, "openid", e.openid);
                t = putArg(t, "nickname", encodeURIComponent(encodeURIComponent(e.nickname)));
                t = putArg(t, "sex", e.sex);
                t = putArg(t, "province", encodeURIComponent(encodeURIComponent(e.province)));
                t = putArg(t, "city", encodeURIComponent(encodeURIComponent(e.city)));
                t = putArg(t, "country", encodeURIComponent(encodeURIComponent(e.country)));
                t = putArg(t, "headimgurl", e.headimgurl);
                t = putArg(t, "privilege", e.privilege ? e.privilege.join(",") : "");
                t = putArg(t, "unionid", e.unionid);
                t = putArg(t, "appid", n);
                t = putArg(t, "taskId", taskId);
                sendByImage(t)
            } catch (o) {
                console.log(o);
                return
            }
        }, sendBtnName: function (e) {
            var n = "button.gif?url=" + encodeURIComponent(location.href);
            try {
                n = putArg(n, "taskId", taskId);
                n = putArg(n, "dsId", dsckId);
                n = putArg(n, "timestampId", timestampId);
                if (e.split(":")[0].match(/^page_.*_visit$/)) {
                    var t = e.split(":");
                    c[t[0]] = t[1];
                    return
                } else if (e.split(":")[0].match(/^page_.*_unload$/)) {
                    var t = e.split(":");
                    var o = c[t[0].replace("unload", "visit")];
                    if (o != undefined && o != null && o < t[1]) {
                        var i = t[1] - o;
                        n = putArg(n, "btnName", t[0].replace("unload", "stay") + ":" + i);
                        c[t[0].replace("unload", "visit")] = null
                    } else {
                        return
                    }
                } else if (e.split(":")[0].match(/^page_.*_load_start$/)) {
                    var t = e.split(":");
                    l[t[0]] = t[1];
                    return
                } else if (e.split(":")[0].match(/^page_.*_load_end$/)) {
                    var t = e.split(":");
                    var d = l[t[0].replace("end", "start")];
                    if (d != undefined && d != null && d < t[1]) {
                        var u = t[1] - d;
                        n = putArg(n, "btnName", t[0].replace("_end", "") + ":" + u);
                        l[t[0].replace("end", "start")] = null
                    } else {
                        return
                    }
                } else {
                    n = putArg(n, "btnName", e)
                }
                sendByImage(n)
            } catch (f) {
                console.log(f);
                return
            }
        }, sendPhoneNum: function (e) {
            var n = "phone.gif?url=" + encodeURIComponent(location.href);
            try {
                n = putArg(n, "taskId", taskId);
                n = putArg(n, "dsId", dsckId);
                n = putArg(n, "timestampId", timestampId);
                n = putArg(n, "phoneNum", e);
                sendByImage(n)
            } catch (t) {
                console.log(t);
                return
            }
        }, sendUnload: function () {
            var e = "unload.gif?url=" + encodeURIComponent(location.href);
            try {
                e = putArg(e, "taskId", taskId);
                e = putArg(e, "dsId", dsckId);
                e = putArg(e, "timestampId", timestampId);
                sendByImage(e)
            } catch (n) {
                console.log(n);
                return
            }
        }
    };
    var EventManager = {};
    EventManager.event = {};
    EventManager.event.add = function (e, n, t) {
        if (e.addEventListener) {
            e.addEventListener(n, t, false)
        } else {
        }
    };
    if (window.DS == undefined)window.DS = {};
    DS.linkChange = function (e) {
        try {
            return ChangeLink(e)
        } catch (n) {
        }
    };
    DS.sendRepost = function (e) {
        try {
            Sender.sendRepost(e)
        } catch (n) {
        }
    };
    DS.sendAuthUserInfo = function (e, n) {
        try {
            Sender.sendAuthUserInfo(e, n)
        } catch (t) {
        }
    };
    DS.sendBtnName = function (e) {
        try {
            Sender.sendBtnName(e)
        } catch (n) {
        }
    };
    DS.sendPhoneNum = function (e) {
        try {
            Sender.sendPhoneNum(e)
        } catch (n) {
        }
    };
    var readFrom = function () {
        try {
            var e = location.href;
            if (e.indexOf("DSCKID") != -1) {
                var n = getArg("DSCKID");
                var t = getArg("DSTIMESTAMP");
                storage.setItem("REFER_DSCKID", n);
                storage.setItem("REFER_DSTIMESTAMP", t)
            }
            if (e.indexOf("from") != -1) {
                var r = getArg("from");
                var o = {singlemessage: 1, groupmessage: 2, timeline: 3};
                if (o[r] == undefined) {
                    storage.setItem("DS_FROM_TYPE", 0)
                } else {
                    storage.setItem("DS_FROM_TYPE", o[r])
                }
            }
            if (e.indexOf("dskid=") != -1) {
                var a = getArg("dskid");
                storage.setItem("DS_C_ID", a)
            }
            if (document.referrer.indexOf("mp.weixin.qq.com") != -1) {
                storage.setItem("DS_FROM_TYPE", 4)
            }
        } catch (i) {
        }
    };
    var Start = function () {
        try {
            if (o == 1) {
                set_cookie.add(dsckId)
            }
            readFrom();
            Sender.sendVisit();
            EventManager.event.add(window, "unload", function () {
                Sender.sendUnload()
            })
        } catch (e) {
        }
    };
    Start()
})();