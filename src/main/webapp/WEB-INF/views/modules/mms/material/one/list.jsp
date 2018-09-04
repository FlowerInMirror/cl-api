<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<div id="engOneListVue" v-cloak>
    <%--导航--%>
    <ul class="clearfix uiTab1 mb5 jcha1 jcha2  jcha2_new jcha3_new jcha4_new jcha5_new">
        <li data-index="1" class="uiTab1-active"><a href="javascript:">平台</a></li>
    </ul>

    <%--分类按钮--%>
    <div class="daily_title jcha2 daily_title_tree clearfix  jcha2_new jcha5_new">
        <ul class="fl">
            <li class="title_cur" data-type="0">全</li>
            <li title="未入库" data-type="2">W</li>
            <li title="报价推送-待办任务" data-type="3">D</li>
            <li title="材料商缺失" data-type="6">R</li>
            <li title="待审核材料" data-type="4">S</li>
            <li class="daily_liLast" title="下达地方任务" data-type="5">T</li>
        </ul>
        <ul class="fr">
            <li title="置顶的材料" data-type="99">圈</li>
        </ul>
    </div>

    <%--列表遍历开始--%>
    <div class="contentbox  contentbox_tree">

        <%--列表检索相关--%>
        <div class="dailyftSearch clearfix hide">
            <div class="clearfix uiHas-textIcon">
                <input type="text" class="col-md-12" placeholder="材料名称/规格/规格编号" id="txtTreeSearch">
                <i class="uiText-searchIcon bgIcon"></i>
            </div>
            <div class="clearfix pl10">
                <select class="fl mr2" style="width:62px;" id="ddlTreeOne">
                    <option value="">一级类</option>
                </select>
                <select class="fl mr2" style="width:62px;" id="ddlTreeTwo">
                    <option value="">二级类</option>
                </select>
                <select class="fl mr2" style="width:54px;" id="ddlTreeThree">
                    <option value="">名称</option>
                </select>
                <select class="fl mr2" style="width:54px;" id="ddlTreeFour">
                    <option value="">规格</option>
                </select>
            </div>
        </div>

        <%--列表头相关--%>
        <table class="uiTable jcha2  jcha2_new">
            <thead>
            <tr>
                <th width="15%"><span class="sort" title="排序"  @click="sortBySortField('rowNum')"></span></th>
                <th width="35%">材料名称<span class="serial_number serial_number_tree"><i class="bgIcon"></i></span></th>
                <th width="16%" class="OneClass">管</th>
                <th width="16%" class="OneClass">业</th>
                <th>回访</th>
            </tr>
            </thead>
        </table>

        <%--列表遍历开始--%>
        <div class="scroll-content scroll-content2">
            <table class="uiTable pointertable trHover1" id="tab2">
                <tbody id="tabDataToMaterial">

                    <tr v-for="(listItem,index) in engOneList"
                        :id="listItem.treeFourID"
                        :data-state="listItem.perfectState == listItem.mpsBaseState ? '1' : '2' "
                        :zz-send="listItem.sendTaskState>=1? '1' : '0' "
                        :zz-sends="listItem.sendTaskState"
                        :title="listItem.treeOneName + '-' + listItem.treeTwoName + '-' + listItem.matName + '-' + listItem.matSpec"
                        :data-tone="listItem.treeOneID"
                        :data-two="listItem.treeTwoID"
                        :data-three="listItem.treeThreeID"
                        :data-four="listItem.treeFourID"
                        :zz-search="listItem.matName + '|' + listItem.matSpec + '|' + listItem.matSpecCode"
                        :zz-mark="listItem.visitMark"
                        :zz-task="listItem.taskTime != null ? '1' : '0'"
                        :zz-top="listItem.topPageIndex > 0 ? '1' : '0'"
                        :zz-user="listItem.userCount >= 1 ? '1' : '0'">

                        <td width="15%">{{listItem.rowNum == null ? 0 : listItem.rowNum}}</td>

                        <%--taskTime 业务任务时间,sendTaskState 任务下达状态--%>
                        <%--业务逻辑: 任务业务时间不等于null 或 任务下达啊时间任务下达状态：1新建，2完成 新建,成立显示红点--%>
                        <%--<td ng-show="listItem.taskTime != null || listItem.sendTaskState == 1" width="35%" class="relative"><i class="trNew"></i><span title="{{listItem.matName}}">{{listItem.matName | changeOverlengthStr: 5}}</span></td>--%>

                        <td width="35%"><span :title="listItem.matName">{{ listItem.matName | convertOverlengthStrings(5) }}</span></td>

                        <%--管--%>
                        <td v-show="listItem.visitMark == null" width="16%" data-wei="0" class="cPurple">未</td>
                        <td v-show="listItem.visitMark == 1" width="16%" data-wei="1" class="cGreen">正</td>
                        <td v-show="listItem.visitMark == 2" width="16%" data-wei="2" class="cOrange">异</td>
                        <td v-show="listItem.visitMark == 3" width="16%" data-wei="3" class="cRed">问</td>

                        <%--业--%>
                        <td v-show="listItem.loopStatus == 0" width="16%" class="cRed OneClass">未</td>
                        <td v-show="listItem.loopStatus == 1" width="16%" class="cGreen">完</td>

                        <%--回访倒计时--%>
                        <td><span id="cityOneVisit" :class="listItem.visitTime | visitCountDown(720) < 0 ? 'cRed cityOneVisit' : 'cityOneVisit'" >{{ listItem.visitTime | visitCountDown(720) }}</span></td>
                    </tr>

                </tbody>
            </table>
        </div>

        <%--联动相关--%>
        <div class="jcha2 clearfix tj_bottom  jcha2_new" id="divStateStatistics">
            <p class="col-md-3 StatisAllCount pointer" title="全部" data-mark="0">0</p>
            <p class="col-md-3 cGreen pointer" title="正常" data-mark="1">0</p>
            <p class="col-md-3 cOrange pointer" title="异常" data-mark="2">0</p>
            <p class="col-md-3 cRed pointer" title="问题" data-mark="3">0</p>
        </div>

    </div>
