<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<div id="threePosition"  v-cloak>
    <div class="clearfix layerRtb-head uiTitle2 mb10 positionCha">
        <i class="uiTitle-icon"></i> 货架详情
        <i class="fr rig_close">×</i>
    </div>
    <div class="layerRtb-scroll">
        <div class="platform_box">
            <div class="clearfix pr20 relative">
                <a href="javascript:" class="addRowBtn" @click="addColumn()"><img src="${ctxStatic}/images/pic/add_img.png?${verStatic}" width="20" height="20"></a>
                <div class="clearfix">
                    <div class="divfloor fl tx-center">
                        <div class="rowItem pa5 ">
                            <div class="SolidBorder">
                                <div class="out">
                                    <b>列</b>
                                    <em>层</em>
                                </div>
                            </div>
                        </div>
                        <div class="bgf7f7f7 pa5" v-for="materialShelfPositionFloor in materialShelfPositionFloorList"><div class="SolidBorder" @dblclick="deleFloor($event)" :data-floor="materialShelfPositionFloor">{{materialShelfPositionFloor}}</div></div>
                        <a href="javascript:" class="dis-il-block" @click="addFloor()"><img src="${ctxStatic}/images/pic/add_img.png?${verStatic}" width="20" height="20"></a>
                    </div>
                    <div class="divrowParent">
                        <div class="percentwidth" v-for="(materialShelfPositionColumn,key) in materialShelfPositionColumnList">
                            <div class="rowItem pa5 bgf7f7f7"><div class="SolidBorder relative" @dblclick="deleColumn($event)" :data-column="key">{{key}}</div></div>
                            <div class="floorItem pa5" v-for="aa in materialShelfPositionColumn" :data-position="aa.Position"><div class="SolidBorder canClick" @click="modifymaterialShelf($event)" :data-mpsid="aa.MspID" :data-matid="aa.MatId" :data-text="aa.MatName" :title="aa.MatName" :class="aa.MatName!='可用'?'hover':''">{{aa.MatName}}</div></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="tx-center ul_tab2divbox relative positionCha" id="divTopSetContent2">
        <div class="ul_tab2div clearfix pt10">
            <div class="dis-il-block">
                <span class="fl" style="line-height: 30px;">货架：</span>
                <div class="ul_tab2 fl">
                    <li v-for="(materialShelf,index) in materialShelfAttr" :data-index="materialShelf.ShelfCode" :data-shelfid="materialShelf.ShelfId" @click="materialShelfClick($event)"><span class="fl tab2_li_span">{{materialShelf.ShelfCode}}</span><span class="fl tab2_li_line">......</span></li>
                </div>
                <img src="${ctxStatic}/images/pic/blackadd.png?${verStatic}" class="deleteGoodImg fl mt5 ml10 addGoodBtn" @click="savematerialShelf()">
                <img src="${ctxStatic}/images/pic/blackDelete.png?${verStatic}" class="deleteGoodImg fl mt5 ml10" @click="delematerialShelf()">
            </div>
        </div>
    </div>
