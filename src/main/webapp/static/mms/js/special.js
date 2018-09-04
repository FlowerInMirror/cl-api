// "======================== 公用 ==========================="

//左侧列表正常个数

// 主推款校验(设置主推时,当前款未主推,其他设置为主推为否)
$(document).on("change",".sltPushType",function(){
    var pushType = $(this).val(); // 主推: 0否,1是
    if (pushType == 1)
        $(".sltPushType").not($(this)).val(0);
});

// 图片上传完成后移入显示放大和重新上传按钮
$(document).on("mouseenter",".uiImgUpload-finished",function(){
    $(this).find(".uiImgUpload-mark").show();
});
// 图片上传完成后移出隐藏放大和重新上传按钮
$(document).on("mouseleave",".uiImgUpload-finished",function(){
    $(this).find(".uiImgUpload-mark").hide();
});

// 图片放大
$(document).on("click",".pinpai_analyItem .enlarge_link",function(){
    var i=$(this).index();
    var thisclick = this;
    var thisId = "preview_" + i;
    var curId = "CurImg_" + i;
    //图片放大
    rxued.images.enSingleLarge(thisclick, thisId, curId);
    //点击旋转按钮
    rxued.images.rotateBtnClick(thisId, curId);
    //关闭图片放大弹出层
    rxued.images.closeImgAlert(thisId);
    //1:1
    rxued.images.oneToone(thisId, curId);
});

// 图片移入移除
$(document).on("hover", ".boxSetMealPic", function () {
    $(".dis_il_block").show();
}, function () {
    $(".dis_il_block").hide();
});

// 上传套餐照片
function uploadSetMealPic(){
    setTimeout(function () {
        debugger;
        var uploadUrl = $("#hidFileUrl").val();
        $(".setmealpicfile").fileupload({
            url: uploadUrl,
            dataType: 'json',
            autoUpload: false,
            acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
            maxFileSize: 2 * 1024 * 1024,
            messages: {maxFileSize: 'error_size', acceptFileTypes: 'error_type'},
            processfail: function (e, data) {
                var currentFile = data.files[data.index];
                if (data.files.error && currentFile.error) {
                    if (currentFile.error == 'error_size')
                        alert('超过允许的最大值！(图片要求：大小：2M以内；格式：jpg；)');
                    if (currentFile.error == 'error_type')
                        alert('图片类型不符合要求！(图片要求：大小：2M以内；格式：jpg；)');
                }
                // 终止上传
                data.abort();
            },
        }).on('fileuploadadd', function (e, data) { // 上传前
            data.submit();
        }).on('fileuploaddone', function (e, data) { // 上传后回调
            debugger
            var json = data.result;
            var photoUrl = json.BaseUrl + json.FileName;

            var divObj = $(e.target).closest(".boxSetMealPic");

            // 校验 携带套餐图片ID
            var parents = $(divObj).parents(".boxSpecialProSetMeal");
            var ssmID = $(parents).data("ssmid"); // 专项套餐ID
            if (ssmID != ""){
                // 携带 执行更新专项产品套餐图片的操作
                var obj = {};
                obj.ssmID = ssmID;
                obj.ssmMealPhotoURL = photoUrl;
                obj.userNo = $("#hidUserNo").val(); // 当前登录操作用户卡号
                obj.dbCityID = $("#hidCityID").val(); // 城市ID
                $.ajax({
                    type: 'POST',
                    url: basePath + "/special_setmeal-api/update",
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (msg) {
                        debugger
                        if (msg.statusMsg == "success") {

                        } else {alert(msg.statusMsg);return;}
                    },
                    error: function (err) {alert("上传专项产品套餐图片系统异常 请联系系统管理员"); return;}
                });
            }

            // 设置回显

            // 1.回显
            $(divObj).find("img").attr("src", photoUrl);
            // 2.放大支持图片路径
            $(divObj).find(".enlarge_link").attr("data-src", photoUrl);

            if ($(divObj).find(".uiImgUpload-first").length >= 1) {
                $(divObj).find(".uiImgUpload-first").remove();

                if ($(divObj).hasClass("uiImgUpload-borred") == false) {
                    $(divObj).addClass("uiImgUpload-borred").removeClass("uiImgUpload-old");
                }
                if ($(divObj).hasClass("uiImgUpload-finished") == false) {
                    $(divObj).addClass("uiImgUpload-finished");
                }
            }

        });
    });
};

// 弹框中间高度计算
function layerCH() {
    $(".layerRtb-scroll").each(function () {
        var oheights=0;
        if($(this).closest(".layerRtb").find(".j_chas").length>0)
        {
            for(var i=0;i<$(this).closest(".layerRtb").find(".j_chas").length;i++)
            {
                oheights+=$(this).closest(".layerRtb").find(".j_chas").eq(i).outerHeight(true);
            }
        }
        $(this).height($(window).height() - $("#topnav").outerHeight(true) - $(this).closest(".layerRtb").find(".layerRtb-head").outerHeight(true) - $(this).closest(".layerRtb").find(".layerRtb-bottom").outerHeight(true) - 20-oheights)
    })
    //计算弹框中回访内容高度
    $(".visit-judgeCon").each(function () {
        $(this).css("height", $(this).parents(".layerRtb-scroll").height() - $(this).prev(".judge-topDiv").outerHeight(true) - $(this).next(".judge-bottomDiv").outerHeight(true) - 10)
    })
};

