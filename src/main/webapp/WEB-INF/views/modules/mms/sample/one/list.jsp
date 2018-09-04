<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<style>
    #tabSampleTreeList tr:nth-child(even),#tabCityData tr:nth-child(even){
        background-color: rgb(249, 249, 249);
    }
    #tabSampleTreeList tr:nth-child(odd),#tabCityData tr:nth-child(odd){
        background-color: rgb(255, 255, 255);
    }
    #tabSampleTreeList tr:hover,#tabCityData tr:hover {
        background: #eaf4fe;
    }
    .tractive td {
        background: #aedeff !important;
    }
</style>
<div id="SampleOneListVue"  v-cloak>
    <%--一段标题--%>
    <ul class="clearfix uiTab1 mb5 jcha1 jcha2">
        <li class="col-md-4 uiTab1-li uiTab1-active" data-index="0">
            <a href="javascript:">样</a>
        </li>
        <li class="col-md-4 uiTab1-li" data-index="1" @click="buildSampleOneListData()">
            <a href="javascript:">北京</a>
        </li>
        <li class="col-md-4" data-index="2">
            <a href="javascript:">打签</a>
        </li>
    </ul>
    <%--标题下选项按钮--%>
    <div class="daily_title jcha1">
        <ul class="clearfix">
            <li class="title_cur">全</li>
            <li>S</li>
            <li>Z</li>
            <li>G</li>
            <li>J</li>
            <li>R</li>
            <li>
                J
                <i class="titleNew"></i>
            </li>
            <li class="daily_liLast">D</li>
        </ul>
    </div>
    <%--标题下选项按钮--%>
    <div class="daily_title jcha2 hide">
        <ul class="clearfix">
            <li class="title_cur">全</li>
            <li>S</li>
            <li>Z</li>
            <li>G</li>
            <li>J</li>
            <li>R</li>
            <li>
                J
                <i class="titleNew"></i>
            </li>
            <li class="daily_liLast">D</li>
        </ul>
    </div>

        <div class="contentbox">
            <div class="dailyftSearch clearfix hide">
                <div class="fl uiHas-textIcon">
                    <input type="text" class="col-md-12">
                    <i class="uiText-searchIcon bgIcon"></i>
                </div>
            </div>
            <table class="uiTable jcha1">
                <thead>
                <tr>
                    <th width="33%">地区名称</th>
                    <th width="33%">小样总数</th>
                    <th>摆放数量</th>
                </tr>
                </thead>
            </table>
            <div class="scroll-content scroll-content1">
                <table class="uiTable pointertable trHover1">
                    <tbody id="tabCityData">
                    <tr v-for="sampleCity in sampleCityList" :data-cityid="sampleCity.CityID" :data-cityname="sampleCity.CityName">
                        <td width="33%">{{sampleCity.CityName}}</td>
                        <td width="33%"><span class="cGreen">{{sampleCity.MaterialCount}}</span></td>
                        <td><span class="cRed">{{sampleCity.ShelfPositionCount}}</span></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="jcha1 clearfix tj_bottom" id="divStateStatisticsToALL">
                <p class="col-md-3 StatisAllCount pointer" title="全部" data-mark="0">0</p>
                <p class="col-md-3 cGreen pointer" title="正常" data-mark="1">0</p>
                <p class="col-md-3 cOrange pointer" title="异常" data-mark="2">0</p>
                <p class="col-md-3 cRed pointer" title="问题" data-mark="3">0</p>
            </div>
        </div>
        <div class="contentbox hide">
        <!-- 搜索 -->
        <div class="dailyftSearch dailyftSearch1 clearfix hide">
            <div class="fl uiHas-textIcon">
                <input type="text" class="col-md-12" placeholder="项目编号/工程名称" id="contract_no">
                <i class="uiText-searchIcon bgIcon"></i>
            </div>
        </div>
        <!-- 日期筛选 -->
        <div class="dailyftSearch hide dailyftSearch2">
            <div class="uiHas-textIcon clearfix">
                <input type="text" class="width100 fl ml10" placeholder="开始时间">
                <span class="plr5 fl lh28">至</span>
                <input type="text" class="width100 fl endTime" placeholder="结束时间">
            </div>
        </div>
        <!-- 搜索加选择 -->
        <div class="dailyftSearch dailyftSearch3 hide">
            <div class="uiHas-textIcon clearfix">
                <input type="text" class="col-md-12" placeholder="项目编号/工程名称" id="contract_no1">
                <i class="uiText-searchIcon bgIcon"></i>
            </div>
            <div class="clearfix mt5 pl5">
                <select class="fl mr2 width60">
                    <option>一级</option>
                </select>
                <select class="fl mr2 width60">
                    <option>二级</option>
                </select>
                <select class="fl mr2 width60">
                    <option>名称</option>
                </select>
                <select class="fl width60">
                    <option>规格</option>
                </select>
            </div>
        </div>
        <table class="uiTable jcha2">
            <thead>
            <tr>
                <th width="15%"><span class="sort" title="排序"></span></th>
                <th width="35%">材料名称<%--<span class="serial_number serial_number_tree"><i class="bgIcon"></i></span>--%></th>
                <th width="16%" class="OneClass">业</th>
                <th>小时</th>
            </tr>
            </thead>
        </table>
        <div class="scroll-content scroll-content2">
            <table class="uiTable pointertable">
                <tbody id="tabSampleTreeList">
                    <tr v-for="(sampleOne,index) in sampleOneList" :data-treeid="sampleOne.TreeId">
                        <td width="15%">{{index+1}}</td>
                        <td width="35%" :title="sampleOne.MatName">{{sampleOne.MatName| cutout('4','','...')}}</td>
                        <td width="16%" :class="sampleOne.Trade=='正'?'cGreen':'cRed'">{{sampleOne.Trade}}</td>
                        <td v-show="sampleOne.Trade=='未'" class="cRed">{{sampleOne.LogHour}}</td>
                        <td v-show="sampleOne.Trade=='正'">--</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="jcha2 clearfix tj_bottom">
            <p class="col-md-3 StatisAllCount pointer" title="全部" data-mark="0">{{sampleOneListLength}}</p>
            <p class="col-md-3 cGreen pointer" title="正常" data-mark="1">0</p>
            <p class="col-md-3 cOrange pointer" title="异常" data-mark="2">0</p>
            <p class="col-md-3 cRed pointer" title="问题" data-mark="3">0</p>
        </div>
    </div>
