<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<div class="analyItem">
    <p class="analyItemTit tx-center">状态</p>
    <div class="analyItemCon clearfix">
    </div>
</div>

<div class="analyItem anItemBor" data-ptype="base">
    <p class="analyItemTit tx-center">基础</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">一级</span><span>{{two_cost.matBase.treeOneName}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">二级</span><span>{{two_cost.matBase.treeTwoName}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">名称</span><span title="{{two_cost.matBase.matName}}">{{two_cost.matBase.matName | changeOverlengthStr: 7}}</span></p>
        <p class="fl col-md-8"><span class="cLightGray pr8">规格</span><span><span title='{{two_cost.matBase.matSpec}}'>{{two_cost.matBase.matSpec | changeOverlengthStr: 20}}</span></span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">单位</span><span class="lbl_base_unit">{{two_cost.matBase.unit}}</span></p>
    </div>
</div>

<!--A档-->
<div ng-show="aLevelShowFlag != 0" class="analyItem anItemBor" data-level="1">
    <p class="analyItemTit tx-center">A档</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">需求</span><span>10种</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">品牌</span><span>{{two_cost.levelCountA}}种</span></p>
    </div>
</div>
<!--A档 结束-->

<!--B档-->
<div ng-show="bLevelShowFlag != 0" class="analyItem anItemBor" data-level="2">
    <p class="analyItemTit tx-center">B档</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">需求</span><span>10种</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">品牌</span><span>{{two_cost.levelCountB}}种</span></p>
    </div>
</div>
<!--B档 结束-->

<!--C档-->
<div ng-show="cLevelShowFlag != 0" class="analyItem anItemBor" data-level="4">
    <p class="analyItemTit tx-center">C档</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">需求</span><span>10种</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">品牌</span><span>{{two_cost.levelCountC}}种</span></p>
    </div>
</div>
<!--C档 结束-->
