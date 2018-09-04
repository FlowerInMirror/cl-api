<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<style>
    .boxShopDepictData img{max-width:100%;}
    .boxShopKindEditor textarea{max-width:100%;}
</style>
<div id="ThreeShop" v-cloak>
<div class="clearfix layerRtb-head pb10 mr10">
    <p class="fl"></p>
    <i class="fr rig_close">×</i>
</div>
<div class="layerRtb-scroll thinScroll overflow plr10">
    <div class="analyItem">
        <p class="analyItemTit tx-center">店铺信息</p>
        <div class="analyItemCon">
            <p class="col-md-4"><span class="cLightGray pr8">店铺类型</span><span class="cGreen">{{ThreeShopDatas.StoresType}}</span>
            </p>
            <p class="col-md-4"><span class="cLightGray pr8">开店时长</span><span>{{ThreeShopDatas.WorkTime}}</span></p>
            <p class="col-md-4 fl"><span class="cLightGray pr8">业务经理</span><span>{{ThreeShopDatas.UserName}}</span></p>
            <p class="col-md-4"><span class="cLightGray pr8">联系电话</span><span>{{ThreeShopDatas.UserPhone}}</span></p>
            <div class="col-md-4 fl">
                <div class="star_scorePs1 clearfix pt5">
                    <span class="cLightGray pr8 fl">星级</span>
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
                    {{ThreeShopDatas.UserLevel}}
                </div>
            </div>
        </div>
    </div>
    <div class="analyItem">
        <p class="analyItemTit tx-center">固定材料</p>
        <div class="analyItemCon">
            <p class="col-md-8"><span class="cLightGray pr8">主营</span><span>{{ThreeShopDatas.MainName}}</span></p>
            <p class="col-md-4"><span class="cLightGray pr8">材料</span><span>{{ThreeShopDatas.MatCount}}</span></p>
        </div>
    </div>

    <div class="analyItem">
        <p class="analyItemTit tx-center">店铺简介</p>
        <div class="analyItemCon">
            <%--编辑按钮--%>
            <img src="${ctxStatic}/images/pic/edit_img.png" class="img_edit_Btn btnEditZXShop fr ml5" style="cursor:pointer;" width="20" height="20">

            <%--回显: 店铺详情数据盒子--%>
            <div class="boxShopDepictData" >无</div>

            <%--编辑: 富文本编辑器盒子--%>
            <div class="boxShopKindEditor" >
                <textarea rows="15" cols="110" id="txtShopDesc" name="description1"></textarea>
                <div class="tx-center pt10 ">
                    <input type="button" value="提交" class="uiBtn-blue uiBtn-normal btnSubmitZXShop">
                    <input type="button" value="返回" class="uiBtn-blue uiBtn-normal btnBackZXShop">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="layerRtb-bottom borT-scroll plr10 pt10 pb5">
    <div class="analyItem">
        <p class="analyItemTit tx-center">综评</p>
        <div class="analyItemCon"></div>
    </div>
</div>
</div>
<script>
    var kindEditor = null; // KindEditor富文本编辑器全局变量

    <%--VUE2.0 开始--%>
    var TwoStatus=new Vue({
        el: '#ThreeShop',
        data: {
            ThreeShopDatas: {},// 店铺数据
        },
        created:function(){
            // 页面加载构建数据
            this.ThreeShopData();
            this.buildKindEditorAndHide();
        },
        methods:{
            // 构建 kindEiditor 富文本编辑器 与 隐藏编辑层
            buildKindEditorAndHide:function(){
                setTimeout(function () {
                    var kingEditorParams = {
                        // 指定上传文件参数名称
                        filePostName  : "uploadFile",
                        // 指定上传文件请求的url。
                        uploadJson : basePath + '/mms_upload-web/pics',
                        // 上传类型，分别为image、flash、media、file
                        dir : "image",
                        width : '100%',
//                        height : '400px'

                    };
                     kindEditor = KindEditor.create('#txtShopDesc',kingEditorParams);

                    // 隐藏编辑层
                    $(".btnBackZXShop").hide();
                    $(".boxShopKindEditor").hide();
                });
            },
            // 店铺三段数据
            ThreeShopData:function(){
                var _this=this;
                var UserID=$("#hidZXUserID").val();
                $.ajax({
                    url: gcApiSite + 'CMaterial/GetStoresDetialToPersonInfoToStoresInThird',
                    type: 'GET',
                    dataType: 'json',
                    data: {UserID:UserID},
                    success: function (res) {
                        _this.ThreeShopDatas = res.Body;

                        var storesIntroduction = res.Body.StoresIntroduction; // 店铺简介内容

                        if (storesIntroduction != null && storesIntroduction != ""){
                            // 设置店铺介绍 数据回显
                            $(".boxShopDepictData").css("width",$(".layerRtb").width()-62).html(storesIntroduction);
                            // 设置店铺介绍 富文本编辑回显
                            kindEditor.html(storesIntroduction);
                        }
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        }

    });
</script>