// 关联材料材料列表 选择此材料 按钮
$(document).on("click",".butSelectProRelevanceMat",function () {

    var parent = $(this).parents("tr");

    // 1.设置 当前专项产品关联材料相关值
    $(".addProRelevanceMatName").html($(parent).data("matname"));
    $(".addProRelevanceMatSpec").html($(parent).data("matspec"));
    $("#hidZXProRelevanceMatID").val($(parent).data("matid"));

    // 2.关闭 layer
    layer.close( $("#hidZXLayerIndex").val());
});

// 删除套餐
$(document).on("click",".btnDelSetMeal",function () {
    debugger
    var length = $(".boxSpecialProSetMeal").length;
    if (length == 1){alert("不允许对最后一个套餐执行删除操作");return;}
    // var pushType = $(this).closest(".sltPushType").val();
    // if (pushType == 0){alert("主推套餐不允许被删除");return};

    if (window.confirm("确认删除此套餐？")) {
        // 判断是否删除已存在套餐(根据是否绑定专项套餐ID)
        var parents = $(this).parents(".boxSpecialProSetMeal");
        var ssmID = $(parents).data("ssmid"); // 专项套餐ID
        if (ssmID == ""){
            // 不存在移除元素
            $(parents).remove();
        } else {
            var obj = {};
            obj.ssmID = ssmID;
            $.ajax({
                type: 'POST',
                url: basePath + "/special_setmeal-api/delete",
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (msg) {
                    if (msg.statusMsg == "success") {
                        $(parents).remove();
                    } else {
                        alert(msg.statusMsg);
                    }
                },
                error: function (err) {alert("系统异常 请联系系统管理员"); return;}
            });
        }
    }
});

// "======================== 一段 ==============================="

// 一段列表===左侧列表行点击
$(document).on("click","#tabSpecial tr",function(){
    $(this).addClass("tractive").siblings().removeClass("tractive");
    var UserID=$(this).attr("data-UserID");
    var userCityID = $(this).data("usercityid");
    var StoresName=$(this).attr("data-StoresName");
    var UserCode=$(this).attr("data-UserCode");
    $("#hidZXUserID").val(UserID);
    $("#hidZXUserCityID").val(userCityID);
    $("#hidStoresName").val(StoresName);
    $("#hidUserCode").val(UserCode);
    $("#divZhuanXiang").find(".uiTab9 li:eq(0)").click();
});
// 点击专项搜索(展示搜索框)
$(document).on("click",".searchZhuanXiang",function(){
    $(this).closest(".contentbox").find(".dailyftSearch").show();
});
// 检索框回车事件
$(document).on("keydown","#Special_no",function(e){
    if (e.keyCode == 13) {
        $(".contentbox_zhuan .uiText-searchIcon").click();
    }
});
// 点击检索
$(document).on("click",".contentbox_zhuan .uiText-searchIcon",function(){
    $(this).parents("#OneList").find(".dailyftSearch").hide();
    var oinputVal=$.trim($("#Special_no").val());
    //加载数据
    if(oinputVal!=""){
        $("#tabSpecial tr").hide();
        $("#tabSpecial tr[data-username='"+oinputVal+"']").show().eq(0).click();
        $("#tabSpecial tr[data-companyname='"+oinputVal+"']").show().eq(0).click();
        $("#tabSpecial tr[data-userphone='"+oinputVal+"']").show().eq(0).click();
    }else{
        $("#tabSpecial tr").show().eq(0).click();
    }
    $("#Special_no").val("");
});

// "======================== 二段 ==============================="

// 二段导航===主选项卡
$(document).on("click","#ZhuanTcCenter .j_uiTab9 li",function(){
        var _othisIndex=$(this).index();
        $(this).addClass("uiTab9-active").siblings().removeClass("uiTab9-active");
        $('#ZhuanTcCenter .uiTab9Con').hide().eq(_othisIndex).show();
        switch (_othisIndex)
        {
            case 0: // 状态
                $("#ZhuanStatus").empty();
                $("#ZhuanStatus").load(basePath+"/public-web/special/two-status",function(){
                    countMiddle();
                    countRight();
                });
                break;
            // case 1: // 基础
            //     $("#ZhuanBase").load(basePath+"/public-web/special/two-base",function(){
            //         countMiddle();
            //         countRight();
            //     });
            //     break;
            case 1:
                $("#ZhuanShop").empty();
                $("#ZhuanShop").load(basePath+"/public-web/special/two-shop",function(){
                    countMiddle();
                    countRight();
                });
                break;
            case 2:
                $("#ZhuanProduct").empty(); // 清空二段产品盒子
                $(".productall-alert").empty(); // 清空三段产品盒子
                $(".productall-alert-four").empty(); // 清空四段添加产品盒子
                $("#ZhuanProduct").load(basePath+"/public-web/special/two-product",function(){
                    countMiddle();
                    countRight();
                });
                break;
            case 3:
                $("#ZhuanOrder").empty();
                $("#ZhuanOrder").load(basePath+"/public-web/special/two-order",function(){
                    countMiddle();
                    countRight();
                });
                break;
            case 4:
                $("#ZhuanPlatform").empty();
                $("#ZhuanPlatform").load(basePath+"/public-web/special/two-platform",function(){
                    countMiddle();
                    countRight();
                });
                break;
        }
        //重置产品添加或者编辑时de 添加按钮
    $(".productFixEdit").css("position","absolute");
        $(".layerRtb").animate({
            left: '-100%',
        },function(){
            $(this).hide();
        });
    });

