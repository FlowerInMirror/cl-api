<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
@using HYBY.Mat.WebExtensions;
@using HYBY.Mat.Entity;
@model List<VTwo_TreeTop>
@{
var xx = 0;
var ii = 0;
var classItems = ViewBag.ClassItems as IEnumerable<V_TypeInfo>;
int icaType = ViewBag.ICAType, icaState = ViewBag.ICAState;

}
<!--置顶设置 开始-->

<div class="pa10">
    <div class="pl5 pr5">
        <h4 class="h4title mb2 materialTopNav_tit">基本信息</h4>
        <div class="pl10 mb5">
            <div class="clearfix mb2 platform_tit">
                <div class="clearfix">
                    <p class="fl minwidth204 lh20"><span class="pr5"><span class="justify_span justify_span_w75">置顶位置</span>：</span>@(ViewBag.TopSet)</p>
                    <p class="fl minwidth204 lh20"><span class="pr5"><span class="justify_span justify_span_w75">是否推荐首页</span>：</span><span class="span-homehsot-v"></span></p>
                </div>
                <div class="tx-center platform_titbox">
                    <ul class="clearfix  mt10">
                        <li class="platform_tit_active">全部</li>

                        @if (classItems != null && classItems.Any())
                        {
                        <c:forEach>
                            <li class="" data-classid="@(item.ti_ID)">@(item.ti_Title)</li>
                        </c:forEach>
                        foreach (var item in classItems)
                        {

                        }
                        }
                    </ul>
                </div>
            </div>
            <div class="clearfix" id="divTopSetContent">
                @foreach (var item in Model)
                {
                <div class="mt10 platform_box @(++xx==1?"":"hide")">
                <ul class="clearfix ul_tab1 mt5" data-pageindex="@(item.PageIndex)">
                    @foreach (var liItem in item.Items)
                    {
                    if (string.IsNullOrEmpty(liItem.MatName))
                    {
                    <li data-pagenum="@(liItem.tci_PageNum)">可用</li>
                    }
                    else if (liItem.tci_TreeID == ViewBag.TreeID)
                    {
                    <li class="bg_blue" data-pagenum="@(liItem.tci_PageNum)" title="@(liItem.MatName)-@(liItem.MatSpec)">@(Html.Raw(TransformHelper.TS_LongStrToSpanByBytes(liItem.MatName, 5)))</li>
                    }
                    else
                    {
                    <li class="bor_blue" data-pagenum="@(liItem.tci_PageNum)" title="@(liItem.MatName)-@(liItem.MatSpec)">@(Html.Raw(TransformHelper.TS_LongStrToSpanByBytes(liItem.MatName, 5)))</li>
                    }
                    }
                </ul>
            </div>
            }
        </div>
        <div class="ul_tab2divbox relative mt10" id="divTopSetContent2">
            <div class="tx-center ul_tab2div">
                <ul class="clearfix ul_tab2 mt10">
                    @foreach (var item in Model)
                    {
                    var liClass = "";
                    ii++;
                    if (ii == 1)
                    {
                    liClass = "bg_blue";
                    }
                    switch (item.TopType)
                    {
                    case 0:
                    case 1:
                    <li class="@(liClass)">@(item.PageIndex.ToString("00"))</li>
                    if (ii != 5)
                    {
                    <span class="fl">......</span>
                    }
                    break;
                    case 2:
                    default:
                    <li class="@(liClass)">@(item.PageIndex.ToString("00"))</li>
                    if (ii != 5)
                    {
                    <span class="fl">......</span>
                    }
                    break;
                    }
                    }
                </ul>
                @if (icaType > 0)
                {
                if (icaType == 1)
                {
                <div class="materialTopNavBot tx-center mt5 clearfix ">
                    @switch (icaState)
                    {
                    case 2:
                    <b class="col-md-4 cRed fz14">不合格</b>
                    break;
                    case 3:
                    <b class="col-md-4 cGreen fz14">合格</b>
                    break;
                    default:
                    <input type="button" value="不合格" class="uiBtn-small uiBtn-red btnICSStateSaveToAlert" data-obj="1" data-type="10" data-val="2">
                    <input type="button" value="合格" class="uiBtn-small uiBtn-blue ml10 btnICSStateSave" data-obj="1" data-type="10" data-val="3">
                    break;
                    }
                </div>
                <script>
                    $(function () {
                        var matSpan = $(".lbl_pf_ica[data-type=1_10]").find(".cRed,.cGreen,.cOrange");
                        var icaMat = @(icaState);

                        BindICAStateToLabel(matSpan, icaMat);

                        ShowBrandPFICAState();
                    });
                </script>
                }
                }
            </div>
        </div>

    </div>

</div>
</div>
<!--置顶设置 结束-->
<script>
    $(".span-homehsot-v").html($(".span-homehsot").html());
    $("#divTopSetContent2 .ul_tab2 li").each(function (i) {
        $(this).click(function () {
            $(".ul_tab2 li").removeClass("bg_blue");
            $(this).addClass("bg_blue");
            $(".ul_tab2 span").css("color", "#ccc");
            $(this).next("span").css("color", "#0e92ff");
            $(".platform_box").hide();
            $(".platform_box").eq(i).show();
        })
    })
    $("#divTopSetContent2 .ul_tab2 li").eq(0).click();
    //点击上面导航
    $(".platform_titbox li").each(function (i) {
        $(this).click(function () {
            $(".platform_titbox li").removeClass("platform_tit_active");
            $(this).addClass("platform_tit_active");

            LoadMaterialDetailToTreeTopSetDetails(1);
        })
    })
</script>