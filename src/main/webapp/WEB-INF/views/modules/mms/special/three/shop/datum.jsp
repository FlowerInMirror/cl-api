<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<div id="ThreeDatum" v-cloak>
<div class="clearfix layerRtb-head pb10 mr10">
    <p class="fl"></p>
    <i class="fr rig_close">×</i>
</div>
<div class="layerRtb-scroll thinScroll overflow plr10">
    <div class="analyItem">
        <p class="analyItemTit tx-center">Z1</p>
        <div class="analyItemCon">
            <p class="col-md-4"><span class="cLightGray pr8">姓名</span><span>{{ThreeDatumDatas.UserName}}</span></p>
            <p class="col-md-4"><span class="cLightGray pr8">联系电话</span><span>{{ThreeDatumDatas.UserPhone}}</span></p>
        </div>
    </div>
    <div class="analyItem">
        <p class="analyItemTit tx-center">Z2</p>
        <div class="analyItemCon">
            <p class="col-md-6"><span class="cLightGray pr8">公司名称</span><span>{{ThreeDatumDatas.CompName}}</span></p>
            <p class="col-md-3"><span class="cLightGray pr8">类别</span><span class="cRed">{{ThreeDatumDatas.CompType}}</span></p>
            <p class="col-md-3"><span class="cLightGray pr8">代理品牌</span><span></span></p>
            <p class="col-md-12"><span
                    class="cLightGray pr8">公司地址</span><span>{{ThreeDatumDatas.CompAddress}}</span></p>

        </div>
    </div>
    <div class="analyItem">
        <p class="analyItemTit tx-center">店面</p>
        <div class="analyItemCon">
            <div class="ResultsPicBox ml10 pl20 col-md-9">
                <div class="scroll relative" id="slide5">
                    <a href="javascript:" class="scrollBtn Prev"></a>
                    <div class="scroll-text scroll-text2 clearfix">
                        <ul class="scrollUl scrollUl2 clearfix" style="width: 270px;">
                            <li v-for="StorefrontData in StorefrontDatas">
                                <span class="main-span"></span>
                                <%--<img :src="">--%>
                                <div class="demand-layerDiv">
                                    <div class="demand-layer-bg"></div>
                                    <div class="demand-layer-btns">
                                        <div class="dis-il-block">
                                            <a href="javascript:" class="demand-bigBtn"
                                               data-src="${ctxStatic}/images/temp/temp_01.jpg"
                                               data-imgname="图纸1" data-msg="moreDemand2"></a>
                                        </div>
                                    </div>
                                </div>
                            </li>

                        </ul>
                    </div>
                    <a href="javascript:" class="scrollBtn Next" style=""></a>
                </div>
            </div>
        </div>
    </div>
    <div class="analyItem">
        <p class="analyItemTit tx-center">店内</p>
        <div class="analyItemCon">
            <div class="ResultsPicBox ml10 pl20 col-md-9">
                <div class="scroll relative" id="slide6">
                    <a href="javascript:" class="scrollBtn Prev"></a>
                    <div class="scroll-text scroll-text2 clearfix">
                        <ul class="scrollUl scrollUl2 clearfix" style="width: 270px;">
                            <li v-for="StoreInsideData in StoreInsideDatas">
                                <span class="main-span"></span>
                                <%--<img :src="">--%>
                                <div class="demand-layerDiv">
                                    <div class="demand-layer-bg"></div>
                                    <div class="demand-layer-btns">
                                        <div class="dis-il-block">
                                            <a href="javascript:" class="demand-bigBtn"
                                               data-src="${ctxStatic}/images/temp/temp_01.jpg"
                                               data-imgname="图纸1" data-msg="moreDemand3"></a>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <a href="javascript:" class="scrollBtn Next" style=""></a>
                </div>
            </div>
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
<script>
    $(function(){

        $(".demand-bigBtn[data-msg='moreDemand2']").each(function (i) {
            $(this).click(function () {
                var thisIndex = i;
                var thisclick = this;
                var thisId = "preview_" + i;
                var curId = "CurImg_" + i;
                //图片放大
                rxued.images.morePicLarge(thisclick, thisId, curId, thisIndex, "moreDemand2", "a");
                //more表示data-msg的值， img表示要放大的图片元素

                //点击旋转按钮
                rxued.images.rotateBtnClick(thisId, curId);
                //关闭图片放大弹出层
                rxued.images.closeImgAlert(thisId);
                //1:1
                rxued.images.oneToone(thisId, curId);
            })
        });
        $(".demand-bigBtn[data-msg='moreDemand3']").each(function (i) {
            $(this).click(function () {
                var thisIndex = i;
                var thisclick = this;
                var thisId = "preview_" + i;
                var curId = "CurImg_" + i;
                //图片放大
                rxued.images.morePicLarge(thisclick, thisId, curId, thisIndex, "moreDemand3", "a");
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
    <%--VUE2.0 开始--%>
    var ThreeDatum=new Vue({
        el: '#ThreeDatum',
        data: {
            ThreeDatumDatas: {},// 店铺资料三段数据
            StorefrontDatas:[],//店面数据
            StoreInsideDatas:[],//店内数据
        },
        methods:{
            ThreeDatumData:function(){
                var _this=this;
                var UserID=$("#hidZXUserID").val();
                $.ajax({
                    url: gcApiSite + 'CMaterial/GetStoresDetialToPersonInfoToDataInThird',
                    type: 'GET',
                    dataType: 'json',
                    data: {UserID:UserID},
                    success: function (res) {
                        _this.ThreeDatumDatas = res.Body;
                        _this.StorefrontDatas= res.Body.Items[0].Items;
                        _this.StoreInsideDatas= res.Body.Items[1].Items;
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        },
        created:function(){
            // 页面加载构建数据
            this.ThreeDatumData();
        }
    })
</script>