// 产品===同步按钮
$(document).on("click","#btnfreshSpecialProduct",function(){
    $(".uiTab9-active").click();
    layer.msg("数据同步成功", {icon: 1});
});

// "======================== 三段 ==============================="

// 状态===处理
$(document).on("click",".chuliall-click",function () {
    $(this).parents(".analyItem").addClass("anItemBor-active").siblings().removeClass("anItemBor-active");
    $(".layerRtb").not(".chuliall-alert").animate({
        left: '-100%',
    },function(){
        $(this).hide();
    });
    $(".chuliall-alert").show().load(basePath+"/public-web/special/three-status-handle",function(){
        layerCH();
    });
    $(".chuliall-alert").stop().animate({
        left: "0px"
    });

});

// 店铺===店铺弹出层
$(document).on("click",".dianpu-click",function () {
    $(this).parents(".analyItem").addClass("anItemBor-active").siblings().removeClass("anItemBor-active");
    $(".layerRtb").not(".dianpu-alert").animate({
        left: '-100%',
    },function(){
        $(this).hide();
    });
    $(".dianpu-alert").show().load(basePath+"/public-web/special/three-shop-shop",function(){
        layerCH();
    });
    $(".dianpu-alert").stop().animate({
        left: "0px"
    });
});
// 店铺简介: 编辑按钮事件
$(document).on("click",".btnEditZXShop",function () {
    // 隐藏显示层相关
    $(".boxShopDepictData").hide();
    $(".btnEditZXShop").hide();

    // 展示编辑层相关
    $(".btnBackZXShop").show();
    $(".boxShopKindEditor").show();
});
// 店铺简介: 返回按钮事件
$(document).on("click",".btnBackZXShop",function () {
    // 隐藏编辑层相关
    $(".btnBackZXShop").hide();
    $(".boxShopKindEditor").hide();

    // 展示显示层相关
    $(".boxShopDepictData").show();
    $(".btnEditZXShop").show();
});
// 店铺简介: 提交按钮事件
$(document).on("click",".btnSubmitZXShop",function () {
    debugger

    // 同步富文本框编辑内容
    KindEditor.sync('#txtShopDesc');

    // 提交校验文本框内容是否有值
    var pmjStoreRemark = $("#txtShopDesc").val();
    if (pmjStoreRemark == "" || pmjStoreRemark.length == 0 ){alert("店铺简介不能为空");return;};

    // 组织提交数据
    var obj = {};
    obj.uid = $("#hidZXUserID").val(); // 材料商ID
    obj.pmj_StoreRemark = pmjStoreRemark; // 店铺介绍

    // 提交
    $.ajax({
        url: gcApiSite + 'CMaterial/SaveStoresIntroduction',
        type: 'POST',
        dataType: 'json',
        data: obj,
        success: function (res) {
            if (res.StatusCode == -1) {alert(res.StatusMsg);return;}
            $(".dianpu-click").click();
        },
        error: function (err) {alert('操作出错！');}
    });
});

// 店铺===资料弹出层
$(document).on("click",".ziliao-click",function () {
    $(this).parents(".analyItem").addClass("anItemBor-active").siblings().removeClass("anItemBor-active");
    $(".layerRtb").not(".ziliao-alert").animate({
        left: '-100%',
    },function(){
        $(this).hide();
    });
    $(".ziliao-alert").show().load(basePath+"/public-web/special/three-shop-datum",function(){
        layerCH();
        rxued.scrolls.doScroll($("#slide5"), ".scrollUl", ".scrollUl li", ".Prev", ".Next", 3);
        rxued.scrolls.doScroll($("#slide6"), ".scrollUl", ".scrollUl li", ".Prev", ".Next", 3);
    });
    $(".ziliao-alert").stop().animate({
        left: "0px"
    });
});

