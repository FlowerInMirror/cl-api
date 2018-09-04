<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<div id="majorThreeCategoryItemsVue" v-cloak>
    <h3 class="CategoryAlert-tit uiTitle2 clearfix j_chas">
        <i class="fl mr5 hide"></i>
        <a href="javascript:" class="close rig_close fr mr5 mt2"></a>
    </h3>
    <div class="CategoryAlert_topm">
        <h2 class="uiTitle2 mb10 ml10 j_chas"><i class="uiTitle-icon mr5"></i><span id="spaZYTypeNmae"></span>-类别项</h2>
        <div class="plr10 j_chas">
            <div class="analyItem">
                <p class="analyItemTit tx-center">类别项</p>
                <div class="analyItemCon relative">
                    <span class="cLightGray pr8">名称</span><input id="txtMajorCategoryItemName" type="text" placeholder="请输入新增项名称" class="width120 mr10 txtMajorCategoryItem">
                    <span class="cLightGray pr8">排序</span><span><input id="txtMajorCategoryItemOrder" type="text" :value="majorItemListSize + 1" class="width120 mr10 txtMajorCategoryItem"></span>
                    <input type="button" value="新增" class="uiBtn-blue uiBtn-normal add_categorys" style="vertical-align: top;">
                </div>
            </div>
        </div>
        <div class="layerRtb-scroll thinScroll thinScrollFix layerRtb-scroll-append overflow plr10">

            <%--类别项遍历开始--%>
            <div v-for="(majorItem,index) in majorItemList" class="analyItem boxMajorItem" :data-mctiid="majorItem.mctiId">
                <p class="analyItemTit tx-center">类别项{{index + 1}}</p>
                <div class="analyItemCon relative">
                    <p class="col-md-4 info-look"><span class="cLightGray pr8">名称</span><span>{{majorItem.mctiName}}</span></p>
                    <p class="col-md-4 info-look"><span class="cLightGray pr8">排序</span><span>{{majorItem.mctiOrder}}</span></p>
                    <p class="col-md-4 info-edit hide"><span class="cLightGray pr8">名称</span><input type="text" :value="majorItem.mctiName" class="txtMajorCategoryItemName"></p>
                    <p class="col-md-4 info-edit hide"><span class="cLightGray pr8">排序</span><input type="text" :value="majorItem.mctiOrder" class="txtMajorCategoryItemOrder"></p>
                    <div class="analyItem_Category_fixed mr10">
                        <img src="${ctxStatic}/images/pic/edit_img.png" width="20" height="20" style="cursor:pointer;" class="img_edit btnMajorItemEdit">
                        <img src="${ctxStatic}/images/pic/delete_img.png" width="20" height="20" style="cursor:pointer;" class="img_delete btnMajorItemDel">
                        <img src="${ctxStatic}/images/pic/save_img.png" width="20" height="20" style="cursor:pointer;" class="save_img hide btnMajorItemSave">
                        <img src="${ctxStatic}/images/pic/goback_img.png" width="20" height="20" style="cursor:pointer;" class="goback_img hide btnMajorItemBack">
                    </div>
                </div>
            </div>

            <div v-show="majorItemList == null"  class="analyItem">
                <p class="analyItemTit tx-center">类别项</p>
                <div class="analyItemCon relative">
                   无
                </div>
            </div>

        </div>
    </div>
</div>

<%--VUE2.0 开始--%>
<script>
    // 主营三段类别项VUE实例
    var majorThreeCategoryItemsVue = new Vue({
        el: '#majorThreeCategoryItemsVue',
        data: {
            majorItemList: [], // 主营属性(类别项)集
            majorItemListSize: 0
        },
        created:function(){
            this.buildMajorItemListData();
        },
        methods:{
            // 构建主营属性类别集数据
            buildMajorItemListData:function(){
                var _this = this;
                var majorTypeID = $("#hidZYTypeID").val();
                $.ajax({
                    url:  basePath + '/major-api/two/type/item',
                    type: 'GET',
                    dataType: 'json',
                    data: {majorTypeID},
                    success: function (res) {
                        if (res.statusCode == 0){

                            _this.majorItemList = res.body;
                            if (res.body != null){
                                var length = res.body.length;
                                // 1.设置主营类别项数量,以供设置排序默认值
                                _this.majorItemListSize = length;
//                                $("[data-treetwoid="+ $("#hidZYTreeTwoID").val() +"]").find("td").eq(2).removeClass("cRed").addClass("cGreen").html("完");
                            }


                            setTimeout(function () {
                                debugger
                                // 默认类别名称选中
                                $("#txtMajorCategoryItemName").focus();
                                // 设置当前三段Title
                                $("#spaZYTypeNmae").html($("#hidZYTypeNmae").val());
                            })
                        } else alert(res.statusMsg);
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        }
    })

</script>

