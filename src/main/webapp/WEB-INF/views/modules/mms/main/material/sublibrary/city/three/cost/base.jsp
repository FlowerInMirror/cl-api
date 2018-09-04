<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<div id="matQuoteThree" v-cloak >
    <div class="divMaterialLoadAlert_topm plr10">
    <h2 class="uiTitle2">
        <i class="uiTitle-icon"></i>
        基础信息
    </h2>
    <div class="materialpicture materialTopNavConm pl5 pr5">
        <div class="SearchCon pt10">
            <!--基础信息 开始-->
            <div class="analyItem">
                <p class="analyItemTit tx-center">基础</p>
                <div class="analyItemCon relative">
                    <p class="fl col-md-4"><span class="cLightGray pr8 justify_span justify_span_w60">一级</span><span>{{matBase.treeOneName}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8 justify_span justify_span_w60">二级</span><span>{{matBase.treeTwoName}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8 justify_span justify_span_w60">名称</span><span>{{matBase.matName | convertOverlengthStrings(matBase.matName,6)}}</span></p>
                    <p class="fl col-md-8"><span class="cLightGray pr8 justify_span justify_span_w60">规格</span><span>{{matBase.matSpec | convertOverlengthStrings(matBase.matSpec,15)}}</span></p>
                    <p class="fl col-md-4">
                        <span class="cLightGray pr8 justify_span justify_span_w60">单位</span>
                        <span>{{matBase.unit}}</span>
                    </p>
                </div>
            </div>
            <!--基础信息 结束-->
            <!--详情 开始-->
            <div class="analyItem">
                <p class="analyItemTit tx-center">详情</p>
                <div class="analyItemCon relative">
                    <div class="fl col-md-12 relative" style="padding-right: 10%">
                        <span class="cLightGray pr8 justify_span justify_span_w60 fl">功能</span>
                        <span class="fl" style="width:80%">{{matBase.matFunction}}</span>
                    </div>
                    <div class="fl col-md-12 relative" style="padding-right: 10%">
                        <span class="cLightGray pr8 justify_span justify_span_w60 fl">描述</span>
                        <span class="fl" style="width:80%">{{matBase.matDescription}}</span>
                    </div>
                    <div class="fl col-md-12 relative " style="padding-right: 10%">
                        <span class="cLightGray pr8 justify_span justify_span_w60 fl">搜索词</span>
                        <span class="fl" style="width:80%">{{matQuoteBase.searchStr}}</span>
                    </div>
                    <div class="fl col-md-12 relative " style="padding-right: 10%">
                        <span class="cLightGray pr8 justify_span justify_span_w60 fl">用途</span>
                        <span class="fl" style="width:80%">{{matQuoteBase.usesStr}}</span>
                    </div>

                    <%--回路积分项--%>
                    <div class="judge-div">
                        <input type="text" :class="matQuoteBase.matFunctionScore != 0 && matQuoteBase.matDescriptionScore != 0 && matQuoteBase.searchTermsScore != 0 && matQuoteBase.usesScore != 0? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                        <input type="text" :class="matQuoteBase.matFunctionScore == 0 || matQuoteBase.matDescriptionScore == 0 || matQuoteBase.searchTermsScore == 0 || matQuoteBase.usesScore == 0? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                        <strong class="fl score cGreen fz14 pl5">{{matQuoteBase.matFunctionScore + matQuoteBase.matDescriptionScore + matQuoteBase.searchTermsScore + matQuoteBase.usesScore}}分</strong>
                    </div>
                </div>
            </div>
            <!--详情 结束-->
            <!--质量标准 开始-->
            <div class="analyItem">
                <p class="analyItemTit tx-center">材料标准</p>
                <div class="analyItemCon relative">
                   <div class="clearfix">
                       <p class="fl col-md-3">
                           <span class="cLightGray pr8">材料标准</span>
                           <span v-show="matQuoteBase.matStandardSize != 0 " class="cGreen lbl_strandard_qs_detect lbl_strandard_qs_detectClick lbl_strandard_qs_detectDown pointer">{{matQuoteBase.matStandardSize}}项</span>
                           <span v-show="matQuoteBase.matStandardSize == 0 " class="cRed">无</span>
                       </p>
                   </div>
                    <div class="judge-div">
                        <input type="text" :class="matQuoteBase.matStandardSize != 0 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                        <input type="text" :class="matQuoteBase.matStandardSize == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                        <strong class="fl score cGreen fz14 pl5">{{matQuoteBase.matStandardSize}}分</strong>
                    </div>
                    <div class="lbl_strandard_qs_detect_show hide">
                        <p v-for="(item, index) in matQuoteBase.matStandards" class="clearfix mt5 "><span class="cLightGray pr8 fl">{{item.tsiExteriorname}}</span> <span class="fl" style="width: 80%;">{{item.tsiSpecialclaim}}</span></p>
                    </div>
                </div>
            </div>
            <!--质量标准 结束-->
            <!--小样标准 开始-->
            <%--<div class="analyItem">--%>
                <%--<p class="analyItemTit tx-center">货架</p>--%>
                <%--<div class="analyItemCon relative">--%>
                    <%--<p class="fl col-md-3"><span class="cLightGray pr8">取样方法</span><span class="cGreen lbl_strandard_mus_method">有</span></p>--%>
                    <%--<p class="fl col-md-7"><span class="cLightGray pr8">样品属性</span><span class="cGreen lbl_strandard_mus_detect">4项</span></p>--%>
                    <%--<div class="judge-div">--%>
                        <%--<input type="text" class="judge-span judge-yes">--%>
                        <%--<input type="text" class="judge-span judge-no active">--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <!--小样标准 结束-->
            <!--包装标准 开始-->
            <%--<div class="analyItem">--%>
                <%--<p class="analyItemTit tx-center">包装标准</p>--%>
                <%--<div class="analyItemCon relative">--%>
                    <%--<p class="fl col-md-3"><span class="cLightGray pr8">包装标准</span><span class="cGreen lbl_strandard_packs_stand">有</span></p>--%>
                    <%--<p class="fl col-md-3"><span class="cLightGray pr8">包装照片</span><span class="cGreen lbl_strandard_packs_photo">有</span></p>--%>
                    <%--<p class="fl col-md-3"><span class="cLightGray pr8">包装属性</span><span class="cGreen lbl_strandard_packs_detect">4项</span></p>--%>
                    <%--<div class="judge-div">--%>
                        <%--<input type="text" class="judge-span judge-yes active">--%>
                        <%--<input type="text" class="judge-span judge-no">--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <!--包装标准 结束-->
            <!--对比标准 开始-->
            <%--<div class="analyItem">--%>
                <%--<p class="analyItemTit tx-center">对比标准</p>--%>
                <%--<div class="analyItemCon relative">--%>
                    <%--<p class="fl col-md-3"><span class="cLightGray pr8">对比项</span><span class="cGreen lbl_base_compared_attr">4项</span></p>--%>
                    <%--<div class="judge-div">--%>
                        <%--<input type="text" class="judge-span judge-yes">--%>
                        <%--<input type="text" class="judge-span judge-no active">--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <!--对比标准 结束-->
            </div>
        </div>
    </div>
    <div class="divMaterialLoadAlert_bottom pt10 plr10 boldTopLine">
        <%--综合项开始--%>
        <div class="analyItem">
            <p class="analyItemTit tx-center">综合</p>
            <div class="analyItemCon">
                <p class="fl col-md-8 cRed">
                    <span class="cLightGray pr8 cRed">--</span>
                    <strong class="plr5 cRed"></strong>
                </p>
                <p class="fl col-md-4"><span class="cLightGray pr8">得分</span><strong class="fz14 cGreen">{{matQuoteBase.totalScore}}分</strong></p>
                <a v-show="matQuoteBase.costBaseLoopFlag != 0" href="javascript:" class="circlemark circlemark-green">完</a>
                <a v-show="matQuoteBase.costBaseLoopFlag == 0" href="javascript:" class="circlemark circlemark-lightRed">未</a>
            </div>
        </div>
    </div>

</div>
<%--页面JS开始--%>
<<script>
    $(function(){

    })
</script>
<%--VUE2.0 开始--%>
<script>
    var matQuoteVue = new Vue({
        el: '#matQuoteThree',
        data: {
            matQuoteBase:{},// 报价基础数据
            matBase:{},// 基础项
        },
        created() {
            this.buildQuoteBaseData();// 报价>基础
        },
        methods: {
            // 构建报价基础数据
            buildQuoteBaseData: function () {
                var _this = this;
                var treeFourID = $("#hidTreeID").val(); // 四级科目ID
                var cityID = $("#hidCityID").val(); // 城市ID
                $.ajax({
                    url: basePath + '/sublibrary-api/city_three_section/cost/base',
                    type: 'GET',
                    dataType: 'json',
                    data: {treeFourID,cityID},
                    success: function (res) {
                        _this.matQuoteBase = res.body;
                        _this.matBase = res.body.baseData;
                    },
                    error: function (err) {alert("操作出错！");}
                });
            }
        }
    });
</script>