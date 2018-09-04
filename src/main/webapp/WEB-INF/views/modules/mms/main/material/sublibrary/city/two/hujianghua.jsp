<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<link href="${ctxStatic}/css/rxui1.0.2.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctxStatic}/css/page-library.css" />
<link href="${ctxStatic}/css/mend-msg.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctxStatic}/css/page-threecolumn.css" />
<link href="${ctxStatic}/css/page-childlibrary.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctxStatic}/css/page-pbank.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/page-itemed.css" />
<link rel="stylesheet" href="${ctxStatic}/css/fm.tagator.jquery.css" />
<link rel="stylesheet" href="${ctxStatic}/css/page-material.css" />
<link href="${ctxStatic}/css/temporary.css" rel="stylesheet" />
<script src="${ctxStatic}/js/angular.min.v1.5.js"></script>
<style>
    html, body {
        height: auto;
    }

    .treefixed {
        width: 172px;
        position: absolute;
        left: 0;
        top: 28px;
        background: #fff;
        border: 1px solid #ccc;
        height: auto;
        min-height: 28px;
        max-height: 200px;
        overflow-y: auto;
        z-index: 10;
    }

    .treefixed li {
        height: 28px;
        line-height: 28px;
        padding: 0 10px;
    }

    .treefixed li:hover {
        background: #f1f1f1;
    }

    input[disabled] {
        background: #eee;
    }
</style>
<div  ng-app="addApp" ng-controller="addController" ng-cloak>
    <ul class="clearfix uiTab9 j_uiTab9">
        <li class="uiTab9-active">新增</li>
        <li>补全标准</li>
    </ul>
    <div class="pr10">
    <div class="analyItem">
        <p class="analyItemTit tx-center">科目树</p>
        <div class="analyItemCon">
            <div class="fl col-md-6 relative">
                <input type="text" maxlength="10" class="width180 proving provingOne" id="treeOneInp" ng-model="treeOneinpVal" placeholder="一级分类" ng-keyup="Addtree($event,1)" ng-focus="Addtree($event,2)" data-treeID="{{treeOneID}}">
                <ul class="treeOneDiv treefixed hide">
                    <li ng-repeat="TreeOneData in TreeOneDatas | limitTo: 10 track by $index" ng-click="evaluate(TreeOneData.treeName,TreeOneData.treeID,1)">{{TreeOneData.treeName}}</li>
                </ul>
            </div>
            <div class="fl col-md-6 relative">
                <input type="text" maxlength="10" class="width180 proving provingOne" id="treeTwoInp" ng-model="treeTwoinpVal" disabled="disabled" placeholder="二级分类" ng-keyup="AddTwotree($event)" ng-focus="AddTwotree($event,2)" data-treeID="{{treeTwoID}}">
                <ul class="treeTwoDiv treefixed hide">

                    <li ng-repeat="TreeTwoData in TreeTwoDatas | limitTo: 10 track by $index" ng-click="evaluate(TreeTwoData.treeName,TreeTwoData.treeID,2)">{{TreeTwoData.treeName}}</li>
                </ul>
            </div>
            <div class="fl col-md-6 mt5 relative">
                <input type="text" maxlength="10" class="width180 proving provingOne" placeholder="材料名称" id="treeThreeInp" disabled="disabled" ng-model="treeThreeinpVal" ng-keyup="AddThreetree($event,1)" ng-focus="AddThreetree($event,2)" data-treeID="{{treeThreeID}}">
                <ul class="treeThreeDiv treefixed hide">

                    <li ng-repeat="TreeThreeData in TreeThreeDatas | limitTo: 10 track by $index" ng-click="evaluate(TreeThreeData.treeName,TreeThreeData.treeID,3)">{{TreeThreeData.treeName}}</li>
                </ul>
            </div>
            <div class="fl col-md-6 mt5 relative">
                <input type="text" class="width180 proving " placeholder="材料规格" id="treeFourInp" disabled="disabled" ng-model="treeFourinpVal" ng-focus="AddFourtree()" ng-blur="verification_tree_exist()">

            </div>
        </div>
    </div>
    <div class="analyItem">
        <p class="analyItemTit tx-center">基础</p>
        <div class="analyItemCon clearfix">
            <p class="col-md-6">
                <select class="width100 proving" id="UnitSelect">
                    <option value="0">选择单位</option>
                    <option value="{{$index+1}}" data-unitID="{{Unit.unitID}}" ng-repeat="Unit in CityData.unitArray track by $index">{{Unit.unitName}}</option>
                </select>
            </p>
            <p class="col-md-6">
                <select class="width100 proving" id="classify">
                    <option value="0">选择成品分类</option>
                    <option value="1">成品类</option>
                    <option value="2">非成品类</option>
                </select>
            </p>
            <p class="fl col-md-12">
                <span class="cLightGray pr8">档次</span>
                <label class="uiRadio12"><input type="checkbox" name="A" class="uiRadio12-input level" value="1">A档</label>
                <label class="uiRadio12 ml10"><input type="checkbox" name="B" class="uiRadio12-input level" value="2">B档</label>
                <label class="uiRadio12 ml10"><input type="checkbox" name="C" class="uiRadio12-input level" value="4">C档</label>
            </p>
        </div>
    </div>
    <div class="analyItem">
        <p class="analyItemTit tx-center">地区</p>
        <div class="analyItemCon analyItemCon2">
            <p class="fl col-md-3" ng-repeat="City in CityData.cityArray track by $index">
                <label class="uiRadio12"><input type="checkbox" class="uiRadio12-input" data-cityID="{{City.cityID}}" title="{{City.cityName}}">{{City.cityName | cutout: 6:0: '...'}}</label>
            </p>
        </div>
    </div>
    <div class="tx-center pt20 pb20">
        <input type="button" value="提交" class="uiBtn-blue uiBtn-small " id="submitButton">
        <input type="button" value="取消" class="uiBtn-gray uiBtn-small " onclick="closeAddStandardIframe()" id="btnAddStandardCancel">
    </div>
