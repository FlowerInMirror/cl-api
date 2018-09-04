<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<style>
    .choice-span, .choice-span3 {
        display: inline-block;
        border: 1px solid #ddd;
        border-radius: 3px;
        padding: 0 10px;
        line-height: 24px;
        margin-bottom: 5px;
        margin-right: 4px;
        cursor: pointer;
    }
    .choice-span3.current {
        background: #0099ff;
        color: #fff;
    }
    .choice-item .mr0 {
        margin-right: 0px;
        padding: 0px 8px;
    }
    .choice-item .fz12 {
        font-size: 12px;
    }
    .operate-roleNav {
        display: inline-block;
    }
    .operate-roleNav li {
        height: 26px;
        line-height: 24px;
        border-radius: 4px;
        border: 1px solid #ddd;
        padding: 0 6px;
        margin-right: 5px;
        float: left;
        cursor: pointer;
    }
    .operate-roleNav li.current {
        background: #0099ff;
        color: #fff;
    }
    .uiBtn-gray-no {
        text-align: center;
        background: #f4f4f4;
        color: #999;
        cursor: not-allowed;
        border: 1px solid #e6e6e6;
        border-radius: 3px;
    }
    .rectify-time2 {
        position: absolute;
        right: 10px;
        top: 0;
    }
    .width95 {
        width: 95px;
    }
    .evaluate-textarea {
        width: 100%;
        height: 66px;
        padding: 4px 8px;
    }
    .operate-bottomR {
        border-left: 5px solid #f1f1f1;
    }
    .operate-bottomDiv {
        position: relative;
    }
    .width65 {
        width: 65px;
    }
    .taskChoice-alert {
        width: 100%;
        position: absolute;
        left: 100%;
        top: 0;
        height: 115px;
        border: 1px solid #ddd;
        z-index: 1;
        background: #fff;
    }
    .taskChoice-con {
        width: 222px;
        margin: 0 auto;
    }
    .width46 {
        width: 60px;
    }
    .moreClick img{width:30px;height:30px;background-size:100% 100%;}
    .mt45{margin-top: 45px;}
</style>

<div class="divMaterialLoadAlert_topm pl10">
    <h2 class="uiTitle2">
        <i class="uiTitle-icon"></i>
        <span class="ml10">状态处理</span>
    </h2>
