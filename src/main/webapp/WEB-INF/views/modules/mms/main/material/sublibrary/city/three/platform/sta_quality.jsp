<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<!--标准 开始-->
<h2 class="uiTitle2 platit ml10">
    <i class="uiTitle-icon"></i>
    质量标准
</h2>
<div id="editMatQualitySta" v-cloak >
    <div class="divMaterialLoadAlert_topm pr10 pl10">
        <div class=" materialTopNavConm ">
            <div class="SearchCon">
                <div :class="matQualitySta.totalScore != 0 ? 'analyItem divNewStandardItem  hide' : 'analyItem divNewStandardItem'">
                    <p class="analyItemTit tx-center">质量标准</p>
                    <div class="analyItemCon relative" style="width:100%">
                        <p class="info-edit mt5">
                            <span class="cLightGray pr8">检验项</span>
                            <span class="ml4f">
                                    <input type="text" class="width77 txtStandardName" value="" data-input="true" />
                                    <input type="text" class="width77 hide txtStandardValue" data-input="false" value="" />
                                    <select class="ddlStandardUnit mt3f ">
                                        <option selected="" value="0">单位</option>
                                        <%--动态加载材料单位--%>
                                        <option v-for="matUnit in matUnitArr" :value="matUnit.unitID">{{matUnit.unitName}}</option>

                                    </select>
                                    <span class="hide">(合格范围:<span><input type="text" class="width60 ddlStandardMIN" value="" /></span>-<span><input type="text" class="width60 ddlStandardMAX" value="" /></span>)</span>
                                    <select class="ddlStandardMatLevel mt3f">
                                        <option selected="" value="0">针对所有档次</option>
                                        <option value="1">A档标准</option>
                                        <option value="2">B档标准</option>
                                        <option value="4">C档标准</option>
                                    </select>
                                </span>
                        </p>
                        <p class="clearfix mt5  mt5">
                            <span class="pr8 fl"> <input type="text" class="width80 fl txtExteriorName" value="" placeholder="检验方法一" data-input="true"></span>
                            <textarea rows="3" class="width360 fl txtStandardPC" placeholder="检验方法描述" data-input="true"></textarea>
                        </p>
                        <p class="clearfix mt5  mt5">
                            <span class="pr8 fl"> <input type="text" class="width80 fl txtExteriorSC" value="" placeholder="检验方法二" data-input="true"></span>
                            <textarea rows="3" class="width360 fl txtStandardDM" placeholder="检验方法描述" data-input="true"></textarea>
                        </p>

                        <div class="dis-il-block imgbox  imgbox2">
                            <%--动态加载 编辑按钮--%>
                            <img v-show="matQualitySta.totalScore == 0" class="save_img btnSaveStandardDetect" src="${ctxStatic}/images/pic/add_img.png" />
                            <img v-show="matQualitySta.totalScore != 0" class="save_img btnSaveStandardDetect" src="${ctxStatic}/images/pic/save_img.png?${verStatic}" />
                            <img v-show="matQualitySta.totalScore != 0" onclick="$(this).closest('div.analyItem').hide()" src="${ctxStatic}/images/pic/delete_img.png?${verStatic}" />
                        </div>
                    </div>
                </div>

                <%--质量标准遍历 开始--%>
                <div  v-for="(qualitySta, index) in matQualitySta.qualitys" class="analyItem" :data-tsiid="qualitySta.tsiId" :zz-unit="qualitySta.tsiUnit" :zz-blevel="qualitySta.tsiMatlevel">
                    <p class="analyItemTit tx-center">{{changeItemTitle(qualitySta.tsiMatlevel)}}</p>
                    <div class="analyItemCon relative">
                        <div class="analyItemConText">
                            <p class="info-look">
                                <span class="cLightGray pr8">检验项</span><span>{{qualitySta.tsiName}}</span>
                                <span v-show="qualitySta.tsiMatlevel == 0" >针对所有档次</span>
                                <span v-show="qualitySta.tsiMatlevel == 1" >A档标准</span>
                                <span v-show="qualitySta.tsiMatlevel == 2" >B档标准</span>
                                <span v-show="qualitySta.tsiMatlevel == 4" >C档标准</span>
                            </p>
                            <p class="info-edit mt5 hide">
                                <span class="cLightGray pr8">检验项</span><input type="text" class="width77 txtStandardName" :value="qualitySta.tsiName" data-input="true" />
                                <input type="text" class="width77 hide txtStandardValue" data-input="false" :value="qualitySta.tsiName" />
                                <select class="ddlStandardUnit mt3f">
                                    <option selected="" value="0">单位</option>
                                    <option v-for="matUnit in matUnitArr" :value="matUnit.unitID">{{matUnit.unitName}}</option>
                                </select>
                                <span class="hide">(合格范围:<span><input type="text" class="width60 ddlStandardMIN" :value="qualitySta.tsiStandardmin" /></span>-<span><input type="text" class="width60 ddlStandardMAX" :value="qualitySta.tsiStandardmax" /></span>)</span>
                                <select class="ddlStandardMatLevel mt3f">
                                    <option selected="" value="0">针对所有档次</option>
                                    <option value="1">A档标准</option>
                                    <option value="2">B档标准</option>
                                    <option value="4">C档标准</option>
                                </select>
                            </p>
                            <p class="clearfix mt5 info-look">
                                <span class="cLightGray pr8 fl justify_span justify_span_w80">{{qualitySta.tsiExteriorname}}</span>
                                <span class="fl" style="width:80%">{{qualitySta.tsiSpecialclaim}}</span>
                            </p>
                            <p class="clearfix mt5 info-edit hide">
                                <span class="pr8 fl"><input type="text" class="width80 fl txtExteriorName" :value="qualitySta.tsiExteriorname" placeholder="检验方法一" data-input="true"></span>
                                <textarea rows="3" class="width360 fl txtStandardPC" placeholder="检验方法描述" data-input="true">{{qualitySta.tsiSpecialclaim}}</textarea>
                            </p>
                            <p class="clearfix mt5 info-look">
                                <span class="cLightGray pr8 fl justify_span justify_span_w80">{{qualitySta.tsiExteriorsc}}</span>
                                <span class="fl" style="width:80%">{{qualitySta.tsiDetectmethod}}</span>
                            </p>
                            <p class="clearfix mt5 info-edit hide">
                                <span class="pr8 fl"><input type="text" class="width80 fl txtExteriorSC" :value="qualitySta.tsiExteriorsc" placeholder="检验方法二" data-input="true"></span>
                                <textarea rows="3" class="width360 fl txtStandardDM" placeholder="检验方法描述" data-input="true">{{qualitySta.tsiDetectmethod}}</textarea>
                            </p>
                            <div class="dis-il-block imgbox imgbox1">
                                <img v-show="index == 0" class="img_add" src="${ctxStatic}/images/pic/add_img.png?${verStatic}" />
                                <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png?${verStatic}" />
                                <img class="img_delete" src="${ctxStatic}/images/pic/delete_img.png?${verStatic}" />
                            </div>
                            <div class="dis-il-block imgbox  hide imgbox2">
                                <img class="save_img btnSaveStandardDetect" src="${ctxStatic}/images/pic/save_img.png?${verStatic}" />
                                <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png?${verStatic}" />
                            </div>
                        </div>
                        <div class="judge-div">
                            <input type="text" class="fl judge-span judge-yes active">
                            <input type="text" class="fl judge-span judge-no">
                            <strong class="fl score cGreen fz14 pl5">1分</strong>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--综合项--%>
    <div class="divMaterialLoadAlert_bottom pt10 plr10 boldTopLine">
        <div class="analyItem">
            <p class="analyItemTit tx-center">综合</p>
            <div class="analyItemCon">
                <p class="fl col-md-8 cRed">
                    <span class="cLightGray pr8 cRed">质量标准需录入4项</span>
                    <strong class="plr5 cRed"></strong>
                </p>
                <p class="fl col-md-4"><span class="cLightGray pr8">得分</span><strong class="fz14 cGreen">{{matQualitySta.totalScore}}分</strong></p>
                <a v-show="matQualitySta.totalScore >= 4" href="javascript:" class="circlemark circlemark-green">完</a>
                <a v-show="matQualitySta.totalScore < 4" href="javascript:" class="circlemark circlemark-lightRed">未</a>
            </div>
        </div>
        <div class="clearfix ml10">
            <p class="minwidth204 lh20">
                <span class="pr5 cRed">注：质量标准全国通用</span>
            </p>
        </div>
    </div>
