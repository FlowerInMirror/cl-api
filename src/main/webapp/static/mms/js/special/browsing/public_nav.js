/*分类导航*/
$(".nav_sub").each(function () {
    var count = 0;
    $(this).mouseover(function () {
        count++;
        $(this).addClass("cur");
        $(this).find(".nav_sub2").show();
        $(this).siblings().removeClass("cur");
        $(this).siblings().find(".nav_sub2").hide();
        if (count == 1) { 
            $(this).find(".nav_sub2").mCustomScrollbar({
                    scrollButtons: {
                        enable: false,
                        scrollType: "continuous",
                        scrollSpeed: 60,
                        scrollAmount: 40
                    }
                    });
            }
    })
});
/*全部商品分类 点击选中*/
$(".navMain .nav_sub2_main").each(function(){
    var _a=$(this).find("a");
    _a.click(function(){
        $(this).addClass("cur").siblings().removeClass("cur");
    })
});
/*关键词点击*/
$(".seachCon").on("click",".aLink",function(){
    $(this).addClass("fOrange").siblings().removeClass("fOrange");
});
/*选择分公司*/
$(".headConL").on("click", "a", function () {
    var _this = $(this);
    var _cityBox = $(this).parents(".headCon").find(".selBox_city");
    if (_cityBox.css("display") == "none") {
        _cityBox.stop().slideDown();
    } else {
        _cityBox.stop().slideUp();
    }
});

$(".selBox_city li").each(function () {
    $(this).click(function () {
        var _newTxt = $(this).text();
        var _DiquID = $(this).find("a").attr("DiquID");
        $("#_DiquID").val(_DiquID);//赋值
        $(this).parents(".headConL").find(".selCity span").text(_newTxt);
    })
});

jQuery(document).ready(function ($) {
    // browser window scroll (in pixels) after which the "back to top" link is shown
    var offset = 300,
		//browser window scroll (in pixels) after which the "back to top" link opacity is reduced
		offset_opacity = 1200,
		//duration of the top scrolling animation (in ms)
		scroll_top_duration = 700,
		//grab the "back to top" link
		$back_to_top = $('.cd-top');

    //hide or show the "back to top" link
    $(window).scroll(function () {
        ($(this).scrollTop() > offset) ? $back_to_top.addClass('cd-is-visible') : $back_to_top.removeClass('cd-is-visible cd-fade-out');
        if ($(this).scrollTop() > offset_opacity) {
            $back_to_top.addClass('cd-fade-out');
        }
    });
    //www.sucaijiayuan.com
    //smooth scroll to top
    $back_to_top.on('click', function (event) {
        event.preventDefault();
        $('body,html').animate({
            scrollTop: 0,
        }, scroll_top_duration
		);
    });

});


$(function () {
    $(".hotQuestion_div_con_cbox[data-type]").each(function () {
        var qType = $(this).data("type");
        for (var ii = 0; ii < dataQuestion.length; ii++) {
            if (dataQuestion[ii].qaType == qType) {
                $(this).append('<div class="hotQuestion_div_list"><a href="/Shopping/CommonProblem?qaid=' + dataQuestion[ii].qaID + '">· ' + dataQuestion[ii].qaTitleAsk + '</a></div>');
            }
        }
    });

    /**热点问题**/
    $(".hotQuestion_div li").click(function () {
        var divObj = $(this).closest(".hotQuestion_div");
        var type = $(divObj).attr("zz-type");
        if (type && type == "1") {
            $(divObj).attr("zz-type", "0");
            $(".hotQuestion_div_con").hide();
        }
        else {
            $(divObj).attr("zz-type", "1");
            $(this).addClass("hotQuestion_cur").siblings().removeClass("hotQuestion_cur");
            $(".hotQuestion_div_con").show();
        }
    });
    /**点击列表显示  隐藏问题详情**/
    $(".hotQuestion_div_list a").click(function () {
        var qaID = $(this).data("qaid");
        for (var xx = 0; xx < dataQuestion.length; xx++) {
            if (dataQuestion[xx].qaID == qaID) {
                $("#hotQuestionContent").html(dataQuestion[xx].qaContent);
            }
        }
        $(".hotQuestionHide").show();
        $("#Commodity").hide();//关联产品 隐藏
        showH();
    })
    /**收起    隐藏问题详情**/
    $(".hotQuestionHide_Stop").click(function () {
        $(".hotQuestionHide").hide();
        showH();
        setTimeout('$("#Commodity").css("display", "")', 200);//关联产品 显示
    });
});

//获取问题类型
function GetQuestionType(typeID) {
    var typeName = "";
    for (var ii = 0; ii < dataQuestionType.length; ii++) {
        if (dataQuestionType[ii].TypeID == typeID) {
            typeName = dataQuestionType[ii].TypeTitle;
            break;
        }
    }
    return typeName;
}

