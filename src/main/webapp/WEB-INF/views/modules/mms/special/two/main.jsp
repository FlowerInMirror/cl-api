<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<style>
    .layerRtb{
        height:100%;}
    .scrollUl li{
        margin-bottom:10px;}
</style>

<div class="tc-center fl" id="ZhuanTcCenter">

    <%--二段导航--%>
    <div class="clearfix">
        <div class=" tc-center-tabW tc-center-tabBox">
            <ul class="clearfix uiTab9 j_uiTab9">
                <li>状态</li>
                <%--<li>基础</li>--%>
                <li>店铺</li>
                <li>产品</li>
                <li>订单</li>
                <li>平台</li>
            </ul>
        </div>
    </div>

    <%--二段盒子--%>
    <div id="j-tc-center-content" class="pr10">

        <%--状态--%>
        <div class="uiTab9Con hide" id="ZhuanStatus"></div>

        <%--TODO 基础 暂未启用--%>
        <%--<div class="uiTab9Con hide" id="ZhuanBase"></div>--%>

        <%--店铺--%>
        <div class="uiTab9Con hide" id="ZhuanShop"></div>

        <%--产品--%>
        <div class="uiTab9Con hide" id="ZhuanProduct"></div>

        <%--订单--%>
        <div class="uiTab9Con hide" id="ZhuanOrder"></div>

        <%--平台--%>
        <div class="uiTab9Con hide" id="ZhuanPlatform"></div>

    </div>

</div>

<div class="tc-right">
    <div class="tc-righttop-tab j-tc-righttop-tab"></div>

    <div class="tc-right-top clearfix">
        <div class="fl needdealt">
            <ul>
                <li class="bgRed" onclick="SidebarPopup()">一段</li>
                <li class="bgOrange" onclick="twoStageProcess()">二段</li>
            </ul>
        </div>
        <!-- 待办弹窗S -->
        <div class="tc-taskDiv">
            <div class="clearfix">
                <p class="fl">• 标题长度自行调整...</p>
                <i class="fr rig_close">×</i>
            </div>
        </div>
        <!-- 待办弹窗E -->
        <div class="visitbox thinScroll">
            <div class="visit" id="j-visit">
                <div class="visi-list tx-left">
                    <div class="visi-listtop">
                        <span class="visi-name">营</span>
                        <span class="visi-date">07-19 14:55</span>
                    </div>
                    <p class="visi-listtxt">
                        主案设计师系统静态页面已经制作完毕，已经交付开发。
                    </p>
                </div>
                <div class="visi-list tx-right">
                    <div class="visi-listtop">
                        <span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>
                    </div>
                    <p class="visi-listtxt cBlue">
                        今日新增任务：3个，均有必须近期前端制作；
                        <br>在执行任务：5个，均为正常状态；
                        <br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。
                    </p>
                </div>
                <div class="visi-list tx-right">
                    <div class="visi-listtop">
                        <span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>
                    </div>
                    <p class="visi-listtxt cBlue">
                        今日新增任务：3个，均有必须近期前端制作；
                        <br>在执行任务：5个，均为正常状态；
                        <br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。
                    </p>
                </div>
                <div class="visi-list tx-right">
                    <div class="visi-listtop">
                        <span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>
                    </div>
                    <p class="visi-listtxt cBlue">
                        今日新增任务：3个，均有必须近期前端制作；
                        <br>在执行任务：5个，均为正常状态；
                        <br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。
                    </p>
                </div>
                <div class="visi-list tx-right">
                    <div class="visi-listtop">
                        <span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>
                    </div>
                    <p class="visi-listtxt cBlue">
                        今日新增任务：3个，均有必须近期前端制作；
                        <br>在执行任务：5个，均为正常状态；
                        <br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。
                    </p>
                </div>
                <div class="visi-list tx-right">
                    <div class="visi-listtop">
                        <span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>
                    </div>
                    <p class="visi-listtxt cBlue">
                        今日新增任务：3个，均有必须近期前端制作；
                        <br>在执行任务：5个，均为正常状态；
                        <br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。
                    </p>
                </div>
                <div class="visi-list tx-right">
                    <div class="visi-listtop">
                        <span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>
                    </div>
                    <p class="visi-listtxt cBlue">
                        今日新增任务：3个，均有必须近期前端制作；
                        <br>在执行任务：5个，均为正常状态；
                        <br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。
                    </p>
                </div>
                <div class="visi-list tx-right">
                    <div class="visi-listtop">
                        <span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>
                    </div>
                    <p class="visi-listtxt cBlue">
                        今日新增任务：3个，均有必须近期前端制作；
                        <br>在执行任务：5个，均为正常状态；
                        <br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。
                    </p>
                </div>

            </div>


        </div>
    </div>
    <div class="tc-right-bottom pa10 relative">
        <div class="tc-right-bottom-left relative">
            <ul class="topdaily">
                <li>
                    <a class="topdailybtn" href="javascript:">预留</a>
                </li>
                <li>
                    <a class="topdailybtn" href="javascript:">预留</a>
                </li>
                <li>
                    <a class="topdailybtn" href="javascript:">预留</a>
                </li>
            </ul>
            <div class="dailyrgt-botradio hide">
                <div class="mb10">
                    <label class="uiRadio12 cGreen">
                        <input value="1" type="radio" name="a" class="uiRadio12-input cGreen">正常</label>
                    <label class="uiRadio12 cOrange">
                        <input value="2" type="radio" name="a" class="uiRadio12-input cOrange">异常</label>
                    <label class="uiRadio12 cRed">
                        <input value="3" type="radio" name="a" class="uiRadio12-input cRed">问题</label>
                </div>
            </div>
            <div class="dailyrgt-divZi">
                <p class="tx-center">先解决业务问题，才有资格说管理。前两个因素完成之后，再反馈系统问题。</p>
            </div>
            <div class="visi-text-content hide">
                <textarea placeholder="请输入回访内容" class="hf-textarea mb10"></textarea>
                <input class="hf-submit" type="submit" value="提交"/>
            </div>


        </div>
        <div class="EventpenaltyDiv">
            <a href="javascrpit:;" class="EventpenaltyBtn">预留</a>
            <a href="javascrpit:;" class="EventpenaltyBtn evaluateBtn">评价标签</a>
        </div>
    </div>




    <!--huhu 默认 -->
    <div class="layerRtb analyItemCclickShow hide">
        <div class="clearfix layerRtb-head pb10 mr10">
            <p class="fl"></p>
            <i class="fr rig_close">×</i>
        </div>
        <div class="layerRtb-scroll thinScroll overflow plr10">
            <div class="analyItem">
                <p class="analyItemTit tx-center">模板一</p>
                <div class="analyItemCon">

                </div>
            </div>
            <div class="analyItem">
                <p class="analyItemTit tx-center">模板二</p>
                <div class="analyItemCon">


                </div>
            </div>

        </div>
        <div class="layerRtb-bottom borT-scroll plr10 pt10 pb5">
            <div class="analyItem">
                <p class="analyItemTit tx-center">综评</p>
                <div class="analyItemCon">
                    <!-- <a href="javascript:" class="circlemark circlemark-green">优</a>
                    <p class="fl col-md-9">&nbsp;</p>
                    <p class="col-md-3 zongPing-score"><span class="cLightGray pr8">统计</span><strong class="fz14 cGreen">17分</strong>
                    </p> -->
                </div>
            </div>
        </div>
    </div>

    <%--"=======================三段========================="--%>

    <!--huhu 处理 -->
    <div class="layerRtb chuliall-alert">

    </div>
    <!--huhu 产品 -->
    <div class="layerRtb productall-alert hide">

    </div>
    <!--huhu 订单 -->
    <div class="layerRtb dindanall-alert hide">

    </div>
    <!--huhu 店铺 -->
    <div class="layerRtb dianpu-alert hide">

    </div>
    <!--huhu 资料 -->
    <div class="layerRtb ziliao-alert hide">

    </div>

    <!--专项四段弹出公用盒子 -->
    <div class="layerRtb layerRtb-four productall-alert-four hide">

    </div>

