<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tr ng-click="pitchOnTwoNav(sublibrariesAList.treeFourID,sublibrariesAList.loopStatus,sublibrariesAList.platformLoopStatus)"
    ng-repeat="sublibrariesAList in sublibrariesALists | orderBy:sortOrder:reservse"
    id="{{sublibrariesAList.treeFourID }}"
    data-state="{{sublibrariesAList.perfectState==sublibrariesAList.mpsBaseState?1:2}}"
    zz-check="{{(sublibrariesAList.dshLevel>=1)||(sublibrariesAList.priceCount>=1)?1:0}}"
    zz-send="{{sublibrariesAList.sendTaskState>=1?1:0}}"
    zz-sends="{{sublibrariesAList.sendTaskState}}"
    title="{{sublibrariesAList.treeOneName+'-'+sublibrariesAList.treeTwoName+'-'+sublibrariesAList.matName+'-'+sublibrariesAList.matSpec}}"
    data-tone="{{sublibrariesAList.treeOneID}}"
    data-two="{{sublibrariesAList.treeTwoID}}"
    data-three="{{sublibrariesAList.treeThreeID}}"
    data-four="{{sublibrariesAList.treeFourID}}"
    zz-search="{{sublibrariesAList.matName+'|'+sublibrariesAList.matSpec+'|'+sublibrariesAList.matSpecCode}}"
    zz-mark="{{sublibrariesAList.visitMark}}" zz-task="{{sublibrariesAList.taskTime != null ? 1 : 0}}"
    zz-top="{{sublibrariesAList.topPageIndex>0?1:0}}"
    zz-user="{{sublibrariesAList.userCount>=1?1:0}}">

    <td width="15%">{{sublibrariesAList.rowNum == null ? 0 : sublibrariesAList.rowNum}}</td>

    <%--taskTime 业务任务时间,sendTaskState 任务下达状态--%>
    <%--业务逻辑: 任务业务时间不等于null 或 任务下达啊时间任务下达状态：1新建，2完成 新建,成立显示红点--%>
    <%--<td ng-show="sublibrariesAList.taskTime != null || sublibrariesAList.sendTaskState == 1" width="35%" class="relative"><i class="trNew"></i><span title="{{sublibrariesAList.matName}}">{{sublibrariesAList.matName | changeOverlengthStr: 5}}</span></td>--%>

    <td width="35%"><span title="{{sublibrariesAList.matName}}">{{sublibrariesAList.matName | changeOverlengthStr: 5}}</span></td>

    <%--管--%>
    <td ng-show="sublibrariesAList.visitMark == null" width="16%" data-wei="0" class="cPurple">未</td>
    <td ng-show="sublibrariesAList.visitMark == 1" width="16%" data-wei="1" class="cGreen">正</td>
    <td ng-show="sublibrariesAList.visitMark == 2" width="16%" data-wei="2" class="cOrange">异</td>
    <td ng-show="sublibrariesAList.visitMark == 3" width="16%" data-wei="3" class="cRed">问</td>

    <%--业--%>
    <td ng-show="sublibrariesAList.loopStatus == 0" width="16%" class="cRed OneClass">未</td>
    <td ng-show="sublibrariesAList.loopStatus == 1" width="16%" class="cGreen">完</td>

    <%--回访倒计时--%>
    <td><span id="cityOneVisit" class="cityOneVisit" ng-class="{cRed:(sublibrariesAList.visitTime| visitCountDown: 720)<0}" title="{{sublibrariesAList.visitTime| visitCountDown: 720 }}">{{sublibrariesAList.visitTime| visitCountDown: 720 }}</span></td>
</tr>