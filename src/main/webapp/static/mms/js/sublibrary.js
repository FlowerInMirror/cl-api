// "====================== 公用 ========================="

// 根据某个字段实现对json数组的排序
/*
 * @param   array  要排序的json数组对象
 * @param   field  排序字段（此参数必须为字符串）
 * @param   reverse 是否倒序（默认为false）
 * @return  array  返回排序后的json数组
*/
function jsonSort(array, field, reverse) {
    //数组长度小于2 或 没有指定排序字段 或 不是json格式数据
    if(array.length < 2 || !field || typeof array[0] !== "object") return array;
    //数字类型排序
    if(typeof array[0][field] === "number") {
        array.sort(function(x, y) { return x[field] - y[field]});
    }
    //字符串类型排序
    if(typeof array[0][field] === "string") {
        array.sort(function(x, y) { return x[field].localeCompare(y[field])});
    }
    //倒序
    if(reverse) {
        array.reverse();
    }
    return array;
}

// "====================== 列表 ========================="

// "====================== 一段 ========================="

// 列表:新增标准加号按钮点击
$(document).on("click", ".AddIcon", function () {
    $(".tc-center-mat").hide(); // 隐藏二段
    $(".tc-center-new").show(); // 打开二段隐藏域
    $("#divMaterialDetailIframe").attr("src", basePath+"/public-web/sublibrary/city-two-hujianghua");
});

// "====================== 二段 ========================="

// "====================== 三段 ========================="

// ======================档次(A档/B档/C档)

// 检测能否更新(品牌)
function CheckFromToBrandSupOption(opType) {
    var obj = new Object();
    obj.mTreeFour = $("#hidTreeID").val();
    obj.mTSID = $("#hidBrandID").val(); // 标准ID  -- 字段弃用
    obj.mCityID = $("#hidCityID").val(); // 地区
    obj.mLevel = $("#hidMatLevel").val();
    obj.mUpdateState = 1;
    obj.userType = $("#hidUserType").val();
    obj.userNo = $("#hidUserNo").val();
    // 品牌信息校验
    if (opType == 1) {
        var brandName = $("#txtBrandName").val();
        if (brandName == "" || brandName == "品牌名称") {
            alert("请输入品牌名称！");
            $("#txtBrandName").focus();
            return false;
        }
        else if (brandName.length > 50) {
            alert("品牌名称不能超过50个字！");
            $("#txtBrandName").focus();
            return false;
        }
        var brandRemark = $("#txtBrandRemark").val();
        if (brandRemark == $("#txtBrandRemark").attr("placeholder")) {
            brandRemark = "";
        }

        obj.mBrandName = brandName;
        obj.mMatDescription = brandRemark;
    }
    // 型号信息校验
    if (opType == 2) {
        var txtVal = $(".divbrandtype").find(".txtTitle:eq(0)");
        var brandType = $(txtVal).val();
        if (brandType == $(txtVal).attr("placeholder")) {
            brandType = "";
        }
        if (brandType.length > 50) {
            alert("型号不能超过50个字！");
            $(txtVal).focus();
            return false;
        }
        obj.mBrandType = brandType;
        obj.mHostState = $("#ddlHost").val();
        obj.mHomeHostState = $("#ddlHomeHost").val();
        obj.mMatType = $("#ddlMatType").val();
    }

    // 价格
    if (opType == 3) {

        var costPrice = parseFloat($("#txtCostPrice").val());//成本价
        if (costPrice === "" || isNaN(costPrice) || costPrice == 0) {
            costPrice = 0;
        }
        else if (costPrice < 0) {
            alert("成本价不能为负数！");
            $("#txtCostPrice").focus();
            return false;
        }
        var quotesPrice = $("#txtQuotesPrice").val();//报价
        if (quotesPrice === "" || isNaN(quotesPrice) || quotesPrice == 0) {
            quotesPrice = 0;
        }
        else if (quotesPrice < 0) {
            alert("报价不能为负数！");
            $("#txtQuotesPrice").focus();
            return false;
        }
        var installPrice = $("#txtInstallPrice").val();//安装价
        if (installPrice === "" || isNaN(installPrice)) {
            installPrice = 0;
        }
        else if (installPrice < 0) {
            alert("安装价不能为负数！");
            $("#txtInstallPrice").focus();
            return false;
        }
        obj.mCostPrice = costPrice;
        obj.mQuotesPrice = quotesPrice;
        obj.mInstallPrice = installPrice;
    }
    return obj;
}

