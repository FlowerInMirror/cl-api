<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>标签</title>
    <!--三栏布局样式表 -->
    <link rel="stylesheet" href="${ctxStatic}/css/rxui1.0.2.min.css?${verStatic}"/>
    <link rel="stylesheet" href="${ctxStatic}/css/frame.min.css?${verStatic}"/>
    <link rel="stylesheet" href="${ctxStatic}/js/skin/layer.css?${verStatic}" type="text/css"/>
    <link href="${ctxStatic}/css/page-threecolumn.css?${verStatic}" rel="stylesheet"/>
    <!--针对此页面单独的样式-->
    <link href="${ctxStatic}/js/skin/layer.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/css/page-materialist.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/Scripts/My97DatePicker/skin/WdatePicker.css?${verStatic}" rel="stylesheet"/>
    <style>
        td {
            word-break: break-all;
        }

        .maul {
            padding-left: 10px;
        }

        .mafirst, .matwo, .qrcodelist {
            float: left;
            margin-right: 10px;
            margin-bottom: 10px;
            width: 100px;
            height: 30px !important;
            text-align: center;
            cursor: pointer;
            border: 1px solid #ccc;
            background: #fff;
            padding: 0 8px;
            height: 28px;
            line-height: 28px;
            letter-spacing: 1px;
            border-radius: 3px;
        }

        .mafirst:hover, .matwo:hover, .qrcodelist:hover {
            background: #09f;
            border: 1px solid #09f;
            color: #fff;
        }

        .active {
            background: #09f;
            border: 1px solid #09f;
            color: #fff;
        }
    </style>
</head>

<body class="logo-c000000 bar-cffffff nav-black nav-vertical" data-class="nav-vertical">
    <div id="main_box"  v-cloak>
        <div class="matile">
            <div class="reporttabCon mtf1 T_started" style="padding:10px 10px 0;">
                <%--检索/打印软件与教程下载--%>
                <div class="clearfix mb10 reporttabSou  ">
                    <ul class="fl daily_title">
                        <li title="置顶的材料" data-type="99" class="active" @click="showList()">圈</li>
                    </ul>
                    <select class="fl  mr5" id="ddlTreeOne" @change="treeOne()">
                        <option value="0" selected>一级类</option>
                        <option v-for="(treeOneData,index) in treeOneDatas" :value="treeOneData.treeID">
                            {{treeOneData.treeName}}
                        </option>
                    </select>
                    <select class="fl mr5" id="ddlTreeTwo" @change="treeTwo()">
                        <option value="0">二级类</option>
                        <option v-for="(treeTwoData,index) in treeTwoDatas" :value="treeTwoData.treeID">
                            {{treeTwoData.treeName}}
                        </option>
                    </select>
                    <select class="fl mr5" id="ddlTreeThree" @change="treeThree()">
                        <option value="0">材料名称</option>
                        <option v-for="(treeThreeData,index) in treeThreeDatas" :value="treeThreeData.treeID">
                            {{treeThreeData.treeName}}
                        </option>
                    </select>
                    <select class="fl mr5" id="ddlTreeFour" @change="treeFour()">
                        <option value="0">规格</option>
                        <option v-for="(treeFourData,index) in treeFourDatas" :value="treeFourData.treeID">
                            {{treeFourData.treeName}}
                        </option>
                    </select>
                    <div class="clearfix uiHas-textIcon fl mr10" style="width:330px;">
                        <input type="text" class="col-md-12" placeholder="材料名/多材料名/品牌名/多品牌名(使用英文逗号分隔)" id="txtTreeSearch" title="全局检索框:支持三种检索方式①单材料名称检索②多材料名称检索(注:请使用逗号分隔材料名称)③单品牌名称检索">
                        <i class="uiText-searchIcon bgIcon" id="txtTreeSearchBtn" @click="sou()"></i>
                    </div>
                    <select class="fl mr5" id="changeLevel" @change="changelevel()">
                        <option value="0">档次过滤</option>
                        <option value="1">A档</option>
                        <option value="2">B档</option>
                        <option value="4">C档</option>
                    </select>
                    <span class="fl lh28">共计{{qrcodeListTotal}}条数据</span>
                    <div class="fr">
                        <a href="${basePath}/mms_download-web/matreial/list/label_print/software_course" class="uiBtn-normal uiBtn-blue fl mr10">打印软件与教程下载</a>
                    </div>
                </div>
                <%--打印标题--%>
                <table class="uiTable tab">
                    <tbody>
                        <tr id="matQrCodeTr" class="tr_changerColor">
                            <th width="6%"><label class="uiRadio12"><input type="checkbox" class="uiRadio12-input InpCheckAll">全选</label>
                            </th>
                            <th width="9%">材料名称</th>
                            <th width="9%">材料编号</th>
                            <th width="5%">类别</th>
                            <th width="9%">品牌</th>
                            <th width="9%">品牌拼音</th>
                            <th width="14%">规格</th>
                            <th width="23%">材料平台地址</th>
                            <th width="5%">报价</th>
                            <th width="5%">单位</th>
                            <th>操作</th>
                        </tr>
                    </tbody>
                </table>
                <%--打印列表--%>
                <div class="tab_list">
                    <table class="uiTable" id="list">
                        <tbody>
                        <tr class="tr_changerColor" v-for="(qrcodeList,index ) in qrcodeLists"
                            :data-matID="qrcodeList.matID">
                            <td width="6%"><input type="checkbox" class="InpCheck"></td>
                            <td width="9%">{{qrcodeList.matName}}</td>
                            <td width="9%">{{qrcodeList.matCode}}</td>
                            <td width="5%">{{qrcodeList.matLevel}}</td>
                            <td width="9%">{{qrcodeList.matBrandName}}</td>
                            <td width="9%">{{qrcodeList.matBrandNamePinYin}}</td>
                            <td width="14%">{{qrcodeList.matSpecName}}</td>
                            <td width="23%">{{qrcodeList.matMallUrl}}</td>
                            <td width="5%">{{qrcodeList.matQuotedPrice}}</td>
                            <td width="5%">{{qrcodeList.matUnit}}</td>
                            <td><input type="button" value="导出" class="uiBtn-normal uiBtn-blue exportBtn"
                                       @click="exportSingle($event)"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <%--批量导出栏--%>
                <div class=" clearfix pa10 fixedFott " data-type="2">
                    <input type="button" value="批量导出选中项" class="uiBtn-normal uiBtn-blue fr ml10" @click="exportgroup($event)">
                </div>

            </div>
        </div>
    </div>
