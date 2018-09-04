/*中间 js*/
$(function () {
    //点击 子库地区二段 状态/平台/商城/A档/B档/C档/价格/成本 绑定数据
    $(document).on("click", ".j_uiTab9 li", function () {
        $("#hidMatLevel").val("");
        $("#hidMatID").val("");
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(".alertpaper").hide();
        var level = $(this).data("level");
        var pType = $(this).data("ptype");
        if (level) {
            $("#hidMatLevel").val(level);
            // LoadMaterialModule(level);

            //清空内容
            $("#divAlertMaterialBaseInfo").html("");
            $("#divAlertMaterialPhoto").html("");
            $("#divAlertMaterialBasePublic").html("");
            $("#divAlertMaterialStandard").html("");
            $("#divAlertMaterialCrafts").html("");
        }
        else if (pType) {
            //加载其他二段版块
            // LoadMaterialModuleToSecond(pType);
        }

    });

    //档次选择事件
    $(document).on("click", "#divMaterialDetailToStatus .anItemBor", function () {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(this).addClass("anItemBor-active");
        var level = $(this).data("level");

        //进入基本信息界面
        $(".j_uiTab9 li[data-level=" + level + "]").click();
    });

    //基础-基础信息 点击进入
    $(document).on("click", "#divMaterialDetailToBase .baseinfo", function (event) {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(this).addClass("anItemBor-active");
        LoadMaterialDetailToTreeBaseInfo();
    });

    //基础-标准（质量，小样，包装，对比） 点击进入
    $(document).on("click", "#divMaterialDetailToBase .standardinfo", function (event) {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(this).addClass("anItemBor-active");
        $("#hidQSType").val("1");
        var opType = parseInt($(this).data("type"));
        var operT = $(this).data("opertype");
        switch (opType) {
            case 1:
            case 2:
            case 3:
            case 4:
                LoadMaterialDetailToTreeStandard(opType);//各项标准
                break;
            case 50://搜索词
                LoadMaterialDetailToSearchByPF(operT);
                break;
            case 66://对比标准
                LoadMaterialDetailToBCompared();
                break;
            case 67://材料约定
                LoadMaterialDetailToBAgreement();
                break;
            default:
                LoadMaterialDetailToTreeStandard(999);//测试
                break;
        }
    });

    //平台-（项） 点击进入
    $(document).on("click", "#divMaterialDetailToPlatform .anItemBor", function (event) {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(this).addClass("anItemBor-active");

        var opType = $(this).data("ptype");
        switch (opType) {
            case "settop"://置顶-排序
                LoadMaterialDetailToTreeTopSet();
                break;
            case "photo"://照片类
                LoadMaterialDetailToPhotoByPF();
                break;
            case "standard"://质量标准
                $("#hidQSType").val(2);
                LoadMaterialDetailToTreeStandard(1);
                break;
            case "compared"://对比图
                LoadMaterialDetailToComparedPhotoByPF();
                break;
            case "agreement"://约定图
                LoadMaterialDetailToAgreementPhotoByPF();
                break;
            case "search"://搜索词
                var operT = $(this).data("opertype");
                LoadMaterialDetailToSearchByPF(operT);
                break;
            default:
        }
    });

    //成本-（项） 点击进入
    $(document).on("click", "#divMaterialDetailToQuotation .anItemBor", function (event) {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(this).addClass("anItemBor-active");

        var opType = $(this).data("ptype");
        var matLevel = $(this).data("level");
        if (opType) {
            switch (opType) {
                case "base"://成本-基础
                    LoadMaterialDetailToQuotesToBase();
                    break;
                default:
            }
        }
        else if (matLevel) {
            LoadMaterialDetailToQuotesToMaterial(matLevel);

        }
    });

    //应用-（项） 点击进入
    $(document).on("click", "#divMaterialDetailToAPP .anItemBor", function (event) {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(this).addClass("anItemBor-active");

        var opType = $(this).data("ptype");
        switch (opType) {
            case "base"://应用-基础
                LoadMaterialDetailToAPPToBase();
                break;
            default:
        }
    });

    //关闭右侧弹出层
    $(document).on("click", ".divBaseInfoAlert .close", function () {

        $(".divBaseInfoAlert").animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
    });


    //档次-点击进入
    $(document).on("click", "#divMaterialDetailToA .anItemBor,#divMaterialDetailToB .anItemBor,#divMaterialDetailToC .anItemBor", function (event) {
        // $("#hidMatID").val("");
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(this).addClass("anItemBor-active");

        //显示信息
        stopEventBubble(event);
        var pType = $(this).data("ptype");
        switch (pType) {
            case "brand":
                {
                    var matID = $(this).data("matid");
                    var first = $(this).data("first");
                    $("#hidLevelBrandsMatID").val(matID);
                    $("#hidLevelBrandsFirstFlag").val(first);
                    LoadMaterialDetailToZBrand(matID, first);//加载材料详情 New
                }
                break;
            case "photo":
                {
                    LoadMaterialDetailToPhoto();//加载材料照片
                }
                break;
            case "base":
                {
                    LoadMaterialDetailToBasePublic();//加载基本信息 公用
                }
                break;
            case "standard":
                {
                    $("#hidQSType").val(3);
                    var opType = parseInt($(this).data("type"));
                    LoadMaterialDetailToTreeStandard(opType, $("#hidMatLevel").val());//各项标准（某档次进入）
                }
                break;
            case "para":
                {
                    LoadMaterialDetailToMatPara();//材料参数
                }
                break;
            case "agreement":
                {
                    var matID = $(this).data("matid");
                    LoadMaterialDetailToDAgreement(matID);//加载材料约定
                }
                break;
            default:
        }
    });

    //添加新品牌
    $(document).on("click", ".addinfo", function (event) {
        stopEventBubble(event);
        $(".alertpaper").hide();
        $.ajax({
            url: basePath + '/public-web/sublibrary/city_three_level/add',
            type: 'POST',
            data: { finishProductType: $("#hidFinishProductType").val(), matID:"00000000-0000-0000-0000-000000000000" },
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
                PhotoRelated();//绑定事件
            },
            error: function (e) {
                console.error(e);
            }
        });
    });

    //完善品牌
    $(document).on("click", ".modify", function (event) {
        stopEventBubble(event);
        var matID = $("#hidMatID").val();
        if (matID == "") {
            alert("无效的材料");
            return false;
        }
        $(".alertpaper").hide();
        $.ajax({
            url: "/Public/PluginToMaterialDetailToThirdZBrandAdd",
            type: 'POST',
            data: { finishProductType: $("#hidFinishProductType").val(), matID: matID },
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
                PhotoRelated();//绑定事件
            },
            error: function (e) {
                console.error(e);
            }
        });
    });



    //关闭右侧弹出层
    $(document).on("click", ".divMaterialAlert .close", function () {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(".divMaterialAlert").animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
    });
    //关闭右侧弹出层
    $(document).on("click", ".divMaterialPhotoAlert .close", function () {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(".divMaterialPhotoAlert").animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
    });
    //关闭右侧弹出层
    $(document).on("click", ".divMaterialBasePublicAlert .close", function () {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(".divMaterialBasePublicAlert").animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
    });
    //关闭右侧弹出层
    $(document).on("click", ".divMaterialStandardAlert .close", function () {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(".divMaterialStandardAlert").animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
    });
    //关闭右侧弹出层
    $(document).on("click", ".divMaterialCraftsAlert .close", function () {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(".divMaterialCraftsAlert").animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
    });
    //关闭加载性弹出层
    $(document).on("click", ".divMaterialLoadAlert .close", function (event) {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(".divMaterialLoadAlert").animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
    });

    //成本|交易价 点击进入（本地）
    $(document).on("click", "#divMaterialDetailToLocalPrice .anItemBor", function (event) {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(this).addClass("anItemBor-active");

        var level = $(this).data("level");

        LoadMaterialDetailToLocalPrice(level);
    });

    //成本|交易价 点击进入（全国）
    $(document).on("click", "#divMaterialDetailToPrice .anItemBor", function (event) {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(this).addClass("anItemBor-active");

        var level = $(this).data("level");
        var ptype = $(this).data("ptype");

        LoadMaterialDetailToPrice(level, ptype);
    });
    //关闭右侧弹出层
    $(document).on("click", ".divMaterialPriceAlert .close", function () {

        $(".divMaterialPriceAlert").animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
    });
    //关闭右侧弹出层
    $(document).on("click", ".divMaterialPriceSupAlert .close", function () {
        $(".anItemBor-active").removeClass("anItemBor-active");
        $(".divMaterialPriceSupAlert").animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
    });

});