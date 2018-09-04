<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<html>
<head>
    <title>编辑专项产品</title>
    <!--页面样式-->
    <link href="${ctxStatic}/js/layui/css/layui.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/js/layui/css/layui.mobile.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-filialeSupervisor.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/rxui1.0.1.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-threecolumn.css?${verStatic}" rel="stylesheet" />
    <link href="${ctxStatic}/css/frame.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-library.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-childlibrary.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-pbank.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-itemed.css?${verStatic}" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/css/fm.tagator.jquery.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-material.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/temporary.css?${verStatic}" rel="stylesheet"/>
    <script src="${basePath}/static/mms/js/jquery-1.11.3.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/Scripts/My97DatePicker/WdatePicker.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/layer.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/vue.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/jquery.qrcode.min.js?${verStatic}"></script>

    <%--专项相关JS--%>
    <script src="${ctxStatic}/js/special.js?${verStatic}"></script>

    <%--kindeditor-4.1.10富文本编辑器--%>
    <script charset="utf-8" src="${ctxStatic}/js/kindeditor-4.1.10/kindeditor-all.js?${verStatic}"></script>
    <script charset="utf-8" src="${ctxStatic}/js/kindeditor-4.1.10/lang/zh_CN.js?${verStatic}"></script>

    <%--页面封装JS相关--%>
    <script src="${ctxStatic}/js/special/open/product/add.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/special/open/product/edit.js?${verStatic}"></script>

    <%--系统全局常量:basePath 系统路径,ctxStatic 静态资源路径,gcApiSite 工程接口服务站点路径(注:以'/'收尾),picUploadUrl 公司图片服务器请求接口--%>
    <script type="text/javascript">const basePath = '${basePath}',ctxStatic='${ctxStatic}',gcApiSite = '${gcApiSite}',picUploadUrl = '${picUploadUrl}'</script>

    <%--URL携带参数相关(VUE实例化时赋值):zxUserID 专项用户ID(材料商ID),zxUserCityID 专项用户地区ID(材料商城市ID),treeTwoID 二级科目ID,wpsUserID WPS登录用户ID(用于记录操作记录)--%>
    <script type="text/javascript">var zxUserID = 0,zxUserCityID = 0,wpsUserID = '',proID = ''</script>

    <style>
        body, html {  overflow: auto;  }

        .main {
            width: 950px;
            margin: 0 auto;
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 60px;
        }
        .head{height:40px;line-height: 40px;text-align: center;background: #24a2f1;
            color: #fff;font-size: 14px;letter-spacing: 3px;}
        .minwidth100 {
            min-width: 100px;
        }

        .ml100 {
            margin-left: 100px;
            overflow: hidden;
        }

        .bgf8f8f8 {
            background: #f8f8f8;
        }

        .commit {
            position: fixed;
            z-index: 997;
            bottom:0;
            height: 60px;
            background-color: #f6f6f6;
            border-top: 1px solid #bbb;
            width: 100%;
            left: 0;
            text-align: center;
            padding: 15px 0;

        }
        input[type=text].EidtPriceInp{ border:none;}


        .uploadSafe{height:110px;width:120px;padding:10px;padding-top: 36px;}
        .inspectionPicCon{height:auto;}
        .inspectionPicUl li,.inspectionPicUl .li{width:120px;height:110px;}

    </style>
</head>
<body>
<div id="editZXProductVue" v-cloak>
    <div class="head">产品更新</div>
    <div class="main">

        <%--产品名称--%>
        <div class="mb10 clearfix">
            <span class="cLightGray pr8 minwidth100 lh28 tx-right fl">产品名称</span>
            <div class="ml100">
                <input type="text" id="txtProductName" class="width120" style="width: 80%;">
            </div>
        </div>

        <%--单位--%>
        <div class="mb10 clearfix">
            <span class="cLightGray pr8 minwidth100 lh28 tx-right fl">单位选择</span>
            <div class="ml100">
                <select id="sltProductUnit" style="padding:0 8px;">
                    <option value="0">请选择</option>
                    <option v-for="matUnit in matUnitArr" :value="matUnit.unitID">{{matUnit.unitName}}</option>
                </select>
            </div>
        </div>

        <%--产品主图--%>
        <div class="mb10 clearfix boxProMainPic">
            <span class="cLightGray pr8 minwidth100 lh28 tx-right fl">产品主图</span>
            <div class="ml100 inspectionPicUl">
                <%--图片上传按钮--%>
                <div class="layui-upload-drag fl uploadSafe mr5">
                    <i class="layui-icon" ></i>
                    <p>点击上传</p>
                </div>
                <%--上传文件存放处--%>
                <div v-for="(picture,index) in pictures" class="fl uiImgUpload-finished li" :data-sdpid="picture.sdpId">
                    <img :src="picture.spdUrl" class="safetyImg">
                    <div class="uiImgUpload-mark" style="display: block;">
                        <div class="uiImgUpload-mark-bg"></div>
                        <div class="uiImgUpload-mark-link">
                            <div class="dis-il-block clearfix">
                                <a href="jvascript:" class="upagain_link fl mr5"><input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="fileone"></a>
                                <a href="javascript:" class="enlarge_link enlarge_link_more fl mr5" data-index="0" :data-src="picture.spdUrl" data-imgname="" data-msg="true"></a>
                                <a href="javascript:" class="delect_link fl"></a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="div_contentlist">
            <%--主营属性类别遍历--%>
            <div v-for="(category,index1) in dftCategorys" class="mb10 clearfix" >

                <%--类别名称--%>
                <ul class="cLightGray pr8 minwidth100 lh28 tx-right fl Father_Title" :data-mctid="category.mctId"  ><li>{{category.mctName}}</li></ul>

                <ul :class="'ml100 bgf8f8f8 Father_Item Father_Item' + index1" :data-mctid="category.mctId" >
                    <%--类别项遍历开始--%>
                    <li v-for="(categoryItem,index2) in category.categoryItem"  class="col-md-3 pt5 pb5">

                        <%--类别项名称--%>
                        <label class="uiRadio12 ml10">
                            <input :id="'Checkbox' + index2" :value="categoryItem.mctiName" :data-mctiid="categoryItem.mctiId" :data-mctitype="categoryItem.mctiType"  type="checkbox" class="uiRadio12-input">
                            {{categoryItem.mctiName}}
                            <%--针对材料商自定义项提供删除接口--%>
                            <a v-show="categoryItem.mctiType == 1" href="javascript:" class="deleteDIYCategoryItem">×</a>
                        </label>

                    </li>

                    <%--材料商专属项--%>
                    <li class="MainCampList">
                        <div class="col-md-3 pt5 pb5">
                            <label class="uiRadio12 ml10"><input :id="'Checkbox' + category.categoryItem.length" data-mctiid="" data-mctitype="1" type="checkbox" class="uiRadio12-input InpWriteRadio"></label><input :placeholder="'自定义'+ category.mctName" type="text"  class="width140 InpWrite txtDIYCategoryItem">
                        </div>
                    </li>
                </ul>

            </div>
        </div>

        <div class="mb10">
            <span class="cLightGray pr8 minwidth100 lh28 tx-right fl">&nbsp;</span>
            <div class="ml100 lh28">
                <span class="cRed pl10"></span>
            </div>
        </div>
        <%--组合项表格--%>
        <div class="mb10">
            <span class="cLightGray pr8 minwidth100 lh28 tx-right fl">组合套餐</span>
            <div id="createTable" class="ml100 lh28">
                暂无组合套餐
            </div>
        </div>
        <div class="mb10 clearfix">
            <span class="cLightGray pr8 minwidth100 lh28 tx-right fl">产品详细</span>
            <div class="ml100">
                <div class="boxKindEditor">
                    <textarea rows="20" cols="115" id="txtProductdescAdd" name="description1"></textarea>
                </div>
            </div>
        </div>

    </div>
    <div class="commit tx-center">
        <input type="button" value="更新" id="btnSubmitProduct" class="uiBtn-normal uiBtn-blue ">
        <input type="button" value="删除" id="btnDelProduct" class="uiBtn-normal uiBtn-red ">
        <input type="button" v-show="product.spStatus == 2" value="上架" id="btnUpProduct" class=" uiBtn-normal uiBtn-green ">
        <input type="button" v-show="product.spStatus == 0" value="下架" id="btnDownProduct" class=" uiBtn-normal uiBtn-orange ">
        <input type="button" value="退出" id="btnCloseProduct" style="background: #7b8b71;color: #fff;" class="uiBtn-normal uiBtn-gray">
    </div>
</div>

<!--隐藏域-->
<input type="hidden" id="hidDIYCategoryItem" value=""/> <%--自定义类比项内容--%>
</body>

<%--图片上传相关JS--%>
<script src="${ctxStatic}/Scripts/fileupload/jquery.ui.widget.js?${verStatic}"></script>
<script src="${ctxStatic}/Scripts/fileupload/load-image.all.min.js?${verStatic}"></script>
<script src="${ctxStatic}/Scripts/fileupload/jquery.iframe-transport.js?${verStatic}"></script>
<script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload.js?${verStatic}"></script>
<script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-ui.js?${verStatic}"></script>
<script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-process.js?${verStatic}"></script>
<script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-image.js?${verStatic}"></script>
<script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-video.js?${verStatic}"></script>
<script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-audio.js?${verStatic}"></script>
<script src="${ctxStatic}/Scripts/fileupload/jquery.fileupload-validate.js?${verStatic}"></script>
<script src="${ctxStatic}/js/jquery.rotate.min.js?${verStatic}"></script>
<script src="${ctxStatic}/js/New_jquery.mousewheel.js?${verStatic}"></script>
<script src="${ctxStatic}/js/bindCombox.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
<script src="${ctxStatic}/js/fm.tagator.jquery.js?${verStatic}" type="text/javascript" charset="utf-8"></script>

<%--公司脚本--%>
<script src="${ctxStatic}/js/rxuedv2.0.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>

<%--多图上传组件--%>
<script src="${ctxStatic}/js/layui/layui.js?${verStatic}"></script>

<%--页面相关脚本--%>
<script src="${ctxStatic}/js/special/open/product/liandong.js?${verStatic}"></script>

<%--VUE2.0 开始--%>
<script>
    // 图片上传索引
    var picDataIndex = 0 ;

    // kindEditor富文本实例
    var kindEditor = null;

    // 添加专项产品VUE实例
    var editZXProductVue = new Vue({
        el: '#editZXProductVue',
        data: {
            matUnitArr: [], // 材料单位集
            product:{}, // 材料信息
            pictures:[], // 主图信息
            categorys: [], // 类别项集
            dftCategorys: [], // 默认回显类别项集
            groups:[] // 组合套餐集
        },
        created:function() {
            this.buildKindEditorAndPicUpload(); // 构建 KindEditor 与 页面其他组件
            this.buildUnitsData(); // 构建材料单位集
            this.buildUpdateEchoData(); // 构建更新回显数据
        },
        methods: {
            // 构建更新回显数据
            buildUpdateEchoData: function(){

                zxUserID = getUrlParms("zxUserID");
                zxUserCityID = getUrlParms("zxUserCityID");
                wpsUserID = getUrlParms("wpsUserID");
                proID = getUrlParms("proID");

                var _this = this;
                $.ajax({
                    url: basePath + '/special-api/open/product/edit',
                    type: 'GET',
                    dataType: 'json',
                    data: {proID},
                    success: function (res) {
                        _this.product = res.body.product;
                        _this.pictures = res.body.pictures;
                        _this.categorys = res.body.categorys;
                        _this.dftCategorys = res.body.dftCategorys;
                        _this.groups = res.body.groups;

                        // 基础数据回显
                        setTimeout(function(){
                            // 名称
                            $("#txtProductName").val(_this.product.spProName);
                            // 单位
                            $("#sltProductUnit").val(_this.product.spProUnitID);
                            // 专项产品主图
                            $(".uiImgUpload-mark").hide();

                            // 组合套餐 - 根据ItemID派发点击事件
                            $.each( _this.categorys, function(index,category)
                            {
                                $.each( category.categoryItem, function(index,item)
                                {
                                    $("input[data-mctiid="+item.mctiId+"]").click();
                                });
                            });

                            // 组合套餐价格与是否主推
                            $.each( _this.groups, function(index,item)
                            {
                                $("tr[data-code="+item.mcgCode+"]").find(".txtMCGPrice").val(item.mcgPrice);
                                if (item.mcgPushtype == 1) {
                                    $("tr[data-code="+item.mcgCode+"]").find(".tadMCGPushType").click();
                                }

                            });


                            // 产品详细
                            kindEditor.html(_this.product.spProDepictData);
                        })
                    },
                    error: function (err) {alert('操作出错！');}
                });
            },
            // KindEditor | 其他
            buildKindEditorAndPicUpload: function () {
                setTimeout(function () {
                    layui.use('upload', function () {
                        var upload = layui.upload; //得到 upload 对象
                        upload.render({
                            elem: '.uploadSafe',
                            url: picUploadUrl,
                            multiple: true,
                            accept: 'images',
                            before: function (obj) {
                                //预读本地文件示例，不支持ie8
                                obj.preview(function (index, file, result) {});
                            },
                            done: function (res, index, upload) {

                                //上传完毕  res.Url res.BaseUrl res.FileName
                                if (res.State == "SUCCESS") {
                                    var photoUrl = res.BaseUrl + res.FileName; // 上传图片回调的照片路径
                                    var item = this.item;
                                    layer.msg("上传成功,可能回显过慢,请不必担心!", {icon: 1});
                                    $(".inspectionPicUl").append(
                                        '<div class="fl uiImgUpload-finished li"  data-sdpid="">'+
                                        '<img src="'+photoUrl+'" class="safetyImg">'+
                                        '<div class="uiImgUpload-mark">'+
                                        '<div class="uiImgUpload-mark-bg"></div>'+
                                        '<div class="uiImgUpload-mark-link">'+
                                        ' <div class="dis-il-block clearfix">'+
                                        '<a href="jvascript:" class="upagain_link fl mr5"><input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="fileone"></a>'+
                                        '<a href="javascript:" class="enlarge_link enlarge_link_more fl mr5" data-index="'+picDataIndex+'" data-src="'+photoUrl+'" data-imgname="" data-msg="true"></a>'+
                                        '<a href="javascript:" class="delect_link fl"></a>'+
                                        '</div>'+
                                        '</div>'+
                                        '</div>'+
                                        '</div>'
                                    );
                                    // 绑定上传事件到元素身上
                                    loadingZXProPicUpload();
                                } else alert("图片服务器异常 请联系系统管理员");
                                picDataIndex ++;
                            },
                            error: function () {console.log("上传专项产品主图图片error");}
                        });
                    });

                    // 富文本编辑器
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
                    kindEditor = KindEditor.create('#txtProductdescAdd',kingEditorParams);
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
            }
        }
    });
</script>
</html>
