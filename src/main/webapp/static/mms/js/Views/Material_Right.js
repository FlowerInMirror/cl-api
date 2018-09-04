/*右侧 JS*/
/**************************************时间格式化处理************************************/
function dateFtt(fmt,date)
{ //author: meizz
    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}


// 创建 品牌二维码
function createBrandQRcoed(elementID,matID,cityID){
    // 生成规则如下
    var url = 'http://c.rxjyzx.com/SD/'+matID+'/'+cityID;
    // 例:http://c.rxjyzx.com/SD/33A923C1-E74B-4911-AFFB-C52DE38347BD/12
    $('#'+ elementID).attr("data-href", url);
    $('#'+ elementID).qrcode({
        width: 84,
        height: 84,
        correctLevel: 0,
        background: "#efeef3", //背景颜色
        foreground: "black", //前景颜色
        text: url
    });
}

$(function () {
    //====================基础====================

    //平台

    //==========标准==========
    //瑞祥标准
    $(document).on("click", ".rxstander", function () {
        $(".rxstander").removeClass("rxstanderSelected");
        $(this).addClass("rxstanderSelected");
        $("#lblRXClaimToCheck").html($(this).data("claim"));
        $("#lblRXRemarkToCheck").html($(this).data("method"));
    });
    //检验标准
    $(document).on("click", ".inspectstander", function () {
        $(".inspectstander").removeClass("inspectstanderSelected");
        $(this).addClass("inspectstanderSelected");
        $(".lblStandardTitleName").html($(this).attr("zz-name"));
        $(".lblStandardTitleOne").html($(this).attr("zz-one"));
        $(".lblStandardTitleTwo").html($(this).attr("zz-two"));
    });


    //修改品牌
    $(document).on("click", ".modify", function () {
        $(".materialTopNavConbox").hide();
        $(".materialTopNavConboxSave").show();
        $("#divBrandPhotoBySave").append($("#divBrandPhotoByLook"));

    });


    //选定参数
    $(document).on("change", "#ddlStandardPara", function () {
        var paraID = $("#ddlStandardPara").val();
        if (paraID != "" && paraID != "0") {
            if ($("#divComparedPhoto li[data-paraid=" + paraID + "]").length == 0) {
                var paraName = $("#ddlStandardPara option[value=" + paraID + "]").text();
                $("#divComparedPhoto").append('<li data-type="3" data-paraid="' + paraID + '"><div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth180 relative"><a href="javascript:" class="uiImgUpload-first"><input type="file" value="" class="file fileone"><em class="bgIcon file-ico"></em></a><img src="" /><!--图片遮罩和放大按钮 开始--><div class="uiImgUpload-mark"><div class="uiImgUpload-mark-bg"></div><div class="uiImgUpload-mark-link"><div class="dis_il_block"><a href="jvascript:" class="upagain_link"><input type="file" class="fileone"></a><a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a><a href="javascript:" class="delect_link"></a></div></div></div><!--图片遮罩和放大按钮 结束--></div><p class="tx-center lh30">' + paraName + '</p></li>');
                initImageUpload();
            }
        }
    });

    //新增产品效果图
    $(document).on("click", "#aAddEffectPhoto", function () {
        $("#ulEffectPhoto").append('<li class="clearfix" data-type="7"><div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth180 relative"><a href="javascript:" class="uiImgUpload-first"><input type="file" value="" class="file fileone"><em class="bgIcon file-ico"></em></a><img src="" /><!--图片遮罩和放大按钮 开始--><div class="uiImgUpload-mark"><div class="uiImgUpload-mark-bg"></div><div class="uiImgUpload-mark-link"><div class="dis_il_block"><a href="jvascript:" class="upagain_link"><input type="file" class="fileone"></a><a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a><a href="javascript:" class="delect_link"></a></div></div></div><!--图片遮罩和放大按钮 结束--></div><div class="prodiagramOpera relative pt5"><div class="prodiagramOperaLeft"><div class="clearfix mb10"><span class="fl lh28 width50">客户：</span><div class="ml50 overflowhid"><input type="text" class="col-md-12 txtTitle" value="" /></div></div><div class="clearfix"><span class="fl lh28 width50">地址：</span><div class="ml50 overflowhid"><textarea rows="4" class="col-md-12 h137 txtContent" value=""></textarea></div></div></div><div class="prodiagramOperaRight"><input type="button" value="保存" class="dis-block uiBtn-blue uiBtn-small saveNewPhoto" /></div></div></li>');
        initImageUpload();

    });

    //新增产品细节图
    $(document).on("click", "#aAddQDPhoto", function () {
        $("#ulQDPhoto").append('<li class="clearfix" data-type="8"><div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 qualitydetailupload relative"><a href="javascript:" class="uiImgUpload-first"><input type="file" value="" class="file fileone"><em class="bgIcon file-ico"></em></a><img src="" /><!--图片遮罩和放大按钮 开始--><div class="uiImgUpload-mark"><div class="uiImgUpload-mark-bg"></div><div class="uiImgUpload-mark-link"><div class="dis_il_block"><a href="jvascript:" class="upagain_link"><input type="file" class="fileone"></a><a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a><a href="javascript:" class="delect_link"></a></div></div></div><!--图片遮罩和放大按钮 结束--></div><div class="qualitydetailOpera relative pt5"><div class="qualitydetailOperaLeft"><div class="clearfix mb10"><div class="ml50 overflowhid"><input type="text" class="col-md-12 txtTitle" value="" /></div></div><div class="clearfix"><div class="ml50 overflowhid"><textarea rows="4" class="col-md-12 h35 txtContent"></textarea></div></div></div><div class="qualitydetailOperaRight"><input type="button" value="保存" class="dis-block uiBtn-blue uiBtn-small saveNewPhoto" /></div></div></li>');
        initImageUpload();
    });

    $(document).on("click", ".btnAddPhotoToAgreemnet", function () {
        var liObj = $(this).closest("div.analyItem").find("li:eq(0)");
        var paraID = $(liObj).data("paraid");
        var matID = $(liObj).data("matid");
        var matLevel = $(liObj).data("matlevel");

        $(liObj).closest("ul").append('<li data-type="11" data-paraid="' + paraID + '" data-matlevel="' + matLevel + '" data-matid="' + matID + '"><div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth130 relative"><a href="javascript:" class="uiImgUpload-first"><input type="file" value="" class="file fileone"><em class="bgIcon file-ico"></em></a><img src="" /><!--图片遮罩和放大按钮 开始--><div class="uiImgUpload-mark"><div class="uiImgUpload-mark-bg"></div><div class="uiImgUpload-mark-link"><div class="dis_il_block"><a href="jvascript:" class="upagain_link"><input type="file" class="fileone"></a><a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a></div></div></div><!--图片遮罩和放大按钮 结束--></div><div class="prodiagramOpera_new relative pt5"><div class="prodiagramOperaLeft_new"><div class="clearfix mb10"><span class="fl lh28 width50">参数</span><div class="ml50 lh28 overflowhid info-look">--</div><div class="ml50 overflowhid info-edit hide"><input type="text" class="col-md-12 txtTitle" value="" /></div></div><div class="clearfix"><span class="fl lh28 width50">描述</span><div class="ml50 lh28 overflowhid info-look">--</div><div class="ml50 overflowhid info-edit hide"><textarea name="" rows="4" class="col-md-12 h52 txtContent"></textarea></div></div></div></div></li>');
        initImageUpload();
    });

    $(document).on("keyup", "li .txtTitle[zz-old],li .txtContent[zz-old]", function () {
        if ($(this).val() != $(this).attr("zz-old")) {
            var liObj = $(this).closest("li");
            $(liObj).find(".saveNewPhoto").show();
        }
    });
    $(document).on("keyup", "li .txtTitle[zz-old],li .txtContent[zz-old]", function () {
        if ($(this).val() != $(this).attr("zz-old")) {
            var liObj = $(this).closest("li");
            $(liObj).find(".saveNewPhoto").show();
        }
    });



    //点击待办
    $(document).on("click", ".needdealt li", function () {
        var taskID = $(this).data("taskid");
        var peID = $(this).data("peid");
        $('.visitbox').hide()
        if (taskID) {
            //任务详情
            var parameter = new Object();
            parameter.taskID = $(this).data("taskid");
            $.post("/Public/BusinessTaskInfo", parameter, function (data) {
                $(".taskdiv").html("");
                $(".taskdiv").html(data);

                $(".tc-taskDiv").show();
                $(".tc-taskDiv").height($(".tc-right-top").height());
                $(".tc-taskDiv").width($(".tc-right-top").width() - 102);
                $(".tc-taskDiv").animate({
                    left: '75px',
                    opacity: 1
                });
                countRight();
            });
        }
        else if (peID) {
            var obj = new Object();
            obj.peID = $(this).data("peid");
            $.ajax({
                url: "/Public/MaterialManageDetailToProcessExecute",
                type: 'POST',
                data: obj,
                dataType: 'html',
                success: function (result) {
                    $("#divAlertMaterialLoad").html(result);

                    $(".alertpaper").hide();
                    $(".divMaterialLoadAlert").show().animate({
                        left: 0
                    }, 200);
                    countLeft();
                    countMiddle();
                    countRight();
                },
                error: function (e) {
                    console.error(e);
                }
            });
        }
        else {
            //待办任务
            $(".tc-taskDiv-history").show();
            $(".tc-taskDiv-history").height($(".tc-right-top").height());
            $(".tc-taskDiv-history").width($(".tc-right-top").width() - 102);
            $(".tc-taskDiv-history").animate({
                left: '75px',
                opacity: 1
            });
            countRight();
            //历史记录弹窗h100%
            $('.tc-right-top-all').height($('.tc-right-all').height())
        }

    });
    //关闭待办
    $(document).on("click", ".tc-taskDiv .rig_close", function () {
        $(this).parents(".tc-taskDiv").animate({
            left: '-100%',
            opacity: 0
        }, function () {
            $(this).hide();
        });
    });
    //关闭待办
    $(document).on("click", ".tc-taskDiv-history .rig_close", function () {
        countRight();
        $(this).parents(".tc-taskDiv-history").animate({
            left: '-100%',
            opacity: 0
        }, function () {
            $(this).hide();
            });
        $('.visitbox').show()
    });


    //打回弹出层（新增品牌）
    $(document).on("click", ".btnAlterBrandCheckBack,.btnAlterBrandCheckLastToBack", function () {
        if ($(this).hasClass("btnAlterBrandCheckLastToBack")) {
            $("#btnBrandCheckLastToBack").show();
            $("#btnBrandCheckBack").hide();
        }
        else {
            $("#btnBrandCheckLastToBack").hide();
            $("#btnBrandCheckBack").show();
        }
        rxued.alert.jAlert(".bohui-alert", 500);
    })

    //审核调价
    $(document).on("click", ".Rev_price_btn", function () {
        LoadMaterialDetailToPriceChangeCheck();

    })
    $(document).on("click", ".Rev_price_alert .close", function () {
        $(".Rev_price_alert").hide().animate({ left: "-100%" }, 200);
    })

    /*
    //报价是成本价1.5
    $(document).on("keyup", "#txtCostPrice,#txtInstallPrice", function () {
        SumQuotesPrice();
    });
    $(document).on("blur", "#txtCostPrice,#txtInstallPrice", function () {
        SumQuotesPrice();
    });

    //报价是成本价1.5
    $("#txtNewCostPrice,#txtNewInstallPrice").keyup(function () {
        SumQuotesPriceToNew();
    });
    $("#txtNewCostPrice,#txtNewInstallPrice").blur(function () {
        SumQuotesPriceToNew();
    });
    */
});