// 产品===打开添加(三段)
$(document).on("click",".addproduct",function () {

    $(".anItemBor-active").removeClass("anItemBor-active");

    $(".layerRtb").not(".productall-alert-four").animate({
        left: '-100%',
    },function () {
        $(this).hide();
    });

    $(".productall-alert").empty(); // 清空三段产品盒子
    $(".productall-alert-four").empty(); // 清空四段添加产品盒子
    $(".productall-alert-four").show().load(basePath+"/public-web/special/three-product-add",function () {
        $(".productall-alert-four").animate({left:"0"},200,function(){
            layerCH()
        });
    });
});
// 关闭添加
$(document).on("click",".rig_close.addZxPro",function () {$(".productall-alert-four").empty();});
// 添加套餐
$(document).on("click",".addSetmealBtn.addZXPro",function(){

    var olen=$(this).closest(".layerRtb").find(".pinpai_analyItem").length+1;
    $(".pinpai_analyItem_list").append(
        '<div class="analyItem pinpai_analyItem boxSpecialProSetMeal " data-ssmid="" data-pinpai="'+(olen-1)+'">'+
        '<p class="analyItemTit tx-center">套餐'+olen+'</p>'+
        '<div class="analyItemCon relative">'+
        '<div class="SetMeal clearfix">'+
        '<div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth130 relative boxSetMealPic">'+
        '<a href="javascript:" class="uiImgUpload-first">'+
        '<input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" value="" class="file setmealpicfile">'+
        '<em class="bgIcon file-ico"></em>'+
        '</a>'+
        '<img src="" />' +
        '<div class="uiImgUpload-mark">\n' +
        '<div class="uiImgUpload-mark-bg"></div>\n' +
        '<div class="uiImgUpload-mark-link">\n' +
        '<div class="dis_il_block">\n' +
        '<a href="jvascript:" class="upagain_link"><input type="file" class="fileOne setmealpicfile"></a>\n' +
        '<a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a>\n' +
        '</div>\n' +
        '</div>\n' +
        '</div>'+
        '</div>'+

        '<div class="SetMealText">'+
        '<p class="clearfix mb10">'+
        '<span class="cLightGray pr8 fl">套餐名称</span>'+
        '<input type="text" class="width130 txtSetMealName" placeholder="套餐'+ olen +' 名称" style="width: 77%">'+
        '</p>'+
        '<p class="clearfix mb10">'+
        '<span class="cLightGray pr8 fl">套餐价格</span>'+
        '<input type="text" class="width130 txtSetMealPrice" placeholder="套餐'+ olen +' 价格">'+
        '</p>'+
        '<p class="clearfix">'+
        '<span class="cLightGray pr8 fl">是否主推</span>'+
        '<select class="fl sltPushType">'+
        '<option value="0">否</option>'+
        '<option value="1">是</option>'+
        '</select>'+
        '</p>'+
        '<div class="fr productFix product-edit">'+
        '<img src=' + ctxStatic + '/images/pic/delete_img.png?2018812" class="fl btnDelSetMeal" width="20" height="20" style="cursor:pointer;">'+
        '</div>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '</div>'
    );
    $(".analyItem[data-pinpai='"+(olen-2)+"']").find(".imgDeleteBtn").show();
    setTimeout(function () {
        uploadSetMealPic();
    })

});
// 添加产品
$(document).on("click",".addAllProduct",function(){

    // 同步富文本框编辑内容
    KindEditor.sync('#txtProductdescAdd');

    // 其他
    var setMealCheckFlag = false;
    var pushTypeFlag = true;
    var userNo = $("#hidUserNo").val(); // 当前登录操作用户卡号
    var dbCityID = $("#hidCityID").val(); // 城市ID

    // 专项产品相关
    var spDealerID  = $("#hidZXUserID").val(); // 产品供应材料商
    var spProName = $.trim($("#txtProductName").val()); // 产品名称
    var spCityID = $("#hidZXUserCityID").val(); // 产品城市
    var spProUnitID = $("#sltProductUnit").val(); // 产品单位
    var spProDepictData  = $("#txtProductdescAdd").val(); // 产品详情数据(富文本编辑内容)

    // 专项产品关联材料相关
    var smrMatID = $("#hidZXProRelevanceMatID").val(); // 专项产品关联材料ID

    // 专项产品套餐相关
    var ssms = []; // 专项产品套餐集

    // 提交检验
    if(spProName==""){alert("请输入产品名称");return;}

    if(smrMatID=="" || $("#txtMatNameToSplitTemp").val() == ""){alert("请选择关联材料");return;}
    $(".boxSpecialProSetMeal").each(function(){
        debugger
        var ssmMealPhotoURL = $(this).find("img").attr("src"); // 套餐图片
        var ssmMealName = $(this).find(".txtSetMealName").val(); // 套餐名称
        var ssmMealPrice = $(this).find(".txtSetMealPrice").val(); // 套餐价格
        var ssmPushType = $(this).find(".sltPushType").val(); // 是否主推：0不主推、1主推  （默认0）

        if ($.trim(ssmMealName) == ""){alert("套餐名称不能为空");setMealCheckFlag = true;return;};
        if (!setMealCheckFlag)
            if (ssmMealPrice < 0 || isNaN(ssmMealPrice)) {alert("请输入正确的套餐价格");setMealCheckFlag = true;return;};
        if (!setMealCheckFlag)
            if (ssmMealPrice === ""|| ssmMealPrice == 0) {alert("套餐价格不能为空");setMealCheckFlag = true;return;};
        if (ssmPushType == 1)
            pushTypeFlag = false;
        if (!setMealCheckFlag){
            var ssm = {};
            ssm.ssmMealPhotoURL = ssmMealPhotoURL;
            ssm.ssmMealName = ssmMealName;
            ssm.ssmMealPrice = ssmMealPrice;
            ssm.ssmPushType = ssmPushType;
            ssms.push(ssm);
        }
    });
    if (setMealCheckFlag){return;};
    if (pushTypeFlag){alert("添加失败,必须有一款主推套餐!");return;};


    // 校验通过进行保存
    var obj = {};
    obj.userNo = userNo;
    obj.dbCityID = dbCityID;
    obj.spDealerID = spDealerID;
    obj.spProName = spProName;
    obj.spCityID = spCityID;
    obj.spProUnitID = spProUnitID;
    obj.spProDepictData = spProDepictData;
    obj.smr = {smrMatID:smrMatID};
    obj.ssms = ssms; // 反转保存
    $.ajax({
        url: basePath + '/special_product-api/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (msg) {
            if (msg.statusMsg != "success") {
                alert(msg.statusMsg);
                return;
            }
            alert("产品: "+spProName+" 添加成功");
            $(".uiTab9-active").click();
        },
        error: function (err) {
            alert("操作出错！");
        }
    });
});


