<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="TwoStatus"  v-cloak>
<div class="analyItem">
    <p class="analyItemTit tx-center">状态</p>
    <div class="analyItemCon">

        <%--TODO 状态评分暂未启用--%>
        <%--<p class="fl col-md-4"><b class="col-md-4 cGreen fz14">正常</b></p>--%>
        <%--<a href="javascript:" class="circlemark-green circlemark">优</a>--%>

    </div>
</div>

<%--TODO 基础项暂未启用--%>
<%--<div class="analyItem anItemBor analyItemCclick">--%>
    <%--<p class="analyItemTit tx-center">基础</p>--%>
    <%--<div class="analyItemCon">--%>
        <%--<p class="fl col-md-4"><span class="cLightGray pr8">编号</span><span id="UserCode"></span>--%>
        <%--</p>--%>
        <%--<p class="fl col-md-4"><span class="cLightGray pr8">名称</span><span id="StoresName"></span>--%>
        <%--</p>--%>
    <%--</div>--%>
<%--</div>--%>

<div class="analyItem anItemBor analyItemCclick">
    <p class="analyItemTit tx-center">店铺</p>
    <div class="analyItemCon">
        <p class="col-md-12">
            <span class="cLightGray pr8">主营</span>
            <span>{{TwoStatusDatas.MainName}}</span>
        </p>
    </div>
</div>
<div class="analyItem anItemBor analyItemCclick">
    <p class="analyItemTit tx-center">产品</p>
    <div class="analyItemCon">

    </div>
</div>
<div class="analyItem anItemBor analyItemCclick">
    <p class="analyItemTit tx-center">订单</p>
    <div class="analyItemCon clearfix">
        <p class="col-md-4">
            <span class="cLightGray pr8">总订单量</span>
            <span class="Company_Personnel_Liang-BZ">{{TwoStatusDatas.TotalCount}}</span>
        </p>
        <p class="col-md-4">
            <span class="cLightGray pr8">订单总额</span>
            <span class="Company_Personnel_Liang-BZ">{{TwoStatusDatas.TotalMoney}}</span>
        </p>
        <p class="col-md-4">
            <span class="cLightGray pr8">月订单数</span>
            <span class="Company_Personnel_Liang-BZ">{{TwoStatusDatas.MonthCount}}</span>
        </p>
    </div>
</div>
<div class="analyItem anItemBor analyItemCclick">
    <p class="analyItemTit tx-center">平台</p>
    <div class="analyItemCon">

    </div>
</div>

<div class="analyItem anItemBor">
    <p class="analyItemTit tx-center">处理</p>
    <div class="analyItemCon chuliall-click">
    </div>
</div>
</div>
<script>
    <%--VUE2.0 开始--%>
    var TwoStatus=new Vue({
        el: '#TwoStatus',
        data: {
            TwoStatusDatas: {},// 状态数据
        },
        methods:{
            TwoStatusData:function(){
                var _this=this;
                var UserID=$("#hidZXUserID").val();
                $.ajax({
                    url: gcApiSite + 'CMaterial/GetStoresDetialToStateInfoInSecond',
                    type: 'GET',
                    dataType: 'json',
                    data: {UserID:UserID},
                    success: function (res) {
                        _this.TwoStatusDatas = res.Body;
                        //编号赋值
                        $("#hidUserCode").text($("#hidUserCode").val());
                        //名称赋值
                        $("#StoresName").text($("#hidStoresName").val());
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        },
        created:function(){
            // 页面加载构建数据
            this.TwoStatusData();
        }
    })
</script>