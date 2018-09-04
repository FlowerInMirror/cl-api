<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<style>
    #tabMajorTreeList tr:nth-child(even) {
        background-color: rgb(249, 249, 249);
    }
    #tabMajorTreeList tr:nth-child(odd){
        background-color: rgb(255, 255, 255);
    }
    #tabMajorTreeList tr:hover {
        background: #eaf4fe;
    }
    .tractive td {
        background: #aedeff !important;
    }
</style>
<div id="majorOneListVue" v-cloak>
    <%--一段标题--%>
    <ul class="clearfix uiTab1 mb5 jcha5_new">
        <li class="uiTab1-active">
            <a href="javascript:">主营</a>
        </li>
    </ul>

    <%--标题下选项按钮--%>
    <div class="daily_title jcha5_new">
        <ul class="clearfix">
            <li class="title_cur">全</li>
            <li>S</li>
            <li>Z</li>
            <li>G</li>
            <li>J</li>
            <li>R</li>
            <li>J</li>
            <li class="daily_liLast">D</li>
        </ul>
    </div>

    <%--检索框--%>
    <div class="dailyftSearch clearfix hide" style="margin-left: 10px;margin-top: 77px;">
        <div class="fl uiHas-textIcon">
            <input type="text" class="col-md-12" placeholder="请输入主营名称的关键字" id="txtMajorSearch">
            <i class="uiText-searchIcon bgIcon"></i>
        </div>
    </div>

    <%--主营:一段列表标题--%>
    <table class=" uiTable jcha5_new ">
        <thead>
        <tr>
            <th width="15%">序号</th>
            <th width="40%">主营名称<span class="searchMajorName serial_number"><i class="bgIcon"></i></span></th>
            <th width="20%">管状</th>
        </tr>
        </thead>
    </table>

    <%--主营:一段列表内容--%>
    <div class="scroll-content">
        <table class="uiTable pointertable" id="tabMajorTreeList">
            <tbody>
            <tr v-for="(majorOne,index) in majorOneList" :data-majorname="majorOne.treeTwoName" :data-treetwoid="majorOne.treeTwoID">
                <td width="15%">{{index + 1}}</td>
                <td width="40%" :title="majorOne.treeOneName + '-' + majorOne.treeTwoName" >{{majorOne.treeTwoName}}</td>
                <td width="20%" :class="majorOne.majorLoopStatus == 1 ? 'cGreen majorLoopStatus' : 'cRed majorLoopStatus'">{{majorOne.majorLoopStatus == 0 ? '未' : '完'}}</td>
            </tr>
            </tbody>
        </table>
    </div>

    <%--一段状态--%>
    <div class="jcha5_new clearfix tj_bottom">
        <p class="col-md-3" title="总数">{{majorOneListLength}}</p>
        <p class="col-md-3 cGreen " title="正常">0</p>
        <p class="col-md-3 cOrange" title="异常">0</p>
        <p class="col-md-3 cRed" title="问题">0</p>
    </div>
</div>
<%--VUE2.0 开始--%>
<script>
    // 主营一段VUE实例
    var majorOneListVue = new Vue({
        el: '#majorOneListVue',
        data: {
            majorOneList: [],// 主营一段列表集
            majorOneListLength: 0// 主营一段列表集数量
        },
        created:function(){
            this.buildMajorOneListData();
        },
        methods:{
            // 构建主营一段列表数据
            buildMajorOneListData:function(){
                var _this = this;
                $.ajax({
                    url:  basePath + '/major-api/one/list',
                    type: 'GET',
                    dataType: 'json',
                    success: function (res) {
                        if (res.statusCode == 0){
                            // 实例赋值
                            _this.majorOneList = res.body;
                            _this.majorOneListLength = res.body.length;

                            // 隐藏域赋值
                            $("#hidZYTreeTwoID").val(res.body[0].treeTwoID); // 主营二级科目ID

                            setTimeout(function(){
                                // 默认点击第一个
                                $("#tabMajorTreeList tr:eq(0)").click();
                            },300)



                        } else {alert(res.statusMsg);};
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        }
    })
</script>
