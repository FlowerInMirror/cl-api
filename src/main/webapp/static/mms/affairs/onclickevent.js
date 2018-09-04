

//index.jsp
 $(document).on("click", ".needdealt li", function () {
 $(".tc-taskDiv").show();
 $(".tc-taskDiv").animate({
 left: '81px',
 opacity: 1
 });
 });
//状态=处理弹出层关闭
$(document).on("click",".close_allPopup",function(){
    $(".anItemBor-active").removeClass("anItemBor-active");
    $(this).parents(".popup").animate({
        left: '100%'
    }, function () {
        $(this).hide();
    });
});
$(document).on("click", "#tab1 tr", function () {
    $(this).addClass("tractive").siblings().removeClass("tractive");//添加点击样式
    $('ul.j_uiTab9').find("li.uiTab9-active").click();//二段刷新
    $(".close_allPopup").click();//关闭三段
    showVisitByUid();
});

//材料上一段底部过滤
$(document).on("click", "#oneSectionFoot p", function () {
    var id = $(this).attr("id");//获取底部P标签ID
    var temp ;
    if(id =="userAll"){
        temp = $("#tab1 tr[data-vRemark]");
    }else if(id =="userNormal"){
        temp = $("#tab1 tr[data-vRemark='1']");
    }else if(id =="userException"){
        temp = $("#tab1 tr[data-vRemark='2']");
    }else if(id =="userProblems"){
        temp = $("#tab1 tr[data-vRemark='3']");
    }
    $("#tab1 tbody tr").hide();
    temp.show();
});
//affairData.jsp
//提交按钮，改变第五阶段状态
// $(document).on("click", ".changeFiveState", function () {
//     var basePath = $("#requestBasePath").val();
//     var uid = $("#tab1").find("tr.tractive").attr("data-uid");
//     if(uid == ""&&uid == null&&uid ==undefined){
//         alert("请您选择材料商");
//         return false;
//     }
//     if(confirm("您确定入驻资料已上传完毕要提交吗？")){
//         $.ajax({
//             type: 'POST',
//             url: basePath+"/user_info_three-api/updateUserInfoSupById",
//             data: {uid:uid},
//             success: function (data) {
//                 if(data.statusCode==0){
//                     alert("提交成功");
//                     initUserInfoList();//重新加载一段列表
//                 }else{
//                     alert("提交失败");
//                 }
//             }
//         });
//     }
// });
//-------------------------------   人事  --------------------------------
//affairData.jsp
var scrollFlag = true;
//照片切换
$(document).on("click", ".ScrollBtn", function () {
    debugger
    if (scrollFlag == true) {
        scrollFlag = false;
    }
    else {
        return false;
    }
    setTimeout(function () {
        //scale();

        scrollFlag = true;
    }, 520)
    var pType = $("#hidOptionType").val();
    var obj = $(".alertCon[data-ptype=" + pType + "]");
    if ($(this).hasClass("Prev_info")) {
        if (imgIndex == 0) {
            return false
        }
        if (imgIndex > 0) {
            imgIndex = imgIndex - 1;
        }
        else {
            imgIndex = $(obj).find(".ScrollUl li").length - 1;
        }
    } else {
        if (imgIndex < $(obj).find(".ScrollUl li").length - 1) {
            imgIndex = imgIndex + 1;
        } else {
            imgIndex = 0;

        }

    }
    scale($("#pm_infoAlert .alertCon[data-ptype=" + pType + "]"), imgIndex)
    checkAuditStatusToImg(imgIndex);
});

//affairData.jsp
//资料提交更改资料状态操作
$(document).on("click", ".addKCImg", function () {

    if (bbSave == true) { bbSave = false; }
    else { return false; }
    /*var obj = new Object();
    obj.uid = $("#tab1").find("tr.tractive").attr("data-uid");
    var tjType = $(this).data("type");
    obj.IsSC = tjType;*/
    var uid=$("#tab1").find("tr.tractive").attr("data-uid");
    if(uid==null||uid==""||uid==undefined){
        alert("请选择材料商");
        return false;
    }
    var liCount = $("#div_upload_35").find("li").length;//如果没有上传照片则提醒
    debugger
    if(liCount==undefined||liCount==0){
        alert("请上传照片,并刷新页面重试");
        return false;
    }
    if (confirm("您确定入驻资料已上传完毕要提交吗？") == false) {
        bbSave = true; return false;
    }

    //更新数据
    $.ajax({
        type: 'POST',
        url: basePath+"/user_info_three-api/AddImgToState",
        data: {uid:uid},
        success: function (data) {
            if(data.statusCode==0){
                alert("提交成功");
                bbSave = true;
                initUserInfoList();//重新加载一段列表
            }else{
                alert("提交失败");
                bbSave = true;
                $(".divUserBaseInfo").click();
            }
        },
        error: function (err) {
            alert("审核出错！");
            bbSave = true;
        }
    });
});