// 产品===三段
$(document).on("click",".analyItemProCclick",function () {

    // 设置当前选中专项产品到隐藏域 作用:加载三段数据
    $("#hidZXSProID").val($(this).data("proid"));

    $(this).parents(".analyItem").addClass("anItemBor-active").siblings().removeClass("anItemBor-active");

    $(".layerRtb").not(".productall-alert").animate({
        left: '-100%',
    },function(){
        $(this).hide();
    });

    $(".productall-alert").empty(); // 清空三段产品盒子
    $(".productall-alert-four").empty(); // 清空四段产品添加盒子
    $(".productall-alert").show().load(basePath+"/public-web/special/three-product-items",function(){
       layerCH();
       rxued.scrolls.doScroll($("#slide7"), ".scrollUl", ".scrollUl li", ".Prev", ".Next", 2);

    });
    $(".productall-alert").stop().animate({
        left: "0px"
    });
});
// 编辑按钮
$(document).on("click",".img_edit_Btn.zxPro",function(){
    $(this).hide(); // 编辑按钮
    $(".boxKindEditor").show();

    $(".boxSpProDepictData").hide();


    $(".product-look").hide();
    $(".product-edit").show();
    $(".goback_img").show();
    $(".upagain_link").show();
    $(".fixright").show();
    layerCH();

});
// 保存
$(document).on("click",".save_img_Btn.zxPro",function(){
    // --- 变量区 ---
    var setMealCheckFlag = false;
    var pushTypeFlag = true;
    var userNo = $("#hidUserNo").val(); // 当前登录操作用户卡号
    var dbCityID = $("#hidCityID").val(); // 城市ID
    var ssms = []; // 套餐集

    // --- 校验保存 ---
    var spProName = $.trim($("#txtProductName").val()); // 产品名称
    var smrMatID = $("#hidZXProRelevanceMatID").val(); // 专项产品关联材料ID

    if(spProName==""){alert("请输入产品名称");return;}
    if(smrMatID=="" || $("#txtMatNameToSplitTemp").val() == ""){alert("请选择关联材料");return;}
    $(".boxSpecialProSetMeal").each(function(){
        debugger
        var ssmMealPhotoURL = $(this).find("img").attr("src"); // 套餐图片
        var ssmMealName = $(this).find(".txtSetMealName").val(); // 套餐名称
        var ssmMealPrice = $(this).find(".txtSetMealPrice").val(); // 套餐价格
        var ssmPushType = $(this).find(".sltPushType").val(); // 是否主推：0不主推、1主推  （默认0）
        if ($.trim(ssmMealName) == ""){alert("套餐名称不能为空");setMealCheckFlag = true;return;};
        if (!setMealCheckFlag)
            if (ssmMealPrice < 0 || isNaN(ssmMealPrice)) {alert("请输入正确的套餐价格");setMealCheckFlag = true;return;};
        if (!setMealCheckFlag)
            if (ssmMealPrice === ""|| ssmMealPrice == 0) {alert("套餐价格不能为空");setMealCheckFlag = true;return;};
        // 校验是否有主推
        if (ssmPushType == 1)
            pushTypeFlag = false;

        if (!setMealCheckFlag){
            var ssmID = $(this).data("ssmid");
            var ssm = {};
            // 判断更新或新增(新增不传递ID,后来以此判断新增或更新)
            if (ssmID != "")
                ssm.ssmID = ssmID;
            ssm.ssmSPID =  $("#hidZXSProID").val();
            ssm.ssmMealPhotoURL = ssmMealPhotoURL;
            ssm.ssmMealName = ssmMealName;
            ssm.ssmMealPrice = ssmMealPrice;
            ssm.ssmPushType = ssmPushType;
            ssms.push(ssm);
        }
    });
    if (setMealCheckFlag){return;};
    if (pushTypeFlag){alert("保存失败,必须有一款主推套餐!");return;}

    if (!window.confirm("请确认保存")) {return;}

    // 保存产品
    var obj = {};
    obj.spID = $("#hidZXSProID").val();
    obj.spProName = $.trim($("#txtProductName").val()); // 产品名称
    obj.spProUnitID = $("#sltProductUnit").val(); // 产品单位
    KindEditor.sync('#txtProductdesc');
    obj.spProDepictData  = $("#txtProductdesc").val(); // 产品详情数据(富文本编辑内容)
    obj.userNo = $("#hidUserNo").val(); // 当前登录操作用户卡号
    obj.dbCityID = $("#hidCityID").val(); // 城市ID
    $.ajax({
        type: 'POST',
        url: basePath + "/special_product-api/update",
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (msg) {
            if (msg.statusMsg == "success") {
                console.log("保存产品:"+ obj.spID +"成功!")
            } else {
                alert(msg.statusMsg);
            }
        },
        error: function (err) {alert("系统异常 请联系系统管理员"); return;}
    });

    // 保存产品关联材料
    var smr = {};
    smr.smrID = $("#hidZXProRelevanceID").val(); // 专项产品关联ID
    smr.smrMatID = $("#hidZXProRelevanceMatID").val(); // 专项产品关联材料ID
    $.ajax({
        type: 'POST',
        url: basePath + "/special_matrelevance-api/update",
        contentType: 'application/json',
        data: JSON.stringify(smr),
        success: function (msg) {
            if (msg.statusMsg == "success") {
                console.log("保存专项产品关联材料:"+ smr.smrID +"成功!")
            } else {
                alert(msg.statusMsg);
            }
        },
        error: function (err) {alert("系统异常 请联系系统管理员"); return;}
    });

    // 保存专项套餐集
    $.ajax({
        type: 'POST',
        url: basePath + "/special_setmeal-api/updates",
        contentType: 'application/json',
        data: JSON.stringify(ssms),
        success: function (msg) {
            if (msg.statusMsg == "success") {
                console.log("保存专项套餐集,成功!")
            } else {
                alert(msg.statusMsg);
            }
        },
        error: function (err) {alert("系统异常 请联系系统管理员"); return;}
    });

    alert("专项产品:"+spProName+",保存成功");
    // $("div[data-proid="+obj.spID+"]").click();
    $(".uiTab9-active").click();

    // 操作样式
    $(".product-look").show();
    $(".product-edit").hide();
    $(".goback_img").hide();
    $(".upagain_link").hide();
    $(".fixright").hide();
});
// 返回
$(document).on("click",".goback_img.zxPro",function(){

    $(".img_edit_Btn.zxPro").show();
    $(".product-look").show();
    $(".product-edit").hide();
    $(".goback_img").hide();
    $(".upagain_link").hide();
    $(".fixright").hide();


    $(".boxSpProDepictData").show();
    $(".boxKindEditor").hide();
});
// 添加套餐
$(document).on("click",".addSetmealBtn.zxPro",function(){
    var olen=$(this).closest(".layerRtb").find(".pinpai_analyItem").length+1;
    $(".pinpai_analyItem_list").append(
        '<div class="analyItem pinpai_analyItem boxSpecialProSetMeal " data-ssmid="" data-pinpai="'+(olen-1)+'">'+
        '<p class="analyItemTit tx-center">套餐'+olen+'</p>'+
        '<div class="analyItemCon relative">' +
        '<div class="SetMeal clearfix">' +
        '<div class="boxSetMealPic SetMealImg zxPro fl relative uiImgUpload-finished">' +
            '<img src=' + ctxStatic + '/images/pic/zan_nopic.png'+ '>' +
        '<div class="uiImgUpload-mark" style="display: none;">' +
        '<div class="uiImgUpload-mark-bg">' +
        '</div> <div class="uiImgUpload-mark-link"><div class="dis_il_block">' +
            '<a href="jvascript:" class="upagain_link">' +
            '<input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="fileone setmealpicfile">' +
            '</a>' +
            '<a href="javascript:" data-src="" data-imgname="" class="enlarge_link enlarge_link_single"></a>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div> ' +
        '<div class="SetMealText">' +
        '<p class="clearfix mb10 product-edit ">' +
        '<span class="cLightGray pr8 fl">套餐名称</span> ' +
        '<input type="text" class="width130 txtSetMealName" placeholder="套餐'+ olen +' 名称" style="width: 77%"></p> ' +
        '<p class="clearfix mb10 product-edit ">' +
        '<span class="cLightGray pr8 fl">套餐价格</span> ' +
        '<input type="text" class="width130 txtSetMealPrice" placeholder="套餐'+ olen +' 价格"></p> ' +
        '<p class="clearfix product-edit ">' +
        '<span class="cLightGray pr8 fl">是否主推</span> ' +
        '<select class="fl sltPushType">' +
        '<option value="0">否</option> ' +
        '<option value="1">是</option>' +
        '</select></p> ' +
        '<div class="fixright ">' +
            '<img src=' + ctxStatic + '/images/pic/delete_img.png?2018812" class="fl btnDelSetMeal imgDeleteBtn" width="20" height="20" style="cursor:pointer;">'+
        '</div> ' +
        '<div class="fr productFix ">' +
            '<input type="button" value="下架" class="uiBtn-orange uiBtn-small fl btnSetMealDown">' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>'
    );
    $(".analyItem[data-pinpai='"+(olen-2)+"']").find(".imgDeleteBtn").show();
    setTimeout(function () {
        uploadSetMealPic();
    })
});
// 保存浏览
$(document).on("click",".btnZXProBrowsing",function(){
    // --- 变量区 ---
    var setMealCheckFlag = false;
    var pushTypeFlag = true;
    var userNo = $("#hidUserNo").val(); // 当前登录操作用户卡号
    var dbCityID = $("#hidCityID").val(); // 城市ID
    var ssms = []; // 套餐集

    // --- 校验保存 ---
    var spProName = $.trim($("#txtProductName").val()); // 产品名称
    var smrMatID = $("#hidZXProRelevanceMatID").val(); // 专项产品关联材料ID

    if(spProName==""){alert("请输入产品名称");return;}
    if(smrMatID=="" || $("#txtMatNameToSplitTemp").val() == ""){alert("请选择关联材料");return;}
    $(".boxSpecialProSetMeal").each(function(){
        debugger
        var ssmMealPhotoURL = $(this).find("img").attr("src"); // 套餐图片
        var ssmMealName = $(this).find(".txtSetMealName").val(); // 套餐名称
        var ssmMealPrice = $(this).find(".txtSetMealPrice").val(); // 套餐价格
        var ssmPushType = $(this).find(".sltPushType").val(); // 是否主推：0不主推、1主推  （默认0）
        if ($.trim(ssmMealName) == ""){alert("套餐名称不能为空");setMealCheckFlag = true;return;};
        if (!setMealCheckFlag)
            if (ssmMealPrice < 0 || isNaN(ssmMealPrice)) {alert("请输入正确的套餐价格");setMealCheckFlag = true;return;};
        if (!setMealCheckFlag)
            if (ssmMealPrice === ""|| ssmMealPrice == 0) {alert("套餐价格不能为空");setMealCheckFlag = true;return;};
        // 校验是否有主推
        if (ssmPushType == 1)
            pushTypeFlag = false;

        if (!setMealCheckFlag){
            var ssmID = $(this).data("ssmid");
            var ssm = {};
            // 判断更新或新增(新增不传递ID,后来以此判断新增或更新)
            if (ssmID != "")
                ssm.ssmID = ssmID;
            ssm.ssmSPID =  $("#hidZXSProID").val();
            ssm.ssmMealPhotoURL = ssmMealPhotoURL;
            ssm.ssmMealName = ssmMealName;
            ssm.ssmMealPrice = ssmMealPrice;
            ssm.ssmPushType = ssmPushType;
            ssms.push(ssm);
        }
    });
    if (setMealCheckFlag){return;};
    if (pushTypeFlag){alert("保存失败,必须有一款主推套餐!");return;}

    if (!window.confirm("请确认保存")) {return;}

    // 保存产品
    var obj = {};
    obj.spID = $("#hidZXSProID").val();
    obj.spProName = $.trim($("#txtProductName").val()); // 产品名称
    obj.spProUnitID = $("#sltProductUnit").val(); // 产品单位
    KindEditor.sync('#txtProductdesc');
    obj.spProDepictData  = $("#txtProductdesc").val(); // 产品详情数据(富文本编辑内容)
    obj.userNo = $("#hidUserNo").val(); // 当前登录操作用户卡号
    obj.dbCityID = $("#hidCityID").val(); // 城市ID
    $.ajax({
        type: 'POST',
        url: basePath + "/special_product-api/update",
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (msg) {
            if (msg.statusMsg == "success") {
                console.log("保存产品:"+ obj.spID +"成功!")
            } else {
                alert(msg.statusMsg);
            }
        },
        error: function (err) {alert("系统异常 请联系系统管理员"); return;}
    });

    // 保存产品关联材料
    var smr = {};
    smr.smrID = $("#hidZXProRelevanceID").val(); // 专项产品关联ID
    smr.smrMatID = $("#hidZXProRelevanceMatID").val(); // 专项产品关联材料ID
    $.ajax({
        type: 'POST',
        url: basePath + "/special_matrelevance-api/update",
        contentType: 'application/json',
        data: JSON.stringify(smr),
        success: function (msg) {
            if (msg.statusMsg == "success") {
                console.log("保存专项产品关联材料:"+ smr.smrID +"成功!")
            } else {
                alert(msg.statusMsg);
            }
        },
        error: function (err) {alert("系统异常 请联系系统管理员"); return;}
    });

    // 保存专项套餐集
    $.ajax({
        type: 'POST',
        url: basePath + "/special_setmeal-api/updates",
        contentType: 'application/json',
        data: JSON.stringify(ssms),
        success: function (msg) {
            if (msg.statusMsg == "success") {
                console.log("保存专项套餐集,成功!")
            } else {
                alert(msg.statusMsg);
            }
        },
        error: function (err) {alert("系统异常 请联系系统管理员"); return;}
    });

    window.open(basePath + '/public-web/special/open-product-browsing?proid=' + obj.spID);
});

