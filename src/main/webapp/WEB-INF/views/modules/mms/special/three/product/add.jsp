<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<%--添加产品出口--%>
<div class="clearfix layerRtb-head pb10 mr10">
    <p class="fl"></p>
    <i class="fr rig_close addZxPro">×</i>
</div>

<%--标题--%>
<div class="divMaterialLoadAlert_topm pa9 ml10">
    <h2 class="uiTitle2" style="margin-top: -17px;">
        <i class="uiTitle-icon"></i>
        添加产品
    </h2>
</div>

<%--添加内容开始--%>
<div id="sProThreeAddVue">
    <div class="plr10 j_chas">
        <div class="analyItem ">
            <p class="analyItemTit tx-center">产品</p>
            <div class="analyItemCon">

                <p class=" mb5">
                    <span class="cLightGray pr8">产品名称</span>
                    <input type="text" class="width120" id="txtProductName" style="width:80%">
                </p>

                <p class="fl col-md-4">
                    <span class="cLightGray pr8">单位选择</span>
                    <select id="sltProductUnit">
                        <option value="0">请选择</option>
                        <option v-for="matUnit in matUnitArr" :value="matUnit.unitID">{{matUnit.unitName}}</option>
                    </select>
                </p>

            </div>
        </div>
    </div>
    <div class="layerRtb-scroll thinScroll thinScrollFix overflow plr10">
        <div class="analyItem" style="position: relative;z-index:1000;">
            <p class="analyItemTit tx-center">关联材料</p>
            <div class="analyItemCon">

                <input type="text" id="txtMatNameToSplitTemp"   @focusin="buildMatNameItemsAndShowSelectData()" @keyup="buildMatNameItemsAndShowSelectData()" autocomplete="off" class="fl" style="width: 88.5%;" placeholder="输入材料名称">

                <%--关联材料下拉选--%>
                <div class="txtHide hide searchMatterial drop_down">
                    <a v-for="matNameItem in matNameItems" :data-treethreeid="matNameItem.TreeID"  @click="showMatRlevancePOPMatList(matNameItem.TreeID,matNameItem.TreeName)" href="javascript:"  >{{matNameItem.TreeName}}</a>
                </div>

                <div class="clearfix">
                    <p class="col-md-4"><span class="fl pr8">材料名称</span><span class="addProRelevanceMatName" >-</span></p>
                    <p class="col-md-4"><span class="fl pr8">材料规格</span><span class="addProRelevanceMatSpec" >-</span></p>
                </div>
            </div>
        </div>
        <div class="pinpai_analyItem_list">
            <div class="analyItem pinpai_analyItem boxSpecialProSetMeal" data-ssmid="" >
                <p class="analyItemTit tx-center">套餐1</p>
                <div class="analyItemCon relative">
                    <div class="SetMeal clearfix">
                        <%--套餐图片--%>
                        <div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth130 relative boxSetMealPic">
                            <%--上传大按钮--%>
                            <a href="javascript:" class="uiImgUpload-first">
                                <input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" value="" class="file setmealpicfile">
                                <em class="bgIcon file-ico"></em>
                            </a>

                            <%--图片存放处--%>
                            <img src="" />

                            <%--回显支持--%>
                            <div class="uiImgUpload-mark">
                                <div class="uiImgUpload-mark-bg"></div>
                                <div class="uiImgUpload-mark-link">
                                    <div class="dis_il_block">
                                        <%--更新小按钮--%>
                                        <a href="jvascript:" class="upagain_link"><input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="file setmealpicfile"></a>
                                        <%--放大小按钮--%>
                                        <a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="SetMealText">
                            <%--套餐名称--%>
                            <p class="clearfix mb10">
                                <span class="cLightGray pr8 fl">套餐名称</span>
                                <input type="text" class="width130 txtSetMealName"  placeholder="套餐1 名称" style="width: 77%" >
                            </p>
                             <%--价格--%>
                            <p class="clearfix mb10">
                                <span class="cLightGray pr8 fl">套餐价格</span>
                                <input type="text" class="width130 txtSetMealPrice" placeholder="套餐1 价格"  >
                            </p>
                            <%--主推--%>
                            <p class="clearfix">
                                <span class="cLightGray pr8 fl">是否主推</span>
                                <select class="fl sltPushType">
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                            </p>

                            <div class="fr productFix product-edit productFixEdit">
                                <img src="${ctxStatic}/images/pic/add_img.png" width="20" height="20" title="添加套餐" class="fl addSetmealBtn addZXPro mr5" style="cursor:pointer;">
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <%--产品详情 富文本编辑器--%>
        <div class="analyItem">
            <p class="analyItemTit tx-center">产品详细</p>
            <div class="analyItemCon">
                <div class="boxKindEditor ">
                    <textarea rows="20" cols="115" id="txtProductdescAdd" name="description1"></textarea>
                </div>
            </div>
        </div>

    </div>

    <%--添加按钮--%>
    <div class="layerRtb-bottom borT-scroll plr10 pt10 pb5">
        <div class="analyItem">
            <div class="analyItemCon tx-center">
                <input type="button" value="添加产品" id="btnAddProduct" class="uiBtn-small uiBtn-blue addAllProduct">
            </div>
        </div>
    </div>

</div>

<%--VUE2.0 开始--%>
<script>
    var sProThreeAddVue = new Vue({
        el: '#sProThreeAddVue',
        data: {
            matUnitArr: [] ,// 材料单位集
            matNameItems: [], // 材料名称下拉选(关联材料键弹起事件)
            layerMatLists: [] // 材料列表
        },
        created:function() {
            this.buildKindEditorAndPicUpload(); // 构建 KindEditor
            this.buildUnitsData(); // 构建材料单位集
            //添加套餐，添加按钮固定
            this.$nextTick(function(){
                $(".thinScrollFix").scroll(function(){
                    var oheight=$(".SetMeal").offset().top+130;
                    var oscrollTop=$(this).scrollTop();
                    if(oscrollTop>oheight)
                    {
                        $(".productFixEdit").css({"position":"fixed","right":"56px","z-index":"100","top":"200px"});
                    }else{
                        $(".productFixEdit").css({"position":"absolute","top":"50%","right":"10px"});
                    }
                })
            })
        },
        methods: {
            // KindEditor
            buildKindEditorAndPicUpload: function () {
                setTimeout(function () {
                    uploadSetMealPic();
                    var kingEditorParams = {
                        //指定上传文件参数名称
                        filePostName  : "uploadFile",
                        //指定上传文件请求的url。
                        uploadJson : basePath + '/mms_upload-web/pics?',
                        //上传类型，分别为image、flash、media、file
                        dir : "image",
                        width : '100%',
                        height : '400px'
                    };
                    var newVar = KindEditor.create('#txtProductdescAdd',kingEditorParams);
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
            }
        },

    });
</script>