var dataQuestion = [
    { qaID: 1, qaTitle: "瑞祥如何保证到场材料为正品", qaTitleAsk: "瑞祥如何保证到场材料为正品?", qaType: 1, qaContent: '<h2 class="hotQuestionHide_titH2">★ 瑞祥如何保证到场材料为正品？</h2><p>瑞祥装饰对材料的使用完整的质量管理体系，确保使用材料能够满足施工需求，从材料选择，到报价、再到配送最后到使用均有严格要求，具体体现为：</p><p>①　材料选择：瑞祥材料的选择需经过多次论证并根据实际需求确定，在材料选择过程中先根据需求确定材料质量标准，在根据质量标准由瑞祥装饰的材料管理部对材料进行材料质量检测，合格后方可使用，影响到安全的材料坚决杜绝在项目使用。（检测方法及合格范围同步在瑞祥材料平台）中。</p><p>②　供应商资质审核：不是谁都可以给瑞祥供货的，每个材料商供货前均需要瑞祥装饰对材料商进行下列内容进行审核：营业执照、税务登记证、组织机构代码。</p><p>③　二次检测、封样：每一种瑞祥材料在上线使用前均需要在次由瑞祥材料质检部门进行材料质量检测并将样品封存公司材料间，封样后方可上线使用。</p><p>④　第三方检测：瑞祥装饰所使用的A级、Ｂ级材料必须经过第三方正规、严格的检测，并带有第三方出具的在有效期内的检测报告。</p><p>⑤　现场验收:瑞祥装饰使用的材料在配送到现场后，项目经理必须对材料进行验收，并经客户验收合格后方可使用。</p>' }
    , { qaID: 2, qaTitle: "如何检查材料质量", qaTitleAsk: "如何检查材料质量?", qaType: 1, qaContent: '<h2 class="hotQuestionHide_titH2">★ 如何检查材料质量？</h2><p>瑞祥的每种材料均有自己的检测方法及质量标准，可以瑞祥材料中的产品介绍板块中进行查看，并据此进行检查验收。</p>' }
    , { qaID: 3, qaTitle: "到场材料检查哪些数据", qaTitleAsk: "到场材料检查哪些数据?", qaType: 1, qaContent: '<h2 class="hotQuestionHide_titH2">★ 到场材料检查哪些数据？</h2><p>瑞祥材料的检测数据是在此材料国家标准的基础上制定而成，可在瑞祥材料中的产品介绍板块进行查看。</p>' }
    , { qaID: 4, qaTitle: "到场材料数量与清单不符怎么办", qaTitleAsk: "到场材料数量与清单不符怎么办?", qaType: 1, qaContent: '<h2 class="hotQuestionHide_titH2">★ 到场材料数量与清单不符怎么办？</h2><p>瑞祥材料进场数量是根据现场施工实际使用需求确定，清单是根据报价工程量推算，两者间会有较小差距，当差距较大时您可通过瑞祥工程客服电话请集团造价中心用量复合。</p>' }
    , { qaID: 5, qaTitle: "到场材料质量不合格和处理方法是什么", qaTitleAsk: "到场材料质量不合格和处理方法是什么?", qaType: 1, qaContent: '<h2 class="hotQuestionHide_titH2">★ 到场材料质量不合格和处理方法是什么？</h2><p>当您对瑞祥材料有异议或认为材料不合格时，将材料小样快递至瑞祥造价.材料中心进行复核，当核实确认材料质量存在质量问题的，按瑞祥材料赔付标准进行标准赔偿。</p>' }
    , { qaID: 6, qaTitle: "质量保障", qaTitleAsk: "质量保障", qaType: 1, qaContent: '<h2 class="hotQuestionHide_titH2">★ 质量保障</h2><p>①　质量保障：现场配送材料质量与平台不符，不合格的，3倍赔偿。</p><p>②　供货周期：所有基础材料24小时配送至现场，加工定制材料7 - 15天配送至现场。</p><p>③　售后保障：A类材料质保2年，2年内免费上门维修或更换。B级材料1年内免费上门维修或更换。超出质保期材料成本价维修。</p>' }
    , { qaID: 10, qaTitle: "材料配送周期是多长", qaTitleAsk: "材料配送周期是多长?", qaType: 2, qaContent: '<h2 class="hotQuestionHide_titH2">★ 材料配送周期是多长？</h2><p>瑞祥材料根据其材料特性有不同的配送周期，配送周期内项目经理和材料商可时时进行沟通，详见下表：</p><p><img src="/Content/Theme/images/temp/qa_order_week.png" alt=""></p>' }
    , { qaID: 11, qaTitle: "材料能否自提", qaTitleAsk: "材料能否自提?", qaType: 2, qaContent: '<h2 class="hotQuestionHide_titH2">★ 材料能否自提？</h2><p>瑞祥配送的所有材料均可以到合作商处自提，提货前和材料商进行联系，以便提前备货。</p>' }
    , { qaID: 12, qaTitle: "材料配送进度在哪里查询", qaTitleAsk: "材料配送进度在哪里查询?", qaType: 2, qaContent: '<h2 class="hotQuestionHide_titH2">★ 材料配送进度在哪里查询？</h2><p>配送进度不同的角色不同的查看位置，项目经理在项目经理APP查看，客户在施工平台查看。</p>' }
    , { qaID: 13, qaTitle: "订单如何取消", qaTitleAsk: "订单如何取消?", qaType: 2, qaContent: '<h2 class="hotQuestionHide_titH2">★ 订单如何取消？</h2><p>项目经理下单后想取消订单，或材料商无法接单的经双方协商后联系集团材料中心进行订单取消。</p>' }
    , { qaID: 14, qaTitle: "下单后是否可以修改", qaTitleAsk: "下单后是否可以修改?", qaType: 2, qaContent: '<h2 class="hotQuestionHide_titH2">★ 下单后是否可以修改？</h2><p>项目经理确认订单后项目经理可及时与材料商联系，由材料商在其APP进行材料配送量调整。</p>' }
    , { qaID: 15, qaTitle: "配送后的确认周期是多长", qaTitleAsk: "配送后的确认周期是多长?", qaType: 2, qaContent: '<h2 class="hotQuestionHide_titH2">★ 配送后的确认周期是多长？</h2><p>材料商送货至现场后申请验收，项目经理需在5天内确认，超过5天系统将自定确认。</p>' }
    , { qaID: 20, qaTitle: "金牌服务有哪些内容", qaTitleAsk: "金牌服务有哪些内容?", qaType: 3, qaContent: '<h2 class="hotQuestionHide_titH2">★ 金牌服务有哪些内容？</h2><p>①　我们的瑞祥材料平台，为您提供网购式体验，快速找到最适合您材料和产品。众所周知，不懂装修的人自己管控一次装修就等同于脱掉一层皮，对各种材料的选购就足以让您身心俱疲。我们的瑞祥材料平台为您提供网购式体验，动动手指就可以让您轻松找到合适的产品。品牌与价格的筛选功能可以最大限度地满足您的喜好和价格要求。</p><p>②　我们的瑞祥材料平台，为您从源头上管控材料的品质。我们有超过200家固定的材料供应商以及完备的供应商渠道管理体系。我们对自己的材料品质，信心十足。如果您发现我们材料品牌与报价清单中材料不匹配，或规格标准不一致，或材料有严重损坏，我们假一赔三。也就是您发现问题的那批材料，按3倍给您赔偿，一根轻钢龙骨有问题，按3根的价格给你赔偿。</p>' }
    , { qaID: 21, qaTitle: "金牌服务对您有什么好处", qaTitleAsk: "金牌服务对您有什么好处?", qaType: 3, qaContent: '<h2 class="hotQuestionHide_titH2">★ 金牌服务对您有什么好处？</h2><p>我们瑞祥材料平台的金牌服务可以让您轻松找到最适合您的材料和产品，并从源头上保障材料和产品的质量。</p>' }
    , { qaID: 22, qaTitle: "如何实现金牌服务", qaTitleAsk: "如何实现金牌服务?", qaType: 3, qaContent: '<h2 class="hotQuestionHide_titH2">★ 如何实现金牌服务？</h2><p> 我们实现金牌服务的方式有两种，首先是线上的瑞祥材料平台让您轻松地选择，第二就是通过线下严格的监督机制保障服务质量（客户发现问题，按当批问题材料价格的3倍赔偿客户，监理发现问题，按当批问题材料价格的2倍作为奖励，工人发现问题，已经举报查实，奖励500元，全民动员，监管质量，保障服务。）</p>' }
    , { qaID: 30, qaTitle: "如何为您解决疑难", qaTitleAsk: "如何为您解决疑难?", qaType: 4, qaContent: '<h2 class="hotQuestionHide_titH2">★ 如何为您解决疑难？</h2><p>我们的瑞祥材料平台中，针对于每一样产品，有详细的产品介绍和参数描述，有效地帮助您了解产品，选择产品,同时可以通过平台教您简单易懂的学会材料质量验收。</p>' }
    , { qaID: 31, qaTitle: "质量疑问", qaTitleAsk: "质量疑问?", qaType: 4, qaContent: '<h2 class="hotQuestionHide_titH2">★ 质量疑问？</h2><p>①　我们的瑞祥材料平台，为您提供明确的质量检验标准和检验方法，并赠送检验器材，让您无后顾之忧。</p><p>②　专业人员答疑。如果以上解决疑难的方式还是不能满足您的需求，没关系，我们平台提供专业人士的联系方式，随时为您答疑解惑。</p>' }
];

var dataQuestionType = [
    { TypeID: 1, TypeTitle: "正品保障" },
    { TypeID: 2, TypeTitle: "急速物流" },
    { TypeID: 3, TypeTitle: "金牌服务" },
    { TypeID: 4, TypeTitle: "随时解决疑难" }
];

function showH() {
    //高度计算
    $(".hotQuestionHide_div").outerHeight($(".hotQuestionHide_div_con").outerHeight() + 20);
    $(window).resize(function () {
        //高度计算
        $(".hotQuestionHide_div").outerHeight($(".hotQuestionHide_div_con").outerHeight() + 20);
    });
}
