function countdaily() {
    $(".ViewBoxScroll").each(function () {
        var oViewLen = $(this).parents(".popup").find(".ViewBoxCha").length;
        var oViewHeight = parseInt($(this).attr("data-need-cha"));
        for (var i = 0; i < oViewLen; i++) {
            oViewHeight += $(this).parents(".popup").find(".ViewBoxCha").eq(i).outerHeight(true);
        }
        var oViewBoxScrollH = $(".tc-right").height() - oViewHeight;
        $(this).height(oViewBoxScrollH).slimScroll({
            height: oViewBoxScrollH,
            borderRadius: "2px"
        });
        $(this).parent(".slimScrollDiv").height(oViewBoxScrollH);
    });
    //人事详情
    $(".personnelDetail_con").each(function(){
        var personnelDetail_conH = $(this).closest(".popup").height() -$(this).closest(".popup").find(".info_tit").outerHeight(true) - 12;
        $(this).height(personnelDetail_conH).slimScroll({ height: personnelDetail_conH, borderRadius: "2px" });
        $(this).parent(".slimScrollDiv").height(personnelDetail_conH);
    });



    //店鋪-材料
    var uiTab9Con_mymatScrollH = $("iframe",parent.document).height() - 20;
    $("#shoplayerAPPStoreAddMaterial .uiTab9Con_mymatScroll").height(uiTab9Con_mymatScrollH).slimScroll({ height: uiTab9Con_mymatScrollH, borderRadius: "0px" });
    $("#shoplayerAPPStoreAddMaterial .uiTab9Con_mymatScroll").parent(".slimScrollDiv").height(uiTab9Con_mymatScrollH);

    var menuboxH = $("#shoplayerAPPStoreAddMaterial  .uiTab9Con_mymatScroll").height() - $("#shoplayerAPPStoreAddMaterial  .fixedbottom").outerHeight(true) - $("#shoplayerAPPStoreAddMaterial  .sureBtn").outerHeight(true) - 40;
    $("#shoplayerAPPStoreAddMaterial .menubox").height(menuboxH).slimScroll({ height: menuboxH, borderRadius: "0px" });
    $("#shoplayerAPPStoreAddMaterial .menubox").parent(".slimScrollDiv").height(menuboxH);
    var fixedbottomConH = $("#shoplayerAPPStoreAddMaterial .fixedbottom").height() - $("#shoplayerAPPStoreAddMaterial .fixedbottomtit").outerHeight(true) - 30;
    $("#shoplayerAPPStoreAddMaterial .fixedbottomCon").height(fixedbottomConH).slimScroll({ height: fixedbottomConH, borderRadius: "0px" });
    $("#shoplayerAPPStoreAddMaterial .fixedbottomCon").parent(".slimScrollDiv").height(fixedbottomConH);
    $("#shoplayerAppMoreToStores").each(function(){
        var shoplayerAppMoreToStoresH=$(this).closest("body").height();
        $(this).height(shoplayerAppMoreToStoresH).slimScroll({ height: shoplayerAppMoreToStoresH, borderRadius: "0px" });
        $(this).parent(".slimScrollDiv").height(shoplayerAppMoreToStoresH);
    })



}