function PhotoRelated() {
    //==========工艺==========
    $(".prodrawing").each(function (i) {
        $(this).click(function () {
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
        })
    });
    //==========标准==========
    //包装照片移入
    $(".packagedphoto").each(function () {
        $(this).hover(function () {
            $(this).find(".uiImgUpload-mark").show();
        }, function () {
            $(this).find(".uiImgUpload-mark").hide();
        });
    });
    //包装照片放大
    $(".packagedphoto .enlarge_link").each(function (i) {
        $(this).click(function () {
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
        })
    });
    //图片移入显示放大删除按钮
    $(".materialquali li").each(function (index) {
        $(this).hover(function () {
            if ($(this).hasClass("uiImgUpload-finished")) {
                $(this).find(".uiImgUpload-mark").show();
            }
        }, function () {
            $(this).find(".uiImgUpload-mark").hide();
        });

    });
    //==========图片==========
    $(".materialpicture .brandlogopic,.brandlogo .brandlogopic").each(function () {
        $(this).hover(function () {
            $(this).find(".uiImgUpload-mark").show();
        }, function () {
            $(this).find(".uiImgUpload-mark").hide();
        });
    });
    $(".materialpicture .brandlogopic_new").each(function () {
        $(this).hover(function () {
            $(this).find(".uiImgUpload-mark").show();
        }, function () {
            $(this).find(".uiImgUpload-mark").hide();
        });
    });
    $(".materialpicture .enlarge_link").each(function (i) {
        $(this).click(function () {
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
        })
    });

}

