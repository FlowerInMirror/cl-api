<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
