/*
(function() {
	var e = 60 * 30;
	var n = function(e) {
		var n = new RegExp("(^|&)" + e + "=([^&]*)(&|$)");
		var t = window.location.search.substr(1).match(n);
		if (t != null) return unescape(t[2]);
		return null
	};
	var t = function(e) {
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
	var r = {
		get: function(e) {
			var n = document.cookie.match(new RegExp("(^| )" + e + "=([^;]*)(;|$)"));
			if (n != null) return unescape(n[2]);
			return null
		},
		add: function(e, n, r) {
			var i = e + "=" + n;
			if (r != 0) {
				var o = new Date;
				var a = r * 1e3;
				o.setTime(o.getTime() + a);
				i += "; expires=" + o.toGMTString()
			}
			var s = t(location.hostname);
			if (s != "") {
				i += "; domain=" + s
			}
			i += ";path=/";
			document.cookie = i
		}
	};
	var i = function() {
		if (sessionStorage) {
			return sessionStorage
		} else {
			var n = {
				setItem: function(n, t) {
					r.add(n, t, e)
				},
				getItem: function(e) {
					return r.get(e)
				}
			};
			return n
		}
	} ();
	var o = function() {
		var e = location.href;
		if (e.indexOf("DSCKID") != -1) {
			var t = n("DSCKID");
			var r = n("DSTIMESTAMP");
			i.setItem("REFER_DSCKID", t);
			i.setItem("REFER_DSTIMESTAMP", r)
		}
		if (e.indexOf("from") != -1) {
			var o = n("from");
			var a = {
				singlemessage: 1,
				groupmessage: 2,
				timeline: 3
			};
			if (a[o] == undefined) {
				i.setItem("DS_FROM_TYPE", 0)
			} else {
				i.setItem("DS_FROM_TYPE", a[o])
			}
		}
		if (document.referrer.indexOf("mp.weixin.qq.com") != -1) {
			i.setItem("DS_FROM_TYPE", 4)
		}
	};
	var a = function() {
		var e = "";
		try {
			e = document.getElementById("DS_PRE_JS").src.split("?")[1].split("=")[1]
		} catch(n) {
			console.log("please read DataStory api doc")
		}
		var t = document.createElement("script");
		t.src = document.location.protocol + "//tongji.datastory.com.cn/ds.js?dsTid=" + e;
		var r = document.getElementsByTagName("script")[0];
		r.parentNode.insertBefore(t, r)
	};
	var s = function() {
		try {
			if (window.DS == undefined) window.DS = {};
			DS.ready = function(e) {
				var n = function() {
					if (DS.linkChange == undefined || DS.sendRepost == undefined) {
						setTimeout(n, 500)
					} else {
						try {
							e()
						} catch(t) {}
					}
				};
				n()
			};
			o();
			a()
		} catch(e) {}
	};
	s()
})();

(function() {
	var e = "https:" == document.location.protocol ? " https://" : " http://";
	var n = e + "tongji.datastory.com.cn/";
	var t = 60 * 30;
	var r = "bc1ed049-0fc3-4f90-a37a-7555c5bb2df8";
	var o = "1";
	var i = "";
	var a = (window.screen.width || 0) + "x" + (window.screen.height || 0);
	var d = (new Date).getTime();
	var s = "";
	var c = function() {
		return parseInt(Math.random() * 1e7)
	};
	var f = function(e) {
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
	var u = function(e) {
		e = p(n + e, "rnd", c());
		var t = new Image;
		t.src = e
	};
	var m = {
		add: function(e, n) {
			var t = document.createElement("iframe");
			t.src = "http://tongji.datastory.com.cn/set_cookie?dsId=" + e;
			t.style.display = "none";
			var r = document.getElementsByTagName("script")[0];
			r.parentNode.insertBefore(t, r);
			if (t.attachEvent) {
				t.attachEvent("onload", function() {
					n && n()
				})
			} else {
				t.onload = function() {
					n && n()
				}
			}
		}
	};
	var l = {
		get: function(e) {
			var n = document.cookie.match(new RegExp("(^| )" + e + "=([^;]*)(;|$)"));
			if (n != null) return unescape(n[2]);
			return null
		},
		add: function(e, n, t) {
			var r = e + "=" + n;
			if (t != 0) {
				var o = new Date;
				var i = t * 1e3;
				o.setTime(o.getTime() + i);
				r += "; expires=" + o.toGMTString()
			}
			var a = f(location.hostname);
			if (a != "") {
				r += "; domain=" + a
			}
			r += ";path=/";
			document.cookie = r
		}
	};
	var v = function() {
		if (sessionStorage) {
			return sessionStorage
		} else {
			var e = {
				setItem: function(e, n) {
					l.add(e, n, t)
				},
				getItem: function(e) {
					return l.get(e)
				}
			};
			return e
		}
	}();
	var I = function(e, n) {
		var t = e.split("&");
		var r = [];
		for (var o in t) {
			var i = false;
			for (var a in n) {
				if (t[o].indexOf(n[a]) == 0) {
					i = true;
					break
				}
			}
			if (!i) {
				r.push(t[o])
			}
		}
		return r.join("&")
	};
	var p = function(e, n, t) {
		if (t == undefined || t == null) {
			t = ""
		}
		if (e.indexOf("?") != -1 || e.indexOf("=") != -1) {
			return e + "&" + n + "=" + t
		} else {
			return e + "?" + n + "=" + t
		}
	};
	var g = function(e) {
		var n = new RegExp("(^|&)" + e + "=([^&]*)(&|$)");
		var t = window.location.search.substr(1).match(n);
		if (t != null) return unescape(t[2]);
		return null
	};
	var h = function(e) {
		var n = e;
		try {
			var t = "";
			if (e.indexOf("#") != -1) {
				t = e.split("#")[1];
				e = e.split("#")[0]
			}
			if (e.indexOf("DSCKID") != -1) {
				if (e.indexOf("?") != -1) {
					e = e.split("?")[0] + I(e.split("?")[1], ["DSCKID", "DSTIMESTAMP"])
				} else {
					e = I(e, ["DSCKID", "DSTIMESTAMP"])
				}
			}
			e = p(e, "DSCKID", r);
			e = p(e, "DSTIMESTAMP", d);
			if (t != "") e += "#" + t;
			return e
		} catch (o) {
			return n
		}
	};
	var D = {
		sendVisit: function() {
			var e = "visit.gif?url=" + encodeURIComponent(location.href);
			e = p(e, "taskId", i);
			e = p(e, "referer", encodeURIComponent(document.referrer));
			e = p(e, "screen", a);
			var n = v.getItem("DS_FROM_TYPE");
			e = p(e, "fromType", n != null ? n : 0);
			e = p(e, "dsId", r);
			e = p(e, "timestampId", d);
			e = p(e, "fromDsId", v.getItem("REFER_DSCKID"));
			e = p(e, "fromTimestamp", v.getItem("REFER_DSTIMESTAMP"));
			u(e)
		},
		sendRepost: function(e) {
			var n = "repost.gif?url=" + encodeURIComponent(location.href);
			n = p(n, "taskId", i);
			n = p(n, "share", e);
			n = p(n, "dsId", r);
			n = p(n, "timestampId", d);
			n = p(n, "fromDsId", v.getItem("REFER_DSCKID"));
			n = p(n, "fromTimestamp", v.getItem("REFER_DSTIMESTAMP"));
			u(n)
		},
		sendAuthUserInfo: function(e, n) {
			if (e == undefined || e == null) return;
			var t = "authInfo.gif?url=" + encodeURIComponent(location.href);
			try {
				t = p(t, "dsId", r);
				t = p(t, "openid", e.openid);
				t = p(t, "nickname", encodeURIComponent(encodeURIComponent(e.nickname)));
				t = p(t, "sex", e.sex);
				t = p(t, "province", encodeURIComponent(encodeURIComponent(e.province)));
				t = p(t, "city", encodeURIComponent(encodeURIComponent(e.city)));
				t = p(t, "country", encodeURIComponent(encodeURIComponent(e.country)));
				t = p(t, "headimgurl", e.headimgurl);
				t = p(t, "privilege", e.privilege ? e.privilege.join(",") : "");
				t = p(t, "unionid", e.unionid);
				t = p(t, "appid", n);
				t = p(t, "taskId", i);
				u(t)
			} catch (o) {
				console.log(o);
				return
			}
		},
		sendBtnName: function(e) {
			var n = "button.gif?url=" + encodeURIComponent(location.href);
			try {
				n = p(n, "taskId", i);
				n = p(n, "dsId", r);
				n = p(n, "timestampId", d);
				n = p(n, "btnName", e);
				if (e.split(":")[0].match(/^page_.*_visit$/)) {
					s = (new Date).getTime()
				}
				n = p(n, "pageTimestampId", s);
				if (e.split(":")[0].match(/^page_.*_unload$/)) {
					s = ""
				}
				u(n)
			} catch (t) {
				console.log(t);
				return
			}
		},
		sendUnload: function() {
			var e = "unload.gif?url=" + encodeURIComponent(location.href);
			try {
				e = p(e, "taskId", i);
				e = p(e, "dsId", r);
				e = p(e, "timestampId", d);
				u(e)
			} catch (n) {
				console.log(n);
				return
			}
		}
	};
	var S = {};
	S.event = {};
	S.event.add = function(e, n, t) {
		if (e.addEventListener) {
			e.addEventListener(n, t, false)
		} else {}
	};
	if (window.DS == undefined) window.DS = {};
	DS.linkChange = function(e) {
		try {
			return h(e)
		} catch (n) {}
	};
	DS.sendRepost = function(e) {
		try {
			D.sendRepost(e)
		} catch (n) {}
	};
	DS.sendAuthUserInfo = function(e, n) {
		try {
			D.sendAuthUserInfo(e, n)
		} catch (t) {}
	};
	DS.sendBtnName = function(e) {
		try {
			D.sendBtnName(e)
		} catch (n) {}
	};
	var R = function() {
		try {
			var e = location.href;
			if (e.indexOf("DSCKID") != -1) {
				var n = g("DSCKID");
				var t = g("DSTIMESTAMP");
				v.setItem("REFER_DSCKID", n);
				v.setItem("REFER_DSTIMESTAMP", t)
			}
			if (e.indexOf("from") != -1) {
				var r = g("from");
				var o = {
					singlemessage: 1,
					groupmessage: 2,
					timeline: 3
				};
				if (o[r] == undefined) {
					v.setItem("DS_FROM_TYPE", 0)
				} else {
					v.setItem("DS_FROM_TYPE", o[r])
				}
			}
			if (document.referrer.indexOf("mp.weixin.qq.com") != -1) {
				v.setItem("DS_FROM_TYPE", 4)
			}
		} catch (i) {}
	};
	var E = function() {
		try {
			if (o == 1) {
				m.add(r)
			}
			R();
			D.sendVisit();
			S.event.add(window, "unload", function() {
				D.sendUnload()
			})
		} catch (e) {}
	};
	E()
})();
*/