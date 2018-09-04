<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<%--状态--%>
<div class="analyItem">
    <p class="analyItemTit tx-center">状态</p>
    <div class="analyItemCon clearfix relative">
        <p class="fl col-md-4">
            <span class="cLightGray pr8" title="无/x种" >品牌总数</span>
            <span ng-show="two_level.brandSize == 0" title="无品牌" class="cRed">无</span>
            <span ng-show="two_level.brandSize != 0" >{{two_level.brandSize}}种</span>
        </p>

        <p class="fl col-md-4">
            <span class="cLightGray pr8" title="完成/未">质量标准</span>
            <span ng-show="two_level.qualityStandardStatus == 1" class="cGreen">完成</span>
            <span ng-show="two_level.qualityStandardStatus == 0" title="未完成" class="cRed">未</span>
        </p>

        <p class="fl col-md-4">
            <span class="cLightGray pr8" title="完成/未">对&ensp;比&ensp;项</span>
            <span ng-show="two_level.contrastItemStatus == 1" class="cGreen">完成</span>
            <span ng-show="two_level.contrastItemStatus == 0" title="未完成" class="cRed">未</span>
        </p>

        <p class="fl col-md-4">
            <span class="cLightGray pr8" title="无/完成/未x种">品牌详情</span>
            <span ng-show="two_level.brandSize == 0 " title="因无品牌无法完成总计" class="cRed">无</span>
            <span ng-show="two_level.brandDetailNotCount == 0 && two_level.brandSize != 0 " class="cGreen">完成</span>
            <span ng-show="two_level.brandDetailNotCount > 0" title="未完成品牌详情录入的品牌种数" class="cRed">未{{two_level.brandDetailNotCount}}种</span>
        </p>

        <p class="fl col-md-4">
            <span class="cLightGray pr8" title="无/完成/未x种">供&ensp;货&ensp;商</span>
            <span ng-show="two_level.brandSize == 0 " title="因无品牌无法完成总计" class="cRed">无</span>
            <span ng-show="two_level.supplierNotCount == 0 && two_level.brandSize != 0 " class="cGreen">完成</span>
            <span ng-show="two_level.supplierNotCount > 0" title="未入驻供应商的品牌种数" class="cRed">未{{two_level.supplierNotCount}}种</span>
        </p>

        <input  type="button" value="添加" class="uiBtn-blue addinfo">
        <a ng-show="two_level.levelTotalLoopScore == 1" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_level.levelTotalLoopScore == 0.5" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>

