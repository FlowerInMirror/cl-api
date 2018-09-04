<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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