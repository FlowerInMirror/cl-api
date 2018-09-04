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
        <p class="fl col-md-4"><span class="cLightGray pr8">一级</span><span>{{two_app.matBase.treeOneName}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">二级</span><span>{{two_app.matBase.treeTwoName}}</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">名称</span><span title="{{two_app.matBase.matName}}">{{two_app.matBase.matName | changeOverlengthStr: 7}}</span></p>
        <p class="fl col-md-8"><span class="cLightGray pr8">规格</span><span><span title='{{two_app.matBase.matSpec}}'>{{two_app.matBase.matSpec | changeOverlengthStr: 20}}</span></span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">单位</span><span class="lbl_base_unit">{{two_app.matBase.unit}}</span></p>
    </div>
</div>

<!--A档-->
<div ng-show="aLevelShowFlag != 0" class="analyItem anItemBor" data-ptype="level" data-level="{{two_app.aLevel.matLevel}}">
    <p class="analyItemTit tx-center">{{two_app.aLevel.matLevel | levelNumChangeLetter}}档</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">品牌</span><span>{{two_app.aLevel.matCount}}种</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">入驻</span><span>{{two_app.aLevel.busCount}}家</span></p>
        <a ng-show="two_app.aLevel.busCount == 0" href="javascript:" style="width: 42px;" class="circlemark cOrange">未驻</a>
        <a ng-show="two_app.aLevel.busCount == 1 || two_app.aLevel.busCount == 2 || two_app.aLevel.busCount == 3" href="javascript:" style="width: 42px;" class="circlemark cGreen">可驻</a>
        <a ng-show="two_app.aLevel.busCount == 4" href="javascript:" style="width: 42px;" class="circlemark cRed">已满</a>
    </div>
</div>
<!--A档 结束-->

<!--B档-->
<div ng-show="bLevelShowFlag != 0" class="analyItem anItemBor" data-ptype="level" data-level="{{two_app.bLevel.matLevel}}">
    <p class="analyItemTit tx-center">{{two_app.bLevel.matLevel | levelNumChangeLetter}}档</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">品牌</span><span>{{two_app.bLevel.matCount}}种</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">入驻</span><span>{{two_app.bLevel.busCount}}家</span></p>
        <a ng-show="two_app.bLevel.busCount == 0" href="javascript:" style="width: 42px;" class="circlemark cOrange">未驻</a>
        <a ng-show="two_app.bLevel.busCount == 1 || two_app.bLevel.busCount == 2 || two_app.bLevel.busCount == 3" href="javascript:" style="width: 42px;" class="circlemark cGreen">可驻</a>
        <a ng-show="two_app.bLevel.busCount == 4" href="javascript:" style="width: 42px;" class="circlemark cRed">已满</a>
    </div>
</div>
<!--B档 结束-->

<!--C档-->
<div ng-show="cLevelShowFlag != 0" class="analyItem anItemBor" data-ptype="level" data-level="{{two_app.cLevel.matLevel}}">
    <p class="analyItemTit tx-center">{{two_app.cLevel.matLevel | levelNumChangeLetter}}档</p>
    <div class="analyItemCon">
        <p class="fl col-md-4"><span class="cLightGray pr8">品牌</span><span>{{two_app.cLevel.matCount}}种</span></p>
        <p class="fl col-md-4"><span class="cLightGray pr8">入驻</span><span>{{two_app.cLevel.busCount}}家</span></p>
        <a ng-show="two_app.cLevel.busCount == 0" href="javascript:" style="width: 42px;" class="circlemark cOrange">未驻</a>
        <a ng-show="two_app.cLevel.busCount == 1 || two_app.cLevel.busCount == 2 || two_app.cLevel.busCount == 3" href="javascript:" style="width: 42px;" class="circlemark cGreen">可驻</a>
        <a ng-show="two_app.cLevel.busCount == 4" href="javascript:" style="width: 42px;" class="circlemark cRed">已满</a>
    </div>
</div>
<!--C档 结束-->