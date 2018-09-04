<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="TwoPosition">
    <div class="analyItem">
        <p class="analyItemTit tx-center">状态</p>
        <div class="analyItemCon clearfix">

        </div>
    </div>
    <div class="analyItem anItemBor positionClick">
        <p class="analyItemTit tx-center">位置</p>
        <div class="analyItemCon clearfix positionText">{{twoPositionDatas.Position}}</div>
    </div>
</div>
<script>
    <%--VUE2.0 开始--%>
    var TwoStandard=new Vue({
        el: '#TwoPosition',
        data: {
            twoPositionDatas: [],
        },
        methods:{
            twoMaterialData:function(){
                var _this=this;
                var AreaId=$("#hidCityID").val();
                var MatId=$("#tabSampleTreeList .tractive").attr("data-treeid");
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/MaterialStandardInfo?MatId='+MatId+'&AreaId='+AreaId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        debugger;
                        _this.twoPositionDatas = res.Body;
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        },
        created:function(){
            // 页面加载构建数据
            this.twoMaterialData();
        }
    })
</script>