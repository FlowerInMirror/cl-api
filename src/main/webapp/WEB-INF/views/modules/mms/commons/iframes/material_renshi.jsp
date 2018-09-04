<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>集团材料1</title>
    <link rel="stylesheet" href="${ctxStatic}/css/rxui1.0.2.min.css?${verStatic}" />
    <link rel="stylesheet" href="${ctxStatic}/css/frame.min.css?${verStatic}" />
    <link rel="stylesheet" href="${ctxStatic}/js/skin/layer.css?${verStatic}" type="text/css"/>
    <!--三栏布局样式表 -->
    <link href="${ctxStatic}/css/page-threecolumn.css?${verStatic}" rel="stylesheet" />
    <!--针对此页面单独的样式-->
    <link href="${ctxStatic}/js/skin/layer.css?${verStatic}" rel="stylesheet" />
    <link href="${ctxStatic}/css/page-materialist.css?${verStatic}" rel="stylesheet"/>
    <link href="${ctxStatic}/Scripts/My97DatePicker/skin/WdatePicker.css?${verStatic}" rel="stylesheet" />
    <!-- 在此引入AngularJS 使 ng-cloak 生效 -->
    <script src="${ctxStatic}/js/angular.js?${verStatic}" type="text/javascript"></script>
    <style>
        .maul{padding-left:10px;}
        .mafirst, .matwo {
            float: left;
            margin-right: 10px;
            margin-bottom: 10px;
            width: 100px;
            height: 30px !important;
            text-align: center;
            cursor: pointer;
            border: 1px solid #ccc;
            background: #fff;
            padding: 0 8px;
            height: 28px;
            line-height: 28px;
            letter-spacing: 1px;
            border-radius: 3px;
        }
         .mafirst:hover, .matwo:hover{
                background: #09f;
                border: 1px solid #09f;
                color: #fff;
            }
        .active {
            background: #09f;
            border: 1px solid #09f;
            color: #fff;
        }
    </style>

</head>

<body class="logo-c000000 bar-cffffff nav-black nav-vertical" data-class="nav-vertical" ng-app="myApp" ng-cloak >

    <div id="main_box" ng-controller="listCtrl"  >

        <div class="matile">
            <div class="report_listCon" id="report_listCon">
                <p class="time_op" style="padding: 50px 0 10px 12px;">
                    <ul class="maul">
                        <li class="mafirst" ng-click="currWeek()">
                            本周数据
                        </li>
                        <li class="matwo" ng-click="weekTwo()">
                            前两周数据
                        </li>
                        <li>
                            <input ng-change="teChange()" ng-model="myVal" onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(){$(this).trigger('change')}})" id="monthTime" class="width100  Wdate" type="text" placeholder="月数据" style="width: 100px;height: 30px; text-align: center;cursor:pointer;">
                        </li>
                    </ul>
                </p>
                <div class="reporttabCon mtf1 T_started" style="padding:0 10px;">
                    <table class="uiTable">
                        <tbody>
                            <tr>
                                <th width="10%">姓名</th>
                                <th width="12.5%">电话</th>
                                <th width="10%">地区</th>
                                <th width="12.5%">标题</th>
                                <th width="17.5%">处理描述</th>
                                <th width="12.5%">处理日期</th>
                                <th width="12.5%">汇总</th>
                                <th>执行状态</th>

                            </tr>

                        </tbody>
                    </table>
                    <div class="tab_list">
                        <table class="uiTable" id="list">
                            <tr class="tr_changerColor" ng-repeat="lftData in lftDatas track by $index" data-pid="{{lftData.pid}}" data-pmainid="{{lftData.pmainid}}">
                                <td width="10%">{{lftData.userName}}</td>
                                <td width="12.5%">{{lftData.mobile}}</td>
                                <td width="10%">{{lftData.inductionArea}}</td>
                                <td width="12.5%">{{lftData.ptitle}}</td>
                                <td width="17.5%">{{lftData.premark}}</td>
                                <td width="12.5%">{{lftData.pcreatetime |afterN:-10 }}</td>
                                <td width="12.5%">{{lftData.psummary}}</td>
                                <td id="lastTd" class="{{list_color(lftData.peExecuteState)}}" data-peExecuteState="{{lftData.peExecuteState}}" title="{{lftData.peExecuteState}}" ng-click="list_click(lftData.pid,lftData.peExecuteState)">{{list_state(lftData.peExecuteState)}}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!--弹出层结束3.28-->
        <!--3.31-->
        <div class="checkMatAlert hide pa10">
            <p class="checkP">执行人姓名:{{three_list.poeUsername}}</p>
            <p class="checkP">执行备注:{{three_list.peRemark}}</p>
            <p class="checkP" ng-show="isEven(three_list.peType)">发起罚分:{{three_list.peFirstscore}}</p>
            <p class="checkP" ng-show="isEven(three_list.peType)">要求整改时间 :{{three_list.peClaimtime}}</p>
            <p class="checkP">执行罚款:{{three_list.peLastscore+'元'}}</p>
            <p class="checkP">确认执行日期 :{{three_list.peDefinetime}}</p>
            <div class="checkMatAlertCon">
                <table class="uiTable" id="tabDataToCheckMaterialDY"></table>
            </div>
        </div>
        <!--3.31-->
    </div>
    <script src="${ctxStatic}/js/jquery-1.11.3.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/layer.js?${verStatic}"></script>
    <script src="${ctxStatic}/js/rxuedv2.0.min.js?${verStatic}" type="text/javascript"></script>
    <script src="${ctxStatic}/js/frame.min.js?${verStatic}" type="text/javascript"></script>
    <!--引入angular-->
    <script src="${ctxStatic}/js/layer.js?${verStatic}"></script>
    <script src="${ctxStatic}/Scripts/My97DatePicker/WdatePicker.js?${verStatic}"></script>

