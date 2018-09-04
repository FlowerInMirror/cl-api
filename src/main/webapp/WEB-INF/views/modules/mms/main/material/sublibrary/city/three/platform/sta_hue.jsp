<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<%--二维码--%>
<script src="${ctxStatic}/js/jquery.qrcode.min.js?${verStatic}"></script>
<style>
    .smallsample .fz15 {
        font-size: 15px;
        margin-bottom: 15px;
    }

    .smallsample .lh22 {
        line-height: 22px;
    }
    .QRcodeImg{width:84px;
        height:84px;margin-top:8px;}
    .mt36{margin-top:36px;}
</style>
<!--标准 开始-->
<div id="editMatHueSta" v-cloak>
    <div class="divMaterialLoadAlert_topm pl10">
        <h2 class="uiTitle2">
            <i class="uiTitle-icon"></i>
            小样标准
        </h2>
        <div class="materialpicture materialTopNavConm pr10">
            <div class="SearchCon pt10">
                <div class="huojiacha"
                     :class="matHueSta.standardSize == 0 ? 'analyItem divNewStandardItem' : 'analyItem divNewStandardItem hide' ">
                    <p class="analyItemTit tx-center">属性{{matHueSta.standardSize + 1 | numToStr(matHueSta.standardSize +
                        1) }}</p>
                    <div class="analyItemCon relative" style="width:100%">
                        <p class="info-edit">
                            <span class="cLightGray pr8 justify_span justify_span_w80">
                                <input type="text" class="width80 txtStandardName" placeholder="属性名称"
                                       data-input="true"/>
                            </span>
                            <span><input type="text" class="width77 txtStandardValue ml5" placeholder="属性值"
                                         data-input="true"/></span>
                            <select class="ddlStandardUnit width70  mt3f">
                                <option selected="" value="0">单位</option>
                                <option v-for="matUnit in matUnitArr" :value="matUnit.unitID">{{matUnit.unitName}}
                                </option>
                            </select>
                            <span>(合格范围:<span><input type="text" class="width55 ddlStandardMIN" value=""/></span>-<span><input
                                    type="text" class="width55 ddlStandardMAX" value=""/></span>)</span>
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class="pr8 fl" style="margin-right:16px;"> <input type="text"
                                                                                    class="width80 fl txtExteriorName"
                                                                                    value="" placeholder="检验方法一"
                                                                                    data-input="false"></span>
                            <input type="text" class="width360 fl txtStandardPC" data-input="false">
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class=" fl" style="margin-right:16px;"> <input type="text"
                                                                                 class="width80 fl txtExteriorSC"
                                                                                 value="" placeholder="检验方法二"
                                                                                 data-input="false"></span>
                            <textarea rows="3" class="width360 fl txtStandardDM" data-input="false"></textarea>
                        </p>

                        <div class="dis-il-block imgbox  imgbox2">
                            <img v-show="matHueSta.standardSize == 0" class="save_img btnSaveStandardDetect"
                                 src="${ctxStatic}/images/pic/add_img.png?${verStatic}"/>
                            <img v-show="matHueSta.standardSize != 0" class="save_img btnSaveStandardDetect"
                                 src="${ctxStatic}/images/pic/save_img.png?${verStatic}"/>
                            <img v-show="matHueSta.standardSize != 0"
                                 onclick="$(this).closest('div.analyItem').addClass('hide')"
                                 src="${ctxStatic}/images/pic/delete_img.png?${verStatic}"/>
                        </div>
                    </div>
                </div>

                <%--小样标准遍历 开始--%>
                <div v-for="(hueSta, index) in matHueSta.standards" class="analyItem huojiacha"
                     :data-tsiid="hueSta.standardID" :zz-unit="hueSta.attributeUnitID">
                    <p class="analyItemTit tx-center">属性{{index + 1 | numToStr(index + 1)}}</p>
                    <div class="analyItemCon relative">
                        <p class="info-look">
                            <span class="cLightGray pr8 justify_span justify_span_w80" :title="hueSta.attributeName">{{hueSta.attributeName|convertOverlengthStrings(5)}}</span><span>{{hueSta.attributeValue + '' + hueSta.attributeUnit}}</span>
                            <span v-show="hueSta.minRange == '' && hueSta.maxRange == ''"></span>
                            <span v-show="hueSta.minRange != '' || hueSta.maxRange != ''">合格范围({{hueSta.attributeUnit}}):{{hueSta.minRange != null ? hueSta.minRange : '∞'}}～{{hueSta.maxRange != null ? hueSta.maxRange : '∞'}}</span>
                        </p>
                        <p class="info-edit hide">
                            <span class="cLightGray pr8 justify_span justify_span_w80">
                             <input type="text" class="width80 txtStandardName" :value="hueSta.attributeName"
                                    data-input="true"/>
                            </span><span><input type="text" class="width77 txtStandardValue ml5"
                                                :value="hueSta.attributeValue" data-input="true"/></span>
                            <select class="ddlStandardUnit width70  mt3f">
                                <option selected="" value="0">单位</option>
                                <option v-for="matUnit in matUnitArr" :value="matUnit.unitID">{{matUnit.unitName}}
                                </option>
                            </select>
                            <span>(合格范围:<span><input type="text" class="width55 ddlStandardMIN"
                                                     :value="hueSta.minRange"/></span>-<span><input type="text"
                                                                                                    class="width55 ddlStandardMAX"
                                                                                                    :value="hueSta.maxRange"/></span>)</span>
                        </p>

                        <p class="clearfix mt5 hide">
                            <span class="cLightGray pr8 fl justify_span justify_span_w80">特殊要求</span>
                            <span>{{hueSta.tsiSpecialClaim}}</span>
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class="pr8 fl" style="margin-right:16px;"> <input type="text"
                                                                                    class="width80 fl txtExteriorName"
                                                                                    value="" placeholder="检验方法一"
                                                                                    data-input="false"></span>
                            <input type="text" class="width360 fl txtStandardPC" data-input="false"
                                   :value="hueSta.tsiSpecialClaim" placeholder="">
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class="cLightGray pr8 fl justify_span justify_span_w80">检测方法</span>
                            <span class="fl" style="width:80%">{{hueSta.tsiDetectMethod}}</span>
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class=" fl" style="margin-right:16px;"> <input type="text"
                                                                                 class="width80 fl txtExteriorSC"
                                                                                 value="" placeholder="检验方法二"
                                                                                 data-input="false"></span>
                            <textarea rows="3" class="width360 fl txtStandardDM" data-input="false">hueSta.tsiDetectMethod</textarea>
                        </p>

                        <div class="dis-il-block imgbox imgbox1">
                            <img v-show="index == 0" class="img_add"
                                 src="${ctxStatic}/images/pic/add_img.png?${verStatic}"/>
                            <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png?${verStatic}"/>
                            <img class="img_delete" src="${ctxStatic}/images/pic/delete_img.png?${verStatic}"/>
                        </div>
                        <div class="dis-il-block imgbox  hide imgbox2">
                            <img class="save_img btnSaveStandardDetect"
                                 src="${ctxStatic}/images/pic/save_img.png?${verStatic}"/>
                            <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png?${verStatic}"/>
                        </div>
                        <div class="judge-div-sample judge-div-small">
                            <input type="text" class="fl judge-span judge-yes active">
                            <input type="text" class="fl judge-span judge-no">
                            <strong class="fl score cGreen fz14 pl5">1分</strong>
                        </div>
                    </div>
                </div>
                <div class="analyItem huojiacha">
                    <p class="analyItemTit tx-center">取样方法</p>
                    <div class="analyItemCon relative">
                        <div class="analyItemConText">
                            <p class="info-look">
                                <span class="cLightGray fl pr8 justify_span justify_span_w80">取样方法</span>
                                <span class="fl" style="width:80%" id="lblMockUpSamplingRemark">{{matHueSta.samplingMethod}}</span>
                            </p>
                            <p class="info-edit hide">
                                <span class="cLightGray pr8 justify_span justify_span_w80">取样方法</span><textarea rows="3"
                                                                                                                class="width335"
                                                                                                                id="txtMockUpSamplingRemark">{{matHueSta.samplingMethod}}</textarea>
                            </p>
                            <div class="dis-il-block imgbox imgbox1">
                                <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png?${verStatic}"/>
                            </div>
                            <div class="dis-il-block imgbox hide imgbox2">
                                <img class="save_img" src="${ctxStatic}/images/pic/save_img.png?${verStatic}"
                                     id="btnSaveStandardMUSMethod"/>
                                <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png?${verStatic}"/>
                            </div>
                            <div class="judge-div-small">
                                <input type="text"
                                       :class="matHueSta.sampleLoopStatus == 1 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                                <input type="text"
                                       :class="matHueSta.sampleLoopStatus == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                                <strong class="fl score cGreen fz14 pl5">{{matHueSta.sampleLoopStatus}}分</strong>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="analyItem">
                    <p class="analyItemTit tx-center">货架</p>
                    <div class="analyItemCon relative">
                        <div class="analyItemsubTitle mb20">
                            <label class="uiRadio12 mt5"><input type="checkbox" name="b" class="uiRadio12-input checkAll">全选</label>
                            <div class="fr">
                                <input type="button" value="批量上架" class="fl mr5 uiBtn-normal uiBtn-blue groupOnShelf groupoperate" @click="SaveMaterialSmallSample($event)" data-shangjia="1">
                                <input type="button" value="批量下架" class="fl uiBtn-normal uiBtn-red groupLowFrame groupoperate" @click="SaveMaterialSmallSample($event)" data-shangjia="2">
                            </div>
                        </div>
                        <div class="analyItem" v-for="GoodsShelves in GoodsShelvesArr" :data-matid="GoodsShelves.MatId" :data-groundingstatus="GoodsShelves.GroundingStatus">
                            <p class="analyItemTit tx-center"><input type="checkbox" class="InpCheck"></p>
                            <div class="analyItemCon relative">
                                <div class="col-md-5">
                                    <h2 class="fz15">{{GoodsShelves.MatName}}</h2>
                                    <p class="lh22">品牌：{{GoodsShelves.BrandName}}</p>
                                    <p class="lh22">规格：{{GoodsShelves.MatSpec}}</p>
                                    <p class="lh22">编码：{{GoodsShelves.Code}}</p>
                                </div>
                                <div class="col-md-5">
                                    <div class="brandQRCode2 QRcodeImg" :data-matid="GoodsShelves.MatId" ></div>
                                    <%-- <img src="/static/mms/images/pic/erweima.png" class="fl QRcodeImg">
                                     <select class="fl ml10 mt36">--%>
                                        <%--<option>有小样</option>--%>
                                        <%--<option>无小样</option>--%>
                                    <%--</select>--%>
                                </div>
                                <div class="col-md-2">
                                    <input type="button" value="上架" class="uiBtn-normal uiBtn-blue mt36" @click="SaveMaterialSmallSample($event)" v-show="GoodsShelves.GroundingStatus!=1" data-shangjia="1">
                                    <input type="button" value="下架" class="uiBtn-normal uiBtn-red mt36" @click="SaveMaterialSmallSample($event)" v-show="GoodsShelves.GroundingStatus==1" data-shangjia="2">
                                </div>
                            </div>
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
                    <span class="cLightGray pr8 cRed">小样属性至少录入一项/取样方法完成录入 | 注:修改此标准信息会同步其他地区标准</span>
                    <strong class="plr5 cRed"></strong>
                </p>
                <p class="fl col-md-4"><span class="cLightGray pr8">得分</span><strong class="fz14 cGreen">{{matHueSta.totalScore}}分</strong>
                </p>
                <a v-show="matHueSta.loopStatus == 1" href="javascript:" class="circlemark circlemark-green">完</a>
                <a v-show="matHueSta.loopStatus == 0" href="javascript:" class="circlemark circlemark-lightRed">未</a>
            </div>
        </div>
    </div>

    <!--标准 结束-->
    <input type="hidden" id="hidTSIID"/>
    <input type="hidden" id="hidTSIType" value="4"/>