//affairData.jsp
//资料审核-第二版
$(document).on("click", "#pm_infoAlert .checkInfo", function () {
    if (bbSave == true) { bbSave = false; }
    else { return false; }
    var uid = $("#tab1").find("tr.tractive").first().attr("data-uid");
    var mobile = $("#tab1").find("tr.tractive").first().attr("data-mobile");
    var currentCardNo = $("#curLoginCardNumber").val();
    if(uid==null||uid =="" ||uid ==undefined){
        alert("请选择材料商");
        return false;
    }
    var pType = $("#hidOptionType").val();
    var obj = $(".alertCon[data-ptype=" + pType + "]");
    if (obj.length == 0) {
        alert("无效审核信息");
        bbSave = true; return false;
    }

    var divInfo = $(obj).find(".info_detail");
    var ckbitems = $(divInfo).find(".selected");
    if (ckbitems.length < $(divInfo).find("p").length) {
        alert("请审核所有信息");
        bbSave = true; return false;
    }

    var chkState = 2;
    var remark = "";
    var imgArr = [];//打回资料
    if (pType == "four") {//考察资料（多张上传，资料打回删除）
        //遍历选中项：是否存在不合格的内容
        $(ckbitems).each(function () {
            var attrID = $(this).attr("zz-photo");
            var imgItem = new Object();
            imgItem.attr_infoid = attrID;
            imgItem.isdelete = 0;//通过
            if ($(this).hasClass("true_default") == true) {
                imgItem.isdelete = 1;//不合格，删除
                chkState = 1;//不合格
                var val = $(this).parent().data("val");
                if (remark.indexOf(val) == -1) {
                    remark += val + "、";
                }
            }
            imgArr.push(imgItem);
        });
        //考察资料 已标记 通过|不合格进行处理
        if ($("input[name=zlfour]:checked").length > 0) {
            chkState = $("input[name=zlfour]:checked").val();
        }
    }else {
        //遍历选中项：是否存在不合格的内容
        $(ckbitems).each(function () {
            if ($(this).hasClass("true_default") == true) {
                chkState = 1;//不合格
                var val = $(this).parent().data("val");
                if (remark.indexOf(val) == -1) {
                    remark += val + "、";
                }
            }
        });
    }
    remark = remark.substring(0, remark.length - 1);
    var strData = "（" + remark + "）";
    var opType = 0;
    if (pType == "one") {
        opType = 1;
        strData = "基础资料" + strData + "不合格打回，请重新填写";
    }else if (pType == "two") {
        opType = 2;
        strData = "银行卡资料" + strData + "不合格打回，请重新填写";
    }else if (pType == "three") {
        opType = 3;
        strData = "认证资料" + strData + "不合格打回，请重新填写";
    }else if (pType == "four") {
        opType = 4;
        strData = "考察资料（外景，办公区，车间，生产线）" + strData + "不合格打回，请重新填写";
    }else if (pType == "five") {
        opType = 5;
        strData = "考察资料（店面，店内，代理品牌授权）" + strData + "不合格打回，请重新填写";
    }else if (pType == "six") {
        opType = 6;
        strData = "培训资料" + strData + "不合格打回，请重新填写";
    }

    var trainState = 0;
    if (pType == "five" && chkState == 2) {
        var radObj = $("input[type=radio][name=traintype]:checked");
        if (radObj.length == 0) {
            alert("请选择材料商的培训状态");
            bbSave = true; return false;
        }
        trainState = $(radObj).val();
    }

    var objIII = new Object();
    objIII.uid = uid;
    objIII.mobile = mobile;
    objIII.opType = opType;
    objIII.state = chkState;//打回
    objIII.strData = chkState == 2 ? "" : strData;
    objIII.remark = remark;
    objIII.cardNo = currentCardNo;
    //更新数据
    $.ajax({
        type: 'POST',
        url: basePath+"/user_info_three-api/checkDataInfoToStage",
        data: objIII,
        success: function (result) {
            debugger
            console.log(result);
            if (result.statusCode != 0) {
                debugger
                alert(result.body.msg);
                bbSave = true;
            }
            else {
                bbSave = true;

                if (pType = "five" && chkState == 2) {
                    var objTrain = new Object();
                    objTrain.uid = uid;
                    objTrain.trainState = trainState;
                    $.ajax({
                        type: 'POST',
                        url: basePath+"/user_info_three-api/SaveUserTrainState",
                        data: objTrain,
                        success: function (data) {
                            console.log("培训状态更新成功")
                        },
                        error: function () {
                            console.log("培训状态更新出错")
                        }
                    });
                }

                if (pType == "four") {
                    var objNei = new Object();
                    objNei.uid = uid;
                    objNei.items = imgArr;
                    //更新数据
                    $.ajax({
                        type: 'POST',
                        url: basePath+"/user_info_three-api/SubPhotoCheckToItmes",
                        data: objNei,
                        async: false,
                        success: function (msg) {
                            console.log(msg);
                        },
                        error: function (err) {
                        }
                    });
                }else {
                    var attrs = $(obj).attr("data-attrid");
                    if (attrs) {
                        var arrID = attrs.split("、");

                        for (var i = 0; i < arrID.length; i++) {
                            var attrID = arrID[i];
                            if (attrID != "" && attrID != "0") {
                                var objNei = new Object();
                                objNei.uid = uid;
                                objNei.attrID = attrID;
                                objNei.state = chkState == 2 ? 1 : 2;//1通过，2打回
                                //更新数据
                                $.ajax({
                                    type: 'POST',
                                    url: basePath+"/user_info_three-api/SubPhotoCheck",
                                    data: objNei,
                                    async: false,
                                    success: function (msg) {
                                        console.log(msg);
                                    },
                                    error: function (err) {
                                    }
                                });
                            }
                        }
                    }
                }
                $("#pm_infoAlert .close").click();
                initUserInfoList();//重新绑定人员列表
            }
        },
        error: function (err) {
            alert("审核出错！");
            bbSave = true;
        }
    });

});
//affairData.jsp
//资料审核-打回-第二
var bbSave = true;
$(document).on("click", ".btnUDataBack", function () {
    if (bbSave == true) { bbSave = false; }
    else { return false; }
    var pType = $("#hidOptionType").val();
debugger
    var strData = "";
    var opType = 0;
    if (pType == "one") {
        opType = 1;
        strData = "基础资料不合格打回，请重新填写";
    }else if (pType == "two") {
        opType = 2;
        strData = "银行卡资料" + strData + "不合格打回，请重新填写";
    }else if (pType == "three") {
        opType = 3;
        strData = "认证资料" + strData + "不合格打回，请重新填写";
    }else if (pType == "four") {
        opType = 4;
        strData = "考察资料（外景，办公区，车间，生产线）" + strData + "不合格打回，请重新填写";
    }else if (pType == "five") {
        opType = 5;
        strData = "考察资料（店面，店内，代理品牌授权）" + strData + "不合格打回，请重新填写";
    }else if (pType == "six") {
        opType = 6;
        strData = "培训资料" + strData + "不合格打回，请重新填写";
    }
    if(opType==0){
        alert("请刷新页面重试");
        return false;
    }
    debugger
    var objIII = new Object();
    objIII.uid = $("#tab1").find("tr.tractive").first().attr("data-uid");
    objIII.mobile = $("#tab1").find("tr.tractive").first().attr("data-mobile");
    objIII.opType = opType;
    objIII.state = 1;//1打回 2通过
    objIII.strData = strData;
    objIII.remark = "";
    objIII.cardNo = $("#curLoginCardNumber").val();
    //更新数据
    $.ajax({
        type: 'POST',
        url: basePath+"/user_info_three-api/checkDataInfoToStage",
        data: objIII,
        success: function (result) {
            if (result.isSuccess == false) {
                alert(result.msg);
                bbSave = true;
            }else {
                bbSave = true;
                $("#pm_infoAlert .close").click();
                //loadUserList();//重新绑定人员列表
                initUserInfoList();
            }
        },
        error: function (err) {
            alert("审核出错！");
            bbSave = true;
        }
    });
});

