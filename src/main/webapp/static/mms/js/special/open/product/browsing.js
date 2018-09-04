// "================================ 功能函数 ==================================="

// 获取URL携带参数
function getUrlParms(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null) return unescape(r[2]);return null;
}

//左侧主图下的小图滚动
function PictureScroll(){
    var oi=0;
    var olens=$(".spec-items-ul li").length;
    var oul=$(".spec-items-ul");
    var oWidth=oul.find("li").eq(0).outerWidth(true);
    oul.width(oWidth*olens);
    $(document).on("click",".arrow-next",function(){
        $(".arrow-prev").removeClass("disabled");
        if(!oul.is(":animated"))
        {
            if(oi<olens-5)
            {
                oi++;
                oul.animate({ "marginLeft": -oWidth*oi + 'px' }, 500);
                if(oi==olens-5)
                {
                    $(this).addClass("disabled");
                }
            }else{
                return;
            }
        }
    });
    $(document).on("click",".arrow-prev",function(){
        $(".arrow-next").removeClass("disabled");
        if(!oul.is(":animated"))
        {
            if(oi>0)
            {
                oi--;
                oul.animate({ "marginLeft": -oWidth*oi + 'px' }, 500);
                if(oi==0)
                {
                    $(this).addClass("disabled");
                }
            }else{
                return;
            }
        }
    });
}

//套餐属性选择 @author ljc @createTime 2018-8-27 19:28:37
$(document).on("click",".brand_lab_ul li:not(.DisabledItem)",function(){
    var _this = this;
    $(this).addClass("current").siblings().removeClass("current");

    // 类比数量
    var categorysSize = $("#hidZYCategorysSize").val();
    // 当前点击类别项所属位置(系列级别)indezx,
    var rank = parseInt($(this).attr("data-rank"));
    // 当前点击类别项ID
    var mctiId = $(this).attr("data-mctiId");

    // 检索参数:
    // 检索类别:1检索不可被选中项组;2检索组合套餐价格
    var searchType = 0;
    // 专项产品ID
    var proID = getUrlParms("proid");
    // 类别项组
    var categoryItems = [];

    // 禁用下级项
    var searchDisabledItemIDsFlag = false;
    var searchDisabledItemIDsObj = {};
    // 当前选中为第一级 执行
    if( rank == 0 )
    {   debugger
        searchDisabledItemIDsFlag = true;
        searchType = 1;
        categoryItems[rank] = mctiId;

        searchDisabledItemIDsObj.searchType = searchType;
        searchDisabledItemIDsObj.proID = proID;
        searchDisabledItemIDsObj.categoryItems = categoryItems;
    }
    // 当前选中不为最后一项 且 不为第一项 执行
    if (rank != (categorysSize -1) && rank != 0 )
    {   debugger
        // 当前选中所有上级都是被选中 执行
        var prevAllLength =  categorysSize - (rank + 2 ); // 排除当前选中项之后,上级选中项应该的数量
        var prevAll = $(this).prevAll(".brand_lab_ul li .current"); // 当前所有上级
        var higherLevelEl = $(prevAll).length; // 当前所有上级被选中的数量
        if (prevAllLength == higherLevelEl)
        {
            searchDisabledItemIDsFlag = true;
            searchType = 1;
            categoryItems[rank] = mctiId;
            $(prevAll).each(function (index,data) {categoryItems[index] = $(this).attr("data-mctiId");});

            searchDisabledItemIDsObj.searchType = searchType;
            searchDisabledItemIDsObj.categoryItems = categoryItems;
        }

    }
    // 获取禁用项校验通过,开始检索
    if (searchDisabledItemIDsFlag)
    {
        $.ajax({
            url: basePath + '/special-api/search/category',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(searchDisabledItemIDsObj),
            success: function (res) {
                if (res.statusMsg != "success") {alert(res.statusMsg);return;}
                // 禁用后回调

                // 设置当前下级禁用
                var disabledItemIDs = res.body;
                if (disabledItemIDs != null)
                {
                    // 清空下级选中状态以及禁用状态
                    var nextELRank = parseInt(rank) + 1 ;
                    $("li[data-rank="+ nextELRank +"]").each(function () {$(this).removeClass("current");});
                    $("li[data-rank="+ nextELRank +"]").each(function () {$(this).removeClass("DisabledItem");});

                    for (var i = 0; i < categorysSize; i ++)
                    {
                        $("li[data-mctiid="+ disabledItemIDs[i] +"]").addClass("DisabledItem").removeClass("current");
                    }
                } else {
                    // 没有恢复当前下级禁用状态
                    $(".DisabledItem").removeClass("DisabledItem");
                }

            },
            error: function (err) {
                alert("系统异常 请联系管理员！");
            }
        });
    }
    // 获取组合套餐价格:1.当前类别为最后一项时;2.所有类别都有选中项时.
    if ( categorysSize ==  $(".current").length)
    {
        searchType = 2;
        $(".current").each(function (index,data) {categoryItems[index] = $(this).attr("data-mctiId");});

        var obj = {};
        obj.searchType = searchType;
        obj.proID = proID;
        obj.categoryItems = categoryItems;

        $.ajax({
            url: basePath + '/special-api/search/category',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (res) {
                if (res.statusMsg != "success") {alert(res.statusMsg);return;}
                if (res.body != null && res.body != 0) $("#spanNowSetMealPrice").html(res.body);
                else $("#spanNowSetMealPrice").html($("#hidZYPriceMaxMin").val());
            },
            error: function (err) {
                alert("系统异常 请联系管理员！");
            }
        });
    } else $("#spanNowSetMealPrice").html($("#hidZYPriceMaxMin").val());
});

