// "======================== 公用 ==========================="

// 事件排除
function debarEvent(event){event.stopPropagation();};
function countS() {
    $(".layerRtb-scroll").each(function () {
        var oheights=0;
        if($(this).closest(".layerRtb").find(".j_chas").length>0)
        {
            for(var i=0;i<$(this).closest(".layerRtb").find(".j_chas").length;i++)
            {
                oheights+=$(this).closest(".layerRtb").find(".j_chas").eq(i).outerHeight(true);
            }
        }
        $(this).height($(window).height() - $("#topnav").outerHeight(true) - 20-oheights)
    })
    $(window).resize(function() {
        $(".layerRtb-scroll").each(function () {
            var oheights=0;
            if($(this).closest(".layerRtb").find(".j_chas").length>0)
            {
                for(var i=0;i<$(this).closest(".layerRtb").find(".j_chas").length;i++)
                {
                    oheights+=$(this).closest(".layerRtb").find(".j_chas").eq(i).outerHeight(true);
                }
            }
            $(this).height($(window).height() - $("#topnav").outerHeight(true) - 20-oheights)
        })
    });
}
// "======================== 一段 ==========================="

// 一段列表===左侧列表行点击
$(document).on("click","#tabMajorTreeList tr",function(){

    $(this).addClass("tractive").siblings().removeClass("tractive");
    $("#majormain").find(".uiTab9 li:eq(0)").click(); // 默认点击类别

    $("#hidZYTreeTwoID").val($(this).data("treetwoid")); // 当前选择主营二级类ID
});

// 检索:主营名称右侧放大镜点击
$(document).on("click",".searchMajorName",function(){
    $(".dailyftSearch").show()
});
// 检索:文本框回车事件
$(document).on("keydown","#txtMajorSearch",function(event){
    if (event.keyCode == 13) $(".uiText-searchIcon").click();
});
// 检索:检索框右侧发放大镜点击
$(document).on("click",".uiText-searchIcon",function(){
    $(".dailyftSearch").hide();
    var oinputVal=$.trim($("#txtMajorSearch").val());
    if(oinputVal!=""){
        $("#tabMajorTreeList tr").hide();
        $("#tabMajorTreeList tr[data-majorname*='"+ oinputVal +"']").show().eq(0).click();
    }else{
        $("#tabMajorTreeList tr").show().eq(0).click();
    }
});


// "======================== 二段 ==========================="

// 二段导航===主选项卡
$(document).on("click","#majormain .j_uiTab9 li",function(){
    var _othisIndex=$(this).index();
    $(this).addClass("uiTab9-active").siblings().removeClass("uiTab9-active");
    $('#majormain .uiTab9Con').hide().eq(_othisIndex).show();
    $("#CategoryTwo").load(basePath+"/public-web/major/two-category",function(){
        countMiddle();
        countRight();
    });
    $(".layerRtb").animate({
        left: '-102%',
    });
});

//新增类别
$(document).on("click",".addCategory",function(){
    var mctName = $("#txtCategoryName").val(); // 类别名称
    var mctOrder = $("#txtCategoryOrderNo").val(); // 类别排序
    var mctTreetwoid = $("#hidZYTreeTwoID").val(); // 主营二级科目ID
    var userNo = $("#hidUserNo").val(); // 当前操作用户

    // 校验(1.类别名称不能为空,2.排序字段不能为空)
    if (mctName == "" ) {alert("类别名称不能为空");return;};
    if (mctName.length > 24 ){alert("类别名称长度,不能大于24个字符");return;};
    if (mctOrder === "" ) {alert("排序字段不能为空"); return;};
    if (isNaN(mctOrder)) {alert("请输入正确的排序字段值"); return;};

    // 提交 (保存内容:主营二级科目ID/名称/排序)
    var obj = {};
    obj.mctName = mctName;
    obj.mctOrder = mctOrder;
    obj.mctTreetwoid = mctTreetwoid;
    obj.userNo = userNo;
    $.ajax({
        type: 'POST',
        url: basePath + "/major_type-api/add",
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (msg) {
            if (msg.statusMsg == "success") {

                // 保存成功 重载二段类别
                $(".uiTab9-active").click();

            } else alert(msg.statusMsg);
        },
        error: function (err) {alert("系统异常 请联系系统管理员"); return;}
    });
});
// 确认新增类别回车事件
$(document).on("keydown",".txtCategory",function(e){
    if (e.keyCode == 13) $(".addCategory").click();
});

