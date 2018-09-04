<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>集团材料</title>
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

<div id="main_box">

    <div class="matile">
        <div class="report_listCon" id="report_listCon" style="height:100%">
            <div class="time_op" style="padding: 60px 10px 0;">
                <%--地区 城市--%>
                <select class="fl  mr5" id="selLoopCity" @change="changeMatLoopList()">
                    <option value="0" selected>全国</option>
                    <option v-for="(citySelect,index) in citySelects" :value="citySelect.cityID">{{citySelect.cityName}}</option>
                </select>


                <ul class="maul clearfix">
                    <li title="材料列表" class="qrcodelist active" data-index="0">材料列表</li>
                    <li @click="showList()" title="标签打印(暂未同步城市选择列表,根据起始页地区列表选择)针对已入库,报价不为0材料" class="qrcodelist " data-index="1" >标签打印</li>
                </ul>
            </div>
            <div class="reporttabCon reporttabConOne" style="padding:0 10px;">
            <div class="clearfix mb10 reporttabSou">
            <div class="fr"></div>
        </div>

        <table class="uiTable tab">
            <tbody>
                <%-- 全国列表 --%>
                <tr class="tr_changerColor qg_matLoopList">
                    <th width="5%">编号</th>
                    <th width="10%">地区</th>
                    <th width="10%">种类</th>
                    <th width="10%">总状</th>
                    <th width="10%">平台</th>
                    <th width="10%">A档</th>
                    <th width="10%">B档</th>
                    <th width="10%">C档</th>
                    <th>处理状态</th>
                </tr>

                <%-- 全国总计 --%>
                <tr id="qgMatLoopListTotal" class="qg_matLoopList">
                    <td width="5%">合计</td>
                    <%--地区--%>
                    <td width="10%">
                        <span title="城市总数" class="totalCities">-</span>&ensp;
                    </td>
                    <%--种类--%>
                    <td width="10%">
                        <span title="种类数量" class="totalNm">{{qgLoopMatListSUM.totalNm}}</span>&ensp;/&ensp;
                        <span title="圈中数量" class="cGreen quanNm">{{qgLoopMatListSUM.quanNm}}</span>
                    </td>
                    <%--总状态--%>
                    <td width="10%">
                        <span title="总回路状态-已完成" class="cGreen loopTotalNmY">{{qgLoopMatListSUM.loopTotalNmY}}</span>&ensp;/&ensp;
                        <span title="总回路状态-未完成" class="cRed loopTotalNmN">{{qgLoopMatListSUM.loopTotalNmN}}</span>
                    </td>
                    <%--平台--%>
                    <td width="10%">
                        <span title="平台-完" class="cGreen loopPlatformNmY">{{qgLoopMatListSUM.loopPlatformNmY}}</span>&ensp;/&ensp;
                        <span title="平台-未" class="cRed loopPlatformNmN">{{qgLoopMatListSUM.loopPlatformNmN}}</span>
                    </td>
                    <%--A档--%>
                    <td width="10%">
                        <span title="A档-完" class="cGreen loopAlevelNmY">{{qgLoopMatListSUM.loopAlevelNmY}}</span>&ensp;/&ensp;
                        <span title="A档-未" class="cRed loopAlevelNmN">{{qgLoopMatListSUM.loopAlevelNmN}}</span>
                    </td>
                    <%--B档--%>
                    <td width="10%">
                        <span title="B档-完" class="cGreen loopBlevelNmY">{{qgLoopMatListSUM.loopBlevelNmY}}</span>&ensp;/&ensp;
                        <span title="B档-未" class="cRed loopBlevelNmN">{{qgLoopMatListSUM.loopBlevelNmN}}</span>
                    </td>
                    <%--C档--%>
                    <td width="10%">
                        <span title="C档-完" class="cGreen loopClevelNmY">{{qgLoopMatListSUM.loopClevelNmY}}</span>&ensp;/&ensp;
                        <span title="C档-未" class="cRed loopClevelNmN">{{qgLoopMatListSUM.loopClevelNmN}}</span>
                    </td>
                    <%--处理状态--%>
                    <td >
                        <span title="未处理" class="cPurple handleTypeNot">{{qgLoopMatListSUM.handleTypeNot}}</span>&ensp;/&ensp;
                        <span title="处理-正常" class="cGreen handleTypeZ">{{qgLoopMatListSUM.handleTypeZ}}</span>&ensp;/&ensp;
                        <span title="处理-异常" class="cOrange handleTypeY">{{qgLoopMatListSUM.handleTypeY}}</span>&ensp;/&ensp;
                        <span title="处理-问题" class="cRed handleTypeW">{{qgLoopMatListSUM.handleTypeW}}</span>
                    </td>
                </tr>


                 <%--地区列表 --%>
                <tr class="tr_changerColor dq_matLoopList" >
                    <th width="5%">序号<span class="NameSspan"  @click="sortBySortField('rowNum')"><a href="javascript:" class="up dis_block"></a><a href="javascript:" class="down dis_block"></a> </span></th>
                    <th width="15%">材料名称<span class="NameSspan"  @click="sortBySortField('tciFlag')"><a href="javascript:" class="up dis_block"></a><a href="javascript:" class="down dis_block"></a> </span></th>
                    <th width="10%">总状态<span class="NameSspan"  @click="sortBySortField('loopStatus')"><a href="javascript:" class="up dis_block"></a><a href="javascript:" class="down dis_block"></a> </span></th>
                    <th width="10%">平台<span class="NameSspan"  @click="sortBySortField('platformLoopStatus')"><a href="javascript:" class="up dis_block"></a><a href="javascript:" class="down dis_block"></a> </span></th>
                    <th width="10%">A档<span class="NameSspan"  @click="sortBySortField('aBrandNotCount')"><a href="javascript:" class="up dis_block"></a><a href="javascript:" class="down dis_block"></a> </span></th>
                    <th width="10%">B档<span class="NameSspan"  @click="sortBySortField('bBrandNotCount')"><a href="javascript:" class="up dis_block"></a><a href="javascript:" class="down dis_block"></a> </span></th>
                    <th width="10%">C档<span class="NameSspan" @click="sortBySortField('cBrandNotCount')"><a href="javascript:" class="up dis_block"></a><a href="javascript:" class="down dis_block"></a> </span></th>
                    <th width="15%">处理状态</th>
                    <th>处理日期</th>
                </tr>
                 <%--地区列表总计 --%>
                <tr id="matLoopListTotal" class="dq_matLoopList">
                    <td width="5%">合计</td>
                    <%--名称--%>
                    <td width="15%">
                        <span title="合计数量" class="totalNm">-</span>&ensp;/&ensp;
                        <span title="圈中数量" class="cGreen quanNm">-</span>
                    </td>
                    <%--总状态--%>
                    <td width="10%">
                        <span title="总回路状态-已完成" class="cGreen loopTotalNmY">-</span>&ensp;/&ensp;
                        <span title="总回路状态-未完成" class="cRed loopTotalNmN">-</span>
                    </td>
                    <%--平台--%>
                    <td width="10%">
                        <span title="平台-完" class="cGreen loopPlatformNmY">-</span>&ensp;/&ensp;
                        <span title="平台-未" class="cRed loopPlatformNmN">-</span>
                    </td>
                    <%--A档--%>
                    <td width="10%">
                        <span title="A档-完" class="cGreen loopAlevelNmY">-</span>&ensp;/&ensp;
                        <span title="A档-未" class="cRed loopAlevelNmN">-</span>
                    </td>
                    <%--B档--%>
                    <td width="10%">
                        <span title="B档-完" class="cGreen loopBlevelNmY">-</span>&ensp;/&ensp;
                        <span title="B档-未" class="cRed loopBlevelNmN">-</span>
                    </td>
                    <%--C档--%>
                    <td width="10%">
                        <span title="C档-完" class="cGreen loopClevelNmY">-</span>&ensp;/&ensp;
                        <span title="C档-未" class="cRed loopClevelNmN">-</span>
                    </td>
                    <%--处理状态--%>
                    <td width="15%">
                        <span title="未处理" class="cPurple handleTypeNot">-</span>&ensp;/&ensp;
                        <span title="处理-正常" class="cGreen handleTypeZ">-</span>&ensp;/&ensp;
                        <span title="处理-异常" class="cOrange handleTypeY">-</span>&ensp;/&ensp;
                        <span title="处理-问题" class="cRed handleTypeW">-</span>
                    </td>
                    <%--处理日期--%>
                    <td>-</td>
                </tr>
            </tbody>
        </table>
        <div class="tab_list">
            <table class="uiTable">
                <tbody>
                    <%-- 全国列表 --%>
                    <tr v-for="(matLoopList,index) in qgMatLoopLists" class="qg_matLoopList">
                        <td width="5%" >{{index + 1}}</td>
                        <td width="10%">{{matLoopList.cityName}}</td>
                        <td width="10%"><span title="种类总数">{{matLoopList.totalNm}}</span>&ensp;/&ensp;<span title="圈中总数" class="cGreen">{{matLoopList.quanNm}}</span></td>
                        <td width="10%"><span title="总状-完" class="cGreen">{{matLoopList.loopTotalNmY}}</span>&ensp;/&ensp;<span title="总状-未" class="cRed">{{matLoopList.totalNm - matLoopList.loopTotalNmY}}</span></td>
                        <td width="10%"><span title="平台-完" class="cGreen">{{matLoopList.loopPlatformNmY}}</span>&ensp;/&ensp;<span title="平台-未" class="cRed">{{matLoopList.loopPlatformNmN}}</span></td>
                        <td width="10%"><span title="A档-完" class="cGreen">{{matLoopList.loopAlevelNmY}}</span>&ensp;/&ensp;<span title="A档-未" class="cRed">{{matLoopList.loopAlevelNmN}}</span></td>
                        <td width="10%"><span title="B档-完" class="cGreen">{{matLoopList.loopBlevelNmY}}</span>&ensp;/&ensp;<span title="A档-未" class="cRed">{{matLoopList.loopBlevelNmN}}</span></td>
                        <td width="10%"><span title="C档-完" class="cGreen">{{matLoopList.loopClevelNmY}}</span>&ensp;/&ensp;<span title="A档-未" class="cRed">{{matLoopList.loopClevelNmN}}</span></td>
                        <td >
                            <span title="未处理" class="cPurple">{{matLoopList.handleTypeNot}}</span>&ensp;/&ensp;
                            <span title="处理-正常" class="cGreen">{{matLoopList.handleTypeZ}}</span>&ensp;/&ensp;
                            <span title="处理-异常" class="cOrange">{{matLoopList.handleTypeY}}</span>&ensp;/&ensp;
                            <span title="处理-问题" class="cRed">{{matLoopList.handleTypeW}}</span>
                        </td>
                    </tr>

                    <%-- 地区列表 --%>
                    <tr v-for="matLoopList in matLoopLists " class="tr_changerColor dq_matLoopList"
                        :loop-total="matLoopList.loopStatus"
                        :loop-platform="matLoopList.platformLoopStatus"
                        :loop-alevel-notnm="matLoopList.aBrandNotCount"
                        :loop-blevel-notnm="matLoopList.bBrandNotCount"
                        :loop-clevel-notnm="matLoopList.cBrandNotCount"
                        :handle-type="matLoopList.visitMark == null ? 0 : matLoopList.visitMark"
                        :quan-flag="matLoopList.tciFlag"
                    >
                        <td width="5%" title="生成策略:汇总所有材料项目中出现次数排序(每月更新一次)">{{matLoopList.rowNum}}</td>
                        <td width="15%":title="matLoopList.treeOneName + ' - ' + matLoopList.treeTwoName + ' - ' + matLoopList.matName + ' - '+ matLoopList.matSpec" :class="matLoopList.tciFlag != 0 ? 'cGreen': ''">{{matLoopList.matName}}</td>
                        <td width="10%" :class="matLoopList.loopStatus == 1 ? 'cGreen' : 'cRed'">{{matLoopList.loopStatus == 1 ? '完' : '未'}}</td>
                        <td width="10%" :class="matLoopList.platformLoopStatus == 1 ? 'cGreen' : 'cRed'">{{matLoopList.platformLoopStatus == 1 ? '完' : '未'}}</td>
                        <td width="10%" :class="matLoopList.aBrandNotCount == 0 ? 'cGreen' : 'cRed'">{{matLoopList.aBrandNotCount == 0 ? '完' : '未'}}</td>
                        <td width="10%" :class="matLoopList.bBrandNotCount == 0 ? 'cGreen' : 'cRed'">{{matLoopList.bBrandNotCount == 0 ? '完' : '未'}}</td>
                        <td width="10%" :class="matLoopList.cBrandNotCount == 0 ? 'cGreen' : 'cRed'">{{matLoopList.cBrandNotCount == 0 ? '完' : '未'}}</td>
                        <td width="15%" :class="{'cGreen':matLoopList.visitMark==1,'cOrange':matLoopList.visitMark==2,'cRed':matLoopList.visitMark==3,'cPurple':matLoopList.visitMark==undefined}" :title="matLoopList.visitContent">{{matLoopList.visitMark | handleTypeChangeStrVue }}</td>
                        <td v-show="null != matLoopList.visitTime">{{matLoopList.visitTime | formatDateTime('yyyy-MM-dd hh:mm') }}</td>
                        <td v-show="null == matLoopList.visitTime">-</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="reporttabCon mtf1 T_started hide" style="padding:0 10px;">
        <div class="clearfix mb10 reporttabSou  ">

            <ul class="fl daily_title">
                <%-- class="title_cur"--%>

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
                <input type="text" class="col-md-12" placeholder="材料名/多材料名/品牌名/多品牌名(使用英文逗号分隔)"
                       id="txtTreeSearch" title="全局检索框:支持三种检索方式①单材料名称检索②多材料名称检索(注:请使用逗号分隔材料名称)③单品牌名称检索">
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
                <a href="${basePath}/mms_download-web/matreial/list/label_print/software_course"
                   class="uiBtn-normal uiBtn-blue fl mr10">打印软件与教程下载</a>

            </div>
        </div>
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
        <div class=" clearfix pa10 fixedFott " data-type="2">
            <input type="button" value="批量导出选中项" class="uiBtn-normal uiBtn-blue fr ml10"
                   @click="exportgroup($event)">
            <%--<input type="button" value="批量导出检索项" class="uiBtn-normal uiBtn-blue fr">--%>
        </div>
    </div>

