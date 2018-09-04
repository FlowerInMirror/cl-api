<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="TwoStatus">
    <div class="analyItem">
        <p class="analyItemTit tx-center">状态</p>
        <div class="analyItemCon">
            <a href="javascript:" class="circlemark-green circlemark">优</a>

        </div>
    </div>
    <div class="analyItem">
        <p class="analyItemTit tx-center">标准</p>
        <div class="analyItemCon">
            <p class="col-md-4" v-for="twoStandarData in twoStandarDatas"><span class="cLightGray pr8">{{twoStandarData.StandName}}</span>{{twoStandarData.StandValue}}{{twoStandarData.StandUnit}}</p>
        </div>
    </div>
    <div class="analyItem">
        <p class="analyItemTit tx-center">材料</p>
        <div class="analyItemCon">
            <span class="cLightGray pr8">材料</span><span class="pr5">{{MaterialBrandGradeInfoLength}}</span>种
        </div>
    </div>
    <div class="analyItem anItemBor positionClick">
        <p class="analyItemTit tx-center">位置</p>
        <div class="analyItemCon">
            <span class="cLightGray pr8">位置</span><span class="positionText">{{TwoStatusDatas.Position}}</span>
        </div>
    </div>
</div>
<script>
    <%--VUE2.0 开始--%>
    var TwoStatus=new Vue({
        el: '#TwoStatus',
        data: {
            TwoStatusDatas: {},// 状态数据
            twoStandarDatas:[],//标准数据集合
            MaterialBrandGradeInfoLength:0//材料种类
        },
        methods:{
            TwoStatusData:function(){
                var _this=this;
                var AreaId=$("#hidCityID").val();
                var MatId=$("#tabSampleTreeList .tractive").attr("data-treeid");
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/MaterialStandardInfo?MatId='+MatId+'&AreaId='+AreaId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        _this.TwoStatusDatas = res.Body;
                        _this.twoStandarDatas=res.Body.MaterialSpecificationInfo;//标准数据集合
                        _this.MaterialBrandGradeInfoLength=res.Body.MaterialBrandGradeInfo.length;//材料种类
                    },
                    error: function (err) {}
                });
            }
        },
        created:function(){
            // 页面加载构建数据
            this.TwoStatusData();
        }
    })
</script>