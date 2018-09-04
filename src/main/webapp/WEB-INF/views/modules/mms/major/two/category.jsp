<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<style>
    .analyItem_Category_fixed{
        position: absolute;right:0;top:50%;margin-top:-10px;}
</style>
<div id="majorTwoCategoryVue" v-cloak>
    <div class="analyItem">
        <p class="analyItemTit tx-center">类别</p>
        <div class="analyItemCon">
            <p class="col-md-5"><span class="cLightGray pr8">名称</span><input type="text" id="txtCategoryName" placeholder="请输入新增类别名称" class="width120 txtCategory"></p>
            <p class="col-md-5"><span class="cLightGray pr8">排序</span><input type="text" id="txtCategoryOrderNo" :value="majorTypeListSize + 1" class="width120 txtCategory"></p>
            <p class="col-md-2"><input type="button" value="新增" class="uiBtn-blue uiBtn-normal addCategory" style="vertical-align: top"></p>
        </div>
    </div>
    <div class="addCategoryBox">

        <%--类别遍历开始--%>
        <div v-for="(majorType,index) in majorTypeList" class="analyItem anItemBor analyItem_Category" :data-mctname="majorType.mctName" :data-mctid="majorType.mctId">
            <p class="analyItemTit tx-center">类别{{index + 1}}</p>
            <div class="analyItemCon">
                <p class="col-md-5 CategoryLook"><span class="cLightGray pr8">名称</span><span>{{majorType.mctName}}</span></p>
                <p class="col-md-5 CategoryLook"><span class="cLightGray pr8">排序</span><span>{{majorType.mctOrder}}</span></p>
                <p class="col-md-5 CategoryEdit hide" onclick="debarEvent(event)"><span class="cLightGray pr8">名称</span><input type="text" :value="majorType.mctName" class="width120 txtCategory txtCategoryName"></p>
                <p class="col-md-5 CategoryEdit hide" onclick="debarEvent(event)"><span class="cLightGray pr8">排序</span><input type="text" :value="majorType.mctOrder" class="width120 txtCategory txtCategoryOrderNo"></p>
                <div class="analyItem_Category_fixed">
                    <img src="${ctxStatic}/images/pic/edit_img.png" width="20" height="20" class="CategoryEditBtn fl mr5" style="cursor: pointer;">
                    <img src="${ctxStatic}/images/pic/save_img.png" title="保存" width="20" height="20" class="CategorySaveBtn fl mr5 hide" style="cursor: pointer;">
                    <img src="${ctxStatic}/images/pic/delete_img.png" width="20" height="20" class="fl CategoryDeleBtn mr5" style="cursor: pointer;">
                    <img src="${ctxStatic}/images/pic/goback_img.png" width="20" height="20" class="CategoryBackBtn fl mr5 hide" style="cursor: pointer;">
                </div>
            </div>
        </div>

        <div v-show="majorTypeList == null" class="analyItem">
            <p class="analyItemTit tx-center">类别</p>
            <div class="analyItemCon">
                无
            </div>
        </div>

    </div>
</div>

<%--VUE2.0 开始--%>
<script>
    // 主营二段属性类别VUE实例
    var majorTwoCategoryVue = new Vue({
        el: '#majorTwoCategoryVue',
        data: {
            majorTypeList: [],// 主营属性类别集
            majorTypeListSize: 0 // 主营属性类别数量
        },
        created:function(){
            this.buildMajorTypeListData();
        },
        methods:{
            // 构建主营属性类别集数据
            buildMajorTypeListData:function(){
                var _this = this;
                var treeTwoID = $("#hidZYTreeTwoID").val();
                $.ajax({
                    url:  basePath + '/major-api/two/type',
                    type: 'GET',
                    dataType: 'json',
                    data: {treeTwoID},
                    success: function (res) {
                        if (res.statusCode == 0){

                            _this.majorTypeList = res.body;
                            if (res.body != null) _this.majorTypeListSize = res.body.length;

                            // 默认类别名称选中
                            setTimeout(function () {$("#txtCategoryName").focus();})



                        } else alert(res.statusMsg);
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        }
    })

</script>
