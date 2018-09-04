<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<div id="sTwoProductVue" v-cloak >
    <%--状态--%>
    <div class="analyItem">
        <p class="analyItemTit tx-center">状态</p>
        <div class="analyItemCon">
            <input id="btnAddSpecialProduct" type="button" value="添加" title="添加专项产品" class="uiBtn-blue" style="position:absolute;top:2px;right:2px;width:35px;">
            <input id="btnfreshSpecialProduct" type="button" value="同步" title="同步新增或修改专项产品数据" class="uiBtn-blue" style="position:absolute;top:25px;right:2px;width:35px;">
        </div>
    </div>

    <%-- 专项产品列表 --%>
    <div v-for="(product,index) in specialPros" class="analyItem" >
        <p class="analyItemTit tx-center">产品{{index + 1 }}</p>
        <div class="analyItemCon" :data-proid="product.proID">
            <p class="col-md-12"><span class="cLightGray pr8">名称</span><span  :title="product.proName">{{product.proName}}</span></p>
            <p class="col-md-6"><span class="cLightGray pr8">价格</span><span title="组合套餐最高价~组合套餐最低价">¥{{product.maxPrice | holdTwoDecimal}}~{{product.minPrice | holdTwoDecimal}}</span></p>
            <p class="col-md-4"><span class="cLightGray pr8">上架状态</span><span>{{product.proStatus == 0 ? '已上架' : '未上架'}}</span></p>
            <a id="zxProBrowse" :href="'${basePath}/public-web/special/open-product-browsing?proid=' + product.proID " target="_blank" class="uiLink" style="position: absolute;right: 20px;font-size: 10px;color: #0099ff;text-decoration: underline;margin-top: 3.5px;">预览</a>
            <a id="zxProEdit" href="javascript:" :data-proid="product.proID"  target="_blank" class="uiLink" style="position: absolute;right: 20px;font-size: 10px;color: #0099ff;text-decoration: underline;margin-top: 30.5px;">编辑</a>
        </div>

    </div>

    <%--无产品提示--%>
    <div v-show="specialPros == null" :class="specialPros == null ? '' : 'anItemBor'" class="analyItem " >
        <p class="analyItemTit tx-center">产品</p>
        <div class="analyItemCon">暂无</div>
    </div>

</div>

<%--VUE2.0 开始--%>
<script>
    var sTwoProductVue = new Vue({
        el: '#sTwoProductVue',
        data: {
            specialPros:[], // 专项产品集
        },
        created:function() {
            this.buildSpecialProsData();// 报价>基础
        },
        methods: {
            // 构建报价基础数据
            buildSpecialProsData: function () {

                var _this = this;
                var cityID = $("#hidZXUserCityID").val();
                var spDealerID= $("#hidZXUserID").val(); // 材料商ID
                $.ajax({
                    url: basePath + '/special-api/two/product',
                    type: 'GET',
                    dataType: 'json',
                    data: {cityID,spDealerID},
                    success: function (res) {
                        _this.specialPros = res.body;
                    },
                    error: function (err) {alert("操作出错！");}
                });
            }
        }
    });
</script>
