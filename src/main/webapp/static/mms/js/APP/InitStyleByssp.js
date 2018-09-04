function InitFirst(obj) {
    obj.parent("li").siblings().removeClass("open");
    obj.parent("li").siblings().find(".open").removeClass("open");
    obj.parent("li").siblings().find("ul").slideUp(200);
    if (obj.next("ul").length > 0) {
        //竖直左侧导航菜单折叠如果body含有class=nav-collapsed-min，就取消含有二级导航的一级导航的click事件
        if ($("body").hasClass("nav-collapsed-min") || $("body").hasClass("nav-horizontal")) {
            return false;
        }
        if (obj.next("ul").css("display") != "block") {
            obj.parent("li").addClass("open");
            obj.next("ul:first").slideDown(200, function () {
                countnavH();
            });
        }
        else {
            obj.parent("li").removeClass("open");
            obj.parent("li").find(".open").removeClass("open");
            obj.parent("li").find("ul").slideUp(200, function () {
                countnavH();
            });
        }
    }
    else {
        obj.parent("li").addClass("active").siblings().removeClass("active");
        obj.parent("li").siblings().find(".active").removeClass("active");
    }
}

function InitSecond(obj) {
    obj.parent("li").siblings().removeClass("open");
    obj.parent("li").siblings().find(".open").removeClass("open");
    obj.parent("li").siblings().find("ul").slideUp(200);
    if (obj.next("ul").length > 0) {
        //水平方向展示的导航菜单，如果二级菜单中有三级菜单，则取消二级菜单点击事件
        if ($("body").hasClass("nav-horizontal") && obj.next("ul").length > 0) {
            return false;
        }
        //竖直方向折叠导航，如果二级菜单中有三级菜单，则取消二级菜单点击事件
        if ($("body").hasClass("nav-collapsed-min") && obj.next("ul").length > 0) {
            return false;
        }
        if (obj.next("ul").css("display") != "block") {
            obj.parent("li").addClass("open");
            obj.next("ul:first").slideDown(200, function () {
                countnavH();
            });
        }
        else {
            obj.parent("li").removeClass("open");
            obj.parent("li").find("ul").slideUp(200, function () {
                countnavH();
            });
        }
    }
    else {
        obj.parent("li").addClass("active").siblings().removeClass("active");
        obj.parent("li").siblings().find(".active").removeClass("active");
        obj.parents("li").addClass("active");
        obj.parents("li").siblings().removeClass("active");
        obj.parents("li").siblings().find(".active").removeClass("active");
        obj.parents("li").siblings().find(".open").removeClass("open");
        obj.parents("li").siblings().find("ul").slideUp(200);
    }
}

function InitThird(obj) {
    obj.parent("li").addClass("active").siblings().removeClass("active");
    obj.parents("li").addClass("active");
    obj.parents("li").siblings().removeClass("active");
    obj.parents("li").siblings().find(".active").removeClass("active");
    obj.parents("li").siblings().find(".open").removeClass("open");
    obj.parents("li").siblings().find("ul").slideUp(200);
}