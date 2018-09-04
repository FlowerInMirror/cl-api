<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!--弹出层-->
<script src="${ctxStatic}/js/jquery.rippleria.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
<script src="${ctxStatic}/js/rxuedv2.0.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
<script src="${ctxStatic}/js/frame.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
<script src="${ctxStatic}/js/jquery.pagination.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--分页控件-->
<script src="${ctxStatic}/js/New_jquery.mousewheel.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--滚轮滚动缩放-->
<script src="${ctxStatic}/js/jquery.rotate.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--图片旋转-->
<script src="${ctxStatic}/js/jquery.session.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--缓存-->
<script>
    //iframe关闭
    function closeevent() {
        $(".if-cs,.iframeTab,.closeevent,.closeIfrTab,.iframeTab_01,.closeIfrTab_01,.iframe_list,iframe_renshi").addClass("hide");

    }
    function close_list() {
        $(".iframe_list").hide();
        $(".iframe_list_01").hide();

    }
    function close_check() {
        $(".iframe_check").hide();
        $(".iframe_check_01").hide();

    }
    function close_renshi() {
        $(".iframe_renshi").hide();
        $(".iframe_renshi_01").hide();
    }


        //事件执行弹层位置计算
    function countLayoutFrame() {
        var eventLeft = $(".eventDiv").position().left;
        var eventWidth = $(".eventDiv").outerWidth();
        var eventRight = $("#main_header").outerWidth() - $(".eventDiv").position().left - eventWidth - $(".closeevent").outerWidth();
        $(".if-cs").css("right", eventRight);
        $(".closeevent").css("right", eventRight + $(".closeevent").outerWidth() + 5);
    }

    $(function () {
        loadRuleData();
        countLayoutFrame(); //事件执行弹层位置计算
        $(window).resize(function () {
            countLayoutFrame(); //事件执行弹层位置计算
        });

        if ($.session.get('OrderMenu1') === '2') {
            $("#" + $.session.get('OrderMenu3')).closest(".sub-2-nav").parent('li').addClass("open");
            $("#" + $.session.get('OrderMenu3')).parent('ul').show();
            $("#" + $.session.get('OrderMenu3')).addClass("active");
        } else {
            $("#" + $.session.get('OrderMenu3')).closest(".sub-2-nav").parent('li').addClass("open");
            $("#" + $.session.get('OrderMenu3')).closest(".sub-2-nav").parent('li').parent("li").addClass("open");
            $("#" + $.session.get('OrderMenu3')).closest(".sub-2-nav").parent('li').find("ul").show();
            $("#" + $.session.get('OrderMenu3')).addClass("active");
        }

        var Menu2Sort = Number($.session.get('OrderMenu2'));
        if (Menu2Sort >= 0) {
            //竖向菜单
            $(".rolechange p").removeClass("Pacitve").eq(Menu2Sort).addClass("Pacitve");

            $(".nav-ul").hide().eq(Menu2Sort).show();
        }

        //主导航切换
        $(".rolechange p").each(function (i) {
            $(this).click(function () {
                $(this).addClass("Pacitve").siblings().removeClass("Pacitve");
                $(".list-group .role-p").eq(i).addClass("role-pactive").siblings().removeClass("role-pactive");
                $(".nav-ul").hide().eq(i).show();
            })
        });
        $(".list-group .role-p").each(function (i) {
            $(this).click(function () {
                $(".rolechange p").eq(i).addClass("Pacitve").siblings().removeClass("Pacitve");
                $(this).addClass("role-pactive").siblings().removeClass("role-pactive");
                $(".nav-ul").hide().eq(i).show();
            })
        });

        //人事|事件？？？？？？
        $(".eventDiv").click(function () {
            switch ($(this).attr("data-value")) {
                case "1":
                    toggleIframeDiv($(".if-cs"));
                    break;
                case "2":
                    toggleIframeDiv($(".iframeTab"));
                    break;
            }
        });

//        $.get('http://api.p.rx/api/EventPlatform/GetExecuteNum?card=00007983').then(function (res) {
//            $(".eventDiv[data-value=1] span.eventCount").html(res[0].totExecute);
//        }, 'html');

    });

    function getMenu(menu1, menu2, menu3) {
        //将点击的菜单信息,存入cookie
        $.session.set('OrderMenu1', menu1);
        $.session.set('OrderMenu2', menu2);
        $.session.set('OrderMenu3', menu3);
    }

    function logout() {
        //清除菜单数据
        $.session.set('OrderMenu1', "");
        $.session.set('OrderMenu2', "");
        $.session.set('OrderMenu3', "");

        window.location.href = "/";
    }

    //池子数据加载
    function loadRuleData() {
        //池子数据请求
//        $.ajax({
//            url: "http://api.idc.com/command/complexTable?campId=75&region=39&cardNumber=00007983",
//            type: "get",
//            dataType: "json",
//            data: {},
//            async: false,
//            success: function (result) {
//                $(".iframeTab").attr("src", result.dataURL);
//                $(".s_tit").text(result.name);
//            }
//        });
        //事件执行
//        $(".if-cs").attr("src", "http://e.p.rx/Event/Launch/IframeZ?card=00007983");
    }

    //事件弹层toggle
    function toggleIframeDiv(obj) {
        switch ($(obj).attr("data-value")) {
            case "1":
                $(".if-cs").toggleClass("hide");
                $('.closeevent').toggleClass('hide');
                $(".iframeTab").addClass("hide");
                $('.closeIfrTab').addClass("hide");
                break;
            case "2":
                $(".if-cs").addClass("hide");
                $('.closeevent').addClass("hide");
                $(".iframeTab").toggleClass("hide");
                $('.closeIfrTab').toggleClass('hide');
                break;
        }
    }
</script>