</div>
<%--"=======================Layer四段弹出========================="--%>

<%--关联材料弹出层--%>
<div class="alertCon pa10 hide divTempSplitSearchMaterial layui-layer-wrap"></div>
<%--主营科目列表弹出层--%>
<div id="boxMajorTreeList" class="alertCon pa10 hide layui-layer-wrap"></div>

<script>
    $(function(){
        //图片移入显示放大删除按钮
        $(".productall-alert-four .brandlogopic_new").each(function () {
            $(this).hover(function () {
                $(this).find(".uiImgUpload-mark").show();
            }, function () {
                $(this).find(".uiImgUpload-mark").hide();
            });
        });
        //套餐放大
        $(".productall-alert-four .enlarge_link").each(function (i) {
            $(this).click(function () {
                var thisclick = this;
                var thisId = "preview_" + i;
                var curId = "CurImg_" + i;
                //图片放大
                rxued.images.enSingleLarge(thisclick, thisId, curId);
                //点击旋转按钮
                rxued.images.rotateBtnClick(thisId, curId);
                //关闭图片放大弹出层
                rxued.images.closeImgAlert(thisId);
                //1:1
                rxued.images.oneToone(thisId, curId);
            })
        });
        //产品详细移入移除显示放大按钮
        $(".scrollUl3Product li").each(function(){
            $(this).hover(function(){
                $(this).find(".demand-layerDiv").show();
            },function(){
                $(this).find(".demand-layerDiv").hide();
            })
        })
        //产品详细图片放大
        $(".demand-bigBtn[data-msg='moreDemand4']").each(function (i) {
            $(this).click(function () {
                var thisIndex = i;
                var thisclick = this;
                var thisId = "preview_" + i;
                var curId = "CurImg_" + i;
                //图片放大
                rxued.images.morePicLarge(thisclick, thisId, curId, thisIndex, "moreDemand4", "a");
                //more表示data-msg的值， img表示要放大的图片元素

                //点击旋转按钮
                rxued.images.rotateBtnClick(thisId, curId);
                //关闭图片放大弹出层
                rxued.images.closeImgAlert(thisId);
                //1:1
                rxued.images.oneToone(thisId, curId);
            })
        });


    })
</script>