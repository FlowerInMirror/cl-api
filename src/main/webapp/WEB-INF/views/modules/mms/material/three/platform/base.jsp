<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<!--图片 开始-->
<div id="editMatBase" v-cloak >
    <div class="divMaterialLoadAlert_topm pl10" >
        <h2 class="uiTitle2">
            <i class="uiTitle-icon"></i>
            基础信息
        </h2>
        <div class="materialpicture materialTopNavConm pr10">
            <div class="SearchCon pt10">
                
                <div class="analyItem">
                    <p class="analyItemTit tx-center">基础信息</p>
                    <div class="analyItemCon relative">
                        <div class="analyItemConText">

                            <%--单位--%>
                            <p class="info-look fl col-md-6">
                                <span class="cLightGray pr8 justify_span justify_span_w80 fl">单位</span>
                                <span class=" fl">{{matTreeBase.unitName}}</span>
                            </p>
                            <p class="info-edit hide fl col-md-6">
                                <span class="cLightGray pr8 justify_span justify_span_w80 fl">单位</span>
                                <select class="width125 fl" id="ddlUnit" :zz-old="matTreeBase.unitID">
                                    <option value="0">请选择计价单位</option>
                                    <option v-for="matUnit in matUnitArr" :value="matUnit.unitID">{{matUnit.unitName}}</option>
                                </select>
                            </p>

                            <%--分类--%>
                            <p class="info-look fl col-md-6 ">
                                <span class="fl cLightGray pr8 justify_span justify_span_w80">分类</span>
                                <span class="fl lh26">{{matTreeBase.classify == 1 ? '成品类' : '非成品类'}}</span>
                            </p>
                            <p class="info-edit hide fl col-md-6 ">
                                <span class="cLightGray pr8 justify_span justify_span_w80 fl">分类</span>
                                <select class=" fl" id="ddlProductType">
                                    <option value="0">请选择分类</option>
                                    <option value="1">成品类</option>
                                    <option value="2">非成品类</option>
                                </select>
                            </p>

                            <script>
                                $("#ddlUnit").val('{{matTreeBase.unitID}}');
                                $("#ddlProductType").val('{{matTreeBase.classify}}');
                            </script>

                            <%--功能--%>
                            <p class="info-look fl col-md-12 clearfix mt5">
                                <span class="cLightGray pr8 fl justify_span justify_span_w80">功能</span>
                                <span class="fl" style="width:80%">{{matTreeBase.function}}</span>
                            </p>
                            <p class="info-edit hide fl col-md-12 clearfix mt5">
                                <span class="cLightGray pr8 justify_span justify_span_w80 fl">功能</span>
                                <input type="text" class="width350 fl" id="txtMatFunction" :value="matTreeBase.function">
                            </p>

                            <%--描述--%>
                            <p class="clearfix mt5 col-md-12 info-look">
                                <span class="cLightGray fl pr8 justify_span justify_span_w80">描述</span>
                                <span class="fl"
                                      style="width:80%">{{matTreeBase.describe}}</span>
                            </p>
                            <p class="info-edit hide mt5 col-md-12">
                                <span class="cLightGray pr8 justify_span justify_span_w80 fl">描述</span><textarea
                                    rows="3"
                                    class="width350 texrea fl"
                                    id="txtMatDescription">{{matTreeBase.describe}}</textarea>
                            </p>

                            <div class="dis-il-block imgbox imgbox1">
                                <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png?${verStatic}">
                            </div>
                            <div class="dis-il-block imgbox  hide imgbox2">
                                <img class="save_img" src="${ctxStatic}/images/pic/save_img.png?${verStatic}" id="btnSaveTreeBaseInfo">
                                <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png?${verStatic}">
                            </div>
                        </div>
                        <div class="judge-div">
                            <input type="text" :class="matTreeBase.scoreBase == 1 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                            <input type="text" :class="matTreeBase.scoreBase == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                            <strong class="fl score cGreen fz14 pl5">{{matTreeBase.scoreBase}}分</strong>
                        </div>
                    </div>
                </div>

                <!--材料参数 开始-->
                <div class="analyItem">
                    <p class="analyItemTit tx-center">材料参数</p>
                    <div class="analyItemCon relative">
                        <div class="analyItemConText">
                            <div class="clearfix info-look">
                                <p v-for="matPara in matTreeBase.matParas" class="fl minwidth204 lh20"><span><span class="justify_span justify_span_w80">{{matPara.paraName}}</span>：</span>{{matPara.paraType == 1 ? matPara.paraValue + '' + matPara.unitName : matPara.paraValue}}</p>
                            </div>
                            <div class="clearfix hide info-edit">
                                <div class="pt10 relative">
                                    <div class="clearfix parentbank-scroll" id="divParameter">
                                        <%--材料参数s--%>
                                        <div v-for="matPara in matTreeBase.matParas" class="col-md-6 mb10" :data-paraid="matPara.paraID">
                                            <label class="lh28 fl"><span class="justify_span justify_span_w70" :title="matPara.paraName">{{matPara.paraName | convertOverlengthStrings(5)}}</span>：</label>
                                            <input v-show="matPara.paraType == 1" type="text" class="form-control width80 fl" placeholder="请输入重量" :value="matPara.paraValue" :zz-old="matPara.paraValue">
                                            <span v-show="matPara.unitName != null && matPara.paraType == 1" class="fl lh28 ml10">{{matPara.unitName}}</span>
                                            <select  v-show="matPara.paraType == 2"  class="width120 fl ddlParaItem" :zz-old="matPara.paraValue">
                                                <option v-bind:selected="matPara.paraValue == ''" value="">请选择{{matPara.paraName}}</option>
                                                <%--材料参数项s--%>
                                                <option v-for="paraItem in matPara.paraItems"  v-bind:selected="paraItem.paraItemName == matPara.paraName" :value="paraItem.paraItemName">{{paraItem.paraItemName}}</option>
                                            </select>
                                            <a @click="deleteMatParam(matPara.paraID)" href="javascript:" class="fl attribute-dele ml5"></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="dis-il-block imgbox imgbox1"><img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png?${verStatic}"></div>
                            <div class="dis-il-block imgbox  hide imgbox2">
                                <img class="add-parameter" src="${ctxStatic}/images/pic/add_img.png?${verStatic}" data-div="divParameter">
                                <img class="saveMaterialPara" src="${ctxStatic}/images/pic/save_img.png?${verStatic}" data-type="1">
                                <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png?${verStatic}">
                            </div>
                        </div>
                        <div class="judge-div">
                            <input type="text" :class="matTreeBase.scorePara == 1 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                            <input type="text" :class="matTreeBase.scorePara == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                            <strong class="fl score cGreen fz14 pl5">{{matTreeBase.scorePara}}分</strong>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="divMaterialLoadAlert_bottom  pt10 plr10 boldTopLine">
        <div class="analyItem">
            <p class="analyItemTit tx-center">综合</p>
            <div class="analyItemCon">
                <p class="fl col-md-8 cRed">
                    <span class="cLightGray pr8 cRed">基础信息内容全部录入/材料参数至少录入一项</span>
                    <strong class="plr5 cRed"></strong>
                </p>
                <p class="fl col-md-4"><span class="cLightGray pr8">得分</span><strong class="fz14 cGreen">{{matTreeBase.scoreTotal}}分</strong></p>
                <a v-show="matTreeBase.scorePara != 0 && matTreeBase.scoreBase != 0" href="javascript:" class="circlemark circlemark-green">1</a>
                <a v-show="matTreeBase.scorePara == 0 || matTreeBase.scoreBase == 0" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
            </div>
        </div>
        <div class="clearfix ml10">
            <p class="minwidth204 lh20">
                <span class="pr5 cRed">注：此信息内容在全国都是统一的<br/></span>
            </p>
        </div>
    </div>

    <!--图片 结束-->
    <!--弹出层 开始-->
    <div class="alertBox addparameterAlert">
        <div class="alertTitle">
            <p>添加参数</p>
        </div>
        <div class="alertCon pl20 pr20 pt10 pb10">
            <div class="clearfix mb10 borBDashLine pb10 hide">
                <label class="fl lh28"><span class="justify_span justify_span_w60">参数名称</span>：</label>
                <div class="fl uiHas-textIcon width180 ml10 mr10">
                    <input type="text" class="form-control col-md-12" placeholder="请输入关键字">
                    <i class="uiText-searchIcon bgIcon"></i>
                </div>
                <input type="button" value="搜索" class="uiBtn-normal uiBtn-blue fl">
            </div>
            <div class="relative">
                <div class="parameter-lft" id="divParaALL">
                    <%--材料参数 弹出层--%>
                    <label v-for="matParm in matParamArr" class="uiRadio12 fl width210 mb10"><input type="checkbox" class="uiRadio12-input" :data-paraid="matParm.paraId">{{matParm.paraName + '    ' + (matParm.paraUnitname == '' ? '' : '(' + matParm.paraUnitname + ')')}}</label>
                </div>
            </div>
        </div>
        <div class="alertFoot pt5 pb5">
            <input type="button" value="确定" class="uiBtn-normal uiBtn-blue mr10" id="btnAddParaItem">
            <input type="button" value="取消" class="uiBtn-normal uiBtn-gray" onclick="$('.addparameterAlert').hide();">
        </div>
    </div>