//关闭所有弹出层
function CloseALL() {
    $("#hidMatID").val("");
    $(".anItemBor-active").removeClass("anItemBor-active");
    $(".alertpaper").hide();
}



function SumQuotesPrice() {
    var costPrice = 0;
    if ($("#txtCostPrice").val() != "" && isNaN($("#txtCostPrice").val()) == false) {
        costPrice = parseFloat($("#txtCostPrice").val())
    }
    var installPrice = 0;
    if ($("#txtInstallPrice").val() != "" && isNaN($("#txtInstallPrice").val()) == false) {
        installPrice = parseFloat($("#txtInstallPrice").val())
    }
    var quotesPrice = parseFloat(costPrice + installPrice) * 1.5;
    $("#txtQuotesPrice").val(quotesPrice.toFixed(2));
}
function SumQuotesPriceToNew() {
    var costPrice = 0;
    if ($("#txtNewCostPrice").val() != "" && isNaN($("#txtNewCostPrice").val()) == false) {
        costPrice = parseFloat($("#txtNewCostPrice").val())
    }
    var installPrice = 0;
    if ($("#txtNewInstallPrice").val() != "" && isNaN($("#txtNewInstallPrice").val()) == false) {
        installPrice = parseFloat($("#txtNewInstallPrice").val())
    }
    var quotesPrice = parseFloat(costPrice + installPrice) * 1.5;
    $("#txtNewQuotesPrice").val(quotesPrice.toFixed(2));
}