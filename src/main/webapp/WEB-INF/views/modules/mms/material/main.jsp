<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<div class="tc-center fl tc-center-mat">
    <%--二段导航--%>
    <ul class="clearfix uiTab9 j_uiTab9">
        <li ng-click="cityTwoState(hidTreeID)" class="uiTab9-active">状态</li>
        <li ng-click="cityTwoPlatform(hidTreeID)" class="">平台</li>
        <li ng-click="cityTwoMall(hidTreeID)" >商城</li>
        <li ng-show="aLevelShowFlag != 0" ui-sref="sublibrary_city.two_level_a" ng-click="showDivMaterialDetail(1)" data-level="1" >A档</li>
        <li ng-show="bLevelShowFlag != 0" ui-sref="sublibrary_city.two_level_b" ng-click="showDivMaterialDetail(2)" data-level="2">B档</li>
        <li ng-show="cLevelShowFlag != 0" ui-sref="sublibrary_city.two_level_c" ng-click="showDivMaterialDetail(3)" data-level="4">C档</li>
        <li ng-click="cityTwoPrice(hidTreeID)" >价格</li>
        <li ui-sref="sublibrary_city.two_cost" ng-click="cityTwoCost(hidTreeID)" data-ptype="quotes">报价</li> <!-- 成本 -->
        <li ui-sref="sublibrary_city.two_app" ng-click="cityTwoApp(hidTreeID)" data-ptype="app">材料商</li> <!-- 应用 -->
    </ul>

    <div id="j-tc-center-content" class="pr10">
        <!--状态 -->
        <div class="uiTab9Con" id="divMaterialDetailToStatus"></div>

        <!--平台-->
        <div class="uiTab9Con dis-none" id="divMaterialDetailToBase"></div>

        <!--商城-->
        <div class="uiTab9Con dis-none" id="divMaterialDetailToPlatform"></div>

        <!--A档-->
        <div ui-view="two_level_a" class="uiTab9Con dis-none" id="divMaterialDetailToA"></div>

        <!--B档-->
        <div ui-view="two_level_b" class="uiTab9Con dis-none" id="divMaterialDetailToB"></div>

        <!--C档-->
        <div ui-view="two_level_c" class="uiTab9Con dis-none" id="divMaterialDetailToC"></div>

        <!--价格-->
        <div class="uiTab9Con dis-none" id="divMaterialDetailToLocalPrice"></div>

        <!--成本（报价）-->
        <div ui-view="two_cost"  class="uiTab9Con dis-none" id="divMaterialDetailToQuotation"></div>

        <!--应用（APP）-->
        <div ui-view="two_app" class="uiTab9Con dis-none" id="divMaterialDetailToAPP"></div>

    </div>

</div>

<div class="tc-center fl tc-center-new hide">
    <iframe src="" frameborder="0" scrolling="no" id="divMaterialDetailIframe" width="100%" height="100%"></iframe>
</div>