//弹出审核层
//affairData.jsp
$(document).on("click",".analyItemCon[data-ptype]",function () {
    //fn();
    debugger
    var pType = $(this).data("ptype");
    if(pType == "four"){
        if($('#img_four').attr("data-zFour") == 0){
            return false;
        }
    }
    $("#hidOptionType").val(pType);
    $("#pm_infoAlert .alertCon").hide();
    $("#pm_infoAlert .alertCon[data-ptype=" + pType + "]").show();
    //$("#pm_infoAlert").children(".alertCon:visible").find("li").attr("data-msg", "true");
    $("#pm_infoAlert .alertCon[data-ptype]").find("li").removeAttr("data-msg");
    $("#pm_infoAlert .alertCon[data-ptype=" + pType + "]").find("li").attr("data-msg", "true");
    checkAuditStatusToImg(0);
    if (pType == "four") {
        $("#img_five").find("div:eq(0)").removeClass("datumdivLeft");
        $("#img_four").find("div:eq(0)").addClass("datumdivLeft");

    }
    else if (pType == "five") {
        /* $("#img_five").find("div:eq(0)").removeClass("datumdivLeft");
         $("#img_four").find("div:eq(0)").addClass("datumdivLeft");*/
    }

    $(".datumdivLeft p:eq(0)").click();
    setTimeout(function () {
        rxued.alert.jAlert("#pm_infoAlert", 800, function () {
            rxued.scrolls.doScroll($(".alertCon[data-ptype=one] .Scroll_2"), ".ScrollUl", ".ScrollUl li", ".Prev_info", ".Next_info", 1);
            rxued.scrolls.doScroll($(".alertCon[data-ptype=two] .Scroll_2"), ".ScrollUl", ".ScrollUl li", ".Prev_info", ".Next_info", 1);
            rxued.scrolls.doScroll($(".alertCon[data-ptype=three] .Scroll_2"), ".ScrollUl", ".ScrollUl li", ".Prev_info", ".Next_info", 1);
            rxued.scrolls.doScroll($(".alertCon[data-ptype=four] .Scroll_2"), ".ScrollUl", ".ScrollUl li", ".Prev_info", ".Next_info", 1);
            rxued.scrolls.doScroll($(".alertCon[data-ptype=five] .Scroll_2"), ".ScrollUl", ".ScrollUl li", ".Prev_info", ".Next_info", 1);
        });
        scale($("#pm_infoAlert .alertCon[data-ptype=" + pType + "]"), 0);
        var w = $('.ts')[0].naturalWidth;//533 4.9
        var h = $('.ts')[0].naturalHeight;//800
    }, 1);
});
var checkFlag = true;
//审核通过按钮
$(document).on("click",".true_default",function () {
    if (checkFlag == true) { checkFlag = false; }
    else { return false; }
    setTimeout(function () {
        checkFlag = true;
    }, 510);

    var pType = $("#hidOptionType").val();
    var obj = $(".alertCon[data-ptype=" + pType + "]");
    if ($(this).hasClass("selected")) {
        //图片审核
        if ($(this).parent().hasClass("imgbox") == true) {
            $(obj).find(".Next_info").click();
        }
    } else {
        infoAudit(1, $(this));
    }
});
//审核未通过按钮
$(document).on("click",".false_default",function () {
    if (checkFlag == true) { checkFlag = false; }
    else { return false; }
    setTimeout(function () {
        checkFlag = true;
    }, 510);

    var pType = $("#hidOptionType").val();
    var obj = $(".alertCon[data-ptype=" + pType + "]");
    if ($(this).hasClass("selected")) {
        //图片审核
        if ($(this).parent().hasClass("imgbox") == true) {
            $(obj).find(".Next_info").click();
        }
    } else {
        infoAudit(2, $(this));
    }
});
//--------------------------------   设置  -------------------------------------
$(document).on("click","#shopSetUp .img_edit",function () {
    var divObj = $(this).closest("div.analyItem");

    $(divObj).find(".info-look").hide();
    $(divObj).find(".info-edit").show();

    $(this).parent(".imgbox").hide();
    $(this).parent(".imgbox").next(".imgbox").show();
});
$(document).on("click","#shopSetUp .goback_img",function () {
    var divObj = $(this).closest("div.analyItem");
    $(divObj).find(".info-look").show();
    $(divObj).find(".info-edit").hide();

    $(this).parents(".analyItemCon").find(".imgbox1").show();
    $(this).parent(".imgbox2").hide();
});
$(document).on("click","#shopSetUp #btnSaveSuppliersStoreInfo",function () {
    debugger
    if (bbSave == true) { bbSave = false; }
    else { return false; }
    var storeName = $("#txtStoreName").val();
    if (storeName == "" || storeName == $("#txtStoreName").attr("placeholder")) {
        alert("请输入店铺名称！");
        $("#txtStoreName").focus();
        bbSave = true; return false;
    }
    debugger
    var uid = $("#tab1").find("tr.tractive").first().attr("data-uid");
    var mobile = $("#tab1").find("tr.tractive").first().attr("data-mobile");

    //更新数据
    $.ajax({
        type: 'POST',
        url: ctx+"/jmat/pmUserinfoJoin/SaveStoreInfoToBase",
        data: {"uid":uid,"mobile":mobile,"pmjStoreName":storeName},
        success: function (data) {
            console.log(data);
            if(data.body.isSuccess!=0){
                layer.alert("保存成功！",{icon:6});
            }
            $("#tab1").find("tr.tractive").click();
            /*if (result.isSuccess == false) {
                alert(result.msg);
                bbSave = true;
            }
            else {
                $("#tab1").find("tr.tractive").click();
                bbSave = true;
            }*/
        },
        error: function (err) {
            alert("保存出错！");
            bbSave = true;
        }
    });

});
//--------------------------------   店铺固定  ------------------------------------
//shopFixed.jsp  材料调价
$(document).on("click","[data-div='fixedOne'] .btnModifyPrice",function () {
    debugger
    var userInfo=$("#tab1").find("tr.tractive").first();
    var trObj = $(this).closest("tr");
    var obj = new Object();
    obj.uid = userInfo.attr("data-uid");
    obj.cityID = userInfo.attr("data-inductionarea");
    obj.mobile = userInfo.attr("data-mobile");
    //var basePath = $("#basePath").val();
    layer.open({
        type: 2,
        title: "调价详情",
        area: ['45%', '60%'],
        btn: ["提交"],
        content: basePath+"/user_info_three-api/index/shoplayerAppStoreChangePrice",
        success: function (layero,index) {
            debugger
            var baseIframe = $(layero).find("iframe").contents();//获取子页面dom
            var baseDom = baseIframe.find("#shoplayerAppStoreChangePrice");
            layero.find('.layui-layer-btn').css('text-align', 'center');
            baseDom.find(".lblMatNameToAPPStoreMore").html($(trObj).data("search"));
            baseDom.find(".lblUnitNameToAPPStoreMore").html($(trObj).data("unitname"));
            baseDom.find(".lblMatSpecToAPPStoreMore").html($(trObj).data("matspec"));
            baseDom.find(".lblMatBrandToAPPStoreMore").html($(trObj).data("brand"));
            baseDom.find(".lblMatPriceToAPPStoreMore").html($(trObj).find("td:eq(4)").html());
            baseDom.find(".lblMatLevelToAPPStoreMore").html($(trObj).find("td:eq(0)").html());
        },
        yes:function (index,layero) {
            debugger
            var baseIframe = $(layero).find("iframe").contents();//获取子页面dom
            var baseDom = baseIframe.find("#shoplayerAppStoreChangePrice");
            var matPrice = baseDom.find("#txtMatPriceToAPPStoreMore").val();//材料价格
            var remark = baseDom.find("#txtChangeRemarkToAPPStoreMore").val();//调价说明
            var matID = $(trObj).data("matid");
            if (matPrice === "" || isNaN(matPrice) || matPrice == 0) {
                //不做出来
            } else {
                var obj = new Object();
                obj.MatID = matID;
                obj.MatPrice = matPrice;
                obj.UserID = userInfo.attr("data-uid");
                obj.Remark = remark;
                var arrObj=[];
                arrObj.push(obj);
                $.ajax({
                    type: 'POST',
                    url: cs_gc +"/APPSuppliers/UpdateMaterialPrice",//调价
                    data: {"":arrObj},
                    success: function (data) {
                        console.log(data);
                        if(data.StatusCode==0){
                            layer.alert("修改成功！",{icon:6});
                        }
                    }
                });
                $("#tab1").find("tr.tractive").click();
                layer.close(index); //关闭当前层
            }
        }
    });
});
//材料下架
//shopFixed.jsp
$(document).on("click", "[data-div='fixedOne'] .btnRemoveMaterial", function () {
    debugger
    // if (bbSave == true) { bbSave = false; }
    // else { return false; }

    if (confirm("确认下架此材料？") == false) {
        bbSave = true; return false;
    }
    var obj = new Object();
    obj.uid = $("#tab1").find("tr.tractive").first().attr("data-uid");
    obj.mobile = $("#tab1").find("tr.tractive").first().attr("data-mobile");
    obj.matID = $(this).data("matid");
    //更新数据
    $.ajax({
        type: 'POST',
        url: basePath+"/user_info_three-api/MaterialDropOff",
        data: obj,
        success: function (data) {
            if (data.statusCode == 0) {
                alert("下架成功");
                $("#tab1").find("tr.tractive").click();
            }else {
                alert("下架失败");
            }
        },
        error: function (err) {
            alert("审核出错！");
        }
    });
});
//layer下架 shoplayerAppMoreToStoresFixed.jsp
$(document).on("click", "#shoplayerAppMoreToStoresFixed .btnRemoveMaterial", function () {
    if (bbSave == true) { bbSave = false; }
    else { return false; }

    if (confirm("确认下架此材料？") == false) {
        bbSave = true; return false;
    }
    var uid = $("#userIdLayer").val();
    var mobile = $("#mobileLayer").val();
    var basePathLayer = $("#basePath").val();
    var obj = new Object();
    obj.uid=uid;
    obj.mobile = mobile;
    obj.matID = $(this).data("matid");
    //更新数据
    $.ajax({
        type: 'POST',
        url: basePathLayer+"/user_info_three-api/MaterialDropOff",
        data: obj,
        success: function (data) {
            if (data.statusCode == 0) {
                alert("下架成功");
                location.reload();
            }else {
                alert("下架失败");
            }
        },
        error: function (err) {
            alert("审核出错！");
        }
    });
});
//shoplayerAppMoreToStoresFixed 调价
$(document).on("click","#shoplayerAppMoreToStoresFixed .btnModifyPrice",function () {
    var trObj = $(this).closest("tr");
    var uid = $("#userIdLayer").val();
    var cityID = $("#cityIDLayer").val();
    var mobile = $("#mobileLayer").val();
    var obj = new Object();
    obj.uid = uid;
    obj.cityID = cityID;
    obj.mobile = mobile;
    var basePath = $("#basePath").val();
    layer.open({
        type: 2,
        title: "调价详情",
        area: ['45%', '60%'],
        btn: ["提交"],
        content: basePath+"/user_info_three-api/index/shoplayerAppStoreChangePrice?uid="+uid+"&cityID="+cityID+"&mobile="+mobile+"",
        success: function (layero,index) {
            debugger
            var baseIframe = $(layero).find("iframe").contents();//获取子页面dom
            var baseDom = baseIframe.find("#shoplayerAppStoreChangePrice");
            layero.find('.layui-layer-btn').css('text-align', 'center');
            baseDom.find(".lblMatNameToAPPStoreMore").html($(trObj).data("search"));
            baseDom.find(".lblUnitNameToAPPStoreMore").html($(trObj).data("unitname"));
            baseDom.find(".lblMatSpecToAPPStoreMore").html($(trObj).data("matspec"));
            baseDom.find(".lblMatBrandToAPPStoreMore").html($(trObj).data("brand"));
            baseDom.find(".lblMatPriceToAPPStoreMore").html($(trObj).find("td:eq(4)").html());
            baseDom.find(".lblMatLevelToAPPStoreMore").html($(trObj).find("td:eq(0)").html());
        },
        yes:function (index,layero) {
            debugger
            var baseIframe = $(layero).find("iframe").contents();//获取子页面dom
            var baseDom = baseIframe.find("#shoplayerAppStoreChangePrice");
            var matPrice = baseDom.find("#txtMatPriceToAPPStoreMore").val();//材料价格
            var remark = baseDom.find("#txtChangeRemarkToAPPStoreMore").val();//调价说明
            var cs_gc = $("#cs_gcLayer").val();
            var matID = $(trObj).data("matid");
            if (matPrice === "" || isNaN(matPrice) || matPrice == 0) {
                //不做出来
            } else {
                var obj = new Object();
                obj.MatID = matID;
                obj.MatPrice = matPrice;
                obj.UserID = uid;
                obj.Remark = remark;
                var arrObj=[];
                arrObj.push(obj);
                $.ajax({
                    type: 'POST',
                    url: cs_gc +"/APPSuppliers/UpdateMaterialPrice",//调价
                    data: {"":arrObj},
                    success: function (data) {
                        console.log(data);
                        if(data.StatusCode==0){
                            layer.alert("修改成功！",{icon:6});
                        }
                    }
                });
                layer.close(index); //关闭当前层
                location.reload(); //刷新父页面
            }
        }
    });

});
//----------------------------  开始 --------------------------------
//设置主营 shoplayerMaterialMainToTree.jsp
$(document).on("click", "#shopStore .setBtn", function () {
    var opType = $(this).data("major");
    var uid = $("#tab1").find("tr.tractive").first().attr("data-uid");
    var cityID = $("#tab1").find("tr.tractive").first().attr("data-inductionarea");
    var mobile = $("#tab1").find("tr.tractive").first().attr("data-mobile");
    var setBtnStr = $(".setBtnOldOne[data-type='tree']").text();
    debugger
    if (opType == "tree") {//设置主营

        if(setBtnStr!='--'){
            layer.prompt({
                formType: 1,//输入框类型，支持0（文本）默认1（密码）2（多行文本）
                //value: '请输入密码',
                title: '密码验证',
                btn: ["确定","修改密码","取消"],
                //.offset: ['150px', '600px']
                btn2:function (index,layero) {
                    layer.open({
                        type: 2,
                        title: "修改密码",
                        area: ['20%', '32%'],
                        btnAlign:'c',
                        btn: ["保存"],
                        content: basePath+"/user_info_three-api/layer/shoplayerMatVerification",
                        success: function (layero) {
                            layero.find('.layui-layer-btn').css('text-align', 'center');
                        },
                        yes:function (index,layero) {
                            var baseIframe = $(layero).find("iframe").contents();//获取document本文重点
                            var matVerification = baseIframe.find("#matVerification");
                            var matPassWordOld = matVerification.find("#matPassWordOld").val();
                            var matPassWordNew = matVerification.find("#matPassWordNew").val();
                            var matPassWordNewSecond = matVerification.find("#matPassWordNewSecond").val();
                            if(matPassWordNew==""||matPassWordNewSecond==""){
                                layer.alert("新输入密码不能为空！",{icon:5});
                                return false;
                            }
                            if(matPassWordNew!=matPassWordNewSecond){
                                layer.alert("两次密码输入不一致，修改失败",{icon:5});
                                return false;
                            }
                            $.ajax({
                                type: 'POST',
                                url: ctx+"/jmat/matVerification/updateMatVerification",//更新密码
                                data: {"vfiType":2,"vfiPwd":matPassWordOld,"vfiPwdNew":matPassWordNew},
                                success: function (data) {
                                    console.log(data);
                                    var state = data.state;
                                    if(state==-2){
                                        layer.alert("旧密码输入错误，请重新输入！",{icon:5});
                                        return false;
                                    }else if(state==-1){
                                        layer.alert("密码更新失败，请刷新页面重试！",{icon:5});
                                        return false;
                                    }else{
                                        layer.alert("密码修改成功！",{icon:6});
                                    }
                                }
                            });
                            layer.close(index);
                        }
                    });
                    layer.close(index);
                }
            },function(value, index, elem){
                console.log(value);
                var vfiType = 2;
                $.ajax({
                    url: ctx+"/jmat/matVerification/findMatVerificationByPwd",
                    type: "post",
                    dataType: "json",
                    data: {passWord:value,vfiType:vfiType},
                    success: function (data) {
                        if(data.body!=undefined){
                            layer.close(index);
                            showMaterialAndSaveMaterial(uid,cityID,mobile);
                        }else{
                            layer.alert("您输入的密码有误，不能进行修改操作！",{icon:5});
                        }
                    }
                });
            });
        }else{
            showMaterialAndSaveMaterial(uid,cityID,mobile);
        }
    }else {//材料管理
        if(setBtnStr=='--'){
            layer.msg("请设置主营，再添加材料！",{
                time:3000
            });
            return false;
        }
        var obj = new Object();
        obj.uid = uid;
        obj.city = cityID;
        layer.open({
            type: 2,
            title: "设置",
            area: ['80%', '90%'],
            btnAlign:'c',
            content: basePath+"/user_info_three-api/layer/shoplayerAPPMoreToStoresFixed?uid="+uid+"&cityID="+cityID+"",
            success: function (layero) {
                layero.find('.layui-layer-btn').css('text-align', 'center');
            },
            cancel: function(index, layero){
                debugger
                layer.close(index);
                $("#tab1").find("tr.tractive").click();
                return false;
            }
        });
    }
});
function showMaterialAndSaveMaterial(uid,cityID,mobile) {
    //$("#divPublicLayerDetails").load(basePath+"/user_info_three-api/index/shoplayerMaterialMainToTree", obj, function () {})
    layer.open({
        type: 2,
        title: "设置",
        area: ['100%', '90%'],
        btnAlign:'c',
        btn: ["保存"],
        content: basePath+"/user_info_three-api/layer/shoplayerMaterialMainToTree?uid="+uid+"&cityID="+cityID+"&mobile="+mobile+"",
        success: function (layero) {
            layero.find('.layui-layer-btn').css('text-align', 'center');
        },
        yes:function (index,layero) {
            debugger
            //var res = window["layui-layer-iframe" + index].callbackdata();
            //temp = $(layero).find("iframe")[0].contentWindow.temp //获取弹层里面的变量
            //var data = $(layero).find("iframe")[0].contentWindow.formData();//调用弹出层页面的函数
            //if(data){fun(data);}//执行主页面传入的回调函数获取弹出层选取的相关数据对象
            var baseIframe = $(layero).find("iframe").contents();//获取document本文重点
            var brandName = baseIframe.find("#brandNameLayer").val();
            var treeSecond = baseIframe.find(".treeSecond .linkblock.activetwo");
            if (bbSave == true) { bbSave = false; }
            else { return false; }
            var treeItems = [];
            $(treeSecond).each(function () {
                var treeID = $(this).data("treeid");
                var matCount = $(this).data("matcount");

                treeItems.push(treeID);//保存二级类
                var threeItems = $(".treeThird .analyItem[data-parent=" + treeID + "] input:checked");
                var checkCount = threeItems.length;
                if (checkCount < matCount) {
                    //保存三级ID
                    $(threeItems).each(function () {
                        var treeThreeID = $(this).val();
                        treeItems.push(treeThreeID);
                    });
                }
            });
            if (treeItems.length == 0) {
                alert("请选择分类，在操作保存");
                bbSave = true; return false;
            }
            var obj = new Object();
            obj.mobile = mobile;
            obj.brandName = brandName;
            obj.items = treeItems;
            //更新数据
            $.ajax({
                type: 'POST',
                url: cs_gc +"/APPUserInfo/SaveTreeItems",
                data: obj,
                success: function (msg) {
                    console.log(msg);
                    debugger
                    if (msg.StatusCode < 0) {
                        alert(msg.Body);
                        bbSave = true;
                    }else {
                        alert(msg.Body);
                        //$("#divMaterial .anItemBor-active").click();
                        //$(".layui-layer-close").click();
                        $(".close_allPopup").click();
                        bbSave = true;
                    }
                },
                error: function (err) {
                    alert("保存出错！");
                    bbSave = true;
                }
            });
            layer.close(index); //关闭当前层
        }
    });
}
//shoplayerMaterialMainToTree.jsp  设置主营与提交事件
//一级分类选择
$(document).on("click", "#shoplayerMaterialMainToTree .analyItemFirst", function () {
    $(this).addClass("anItemBor-active").siblings().removeClass("anItemBor-active");

    var treeOneID = $(this).data("treeid");
    $(".treeSecond .treeitem[data-parent]").hide();
    $(".treeSecond .treeitem[data-parent=" + treeOneID + "]").show();
});