</div>
<%--VUE2.0 开始--%>
<script>


    function doScroll(obj, ulclass, li, prev, next, scrollsl, fn) {
        var oUllist = obj.find(ulclass);
        var oLi = obj.find(li);
        var theLength = oLi.length;
        var oWidth = parseInt(oLi.eq(0).outerWidth()) + parseInt(oLi.eq(0).css("margin-left")) + parseInt(oLi.eq(0).css("margin-right"));
        oUllist.width(oWidth * theLength);
        if (theLength > scrollsl) {
            obj.find(prev).show();
            obj.find(next).show()
        } else {
            obj.find(prev).hide();
            obj.find(next).hide()
        }
        var iNum = 0;
        obj.find(prev).unbind("click");
        obj.find(prev).click(function () {
            if (!oUllist.is(":animated")) {
                if (iNum == 0) {
                } else {
                    iNum -= scrollsl;
                    oUllist.animate({
                        "marginLeft": -oWidth * iNum + "px"
                    }, 500)
                }
            }
        });
        obj.find(next).unbind("click");
        obj.find(next).click(function () {
            if (!oUllist.is(":animated")) {
                iNum += scrollsl;
                if (iNum >= theLength) {
                    oUllist.animate({
                        "marginLeft": 0 + "px"
                    }, 500);
                    iNum = 0
                }
                if (theLength - iNum < scrollsl) {
                    oUllist.animate({
                        "marginLeft": -oWidth * (theLength - scrollsl) + "px"
                    }, 500)
                } else {
                    oUllist.animate({
                        "marginLeft": -oWidth * iNum + "px"
                    }, 500)
                }
            }
        });
        if (typeof (fn) == "function") {
            fn()
        }
    }

    function DoPictureCount() {
        var oheight = 0;
        for (var i = 0; i < $(".huojiacha").length; i++) {
            oheight += $(".huojiacha").eq(i).outerHeight(true);
        }
        var oendheight = $(".divMaterialLoadAlert").height() - $(".divMaterialLoadAlert_bottom").outerHeight(true) - 35 - $(".divMaterialLoadAlert_center").outerHeight(true) - oheight;
        $(".GoodsShelves").width($(".GoodsShelvesBoxCon").width());
        $(".GoodsShelves").height(oendheight);
        $(".GoodsShelvesUl").width($(".GoodsShelves").width() * $(".GoodsShelves").length);
    }

    $(function () {
        setTimeout(function () {

            DoPictureCount();

        }, 400)
        doScroll($(".GoodsShelvesBox"), ".GoodsShelvesUl", ".GoodsShelvesUl .GoodsShelves", ".GoodsShelvesPrev", ".GoodsShelvesNext", 1);

        $(document).on("click", ".GoodsShelvesp", function () {
            $(this).closest(".col-md-4").find(".GoodsShelvesp").removeClass("GoodsShelvespZindex");
            $(this).addClass("GoodsShelvespZindex");

        });
        //全选
        $(document).on("click",".checkAll",function(){
            $(this).prop("checked") == true ? $(".InpCheck").prop("checked", true) : $(".InpCheck").prop("checked", false)
        });
        $(document).on("click",".InpCheck",function(e){
            $(".InpCheck").length == $(".InpCheck").filter(":checked").length ? $(".checkAll").prop("checked", true) : $(".checkAll").prop("checked", false);
            e.stopPropagation()
        });

    })


    var editMatHueStaVue = new Vue({
        // 提供一个在页面上已存在的 DOM 元素作为 Vue 实例的挂载目标。
        el: '#editMatHueSta',
        // Vue实例的数据对象。Vue 将会递归将 data 的属性转换为 getter/setter，从而让 data 的属性能够响应数据变化
        data: {
            matHueSta: {},// 材料基础信息
            matUnitArr: [],// 材料单位数组
            GoodsShelvesArr:[],//货架小样数组
        },
        // 实例已经创建完成之后被调用。在这一步，实例已完成以下的配置：数据观测(data observer)，属性和方法的运算， watch/event 事件回调。然而，挂载阶段还没开始，$el 属性目前不可见
        created:function() {
            // 页面加载构建数据
            this.buildHueStaData();// 小样标准数据
            this.buildMatUnitArrData();// 材料单位数组参数
            this.BindGoodsShelves();//货架小样
        },
        // 局部过滤器
        filters: {},
        // methods将被混入到 Vue 实例中，可以直接通过 VM 实例访问这些方法，或者在指令表达式中使用 方法中的 this自动绑定为 Vue 实例
        methods: {
            // 小样标准数据
            buildHueStaData: function () {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                var cityID = $("#hidCityID").val();
                $.ajax({
                    url: basePath + '/sublibrary-api/city_three_section/platform/sta_hue',
                    type: 'GET',
                    dataType: 'json',
                    data: {treeFourID,cityID},
                    success: function (res) {
                        _this.matHueSta = res.body;
                    },
                    error: function (err) {
                        alert("操作出错！");
                    }
                });
            },
            // 材料单位数组
            buildMatUnitArrData: function () {
                var _this = this;
                $.ajax({
                    url: basePath + '/common-api/units',
                    type: 'GET',
                    dataType: 'json',
                    success: function (res) {
                        _this.matUnitArr = res.body;
                    },
                    error: function (err) {
                        alert("操作出错！");
                    }
                });
            },
            // 删除材料参数 通过:参数ID,四级科目ID
            deleteMatParam: function (paraID) {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                if (confirm("确定要删除材料参数吗？")) {
                    $.ajax({
                        url: basePath + '/standard_para-api/delete',
                        type: 'POST',
                        data: {'paraID': paraID, treeFourID},
                        dataType: 'json',
                        success: function (res) {
                            alert(res.statusMsg)
                        },
                        error: function (err) {
                            alert("操作出错！");
                        }
                    });
                }
            },
            //货架数据
            BindGoodsShelves:function(){
                var _this=this;
                MatId=$("#hidTreeID").val();//材料id
                AreaId=$("#hidCityID").val();//地区id
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/MaterialBrandGradeInfo?MatId='+MatId+"&AreaId="+AreaId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        _this.GoodsShelvesArr = res.Body;
                        // $nextTick 是在下次 DOM 更新循环结束之后执行延迟回调，在修改数据之后使用 $nextTick，则可以在回调中获取更新后的 DOM，API 文档中官方示例如下：
                        _this.$nextTick(function () {
                            // DOM is now updated DOM现在正在更新
                            // `this` is bound to the current instance `this‘绑定到当前实例
                            // 生成二维码
                            $(".brandQRCode2").each(function (index, domEle) {
                                var matID = $(domEle).data('matid');
                                var cityID = $("#hidCityID").val();
                                // 生成规则如下
                                var url = 'http://c.rxjyzx.com/SD/'+matID+'/'+cityID;
                                // 例:http://c.rxjyzx.com/SD/33A923C1-E74B-4911-AFFB-C52DE38347BD/12
                                $(domEle).attr("data-href", url);
                                $(domEle).qrcode({
                                    width: 84,
                                    height: 84,
                                    correctLevel: 0,
                                    background: "#efeef3", //背景颜色
                                    foreground: "black", //前景颜色
                                    text: url
                                });
                            });
                        })
                    },
                    error: function (err) {
                        alert("操作出错！");
                    }
                });
            },
            //货架“上架、下架”$(element.currentTarget).parents("tr").attr("data-matid");
            SaveMaterialSmallSample:function(element){
                var _this = this;
                var arrObj=[];
                if(!$(element.currentTarget).hasClass("groupoperate"))//批量上架或下架
                {
                    $(element.currentTarget).closest(".analyItem").find(".InpCheck").prop("checked","checked")
                }
                var ocheckedlen = $(".InpCheck:checked").length;
                for(var i=0;i<ocheckedlen;i++)
                {
                    var obj = new Object();
                    obj.mss_MatID=$(".InpCheck:checked").eq(i).closest(".analyItem").attr("data-matid");//材料id   data-matid
                    obj.mss_SampleStatus=0;//小样状态
                    obj.mss_Status=0;
                    obj.mss_GroundingStatus=$(element.currentTarget).attr("data-shangjia");//上架、未上架
                    arrObj.push(obj);
                }
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/SaveMaterialSmallSample',
                    type: 'post',
                    data: {"":arrObj},
                    dataType: 'json',
                    success: function (res) {
                        if (res.StatusCode == "1") {
                            alert("保存成功!");
                            $(".standardinfo.anItemBor-active").click();
                        }
                    },
                    error: function (err) {
                        alert("操作出错！");
                    }
                });
            }
        }
    });
    $(document).on("click","",function(){

    });
</script>

<script>
    <%--编辑完成操作--%>
    //    $(".lbl_strandard_mus_method").removeClass("cGreen").removeClass("cRed");
    //    $(".lbl_strandard_mus_method").addClass("cGreen").html("有");
    //    $(".lbl_strandard_mus_detect").removeClass("cGreen").removeClass("cRed");
    //    $(".lbl_strandard_mus_detect").addClass("cGreen").html("2项");
    //    $(".lbl_strandard_mus_perfect").removeClass("cGreen").removeClass("cRed");
    //    $(".lbl_strandard_mus_perfect").addClass("cGreen").html("100%");
</script>

<script>
    //清空数据
    function ClearStandard() {
    }

    $(function () {
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
        $(document).on("click", ".img_add", function () {
            $(".divNewStandardItem").removeClass("hide");
        });
    });
</script>