</div>
<%--VUE2.0 开始--%>
<script>
    var threePositionVue = new Vue({
        el: '#threePosition',
        data: {
            materialShelfAttr: [],// 货架集合
            materialShelfPositionColumnList:{},//货架数据列集合
            materialShelfPositionFloorList:[],//货架数据行集合
            matPositions:{} // 材料位置
        },
        created:function() {
            this.materialShelfData(); //货架

        },
        methods: {
            materialShelfClick:function(event){
                var i=$(event.currentTarget).index();
                $(".ul_tab2 li").removeClass("bg_blue");
                $(event.currentTarget).addClass("bg_blue");
                this.materialShelfPositionData();

            },
            // 货架集合
            materialShelfData: function () {
                var _this = this;
                var AreaId=$("#hidCityID").val();
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/MaterialShelf?AreaId='+AreaId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        _this.materialShelfAttr = res.Body;
                        //默认选中第一项
                        setTimeout(function(){
                            $(".ul_tab2 li:eq(0)").click();

                        },200)
                    },
                    error: function (err) {alert("操作出错！");}
                });
            },
            //材料货架位置摆放信息
            materialShelfPositionData:function(){
                var _this = this;
                var ShelfId=$(".ul_tab2 .bg_blue").attr("data-shelfid");
                var AreaId=$("#hidCityID").val();
                var olength=0;
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/MaterialShelfLocationMsg?ShelfId='+ShelfId+'&AreaId='+AreaId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        _this.materialShelfPositionColumnList = res.Body.Column;//获取列集合
                        olength= Object.keys(res.Body.Column).length;//获取列个数
                        _this.$nextTick(function(){
                            //求列的宽度
                            $(".percentwidth").width(100/olength+"%");
                            //添加active
                            $(".canClick.active").removeClass("active");
                            var otractiveTreeid=$("#tabSampleTreeList .tractive").attr("data-treeid");
                            $(".canClick[data-matid='"+otractiveTreeid+"']").addClass("active");
                            var onewposition=$(".canClick.active").closest(".floorItem").attr("data-position");
                            $(".positionText").text(onewposition);
                        });
                        _this.materialShelfPositionFloorList=res.Body.Floor;//获取行集合
                    },
                    error: function (err) {alert("操作出错！");}
                });
            },
            //新增货架
            savematerialShelf:function(){
                var _this = this;
                var AreaId=$("#hidCityID").val();
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/OperationMaterialShelfLocation?AreaId='+AreaId+'&OperationType=1',
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                      if(res.StatusCode==1)
                      {
                         // alert("保存成功");
                          $(".anItemBor-active").click();
                      }
                    },
                    error: function (err) {alert("操作出错！");}
                });
            },
            //添加列
            addColumn:function(){
                var _this = this;
                var AreaId=$("#hidCityID").val();
                var ShelfId=$(".bg_blue").attr("data-shelfid");//货架编号
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/OperationMaterialShelfLocation?AreaId='+AreaId+'&OperationType=2&ShelfId='+ShelfId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        if(res.StatusCode==1)
                        {
                           // alert("保存成功");
                            $(".anItemBor-active").click();
                        }
                    },
                    error: function (err) {alert("操作出错！");}
                });
            },
            //添加行
            addFloor:function(){
                var _this = this;
                var AreaId=$("#hidCityID").val();
                var ShelfId=$(".bg_blue").attr("data-shelfid");//货架编号
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/OperationMaterialShelfLocation?AreaId='+AreaId+'&OperationType=3&ShelfId='+ShelfId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        if(res.StatusCode==1)
                        {
                            //alert("保存成功");
                            $(".anItemBor-active").click();
                        }
                    },
                    error: function (err) {alert("操作出错！");}
                });
            },
            //删除列
            deleColumn:function(event){
                var _this = this;
                var AreaId=$("#hidCityID").val();
                var ShelfId=$(".bg_blue").attr("data-shelfid");//货架编号
                var Column=$(event.currentTarget).attr("data-column")//列编号
                layer.open({
                    type: 1,
                    title: "提示",
                    area: ["300px", "200px"],
                    content: $(".comfirmAlertLie"),
                    btn: ['确定', '取消'],
                    yes:function(index){
                        $.ajax({
                            url: gcApiSite + '/APPSuppliers/OperationMaterialShelfLocation?AreaId='+AreaId+'&OperationType=4&Column='+Column+'&ShelfId='+ShelfId,
                            type: 'post',
                            dataType: 'json',
                            success: function (res) {
                                if(res.StatusCode==1)
                                {
                                    alert("保存成功");
                                    $(".anItemBor-active").click();
                                }
                            },
                            error: function (err) {alert("操作出错！");}
                        });
                        layer.close(index);
                    }
                })
            },
            //删除行
            deleFloor:function(event){
                var _this = this;
                var AreaId=$("#hidCityID").val();
                var ShelfId=$(".bg_blue").attr("data-shelfid");//货架编号
                var Floor=$(event.currentTarget).attr("data-floor")//行编号
                layer.open({
                    type: 1,
                    title: "提示",
                    area: ["300px", "200px"],
                    content: $(".comfirmAlertCeng"),
                    btn: ['确定', '取消'],
                    yes:function(index){
                        $.ajax({
                            url: gcApiSite + '/APPSuppliers/OperationMaterialShelfLocation?AreaId='+AreaId+'&OperationType=5&Floor='+Floor+'&ShelfId='+ShelfId,
                            type: 'post',
                            dataType: 'json',
                            success: function (res) {
                                if(res.StatusCode==1)
                                {
                                    alert("保存成功");
                                    $(".anItemBor-active").click();
                                }
                            },
                            error: function (err) {alert("操作出错！");}
                        });
                        layer.close(index);
                    }
                })
            },
            //修改货架材料
            modifymaterialShelf:function(event) {

                var _this = this;
                var AreaId=$("#hidCityID").val();
                var ShelfId=$(".bg_blue").attr("data-shelfid");//货架编号
                var MspID=$(event.currentTarget).attr("data-mpsid");//获取当前选中项的MspID
                var MatId=$("#tabSampleTreeList .tractive").attr("data-treeid");//左侧列表选中项的treeid
                if($(event.currentTarget).hasClass("active"))
                {
                    MatId="";
                }
                var oposition=$(event.currentTarget).closest(".floorItem").attr("data-position");
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/OperationMaterialShelfLocation?AreaId='+AreaId+'&OperationType=6&ShelfId='+ShelfId+'&MspId='+MspID+'&MatId='+MatId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        if(res.StatusCode==1)
                        {
                            var oGoodPosition=oposition.split("-")[0];//获取当前选中的材料在几号货架
                           // alert("保存成功");
                            //$(".anItemBor-active").click();
                            $(".ul_tab2 li[data-index='"+oGoodPosition+"']").click();
                           //刷新左侧列表数据
                            SampleOneListVue.reloadSampleOneListData();
                        }
                    },
                    error: function (err) {alert("操作出错！");}
                });
            },
            //删除货架
            delematerialShelf:function(){
                var _this = this;
                var AreaId=$("#hidCityID").val();
                var ShelfId=$(".bg_blue").attr("data-shelfid");//货架编号
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/OperationMaterialShelfLocation?AreaId='+AreaId+'&OperationType=7&ShelfId='+ShelfId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        if(res.StatusCode==1)
                        {
                            alert("删除成功");
                            $(".anItemBor-active").click();
                        }
                    },
                    error: function (err) {alert("操作出错！");}
                });
            }

        }
    });
</script>
