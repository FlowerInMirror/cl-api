<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="matRelevanceListVue">
    <div class="qingdanHead cha2 Temp_item">
        <table class="uiTable" id="tb_3">
            <thead>
            <tr>
                <th width="3%">档次</th>
                <th width="16%">名称</th>
                <th width="20%">规格</th>
                <th width="8%">单位</th>
                <th width="12%">型号</th>
                <th width="12%">品牌</th>
                <th width="12%">成本</th>
                <th>操作</th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="Temp_itemscroll">
        <table class="uiTable" id="tb_4">
            <tbody id="tabDataToMaterialToSearchTreeThree">
                <tr v-for="matItem in matLists" :data-matid="matItem.MatID" :data-matname="matItem.MatName" :data-matspec="matItem.MatSpec">
                    <td width="3%" :title="matItem.MatCode">{{matItem.MatLevel | levelFlagTOABC}}</td>
                    <td width="16%">{{matItem.MatName}}</td>
                    <td width="20%">{{matItem.MatSpec}}</td>
                    <td width="8%">{{matItem.UnitName}}</td>
                    <td width="12%">{{matItem.BrandType}}</td>
                    <td width="12%">{{matItem.BrandName}}</td>
                    <td width="12%">{{matItem.MatPrice}}</td>
                    <td><input type="button" value="选择此材料" class="uiBtn-small uiBtn-blue butSelectProRelevanceMat"></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<%--VUE2.0 开始--%>
<script>
    var matRelevanceListVue = new Vue({
        el: '#matRelevanceListVue',
        data: {
            matLists: [] // 材料列表(专项产品关联材料列表)
        },
        created() {
            this.buildMatListsData();
        },
        methods: {
            // 关联材料列表
            buildMatListsData: function () {
                var _this = this;
                var UserID = $("#hidZXUserID").val();
                var city = $("#hidZXUserCityID").val();
                var treeID = $("#hidZXTreeThreeID").val();
                $.ajax({
                    url: gcApiSite + 'CMaterial/GetMaterialItemsByTreeThreeID',
                    type: 'GET',
                    dataType: 'json',
                    data: {UserID,city,treeID},
                    success: function (res) {
                        _this.matLists = res.Body;
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        }
    });
</script>
