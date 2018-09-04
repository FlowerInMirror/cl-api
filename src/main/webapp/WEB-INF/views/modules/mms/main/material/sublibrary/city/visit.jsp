<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="sublibraryVisitVue">
    <div v-for="(item, index) in visits" :class="visitVo.vOperattype == item.vOperattype ? 'tx-right' : 'tx-left'">
        <div class="visi-listtop">
            <div v-if="visitVo.vOperattype == item.vOperattype" >
                 <span  class="visi-date mr5">{{item.vCreatetime | timeFormattingVisit}}</span>
                 <span class="visi-name">集</span>
            </div>
            <div v-else >
                <span class="visi-name">集</span>
                <span class="visi-date mr5">{{item.vCreatetime | timeFormattingVisit}}</span>
            </div>
        </div>
        <p class="visi-listtxt cBlue">
            {{item.vContent}}
        </p>
    </div>
</div>

<%--VUE2.0 开始--%>
<script>


    var sublibraryVisitVue = new Vue({
        el: '#sublibraryVisitVue',
        data: {
            visits: {}, // 回访记录集
            visitVo: {} // 检索回访记录包装类
        },
        created:function() {
            this.buildSublibraryVisitData(); // 构建 子库回访数据
        },
        methods: {
            // 构建 子库回访数据
            buildSublibraryVisitData: function () {
                var _this = this;
                var obj = new Object();
                obj.vPiid = $("#hidTreeID").val();
                obj.vUserid = $("#hidCityID").val();
                obj.vType = 100;
                $.ajax({
                    type:'POST',
                    url: basePath +'/visit-api/load',
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success:function (data) {
                        _this.visitVo = data.body.visitVo;
                        if (data.body.visits != null){
                            _this.visits = data.body.visits;

                        }
                        setTimeout(function(){
                            var ovisitedConH = $("#sublibraryVisitVue").closest(".visiterecord-rgt-scroll-con").height();
                            var ovisitedboxH = $("#sublibraryVisitVue").closest(".visiterecord-rgt-scroll").height();
                            $("#sublibraryVisitVue").closest(".visiterecord-rgt-scroll").scrollTop(ovisitedConH - ovisitedboxH);
                        },300)

                    }
                })
            }
        }
    });
</script>
