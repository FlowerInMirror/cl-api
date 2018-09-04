<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="OneList" class="relative"  v-cloak>

<%--检索框--%>
<div class="dailyftSearch clearfix hide" style="height:36px;top:1px;">
    <div class="fl uiHas-textIcon">
        <input type="text" class="col-md-12" placeholder="姓名/手机号/公司名称" id="Special_no">
        <i class="uiText-searchIcon bgIcon"></i>
    </div>
</div>

<table class="uiTable jcha5_new">
    <thead>
    <tr>
        <th width="28%">
            <span class="serial_number searchZhuanXiang">
                姓名...
                <i class="bgIcon "></i>
            </span>
        </th>
        <th width="30%">
            <select class="theadselect">
                <option value="" data-type="K">空调</option>
                <option data-type="X">消防</option>
                <option data-type="C" selected>材料</option>
            </select>
        </th>
        <th width="22%">管状</th>
        <th>回访</th>
    </tr>
    </thead>
</table>
<div class="scroll-content">
    <table class="uiTable pointertable" id="tabSpecial">
        <tbody>
        <tr v-for="(OneListData,index) in OneListDatas" :data-userid="OneListData.UserID" :data-usercityid="OneListData.CityID" :data-StoresName="OneListData.StoresName" :data-UserCode="OneListData.UserCode" :data-companyname
="OneListData.CompanyName" :data-userphone="OneListData.UserPhone" :data-username="OneListData.UserName">
            <td width="28%">{{OneListData.UserCode}}</td>
            <td width="30%" v-show="OneListData.UserID!=0" :title="OneListData.CompanyName">{{OneListData.CompanyName | cutout('4','','...')}}</td>
            <td width="30%" v-show="OneListData.UserID==0" class="cRed">未入驻</td>
            <td width="22%" v-show="OneListData.StoresName!=''">待审</td>
            <td width="22%" v-show="OneListData.StoresName==''">--</td>
            <td v-show="OneListData.StoresName!=''" class="last-child">21</td>
            <td v-show="OneListData.StoresName==''" class="last-child">--</td>
        </tr>
        </tbody>
    </table>
</div>
<div class="clearfix tj_bottom jcha2 clearfix tj_bottom  jcha5_new">
    <p class="col-md-3 cGray">{{OneListDatasLength}}</p>
    <p class="col-md-3 cGreen NormalLen">1</p>
    <p class="col-md-3 cOrange">0</p>
    <p class="col-md-3 cRed">0</p>
</div>
</div>
<script>
    <%--VUE2.0 开始--%>
    var OneList=new Vue({
        el: '#OneList',
        data: {
            OneListDatas: [],// 左侧列表数据数组
            OneListDatasLength:0//列表总数据
        },
        filters: {
            cutout: function (str, m, n, e) {
                if(str!=null||str!=undefined){
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

            }
        },
        methods:{
            //左侧列表数据
            OneListDataFn:function(){
                var _this=this;
                var city=$("#hidCityID").val();
                $.ajax({
                    url: gcApiSite + 'CMaterial/GetStoresItems?type=1',
                    type: 'GET',
                    dataType: 'json',
                    data: {city:city},
                    success: function (res) {
                        _this.OneListDatas = res.Body;
                        _this.OneListDatasLength=res.Body.length;
                        if(_this.OneListDatasLength>0)
                        {
                            $("#idZXUserID").val(res.Body[0].UserID);
                            $("#hidStoresName").val(res.Body[0].StoresName);
                            $("#hidUserCode").val(res.Body[0].UserCode);


                            setTimeout(function(){
                                // 默认点击第一个
                                $("#tabSpecial tr:eq(0)").click();
//                                $("#divZhuanXiang").find(".uiTab9 li:eq(0)").click();

                                // 加载当前材料商数量
                                var ototalLen=$("#tabSpecial tr").length;
                                var onoLen=$("#tabSpecial tr[data-userid='0']").length;
                                $(".NormalLen").html(ototalLen-onoLen);
                            },200)
                        }
                    },
                    error: function (err) {alert('操作出错！');}
                });
            }
        },

        created:function(){
            // 页面加载构建数据
            this.OneListDataFn();
        }
    })
</script>