</div>
</div>
<!--弹出层结束3.28-->

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
    <%--<script src="${ctxStatic}/js/angular.js?${verStatic}" type="text/javascript" ></script>--%>
    <script src="${ctxStatic}/Scripts/My97DatePicker/WdatePicker.js?${verStatic}"></script>

    <%-- 子库列表JS --%>
    <script src="${ctxStatic}/js/sublibrary/list.js?${verStatic}"></script>
</html>
<script>

    // 设置城市名称:区分全国与地区,判断是否切换地区(父级隐藏域是否存在跳转地区标识)
    var $hidToCityFlag = $("#hidToCityFlag", parent.document).val();

    $(function () {

        $(document).on("click",".daily_title_liebiao li",function(){
            $(this).toggleClass("active")
        })

        heights();
        rxued.table.LChangeapart($(".tab_list tr"), "#f9f9f9", "#fff");
        rxued.table.hoverChage($(".tab_list tr"), "#eaf4fe");
        //选项卡切换
        $(document).on("click",".qrcodelist",function(){
            var index=$(this).attr("data-index");
            $(this).addClass("active").siblings().removeClass("active");
            $(".reporttabCon").hide().eq(index).show();
            heights();
        });

        //全选
        $(".InpCheckAll").click(function () {
            $(this).prop("checked") == true ? $(".InpCheck").prop("checked", true) : $(".InpCheck").prop("checked", false)
        });


    })

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


   var matListVUE = new Vue({
        el: '#main_box',
        data: {
            citySelects:[],
            qrcodeLists: [],
            qrcodeListTotal: 0,
            matLoopLists: [],
            matLoopListSize:0, // 材料列表数据长度
            qgMatLoopLists: [],
            qgLoopMatListSUM:{}, // 全国材料回路合计
            treeOneDatas: [],
            treeTwoDatas: [],
            treeThreeDatas: [],
            treeFourDatas: {},
            endIndex: 10,
            // 列表排序相关
            searchData: '',
            sortField: '',
            sortOrder: 0
        },
       created: function () {
           // 页面加载构建数据
           this.checkShowQgOrDqMatLoopList(); // 展示默认材料回路李博啊
           this.treelist(); // 科目树集 给一级分类绑定数据

       },
        methods: {
            // "========================== 材料回路列表相关 =============================="
            /**
             * 检查展示全国或城市材料回路列表
             * @author ljc
             * @param _this VUE实例
             * @param $hidToCityFlag 是否去往城市标识 0.全国 1.城市 (默认0)
             * @createTime 2018-8-6 14:20:50
             */
            checkShowQgOrDqMatLoopList: function () {
                var _this = this;
                var cityID = $("#hidCityID", parent.document).val();
                var obj = {};

                setTimeout(function(){
                    // 城市
                    if ( $hidToCityFlag == 1){
                        obj.cityID = cityID;
                        // 1.隐藏全国 展示 城市
                        $(".qg_matLoopList").hide();
                        $(".dq_matLoopList").show();
                        // 2.加载 城市材料回路列表
                        _this.getMatLoopListRes(obj);
                    }
                    // 全国
                    if ( $hidToCityFlag == 0){
                        cityID = 0;
                        $(".dq_matLoopList").hide();
                        $(".qg_matLoopList").show();
                        _this.getQGMatLoopListRes(obj);
                    }
                    // 计算高度
                    heights();

                    // 加载城市列表
                    _this.loadCitySelect(cityID); // 加载城市选择列表
                })
            },
            // 获取地区材料回路列表
            getMatLoopListRes: function (obj) {
                var _this = this;
                $.ajax({
                    url: "${basePath}/sublibrary-api/list/mat_loop/serarch",
                    type: "post",
                    dataType: "json",
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (data) {
                        _this.matLoopLists = data.body;
                        var matLoopListSize = 0;
                        if (data.body != null)
                            matLoopListSize = data.body.length;
                        _this.matLoopListSize = matLoopListSize;
                        setTimeout(function(){
                            // 设置材料列表总计
                            setMatListLoopTotal();
                        })
                    }
                });
            },
            // 获取全国材料回路列表
            getQGMatLoopListRes: function (obj) {
                var _this = this;
                $.ajax({
                    url: "${basePath}/sublibrary-api/list/mat_loop/serarch",
                    type: "post",
                    dataType: "json",
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (data) {
                        _this.qgMatLoopLists = data.body.qgLoopMatList;
                        _this.qgLoopMatListSUM = data.body.qgLoopMatListSUM;
                    }
                });
            },
            // 搜素 材料回路列表
            materialSou:function(){//材料列表搜索
                var _this = this;
                debugger
                // --- 赋值调用 ---
                var totalFlag = $("#TotalFlag option:selected").val();
                var platformFlag = $("#PlatformFlag option:selected").val();
                var aLevelFlag = $("#ALevelFlag option:selected").val();
                var bLevelFlag = $("#BLevelFlag option:selected").val();
                var cLevelFlag = $("#CLevelFlag option:selected").val();
                var handleTypeFlag = $("#HandleTypeFlag option:selected").val();
                var handleTimeFlag = $("#HandleTimeFlag option:selected").val();

                // --- 组织 材料回路列表检索对象 ---
                var obj={};
                obj.cityID = $("#hidCityID", parent.document).val();
                if($(".daily_title_liebiao li").hasClass("active"))
                    obj.circleFlag = 1;
                if(totalFlag != 0)
                    obj.totalFlag = totalFlag;
                if(platformFlag != 0)
                    obj.platformFlag = platformFlag;
                if(aLevelFlag != 0)
                    obj.aLevelFlag = aLevelFlag;
                if(bLevelFlag != 0)
                    obj.bLevelFlag = bLevelFlag;
                if(cLevelFlag != 0)
                    obj.cLevelFlag = cLevelFlag;
                if(handleTypeFlag != 0)
                    obj.handleTypeFlag = handleTypeFlag;
                if(handleTimeFlag != 0)
                    obj.handleTimeFlag = handleTimeFlag;
                _this.getMatLoopListRes(obj);
            },
            // 子库列表排序 通过排序字段
            sortBySortField : function(sortField){
                var _this = this;
                var matLoopLists = _this.matLoopLists;
                // 判断降序还是升序 0降序(默认) 1升序
                if (_this.sortOrder){
                    // 升序
                    matLoopLists = jsonSort(matLoopLists,sortField,false);
                    // 设值下次降序
                    _this.sortOrder = 0;
                } else {
                    // 降序
                    matLoopLists = jsonSort(matLoopLists,sortField,true);
                    // 恢复默认值 升序
                    _this.sortOrder = 1;
                }
                _this.matLoopLists = matLoopLists;
            },
            // 加载城市选择列表
            loadCitySelect : function(cityID){
                var _this = this;
                $.get("${basePath}/common-api/citys", function(data){
                    _this.citySelects = data.body;
                    setTimeout(function(){
                        // 设置 城市列表默认选中
                        $("#selLoopCity").val(cityID);
                    })
                });
            },
            // 切换城市重新加载 地区材料回路列表
            changeMatLoopList: function(){
                var _this = this;
                // 获取当前选中校验加载全国还是城市
                var cityID = $("#selLoopCity").val();

                var obj = {};
                // 地区
                if ( cityID != 0){
                    obj.cityID = cityID;
                    $(".qg_matLoopList").hide();
                    $(".dq_matLoopList").show();
                    _this.getMatLoopListRes(obj);
                }
                // 全国
                if ( cityID == 0){
                    $(".dq_matLoopList").hide();
                    $(".qg_matLoopList").show();
                    _this.getQGMatLoopListRes(obj);
                }
            },


            // "========================== 标签打印列表相关 =============================="
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
            treelist: function () {//给一级分类绑定数据
                var _this = this;
                var obj = {};
                obj.treeParentID = "";
                $.get("${basePath}/common-api/tree_names", obj, function (data) {
                    _this.treeOneDatas = data.body;

                });
            },
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
            exportSingle: function (element) {//导出单个项
                var matIDs = $(element.currentTarget).parents("tr").attr("data-matid");
                var cityID = $("#hidCityID", parent.document).val();
                window.location.href = "${basePath}/sublibrary-api/list/qrcode_print/export_excel?cityID=" + cityID + "&&matIDs=" + matIDs;
            },
            exportgroup: function (element) {//批量导出
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

        },
        computed: {
            qrcodeListSlice: function () {
                //return this.qrcodeLists.slice(0,this.endIndex);
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
