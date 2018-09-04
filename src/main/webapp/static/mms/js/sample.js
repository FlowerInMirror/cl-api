// "======================== 公用 ==============================="
function countH(){
    var oheight=0;
    for(var i=0;i<$(".positionCha").length;i++)
    {
        oheight+=$(".positionCha").eq(i).outerHeight(true);
    }
    $(".layerRtb-scroll").each(function(){
        var oendheight=$(this).closest(".layerRtb").height()-oheight;
        $(this).height(oendheight).slimScroll({height:oendheight, borderRadius: "0px"});
        $(this).parent(".slimScrollDiv").height(oendheight);
    })
}

// "======================== 一段 ==============================="

//选项卡
$(document).on("click",".uiTab1 .uiTab1-li",function(){
    var oindex=$(this).attr("data-index");
    $(this).addClass("uiTab1-active").siblings().removeClass("uiTab1-active");
    $(".daily_title").hide().eq(oindex).show();
    $(".daily_title").eq(oindex).find("li:eq(0)").click();
    $(".contentbox").hide().eq(oindex).show();
    $(".tc-main").hide().eq(oindex).show();
    countLeft();
});

//地区=== 一段列表===左侧列表行点击
$(document).on("click","#tabCityData tr",function(){
    $(this).addClass("tractive").siblings().removeClass("tractive");
    $(".uiTab1 li:eq(1)").find("a").text($("#tabCityData .tractive").attr("data-cityname"));
    $("#hidCityID").val($(this).attr("data-cityid"));// 设置 城市ID到作用域中
    $(".uiTab1-li[data-index='1']").click();
});
// 样==== 一段列表===左侧列表行点击
$(document).on("click","#tabSampleTreeList tr",function(){
    $(this).addClass("tractive").siblings().removeClass("tractive");
    $("#tc-main-city").find(".uiTab9 li:eq(0)").click(); // 默认点击状态
});
//打签
$(document).on("click",".uiTab1 li[data-index='2']",function(){
    layer.open({
        type: 2,
        title: "标签打印",
        area: ['100%', '100%'],
        btn: ["关闭"],
        content: basePath+"/public-web/sample/open-print",
        success: function (layero,index) {
           // layero.find('.layui-layer-btn').css('text-align', 'center');
        },
    });
});

// "======================== 二段 ==============================="
// 二段导航===主选项卡
$(document).on("click","#samplemain .j_uiTab9 li",function(){
    var _othisIndex=$(this).index();
    $(this).addClass("uiTab9-active").siblings().removeClass("uiTab9-active");
    $('#SampleTcCenter .uiTab9Con').hide().eq(_othisIndex).show();
    switch (_othisIndex)
    {
        case 0: // 状态
            $("#SampleStatus").empty();
            $("#SampleStatus").load(basePath+"/public-web/sample/two-status",function(){
                countMiddle();
                countRight();
            });
            break;
        case 1://标准
            $("#SampleStandard").empty();
            $("#SampleStandard").load(basePath+"/public-web/sample/two-standard",function(){
                countMiddle();
                countRight();
            });
            break;
        case 2://材料
            $("#SampleMaterial").empty();
            $("#SampleMaterial").load(basePath+"/public-web/sample/two-material",function(){
                countMiddle();
                countRight();
            });
            break;
        case 3://位置
            $("#SamplePosition").empty();
            $("#SamplePosition").load(basePath+"/public-web/sample/two-position",function(){
                countMiddle();
                countRight();
            });
            break;
    }
    $(".layerRtb").animate({
        left: '-100%',
    },function(){
        $(this).hide();
    });
});



// "======================== 三段 ==============================="
//位置弹出层
$(document).on("click",".positionClick",function(){
    $(".anItemBor-active").removeClass("anItemBor-active");
    $(this).addClass("anItemBor-active");
    $(".positionAlert").show().load(basePath+"/public-web/sample/three-position",function(){
        countH();
    });
    $(".positionAlert").stop(true,true).animate({"left":"0"},200,function(){
        countH();
    });
});
$(document).on("click",".rig_close",function(){
    $(".anItemBor-active").removeClass("anItemBor-active");
    $(".positionAlert").animate({left:"-100%"},function(){
        $(this).hide();
    });
});














