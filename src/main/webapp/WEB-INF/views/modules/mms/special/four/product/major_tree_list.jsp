<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="majorTreeListVue">
    <div class="qingdanHead cha2 Temp_item">
        <table class="uiTable" id="tb_3">
            <thead>
            <tr>
                <th width="10%">序号</th>
                <th width="30%">一级名称</th>
                <th width="30%">主营名称</th>
                <th >操作</th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="Temp_itemscroll">
        <table class="uiTable trHover1" id="tb_4">
            <tbody id="tabMajorTreeList">
                <tr v-for="(majorTree,index) in majorTreeList" :data-treetwoid="majorTree.TreeID" >

                    <td width="10%">{{index + 1}}</td>
                    <td width="30%">{{majorTree.TreeOneName}}</td>
                    <td width="30%">{{majorTree.TreeName}}</td>
                    <td>
                        <input v-show="majorTree.HavAttr == 1" type="button" value="选择此主营" class="uiBtn-small uiBtn-blue butSelectMajorTree">
                        <span  v-show="majorTree.HavAttr == 0" >集团:暂未添加，此主营属性类别</span>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<%--VUE2.0 开始--%>
<script>
    var majorTreeListVue = new Vue({
        el: '#majorTreeListVue',
        data: {
            majorTreeList: [] // 主营树列表
        },
        created() {
            this.buildMajorTreeListData();
        },
        methods: {
            // 构建主营树列表
            buildMajorTreeListData: function () {
                var _this = this;
                var userID = $("#hidZXUserID").val();
                $.ajax({
                    url: gcApiSite + 'CMaterial/GetMainTreeItemsByUserID',
                    type: 'GET',
                    dataType: 'json',
                    data: {userID},
                    success: function (res) {
                        _this.majorTreeList = res.Body;
                        setTimeout(function () {
                            // 隔行换色
                            rxued.table.LChangeapart($("#tabMajorTreeList tr"), "#fff", "#f9f9f9");
                        });

                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        }
    });
</script>