$(function(){
    PictureScroll();
});

//小图选中当前项
$(document).on("click",".spec-items-ul li",function(){
    $(this).addClass("img-hover").siblings().removeClass("img-hover");
    var osrc=$(this).find("img").attr("src");
    $(".mall_slide_box_con img").attr("src",osrc);
});



// "============================== 页面点击事件 ==================================="

// 选择套餐 点击事件
$(document).on("click",".brand_lab .SetMealItem",function(){
    var othisAttr=$(this).attr("data-setmealr");
    var osrc=$(this).find("img").attr("src");
    $(this).addClass("selected").siblings().removeClass("selected");
    $(".spec-items-ul li[data-setmeall='"+othisAttr+"']").addClass("img-hover").siblings().removeClass("img-hover");
    $(".PictureBoxParent .show_img").attr("src",osrc);

    // 设置 当前选中套餐名称
    $("#spanNowSetMealName").html($(this).data("smname"));
    // 设置 当前讯中套餐价格
    $("#spanNowSetMealPrice").html($(this).data("smprice"));

});

//左侧主图下的小图切换
$(document).on("click",".spec-items-ul li",function(){
    var othisAttr=$(this).attr("data-setmeall");
    var osrc=$(this).find("img").attr("src");
    $(this).addClass("img-hover").siblings().removeClass("img-hover");
    //                $(".brand_lab .SetMealItem[data-setmealr='"+othisAttr+"']").addClass("selected").siblings().removeClass("selected");
    $(".PictureBoxParent .show_img").attr("src",osrc);
});
//左侧主图下的小图滚动
function PictureScroll(){
    var oi=0;
    var olens=$(".spec-items-ul li").length;
    var oul=$(".spec-items-ul");
    var oWidth=oul.find("li").eq(0).outerWidth(true);
    oul.width(oWidth*olens);
    $(document).on("click",".arrow-next",function(){
        if(!oul.is(":animated"))
        {
            if(oi<olens-5)
            {
                oi++;
                oul.animate({ "marginLeft": -oWidth*oi + 'px' }, 500);
            }else{
                return;
            }
        }
    });
    $(document).on("click",".arrow-prev",function(){
        if(!oul.is(":animated"))
        {
            if(oi>0)
            {
                oi--;
                oul.animate({ "marginLeft": -oWidth*oi + 'px' }, 500);
            }else{
                return;
            }
        }
    });
}




