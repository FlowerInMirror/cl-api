

//加载材料详情页（第二段 主）
// TODO LoadMaterialDetail AngularJS 实现 功能暂时保留
function LoadMaterialDetail(treeID, cityID, level) {
    var obj = new Object();
    obj.treeID = treeID;
    obj.cityID = cityID;
    obj.pageType = $("#hidPageType").val();
    if ($("#hidJSID").length >= 1) {
        obj.jsID = $("#hidJSID").val();
    }
    // $.ajax({
    //     url: "/Public/PluginToMaterialDetail",
    //     type: 'POST',
    //     data: obj,
    //     dataType: 'html',
    //     success: function (result) {
    //         $("#divMaterialDetail").html(result);
    //
            loadVisit();//加载回访
    //
    //         countLeft();
    //         countMiddle();
    //         countRight();
    //         visitedScroll();
    //
    //         rxued.areaSwitch.Tab($('#divMaterialDetail .j_uiTab9'), 'uiTab9-active', $('#divMaterialDetail .uiTab9Con'), 'click', function () {
    //             countLeft();
    //             countMiddle();
    //             countRight();
    //         });
    //     },
    //     error: function (e) {
    //         console.error(e);
    //     }
    // });
}

//加载材料的版块（A档|B档|C档）（第二段-版块）
function LoadMaterialModule(level) {
    var divID = "";
    switch (level) {
        case 1://加载  A
            divID = "divMaterialDetailToA";
            break;
        case 2://加载  B
            divID = "divMaterialDetailToB";
            break;
        case 4://加载  C
            divID = "divMaterialDetailToC";
            break;
        default:
            break;
    }

    if (divID != "") {
        var lookType = $("#hidLookType").val();
        var url = "/Public/PluginToMaterialDetailToLevel";
        if (lookType == "1") {
            url = "/Public/PluginMaterialToVerificationToLevel";
        }
        if ($("#" + divID).html() == "") {
            var obj = new Object();
            obj.treeID = $("#hidTreeID").val();
            obj.cityID = $("#hidCityID").val();
            obj.level = level;
            obj.pageType = $("#hidPageType").val();
            obj.ica = $("#hidIcaType").val();
            if ($("#hidJSID").length >= 1) {
                obj.jsID = $("#hidJSID").val();
            }


            $.ajax({
                url: url,
                type: 'POST',
                data: obj,
                dataType: 'html',
                success: function (result) {
                    $("#" + divID).html(result);
                    $("#hidBrandID").val($(".j_uiTab9 li[data-level=" + level + "]").data("brandid"));
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
            $("#hidBrandID").val($(".j_uiTab9 li[data-level=" + level + "]").data("brandid"));
        }
    }

}


//加载版块（二段）
function  LoadMaterialModuleToSecond(pType) {
    var divID = "";
    var url = "";
    switch (pType) {
        case "quotes"://加载  
            divID = "divMaterialDetailToQuotation";
            url = "/Public/PluginToMaterialDetailToSecondQuotes";
            break;
        case "app"://加载  
            divID = "divMaterialDetailToAPP";
            url = "/Public/PluginToMaterialDetailToSecondAPP";
            break;
        default:
            break;
    }

    if (divID != "") {
        if ($("#" + divID).html() == "") {
            var obj = new Object();
            obj.treeID = $("#hidTreeID").val();
            obj.cityID = $("#hidCityID").val();
            obj.pageType = $("#hidPageType").val();

            $.ajax({
                url: url,
                type: 'POST',
                data: obj,
                dataType: 'html',
                success: function (result) {
                    $("#" + divID).html(result);
                    countLeft();
                    countMiddle();
                    countRight();
                },
                error: function (e) {
                    console.error(e);
                }
            });
        }
    }

}


//加载材料子库详情（第三段）
function LoadMaterialDetailToBase(matID) {
    $("#hidMatID").val(matID);
    $.ajax({
        url: "/Public/PluginToMaterialDetailToBMDetail",
        type: 'POST',
        data: { matID: matID, pageType: $("#hidPageType").val() },
        dataType: 'html',
        success: function (result) {
            $("#divAlertMaterialBaseInfo").html(result);
            $(".alertpaper").hide();
            $(".divMaterialAlert").show().animate({
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

}

//加载材料照片（第三段）
function LoadMaterialDetailToPhoto() {
    if ($("#divAlertMaterialPhoto").html() == "") {
        $.ajax({
            url: "/Public/PluginToMaterialDetailToPMDetail",
            type: 'POST',
            data: { treeID: $("#hidTreeID").val(), cityID: $("#hidCityID").val(), level: $("#hidMatLevel").val(), pageType: $("#hidPageType").val() },
            dataType: 'html',
            success: function (result) {
                $("#divAlertMaterialPhoto").html(result);
                $(".alertpaper").hide();
                $(".divMaterialPhotoAlert").show().animate({
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
    }
    else {
        $(".alertpaper").hide();
        $(".divMaterialPhotoAlert").show().animate({
            left: 0
        }, 200);
        countLeft();
        countMiddle();
        countRight();
    }
}

//加载材料基本信息 公用（第三段）
function LoadMaterialDetailToBasePublic() {
    if ($("#divAlertMaterialBasePublic").html() == "") {
        $.ajax({
            url: "/Public/PluginToMaterialDetailToBaseInfo",
            type: 'POST',
            data: { treeID: $("#hidTreeID").val(), cityID: $("#hidCityID").val(), level: $("#hidMatLevel").val(), pageType: $("#hidPageType").val() },
            dataType: 'html',
            success: function (result) {
                $("#divAlertMaterialBasePublic").html(result);
                $(".alertpaper").hide();
                $(".divMaterialBasePublicAlert").show().animate({
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
        $(".alertpaper").hide();
        $(".divMaterialBasePublicAlert").show().animate({
            left: 0
        }, 200);
        countLeft();
        countMiddle();
        countRight();
    }
}

//加载材料标准详情（第三段）
function LoadMaterialDetailToStandard() {
    if ($("#divAlertMaterialStandard").html() == "") {
        $.ajax({
            url: "/Public/PluginToMaterialDetailToSMDetail",
            type: 'POST',
            data: { treeID: $("#hidTreeID").val(), level: $("#hidMatLevel").val(), pageType: $("#hidPageType").val() },
            dataType: 'html',
            success: function (result) {
                $("#divAlertMaterialStandard").html(result);
                $(".alertpaper").hide();
                $(".divMaterialStandardAlert").show().animate({
                    left: 0
                }, 200);
                countLeft();
                countMiddle();
                countRight();
                PhotoRelated();//绑定事件

                $(".rxstander:eq(0)").click();
                $(".inspectstander:eq(0)").click();

                //绑定标准
                $("#lblOffExteriorName").html($("#lblMainOffExteriorName").html());
                $("#lblOffExteriorSC").html($("#lblMainOffExteriorSC").html());
                $("#lblOffExteriorDM").html($("#lblMainOffExteriorDM").html());
            },
            error: function (e) {
                console.error(e);
            }
        });
    }
    else {
        $(".alertpaper").hide();
        $(".divMaterialStandardAlert").show().animate({
            left: 0
        }, 200);
        countLeft();
        countMiddle();
        countRight();
    }
}

//加载材料价格（成本|交易价）（第三段）（全国）
function LoadMaterialDetailToPrice(level, type) {
    $.ajax({
        url: "/Public/PluginToMaterialDetailToPrice",
        type: 'POST',
        data: { treeID: $("#hidTreeID").val(), level: level, type: type },
        dataType: 'html',
        success: function (result) {
            ("#divAlertMaterialPriceSup").html(result);
            $(".alertpaper").hide();
            $(".divMaterialPriceSupAlert").show().animate({
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
}

//加载材料价格（成本|交易价）（第三段）（本地）
function LoadMaterialDetailToLocalPrice(level) {
    var obj = new Object();
    obj.treeID = $("#hidTreeID").val();
    obj.cityID = $("#hidCityID").val();
    obj.level = level;
    obj.pageType = $("#hidPageType").val();

    $.ajax({
        url: "/Public/PluginToMaterialDetailToLocalPrice",
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



//加载材料子库价格审核详情
function LoadMaterialDetailToPriceChangeCheck() {
    var obj = new Object();
    obj.matID = $("#hidMatID").val();
    $.ajax({
        url: "/Public/PluginToMaterialDetailToCheckPriceChange",
        type: 'POST',
        data: obj,
        dataType: 'html',
        success: function (result) {
            $("#divAlertMaterialCheckChangePrice").html(result);

            rxued.alert.jAlert(".adjustmentAlert", 500);
            countLeft();
            countMiddle();
            countRight();
        },
        error: function (e) {
            console.error(e);
        }
    });

}

//加载基础-基本信息（第三段）
function LoadMaterialDetailToTreeBaseInfo() {
    $.ajax({
        url: basePath + "/public-web/sublibrary/city_three_platform/base",
        type: 'POST',
        data: { treeID: $("#hidTreeID").val(), cityID: $("#hidCityID").val(), pageType: $("#hidPageType").val() },
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

//加载基础-标准信息（第三段）
function LoadMaterialDetailToTreeStandard(opType, matLevel) {
    // debugger
    var url = "/Public/PluginToMaterialDetailToThirdOQA";
    switch (opType) {
        case 1:
            url = basePath + "/public-web/sublibrary/city_three_platform/sta_quality";//质量
            break;
        case 2:
            url = "/Public/PluginToMaterialDetailToThirdOQA";//官方
            break;
        case 3:
            url = basePath + "/public-web/sublibrary/city_three_platform/sta_hue";//小样
            break;
        case 4:
            url = basePath + "/public-web/sublibrary/city_three_platform/sta_package";//包装
            break;
        case 33:
            url = basePath + "/public-web/sublibrary/city_three_level/standard";// 子库地区档次三段 - 标准
            break;
        default:
            url = "/Public/PluginToMaterialDetailToThirdTest";//测试
            break;
    }
    $.ajax({
        url: url,
        type: 'POST',
        data: {
            treeID: $("#hidTreeID").val(), cityID: $("#hidCityID").val(), qsType: $("#hidQSType").val(), pageType: $("#hidPageType").val(), level: $("#hidMatLevel").val()
        },
        dataType: 'html',
        success: function (result) {
            $("#divAlertMaterialLoad").html(result);

            $(".alertpaper").hide();
            $(".divMaterialLoadAlert").show().animate({
                left: 0
            }, 200,function(){
                countH();
                // setTimeout(function(){
                //     switch (opType)
                //     {
                //         case 1:
                //             //判断质量标准
                //             var ototalNumber = $("#divAlertMaterialLoad").find(".analyItem[data-tsiid]").length;
                //             if(ototalNumber==0)
                //             {
                //                 $(".lbl_strandard_qs_detect").addClass("cRed").removeClass("cGreen").text("无");
                //             }else{
                //                 $(".lbl_strandard_qs_detect").addClass("cGreen").removeClass("cRed").text(ototalNumber + "项");
                //             }
                //             break;
                //         case 3:
                //             //判断小样标准
                //             //判断样品属性
                //             var ototalNumber = $("#divAlertMaterialLoad").find(".analyItem[data-tsiid]").length;
                //             if(ototalNumber==0)
                //             {
                //                 $(".lbl_strandard_mus_detect").addClass("cRed").removeClass("cGreen").text("无");
                //             }else{
                //                 $(".lbl_strandard_mus_detect").addClass("cGreen").removeClass("cRed").text(ototalNumber + "项");
                //             }
                //             break;
                //         case 4:
                //             //判断包装标准
                //             //包装照片
                //             if($(".brandmodel .packagedphoto").find("img").attr("src")!="")
                //             {
                //                 $(".lbl_strandard_packs_photo").addClass("cGreen").removeClass("cRed").text("有");
                //             }else{
                //                 $(".lbl_strandard_packs_photo").addClass("cRed").removeClass("cGreen").text("无");
                //             }
                //             //包装属性
                //             var ototalNumber = $("#divAlertMaterialLoad").find(".analyItem[data-tsiid]").length;
                //             if(ototalNumber==0)
                //             {
                //                 $(".lbl_strandard_packs_detect").addClass("cRed").removeClass("cGreen").text("无");
                //             }else{
                //                 $(".lbl_strandard_packs_detect").addClass("cGreen").removeClass("cRed").text(ototalNumber + "项");
                //             }
                //             break;
                //
                //
                //         default:
                //             break;
                //     }
                // },500)

            });
            countLeft();
            countMiddle();
            countRight();
        },
        error: function (e) {
            console.error(e);
        }
    });
}

//加载基础-对比属性
function LoadMaterialDetailToBCompared() {
    $.ajax({
        url: basePath + "/public-web/sublibrary/city_three_platform/sta_contrast",
        type: 'POST',
        data: { treeID: $("#hidTreeID").val(), cityID: $("#hidCityID").val(), pageType: $("#hidPageType").val() },
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

//加载基础-材料约定属性
function LoadMaterialDetailToBAgreement() {
    $.ajax({
        url: "/Public/PluginToMaterialDetailToThirdBAgreement",
        type: 'POST',
        data: { treeID: $("#hidTreeID").val(), cityID: $("#hidCityID").val(), pageType: $("#hidPageType").val() },
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

//加载材料-材料参数
function LoadMaterialDetailToMatPara() {
    $.ajax({
        url: "/Public/PluginToMaterialDetailToThirdPara",
        type: 'POST',
        data: { treeID: $("#hidTreeID").val(), level: $("#hidMatLevel").val(), pageType: $("#hidPageType").val() },
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

//加载平台-置顶（第三段）
function LoadMaterialDetailToTreeTopSet(pageIndex) {
    var obj = new Object();
    obj.treeID = $("#hidTreeID").val();
    obj.cityID = $("#hidCityID").val();
    obj.pageType = $("#hidPageType").val();
    obj.ica = $("#hidIcaType").val();

    $.ajax({
        url: "/Public/PluginToMaterialDetailToThirdTopSet",
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

            if (pageIndex) {
                $("#divTopSetContent2 .ul_tab2 li").eq(pageIndex - 1).click();
            }
        },
        error: function (e) {
            console.error(e);
        }
    });
}

//加载平台-置顶-详情（第三段）
function LoadMaterialDetailToTreeTopSetDetails(pageIndex) {
    var classID = $(".platform_titbox li.platform_tit_active").data("classid");
    if (!classID) {
        classID = "00000000-0000-0000-0000-000000000000";
    }

    $.ajax({
        url: "/Public/PluginToMaterialDetailToThirdTopSetDetails",
        type: 'POST',
        data: { treeID: $("#hidTreeID").val(), cityID: $("#hidCityID").val(), classID: classID },
        dataType: 'html',
        success: function (result) {
            $("#divTopSetContent").html(result);

            if (pageIndex) {
                $("#divTopSetContent2 .ul_tab2 li").eq(pageIndex - 1).click();
            }
        },
        error: function (e) {
            console.error(e);
        }
    });
}

//加载平台-照片类
function LoadMaterialDetailToPhotoByPF() {
    var obj = new Object();
    obj.treeID = $("#hidTreeID").val();
    obj.cityID = $("#hidCityID").val();
    obj.pageType = $("#hidPageType").val();
    obj.ica = $("#hidIcaType").val();

    $.ajax({
        url: "/Public/PluginToMaterialDetailToThirdPlatformPhoto",
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
            PhotoRelated();//绑定事件
        },
        error: function (e) {
            console.error(e);
        }
    });
}

//加载平台-对比照片
function LoadMaterialDetailToComparedPhotoByPF() {
    var obj = new Object();
    obj.treeID = $("#hidTreeID").val();
    obj.cityID = $("#hidCityID").val();
    obj.pageType = $("#hidPageType").val();
    obj.ica = $("#hidIcaType").val();

    $.ajax({
        url: "/Public/PluginToMaterialDetailToThirdCComparedPhoto",
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
            PhotoRelated();//绑定事件
        },
        error: function (e) {
            console.error(e);
        }
    });
}

//加载平台-材料约定照片
function LoadMaterialDetailToAgreementPhotoByPF() {
    $.ajax({
        url: "/Public/PluginToMaterialDetailToThirdCAgreementPhoto",
        type: 'POST',
        data: { treeID: $("#hidTreeID").val(), cityID: $("#hidCityID").val(), pageType: $("#hidPageType").val() },
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
}

//加载平台-搜索词-用途
function LoadMaterialDetailToSearchByPF(operT) {
    var obj = new Object();
    obj.treeID = $("#hidTreeID").val();
    obj.cityID = $("#hidCityID").val();
    obj.pageType = $("#hidPageType").val();
    obj.operType = operT;
    $.ajax({
        url: basePath + "/public-web/sublibrary/city_three_platform/search",
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


//加载档次-材料详情（第三段）
function LoadMaterialDetailToDAgreement(matID) {
    $("#hidMatID").val(matID);
    var obj = new Object();
    obj.pageType = $("#hidPageType").val();
    obj.matID = matID;
    obj.ica = $("#hidIcaType").val();
    $.ajax({
        url: "/Public/PluginToMaterialDetailToThirdDAgreement",
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
            PhotoRelated();//绑定事件
        },
        error: function (e) {
            console.error(e);
        }
    });

}

//加载档次-材料详情（第三段）
function LoadMaterialDetailToZBrand(matID, first) {
    // $("#hidMatID").val(matID);
    var obj = new Object();
    obj.matID = matID;
    obj.pageType = $("#hidPageType").val();
    obj.ica = $("#hidIcaType").val();
    obj.first = first;

    $.ajax({
        url: basePath + '/public-web/sublibrary/city_three_level/brands',
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
            PhotoRelated();//绑定事件
        },
        error: function (e) {
            console.error(e);
        }
    });

}


//加载成本-基础详情（第三段）
function LoadMaterialDetailToQuotesToBase() {
    var obj = new Object();
    obj.treeID = $("#hidTreeID").val();
    obj.cityID = $("#hidCityID").val();
    obj.pageType = $("#hidPageType").val();

    $.ajax({
        url: basePath +"/public-web/sublibrary/city_three_cost/base",
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

//加载成本-材料档次详情（第三段）
function LoadMaterialDetailToQuotesToMaterial(matLevel) {
    var obj = new Object();
    obj.treeFourID = $("#hidTreeID").val();
    obj.cityID = $("#hidCityID").val();
    // obj.matLevel = matLevel;
    $("#hidCostMatLevel").val(matLevel); // 隐藏的成本材料档次 设值
    // obj.pageType = $("#hidPageType").val();
    $.ajax({
        // /Public/PluginToMaterialDetailToThirdQuotesToMaterial
        url: basePath + "/public-web/sublibrary/city_three_cost/levels",
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



//加载应用-基础详情（第三段）
function LoadMaterialDetailToAPPToBase() {
    var obj = new Object();
    obj.treeID = $("#hidTreeID").val();
    obj.cityID = $("#hidCityID").val();
    obj.pageType = $("#hidPageType").val();

    $.ajax({
        url: "/Public/PluginToMaterialDetailToThirdAPPToBase",
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



/*-------------------------------------------  审核（圈中材料）  ------------------------------------------*/
//加载地区材料（圈中的）
function loadCityDetailToVer(icaType) {
    if ($("#tabDataToMaterialVer").length == 0) {
        return false;
    }

    $("#tabDataToMaterialVer").html("");
    var obj = new Object();
    obj.pageType = $("#hidPageType").val();
    obj.cityID = $("#hidCityID").val();
    if (icaType) {
        obj.ica = icaType;
    }
    else {
        obj.ica = 0;
    }

    $.ajax({
        url: "/Public/MaterialToVerificationRows",
        type: 'POST',
        data: obj,
        dataType: 'html',
        success: function (result) {
            $("#tabDataToMaterialVer").html(result);

            LoadTreeListToSearchToVer();
            countLeft();
        },
        error: function (e) {
            console.error(e);
        }
    });
}


//加载材料详情页（第二段 主）（圈中的）
function LoadMaterialDetailVer(treeID, cityID) {
    var obj = new Object();
    obj.treeID = treeID;
    obj.cityID = cityID;
    obj.pageType = $("#hidPageType").val();
    obj.ica = $("#hidIcaType").val();
    $.ajax({
        url: "/Public/PluginMaterialToVerification",
        type: 'POST',
        data: obj,
        dataType: 'html',
        success: function (result) {
            $("#divMaterialDetail").html(result);

            loadVisit();//加载回访

            countLeft();
            countMiddle();
            countRight();
            visitedScroll();

            rxued.areaSwitch.Tab($('#divMaterialDetail .j_uiTab9'), 'uiTab9-active', $('#divMaterialDetail .uiTab9Con'), 'click', function () {
                countLeft();
                countMiddle();
                countRight();
            });
        },
        error: function (e) {
            console.error(e);
        }
    });
}

//管理-----------****************************************
function loadCityDetailToManage() {
    if ($("#tabDataToMaterialManage").length == 0) {
        return false;
    }

    $("#tabDataToMaterialManage").html("");
    var obj = new Object();
    obj.cityID = $("#hidCityID").val();
    obj.type = 1;

    $.ajax({
        url: "/BMaterial/MaterialManageDetailToTreeRows",
        type: 'POST',
        data: obj,
        dataType: 'html',
        success: function (result) {
            $("#tabDataToMaterialManage").html(result);
            LoadTreeListToSearchToManage();
            countLeft();
        },
        error: function (e) {
            console.error(e);
        }
    });
}


//加载材料详情页（第二段 主）
function LoadMaterialDetailToManage(treeID, cityID, level) {
    $.ajax({
        url: "/Public/PluginToMaterialDetailToManage",
        type: 'POST',
        data: { treeID: treeID, cityID: cityID, pageType: $("#hidPageType").val() },
        dataType: 'html',
        success: function (result) {
            $("#divMaterialDetail").html(result);

            loadVisit();//加载回访

            countLeft();
            countMiddle();
            countRight();
            visitedScroll();

            rxued.areaSwitch.Tab($('#divMaterialDetail .j_uiTab9'), 'uiTab9-active', $('#divMaterialDetail .uiTab9Con'), 'click', function () {
                countLeft();
                countMiddle();
                countRight();
            });
        },
        error: function (e) {
            console.error(e);
        }
    });
}



/*-------------------------------------------  方法（处理-审核相关）  ------------------------------------------*/
function ShowBrandLevelICAState(className) {
    var levelState = -1;
    $("." + className + " .lbl_mat_ica").each(function () {
        if ($(this).text().indexOf("待审") >= 0) {
            levelState = 0;
            return false;//跳出循环
        }
        else if ($(this).text().indexOf("不合") >= 0) {
            levelState = 2;
        }
        else {
            levelState = levelState == 2 ? levelState : 3;//不合格与合格同存在， 为不合格
        }
    });

    var newHtml = "";
    switch (levelState) {
        case 2:
            newHtml = "<a href=\"javascript:\" style=\"width: 42px;\" class=\"circlemark cRed\" title=\"不合格\">不合</a>";
            break;
        case 3:
            newHtml = "<a href=\"javascript:\" style=\"width: 42px;\" class=\"circlemark cGreen\">合格</a>";
            break;
        default:
            newHtml = "<a href=\"javascript:\" style=\"width: 42px;\" class=\"circlemark cOrange\">待审</a>";
            break;
    }

    $(".lbl_mat_ica_all[data-level=" + $("#hidMatLevel").val() + "]").html(newHtml);
}

function ShowBrandPFICAState() {
    var icaState = -1;
    $(".lbl_pf_ica[data-type]").each(function () {
        if ($(this).text().indexOf("待审") >= 0) {
            icaState = 0;
            return false;//跳出循环
        }
        else if ($(this).text().indexOf("不合") >= 0) {
            icaState = 2;
        }
        else {
            icaState = icaState == 2 ? icaState : 3;//不合格与合格同存在， 为不合格
        }
    });

    var newHtml = "";
    switch (icaState) {
        case 2:
            newHtml = "<a href=\"javascript:\" style=\"width: 42px;\" class=\"circlemark cRed\" title=\"不合格\">不合</a>";
            break;
        case 3:
            newHtml = "<a href=\"javascript:\" style=\"width: 42px;\" class=\"circlemark cGreen\">合格</a>";
            break;
        default:
            newHtml = "<a href=\"javascript:\" style=\"width: 42px;\" class=\"circlemark cOrange\">待审</a>";
            break;
    }

    $(".lbl_pf_ica_all").html(newHtml);
}

//绑定审核状态
function BindICAStateToLabel(lblObj, icaState) {
    switch (icaState) {
        case 2:
            $(lblObj).removeClass("cRed").removeClass("cGreen").removeClass("cOrange").addClass("cRed").html("不合");
            break;
        case 3:
            $(lblObj).removeClass("cRed").removeClass("cGreen").removeClass("cOrange").addClass("cGreen").html("合格");
            break;
        default:
            $(lblObj).removeClass("cRed").removeClass("cGreen").removeClass("cOrange").addClass("cOrange").html("待审");
            break;
    }

}