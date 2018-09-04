<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<script type="text/javascript">const basePath = '${basePath}',ctxStatic='${ctxStatic}',gcApiSite = '${gcApiSite}',picUploadUrl = '${picUploadUrl}'</script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>工程经理.材料.列表</title>
    <link rel="stylesheet" href="${ctxStatic}/css/rxui1.0.2.min.css?${verStatic}"/>
    <link rel="stylesheet" href="${ctxStatic}/css/frame.min.css?${verStatic}"/>
    <link rel="stylesheet" href="${ctxStatic}/js/skin/layer.css?${verStatic}" type="text/css"/>
    <!--三栏布局样式表 -->
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

    <div id="emMatListVue">
        <div class="matile">
            <div class="report_listCon" id="report_listCon" style="height:100%">

                <div class="time_op" style="padding: 60px 10px 0;">
                    <ul class="maul clearfix">
                        <li  title="材料列表" class="qrcodelist active" data-index="0">材料列表</li>
                    </ul>
                </div>

                <div class="reporttabCon reporttabConOne" style="padding:0 10px;">
                    <div class="clearfix mb10 reporttabSou"><div class="fr"></div></div>
                    <table class="uiTable tab">
                        <tbody>
                            <%-- 表头 --%>
                            <tr class="tr_changerColor ">
                                <th width="5%">
                                    序号
                                    <span class="NameSspan"  @click="sortBySortField('rowNo')"><a href="javascript:" class="up dis_block"></a><a href="javascript:" class="down dis_block"></a> </span>
                                </th>
                                <th width="12%" @click="sortBySortField('matName')">材料名称<span class="sort" title="排序"></span></th>
                                <th width="5%"  @click="sortBySortField('levelCode')">档次</th>
                                <th width="12%" @click="sortBySortField('brandName')">品牌</th>
                                <th width="12%" @click="sortBySortField('brandType')">型号</th>
                                <th width="6%">
                                    LOGO
                                    <span class="NameSspan"  @click="sortBySortField('logoPicCount')"><a href="javascript:" class="up dis_block"></a><a href="javascript:" class="down dis_block"></a> </span>
                                </th>
                                <th width="6%">
                                    主图
                                    <span class="NameSspan"  @click="sortBySortField('mainPicCount')"><a href="javascript:" class="up dis_block"></a><a href="javascript:" class="down dis_block"></a> </span>
                                </th>
                                <th width="6%">
                                    型号照片
                                    <span class="NameSspan"  @click="sortBySortField('typePicCount')"><a href="javascript:" class="up dis_block"></a><a href="javascript:" class="down dis_block"></a> </span>
                                </th>
                                <th width="6%" @click="sortBySortField('mdCount')">入驻</th>
                                <th width="8%" @click="sortBySortField('costPrice')">成本价</th>
                                <th width="8%" @click="sortBySortField('quotesPrice')">报价</th>
                                <th width=""   @click="sortBySortField('mdMaxPrice')">交易价</th>
                            </tr>

                            <%--合计--%>
                            <tr id="tr_MatListAddUpTo" class="">
                                <td width="">合计</td>
                                <td width=""><span title="材料总数" class="matTotalCont">{{matListSize}}</span></td>
                                <td width="">-</td>
                                <td width="">-</td>
                                <td width="">-</td>

                                <%--LOGO--%>
                                <td width=""><span title="LOGO-完" class="cGreen logoPicCountY">-</span>&ensp;/&ensp;<span id="" title="LOGO-未" class="cRed logoPicCountN">-</span></td>
                                <%--主图--%>
                                <td width=""><span title="主图-完" class="cGreen mainPicCountY">-</span>&ensp;/&ensp;<span title="主图-未" class="cRed mainPicCountN">-</span></td>
                                <%--型号照片--%>
                                <td width=""><span title="型号照片-完" class="cGreen typePicCountY">-</span>&ensp;/&ensp;<span title="型号照片-未" class="cRed typePicCountN">-</span></td>

                                <td width="">-</td>
                                <td width="">-</td>
                                <td width="">-</td>
                                <td width="">-</td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="tab_list">
                        <table class="uiTable">
                            <tbody>
                                <%-- 列表 --%>
                                <tr v-for="(mat,index) in matList" class="tr_matlist"
                                    :data-logo="mat.logoPicCount"
                                    :data-main="mat.mainPicCount"
                                    :data-type="mat.typePicCount"
                                >
                                    <td width="5%">{{mat.rowNo}}</td>
                                    <td width="12%">{{mat.matName}}</td>
                                    <td width="5%">{{mat.levelCode}}</td>
                                    <td width="12%">{{mat.brandName}}</td>
                                    <td width="12%">{{mat.brandType}}</td>
                                    <td width="6%" :class="mat.logoPicCount == 0 ? 'cRed' : 'cGreen'">{{mat.logoPicCount == 0 ? '未' : '完'}}</td>
                                    <td width="6%" :class="mat.mainPicCount == 0 ? 'cRed' : 'cGreen'">{{mat.mainPicCount == 0 ? '未' : '完'}}</td>
                                    <td width="6%" :class="mat.typePicCount == 0 ? 'cRed' : 'cGreen'">{{mat.typePicCount == 0 ? '未' : '完'}}</td>
                                    <td width="6%" :class="mat.mdCount == 0 ? 'cRed' : ''">{{mat.mdCount == 0 ? '无' : mat.mdCount }}</td>
                                    <td width="8%" :class="mat.costPrice == 0 ? 'cRed' : ''">{{ mat.costPrice| holdTwoDecimal}}</td>
                                    <td width="8%" :class="mat.quotesPrice == 0 ? 'cRed' : ''">{{mat.quotesPrice| holdTwoDecimal}}</td>
                                    <td width=""   :class="mat.mdCount == 0 ? 'cRed' : ''">
                                        {{mat.mdMinPrice| holdTwoDecimal }}&ensp;~&ensp;{{ mat.mdMaxPrice| holdTwoDecimal }}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

    <script src="${ctxStatic}/js/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
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

    $(function () {
        $(document).on("click",".daily_title_liebiao li",function(){
            $(this).toggleClass("active")
        });
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
                var theight = $("body").height() -  $(".time_op").outerHeight(true) - $(this).closest(".reporttabCon").find(".tab").outerHeight(true) - $(this).closest(".reporttabCon").find(".reporttabSou").outerHeight(true) - 50;
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
                    var theight = $("body").height() - $(".time_op").outerHeight(true) - $(this).closest(".reporttabCon").find(".tab").outerHeight(true) - $(this).closest(".reporttabCon").find(".reporttabSou").outerHeight(true) - 50;
                }
                $(this).height(theight).slimScroll({
                    height: theight,
                    borderRadius: "0px"
                });
                $(this).parents(".slimScrollDiv").height(theight);
            });
        });
    }

    // 材料列表 合计列逻辑脚本
    function matListAddUpTo(){

        // LOGO
        $("#tr_MatListAddUpTo .logoPicCountY").html($(".tr_matlist[data-logo != '0']").length);
        $("#tr_MatListAddUpTo .logoPicCountN").html($(".tr_matlist[data-logo = '0']").length);
        // 主图;
        $("#tr_MatListAddUpTo .mainPicCountY").html($(".tr_matlist[data-main != '0']").length);
        $("#tr_MatListAddUpTo .mainPicCountN").html($(".tr_matlist[data-main = '0']").length);
        // 型号图;
        $("#tr_MatListAddUpTo .typePicCountY").html($(".tr_matlist[data-type != '0']").length);
        $("#tr_MatListAddUpTo .typePicCountN").html($(".tr_matlist[data-type = '0']").length);

    }


   // 工程经理.材料列表VUE实例
   var emMatListVue = new Vue({
        el: '#emMatListVue',
        data: {
            matList:[],
            matListSize:0,
            sortOrder: 0
        },
       created: function () {
           this.buildMatListData();
       },
       methods: {
            // 获取材料列表
            buildMatListData: function () {
                var _this = this;
                var cityID = $("#hidCityID", parent.document).val();
                $.ajax({
                    url: basePath + "/em/material-api/list/material",
                    type: "GET",
                    dataType: "json",
                    contentType: 'application/json',
                    data: {cityID},
                    success: function (data) {
                        _this.matList = data.body;
                        if (data.body != null) _this.matListSize = data.body.length;
                        setTimeout(function(){matListAddUpTo();})
                    }
                });
            },
            // 列表排序 通过排序字段
            sortBySortField : function(sortField){
                var _this = this;
                var matList = _this.matList;
                // 判断降序还是升序 0降序(默认) 1升序
                if (_this.sortOrder){
                    // 升序
                    matList = jsonSort(matList,sortField,false);
                    // 设值下次降序
                    _this.sortOrder = 0;
                } else {
                    // 降序
                    matList = jsonSort(matList,sortField,true);
                    // 恢复默认值 升序
                    _this.sortOrder = 1;
                }
                _this.matList = matList;
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

            }
       }
   });
</script>