// 类别编辑按钮
$(document).on("click",".CategoryEditBtn",function(event){
    event.stopPropagation();

    // 打开编辑按钮前进行校验:是否其他项已经保存
    if($(".CategorySaveBtn[data-lei='1']").length > 0){alert("请先完成已选项编辑，再进行操作!");return;};

    $(this).closest(".analyItem_Category").find(".CategoryLook").hide();
    $(this).closest(".analyItem_Category").find(".CategoryEdit").show();
    $(this).closest(".analyItem_Category").find(".CategoryDeleBtn").hide();
    $(this).closest(".analyItem_Category").find(".CategoryEditBtn").hide();
    $(this).closest(".analyItem_Category").find(".CategorySaveBtn").show().attr("data-lei","1");
    $(this).closest(".analyItem_Category").find(".CategoryBackBtn").show();
});
// 类别返回按钮
$(document).on("click",".CategoryBackBtn",function(event){
    event.stopPropagation();

    $(this).closest(".analyItem_Category").find(".CategoryLook").show();
    $(this).closest(".analyItem_Category").find(".CategoryEdit").hide();
    $(this).closest(".analyItem_Category").find(".CategoryDeleBtn").show();
    $(this).closest(".analyItem_Category").find(".CategoryEditBtn").show();
    $(this).closest(".analyItem_Category").find(".CategorySaveBtn").hide().removeAttr("data-lei");
    $(this).closest(".analyItem_Category").find(".CategoryBackBtn").hide();
});
// 类别保存更新按钮(更新内容:类别名称/类别排序)
$(document).on("click",".CategorySaveBtn",function(event){
    event.stopPropagation();
    debugger

    var mctName = $(this).closest(".analyItem_Category_fixed").prevAll().find(".txtCategoryName").val(); // 类别名称
    var mctOrder = $(this).closest(".analyItem_Category_fixed").prevAll().find(".txtCategoryOrderNo").val(); // 类别排序
    var mctTreetwoid = $("#hidZYTreeTwoID").val(); // 主营二级科目ID
    var userNo = $("#hidUserNo").val(); // 当前操作用户

    // 校验(1.类别名称不能为空,2.排序字段不能为空)
    if (mctName == "" ) {alert("类别名称不能为空");return;};
    if (mctName.length > 24 ){alert("类别名称长度,不能大于24个字符");return;};
    if (mctOrder === "" ) {alert("排序字段不能为空"); return;};
    if (isNaN(mctOrder)) {alert("请输入正确的排序字段值"); return;};

    // 更新 (更新内容:主营二级科目ID/名称/排序)
    var obj = {};
    obj.mctId = $(this).closest(".analyItem_Category").data("mctid"); // 当前操作类别ID
    obj.mctTreetwoid = mctTreetwoid; // 当前主营二级科目ID
    obj.mctName = mctName; // 类别名称
    obj.mctOrder = mctOrder; // 类别排序
    obj.userNo = $("#hidUserNo").val(); // 当前操作用户
    $.ajax({
        type: 'POST',
        url: basePath + "/major_type-api/update",
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (msg) {
            if (msg.statusMsg == "success") {

                // update 成功 重载二段类别
                $(".uiTab9-active").click();

            } else alert(msg.statusMsg);
        },
        error: function (err) {alert("系统异常 请联系系统管理员"); return;}
    });
});


// 类别删除按钮(删除内容:当前主营科目下类比)
$(document).on("click",".CategoryDeleBtn",function(event){
    event.stopPropagation();

    if (window.confirm("确认删除此类别吗?")){
        var obj = {};
        obj.mctTreetwoid = $("#hidZYTreeTwoID").val(); // 当前主营二级科目ID
        obj.mctId = $(this).closest(".analyItem_Category").data("mctid"); // 当前操作类别ID
        obj.userNo = $("#hidUserNo").val(); // 当前操作用户
        $.ajax({
            type: 'POST',
            url: basePath + "/major_type-api/delete",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                if (msg.statusMsg == "success") {

                    // delete 成功 重载二段类别
                    $(".uiTab9-active").click();

                } else alert(msg.statusMsg);
            },
            error: function (err) {alert("系统异常 请联系系统管理员"); return;}
        });
    }
});



// "======================== 三段 ==========================="

// 类别点击弹出类别项
$(document).on("click",".analyItem_Category:not()",function () {
debugger
    $("#hidZYTypeID").val($(this).data("mctid")); // 当前操作的类别
    $("#hidZYTypeNmae").val($(this).attr("data-mctname")); // 当前操作的类别名称
    $(this).addClass("anItemBor-active").siblings().removeClass("anItemBor-active");
    $(".layerRtb").not(".CategoryAlert").animate({
        left: '-100%',
    },function(){
        $(this).hide();
    });
    $(".CategoryAlert").show().load(basePath+"/public-web/major/three-category-items",function(){
        countS()
    });
    $(".CategoryAlert").stop().animate({
        left: "0px"
    });
});
// 新增类别项按钮
$(document).on("click",".add_categorys",function(){
    var mctiName = $("#txtMajorCategoryItemName").val(); // 类别项名称
    var mctiOrder = $("#txtMajorCategoryItemOrder").val(); // 类别项排序
    var mctiMctid = $("#hidZYTypeID").val(); // 当前操作的类别ID
    var userNo = $("#hidUserNo").val(); // 当前操作用户

    // 校验(1.类别名称不能为空,2.排序字段不能为空)
    if (mctiName == "" ) {alert("类别项名称不能为空");return;};
    if (mctiName.length > 24 ){alert("类别项名称长度,不能大于24个字符");return;};
    if (mctiOrder === "" ) {alert("排序字段不能为空"); return;};
    if (isNaN(mctiOrder)) {alert("请输入正确的排序字段值"); return;};

    // 提交 (保存内容:主营二级科目ID/名称/排序)
    var obj = {};
    obj.mctiName = mctiName;
    obj.mctiOrder = mctiOrder;
    obj.mctiMctid = mctiMctid;
    obj.userNo = userNo;
    $.ajax({
        type: 'POST',
        url: basePath + "/major_type_item-api/add",
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (msg) {
            if (msg.statusMsg == "success") {

                // 保存成功 重载三段类别项
                $(".anItemBor-active").click();

            } else alert(msg.statusMsg);
        },
        error: function (err) {alert("系统异常 请联系系统管理员"); return;}
    });
});
// 确认新增类别项回车事件
$(document).on("keydown",".txtMajorCategoryItem",function(e){
    if (e.keyCode == 13) $(".add_categorys").click();
});