</div>
<script>
    //滚动条高度计算
    function countAlert(){
        var theight = $(window).height()-$("#main_header").outerHeight(true) -$("#editMatBase .uiTitle2").outerHeight(true) - $(".divMaterialLoadAlert_bottom").outerHeight(true) - 10;
        $(".materialpicture").height(theight).slimScroll({
            height: theight,
            borderRadius: "0px"
        });
        $(".materialpicture").closest(".slimScrollDiv").height(theight);
        $(window).resize(function () {
            var theight = $(window).height()-$("#main_header").outerHeight(true) -$("#editMatBase .uiTitle2").outerHeight(true) - $(".divMaterialLoadAlert_bottom").outerHeight(true) - 10;
            $(".materialpicture").height(theight).slimScroll({
                height: theight,
                borderRadius: "0px"
            });
            $(".materialpicture").closest(".slimScrollDiv").height(theight);
        })
    }


    var editMatBaseVue = new Vue({
        // 提供一个在页面上已存在的 DOM 元素作为 Vue 实例的挂载目标。
        el: '#editMatBase',
        // Vue实例的数据对象。Vue 将会递归将 data 的属性转换为 getter/setter，从而让 data 的属性能够响应数据变化
        data: {
            matTreeBase: {},// 材料基础信息
            matUnitArr:[],// 材料单位数组
            matParamArr:[],// 材料参数数组
            matParamItemArr:[]// 材料参数'项'数组

        },
        // 实例已经创建完成之后被调用。在这一步，实例已完成以下的配置：数据观测(data observer)，属性和方法的运算， watch/event 事件回调。然而，挂载阶段还没开始，$el 属性目前不可见
        created() {
            this.buildBaseTreeData();
            this.buildMatUnitArrData();
            this.buildMatParamArrData();
        },
        // 局部过滤器
        filters: {},
        // methods将被混入到 Vue 实例中，可以直接通过 VM 实例访问这些方法，或者在指令表达式中使用 方法中的 this自动绑定为 Vue 实例
        methods: {
            // 基础信息 & 材料参数
            buildBaseTreeData: function () {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                $.ajax({
                    url: basePath + '/sublibrary-api/city_three_section/platform/base',
                    type: 'GET',
                    dataType: 'json',
                    data: {treeFourID},
                    success: function (res) {_this.matTreeBase = res.body;},
                    error: function (err) {alert("操作出错！");}
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
            },
            // 材料参数数组
            buildMatParamArrData: function () {
                var _this = this;
                $.ajax({
                    url: basePath + '/common-api/parameters',
                    type: 'GET',
                    dataType: 'json',
                    success: function (res) {_this.matParamArr = res.body;},
                    error: function (err) {alert("操作出错！");}
                });
            },
            // 参数项 未使用
            buildParamItem : function (paramID) {
                var _this = this;
                $.ajax({
                    url: basePath + '/parameter_item-api/para_items',
                    type: 'GET',
                    data: {paramID},
                    dataType: 'json',
                    success: function (res) {this.matParamItemArr = res.body;},
                    error: function (err) {alert("操作出错！");}
                });
            },
            // 删除材料参数 通过:参数ID,四级科目ID
            deleteMatParam : function (paraID) {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                if(confirm("确定要删除材料参数吗？")) {
                    $.ajax({
                        url: basePath + '/standard_para-api/delete',
                        type: 'POST',
                        data: {'paraID':paraID,treeFourID},
                        dataType: 'json',
                        success: function (res) {
                        },
                        error: function (err) {alert("操作出错！");}
                    });
                }
            },
            // 积分样式
            scoreClass : function (scoreBase,finishType) {
                var _this = this;
                var a = _this.matTreeBase.function;
                switch (finishType){
                    case 1 :if (scoreBase) return 'fl judge-span judge-yes active'; return 'fl judge-span judge-yes';
                    case 2 :if (scoreBase) return 'fl judge-span judge-no active'; return 'fl judge-span judge-no';
                }
                return '';
            }
        }
    });
</script>

<script>
//    $(".lbl_base_unit").html("套");
//    $(".lbl_base_finishProductType").html("非成品类");
</script>


<script>
    //清空数据
    function ClearStandard() {
    }

    $(function () {
        setTimeout(function(){
            countAlert();
        });
        $(document).on("click", ".img_edit", function () {
            var divObj = $(this).closest("div.analyItem");

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


        //添加参数
        $(document).on("click", ".add-parameter", function () {
            $(".addparameterAlert").show();
            $("#btnAddParaItem").attr("zz-div", $(this).data("div"));

            var owidth = $(".wrapper").outerWidth(true);
            $(".addparameterAlert").css({
                width: owidth,
                height: $(window).height(),
                left: $("#nav").width(),
                top: 0
            });
            $(".addparameterAlert .alertCon").css({
                maxHeight: $(window).height() - $(".alertTitle").outerHeight(true) - $(".alertFoot").outerHeight(true),
                height: $(window).height() - $(".alertTitle").outerHeight(true) - $(".alertFoot").outerHeight(true)
            });
        });

        //删除
        $(document).on("click", ".divMaterialLoadAlert .attribute-dele", function () {
            var divObj = $(this).parents("div[data-paraid]");
            $(divObj).remove();
        });

        var paraArr = [{
            "para_ID": 1,
            "para_Name": "长度",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1490926656790)\/",
            "para_updateTime": "\/Date(1491532288477)\/",
            "ParaItems": null
        }, {
            "para_ID": 2,
            "para_Name": "宽度",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1490926663100)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 3,
            "para_Name": "厚度",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1490926669050)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 4,
            "para_Name": "翼缘高度",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1490926680467)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 6,
            "para_Name": "外观",
            "para_Code": "",
            "para_Unit": 0,
            "para_UnitName": "",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1490927412203)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 7,
            "para_Name": "底面平直度",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1490927427990)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 8,
            "para_Name": "侧面平直度",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1490927436057)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 9,
            "para_Name": "重量",
            "para_Code": "",
            "para_Unit": 2,
            "para_UnitName": "KG ",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1490959044037)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 10,
            "para_Name": "外径",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1491377178137)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 11,
            "para_Name": "壁厚",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1491377197783)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 12,
            "para_Name": "不透光性",
            "para_Code": "",
            "para_Unit": 0,
            "para_UnitName": "",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1491377310560)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 13,
            "para_Name": "甲醛释放量",
            "para_Code": "",
            "para_Unit": 8,
            "para_UnitName": "mg/100g",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1491789301580)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 14,
            "para_Name": "高度",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1491874608183)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 15,
            "para_Name": "材质",
            "para_Code": "",
            "para_Unit": 0,
            "para_UnitName": "",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1491890563350)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 16,
            "para_Name": "桶",
            "para_Code": "",
            "para_Unit": 13,
            "para_UnitName": "L/桶",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1492418945293)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 17,
            "para_Name": "袋",
            "para_Code": "",
            "para_Unit": 18,
            "para_UnitName": "kg/袋",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1492567421190)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 18,
            "para_Name": "盘",
            "para_Code": "",
            "para_Unit": 3,
            "para_UnitName": "m",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1492651211130)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 19,
            "para_Name": "内径",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1492651271520)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 20,
            "para_Name": "直径",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1492762227690)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 21,
            "para_Name": "锁边距",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1493876753300)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 22,
            "para_Name": "适配门厚度",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1493876803770)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 23,
            "para_Name": "产地",
            "para_Code": "",
            "para_Unit": 0,
            "para_UnitName": "",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1495177113547)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 24,
            "para_Name": "竖向龙骨厚度",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1495711550110)\/",
            "para_updateTime": "\/Date(1495711767617)\/",
            "ParaItems": null
        }, {
            "para_ID": 25,
            "para_Name": "功率",
            "para_Code": "",
            "para_Unit": 39,
            "para_UnitName": "瓦",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1495860207033)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 26,
            "para_Name": "额定电压",
            "para_Code": "",
            "para_Unit": 40,
            "para_UnitName": "V",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1495860218967)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 27,
            "para_Name": "主电功耗",
            "para_Code": "",
            "para_Unit": 39,
            "para_UnitName": "瓦",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1495860243087)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 28,
            "para_Name": "基材环保等级",
            "para_Code": "",
            "para_Unit": 42,
            "para_UnitName": "级",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1496307611113)\/",
            "para_updateTime": "\/Date(1496307930567)\/",
            "ParaItems": null
        }, {
            "para_ID": 29,
            "para_Name": "进深",
            "para_Code": "",
            "para_Unit": 1,
            "para_UnitName": "mm",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1496315778100)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 30,
            "para_Name": "光源类型",
            "para_Code": "",
            "para_Unit": 0,
            "para_UnitName": "",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1497073050977)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 31,
            "para_Name": "安装方式",
            "para_Code": "",
            "para_Unit": 0,
            "para_UnitName": "",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1497073064107)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 32,
            "para_Name": "色温",
            "para_Code": "",
            "para_Unit": 49,
            "para_UnitName": "K",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1497273291107)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 33,
            "para_Name": "应急工作时间",
            "para_Code": "",
            "para_Unit": 41,
            "para_UnitName": "小时",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1497421311717)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 34,
            "para_Name": "额定电流",
            "para_Code": "",
            "para_Unit": 50,
            "para_UnitName": "A",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1497493313490)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 35,
            "para_Name": "防火等级",
            "para_Code": "",
            "para_Unit": 0,
            "para_UnitName": "",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1498109772987)\/",
            "para_updateTime": "\/Date(1498110311657)\/",
            "ParaItems": null
        }, {
            "para_ID": 37,
            "para_Name": "耐火时间",
            "para_Code": "",
            "para_Unit": 41,
            "para_UnitName": "小时",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1498110341650)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 38,
            "para_Name": "工序数量",
            "para_Code": "",
            "para_Unit": 56,
            "para_UnitName": "道",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1498182319243)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 39,
            "para_Name": "保质期",
            "para_Code": "",
            "para_Unit": 62,
            "para_UnitName": "月",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1501725769133)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 40,
            "para_Name": "导体电阻（20°）",
            "para_Code": "",
            "para_Unit": 63,
            "para_UnitName": "Ω/km",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1501730530813)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 41,
            "para_Name": "时间",
            "para_Code": "",
            "para_Unit": 64,
            "para_UnitName": "h",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1501731012683)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 42,
            "para_Name": "桶",
            "para_Code": "",
            "para_Unit": 59,
            "para_UnitName": "KG/桶",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1501745760433)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 43,
            "para_Name": "商业配比",
            "para_Code": "",
            "para_Unit": 0,
            "para_UnitName": "",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1501752880790)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 44,
            "para_Name": "理论用量",
            "para_Code": "",
            "para_Unit": 58,
            "para_UnitName": "kg/㎡",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1501753024560)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 45,
            "para_Name": "风量",
            "para_Code": "",
            "para_Unit": 66,
            "para_UnitName": "m³/h",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1502086127243)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 46,
            "para_Name": "噪音",
            "para_Code": "",
            "para_Unit": 67,
            "para_UnitName": "DB",
            "para_Type": 1,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1502086377210)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": null
        }, {
            "para_ID": 47,
            "para_Name": "City",
            "para_Code": "",
            "para_Unit": 0,
            "para_UnitName": "",
            "para_Type": 2,
            "para_FixedState": 0,
            "para_MustState": 0,
            "para_jincheng": 0,
            "para_createTime": "\/Date(1508736583783)\/",
            "para_updateTime": "\/Date(-62135596800000)\/",
            "ParaItems": [{
                "paraitem_ID": 1,
                "paraitem_paraID": 47,
                "paraitem_Name": "BJ,TJ,SJZ",
                "paraitem_Sort": 1,
                "paraitem_jincheng": 0,
                "paraitem_createTime": "\/Date(1508736583787)\/",
                "paraitem_updateTime": "\/Date(-62135596800000)\/"
            }]
        }];

        //选定参数
        $(document).on("click", "#btnAddParaItem", function () {
            var divID = $("#btnAddParaItem").attr("zz-div");
            $("#divParaALL input.uiRadio12-input:checked").each(function () {
                var paraID = $(this).data("paraid");
                var ifAdd = true;
                $("#" + divID + " div[data-paraid]").each(function () {
                    if ($(this).data("paraid") == paraID) {
                        ifAdd = false;//存在不用在添加了
                        return true;
                    }
                });
                if (ifAdd == true) {
                    var paraItem;
                    for (var xx = 0; xx < paraArr.length; xx++) {
                        if (paraArr[xx].para_ID == paraID) {
                            paraItem = paraArr[xx];
                        }
                    }
                    if (paraItem) {
                        if (paraItem.para_Type == 1) {
                            $("#" + divID).append('<div class="col-md-6 mb10 newParameter" data-paraid="' + paraItem.para_ID + '"><label class="lh28 fl">' + CutStr(paraItem.para_Name) + '：</label><input type="text" class="form-control width80 fl" placeholder="请输入' + paraItem.para_Name + '" />'
                                + (paraItem.para_UnitName == '' ? '' : '<span class="fl lh28 ml10">' + paraItem.para_UnitName + '</span>')
                                + '<a href="javascript:" class="fl attribute-dele ml5"></a></div>');
                        }
                        else {
                            var options = '<option selected="" value="">请选择' + paraItem.para_Name + '</option>';
                            for (var zz = 0; zz < paraItem.ParaItems.length; zz++) {
                                options += '<option value="' + paraItem.ParaItems[zz].paraitem_Name + '">'
                                    + paraItem.ParaItems[zz].paraitem_Name + '</option>';
                            }

                            $("#" + divID).append('<div class="col-md-6 mb10 newParameter" data-paraid="' + paraItem.para_ID + '"><label class="lh28 fl">' + CutStr(paraItem.para_Name) + '：</label><select class="width120 fl ddlParaItem">' + options + '</select><a href="javascript:" class="fl attribute-dele ml5"></a></div>');
                        }
                    }
                }
            });

            $(".addparameterAlert").hide();
        });
    })

    function CutStr(str) {
        if (str.length >= 6) {
            str = '<span class="justify_span justify_span_w70" title="' + str + '">' + str.substring(0, 5) + '</span>';
        }
        else {
            str = '<span class="justify_span justify_span_w70">' + str + '</span>';
        }
        return str;
    }

</script>