<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${ctxStatic}/css/page-threecolumn.css?${verStatic}" />
        <link rel="stylesheet" href="${ctxStatic}/css/mend-msg.css?${verStatic}" type="text/css"/>
        <link rel="stylesheet" href="${ctxStatic}/css/darktooltip.min.css?${verStatic}" type="text/css"/>
        <link rel="stylesheet" href="${ctxStatic}/css/maincamp.css?${verStatic}" type="text/css"/>
    </head>



    <body>
        <div class="wrapper clearfix pt10 pl10 pr10">
            <%--左侧(一段列表为主)--%>
            <div class="tc-left pr10 fl" id="majorleft"></div>
            <%--主视图(含:二段/三段)--%>
            <div id="majormain"></div>
        </div>

        <!--添加类别-->
        <div class="CategoryAdd pa20 hide">
            <div class="mb20 pl20">
                <span title="宽度" class="cLightGray pr8 justify_span justify_span_w80 pl10">名称：</span>
                <input type="text" data-input="true" class="width180">
            </div>
            <div class=" pl20">
                <span title="宽度" class="cLightGray pr8 justify_span justify_span_w80 pl10">序号：</span>
                <input type="text" data-input="true" class="width180">
            </div>
        </div>

        <!--隐藏域-->
        <input type="hidden" id="hidUserNo" value="${user.cardNumber}"/><%-- 用户卡号 --%>
        <input type="hidden" id="hidZYTreeTwoID" value=""/><%-- 主营二级科目ID --%>
        <input type="hidden" id="hidZYTypeID" value=""/><%-- 主营类别ID --%>
        <input type="hidden" id="hidZYTypeNmae" value=""/><%-- 主营类别名称 --%>

        <%--页面脚本开始--%>
        <footer-script>

            <%--VUE相关JS--%>
            <script src="${ctxStatic}/js/vue.js?${verStatic}"></script>
            <script src="${ctxStatic}/js/vue/mms/commons/filters.js?${verStatic}" type="text/javascript" charset="utf-8"/>

            <%--系统相关--%>
            <script type="text/javascript" src="${ctxStatic}/js/jquery.rippleria.min.js?${verStatic}"></script>
            <script src="${ctxStatic}/js/New_jquery.mousewheel.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
            <script src="${ctxStatic}/js/jquery.rotate.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
            <script src="${ctxStatic}/js/layer.js?${verStatic}" type="text/javascript" charset="utf-8"></script>

            <%--主营相关JS--%>
            <script src="${ctxStatic}/js/major.js?${verStatic}" type="text/javascript" charset="utf-8"></script>

            <script>
                function countLeft() {
                    //左侧JS
                    var oheight5_new = 10;
                    for (var i = 0; i < $(".jcha5_new").length; i++) {
                        oheight5_new += $(".jcha5_new").eq(i).outerHeight(true);
                    }
                    rxued.init.countH($(".scroll-content"), oheight5_new, function () {
                        $(".scroll-content").slimScroll({
                            height: $(".scroll-content").eq(0).height(),
                            borderRadius: "0px"
                        });
                    });
                    $(".scroll-content").parent(".slimScrollDiv").height($(".scroll-content").height());

                }
                function countMiddle() {
                    var theight = $("body").height() - $("#topnav").height() - $(".j_uiTab9").outerHeight() - 11;
                    $("#j-tc-center-content").height(theight).slimScroll({
                        height: theight,
                        borderRadius: "0px"
                    });
                    $("#j-tc-center-content").parents(".slimScrollDiv").eq(0).height(theight);
                }

                function countRight() {
                    //右侧JS
                    var rightHeight = $("body").height() - $("#topnav").outerHeight() - $(".j-tc-righttop-tab").outerHeight() - $(".tc-right-bottom").outerHeight() - 10;
                    $(".tc-right-top,#j-visit").height(rightHeight);
                    $(".tc-right-top,#j-visit").parent(".slimScrollDiv").height(rightHeight);
                }


                $(function() {
                    // 页面加载1段
                    $("#majorleft").load(basePath+"/public-web/major/one-list",function(){
                        $(".setup_nav").click(); // 折叠右侧主导航

                        countLeft();
                        // 加载二段主内容区
                        $("#majormain").load(basePath+"/public-web/major/main",function(){});
                    });
                });
            </script>
        </footer-script>
    </body>
</html>
