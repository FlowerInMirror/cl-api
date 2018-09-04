<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<div id="addBrandItemVue"  v-cloak >
    <div class="divMaterialLoadAlert_topm pa9 ml10">
        <h2 class="uiTitle2">
            <i class="uiTitle-icon"></i>
            添加品牌（<span id="lblAddMaterialLevelText"></span>）
        </h2>
        <div class="materialpicture materialTopNavConm pr10">
            <div class="SearchCon pt10">
                <!--品牌信息 开始-->
                <div class="analyItem">
                    <p class="analyItemTit tx-center">品牌信息</p>
                    <div class="analyItemCon relative">
                        <p class="info-edit fl col-md-4">
                            <input type="text" class="width130" id="txtBrandName" value="" placeholder="品牌名称">
                        </p>
                        <p class="info-edit fl col-md-8">
                            <input type="text" class="width308" id="txtBrandRemark" value="" placeholder="描述">
                        </p>
                        <p class="lh20 p_tip1 dis-il-block tx-center" style="width:126px;">
                            品牌LOGO
                        </p>
                        <p class="lh20 p_tip2 dis-il-block tx-center" style="width:126px;">
                            产品主图
                        </p>
                        <ul class="brandlogo clearfix">
                            <li data-type="1" data-add="add">
                                <div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth126 relative" id="brandLogo">
                                    <a href="javascript:" class="uiImgUpload-first">
                                        <input type="file" value="" class="file fileone">
                                        <em class="bgIcon file-ico"></em>
                                    </a>
                                    <img src="" />
                                    <!--图片遮罩和放大按钮 开始-->
                                    <div class="uiImgUpload-mark">
                                        <div class="uiImgUpload-mark-bg"></div>
                                        <div class="uiImgUpload-mark-link">
                                            <div class="dis_il_block">
                                                <a href="jvascript:" class="upagain_link"><input type="file" class="fileone"></a>
                                                <a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--图片遮罩和放大按钮 结束-->
                                </div>
                            </li>
                            <li data-type="4" data-add="add">
                                <div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth130 relative" id="ProPicture">
                                    <a href="javascript:">
                                        <input type="file" value="" class="file filemore">
                                        <em class="bgIcon file-ico"></em>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <!--品牌信息 结束-->
                <!--品牌型号 开始-->
                <div class="analyItem divbrandtype">
                    <p class="analyItemTit tx-center">型号信息</p>
                    <div class="analyItemCon relative clearfix">
                        <p class="cRed lh20">图片要求：尺寸180*180px；大小：2M以内；格式：jpg；</p>
                        <ul class="brandmodel_new clearfix mb10">
                            <li data-type="2" data-add="add">
                                <div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth130 relative">
                                    <a href="javascript:" class="uiImgUpload-first">
                                        <input type="file" value="" class="file fileone">
                                        <em class="bgIcon file-ico"></em>
                                    </a>
                                    <img src="" />
                                    <!--图片遮罩和放大按钮 开始-->
                                    <div class="uiImgUpload-mark">
                                        <div class="uiImgUpload-mark-bg"></div>
                                        <div class="uiImgUpload-mark-link">
                                            <div class="dis_il_block">
                                                <a href="jvascript:" class="upagain_link"><input type="file" class="fileone"></a>
                                                <a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--图片遮罩和放大按钮 结束-->
                                </div>
                                <input type="text" class="col-md-12 txtTitle mt5 info-edit" value="" placeholder="型号名称" />
                            </li>
                            <li data-type="2" data-add="add">
                                <div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth130 relative">
                                    <a href="javascript:" class="uiImgUpload-first">
                                        <input type="file" value="" class="file fileone">
                                        <em class="bgIcon file-ico"></em>
                                    </a>
                                    <img src="" />
                                    <!--图片遮罩和放大按钮 开始-->
                                    <div class="uiImgUpload-mark">
                                        <div class="uiImgUpload-mark-bg"></div>
                                        <div class="uiImgUpload-mark-link">
                                            <div class="dis_il_block">
                                                <a href="jvascript:" class="upagain_link"><input type="file" class="fileone"></a>
                                                <a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a>
                                                <a href="javascript:" class="delect_link"></a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--图片遮罩和放大按钮 结束-->
                                </div>
                                <input type="text" class="col-md-12 txtTitle mt5 info-edit" value="" placeholder="型号名称" />
                            </li>
                            <li data-type="2" data-add="add">
                                <div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth130 relative">
                                    <a href="javascript:" class="uiImgUpload-first">
                                        <input type="file" value="" class="file fileone">
                                        <em class="bgIcon file-ico"></em>
                                    </a>
                                    <img src="" />
                                    <!--图片遮罩和放大按钮 开始-->
                                    <div class="uiImgUpload-mark">
                                        <div class="uiImgUpload-mark-bg"></div>
                                        <div class="uiImgUpload-mark-link">
                                            <div class="dis_il_block">
                                                <a href="jvascript:" class="upagain_link"><input type="file" class="fileone"></a>
                                                <a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a>
                                                <a href="javascript:" class="delect_link"></a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--图片遮罩和放大按钮 结束-->
                                </div>
                                <input type="text" class="col-md-12 txtTitle mt5 info-edit" value="" placeholder="型号名称" />
                            </li>
                            <li data-type="2" data-add="add">
                                <div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth130 relative">
                                    <a href="javascript:" class="uiImgUpload-first">
                                        <input type="file" value="" class="file fileone">
                                        <em class="bgIcon file-ico"></em>
                                    </a>
                                    <img src="" />
                                    <!--图片遮罩和放大按钮 开始-->
                                    <div class="uiImgUpload-mark">
                                        <div class="uiImgUpload-mark-bg"></div>
                                        <div class="uiImgUpload-mark-link">
                                            <div class="dis_il_block">
                                                <a href="jvascript:" class="upagain_link"><input type="file" class="fileone"></a>
                                                <a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a>
                                                <a href="javascript:" class="delect_link"></a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--图片遮罩和放大按钮 结束-->
                                </div>
                                <input type="text" class="col-md-12 txtTitle mt5 info-edit" value="" placeholder="型号名称" />
                            </li>
                        </ul>
                        <p class="hide fl col-md-4">
                            <span class="cLightGray pr8 justify_span justify_span_w80">是否推荐首页</span>
                            <select class="width80" id="ddlHomeHost">
                                <option value="1">是</option>
                                <option value="0" selected="selected">否</option>
                            </select>
                        </p>
                        <p class="info-edit  fl col-md-4">
                            <span class="cLightGray pr8 justify_span justify_span_w80">是否主推</span>
                            <select class="width80" id="ddlHost">
                                <option value="1">是</option>
                                <option value="0" selected="selected">否</option>
                            </select>
                        </p>
                        <p class="info-edit  fl col-md-8">
                            <span class="cLightGray pr8 justify_span justify_span_w80">材料类型</span>
                            <select class="width120 nopaddlr" id="ddlMatType">
                                <option value="1" selected="selected">品牌材料</option>
                                <option value="2">瑞祥专供材料</option>
                            </select>
                        </p>
                    </div>
                </div>
                <!--品牌型号 结束-->
                <%--价格 开始--%>
                <div class="analyItem">
                    <p class="analyItemTit tx-center">价格</p>
                    <div class="analyItemCon relative">
                        <p class="info-edit  fl col-md-4 ">
                            <span class="cLightGray pr8 justify_span justify_span_w80">报价</span>
                            <input type="text" class="width80" id="txtQuotesPrice" value="">
                        </p>
                        <p class="info-edit  fl col-md-4 ">
                            <span class="cLightGray pr8 justify_span justify_span_w80">成本</span>
                            <input type="text" class="width80" id="txtCostPrice" value="">
                        </p>
                        <%--成品分类展示--%>
                        <p v-show="matClassify == 1" class=" fl col-md-4">
                            <span class="cLightGray pr8 justify_span justify_span_w80">安装/加工费</span>
                            <input type="text" class="width80" id="txtInstallPrice" value="">
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="divMaterialLoadAlert_bottom mt10">
        <div class="mt10 clearfix ml10">
            <p class="minwidth204 lh20">
                <span class="pr5 cRed">注：</span>
            </p>
        </div>
        <div class="tx-center mt5 clearfix pb5 ">

            <input type="button" value="添加品牌" class="uiBtn-small uiBtn-blue" id="btnAddBrand" />
        </div>
    </div>