// 品牌项:保存更新品牌（分项）品牌信息/型号信息/价格
$(document).on("click", ".btnSaveBrandSupOption", function () {

        var opType = $(this).data("type");// 1品牌信息 2品牌型号 3价格

        var obj = CheckFromToBrandSupOption(opType);

        obj.saveType = opType;//保存类型
        obj.mID = $("#hidMatID").val();
        $.ajax({
            type: "POST",
//                    url: "/Public/SaveMaterialBaseInfoToSupOption",
            url: basePath + "/material-api/brand_item/save",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (data) {

                if (data.statusMsg == "success") {
                    debugger
                    if (opType == 1 || opType == 2) SaveBrandType();

                    // 品牌信息
                    if (opType == 1) {
                        var txtBrandNameVal = $("#txtBrandName").val();
                        $(".lbl_material_brand_name").text(txtBrandNameVal);
                        if ($("#brandLogo img").attr("src") == "") {
                            $(".anItemBor-active").find(".lbl_material_logo").addClass("cRed").text("未");
                        } else {
                            $(".anItemBor-active").find(".lbl_material_logo").removeClass("cRed").text("有");
                        }
                    }
                    // 价格
                    if  (opType == 3){

                        $("#spanQuotesPrice").html(obj.mQuotesPrice);
                        $("#spanCostPrice").html(obj.mCostPrice);
                        $("#spanInstallPrice").html(obj.mInstallPrice);

                        layer.msg("价格更新成功", {icon: 6});
                    }

                }
                else {
                    layer.alert(data.statusMsg, {icon: 5});
                }
            },
            error: function (err) {
                alert("保存出错！");
            }
        });

});
// 品牌项:删除材料按钮点击
var brandRequestCommittedFlag = true; // 删除品牌请求提交标识 默认可提交状态 ture
$(document).on("click", ".btnBrandDelete", function () {
    if (brandRequestCommittedFlag) {

        brandRequestCommittedFlag = false; // 防止重复提交

        // 用户确认
        if (confirm("确认删除此材料？") == false) {
            brandRequestCommittedFlag = true; // 启用提交
            return false;
        }

        // TODO 材料库使用校验

        // TODO 报价部门使用校验

        var obj = new Object();
        obj.mID = $("#hidMatID").val();
        obj.userNo = $("#hidUserNo").val();
        $.ajax({
            type: 'POST',
//                    url: "/BMaterial/DeleteMaterialByID",
            url: basePath + "/material-api/brand_item/delete", // 删除品牌项
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                if (msg.statusMsg == "success") {
                    //清空数据
                    $("#tabDataToMaterial > tr.tractive").click();
                }
                else {
                    alert(msg.statusMsg);
                }
                brandRequestCommittedFlag = true; // 启用提交
            },
            error: function (err) {
                alert("操作出错！");
                brandRequestCommittedFlag = true; // 启用提交
            }
        });
    }
});
// 品牌项:预下架和取消预下架点击事件
$(document).on("click", ".btrandPreloadFrame", function () {
    if (brandRequestCommittedFlag) {

        brandRequestCommittedFlag = false; // 防止重复提交

        var dataType = parseInt($(this).attr("data-type")); // 0取消预下架,2预下架
        // 预下架
        if (dataType == 2){
            // 用户确认
            if (confirm("确认'预下架'此材料？") == false) {
                brandRequestCommittedFlag = true; // 启用提交
                return false;
            }
        }
        var obj = new Object();
        obj.mID = $("#hidMatID").val();
        obj.userNo = $("#hidUserNo").val();
        obj.mShoppingState = dataType;

        $.ajax({
            type: 'POST',
         // url: "/BMaterial/DeleteMaterialByID",
            url: basePath + "/material-api/brand_item/pre_operation", // 预操作(0取消预下架/2预下架)
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                debugger
                if (msg.statusMsg == "success") {
                    // 预下架
                    if (dataType == 0){
                        $(".btrandPreloadFrame[data-type='0']").hide();
                        $(".btrandPreloadFrame[data-type='2']").show();
                        layer.msg("取消预下架 成功", {icon: 6});
                    }
                    // 取消预下架
                    if (dataType == 2){
                        $(".btrandPreloadFrame[data-type='0']").show();
                        $(".btrandPreloadFrame[data-type='2']").hide();
                        layer.msg("预下架 成功", {icon: 6});
                    }
                }
                else {
                    layer.alert(msg.statusMsg, {icon: 2});
                }
                brandRequestCommittedFlag = true; // 启用提交
            },
            error: function (err) {
                layer.alert(msg.statusMsg, {icon: 2});
                brandRequestCommittedFlag = true; // 启用提交
            }
        });
    }
});

// 品牌项:应用按钮点击(品牌确认应用回到展示层)
$(document).on("click", ".btnBrandNameApply", function () {
    // 校验 当前项是否已经保存
    var visibleStatus = $(".save_img").is(':visible');
    if (visibleStatus) {
        alert("请先确认保存编辑内容或退出编辑状态");
        return;
    }
    var matID = $("#hidMatID").val();
    $("#brand" + matID).click();
});

// "====================== 四段 ========================="