// 下架套餐
$(document).on("click",".btnSetMealDown",function(){

    if (window.confirm("确认下架此套餐？")) {
        var parents = $(this).parents(".boxSpecialProSetMeal");
        var ssmID = $(parents).data("ssmid"); // 专项套餐ID
        var obj = {};
        obj.ssmID = ssmID;
        obj.ssmStatus = 2;
        obj.userNo = $("#hidUserNo").val(); // 当前登录操作用户卡号
        obj.dbCityID = $("#hidCityID").val(); // 城市ID
        $.ajax({
            type: 'POST',
            url: basePath + "/special_setmeal-api/update",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                if (msg.statusMsg == "success") {
                    alert("下架套餐成功!");
                } else {
                    alert(msg.statusMsg);
                }
            },
            error: function (err) {alert("系统异常 请联系系统管理员"); return;}
        });
        $(this).siblings(".btnSetMealUp").show();
        $(this).hide();
    }
});
// 上架套餐
$(document).on("click",".btnSetMealUp",function(){
    if (window.confirm("确认上架此套餐？")) {
        var parents = $(this).parents(".boxSpecialProSetMeal");
        var ssmID = $(parents).data("ssmid"); // 专项套餐ID
        var obj = {};
        obj.ssmID = ssmID;
        obj.ssmStatus = 0;
        obj.userNo = $("#hidUserNo").val(); // 当前登录操作用户卡号
        obj.dbCityID = $("#hidCityID").val(); // 城市ID
        $.ajax({
            type: 'POST',
            url: basePath + "/special_setmeal-api/update",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                if (msg.statusMsg == "success") {
                    alert("上架套餐成功!");
                } else {
                    alert(msg.statusMsg);
                }
            },
            error: function (err) {alert("系统异常 请联系系统管理员"); return;}
        });
        $(this).siblings(".btnSetMealDown").show();
        $(this).hide();
    }
});
// 上架产品
$(document).on("click",".btnZXProUp",function(){
    if (window.confirm("确认上架此产品？")) {
        var obj = {};
        obj.spID = $("#hidZXSProID").val();
        obj.spStatus = 0;
        obj.userNo = $("#hidUserNo").val(); // 当前登录操作用户卡号
        obj.dbCityID = $("#hidCityID").val(); // 城市ID
        $.ajax({
            type: 'POST',
            url: basePath + "/special_product-api/update",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                if (msg.statusMsg == "success") {
                    $(".btnZXProUp").hide();
                    $(".btnZXProDown").show();
                    alert("上架产品成功!");

                } else {
                    alert(msg.statusMsg);
                }
            },
            error: function (err) {alert("系统异常 请联系系统管理员"); return;}
        });
    }
});
// 下架产品
$(document).on("click",".btnZXProDown",function(){
    if (window.confirm("确认下架此产品？")) {
        var obj = {};
        obj.spID = $("#hidZXSProID").val();
        obj.spStatus = 2;
        obj.userNo = $("#hidUserNo").val(); // 当前登录操作用户卡号
        obj.dbCityID = $("#hidCityID").val(); // 城市ID
        $.ajax({
            type: 'POST',
            url: basePath + "/special_product-api/update",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                if (msg.statusMsg == "success") {
                    $(".btnZXProDown").hide();
                    $(".btnZXProUp").show();
                    alert("下架产品成功!");
                } else {
                    alert(msg.statusMsg);
                }
            },
            error: function (err) {alert("系统异常 请联系系统管理员"); return;}
        });
    }
});
// 删除产品
$(document).on("click",".btnZXProDel",function(){
    if (window.confirm("确认删除此产品？")) {
        var obj = {};
        obj.spID = $("#hidZXSProID").val();
        obj.spStatus = 1;
        obj.userNo = $("#hidUserNo").val(); // 当前登录操作用户卡号
        obj.dbCityID = $("#hidCityID").val(); // 城市ID
        $.ajax({
            type: 'POST',
            url: basePath + "/special_product-api/delete",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                if (msg.statusMsg == "success") {
                    // 清空盒子
                    $(".productall-alert").empty();
                    $(".uiTab9-active").click();
                } else {
                    alert(msg.statusMsg);
                }
            },
            error: function (err) {alert("系统异常 请联系系统管理员"); return;}
        });
    }
});