</div>
</div>
<script src="${ctxStatic}/js/jquery-1.11.3.min.js"></script>
<script src="${ctxStatic}/js/rxuedv2.0.min.js"></script>
<script>

    // 取消:新增标准.1.隐藏新增标准页面;2.展示点击新增标准加号时隐藏的二段数据.
    function  closeAddStandardIframe() {
        var treeOneInp = $("#treeOneInp").val();
        if (treeOneInp != "")
        {
            if (!window.confirm("你新增的标准还未提交,是否退出?")) return;
        }
        $(".tc-center-mat", parent.document).show();
        $(".tc-center-new", parent.document).hide();
    }

    function countHeight() {
        var oheight = $("body").height();
        $("#divMaterialDetailIframe", parent.document).height(oheight);
        $(window).resize(function () {
            var oheight = $("body").height();
            $("#divMaterialDetailIframe", parent.document).height(oheight);
        });
    }
    $(function () {
        countHeight();
    });

    //拿数据库中的值和输入的值作比较来判断输入框中的值是否存在
    function compare(inputValue, obj) {
        if (obj == '' || obj == null || obj == undefined)
        {
            //$(".treefixed").hide();
            return false;
        }

        for (var i = 0; i < obj.length; i++) {
            if ($(inputValue).val() == obj[i].treeName) {
                $(inputValue).attr("data-treeid", obj[i].treeID);
                return true;
            }
        }


    }
    //声明变量判断分类是新加的还是数据库中已经存在的，新加的需要传入后台，已经存在的不需要往后台传
    var IsNotreeOne = 0;
    var IsNotreeTwo = 0;
    var IsNotreeThree = 0;
    addApp = angular.module('addApp', []);
    addApp.controller("addController", function ($scope, $http) {
        $scope.treeOneinpVal = "";
        //科目树分类赋值
        $scope.evaluate = function (treeName, treeID, type) {
            if (type == 1) {
                $scope.treeOneinpVal = treeName;
                $scope.treeOneID = treeID;
                $(".treeOneDiv").hide();
                IsNotreeOne = 1;
                //$(".treeOneDiv").hide();
                //一级分类选择完成后，光标自动定位到二级分类
                setTimeout(function () {
                    $("#treeTwoInp").removeAttr("disabled");
                    $("#treeTwoInp").focus();
                    console.log($scope.treeOneinpVal);
                })
            }
            else if (type == 2) {
                $scope.treeTwoinpVal = treeName;
                $scope.treeTwoID = treeID;
                $(".treeTwoDiv").hide();
                IsNotreeTwo = 1;
                // $(".treeTwoDiv").hide();
                //二级分类选择完成后，光标自动定位到三级分类
                setTimeout(function () {
                    $("#treeThreeInp").removeAttr("disabled");
                    $("#treeThreeInp").focus();
                })
            }
            else if (type == 3) {
                $scope.treeThreeinpVal = treeName;
                $scope.treeThreeID = treeID;
                $(".treeThreeDiv").hide();
                IsNotreeThree = 1;
                //二级分类选择完成后，光标自动定位到三级分类
                setTimeout(function () {
                    $("#treeFourInp").removeAttr("disabled");
                    $("#treeFourInp").focus();
                })
                //$(".treeThreeDiv").hide();
            }


        }

        //展示地区
        $scope.SelectCity = function () {
            $http({
                method: 'GET',
                url: "${basePath}/sublibrary-api/city_newly_increased"
                //params: { topTab, searchKey, userNo, bottomTab }
            }).then(function (res) {
                console.log('地区'+res)
                $scope.CityData = res.data.body;
                setTimeout(function () {
                    countHeight();
                });
            }, function (err) {
                console.log(err)
            })
        };
        //科目树
        $scope.Addtree = function ($event, keytype) {
            // alert($event.keyCode);
            if ($event.keyCode != undefined && ($event.keyCode == 17 || $event.keyCode == 18 || $event.keyCode == 16 || $event.keyCode == 9)) {
                return false;
            }
            $("#treeOneInp").attr("data-treeid", "");
            var obj = {};
            obj.treeLevel = 1;
            obj.treeName = $("#treeOneInp").val();
            $http({
                method: 'GET',
                url: "${basePath}/sublibrary-api/city_newly_increased/correlation_echo",
                params: obj
            }).then(function (res) {
                $scope.TreeOneDatas = res.data.body;

                if ($scope.TreeOneDatas != null && res.data.body.length > 0) {
                    $(".treeOneDiv").show();
                    if (compare("#treeOneInp", $scope.TreeOneDatas)) {
                        //判断分类是新加的还是数据库中已经存在的，新加的点击提交按钮时需要传入后台，已经存在的不需要往后台传,1存在，0不存在
                        IsNotreeOne = 1;
                    } else {
                        IsNotreeOne = 0;
                    }
                } else {
                    IsNotreeOne = 0;
                    $(".treeOneDiv").hide();
                }
                //只有输入框值改变的时候才清空后面的输入框的，1清空
                if (keytype == 1)
                {
                    //清空二级菜单输入框的值，并隐藏二级菜单的div
                    $scope.treeTwoinpVal = "";
                    $(".treeTwoDiv").hide();
                    //清空三级菜单输入框的值，并隐藏二级菜单的div
                    $scope.treeThreeinpVal = "";
                    $(".treeThreeDiv").hide();
                    //清空四级菜单输入框的值
                    $scope.treeFourinpVal = "";
                    //判断一级分类是否输入，只有一级分类输入了二级分类才能填写
                    if ($("#treeOneInp").val() != "") {
                        $("#treeTwoInp").removeAttr("disabled");
                    } else {
                        $("#treeTwoInp").attr("disabled", "disabled");
                        $("#treeThreeInp").attr("disabled", "disabled");
                        $("#treeFourInp").attr("disabled", "disabled");
                    }

                }


            }, function (err) {
                console.log(err)
            })
        };
        //点击页面其他位置隐藏科目树分类菜单
        $(document).click(function (e) {
            if (e.target.className.indexOf('proving') == -1) {
                $(".treefixed").hide();
                //点击别的地方时，如果输入框内没有值是，下级input框置灰
                $(".provingOne").each(function (i) {
                    if ($(this).val().trim() == "") {
                        $(".provingOne").eq(i + 1).attr("disabled", "disabled");
                    }
                })
            }
        });
        $scope.AddTwotree = function ($event,keytype) {
            if ($event.keyCode != undefined && ($event.keyCode == 17 || $event.keyCode == 18 || $event.keyCode == 16 || $event.keyCode == 9)) {
                return false;
            }
            $("#treeTwoInp").attr("data-treeid", "");
            $(".treeOneDiv").hide();
            var obj = {};
            obj.treeParentID = $("#treeOneInp").attr("data-treeid");
            obj.treeLevel = 2;
            obj.treeName = $("#treeTwoInp").val();
            $http({
                method: 'GET',
                url: "${basePath}/sublibrary-api/city_newly_increased/correlation_echo",
                params: obj
            }).then(function (res) {
                $scope.TreeTwoDatas = res.data.body;
                if ($scope.TreeTwoDatas != null && res.data.body.length > 0) {
                    $(".treeTwoDiv").show();
                    if (compare("#treeTwoInp", $scope.TreeTwoDatas)) {
                        //判断分类是新加的还是数据库中已经存在的，新加的点击提交按钮时需要传入后台，已经存在的不需要往后台传,1存在，0不存在
                        IsNotreeTwo = 1;
                    } else {
                        IsNotreeTwo = 0;
                    }
                } else {
                    IsNotreeTwo = 0;
                    $(".treeTwoDiv").hide();
                }
                //只有输入框值改变的时候才清空后面的输入框的，1清空
                if (keytype == 1)
                {
                    //清空三级菜单输入框的值，并隐藏二级菜单的div
                    $scope.treeThreeinpVal = "";
                    $(".treeThreeDiv").hide();
                    //清空四级菜单输入框的值
                    $scope.treeFourinpVal = "";
                    //判断二级分类是否输入，只有二级分类输入了三级分类才能填写
                    if ($("#treeTwoInp").val() != "") {
                        $("#treeThreeInp").removeAttr("disabled");
                    } else {
                        $("#treeThreeInp").attr("disabled", "disabled");
                        $("#treeFourInp").attr("disabled", "disabled");
                    }
                }




            }, function (err) {
                console.log(err)
            })
        };
        $scope.AddThreetree = function ($event, keytype) {
            if ($event.keyCode != undefined && ($event.keyCode == 17 || $event.keyCode == 18 || $event.keyCode == 16 || $event.keyCode == 9)) {
                return false;
            }
            $("#treeThreeInp").attr("data-treeid", "");
            $(".treeTwoDiv").hide();
            var obj = {};
            obj.treeParentID = $("#treeTwoInp").attr("data-treeid");
            obj.treeLevel = 3;
            obj.treeName = $("#treeThreeInp").val();
            $http({
                method: 'GET',
                url: "${basePath}/sublibrary-api/city_newly_increased/correlation_echo",
                params: obj
            }).then(function (res) {
                $scope.TreeThreeDatas = res.data.body;
                if ($scope.TreeThreeDatas != null && res.data.body.length > 0) {
                    $(".treeThreeDiv").show();
                    if (compare("#treeThreeInp", $scope.TreeThreeDatas)) {
                        //判断分类是新加的还是数据库中已经存在的，新加的点击提交按钮时需要传入后台，已经存在的不需要往后台传,1存在，0不存在
                        IsNotreeThree = 1;
                    } else {
                        IsNotreeThree = 0;
                    }
                } else {
                    IsNotreeThree = 0;
                    $(".treeThreeDiv").hide();
                }
                //只有输入框值改变的时候才清空后面的输入框的，1清空
                if (keytype == 1)
                {
                    //清空四级菜单输入框的值
                    $scope.treeFourinpVal = "";
                    //判断三级分类是否输入，只有三级分类输入了四级分类才能填写
                    if ($("#treeThreeInp").val() != "") {
                        $("#treeFourInp").removeAttr("disabled");
                    } else {
                        $("#treeFourInp").attr("disabled", "disabled");
                    }
                }



            }, function (err) {
                console.log(err)
            })
        };
        $scope.AddFourtree = function () {
            $(".treeThreeDiv").hide();
        }
        //验证数据是否已存在
        $scope.verification_tree_exist = function () {

            var obj = {};
            obj.parentID = $("#treeThreeInp").attr("data-treeid");
            obj.treeLevel =4;
            obj.treeName = window.encodeURI($("#treeFourInp").val());
            $http({
                method: 'GET',
                url: "${basePath}/sublibrary-api/verification_tree_exist",
                params: obj
            }).then(function (res) {
                if(res.data.body>0)
                {
                    alert("数据已经存在！");
                    $("#submitButton").addClass("submitButtonClick");
                } else {
                    $("#submitButton").removeClass("submitButtonClick");
                }

            }, function (err) {
                console.log(err)
            })
        }
        $scope.SelectCity();
        //点击提交
        $("#submitButton").click(function () {
            //判断是否存在重复提交class
            if ($(this).hasClass("submitButtonClick"))
            { return; }
            $(this).addClass("submitButtonClick");
            var obj = {};
            var treeOneInp = $("#treeOneInp").val();
            var treeOneId = $("#treeOneInp").attr("data-treeid");
            var treeTwoInp = $("#treeTwoInp").val();
            var treeTwoId = $("#treeTwoInp").attr("data-treeid");
            var treeThreeInp = $("#treeThreeInp").val();
            var treeThreeId = $("#treeThreeInp").attr("data-treeid");
            var treeFourInp = $("#treeFourInp").val();
            var unitID = $("#UnitSelect option:selected").attr("data-unitID");
            var unitName = $("#UnitSelect option:selected").text();
            var classify = $("#classify").val();
            var levelCount = 0;
            for (var i = 0; i < $(".level:checked").length; i++) {
                levelCount += Number($(".level:checked").eq(i).val());
            }
            var cityIds = '';
            $('.analyItemCon2').find('input:checked').each(function (i, ele) {
                cityIds += $(ele).attr('data-cityID') + ",";
            });
            cityIds = cityIds.substring(0, cityIds.length - 1);
            var flag = 0;
            $(".proving").each(function (i) {
                if ($(this).val() == "") {
                    flag = 1;
                    return false;
                } else if ($(this).val() == 0) {
                    flag = 2;
                    return false;
                }
            });
            if (flag == 1) {
                alert("请输入科目");
                $("#submitButton").removeClass("submitButtonClick");
                return false;
            } else if (flag == 2) {
                alert("请选择单位");
                $("#submitButton").removeClass("submitButtonClick");
                return false;
            }
            if ($(".level:checked").length <= 0) {
                alert("请选择档次");
                $("#submitButton").removeClass("submitButtonClick");
                return false;
            }
            if ($(".analyItemCon2 input:checked").length <= 0) {
                alert("请选择地区");
                $("#submitButton").removeClass("submitButtonClick");
                return false;
            }
            if (IsNotreeOne <= 0)//一级分类为新加入的
            {
                obj.treeOne = {};
                obj.treeOne.treeName = treeOneInp;
                obj.treeTwo = {};
                obj.treeTwo.treeName = treeTwoInp;
                obj.treeThree = {};
                obj.treeThree.treeName = treeThreeInp;
                obj.treeFour = {};
                obj.treeFour.treeName = treeFourInp;
            }
            else if (IsNotreeTwo <= 0)//一级分类数据库中有二级分类为新加入的
            {
                obj.treeTwo = {};
                obj.treeTwo.treeName = treeTwoInp;
                obj.treeTwo.treeParentid = treeOneId;
                obj.treeThree = {};
                obj.treeThree.treeName = treeThreeInp;
                obj.treeFour = {};
                obj.treeFour.treeName = treeFourInp;
            }
            else if (IsNotreeThree <= 0)//一级分类二级分类数据库中有，三级分类为新加入的
            {
                obj.treeThree = {};
                obj.treeThree.treeName = treeThreeInp;
                obj.treeThree.treeParentid = treeTwoId;
                obj.treeFour = {};
                obj.treeFour.treeName = treeFourInp;
            }
            else {
                obj.treeFour = {};
                obj.treeFour.treeName = treeFourInp;
                obj.treeFour.treeParentid = treeThreeId;

            }
            obj.unitID = unitID;
            obj.unitName = unitName;
            obj.classify = classify;
            obj.levelCount = levelCount;
            obj.cityIds = cityIds;
            console.log(obj)
            $.ajax({ //
                url: "${basePath}/sublibrary-api/city_newly_increased/save",
                type: "post",
                contentType: 'application/json',
                dataType: "json",
                data: JSON.stringify(obj),
                success: function (res) {

                    //console.log(res.statusCode);
                    alert("数据提交成功");
                    //清空重复提交class
                    $("#submitButton").removeClass("submitButtonClick");
                    //提交完成后，清空之前绑定的数据
                    $scope.treeOneinpVal = "";
                    $scope.treeOneID = "";
                    $scope.treeTwoinpVal = "";
                    $scope.treeTwoID = "";
                    $scope.treeThreeinpVal = "";
                    $scope.treeThreeID = "";
                    $scope.treeFourinpVal = "";
                    $scope.treeFourID = "";
                    //提交完成后清空内容
                    $(".proving").each(function (i) {
                        if ($(this).find("option").length <= 0) {
                            $(this).val("");
                            if (i != 0) {
                                $(this).attr("disabled", "disabled");
                            }
                        } else {
                            $(this).val("0");
                        }

                    });
                    $(".level").removeAttr("checked");
                    $(".analyItemCon2 input").removeAttr("checked");
                    IsNotreeOne = 0;
                    IsNotreeTwo = 0;
                    IsNotreeThree = 0;
                    //提交完成后刷新父级页面
                    parent.location.reload();
                },
                error: function (err) {
                    console.log(err);
                }
            });



        });
    });
    //自定义过滤器,截取字符串(默认显示后四位) m > 0: 截取后n位, n > 0: 截取前n位, e: 超出显示什么符号
    addApp.filter("cutout", function () {
        return function (str, m, n, e) {
            var n = n || 0;
            var m = m || 0;
            var e = e || '';
            //string.substr(start[, length])
            if (n > 0 && m <= 0) {
                if (n >= str.length) {
                    str = str
                } else {
                    str = str.substr(-n, n) + e
                }
            } else if (n <= 0 && m > 0) {
                if (m >= str.length) {
                    str = str
                } else {
                    str = str.substr(0, m) + e
                }
            } else {
                str = str.substr(0, m) + e + str.substr(-n, n);
            }
            return str;
        }


    });

</script>