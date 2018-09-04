// 更新按钮
$(document).on("click","#btnSubmitProduct",function(){
    layer.msg("功能正在研发中...", {icon: 4});
});
// 删除按钮
$(document).on("click","#btnDelProduct",function(){
    layer.confirm("请确认删除产品？", {
        bth:['确定','取消']
    }, function () {
        var obj = {};
        obj.spID = proID;
        obj.spStatus = 1;
        obj.userNo = wpsUserID; // 当前登录操作用户卡号
        $.ajax({
            type: 'POST',
            url: basePath + "/special_product-api/delete",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                if (msg.statusMsg == "success") {
                    layer.msg("删除成功", {icon: 5});
                    window.opener=null;
                    window.open('','_self');
                    window.close();
                } else {
                    alert(msg.statusMsg);
                }
            },
            error: function (err) {alert("系统异常 请联系系统管理员"); return;}
        });
    }, function () {
    });

});
// 下架按钮
$(document).on("click","#btnDownProduct",function(){
    layer.confirm("请确认下架产品？", {
        bth:['确定','取消']
    }, function () {
        var obj = {};
        obj.spID = proID;
        obj.spStatus = 2;
        obj.userNo = wpsUserID; // 当前登录操作用户卡号
        $.ajax({
            type: 'POST',
            url: basePath + "/special_product-api/update",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (msg) {
                if (msg.statusMsg == "success") {
                    $("#btnUpProduct").show();
                    $("#btnDownProduct").hide();
                    layer.msg("下架成功", {icon: 1});

                } else {
                    alert(msg.statusMsg);
                }
            },
            error: function (err) {alert("系统异常 请联系系统管理员"); return;}
        });
    }, function () {
    });

});
// 上架按钮
$(document).on("click","#btnUpProduct",function(){
    var obj = {};
    obj.spID = proID;
    obj.spStatus = 0;
    obj.userNo = wpsUserID; // 当前登录操作用户卡号
    $.ajax({
        type: 'POST',
        url: basePath + "/special_product-api/update",
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (msg) {
            if (msg.statusMsg == "success") {
                $("#btnUpProduct").hide();
                $("#btnDownProduct").show();
                layer.msg("上架成功", {icon: 1});

            } else {
                alert(msg.statusMsg);
            }
        },
        error: function (err) {alert("系统异常 请联系系统管理员"); return;}
    });

});
// 关闭按钮
$(document).on("click","#btnCloseProduct",function(){
    window.opener=null;
    window.open('','_self');
    window.close();
});