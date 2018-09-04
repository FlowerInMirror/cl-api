<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<div id="TwoShop"  v-cloak>
<div class="analyItem">
    <p class="analyItemTit tx-center">状态</p>
    <div class="analyItemCon">
        <p class="col-md-11">
            <span class="cLightGray pr8">主营</span>
            <span>{{TwoShopDatas.MainName}}</span>
        </p>
        <%--<a href="javascript:" class="circlemark circlemark-green">正</a>--%>
    </div>
</div>
<div class="analyItem anItemBor">
    <p class="analyItemTit tx-center">店铺</p>
    <div class="analyItemCon dianpu-click">
        <p class="col-md-4">
            <span class="cLightGray pr8">店铺</span>
            <span class="cGreen">{{TwoShopDatas.StoresType}}</span>
        </p>
        <p class="col-md-4">
            <span class="cLightGray pr8">主营种类</span>
            <span>{{TwoShopDatas.MainCount}}</span>
        </p>
    </div>
</div>
<div class="analyItem anItemBor">
    <p class="analyItemTit tx-center">资料</p>
    <div class="analyItemCon ziliao-click">
        <div class="col-md-8">
            <p class="col-md-12">
                <span class="cLightGray pr8">姓名</span>
                <span>{{TwoShopDatas.UserName}}</span>
            </p>
            <p class="col-md-12">
                <span class="cLightGray pr8">联系方式</span>
                <span>{{TwoShopDatas.UserPhone}}</span>
            </p>
        </div>
        <p class="col-md-4">
            <img :src="TwoShopDatas.UserPhoto" class="head_img" width="80" height="80">
        </p>
    </div>
</div>
<div class="analyItem">
    <p class="analyItemTit tx-center">级别</p>
    <div class="analyItemCon">
        <p class="col-md-4">
            <span class="cLightGray pr8">级别</span>
            <span>{{TwoShopDatas.UserLevel}}</span>
        </p>
        <p class="col-md-4">
            <span class="cLightGray pr8">合作时长</span>
            <span>{{TwoShopDatas.WorkTime}}</span>
        </p>
    </div>
</div>
</div>
<script>
    <%--VUE2.0 开始--%>
    var TwoStatus=new Vue({
        el: '#TwoShop',
        data: {
            TwoShopDatas: {},// 店铺数据
        },
        methods:{
            TwoShopData:function(){
                var _this=this;
                var UserID=$("#hidZXUserID").val();
                $.ajax({
                    url: gcApiSite + 'CMaterial/GetStoresDetialToPersonInfoInSecond',
                    type: 'GET',
                    dataType: 'json',
                    data: {UserID:UserID},
                    success: function (res) {
                        _this.TwoShopDatas = res.Body;
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        },
        created:function(){
            // 页面加载构建数据
            this.TwoShopData();
        }
    })
</script>