<div class="tc-right tc-right-all relative">
    <!--新增任务-->
    <div class="NewAddBox alertpaper hide">
        <div class="pa10">
            <div class="clearfix NewAddBoxTit">
                <h2 class="uiTitle2 fl">
                    <i class="uiTitle-icon"></i>
                    新增任务
                </h2>
                <div class="cBlue cur fr closeNewAddBox">收起</div>
            </div>
            <div class="TaskAddBox thinScroll" style="height: 495px; overflow-y: auto;">
                <table class="uiTable-borderNone TaskAddList">
                    <tbody>
                    <tr>
                        <!--<td class="tx-right">周期：</td>-->
                        <td class="tx-left">
                            <input type="text" value="开始时间" class="width100" id="taskStartTime" onclick="WdatePicker();">
                            至
                            <input type="text" value="结束时间" class="width100" id="taskEndTime" onclick="WdatePicker();">
                        </td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <!--<td width="60" class="tx-right">任务名：</td>-->
                        <td class="tx-left"><input type="text" placeholder="任务名请输入" class="taskName" id="taskTitle"></td>
                    </tr>
                    <tr>
                        <td class="tx-left">
                            <textarea rows="3" placeholder="任务描述" id="taskContent"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="TaskAddSubmitBox mt10 tx-center">
                <input type="button" value="提交" class="uiBtn-blue uiBtn-normal" id="submitTask">
            </div>
        </div>
    </div>

    <div class="tc-righttop-tab j-tc-righttop-tab">
        <input type="button" value="列表" class="uiBtn-blue uiBtn-normal mater_lists fr" >
    </div>

    <div class="tc-right-top tc-right-top-all clearfix">
        <div class="fl needdealt">
            <ul>
            </ul>
            <ul class="pos_ab_bottom">
            </ul>
        </div>
        <div class="tc-taskDiv hide">
            <div class="clearfix">
                <i class="fr rig_close">×</i>
                <div class="pa10">
                    <div class="clearfix close_addTaskAlert mb5">
                        <a href="javascript:" class="close fr mt5"></a>
                    </div>
                </div>
                <div class="taskdiv">
                </div>
            </div>
        </div>
        <div class="tc-taskDiv-history hide fl">
            <div class="clearfix">
                <i class="fr rig_close">×</i>
                <div class="pa10">
                    <div class="clearfix close_addTaskAlert mb5">
                        <a href="javascript:" class="close fr mt5"></a>
                    </div>
                </div>
                <div class="taskdivHistory">
                    <h2 class="uiTitle2 platit plr10">
                        <i class="uiTitle-icon"></i>
                        下达任务
                    </h2>
                    <div class="SearchCon">
                        <div class="qingdanHead cha2 tabDataToTwo Temp_item">
                            <table class="uiTable" id="tb_3">
                                <thead>
                                <tr>
                                    <th width="6%">序</th>
                                    <th width="14%">任务名称</th>
                                    <th width="32%">任务描述</th>
                                    <th width="18%">推送时间</th>
                                    <th width="12%">状态</th>
                                    <th>处理时间</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="Temp_itemscroll">
                            <table class="uiTable" id="tb_4">
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="visitbox">
            <div class="visit visiterecord-rgt-scroll thinScroll" id="j-visit">
                <div class="visiterecord-rgt-scroll-con">

                </div>
            </div>
        </div>
    </div>

    <div class="tc-right-bottom pa10 relative">
        <div class="tc-right-bottom-left relative">
            <ul class="topdaily">
                <li>
                    <a class="topdailybtn visittype" data-val="1" href="javascript:">正常</a>
                </li>
                <li>
                    <a class="topdailybtn visittype" data-val="2" href="javascript:">异常</a>
                </li>
                <li>
                    <a class="topdailybtn visittype" data-val="3" href="javascript:">问题</a>
                </li>
            </ul>
            <div class="dailyrgt-botradio hide">
            </div>
            <div class="dailyrgt-divZi">
                <p class="tx-center">先解决业务问题，才有资格说管理。前两个因素完成之后，再反馈系统问题。</p>
            </div>
            <div class="visi-text-content hide">
                <textarea placeholder="请输入回访内容" class="hf-textarea mb10" id="txtVisitContent"></textarea>
                <input class="hf-submit" type="submit" value="提交" />
            </div>
        </div>
        <div class="EventpenaltyDiv">
            <a href="javascrpit:;" class="EventpenaltyBtn NewAddBtn">新增待办</a>
        </div>
    </div>

    <!--基础信息 弹出层开始-->
    <div class="divBaseInfoAlert pa10 hide alertpaper">
        <h3 class="divBaseInfoAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divBaseInfoAlert-con">
            <!--参数 开始-->
            <div class="hasscroll materialTopNavCon pl5 pr5">
                <h4 class="h4title mb2">基础信息</h4>
                <div class="pl10">
                    <div class="clearfix ">
                        <p class="fl minwidth204 lh20">
                            <span class="pr5 fl "><span class="justify_span justify_span_w40">单位</span>：</span>
                            <span class="fl">cm</span>
                        </p>
                        <p class="fl minwidth204 mr5 lh20">
                            <span class="pr5 fl">分类：</span>
                            <span class="fl">成品类</span>
                        </p>
                    </div>
                    <div class="clearfix ">
                        <span class="pr5 fl lh20"><span class="justify_span justify_span_w40">功能</span>：</span>
                        <span class="fl">功能功能功能功能功能功能功能</span>
                    </div>
                    <div class="clearfix mb10">
                        <span class="fl justify_span justify_span_w40">描述</span>：
                        <div class="ml52 ">描述描述描述描述描述描述描述</div>
                    </div>
                    <div class="clearfix ">
                        <span class="pr5 fl lh20"><span class="justify_span justify_span_w40">搜索词</span>：</span>
                        <span class="fl">搜索词搜索词搜索词搜索词搜索词</span>
                    </div>
                    <div class="clearfix ">
                        <span class="pr5 fl lh20"><span class="justify_span justify_span_w40">用途</span>：</span>
                        <span class="fl">用途用途用途用途用途用途用途</span>
                    </div>
                </div>
                <h4 class="h4title mb2">材料参数</h4>
                <div class="pl10">
                    <div class="clearfix ">
                        <p class="fl minwidth204 lh20"><span class="pr5"><span class="justify_span justify_span_w40">长</span>：</span>10cm</p>
                        <p class="fl minwidth204 lh20"><span class="pr5"><span class="justify_span justify_span_w40">宽</span>：</span>2cm</p>
                    </div>
                </div>
                <div class="mt10 tx-center">
                    <input type="button" value="修改基础" class="uiBtn-blue uiBtn-normal">
                    <p class="fl minwidth204 lh20"><span class="pr5 cRed">注：修改此基础信息会同步全国此材料规格的基础信息</span></p>
                </div>
            </div>
            <!--参数 结束-->
        </div>
    </div>

    <!--材料 弹出层开始-->
    <div class="divMaterialAlert pa10 hide alertpaper">
        <h3 class="divMaterialAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialAlert-con" id="divAlertMaterialBaseInfo"></div>
    </div>

    <!--照片 弹出层开始-->
    <div class="divMaterialPhotoAlert pa10 hide alertpaper">
        <h3 class="divMaterialPhotoAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialPhotoAlert-con" id="divAlertMaterialPhoto"></div>
    </div>

    <!--基本信息 公用 弹出层开始-->
    <div class="divMaterialBasePublicAlert pa10 hide alertpaper">
        <h3 class="divMaterialBasePublicAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialBasePublicAlert-con" id="divAlertMaterialBasePublic"></div>
    </div>


    <!--材料标准 弹出层开始-->
    <div class="divMaterialStandardAlert pa10 hide alertpaper">
        <h3 class="divMaterialStandardAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialStandardAlert-con" id="divAlertMaterialStandard"></div>
    </div>

    <!--材料工艺 弹出层开始-->
    <div class="divMaterialCraftsAlert pa10 hide alertpaper">
        <h3 class="divMaterialCraftsAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialCraftsAlert-con" id="divAlertMaterialCrafts"></div>
    </div>

    <!--成本价格 弹出层开始-->
    <div class="divMaterialPriceAlert pa10 hide alertpaper">
        <h3 class="divMaterialPriceAlert-tit uiTitle2">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialPriceAlert-con" id="divAlertMaterialPrice"></div>
    </div>

    <!--交易价 弹出层开始-->
    <div class="divMaterialPriceSupAlert pa10 hide alertpaper">
        <div class="divMaterialPriceSupAlert-tit clearfix">
            <h3 class="uiTitle2 mb5  fl">
                <i class="uiTitle-icon mr5"></i>
                价格
            </h3>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </div>
        <div class="divMaterialPriceSupAlert-con" id="divAlertMaterialPriceSup"></div>
    </div>

    <!--加载性弹层 弹出层开始-->
    <div id="three-section" class="divMaterialLoadAlert hide alertpaper relative">
        <h3 class="divMaterialLoadAlert-tit uiTitle2 clearfix platit">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <%-- 三段弹出位 --%>
        <div class="divMaterialLoadAlert-con" id="divAlertMaterialLoad" >
        </div>
    </div>

