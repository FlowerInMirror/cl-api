<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<style>
    .Foursegment{background:#fff;position:absolute;left:100%;top:0;width:100%;height:100%;}
</style>
<div id="editCostLevels" v-cloak >
    <div class="divMaterialLoadAlert_top">
        <h2 class="uiTitle2 ml10">
            <i class="uiTitle-icon"></i>
            品牌
        </h2>
        <div class="materialpicture materialTopNavConm">
            <div class="SearchCon  pt10 plr10">

                <%--完善材料集遍历开始--%>
                <div v-for="(perfectMat, index) in costLevels.perfectMats" class="analyItem anItemBor analyItemThreeClick"　:data-brandid="perfectMat.mid" >
                    <p class="analyItemTit tx-center">{{perfectMat.mlevel | levelFlagTOABC(perfectMat.mlevel)}}{{index + 1 }}</p>
                    <div class="analyItemCon relative">
                        <p class="fl col-md-3 ellipsis"><span class="cLightGray pr8">品牌名称</span><span :title="perfectMat.mbrandname">{{perfectMat.mbrandname }}</span></p>
                        <p class="fl col-md-3 ellipsis"><span class="cLightGray pr8">型号名称</span><span :title="perfectMat.mbrandtype">{{perfectMat.mbrandtype }}</span></p>
                        <p class="fl col-md-3"><span class="cLightGray pr8" title="材料总成本(成本价+安装价)">成本</span><span>{{perfectMat.mcostprice + perfectMat.minstallprice | holdTwoDecimal(perfectMat.mcostprice + perfectMat.minstallprice)}}</span></p>
                        <p class="fl col-md-3"><span class="cLightGray pr8">&nbsp;</span><span class="cRed"></span></p>
                        <p class="fl col-md-3">
                            <span class="cLightGray pr8 dis-block">品牌LOGO</span>
                            <img :src="perfectMat.brandLOGOPhotoURL == null || perfectMat.brandLOGOPhotoURL == '' ? '${ctxStatic}/images/pic/zan_nopic.png' : perfectMat.brandLOGOPhotoURL " width="60" height="60">
                        </p>
                        <p class="fl col-md-3">
                            <span class="cLightGray pr8 dis-block">型号图片</span>
                            <img :src="perfectMat.branTypePhotoURL == null || perfectMat.branTypePhotoURL == '' ? '${ctxStatic}/images/pic/zan_nopic.png' : perfectMat.branTypePhotoURL " width="60" height="60">
                        </p>
                        <p class="fl col-md-3">
                            <span class="cLightGray pr8 dis-block">主图</span>
                            <img :src="perfectMat.branMainPhotoURL == null || perfectMat.branMainPhotoURL == '' ? '${ctxStatic}/images/pic/zan_nopic.png' : perfectMat.branMainPhotoURL " width="60" height="60">
                        </p>
                        <p class="fl col-md-3">
                            <span class="cLightGray pr8 dis-block">二维码</span>
                        <div class="brandQRCode" :data-matid="perfectMat.mid" ></div>
                        </p>
                    </div>
                </div>

                <%--未完善材料集遍历开始--%>
                <div v-show="null == costLevels.perfectMats" class="analyItem">
                    <p class="analyItemTit tx-center"></p>
                    <div class="analyItemCon relative">
                        无
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="divMaterialLoadAlert_bottom  pt10 plr10 boldTopLine">
        <%--<div class="mt10 clearfix ml10">--%>
        <%--<p class="minwidth204 lh20">--%>
        <%--</p>--%>
        <%--</div>--%>
        <div class="tx-center mt5 clearfix mb10">

            <div class="analyItem">
                <p class="analyItemTit tx-center">总计</p>
                <div class="analyItemCon relative">
                    <p class="fl col-md-4"><span class="cLightGray pr8">需求</span><span>10</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">品牌</span><span>{{costLevels.perfectMatSize}}</span></p>
                </div>
            </div>
        </div>
    </div>
</div>
<%--页面脚本 开始--%>

<%--四段弹出--%>
<div class="Foursegment hide">
</div>

<script>
    //四段计算
    function countFour(){
        var fourH=$(".Foursegment").height()-$(".Foursegment .uiTitle2").outerHeight(true);
        $(".FoursegmentScroll").height(fourH).slimScroll({ height: fourH, borderRadius: "0px" });
        $(".FoursegmentScroll").parent(".slimScrollDiv").height(fourH);
    }
    $(function () {

        //四段关闭
        $(document).on("click",".Foursegment .closepic",function(){
            $(".Foursegment").animate({
                left:"100%"
            },200,function(){
                // 1.隐藏 四段
                $(this).hide();
                // 3.清空 内容
                $(this).empty();

            })
        });

        setTimeout(function () {
            // 生成二维码
            $(".brandQRCode").each(function (index, domEle) {
                var matID = $(domEle).data('matid');
                var cityID = $("#hidCityID").val();
                // 生成规则如下
                var url = 'http://c.rxjyzx.com/SD/'+matID+'/'+cityID;
                // 例:http://c.rxjyzx.com/SD/33A923C1-E74B-4911-AFFB-C52DE38347BD/12
                $(domEle).attr("data-href", url);
                $(domEle).qrcode({
                    width: 60,
                    height: 60,
                    correctLevel: 0,
                    background: "#efeef3", //背景颜色
                    foreground: "black", //前景颜色
                    text: url
                });
            });
        },100)
    })


</script>

<%--VUE2.0 开始--%>
<script>
    var editCostLevelsVue = new Vue({
        el: '#editCostLevels',
        data: {
            costLevels: {}, // 成本档次数据
            brandData : {} // 品牌数据
        },
        created() {
            this.buildCostLevelsData(); // 构建 成本 档次 数据
        },
        methods: {
            // 成本 档次
            buildCostLevelsData: function () {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                var cityID = $("#hidCityID").val();
                var levelFlag = $("#hidCostMatLevel").val();
                $.ajax({
                    url: basePath + '/sublibrary-api/city_three_section/cost/levels',
                    type: 'GET',
                    dataType: 'json',
                    data: {treeFourID,cityID,levelFlag},
                    success: function (res) {
                        _this.costLevels = res.body;
                        _this.$nextTick(function () {
                            // 地区>报价>档次项>需求品牌（四段弹出 点击 事件）
                            $(".analyItemThreeClick").click(function () {
                                var matID = $(this).data('brandid');

                                // 1.设置 材料ID 到隐藏域
                                $("#hidMatID").val(matID);
                                // 2.展示 四段
                                $(".Foursegment").load(basePath + "/public-web/sublibrary/city_four_cost/brand",function () {
                                    $(".Foursegment").show().animate({
                                        left:0
                                    },200,function(){
                                        countFour();
                                    })
                                })
                            });
                        })



                    },
                    error: function (err) {alert("操作出错！");}
                });
            },

            // 展示 四段弹出
            showFour : function (matID) {
                // 1.设置 材料ID 到隐藏域
                $("#hidMatID").val(matID);
                // 2.展示 四段
                $(".Foursegment").load(basePath + "/public-web/sublibrary/city_four_cost/brand",function () {
                    $(".Foursegment").show().animate({
                        left:0
                    },200,function(){
                        countFour();
                    })
                })
            }
        }
    });
</script>


