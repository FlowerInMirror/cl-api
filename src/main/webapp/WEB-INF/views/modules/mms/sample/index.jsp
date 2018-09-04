<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<html>
<head>
    <link rel="stylesheet" href="${ctxStatic}/css/page-threecolumn.css?${verStatic}" />
    <link rel="stylesheet" href="${ctxStatic}/css/mend-msg.css?${verStatic}" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/darktooltip.min.css?${verStatic}" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/maincamp.css?${verStatic}" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/Goodshelves.css?${verStatic}" type="text/css"/>
</head>

<%--系统分发:页面独立脚本(定义此位置原因:避免Sitemesh装饰事获取,引起页面脚本或样式冲突)--%>
<link rel="stylesheet" href="${ctxStatic}/css/rxui1.0.2.min.css?${verStatic}">
<link rel="stylesheet" href="${ctxStatic}/css/frame.min.css?${verStatic}">
<link rel="stylesheet" href="${ctxStatic}/css/mend-msg.css?${verStatic}" type="text/css" >
<link href="${ctxStatic}/css/page-library.css?${verStatic}" rel="stylesheet"/>
<link href="${ctxStatic}/css/page-threecolumn.css?${verStatic}" rel="stylesheet"/>
<link href="${ctxStatic}/css/page-childlibrary.css?${verStatic}" rel="stylesheet"/>
<link href="${ctxStatic}/css/page-pbank.css?${verStatic}" rel="stylesheet"/>
<link href="${ctxStatic}/css/page-itemed.css?${verStatic}" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/css/fm.tagator.jquery.css?${verStatic}" rel="stylesheet"/>
<link href="${ctxStatic}/css/page-material.css?${verStatic}" rel="stylesheet"/>
<link href="${ctxStatic}/css/temporary.css?${verStatic}" rel="stylesheet"/>
<script src="${ctxStatic}/js/jquery-1.11.3.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
<script src="${ctxStatic}/Scripts/My97DatePicker/WdatePicker.js?${verStatic}"></script>
<script src="${ctxStatic}/js/jquery.rippleria.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
<script src="${ctxStatic}/js/rxuedv2.0.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
<script src="${ctxStatic}/js/frame.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
<script src="${ctxStatic}/js/jquery.pagination.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--分页控件-->
<script src="${ctxStatic}/js/New_jquery.mousewheel.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--滚轮滚动缩放-->
<script src="${ctxStatic}/js/jquery.rotate.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--图片旋转-->
<script src="${ctxStatic}/js/jquery.session.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--缓存-->
<%--basePath: 项目基础路径, ctxStatic: 项目静态资源路径,gcApiSite: 工程接口服务站点 --%>
<script type="text/javascript">var basePath = '${basePath}',ctxStatic='${ctxStatic}',gcApiSite = '${gcApiSite}';</script>

<body>
<div class="wrapper clearfix pt10 pl10 pr10">
    <%--左侧(一段列表为主)--%>
    <div class="tc-left pr10 fl" id="sampleleft"></div>
    <%--主视图(含:二段/三段)--%>
    <div id="samplemain"></div>
</div>
<!--隐藏域-->
<input type="hidden" id="hidUserNo" value="${user.cardNumber}"/><%-- 用户卡号 --%>
<input type="hidden" id="hidUserArea" value="${user.u_diqu}"/><%-- 用户地区 --%>
<input type="hidden" id="hidCityID" value=""/><%-- 城市id --%>

<%--标签打印--%>
<iframe id="labelPrint" width="100%" height="100%" class="hide"></iframe>

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
    <%--样相关JS--%>
    <script src="${ctxStatic}/js/sample.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script>
            function countLeft() {
                //左侧JS
                var oheight1 = 10;
                for (var i = 0; i < $(".jcha1").length; i++) {
                    oheight1 += $(".jcha1").eq(i).outerHeight(true);
                }
                rxued.init.countH($(".scroll-content1"), oheight1, function () {
                    $(".scroll-content1").slimScroll({
                        height: $(".scroll-content1").eq(0).height(),
                        borderRadius: "0px"
                    });
                });
                $(".scroll-content1").parent(".slimScrollDiv").height($(".scroll-content1").height());
                var oheight2 = 10;
                for (var i = 0; i < $(".jcha2").length; i++) {
                    oheight2 += $(".jcha2").eq(i).outerHeight(true);
                }
                rxued.init.countH($(".scroll-content2"), oheight2, function () {
                    $(".scroll-content2").slimScroll({
                        height: $(".scroll-content2").eq(0).height(),
                        borderRadius: "0px"
                    });
                });
                $(".scroll-content2").parent(".slimScrollDiv").height($(".scroll-content2").height());
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
                $("#sampleleft").load(basePath+"/public-web/sample/one-list",function(){
                    $(".setup_nav").click(); // 折叠右侧主导航
                    //判断用户登录的地区左侧列表展示对应地区的数据
//                    var hidUserArea=$("#hidUserArea").val();
//                    //39为集团
//                    if(hidUserArea!='39')
//                    {
//                        $(".uiTab1 li:eq(0)").hide();
//                        $(".uiTab1 li:eq(1)").show();
//                        $(".daily_title").hide().eq(1).show();
//                        $(".contentbox").hide().eq(1).show();
//                        $.ajax({
//                            url:  gcApiSite + 'APPSuppliers/MaterialShelfStatistic?AreaId='+hidUserArea,
//                            type: 'post',
//                            dataType: 'json',
//                            success: function (res) {
//                                if (res.StatusCode == 0){
//                                    // 实例赋值
//                                    _this.sampleOneList = res.Body;
//                                    _this.sampleOneListLength = res.Body.length;
//                                    setTimeout(function(){
//                                        // 默认点击第一个
//                                        $("#tabSampleTreeList tr:eq(0)").click();
//                                    },200)
//                                } else {alert(res.statusMsg);};
//                            },
//                            error: function (err) {alert('操作出错！');}
//                        });
//                    }

                    countLeft();
                    // 加载二段主内容区
                    $("#samplemain").load(basePath+"/public-web/sample/main",function(){
                        countRight();
                    });
                });
            });
        </script>

</footer-script>
</body>
</html>
