<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<!--标准 开始-->
<div id="editMatContrastSta" v-cloak >
    <div class="divMaterialLoadAlert_topm pl10">
        <h2 class="uiTitle2">
            <i class="uiTitle-icon"></i>
            对比标准
        </h2>
        <div class="materialpicture materialTopNavConm pr10">
            <div class="SearchCon pt10">
                <div :class="matContrastSta.totalScore != 0 ? 'analyItem divNewStandardItem hide' : 'analyItem divNewStandardItem'" data-type="1">
                    <p class="analyItemTit tx-center">属性{{matContrastSta.totalScore + 1 | numToStr(matContrastSta.totalScore + 1)}}</p>
                    <div class="analyItemCon relative" style="width:100%">
                        <p class="info-edit ">
                            <span class="cLightGray pr8">
                                <input type="text" class="width120 txtAttrName" placeholder="属性名称" data-input="true" />
                            </span>
                        </p>
                        <div class="dis-il-block imgbox  imgbox2">
                            <img v-show="matContrastSta.totalScore == 0" class="save_img btnSaveComparedAttrItem" src="${ctxStatic}/images/pic/add_img.png?${verStatic}" />
                            <img v-show="matContrastSta.totalScore != 0" class="save_img btnSaveComparedAttrItem" src="${ctxStatic}/images/pic/save_img.png?${verStatic}" />
                            <img v-show="matContrastSta.totalScore != 0" onclick="$(this).closest('div.analyItem').addClass('hide')" src="${ctxStatic}/images/pic/delete_img.png?${verStatic}" />
                        </div>
                    </div>
                </div>
                <%--对比标准对比项遍历 开始--%>
                <div v-for="(contrastSta, index) in matContrastSta.compareds" class="analyItem" :data-attrid="contrastSta.mcaID" data-type="1">
                    <p class="analyItemTit tx-center">属性{{index + 1 | numToStr(index + 1)}}</p>
                    <div class="analyItemCon relative">
                        <p class="info-look">
                            <span class="cLightGray pr8">{{contrastSta.attributeName}}</span>
                        </p>
                        <p class="info-edit hide">
                                    <span class="cLightGray pr8">
                                        <input type="text" class="width120 txtAttrName" placeholder="属性名称" :value="contrastSta.attributeName" />
                                    </span>
                        </p>
                        <div class="dis-il-block imgbox imgbox1" >
                            <img v-show="index == 0" class="img_add" src="${ctxStatic}/images/pic/add_img.png?${verStatic}" />
                            <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png?${verStatic}" />
                            <img class="img_delete" src="${ctxStatic}/images/pic/delete_img.png?${verStatic}" />
                        </div>
                        <div class="dis-il-block imgbox  hide imgbox2">
                            <img class="save_img btnSaveComparedAttrItem" src="${ctxStatic}/images/pic/save_img.png?${verStatic}" />
                            <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png?${verStatic}" />
                        </div>
                        <%--回路积分项--%>
                        <div class="judge-div-small">
                            <input type="text" class="fl judge-span judge-yes active">
                            <input type="text" class="fl judge-span judge-no">
                            <strong class="fl score cGreen fz14 pl5">1分</strong>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="divMaterialLoadAlert_bottom pt10 plr10 boldTopLine">
        <div class="analyItem">
            <p class="analyItemTit tx-center">综合</p>
            <div class="analyItemCon">
                <p class="fl col-md-8 cRed">
                    <span class="cLightGray pr8 cRed">对比属性至少录入一项 | 注:对比属性名称全国唯一(相关对比图针对城市)</span>
                    <strong class="plr5 cRed"></strong>
                </p>
                <p class="fl col-md-4"><span class="cLightGray pr8">得分</span><strong class="fz14 cGreen">{{matContrastSta.totalScore}}分</strong></p>
                <a v-show="matContrastSta.totalScore != 0" href="javascript:" class="circlemark circlemark-green">完</a>
                <a v-show="matContrastSta.totalScore == 0" href="javascript:" class="circlemark circlemark-lightRed">未</a>
            </div>
        </div>
        <div class="tx-center mt5 clearfix mb10 hide">
            <input type="button" value="进入编辑" class="uiBtn-small uiBtn-blue btnOQAUpdate">
        </div>
    </div>
</div>
<!--标准 结束-->

<%--VUE2.0 开始--%>
<script>
    // 编辑材料对比标准 vue应用
    var editMatContrastStaVue = new Vue({
        // 提供一个在页面上已存在的 DOM 元素作为 Vue 实例的挂载目标。
        el: '#editMatContrastSta',
        // Vue实例的数据对象。Vue 将会递归将 data 的属性转换为 getter/setter，从而让 data 的属性能够响应数据变化
        data: {
            matContrastSta: {}// 材料对比标准
        },
        // 实例已经创建完成之后被调用。在这一步，实例已完成以下的配置：数据观测(data observer)，属性和方法的运算， watch/event 事件回调。然而，挂载阶段还没开始，$el 属性目前不可见
        created() {
            // 页面加载构建数据
            this.buildContrastSta();// 对比标准数据
        },
        // 局部过滤器
        filters: {
        },
        // methods将被混入到 Vue 实例中，可以直接通过 VM 实例访问这些方法，或者在指令表达式中使用 方法中的 this自动绑定为 Vue 实例
        methods: {
            // 对比标准数据
            buildContrastSta: function () {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                $.ajax({
                    url: basePath + '/sublibrary-api/city_three_section/platform/sta_contrast',
                    type: 'GET',
                    dataType: 'json',
                    data: {treeFourID},
                    success: function (res) {_this.matContrastSta = res.body;},
                    error: function (err) {alert("操作出错！");}
                });
            },
            // 删除材料参数 通过:参数ID,四级科目ID
            deleteMatParam : function (paraID) {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                if(confirm("确定要删除材料参数吗？")) {
                    $.ajax({
                        url: basePath + '/standard_para-api/delete',
                        type: 'POST',
                        data: {'paraID':paraID,treeFourID},
                        dataType: 'json',
                        success: function (res) {
                            alert(res.statusMsg)
                        },
                        error: function (err) {alert("操作出错！");}
                    });
                }
            }
        }
    });
</script>

<script>
//    $(".lbl_base_compared_attr").removeClass("cGreen").removeClass("cRed");
//    $(".lbl_base_compared_attr").addClass("cGreen").html("3项");
</script>

<script>
    //清空数据
    function ClearStandard() {
    }
    $(function () {
        setTimeout(function () {
            $(".img_edit").click(function () {
                var divObj = $(this).closest("div.analyItem");

                $(divObj).find(".info-look").hide();
                $(divObj).find(".info-edit").show();

                $(this).parent(".imgbox").hide();
                $(this).parent(".imgbox").next(".imgbox").show();
            });
            $(".goback_img").click(function () {
                var divObj = $(this).closest("div.analyItem");
                $(divObj).find(".info-look").show();
                $(divObj).find(".info-edit").hide();

                $(this).parents(".analyItemCon").find(".imgbox1").show();
                $(this).parent(".imgbox2").hide();
            });
            $(".img_add").click(function () {
                $(".divNewStandardItem").removeClass("hide");
            });
        });
    });
</script>