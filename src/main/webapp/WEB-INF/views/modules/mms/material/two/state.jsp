<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<div class="analyItem">
    <p class="analyItemTit tx-center">状态</p>
    <div class="analyItemCon">
        <p class="fl col-md-3"><b class="fz14"></b></p>
        <%--hidLoopStatus OLD总回路标识--%>
        <a ng-show="hidLoopStatus == 1" href="javascript:" class="circlemark circlemark-green">完</a>
        <a ng-show="hidLoopStatus == 0 " href="javascript:" class="circlemark circlemark-lightRed">未</a>
    </div>
</div>
<div class="analyItem">
    <p class="analyItemTit tx-center" style="background: #3c6;color: #eaf4fe;">平台</p>
    <div class="analyItemCon relative" >
        <p class="fl col-md-4"><span class="cLightGray pr8">一级</span><span>{{two_state.platform.treeOneName}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">二级</span><span>{{two_state.platform.treeTwoName}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">名称</span><span title="{{two_state.platform.matName }}">{{two_state.platform.matName | changeOverlengthStr: 7}}</span></p>
        <p class="fl col-md-12"><span class="cLightGray pr8">规格</span><span title="{{two_state.platform.matSpec}}">{{two_state.platform.matSpec }}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">单位</span><span class="lbl_base_unit">{{two_state.platform.unit}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">分类</span><span class="lbl_base_finishProductType">{{two_state.platform.classify == 1 ? '成本类' : '非成品类' }}</span></p>

        <a ng-show="hidPlatformLoopStatus == 1" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="hidPlatformLoopStatus == 0" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>
<div class="analyItem">
    <p class="analyItemTit tx-center">商城</p>
    <div class="analyItemCon relative">
        <p class="fl col-md-4"><span class="cLightGray pr8">置顶位置</span><span class="span-topset">{{two_state.mall.topPosition}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">是否推荐首页</span><span class="span-homehsot">{{two_state.mall.homeHostState}}</span></p>
    </div>
</div>
<div ng-show="aLevelShowFlag != 0" class="analyItem anItemBor" data-level="1">
    <p class="analyItemTit tx-center">A档</p>
    <%--A档字段开始--%>
    <div class="analyItemCon relative">
        <p class="fl col-md-4"><span class="cLightGray pr8" title="无/x种">品牌总数</span>
            <span ng-show="two_state.aLevel.brandSize == 0" class="cRed" title="无品牌"> 无</span>
            <span ng-show="two_state.aLevel.brandSize != 0">{{two_state.aLevel.brandSize}}种</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">质量标准</span>
            <span ng-show="two_state.aLevel.qualityStandardStatus == 0" class="cRed">未</span>
            <span ng-show="two_state.aLevel.qualityStandardStatus == 1" class="cGreen">完成</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">对&ensp;比&ensp;项</span>
            <span ng-show="two_state.aLevel.contrastItemStatus == 0" class="cRed">未</span>
            <span ng-show="two_state.aLevel.contrastItemStatus == 1" class="cGreen">完成</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">品牌详情</span>
            <span ng-show="two_state.aLevel.brandSize == 0 " class="cRed" title="因无品牌无法完成总计">无</span>
            <span ng-show="two_state.aLevel.brandDetailNotCount == 0 && two_state.aLevel.brandSize != 0 " class="cGreen">完成</span>
            <span ng-show="two_state.aLevel.brandDetailNotCount > 0" class="cRed" title="未完成品牌详情录入的品牌种数" >未{{two_state.aLevel.brandDetailNotCount}}种</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">供&ensp;货&ensp;商</span>
            <span ng-show="two_state.aLevel.brandSize == 0 " class="cRed" title="因无品牌无法完成总计">无</span>
            <span ng-show="two_state.aLevel.supplierNotCount == 0 && two_state.aLevel.brandSize != 0 " class="cGreen">完成</span>
            <span ng-show="two_state.aLevel.supplierNotCount > 0" class="cRed" title="未入驻供应商的品牌种数">未{{two_state.aLevel.supplierNotCount}}种</span>
        </p>

        <a ng-show="two_state.aLevel.levelTotalLoopScore == 1" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_state.aLevel.levelTotalLoopScore == 0.5" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>

<%--B档字段开始--%>
<div ng-show="bLevelShowFlag != 0" class="analyItem anItemBor" data-level="2">
    <p class="analyItemTit tx-center" style="background: #3c6;color: #eaf4fe;">B档</p>

    <div class="analyItemCon relative">
        <p class="fl col-md-4"><span class="cLightGray pr8" title="无/x种">品牌总数</span>
            <span ng-show="two_state.bLevel.brandSize == 0" class="cRed" title="无品牌"> 无</span>
            <span ng-show="two_state.bLevel.brandSize != 0">{{two_state.bLevel.brandSize}}种</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">质量标准</span>
            <span ng-show="two_state.bLevel.qualityStandardStatus == 0" class="cRed">未</span>
            <span ng-show="two_state.bLevel.qualityStandardStatus == 1" class="cGreen">完成</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">对&ensp;比&ensp;项</span>
            <span ng-show="two_state.bLevel.contrastItemStatus == 0" class="cRed">未</span>
            <span ng-show="two_state.bLevel.contrastItemStatus == 1" class="cGreen">完成</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">品牌详情</span>
            <span ng-show="two_state.bLevel.brandSize == 0 " class="cRed" title="因无品牌无法完成总计">无</span>
            <span ng-show="two_state.bLevel.brandDetailNotCount == 0 && two_state.bLevel.brandSize != 0 " class="cGreen">完成</span>
            <span ng-show="two_state.bLevel.brandDetailNotCount > 0" class="cRed" title="未完成品牌详情录入的品牌种数" >未{{two_state.bLevel.brandDetailNotCount}}种</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">供&ensp;货&ensp;商</span>
            <span ng-show="two_state.bLevel.brandSize == 0 " class="cRed" title="因无品牌无法完成总计">无</span>
            <span ng-show="two_state.bLevel.supplierNotCount == 0 && two_state.bLevel.brandSize != 0 " class="cGreen">完成</span>
            <span ng-show="two_state.bLevel.supplierNotCount > 0" class="cRed" title="未入驻供应商的品牌种数">未{{two_state.bLevel.supplierNotCount}}种</span>
        </p>

        <a ng-show="two_state.bLevel.levelTotalLoopScore == 1" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_state.bLevel.levelTotalLoopScore == 0.5" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>

<%--C档字段开始--%>
<div ng-show="cLevelShowFlag != 0" class="analyItem anItemBor" data-level="4">
    <p class="analyItemTit tx-center">C档</p>

    <div class="analyItemCon relative">
        <p class="fl col-md-4"><span class="cLightGray pr8" title="无/x种">品牌总数</span>
            <span ng-show="two_state.cLevel.brandSize == 0" class="cRed" title="无品牌"> 无</span>
            <span ng-show="two_state.cLevel.brandSize != 0">{{two_state.cLevel.brandSize}}种</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">质量标准</span>
            <span ng-show="two_state.cLevel.qualityStandardStatus == 0" class="cRed">未</span>
            <span ng-show="two_state.cLevel.qualityStandardStatus == 1" class="cGreen">完成</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="完成/未">对&ensp;比&ensp;项</span>
            <span ng-show="two_state.cLevel.contrastItemStatus == 0" class="cRed">未</span>
            <span ng-show="two_state.cLevel.contrastItemStatus == 1" class="cGreen">完成</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">品牌详情</span>
            <span ng-show="two_state.cLevel.brandSize == 0 " class="cRed" title="因无品牌无法完成总计">无</span>
            <span ng-show="two_state.cLevel.brandDetailNotCount == 0 && two_state.cLevel.brandSize != 0 " class="cGreen">完成</span>
            <span ng-show="two_state.cLevel.brandDetailNotCount > 0" class="cRed" title="未完成品牌详情录入的品牌种数" >未{{two_state.cLevel.brandDetailNotCount}}种</span>
        </p>

        <p class="fl col-md-4"><span class="cLightGray pr8" title="无/完成/未x种">供&ensp;货&ensp;商</span>
            <span ng-show="two_state.cLevel.brandSize == 0 " class="cRed" title="因无品牌无法完成总计">无</span>
            <span ng-show="two_state.cLevel.supplierNotCount == 0 && two_state.cLevel.brandSize != 0 " class="cGreen">完成</span>
            <span ng-show="two_state.cLevel.supplierNotCount > 0" class="cRed" title="未入驻供应商的品牌种数">未{{two_state.cLevel.supplierNotCount}}种</span>
        </p>

        <a ng-show="two_state.cLevel.levelTotalLoopScore == 1" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_state.cLevel.levelTotalLoopScore == 0.5" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>
<div class="analyItem anItemBor" id="divProcessing">
    <p class="analyItemTit tx-center">处理</p>
    <div class="analyItemCon">
        <a ng-show="two_state.vmhType == ''" href="javascript:" class="circlemark circlemark-purple" title="处理状态:未/完/问/异">未</a>
        <a ng-show="two_state.vmhType == 1" href="javascript:" class="circlemark circlemark-green" title="处理状态:未/完/问/异">正</a>
        <a ng-show="two_state.vmhType == 2" href="javascript:" class="circlemark circlemark-orange" title="处理状态:未/完/问/异">异</a>
        <a ng-show="two_state.vmhType == 3" href="javascript:" class="circlemark circlemark-red" title="处理状态:未/完/问/异">问</a>
    </div>
</div>