</div>
<%--VUE2.0 开始--%>
<script>

    // 小样一段VUE实例
    var SampleOneListVue= new Vue({
        el: '#SampleOneListVue',
        data: {
            sampleCityList:[],//地区列表集合
            sampleOneList: [],// 样一段列表集
            sampleOneListLength: 0// 样一段列表集数量
        },
        created:function(){
            this.buildSampleCityListData();//地区列表
           // this.buildSampleOneListData();//样列表
        },
        filters: {
            cutout: function (str, m, n, e) {
                if(str!=null||str!=undefined){
                    var n = n || 0;
                    var m = m || 0;
                    var e = e || '';
                    //string.substr(start[, length])
                    if (n > 0 && m <= 0) {
                        if (n >= str.length) {
                            str = str
                        } else {
                            str = str.substr(-n, n) + e
                        }
                    } else if (n <= 0 && m > 0) {
                        if (m >= str.length) {
                            str = str
                        } else {
                            str = str.substr(0, m) + e
                        }
                    } else {
                        str = str.substr(0, m) + e + str.substr(-n, n);
                    }
                    return str;
                }

            }
        },
        methods:{
            //构建地区列表
            buildSampleCityListData:function(){
                var _this=this;
                $.ajax({
                    url:  gcApiSite + '/APPSuppliers/AllRegionMaterialStatistic',
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        _this.sampleCityList = res.Body;
                        //判断用户登录的地区左侧列表展示对应地区的数据

                        setTimeout(function(){
                            debugger;
                            var hidUserArea=$("#hidUserArea").val();
                            //39为集团
                            if(hidUserArea!=39)
                            {
                                $(".uiTab1 li[data-index='0']").hide();
                                $(".daily_title").hide().eq(1).show();
                                $("tr[data-cityid='"+hidUserArea+"']").click();
                            }
                            else
                            {
                                hidUserArea=39;
                                $("#tabCityData tr").eq(0).click();
                            }
                        },200)
                    },
                    error: function (err) {alert('操作出错！');}
                });
            },
            // 构建样一段列表数据
            buildSampleOneListData:function(){
                var _this = this;
                var AreaId=$("#hidCityID").val();
                $.ajax({
                    url:  gcApiSite + 'APPSuppliers/MaterialShelfStatistic?AreaId='+AreaId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        if (res.StatusCode == 0){
                            // 实例赋值
                            _this.sampleOneList = res.Body;
                            _this.sampleOneListLength = res.Body.length;
                            setTimeout(function(){
                                // 默认点击第一个
                                if($("#tabSampleTreeList tr").length>0)
                                {
                                    $("#tabSampleTreeList tr:eq(0)").click();
                                }else{
                                    $("#tc-main-city li:eq(0)").click();
                                }

                            },200)
                        } else {alert(res.statusMsg);};
                    },
                    error: function (err) {alert('操作出错！');}
                });
            },
            // 刷新样一段列表数据
            reloadSampleOneListData:function(){
                var _this = this;
                var AreaId=$("#hidCityID").val();
                $.ajax({
                    url:  gcApiSite + 'APPSuppliers/MaterialShelfStatistic?AreaId='+AreaId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        if (res.StatusCode == 0){
                            // 实例赋值
                            _this.sampleOneList = res.Body;
                            _this.sampleOneListLength = res.Body.length;
                        } else {alert(res.statusMsg);};
                    },
                    error: function (err) {alert('操作出错！');}
                });
            },
        }
    })
</script>