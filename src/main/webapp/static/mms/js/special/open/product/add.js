// 专项产品主图上传
function loadingZXProPicUpload() {
    $(".fileone").fileupload({
        url: picUploadUrl,
        dataType: 'json',
        autoUpload: false,
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 4 * 1024 * 1024,
        messages: {
            maxFileSize: 'error_size',
            acceptFileTypes: 'error_type'
        },
        processfail: function (e, data) {
            var currentFile = data.files[data.index];
            if (data.files.error && currentFile.error) {
                // there was an error, do something about it
                console.log(currentFile);
                if (currentFile.error == 'error_size') alert('后台限制最大图片为4M！(图片要求：大小：2M以内；格式：jpg；)');
                if (currentFile.error == 'error_type') alert('图片类型不符合要求！(图片要求：大小：2M以内；格式：jpg；)');
            }
            // 终止上传
            data.abort();
        },
    }).on('fileuploadadd', function (e, data) { // 当文件被添加到上传组件时被触发'
        data.submit();
    }).on('fileuploaddone', function (e, data) { // 当文件被添加到上传成功回调
        var photoUrl = data.result.BaseUrl + data.result.FileName; // 回调图片地址

        // 更新产品主图
        var $closestObj = $(e.target).closest("li.uiImgUpload-finished");

        $($closestObj).find("img").attr("src", photoUrl); // 回显
        $($closestObj).find(".enlarge_link").attr("data-src", photoUrl); // 放大插件支持属性赋值


        // 判断是否携带专项图片ID,携带更新图片.
        var sdpId = ($closestObj).data("sdpid");
        if (sdpId == "") return;
        var obj = {};
        obj.sdpId = sdpId;
        obj.spdUrl = photoUrl;
        $.ajax({
            type: 'POST',
            url: basePath + "/special_picture-api/update",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (res) {

                debugger
                if (res.statusMsg == "success") {} else {alert(res.statusMsg);return;}
            },
            error: function (err) {alert("更新专项产品主图 系统异常 请联系系统管理员"); return;}
        });
    });
}

// 专项产品主图:放大
$(document).on("click",".enlarge_link_more",function(){
    var i=$(this).attr("data-index");
    var thisclick = this;
    var thisId = "preview_" + i;
    var curId = "CurImg_" + i;
    //图片放大
    rxued.images.morePicLarge(thisclick, thisId, curId,i,"true","a");
    //点击旋转按钮
    rxued.images.rotateBtnClick(thisId, curId);
    //关闭图片放大弹出层
    rxued.images.closeImgAlert(thisId);
    //1:1
    rxued.images.oneToone(thisId, curId);
});

// 专项产品主图:删除
$(document).on("click",".delect_link",function(event){
    debugger
    var _this = this;
    var sdpId = $(event.target).closest("li.uiImgUpload-finished").data("sdpid"); // 图片ID

    // 校验是否存在ID,存在询问是否删除执行对应操作.
    if (sdpId != "") {
        if (window.confirm("确定删除这张图片吗?")){
            var obj = {};
            obj.sdpId = sdpId;
            obj.spdSpid = getUrlParms("zxProID");
            $.ajax({
                type: 'POST',
                url: basePath + "/special_picture-api/delete",
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (msg) {
                    if (msg.statusMsg == "success") {
                        $(_this).parents("li").remove();
                    } else {alert(msg.statusMsg);return;}
                },
                error: function (err) {alert("删除专项产品主图 系统异常 请联系系统管理员"); return;}
            });
        }
    } else $(this).parents("li").remove();
});

// 获取地址栏中的参数值
function getUrlParms(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null) return unescape(r[2]);return null;
}

// 自定义文本框:删除材料商自定义项 mctiId  mctiMctid  wpsUserID
function deleteDIYCategoryItem(mctiId,mctiMctid) {
    var result = true;

    var obj = {};
    obj.mctiId = mctiId;
    obj.mctiMctid = mctiMctid;// 当前操作类别ID
    obj.mctiType = 1;
    obj.userNo = wpsUserID; // 当前操作用户
    var ajax = $.ajax({
        type: 'POST',
        url: basePath + "/major_type_item-api/delete",
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (msg) {
            if (msg.statusMsg != "success"){alert(msg.statusMsg);result = false;}
        },
        error: function (err) {alert("移除材料商自定义类别项 系统异常 请联系系统管理员"); return;}
    });

    return result;
}