// 订单
$(document).on("click",".dindanall-click",function () {
    $(this).parents(".analyItem").addClass("anItemBor-active").siblings().removeClass("anItemBor-active");
    $(".layerRtb").not(".dindanall-alert").animate({
        left: '-100%',
    },function(){
        $(this).hide();
    });
    $(".dindanall-alert").show().load(basePath+"/public-web/special/three-order-order",function(){
        layerCH();
    });
    $(".dindanall-alert").stop().animate({
        left: "0px"
    });
    layerCH();
})
// 默认
$(document).on("click",".analyItemCclick",function () {
    $(this).addClass("anItemBor-active").siblings().removeClass("anItemBor-active");
    $(".layerRtb").not(".dianpu-alert").animate({
        left: '-100%',
    },function(){
        $(this).hide();
    });
    $(".analyItemCclickShow").show();
    $(".analyItemCclickShow").stop().animate({
        left: "0px"
    });
    layerCH();
});


$(document).on("mouseenter",".scroll-text2 li",function () {
    $(this).find(".demand-layerDiv").show();
});
$(document).on("mouseleave",".scroll-text2 li",function () {
    $(this).find(".demand-layerDiv").hide();
});

//专项弹出层
$(".analyItemCclickShow .rig_close").click(function () {
    $(".anItemBor-active").removeClass("anItemBor-active");
    $(".analyItemCclickShow").animate({"left": "-100%"}, 200, function () {
        $(this).hide();
    });
});


