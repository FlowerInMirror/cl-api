<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<style>
    .QRcodeImg{margin-top:12px;}
</style>
<%--二维码--%>
<script src="${ctxStatic}/js/jquery.qrcode.min.js?${verStatic}"></script>

<div id="twoMaterial">
    <div class="analyItem">
        <p class="analyItemTit tx-center">状态</p>
        <div class="analyItemCon clearfix">

        </div>
    </div>
    <div class="analyItem" v-for="twoMaterialData in twoMaterialDatas">
        <p class="analyItemTit tx-center">{{twoMaterialData.Level}}</p>
        <div class="analyItemCon clearfix relative">
            <div class="col-md-9">
                <h2 class="fz14 mb5">{{twoMaterialData.MatName}}</h2>
                <p class="lh22">品牌：{{twoMaterialData.BrandName}}</p>
                <p class="lh22">规格：{{twoMaterialData.MatSpec}}</p>
                <p class="lh22">编码：{{twoMaterialData.Code}}</p>
            </div>
            <div class="brandQRCode2 QRcodeImg" :data-matid="twoMaterialData.MatId" ></div>
        </div>
    </div>
</div>
<script>
    <%--VUE2.0 开始--%>
    var TwoStandard=new Vue({
        el: '#twoMaterial',
        data: {
            twoMaterialDatas: [],
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
                        _this.twoMaterialDatas = res.Body.MaterialBrandGradeInfo;
                        // $nextTick 是在下次 DOM 更新循环结束之后执行延迟回调，在修改数据之后使用 $nextTick，则可以在回调中获取更新后的 DOM，API 文档中官方示例如下：
                        _this.$nextTick(function () {
                            // DOM is now updated DOM现在正在更新
                            // `this` is bound to the current instance `this‘绑定到当前实例
                            // 生成二维码
                            $(".brandQRCode2").each(function (index, domEle) {
                                var matID = $(domEle).data('matid');
                                var cityID = $("#hidCityID").val();
                                // 生成规则如下
                                var url = 'http://c.rxjyzx.com/SD/'+matID+'/'+cityID;
                                // 例:http://c.rxjyzx.com/SD/33A923C1-E74B-4911-AFFB-C52DE38347BD/12
                                $(domEle).attr("data-href", url);
                                $(domEle).qrcode({
                                    width: 84,
                                    height: 84,
                                    correctLevel: 0,
                                    background: "#efeef3", //背景颜色
                                    foreground: "black", //前景颜色
                                    text: url
                                });
                            });
                        })
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
