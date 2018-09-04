<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/browsing/page_detail.css?${verStatic}"/>

<style>
    .mall_top_box .mall_slide_box{
        height:auto;border:none;}
</style>
<div class="clearfix layerRtb-head pb10 mr10">
    <p class="fl"></p>
    <i class="fr rig_close">×</i>
</div>
<div id="sProThreeItemVue" v-cloak >
    <div class="plr10 sProThreeItemScroll thinScroll">
        <%--产品详细--%>
        <div class="mall_top_box">
            <div class="clearfix">
                <div class="slide_img_info relative" style="margin-left:0;">
                    <img src="${ctxStatic}/images/pic/edit_img.png" class="img_edit_Btn zxPro fr ml5" style="cursor:pointer;position: absolute;right:5px;top:0;" width="20" height="20">
                    <%--产品名称--%>
                    <p class="f3 fz24 mb14 lh28"><span>{{product.spProName}}</span></p>
                    <div class="classification clearfix">

                        <%--当前套餐名称--%>
                        <p class="lh32 fz14 mb10">价格<strong class="fz18 cce2222" >￥<span id="spanNowSetMealPrice">0</span></strong></p>
                    </div>

                    <%--专项套餐开始--%>
                    <div class="brand_lab mt50 pb6">
                        <div class="dt">选择套餐</div>
                        <div class="dd clearfix chosesetmeal">
                            <%--套餐遍历开始--%>
                            <div v-for="(setMeal,index) in setMeals" :class="index == 0 ? 'SetMealItem selected' : 'SetMealItem'" :data-smname="setMeal.ssmMealName" :data-smprice="setMeal.ssmMealPrice" :data-setmealr="index + 1">
                                <a href="javascript:">
                                    <img :src="setMeal.ssmMealPhotoURL == null ?  '${ctxStatic}/images/pic/zan_nopic.png' : setMeal.ssmMealPhotoURL" :alt="setMeal.ssmMealName" width="40" height="40" >
                                    <i>{{setMeal.ssmMealName}}</i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="brand_lab pb6">
                        <div class="dt">系列</div>
                        <div class="dd clearfix">
                            <ul class="brand_lab_ul">
                                <li class="current"><a href="javascript:">60<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">60A<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">65A<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">70A<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">75A<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="brand_lab pb6">
                        <div class="dt">开启方式</div>
                        <div class="dd clearfix">
                            <ul class="brand_lab_ul">
                                <li class="current"><a href="javascript:">固定<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">平开<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">平开上悬<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">外开<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">外下悬<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">单开<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">双开<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">内开<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="brand_lab pb6">
                        <div class="dt">玻璃</div>
                        <div class="dd clearfix">
                            <ul class="brand_lab_ul">
                                <li class="current"><a href="javascript:">双白普通<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">双钢化<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">钢化+LoW+E钢化<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="brand_lab pb6">
                        <div class="dt">五金</div>
                        <div class="dd clearfix">
                            <ul class="brand_lab_ul">
                                <li class="current"><a href="javascript:">无<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">香港坚朗<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                                <li><a href="javascript:">德国丝古利亚<i class="brand_lab_ico brand_lab_arrow"></i></a></li>
                            </ul>
                        </div>
                    </div>

                    <%--二维码 开始--%>
                    <span class="erwm">
                                    <img src="${ctxStatic}/images/browsing/erwm.jpg?${verStatic}">

                                </span>
                </div>
                <div class="mall_slide_box posRa PictureBoxParent">
                    <%--大图框--%>
                    <div class="mall_slide_box_con">
                        <ul class="clearfix mall_slide_box_ul">
                            <li style="position: absolute; left: 0px; top: 0px;"><img class="db_100 show_img" src="http://img9.rxjy.com:9120/image/0DC3wKgBtFrMgFCASfmkAABXfd1Oq0M604.jpg" alt=""></li>
                        </ul>
                    </div>
                    <div class="spec-list">
                        <a id="spec-forward" href="javascript:;" class="arrow-prev"><i class="sprite-arrow-prev"></i></a>
                        <a id="spec-backward" href="javascript:;" class="arrow-next"><i class="sprite-arrow-next"></i></a>
                        <div class="spec-items">
                            <ul class="spec-items-ul clearfix">
                                <%--小图集遍历--%>
                                <li>
                                    <img width="50px" height="50px" src="http://img9.rxjy.com:9120/image/0DC3wKgBtFrMgFCASfmkAABXfd1Oq0M604.jpg" >
                                </li>
                                    <li>
                                        <img width="50px" height="50px" src="http://img9.rxjy.com:9120/image/0DC3wKgBtFrMgFCASfmkAABXfd1Oq0M604.jpg" >
                                    </li>
                                    <li>
                                        <img width="50px" height="50px" src="http://img9.rxjy.com:9120/image/0DC3wKgBtFrMgFCASfmkAABXfd1Oq0M604.jpg" >
                                    </li>
                                    <li>
                                        <img width="50px" height="50px" src="http://img9.rxjy.com:9120/image/0DC3wKgBtFrMgFCASfmkAABXfd1Oq0M604.jpg" >
                                    </li>
                                    <li>
                                        <img width="50px" height="50px" src="http://img9.rxjy.com:9120/image/0DC3wKgBtFrMgFCASfmkAABXfd1Oq0M604.jpg" >
                                    </li>
                                    <li>
                                        <img width="50px" height="50px" src="http://img9.rxjy.com:9120/image/0DC3wKgBtFrMgFCASfmkAABXfd1Oq0M604.jpg" >
                                    </li>
                                    <li>
                                        <img width="50px" height="50px" src="http://img9.rxjy.com:9120/image/0DC3wKgBtFrMgFCASfmkAABXfd1Oq0M604.jpg" >
                                    </li>
                                    <li>
                                        <img width="50px" height="50px" src="https://img.alicdn.com/imgextra/i1/1714128138/TB29OnyEYSYBuNjSspfXXcZCpXa_!!1714128138.jpg_60x60q90.jpg" >
                                    </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>