// 自定义文本框:材料商专属项添加:输入框内键盘弹起
$(document).on("keyup",".InpWrite",function(){
    var txtContent = $(this).val();
    if(txtContent!="") // 文本值不为空
    {
        // 获取最近的input并设值未选中状态
        $(this).closest(".col-md-3").find("input[type='checkbox']").prop("checked","checked");

        // 设置文本框的值到复选框的默认值回
        $(this).closest(".col-md-3").find("input[type='checkbox']").val(txtContent);

        if($(this).closest(".col-md-3").next(".col-md-3").length>0){
            return;
        }else{
            $(this).closest(".MainCampList").append('<div class="col-md-3 pt5 pb5"><label class="uiRadio12 ml10"><input type="checkbox" data-mctiid="" data-mctitype="1" class="uiRadio12-input InpWriteRadio"></label><input type="text" class="width140 InpWrite txtDIYCategoryItem"></div>');
        }
    }else{
        $(this).closest(".col-md-3").find("input[type='checkbox']").prop("checked","");
        $(this).closest(".col-md-3").nextAll(".col-md-3").remove();
    }
});

// 自定义文本框:复选框改变事件 -- 材料商自定义专属项添加(删除自定义项):
$(document).on("change",".InpWriteRadio",function(){
    // 当前操作自定类别项ID
    var mctiId = $(_this).attr("data-mctiid");

    if($(this).prop("checked")!=false||$(this).prop("checked")!='')
    {
        var InpVal=$(this).closest(".col-md-3").find("input[type='text']").val();
        if (InpVal == "")
        {
            layer.alert("请输入自定义项内容", {icon: 5});
            $(this).removeAttr("checked");
            step.Creat_Table();
        }
        if(InpVal=="" && mctiId != "")
        {
            return;
        }else{
            $(this).closest(".MainCampList").append('<div class="col-md-3 pt5 pb5 MainCampAppend"><label class="uiRadio12 ml10"><input type="checkbox" data-mctiid="" class="uiRadio12-input InpWriteRadio"></label><input type="text" class="width140 InpWrite txtDIYCategoryItem"></div>');
        }
    }else{
        var _this = this;

        // 保证只要预留一个文本框
        if ($(this).closest(".MainCampList").find("div").length == 1) return;

        // 当前选中自定义类别项ID不为空执行
        if (mctiId != "")
        {
            var deleteFlag = deleteDIYCategoryItem(mctiId,$(_this).parents("ul").data("mctid"));
            if (deleteFlag){
                $(_this).closest(".col-md-3").find("input[type='text']").val("");
                $(_this).closest(".col-md-3").remove();
                step.Creat_Table();
            }

        }
    }
});

// 自定义文本框:删除材料商自定义专属项
$(document).on("click",".deleteDIYCategoryItem",function(){
    if (window.confirm("你确定要删除当前自定义项吗?")){
        var mctiId = $(this).prev("input").attr("data-mctiid");
        var mctiMctid = $(this).closest("ul").attr("data-mctid");
        if (deleteDIYCategoryItem(mctiId,mctiMctid)) $(this).closest("li").remove();
    }
});

// 自定义文本框:获得焦点时间(更新自定义类别项提供支持)
$(document).on("focus",".txtDIYCategoryItem",function(){
    // 记录当前操作元素的内容值,以供失去焦点时比对是否进行更新操作.
    var thisVal = $(this).val();
    if (thisVal == "") return;
    $("#hidDIYCategoryItem").val(thisVal);
});

