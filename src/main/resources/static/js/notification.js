/**
 * @author shonve
 * @version 0.1
 * @createDate 2015-06-17 15:28:11
 * @description 浏览器桌面通知插件
 * @modifier
 * @modifyDate
 */
(function ($) {
    $.fn.XNotify = function (options) {
        var defaults = {
            icon: "",// 显示的图标
            title: "title",// 通知的标题
            content: "content",// 显示的内容
            tag: "",// // 通知框ID，相同id可替换，而不是出现新的通知框
            lang: "",// 语言
            dir: "auto"// 文字方向
        }, _this = this;
        this.shonve = $.extend(defaults, options);
        if (!("Notification" in window) && !window.webkitNotifications) {
            alert("非常抱歉，您当前浏览器不支持桌面提醒功能！");
        } else {
            if (window.webkitNotifications) {// chrome老版本，目前高版本好像不支持了？
                oldVersionChrome();
            } else if ("Notification" in window) {// 新版本chrome以及html5
                /*
                 * Notification.permission 有三种状态
                 * default：未设置过为这个状态，通过Notification.requestPermission()可以询问用户是否允许通知
                 * granted：用户点击允许后的状态 denied： 用户点击拒绝后的状态，通知框不可用
                 */
                if (Notification.permission === "granted") {
                    var notification = new Notification(_this.shonve.title,
                        arrayDate());
                    //}else if (Notification.permission !== 'denied') {
                } else if (Notification.permission === 'default') {// 如果没权限，则请求权限

                    Notification.requestPermission(function (permission) {
                        if (!('permission' in Notification)) {
                            Notification.permission = permission;
                        }
                        if (permission === "granted") {// 如果接受请求
                            var notification = new Notification(
                                _this.shonve.title, arrayDate());
                        }
                    });
                }
            }
        }
        function oldVersionChrome() {
            // 该方法返回0, 1,
            // 2三个值，0代表PERMISSION_ALLOWED,即’允许’；1代表PERMISSION_NOT_ALLOWED，即不允许；2代表PERMISSION_DENIED，即拒绝
            if (window.webkitNotifications.checkPermission() == 0) {
                // 创建一个简单的文本通知：
                var notif = window.webkitNotifications.createNotification(
                    _this.shonve.icon, _this.shonve.title,
                    _this.shonve.content);
                notif.display = function () {
                    setTimeout(function () {
                        event.currentTarget.cancel();
                    }, 10000);
                };
                notif.onerror = function () {
                };
                notif.onclose = function () {
                };
                // 下面是定义点击事件，类似地还可定义其它事件
                notify.onclick = function () {
                    window.focus();
                    this.cancel();
                };
                notif.replaceId = 'Meteoric';
                notif.show();
                // 或者创建 HTML 通知：
                // var tempPopup =
                // window.webkitNotifications.createHTMLNotification(["http://www.baidu.com/",
                // "http://www.soso.com"][Math.random() >= 0.5 ? 0 : 1]);
                // tempPopup.replaceId = "Meteoric_cry";
                // tempPopup.show();
            } else {
                // 调用该方法将会在浏览器的信息栏弹出一个是否允许桌面通知的提醒，该方法只能由用户主动事件触发，如click 或 mouse
                // over，也就是说你不能在document.ready里面直接调用该方法。
                window.webkitNotifications.requestPermission(oldVersionChrome);
            }
        }

        function arrayDate() {
            var requestDate = {
                "icon": _this.shonve.icon,
                "body": _this.shonve.content,
                "tag": _this.shonve.tag,
                "lang": _this.shonve.lang,
                "dir": _this.shonve.dir
            }
            return requestDate;
        }
    };
})(jQuery);