</div>
<script src="${basePath}/static/mms/js/special/open/product/browsing.js?${verStatic}" type="text/javascript" charset="utf-8"></script>

<%--VUE2.0 开始--%>
<script>
    //计算滚动条
    function countScroll(){
        $(".sProThreeItemScroll").each(function(){
            var endH=$(this).closest(".layerRtb").height()-$(this).closest(".layerRtb").find(".layerRtb-head").outerHeight(true)-10;
            $(this).height(endH);
        })
    }
    $(function(){
        countScroll()
    })
    var sProThreeItemVue = new Vue({
        el: '#sProThreeItemVue',
        data: {
            matUnitArr: [] ,// 材料单位集

            product: {}, // 产品
            matRelevance: {}, // 关联材料
            setMeals: [], // 套餐集

            matNameItems: [], // 材料名称下拉选(关联材料键弹起事件)
            layerMatLists: [] // 材料列表
        },
        created:function() {
            // VUE 实例创建加载数据
//            this.buildKindEditorAndPicUpload(); // 构建 KindEditor
//            this.buildUnitsData(); // 材料单位集
//            this.buildProThreeItemData(); // 产品三段项
            this.$nextTick(function(){
                setTimeout(function(){
                    // 小图滚动
                    PictureScroll();
                })

            })

        },
        mounted:function(){

        },
        methods: {
            // KindEditor
            buildKindEditorAndPicUpload: function () {
                setTimeout(function () {
                    // 加载图片上传
                    uploadSetMealPic();
                    var kingEditorParams = {
                        //指定上传文件参数名称
                        filePostName  : "uploadFile",
                        //指定上传文件请求的url。
                        uploadJson : basePath + '/mms_upload-web/pics',
                        //上传类型，分别为image、flash、media、file
                        dir : "image",
                      width : '100%',
                      height : '400px'
                    };
                    var newVar = KindEditor.create('#txtProductdesc',kingEditorParams);
                },200);
            },
            // 展示材料关联layer四段弹出材料列表
            showMatRlevancePOPMatList: function (treeThreeID,treeTreeName) {

                // 1.隐藏关联材料下拉选
                $(".searchMatterial").hide();

                // 2.设置专项三级科目ID到隐藏域,以供加载专项产品关联材料列表
                $("#hidZXTreeThreeID").val(treeThreeID);
                $("#txtMatNameToSplitTemp").val(treeTreeName); // 设置回显关联材料框架检索默认值


                // 3.加载 专项产品关联材料列表到材料关联layer四段弹出中
                $(".divTempSplitSearchMaterial").load(basePath+"/public-web/special/four-product-mat_list",function () {

                    // 4.打开layer四段弹出材料列表
                    var index = layer.open({
                        type: 1,
                        title: "选择材料",
                        area: ['90%', '90%'],
                        content: $(".divTempSplitSearchMaterial"),
                        btn: ["关闭"],
                    });
                    $("#hidZXLayerIndex").val(index); // 记录layer索引以供关闭
                });
            },
            // 材料名称集与展示下拉选
            buildMatNameItemsAndShowSelectData: function () {
                var _this = this;
                var UserID = $("#hidZXUserID").val();
                var city = $("#hidZXUserCityID").val();
                var search = $("#txtMatNameToSplitTemp").val();
                $.ajax({
                    url: gcApiSite + 'CMaterial/GetMatNameItems',
                    type: 'GET',
                    dataType: 'json',
                    data: {UserID,city,search},
                    success: function (res) {
                        _this.matNameItems = res.Body;

                        setTimeout(function () {
                            $(".searchMatterial").show();
                        })
                    },
                    error: function (err) {alert('操作出错！');}
                });
            },
            // 材料单位集
            buildUnitsData: function () {
                var _this = this;
                $.ajax({
                    url: basePath + '/common-api/units',
                    type: 'GET',
                    dataType: 'json',
                    success: function (res) {
                        _this.matUnitArr = res.body;
                    },
                    error: function (err) {alert("操作出错！");}
                });
            },
            // 构建产品三段项数据
            buildProThreeItemData: function () {
                var _this = this;
                var spID=$("#hidZXSProID").val(); // 专项产品ID
                $.ajax({
                    url: basePath + '/special-api/three/product/items',
                    type: 'GET',
                    dataType: 'json',
                    data: {spID},
                    success: function (res) {
                        _this.product = res.body.product;
                        _this.matRelevance = res.body.matRelevance;
                        _this.setMeals = res.body.setMeals;

                        // 设置 编辑回显
                        var spProDepictData = res.body.product.spProDepictData;
                        if (spProDepictData != null && spProDepictData != ""){
                            $(".boxSpProDepictData").css("width",$(".layerRtb").width()-62).html( _this.product.spProDepictData); // 专项详情数据
                            $("#txtProductdesc").html(_this.product.spProDepictData);
                            KindEditor.sync('#txtProductdesc');
                        }

                        $("#sltProductUnit").val( _this.product.spProUnitID); // 专项产品单位
                        $("#hidZXProRelevanceID").val(_this.matRelevance.smrID); // 设置 专项产品关联ID
                        $("#hidZXProRelevanceMatID").val(_this.matRelevance.smrMatID); // 设置 专项关联材料ID到隐藏域



                    },
                    error: function (err) {alert("操作出错！");}
                });
            }
        }
    });
</script>