// 自定义文本框:失去焦点事件(新增或更新自定义类别项):1.后台保存内容至属性项表中,指定类型为材料商自定义分类;2.保存成功:①绑定后台回调的材料商自定义类别项ID到复选框;②重新生成SKU表格.
$(document).on("focusout",".txtDIYCategoryItem",function(){
    var _this = this;
    var closestCheckboxEl = $(_this).prev().find("input[type='checkbox']");
    // 类别ID
    var mctiMctid = $(_this).parents("ul").data("mctid");
    // 自定义类别项ID
    var mctiid = $(closestCheckboxEl).attr("data-mctiid");
    // 自定义类别项名称
    var mctiName = $(_this).val().trim();
    // 文本框获得焦点记录的老自定义类别项名称
    var oldMctiName = $("#hidDIYCategoryItem").val();

    // 复选框赋值
    $(closestCheckboxEl).val(mctiName);

    // 更新
    if (mctiid != "" && mctiName != ""  && oldMctiName != "" && mctiName != oldMctiName)
    {
        var obj = {};
        obj.mctiId = mctiid; // 当前操作类别项ID
        obj.mctiName = mctiName;
        obj.mctiMctid = mctiMctid;
        obj.userNo = wpsUserID;
        $.ajax({
            type: 'POST',
            url: basePath + "/major_type_item-api/update",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                if (msg.statusMsg == "success") {
                    // 更新成功,重新生成新的表格.
                    step.Creat_Table();
                } else alert(msg.statusMsg);
            },
            error: function (err) {alert("系统异常 请联系系统管理员"); return;}
        });
    }

    // 新增
    if (mctiid == "" && mctiName != "") // 当前没有类别项ID时与文本值不为空时执行
    {
        // 1.后台保存内容(类别ID,材料商ID,自定义内容名称,类别为1材料商自定义)至属性项表中,指定类型为材料商自定义分类;
        var obj = {};
        obj.mctiMctid = mctiMctid;
        obj.mctiDealerID = zxUserID;
        obj.mctiName = mctiName;
        obj.mctiType = 1;
        obj.userNo = wpsUserID;
        $.ajax({
            type: 'POST',
            url: basePath + "/major_type_item-api/add",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (res) {
                if (res.statusMsg == "success") {
                    // 2.保存成功:
                    // ①绑定后台回调的材料商自定义类别项ID到复选框;
                    $(closestCheckboxEl).attr("data-mctiid", res.body);
                    // ②重新生成SKU表格.
                    step.Creat_Table();
                } else {
                    alert(res.statusMsg);
                    return;
                }
            },
            error: function (err) {
                alert("添加自定义类别项 系统异常 请联系系统管理员");
                return;
            }
        });
    }
});

// 组合表格:价格录入键盘弹起组合套餐价格录入安全校验
$(document).on("keyup",".txtMCGPrice",function(){
    var price = $(this).val();
    if (price == ""){layer.alert("价格不能为空哦", {icon: 5});$(this).focus();return;}
    if (price < 0 || isNaN(price)){layer.alert("请输入正确的价格", {icon: 5});$(this).focus();return;}
});