// "======================== 四段(含弹出新窗口) ==============================="

// 产品 == 浏览超链接 @describe 阻止事件传播
$(document).on("click","#zxProBrowse",function(event){
    event.stopPropagation();
});

// 产品 == 编辑超链接 点击事件
$(document).on("click","#zxProEdit",function(event){
    debugger
    // 携带数据跳转到添加页面
    var zxUserID = $("#hidZXUserID").val(); // 专项材料商ID
    var zxUserCityID = $("#hidZXUserCityID").val(); // 专项材料商地区ID
    var wpsUserID = $("#hidUserNo").val(); // WPS登录用户卡号
    var proID = $(this).data("proid");
    window.open( basePath + "/public-web/special/open-product-edit?zxUserID=" + zxUserID + "&zxUserCityID=" + zxUserCityID + "&wpsUserID=" + wpsUserID + "&proID=" + proID);
});

// 产品 == 添加按钮 四段Layer弹出
$(document).on("click","#btnAddSpecialProduct",function(event){
    var $boxMajorTreeList = $("#boxMajorTreeList"); // 主营科目列表弹出层盒子
    // 1.load 主营科目列表
    $boxMajorTreeList.load(basePath+"/public-web/special/four-product-major_tree_list",function () {

        // 2.open 主营科目列表
        var index = layer.open({
            type: 1,
            title: "选择主营",
            area: ['78%', '78%'],
            content: $boxMajorTreeList,
            btn: ["关闭"],
        });

        // 3.记录layer索引以供关闭
        $("#hidZXLayerIndex").val(index);
    });
});

// 选择此主营
$(document).on("click",".butSelectMajorTree",function(){
    // 关闭 layer
    layer.close( $("#hidZXLayerIndex").val());

    // 携带数据跳转到添加页面
    var zxUserID = $("#hidZXUserID").val(); // 专项材料商ID
    var zxUserCityID = $("#hidZXUserCityID").val(); // 专项材料商地区ID
    var treeTwoID = $(this).parents("tr").data("treetwoid"); // 主营科目ID
    var wpsUserID = $("#hidUserNo").val(); // WPS登录用户卡号

    window.open( basePath + "/public-web/special/open-product-add?zxUserID=" + zxUserID + "&zxUserCityID=" + zxUserCityID + "&treeTwoID=" + treeTwoID + "&wpsUserID=" + wpsUserID);
});













