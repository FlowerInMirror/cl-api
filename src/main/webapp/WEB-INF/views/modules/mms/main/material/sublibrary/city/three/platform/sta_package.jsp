<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<!--标准 开始-->
<div id="editMatPackSta" v-cloak >
    <div class="divMaterialLoadAlert_topm pa10">
        <h2 class="uiTitle2">
            <i class="uiTitle-icon"></i>
            包装标准
        </h2>
        <div class=" materialTopNavConm">
            <div class="SearchCon pt10">
                <%--添加 包装质量属性开始--%>
                <div :class="matPackSta.packAttrSize == 0 ? 'analyItem divNewStandardItem' : 'analyItem divNewStandardItem hide'">
                    <p class="analyItemTit tx-center">属性{{matPackSta.packAttrSize + 1 | numToStr(matPackSta.packAttrSize + 1) }}</p>
                    <div class="analyItemCon relative" style="width:100%">
                        <p class="info-edit">
                            <span class="cLightGray mr5 justify_span justify_span_w80 fl">
                                <input type="text" class="width80 txtStandardName" placeholder="属性名称" data-input="true" />
                            </span>
                            <span class="mr5 fl"><input type="text" class="width60 txtStandardValue" placeholder="属性值" data-input="true" /></span>
                            <select class="ddlStandardUnit fl mr5">
                                <option selected="" value="0">单位</option>
                                <%--单位遍历开始--%>
                                <option v-for="matUnit in matUnitArr" :value="matUnit.unitID">{{matUnit.unitName}}</option>
                            </select>
                            <span class="hide fl mr5">(合格范围:<span><input type="text" class="width60 ddlStandardMIN" value="" /></span>-<span><input type="text" class="width60 ddlStandardMAX" value="" /></span>)</span>
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class="pr8 fl"> <input type="text" class="width80 fl txtExteriorName" value="" placeholder="检验方法一" data-input="false"></span>
                            <input type="text" class="width360 fl txtStandardPC">
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class="pr8 fl"> <input type="text" class="width80 fl txtExteriorSC" value="" placeholder="检验方法二" data-input="false"></span>
                            <textarea rows="3" class="width360 fl txtStandardDM"></textarea>
                        </p>
                        <div class="dis-il-block imgbox  imgbox2">
                            <img v-show="matPackSta.packAttrSize == 0" class="save_img btnSaveStandardDetect" src="${ctxStatic}/images/pic/add_img.png" />
                            <img v-show="matPackSta.packAttrSize != 0" class="save_img btnSaveStandardDetect" src="${ctxStatic}/images/pic/save_img.png" />
                            <img v-show="matPackSta.packAttrSize != 0" onclick="$(this).closest('div.analyItem').addClass('hide')" src="${ctxStatic}/images/pic/delete_img.png"/>
                        </div>
                    </div>
                </div>
                <%-- 包装质量数据 遍历开始 --%>
                <div  v-for="(packSta, index) in matPackSta.packAttrs"  class="analyItem" :data-tsiid="packSta.tsiId" :zz-unit="packSta.tsiUnit">
                    <p class="analyItemTit tx-center">属性{{index + 1 | numToStr(index + 1)}}</p>
                    <div class="analyItemCon relative">
                        <p class="info-look">
                            <span class="cLightGray pr8 justify_span justify_span_w80">{{packSta.attributeName|convertOverlengthStrings(5)}}</span><span>{{packSta.attributeValue + '' + packSta.attributeUnit}}</span>
                            <span></span>
                        </p>
                        <p class="info-edit hide">
                            <%--属性名--%>
                            <span class="cLightGray pr8 justify_span justify_span_w80"><input type="text" class="width80 txtStandardName" :value="packSta.attributeName" data-input="true" /></span>
                            <%--属性值--%>
                            <span><input type="text" class="width60 txtStandardValue" :value="packSta.attributeValue" data-input="true" /></span>
                            <%--标准单位--%>
                            <select class="ddlStandardUnit mt3f">
                                <option selected="" value="0">单位</option>
                                <%--单位遍历开始--%>
                                <option v-for="matUnit in matUnitArr" :value="matUnit.unitID">{{matUnit.unitName}}</option>
                            </select>
                            <span class="hide">(合格范围:<span><input type="text" class="width60 ddlStandardMIN" :value="packSta.tsiStandardmin" /></span>-<span><input type="text" class="width60 ddlStandardMAX" :value="packSta.tsiStandardmax" /></span>)</span>
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class="cLightGray pr8 fl justify_span justify_span_w80">特殊要求</span>
                            <span>{{packSta.tsiSpecialclaim}}</span>
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class="cLightGray pr8 fl justify_span justify_span_w80">特殊要求</span>
                            <input type="text" class="width360 fl txtStandardPC" :value="packSta.tsiSpecialclaim">
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class="pr8 fl" style="margin-right:16px;"> <input type="text" class="width80 fl txtExteriorName" value="" placeholder="检验方法" data-input="false"></span>
                            <span class="fl" style="width:80%">{{packSta.tsiDetectmethod}}</span>
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class=" fl" style="margin-right:16px;"> <input type="text" class="width80 fl txtExteriorSC" value="" placeholder="检验方法" data-input="false"></span>
                            <textarea rows="3" class="width360 fl txtStandardDM">{{packSta.tsiDetectmethod}}</textarea>
                        </p>
                        <div class="dis-il-block imgbox imgbox1">
                            <img v-show="index == 0" class="img_add" src="${ctxStatic}/images/pic/add_img.png" />
                            <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png" />
                            <img class="img_delete" src="${ctxStatic}/images/pic/delete_img.png" />
                        </div>
                        <div class="dis-il-block imgbox  hide imgbox2">
                            <img class="save_img btnSaveStandardDetect" src="${ctxStatic}/images/pic/save_img.png" />
                            <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png" />
                        </div>

                        <div class="judge-div-sample judge-div-small" >
                            <input type="text" class="fl judge-span judge-yes active">
                            <input type="text" class="fl judge-span judge-no">
                            <strong class="fl score cGreen fz14 pl5">1分</strong>
                        </div>
                    </div>
                </div>

                <div class="analyItem">
                    <p class="analyItemTit tx-center">包装标准</p>
                    <div class="analyItemCon relative">
                        <div class="analyItemConText">
                        <p class="info-look">
                            <span class="cLightGray pr8 justify_span justify_span_w80">包装类别</span>
                            <span v-show="matPackSta.packCategory == 1" id="lblPSType">瑞祥标准</span>
                            <span v-show="matPackSta.packCategory == 2" id="lblPSType">合作商标准</span>
                            <span v-show="matPackSta.packCategory == null" id="lblPSType">--</span>
                        </p>
                        <p class="info-edit hide">
                            <span class="cLightGray fl pr8 justify_span justify_span_w80">包装类别</span>
                            <label class="uiRadio12">
                                <span><input type="radio" value="1" name="pstype" class="uiRadio12-input" /></span>瑞祥标准
                            </label>
                            <label class="uiRadio12">
                                <span><input type="radio" value="2" name="pstype" class="uiRadio12-input" /></span>合作商标准
                            </label>
                        </p>
                        <p class="clearfix mt5 info-look">
                            <span class="cLightGray pr8 fl justify_span justify_span_w80">包装材质</span>
                            <span class="fl" style="width:80%" id="lblPSMaterialQuality">{{matPackSta.packMatQuality}}</span>
                        </p>
                        <p class="clearfix mt5 info-edit hide">
                            <span class="cLightGray pr8 fl justify_span justify_span_w80">包装材质</span>
                            <input class="width320" type="text" placeholder="" id="txtPSMaterialQuality" :value="matPackSta.packMatQuality">
                        </p>
                        <p class="clearfix mt5 info-look">
                            <span class="cLightGray fl pr8 justify_span justify_span_w80">包装说明</span>
                            <span class="fl" style="width:80%" id="lblPSMarkRemark">{{matPackSta.packExplain}}</span>
                        </p>
                        <p class="mt5 info-edit hide">
                            <span class="cLightGray pr8 justify_span justify_span_w80">包装说明</span><textarea rows="3" class="width320" id="txtPSMarkRemark">{{matPackSta.packExplain}}</textarea>
                        </p>
                        <div class="dis-il-block imgbox imgbox1">
                            <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png" />
                        </div>
                        <div class="dis-il-block imgbox hide imgbox2">
                            <img class="save_img" src="${ctxStatic}/images/pic/save_img.png" id="btnSaveStandardPackSStandard" />
                            <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png" />
                        </div>

                        <div class="judge-div">
                            <input type="text" :class="matPackSta.packStaScore == 1 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                            <input type="text" :class="matPackSta.packStaScore == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                            <strong class="fl score cGreen fz14 pl5">{{matPackSta.packStaScore}}分</strong>
                        </div>
                    </div>
                    </div>
                </div>
                <div class="analyItem">
                    <p class="analyItemTit tx-center">包装照片</p>
                    <div class="analyItemCon relative">
                        <ul class="brandmodel clearfix">

                            <%--有照片时展示--%>
                            <li v-show="matPackSta.packPhoto != null && matPackSta.packPhoto != ''"  >
                                <h4 class="h4title mb15"></h4>
                                <div class="packagedphoto">
                                    <img :src="matPackSta.packPhoto" />
                                    <!--图片遮罩和放大按钮 开始-->
                                    <div class="uiImgUpload-mark">
                                        <div class="uiImgUpload-mark-bg"></div>
                                        <div class="uiImgUpload-mark-link">
                                            <div class="dis_il_block">
                                                <a href="jvascript:" class="upagain_link"><input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="file filepack"></a>
                                                <a href="javascript:" class="enlarge_link" :data-src="matPackSta.packPhoto" data-imgname=""></a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--图片遮罩和放大按钮 结束-->
                                </div>
                            </li>

                            <%-- 无照片时展示 --%>
                            <li v-show="matPackSta.packPhoto == null || matPackSta.packPhoto == ''" >
                                <h4 class="h4title mb15"></h4>
                                <div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth180 relative">
                                    <a href="javascript:" class="uiImgUpload-first">
                                        <input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" value="" class="file filepack">
                                        <em class="bgIcon file-ico"></em>
                                    </a>
                                    <img src="" />
                                    <!--图片遮罩和放大按钮 开始-->
                                    <div class="uiImgUpload-mark">
                                        <div class="uiImgUpload-mark-bg"></div>
                                        <div class="uiImgUpload-mark-link">
                                            <div class="dis_il_block">
                                                <a href="jvascript:" class="upagain_link"><input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="filepack"></a>
                                                <a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--图片遮罩和放大按钮 结束-->
                                </div>
                            </li>
                        </ul>

                        <div class="judge-div">
                            <input type="text" :class="matPackSta.packPhoto != '' ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                            <input type="text" :class="matPackSta.packPhoto == '' ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                            <strong class="fl score cGreen fz14 pl5">{{matPackSta.packPhoto != '' ? '1' : '0'}}分</strong>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="divMaterialLoadAlert_bottom pt10 plr10 boldTopLine">
        <div class="analyItem">
            <p class="analyItemTit tx-center">综合</p>
            <div class="analyItemCon">
                <p class="fl col-md-8 cRed">
                    <span class="cLightGray pr8 cRed">包装标准属性至少录入一项/完成包装类别,包装材质,包装说明录入/完成包装照片上传</span>
                    <strong class="plr5 cRed"></strong>
                </p>
                <p class="fl col-md-4"><span class="cLightGray pr8">得分</span><strong class="fz14 cGreen">{{matPackSta.totalLoopScore}}分</strong></p>
                <a v-show="matPackSta.packStaLoopFlag == 1" href="javascript:" class="circlemark circlemark-green">完</a>
                <a v-show="matPackSta.packStaLoopFlag == 0" href="javascript:" class="circlemark circlemark-lightRed">未</a>
            </div>
        </div>
        <div class="mt10 clearfix ml10">
            <p class="minwidth204 lh20">
                <span class="pr5 cRed">注：修改此标准信息会同步其他地区标准</span>
            </p>
        </div>
    </div>
    <!--标准 结束-->
    <input type="hidden" id="hidTSIID" />
    <input type="hidden" id="hidTSIType" value="8" />