//二级分类选中事件
$(document).on("click", "#shoplayerMaterialMainToTree .linkblock", function () {
    var maxTreeCount =  $("#majorMaxCount").val();
    var isCheck = $(this).hasClass("activetwo");
    isCheck = !isCheck;//取反：是否选中
    if (isCheck == true) {
        //可用|不可用 处理（二级分类不可选）
        var treeTwoCount = $(".treeSecond .linkblock.activetwo").length;
        if (treeTwoCount >= maxTreeCount) {
            alert("超出可营最大量");
            return true;
        }
    }
    var treeTwoID = $(this).data("treeid");

    if (isCheck == true) {
        $(this).addClass("activetwo").addClass("active");
        //选中 三级分类
        $(".treeThird .analyItem[data-parent=" + treeTwoID + "] input").prop("checked", true);
    }
    else {
        $(this).removeClass("activetwo").removeClass("active");
        //清空三级选中
        $(".treeThird .analyItem[data-parent=" + treeTwoID + "] input").prop("checked", false);
    }
    SumTreeCheckCount(treeTwoID);//计算选中量
    ShowThridTreeItems();//显示第三极的内容
});

//三级分类 选择
$(document).on("click", "#shoplayerMaterialMainToTree .treeThird input[type=checkbox]", function () {
    var labelT = $(this).closest(".analyItem");
    var treeID = $(labelT).data("parent");//获取二级ID
    SumTreeCheckCount(treeID);//计算选中量
});