</div>

<script>
    $(function () {
        setTimeout(function () {
            // 加载图片上传控件
            initImageUpload("0");
            initImageUploadAddItems("0");

            var levelTitle = "";
            var level = parseInt($("#hidMatLevel").val());
            switch (level) {
                case 1:
                    levelTitle = "A档";
                    break;
                case 2:
                    levelTitle = "B档";
                    break;
                case 4:
                    levelTitle = "C档";
                    break;
                default:
            }

            $("li[data-add=add]").each(function () {
                $(this).attr("data-matlevel", level);
            });

            $("#lblAddMaterialLevelText").html(levelTitle);
        })
    })

    //清空数据
    function ClearStandard() {
    }
</script>

<%--VUE2.0 开始--%>
<script>
    var addBrandItemVue = new Vue({
        el: '#addBrandItemVue',
        data: {
            matClassify : 0 // 材料分类 （1成品，2非成品）  （默认0）
        },
        created() {
            this.buildMatClassify();// 获取材料分类
        },
        methods: {
            // 获取材料分类 action:成品展示 安装/加工费 input
            buildMatClassify: function () {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                $.get( basePath +"/tree_sst-api/get/mat_classify" , {treeFourID},
                    function(data){
                        var statusMsg = data.statusMsg; // 响应状态消息
                        if ( statusMsg == "success"){
                            _this.matClassify = data.body;
                        } else {
                            alert(statusMsg)
                        }
                });
            }
        }
    });
</script>

