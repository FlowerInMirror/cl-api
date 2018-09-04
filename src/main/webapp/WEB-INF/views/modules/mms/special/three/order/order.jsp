<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="ThreeOrder" v-cloak>
<div class="clearfix layerRtb-head pb10 mr10">
    <p class="fl"></p>
    <i class="fr rig_close">×</i>
</div>
<div class="layerRtb-scroll thinScroll overflow plr10">
        <div class="analyItem" v-for="ThreeOrderData in ThreeOrderDatas">
        <p class="analyItemTit tx-center">订单一</p>
        <div class="analyItemCon">
            <p class="col-md-6"><span class="cLightGray pr8">合同号</span><span>{{ThreeOrderData.ContractNo}}</span></p>
            <p class="col-md-6"><span class="cLightGray pr8">订单号</span><span>{{ThreeOrderData.OrderNumber}}</span></p>
            <p class="col-md-6"><span class="cLightGray pr8">项目经理</span><span>{{ThreeOrderData.ManagerName}}</span></p>
            <p class="col-md-6"><span class="cLightGray pr8">电话</span><span>{{ThreeOrderData.ManagerPhone}}</span></p>
            <p class="col-md-6"><span class="cLightGray pr8">送到日期</span><span>{{ThreeOrderData.ReceiptTime}}</span></p>
            <div class="col-md-6 fl">
                <div class="star_scorePs1 clearfix pt5">
                    <span class="cLightGray pr8 fl">星级</span>
                    {{ThreeOrderData.EvaluateStarToPM}}
                    <%--<div class="dis_il_block star_score fl">--%>
                        <%--<a href="javascript:void(0)" class="clibg" style="left: 0px; width: 9px;"></a>--%>
                        <%--<a class="right clibg" href="javascript:void(0)"--%>
                           <%--style="left: 9px;width: 9px;"></a>--%>
                        <%--<a href="javascript:void(0)" class="clibg" style="left: 18px; width: 9px;"></a>--%>
                        <%--<a class="right clibg" href="javascript:void(0)"--%>
                           <%--style="left: 27px; width: 9px;"></a>--%>
                        <%--<a href="javascript:void(0)" class="clibg" style="left: 36px; width: 9px;"></a>--%>
                        <%--<a class="right clibg" href="javascript:void(0)"--%>
                           <%--style="left: 45px; width: 9px;"></a>--%>
                        <%--<a href="javascript:void(0)" class="clibg" style="left: 54px; width: 9px;"></a>--%>
                        <%--<a class="right" href="javascript:void(0)" style="left: 63px; width: 9px;"></a>--%>
                        <%--<a href="javascript:void(0)" style="left: 72px; width: 9px;" class=""></a>--%>
                        <%--<a class="right" href="javascript:void(0)" style="left: 81px; width: 9px;"></a>--%>
                    <%--</div>--%>
                </div>
            </div>
            <p class="col-md-11 lh20"><span class="cLightGray pr8">评语</span>{{ThreeOrderData.EvaluateRemarkToPM}}</p>
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
    <%--VUE2.0 开始--%>
    var TwoStatus=new Vue({
        el: '#ThreeOrder',
        data: {
            ThreeOrderDatas: [],// 店铺数据
        },
        methods:{
            ThreeOrderData:function(){
                var _this=this;
                var UserID=$("#hidZXUserID").val();
                $.ajax({
                    url: gcApiSite + 'CMaterial/GetStoresDetialToProcessInfoToOrderInThird',
                    type: 'GET',
                    dataType: 'json',
                    data: {UserID:UserID},
                    success: function (res) {
                        _this.ThreeOrderDatas = res.Body;
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        },
        created:function(){
            // 页面加载构建数据
            this.ThreeOrderData();
        }
    })
</script>