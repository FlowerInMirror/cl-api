<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<div class="tc-center fl tc-center-mat">
    <ul class="clearfix uiTab9 j_uiTab9">
        <li ng-click="cityTwoState(hidTreeID)" class="uiTab9-active">状态</li>
        <%--平台更名规格--%>
        <li ng-click="cityTwoPlatform(hidTreeID)" class="">平台</li>
        <li ng-click="cityTwoMall(hidTreeID)" >商城</li>
        <li ng-show="aLevelShowFlag != 0" ui-sref="sublibrary_city.two_level_a" ng-click="showDivMaterialDetail(1)" data-level="1" >A档</li>
        <li ng-show="bLevelShowFlag != 0" ui-sref="sublibrary_city.two_level_b" ng-click="showDivMaterialDetail(2)" data-level="2">B档</li>
        <li ng-show="cLevelShowFlag != 0" ui-sref="sublibrary_city.two_level_c" ng-click="showDivMaterialDetail(3)" data-level="4">C档</li>
        <li ng-click="cityTwoPrice(hidTreeID)" >价格</li>
        <li ui-sref="sublibrary_city.two_cost" ng-click="cityTwoCost(hidTreeID)" data-ptype="quotes">报价</li> <!-- 成本 -->
        <li ui-sref="sublibrary_city.two_app" ng-click="cityTwoApp(hidTreeID)" data-ptype="app">材料商</li> <!-- 应用 -->
    </ul>
    <div id="j-tc-center-content" class="pr10">
        <!--状态 -->
        <div class="uiTab9Con" id="divMaterialDetailToStatus">
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

            <%--<div class="analyItem ">--%>
            <%--<p class="analyItemTit tx-center">处理</p>--%>
            <%--<div class="analyItemCon">--%>
            <%--<a href="javascript:" class="circlemark circlemark-gray">待</a>--%>
            <%--</div>--%>
            <%--</div>--%>

            <div class="analyItem anItemBor" id="divProcessing">
                <p class="analyItemTit tx-center">处理</p>
                <div class="analyItemCon">
                    <a ng-show="two_state.vmhType == ''" href="javascript:" class="circlemark circlemark-purple" title="处理状态:未/完/问/异">未</a>
                    <a ng-show="two_state.vmhType == 1" href="javascript:" class="circlemark circlemark-green" title="处理状态:未/完/问/异">正</a>
                    <a ng-show="two_state.vmhType == 2" href="javascript:" class="circlemark circlemark-orange" title="处理状态:未/完/问/异">异</a>
                    <a ng-show="two_state.vmhType == 3" href="javascript:" class="circlemark circlemark-red" title="处理状态:未/完/问/异">问</a>
                </div>
            </div>


        </div>
        <!--状态 结束-->

        <!--平台-->
        <div class="uiTab9Con dis-none" id="divMaterialDetailToBase"  >
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
        </div>
        <!--平台 结束-->

        <!--商城-->
        <div class="uiTab9Con dis-none" id="divMaterialDetailToPlatform">
            <div class="analyItem">
                <p class="analyItemTit tx-center">状态</p>
                <div class="analyItemCon clearfix">
                </div>
            </div>
            <div class="analyItem ">
                <p class="analyItemTit tx-center">基础</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8">一级</span><span>{{two_mall.matBase.treeOneName}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">二级</span><span>{{two_mall.matBase.treeTwoName}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">名称</span><span title="{{two_mall.matBase.matName}}">{{two_mall.matBase.matName | changeOverlengthStr: 7}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">规格</span><span><span title='{{two_mall.matBase.matSpec}}'>{{two_mall.matBase.matSpec | changeOverlengthStr: 9}}</span></span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">单位</span><span class="lbl_base_unit">{{two_mall.matBase.unit}}</span></p>
                </div>
            </div>
            <div class="analyItem anItemBor" data-ptype="settop">
                <p class="analyItemTit tx-center">商城</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8">置顶位置</span><span class="span-topset">{{null != two_mall.topPageIndex && two_mall.topPageIndex != 0 ? two_mall.topPageIndex + '-' +two_mall.topPageNum : '-'}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">是否推荐首页</span><span class="span-homehsot">{{null != two_mall.homeHostState && two_mall.homeHostState != 0 ? '是' : '否'}}</span></p>
                    <p ng-show="two_mall.getIntoMatID != null" class="fl col-md-4"><a href="{{'http://c.rxjyzx.com/Shopping/Details?ProductID=' + two_mall.getIntoMatID + '&cityID=' + hidCityID}}" target="_blank" class="uiLink">进入商城中材料</a></p>
                </div>
            </div>
            <div class="analyItem anItemBor" data-ptype="search" data-opertype="look">
                <p class="analyItemTit tx-center">搜索词</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8">搜索词</span><span ng-class="{'cGreen lbl_tree_search':(two_mall.searchTerm != -1),'cRed lbl_tree_search':(two_mall.searchTerm == -1)}">{{two_mall.searchTerm != -1 ? two_mall.searchTerm + '项' : '无'}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">用途</span><span ng-class="{'cGreen lbl_tree_use':(two_mall.use != -1),'cRed lbl_tree_use':(two_mall.use == -1)}">{{two_mall.use != -1 ? two_mall.use + '项' : '无'}}</span></p>
                </div>
            </div>
            <div class="analyItem anItemBor" data-ptype="photo">
                <p class="analyItemTit tx-center">照片类</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8">品牌Logo</span><span ng-class="{cRed:two_mall.perfectMatCount != two_mall.brandLogoPhotoNum}">{{two_mall.brandLogoPhotoNum + '/' + two_mall.perfectMatCount}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">品牌型号</span><span ng-class="{cRed:two_mall.perfectMatCount != two_mall.brandTypePhotoNum}">{{two_mall.brandTypePhotoNum + '/' + two_mall.perfectMatCount}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">主图</span><span ng-class="{cRed:two_mall.mainPhotoNum == 0}">{{two_mall.mainPhotoNum !=0 ? two_mall.mainPhotoNum + '张' : '无'}}</span></p>
                    <p class="fl col-md-4 hide"><span class="cLightGray pr8">效果</span><span class="cRed">无</span></p>
                    <p class="fl col-md-4 hide"><span class="cLightGray pr8">细节</span><span class="cRed">无</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">自定模块</span><span ng-class="{cRed:two_mall.userDefinedPhotoNum == 0}">{{two_mall.userDefinedPhotoNum !=0 ? two_mall.userDefinedPhotoNum + '张' : '无'}}</span></p>
                </div>
            </div>
            <div class="analyItem anItemBor" data-ptype="compared">
                <p class="analyItemTit tx-center">对比图</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8">对比项</span><span ng-class="two_mall.matCompared != 0 ?'cGreen lbl_base_compared_attr' : 'cRed lbl_base_compared_attr'">{{two_mall.matCompared != 0 ? two_mall.matCompared + '项' : '无'}}</span></p>
                </div>
            </div>
            <div class="analyItem anItemBor" data-ptype="standard" data-type="1">
                <p class="analyItemTit tx-center">质量标准</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8">检验标准</span><span ng-class="two_mall.qualityStandard != 0 ? 'cGreen lbl_strandard_qs_detect' : 'cRed lbl_strandard_qs_detect'">{{two_mall.qualityStandard != 0 ? two_mall.qualityStandard + "项" : "无" }}</span></p>
                </div>
            </div>
            <div class="analyItem anItemBor hide" data-ptype="agreement">
                <p class="analyItemTit tx-center">材料约定</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8">约定项</span><span class="cRed lbl_base_agreement_attr">无</span></p>
                </div>
            </div>
        </div>
        <!--商城 结束-->
        <!--A档-->
        <div ui-view="two_level_a" class="uiTab9Con dis-none" id="divMaterialDetailToA"></div>
        <!--A档 结束-->
        <!--B档-->
        <div ui-view="two_level_b" class="uiTab9Con dis-none" id="divMaterialDetailToB"></div>
        <!--B档 结束-->
        <!--C档-->
        <div ui-view="two_level_c" class="uiTab9Con dis-none" id="divMaterialDetailToC"></div>
        <!--C档 结束-->
        <!--价格-->
        <div class="uiTab9Con dis-none" id="divMaterialDetailToLocalPrice">
            <div class="analyItem">
                <p class="analyItemTit tx-center">状态</p>
                <div class="analyItemCon clearfix">
                </div>
            </div>
            <div class="analyItem">
                <p class="analyItemTit tx-center">基础</p>
                <div class="analyItemCon">
                    <p class="fl col-md-4"><span class="cLightGray pr8">一级</span><span>{{two_price.matBase.treeOneName}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">二级</span><span>{{two_price.matBase.treeTwoName}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">名称</span><span title="{{two_price.matBase.matName}}">{{two_price.matBase.matName | changeOverlengthStr: 7}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">规格</span><span><span title='{{two_price.matBase.matSpec}}'>{{two_price.matBase.matSpec | changeOverlengthStr: 9}}</span></span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">单位</span><span class="lbl_base_unit">{{two_price.matBase.unit}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">分类</span><span class="lbl_base_finishProductType">{{two_price.matBase.classify == 1 ? '成品类' : '非成品类' }}</span></p>
                </div>
            </div>

            <div ng-repeat="level in two_price.levels" class="analyItem">
                <p class="analyItemTit tx-center">{{level.matLevel | levelNumChangeLetter}}档</p>
                <div class="analyItemCon anItemBor" data-level="1">
                    <p class="fl col-md-4"><span class="cLightGray pr8">品牌</span><span>{{level.levelCount}}种</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">成本</span><span>{{levelPriceInterval(level.costMin,level.costMax)}}</span></p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">均价<!--交易价--></span><span>{{levelPriceInterval(level.dealMin,level.dealMax)}}</span></p>
                </div>
            </div>
        </div>
        <!--价格 结束-->
        <!--成本（报价）-->
        <div ui-view="two_cost"  class="uiTab9Con dis-none" id="divMaterialDetailToQuotation"></div>
        <!--成本（报价） 结束-->
        <!--应用（APP）-->
        <div ui-view="two_app" class="uiTab9Con dis-none" id="divMaterialDetailToAPP"></div>
        <!--应用（APP） 结束-->
    </div>
</div>

<%--二段隐藏域:1.点击新增标准时触发展示.--%>
<div class="tc-center fl tc-center-new hide">
    <iframe src="" frameborder="0" scrolling="no" id="divMaterialDetailIframe" width="100%" height="100%"></iframe>
</div>

<div class="tc-right tc-right-all relative">
    <!--新增任务-->
    <div class="NewAddBox alertpaper hide">
        <div class="pa10">
            <div class="clearfix NewAddBoxTit">
                <h2 class="uiTitle2 fl">
                    <i class="uiTitle-icon"></i>
                    新增任务
                </h2>
                <div class="cBlue cur fr closeNewAddBox">收起</div>
            </div>
            <div class="TaskAddBox thinScroll" style="height: 495px; overflow-y: auto;">
                <table class="uiTable-borderNone TaskAddList">
                    <tbody>
                    <tr>
                        <!--<td class="tx-right">周期：</td>-->
                        <td class="tx-left">
                            <input type="text" value="开始时间" class="width100" id="taskStartTime" onclick="WdatePicker();">
                            至
                            <input type="text" value="结束时间" class="width100" id="taskEndTime" onclick="WdatePicker();">
                        </td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <!--<td width="60" class="tx-right">任务名：</td>-->
                        <td class="tx-left"><input type="text" placeholder="任务名请输入" class="taskName" id="taskTitle"></td>
                    </tr>
                    <tr>
                        <td class="tx-left">
                            <textarea rows="3" placeholder="任务描述" id="taskContent"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="TaskAddSubmitBox mt10 tx-center">
                <input type="button" value="提交" class="uiBtn-blue uiBtn-normal" id="submitTask">
            </div>
        </div>
    </div>
    <div class="tc-righttop-tab j-tc-righttop-tab">
        <input type="button" value="列表" class="uiBtn-blue uiBtn-normal mater_lists fr" >
    </div>
    <div class="tc-right-top tc-right-top-all clearfix">
        <div class="fl needdealt">
            <ul>
            </ul>
            <ul class="pos_ab_bottom">
            </ul>
        </div>
        <div class="tc-taskDiv hide">
            <div class="clearfix">
                <i class="fr rig_close">×</i>
                <div class="pa10">
                    <div class="clearfix close_addTaskAlert mb5">
                        <a href="javascript:" class="close fr mt5"></a>
                    </div>
                </div>
                <div class="taskdiv">
                </div>
            </div>
        </div>
        <div class="tc-taskDiv-history hide fl">
            <div class="clearfix">
                <i class="fr rig_close">×</i>
                <div class="pa10">
                    <div class="clearfix close_addTaskAlert mb5">
                        <a href="javascript:" class="close fr mt5"></a>
                    </div>
                </div>
                <div class="taskdivHistory">
                    <h2 class="uiTitle2 platit plr10">
                        <i class="uiTitle-icon"></i>
                        下达任务
                    </h2>
                    <div class="SearchCon">
                        <div class="qingdanHead cha2 tabDataToTwo Temp_item">
                            <table class="uiTable" id="tb_3">
                                <thead>
                                <tr>
                                    <th width="6%">序</th>
                                    <th width="14%">任务名称</th>
                                    <th width="32%">任务描述</th>
                                    <th width="18%">推送时间</th>
                                    <th width="12%">状态</th>
                                    <th>处理时间</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="Temp_itemscroll">
                            <table class="uiTable" id="tb_4">
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="visitbox">
            <div class="visit visiterecord-rgt-scroll thinScroll" id="j-visit">
                <div class="visiterecord-rgt-scroll-con">

                </div>
            </div>
        </div>
    </div>
    <div class="tc-right-bottom pa10 relative">
        <div class="tc-right-bottom-left relative">
            <ul class="topdaily">
                <li>
                    <a class="topdailybtn visittype" data-val="1" href="javascript:">正常</a>
                </li>
                <li>
                    <a class="topdailybtn visittype" data-val="2" href="javascript:">异常</a>
                </li>
                <li>
                    <a class="topdailybtn visittype" data-val="3" href="javascript:">问题</a>
                </li>
            </ul>
            <div class="dailyrgt-botradio hide">
            </div>
            <div class="dailyrgt-divZi">
                <p class="tx-center">先解决业务问题，才有资格说管理。前两个因素完成之后，再反馈系统问题。</p>
            </div>
            <div class="visi-text-content hide">
                <textarea placeholder="请输入回访内容" class="hf-textarea mb10" id="txtVisitContent"></textarea>
                <input class="hf-submit" type="submit" value="提交" />
            </div>
        </div>
        <div class="EventpenaltyDiv">
            <a href="javascrpit:;" class="EventpenaltyBtn NewAddBtn">新增待办</a>
        </div>
    </div>
    <!--基础信息 弹出层开始-->
    <div class="divBaseInfoAlert pa10 hide alertpaper">
        <h3 class="divBaseInfoAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divBaseInfoAlert-con">
            <!--参数 开始-->
            <div class="hasscroll materialTopNavCon pl5 pr5">
                <h4 class="h4title mb2">基础信息</h4>
                <div class="pl10">
                    <div class="clearfix ">
                        <p class="fl minwidth204 lh20">
                            <span class="pr5 fl "><span class="justify_span justify_span_w40">单位</span>：</span>
                            <span class="fl">cm</span>
                        </p>
                        <p class="fl minwidth204 mr5 lh20">
                            <span class="pr5 fl">分类：</span>
                            <span class="fl">成品类</span>
                        </p>
                    </div>
                    <div class="clearfix ">
                        <span class="pr5 fl lh20"><span class="justify_span justify_span_w40">功能</span>：</span>
                        <span class="fl">功能功能功能功能功能功能功能</span>
                    </div>
                    <div class="clearfix mb10">
                        <span class="fl justify_span justify_span_w40">描述</span>：
                        <div class="ml52 ">描述描述描述描述描述描述描述</div>
                    </div>
                    <div class="clearfix ">
                        <span class="pr5 fl lh20"><span class="justify_span justify_span_w40">搜索词</span>：</span>
                        <span class="fl">搜索词搜索词搜索词搜索词搜索词</span>
                    </div>
                    <div class="clearfix ">
                        <span class="pr5 fl lh20"><span class="justify_span justify_span_w40">用途</span>：</span>
                        <span class="fl">用途用途用途用途用途用途用途</span>
                    </div>
                </div>
                <h4 class="h4title mb2">材料参数</h4>
                <div class="pl10">
                    <div class="clearfix ">
                        <p class="fl minwidth204 lh20"><span class="pr5"><span class="justify_span justify_span_w40">长</span>：</span>10cm</p>
                        <p class="fl minwidth204 lh20"><span class="pr5"><span class="justify_span justify_span_w40">宽</span>：</span>2cm</p>
                    </div>
                </div>
                <div class="mt10 tx-center">
                    <input type="button" value="修改基础" class="uiBtn-blue uiBtn-normal">
                    <p class="fl minwidth204 lh20"><span class="pr5 cRed">注：修改此基础信息会同步全国此材料规格的基础信息</span></p>
                </div>
            </div>
            <!--参数 结束-->
        </div>
    </div>
    <!--基础信息 弹出层结束-->
    <!--材料 弹出层开始-->
    <div class="divMaterialAlert pa10 hide alertpaper">
        <h3 class="divMaterialAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialAlert-con" id="divAlertMaterialBaseInfo"></div>
    </div>
    <!--材料 弹出层结束-->
    <!--照片 弹出层开始-->
    <div class="divMaterialPhotoAlert pa10 hide alertpaper">
        <h3 class="divMaterialPhotoAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialPhotoAlert-con" id="divAlertMaterialPhoto"></div>
    </div>
    <!--照片 弹出层结束-->
    <!--基本信息 公用 弹出层开始-->
    <div class="divMaterialBasePublicAlert pa10 hide alertpaper">
        <h3 class="divMaterialBasePublicAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialBasePublicAlert-con" id="divAlertMaterialBasePublic"></div>
    </div>
    <!--基本信息 公用 弹出层结束-->
    <!--材料标准 弹出层开始-->
    <div class="divMaterialStandardAlert pa10 hide alertpaper">
        <h3 class="divMaterialStandardAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialStandardAlert-con" id="divAlertMaterialStandard"></div>
    </div>
    <!--材料标准 弹出层结束-->
    <!--材料工艺 弹出层开始-->
    <div class="divMaterialCraftsAlert pa10 hide alertpaper">
        <h3 class="divMaterialCraftsAlert-tit uiTitle2 clearfix">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialCraftsAlert-con" id="divAlertMaterialCrafts"></div>
    </div>
    <!--材料工艺 弹出层结束-->
    <!--成本价格 弹出层开始-->
    <div class="divMaterialPriceAlert pa10 hide alertpaper">
        <h3 class="divMaterialPriceAlert-tit uiTitle2">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <div class="divMaterialPriceAlert-con" id="divAlertMaterialPrice"></div>
    </div>
    <!--成本价格 弹出层结束-->
    <!--交易价 弹出层开始-->
    <div class="divMaterialPriceSupAlert pa10 hide alertpaper">
        <div class="divMaterialPriceSupAlert-tit clearfix">
            <h3 class="uiTitle2 mb5  fl">
                <i class="uiTitle-icon mr5"></i>
                价格
            </h3>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </div>
        <div class="divMaterialPriceSupAlert-con" id="divAlertMaterialPriceSup"></div>
        <!--交易价 弹出层结束-->
    </div>

    <script src="${ctxStatic}/js/vue/mms/commons/filters.js?${verStatic}" type="text/javascript" charset="utf-8"/>
    <script src="${ctxStatic}/js/vue/mms/commons/functions.js?${verStatic}" type="text/javascript" charset="utf-8"/>

    <!--加载性弹层 弹出层开始-->
    <div id="three-section" class="divMaterialLoadAlert hide alertpaper relative">
        <h3 class="divMaterialLoadAlert-tit uiTitle2 clearfix platit">
            <i class="fl mr5 hide"></i>
            <a href="javascript:" class="close fr mr5 mt2"></a>
        </h3>
        <%-- 三段弹出位 --%>
        <div class="divMaterialLoadAlert-con" id="divAlertMaterialLoad" >
        </div>
    </div>
    <!--加载性弹层 弹出层结束-->
</div>
<script>
    // ========== 平台vuejs 开始
    // 挂载vue应用
    //    var divAlertMaterialLoadVue = new Vue({
    //        // 提供一个在页面上已存在的 DOM 元素作为 Vue 实例的挂载目标。
    //        el:'#divAlertMaterialLoad',
    //        // Vue实例的数据对象。Vue 将会递归将 data 的属性转换为 getter/setter，从而让 data 的属性能够响应数据变化
    //        data:{
    //            matBase:{},// 基础信息
    //            staQuality:[],// 质量标准
    //            staHue:[],// 小样标准
    //            staPackage:[],// 包装标准
    //            staContrast:[],// 对比标准
    //            search:[]// 搜索词
    //        },
    //        // 实例已经创建完成之后被调用。在这一步，实例已完成以下的配置：数据观测(data observer)，属性和方法的运算， watch/event 事件回调。然而，挂载阶段还没开始，$el 属性目前不可见
    //        created() {
    ////                    this.lftFn();
    //        },
    //        // 局部过滤器
    //        filters:{
    ////                    // 获取时间7天后的时间
    ////                    afterDate: function (data, n) {
    ////                        var d = new Date(data);
    ////                        d.setDate(d.getDate() + n);
    ////                        var month = (d.getMonth() + 1) < 10 ? '0' + (d.getMonth() + 1) : (d.getMonth() + 1);
    ////                        var day = d.getDate() < 10 ? '0' + d.getDate() : '' + d.getDate();
    ////                        return d.getFullYear() + '-' + month + '-' + day;
    ////                    }
    //        },
    //        // methods将被混入到 Vue 实例中，可以直接通过 VM 实例访问这些方法，或者在指令表达式中使用 方法中的 this自动绑定为 Vue 实例
    //        methods: {
    //            // 搜索词
    //            buildSearchData: function () {
    //
    //            },
    //            // 对比标准
    //            buildStaContrastData: function () {
    //
    //            },
    //            // 包装标准
    //            buildStaPackageData: function () {
    //
    //            },
    //            // 小样标准
    //            buildStaHueData: function () {
    //
    //            },
    //            // 质量标准
    //            buildStaQualityData: function () {
    //                var _this = this;
    //                var treeFourID = $("#hidTreeID").val();
    //                $.ajax({
    //                    url: basePath + '/sublibrary-api/city_three_section/platform/base',
    //                    type: 'GET',
    //                    dataType: 'json',
    //                    data: { treeFourID },
    //                    success: function (data) {
    //                        _this.matBase = data.body;
    //                        console.log(111)
    //                    },
    //                    error: function (err) {
    //                        alert("操作出错！");
    //                    }
    //                });
    //            },
    //            // 基础信息
    //            buildBaseData: function () {
    //                var _this = this;
    //                var treeFourID = $("#hidTreeID").val();
    //                $.ajax({
    //                    url: basePath + '/sublibrary-api/city_three_section/platform/base',
    //                    type: 'GET',
    //                    dataType: 'json',
    //                    data: { treeFourID },
    //                    success: function (res) {
    //                        _this.matBase = res.body;
    //                        _this.LoadTreeBaseInfo();
    //                    },
    //                    error: function (err) {alert("操作出错！");}
    //                });
    //            },
    //            // 分布页
    //            LoadTreeBaseInfo: function(){
    //                var _this = this;
    //                $.ajax({
    //                    url: basePath + "/public-web/sublibrary/city_three_platform/base",
    //                    type: 'POST',
    //                    data: { treeID: $("#hidTreeID").val(), cityID: $("#hidCityID").val(), pageType: $("#hidPageType").val() },
    //                    dataType: 'html',
    //                    success: function (result) {
    //                        alert(_this.matBase.classify);
    //                        $("#divAlertMaterialLoad").html(result);
    //                        $(".alertpaper").hide();
    //                        $(".divMaterialLoadAlert").show().animate({
    //                            left: 0
    //                        }, 200);
    //                        countLeft();
    //                        countMiddle();
    //                        countRight();
    //                    },
    //                    error: function (e) {console.error(e);}
    //                });
    //            }
    //        }
    //    });
</script>

<input type="hidden" id="hidFinishProductType" value="2" />
<script>
    function countH(){
        //登陆集团材料--材料子库--平台--包装标准弹出层
        $(".divMaterialLoadAlert-con").each(function(){
            var odivMaterialLoadAlertConH=$(this).parents(".divMaterialLoadAlert").height()-$(this).parents(".divMaterialLoadAlert").find(".divMaterialLoadAlert-tit").outerHeight(true);
            $(this).height(odivMaterialLoadAlertConH).slimScroll({ height: odivMaterialLoadAlertConH, borderRadius: "0px" });
            $(this).parents(".slimScrollDiv").height(odivMaterialLoadAlertConH);
        });
        $(window).resize(function(){
            $(".divMaterialLoadAlert-con").each(function(){
                var odivMaterialLoadAlertConH=$(this).parents(".divMaterialLoadAlert").height()-$(this).parents(".divMaterialLoadAlert").find(".divMaterialLoadAlert-tit").outerHeight(true);
                $(this).height(odivMaterialLoadAlertConH).slimScroll({ height: odivMaterialLoadAlertConH, borderRadius: "0px" });
                $(this).parents(".slimScrollDiv").height(odivMaterialLoadAlertConH);
            });

        })
    }
    $("#hidIcaType").val("0");
    $(function () {

        $(".topdaily .topdailybtn").click(function () {
            $(this).parents(".topdaily").find(".topdailybtn").removeClass("active");
            $(this).addClass("active");
            $(".dailyrgt-divZi").hide();
            $(".visi-text-content").show();
            $(".dailyrgt-botradio").hide();
        });
        //历史记录弹窗h100%
//        console.log('tc-right-all的高: ' + $('.tc-right-all').height())
        $('.tc-right-top-all').height($('.tc-right-all').height())
    });
</script>
<script>
    $(function () {
        $(".mater_lists").click(function () {
            $('.iframe_list').css({
                "position": "fixed",
                "left": '0',
                "top": '0',
                "width": '100%',
                "height": '100%',
                "z-index": "100"
            })
            $('.iframe_list').attr('src', basePath + '/public-web/material/list');
            $('.iframe_list').show();
            $(".iframe_list_01").show();

        })
        $(".mater_check").click(function () {
            $('.iframe_check').css({
                "position": "fixed",
                "left": '0',
                "top": '0',
                "width": '100%',
                "height": '100%',
                "z-index": "100"
            })
            $('.iframe_check').attr('src', '/html/material_check.html');
            $('.iframe_check').show();
            $(".iframe_check_01").show();
        })
    })
</script>
