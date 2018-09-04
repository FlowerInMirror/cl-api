<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<html>
<link rel="stylesheet" href="${ctxStatic}/css/rxui1.0.2.min.css?${verStatic}">
<link rel="stylesheet" href="${ctxStatic}/css/frame.min.css?${verStatic}">
<link rel="stylesheet" href="${ctxStatic}/css/mend-msg.css?${verStatic}" type="text/css" >
<script src="${ctxStatic}/js/jquery-1.11.3.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
<%--basePath: 项目基础路径, ctxStatic: 项目静态资源路径,gcApiSite: 工程接口服务站点 --%>
<script type="text/javascript">var basePath = '${basePath}',ctxStatic='${ctxStatic}',gcApiSite = '${gcApiSite}',userCityID = '${user.u_diqu}';</script>
<head>
    <link href="${ctxStatic}/css/page-library.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-threecolumn.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-childlibrary.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-pbank.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-itemed.css?${verStatic}" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/css/fm.tagator.jquery.css?${verStatic}" rel="stylesheet" />
    <link href="${ctxStatic}/css/page-material.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/temporary.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/js/layui/css/layui.css?${verStatic}" rel="stylesheet"/>
    <style>
        body {
            font-family: "Microsoft YaHei", "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: 12px;
            color: #666;
            line-height: 18px;
        }
    </style>
    <%--layer前端UI框架,在此引入:保证AngularJS调用成功 --%>
    <script src="${ctxStatic}/js/layer.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/layui/layui.js?${verStatic}"></script>
    <%--在此引入:保证ng-cloak生效--%>
    <script src="${ctxStatic}/js/angular.js?${verStatic}"></script>
    <%--kindeditor-4.1.10富文本编辑器--%>
    <script charset="utf-8" src="${ctxStatic}/js/kindeditor-4.1.10/kindeditor-all.js?${verStatic}"></script>
    <script charset="utf-8" src="${ctxStatic}/js/kindeditor-4.1.10/lang/zh_CN.js?${verStatic}"></script>

