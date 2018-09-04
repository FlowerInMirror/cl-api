//除店铺，人事 三段click事件
//processPersonnelToOrderExpected.jsp
$(document).on("click","#processPersonnelToOrderExpected .orderExp",function () {
    debugger
    $(this).addClass("anItemBor-active").siblings().removeClass("anItemBor-active");
    var uid = $("#tab1").find("tr.tractive").attr("data-uid");
    var rwdid = $(this).attr("data-rwdid");
    layer.open({
        type: 2,
        title: "预计订单",
        area: ['80%', '80%'],
        btn: ["关闭"],
        content: basePath+"/user_info_three-api/layer/processlayerPersonnelToOrderExpectedDetail?uid="+uid+"&rwdid="+rwdid+"",
        success: function (layero,index) {
            layero.find('.layui-layer-btn').css('text-align', 'center');
        },
    });
});
//processPersonnelToOrderDetail.jsp
//订单详情点击查看
$(document).on("click", ".order[data-orderid]", function () {
    debugger
    var orderID = $(this).data("orderid");

    $.ajax({
        url: rx_gc+"/NewMaterail/OrderDetailsFlow",
        type: 'POST',
        data: { poID: orderID },
        dataType: 'html',
        success: function (result) {
            $(".SearchConMain").hide();
            $(".SearchConDetail").show();
            $(".SearchConDetail").html(result);
            countdaily();
        },
        error: function (e) {
            console.error(e);
        }
    });
});
$(document).on("click", ".rig_closer", function () {
    $(".SearchConMain").show();
    $(".SearchConDetail").hide();
});

//登录冻结开关按钮
$(document).on("click", "#ResultEntranceEvaluation .WithdrawMoney", function () {
    debugger
    var obj = new Object();
    var uid = $("#tab1").find("tr.tractive").attr("data-uid");
    obj.uid = uid;
    obj.systemtype = $(this).attr("data-systype");
    obj.infotype = parseInt($(this).attr("data-infotype"));
    $(this).toggleClass("switchActived");
    $(this).parent().find(".remark").toggleClass("cgreen");
    if ($(this).hasClass("switchActived")) {
        obj.switchstatus = 1;
        $(this).parent().find(".remark").text("通过");
    } else {
        $(this).parent().find(".remark").text("关闭");
        obj.switchstatus = 0;
    }
    var updateuserno = $("#curLoginCardNumber").val();
    obj.updateuserno = updateuserno;
    if(updateuserno==null||updateuserno==undefined){
        layer.alert("请重新登陆！",{icon:5});
        return false;
    }
    $.ajax({
        url: ctx +"/jmat/infoAuditSwitch/saveInfoAuditSwitch",
        type: "post",
        dataType: "json",
        data: obj,
        success: function (data) {

        },
        error: function (err) {
            alert("操作出错！");
        }
    });
});
