(function () {
    /**
     * 全局工具对象。
     * @param win {Window} 窗口对象。
     * @param doc {Document} 文档对象。
     */
    var Global = (function (win, doc) {
        return {
            window: win,
            document: doc,
            navigator: window.navigator,
            location: window.location,
            screen: win.screen,
            empty: 'empty',
            /**
             * 定时执行指定的回调函数。
             * @param callback {Function} 需要执行的回调函数。
             * @param delay {Int} 延时的间隔（以毫秒计）。
             */
            setTimeout: function (callback, delay) {
                setTimeout(callback, delay);
            },
            /**
             * 用户代理头的字符串表示(就是包括浏览器版本信息等的字符串)是否包含指定的字符串。
             * @param key {String} 指定的字符串。
             * @return {Boolean} 是否包含。
             */
            contains: function (key) {
                return navigator.userAgent[_str_indexOf](key) >= 0;
            },
            setCookie: function (name, value, days) {
                var Days = days || 30;
                var exp = new Date();
                exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
                document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
            },
            getCookie: function (name) {
                var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
                if (arr = document.cookie.match(reg))
                    return unescape(arr[2]);
                else
                    return null;
            },
            delCookie: function (name) {
                var exp = new Date();
                exp.setTime(exp.getTime() - 1);
                var cval = getCookie(name);
                if (cval != null)
                    document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
            },
            /**
             * 处理网页来源页面的URL地址。
             * @return {String} 处理过的URL地址。
             */
            processSource: function (source) {
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
            }
        };
    })(window, document);
    var Detect = (function () {
        var screen = Global.screen,
            location = Global.location,
            navigator = Global.navigator,
            empty = Global.empty,
            document = Global.document;
        return {
            referrer: function () {
                return document ? document.referrer : empty;
            },
            href: function () {
                return location ? location.href : empty;
            },
            javaEnabled: function () {
                return navigator.javaEnabled && navigator.javaEnabled();
            },
            screen: function () {
                return screen ? screen.width + 'x' + screen.height : empty;
            },
            colorDepth: function () {
                return screen ? screen.colorDepth : empty;
            },
            charset: function () {
                return document ? document.characterSet : empty;
            },
            language: function () {
                return navigator ? navigator.language : empty;
            },
            /**
             * 获取浏览器FLASH播放器的版本号。
             * @return {String} FLASH播放器的版本号。
             */
            flashVersion: function () {
                var _active_x_object,
                    _flash,
                    _version, empty = Global.empty;
                _flash = "ShockwaveFlash";
                var _version_key = "$version",
                    navigator = Global.navigator;
                if ((navigator = navigator ? navigator.plugins : undefined) && navigator.length > 0) {
                    for (var i = 0; i < navigator.length && !_version; i++) {
                        _flash = navigator[i];
                        if (_flash.name.indexOf("Shockwave Flash") > -1) {
                            _version = _flash.description.split("Shockwave Flash ")[1];
                        }
                    }
                } else {
                    _flash = _flash + "." + _flash;
                    try {
                        _active_x_object = new ActiveXObject(_flash + ".7");
                        _version = _active_x_object.GetVariable(_version_key);
                    } catch (ex) {
                    }
                    if (!_version) {
                        try {
                            _active_x_object = new ActiveXObject(_flash + ".6");
                            _version = "WIN 6,0,21,0";
                            _active_x_object.$_version = "always";
                            _version = _active_x_object.GetVariable(_version_key);
                        } catch (ex) {
                        }
                    }
                    if (!_version) {
                        try {
                            _active_x_object = new ActiveXObject(_flash);
                            _version = _active_x_object.GetVariable(_version_key);
                        } catch (ex) {
                        }
                    }
                    if (_version) {
                        _version = _version.split(" ")[1].split(",");
                        _version = _version[0] + "." + _version[1] + " r" + _version[2];
                    }
                }
                return _version ? _version : empty;
            }
        };
    })();
})();