</div>

<script>
    var engOneListVue = new Vue({
        el: '#engOneListVue',
        data: {
            engOneList: [],
            engOneListLength: 0,
            sortOrder: 0 // 排序字段
        },
        created:function(){
            this.buildMajorOneListData();
        },
        methods:{
            buildMajorOneListData:function(){
                var _this = this;
                var cityID = $("#hidCityID").val();
                $.ajax({
                    url: basePath + "/sublibrary-api/city_one_section",
                    type: 'GET',
                    data:{cityID},
                    success: function (res) {
                        if (res.statusCode == 0){
                            _this.engOneList = res.body;
                            if (res.body != null)
                            {
                                _this.engOneListLength = res.body.length;
                                $("#hidTreeTwoID").val(res.body[0].treeTwoID); // 二级科目ID
                            }
                            setTimeout(function(){LoadTreeListToSearch()});
                        } else {alert(res.statusMsg);};
                    },
                    error: function (err) {alert('操作出错！');}
                });
            },
            // 子库列表排序 通过排序字段
            sortBySortField : function(sortField){
                var _this = this;
                var engOneList = _this.engOneList;
                // 判断降序还是升序 0降序(默认) 1升序
                if (_this.sortOrder){
                    // 升序
                    engOneList = jsonSort(engOneList,sortField,false);
                    // 设值下次降序
                    _this.sortOrder = 0;
                } else {
                    // 降序
                    engOneList = jsonSort(engOneList,sortField,true);
                    // 恢复默认值 升序
                    _this.sortOrder = 1;
                }
                _this.engOneList = engOneList;
            },
        }
    })
</script>

