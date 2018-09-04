<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="TwoOrder" v-cloak>
<div class="analyItem">
    <p class="analyItemTit tx-center">状态</p>
    <div class="analyItemCon clearfix">
        <p class="col-md-4">
            <span class="cLightGray pr8">总订单量</span>
            <span class="Company_Personnel_Liang-BZ">{{TwoOrderDatas.TotalCount}}</span>
        </p>
        <p class="col-md-4">
            <span class="cLightGray pr8">订单总额</span>
            <span class="Company_Personnel_Liang-BZ">{{TwoOrderDatas.TotalMoney}}</span>
        </p>
        <p class="col-md-4">
            <span class="cLightGray pr8">月订单数</span>
            <span class="Company_Personnel_Liang-BZ">{{TwoOrderDatas.MonthCount}}</span>
        </p>
    </div>
</div>
<div class="analyItem anItemBor">
    <p class="analyItemTit tx-center">订单</p>
    <div class="analyItemCon dindanall-click">
        <p class="col-md-4">
            <span class="cLightGray pr8">总订单量</span>
            <span class="Company_Personnel_Liang-BZ">{{TwoOrderDatas.TotalCount}}</span>
        </p>
    </div>
</div>
<div class="analyItem anItemBor analyItemCclick">
    <p class="analyItemTit tx-center">模块二</p>
    <div class="analyItemCon">

    </div>
</div>

<div class="analyItem anItemBor analyItemCclick">
    <p class="analyItemTit tx-center">模块三</p>
    <div class="analyItemCon">

    </div>
</div>
<div class="analyItem anItemBor analyItemCclick">
    <p class="analyItemTit tx-center">模块四</p>
    <div class="analyItemCon">

    </div>
</div>
</div>
<script>
    <%--VUE2.0 开始--%>
    var TwoOrder=new Vue({
        el: '#TwoOrder',
        data: {
            TwoOrderDatas: {},// 店铺数据
        },
        methods:{
            TwoOrderData:function(){
                var _this=this;
                var UserID=$("#hidZXUserID").val();
                $.ajax({
                    url: gcApiSite + 'CMaterial/GetStoresDetialToProcessInfoInSecond',
                    type: 'GET',
                    dataType: 'json',
                    data: {UserID:UserID},
                    success: function (res) {
                        _this.TwoOrderDatas = res.Body;
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        },
        created:function(){
            // 页面加载构建数据
            this.TwoOrderData();
        }
    })
</script>