</body>
    <script src="${ctxStatic}/js/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/layer.js?${verStatic}"></script>
    <script type="text/javascript" src="${ctxStatic}/js/rxuedv2.0.min.js?${verStatic}"></script>
    <script type="text/javascript" src="${ctxStatic}/js/frame.min.js?${verStatic}"></script>
    <!--引入vue2.0JS-->
    <script src="${ctxStatic}/js/layer.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/vue.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/vue/mms/commons/filters.js?${verStatic}" type="text/javascript" charset="utf-8"/>
    <script src="${ctxStatic}/Scripts/My97DatePicker/WdatePicker.js?${verStatic}"></script>
    <%-- 子库列表JS --%>
    <script src="${ctxStatic}/js/sublibrary/list.js?${verStatic}"></script>
</html>
<script>
    //全选
    $(document).on("click",".InpCheckAll",function(){
        $(this).prop("checked") == true ? $(".InpCheck").prop("checked", true) : $(".InpCheck").prop("checked", false)
    });

    $(function () {
        heights();
        rxued.table.LChangeapart($(".tab_list tr"), "#f9f9f9", "#fff");
        rxued.table.hoverChage($(".tab_list tr"), "#eaf4fe");
    });

    //表格高度计算
    function heights() {
        $(".tab_list").each(function(){
            if($(this).closest(".reporttabCon").hasClass("reporttabConOne"))
            {
                var theight = $("body").height() - $(".time_op").outerHeight(true) - $(this).closest(".reporttabCon").find(".tab").outerHeight(true) - $(this).closest(".reporttabCon").find(".reporttabSou").outerHeight(true)-10;
            }else{
                var theight = $("body").height() -  $(".time_op").outerHeight(true) - $(this).closest(".reporttabCon").find(".tab").outerHeight(true) - $(this).closest(".reporttabCon").find(".reporttabSou").outerHeight(true) - 60;
            }
            $(this).height(theight).slimScroll({
                height: theight,
                borderRadius: "0px"
            });
            $(this).parents(".slimScrollDiv").height(theight);
        });
        $(window).resize(function () {
            $(".tab_list").each(function(){
                if($(this).hasClass("reporttabConOne"))
                {
                    var theight = $("body").height() -  $(".time_op").outerHeight(true) - $(this).closest(".reporttabCon").find(".tab").outerHeight(true) - $(this).closest(".reporttabCon").find(".reporttabSou").outerHeight(true)-10;
                }else{
                    var theight = $("body").height() - $(".time_op").outerHeight(true) - $(this).closest(".reporttabCon").find(".tab").outerHeight(true) - $(this).closest(".reporttabCon").find(".reporttabSou").outerHeight(true) - 60;
                }
                $(this).height(theight).slimScroll({
                    height: theight,
                    borderRadius: "0px"
                });
                $(this).parents(".slimScrollDiv").height(theight);
            });
        });
    }


   var matListVUE = new Vue({
        el: '#main_box',
        data: {
            qrcodeLists: [],
            qrcodeListTotal: 0,
            treeOneDatas: [],
            treeTwoDatas: [],
            treeThreeDatas: [],
            treeFourDatas: {},
            endIndex: 10
        },
       created: function () {
           this.showList(); // 默认展示圈中打印材料列表
           this.treelist(); // 科目树集 给一级分类绑定数据
       },
        methods: {
            // 获取二维码打印列表
            getQRCodePrintRes: function (obj) {
                var _this = this;
                $.ajax({
                    url: "${basePath}/sublibrary-api/list/qrcode_print/serarch",
                    type: "post",
                    dataType: "json",
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (data) {
                        _this.qrcodeLists = data.body.qrCodePrintLists;
                        _this.qrcodeListTotal = data.body.qrCodePrintListSize;
                        heights();
                        _this.$nextTick(function () {
                            //点击搜索框置空一二三四级分类
                            $("#txtTreeSearch").keydown(function (e) {
                                if (e.keyCode == 13) {
                                    _this.sou();
                                }
                            });
                            $(".InpCheck").click(function (e) {
                                $(".InpCheck").length == $(".InpCheck").filter(":checked").length ? $(".InpCheckAll").prop("checked", true) : $(".InpCheckAll").prop("checked", false);
                                e.stopPropagation()
                            });
                        });
                    }
                });
            },
            // 展示圈中的
            showList: function () {//圈
                var _this = this;
                $(".daily_title li").addClass("active");
                $("#ddlTreeOne").val("0");
                //清空搜索框
                $("#txtTreeSearch").val("");
                $("#changeLevel").val("0");
                _this.treeTwoDatas = [];
                _this.treeThreeDatas = [];
                _this.treeFourDatas = [];
                var obj = {};
                obj.cityID = $("#hidCityID", parent.document).val();
                obj.pageType = 9;
                this.getQRCodePrintRes(obj);
            },
            // 默认展示一级:科目树检索相关
            treelist: function () {//给一级分类绑定数据
                var _this = this;
                var obj = {};
                obj.treeParentID = "";
                $.get("${basePath}/common-api/tree_names", obj, function (data) {
                    _this.treeOneDatas = data.body;

                });
            },
            // 一级分类检索
            treeOne: function () {//给二级分类绑定数据
                var _this = this;
                var creeTreeID = $("#ddlTreeOne").val();
                var obj = {};
                obj.treeParentID = creeTreeID;
                $(".daily_title li").removeClass("active");
                $.get("${basePath}/common-api/tree_names", obj, function (data) {
                    _this.treeTwoDatas = data.body;
                    $("#ddlTreeTwo").val("0");
                    _this.treeThreeDatas = [];
                    _this.treeFourDatas = [];
                });
                //清空checbox
                $(".InpCheck,.InpCheckAll").prop("checked", "");
                //请求数据
                var obj2 = {};
                obj2.cityID = $("#hidCityID", parent.document).val();
                obj2.treeOneID = creeTreeID;
                _this.getQRCodePrintRes(obj2);
                $("#txtTreeSearch").val("");
                $("#changeLevel").val("0");
            },
            // 二级分类检索
            treeTwo: function () {//给三级分类绑定数据
                var _this = this;
                var creeTreeID = $("#ddlTreeTwo").val();
                var obj = {};
                obj.treeParentID = creeTreeID;
                $(".daily_title li").removeClass("active");
                $.get("${basePath}/common-api/tree_names", obj, function (data) {

                    _this.treeThreeDatas = data.body;
                    $("#ddlTreeThree").val("0");
                    _this.treeFourDatas = [];
                });
                //清空checbox
                $(".InpCheck,.InpCheckAll").prop("checked", "");
                //请求数据
                var obj2 = {};
                obj2.cityID = $("#hidCityID", parent.document).val();
                obj2.treeOneID = $("#ddlTreeOne").val();
                obj2.treeTwoID = creeTreeID;
                _this.getQRCodePrintRes(obj2);
                $("#txtTreeSearch").val("");
                $("#changeLevel").val("0");
            },
            // 三级分类检索
            treeThree: function () {//给四级分类绑定数据
                var _this = this;
                var creeTreeID = $("#ddlTreeThree").val();
                var obj = {};
                obj.treeParentID = creeTreeID;
                $(".daily_title li").removeClass("active");
                $.get("${basePath}/common-api/tree_names", obj, function (data) {
                    _this.treeFourDatas = data.body;
                    $("#ddlTreeFour").val("0");
                });
                //清空checbox
                $(".InpCheck,.InpCheckAll").prop("checked", "");
                //请求数据
                var obj2 = {};
                obj2.cityID = $("#hidCityID", parent.document).val();
                obj2.treeOneID = $("#ddlTreeOne").val();
                obj2.treeTwoID = $("#ddlTreeTwo").val();
                obj2.treeThreeID = creeTreeID;
                _this.getQRCodePrintRes(obj2);
                $("#txtTreeSearch").val("");
                $("#changeLevel").val("0");
            },
            // 四级分类检索
            treeFour: function () {
                var _this = this;
                //清空checbox
                $(".InpCheck,.InpCheckAll").prop("checked", "");
                //请求数据
                var obj2 = {};
                obj2.cityID = $("#hidCityID", parent.document).val();
                obj2.treeOneID = $("#ddlTreeOne").val();
                obj2.treeTwoID = $("#ddlTreeTwo").val();
                obj2.treeThreeID = $("#ddlTreeThree").val();
                obj2.treeFourID = $("#ddlTreeFour").val();
                _this.getQRCodePrintRes(obj2);
                $("#txtTreeSearch").val("");
                $("#changeLevel").val("0");
            },
            // 最终基础信息过滤
            changelevel: function () {
                //档次过滤
                var _this = this;
                var ddlTreeOneValue = $("#ddlTreeOne").val();
                var ddlTreeTwoValue = $("#ddlTreeTwo").val();
                var ddlTreeThreeValue = $("#ddlTreeThree").val();
                var ddlTreeFourValue = $("#ddlTreeFour").val();
                var obj = {};
                obj.cityID = $("#hidCityID", parent.document).val();
                if ($("#changeLevel").val() != "0") {
                    obj.levelFlag = $("#changeLevel").val();
                }

                if ($(".daily_title li").hasClass("active")) {
                    obj.pageType = 9;
                    _this.getQRCodePrintRes(obj);
                }
                else if (ddlTreeOneValue != "0" && ddlTreeTwoValue == "0") {
                    obj.treeOneID = $("#ddlTreeOne").val();
                    _this.getQRCodePrintRes(obj);
                }
                else if (ddlTreeOneValue != "0" && ddlTreeTwoValue != "0" && ddlTreeThreeValue == "0") {
                    obj.treeOneID = $("#ddlTreeOne").val();
                    obj.treeTwoID = $("#ddlTreeTwo").val();
                    _this.getQRCodePrintRes(obj);
                }
                else if (ddlTreeOneValue != "0" && ddlTreeTwoValue != "0" && ddlTreeThreeValue != "0" && ddlTreeFourValue == "0") {
                    obj.treeOneID = $("#ddlTreeOne").val();
                    obj.treeTwoID = $("#ddlTreeTwo").val();
                    obj.treeThreeID = $("#ddlTreeThree").val();
                    _this.getQRCodePrintRes(obj);
                }
                else if (ddlTreeOneValue != "0" && ddlTreeTwoValue != "0" && ddlTreeThreeValue != "0" && ddlTreeFourValue != "0") {
                    obj.treeOneID = $("#ddlTreeOne").val();
                    obj.treeTwoID = $("#ddlTreeTwo").val();
                    obj.treeThreeID = $("#ddlTreeThree").val();
                    obj.treeFourID = $("#ddlTreeFour").val();
                    _this.getQRCodePrintRes(obj);
                }
                else if ($.trim($("#txtTreeSearch").val()) != "") {
                    obj.matName = $.trim($("#txtTreeSearch").val());
                    _this.getQRCodePrintRes(obj);
                }
            },
            // 加载更多列表
            loadMoreList: function () {
                this.endIndex += 10;
            },
            sou: function () {
                if ($.trim($("#txtTreeSearch").val()) != "") {
                    var _this = this;
                    $(".daily_title li").removeClass("active");
                    $("#ddlTreeOne").val("0");
                    $("#changeLevel").val("0");
                    _this.treeTwoDatas = [];
                    _this.treeThreeDatas = [];
                    _this.treeFourDatas = [];
                    //请求数据
                    var obj2 = {};
                    obj2.cityID = $("#hidCityID", parent.document).val();
                    obj2.matName = $.trim($("#txtTreeSearch").val());
                    _this.getQRCodePrintRes(obj2);
                }
            },

            // 导出单个事件
            exportSingle: function (element) {
                var matIDs = $(element.currentTarget).parents("tr").attr("data-matid");
                var cityID = $("#hidCityID", parent.document).val();
                window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&&matIDs=" + matIDs;
            },
            // 批量导出事件
            exportgroup: function (element) {
                var ocheckedlen = $(".InpCheck:checked").length;
                if (ocheckedlen == 0) {
                    alert("请至少选中一款材料");
                    return;
                }
                var matIDs = "";
                var cityID = $("#hidCityID", parent.document).val();
                var ddlTreeOneValue = $("#ddlTreeOne").val();
                var ddlTreeTwoValue = $("#ddlTreeTwo").val();
                var ddlTreeThreeValue = $("#ddlTreeThree").val();
                var ddlTreeFourValue = $("#ddlTreeFour").val();
                var changeLevel = $("#changeLevel").val();

                if ($(".InpCheckAll").prop("checked") != true) {

                    for (var i = 0; i < $("#list tr").length; i++) {
                        if ($("#list tr").eq(i).find(".InpCheck").prop("checked") == true) {
                            matIDs += $("#list tr").eq(i).attr("data-matid") + ",";
                        }
                    }
                    //判断是否是档次过滤项
                    if (changeLevel != 0) {
                        window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&matIDs=" + matIDs + "&levelFlag=" + changeLevel;
                    } else {
                        window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&matIDs=" + matIDs;
                    }
                    return;
                }
                else//全选
                {
                    if ($(".daily_title li").hasClass("active"))//情况一如果是圈中的
                    {
                        //判断是否是档次过滤项
                        if (changeLevel != 0) {
                            window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&pageType=9" + "&levelFlag=" + changeLevel;
                        } else {
                            window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&pageType=9";
                        }
                        return;
                    }
                    else if ($.trim($("#txtTreeSearch").val())) {
                        var matName = $.trim($("#txtTreeSearch").val());
                        //判断是否是档次过滤项
                        if (changeLevel != 0) {
                            window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&matName=" + matName + "&levelFlag=" + changeLevel;
                        } else {
                            window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&matName=" + matName;
                        }

                        return;
                    }
                    else {//情况二如果是一二三四级选中
                        if (ddlTreeOneValue != "0" && ddlTreeTwoValue == "0") {
                            //判断是否是档次过滤项
                            if (changeLevel != 0) {
                                window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&treeOneID=" + ddlTreeOneValue + "&levelFlag=" + changeLevel;
                            } else {
                                window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&treeOneID=" + ddlTreeOneValue;
                            }

                            return;
                        }
                        else if (ddlTreeOneValue != "0" && ddlTreeTwoValue != "0" && ddlTreeThreeValue == "0") {
                            //判断是否是档次过滤项
                            if (changeLevel != 0) {
                                window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&treeOneID=" + ddlTreeOneValue + "&treeTwoID=" + ddlTreeTwoValue + "&levelFlag=" + changeLevel;
                            } else {
                                window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&treeOneID=" + ddlTreeOneValue + "&treeTwoID=" + ddlTreeTwoValue;
                            }

                            return;
                        }
                        else if (ddlTreeOneValue != "0" && ddlTreeTwoValue != "0" && ddlTreeThreeValue != "0" && ddlTreeFourValue == "0") {
                            //判断是否是档次过滤项
                            if (changeLevel != 0) {
                                window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&treeOneID=" + ddlTreeOneValue + "&treeTwoID=" + ddlTreeTwoValue + "&treeThreeID=" + ddlTreeThreeValue + "&levelFlag=" + changeLevel;
                            } else {
                                window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&treeOneID=" + ddlTreeOneValue + "&treeTwoID=" + ddlTreeTwoValue + "&treeThreeID=" + ddlTreeThreeValue;
                            }

                            return;
                        } else {
                            //判断是否是档次过滤项
                            if (changeLevel != 0) {
                                window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&treeOneID=" + ddlTreeOneValue + "&treeTwoID=" + ddlTreeTwoValue + "&treeThreeID=" + ddlTreeThreeValue + "&treeFourID=" + ddlTreeFourValue + "&levelFlag=" + changeLevel;
                            } else {
                                window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&treeOneID=" + ddlTreeOneValue + "&treeTwoID=" + ddlTreeTwoValue + "&treeThreeID=" + ddlTreeThreeValue + "&treeFourID=" + ddlTreeFourValue;
                            }
                            return;
                        }
                    }
                }
            }
        }
    });


</script>
<script type="text/javascript">
    $(function () {
        $(".content_p .close").click(function () {
            $(".threeList").empty();
        })
    })
</script>