</body>

</html>
<script>
    //表格高度计算
    function heights() {
        var theight = $("body").height() - $("#topnav").height() - $(".time_op").outerHeight() - $(".tab").outerHeight() -79;
        $(".tab_list").height(theight).slimScroll({
            height: theight,
            borderRadius: "0px"
        });
        $(window).resize(function () {
            var theight = $("body").height() - $("#topnav").height() - $(".time_op").outerHeight() - $(".tab").outerHeight() -79;
            $(".tab_list ").height(theight).slimScroll({
                height: theight,
                borderRadius: "0px"
            });
            $(".tab_list ").parents(".slimScrollDiv").eq(0).height(theight)
        });
    }
    var app = angular.module('myApp', []);
    //截取长度
    app.filter('afterN', function () {
        return function (str, n, e) {
            var n = n || 4;
            var e = e || '';
            //string.substr(start[, length])
            if (n > 0) {
                str = str.substr(-n, n) + e
            } else {
                if (-n > str.length) {
                    str = str
                } else {
                    str = str.substr(0, -n) + e
                }
            }
            return str;
        }
    })
    app.controller('listCtrl', ['$scope', '$http', function ($scope, $http) {
        //执行状态判断
        $scope.list_state = function (val) {

            if (val == "0") {
                return "无执行"
            } else if (val == "1") {
                return "执行中"
            } else if (val == "2") {
                return "已执行"
            } else {
                return "无执行"
            }

        }
        //执行状态颜色
        $scope.list_color = function (val) {
            var color_change = '';
            switch (val) {
                case 0:
                    color_change = 'cSame';
                    break;
                case 1:
                    color_change = 'cBlue';
                    break;
                case 2:
                    color_change = 'cBlue';
                    break;
            }
            return color_change;
        }
        //执行状态点击事件
        $scope.list_click = function (id, state) {
            if (state == "1" || state == "2") {
                //$(".threeList").show();
                layer.open({
                    type: 1,
                    title: "人事详情",
                    area: ['30%', '35%'],
                    content: $(".checkMatAlert"),
                });
                $(".checkP").css({ "font-size": "12px", "margin-top": "5px" })
            }
            $http({
                method: 'GET',
                url:  "${basePath}/pub_processing-api/processing_execute_three_section/platform",
                params: {
                    pePid: id
                }
            }).then(function success(res) {
                //console.log(res.data.body[0])
                $scope.myShow = true;
                $scope.three_list = res.data.body[0];
                setTimeout(function () {
                    heights();
                })
                $scope.isEven = function (type) {
                    if (type > 1) {
                        return true;
                    } else {
                        return false;
                    }

                }

            }, function error(res) { })

        }
        //时间筛选
        $scope.teChange = function () {
            $('.mafirst').removeClass('active');
            $('.matwo').removeClass('active');
            var timer = new Date();
            var year = timer.getFullYear();
            var mon = timer.getMonth() + 1;
            var last = new Date(year, mon, 0);
            if (mon < '10') {
                mon = '0' + mon;
            } else {
                mon = mon
            }
            var firstDays = year + '-' + mon + '-' + '01';
            var lastDays = year + '-' + mon + '-' + last.getDate();
            var mowt = year + '-' + mon;
            var time = $("#report_listCon .Wdate").val().split('-');
            var day = new Date(time[0], time[1], 0);
            var firstDay = $("#report_listCon .Wdate").val() + '-' + '01';
            var lastDay = $("#report_listCon .Wdate").val() + '-' + day.getDate();
            if ($("#report_listCon .Wdate").val() == '' || $("#report_listCon .Wdate").val() == 'undefined') {
                firstDay = firstDays;
                lastDay = lastDays;

            } else {
                firstDay = firstDay;
                lastDay = lastDay
            }
            $http({
                method: 'GET',
                url:  "${basePath}/pub_processing-api/ptitle_two_section/platform",
                params: {
                    pType: 2,
                    start: firstDay,
                    stop: lastDay
                }
            }).then(function success(res) {
                $scope.lftDatas = res.data.body;
                if ($("#report_listCon .Wdate").val() == '' || $("#report_listCon .Wdate").val() == 'undefined') {
                    $("#report_listCon .Wdate").val(mowt)
                }
                setTimeout(function () {
                    heights();
                })
             
            }, function error(res) {
                alert("请求失败")
            })

        }
     
      //本周数据点击
      $scope.currWeek = function () {
          $('.mafirst').addClass('active').siblings().removeClass('active');
          //一天的毫秒数   
          var millisecond = 1000 * 60 * 60 * 24;
          //获取当前时间   
          var currentDate = new Date();
          //相对于当前日期AddWeekCount个周的日期
          // currentDate = new Date(currentDate.getTime() + (millisecond * 7 * AddWeekCount));
          //返回date是一周中的某一天
          var week = currentDate.getDay();
          var minusDay = week != 0 ? week - 1 : 6;
          //获得当前周的第一天   
          var currentWeekFirstDay = new Date(currentDate.getTime() - (millisecond * minusDay));
          //获得当前周的最后一天
          var currentWeekLastDay = new Date(currentWeekFirstDay.getTime() + (millisecond * 6));
          //获取年月日
          var year = currentWeekFirstDay.getFullYear();
          var mon = currentWeekFirstDay.getMonth() + 1;
          var firstDay = currentWeekFirstDay.getDate();
          var lastDay = currentWeekLastDay.getDate();
          if (mon < '10') {
              mon = '0' + mon;
          } else {
              mon = mon
          }
          if (firstDay < '10') {
              firstDay = '0' + firstDay;
          } else {
              firstDay = firstDay
          }
          if (lastDay < '10') {
              lastDay = '0' + lastDay;
          } else {
              lastDay = lastDay
          }
          var preWeek = year + '-' + mon + '-' + firstDay;
          var aftWeek = year + '-' + mon + '-' + lastDay;
          $http({
              method: 'GET',
              url:  "${basePath}/pub_processing-api/ptitle_two_section/platform",
              params: {
                  pType: 2,
                  start: preWeek,
                  stop: aftWeek
              }

          }).then(function success(res) {
              $scope.lftDatas = res.data.body;
              setTimeout(function () {
                  heights();
              })
          }, function error(res) { })
        }
      $scope.currWeek();
//前两周数据点击
      $scope.weekTwo = function () {
          $('.matwo').addClass('active').siblings().removeClass('active');
          debugger
          //一天的毫秒数   
          var millisecond = 1000 * 60 * 60 * 24;
          //获取当前时间   
          var currentDate = new Date();
          //返回date是一周中的某一天  星期 0 1 2 3 4 5 6
          var week = currentDate.getDay();
          //减去的天数 
          var minusDay = week != 0 ? week - 1 : 6;
          //获得当前周的第一天   
          var currentWeekFirstDay = new Date(currentDate.getTime() - (millisecond * minusDay));
          //获得当前周的最后一天
          var currentWeekLastDay = new Date(currentWeekFirstDay.getTime() + (millisecond * 6));
          //前两周第一天
          var twoWeekFirst = new Date(currentDate.getTime() - (millisecond * (minusDay + 14)));
          //前两周最后一天
          var twoWeekLast = new Date(currentDate.getTime() - (millisecond * (minusDay + 1)));
          //获取年月日
          var yearFirst = twoWeekFirst.getFullYear();
          var monFirst = twoWeekFirst.getMonth() + 1;
          var dayFirst = twoWeekFirst.getDate();

          var yearLast = twoWeekLast.getFullYear();
          var monLast = twoWeekLast.getMonth() + 1;
          var dayLast = twoWeekLast.getDate();
          if (dayFirst < '10') {
              dayFirst = '0' + dayFirst;
          } else {
              dayFirst = dayFirst
          }
          if (dayLast < '10') {
              dayLast = '0' + dayLast;
          } else {
              dayLast = dayLast
          }
          if (monFirst < '10') {
              monFirst = '0' + monFirst;
          } else {
              monFirst = monFirst
          }
          if (monLast < '10') {
              monLast = '0' + monLast;
          } else {
              monLast = monLast
          }
          var preWeek = yearFirst + '-' + monFirst + '-' + dayFirst;
          var aftWeek = yearLast + '-' + monLast + '-' + dayLast;
              $http({
                  method: 'GET',
                  url:  "${basePath}/pub_processing-api/ptitle_two_section/platform",
                  params: {
                      pType: 2,
                      start: preWeek,
                      stop: aftWeek
                  }
              }).then(function success(res) {
                  $scope.lftDatas = res.data.body;
                  setTimeout(function () {
                      heights();
                  })

              }, function error(res) {
                  alert("请求失败")
              })

        
      }

    }])
</script>
<script type="text/javascript">
    $(".content_p .close").click(function () {
        $(".threeList").hide();
    })
    //$(".time_op  .close").click(function () {
    //   // $('#main_box').hide();
    //    debugger
    //    console.log($('.iframe_list'))
    //    $('.iframe_list').hide();

    //})
</script>
