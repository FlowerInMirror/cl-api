<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<div id="cityFourCostBrandVue" >
    <div class="Foursegment hide">
        <h2 class="uiTitle2 ml10">
            <i class="uiTitle-icon"></i>
            品牌
            <a href="javascript:" class="closepic  fr mr5 mt2"></a>
        </h2>
        <div class="FoursegmentCon">
            <div class="FoursegmentScroll">
                <div class="pa10">

                    <!--成本 开始-->
                    <div class="analyItem">
                        <p class="analyItemTit tx-center">成本</p>
                        <div class="analyItemCon relative">
                            <p class="fl col-md-3"><span class="cLightGray pr8">品牌名称</span><span>{{brandData.mbrandname}}</span></p>
                            <p class="fl col-md-3"><span class="cLightGray pr8">型号名称</span><span :title="brandData.mbrandtype">{{brandData.mbrandtype | }}</span></p>
                            <p class="fl col-md-3"><span class="cLightGray pr8"  title="材料总成本(成本价+安装价)" >成本</span><span  title="材料总成本(成本价+安装价)" >{{brandData.mcostprice + brandData.minstallprice | holdTwoDecimal(brandData.mcostprice + brandData.minstallprice)}}</span></p>
                        </div>
                    </div>

                    <!--主案 开始-->
                    <div class="analyItem">
                        <p class="analyItemTit tx-center">主案</p>
                        <div class="analyItemCon relative">
                            <p class="fl col-md-3"><span class="cLightGray pr8">品牌名称</span><span>{{brandData.mbrandname}}</span></p>
                            <p class="fl col-md-3"><span class="cLightGray pr8">型号名称</span><span :title="brandData.mbrandtype">{{brandData.mbrandtype | }}</span></p>
                            <p class="fl col-md-3">&nbsp;</p>
                            <p class="fl col-md-3">&nbsp;</p>
                            <p class="fl col-md-3">
                                <span class="cLightGray pr8 dis-block">品牌LOGO</span>
                                <img :src="brandData.brandLOGOPhotoURL == null || brandData.brandLOGOPhotoURL == '' ? '${ctxStatic}/images/pic/zan_nopic.png' : brandData.brandLOGOPhotoURL " width="60" height="60">
                            </p>
                            <p class="fl col-md-3">
                                <span class="cLightGray pr8 dis-block">型号图片</span>
                                <img :src="brandData.branTypePhotoURL == null || brandData.branTypePhotoURL == ''? '${ctxStatic}/images/pic/zan_nopic.png' : brandData.branTypePhotoURL " width="60" height="60">
                            </p>
                            <p class="fl col-md-3">
                                <span class="cLightGray pr8 dis-block">主图</span>
                                <img :src="brandData.branMainPhotoURL == null || brandData.branMainPhotoURL == ''? '${ctxStatic}/images/pic/zan_nopic.png' : brandData.branMainPhotoURL " width="60" height="60">
                            </p>
                            <p class="fl col-md-3">
                                <span class="cLightGray pr8 dis-block">二维码</span>
                            <div class="brandQRCode2" :data-matid="brandData.mid" ></div>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--VUE2.0 开始--%>
<script>
    var cityFourCostBrandVue = new Vue({
        el: '#cityFourCostBrandVue',
        data: {
            brandData: {} // 品牌数据
        },
        created() {
            this.buildBrnadData(); // 构建 成本 档次 数据
        },
        methods: {
            // 构建 品牌数据
            buildBrnadData: function () {
                var _this = this;
                var matID = $("#hidMatID").val();
                console.log("4段"+matID)
                $.get( basePath +"/sublibrary-api/city_three_section/four/brand?date="+new Date() , {matID}, function(data) {
                    var statusMsg = data.statusMsg; // 响应状态消息
                    if (statusMsg == "success") {
                        _this.brandData = data.body;

                        // $nextTick 是在下次 DOM 更新循环结束之后执行延迟回调，在修改数据之后使用 $nextTick，则可以在回调中获取更新后的 DOM，API 文档中官方示例如下：
                        _this.$nextTick(function () {
                            // DOM is now updated DOM现在正在更新
                            // `this` is bound to the current instance `this‘绑定到当前实例
                            // 生成二维码
                            $(".brandQRCode2").each(function (index, domEle) {
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
                        })

                    } else {
                        alert(statusMsg)
                    }
                });
            }
        }
    });
</script>