</div>
<div id="matStatusThreeHandleVue"  v-cloak>
    <div class="clAlertTopFoo">
        <div class="SearchCon  pt10 plr10">
            <!--处理信息 开始-->
            <div class="analyItem cl_zh">
                <p class="analyItemTit tx-center" style="background: #3c6;color: #eaf4fe;">平台</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8">一级</span><span >{{platform.treeOneName}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">二级</span><span >{{platform.treeTwoName}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">名称</span><span title="亚克力雕刻字" >{{platform.matName}}</span></p>
                    <p class="fl col-md-12"><span class="cLightGray pr8">规格</span><span title="≤300mm" >{{platform.matSpec}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">单位</span><span class="lbl_base_unit ">{{platform.unitName}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">分类</span><span class="lbl_base_finishProductType ">{{platform.matClassify == 1 ? '成品类' : '非成品类' }}</span></p>

                    <a v-show="platform.platformLoopStatus == 1" href="javascript:" class="circlemark circlemark-green">1</a>
                    <a v-show="platform.platformLoopStatus != 1" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
                </div>
            </div>

            <div class="analyItem cl_zh">
                <p class="analyItemTit tx-center">A档</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8" title="无/x种">品牌总数</span>
                        <span v-show="aLevel.brandSize == 0" class="cRed " title="无品牌"> 无</span>
                        <span v-show="aLevel.brandSize != 0" >1种</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">质量标准</span>
                        <span v-show="aLevel.qualityStandardStatus == 0" class="cRed ">未</span>
                        <span v-show="aLevel.qualityStandardStatus == 1" class="cGreen">完成</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">对&ensp;比&ensp;项</span>
                        <span v-show="aLevel.contrastItemStatus == 0" class="cRed">未</span>
                        <span v-show="aLevel.contrastItemStatus == 1" class="cGreen ">完成</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">品牌详情</span>
                        <span v-show="aLevel.brandSize == 0 " class="cRed " title="因无品牌无法完成总计">无</span>
                        <span v-show="aLevel.brandDetailNotCount == 0 &amp;&amp; aLevel.brandSize != 0 " class="cGreen ">完成</span>
                        <span v-show="aLevel.brandDetailNotCount > 0" class="cRed " title="未完成品牌详情录入的品牌种数">未1种</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">供&ensp;货&ensp;商</span>
                        <span v-show="aLevel.brandSize == 0 " class="cRed " title="因无品牌无法完成总计">无</span>
                        <span v-show="aLevel.supplierNotCount == 0 &amp;&amp; aLevel.brandSize != 0 " class="cGreen ">完成</span>
                        <span v-show="aLevel.supplierNotCount > 0" class="cRed " title="未入驻供应商的品牌种数">未1种</span>
                    </p>

                    <a v-show="aLevel.levelTotalLoopScore == 1" href="javascript:" class="circlemark circlemark-green ">1</a>
                    <a v-show="aLevel.levelTotalLoopScore == 0.5" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
                </div>
            </div>

            <div class="analyItem cl_zh">
                <p class="analyItemTit tx-center" style="background: #3c6;color: #eaf4fe;">B档</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8" title="无/x种">品牌总数</span>
                        <span v-show="aLevel.brandSize == 0" class="cRed " title="无品牌"> 无</span>
                        <span v-show="aLevel.brandSize != 0" class="">1种</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">质量标准</span>
                        <span v-show="aLevel.qualityStandardStatus == 0" class="cRed ">未</span>
                        <span v-show="aLevel.qualityStandardStatus == 1" class="cGreen">完成</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">对&ensp;比&ensp;项</span>
                        <span v-show="aLevel.contrastItemStatus == 0" class="cRed">未</span>
                        <span v-show="aLevel.contrastItemStatus == 1" class="cGreen ">完成</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">品牌详情</span>
                        <span v-show="aLevel.brandSize == 0 " class="cRed " title="因无品牌无法完成总计">无</span>
                        <span v-show="aLevel.brandDetailNotCount == 0 &amp;&amp; aLevel.brandSize != 0 " class="cGreen ">完成</span>
                        <span v-show="aLevel.brandDetailNotCount > 0" class="cRed " title="未完成品牌详情录入的品牌种数">未1种</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">供&ensp;货&ensp;商</span>
                        <span v-show="aLevel.brandSize == 0 " class="cRed " title="因无品牌无法完成总计">无</span>
                        <span v-show="aLevel.supplierNotCount == 0 &amp;&amp; aLevel.brandSize != 0 " class="cGreen ">完成</span>
                        <span v-show="aLevel.supplierNotCount > 0" class="cRed " title="未入驻供应商的品牌种数">未1种</span>
                    </p>

                    <a v-show="aLevel.levelTotalLoopScore == 1" href="javascript:" class="circlemark circlemark-green ">1</a>
                    <a v-show="aLevel.levelTotalLoopScore == 0.5" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
                </div>
            </div>

            <div class="analyItem cl_zh">
                <p class="analyItemTit tx-center">C档</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8" title="无/x种">品牌总数</span>
                        <span v-show="cLevel.brandSize == 0" class="cRed " title="无品牌"> 无</span>
                        <span v-show="cLevel.brandSize != 0" class="">1种</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">质量标准</span>
                        <span v-show="cLevel.qualityStandardStatus == 0" class="cRed ">未</span>
                        <span v-show="cLevel.qualityStandardStatus == 1" class="cGreen">完成</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">对&ensp;比&ensp;项</span>
                        <span v-show="cLevel.contrastItemStatus == 0" class="cRed">未</span>
                        <span v-show="cLevel.contrastItemStatus == 1" class="cGreen ">完成</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">品牌详情</span>
                        <span v-show="cLevel.brandSize == 0 " class="cRed " title="因无品牌无法完成总计">无</span>
                        <span v-show="cLevel.brandDetailNotCount == 0 &amp;&amp; cLevel.brandSize != 0 " class="cGreen ">完成</span>
                        <span v-show="cLevel.brandDetailNotCount > 0" class="cRed " title="未完成品牌详情录入的品牌种数">未1种</span>
                    </p>

                    <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">供&ensp;货&ensp;商</span>
                        <span v-show="cLevel.brandSize == 0 " class="cRed " title="因无品牌无法完成总计">无</span>
                        <span v-show="cLevel.supplierNotCount == 0 &amp;&amp; cLevel.brandSize != 0 " class="cGreen ">完成</span>
                        <span v-show="cLevel.supplierNotCount > 0" class="cRed " title="未入驻供应商的品牌种数">未1种</span>
                    </p>

                    <a v-show="cLevel.levelTotalLoopScore == 1" href="javascript:" class="circlemark circlemark-green ">1</a>
                    <a v-show="cLevel.levelTotalLoopScore == 0.5" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
                </div>
            </div>

        </div>
    </div>
    <div class="layerRtb-bottom operate-bottom  pt10 plr10 clAlertBotoomFoo boldTopLine">

        <div class="analyItem">
            <p class="analyItemTit tx-center">综合</p>
            <div class="analyItemCon">
                <p class="fl col-md-3"><b class="fz14"></b></p>
                <%--<p class="fl col-md-4"><span class="cLightGray pr8">得分</span><strong class="fz14 cGreen">{{(platform.platformLoopStatus == 0 ? 0.5 : 1) +  cLevel.levelTotalLoopScore + bLevel.levelTotalLoopScore + aLevel.levelTotalLoopScore}}分</strong></p>--%>
                <a v-show="platform.platformLoopStatus == 1 &&  cLevel.levelTotalLoopScore == 1 && bLevel.levelTotalLoopScore == 1 && aLevel.levelTotalLoopScore == 1" href="javascript:" class="circlemark circlemark-green ">完</a>
                <a v-show="platform.platformLoopStatus != 1 ||  cLevel.levelTotalLoopScore != 1 || bLevel.levelTotalLoopScore != 1 || aLevel.levelTotalLoopScore != 1" href="javascript:" class="circlemark circlemark-lightRed">未</a>
            </div>
        </div>

        <div class="clearfix pt10" id="handleInfoDom">
            <div class="col-md-7 fl mt45">
                <h2 class="uiTitle2 mb10 choice-item" id="handle">
                    <i class="uiTitle-icon"></i>
                    处理
                    <span class="choice-span choice-span3 mr0 fz12 choice-spanfirst cGreen" handType="1" title = "在输入框填写回访 提交即可" >正常</span>
                    <span class="choice-span choice-span3 mr0 fz12 choice-spanfirst cOrange" handType="2" title = "在输入框填写回访 提交即可" >异常</span>
                    <span class="choice-span choice-span3 mr0 fz12 choice-spanfirst cRed" handType="3" title = "在输入框填写回访 提交即可" >问题</span>
                    <span class="choice-span choice-span3 mr0 fz12 choice-spanfirst" handType="4" title = "在输入框填写回访 提交即可" >下架</span>
                </h2>
                <%--处理内容框--%>
                <div class="clearfix pb10 pr10 pt10">
                    <textarea class="evaluate-textarea" id="handleContent"></textarea>
                </div>
            </div>
            <div class="col-md-5 fl operate-bottomDiv EventBanner operate-bottomR pt20 pl10" style="overflow: hidden;height:280px;">
                <div class="pt10 tx-center" style="padding:96px 0 10px;">
                    <input type="button" class="uiBtn-normal uiBtn-blue  operate-submit" value="提交" id="buttonClick">
                </div>
            </div>
        </div>
    </div>
</div>

<%--VUE2.0 开始--%>
<script>
    function countSearchCon(){
        $(".SearchCon").each(function(){
            var endH=$(this).closest(".divMaterialLoadAlert").height()-$(this).closest(".divMaterialLoadAlert").find(".divMaterialLoadAlert-tit").outerHeight(true)-$(this).closest(".divMaterialLoadAlert").find(".divMaterialLoadAlert_topm").outerHeight(true)-$(this).closest(".divMaterialLoadAlert").find(".layerRtb-bottom").outerHeight(true);
            $(this).height(endH).slimScroll({ height: endH, borderRadius: "0px" });
            $(this).parent(".slimScrollDiv").height(endH);
        })
    }



    var matStatusThreeHandleVue = new Vue({
        el: '#matStatusThreeHandleVue',
        data: {
            platform:{},
            aLevel:{},
            bLevel:{},
            cLevel:{}
        },
        created:function() {
            this.buildMatStatusThreeHandleData();// 构建 材料状态三段处理数据
        },
        methods: {
            // 材料状态三段处理数据
            buildMatStatusThreeHandleData: function () {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                var cityID = $("#hidCityID").val();
                $.ajax({
                    url: basePath + '/sublibrary-api/city_three_section/status/handle',
                    type: 'GET',
                    dataType: 'json',
                    data: {cityID,treeFourID},
                    success: function (res) {
                        // 作用域赋值
                        _this.platform = res.body.platform;
                        _this.aLevel = res.body.aLevel;
                        _this.bLevel = res.body.bLevel;
                        _this.cLevel = res.body.cLevel;
                        countSearchCon();
                    },
                    error: function (err) {alert("操作出错！");}
                });
            }
        }
    });
</script>

