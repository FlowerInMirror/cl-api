<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<!--搜索词 开始-->
<div id="editMatSearchTerm" v-cloak >
    <div class="divMaterialLoadAlert_topm pl10">
        <h2 class="uiTitle2">
            <i class="uiTitle-icon"></i>
            搜索词
        </h2>
        <div class="materialpicture materialTopNavConm pr10">
            <div class="SearchCon">
                <%--搜索词--%>
                <div class="analyItem">
                    <p class="analyItemTit tx-center">搜索词</p>
                    <div class="analyItemCon relative">
                        <div class="analyItemConText">
                        <p class="info-look">
                            <span class="cLightGray pr10 dis-il-block">
                               {{matSearchTerm.searchItemsEchoStr}}
                            </span>
                        </p>
                        <p class="info-edit hide">
                            <span class="cLightGray pr8">
                                <input type="text" id="searchItemsStrID" name="inputTagator" class="inputTagator fl mr10 txtSearchContent"    />
                            </span>
                        </p>
                        <p class="col-md-12 info-edit hide">
                            <span class="cRed">注：以“,”分隔各项搜索词</span>
                        </p>
                        <div class="dis-il-block imgbox imgbox1">
                            <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png?${verStatic}" />
                        </div>
                        <div class="dis-il-block imgbox  hide imgbox2">
                            <img class="save_img btnSaveSearchItem" src="${ctxStatic}/images/pic/save_img.png?${verStatic}" data-type="1" />
                            <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png?${verStatic}" />
                        </div>
                        <div class="judge-div-sample judge-div-small">
                            <input type="text" :class="matSearchTerm.searchItemScore != 0 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                            <input type="text" :class="matSearchTerm.searchItemScore == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                            <strong class="fl score cGreen fz14 pl5">{{matSearchTerm.searchItemScore}}分</strong>
                        </div>
                    </div>
                    </div>
                </div>

                 <%--用途--%>
                <div class="analyItem">
                    <p class="analyItemTit tx-center">用途</p>
                    <div class="analyItemCon relative">
                        <div class="analyItemConText">
                        <p class="info-look">
                            <span class="cLightGray pr20 dis-il-block">
                                {{matSearchTerm.useItemsEchoStr}}
                            </span>
                        </p>
                        <p class="info-edit hide">
                            <span class="cLightGray pr8">
                                <input type="text" name="inputTagator" class="inputTagator fl mr10 txtSearchContent"   :value="matSearchTerm.useItemsStr" />
                            </span>
                        </p>
                        <p class="col-md-12 info-edit hide">
                            <span class="cRed">注：以“,”分隔各项用途</span>
                        </p>
                        <div class="dis-il-block imgbox imgbox1">
                            <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png?${verStatic}" />
                        </div>
                        <div class="dis-il-block imgbox  hide imgbox2">
                            <img class="save_img btnSaveSearchItem" src="${ctxStatic}/images/pic/save_img.png?${verStatic}" data-type="2" />
                            <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png?${verStatic}" />
                        </div>
                        <div class="judge-div-sample judge-div-small">
                            <input type="text" :class="matSearchTerm.useScore != 0 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                            <input type="text" :class="matSearchTerm.useScore == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                            <strong class="fl score cGreen fz14 pl5">{{matSearchTerm.useScore}}分</strong>
                        </div>
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
                    <span class="cLightGray pr8 cRed">搜索词至少录入1项/用途至少录入1项 | 注:全国统一搜索词</span>
                    <strong class="plr5 cRed"></strong>
                </p>
                <p class="fl col-md-4"><span class="cLightGray pr8">得分</span><strong class="fz14 cGreen">{{matSearchTerm.totalScore}}分</strong></p>
                <a v-show="matSearchTerm.totalScore == 2" href="javascript:" class="circlemark circlemark-green">1</a>
                <a v-show="matSearchTerm.totalScore != 2" href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
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
    var editMatSearchTermVue = new Vue({
        // 提供一个在页面上已存在的 DOM 元素作为 Vue 实例的挂载目标。
        el: '#editMatSearchTerm',
        // Vue实例的数据对象。Vue 将会递归将 data 的属性转换为 getter/setter，从而让 data 的属性能够响应数据变化
        data: {
            matSearchTerm: {}// 材料基础信息
        },
        // 实例已经创建完成之后被调用。在这一步，实例已完成以下的配置：数据观测(data observer)，属性和方法的运算， watch/event 事件回调。然而，挂载阶段还没开始，$el 属性目前不可见
        created() {
            // 页面加载构建数据
            this.buildMatSearchTermData();// 搜错词 & 用途 数据
        },
        // 局部过滤器
        filters: {
        },
        // methods将被混入到 Vue 实例中，可以直接通过 VM 实例访问这些方法，或者在指令表达式中使用 方法中的 this自动绑定为 Vue 实例
        methods: {
            // 搜错词 & 用途 数据
            buildMatSearchTermData: function () {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                $.ajax({
                    url: basePath + '/sublibrary-api/city_three_section/platform/search',
                    type: 'GET',
                    dataType: 'json',
                    data: {treeFourID},
                    success: function (res) {
                        _this.matSearchTerm = res.body;
                        $("#searchItemsStrID").val(res.body.searchItemsStr);

                    },
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
//    $(".lbl_tree_search").removeClass("cGreen").removeClass("cRed");
//    $(".lbl_tree_search").addClass("cGreen").html("2项");
//    $(".lbl_tree_use").removeClass("cGreen").removeClass("cRed");
//    $(".lbl_tree_use").addClass("cGreen").html("15项");
</script>

<script>
    //清空数据
    function ClearStandard() {
    }
    $(function () {
        setTimeout(function () {
            //插件What?
            $('.inputTagator').tagator({ autocomplete: [] });
        })
        $(document).on("click", ".img_edit", function () {
            var divObj = $(this).closest("div.analyItem");

            $(divObj).find(".info-look").hide();
            $(divObj).find(".info-edit").show();

            $(this).parent(".imgbox").hide();
            $(this).parent(".imgbox").next(".imgbox").show();
        });
        $(document).on("click", ".goback_img", function () {
            var divObj = $(this).closest("div.analyItem");
            $(divObj).find(".info-look").show();
            $(divObj).find(".info-edit").hide();

            $(this).parents(".analyItemCon").find(".imgbox1").show();
            $(this).parent(".imgbox2").hide();
        });
    });
</script>