</div>
<input type="hidden" id="hidFinishProductType" value="2" />
<script>
    function countH(){
        //登陆集团材料--材料子库--平台--包装标准弹出层
        $(".divMaterialLoadAlert-con").each(function(){
            var odivMaterialLoadAlertConH=$(this).parents(".divMaterialLoadAlert").height()-$(this).parents(".divMaterialLoadAlert").find(".divMaterialLoadAlert-tit").outerHeight(true);
            $(this).height(odivMaterialLoadAlertConH).slimScroll({ height: odivMaterialLoadAlertConH, borderRadius: "0px" });
            $(this).parents(".slimScrollDiv").height(odivMaterialLoadAlertConH);
        });
        $(window).resize(function(){
            $(".divMaterialLoadAlert-con").each(function(){
                var odivMaterialLoadAlertConH=$(this).parents(".divMaterialLoadAlert").height()-$(this).parents(".divMaterialLoadAlert").find(".divMaterialLoadAlert-tit").outerHeight(true);
                $(this).height(odivMaterialLoadAlertConH).slimScroll({ height: odivMaterialLoadAlertConH, borderRadius: "0px" });
                $(this).parents(".slimScrollDiv").height(odivMaterialLoadAlertConH);
            });

        })
    }
    $("#hidIcaType").val("0");
    $(function () {

        $(".topdaily .topdailybtn").click(function () {
            $(this).parents(".topdaily").find(".topdailybtn").removeClass("active");
            $(this).addClass("active");
            $(".dailyrgt-divZi").hide();
            $(".visi-text-content").show();
            $(".dailyrgt-botradio").hide();
        });
        $('.tc-right-top-all').height($('.tc-right-all').height())
    });
</script>
<script>
    $(function () {
        $(".mater_check").click(function () {
            $('.iframe_check').css({
                "position": "fixed",
                "left": '0',
                "top": '0',
                "width": '100%',
                "height": '100%',
                "z-index": "100"
            })
            $('.iframe_check').attr('src', '/html/material_check.html');
            $('.iframe_check').show();
            $(".iframe_check_01").show();
        })
    })
</script>
