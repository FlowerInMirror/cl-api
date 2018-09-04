<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="twoStandard">
    <div class="analyItem">
        <p class="analyItemTit tx-center">状态</p>
        <div class="analyItemCon clearfix">

        </div>
    </div>
    <div class="analyItem">
        <p class="analyItemTit tx-center">基础</p>
        <div class="analyItemCon clearfix">
            <p class="fl col-md-4"><span class="cLightGray pr8">一级</span><span>{{twoStandardDatas.TreeOneName}}</span></p>
            <p class="fl col-md-4"><span class="cLightGray pr8">二级</span><span>{{twoStandardDatas.TreeTwoName}}</span></p>
            <p class="fl col-md-4"><span class="cLightGray pr8">名称</span><span :title="twoStandardDatas.MatName">{{twoStandardDatas.MatName| cutout('8','','...')}}</span></p>
            <p class="fl col-md-4"><span class="cLightGray pr8">规格</span><span :title="twoStandardDatas.MatSpec">{{twoStandardDatas.MatSpec| cutout('8','','...')}}</span></p>
            <p class="fl col-md-4"><span class="cLightGray pr8">单位</span>{{twoStandardDatas.UnitName}}</p>
            <p class="fl col-md-4"><span class="cLightGray pr8">分类</span>{{twoStandardDatas.ProductType}}</p>
        </div>
    </div>
    <div class="analyItem">
        <p class="analyItemTit tx-center">小样</p>
        <div class="analyItemCon clearfix">
            <p class="col-md-4" v-for="twoSmallSampleData in twoSmallSampleDatas"><span class="cLightGray pr8">{{twoSmallSampleData.StandName}}</span>{{twoSmallSampleData.StandValue}}{{twoSmallSampleData.StandUnit}}</p>
        </div>
    </div>
    <div class="analyItem">
        <p class="analyItemTit tx-center">取样方法</p>
        <div class="analyItemCon clearfix">{{twoStandardDatas.MockUpSamplingRemark}}</div>
    </div>
</div>
<script>
    <%--VUE2.0 开始--%>
    var TwoStandard=new Vue({
        el: '#twoStandard',
        data: {
            twoStandardDatas: {},//基础数据
            twoSmallSampleDatas:[]//小样数据集合
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
            TwoStandardData:function(){
                var _this=this;
                var AreaId=$("#hidCityID").val();
                var MatId=$("#tabSampleTreeList .tractive").attr("data-treeid");
                $.ajax({
                    url: gcApiSite + '/APPSuppliers/MaterialStandardInfo?MatId='+MatId+'&AreaId='+AreaId,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        _this.twoStandardDatas = res.Body;//基础数据
                        _this.twoSmallSampleDatas=res.Body.MaterialSpecificationInfo;//小样数据集合
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        },
        created:function(){
            // 页面加载构建数据
            this.TwoStandardData();
        }
    })
</script>