<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<html>
<head>

    <title>工程经理.材料</title>
    <link rel="stylesheet" href="${ctxStatic}/css/rxui1.0.2.min.css?${verStatic}">
    <link rel="stylesheet" href="${ctxStatic}/css/frame.min.css?${verStatic}">
    <link rel="stylesheet" href="${ctxStatic}/css/mend-msg.css?${verStatic}" type="text/css" >
    <script src="${ctxStatic}/js/jquery-1.11.3.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <link href="${ctxStatic}/css/page-library.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-threecolumn.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-childlibrary.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-pbank.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-itemed.css?${verStatic}" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/css/fm.tagator.jquery.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-material.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/temporary.css?${verStatic}" rel="stylesheet"/>
    <script src="${ctxStatic}/Scripts/My97DatePicker/WdatePicker.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/layer.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/vue.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/vue/mms/commons/filters.js?${verStatic}" type="text/javascript" charset="utf-8"/>
    <script src="${ctxStatic}/js/jquery.qrcode.min.js?${verStatic}"></script>

    <%--basePath: 项目基础路径, ctxStatic: 项目静态资源路径,gcApiSite: 工程接口服务站点 --%>
    <script type="text/javascript">var basePath = '${basePath}',ctxStatic='${ctxStatic}',gcApiSite = '${gcApiSite}'</script>

    <%--子库相关JS--%>
    <script src="${ctxStatic}/js/Views/Material_Left.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/Views/Material_Center.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/Views/Material_Right.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/Views/Material_Load.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/sublibrary.js?${verStatic}"></script>
    <style>
        body {
            font-family: "Microsoft YaHei", "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: 12px;
            color: #666;
            line-height: 18px;
        }
    </style>
</head>
<body>
    <%--主视图--%>
    <div class="wrapper clearfix pt10 pl10 pr10">
        <%--左侧--%>
        <div class="tc-left pr10 fl" id="engineeringLeft"></div>
        <%--主(中心与右侧)--%>
        <div class="div_main_data" id="divMaterialDetail"></div>
    </div>

    <%--页面iframe--%>
    <iframe class="iframe_list hide" src=""></iframe>
    <div class="closeIfrTab_01 iframe_list_01 hide" onclick="close_list()">×</div>

    <%--页面隐藏域--%>
    <input type="hidden" id="hidTreeTwoID" value=""/><%-- 二级科目ID --%>
    <%--<input type="hidden" id="hidCityID" value="${user.u_diqu}"/>--%>
    <input type="hidden" id="hidCityID" value="12"/>
    <input type="hidden" id="hidTreeID" value=""/>
    <input type="hidden" id="hidMark" value="0"/>
    <input type="hidden" id="hidMatLevel" value="2"/>
    <input type="hidden" id="hidMatID" value=""/>
    <input type="hidden" id="hidBrandID" value=""/>
    <input type="hidden" id="hidFileUrl" value="http://img9.rxjy.com:9120/?action=uploadimage&isAddTextWarterMark=False&isAddImageWaterMark=False&savePath=Engine/PMPhoto/&imageSpecifications=800X800;800X600;400X400;150X150;60X60;280X200;150X220;200X200;"/>
    <input type="hidden" id="hidPageType" value="1"/>
    <input type="hidden" id="hidUserType" value="6"/>
    <input type="hidden" id="hidUserNo" value="${user.cardNumber}"/>
    <input type="hidden" id="hidUserName" value="${user.xinming}"/>
    <input type="hidden" id="hidJSID" value="${user.juese}"/>

    <input type="hidden" id="hidIcaType" value="0"/>
    <input type="hidden" id="hidLookType" value=""/><%--1代表圈中的 2代表管理*@--%>
    <input type="hidden" id="hidQSType" value="1"/>
    <!-- 二段导航标识:0.状态(默认)/1.平台/2.商城/3-5.A-C档次/6.价格/7.成本/8.应用 -->
    <input type="hidden" id="hidTwoNavFlag" value="0"/>
    <%--作用:档次 质量标准项 保存,点击导航栏设值.--%>
    <input type="hidden" id="hidLevelStaFlag" value="0"/>
    <%--作用:新增品牌后关闭三段弹出层,重新加载档次数据--%>
    <input type="hidden" id="hidLevelAddFlag" value=""/>
    <%--作用 加载 成本 多档次三段 的隐藏域字段--%>
    <input type="hidden" id="hidCostMatLevel" value="0"/>

    <span id="lblMainOffExteriorName" class="hide"></span>
    <span id="lblMainOffExteriorSC" class="hide"></span>
    <span id="lblMainOffExteriorDM" class="hide"></span>

    <%--子库地区档次品牌项弹出层隐藏域--%>
    <input type="hidden" id="hidLevelBrandsMatID" value=""/>
    <input type="hidden" id="hidLevelBrandsFirstFlag" value=""/>

</body>
    <%--基础脚本--%>
    <script src="${ctxStatic}/js/jquery.rippleria.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/rxuedv2.0.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/frame.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/jquery.pagination.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--分页控件-->
    <script src="${ctxStatic}/js/New_jquery.mousewheel.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--滚轮滚动缩放-->
    <script src="${ctxStatic}/js/jquery.rotate.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--图片旋转-->
    <script src="${ctxStatic}/js/jquery.session.js?${verStatic}" type="text/javascript" charset="utf-8"></script><!--缓存-->

    <%--图片相关JS--%>
    <script src="${ctxStatic}/Scripts/fileupload/jquery.ui.widget.js?${verStatic}"></script>
    <script src="${ctxStatic}/Scripts/fileupload/load-image.all.min.js?${verStatic}"></script>
    <script src="${ctxStatic}/Scripts/fileupload/jquery.iframe-transport.js?${verStatic}"></script>
    <script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload.js?${verStatic}"></script>
    <script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-ui.js?${verStatic}"></script>
    <script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-process.js?${verStatic}"></script>
    <script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-image.js?${verStatic}"></script>
    <script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-video.js?${verStatic}"></script>
    <script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-audio.js?${verStatic}"></script>
    <script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-validate.js?${verStatic}"></script>

    <%--其他--%>
    <script src="${ctxStatic}/js/jquery.rotate.min.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/New_jquery.mousewheel.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/bindCombox.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/fm.tagator.jquery.js?${verStatic}" type="text/javascript" charset="utf-8"></script>

    <%--页面相关JS--%>
    <script src="${ctxStatic}/js/material/base.js?${verStatic}"></script>
    <script>
        $(function() {
            // 加载 -- 左侧
            $("#engineeringLeft").load(basePath + "/em/material-web/one-list",function(){
                $(".setup_nav").click(); // 折叠右侧主导航
                countLeft();

                // 加载 -- 主(中心与右侧)
                $("#divMaterialDetail").load(basePath + "/em/material-web/main",function(){});
            });
        });
    </script>
</html>
