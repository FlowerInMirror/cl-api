<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>专项产品浏览</title>
        <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/browsing/base.css?${verStatic}"/>
        <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/browsing/frame_head_footer.css?${verStatic}"/>
        <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/browsing/public_nav.css?${verStatic}"/>
        <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/browsing/page_detail.css?${verStatic}"/>
        <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/browsing/page_particulars.css?${verStatic}"/>

        <script src="${basePath}/static/mms/js/jquery-1.11.3.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
        <script src="${ctxStatic}/js/vue.js?${verStatic}"></script>

        <script type="text/javascript">var basePath = '${basePath}',ctxStatic='${ctxStatic}',gcApiSite = '${gcApiSite}';</script>
        <style>
            .boxSpProDepictData img{max-width:100%;}
        </style>
    </head>
    <body  >
        <div id="zxProBrowsingVue" v-cloak>
            <!--头部-->
            <div class="mallHead lh26">
                <div class="headCon mallCon clearfix">
                    <div class="headConL fl">
                        <em class="icon_bg icon_bg_1 mr5 dis_block fl mt8"></em>
                        <a href="javascript:" class="fl selCity" id="city"><span id="cityName">北京</span><em class="tran_top fr"></em></a>
                        <div class="selBox_city borderAll">
                            <ul>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(12);" diquid="12">北京</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(19);" diquid="19">天津</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(31);" diquid="31">石家庄</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(30);" diquid="30">济南</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(25);" diquid="25">西安</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(20);" diquid="20">郑州</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(22);" diquid="22">成都</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(21);" diquid="21">重庆</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(18);" diquid="18">武汉</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(16);" diquid="16">合肥</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(15);" diquid="15">南京</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(38);" diquid="38">上海</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(26);" diquid="26">杭州</a></li>
                                <li class="pl10 pr10"><a href="javascript:" class="db_100 borderDashB aLink" onclick="CityLoadProduct(36);" diquid="36">宁波</a></li>
                            </ul>
                        </div>
                        <span class="fl headLine">|</span>
                        <a href="http://c.rxjyzx.com/" class="gotoindex">瑞祥材料首页</a>
                    </div>
                    <div class="headConR fr">
                        <a href="#" class="headLogin fr"><em class="icon_bg"></em>登录</a>
                    </div>
                </div>
            </div>
            <!--搜索-->
            <div class="mallCon mallSeach clearfix">
                <div class="mallSeachLeft fl">
                    <h1 class="logo fl">
                        <img src="${ctxStatic}/images/browsing/logo.jpg?${verStatic}" alt="瑞祥材料">
                    </h1>
                    <span class="mallSeachLine fl">|</span>
                    <span class="fl">瑞祥装饰出品</span>
                </div>
                <div class="mallSeachCenter fl">
                    <div class="fl searchBox">
                        <input type="text" id="SeachWhere" name="SeachWhere" class="form-control" placeholder="地砖 灯 壁纸 开关">
                    </div>
                    <input type="submit" value="搜索" class="fl btn searchSubmit fz16" onclick="SeachWhere();">
                </div>
                <div class="mallSeachRight fr">
                    <span class="mallSeachPhone fl"><em class="icon_bg fl"></em>400-168-5038</span>
                    <a href="javascript:" class="fl goingIn">我要入驻</a>
                </div>

            </div>
            <div class="mallWrap">
                <!--产品详情-->
                <div class="mallCon">
                    <div class="Crumbs">首页 ＞ {{product.treeTwoName}}</div>
                    <div class="mall_detail clearfix">
                        <div class="mall_top_box">
                            <div class="clearfix">
                                <div class="mall_slide_box fl posRa PictureBoxParent">
                                    <%--大图框--%>
                                    <div class="mall_slide_box_con">
                                        <ul class="clearfix mall_slide_box_ul">
                                            <li style="position: absolute; left: 0px; top: 0px;"><img class="db_100 show_img" :src="firstSpdUrl" alt=""></li>
                                        </ul>
                                    </div>
                                    <div class="spec-list">
                                        <a id="spec-forward" href="javascript:;" class="arrow-prev disabled"><i class="sprite-arrow-prev"></i></a>
                                        <a id="spec-backward" href="javascript:;" class="arrow-next"><i class="sprite-arrow-next"></i></a>
                                        <div class="spec-items">
                                            <ul class="spec-items-ul clearfix">
                                                <%--小图集遍历--%>
                                                <li v-for="(picture,index) in pictures" :class="{'img-hover':index == 0}"  :data-setmeall="index + 1">
                                                    <img width="50" height="50" :src="picture.spdUrl == null ?  '${ctxStatic}/images/pic/zan_nopic.png' : picture.spdUrl" >
                                                </li>
                                            </ul>
                                        </div>
                                    </div>

                                </div>

                                <div class="slide_img_info relative">

                                    <%--产品名称--%>
                                    <p class="f3 fz24 mb14 lh28"><span>{{product.spProName}}</span></p>

                                    <%--当前组合套餐价格--%>
                                    <div class="fRed slidePrice">
                                        ￥<span id="spanNowSetMealPrice">{{product.maxPrice}}-{{product.minPrice}}</span>
                                    </div>

                                    <%--<li class="current"><a href="javascript:">60<i class="brand_lab_ico brand_lab_arrow"></i></a></li>--%>
                                    <%--类别遍历开始--%>
                                    <div v-for="(category,index1) in categorys" class="brand_lab pb6">
                                        <div class="dt">{{category.spcMctname}}</div>
                                        <div class="dd clearfix">
                                            <ul class="brand_lab_ul">
                                                <li v-for="(categoryItem,index2) in category.categoryItem" class="" :data-rank="index1" :data-mctiId="categoryItem.mctiId">
                                                    <a href="javascript:">{{categoryItem.mctiName}}<i class="brand_lab_ico brand_lab_arrow"></i></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>

                                    <%--二维码 开始--%>
                                    <span class="erwm">
                                    <img src="${ctxStatic}/images/browsing/erwm.jpg?${verStatic}">

                                </span>
                                </div>
                            </div>

                        </div>
                        <div class="mall_bottom_box clearfix relative">
                            <div class="mall_detail_l fl">
                                <!--产品详情下方内容-->
                                <div class="mall_btm_box">
                                    <div class="mall_detail_tab" style="position: absolute; top: 0px;">
                                        <ul class="clearfix">
                                            <li class="fl cur"><a href="javascript:" class="alignCenter">产品介绍</a></li>
                                        </ul>
                                    </div>

                                    <!--产品介绍-->
                                    <div class="mall_detail_main detail_main_1 boxSpProDepictData">

                                    </div>

                                </div>
                            </div>

                            <%--关联商品浮动框--%>
                            <div class="mall_detail_r fr pb14" id="Commodity" style="top: 0px; position: absolute; right: 0px; display: block;">
                                <div class="delRigTit">关联商品</div>
                                <div class="mall_detail_rBox">
                                    <ul id="ProductRelated">

                                        <li class="clearfix">
                                            <a href="javascript:" class="db_100" onclick="Details('992fe545-7ed1-445a-98af-125c72bfa98f');">
                                                <img class="db_100" src="http://img9.rxjy.com:9120/image/0D8DwKgBtFrJruKAVDViAAAsbX7a9JA780.jpg" alt="">
                                                <p>-</p>
                                                <p class="fRed fz14 mt10 fnomal">¥ 0.0 - 0.0</p>
                                            </a>
                                        </li>
                                        <li class="clearfix">
                                            <a href="javascript:" class="db_100" onclick="Details('992fe545-7ed1-445a-98af-125c72bfa98f');">
                                                <img class="db_100" src="http://img9.rxjy.com:9120/image/0D8DwKgBtFrJruKAVDViAAAsbX7a9JA780.jpg" alt="">
                                                <p>-</p>
                                                <p class="fRed fz14 mt10 fnomal">¥ 0.0 - 0.0</p>
                                            </a>
                                        </li>
                                        <li class="clearfix">
                                            <a href="javascript:" class="db_100" onclick="Details('992fe545-7ed1-445a-98af-125c72bfa98f');">
                                                <img class="db_100" src="http://img9.rxjy.com:9120/image/0D8DwKgBtFrJruKAVDViAAAsbX7a9JA780.jpg" alt="">
                                                <p>-</p>
                                                <p class="fRed fz14 mt10 fnomal">¥ 0.0 - 0.0</p>
                                            </a>
                                        </li>

                                    </ul>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <%--页脚--%>
            <div class="mallFooter">
                <div class="mallCon clearfix">
                    <div class="mallFooterLeft fl">
                        <span class="CompanyAdress fl"><em class="icon_bg fl"></em>地址：北京市海淀区永定河路388号</span>
                        <span class="CompanyPhone fl"><em class="icon_bg fl"></em>电话：400-168-5308</span>
                        <span class="CompanyCopyRight fl">京ICP备18022895号-1</span>
                    </div>
                    <div class="mallFooterRight fr"> <img src="${ctxStatic}/images/browsing/footImg.jpg?${verStatic}"></div>
                </div>
            </div>

            <%--回到顶部--%>
            <a href="#0" class="cd-top">Top</a>

        </div>

        <%--隐藏域--%>
        <input type="hidden" id="hidZYCategorysSize" value=""/><%-- 当前产品类别数量 --%>
        <input type="hidden" id="hidZYPriceMaxMin" value=""/><%-- 组合套餐价区间 --%>
    </body>


    <script src="${basePath}/static/mms/js/special/browsing/slider.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/static/mms/js/special/browsing/mall_detail.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/static/mms/js/special/browsing/public_nav.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/static/mms/js/rxuedv2.0.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>

    <%--页面JS相关--%>
    <script src="${basePath}/static/mms/js/special/open/product/browsing.js?${verStatic}" type="text/javascript" charset="utf-8"></script>

    <%--VUE2.0 开始--%>
    <script>
        var zxProBrowsingVue = new Vue({
            el: '#zxProBrowsingVue',
            data: {
                product: {}, // 产品
                pictures: [], // 主图集
                firstSpdUrl: "", // 主图集
                categorys: [] // 类别集
            },
            created:function() {
                // VUE 实例创建加载数据
                this.buildZXProBrowsingData();
                this.$nextTick(function(){
                    //小图滚动
                    setTimeout(function(){
                        PictureScroll();
                    },200)
                })
            },
            methods: {
                // 构建专项产品浏览数据
                buildZXProBrowsingData: function () {
                    var _this = this;
                    var proID= getUrlParms("proid"); // 材料商ID
                    $.ajax({
                        url: basePath + '/special-api/open/product/browsing',
                        type: 'GET',
                        dataType: 'json',
                        data: {proID},
                        success: function (res) {
                            _this.product = res.body.product;
                            _this.pictures = res.body.pictures;
                            _this.firstSpdUrl =  res.body.pictures[0].spdUrl;
                            _this.categorys = res.body.categorys;

                            $("#hidZYCategorysSize").val(res.body.categorys.length);
                            var priceMaxMin = _this.product.maxPrice + "-" + _this.product.minPrice;
                            $("#hidZYPriceMaxMin").val(priceMaxMin);
                            setTimeout(function () {
                                // 设置产品介绍
                                $(".boxSpProDepictData").html(res.body.product.spProDepictData);
                            });
                        },
                        error: function (err) {alert("操作出错！");}
                    });
                }
            }
        });
    </script>
</html>