</div>

<script>
//    $(".lbl_strandard_packs_stand").removeClass("cGreen").removeClass("cRed");
//    $(".lbl_strandard_packs_stand").addClass("cGreen").html("有");
//    $(".lbl_strandard_packs_photo").removeClass("cGreen").removeClass("cRed");
//    $(".lbl_strandard_packs_photo").addClass("cGreen").html("有");
//    $(".lbl_strandard_packs_detect").removeClass("cGreen").removeClass("cRed");
//    $(".lbl_strandard_packs_detect").addClass("cGreen").html("1项");
//    $(".lbl_strandard_packs_perfect").removeClass("cGreen").removeClass("cRed");
//    $(".lbl_strandard_packs_perfect").addClass("cGreen").html("100%");
</script>


<script>

</script>

<script>
    $(document).on("click",".enlarge_link",function(){
        var i=$(this).index();
        var thisclick = this;
        var thisId = "preview_" + i;
        var curId = "CurImg_" + i;
        //图片放大
        rxued.images.enSingleLarge(thisclick, thisId, curId);
        //点击旋转按钮
        rxued.images.rotateBtnClick(thisId, curId);
        //关闭图片放大弹出层
        rxued.images.closeImgAlert(thisId);
        //1:1
        rxued.images.oneToone(thisId, curId);
    });

    //滚动条高度计算
    function countAlert(){
        var theight = $(window).height()-$("#main_header").outerHeight(true) -$(".divMaterialLoadAlert-con .platit").outerHeight(true) - $(".divMaterialLoadAlert_bottom").outerHeight(true) - 10;
        $(".divMaterialLoadAlert_topm").height(theight).slimScroll({
            height: theight,
            borderRadius: "0px"
        });
        $(".divMaterialLoadAlert_topm").closest(".slimScrollDiv").height(theight);
        $(window).resize(function () {
            var theight = $(window).height()-$("#main_header").outerHeight(true) -$(".divMaterialLoadAlert-con .platit").outerHeight(true) - $(".divMaterialLoadAlert_bottom").outerHeight(true) - 10;
            $(".divMaterialLoadAlert_topm").height(theight).slimScroll({
                height: theight,
                borderRadius: "0px"
            });
            $(".divMaterialLoadAlert_topm").closest(".slimScrollDiv").height(theight);
        })
    }
    //清空数据
    function ClearStandard() {
    }
    $(function () {

        setTimeout(function () {

            countAlert();
            initImageUploadPS();//初始包装图片上传脚本
            PhotoRelated();
            $(document).on("click", ".img_add", function () {
                $(".divNewStandardItem").removeClass("hide");
            });
            $(document).on("click", ".img_edit", function () {
                var divObj = $(this).closest("div.analyItem");
                var tsiID = $(divObj).data("tsiid");
                if (tsiID) {
                    $("#hidTSIID").val(tsiID);
                }
                var unit = $(divObj).attr("zz-unit");
                if (unit) {
                    $(divObj).find(".ddlStandardUnit").val(unit);
                }

                $(divObj).find(".info-look").hide();
                $(divObj).find(".info-edit").show();

                $(this).parent(".imgbox").hide();
                $(this).parent(".imgbox").next(".imgbox").show();
            });
            $(document).on("click", ".goback_img", function () {
                var divObj = $(this).closest("div.analyItem");
                $(divObj).find(".info-look").show();
                $(divObj).find(".info-edit").hide();

                $(this).parents(".analyItemCon").find(".imgbox1").show();
                $(this).parent(".imgbox2").hide();
            });

        });

    });