<%--基础--%>
<div class="analyItem">
    <p class="analyItemTit tx-center">基础</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">一级</span><span>{{two_level.matBase.treeOneName}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">二级</span><span>{{two_level.matBase.treeTwoName}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">名称</span><span title="{{two_level.matBase.matName}}">{{two_level.matBase.matName | changeOverlengthStr: 7}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">规格</span><span><span title='{{two_level.matBase.matSpec}}'>{{two_level.matBase.matSpec | changeOverlengthStr: 9}}</span></span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">单位</span><span class="lbl_base_unit">{{two_level.matBase.unit}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">分类</span><span class="lbl_base_finishProductType">{{two_level.matBase.classify == 1 ? '成品类' : '非成品类' }}</span></p>

        <%--专项标识--%>
        <a ng-show="two_level.matBase.specialID != null" href="javascript:" class="circlemark circlemark-purple">专</a>
    </div>
</div>

<%--标准--%>
<div class="analyItem anItemBor" data-ptype="standard" data-type="33">
    <p class="analyItemTit tx-center">质量标准</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">质量标准</span><span ng-class="two_level.qualityStandardSize != 0 ? 'cGreen ' : 'cRed '">{{two_level.qualityStandardSize != 0 ? two_level.qualityStandardSize + "项" : "未" }}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">对比项</span><span ng-class="two_level.contrastItemSize != 0 ? 'cGreen lbl_base_compared_attr' : 'cRed lbl_base_compared_attr'">{{two_level.contrastItemSize != 0  ? two_level.contrastItemSize + '项' : '未' }}</span></p>

        <a ng-show="two_level.qualityStandardLoopScore == 1" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_level.qualityStandardLoopScore == 0.5" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>

<%--档次--%>
<div ng-repeat="level in two_level.brandMaps track by $index" ng-click="setHideMatID(level.matID)" class="analyItem anItemBor relative" id="{{'brand' + level.matID}}" data-ptype="brand" data-matid="{{level.matID }}" data-first="{{two_level.currentLevel == 2 && $index == 0 ? 1 : 0}}">
    <p class="analyItemTit tx-center" title="{{level.brandName}}">{{level.brandName == '' ? '未' : level.brandName | changeOverlengthStr : 5 }}</p>
    <div class="analyItemCon pinpai">
        <p class="fl col-md-4 ellipsis">
            <span class="cLightGray pr8">品牌名称</span>
            <span ng-show="level.brandName != '' && level.brandName != null " class="lbl_material_brand_name" title="{{level.brandName}}">{{level.brandName}}</span>
            <span ng-show="level.brandName == '' || level.brandName == null " class=" cRed" >未</span>
        </p>
        <p class="fl col-md-4 ellipsis">
            <span class="cLightGray pr8">型号名称</span>
            <span ng-show="level.brandType != '' && level.brandType != null " class="lbl_material_brand_type" title="{{level.brandType}}" >{{level.brandType | changeOverlengthStr: 9}}</span>
            <span ng-show="level.brandType == '' || level.brandType == null " class="  cRed" >未</span>
        </p>
        <p class="fl col-md-4"><span class="cLightGray pr8">编号</span><span>{{level.matCode}}</span></p>
        <p class="fl col-md-4">
            <span class="cLightGray pr8">LOGO照</span>
            <span class="lbl_material_logo" ng-class="{cRed:(level.matLogoPhoto == 0)}">{{level.matLogoPhoto != 0 ? '有' : '未'}}</span>
        </p>
        <p class="fl col-md-4">
            <span class="cLightGray pr8">主图照片</span>
            <span class="lbl_material_zhutu" ng-class="{cRed:(level.matMainPhoto == 0)}">{{level.matMainPhoto != 0 ? level.matMainPhoto + '张' : '未'}}</span>
        </p>
        <p class="fl col-md-4 ">
            <span class="cLightGray pr8">型号照片</span>
            <span class="lbl_material_xinghaozhaopian" ng-class="{cRed:(level.matTyepPhoto == 0)}">{{level.matTyepPhoto != 0 ? '有' : '未'}}</span>
        </p>
        <p class="fl col-md-4 ">
            <span class="cLightGray pr8">入库状态</span>
            <span class="" ng-class="{cRed:(level.matUpdatestate != 1)}">{{level.matUpdatestate == 1 ? '已入库' : '未入库'}}</span>
        </p>
        <p class="fl col-md-4 ">
            <span class="cLightGray pr8">是否主推</span>
            <span class="" >{{level.matHoststate == 1 ? '是' : '否'}}</span>
        </p>
        <p class="fl col-md-4 ">
            <span class="cLightGray pr8">供&ensp;货&ensp;商</span>
            <span ng-show="level.supplierSize == 0" class="cRed">未</span>
            <span ng-show="level.supplierSize > 0" >有</span>
        </p>
        <%--<p ng-show="level.matPerfect == 0" class="fl col-md-4"><span class="cLightGray pr8">状态</span><span class="cRed">待完善</span></p>--%>

        <%--策略:品牌名称不为空/品牌描述不为空/拥有品牌LOGO图/产品主图至少一张/型号图片至少一张且对应的title型号图片名称不为null/总成本小于等于0/材料状态不为未编辑未完善状态--%>
        <a ng-show="level.brandLooopScore == 1" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="level.brandLooopScore == 0.5" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>
<script>
    //    $(function () {
    //        $("#lblMainOffExteriorName").html("@(Model.PerfectM.tps_ExteriorName)");
    //        $("#lblMainOffExteriorSC").html("@(Model.PerfectM.tps_ExteriorSC)");
    //        $("#lblMainOffExteriorDM").html("@(Model.PerfectM.tps_ExteriorDM)");
    //        $(".j_uiTab9 li[data-level=two_level.currentLevel]").attr("data-brandid", "@(Model.TSID)");
    //    });
</script>
