
//详细页左侧图片轮播
$(function() {
    var bannerSlider = new Slider($('.mall_slide_box'), {
        time: 3000,
        delay: 400,
        auto: false,
        mode: 'fade',
        controller: $('.none'),
        activeControllerCls: 'cur'
    });
    $('.mall_slide_box .prev').click(function() {
        bannerSlider.prev();
    });
    $('.mall_slide_box .next').click(function() {
        bannerSlider.next();
    });
});
///*选项卡*/
//$(".mall_detail_tab li").each(function (index) {
//  $(this).click(function () {
//      //$(window).scrollTop($(window).height()-42);
//      $(this).addClass("cur").siblings().removeClass("cur");
//      $(".mall_detail_main").eq(index).show().siblings(".mall_detail_main").hide();
//  })
//});
//关联商品随屏滚动
$(window).scroll(function () {
    var menuYloc = $(".mall_detail_r").offset().top;
    var menuZloc = $(".mall_detail_l").offset().top;
    var offsetTop = $(window).scrollTop();
    var _foot_top = $(".mallFooter").offset().top - 20;
    if (offsetTop > menuZloc && offsetTop < _foot_top) {
        $(".mall_detail_r").css({ position: "absolute", right: 0 });
        $(".mall_detail_r").show();
       // $(".mall_detail_r").stop().animate({ top: offsetTop - 185 }, { duration: "fast", queue: false });
        $(".mall_detail_r").stop().css({ top: offsetTop - menuZloc });
    }
    if (offsetTop < menuZloc) {
        $(".mall_detail_r").show();
       // $(".mall_detail_r").stop().animate({ top: 0 }, { duration: "fast", queue: false });
        $(".mall_detail_r").stop().css({ top: 0 });
    }
    if (offsetTop > _foot_top - $(".mall_detail_r").outerHeight()) {
        $(".mall_detail_r").css({ position: "absolute", right: 0 });
        $(".mall_detail_r").hide();
    }
    //产品介绍导航固定在屏幕上方
    var _tabT = $(".mall_btm_box").offset().top;
    if (offsetTop > _tabT) {
        $(".mall_detail_tab").css({ position: "fixed",top:0 });
    } else {
        $(".mall_detail_tab").css({ position: "absolute" });
    }
});
