</script>

<%--VUE2.0 开始--%>
<script>
    var editMatPackSta = new Vue({
        el: '#editMatPackSta',
        data: {
            matPackSta: {},// 材料基础信息
            matUnitArr:[],// 材料单位数组
        },
        created() {
            // 页面加载构建数据
            this.buildPackStaData();// 包装标准数据
            this.buildMatUnitArrData();// 材料单位数组参数
        },
        // 局部过滤器
        filters: {
        },
        methods: {
            // 包装标准数据
            buildPackStaData: function () {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                $.ajax({
                    url: basePath + '/sublibrary-api/city_three_section/platform/sta_package',
                    type: 'GET',
                    dataType: 'json',
                    data: {treeFourID},
                    success: function (res) {
                        _this.matPackSta = res.body;
                        // 设置 图片类型默认选中状态
                        $("input[name=pstype][value="+res.body.packCategory+"]").prop("checked", true);
                    },
                    error: function (err) {alert('操作出错！');}
                });
            },
            // 材料单位数组
            buildMatUnitArrData: function () {
                var _this = this;
                $.ajax({
                    url: basePath + '/common-api/units',
                    type: 'GET',
                    dataType: 'json',
                    success: function (res) {_this.matUnitArr = res.body;},
                    error: function (err) {alert("操作出错！");}
                });
            }
        }
    });
</script>