</div>

<!--标准 结束-->
<input type="hidden" id="hidTSIID" />
<input type="hidden" id="hidTSIType" value="1" />

<script>
    var editMatQualityStaVue = new Vue({
        // 提供一个在页面上已存在的 DOM 元素作为 Vue 实例的挂载目标。
        el: '#editMatQualitySta',
        // Vue实例的数据对象。Vue 将会递归将 data 的属性转换为 getter/setter，从而让 data 的属性能够响应数据变化
        data: {
            matQualitySta: {},// 材料基础信息
            matUnitArr:[],// 材料单位数组
        },
        // 实例已经创建完成之后被调用。在这一步，实例已完成以下的配置：数据观测(data observer)，属性和方法的运算， watch/event 事件回调。然而，挂载阶段还没开始，$el 属性目前不可见
        created() {
            // 页面加载构建数据
            this.buildMatUnitArrData();// 材料单位数组参数
            this.buildQualityStaData();// 质量标准参数
        },
        // 局部过滤器
        filters: {
            // 阿拉伯数字转换为简写汉字
            numToStr1:function (Num) {
                debugger
                for (i = Num.length - 1; i >= 0; i--) {
                    Num = Num.replace(",", "")//替换Num中的“,”
                    Num = Num.replace(" ", "")//替换Num中的空格
                }
                if (isNaN(Num)) { //验证输入的字符是否为数字
                    //alert("请检查小写金额是否正确");
                    return;
                }
                //字符处理完毕后开始转换，采用前后两部分分别转换
                part = String(Num).split(".");
                newchar = "";
                //小数点前进行转化
                for (i = part[0].length - 1; i >= 0; i--) {
                    if (part[0].length > 10) {
                        //alert("位数过大，无法计算");
                        return "";
                    }//若数量超过拾亿单位，提示
                    tmpnewchar = ""
                    perchar = part[0].charAt(i);
                    switch (perchar) {
                        case "0":  tmpnewchar = "零" + tmpnewchar;break;
                        case "1": tmpnewchar = "一" + tmpnewchar; break;
                        case "2": tmpnewchar = "二" + tmpnewchar; break;
                        case "3": tmpnewchar = "三" + tmpnewchar; break;
                        case "4": tmpnewchar = "四" + tmpnewchar; break;
                        case "5": tmpnewchar = "五" + tmpnewchar; break;
                        case "6": tmpnewchar = "六" + tmpnewchar; break;
                        case "7": tmpnewchar = "七" + tmpnewchar; break;
                        case "8": tmpnewchar = "八" + tmpnewchar; break;
                        case "9": tmpnewchar = "九" + tmpnewchar; break;
                    }
                    switch (part[0].length - i - 1) {
                        case 0: tmpnewchar = tmpnewchar; break;
                        case 1: if (perchar != 0) tmpnewchar = tmpnewchar + "十"; break;
                        case 2: if (perchar != 0) tmpnewchar = tmpnewchar + "百"; break;
                        case 3: if (perchar != 0) tmpnewchar = tmpnewchar + "千"; break;
                        case 4: tmpnewchar = tmpnewchar + "万"; break;
                        case 5: if (perchar != 0) tmpnewchar = tmpnewchar + "十"; break;
                        case 6: if (perchar != 0) tmpnewchar = tmpnewchar + "百"; break;
                        case 7: if (perchar != 0) tmpnewchar = tmpnewchar + "千"; break;
                        case 8: tmpnewchar = tmpnewchar + "亿"; break;
                        case 9: tmpnewchar = tmpnewchar + "十"; break;
                    }
                    newchar = tmpnewchar + newchar;
                }
                //替换所有无用汉字，直到没有此类无用的数字为止
                while (newchar.search("零零") != -1 || newchar.search("零亿") != -1 || newchar.search("亿万") != -1 || newchar.search("零万") != -1) {
                    newchar = newchar.replace("零亿", "亿");
                    newchar = newchar.replace("亿万", "亿");
                    newchar = newchar.replace("零万", "万");
                    newchar = newchar.replace("零零", "零");
                }
                //替换以“一十”开头的，为“十”
                if (newchar.indexOf("一十") == 0) {
                    newchar = newchar.substr(1);
                }
                //替换以“零”结尾的，为“”
                if (newchar.lastIndexOf("零") == newchar.length - 1) {
                    newchar = newchar.substr(0, newchar.length - 1);
                }
                return newchar;
            }
        },
        // methods将被混入到 Vue 实例中，可以直接通过 VM 实例访问这些方法，或者在指令表达式中使用 方法中的 this自动绑定为 Vue 实例
        methods: {
            // 改变小项标题 入参:当前项档次标识
            changeItemTitle: function (levelFlag) {
                switch(levelFlag) {
                    case 0:return '统一质量标准';
                    case 1:return 'A档质量标准';
                    case 2:return 'B档质量标准';
                    case 4:return 'C档质量标准';
                }
            },
            // 材料质量标准
            buildQualityStaData: function () {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                $.ajax({
                    url: basePath + '/sublibrary-api/city_three_section/platform/sta_quality',
                    type: 'GET',
                    dataType: 'json',
                    data: {treeFourID},
                    success: function (res) {_this.matQualitySta = res.body;},
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
                            alert(res.statusMsg)
                        },
                        error: function (err) {alert("操作出错！");}
                    });
                }
            }
        }
    });
</script>

<script>
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
        setTimeout(function(){
            countAlert();
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
                var blevel = $(divObj).attr("zz-blevel");
                if (blevel) {
                    $(divObj).find(".ddlStandardMatLevel").val(blevel);
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
                $(".divNewStandardItem").css("display","table");
            });
        })
    });
</script>