</head>
<script src="${ctxStatic}/js/rxuedv2.0.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
<body>
    <div class="wrapper clearfix pt10 pl10 pr10" ng-app="myApp" ng-controller="myController" ng-cloak>
        <!-- 左侧 开始 -->
        <div class="tc-left pr10 fl">
            <%--标题--%>
            <ul class="clearfix uiTab1 mb5 jcha1 jcha2  jcha2_new jcha3_new jcha4_new jcha5_new">
                <li class="uiTab1-active" data-index="0"><a href="javascript:">子库</a></li>
                <li data-index="1"><a href="javascript:">北京</a></li>
                <%--根据当前登录用户ID判断是否为材料管理员,展示材料审核管理操作按钮.--%>
                <c:if test="${fns:getConfig('sys.material') eq user.juese or fns:getConfig('sys.admin') eq user.juese }">
                    <li class="hide" data-index="2"><a href="javascript:">审核</a></li>
                    <li class="hide" data-index="3"><a href="javascript:">管理</a></li>
                </c:if>
            </ul>

            <%--起始页第二排按钮--%>
            <div class="daily_title jcha1">
                <ul class="clearfix">
                    <li class="title_cur">全</li>
                    <li ng-click="freshIndexOneList()" style="float: right;border:0;" id="freshIndexList" title="同步"><img src="${ctxStatic}/images/pic/fresh.png?${verStatic}" style="margin-top:6px;margin-left: -20px;" width="17" height="17"></li>
                </ul>
            </div>

            <%--地区二排按钮--%>
            <div class="daily_title jcha2 hide daily_title_tree clearfix  jcha2_new jcha5_new">
                <ul class="fl">
                    <li class="title_cur" data-type="0">全</li>
                    <c:choose>
                        <c:when test="${fns:getConfig('sys.material') eq user.juese or fns:getConfig('sys.admin') eq user.juese }">
                            <li title="未入库" data-type="2">W</li>
                            <li title="报价推送-待办任务" data-type="3">D</li>
                            <li title="材料商缺失" data-type="6">R</li>
                            <li title="待审核材料" data-type="4">S</li>
                            <li class="daily_liLast" title="下达地方任务" data-type="5">T</li>
                            <li title="专项" data-type="zhuan">专</li>
                        </c:when>
                        <c:otherwise>
                            <li title="报价推送-待办任务" data-type="3">D</li>
                            <li title="材料商缺失" data-type="6">R</li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <ul class="fr">
                    <li title="置顶的材料" data-type="99">圈</li>
                </ul>
            </div>

            <%--起始页一段内容列表--%>
            <div class="contentbox">
                <div class="dailyftSearch clearfix hide">
                    <div class="fl uiHas-textIcon">
                        <input type="text" class="col-md-12">
                        <i class="uiText-searchIcon bgIcon"></i>
                    </div>
                </div>
                <table class="uiTable jcha1">
                    <thead>
                    <tr>
                        <th width="30%">地区</th>
                        <th width="30%">种类</th>
                        <th width="20%">完</th>
                        <th width="20%">未</th>
                    </tr>
                    </thead>
                </table>
                <div class="scroll-content scroll-content1">
                    <table class="uiTable pointertable trHover1" id="tab1" data-ng-init="loadIndexOneList()">
                        <tbody id="tabData">
                        <%--子库初始页一段,城市/种类/...--%>
                        <tr ui-sref="sublibrary_city" ui-sref-opts="{reload:true}" ng-repeat="cityStatistics in cityStatisticsItems track by $index" data-cityname="{{cityStatistics.cityName}}" data-city="{{cityStatistics.cityID}}" ng-click="loadSecondDatas(cityStatistics.cityID,cityStatistics.classs,cityStatistics.accomplish)">
                            <td width="30%">{{cityStatistics.cityName}}</td>
                            <td width="30%">{{cityStatistics.classs}}</td>
                            <td width="20%"><span class="cGreen">{{cityStatistics.accomplish}}</span></td>
                            <td width="20%"><span class="cRed">{{cityStatistics.classs - cityStatistics.accomplish}}</span></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="jcha1 clearfix tj_bottom" id="divStateStatisticsToALL">
                    <p class="col-md-3 StatisAllCount pointer" title="全部" data-mark="0">0</p>
                    <p class="col-md-3 cGreen pointer" title="正常" data-mark="1">0</p>
                    <p class="col-md-3 cOrange pointer" title="异常" data-mark="2">0</p>
                    <p class="col-md-3 cRed pointer" title="问题" data-mark="3">0</p>
                </div>
            </div>

            <%--地区一段内容里列表--%>
            <div class="contentbox hide contentbox_tree">
                <div class="dailyftSearch clearfix hide">
                    <div class="clearfix uiHas-textIcon">
                        <input type="text" class="col-md-12" placeholder="材料名称/规格/规格编号" id="txtTreeSearch">
                        <i class="uiText-searchIcon bgIcon"></i>
                    </div>
                    <div class="clearfix pl10">
                        <select class="fl mr2" style="width:62px;" id="ddlTreeOne">
                            <option value="">一级类</option>
                        </select>
                        <select class="fl mr2" style="width:62px;" id="ddlTreeTwo">
                            <option value="">二级类</option>
                        </select>
                        <select class="fl mr2" style="width:54px;" id="ddlTreeThree">
                            <option value="">名称</option>
                        </select>
                        <select class="fl mr2" style="width:54px;" id="ddlTreeFour">
                            <option value="">规格</option>
                        </select>
                    </div>
                </div>
                <table class="uiTable jcha2  jcha2_new">
                    <thead>
                        <tr>
                            <th width="15%"><span class="sort" title="排序" ng-click="sortByTreeOrder('rowNum')"></span></th>
                            <th width="35%">材料名称<span class="serial_number serial_number_tree"><i class="bgIcon"></i></span></th>
                            <th width="16%" class="OneClass" title="管理状态:未-未处理/正-正常/异-异常/问-问题">管</th>
                            <th width="16%" class="OneClass" title="业务状态:针对平台、A档、B档、C档，全部完成为'完'，反之则'未'">业</th>
                            <th ><span class="AddIcon"></span></th>
                        </tr>
                    </thead>
                </table>
                <div class="scroll-content scroll-content2">
                    <table class="uiTable pointertable trHover1" id="tab2">
                        <tbody id="tabDataToMaterial" ui-view="one_list"></tbody>
                    </table>
                </div>
                <div class="jcha2 clearfix tj_bottom  jcha2_new" id="divStateStatistics">
                    <p class="col-md-3 StatisAllCount pointer" title="全部" data-mark="0">0</p>
                    <p class="col-md-3 cGreen pointer" title="正常" data-mark="1">0</p>
                    <p class="col-md-3 cOrange pointer" title="异常" data-mark="2">0</p>
                    <p class="col-md-3 cRed pointer" title="问题" data-mark="3">0</p>
                </div>
            </div>

            <div class="contentbox hide contentbox_tree_ver">
                <div class="dailyftSearch clearfix hide">
                    <div class="clearfix uiHas-textIcon">
                        <input type="text" class="col-md-12" placeholder="材料名称/规格/规格编号" id="txtTreeSearchVer">
                        <i class="uiText-searchIcon bgIcon"></i>
                    </div>
                    <div class="clearfix pl10">
                        <select class="fl mr2" style="width:62px;" id="ddlTreeOneVer">
                            <option value="">一级类</option>
                        </select>
                        <select class="fl mr2" style="width:62px;" id="ddlTreeTwoVer">
                            <option value="">二级类</option>
                        </select>
                        <select class="fl mr2" style="width:54px;" id="ddlTreeThreeVer">
                            <option value="">名称</option>
                        </select>
                        <select class="fl mr2" style="width:54px;" id="ddlTreeFourVer">
                            <option value="">规格</option>
                        </select>
                    </div>
                </div>
                <table class="uiTable jcha2 jcha3_new">
                    <thead>
                    <tr>
                        <th width="28%">二级类</th>
                        <th width="32%"><span class="serial_number serial_number_tree_ver"><i class="bgIcon"></i></span>
                        </th>
                        <th width="22%">状态</th>
                        <th>小时</th>
                    </tr>
                    </thead>
                </table>
                <div class="scroll-content scroll-content2">
                    <table class="uiTable pointertable" id="tab3">
                        <tbody id="tabDataToMaterialVer"></tbody>
                    </table>
                </div>
                <div class="jcha2 clearfix tj_bottom jcha3_new" id="divStateStatisticsVer">
                    <p class="col-md-3 StatisAllCount pointer" title="全部" data-mark="0">0</p>
                    <p class="col-md-3 cGreen pointer" title="正常" data-mark="1">0</p>
                    <p class="col-md-3 cOrange pointer" title="异常" data-mark="2">0</p>
                    <p class="col-md-3 cRed pointer" title="问题" data-mark="3">0</p>
                </div>
            </div>
            <div class="contentbox hide contentbox_tree_manage">
                <div class="dailyftSearch clearfix hide">
                    <div class="clearfix uiHas-textIcon" style="margin-bottom:5px;">
                        <input type="text" class="col-md-12" placeholder="材料名称/规格/规格编号" id="txtTreeSearchManage">
                        <i class="uiText-searchIcon bgIcon"></i>
                    </div>
                    <div class="clearfix pl10">
                        <select class="fl mr2" style="width:62px;" id="ddlTreeOneManage">
                            <option value="">一级类</option>
                        </select>
                        <select class="fl mr2" style="width:62px;" id="ddlTreeTwoManage">
                            <option value="">二级类</option>
                        </select>
                        <select class="fl mr2" style="width:54px;" id="ddlTreeThreeManage">
                            <option value="">名称</option>
                        </select>
                        <select class="fl mr2" style="width:54px;" id="ddlTreeFourManage">
                            <option value="">规格</option>
                        </select>
                    </div>
                </div>
                <table class="uiTable jcha2 jcha4_new">
                    <thead>
                    <tr>
                        <th width="15%"><span class="serial_number serial_number_tree_manage"><i class="bgIcon"></i></span>
                        </th>
                        <th width="30%">二级类</th>
                        <th width="36%">材料名称</th>
                        <th>小时</th>
                    </tr>
                    </thead>
                </table>
                <div class="scroll-content scroll-content2">
                    <table class="uiTable pointertable" id="tab4">
                        <tbody id="tabDataToMaterialManage"></tbody>
                    </table>
                </div>
                <div class="jcha2 clearfix tj_bottom jcha4_new" id="divStateStatisticsManage">
                    <p class="col-md-3 StatisAllCount pointer" title="全部" data-mark="0">0</p>
                    <p class="col-md-3 cGreen pointer" title="正常" data-mark="1">0</p>
                    <p class="col-md-3 cOrange pointer" title="异常" data-mark="2">0</p>
                    <p class="col-md-3 cRed pointer" title="问题" data-mark="3">0</p>
                </div>
            </div>
            <%--专项111--%>
            <div class="contentbox hide contentbox_zhuan">

            </div>
        </div>

        <div class="div_main_data" id="divChildDetail">

            <!--子库右侧数据开始-->
            <div class="tc-center fl">
                <ul class="clearfix uiTab9 j_uiTab9 jcha2 jcha3 jcha4 jcha5 jcha6">
                    <li class="fl uiTab9-active" data-index="0">状态</li>
                    <li class="fl" data-index="1">统计</li>
                    <li class="fl" data-index="2">记录</li>
                    <li class="fl" data-index="3">成本</li>
                </ul>
                <div id="j-tc-center-content" class="pr10">
                    <div class="uiTab9Con">
                        <div class="analyItem ">
                            <p class="analyItemTit tx-center">状态</p>
                            <div class="analyItemCon"></div>
                        </div>
                        <div class="analyItem anItemBor" id="divTurnToMaterialList">
                            <p class="analyItemTit tx-center">子库</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-12 tx-center">点击进入子库</p>
                            </div>
                        </div>
                        <%--材料统计--%>
                        <div class="analyItem">
                            <p class="analyItemTit tx-center">统计</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-4"><span class="cLightGray pr8">种类</span><span>{{loopClasssCount}}</span>&ensp;个</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">已完</span><span class="cGreen">{{loopAccomplishCount}}</span>&ensp;个</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">未完</span><span ng-class="AddClass(loopUnfinishedCount)">{{loopUnfinishedCount}}</span>&ensp;个</p>

                                <p class="fl col-md-4"><span class="cLightGray pr8">品牌</span><span>{{SecondDatas.brands.brand}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">已完</span><span title="完成标准:有品牌名称/有型号名称/LOGO图,主图,型号图最少上传一张/有成本价/已入库" class="cGreen">{{SecondDatas.brands.brand - SecondDatas.brands.imperfect}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">未完</span><span title="完成标准:有品牌名称/有型号名称/LOGO图,主图,型号图最少上传一张/有成本价/已入库" ng-class="AddClass(SecondDatas.brands.imperfect)">{{SecondDatas.brands.imperfect}}</span>&ensp;种</p>

                                <p class="fl col-md-4"><span class="cLightGray pr8" title="圈往材料商城中的品牌材料">商城</span><span>{{SecondDatas.mallBrand.mallBrandCount}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">已完</span><span class="cGreen">{{SecondDatas.mallBrand.mallBrandCount - SecondDatas.mallBrand.mallBrandNotCount}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">未完</span><span ng-class="AddClass(SecondDatas.mallBrand.mallBrandNotCount)">{{SecondDatas.mallBrand.mallBrandNotCount}}</span>&ensp;种</p>
                            </div>
                        </div>
                        <%--本月处理记录--%>
                        <div class="analyItem">
                            <p class="analyItemTit tx-center">处理</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-4"><span class="cLightGray pr8">正常</span><span class="cGreen">{{SecondDatas.handle.zCount}}</span>&ensp;个</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">异常</span><span class="cOrange">{{SecondDatas.handle.yCount}}</span>&ensp;个</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">问题</span><span class="cRed">{{SecondDatas.handle.wCount}}</span>&ensp;个</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">下架</span><span>{{SecondDatas.handle.xCount}}</span>&ensp;个</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">总数</span><span >{{SecondDatas.handle.handelCount}}</span>&ensp;个</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">未管</span><span class="cPurple">{{loopClasssCount - SecondDatas.handle.handelCount}}</span>&ensp;个</p>
                            </div>
                        </div>
                        <%--本月操作记录--%>
                        <div class="analyItem">
                            <p class="analyItemTit tx-center">调整</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-4"><span class="cLightGray pr8">材料</span><span>{{SecondDatas.s_theLastMonth.mat}}</span>&ensp;次</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">照片</span><span>{{SecondDatas.s_theLastMonth.photo}}</span>&ensp;张</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">价格</span><span>{{SecondDatas.s_theLastMonth.price}}</span>&ensp;次</p>
                            </div>
                        </div>
                        <%--成本--%>
                        <div class="analyItem">
                            <p class="analyItemTit tx-center">成本</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-4"><span class="cLightGray pr8">整体排名</span><span>{{SecondDatas.s_cost.cityRanking}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">价低</span><span class="cGreen">{{SecondDatas.s_cost.lowPrice}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">价高</span><span class="cRed">{{SecondDatas.s_cost.highPrice}}</span>&ensp;种</p>
                            </div>
                        </div>
                    </div>
                    <!--材料统计 开始-->
                    <div class="uiTab9Con dis-none">
                        <div class="analyItem">
                            <p class="analyItemTit tx-center">状态</p><div class="analyItemCon">
                        </div>
                        </div>
                        <div class="analyItem ">
                            <p class="analyItemTit tx-center">平台</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-4"><span class="cLightGray pr8">种类</span><span>{{loopClasssCount}}</span>&ensp;个</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">已完</span><span class="cGreen">{{loopAccomplishCount}}</span>&ensp;个</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">未完</span><span ng-class="AddClass(loopUnfinishedCount)">{{loopUnfinishedCount}}</span>&ensp;个</p>
                            </div>
                        </div>
                        <div class="analyItem ">
                            <p class="analyItemTit tx-center">品牌</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-4"><span class="cLightGray pr8">总数</span><span>{{SecondDatas.brands.brand}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">已完</span><span title="完成标准:有品牌名称/有型号名称/LOGO图,主图,型号图最少上传一张/有成本价/已入库" class="cGreen">{{SecondDatas.brands.brand - SecondDatas.brands.imperfect}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">未完</span><span title="完成标准:有品牌名称/有型号名称/LOGO图,主图,型号图最少上传一张/有成本价/已入库" ng-class="AddClass(SecondDatas.brands.imperfect)">{{SecondDatas.brands.imperfect}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">月调</span><span>{{SecondDatas.s_theLastMonth.mat}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">价低</span><span class="cGreen">{{SecondDatas.s_cost.lowPrice}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">价高</span><span class="cRed">{{SecondDatas.s_cost.highPrice}}</span>&ensp;种</p>
                            </div>
                        </div>
                        <div class="analyItem ">
                            <p class="analyItemTit tx-center">商城</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-4"><span class="cLightGray pr8" title="圈往材料商城中的品牌材料">商城</span><span>{{SecondDatas.mallBrand.mallBrandCount}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">已完</span><span class="cGreen">{{SecondDatas.mallBrand.mallBrandCount - SecondDatas.mallBrand.mallBrandNotCount}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">未完</span><span ng-class="AddClass(SecondDatas.mallBrand.mallBrandNotCount)">{{SecondDatas.mallBrand.mallBrandNotCount}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">置顶</span><span>{{SecondDatas.m_platform.topCount}}</span>&ensp;种</p>
                            </div>
                        </div>
                    </div>
                    <!--项目 结束-->
                    <!--操作历史 开始-->
                    <div class="uiTab9Con dis-none">
                        <div class="analyItem">
                            <p class="analyItemTit tx-center">状态</p>
                            <div class="analyItemCon"></div>
                        </div>
                        <div class="analyItem">
                            <p class="analyItemTit tx-center">操作记录</p>
                            <div class="analyItemCon">
                                <div class="col-md-5 mb10">
                                    <span class="cLightGray">开始</span>
                                    <input type="text" class="form-control width100 startDate" onfocus="WdatePicker({ skin: 'whyGreen', dateFmt: 'yyyy-M-d' })"/>
                                </div>

                                <div class="col-md-5 mb10">
                                    <span class="cLightGray">结束</span>
                                    <input type="text" class="form-control width100 endDate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-M-d' })"/>
                                </div>
                                <div class="col-md-2 mb10 tx-center">
                                    <input type="button" class="uiBtn-blue uiBtn-normal" value="搜索" ng-click="recordDataShow()"/>
                                </div>
                            </div>
                        </div>
                        <div class="analyItem anItemBor analyItemMaterial" ng-click="materialDataShow($event)">
                            <p class="analyItemTit tx-center">材料调整</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-4"><span class="cLightGray pr8">总数</span><span class="MaterialTotalNum"></span>&ensp;种</p>
                            </div>
                        </div>
                        <div class="analyItem anItemBor analyItemPhoto" ng-click="photoDataShow($event)">
                            <p class="analyItemTit tx-center">照片上传</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-4"><span class="cLightGray pr8">总数</span><span class="PhotoTotalNum"></span>&ensp;张</p>
                            </div>
                        </div>
                        <div class="analyItem anItemBor analyItemPrice" ng-click="priceDataShow($event)">
                            <p class="analyItemTit tx-center">价格调整</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-4"><span class="cLightGray pr8">总数</span><span class="PriceTotalNum"></span>&ensp;次</p>
                            </div>
                        </div>
                    </div>
                    <!--操作历史 结束-->
                    <!--成本 开始-->
                    <div class="uiTab9Con dis-none">
                        <div class="analyItem">
                            <p class="analyItemTit tx-center">状态</p>
                            <div class="analyItemCon"></div>
                        </div>
                        <div class="analyItem anItemBor analyItemCost">
                            <p class="analyItemTit tx-center">成本</p>
                            <div class="analyItemCon">
                                <p class="fl col-md-4"><span class="cLightGray pr8">整体排名</span><span>{{SecondDatas.s_cost.cityRanking}}</span></p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">价低</span><span class="cGreen">{{SecondDatas.s_cost.lowPrice}}</span>&ensp;种</p>
                                <p class="fl col-md-4"><span class="cLightGray pr8">价高</span><span class="cRed">{{SecondDatas.s_cost.highPrice}}</span>&ensp;种</p>
                            </div>
                        </div>
                        <div class="analyItem useRankings">
                            <p class="analyItemTit tx-center">使用排名</p>
                            <div class="analyItemCon">
                                <span class="cLightGray pr8">量</span>
                                <label class="uiRadio12 useRankingLabel"><input type="radio" name="useRank" class="uiRadio12-input" data-number="100">前100名</label>
                                <label class="uiRadio12 useRankingLabel"><input type="radio" name="useRank" class="uiRadio12-input" data-number="200">前200名</label>
                                <label class="uiRadio12 useRankingLabel"><input type="radio" name="useRank" class="uiRadio12-input" data-number="300">前300名</label>
                            </div>
                        </div>
                    </div>
                    <!--成本 结束-->
                </div>
            </div>
            <!--3.28-->
            <div class="tc-right relative">
                <div class="tc-righttop-tab j-tc-righttop-tab">
                    <input type="button" value="列表" class="uiBtn-blue uiBtn-normal mater_lists" style="float: right;">

                </div>
                <div class="tc-right-top clearfix relative">
                    <div class="fl needdealt">
                        <ul></ul>
                    </div>
                    <div class="visitbox">
                        <div class="visiterecord-rgt-scroll">
                            <div class="visiterecord-rgt-scroll-con" id="j-visit">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tc-right-bottom pa10 relative">
                    <div class="tc-right-bottom-left relative">
                        <!--固定在回访右侧的操作按钮 结束-->
                        <div class="question-div clearfix relative">
                        </div>
                        <div class="dailyrgt-botradio hide">
                            <div class="mb10">
                                <label class="uiRadio12 Fillreturnlabel_zc"><input type="radio" name="visittype"
                                                                                   class="uiRadio12-input"
                                                                                   value="1">正常</label>
                                <label class="uiRadio12 Fillreturnlabel_yc"><input type="radio" name="visittype"
                                                                                   class="uiRadio12-input"
                                                                                   value="2">异常</label>
                                <label class="uiRadio12 Fillreturnlabel_wt"><input type="radio" name="visittype"
                                                                                   class="uiRadio12-input"
                                                                                   value="3">问题</label>
                            </div>
                        </div>
                        <div class="dailyrgt-divZi">
                            <p class="tx-center">先解决业务问题，才有资格说管理。前两个因素完成之后，再反馈系统问题。</p>
                        </div>
                        <div class="visi-text-content hide">
                            <textarea placeholder="请输入回访内容" class="hf-textarea mb10" id="txtVisitContentToCity"></textarea>
                            <input class="hf-submitToCity" type="submit" value="提交"/>
                        </div>
                    </div>
                    <div class="EventpenaltyDiv">
                    </div>
                </div>
                <!--加载性弹层 弹出层开始-->
                <div class="divCityLoadAlert hide alertpaper">
                    <h3 class="divCityLoadAlert-tit uiTitle2">
                        <i class="fl mr5 hide"></i>
                        <a href="javascript:" class="close fr mr5 mt2"></a>
                    </h3>
                    <div class="divCityLoadAlert-con" id="divAlertCityLoad">
                        <div class="divCityLoadAlert_top pa10">
                        </div>
                    </div>
                    <div class="divCityLoadAlert_bottom mt10">
                    </div>
                </div>
                <!--加载性弹层 弹出层结束-->
                <!--操作记录-材料 弹出层开始-->
                <div class="operaMaterialAlert commonRgtAlert">
                    <h3 class="uiTitle2 clearfix pt10 pb10 divcostcha">
                        <i class="fl mr5 hide"></i>
                        <a href="javascript:" class="close fr mr5"></a>
                    </h3>
                    <div class="operaMaterialAlertCon pl10 pr10">
                        <table class="uiTable divcostcha">
                            <thead>
                            <tr>
                                <th width="7%">序</th>
                                <th width="15%">材料名称</th>
                                <th width="15%">材料规格</th>
                                <th width="13%">分类名称</th>
                                <th width="11%">品牌名称</th>
                                <th width="25%">行为内容</th>
                                <th>操作时间</th>
                            </tr>
                            </thead>
                        </table>
                        <div class="oprecordScroll">
                            <table class="uiTable">
                                <tbody>
                                <tr ng-repeat="MaterialData in MaterialDatas track by $index">
                                    <td width="7%">{{MaterialData.rowNum}}</td>
                                    <%--<td width="11%">{{MaterialData.treeOneName}}</td>--%>
                                    <%--<td width="11%">{{MaterialData.treeTwoName}}</td>--%>
                                    <td width="15%" title="{{MaterialData.treeThreeName}}">{{MaterialData.treeThreeName |
                                        cutout: 5:0: '...'}}
                                    </td>
                                    <td width="15%" title="{{MaterialData.treeFourName}}">{{MaterialData.treeFourName|
                                        cutout: 5:0: '...'}}
                                    </td>
                                    <td width="13%" title="{{MaterialData.className}}">{{MaterialData.className| cutout:
                                        5:0: '...'}}
                                    </td>
                                    <td width="11%">{{MaterialData.brandName}}</td>
                                    <td width="25%" title="{{MaterialData.content}}">{{MaterialData.content | cutout: 10:0:
                                        '...'}}
                                    </td>
                                    <td>{{filterDate(MaterialData.operateTime) | date: 'yyyy-MM-dd'}}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <tm-pagination conf="paginationConf1"></tm-pagination>
                    </div>
                </div>
                <!--操作记录-材料 弹出层结束-->
                <!--操作记录-照片 弹出层开始-->
                <div class="opreraPhotoAlert commonRgtAlert">
                    <h3 class="uiTitle2 clearfix pt10 pb10 divcostcha">
                        <i class="fl mr5 hide"></i>
                        <a href="javascript:" class="close fr mr5"></a>
                    </h3>
                    <div class="opreraPhotoAlertCon pl10 pr10">
                        <table class="uiTable divcostcha">
                            <thead>
                            <tr>
                                <th width="7%">序</th>
                                <%--<th width="11%">一级分类</th>--%>
                                <%--<th width="11%">二级分类</th>--%>
                                <th width="15%">材料名称</th>
                                <th width="18%">材料规格</th>
                                <th width="15%">图片类型</th>
                                <th width="30%">行为内容</th>
                                <th>操作时间</th>
                            </tr>
                            </thead>
                        </table>
                        <div class="oprecordScroll">
                            <table class="uiTable">
                                <tbody>
                                <tr ng-repeat="PhotoData in PhotoDatas track by $index">
                                    <td width="7%">{{PhotoData.rowNum}}</td>
                                    <td width="15%" title="{{PhotoData.treeThreeName}}">{{PhotoData.treeThreeName | cutout:
                                        5:0: '...'}}
                                    </td>
                                    <td width="18%" title="{{PhotoData.treeFourName}}">{{PhotoData.treeFourName| cutout:
                                        6:0: '...'}}
                                    </td>
                                    <td width="15%" title="{{PhotoData.className}}">{{PhotoData.className| cutout: 5:0:
                                        '...'}}
                                    </td>
                                    <td width="30%" title="{{PhotoData.content}}">{{PhotoData.content | cutout:15:0:
                                        '...'}}
                                    </td>
                                    <td>{{filterDate(PhotoData.operateTime) | date: 'yyyy-MM-dd'}}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <tm-pagination conf="paginationConf2"></tm-pagination>
                    </div>
                </div>
                <!--操作记录-照片 弹出层结束-->
                <!--操作记录-价格 弹出层开始-->
                <div class="opreraPriceAlert commonRgtAlert">
                    <h3 class="uiTitle2 clearfix pt10 pb10 divcostcha">
                        <i class="fl mr5 hide"></i>
                        <a href="javascript:" class="close fr mr5"></a>
                    </h3>
                    <div class="opreraPriceAlertCon pl10 pr10">
                        <table class="uiTable divcostcha">
                            <thead>
                            <tr>
                                <th width="7%">序</th>
                                <%--<th width="11%">一级分类</th>--%>
                                <%--<th width="11%">二级分类</th>--%>
                                <th width="15%">材料名称</th>
                                <th width="20%">材料规格</th>
                                <th width="15%">品牌名称</th>
                                <th width="28%">行为内容</th>
                                <th>操作时间</th>
                            </tr>
                            </thead>
                        </table>
                        <div class="oprecordScroll">
                            <table class="uiTable">
                                <tbody>
                                <tr ng-repeat="PriceData in PriceDatas track by $index">
                                    <td width="7%">{{PriceData.rowNum}}</td>
                                    <%--<td width="11%">{{PriceData.treeOneName}}</td>--%>
                                    <%--<td width="11%">{{PriceData.treeTwoName}}</td>--%>
                                    <td width="15%" title="{{PriceData.treeThreeName}}">{{PriceData.treeThreeName | cutout:
                                        5:0: '...'}}
                                    </td>
                                    <td width="20%" title="{{PriceData.treeFourName}}">{{PriceData.treeFourName| cutout:
                                        8:0: '...'}}
                                    </td>
                                    <td width="15%" title="{{PriceData.brandName}}">{{PriceData.brandName| cutout: 5:0:
                                        '...'}}
                                    </td>
                                    <td width="28%" title="{{PriceData.content}}">{{PriceData.content | cutout: 12:0:
                                        '...'}}
                                    </td>
                                    <td>{{filterDate(PriceData.operateTime) | date: 'yyyy-MM-dd'}}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <tm-pagination conf="paginationConf3"></tm-pagination>
                    </div>
                </div>
                <!--操作记录-价格 弹出层结束-->
                <!--成本 弹出层开始-->
                <div class="divcostAlert commonRgtAlert hide">
                    <h3 class="uiTitle2 clearfix pt10 pb10 divcostcha">
                        <i class="fl mr5 hide"></i>
                        <a href="javascript:" class="close fr mr5"></a>
                    </h3>
                    <div class="divcostAlertCon pl10 pr10">
                        <table class="uiTable divcostcha">
                            <thead>
                            <tr>
                                <th width="20%">名次</th>
                                <th width="20%">地区名称</th>
                                <th width="20%">总成本</th>
                                <th width="20%">排名浮动</th>
                                <th>成本浮动</th>
                            </tr>
                            </thead>
                        </table>
                        <div class="divcostScroll">
                            <table class="uiTable" id="tab1">
                                <tbody>
                                <tr ng-repeat="costData in costDatas track by $index">
                                    <td width="20%">{{costData.cmRanking}}</td>
                                    <td width="20%">{{costData.cityName}}</td>
                                    <td width="20%">{{costData.cmTotalPrice}}</td>
                                    <td width="20%">{{costData.recentRanking}}<i
                                            ng-class="{'decline-red': costData.recentRanking>0,'Rise-green':costData.recentRanking<0}"></i>
                                    </td>
                                    <%--逻辑:最近涨幅 = 上月成本 - 当前成本,为正值证明成本下降的加绿色样式,为负值证明成本上涨了加红色样式--%>
                                    <td>{{costData.recentGains}}<i
                                            ng-class="{'decline-red': costData.recentGains<0,'Rise-green':costData.recentGains>0}"></i>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!--成本 弹出层结束-->

            </div>

            <!--子库右侧数据结束-->


        </div>

        <div class="div_main_data hide" id="divMaterialDetail" ui-view="two_all"></div>
        <%--专项111--%>
        <div class="div_main_data hide" id="divZhuanXiang">

        </div>
        <!--使用排名 弹出层开始-->
        <div class="useRankingAlert hide pl10 pr10 pt10">
            <table class="uiTable useRankingcha">
                <thead>
                <tr>
                    <th width="8%">名次</th>
                    <th width="8%">档次</th>
                    <th width="15%">材料名称</th>
                    <th width="20%">规格</th>
                    <th width="10%">品牌</th>
                    <th width="10%">成本</th>
                    <th width="10%">全国均价</th>
                    <th width="10%">交易均价</th>
                    <th>使用量</th>
                </tr>
                </thead>
            </table>
            <div class="useRankingScroll">
                <table class="uiTable" id="tab1">
                    <tbody>
                    <tr ng-repeat="userankData in userankDatas track by $index">
                        <td width="8%">{{userankData.topNum}}</td>
                        <td width="8%" title="{{userankData.matCode}}">{{userankData.matLevel}}</td>
                        <td width="15%" title="{{userankData.treeThreeName}}">{{userankData.treeThreeName | cutout:10:0:
                            '...'}}
                        </td>
                        <td width="20%" title="{{userankData.treeFourName}}">{{userankData.treeFourName | cutout: 18:0:
                            '...'}}
                        </td>
                        <td width="10%" title="{{userankData.brandName}}">{{userankData.brandName | cutout: 8:0: '...'}}
                        </td>
                        <td width="10%" ng-class="{'cRed':userankData.costPrice>userankData.nationwideMatAvgPrice}">
                            {{userankData.costPrice}}
                        </td>
                        <td width="10%">{{userankData.nationwideMatAvgPrice}}</td>
                        <td width="10%"
                            ng-class="{'cRed':userankData.matAvgPrice>(userankData.costPrice+userankData.installPrice)}">
                            {{userankData.matAvgPrice}}
                        </td>
                        <td>{{userankData.totalSingleQuantity}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!--使用排名 弹出层结束-->
    </div>
    <!--驳回弹出层-->
    <div class="alertMask"></div>
    <div class="alertBox bohui-alert">
        <div class="alertTitle">
            <p>驳回</p>
            <span class="alertClose close"></span>
        </div>
        <div class="alertCon tx_center pa10">
            <textarea rows="6" class="col-md-12" id="txtCheckRemarkNewBrand"></textarea>
        </div>
        <div class="alertFoot pb20 pt20">
            <div class="dis_il_block">
                <input type="button" value="打回" class="uiBtn-blue fl uiBtn-normal mr10 queding" id="btnBrandCheckBack"/>
                <input type="button" value="打回" class="uiBtn-blue fl uiBtn-normal mr10 queding hide"
                       id="btnBrandCheckLastToBack"/>
                <input type="button" value="取消" class="uiBtn-gray uiBtn-normal quxiao"
                       onclick="$('.bohui-alert .close').click();"/>
            </div>
        </div>
    </div>
    <!--审核调价 弹出层开始-->
    <div class="alertBox hide adjustmentAlert">
        <div class="alertTitle">
            <p>审核调价</p>
            <a href="javascript:" class="close"></a>
        </div>
        <div class="alertCon pa20">
            <div id="divAlertMaterialCheckChangePrice"></div>
        </div>
    </div>
    <!--审核调价 弹出层结束-->
    <%-- 成本统计详情弹出层 --%>
    <div class="alertCon pt10 pb10 plr20 dis-none alertCostPriceStatisitcs">
        <div class="clearfix mb10" id="divCostPriceStatisitcs">
        </div>
    </div>
    <%--iframe--%>
    <iframe class="if-cs hide" src="" data-value="1"></iframe>
    <div class="closeevent hide" onclick="closeevent()">×</div>
    <iframe class="iframeTab hide" src="" data-value="2"></iframe>
    <div class="closeIfrTab hide" onclick="closeevent()">×</div>
    <iframe class="iframeTab_01 hide" src=""></iframe>
    <div class="closeIfrTab_01 hide" onclick="closeevent()">×</div>
    <iframe class="iframe_list hide" src=""></iframe>
    <div class="closeIfrTab_01 iframe_list_01 hide" onclick="close_list()">×</div>
    <iframe class="iframe_renshi hide" src=""></iframe>
    <div class="closeIfrTab_01 iframe_renshi_01 hide" onclick="close_renshi()">×</div>
    <iframe class="iframe_check hide" src=""></iframe>
    <div class="closeIfrTab_01 iframe_check_01 hide" onclick="close_check()">×</div>
    <!--隐藏域-->
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
    <%--子库列表:区分地区与全国 0代表还没有跳转,全国状态--%>
    <input type="hidden" id="hidToCityFlag" value="0"/>
    <%--专项--%>
    <input type="hidden" id="hidZXUserID" value=""/><!-- 专项材料商ID -->
    <input type="hidden" id="hidZXUserCityID" value=""/><!-- 专项材料商城市ID -->
    <input type="hidden" id="hidZXTreeThreeID" value=""/><!-- 专项关联材料三级科目ID -->
    <input type="hidden" id="hidZXProRelevanceID" value=""/><!-- 专项产品关联ID -->
    <input type="hidden" id="hidZXProRelevanceMatID" value=""/><!-- 专项产品关联材料ID -->
    <input type="hidden" id="hidStoresName" value=""/>
    <input type="hidden" id="hidUserCode" value=""/>
    <input type="hidden" id="hidZXSProID" value=""/><!-- 专项产品ID -->
    <input type="hidden" id="hidZXLayerIndex" value=""/><!-- 专项产品ID -->
</body>
<script src="${ctxStatic}/js/jquery.rippleria.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
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

    $(function () {
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
<footer-script>
    <%--jquery--%>
    <script src="${ctxStatic}/js/jquery.qrcode.min.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/jquery.rotate.min.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/jquery-ui.min.js?${verStatic}"></script>
    <%--AngularJS 相关--%>
    <script src="${ctxStatic}/js/angular-ui-router.min.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/angularjs/mms/material/sublibrary/apps.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/angularjs/mms/material/sublibrary/filters.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/angularjs/mms/material/sublibrary/routers.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/angularjs/mms/material/sublibrary/controllers.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/tm.pagination.js?${verStatic}"></script><%--翻页控件引用的js--%>
    <%--VUE--%>
    <script src="${ctxStatic}/js/vue.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/vue-router.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/vue/mms/commons/filters.js?${verStatic}" type="text/javascript" charset="utf-8"/>
    <script src="${ctxStatic}/js/vue/mms/commons/functions.js?${verStatic}" type="text/javascript" charset="utf-8"/>
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
    <script src="${ctxStatic}/js/New_jquery.mousewheel.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/bindCombox.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/fm.tagator.jquery.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/Scripts/My97DatePicker/WdatePicker.js?${verStatic}"></script>
    <%--自定义脚本--%>
    <script src="${ctxStatic}/js/sublibrary.js?${verStatic}"></script><%--子库相关JS--%>
    <script src="${ctxStatic}/js/special.js?${verStatic}"></script><%--专项相关JS--%>
    <script src="${ctxStatic}/js/Views/Material_Left.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/Views/Material_Center.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/Views/Material_Right.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/Views/Material_Load.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" charset="utf-8">
        //点击左侧弹框的关闭按钮
        $("body").on('click', '.layerRtb .rig_close', function () {
            $(this).parents(".layerRtb").animate({
                left: '-102%'
            }, function () {
                if(!$(this).closest(".layerRtb").hasClass("layerRtb-four"))
                {
                    $(".anItemBor-active").removeClass("anItemBor-active");
                }

            });
        })

        $(document).on("click", ".huojiaClick", function () {
            layer.open({
                type: 1,
                title: "货架",
                area: ["870px", "625px"],
                content: $(".GoodsShelvesBox")
            })

        })
        $(function () {
            $(window).resize(function () {
                countLeft();
                countMiddle();
                countRight();
            });

            //隔行换色:
            rxued.table.LChangeapart($(".contentbox_tree_manage tr"), "#f9f9f9", "#fff");
            rxued.table.hoverChage($(".contentbox_tree_manage tr"), "#6666FF")

        })
    </script>
    <script type="text/javascript" charset="utf-8">
        function countLeft() {
            //左侧JS
            //子库列表
            var oheight1 = 10;
            for (var i = 0; i < $(".jcha1").length; i++) {
                oheight1 += $(".jcha1").eq(i).outerHeight(true);
            }
            rxued.init.countH($(".scroll-content1"), oheight1, function () {
                $(".scroll-content1").slimScroll({height: $(".scroll-content1").eq(0).height(), borderRadius: "0px"});
            });
            $(".scroll-content1").parent(".slimScrollDiv").height($(".scroll-content1").height());
            //北京列表
            var oheight2_new = 10;
            for (var i = 0; i < $(".jcha2_new").length; i++) {
                oheight2_new += $(".jcha2_new").eq(i).outerHeight(true);
            }
            rxued.init.countH($(".contentbox_tree .scroll-content2"), oheight2_new, function () {
                $(".contentbox_tree .scroll-content2").slimScroll({
                    height: $(".contentbox_tree .scroll-content2").eq(0).height(),
                    borderRadius: "0px"
                });
            });
            $(".contentbox_tree .scroll-content2").parent(".slimScrollDiv").height($(".contentbox_tree .scroll-content2").height());

            //审核列表
            var oheight3_new = 10;
            for (var i = 0; i < $(".jcha3_new").length; i++) {
                oheight3_new += $(".jcha3_new").eq(i).outerHeight(true);
            }
            rxued.init.countH($(".contentbox_tree_ver .scroll-content2"), oheight3_new, function () {
                $(".contentbox_tree_ver .scroll-content2").slimScroll({
                    height: $(".contentbox_tree_ver .scroll-content2").eq(0).height(),
                    borderRadius: "0px"
                });
            });
            $(".contentbox_tree_ver .scroll-content2").parent(".slimScrollDiv").height($(".contentbox_tree_ver .scroll-content2").height());

            //管理列表
            var oheight4_new = 10;
            for (var i = 0; i < $(".jcha4_new").length; i++) {
                oheight4_new += $(".jcha4_new").eq(i).outerHeight(true);
            }
            rxued.init.countH($(".contentbox_tree_manage .scroll-content2"), oheight4_new, function () {
                $(".contentbox_tree_manage .scroll-content2").slimScroll({
                    height: $(".contentbox_tree_manage .scroll-content2").eq(0).height(),
                    borderRadius: "0px"
                });
            });
            $(".contentbox_tree_manage .scroll-content2").parent(".slimScrollDiv").height($(".contentbox_tree_manage .scroll-content2").height());
            //专项
            var oheight5_new = 10;
            for (var i = 0; i < $(".jcha5_new").length; i++) {
                oheight5_new += $(".jcha5_new").eq(i).outerHeight(true);
            }
            rxued.init.countH($(".contentbox_zhuan .scroll-content"), oheight5_new, function () {
                $(".contentbox_zhuan .scroll-content").slimScroll({
                    height: $(".contentbox_zhuan .scroll-content").eq(0).height(),
                    borderRadius: "0px"
                });
            });
            $(".contentbox_zhuan .scroll-content").parent(".slimScrollDiv").height($(".contentbox_zhuan .scroll-content").height());

        }

        function countMiddle() {
            var theight = $("body").height() - $("#topnav").height() - $(".j_uiTab9").outerHeight() - 11;
            $("#j-tc-center-content,#divMaterialDetail #j-tc-center-content").height(theight).slimScroll({
                height: theight,
                borderRadius: "0px"
            });
            $("#j-tc-center-content,#divMaterialDetail #j-tc-center-content").parents(".slimScrollDiv").eq(0).height(theight);
        }

        function countRight() {

            //右侧JS
            var rightHeight = $("body").height() - $("#topnav").outerHeight() - $(".j-tc-righttop-tab").outerHeight() - $(".tc-right-bottom").outerHeight() - 10;
            $(".tc-right-top,.visiterecord-rgt-scroll").height(rightHeight);
            $(".tc-right-top,.visiterecord-rgt-scroll").parent(".slimScrollDiv").height(rightHeight);
            //加载性弹层
            var divMaterialLoadAlert_topH = $(".divMaterialLoadAlert").height() - $(".divMaterialLoadAlert_bottom").outerHeight(true) - 35 - $(".divMaterialLoadAlert_center").outerHeight(true);
            $(".divMaterialLoadAlert_top").height(divMaterialLoadAlert_topH);
            $(".divMaterialLoadAlert_top").slimScroll({height: divMaterialLoadAlert_topH, borderRadius: "0px"});
            $(".divMaterialLoadAlert_top").parent(".slimScrollDiv").height(divMaterialLoadAlert_topH);
            //基础--搜索词
            var divMaterialLoadAlert_topmH = divMaterialLoadAlert_topH - 20;
            $(".materialpicture:not('.materialpictureNoCount')").height(divMaterialLoadAlert_topmH);
            $(".materialpicture:not('.materialpictureNoCount')").slimScroll({
                height: divMaterialLoadAlert_topmH,
                borderRadius: "0px"
            });
            $(".materialpicture:not('.materialpictureNoCount')").parent(".slimScrollDiv").height(divMaterialLoadAlert_topmH);
            //弹出层
            $(".divMaterialPhotoAlert-con,.divMaterialStandardAlert-con,.divMaterialCraftsAlert-con,.materialCLeftNavConScroll,.materialTopNavCon").height($(window).height() - $("#topnav").outerHeight(true) - $(".save_btn").outerHeight(true) - 38);
            $(".divMaterialPhotoAlert-con,.divMaterialStandardAlert-con,.materialCLeftNavConScroll,.materialTopNavCon").slimScroll({
                height: $(".divMaterialPhotoAlert-con").height(),
                borderRadius: "0px"
            });
            $(".divMaterialPhotoAlert-con,.divMaterialStandardAlert-con,.materialCLeftNavConScroll,.materialTopNavCon").parent(".slimScrollDiv").height($(".divMaterialPhotoAlert-con").height());
            //
            //$(".materialTopNavCon_m").height($(window).height() - $("#topnav").outerHeight(true) - $(".save_btn").outerHeight(true) - 38);
            //$(".materialTopNavCon_m").slimScroll({ height: $(".materialTopNavCon_m").height(), borderRadius: "0px" });
            //$(".materialTopNavCon_m").parent(".slimScrollDiv").height($(".materialTopNavCon_m").height());

            //$(".divMaterialCraftsAlert-con").height($(".gongyi_tit").height() - $(".headTable").outerHeight(true));
            $(".divMaterialCraftsAlert-con").slimScroll({
                height: $(".divMaterialCraftsAlert-con").height(),
                borderRadius: "0px"
            });
            $(".divMaterialCraftsAlert-con").parent(".slimScrollDiv").height($(".divMaterialCraftsAlert-con").height());
            ////品牌一弹窗
            //$(".materialTopNavCon").height($(window).height() - $("#topnav").outerHeight() - $(".divMaterialPhotoAlert-tit").outerHeight(true) - $(".save_btn").outerHeight(true) - 40);
            //$(".materialTopNavCon").slimScroll({ height: $(".materialTopNavCon").height(), borderRadius: "0px" });
            //$(".materialTopNavCon").parent(".slimScrollDiv").height($(".materialTopNavCon").height());
            //价格弹窗
            $(".priceScroll").height($(".divMaterialPriceSupAlert").height() - $(".divMaterialPriceSupAlert-tit").outerHeight(true) - $(".divMaterialPriceSupAlert-con table").outerHeight(true));
            $(".priceScroll").slimScroll({height: $(".priceScroll").height(), borderRadius: "0px"});
            $(".priceScroll").parent(".slimScrollDiv").height($(".priceScroll").height());
            //平台
            //$("#divTopSetContent").height($(".materialTopNavCon").height() - $(".platform_tit").outerHeight(true) - $(".ul_tab2divbox").outerHeight(true)+10);
            $("#divTopSetContent").height($(".divMaterialLoadAlert").height() - $(".materialTopNav_tit").outerHeight(true) - $(".platform_tit").outerHeight(true) - $(".ul_tab2divbox").outerHeight(true));
            $("#divTopSetContent").slimScroll({height: $("#divTopSetContent").height(), borderRadius: "0px"});
            $("#divTopSetContent").parent(".slimScrollDiv").height($("#divTopSetContent").height());
            //

            var rightHeight = $("body").height() - $("#topnav").outerHeight() - $(".j-tc-righttop-tab").outerHeight() - $(".tc-right-bottom").outerHeight() - 10;
            $(".tc-taskDiv").height(rightHeight - 22);
            $(".tc-taskDiv").width($("#divMaterialDetail .tc-right-top").width() - 102);
            $(".tc-taskDiv-history").height(rightHeight - 22);
            $(".tc-taskDiv-history").width($("#divMaterialDetail .tc-right-top").width() - 102);
            //专项
//            var zhuanxiangH = $(".analyItemCclickShow").height() - $(".analyItemCclickShow").find(".layerRtb-head").outerHeight(true) - $(".analyItemCclickShow").find(".analyItemCclickShow_bottom").outerHeight(true) - 10;
//            $(".analyItemCclickShowScroll").height(zhuanxiangH).slimScroll({height: zhuanxiangH, borderRadius: "0px"});
//            $(".analyItemCclickShowScroll").parent(".slimScrollDiv").height(zhuanxiangH);

        }


        function trchangecolor() {
            //隔行换色
            $("#tab2,#tab3,#tab4").find("tr").each(function () {
                $(this).removeClass("trshow");
                if ($(this).css("display") == "table-row") {
                    $(this).addClass("trshow");
                }
            });
            rxued.table.LChangeapart($("#tab2,#tab3,#tab4").find(".trshow"), "#fff", "#f9f9f9");
        }

        function visitedScroll() {
            //回访自动定位到最底部
            var ovisitedConH = $(".visiterecord-rgt-scroll-con").height();
            var ovisitedboxH = $(".visiterecord-rgt-scroll").height();
            $(".visiterecord-rgt-scroll").scrollTop(ovisitedConH - ovisitedboxH);

        }

        function auditVisitedScroll(objCon, objScroll) {
            var ovisitedConH = $(objCon).height();
            var ovisitedboxH = $(objScroll).height();
            $(objScroll).scrollTop(ovisitedConH - ovisitedboxH);
        }

        $(function () {
            visitedScroll()
            //图片移入显示放大删除按钮
            $(".uiImgUpload-gblock").each(function (index) {
                $(this).hover(function () {
                    if ($(this).parent("li").hasClass("uiImgUpload-finished")) {
                        $(this).find(".uiImgUpload-mark").show();
                    }
                }, function () {
                    $(this).find(".uiImgUpload-mark").hide();
                });
            });

            $(".materialquali li").each(function (index) {
                $(this).hover(function () {
                    if ($(this).hasClass("uiImgUpload-finished")) {
                        $(this).find(".uiImgUpload-mark").show();
                    }
                }, function () {
                    $(this).find(".uiImgUpload-mark").hide();
                });

            });

            //移入、移出问号
            $(".fixedTrangle").mouseenter(function () {
                $(this).find(".fixedtipscon").show()
            }).mouseleave(function () {
                $(this).find(".fixedtipscon").hide();
            });

            //点击专项搜索
            $(".contentbox_zhuan .serial_number").click(function () {
                $(".contentbox_zhuan .dailyftSearch").show()
            });
            $(".contentbox_zhuan .uiText-searchIcon").click(function () {
                $(".contentbox_zhuan .dailyftSearch").hide()
            });
            //点击子库搜索
            $(".serial_number_tree").click(function () {
                $(".contentbox_tree .dailyftSearch").show()
            });

            $(".contentbox_tree .uiText-searchIcon").click(function () {
                $(this).parents(".contentbox_tree .dailyftSearch").hide()
                LoadTreeListToSearch();//加载数据
            });

            //点击子库-审核 搜索
            $(".serial_number_tree_ver").click(function () {
                $(".contentbox_tree_ver .dailyftSearch").show()
            });
            $(".contentbox_tree_ver .uiText-searchIcon").click(function () {
                $(this).parents(".contentbox_tree_ver .dailyftSearch").hide()
                LoadTreeListToSearchToVer();//加载数据
            });
            //点击子库-管理 搜索
            $(".serial_number_tree_manage").click(function () {

                $(".contentbox_tree_manage .dailyftSearch").show();
            });
            $(".contentbox_tree_manage .uiText-searchIcon").click(function () {
                $(this).parents(".contentbox_tree_manage .dailyftSearch").hide()
                LoadTreeListToSearchToManage();//加载数据
            });

            //点击日期搜索
            $(".serial_number_data").click(function () {
                $(".dailyftSearch2").show();
            });
            $(".endTime").blur(function () {
                $(this).parents(".dailyftSearch2").hide();
            });

            $(".setup_nav").click();

            //中间JS
            rxued.table.LChangeapart($(".tc-table tr"), "#f9f9f9", "#fff");
            rxued.table.hoverChage($(".tc-table tr"), "#eaf4fe")

            countLeft();
            countMiddle();
            countRight();

        })
    </script>
    <script type="text/javascript">
        // 图片上传
        function initImageUpload() {//后台限制最大图片为4M
            setTimeout(function () {
                var uploadUrl = $("#hidFileUrl").val();
                $(".fileone").fileupload({
                    url: uploadUrl,
                    dataType: 'json',
                    autoUpload: false,
                    acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                    maxFileSize: 4000000
                }).on('fileuploadadd', function (e, data) {

                    data.submit();
                }).on('fileuploadprocessalways', function (e, data) {
                }).on('fileuploadprogressall', function (e, data) {
                }).on("fileuploadprogress", function (e, data) {
                }).on('fileuploaddone', function (e, data) {
                    // 图片上传成功回调函数 开始
                    var liObj = $(e.target).closest("li");
                    var tspID = $(liObj).attr("data-tspid");
                    var tspType = $(liObj).data("type");
                    var addType = $(liObj).data("add");
                    var matID = $(liObj).data("matid");//材料ID|标准ID
                    var matLevel = $(liObj).attr("data-matlevel");//档次
                    if (!matLevel) {
                        matLevel = 0;
                    }
                    var pClass = 2;
                    if (!matID || matID == "") {
                        pClass = 1;
                        matID = $("#hidBrandID").val();//标准ID
                    }

                    var json = data.result;
                    var photoUrl = json.BaseUrl + json.FileName;
                    // 校验是否更新或新增
                    if (tspID && tspID != "") {
                        if (tspID == "1") {
                            //新增 并插入数据
                            var obj = new Object();
                            obj.tspTreeFour = $("#hidTreeID").val();
                            obj.tspTSID = matID;
                            obj.tspType = tspType;
                            obj.tspPhotoURL = photoUrl;
                            obj.tspMatLevel = matLevel;
                            obj.tspClass = pClass;
                            if (tspType == "3" || tspType == "11") {
                                obj.tspParaID = $(liObj).data("paraid");
                                obj.tspCityID = $("#hidCityID").val();
                            }
                            var tiID = $(liObj).data("brand");
                            if (tiID) {
                                obj.tspBrandID = tiID;
                            }
                            obj.userType = $("#hidUserType").val();
                            obj.userNo = $("#hidUserNo").val();
                            obj.treeID = $("#hidTreeID").val();
                            obj.city = $("#hidCityID").val();
                            $.ajax({
                                type: 'POST',
                                url: basePath + "/tree_standard_photo-api/add",
                                contentType: 'application/json',
                                data: JSON.stringify(obj),
                                success: function (msg) {
                                    if (msg.statusMsg.indexOf("ok") >= 0) {
                                        $(liObj).attr("data-tspid", msg.statusMsg.substr(2));
                                        $(liObj).find("img").attr("src", photoUrl);
                                        $(liObj).find(".enlarge_link").attr("data-src", photoUrl);
                                        $(liObj).find(".uiImgUpload-first").remove();
                                    }
                                    else {
                                        alert(msg.statusMsg);
                                    }
                                },
                                error: function (err) {
                                    alert("操作出错！");
                                }
                            });

                        }
                        else {
                            var obj = new Object();
                            obj.tspID = tspID;
                            obj.tspPhotoURL = photoUrl;
                            obj.userType = $("#hidUserType").val();
                            obj.userNo = $("#hidUserNo").val();
                            obj.treeID = $("#hidTreeID").val();
                            obj.city = $("#hidCityID").val();
                            $.ajax({
                                type: 'POST',
//                                url: "/Public/UpdateMaterialPhoto",
//                                data: obj,
                                url: basePath + "/tree_standard_photo-api/update",
                                contentType: 'application/json',
                                data: JSON.stringify(obj),
                                success: function (msg) {
                                    if (msg.statusMsg == "success") {
                                        $(liObj).find("img").attr("src", photoUrl);
                                        $(liObj).find(".enlarge_link").attr("data-src", photoUrl);
                                        $(liObj).find(".uiImgUpload-first").remove();
                                    }
                                    else {
                                        alert(msg.statusMsg);
                                    }
                                },
                                error: function (err) {
                                    alert("操作出错！");
                                }
                            });
                        }
                    }
                    else {
                        //新增
                        if (addType && addType == "add") {
                            //针对先上传照片  在保存的
                            $(liObj).find("img").attr("src", photoUrl);
                            $(liObj).find(".enlarge_link").attr("data-src", photoUrl);
                            setTimeout(function () {
                                $(liObj).find(".uiImgUpload-first").remove();
                            }, 200);

                            if ($(liObj).hasClass("uiImgUpload-borred") == false) {
                                $(liObj).addClass("uiImgUpload-borred").removeClass("uiImgUpload-old");
                            }
                            if ($(liObj).hasClass("uiImgUpload-finished") == false) {
                                $(liObj).addClass("uiImgUpload-finished");
                                PhotoRelated();
                            }
                        }
                        else {
                            //新增 并插入数据
                            var obj = new Object();
                            obj.tspTreeFour = $("#hidTreeID").val();
                            obj.tspTSID = matID;
                            obj.tspType = tspType;
                            obj.tspPhotoURL = photoUrl;
                            obj.tspMatLevel = matLevel;
                            obj.tspClass = pClass;
                            if (tspType == "3" || tspType == "11") {
                                obj.tspParaID = $(liObj).data("paraid");
                                obj.tspCityID = $("#hidCityID").val();
                            }
                            var tiID = $(liObj).data("brand");
                            if (tiID) {
                                obj.tspBrandID = tiID;
                            }
                            obj.userType = $("#hidUserType").val();
                            obj.userNo = $("#hidUserNo").val();
                            obj.treeID = $("#hidTreeID").val();
                            obj.city = $("#hidCityID").val();
                            $.ajax({
                                type: 'POST',
//                            url: "/Public/AddMaterialPhoto",
//                            data: obj,
                                url: basePath + "/tree_standard_photo-api/add",
                                contentType: 'application/json',
                                data: JSON.stringify(obj),
                                success: function (msg) {
                                    if (msg.statusMsg.indexOf("ok") >= 0) {
                                        $(liObj).attr("data-tspid", msg.statusMsg.substr(2));
                                        $(liObj).find("img").attr("src", photoUrl);
                                        $(liObj).find(".enlarge_link").attr("data-src", photoUrl);
                                        $(liObj).find(".uiImgUpload-first").remove();
                                        if ($(liObj).hasClass("uiImgUpload-borred") == false) {
                                            $(liObj).addClass("uiImgUpload-borred").removeClass("uiImgUpload-old");
                                        }
                                        if ($(liObj).hasClass("uiImgUpload-finished") == false) {
                                            $(liObj).addClass("uiImgUpload-finished");
                                            PhotoRelated();
                                        }
                                    }
                                    else {
                                        alert(msg);
                                    }
                                },
                                error: function (err) {
                                    alert("操作出错！");
                                }
                            });
                        }
                    }
                    $(".uiImgUpload-gblock").each(function (index) {
                        $(this).hover(function () {
                            if ($(this).parent("li").hasClass("uiImgUpload-finished")) {
                                $(this).find(".uiImgUpload-mark").show();
                            }
                        }, function () {
                            $(this).find(".uiImgUpload-mark").hide();
                        });
                    });


                }).on('fileuploadfail', function (e, data) {
                });
                $(".fileone").removeClass("fileone")
            })

        }

        function initImageUploadAddItems() {//后台限制最大图片为2M
            setTimeout(function () {
                var uploadUrl = $("#hidFileUrl").val();
                $(".filemore").fileupload({
                    url: uploadUrl,
                    dataType: 'json',
                    autoUpload: false,
                    acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                    maxFileSize: 2 * 1024 * 1024,
                    messages: {
                        maxFileSize: 'error_size',
                        acceptFileTypes: 'error_type'
                    },
                    processfail: function (e, data) {
                        var currentFile = data.files[data.index];
                        if (data.files.error && currentFile.error) {
                            // there was an error, do something about it
                            console.log(currentFile);
                            if (currentFile.error == 'error_size') {
                                alert('超过允许的最大值！(图片要求：大小：2M以内；格式：jpg；)');
                            }
                            if (currentFile.error == 'error_type') {
                                alert('图片类型不符合要求！(图片要求：大小：2M以内；格式：jpg；)');
                            }
                        }
                        // 终止上传
                        data.abort();
                    },
                }).on('fileuploadadd', function (e, data) {
                    var liObj = $(e.target).closest("li");
                    var tspType = parseInt($(liObj).data("type"));
                    var matLevel = $(liObj).attr("data-matlevel");//档次
                    if (tspType == 4) {
                        if (!matLevel || matLevel == "0") {
                            alert("请选择主图的档次");
                            return false;
                        }
                    }
                    data.submit();
                }).on('fileuploadprocessalways', function (e, data) {
                }).on('fileuploadprogressall', function (e, data) {
                }).on("fileuploadprogress", function (e, data) {
                }).on('fileuploaddone', function (e, data) {
                    var liObj = $(e.target).closest("li");
                    var tspType = $(liObj).data("type");
                    var matID = $(liObj).data("matid");//材料ID|标准ID
                    var matLevel = $(liObj).attr("data-matlevel");//档次
                    if (!matLevel) {
                        matLevel = 0;
                    }
                    var addType = $(liObj).data("add");
                    var title = $(liObj).data("title");//标题
                    if (title && title != "") {
                        title = '<h4 class="h4title mb10">材料资质</h4>';
                    }
                    else {
                        title = "";
                    }
                    var pClass = 2;
                    if (!matID || matID == "") {
                        pClass = 1;
                        matID = $("#hidBrandID").val();//标准ID
                    }
                    var json = data.result;
                    var photoUrl = json.BaseUrl + json.FileName;

                    if (addType && addType == "add") {
                        //针对先上传照片  在保存的
                        $(liObj).before('<li data-add="add" data-type="' + tspType + '" class="uiImgUpload-finished">' + title + '<div class="brandlogopic brandlogopic_new"><img src="' + photoUrl + '" /><!--图片遮罩和放大按钮 开始--><div class="uiImgUpload-mark"><div class="uiImgUpload-mark-bg"></div><div class="uiImgUpload-mark-link"><div class="dis_il_block"><a href="jvascript:" class="upagain_link"><input type="file" class="fileone"></a><a href="javascript:" class="enlarge_link" data-src="' + photoUrl + '" data-imgname=""></a><a href="javascript:" class="delect_link"></a></div></div></div><!--图片遮罩和放大按钮 结束--></div></li>');


                        PhotoRelated();//显示控制操作
                        initImageUpload();//加载上传控件
                        return;
                    }
                    //新增
                    var obj = new Object();
                    obj.tspTreeFour = $("#hidTreeID").val();
                    obj.tspTSID = matID;
                    obj.tspType = tspType;
                    obj.tspPhotoURL = photoUrl;
                    obj.tspMatLevel = matLevel;
                    obj.tspClass = pClass;
                    var tiID = $(liObj).data("brand");
                    if (tiID) {
                        obj.tspBrandID = tiID;
                    }
                    obj.userType = $("#hidUserType").val();
                    obj.userNo = $("#hidUserNo").val();
                    obj.treeID = $("#hidTreeID").val();
                    obj.city = $("#hidCityID").val();
                    $.ajax({
                        type: 'POST',
//                        url: "/Public/AddMaterialPhoto",
//                        data: obj,
                        url: basePath + "/tree_standard_photo-api/add",
                        contentType: 'application/json',
                        data: JSON.stringify(obj),
                        success: function (msg) {
                            if (msg.statusMsg.indexOf("ok") >= 0) {
                                var tspID = msg.statusMsg.substr(2);
                                $(liObj).before('<li data-tspid="' + tspID + '" data-type="' + tspType + '" class="uiImgUpload-finished">' + title + '<div class="brandlogopic brandlogopic_new"><img src="' + photoUrl + '" /><!--图片遮罩和放大按钮 开始--><div class="uiImgUpload-mark"><div class="uiImgUpload-mark-bg"></div><div class="uiImgUpload-mark-link"><div class="dis_il_block"><a href="jvascript:" class="upagain_link"><input type="file" class="fileone"></a><a href="javascript:" class="enlarge_link" data-src="' + photoUrl + '" data-imgname=""></a><a href="javascript:" class="delect_link"></a></div></div></div><!--图片遮罩和放大按钮 结束--></div></li>');

                                PhotoRelated();//显示控制操作
                                initImageUpload();//加载上传控件
                            }
                            else {
                                alert(msg);
                            }

                        },
                        error: function (err) {
                            alert("操作出错！");
                        }
                    });


                }).on('fileuploadfail', function (e, data) {
                })
            })

        }

        //包装照片上传
        function initImageUploadPS() {//后台限制最大图片为4M
            setTimeout(function () {
                var uploadUrl = $("#hidFileUrl").val();
                $(".filepack").fileupload({
                    url: uploadUrl,
                    dataType: 'json',
                    autoUpload: false,
                    acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                    maxFileSize: 2 * 1024 * 1024,
                    messages: {
                        maxFileSize: 'error_size',
                        acceptFileTypes: 'error_type'
                    },
                    processfail: function (e, data) {
                        var currentFile = data.files[data.index];
                        if (data.files.error && currentFile.error) {
                            // there was an error, do something about it
                            console.log(currentFile);
                            if (currentFile.error == 'error_size') {
                                alert('超过允许的最大值！(图片要求：大小：2M以内；格式：jpg；)');
                            }
                            if (currentFile.error == 'error_type') {
                                alert('图片类型不符合要求！(图片要求：大小：2M以内；格式：jpg；)');
                            }
                        }
                        // 终止上传
                        data.abort();
                    },
                }).on('fileuploadadd', function (e, data) {
                    data.submit();
                }).on('fileuploadprocessalways', function (e, data) {
                }).on('fileuploadprogressall', function (e, data) {
                }).on("fileuploadprogress", function (e, data) {
                }).on('fileuploaddone', function (e, data) {
                    var divObj = $(e.target).closest("ul.brandmodel");

                    var json = data.result;
                    var photoUrl = json.BaseUrl + json.FileName;
                    var obj = new Object();
                    obj.treeFourID = $("#hidTreeID").val();
                    obj.photoUrl = photoUrl;// 包装照片

                    $.ajax({
                        type: 'POST',
                        url: basePath + "/tree_base_info-api/update_pack_photo",
                        data: obj,
                        success: function (msg) {
                            if (msg.statusMsg == "success") {
                                $(divObj).find("img").attr("src", photoUrl);
                                $(divObj).find(".enlarge_link").attr("data-src", photoUrl);
                                if ($(divObj).find(".uiImgUpload-first").length >= 1) {
                                    $(divObj).find(".uiImgUpload-gblock").addClass("packagedphoto");
                                    $(divObj).find(".uiImgUpload-first").remove();

                                    if ($(divObj).hasClass("uiImgUpload-borred") == false) {
                                        $(divObj).addClass("uiImgUpload-borred").removeClass("uiImgUpload-old");
                                    }
                                    if ($(divObj).hasClass("uiImgUpload-finished") == false) {
                                        $(divObj).addClass("uiImgUpload-finished");
                                    }
                                    PhotoRelated();
                                }

                                var opType = $(".anItemBor-active").data("type")
                                if (opType) {
                                    LoadMaterialDetailToTreeStandard(opType);//加载第三段
                                }
                            }
                            else {
                                alert(msg.statusMsg);
                            }
                        },
                        error: function (err) {
                            alert("操作出错！");
                        }
                    });

                }).on('fileuploadfail', function (e, data) {
                });
            })

        }
    </script>
    <script>
        // 材料标准点击
        $(document).on("click", ".lbl_strandard_qs_detectClick", function () {
            if ($(this).hasClass("lbl_strandard_qs_detectDown")) {
                $(this).addClass("lbl_strandard_qs_detectUp").removeClass("lbl_strandard_qs_detectDown");
                $(".lbl_strandard_qs_detect_show").stop().slideDown();
            } else {
                $(this).addClass("lbl_strandard_qs_detectDown").removeClass("lbl_strandard_qs_detectUp");
                $(".lbl_strandard_qs_detect_show").stop().slideUp();
            }
        })
        $(function () {
            /*左侧 JS*/
            //子库地区一段,左侧表格点击（材料）
            // TODO 子库地区一段,左侧表格点击（材料） $(document).on("click", "#tabDataToMaterial tr", function () AngularJS ng-click cityTwoState(treeFourID) 功能实现 暂时保留
            $(document).on("click", "#tabDataToMaterial tr", function () {
                $(this).addClass("tractive").siblings().removeClass("tractive");
                var treeID = $(this).attr("id");
                $("#hidTreeID").val(treeID);
                $("#hidLookType").val("");
                $("#hidMatID").val("");

                //加载材料详情
                LoadMaterialDetail(treeID, $("#hidCityID").val());
            });

            //左侧表格点击（圈中的材料）
            $(document).on("click", "#tabDataToMaterialVer tr", function () {
                $(this).addClass("tractive").siblings().removeClass("tractive");
                var treeID = $(this).attr("id");
                $("#hidTreeID").val(treeID);
                $("#hidLookType").val("1");
                $("#hidMatID").val("");

                //加载材料详情
                LoadMaterialDetailVer(treeID, $("#hidCityID").val());
            });

            //左侧表格点击（管理）
            $(document).on("click", "#tabDataToMaterialManage tr", function () {
                $(this).addClass("tractive").siblings().removeClass("tractive");
                var treeID = $(this).attr("id");
                $("#hidTreeID").val(treeID);
                $("#hidLookType").val("2");
                $("#hidMatID").val("");

                //加载材料详情
                LoadMaterialDetailToManage(treeID, $("#hidCityID").val());
            });


            /*中间 js*/
            //处理  点击详情
            $(document).on("click", "#divProcessing", function () {
                $(".anItemBor-active").removeClass("anItemBor-active");
                $(this).addClass("anItemBor-active");

                var obj = new Object();
                obj.treeID = $("#hidTreeID").val();
                obj.cityID = $("#hidCityID").val();
                obj.pageType = $("#hidPageType").val();
                $.ajax({
                    url: basePath + '/public-web/sublibrary/city_three_status/handle',
                    type: 'POST',
                    data: obj,
                    dataType: 'html',
                    success: function (result) {
                        $("#divAlertMaterialLoad").html(result);

                        $(".alertpaper").hide();
                        $(".divMaterialLoadAlert").show().animate({
                            left: 0
                        }, 200, function () {
                            countLeft();
                            countMiddle();
                            countRight();

                        });
                        countLeft();
                        countMiddle();
                        countRight();

                    },
                    error: function (e) {
                        console.error(e);
                    }
                });
            });


            //执行 详情
            $(document).on("click", ".operate-bottomDiv .execute-title", function () {
                var obj = new Object();
                obj.peID = $(this).data("peid");
                $.ajax({
                    url: "/Public/MaterialManageDetailToProcessExecute",
                    type: 'POST',
                    data: obj,
                    dataType: 'html',
                    success: function (result) {
                        $("#divAlertMaterialLoad").html(result);

                        $(".alertpaper").hide();
                        $(".divMaterialLoadAlert").show().animate({
                            left: 0
                        }, 200, function () {
                            countLeft();
                            countMiddle();
                            countRight();

                        });
                        countLeft();
                        countMiddle();
                        countRight();
                    },
                    error: function (e) {
                        console.error(e);
                    }
                });

            });

            /*右侧 JS*/

            /*新建任务*/
            $(document).on("click", ".NewAddBtn", function () {
                $(".NewAddBox").animate({"right": "0"}, 500).show();
            });
            $(document).on("click", ".closeNewAddBox", function () {
                $(".NewAddBox").animate({"right": "-100%"}, 500);
            });
        });
    </script>
    <script>
        //公用的方法

        //材料检索
        function LoadTreeListToSearch() {

            //整体控制
            var liObj = $(".daily_title_tree li.title_cur");
            var type = $(liObj).data("type");
            var sBase = "";
            if (type == "1") {//已完成
                sBase = "[data-state=1]";
            }
            else if (type == "2") {//未完成
                sBase = "[data-state=2]";
            }
            else if (type == "3") {//待办任务
                sBase = "[zz-task=1]";
            }
            else if (type == "4") {//待审核-新增品牌|价格变更
                sBase = "[zz-check=1]";
            }
            else if (type == "5") {//下达地方任务
                sBase = "[zz-send=1]";
            }
            else if (type == "6") {//缺失材料商
                sBase = "[zz-user=0]";
            }
            else if (type == 99) {
                sBase = "[zz-top=1]";
            }
            $("#divStateStatistics .StatisAllCount").text($("#tabDataToMaterial tr" + sBase).length);
            $("#divStateStatistics .cGreen").text($("#tabDataToMaterial tr" + sBase + "[zz-mark=1]").length);
            $("#divStateStatistics .cOrange").text($("#tabDataToMaterial tr" + sBase + "[zz-mark=2]").length);
            $("#divStateStatistics .cRed").text($("#tabDataToMaterial tr" + sBase + "[zz-mark=3]").length);

            //控制待办任务是否变红
            var iTitleNewObj = $(".daily_title_tree li[data-type=3]").find("i.titleNew");
            if ($("#tabDataToMaterial tr[zz-task=1]").length > 0) {
                if (iTitleNewObj.length == 0) {
                    $(".daily_title_tree li[data-type=3]").append('<i class="titleNew"></i>');
                }
            }
            else {
                if (iTitleNewObj.length > 0) {
                    $(iTitleNewObj).remove();
                }
            }
            //控制待审核 是否变红
            var iTitleNewObj2 = $(".daily_title_tree li[data-type=4]").find("i.titleNew");
            if ($("#tabDataToMaterial tr[zz-check=1]").length > 0) {
                if (iTitleNewObj2.length == 0) {
                    $(".daily_title_tree li[data-type=4]").append('<i class="titleNew"></i>');
                }
            }
            else {
                if (iTitleNewObj2.length > 0) {
                    $(iTitleNewObj2).remove();
                }
            }
            //控制下达地方任务 是否变红
            var iTitleNewObj3 = $(".daily_title_tree li[data-type=5]").find("i.titleNew");
            if ($("#tabDataToMaterial tr[zz-sends=1]").length > 0) {
                if (iTitleNewObj3.length == 0) {
                    $(".daily_title_tree li[data-type=5]").append('<i class="titleNew"></i>');
                }
            }
            else {
                if (iTitleNewObj3.length > 0) {
                    $(iTitleNewObj3).remove();
                }
            }
            //控制无供货商的材料 是否变红
            var iTitleNewObj4 = $(".daily_title_tree li[data-type=6]").find("i.titleNew");
            if ($("#tabDataToMaterial tr[zz-user=0]").length > 0) {
                if (iTitleNewObj4.length == 0) {
                    $(".daily_title_tree li[data-type=6]").append('<i class="titleNew"></i>');
                }
            }
            else {
                if (iTitleNewObj4.length > 0) {
                    $(iTitleNewObj4).remove();
                }
            }
            //控制审核不合格是否变红
            var iTitleNewObj5 = $(".daily_title_tree li[data-type=99]").find("i.titleNew");
            if ($("#tabDataToMaterial tr[zz-quan=1]").length > 0) {
                if (iTitleNewObj5.length == 0) {
                    $(".daily_title_tree li[data-type=99]").append('<i class="titleNew"></i>');
                }
            }
            else {
                if (iTitleNewObj5.length > 0) {
                    $(iTitleNewObj5).remove();
                }
            }

            //控制检索
            var strSS = "";
            var treeFour = $("#ddlTreeFour").val();
            var treeThree = $("#ddlTreeThree").val();
            var treeTwo = $("#ddlTreeTwo").val();
            var treeOne = $("#ddlTreeOne").val();
            if (treeFour != "") {
                strSS = "[data-four=" + treeFour + "]";
            }
            else if (treeThree != "") {
                strSS = "[data-three=" + treeThree + "]";
            }
            else if (treeTwo != "") {
                strSS = "[data-two=" + treeTwo + "]";
            }
            else if (treeOne != "") {
                strSS = "[data-one=" + treeOne + "]";
            }
            //var topSet = parseInt($("#ddlTopMaterial").val());//置顶材料
            //if (topSet == 1) {
            //    strSS = "[zz-top=1]";
            //}

            var ssContent = $("#txtTreeSearch").val();
            if (ssContent != "" && ssContent != $("#txtTreeSearch").attr("placeholder")) {
                strSS += "[zz-search*=" + ssContent + "]";
            }

            var mark = $("#hidMark").val();
            if (mark != "" && mark != "0") {
                strSS += "[zz-mark=" + mark + "]";
            }

            $("#tabDataToMaterial tr").hide();
            $("#tabDataToMaterial tr" + sBase + strSS).show();

            if ($("#hidType").val() != "1") {
                if ($("#tabDataToMaterial tr" + sBase + strSS).length > 0) {
                    //默认点击第一项
                    $("#tabDataToMaterial tr" + sBase + strSS + ":eq(0)").click();
                    $("#divMaterialDetail").show();
                }
                else {
                    $("#divMaterialDetail").hide();
                }
            }
            else {
                $("#hidType").val("0");
            }

            trchangecolor();//隔行换色
        }

        //材料检索（圈中的）
        function LoadTreeListToSearchToVer() {
            //整体控制
            var liObj = $(".daily_title_tree_ver ul.fr li.title_cur");//历史是否选中
            var sBase = "[ver-history=0]";
            if (liObj.length > 0) {
                sBase = "[ver-history=1]";
            }

            $("#divStateStatisticsVer .StatisAllCount").text($("#tabDataToMaterialVer tr" + sBase).length);
            $("#divStateStatisticsVer .cGreen").text($("#tabDataToMaterialVer tr" + sBase + "[zz-mark=1]").length);
            $("#divStateStatisticsVer .cOrange").text($("#tabDataToMaterialVer tr" + sBase + "[zz-mark=2]").length);
            $("#divStateStatisticsVer .cRed").text($("#tabDataToMaterialVer tr" + sBase + "[zz-mark=3]").length);

            //控制是否变红（一审）
            var iTitleVerOne = $(".daily_title_tree_ver li[data-type=1]").find("i.titleNew");
            if ($("#tabDataToMaterialVer tr[ver-one=1]").length > 0) {
                if (iTitleVerOne.length == 0) {
                    $(".daily_title_tree_ver li[data-type=1]").append('<i class="titleNew"></i>');
                }
            }
            else {
                if (iTitleVerOne.length > 0) {
                    $(iTitleVerOne).remove();
                }
            }
            //控制是否变红（二审）
            var iTitleVerTwo = $(".daily_title_tree_ver li[data-type=2]").find("i.titleNew");
            if ($("#tabDataToMaterialVer tr[ver-two=1]").length > 0) {
                if (iTitleVerTwo.length == 0) {
                    $(".daily_title_tree_ver li[data-type=2]").append('<i class="titleNew"></i>');
                }
            }
            else {
                if (iTitleVerTwo.length > 0) {
                    $(iTitleVerTwo).remove();
                }
            }
            /*
            //控制是否变红（三审）
            var iTitleVerThree = $(".daily_title_tree_ver li[data-type=4]").find("i.titleNew");
            if ($("#tabDataToMaterialVer tr[ver-three=1]").length > 0) {
                if (iTitleVerThree.length == 0) {
                    $(".daily_title_tree_ver li[data-type=4]").append('<i class="titleNew"></i>');
                }
            }
            else {
                if (iTitleVerThree.length > 0) {
                    $(iTitleVerThree).remove();
                }
            }
            */

            //控制检索
            var strSS = "";
            var treeFour = $("#ddlTreeFourVer").val();
            var treeThree = $("#ddlTreeThreeVer").val();
            var treeTwo = $("#ddlTreeTwoVer").val();
            var treeOne = $("#ddlTreeOneVer").val();
            if (treeFour != "") {
                strSS = "[data-four=" + treeFour + "]";
            }
            else if (treeThree != "") {
                strSS = "[data-three=" + treeThree + "]";
            }
            else if (treeTwo != "") {
                strSS = "[data-two=" + treeTwo + "]";
            }
            else if (treeOne != "") {
                strSS = "[data-one=" + treeOne + "]";
            }
            var ssContent = $("#txtTreeSearchVer").val();
            if (ssContent != "" && ssContent != $("#txtTreeSearchVer").attr("placeholder")) {
                strSS += "[zz-search*=" + ssContent + "]";
            }

            var mark = $("#hidMark").val();
            if (mark != "" && mark != "0") {
                strSS += "[zz-mark=" + mark + "]";
            }

            $("#tabDataToMaterialVer tr").hide();
            $("#tabDataToMaterialVer tr" + sBase + strSS).show();

            if ($("#hidType").val() != "1") {
                if ($("#tabDataToMaterialVer tr" + sBase + strSS).length > 0) {
                    //默认点击第一项
                    $("#tabDataToMaterialVer tr" + sBase + strSS + ":eq(0)").click();
                }
                else {
                    $("#divMaterialDetail").html("");
                }
            }
            else {
                $("#hidType").val("0");
            }

            trchangecolor();//隔行换色
        }


        //材料检索
        function LoadTreeListToSearchToManage() {
            //整体控制
            var liObj = $(".daily_title_tree_manage ul.fr li.title_cur");//历史是否选中
            var sBase = "";

            $("#divStateStatisticsManage .StatisAllCount").text($("#tabDataToMaterialManage tr" + sBase).length);
            $("#divStateStatisticsManage .cGreen").text($("#tabDataToMaterialManage tr" + sBase + "[zz-mark=1]").length);
            $("#divStateStatisticsManage .cOrange").text($("#tabDataToMaterialManage tr" + sBase + "[zz-mark=2]").length);
            $("#divStateStatisticsManage .cRed").text($("#tabDataToMaterialManage tr" + sBase + "[zz-mark=3]").length);


            //控制检索
            var strSS = "";
            var treeFour = $("#ddlTreeFourManage").val();
            var treeThree = $("#ddlTreeThreeManage").val();
            var treeTwo = $("#ddlTreeTwoManage").val();
            var treeOne = $("#ddlTreeOneManage").val();
            if (treeFour != "") {
                strSS = "[data-four=" + treeFour + "]";
            }
            else if (treeThree != "") {
                strSS = "[data-three=" + treeThree + "]";
            }
            else if (treeTwo != "") {
                strSS = "[data-two=" + treeTwo + "]";
            }
            else if (treeOne != "") {
                strSS = "[data-one=" + treeOne + "]";
            }
            var ssContent = $("#txtTreeSearchManage").val();
            if (ssContent != "" && ssContent != $("#txtTreeSearchManage").attr("placeholder")) {
                strSS += "[zz-search*=" + ssContent + "]";
            }

            var mark = $("#hidMark").val();
            if (mark != "" && mark != "0") {
                strSS += "[zz-mark=" + mark + "]";
            }

            $("#tabDataToMaterialManage tr").hide();
            $("#tabDataToMaterialManage tr" + sBase + strSS).show();

            //hidType ？？？？？？？？？
            if ($("#hidType").val() != "2") {
                if ($("#tabDataToMaterialManage tr" + sBase + strSS).length > 0) {
                    //默认点击第一项
                    $("#tabDataToMaterialManage tr" + sBase + strSS + ":eq(0)").click();
                }
                else {
                    $("#divMaterialDetail").html("");
                }
            }
            else {
                $("#hidType").val("0");
            }

            trchangecolor();//隔行换色
        }

        //加载地区材料列表
        function loadCityDetail() {
//            $("#divCityDetail").html("");
            //$.ajax({
            //    url: "/BMaterial/MaterialManageDetailToCity",
            //    type: 'POST',
            //    data: { cityID: $("#hidCityID").val() },
            //    dataType: 'html',
            //    success: function (result) {
            //        $("#divCityDetail").html(result);

            //        countLeft();
            //        rxued.areaSwitch.Tab($('#divCityDetail .j_uiTab9'), 'uiTab9-active', $('#divCityDetail .uiTab9Con'), 'click', function () {
            //            countLeft();
            //            countMiddle();
            //            countRight();
            //        });
            //    },
            //    error: function (e) {
            //        console.error(e);
            //    }
            //});

            $("#tabDataToMaterial").html("");
//            $.ajax({
//                url: "/BMaterial/MaterialManageDetailToTreeRows",
//                type: 'POST',
//                data: { cityID: $("#hidCityID").val() },
//                dataType: 'html',
//                success: function (result) {
//                    $("#tabDataToMaterial").html(result);
//
//                    LoadTreeListToSearch();
//                    countLeft();
//                },
//                error: function (e) {
//                    console.error(e);
//                }
//            });
        }


        //加载回访后数据
        function LoadTable(treeID, cityID, visitMark) {

            var trObj = $("#tabDataToMaterial tr[id=" + treeID + "]");
            $(trObj).find("td:eq(3)").html(720);
            $(trObj).attr("zz-mark", visitMark);
            var trLastObj = $("#tabDataToMaterial tr:last");
            trObj.insertAfter(trLastObj);

            LoadTreeListToSearch();
        }

        //加载回访记录
        function loadVisit() {
            $("#j-visit .visiterecord-rgt-scroll-con").load(basePath + '/public-web/sublibrary/city/visit',function(){
                setTimeout(function(){
                    visitedScroll();
                    $("#j-visit").scrollTop($(".visiterecord-rgt-scroll-con")[0].scrollHeight);
                },200)

            });
            auditVisitedScroll($(".auditLogCon"), $(".auditLogScroll"));
//            var obj = new Object();
//            obj.v_piID = $("#hidTreeID").val();
//            obj.v_userID = $("#hidCityID").val();
//            obj.v_type = 100;
//            $.ajax({
//                url: "/SuppliersManage/VisitRows",
//                type: 'POST',
//                 data: obj,
//                dataType: 'html',
//                success: function (result) {
//                    $("#j-visit .visiterecord-rgt-scroll-con").html(result);
//                    auditVisitedScroll($(".auditLogCon"), $(".auditLogScroll"));
//                },
//                error: function (e) {
//                    console.error(e);
//                }
//            });
        }
    </script>
    <script>
        //保存数据 JS
        //品牌变更
        function SaveSuccess() {
            var level = $("#hidMatLevel").val();
//            var divID = "";
//            switch (level) {
//                case 1:
//                case "1"://加载  A
//                {
//                    divID = "divMaterialDetailToA";
//                }
//                    break;
//                case 2:
//                case "2"://加载  B
//                {
//                    divID = "divMaterialDetailToB";
//                }
//                    break;
//                case 4:
//                case "4"://加载  C
//                {
//                    divID = "divMaterialDetailToC";
//                }
//                    break;
//                default:
//                    break;
//            }
//            if (divID != "") {
//                $("#" + divID).html("");
//            }
            $(".j_uiTab9 li[data-level=" + level + "]").click();
            CloseALL();
        }

        $(function () {
            var bbSave = true;
            //添加品牌
            $(document).on("click", "#btnAddBrand", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var obj = CheckFromToAddBrand();
                if (obj == false) {
                    bbSave = true;
                    return false;
                }

                var strData = "";
                //获取上传照片
                var items = [];
                $("#divAlertMaterialLoad li[data-type]").each(function () {
                    var tspType = $(this).data("type");
                    var img = $(this).find("img").attr("src");
                    var txtTitle = $(this).find(".txtTitle");
                    var title = "";
                    if (txtTitle.length > 0) {
                        title = $(txtTitle).val();
                        if (title == $(txtTitle).attr("placeholder")) {
                            title = "";
                        }
                    }
                    if (title != "" || (img && img != "")) {
                        var objImg = {};
                        objImg.tspType = tspType;
                        objImg.tspPhotoURL = img;
                        objImg.tspTitle = title;
                        items.push(objImg);
                        strData += tspType + "♣" + img + "♣" + title + "♠";
                    }
                });
                obj.photoItems = items;
                obj.strData = strData;
                $.ajax({
                    type: "POST",
//                    url: "/Public/AddMaterialBaseInfo",
                    url: basePath + "/material-api/brand_item/add",
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (data) {
                        if (data.statusMsg == "success") {
                            // 重新绑定品牌
                            var level = $("#hidMatLevel").val();
                            $("#hidLevelAddFlag").val(level);
                            $(".j_uiTab9 li[data-level=" + level + "]").click();
                            CloseALL();
                            bbSave = true;
                        }
                        else {
                            alert(data.statusMsg);
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("保存出错！");
                        bbSave = true;
                    }
                });
            });

            //保存品牌
            $(document).on("click", "#btnSaveBrand", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var obj = CheckFromToAddBrand();
                if (obj == false) {
                    bbSave = true;
                    return false;
                }
                obj.m_ID = $("#hidMatID").val();

                $.ajax({
                    type: "POST",
                    url: "/Public/SaveMaterialBaseInfo",
                    data: obj,
                    success: function (data) {
                        if (data == "ok") {
                            SaveSuccess();//重新绑定品牌

                            bbSave = true;
                        }
                        else {
                            alert(data);
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("保存出错！");
                        bbSave = true;
                    }
                });
            });



            //保存价格
            $(document).on("click", ".btnSavePriceToMaterial", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }
                var divObj = $(this).closest("div.analyItem");
                var matID = $(divObj).data("matid");
                var matLevel = $(divObj).data("matlevel");

                var costPrice = parseFloat($(divObj).find("#txtCostPrice").val());//成本价
                if (costPrice === "" || isNaN(costPrice) || costPrice == 0) {
                    costPrice = 0;
                }
                else if (costPrice < 0) {
                    alert("成本价不能为负数！");
                    $(divObj).find("#txtCostPrice").focus();
                    bbSave = true;
                    return false;
                }
                var quotesPrice = $(divObj).find("#txtQuotesPrice").val();//报价
                if (quotesPrice === "" || isNaN(quotesPrice) || quotesPrice == 0) {
                    quotesPrice = 0;
                }
                else if (quotesPrice < 0) {
                    alert("报价不能为负数！");
                    $(divObj).find("#txtQuotesPrice").focus();
                    bbSave = true;
                    return false;
                }
                var installPrice = $(divObj).find("#txtInstallPrice").val();//安装价
                if (installPrice === "" || isNaN(installPrice)) {
                    installPrice = 0;
                }
                else if (installPrice < 0) {
                    alert("安装价不能为负数！");
                    $(divObj).find("#txtInstallPrice").focus();
                    bbSave = true;
                    return false;
                }
                var obj = new Object();
                obj.m_CostPrice = costPrice;
                obj.m_QuotesPrice = quotesPrice;
                obj.m_InstallPrice = installPrice;
                obj.m_ID = matID;
                obj.m_TreeFour = $("#hidTreeID").val();
                obj.m_CityID = $("#hidCityID").val();//地区
                obj.m_Level = matLevel;
                obj.userType = $("#hidUserType").val();//操作人类型
                obj.userNo = $("#hidUserNo").val();//操作人

                $.ajax({
                    type: "POST",
                    url: "/Public/SaveMaterialBaseInfoToPrice",
                    data: obj,
                    success: function (data) {
                        if (data == "ok") {
                            $(".anItemBor-active").click();

                            bbSave = true;
                        }
                        else {
                            alert(data);
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("保存出错！");
                        bbSave = true;
                    }
                });
            });

            // 材料处理:按钮样式切换
            $(document).on("click", "#handle .choice-span", function () {
                $(this).addClass("current").siblings().removeClass("current");
            });
            // 添加处理
            $(document).on("click", "#buttonClick", function () {
                if ($("#handle .current").attr("handtype") == undefined || $("#handleContent").val() == "") {
                    alert("请选择处理类型填写处理内容");
                    return;
                }
                var obj = {};
                obj.vmhType = $("#handle .current").attr("handtype");
                obj.vmhCityid = $("#hidCityID").val();
                obj.vmhContent = $("#handleContent").val();
                obj.vmhMainobject = $("#hidTreeID").val();
                obj.vmhUsercard = $("#hidUserNo").val();
                obj.vmhUsertype = 1;
                obj.visitType = 100;

                $.ajax({
                    url: basePath + '/material_handle-api/save',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (msg) {
                        if (msg.statusMsg != "success") {
                            alert(msg.statusMsg);
                            return;
                        }
                        // 判断下架提示
                        if (obj.vmhType == 4) {
                            $(".tractive").remove();
                            $(".div_main_data").hide();
                            alert("下架成功")
                            return;
                        }
                        debugger;
                        var trObj = $("#tabDataToMaterial tr[id=" + obj.vmhMainobject + "]");
                        trObj.find(".cityOneVisit").html(720);
                        trObj.find(".cityOneVisit").removeClass('cRed');

                        $(trObj).attr("zz-mark", obj.vmhType);
                        debugger;
                        trObj.find("td[data-wei]").hide();
                        if (obj.vmhType == 1) {
                            trObj.find("td[data-wei='1']").removeClass("ng-hide").show();
                        } else if (obj.vmhType == 2) {
                            trObj.find("td[data-wei='2']").removeClass("ng-hide").show();
                        }
                        else if (obj.vmhType == 3) {
                            trObj.find("td[data-wei='3']").removeClass("ng-hide").show();
                        }
                        var trLastObj = $("#tabDataToMaterial tr:last");
                        trObj.insertAfter(trLastObj);

                        LoadTreeListToSearch();//搜索

                    },
                    error: function (err) {
                        alert("操作出错！");
                    }
                });
            })

            //添加回访
            $(document).on("click", ".hf-submit", function () {
                debugger
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }
                var checkA = $(".topdaily a.active");
                if (checkA.length == 0) {
                    alert("请选择重要性");
                    bbSave = true;
                    return false;
                }
                var visitMark = $(checkA).data("val");

                var visitContent = $("#txtVisitContent").val();
                if (visitContent == "" || visitContent == $("#txtVisitContent").attr("placeholder")) {
                    $("#txtVisitContent").focus();
                    bbSave = true;
                    return false;
                }

                var obj = new Object();
                obj.vPiid = $("#hidTreeID").val(); // 项目ID/四级科目ID
                obj.vUserid = $("#hidCityID").val(); // 地区
                obj.vContent = visitContent; // 回访内容
                obj.vMark = visitMark; // 回访类型：1项目，2材料商，3配送单,100材料子库,200集团主案
                obj.vKahao = $("#hidUserNo").val(); // 回访人卡号
                obj.vType = 100; // 回访类型：1项目，2材料商，3配送单,100材料子库,200集团主案
                obj.vOperattype = 1; // 操作人类型：1集团，2地方，3集团工程：>=10相减当二次类型

                /*提交回访*/
                $.ajax({
                    type: 'POST',
//                    url: "/Public/AddVisitInfo",
                    url: basePath + "/visit-api/save",
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (msg) {
                        if (msg.statusMsg != "success") {
                            alert(msg.statusMsg);
                            bbSave = true;
                        }
                        else {
                            loadVisit();
                            //清空内容
                            $(".topdaily").find(".topdailybtn").removeClass("active");
                            $(".dailyrgt-divZi").show();
                            $(".visi-text-content").hide();
                            $(".dailyrgt-botradio").hide();
                            $("#txtVisitContent").val("");
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("添加回访出错！");
                        bbSave = true;
                    }
                });
            });

            //删除图片信息
            $(document).on("click", "li a.delect_link", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var liObj = $(this).closest("li");
                var tspID = $(liObj).attr("data-tspid");
                var matID = $(liObj).attr("data-matid");
                if (!tspID || tspID == "") {
                    $(liObj).remove();
                    bbSave = true;
                    return false;
                }

                if (confirm("确认删除图片信息？") == false) {
                    bbSave = true;
                    return false;
                }

                var obj = new Object();
                obj.tspID = tspID;
                obj.userType = $("#hidUserType").val();
                obj.userNo = $("#hidUserNo").val();
                obj.treeID = $("#hidTreeID").val();
                obj.city = $("#hidCityID").val();
                $.ajax({
                    type: 'POST',
//                    url: "/Public/DeleteTreeStandardPhoto",
                    url: basePath + "/tree_standard_photo-api/delete",
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            // TODO 品牌型号图片删除 2 重新加载页面
                            var photoType = $(liObj).attr("data-type");
                            if (photoType == 2) {
                                $("#brand" + matID).click();
                            } else {
                                $(liObj).remove();
                            }

                        }
                        else {
                            alert(msg);
                        }


                        bbSave = true;
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });

            });

            //照片新增 保存
            $(document).on("click", "li .saveNewPhoto", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var saveObj = $(this);
                var liObj = $(this).closest("li");
                var tspID = $(liObj).attr("data-tspid");
                if (tspID == "1") {
                    alert("请先上传照片！");
                    bbSave = true;
                    return false;
                }
                var txtTitle = $(liObj).find(".txtTitle");
                var txtContent = $(liObj).find(".txtContent");
                var title = $(txtTitle).val();
                if (title == "" || title == $(txtTitle).attr("placeholder")) {
                    alert("请输入内容！");
                    $(txtTitle).focus();
                    bbSave = true;
                    return false;
                }
                var content = $(txtContent).val();
                if (content == "" || content == $(txtContent).attr("placeholder")) {
                    alert("请输入内容！");
                    $(txtContent).focus();
                    bbSave = true;
                    return false;
                }

                var obj = new Object();
                obj.tspTitle = title;
                obj.tspContent1 = content;
                obj.userType = $("#hidUserType").val();
                obj.userNo = $("#hidUserNo").val();
                obj.treeID = $("#hidTreeID").val();
                obj.city = $("#hidCityID").val();
                if (tspID && tspID != "") {
                    //保存
                    obj.tspID = tspID;

                    $.ajax({
                        type: 'POST',
//                        url: "/Public/SaveMaterialPhotoOterText",
//                        data: obj,
                        url: basePath + "/tree_standard_photo-api/para/update",
                        contentType: 'application/json',
                        data: JSON.stringify(obj),
                        success: function (msg) {
                            if (msg.statusMsg == "success") {
                                $(txtTitle).attr("zz-old", title);
                                $(txtContent).attr("zz-old", content);

                                $(saveObj).hide();//隐藏按钮
                            }
                            else {
                                alert(msg);
                            }
                            bbSave = true;
                        },
                        error: function (err) {
                            alert("操作出错！");
                            bbSave = true;
                        }
                    });
                }
                else {
                    //新增
                    var photoUrl = $(liObj).find("img").attr("src");
                    if (!photoUrl || photoUrl == "") {
                        alert("请上传相应照片！");
                        $(txtContent).focus();
                        bbSave = true;
                        return false;
                    }

                    var tspType = $(liObj).data("type");
                    var matID = $(liObj).data("matid");//材料ID|标准ID
                    if (!matID || matID == "") {
                        matID = $("#hidBrandID").val();//标准ID
                    }
                    obj.tspTreeFour = $("#hidTreeID").val();
                    obj.tspTSID = matID;
                    obj.tspType = tspType;
                    obj.tspPhotoURL = photoUrl;

                    $.ajax({
                        type: 'POST',
//                        url: "/Public/AddMaterialPhoto",
                        url: basePath + "/tree_standard_photo-api/add",
                        contentType: 'application/json',
                        data: JSON.stringify(obj),
                        success: function (msg) {
                            if (msg.statusMsg.indexOf("ok") >= 0) {
                                var tspID = msg.statusMsg.substr(2);
                                $(liObj).attr("data-tspid", tspID);
                                $(txtTitle).attr("zz-old", title);
                                $(txtContent).attr("zz-old", content);

                                $(saveObj).hide();//隐藏按钮
                            }
                            else {
                                alert(msg);
                            }
                            bbSave = true;
                        },
                        error: function (err) {
                            alert("操作出错！");
                            bbSave = true;
                        }
                    });
                }

            });

            //品牌审核打回
            $(document).on("click", "#btnBrandCheckBack", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var checkRemark = $("#txtCheckRemarkNewBrand").val();
                if (checkRemark == "" || $("#txtCheckRemarkNewBrand").attr("placeholder")) {
                    alert("请输入打回备注！");
                    $("#txtCheckRemarkNewBrand").focus();
                    bbSave = true;
                    return false;
                }

                var obj = new Object();
                obj.mID = $("#hidMatID").val();
                obj.mUpdateState = -2;
                obj.mRemark = checkRemark; // 打回备注
                obj.saveType = 5; // 操作类型 5品牌审核打回
                $.ajax({
                    type: 'POST',
                    url: "/BMaterial/ProcessMaterialNewBrandCheck",
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (msg) {
                        if (msg == "ok") {
                            var treeID = $("#hidTreeID").val();
                            var trObj = $("#tabDataToMaterial tr[id=" + treeID + "]");
                            var wcCount = parseInt($(trObj).attr("zz-ccount")) - 1;
                            if (wcCount <= 0) {
                                wcCount = 0;
                                $(trObj).attr("zz-check", "0");
                            }
                            $(trObj).attr("zz-ccount", wcCount);
                            if (wcCount == 0) {
                                LoadTreeListToSearch();//整体绑定
                            }
                            else {
                                SaveSuccess();//重新绑定品牌
                            }
                            $(".bohui-alert .close").click();
                            bbSave = true;
                        }
                        else {
                            alert(msg);
                        }
                        bbSave = true;
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });

            });

            //品牌审核通过
            $(document).on("click", ".btnBrandCheckPass", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                if (confirm("确认品牌无误，通过成为正式材料？") == false) {
                    bbSave = true;
                    return false;
                }

                var obj = new Object();
                obj.mID = $("#hidMatID").val();
                obj.mUpdateState = 1;
                obj.saveType = 4; // 操作类型 4 品牌审核通过
                $.ajax({
                    type: 'POST',
                    url: "/BMaterial/rocessMaterialNewBrandCheck",
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (msg) {
                        if (msg == "ok") {
                            var treeID = $("#hidTreeID").val();
                            var trObj = $("#tabDataToMaterial tr[id=" + treeID + "]");
                            var wcCount = parseInt($(trObj).attr("zz-ccount")) - 1;
                            if (wcCount <= 0) {
                                wcCount = 0;
                                $(trObj).attr("zz-check", "0");
                            }
                            $(trObj).attr("zz-ccount", wcCount);
                            if (wcCount == 0) {
                                LoadTreeListToSearch();//整体绑定
                            }
                            else {
                                SaveSuccess();//重新绑定品牌
                            }

                            bbSave = true;
                        }
                        else {
                            alert(msg);
                        }
                        bbSave = true;
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });

            });


            //品牌审核（二次修改审核）打回
            $(document).on("click", "#btnBrandCheckLastToBack", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var checkRemark = $("#txtCheckRemarkNewBrand").val();
                if (checkRemark == "" || $("#txtCheckRemarkNewBrand").attr("placeholder")) {
                    alert("请输入打回备注！");
                    $("#txtCheckRemarkNewBrand").focus();
                    bbSave = true;
                    return false;
                }

                var obj = new Object();
                obj.mch_MID = $("#hidMatID").val();
                obj.mch_remark = checkRemark;
                obj.mch_jincheng = 2;
                $.ajax({
                    type: 'POST',
                    url: "/BMaterial/ProcessMaterialBrandSecondCheck",
                    data: obj,
                    success: function (msg) {
                        if (msg == "ok") {
                            var treeID = $("#hidTreeID").val();
                            var trObj = $("#tabDataToMaterial tr[id=" + treeID + "]");
                            var wcCount = parseInt($(trObj).attr("zz-ccount")) - 1;
                            if (wcCount <= 0) {
                                wcCount = 0;
                                $(trObj).attr("zz-check", "0");
                            }
                            $(trObj).attr("zz-ccount", wcCount);
                            if (wcCount == 0) {
                                LoadTreeListToSearch();//整体绑定
                            }
                            else {
                                SaveSuccess();//重新绑定品牌
                            }
                            $(".bohui-alert .close").click();
                            bbSave = true;
                        }
                        else {
                            alert(msg);
                        }
                        bbSave = true;
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });

            });

            //品牌审核（二次修改审核）通过
            $(document).on("click", ".btnBrandCheckLastToPass", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                if (confirm("确认审核通过？") == false) {
                    bbSave = true;
                    return false;
                }

                var obj = new Object();
                obj.mch_MID = $("#hidMatID").val();
                obj.mch_jincheng = 1;
                $.ajax({
                    type: 'POST',
                    url: "/BMaterial/ProcessMaterialBrandSecondCheck",
                    data: obj,
                    success: function (msg) {
                        if (msg == "ok") {
                            var treeID = $("#hidTreeID").val();
                            var trObj = $("#tabDataToMaterial tr[id=" + treeID + "]");
                            var wcCount = parseInt($(trObj).attr("zz-ccount")) - 1;
                            if (wcCount <= 0) {
                                wcCount = 0;
                                $(trObj).attr("zz-check", "0");
                            }
                            $(trObj).attr("zz-ccount", wcCount);
                            if (wcCount == 0) {
                                LoadTreeListToSearch();//整体绑定
                            }
                            else {
                                SaveSuccess();//重新绑定品牌
                            }

                            bbSave = true;
                        }
                        else {
                            alert(msg);
                        }
                        bbSave = true;
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });

            });


            //品牌完成入库（操作）
            $(document).on("click", ".btnBrandNamePerfectState", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                if (confirm("确认品牌无误，转为正式材料？") == false) {
                    bbSave = true;
                    return false;
                }

                var obj = new Object();
                obj.mID = $("#hidMatID").val();
                obj.mUpdateState = 1;
                obj.saveType = 3; // 操作类型 3 入库操作
                // 价格校验所需参数
                obj.mTreeFour = $("#hidTreeID").val();
                obj.mCityID = $("#hidCityID").val();
                obj.mLevel = $("#hidLevelStaFlag").val();
                obj.mCostPrice = $("#txtCostPrice").val();
                obj.mQuotesPrice = $("#txtQuotesPrice").val();
                obj.mInstallPrice = $("#txtInstallPrice").val();

                $.ajax({
                    type: 'POST',
//                    url: "/BMaterial/ProcessMaterialNewBrandCheck",
                    url: basePath + "/material-api/brand_item/into", // 品牌完成入库（操作）
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            SaveSuccess();//重新绑定品牌
                            bbSave = true;
                        }
                        else {
                            alert(msg.statusMsg);
                        }
                        bbSave = true;
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });

            });

            //价格审核处理
            $(document).on("click", ".btnPriceChangeCheck", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }
                var status = $(this).data("val");

                var obj = new Object();
                obj.pch_MatID = $("#hidMatID").val();
                obj.pch_Status = status;

                var checkRemark = $("#txtCheckRemarkPriceChange").val();
                if (checkRemark == "" || $("#txtCheckRemarkPriceChange").attr("placeholder")) {
                    checkRemark = "";
                }

                if (status == 2) {
                    if (confirm("确认价格审核通过？") == false) {
                        bbSave = true;
                        return false;
                    }
                }
                else {
                    if (checkRemark == "") {
                        alert("请输入打回备注！");
                        $("#txtCheckRemarkPriceChange").focus();
                        bbSave = true;
                        return false;
                    }
                }

                obj.pch_CheckRemark = checkRemark;
                obj.treeID = $("#hidTreeID").val();
                obj.city = $("#hidCityID").val();
                $.ajax({
                    type: 'POST',
                    url: basePath + "/tree_base_info-api/save_or_update/base_info",
                    data: obj,
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            var treeID = $("#hidTreeID").val();
                            var trObj = $("#tabDataToMaterial tr[id=" + treeID + "]");
                            var pCount = parseInt($(trObj).attr("zz-pcount")) - 1;
                            if (pCount <= 0) {
                                pCount = 0;
                                $(trObj).attr("zz-price", "0");
                            }
                            $(trObj).attr("zz-pcount", pCount);
                            if (pCount == 0) {
                                LoadTreeListToSearch();//整体绑定
                            }
                            else {
                                SaveSuccess();//重新绑定品牌
                            }

                            $(".adjustmentAlert .close").click();
                            bbSave = true;
                        }
                        else {
                            alert(msg);
                        }
                        bbSave = true;
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });

            });


            //基础信息保存
            $(document).on("click", "#btnSaveTreeBaseInfo", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }
                var obj = CheckFrom();
                if (obj == false) {
                    bbSave = true;
                    return false;
                }
                obj.tbi_TreeID = $("#hidTreeID").val();

                var unitID = obj.tbi_unit;
                var treeFourID = obj.tbi_TreeID;
                var matFunction = obj.tbi_MatFunction;
                var depict = obj.tbi_MatDescription;
                var type = obj.matClass;

                $.ajax({
                    type: 'PUT',
                    url: basePath + "/tree_base_info-api/save_or_update/base_info",
                    data: {unitID, treeFourID, matFunction, depict, type},
                    success: function (msg) {
                        if (msg.statusMsg == "ok") {
                            $(".anItemBor-active").click();
                            bbSave = true;
                            //更新二段列表
                            $(".anItemBor-active").find(".lbl_base_unit").text($("#ddlUnit option[value='" + unitID + "']").text());
                            $(".anItemBor-active").find(".lbl_base_finishProductType").text($("#ddlProductType option[value='" + type + "']").text());
                        }
                        else {
                            alert(msg.statusMsg);
                            bbSave = true;
                        }

                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
                $(".add-parameter").hide();
            });


            //材料参数保存
            $(document).on("click", ".saveMaterialPara", function () {

                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }
                var obj = CheckFromToPara($(this));
                if (obj == false) {
                    bbSave = true;
                    return false;
                }
                obj.treeFourID = $("#hidTreeID").val();
                if (parseInt($(this).data("type")) == 2) {//更新档次下的材料参数
                    obj.MatLevel = $("#hidMatLevel").val()
                }

                $.ajax({
                    type: 'POST',
                    url: basePath + "/standard_para-api/save_or_update",
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            $(".anItemBor-active").click();
                            bbSave = true;
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }


                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //官方标准：外观标准 保存
            $(document).on("click", "#btnSaveStandardOQAExterior", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var obj = CheckFromToStandardOQAExterior();
                if (obj == false) {
                    bbSave = true;
                    return false;
                }
                $.ajax({
                    type: 'POST',
                    url: "/BMaterial/SvaeTreeSpecInfoToOAQExterior",
                    data: obj,
                    success: function (msg) {
                        if (msg == "ok") {
                            //清空数据
                            $("#lblExteriorName").html(obj.tbi_ExteriorName);
                            $("#lblExteriorSC").html(obj.tbi_ExteriorSC);
                            $("#lblExteriorDM").html(obj.tbi_ExteriorDM);
                            $("#btnSaveStandardOQAExterior").next().click();

                            //$("#tabDataToMaterial tr[id=" + obj.tbi_TreeID + "]").click();
                            bbSave = true;
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //小样标准：取样方法 保存
            $(document).on("click", "#btnSaveStandardMUSMethod", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var obj = CheckFromToStandardMUSMethod();
                if (obj == false) {
                    bbSave = true;
                    return false;
                }
                $.ajax({
                    type: 'POST',
                    url: basePath + "/tree_base_info-api/save_or_update/sampling_method",
                    data: obj,
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            //清空数据
                            $("#lblMockUpSamplingRemark").html(obj.samplingMethod);
                            $("#btnSaveStandardMUSMethod").next().click();

                            //$("#tabDataToMaterial tr[id=" + obj.tbi_TreeID + "]").click();

                            //判断是否有取样方法
                            var txtMockUpSamplingRemarkVal = $.trim($("#txtMockUpSamplingRemark").val());
                            if (txtMockUpSamplingRemarkVal == "") {
                                $(".lbl_strandard_mus_method").addClass("cRed").removeClass("cGreen").text("无");
                            } else {
                                $(".lbl_strandard_mus_method").addClass("cGreen").removeClass("cRed").text("有");
                            }

                            bbSave = true;
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //包装标准：包装标准 保存
            $(document).on("click", "#btnSaveStandardPackSStandard", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var obj = CheckFromToStandardPackSStandard();
                if (obj == false) {
                    bbSave = true;
                    return false;
                }
                $.ajax({
                    type: 'POST',
                    url: basePath + "/tree_base_info-api/save_or_update/pack_standard",
                    data: obj,
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            //清空数据
                            $("#lblPSType").html(obj.tbi_PSType == 1 ? "瑞祥标准" : "合作商标准");
                            $("#lblPSMaterialQuality").html(obj.tbi_PSMaterialQuality);
                            $("#lblPSMarkRemark").html(obj.tbi_PSMarkRemark);

                            var opType = $(".anItemBor-active").data("type")
                            if (opType) {
                                LoadMaterialDetailToTreeStandard(opType);//加载第三段
                            }

//                            $("#btnSaveStandardPackSStandard").next().click();

                            //$("#tabDataToMaterial tr[id=" + obj.tbi_TreeID + "]").click();
                            bbSave = true;
                        }
                        else {
                            alert(msg.statusMsg);
                            bbSave = true;
                        }
                        //二段数据更新
                        $(".lbl_strandard_packs_stand").addClass("cGreen").removeClass("cRed").text("有");
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //标准项-质量标准 子项保存
            $(document).on("click", ".btnSaveStandardDetectNode", function () {

                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }
                var obj = CheckFromToStandarddDetect($(this));
                if (obj == false) {
                    bbSave = true;
                    return false;
                }
                obj.levelStaFlag = 1;// 档次质量编辑标识
                obj.currentLevel = $("#hidLevelStaFlag").val();// 当前操作档次
                $.ajax({
                    type: 'POST',
                    url: basePath + '/tree_standard_itme-api/save_or_update',
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify(obj),
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            var opType = $(".anItemBor-active").data("type")
                            if (opType) {
                                LoadMaterialDetailToTreeStandard(opType);//加载第三段
                            }
                            //$("#tabDataToMaterial tr[id=" + obj.tbi_TreeID + "]").click();
                            bbSave = true;
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }

                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //标准项 统一 保存 口
            $(document).on("click", ".btnSaveStandardDetect", function () {
                var _othis = $(this);
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var obj = CheckFromToStandarddDetect($(this));
                if (obj == false) {
                    bbSave = true;
                    return false;
                }
                $.ajax({
                    type: 'POST',
                    url: basePath + '/tree_standard_itme-api/save_or_update',
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify(obj),
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            var opType = $(".anItemBor-active").data("type")
                            if (opType) {
                                LoadMaterialDetailToTreeStandard(opType);//加载第三段
                            }

                            //$("#tabDataToMaterial tr[id=" + obj.tbi_TreeID + "]").click();
                            bbSave = true;
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }


                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //删除标准项 统一 保存 口
            $(document).on("click", "div.analyItem[data-tsiid] .img_delete", function () {
                var _othis = $(this);
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                if (confirm("确认删除此项检验标准？") == false) {
                    bbSave = true;
                    return false;
                }

                var divObj = $(this).closest("div.analyItem[data-tsiid]");
                var standardID = $(divObj).data("tsiid");
                $.ajax({
                    type: 'POST',
                    url: basePath + '/tree_standard_itme-api/delete',
                    data: {standardID},
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            var opType = $(".anItemBor-active").data("type")
                            if (opType) {
                                LoadMaterialDetailToTreeStandard(opType);//加载第三段
                            }

                            //$("#tabDataToMaterial tr[id=" + obj.tbi_TreeID + "]").click();
                            bbSave = true;
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }


                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //基础-对比标准属性保存
            $(document).on("click", "#btnSaveComparedAttr", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var divObj = $(this).closest("div.analyItem");

                var ckbList = $(divObj).find("input:checked");
                var arr = [];
                $(ckbList).each(function () {
                    var item = new Object();
                    item.mca_Type = $(this).data("type");
                    if (item.mca_Type == 1) {
                        item.mca_StandardID = $(this).data("tid");
                    }
                    else {
                        item.mca_ParaID = $(this).data("tid");
                    }
                    arr.push(item);
                });

                var obj = new Object();
                obj.treeID = $("#hidTreeID").val();
                obj.Items = arr;

                $.ajax({
                    type: 'POST',
                    url: "/BMaterial/SaveComparedAttr",
                    data: obj,
                    success: function (msg) {
                        if (msg == "ok") {
                            //清空数据
                            $(".anItemBor-active").click();

                            bbSave = true;
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //基础-对比标准属性保存
            $(document).on("click", ".btnSaveComparedAttrItem", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var divObj = $(this).closest("div.analyItem");
                var attrID = $(divObj).data("attrid");
                var attrType = $(divObj).data("type");

                //描述
                var attrName = $(divObj).find(".txtAttrName").val();
                if (attrName == "" || attrName == $(divObj).find(".txtAttrName").attr("placeholder")) {
                    $(divObj).find(".txtAttrName").focus();
                    bbSave = true;
                    return false;
                }
                else if (attrName.length > 30) {
                    alert("对比属性名称不能超过30个字！");
                    $(divObj).find(".txtAttrName").focus();
                    bbSave = true;
                    return false;
                }

                var obj = new Object();
                if (attrID) {
                    obj.mcaID = attrID;
                }
                obj.treeFourID = $("#hidTreeID").val();
                obj.mcaName = attrName;
                obj.mcaType = attrType;

                $.ajax({
                    type: 'POST',
                    url: basePath + "/material_compared_attributes-api/save_or_update",
                    data: obj,
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            //清空数据
                            $(".anItemBor-active").click();
                            bbSave = true;
                            //对比标准二段更新
                            var ototalNumber = $("#divAlertMaterialLoad").find(".analyItem[data-attrid]").length + 1;
                            $(".lbl_base_compared_attr").addClass("cGreen").removeClass("cRed").text(ototalNumber + "项");
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }

                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //删除对比属性
            $(document).on("click", "div.analyItem[data-attrid] .img_delete", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                if (confirm("确认删除此项对比属性？") == false) {
                    bbSave = true;
                    return false;
                }

                var divObj = $(this).closest("div.analyItem[data-attrid]");
                var mcaID = $(divObj).data("attrid");
                var attrType = $(divObj).data("type");

                var obj = new Object();
                obj.mcaID = mcaID;
                obj.attrType = attrType;// 类型（1对比，2材料约定）
                $.ajax({
                    type: 'POST',
                    url: basePath + "/material_compared_attributes-api/delete",
                    data: obj,
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            //清空数据
                            $(".anItemBor-active").click();

                            bbSave = true;
                            //对比标准二段更新
                            var ototalNumber = $("#divAlertMaterialLoad").find(".analyItem[data-attrid]").length - 1;
                            if (ototalNumber <= 0) {
                                $(".lbl_base_compared_attr").addClass("cRed").removeClass("cGreen").text("无");
                            } else {
                                $(".lbl_base_compared_attr").addClass("cGreen").removeClass("cRed").text(ototalNumber + "项");
                            }

                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //平台-搜索词|用途保存
            $(document).on("click", ".btnSaveSearchItem", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }
                var divObj = $(this).closest("div.analyItem");
                var seType = $(this).data("type");// 检索类型
                var paraData = []; // 检索内容
                var val = $(divObj).find(".txtSearchContent").val().split(",");
                var searchContent = $($(divObj).find(".txtSearchContent").val().split(",")).each(function (i, n) {
                    var map = {};
                    map[seType] = n;
                    paraData[i] = map;
                })
                var obj = new Object();
                obj.treeFourID = $("#hidTreeID").val();// 四级科目ID
                obj.type = seType;// 检索类型 类型：1搜索，2用途，4搜索自动生成内容（可位运算|）
                obj.items = paraData;
                var olength = obj.items.length;
//                obj.userType = $("#hidUserType").val();
//                obj.userNo = $("#hidUserNo").val();

                $.ajax({
                    type: 'POST',
                    url: basePath + "/search_item-api/search_term_edit",
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify(obj),
                    success: function (msg) {
                        if (msg.statusMsg == "success") {
                            //清空数据
                            $(".anItemBor-active").click();
                            bbSave = true;
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }
                        //更新二段数据
                        if (seType == 1) {
                            var olength = $("#tagator_searchItemsStrID .tagator_tag").length;
                            if (olength <= 0) {
                                $(".lbl_tree_search").addClass("cRed").removeClass("cGreen").text("无");
                            } else {
                                $(".lbl_tree_search").addClass("cGreen").removeClass("").text(olength + "项");
                            }
                        }
                        else if (seType == 2) {
                            var olength = $("#tagator_ .tagator_tag").length;
                            if (olength <= 0) {
                                $(".lbl_tree_use").addClass("cRed").removeClass("cGreen").text("无");
                            } else {
                                $(".lbl_tree_use").addClass("cGreen").removeClass("").text(olength + "项");
                            }
                        }

                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //平台-添加 照片类信息
            $(document).on("click", ".btnAddPhotoClass", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var divObj = $(this).closest("div.analyItem");
                var obj = CheckFromToTypeInfoAdd(divObj);
                if (obj == false) {
                    bbSave = true;
                    return false;
                }

                obj.tr_Type = 1;
                obj.tr_OneID = $("#hidTreeID").val();
                obj.userType = $("#hidUserType").val();
                obj.userNo = $("#hidUserNo").val();
                $.ajax({
                    type: 'POST',
                    url: "/Public/AddCustomPhotoClass",
                    data: obj,
                    success: function (msg) {
                        if (msg == "ok") {
                            //清空数据
                            $(".anItemBor-active").click();

                            bbSave = true;
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //平台-保存 照片类信息
            $(document).on("click", ".btnSavePhotoClass", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var divObj = $(this).closest("div.analyItem");
                var obj = CheckFromToTypeInfoSave(divObj);
                if (obj == false) {
                    bbSave = true;
                    return false;
                }
                obj.userType = $("#hidUserType").val();
                obj.userNo = $("#hidUserNo").val();
                $.ajax({
                    type: 'POST',
                    url: "/Public/SaveTypeInfo",
                    data: obj,
                    success: function (msg) {
                        if (msg == "ok") {
                            //清空数据
                            $(".anItemBor-active").click();

                            bbSave = true;
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //平台-保存 对比照片
            $(document).on("click", "#btnSavePhotoToCompared", function () {
                debugger
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var divObj = $(this).closest("div.analyItem");

                var isOK = true;
                /*第一步：验证是否合格*/
                $(divObj).find("ul li").each(function () {
                    var liObj = this;
                    var tspID = $(liObj).attr("data-tspid");
                    var txtTitle = $(liObj).find(".txtTitle");
                    var title = $(txtTitle).val();
                    if (title != "" && title != $(txtTitle).attr("placeholder")) {
                        if (!tspID) {
                            $(txtTitle).focus();
                            alert("请先上传照片在保存");
                            isOK = false;
                            return false;
                        }
                    }
                    var txtContent = $(liObj).find(".txtContent");
                    var content = $(txtContent).val();
                    if (content != "" && content != $(txtContent).attr("placeholder")) {
                        if (!tspID) {
                            $(txtContent).focus();
                            alert("请先上传照片在保存");
                            isOK = false;
                            return false;
                        }
                    }
                });
                if (isOK == false) {
                    bbSave = true;
                    return false;
                }

                //第二步：保存对比照片描述
                $(divObj).find("ul li").each(function () {
                    var liObj = this;
                    var tspID = $(liObj).attr("data-tspid");
                    var photoUrl = $(liObj).find("img").attr("src");

                    var txtTitle = $(liObj).find(".txtTitle");
                    var title = $(txtTitle).val();
                    var txtContent = $(liObj).find(".txtContent");
                    var content = $(txtContent).val();
                    if (tspID && (title != "" || txtContent != "") && tspID != "") {
                        var obj = new Object();
                        obj.tspTitle = title;//参数
                        obj.tspContent1 = content;//描述
                        obj.userType = $("#hidUserType").val();
                        obj.userNo = $("#hidUserNo").val();
                        obj.treeID = $("#hidTreeID").val();
                        obj.city = $("#hidCityID").val();
                        //保存
                        obj.tspID = tspID;

                        $.ajax({
                            type: 'POST',
//                            url: "/Public/SaveMaterialPhotoOterText",
//                            data: obj,
                            url: basePath + "/tree_standard_photo-api/para/update",
                            contentType: 'application/json',
                            data: JSON.stringify(obj),
                            async: false,
                            success: function (msg) {
                                if (msg.statusMsg == "success") {
                                }
                                else {
                                    //alert(msg);
                                }
                            },
                            error: function (err) {
                                //alert("操作出错！");
                            }
                        });
                    }
                });

                //执行成功
                bbSave = true;
                $(".anItemBor-active").click();
            });

            //删除平台-照片类
            $(document).on("click", "div.analyItem[data-tiid] .img_delete", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                if (confirm("确认删除此自定模块？") == false) {
                    bbSave = true;
                    return false;
                }

                var divObj = $(this).closest("div.analyItem[data-tiid]");
                var tiID = $(divObj).data("tiid");

                var obj = new Object();
                obj.treeID = $("#hidTreeID").val();
                obj.tiID = tiID;
                obj.userType = $("#hidUserType").val();
                obj.userNo = $("#hidUserNo").val();
                $.ajax({
                    type: 'POST',
                    url: "/Public/DeleteCustomPhotoClass",
                    data: obj,
                    success: function (msg) {
                        if (msg == "ok") {
                            //清空数据
                            $(".anItemBor-active").click();

                            bbSave = true;
                        }
                        else {
                            alert(msg);
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //置顶设置
            $(document).on("click", "#divTopSetContent li[data-pagenum]", function () {
                alert("操作权限未开通");
            });


            //审核信息
            $(document).on("click", ".btnICSStateSave", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var icaVal = $(this).data("val");
                if (icaVal == 2) {
                    if (confirm("确认标记为不合格？") == false) {
                        bbSave = true;
                        return false;
                    }
                }
                else {
                    if (confirm("确认标记为合格？") == false) {
                        bbSave = true;
                        return false;
                    }
                }


                var icaObject = $(this).data("obj");
                var icaType = $(this).data("type");

                var obj = new Object();
                obj.ica_TreeID = $("#hidTreeID").val();
                obj.ica_CityID = $("#hidCityID").val();
                obj.ica_MatID = $("#hidMatID").val();
                obj.ica_Object = icaObject;
                obj.ica_Type = icaType;
                obj.ica_State = icaVal;
                obj.userType = $("#hidUserType").val();
                obj.userNo = $("#hidUserNo").val();
                $.ajax({
                    type: 'POST',
                    url: "/Public/SaveInfoCheckAndAccept",
                    data: obj,
                    success: function (msg) {
                        if (msg == "ok") {
                            //清空数据
                            $(".anItemBor-active").click();
                        }
                        else if (msg == "okok") {
                            $("#tabDataToMaterialVer > tr.tractive").click();
                        }
                        else if (msg == "okokok") {
                            $(".daily_title_tree_ver li.title_cur").click();
                        }
                        else {
                            alert(msg);
                        }
                        bbSave = true;
                    },
                    error: function (err) {
                        alert("操作出错！");
                        bbSave = true;
                    }
                });
            });

            //保存新增任务
            $(document).on("click", "#submitTask", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                var title = $("#taskTitle").val();
                if (title == "" || title == $("#taskTitle").attr("placeholder")) {
                    alert("请输入任务名！");
                    $("#taskTitle").focus();
                    bbSave = true;
                    return false;
                }
                var content = $("#taskContent").val();
                if (content == "" || content == $("#taskContent").attr("placeholder")) {
                    alert("请输入描述！");
                    $("#taskContent").focus();
                    bbSave = true;
                    return false;
                }
                var startTime = $("#taskStartTime").val();
                if (startTime == "" || startTime == $("#taskStartTime").attr("placeholder")) {
                    alert("请选择开始日期！");
                    bbSave = true;
                    return false;
                }
                var endTime = $("#taskEndTime").val();
                if (endTime == "" || endTime == $("#taskEndTime").attr("placeholder")) {
                    alert("请选择结束日期！");
                    bbSave = true;
                    return false;
                }

                var treeID = $("#hidTreeID").val();
                var task = new Object();
                task.bt_Type = 2;//子库材料-集团下地方任务
                task.bt_MainID = treeID;
                task.bt_CityID = $("#hidCityID").val();
                task.bt_Title = title;
                task.bt_Content = content;
                task.bt_StartTime = startTime;
                task.bt_EndTime = endTime;
                task.bt_CreateUser = $("#hidUserNo").val();
                task.bt_CreateUserName = $("#hidUserName").val();

                /*提交新增任务*/
                $.ajax({
                    type: 'POST',
                    url: "/Public/SaveBusinessTask",
                    data: task,
                    success: function (msg) {
                        if (msg != "ok") {
                            alert(msg);
                            bbSave = true;
                        }
                        else {
                            //清空内容
                            $("#taskTitle").val("");
                            $("#taskContent").val("");
                            $("#taskStartTime").val("");
                            $("#taskEndTime").val("");

                            //控制标记
                            var trObj = $("#tabDataToMaterial tr[id=" + treeID + "]");
                            $(trObj).click();// 重新加载此材料
                            $(".addTaskAlert").hide().animate({left: "100%"}, 400);
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("新增任务出错！");
                        bbSave = true;
                    }
                });

            });

            //保存型号照片
            function SaveBrandType() {
                $(".divbrandtype ul.brandmodel_new li").each(function () {
                    var liObj = this;
                    var tspID = $(liObj).attr("data-tspid");
                    var photoUrl = $(liObj).find("img").attr("src");

                    var txtTitle = $(liObj).find(".txtTitle");
                    var title = $(txtTitle).val();
                    /*
                    if (photoUrl && photoUrl != "") {
                        if (title == "" || title == $(txtTitle).attr("placeholder")) {
                            alert("请输入内容！");
                            $(txtTitle).focus();
                            bbSave = true; return false;
                        }
                    }
                    */
                    if (title != "" || photoUrl != "") {
                        var obj = new Object();
                        obj.tspTitle = title;
                        obj.userType = $("#hidUserType").val();
                        obj.userNo = $("#hidUserNo").val();
                        obj.treeID = $("#hidTreeID").val();
                        obj.city = $("#hidCityID").val();
                        if (tspID && tspID != "") {
                            //保存
                            obj.tspID = tspID;

                            $.ajax({
                                type: 'POST',
//                                url: "/Public/SaveMaterialPhotoOterText",
//                                data: obj,
                                url: basePath + "/tree_standard_photo-api/para/update",
                                contentType: 'application/json',
                                data: JSON.stringify(obj),
                                async: false,
                                success: function (msg) {
                                    if (msg.statusMsg == "success") {
                                    }
                                    else {
                                        //alert(msg);
                                    }
                                },
                                error: function (err) {
                                    //alert("操作出错！");
                                }
                            });
                        }
                        else {
                            //新增
                            var tspType = $(liObj).data("type");
                            var matID = $(liObj).data("matid");//材料ID
                            var matLevel = $(liObj).data("matlevel");//材料ID
                            obj.tspTreeFour = $("#hidTreeID").val();
                            obj.tspTSID = matID;
                            obj.tspType = tspType;
                            obj.tspPhotoURL = photoUrl;
                            if (matLevel) {
                                obj.tspMatLevel = matLevel;
                            }

                            $.ajax({
                                type: 'POST',
//                                url: "/Public/AddMaterialPhoto",
                                url: basePath + "/tree_standard_photo-api/add",
                                contentType: 'application/json',
                                data: JSON.stringify(obj),
                                async: false,
                                success: function (msg) {
                                    if (msg.statusMsg.indexOf("ok") >= 0) {
                                        var tspID = msg.statusMsg.substr(2);
                                        $(liObj).attr("data-tspid", tspID);

                                    }
                                    else {
                                        //alert(msg);
                                    }
                                },
                                error: function (err) {
                                    //alert("操作出错！");
                                }
                            });
                        }
                    }
                });
            }
        })

        //检测能否保存(品牌)
        function CheckFromToBrand() {
            var brandName = $("#txtBrandName").val();
            if (brandName == "" || brandName == "品牌名称") {
                alert("请输入品牌名称！");
                $("#txtBrandName").focus();
                return false;
            }
            else if (brandName.length > 50) {
                alert("品牌名称不能超过50个字！");
                $("#txtBrandName").focus();
                return false;
            }

            var brandType = $("#txtBrandType").val();
            if (brandType == "" || brandType == "型号") {
                brandType = "";
            }
            else if (brandType.length > 50) {
                alert("型号不能超过50个字！");
                $("#txtBrandType").focus();
                return false;
            }
            var costPrice = parseFloat($("#txtCostPrice").val());//成本价
            if (costPrice === "" || isNaN(costPrice) || costPrice == 0) {
                costPrice = 0;
            }
            else if (costPrice < 0) {
                costPrice = 0;
            }
            var quotesPrice = $("#txtQuotesPrice").val();//报价
            if (quotesPrice === "" || isNaN(quotesPrice) || quotesPrice == 0) {
                quotesPrice = 0;
            }
            else if (quotesPrice < 0) {
                quotesPrice = 0;
            }
            var installPrice = $("#txtInstallPrice").val();//安装价
            if (installPrice === "" || isNaN(installPrice)) {
                installPrice = 0;
            }
            else if (installPrice < 0) {
                installPrice = 0;
            }


            var obj = new Object();
            obj.m_TreeFour = $("#hidTreeID").val();
            obj.m_TSID = $("#hidBrandID").val();//标准ID
            obj.m_CityID = $("#hidCityID").val();//地区
            obj.m_Level = $("#hidMatLevel").val();
            obj.m_BrandName = brandName;
            obj.m_BrandType = brandType;
            obj.m_CostPrice = costPrice;
            obj.m_QuotesPrice = quotesPrice;
            obj.m_InstallPrice = installPrice;
            obj.m_HostState = $("#ddlHost").val();
            obj.m_HomeHostState = $("#ddlHomeHost").val();
            obj.m_MatType = $("#ddlMatType").val();
            obj.m_UpdateState = 1;
            obj.userType = $("#hidUserType").val();
            obj.userNo = $("#hidUserNo").val();

            return obj;
        }


        //检测能否保存(品牌)
        function CheckFromToAddBrand() {
            var brandName = $("#txtBrandName").val();
            if (brandName == "" || brandName == $("#txtBrandName").attr("placeholder")) {
                alert("请输入品牌名称！");
                $("#txtBrandName").focus();
                return false;
            }
            else if (brandName.length > 50) {
                alert("品牌名称不能超过50个字！");
                $("#txtBrandName").focus();
                return false;
            }
            var brandRemark = $("#txtBrandRemark").val();
            if (brandRemark == $("#txtBrandRemark").attr("placeholder")) {
                brandRemark = "";
            }

            var txtBrandType = $(".divbrandtype").find(".txtTitle:eq(0)");
            var brandType = $(txtBrandType).val();
            if (brandType == "" || brandType == $(txtBrandType).attr("placeholder")) {
                brandType = "";
            }
            else if (brandType.length > 50) {
                alert("型号不能超过50个字！");
                $(txtBrandType).focus();
                return false;
            }
            var quotesPrice = $("#txtQuotesPrice").val();//报价
            if (quotesPrice === "" || isNaN(quotesPrice) || quotesPrice == 0) {
                alert("请输入报价！");
                $("#txtQuotesPrice").focus();
                return false;
            }
            else if (quotesPrice < 0) {
                alert("请输入正确的报价！");
                $("#txtQuotesPrice").focus();
                return false;
            }
            var costPrice = parseFloat($("#txtCostPrice").val());//成本价
            if (costPrice === "" || isNaN(costPrice) || costPrice == 0) {
                alert("请输入成本价！");
                $("#txtCostPrice").focus();
                return false;
            }
            else if (costPrice < 0) {
                alert("请输入正确的成本价！");
                $("#txtCostPrice").focus();
                return false;
            }

            var installPrice = $("#txtInstallPrice").val();//安装价
            if (installPrice === "" || isNaN(installPrice)) {
                installPrice = 0;
            }
            else if (installPrice < 0) {
                alert("请输入正确的安装价！");
                $("#txtInstallPrice").focus();
                return false;
            }

            var obj = new Object();
            obj.mTreeFour = $("#hidTreeID").val();
            obj.mTSID = $("#hidBrandID").val();//标准ID
            obj.mCityID = $("#hidCityID").val();//地区
            obj.mLevel = $("#hidMatLevel").val();
            obj.mBrandName = brandName;
            obj.mMatDescription = brandRemark;
            obj.mBrandType = brandType;
            obj.mCostPrice = costPrice;
            obj.mQuotesPrice = quotesPrice;
            obj.mInstallPrice = installPrice;
            obj.mHostState = $("#ddlHost").val();
            obj.mHomeHostState = $("#ddlHomeHost").val();
            obj.mMatType = $("#ddlMatType").val();
            if (costPrice > 0 && quotesPrice > 0) {
                obj.m_UpdateState = 1;
            }
            obj.userType = $("#hidUserType").val();
            obj.userNo = $("#hidUserNo").val();

            return obj;
        }

        //检测能否保存
        function CheckFrom() {
            var unit = $("#ddlUnit").val();
            if (unit == "" || unit == "0") {
                alert("请选择计价单位！");
                return false;
            }
            //描述
            var matDescription = $("#txtMatDescription").val();
            if (matDescription == "" || matDescription == "材料特色亮点、使用范围、用途") {
                alert("请输入材料描述！");
                $("#txtMatDescription").focus();
                return false;
            }
            else if (matDescription.length > 2000) {
                alert("材料描述不能超过2000个字！");
                $("#txtMatDescription").focus();
                return false;
            }
            //功能
            var matFunction = $("#txtMatFunction").val();
            if (matFunction == "") {
                alert("请输入功能！");
                $("#txtMatFunction").focus();
                return false;
            }
            else if (matFunction.length > 2000) {
                alert("功能不能超过2000个字！");
                $("#txtMatFunction").focus();
                return false;
            }
            var matClass = $("#ddlProductType").val();
            if (matClass == "" || matClass == "0") {
                alert("请选择材料分类！");
                return false;
            }

            var obj = new Object;
            obj.tbi_unit = unit;
            obj.tbi_MatDescription = matDescription;
            obj.tbi_MatFunction = matFunction;
            obj.matClass = matClass;

            return obj;
        }


        //检测能否保存
        function CheckFromToPara(obj) {
            var divObj = $(obj).closest("div.analyItem");
            var paraData = [];
            var isOK = true;
            $(divObj).find("div[data-paraid]").each(function (i, n) {
                var txtValue = $(this).find("input[type=text]");
                var paraID = $(this).data("paraid");
                var paraVal = $(txtValue).val();
                var map = {};
                map[paraID] = paraVal;
                if (txtValue.length > 0) {

                    if (paraVal == "") {
                        $(txtValue).focus();
                        isOK = false;
                        return false;
                    }
                    else if (paraVal.length > 20) {
                        alert("参数的值不能超过20个字！");
                        $(txtValue).focus();
                        isOK = false;
                        return false;
                    }
                    paraData[i] = map;
                }
                else {
                    var ddlItem = $(this).find("select");
                    if ($(ddlItem).val() == "") {
                        alert("请选择材料参数的值！");
                        isOK = false;
                        return false;
                    }
                    if (ddlItem.length > 0) {
                        paraData[i] = map;
                    }
                }
            });
            if (isOK == false) {
                return false;
            }

            var obj = new Object;
            obj.paras = paraData;

            return obj;
        }

        //检测能否保存（官方标准-外观标准）
        function CheckFromToStandardOQAExterior() {
            //外观
            var matExteriorName = $("#txtExteriorName").val();
            if (matExteriorName == "" || $("#txtExteriorName").attr("placeholder") == matExteriorName) {
                $("#txtExteriorName").focus();
                return false;
            }
            else if (matExteriorName.length > 100) {
                alert("不能超过100个字！");
                $("#txtExteriorName").focus();
                return false;
            }
            //特殊要求
            var matExteriorSC = $("#txtExteriorSC").val();
            if (matExteriorSC == "" || $("#txtExteriorSC").attr("placeholder") == matExteriorSC) {
                $("#txtExteriorSC").focus();
                return false;
            }
            else if (matExteriorSC.length > 2000) {
                alert("不能超过2000个字！");
                $("#txtExteriorSC").focus();
                return false;
            }
            //检测方法
            var matExteriorDM = $("#txtExteriorDM").val();
            if (matExteriorDM == "" || $("#txtExteriorDM").attr("placeholder") == matExteriorDM) {
                $("#txtExteriorDM").focus();
                return false;
            }
            else if (matExteriorDM.length > 2000) {
                alert("不能超过2000个字！");
                $("#txtExteriorDM").focus();
                return false;
            }

            var obj = new Object;
            obj.tbi_TreeID = $("#hidTreeID").val();
            obj.tbi_ExteriorName = matExteriorName;
            obj.tbi_ExteriorSC = matExteriorSC;
            obj.tbi_ExteriorDM = matExteriorDM;

            return obj;
        }

        //检测能否保存（官方标准-外观标准）
        function CheckFromToStandardMUSMethod() {
            //取样方法
            var matMockUpSamplingRemark = $("#txtMockUpSamplingRemark").val();
            if (matMockUpSamplingRemark == "" || matMockUpSamplingRemark == $("#txtMockUpSamplingRemark").attr("placeholder")) {
                $("#txtMockUpSamplingRemark").focus();
                return false;
            }
            else if (matMockUpSamplingRemark.length > 2000) {
                alert("不能超过2000个字！");
                $("#txtMockUpSamplingRemark").focus();
                return false;
            }

            var obj = new Object;
            obj.treeFourID = $("#hidTreeID").val();
            obj.samplingMethod = matMockUpSamplingRemark;

            return obj;
        }

        //检测能否保存（包装标准）
        function CheckFromToStandardPackSStandard() {
            var ipt = $("input[name=pstype]:checked");
            if (ipt.length == 0) {
                alert("请选择包装标准类别");
                return false;
            }
            //包装材质
            var matPSMaterialQuality = $("#txtPSMaterialQuality").val();
            if (matPSMaterialQuality == "" || matPSMaterialQuality == $("#txtPSMaterialQuality").attr("placeholder")) {
                $("#txtPSMaterialQuality").focus();
                return false;
            }
            else if (matPSMaterialQuality.length > 2000) {
                alert("不能超过2000个字！");
                $("#txtPSMaterialQuality").focus();
                return false;
            }
            //包装说明
            var matPSMarkRemark = $("#txtPSMarkRemark").val();
            if (matPSMarkRemark == "" || matPSMarkRemark == $("#txtPSMarkRemark").attr("placeholder")) {
                $("#txtPSMarkRemark").focus();
                return false;
            }
            else if (matPSMarkRemark.length > 2000) {
                alert("不能超过2000个字！");
                $("#txtPSMarkRemark").focus();
                return false;
            }
            //var matPSPackageRemark = $(".packagedphoto img").attr("src");
            //if (matPSPackageRemark == "") {
            //    alert("上传包装照片！");
            //    return false;
            //}

            var obj = new Object;
            obj.treeFourID = $("#hidTreeID").val();
            obj.packGenre = $(ipt).val();
            obj.packQuality = matPSMaterialQuality;
            obj.packExplain = matPSMarkRemark;
            // obj.tbi_PSPackageRemark = matPSPackageRemark;
            return obj;
        }

        //检测能否添加（分类信息）
        function CheckFromToTypeInfoAdd(divObj) {
            var tiID = "";
            if ($(divObj).find("#ddlTI_TypeInfo").length > 0) {
                tiID = $(divObj).find("#ddlTI_TypeInfo").val();
                if (tiID == "") {
                    alert("请选择模块");
                    return false;
                }
                if (tiID == "999") {
                    tiID = "";
                }
            }

            var obj = new Object;
            if (tiID == "") {//验证数据是否填写
                //标题
                var title = $(divObj).find("#txtTI_Title").val();
                if (title == "" || title == $(divObj).find("#txtTI_Title").attr("placeholder")) {
                    $(divObj).find("#txtTI_Title").focus();
                    return false;
                }
                else if (title.length > 10) {
                    alert("不能超过10个字！");
                    $(divObj).find("#txtTI_Title").focus();
                    return false;
                }
                //标题英文
                var titleEn = $(divObj).find("#txtTI_TitleEn").val();
                if (titleEn == "" || titleEn == $(divObj).find("#txtTI_TitleEn").attr("placeholder")) {
                    $(divObj).find("#txtTI_TitleEn").focus();
                    return false;
                }
                else if (titleEn.length > 40) {
                    alert("不能超过40个字！");
                    $(divObj).find("#txtTI_TitleEn").focus();
                    return false;
                }
                //描述
                var description = $(divObj).find("#txtTI_Description").val();
                if (description == $(divObj).find("#txtTI_Description").attr("placeholder")) {
                    description = "";
                }
                if (description.length > 100) {
                    alert("不能超过100个字！");
                    $(divObj).find("#txtTI_Description").focus();
                    return false;
                }

                obj.ti_Type = 1;
                obj.ti_Title = title;
                obj.ti_TitleEn = titleEn;
                obj.ti_Description = description;
            }
            else {
                obj.ti_ID = tiID;
            }
            return obj;
        }

        //检测能否保存（分类信息）
        function CheckFromToTypeInfoSave(divObj) {
            //标题
            var title = $(divObj).find("#txtTI_Title").val();
            if (title == "" || title == $(divObj).find("#txtTI_Title").attr("placeholder")) {
                $(divObj).find("#txtTI_Title").focus();
                return false;
            }
            else if (title.length > 10) {
                alert("不能超过10个字！");
                $(divObj).find("#txtTI_Title").focus();
                return false;
            }
            //标题英文
            var titleEn = $(divObj).find("#txtTI_TitleEn").val();
            if (titleEn == "" || titleEn == $(divObj).find("#txtTI_TitleEn").attr("placeholder")) {
                $(divObj).find("#txtTI_TitleEn").focus();
                return false;
            }
            else if (titleEn.length > 40) {
                alert("不能超过40个字！");
                $(divObj).find("#txtTI_TitleEn").focus();
                return false;
            }
            //描述
            var description = $(divObj).find("#txtTI_Description").val();
            if (description == $(divObj).find("#txtTI_Description").attr("placeholder")) {
                description = "";
            }
            if (description.length > 100) {
                alert("不能超过100个字！");
                $(divObj).find("#txtTI_Description").focus();
                return false;
            }

            var obj = new Object;
            obj.ti_ID = $(divObj).data("tiid");
            obj.ti_Type = 1;
            obj.ti_Title = title;
            obj.ti_TitleEn = titleEn;
            obj.ti_Description = description;

            return obj;
        }

        //检验标准统一口
        function CheckFromToStandarddDetect(obj) {
            var divObj = $(obj).closest("div.analyItem");
            var parentID = $(divObj).data("parent");
            var sName = $(divObj).find(".txtStandardName").val();
            if ($(divObj).find(".txtStandardName").data("input") == true
                && (sName == "" || sName == $(divObj).find(".txtStandardName").attr("placeholder"))) {
                $(divObj).find(".txtStandardName").focus();
                return false;
            }
            else if (sName.length > 50) {
                alert("属性名称不能超过50个字！");
                $(divObj).find(".txtStandardName").focus();
                return false;
            }
            //单位
            var sUnit = $(divObj).find(".ddlStandardUnit").val();//单位
            var sUnitName = sUnit == "0" ? "" : $(divObj).find(".ddlStandardUnit").find("option:selected").text();//单位内容
            var sDiffMin = $(divObj).find(".ddlStandardMIN").val();//合格范围值最小
            var sDiffMax = $(divObj).find(".ddlStandardMAX").val();//合格范围值最大
            if (sDiffMin.length > 10) {
                alert("范围值不能超过10个字！");
                $(divObj).find(".ddlStandardMIN").focus();
                return false;
            }
            if (sDiffMax.length > 10) {
                alert("范围值不能超过10个字！");
                $(divObj).find(".ddlStandardMAX").focus();
                return false;
            }
            //标准-值
            var value = $(divObj).find(".txtStandardValue").val();
            if ($(divObj).find(".txtStandardValue").data("input") == true
                && (value == "" || value == $(divObj).find(".txtStandardValue").attr("placeholder"))) {
                $(divObj).find(".txtStandardValue").focus();
                return false;
            }
            else if (value.length > 100) {
                alert("标准数值不能超过100个字！");
                $(divObj).find(".txtStandardValue").focus();
                return false;
            }
            //属性一：
            var sAttrNameOne = $(divObj).find(".txtExteriorName").val();
            if ($(divObj).find(".txtExteriorName").data("input") == true && (sAttrNameOne == "" || sAttrNameOne == $(divObj).find(".txtExteriorName").attr("placeholder"))) {
                $(divObj).find(".txtExteriorName").focus();
                return false;
            }
            else if (sAttrNameOne.length > 10) {
                alert("检验方法一不能超过10个字！");
                $(divObj).find(".txtExteriorName").focus();
                return false;
            }
            var sClaimToCheck = $(divObj).find(".txtStandardPC").val();
            if ($(divObj).find(".txtStandardPC").data("input") == true && (sClaimToCheck == "" || sClaimToCheck == $(divObj).find(".txtStandardPC").attr("placeholder"))) {
                $(divObj).find(".txtStandardPC").focus();
                return false;
            }
            else if (sClaimToCheck.length > 2000) {
                alert("检验描述不能超过2000个字！");
                $(divObj).find(".txtStandardPC").focus();
                return false;
            }
            //属性二：
            var sAttrNameTwo = $(divObj).find(".txtExteriorSC").val();
            if ($(divObj).find(".txtExteriorSC").data("input") == true && (sAttrNameTwo == "" || sAttrNameTwo == $(divObj).find(".txtExteriorSC").attr("placeholder"))) {
                $(divObj).find(".txtExteriorSC").focus();
                return false;
            }
            else if (sAttrNameTwo.length > 10) {
                alert("检验方法二不能超过10个字！");
                $(divObj).find(".txtExteriorSC").focus();
                return false;
            }
            var sRemarkToCheck = $(divObj).find(".txtStandardDM").val();
            if ($(divObj).find(".txtStandardDM").data("input") == true && (sRemarkToCheck == "" || sRemarkToCheck == $(divObj).find(".txtStandardDM").attr("placeholder"))) {
                $(divObj).find(".txtStandardDM").focus();
                return false;
            }
            else if (sRemarkToCheck.length > 2000) {
                alert("检验描述不能超过2000个字！");
                $(divObj).find(".txtStandardDM").focus();
                return false;
            }

            var qsLevel = $(divObj).data("level");
            var arr = [];
            //分类：整 档
            var ddlMatLevel = $(divObj).find(".ddlStandardMatLevel");
            if ($("#hidTSIType").val() == "1" && $(ddlMatLevel).length > 0) {
                //针对质量标准：档次
                var fLevel = $(ddlMatLevel).val();
                if (fLevel != "0") {
                    qsLevel = fLevel;
                }
            }
            else {
                //针对质量标准：所有的项
                if ($("#hidTSIType").val() == "1") {
                    //绑定个档次的标准
                    $(divObj).find(".info-edit[data-level]").each(function () {
                        var matLevel = $(this).data("level");
                        var nodeValue = $(this).find(".txtStandardValueNode").val();
                        if (nodeValue.length > 100) {
                            alert("标准数值不能超过100个字！");
                            $(this).find(".txtStandardValueNode").focus();
                            return false;
                        }
                        var nodeDiffMin = $(this).find(".ddlStandardMINNode").val();//合格范围值最小
                        var nodeDiffMax = $(this).find(".ddlStandardMAXNode").val();//合格范围值最大

                        var item = new Object();
                        item.tsiMatlevel = matLevel;
                        item.tsiValue = nodeValue;
                        item.tsiStandardmin = nodeDiffMin;
                        item.tsiStandardmax = nodeDiffMax;
                        arr.push(item);
                    });
                }
            }
            var obj = new Object();
            obj.tsiId = $("#hidTSIID").val();
            obj.tsiTreefour = $("#hidTreeID").val();
            obj.tsiType = $("#hidTSIType").val();
            obj.tsiName = sName;
            obj.tsiUnit = sUnit;
            obj.tsiUnitname = sUnitName;
            obj.tsiValue = value;
            obj.tsiStandardmin = sDiffMin;
            obj.tsiStandardmax = sDiffMax;
            obj.tsiExteriorname = sAttrNameOne;//属性一
            obj.tsiSpecialclaim = sClaimToCheck;
            obj.tsiExteriorsc = sAttrNameTwo;//属性二
            obj.tsiDetectmethod = sRemarkToCheck;
            obj.items = arr;
            if (parentID) {
                obj.tsiParentid = parentID;
            }
            if (qsLevel) {
                obj.tsiMatlevel = qsLevel;
            }
            return obj;
        }
    </script>
    <script>
        $(function () {

            var bbSave = true;
            //提交处理（池子）
            $(document).on("click", "#btnProcessingSubmit", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                //获取选中标签
                var spanObj = $(".d_processing_title .current");
                if (spanObj.length == 0) {
                    alert("请选择问题的标签");
                    bbSave = true;
                    return false;
                }
                var issueRemark = $("#txtProcessingIssueRemark").val();
                if (issueRemark == "") {
                    alert("请填写问题汇总！");
                    bbSave = true;
                    return false;
                }

                var obj = new Object();
                obj.pe_Type = 0;

                var isFlag = true;
                //执行
                var liExe = $(".execute-ul li.current");
                if ($(liExe).data("type") == 1) {
                    var exeUser = $("#ddlProcessingUser").val();
                    var exeTime = $("#txtProcessingClaimTime").val();
                    var exeScore = $("#txtProcessingClaimScore").val();
                    var exeRemark = $("#txtProcessingClaimRemark").val();
                    //整改执行
                    if (exeTime != "" || exeScore != "" || exeRemark != "") {
                        if (exeTime == "") {
                            alert("整改执行必须要选择结束时间！");
                            isFlag = false;
                        }
                        else if (exeScore == "" || exeScore == "0" || isNaN(exeScore) == true) {
                            $("#txtProcessingClaimScore").focus();
                            alert("请录入有效的金额！");
                            isFlag = false;
                        }
                        else if (exeRemark == "") {
                            $("#txtProcessingClaimRemark").focus();
                            alert("请录入执行的备注！");
                            isFlag = false;
                        }
                        if (exeUser == "" || exeUser == "0") {
                            alert("请选择执行人！");
                            isFlag = false;
                        }

                        obj.pe_Type = 1;
                        obj.pe_FirstScore = exeScore;
                        obj.pe_ClaimTime = exeTime;
                        obj.pe_Remark = exeRemark;
                        obj.poe_UserType = 1;
                        obj.poe_UserNo = exeUser;
                        obj.poe_UserName = $("#ddlProcessingUser").find("option:selected").text();
                    }
                }
                else {
                    var exeUser = $("#ddlProcessingUser").val();
                    var exeScore = $("#txtProcessingScore").val();
                    var exeRemark = $("#txtProcessingRemark").val();
                    //立即执行
                    if (exeScore != "" || exeRemark != "") {
                        if (exeScore == "" || exeScore == "0" || isNaN(exeScore) == true) {
                            $("#txtProcessingScore").focus();
                            alert("请录入有效的金额！");
                            isFlag = false;
                        }
                        else if (exeRemark == "") {
                            $("#txtProcessingRemark").focus();
                            alert("请录入执行的备注！");
                            isFlag = false;
                        }
                        if (exeUser == "" || exeUser == "0") {
                            alert("请选择执行人！");
                            isFlag = false;
                        }
                        obj.pe_Type = 2;
                        obj.pe_FirstScore = exeScore;
                        obj.pe_Remark = exeRemark;
                        obj.poe_UserType = 1;
                        obj.poe_UserNo = exeUser;
                        obj.poe_UserName = $("#ddlProcessingUser").find("option:selected").text();
                    }
                }
                if (isFlag == false) {
                    bbSave = true;
                    return false;
                }

                if (confirm("确定提交处理？") == false) {
                    bbSave = true;
                    return false;
                }
                var proTitle = $(spanObj).text();
                obj.cardNo = $("#hidUserNo").val();
                obj.userName = $("#hidUserName").val();
                obj.p_MainID = $("#hidTreeID").val();
                obj.p_OtherID = $("#hidCityID").val();
                obj.p_Title = proTitle;
                obj.p_Type = 10;
                obj.p_Remark = issueRemark;
                obj.p_Summary = "";
                //执行

                /*提交评价*/
                $.ajax({
                    type: 'POST',
                    url: "/Public/SubmitMaterialProcessing",
                    data: obj,
                    success: function (msg) {
                        if (msg != "ok") {
                            alert(msg);
                            bbSave = true;
                        }
                        else {
                            if (obj.pe_Type == 2) {//立即执行
                                ExecuteToUserSystem(obj);
                            }

                            var trObj = $("#tabDataToMaterial tr[id=" + obj.p_MainID + "]");
                            $(trObj).find("td:eq(4)").html(720);
                            $(trObj).attr("zz-mark", (obj.pe_Type == 0 ? 1 : 2));
                            var trLastObj = $("#tabDataToMaterial tr:last");
                            trObj.insertAfter(trLastObj);

                            LoadTreeListToSearch();//搜索
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("提交处理出错！");
                        bbSave = true;
                    }
                });
            });


            //确定执行（池子）
            $(document).on("click", "#btnProcessingExecuteSubmit", function () {
                if (bbSave == true) {
                    bbSave = false;
                }
                else {
                    return false;
                }

                //确定积分
                var exeScore = $("#txtProcessingScoreConfirm").val();
                if (exeScore == "" || isNaN(exeScore) == true) {
                    $("#txtProcessingScoreConfirm").focus();
                    alert("请录入有效的金额！");
                    bbSave = true;
                    return false;
                }

                if (confirm("确定提交执行？") == false) {
                    bbSave = true;
                    return false;
                }

                var obj = new Object();
                obj.pe_ID = $("#btnProcessingExecuteSubmit").attr("zz-peid");
                obj.pe_LastScore = exeScore;
                obj.pe_ExecuteState = 2;
                obj.poe_UserNo = $(this).attr("zz-exeuser");
                obj.pe_FirstScore = exeScore;
                obj.pe_Remark = $("#lblProcessingClaimRemark").text();


                /*确定执行*/
                $.ajax({
                    type: 'POST',
                    url: "/Public/SvaeMaterialProcessingExecute",
                    data: obj,
                    success: function (msg) {
                        if (msg != "ok") {
                            alert(msg);
                            bbSave = true;
                        }
                        else {
                            if (obj.pe_FirstScore > 0) {
                                ExecuteToUserSystem(obj);
                            }

                            $("#tabDataToMaterial tr[id=" + $("#hidTreeID").val() + "]").click();
                            bbSave = true;
                        }
                    },
                    error: function (err) {
                        alert("确定执行出错！");
                        bbSave = true;
                    }
                });
            })


            function ExecuteToUserSystem(obj) {
                var userM = new Object();
                userM.em_Sponsor = $("#hidUserNo").val();//   发起人卡号
                userM.em_Executor = obj.poe_UserNo;//  执行人卡号
                userM.em_Content = obj.pe_Remark;//    备注
                userM.rp_Points = 0;//    默认0
                userM.rp_Money = -obj.pe_FirstScore;//     奖罚金额
                userM.rp_Method = 2;//    奖罚方式（事件默认工资）

                $.ajax({
                    type: 'POST',
                    async: false,
                    url: "@(ViewBag.EventUI)/api/EventPlatform/LaunchEventToHr",
                    data: userM,
                    success: function (msg) {
                        if (msg.status != 0) {
                            console.log("事件执行失败");
                        }
                        else {
                            console.log("事件执行成功");
                        }
                    },
                    error: function (err) {
                        console.log("事件执行出错");
                    }
                });

            }
        })
    </script>
    <script>
        //子库-成本-成本弹出层
        function countDiv() {
            //成本计算
            var divcostchaH = 10;
            for (var i = 0; i < $(".divcostAlert .divcostcha").length; i++) {
                divcostchaH += $(".divcostAlert .divcostcha").eq(i).outerHeight(true);
            }
            var divcostHeight = $(".divcostAlert").height() - divcostchaH;
            $(".divcostScroll").height(divcostHeight).slimScroll({height: divcostHeight, borderRadius: "0px"});
            $(".divcostScroll").parent(".slimScrollDiv").height(divcostHeight);
            //使用排名
            var divuserankchaH = 10;
            for (var i = 0; i < $(".useRankingAlert .useRankingcha").length; i++) {
                divuserankchaH += $(".useRankingAlert .useRankingcha").eq(i).outerHeight(true);
            }
            var useRankingScrollH = $(".layui-layer-content").height() - divuserankchaH;
            $(".useRankingScroll").height(useRankingScrollH);
            $(".useRankingScroll").slimScroll({height: useRankingScrollH, borderRadius: "0px"});
            $(".useRankingScroll").parent(".slimScrollDiv").height(useRankingScrollH);
            $(window).resize(function () {
                //成本计算
                var divcostchaH = 10;
                for (var i = 0; i < $(".divcostAlert .divcostcha").length; i++) {
                    divcostchaH += $(".divcostAlert .divcostcha").eq(i).outerHeight(true);
                }
                var divcostHeight = $(".divcostAlert").height() - divcostchaH;
                $(".divcostScroll").height(divcostHeight).slimScroll({height: divcostHeight, borderRadius: "0px"});
                $(".divcostScroll").parent(".slimScrollDiv").height(divcostHeight);
                //使用排名
                var divuserankchaH = 10;
                for (var i = 0; i < $(".useRankingAlert .useRankingcha").length; i++) {
                    divuserankchaH += $(".useRankingAlert .useRankingcha").eq(i).outerHeight(true);
                }
                var useRankingScrollH = $(".layui-layer-content").height() - divuserankchaH;
                $(".useRankingScroll").height(useRankingScrollH);
                $(".useRankingScroll").slimScroll({height: useRankingScrollH, borderRadius: "0px"});
                $(".useRankingScroll").parent(".slimScrollDiv").height(useRankingScrollH);
            })
        }

        //子库-操作记录-材料弹出层countAlertH(".oprecordAlert")
        function countAlertH(obj) {
            var ocha = $(obj).find(".page-list").outerHeight(true) + 20;
            for (var i = 0; i < $(obj).find(".divcostcha").length; i++) {
                ocha += $(obj).find(".divcostcha").eq(i).outerHeight(true);
            }
            var olastH = $(obj).height() - ocha;
            $(obj).find(".oprecordScroll").height(olastH).slimScroll({height: olastH, borderRadius: "0px"});
            $(obj).find(".oprecordScroll").parent(".slimScrollDiv").height(olastH);
            $(window).resize(function () {
                var ocha = $(obj).find(".page-list").outerHeight(true) + 20;
                for (var i = 0; i < $(obj).find(".divcostcha").length; i++) {
                    ocha += $(obj).find(".divcostcha").eq(i).outerHeight(true);
                }
                var olastH = $(obj).height() - ocha;
                $(obj).find(".oprecordScroll").height(olastH).slimScroll({height: olastH, borderRadius: "0px"});
                $(obj).find(".oprecordScroll").parent(".slimScrollDiv").height(olastH);
            })
        }

        $(function () {

            //成本弹出层
            $(".analyItemCost").click(function () {
                $(".commonRgtAlert").not(".divcostAlert").hide().css("left", "100%");
                $(".anItemBor-active").removeClass("anItemBor-active");
                $(this).addClass("anItemBor-active");
                $(".divcostAlert").show().animate({
                    left: 0
                }, 200, function () {
                    countDiv();
                    //隔行换色
                    rxued.table.LChangeapart($(".divcostScroll tr"), "#fff", "#f9f9f9");
                });
            });
            $(".divcostAlert .close").click(function () {
                $(".divcostAlert").animate({
                    left: "100%"
                }, 200, function () {
                    $(this).hide();
                });
                $(".anItemBor-active").removeClass("anItemBor-active");
            });
            rxued.areaSwitch.Tab($('.j_uiTab9'), 'uiTab9-active', $('.uiTab9Con'), 'click', function () {
                countLeft();
                countMiddle();
                countRight();
                //huhuhu专项

                $(".layerRtb").animate({"left": "-100%"}, 200, function () {
                    $(this).hide();
                });



            });
            $(document).on("mouseover", ".divcostScroll tr", function () {
                $(this).addClass("Trhover");
            });
            $(document).on("mouseout", ".divcostScroll tr", function () {
                $(this).removeClass("Trhover");
            });


            //使用排名
            //$(".useRankings").click(function () {
            //    $(".commonRgtAlert").not(".useRankingAlert").hide().css("left","100%");
            //    $(".anItemBor-active").removeClass("anItemBor-active");
            //    $(this).addClass("anItemBor-active");
            //    $(".useRankingAlert").show().stop(true, true).animate({
            //        left: "0"
            //    }, 200);
            //});
            //$(".useRankingAlert .close").click(function () {
            //    $(".useRankingAlert").stop().animate({
            //        left: "100%"
            //    }, 200, function () {
            //        $(this).hide();
            //    });
            //    $(".anItemBor-active").removeClass("anItemBor-active");
            //});

        })
    </script>
    <script>
        $(function () {
            //操作记录
            var date = new Date();
            //获取本月第一天
            var startDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + "1";
            $(".startDate").val(startDate);
            //获取本月最后一天
            var new_year = date.getFullYear();    //取当前的年份
            var new_month = date.getMonth() + 1;//取下一个月的第一天，方便计算（最后一天不固定）
            if (date.getMonth() >= 11)            //如果当前大于12月，则年份转到下一年
            {
                new_month = 0;        //月份减
                new_year++;            //年份增
            }
            //new Date(year, month[, day[, hour[, minutes[, seconds[, milliseconds]]]]]);
            //重点：
            //month 参数，是指月份，从 0 开始，也就是说，0 表示 1月，2表示 3月。
            //day 参数，是指月份中的某一天，从 1 开始，也就是说 1 就是月份中的第一天，2就是第二天，0 是不在取值范围内的
            var new_date = new Date(new_year, new_month, 1);                //取当年当月中的第一天
            var last_date = new Date(new_date.getTime() - 1000 * 60 * 60 * 24);//获得当月最后一天的日期
            var endDate = last_date.getFullYear() + '-' + (last_date.getMonth() + 1) + '-' + last_date.getDate();
            $(".endDate").val(endDate);
        });
    </script>
</footer-script>

</html>