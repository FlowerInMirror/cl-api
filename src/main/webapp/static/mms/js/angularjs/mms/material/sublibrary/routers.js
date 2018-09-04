//===============UI Router(用户界面 路由)开始
// 定义路由状态 $stateProvider 状态提供者对象,$urlRouterProvider URL路由提供者对象.
myApp.config(function($stateProvider,$urlRouterProvider){
    // 指定请求不存在,404页面. '/'当前应用的根路径
    $urlRouterProvider.otherwise("/");

    // ==============定义路由状态 开始

    // 子库地区 二段 A档/B档/C档/成本/应用 路由状态
    // 子库地区板块(一段列表/二段全部选项) 路由
    $stateProvider.state("sublibrary_city",{
        url:'/sublibrary_city',
        views:{
            // 一段列表(加载地区材料列表)
            "one_list":{
                templateUrl: basePath + "/public-web/sublibrary/city_one_list",
                // ==============预加载 开始
                resolve: {
                    oneList:function($http){
                        var promise = $http({
                            method: 'GET',
                            url: basePath + "/sublibrary-api/city_one_section",
                            params: { cityID: $("#hidCityID").val() }
                        });
                        // 处理成功的响应
                        promise.success(function(data,status,config,headers){
                            // 设置四级科目ID到隐藏域中.
                            $("#hidTreeID").val(data.body[0].treeFourID);
                        });
                        return promise;
                    }
                },
                controller:function($scope,$http,oneList){
                    $scope.sublibrariesALists = oneList.data.body;
                    // 一点加载默认选中第一个元素
                    setTimeout(function () {
                        $("#tabDataToMaterial tr").eq(0).click();
                        $("#divStateStatistics p").eq(0).click();
                    })

                }
            },
            // 二段详情
            "two_all":{
                templateUrl: basePath + "/public-web/sublibrary/city_two_all",
                resolve: {
                    twoAll:function(){

                    }
                },
                controller:function($scope,$http,twoAll){
                    $scope.cityTwoState($("#hidTreeID").val());// 绑定子库二段状态接口,到作用域.
                    setTimeout(function(){// 加载样式
                        $("#divMaterialDetail").removeClass("hide");// 展示二段详情
                        $("#divMaterialDetail").prop("style","display: block;");// 设置启用属性值
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
                    })
                }
            }
        }
    }).state("sublibrary_city.two_level_a",{// 子库地区 二段 A档次 路由状态
        url:'/sublibrary_city.two_level_a',
        views:{
            "two_level_a":{
                templateUrl: basePath + "/public-web/sublibrary/city_two_level",
                controller:function($scope,$http,$state){
                    // 加载A档所需数据
                    $scope.cityTwoLevelA($("#hidTreeID").val());
                    setTimeout(function() {// 加载样式
                        $("#divMaterialDetailToA").prop("style","display: block;").siblings().prop("style","display: none;");
                        countLeft();
                        countMiddle();
                        countRight();
                    })
                }
            }
        }
    }).state("sublibrary_city.two_level_b",{// 子库地区 二段 B档次 路由状态
        url:'/sublibrary_city.two_level_b',
        views:{
            "two_level_b":{
                templateUrl: basePath + "/public-web/sublibrary/city_two_level",
                controller:function($scope,$http,$state){
                    // 加载A档所需数据
                    $scope.cityTwoLevelB($("#hidTreeID").val());
                    setTimeout(function() {
                        // 获取当前选中项元素,设置元素的 display 属性,为 block 显示状态 ,其他兄弟节点, 为 none 不显示.
                        $("#divMaterialDetailToB").prop("style","display: block;").siblings().prop("style","display: none;");
                        countLeft();
                        countMiddle();
                        countRight();
                    })
                }
            }
        }
    }).state("sublibrary_city.two_level_c",{// 子库地区 二段 C档次 路由状态
        url:'/sublibrary_city.two_level_c',
        views:{
            "two_level_c":{
                templateUrl: basePath + "/public-web/sublibrary/city_two_level",
                controller:function($scope,$http,$state){
                    // 加载A档所需数据
                    $scope.cityTwoLevelC($("#hidTreeID").val());
                    setTimeout(function() {
                        // 获取当前选中项元素,设置元素的 display 属性,为 block 显示状态 ,其他兄弟节点, 为 none 不显示.
                        $("#divMaterialDetailToC").prop("style","display: block;").siblings().prop("style","display: none;");
                        countLeft();
                        countMiddle();
                        countRight();
                    })
                }
            }
        }
    }).state("sublibrary_city.two_cost",{// 子库地区 二段 '成本' 路由状态
        url:'/sublibrary_city.two_cost',
        views:{
            "two_cost":{
                templateUrl: basePath + "/public-web/sublibrary/city_two_cost",
                controller:function($scope,$http,$state){
                    // 加载A档所需数据
                    $scope.cityTwoCost($("#hidTreeID").val());
                    setTimeout(function() {
                        // 获取当前选中项元素,设置元素的 display 属性,为 block 显示状态 ,其他兄弟节点, 为 none 不显示.
                        $("#divMaterialDetailToQuotation").prop("style","display: block;").siblings().prop("style","display: none;");
                        countLeft();
                        countMiddle();
                        countRight();
                    })
                }
            }
        }
    }).state("sublibrary_city.two_app",{// 子库地区 二段 '应用' 路由状态
        url:'/sublibrary_city.two_app',
        views:{
            "two_app":{
                templateUrl: basePath + "/public-web/sublibrary/city_two_app",
                controller:function($scope,$http,$state){
                    // 加载A档所需数据
                    $scope.cityTwoApp($("#hidTreeID").val());
                    setTimeout(function() {
                        // 获取当前选中项元素,设置元素的 display 属性,为 block 显示状态 ,其他兄弟节点, 为 none 不显示.
                        $("#divMaterialDetailToAPP").prop("style","display: block;").siblings().prop("style","display: none;");
                        countLeft();
                        countMiddle();
                        countRight();
                    })
                }
            }
        }
    })
    // 子库地区 > 状态 > 处理三段 路由状态
    .state("sublibrary_city.status_three_handle",{
        url:'/sublibrary_city.status_three_handle',
        views:{
            "status_three_handle":{
                templateUrl: basePath + '/public-web/sublibrary/city_three_status/handle',
                controller:function($scope,$http,$state,$injector){
                    $injector.get('$templateCache').removeAll();
                    // 重新加载状态内容到隐藏域中
                    var treeFourID = $("#hidTreeID").val();
                    $scope.cityTwoState(treeFourID);
                    alert(treeFourID)
                    setTimeout(function() {
                        $("#three-section").show();
                        // 加载样式开始
                        $(".alertpaper").hide();
                        $(".divMaterialLoadAlert").show().animate({
                            left: 0
                        }, 200,function(){
                            countLeft();
                            countMiddle();
                            countRight();
                        });
                        countLeft();
                        countMiddle();
                        countRight();
                    })
                }
            }
        },
        // 每次单击调用控制器,不缓存
        cache: false
    })
});