// 编辑-类别项按钮
$(document).on("click",".btnMajorItemEdit",function(){
    debugger
    // 打开编辑按钮前进行校验:是否其他项已经保存


    if($(".btnMajorItemSave[data-lei='1']").length > 0){alert("请先完成已选项编辑，再进行操作!");return;};

    $(".btnMajorItemSave").attr("data-lei","1");

    $(this).hide();
    $(this).closest(".analyItem").find(".img_delete").hide();

    $(this).closest(".analyItem").find(".save_img").show();
    $(this).closest(".analyItem").find(".goback_img").show();

    $(this).closest(".analyItem").find(".info-edit").show();
    $(this).closest(".analyItem").find(".info-look").hide();
    countS();
});
// 返回-类别项按钮
$(document).on("click",".btnMajorItemBack",function(){

    $(".btnMajorItemSave").removeAttr("data-lei");

    $(this).hide();
    $(this).closest(".analyItem").find(".img_delete").show();

    $(this).closest(".analyItem").find(".save_img").hide();
    $(this).closest(".analyItem").find(".goback_img").hide();

    $(this).closest(".analyItem").find(".info-edit").hide();
    $(this).closest(".analyItem").find(".info-look").show();
    $(this).closest(".analyItem").find(".btnMajorItemEdit").show();
    countS();
});
// 保存-编辑类别项按钮
$(document).on("click",".btnMajorItemSave",function(){
    var mctiName = $(this).closest(".boxMajorItem").find(".txtMajorCategoryItemName").val(); // 类别项名称
    var mctiOrder = $(this).closest(".boxMajorItem").find(".txtMajorCategoryItemOrder").val(); // 类别项排序
    var mctiMctid = $("#hidZYTypeID").val(); // 当前操作的类别ID
    var userNo = $("#hidUserNo").val(); // 当前操作用户

    // 校验(1.类别名称不能为空,2.排序字段不能为空)
    if (mctiName == "" ) {alert("类别项名称不能为空");return;};
    if (mctiName.length > 24 ){alert("类别项名称长度,不能大于24个字符");return;};
    if (mctiOrder === "" ) {alert("排序字段不能为空"); return;};
    if (isNaN(mctiOrder)) {alert("请输入正确的排序字段值"); return;};

    // 提交 (保存内容:主营二级科目ID/名称/排序)
    var obj = {};
    obj.mctiId = $(this).closest(".boxMajorItem").data("mctiid"); // 当前操作类别项ID
    obj.mctiName = mctiName;
    obj.mctiOrder = mctiOrder;
    obj.mctiMctid = mctiMctid;
    obj.userNo = userNo;
    $.ajax({
        type: 'POST',
        url: basePath + "/major_type_item-api/update",
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (msg) {
            if (msg.statusMsg == "success") {

                // 保存成功 重载二段类别
                $(".anItemBor-active").click();

            } else alert(msg.statusMsg);
        },
        error: function (err) {alert("系统异常 请联系系统管理员"); return;}
    });
});

// 删除-类别项按钮
$(document).on("click",".btnMajorItemDel",function(){
    if (window.confirm("确认删除此类别项吗?")){
        var obj = {};
        obj.mctiId = $(this).closest(".boxMajorItem").data("mctiid"); // 当前操作类别项ID
        obj.mctiMctid = $("#hidZYTypeID").val(); // 当前操作类别ID
        obj.userNo = $("#hidUserNo").val(); // 当前操作用户
        $.ajax({
            type: 'POST',
            url: basePath + "/major_type_item-api/delete",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                if (msg.statusMsg == "success") {

                    // delete 成功 重载二段类别
                    $(".anItemBor-active").click();

                } else alert(msg.statusMsg);
            },
            error: function (err) {alert("系统异常 请联系系统管理员"); return;}
        });
    }
});


$("body").on('click', '.layerRtb .rig_close', function() {
    $(".anItemBor-active").removeClass("anItemBor-active");
    $(this).parents(".layerRtb").animate({
        left: '100%'
    });
});