<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<div class="analyItem">
    <p class="analyItemTit tx-center">状态</p>

    <div class="analyItemCon clearfix">

        <a ng-show="two_platform.statusFlag == 1" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_platform.statusFlag == 0" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>
<div class="analyItem anItemBor baseinfo">
    <p class="analyItemTit tx-center">基础</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">一级</span><span>{{two_platform.matBase.treeOneName}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">二级</span><span>{{two_platform.matBase.treeTwoName}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">名称</span><span title="{{two_platform.matBase.matName}}">{{two_platform.matBase.matName | changeOverlengthStr: 6}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">规格</span><span><span title='{{two_platform.matBase.matSpec}}'>{{two_platform.matBase.matSpec | changeOverlengthStr: 9}}</span></span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">单位</span><span class="lbl_base_unit">{{two_platform.matBase.unit}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">分类</span><span class="lbl_base_finishProductType">{{two_platform.matBase.classify == 1 ? '成品类' : '非成品类' }}</span></p>

        <a ng-show="two_platform.matBaseFalg == 1" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_platform.matBaseFalg == 0" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>
<div class="analyItem anItemBor standardinfo" data-type="1">
    <p class="analyItemTit tx-center">质量标准</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightG1ray pr8">质量标准</span><span ng-class="two_platform.qualityStandard.qualitySize != 0 ? 'cGreen lbl_strandard_qs_detect' : 'cRed lbl_strandard_qs_detect'">{{two_platform.qualityStandard.qualitySize | judgeYesOrNo}}</span></p>

        <a ng-show="two_platform.qualityStandard.qualitySize >= 4" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_platform.qualityStandard.qualitySize < 4" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>
<div class="analyItem anItemBor standardinfo" data-type="3">
    <p class="analyItemTit tx-center">小样标准</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">取样方法</span><span ng-class="{'cGreen lbl_strandard_mus_method':(two_platform.sampleStandard.samplingMethod != '无'),'cRed lbl_strandard_mus_method':(two_platform.sampleStandard.samplingMethod == '无')}">{{two_platform.sampleStandard.samplingMethod}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">样品属性</span><span ng-class="{'cGreen lbl_strandard_mus_detect':(two_platform.sampleStandard.acceptanceStandard > 0),'cRed lbl_strandard_mus_detect':(two_platform.sampleStandard.acceptanceStandard == '无')}" ng-bind="two_platform.sampleStandard.acceptanceStandard != '无' ? two_platform.sampleStandard.acceptanceStandard + '项': '无'" ></span></p>

        <a ng-show="two_platform.sampleStandard.samplingMethod != '无' && two_platform.sampleStandard.acceptanceStandard > 0" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_platform.sampleStandard.samplingMethod == '无' && two_platform.sampleStandard.acceptanceStandard == '无'" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>
<div class="analyItem anItemBor standardinfo" data-type="4">
    <p class="analyItemTit tx-center">包装标准</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">包装标准</span><span ng-class="{'cGreen lbl_strandard_packs_stand':(two_platform.packagingStandard.packagingStandard != '无'),'cRed lbl_strandard_packs_stand':(two_platform.packagingStandard.packagingStandard == '无')}">{{two_platform.packagingStandard.packagingStandard}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">包装照片</span><span ng-class="{'cGreen lbl_strandard_packs_photo':(two_platform.packagingStandard.packagingPhoto != '无'),'cRed lbl_strandard_packs_photo':(two_platform.packagingStandard.packagingPhoto == '无')}">{{two_platform.packagingStandard.packagingPhoto}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">包装属性</span><span ng-class="{'cGreen lbl_strandard_packs_detect':(two_platform.packagingStandard.packagingAttribute != '无'),'cRed lbl_strandard_packs_detect':(two_platform.packagingStandard.packagingAttribute == '无')}">{{two_platform.packagingStandard.packagingAttribute > 0 ? two_platform.packagingStandard.packagingAttribute + '项' : '无' }}</span></p>

        <%--回路策略:包装标准/包装照片为'有',且包装属性至少有一项状态为:'完',反之'未'--%>
        <a ng-show="two_platform.packStaLoop == 1" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_platform.packStaLoop == 0" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>
<div class="analyItem anItemBor standardinfo" data-type="66">
    <p class="analyItemTit tx-center">对比标准</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">对比项</span><span ng-class="{'cGreen lbl_base_compared_attr':(two_platform.contrastStandard.contrastItem  != '无'),'cRed lbl_base_compared_attr':(two_platform.contrastStandard.contrastItem  == '无')}">{{two_platform.contrastStandard.contrastItem  != '无' ? two_platform.contrastStandard.contrastItem + '项' : '无' }}</span></p>

        <a ng-show="two_platform.contrastStandard.contrastItem  != '无'" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_platform.contrastStandard.contrastItem  == '无'" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>
<div class="analyItem anItemBor standardinfo" data-type="50" data-opertype="update">
    <p class="analyItemTit tx-center">搜索词</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">搜索词</span><span ng-class="{'cGreen lbl_tree_search':(two_platform.searchTerm.searchTerm  > 0 ),'cRed lbl_tree_search':(two_platform.searchTerm.searchTerm  == '无')}">{{two_platform.searchTerm.searchTerm  == '无' ? '无' : two_platform.searchTerm.searchTerm + '项'  }}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">用途</span><span ng-class="{'cGreen lbl_tree_use':(two_platform.searchTerm.use >  0),'cRed lbl_tree_use':(two_platform.searchTerm.use == '无')}">{{two_platform.searchTerm.use == '无' ? '无' : two_platform.searchTerm.use + '项' }}</span></p>

        <a ng-show="two_platform.searchTerm.searchTerm  > 0 && two_platform.searchTerm.use >  0" href="javascript:" class="circlemark circlemark-green">1</a>
        <a ng-show="two_platform.searchTerm.searchTerm  == '无' && two_platform.searchTerm.use == '无'" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
    </div>
</div>