//var bbSave = true;
//保存科目数
$(document).on("click", ".btnSaveTreeToSuppliers", function () {
    if (bbSave == true) { bbSave = false; }
    else { return false; }
    var treeItems = [];
    $(".treeSecond .linkblock.activetwo").each(function () {
        var treeID = $(this).data("treeid");
        var matCount = $(this).data("matcount");

        treeItems.push(treeID);//保存二级类
        var threeItems = $(".treeThird .analyItem[data-parent=" + treeID + "] input:checked");
        var checkCount = threeItems.length;
        if (checkCount < matCount) {
            //保存三级ID
            $(threeItems).each(function () {
                var treeThreeID = $(this).val();
                treeItems.push(treeThreeID);
            });
        }
    });
    if (treeItems.length == 0) {
        alert("请选择分类，在操作保存");
        bbSave = true; return false;
    }

    var url = $("#cs_gcShopLayer").val();
    var obj = new Object();
    obj.mobile = $("#mobileLayer").val();
    obj.brandName = $("#brandNameLayer").val();
    obj.items = treeItems;

    //更新数据
    $.ajax({
        type: 'POST',
        url: url +"/APPUserInfo/SaveTreeItems",
        data: obj,
        success: function (msg) {
            console.log(msg);
            debugger
            if (msg.StatusCode < 0) {
                alert(msg.Body);
                bbSave = true;
            }else {
                alert(msg.Body);
                $("#divMaterial .anItemBor-active").click();
                $(".layui-layer-close").click();
                bbSave = true;
            }
        },
        error: function (err) {
            alert("保存出错！");
            bbSave = true;
        }
    });
});
//shoplayerAPPMoreToStoresFixed.jsp 添加材料
$(document).on("click", "#shoplayerAppMoreToStores .ThreeViewClick", function () {
    var uid = $("#userIdLayer").val();
    var cityID = $("#cityIDLayer").val();
    var basePath = $("#basePath").val();
    layer.open({
        type: 2,
        title: "添加材料",
        area: ['100%', '100%'],
        btnAlign:'c',
        btn: ["添加到待上架材料"],
        content: basePath+"/user_info_three-api/layer/shoplayerAPPStoreAddMaterial?uid="+uid+"&cityID="+cityID+"",
        success: function (layero) {
            layero.find('.layui-layer-btn').css('text-align', 'center');

        },
        yes:function (index,layero) {
            debugger
            var baseIframe = $(layero).find("iframe").contents();//获取document本文重点
            var baseDom = baseIframe.find("#shoplayerAPPStoreAddMaterial");

            //获取选中科目树
            var arrTree = [];
            baseDom.find(".menuthird li[data-one]").attr("zz-over", 0);
            //二级类
            baseDom.find(".menusecond li:has(:checked)").each(function () {
                var treeID = $(this).data("treeid");
                var items = baseDom.find(".menuthird li[data-two=" + treeID + "][zz-over=0]");
                if (baseDom.find(".menuthird li[data-two=" + treeID + "]").length == $(items).find(":checked").length) {
                    var item = new Object();
                    item.matID = treeID;
                    item.matLevel = 2;
                    item.userID = uid;
                    arrTree.push(item);
                    $(items).attr("zz-over", 1);
                }
            });
            //三级（材料名称）
            baseDom.find(".menuthird li[zz-over=0]:has(:checked)").each(function () {
                var treeID = $(this).data("treeid");
                var item = new Object();
                item.matID = treeID;
                item.matLevel = 3;
                item.userID = uid;
                arrTree.push(item);
            });
            if (arrTree.length == 0) {
                alert("请选择材料树");
                bbSave = true; return false;
            }

            var arrBrand = [];
            arrBrand.push(uid);
            //获取，选中品牌
            var ckbBrand = baseDom.find("#divBrandNameItems input[type=checkbox]:checked");
            $(ckbBrand).each(function () {
                arrBrand.push($(this).data("brand"));
            });
            if (arrBrand.length == 0) {
                alert("请选择材料的品牌");
                bbSave = true; return false;
            }
            baseDom.find("#tabDataToAPPMaterial").html("");

            $.ajax({
                url: basePath+"/user_info_three-api/SaveTreeToAPPMore",//保存选中树与品牌,07-06 10:47分开插入
                type: "post",
                data: JSON.stringify(arrTree),
                contentType : 'application/json;charset=utf-8',
                dataType:"json",
                success: function (data) {
                    console.log(data);
                    if(data.statusCode==0){
                        SaveBrandToAPPMore(basePath,arrBrand,uid,cityID,basePath);//保存品牌
                        //layer.alert("添加成功！",{icon:6});
                    }
                    //layer.close(index); //关闭当前层
                    /*if (result.isSuccess == false) {
                        alert(result.msg);
                        bbSave = true;
                    }
                    else {
                        bbSave = true;
                        $(".AddTreeBrandToSucessBtn").click();//进入下一步 未上架材料
                    }*/
                },
            });
        }
    })
});
function SaveBrandToAPPMore(basePath,arrBrand,uid,cityID,basePath) {
    $.ajax({
        url: basePath+"/user_info_three-api/SaveBrandToAPPMore",//保存选中树与品牌,07-06 10:47分开插入
        type: "post",
        data: JSON.stringify(arrBrand),//uid存到数组里
        contentType : 'application/json;charset=utf-8',
        dataType:"json",
        success: function (data) {
            if(data.statusCode==0){
                //上架材料  shoplayerAPPStorePerfectMaterial.jsp
                layer.open({
                    type: 2,
                    title: "上架材料",
                    area: ['100%', '100%'],
                    btnAlign:'c',
                    btn: ["上架材料"],
                    content: basePath+"/user_info_three-api/layer/shoplayerAPPStorePerfectMaterial?uid="+uid+"&cityID="+cityID+"",
                    success: function (layero) {
                        layero.find('.layui-layer-btn').css('text-align', 'center');
                    },
                    yes:function (index,layero) {
                        var baseIframe = $(layero).find("iframe").contents();//获取document本文重点
                        var baseDom = baseIframe.find("#shoplayerAPPStorePerfectMaterial");
                        debugger
                        if (bbSave == true) { bbSave = false; }
                        else { return false; }
                        var arrMat = [];
                        var isOK = true;
                        //验证材料录入合不合格
                        baseDom.find("#tabDataToAPPMaterial tr").not("[zz-del]").each(function () {
                            var matID = $(this).data("matid");
                            //加入库的
                            var txtPrice = $(this).find("input[type=text]");
                            var costPrice = $(txtPrice).val();//价格
                            if (costPrice === "" || isNaN(costPrice) || costPrice == 0) {
                                //不做出来
                            } else {
                                var item = new Object();
                                item.matID = matID;
                                item.costPrice = costPrice;
                                item.userID = uid;
                                arrMat.push(item);
                            }
                        });
                        if (arrMat.length == 0) {
                            alert("请确定标准，填写价格，在操作上架");
                            bbSave = true; return false;
                        }
                        /*                 var obj = new Object();
                         obj.uid = uid;
                         obj.MatItems = arrMat;*/
                        //上架材料
                        $.ajax({
                            url: basePath+"/user_info_three-api/SaveMaterialToShelf",
                            type: "post",
                            dataType: "json",
                            data: JSON.stringify(arrMat),
                            contentType : 'application/json;charset=utf-8',
                            success: function (data) {
                                console.log(data);
                                if(data.statusCode==0){
                                    layer.msg("上架成功！",{icon:6});
                                    layer.close(index); //关闭当前层
                                    location.reload(); //刷新父页面
                                }
                                /*if (!result.isSuccess) {
                                 alert("操作失败！");
                                 } else {
                                 if ($("#div_app .anItemBor-active").length > 0) {
                                 $("#div_app .anItemBor-active").click();
                                 }
                                 else {
                                 $(".layui-layer-close").click();
                                 $(".anItemBor-active").click();
                                 }
                                 }*/
                            },
                            error: function (err) {
                                console.log(err);
                                layer.alert("操作出错！");
                            }
                        });
                        /*                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                         parent.layer.close(index);
                         $(".close_allPopup").click();*/
                    }
                })
            }
        }
    })
}
//选中事件 shoplayerAPPStoreAddMaterial.jsp
$(document).on("click", "#shoplayerAPPStoreAddMaterial .menu input[type=checkbox]", function () {
    var ulObj = $(this).closest("ul");
    var level = $(ulObj).data("level");
    var treeID = $(this).closest("li").data("treeid");

    var checked = $(this).is(":checked");
    switch (level) {
        case 1://选中|取消选中  二级|三级类
            $(".menusecond li[data-one=" + treeID + "] input[type=checkbox]").prop("checked", checked);
            $(".menuthird li[data-one=" + treeID + "] input[type=checkbox]").prop("checked", checked);
            break;
        case 2://二级选中：控制三级分类
            $(".menuthird li[data-two=" + treeID + "] input[type=checkbox]").prop("checked", checked);
            break;
        case 3://三级选中：控制二级
            var treeTwoID = $(this).closest("li").data("two");
            var checkedTwo = $(".menuthird li[data-two=" + treeTwoID + "] input[type=checkbox]:checked").length == 0 ? false : true;
            $(".menusecond li[data-treeid=" + treeTwoID + "] input[type=checkbox]").prop("checked", checkedTwo);
            break;
        default:
            break;
    }
    BindBrandNameToUser();
});
//shoplayerAPPStorePerfectMaterial.jsp  //添加价格
$(document).on("click", "#tabDataToAPPMaterial .setBtn", function () {
    $(this).hide();
    $(this).prev().show();
});
//shoplayerAPPStorePerfectMaterial.jsp 展示的材料-删除
$(document).on("click", "#tabDataToAPPMaterial img.delete_img", function () {
    var trObj = $(this).closest("tr");
    $(trObj).addClass("hide").attr("zz-del", 1);
    //if (confirm("确认删除此材料？") == true) {
    //}
});
//--------------------------------   结束 ------------------------------
//--------------------------------   改变事件  --------------------------------
$(document).on("change",".theadselect",function () {
    $("#tab1 tr").hide();
    var cityId = $(this).val();
    if(cityId==-1){
        $("#tab1 tr").show();
        return false;
    }
    $("#tab1 tr[data-inductionarea='"+cityId+"']").show();

});