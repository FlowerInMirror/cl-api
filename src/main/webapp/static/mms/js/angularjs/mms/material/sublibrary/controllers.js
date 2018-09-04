// ========== AngularJS 控制器开始
myApp.controller("myController", function ($scope, $http) {
    // 公共函数
    $scope.getRes = function (method,url,data){
        $http({
            method: method,
            url: url,
            data: data,
            headers: { 'Content-Type': 'application/json;charset=UTF-8' }
        }).success(function (data){
            return data;
        }).error(function (response) {
            alert("操作异常,请联系统管理员");console.log("error"); console.log(response);
        });
    }

    //===============子库地区 开始
    // 子库地区一段 AngularJS排序的功能
    $scope.sortByTreeOrder = function (ziduan) {
        if (ziduan == $scope.sortOrder) {
            $scope.sortOrder = null;
            $scope.reservse = null;
            setTimeout(function () {
                $("#tabDataToMaterial tr").eq(0).click();
            })
            return;
        } else {
            $scope.reservse = false;
        }
        $scope.sortOrder = ziduan;
        setTimeout(function () {
            $("#tabDataToMaterial tr").eq(0).click();
        })
    };

    // 子库地区二段 展示 材料档次DIV,之间判断档次新增品牌标识重载当前段位数据
    $scope.showDivMaterialDetail = function (levelFlag) {
        var hidLevelAddFlag = $("#hidLevelAddFlag").val();
        var treeFourID =$("#hidTreeID").val();
        switch(levelFlag) {
            case 1:
                $("#hidTwoNavFlag").val(3);
                $("#hidLevelStaFlag").val(1);
                $("#divMaterialDetailToA").prop("style","display: block;").siblings().prop("style","display: none;");
                if (hidLevelAddFlag)
                    $scope.cityTwoLevelA(treeFourID);
                break;
            case 2:
                $("#hidTwoNavFlag").val(4);
                $("#hidLevelStaFlag").val(2);
                $("#divMaterialDetailToB").prop("style","display: block;").siblings().prop("style","display: none;");
                if (hidLevelAddFlag)
                    $scope.cityTwoLevelB(treeFourID);
                break;
            case 3:
                $("#hidTwoNavFlag").val(5);
                $("#hidLevelStaFlag").val(4);
                $("#divMaterialDetailToC").prop("style","display: block;").siblings().prop("style","display: none;");
                if (hidLevelAddFlag)
                    $scope.cityTwoLevelC(treeFourID);
                break;
        }
        $("#hidLevelAddFlag").val(""); // 重置档次品牌项添加标识
    };


    // 规格下对应档次是否存在标识 作用于:动态加载 状态 导航栏/状态、成本、应用下 档次项。
    $scope.getLevelShowFlag = function(treeFourID){
        var obj = {};// 定义 JSON对象(获取数据检索条件)
        obj.cityID = $("#hidCityID").val();
        obj.treeFourID = treeFourID;
        $http({
            method:'post',
            url: basePath + "/sublibrary-api/city_two_section/level_flag",
            data:obj,
            headers: { 'Content-Type': 'application/json;charset=UTF-8' }
        }).success(function (data){
            // 标识状态: 0 不予显示,!0 显示
            $scope.aLevelShowFlag = data.body.aLevel;// A档标识
            $scope.bLevelShowFlag = data.body.bLevel;// B档标识
            $scope.cLevelShowFlag = data.body.cLevel;// C档标识
            // 默认选中 地库一段列表第一个
        }).error(function (response) {
            console.log("error"); console.log(response);
        });
    };

    // 选中二段导航 子库地区一段列表 点击事件
    // function : 获取当前选中的二段 项 ,重新发送请求页面所需数据,从而设置页面的数据到AngularJS作用域,AngularJS检测脏值,并应用新值.动态加载页面数据
    $scope.pitchOnTwoNav = function (treeFourID,loopStatus,platformLoopStatus) {
        $(".anItemBor-active").removeClass("anItemBor-active");//移除二段选中项
        $("#three-section").hide();// 切换规格 隐藏三段
        // 设置 档次显示标识
        $scope.getLevelShowFlag(treeFourID);
        // 设置 作用域中的四级科目ID
        $scope.hidTreeID = treeFourID;
        // 设值 回路总标识到作用域中
        $scope.hidLoopStatus = loopStatus;
        // 设值 回路总标识到作用域中
        $scope.hidPlatformLoopStatus = platformLoopStatus;

        // 获取当前选中的二段导航标识
        var towNavFlag = $("#hidTwoNavFlag").val();
        // 根据不同标识,从绑定新数据到页面上.
        switch(towNavFlag){
            case '0':$scope.cityTwoState(treeFourID);break;
            case '1':$scope.cityTwoPlatform(treeFourID);break;
            case '2':$scope.cityTwoMall(treeFourID);break;
            case '3':$scope.cityTwoLevelA(treeFourID);break;
            case '4':$scope.cityTwoLevelB(treeFourID);break;
            case '5':$scope.cityTwoLevelC(treeFourID);break;
            case '6':$scope.cityTwoPrice(treeFourID);break;
            case '7':$scope.cityTwoCost(treeFourID);break;
            case '8':$scope.cityTwoApp(treeFourID);break;
        }
    };

    // 发送$http请求 子库地区二段接口 获取Promise对象,根据返回的Promise对象响应成功/失败的状态来处理数据.
    // 入参obj:
    // cityID=城市ID
    // treeFourID=四级科目树ID
    // pageType=页面类型(0.状态(默认)/1.平台/2.商城/3-5.A-C档次/6.价格/7.成本/8.应用)--检索不同结果集返回
    $scope.getSublibraryCityPromise = function (obj) {
        return $http({
            method: 'post',
            url:  basePath + "/sublibrary-api/city_two_section",
            data: obj,
            headers: { 'Content-Type': 'application/json;charset=UTF-8' }
        });
    };
    // AngularJS 双向数据绑定应用
    // 应用 8
    $scope.cityTwoApp = function (treeFourID) {
        $("#hidTwoNavFlag").val(8);// 设置 二段导航标识
        var obj = {};// 定义 JSON对象(获取数据检索条件)
        obj.cityID = $("#hidCityID").val();
        obj.treeFourID = treeFourID;
        obj.pageType = 8;
        var promise = $scope.getSublibraryCityPromise(obj);
        // 成功响应处理
        promise.success(function(data,status,config,headers){$scope.two_app = data.body;});
        // 失败响应处理
        promise.error(function(data,status,hedaers,config){console.log(data.body.statusCode); console.log(data);});
    }

    // 报价(原成本) 7
    $scope.cityTwoCost = function (treeFourID) {
        // 展示成本,隐藏其他.
        $("#divMaterialDetailToQuotation").prop("style","display: block;").siblings().prop("style","display: none;");

        $("#hidTwoNavFlag").val(7);// 设置 二段导航标识
        var obj = {};// 定义 JSON对象(获取数据检索条件)
        obj.cityID = $("#hidCityID").val();
        obj.treeFourID = treeFourID;
        obj.pageType = 7;
        var promise = $scope.getSublibraryCityPromise(obj);
        // 成功响应处理
        promise.success(function(data,status,config,headers){$scope.two_cost = data.body;});
        // 失败响应处理
        promise.error(function(data,status,hedaers,config){console.log(data.body.statusCode); console.log(data);});
    }

    // 价格 6
    $scope.cityTwoPrice = function (treeFourID) {
        $("#divMaterialDetailToLocalPrice").siblings().prop("style","display: none;");
        $("#hidTwoNavFlag").val(6);// 设置 二段导航标识
        var obj = {};// 定义 JSON对象(获取数据检索条件)
        obj.cityID = $("#hidCityID").val();
        obj.treeFourID = treeFourID;
        obj.pageType = 6;
        var promise = $scope.getSublibraryCityPromise(obj);
        // 成功响应处理
        promise.success(function(data,status,config,headers){$scope.two_price = data.body;});
        // 失败响应处理
        promise.error(function(data,status,hedaers,config){console.log(data.body.statusCode); console.log(data);});
    };

    // 档次价格区间转换字符串函数
    $scope.levelPriceInterval = function(priceMin,priceMax){
        if(0 != priceMin && 0 != priceMax && priceMin != priceMax)
        // 有价格区间,返回区间字符串
            return priceMin + '-' + priceMax;
        else if (0 != priceMin && 0 != priceMax && priceMin == priceMax)
        // 区间不等于0且区间值相同,返回单个字符串
            return priceMin;
        // 无最大最小价钱区间,返回 '-'
        return '-';
    };
    // 设置隐藏域材料ID(JQuery与Angularjs冲突,使用Angularjs事件.)
    $scope.setHideMatID = function(matID){
        $("#hidMatID").val(matID);
    };

    // C档 5
    $scope.cityTwoLevelC = function (treeFourID) {
        $("#hidTwoNavFlag").val(5);// 设置 二段导航标识
        var obj = {};// 定义 JSON对象(获取数据检索条件)
        obj.cityID = $("#hidCityID").val();
        obj.treeFourID = treeFourID;
        obj.pageType = 5;
        obj.levelFlag = 4;
        var promise = $scope.getSublibraryCityPromise(obj);
        // 成功响应处理
        promise.success(function(data,status,config,headers){$scope.two_level = data.body});
        // 失败响应处理
        promise.error(function(data,status,hedaers,config){console.log(data.body.statusCode); console.log(data);});
    }

    // B档 4
    $scope.cityTwoLevelB = function (treeFourID) {
        $("#hidTwoNavFlag").val(4);// 设置 二段导航标识
        var obj = {};// 定义 JSON对象(获取数据检索条件)
        obj.cityID = $("#hidCityID").val();
        obj.treeFourID = treeFourID;
        obj.pageType = 4;
        obj.levelFlag = 2;
        var promise = $scope.getSublibraryCityPromise(obj);
        // 成功响应处理
        promise.success(function(data,status,config,headers){$scope.two_level = data.body});
        // 失败响应处理
        promise.error(function(data,status,hedaers,config){console.log(data.body.statusCode); console.log(data);});
    }

    // A档 3
    $scope.cityTwoLevelA = function (treeFourID) {
        $("#hidTwoNavFlag").val(3);// 设置 二段导航标识
        var obj = {};// 定义 JSON对象(获取数据检索条件)
        obj.cityID = $("#hidCityID").val();
        obj.treeFourID = treeFourID;
        obj.pageType = 3;
        obj.levelFlag = 1;
        var promise = $scope.getSublibraryCityPromise(obj);
        // 成功响应处理
        promise.success(function(data,status,config,headers){
            $scope.two_level = data.body;
            // 设置隐藏域数据
            // TODO 二段 档次 弹层隐藏域设值
            // $("#lblMainOffExteriorName").html("@(Model.PerfectM.tps_ExteriorName)");
            // $("#lblMainOffExteriorSC").html("@(Model.PerfectM.tps_ExteriorSC)");
            // $("#lblMainOffExteriorDM").html("@(Model.PerfectM.tps_ExteriorDM)");
            // $(".j_uiTab9 li[data-level=two_level.currentLevel]").attr("data-brandid", "@(Model.TSID)");
        });
        // 失败响应处理
        promise.error(function(data,status,hedaers,config){console.log(data.body.statusCode); console.log(data);});
    };

    // 商城 2
    $scope.cityTwoMall = function (treeFourID) {
        $("#divMaterialDetailToPlatform").siblings().prop("style","display: none;");
        $("#hidTwoNavFlag").val(2);// 设置 二段导航标识
        var obj = {};// 定义 JSON对象(获取数据检索条件)
        obj.cityID = $("#hidCityID").val();
        obj.treeFourID = treeFourID;
        obj.pageType = 2;
        var promise = $scope.getSublibraryCityPromise(obj);
        // 成功响应处理
        promise.success(function(data,status,config,headers){$scope.two_mall = data.body});
        // 失败响应处理
        promise.error(function(data,status,hedaers,config){console.log(data.body.statusCode); console.log(data);});
    };

    // 平台 1
    $scope.cityTwoPlatform = function (treeFourID) {
        $("#divMaterialDetailToBase").siblings().prop("style","display: none;");
        $("#hidTwoNavFlag").val(1);// 设置 二段导航标识
        var obj = {};// 定义 JSON对象(获取数据检索条件)
        obj.cityID = $("#hidCityID").val();
        obj.treeFourID = treeFourID;
        obj.pageType = 1;
        var promise = $scope.getSublibraryCityPromise(obj);
        // 成功响应处理
        promise.success(function(data,status,config,headers){
            $scope.two_platform = data.body.body;
        });
        // 失败响应处理
        promise.error(function(data,status,hedaers,config){console.log(data.body.statusCode); console.log(data);});
    };

    // 状态 0(默认)
    $scope.cityTwoState = function (treeFourID) {
        $("#divMaterialDetailToStatus").siblings().prop("style","display: none;");
        if ( null == treeFourID)
            treeFourID = $("#hidTreeID").val();
        $("#hidTwoNavFlag").val(0);// 设置 二段导航标识
        var obj = {};// 定义 JSON对象(获取数据检索条件)
        obj.cityID = $("#hidCityID").val();
        obj.treeFourID = treeFourID;
        var promise = $scope.getSublibraryCityPromise(obj);
        // 成功响应处理
        promise.success(function(data,status,config,headers){
            $scope.two_state = data.body;
            // 计算A/B/C档次 回路总标识状态 levelLoopScore 档次回路积分(1 完成,0.5 未,默认:1,三种档次为小数时变为0.5)
            var levelLoopScoreA = data.body.aLevel.levelTotalLoopScore;
            var levelLoopScoreB = data.body.bLevel.levelTotalLoopScore;
            var levelLoopScoreC = data.body.cLevel.levelTotalLoopScore;
            $scope.two_state.levelLoopScore = 1;
            if(String(levelLoopScoreA).indexOf(".")>-1 || String(levelLoopScoreB).indexOf(".")>-1 || String(levelLoopScoreC).indexOf(".")>-1 ){
                $scope.two_state.levelLoopScore = 0.5;
            }
        });
        // 失败响应处理
        promise.error(function(data,status,hedaers,config){console.log(data.body.statusCode);});
    };


    //===============子库起始页 开始
    //一段列表(子库)数据绑定
    $scope.loadIndexOneList = function () {
        $http({
            method:'post',
            url: basePath + "/sublibrary-api/index_one_section",
            headers:{'Content-Type':'application/json;charset=UTF-8'},
        }).success(function (data){

            // 设置 一段列表 到作用域中
            $scope.cityStatisticsItems = data.body;

            // 设置 回路总数/回路完成总数/回路未完成总数 到作用域中
            $scope.loopClasssCount = data.body[0].classs;
            $scope.loopAccomplishCount = data.body[0].accomplish;
            $scope.loopUnfinishedCount = $scope.loopClasssCount - $scope.loopAccomplishCount;
            // <%--工程经理.材料 登录--%>
            setTimeout(function(){
                // 登录人不为集团时,开始模拟城市选择操作,最终展示效果:只展示指定地区内容. 集团标识:地址ID为39
                if (userCityID != 39)
                {
                    $("tr[data-city='" + userCityID + "']").click();
                    $(".uiTab1").find("li[data-index=\"1\"]").click().siblings().hide();

                } else {
                    $("#tabData tr:eq(0)").click();
                    rxued.table.LChangeapart($("#tab1 tr"), "#f9f9f9", "#fff");
                }
            })
        }).error(function (response) {
            console.log("error"); console.log(response);
        });
    };
    // 同步 起始一段列表数据
    $scope.freshIndexOneList = function () {
        // 销毁 子库一段列表缓存
        $http.get(basePath + "/sublibrary-api/destroy_index_list_cache")
        .success(function (res) {
            console.log(res);
            // 重载 子库一段列表数据
            $http({
                method:'post',
                url: basePath + "/sublibrary-api/index_one_section",
                headers:{'Content-Type':'application/json;charset=UTF-8'},
            }).success(function (data){
                // 设置 一段列表 到作用域中
                $scope.cityStatisticsItems = data.body;

                // 设置 回路总数/回路完成总数/回路未完成总数 到作用域中
                $scope.loopClasssCount = data.body[0].classs;
                $scope.loopAccomplishCount = data.body[0].accomplish;
                $scope.loopUnfinishedCount = $scope.loopClasssCount - $scope.loopAccomplishCount;

            }).error(function (response) {
                console.log("error"); console.log(response);
            });
            // 重载 二段状态/材料统计/成本相关数据
            $http({
                method: 'GET',
                url:  basePath + "/sublibrary-api/index_two_section/status_and_statistics",
                params: {'cityID':$scope.hidCityID}
            }).then(function (res) {
                $scope.SecondDatas = res.data.body;
                newobj.materialVal = $scope.SecondDatas.s_theLastMonth.mat;
                newobj.photoVal = $scope.SecondDatas.s_theLastMonth.photo;
                newobj.priceVal = $scope.SecondDatas.s_theLastMonth.price;
                $scope.evaluate();
            }, function (err) {
                console.log(err)
            });

            layer.msg("全国材料数据 同步成功",{icon:6});
        })
        .error(function (res) {console.log(res);});
    };


    var newobj = {};
    $scope.evaluate = function () {
        //子库二段 操作记录数据绑定
        $(".MaterialTotalNum").text(newobj.materialVal);
        $(".PhotoTotalNum").text(newobj.photoVal);
        $(".PriceTotalNum").text(newobj.priceVal);
    }
    //子库二段 操作记录 点击搜索 搜索数据
    $scope.recordDataShow = function () {
        $(".commonRgtAlert").stop().animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
        $(".analyItem").removeClass("anItemBor-active");
        var obj = {};
        obj.cityID = $(".tractive").attr("data-city");
        obj.startTime = $(".startDate").val();
        obj.terminalTime = $(".endDate").val();
        $http({
            method: 'post',
            url:  basePath + "/sublibrary-api/index_two_section/operation_note",
            data: obj,
            headers: { 'Content-Type': 'application/json;charset=UTF-8' },
        }).success(function (res) {
            $scope.SearchDatas = res.body;
            newobj.materialVal = $scope.SearchDatas.materialCount;
            newobj.photoVal = $scope.SearchDatas.photoCount;
            newobj.priceVal = $scope.SearchDatas.priceCount;
            $scope.evaluate();
        }).error(function (response) {
            console.log("error"); console.log(response);
        });
    }
    //将字符串格式的时间转换成时间格式
    $scope.filterDate = function (str) {
        return new Date(str.replace(/-/g, "/"));
    }
    //****************翻页效果开始***************
    // 配置分页基本参数
    $scope.paginationConf1 = {
        currentPage: 1,// 获取当前页
        itemsPerPage: 15// 获取每页多少条数据
    };
    $scope.paginationConf2 = {
        currentPage: 1,// 获取当前页
        itemsPerPage: 15// 获取每页多少条数据
    };
    $scope.paginationConf3 = {
        currentPage: 1,// 获取当前页
        itemsPerPage: 15// 获取每页多少条数据
    };
    //子库二段 操作记录 材料数据绑定
    $scope.materialDataShow = function (myevent) {
        //************给当前点击的元素添加class************
        $(myevent.target).closest(".analyItem").addClass("anItemBor-active").siblings().removeClass("anItemBor-active");
        //弹出层展示
        $(".commonRgtAlert").not(".operaMaterialAlert").stop().animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
        $(".operaMaterialAlert").show().stop(true, true).animate({
            left: "0"
        }, 200);
        $(".commonRgtAlert .close").click(function () {
            $(".commonRgtAlert").stop().animate({
                left: "100%"
            }, 200, function () {
                $(myevent.target).closest(".analyItem").removeClass("anItemBor-active");
            });
        });

        // 重新获取数据条目
        var reGetProducts = function () {
            // 发送给后台的请求数据
            var postData = {
                currentPage: $scope.paginationConf1.currentPage,
                itemsPerPage: $scope.paginationConf1.itemsPerPage,
                cityID: $(".tractive").attr("data-city"),
                startTime: $(".startDate").val(),
                terminalTime: $(".endDate").val(),
                beginRowNum:  ($scope.paginationConf1.currentPage-1)*15+1,
                endRowNum: $scope.paginationConf1.currentPage * 15
            };
            $http.post( basePath + '/sublibrary-api/index_three_section/operation_note/material', postData).success(function (data) {
                // 变更分页的总数
                $scope.paginationConf1.totalItems = data.body.totalRecord;
                // 变更产品条目
                $scope.MaterialDatas = data.body.result;
                setTimeout(function () {
                    //计算弹出层数据高度
                    countAlertH(".operaMaterialAlert");
                    // 隔行换色
                    $('.operaMaterialAlert .oprecordScroll tr:visible:odd').css("background-color", "#fcfcfc");
                    $('.operaMaterialAlert .oprecordScroll tr').mouseover(function () {
                        $(this).addClass("trhover").siblings().removeClass("trhover");
                    }).mouseout(function () {
                        $(".trhover").removeClass("trhover");
                    });
                });
            });
        }
        $scope.paginationConf1.currentPage = 1;
        // 通过$watch currentPage和itemperPage 当他们一变化的时候，重新获取数据条目
        $scope.$watch('paginationConf1.currentPage + paginationConf1.itemsPerPage', reGetProducts);
    };
    //****************翻页效果结束***************
    //子库二段 操作记录 照片数据绑定
    $scope.photoDataShow = function (myevent) {
        //************给当前点击的元素添加class************
        $(myevent.target).closest(".analyItem").addClass("anItemBor-active").siblings().removeClass("anItemBor-active");
        //弹出层展示
        $(".commonRgtAlert").not(".opreraPhotoAlert").stop().animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
        $(".opreraPhotoAlert").show().stop(true, true).animate({
            left: "0"
        }, 200);
        $(".commonRgtAlert .close").click(function () {
            $(".commonRgtAlert").stop().animate({
                left: "100%"
            }, 200, function () {
                $(myevent.target).closest(".analyItem").removeClass("anItemBor-active");
            });
        });

        // 重新获取数据条目
        var reGetProducts = function () {
            // 发送给后台的请求数据
            var postData = {
                currentPage: $scope.paginationConf2.currentPage,
                itemsPerPage: $scope.paginationConf2.itemsPerPage,
                cityID: $(".tractive").attr("data-city"),
                startTime: $(".startDate").val(),
                terminalTime: $(".endDate").val(),
                beginRowNum: ($scope.paginationConf2.currentPage - 1) * 15 + 1,
                endRowNum: $scope.paginationConf2.currentPage * 15
            };
            $http.post( basePath + '/sublibrary-api/index_three_section/operation_note/photo', postData).success(function (data) {
                // 变更分页的总数
                $scope.paginationConf2.totalItems = data.body.totalRecord;
                // 变更产品条目
                $scope.PhotoDatas = data.body.result;
                setTimeout(function () {
                    //计算弹出层数据高度
                    countAlertH(".opreraPhotoAlert");
                    // 隔行换色
                    $('.opreraPhotoAlert .oprecordScroll tr:visible:odd').css("background-color", "#fcfcfc");
                    $('.opreraPhotoAlert .oprecordScroll tr').mouseover(function () {
                        $(this).addClass("trhover").siblings().removeClass("trhover");
                    }).mouseout(function () {
                        $(".trhover").removeClass("trhover");
                    });
                });
            });
        }
        $scope.paginationConf2.currentPage = 1;
        // 通过$watch currentPage和itemperPage 当他们一变化的时候，重新获取数据条目
        $scope.$watch('paginationConf2.currentPage + paginationConf2.itemsPerPage', reGetProducts);
    };
    //子库二段 操作记录 价格数据绑定
    $scope.priceDataShow = function (myevent) {
        //************给当前点击的元素添加class************
        $(myevent.target).closest(".analyItem").addClass("anItemBor-active").siblings().removeClass("anItemBor-active");
        //弹出层展示
        $(".commonRgtAlert").not(".opreraPriceAlert").stop().animate({
            left: "100%"
        }, 200, function () {
            $(this).hide();
        });
        $(".opreraPriceAlert").show().stop(true, true).animate({
            left: "0"
        }, 200);
        $(".commonRgtAlert .close").click(function () {
            $(".commonRgtAlert").stop().animate({
                left: "100%"
            }, 200, function () {
                $(myevent.target).closest(".analyItem").removeClass("anItemBor-active");
            });
        });
        // 重新获取数据条目
        var reGetProducts = function () {
            // 发送给后台的请求数据
            var postData = {
                currentPage: $scope.paginationConf3.currentPage,
                itemsPerPage: $scope.paginationConf3.itemsPerPage,
                cityID: $(".tractive").attr("data-city"),
                startTime: $(".startDate").val(),
                terminalTime: $(".endDate").val(),
                beginRowNum: ($scope.paginationConf3.currentPage - 1) * 15 + 1,
                endRowNum: $scope.paginationConf3.currentPage * 15
            };
            $http.post( basePath + '/sublibrary-api/index_three_section/operation_note/price', postData).success(function (data) {
                // 变更分页的总数
                $scope.paginationConf3.totalItems = data.body.totalRecord;
                // 变更产品条目
                $scope.PriceDatas = data.body.result;
                setTimeout(function () {
                    //计算弹出层数据高度
                    countAlertH(".opreraPriceAlert");
                    // 隔行换色
                    $('.opreraPriceAlert .oprecordScroll tr:visible:odd').css("background-color", "#fcfcfc");
                    $('.opreraPriceAlert .oprecordScroll tr').mouseover(function () {
                        $(this).addClass("trhover").siblings().removeClass("trhover");
                    }).mouseout(function () {
                        $(".trhover").removeClass("trhover");
                    });
                });
            });
        }
        $scope.paginationConf3.currentPage = 1;
        // 通过$watch currentPage和itemperPage 当他们一变化的时候，重新获取数据条目
        $scope.$watch('paginationConf3.currentPage + paginationConf3.itemsPerPage', reGetProducts);
    };
    //子库二段(状态、材料模块)数据绑定
    $scope.bindDatas = function (cityID) {
        $http({
            method: 'GET',
            url:  basePath + "/sublibrary-api/index_two_section/status_and_statistics",
            params: {'cityID':cityID}
        }).then(function (res) {
            $scope.SecondDatas = res.data.body;
            setTimeout(function () {
                $("#divChildDetail .uiTab9 li:eq(0)").click();
            });
            newobj.materialVal = $scope.SecondDatas.s_theLastMonth.mat;
            newobj.photoVal = $scope.SecondDatas.s_theLastMonth.photo;
            newobj.priceVal = $scope.SecondDatas.s_theLastMonth.price;
            $scope.evaluate();
        }, function (err) {
            console.log(err)
        })
    };

    // 切换城市
    $scope.loadSecondDatas = function (cityID,classs,accomplish) {
        // 重新设置 回路相关数据(种类总数/完成总数/未完总数) 到作用域
        $scope.loopClasssCount = classs;
        $scope.loopAccomplishCount = accomplish;
        $scope.loopUnfinishedCount = classs - accomplish;

        $("#tab1 tr[data-city='" + cityID+"']").addClass("tractive").siblings().removeClass("tractive");
        $scope.hidCityID = cityID;// 设置 城市ID到作用域中
        $scope.bindDatas(cityID);
    };
    //class的添加
    $scope.AddClass = function (obj) {
        if(obj>0)
        {
            return 'cRed';
        } else {
            return '';
        }
    }
    //绑定子库-成本弹出层数据
    $http({
        method: 'GET',
        url:  basePath + "/sublibrary-api/index_three_section/cost/cost"
        //params: obj
    }).then(function (res) {
        $scope.costDatas = res.data.body;
        setTimeout(function () {
            countDiv();
        })
        //取绝对值
        //$scope.AbsoluteVal = function (obj) {
        //    return Math.abs(obj);
        //}
    }, function (err) {
        console.log(err)
    });
    //绑定子库-成本-使用排名弹出层数据
    $scope.appamoutDataShow = function (myevent) {
        var objJson = {};
        objJson.cityID = $(".tractive").attr("data-city");
        objJson.topNum = $(".useRankingLabel input:checked").attr("data-number");
        $http({
            method: 'GET',
            url:  basePath + "/sublibrary-api/index_three_section/cost/application_amount/plan",
            params: objJson
        }).then(function (res) {
            $scope.userankDatas = res.data.body;
            setTimeout(function () {
                countDiv();
                $(".useRankingScroll tr:odd:visible").css("background", "#f9f9f9");
                $(".useRankingScroll tr").hover(function () { $(this).addClass("trhover") }, function () { $(this).removeClass("trhover") })
            })
        }, function (err) {
            console.log(err)
        });
    };
    $("input[name='useRank']").change(function () {
        $scope.appamoutDataShow();
        layer.open({
            type: 1,
            title: "使用排名",
            area: ['80%', '85%'],
            content: $(".useRankingAlert"),
            btn: ["关闭"],
            end: function () {
                $("input[name='useRank']").removeAttr("checked")
            }
        });
    });
});