// 发布产品按钮点击事件
$(document).on("click","#btnAddProduct",function(){
    KindEditor.sync('#txtProductdescAdd'); // 同步富文本框编辑内容

    // WPS登录用户ID
    var userNo = wpsUserID;

    // 1.保存专项产品基础信息:材料商ID/所属主营二级科目ID/材料商城市/产品名称/单位/产品详细;
    var spDealerID = zxUserID;
    var spMajorTreeTwoID = treeTwoID;
    var spCityID = zxUserCityID;
    var spProName = $("#txtProductName").val();
    var spProUnitID = $("#sltProductUnit").val();
    var spProDepictData = $("#txtProductdescAdd").val();

    // 2.保存产品主图集
    var specialPictures = [];
    $(".uiImgUpload-finished").each(function () {
        var spdUrl = $(this).find("img").attr("src"); // 图片路径
        var spdType = 1; // 图片类型:1产品主图（默认0）
        var obj = {};
        obj.spdUrl = spdUrl;
        obj.spdType = spdType;
        specialPictures.push(obj);
    });

    // 3.类别集:主营属性类别ID/主营属性类别项ID/类型
    var specialProductCategory = [];
    // 4.材料商自定义项(类别为1材料商录入):属性类别项ID/类别项名称
    var majorMaterialDealerItems = [];
    $(".Father_Title").each(function () {
        var spcMctid = $(this).attr("data-mctid");
        var spcMctname = $(this).find("li").html();
        $(this).next(".Father_Item").find("input[type='checkbox']:checked").each(function () {

            var spcType = $(this).attr("data-mctitype");
            var spcItemid = $(this).attr("data-mctiid");

            // 类别集
            var obj = {};
            obj.spcMctid = spcMctid;
            obj.spcMctname = spcMctname;
            obj.spcItemid = spcItemid;
            obj.spcType = spcType;
            specialProductCategory.push(obj);

            // 材料商自定义项
            if (spcType == 1)
            {
                var obj2 = {};
                obj2.mmdiItemid = spcItemid;
                obj2.mmdiName = $(this).val();
                majorMaterialDealerItems.push(obj2);
            }
        });
    });

    // 5.组合套餐集:编码/价格/是否主推
    var majorCampGroups = [];
    var mcgPriceCheck = 0; // 价格检验:0代表没有组合或所有组合都未填写价格,不为0则代表正常状态.
    var mcgPushtypeCheck = 0; // 主推检验:0代表没有组合或所有组合都未选主推,不为0则代表正常状态.
    $(".majorCampGroup tr").each(function () {
        debugger
        var mcgCode = $(this).attr("data-code");
        var mcgPrice = $(this).find("td .txtMCGPrice").val();
        var mcgPushtype = $(this).find("td .tadMCGPushType").is(":checked") ? 1 : 0;
        if (mcgPrice != 0 && mcgPrice != "" && !isNaN(mcgPrice)) mcgPriceCheck ++;
        if (mcgPushtype != 0) mcgPushtypeCheck ++;

        var obj = {};
        obj.mcgCode = mcgCode;
        obj.mcgPrice = mcgPrice;
        obj.mcgPushtype = mcgPushtype;
        majorCampGroups.push(obj);
    });

    // 提交校验:材料名称不为空/单位已选择/至少一张产品主图/至少一种组合套餐且本组套餐必须填写价格与设置主推
    if (spProName==""){layer.alert("请输入产品名称", {icon: 5});return;}
    if (spProUnitID == 0){layer.alert("请选择单位", {icon: 5});return;}
    if (specialPictures.length < 1){layer.alert("请至少上传一张产品主图", {icon: 5});return;}
    if (majorCampGroups.length < 1){layer.alert("至少需要一种组合套餐 且价格填写完整和设置为主推哦", {icon: 5});return;}
    if (mcgPriceCheck == 0){{layer.alert("组合套餐价格:不能都为0元 不能为空 不能录入非法内容", {icon: 5});return;}}
    if (mcgPushtypeCheck == 0){{layer.alert("必须设置一款主推 专项产品才成立", {icon: 5});return;}}

    // 校验通过 执行提交
    var obj = {};
    obj.userNo = userNo;
    obj.spMajorTreeTwoID = spMajorTreeTwoID;
    obj.spDealerID = spDealerID;
    obj.spProName = spProName;
    obj.spCityID = spCityID;
    obj.spProUnitID = spProUnitID;
    obj.spProDepictData = spProDepictData;
    obj.specialPictures = specialPictures;
    obj.specialProductCategory = specialProductCategory;
    obj.majorMaterialDealerItems = majorMaterialDealerItems;
    obj.majorCampGroups = majorCampGroups;
    $.ajax({
        url: basePath + '/special_product-api/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (res) {

            if (res.statusMsg != "success") {alert(res.statusMsg);return;}


            layer.confirm("产品: "+spProName+" 发布成功!是否需要浏览此专项产品?", {
                bth:['需要','退出']
            }, function () {
                window.open(basePath+"/public-web/special/open-product-browsing?proid=" + res.body);

                window.opener=null;
                window.open('','_self');
                window.close();
            }, function () {

                window.opener=null;
                window.open('','_self');
                window.close();
            })
        },
        error: function (err) {
            alert("系统异常 请联系管理员！");
        }
    });
});