    /*左侧 JS*/
$(function () {
    // 地区>一段>检索框回车事件
    $("#txtTreeSearch").keydown(function (e) {
        if (e.keyCode == 13) {
            $(".contentbox_tree .uiText-searchIcon").click();
        }
    });

    countLeft();
    // 使用AngularJS 页面加载,不能加载样式到一段列表中,注释.
    // rxued.table.LChangeapart($("#tab1 tr"), "#f9f9f9", "#fff");
    // rxued.table.hoverChage($("#tab1 tr"), "#eaf4fe");

    //切换：子库-地区-审核
    $(document).on("click",".uiTab1 li",function(e){
        // 设置子库地区一段列表,第一个四级科目树ID,到科目树ID隐藏域中.
        var treeID = $("#tabDataToMaterial tr:eq(0)").attr("id");
        $("#hidTreeID").val(treeID);

        $(this).addClass("uiTab1-active").siblings().removeClass("uiTab1-active");
        $("#hidMatLevel").val("");
        $("#hidMatID").val("");

        var index = $(this).data("index"); // 0 子库 1 北京 3 审核 4 管理
        if (index == 0 ){
            $("#hidToCityFlag").val(0); // 全国
        }
        if (index == 1) {
            $("#hidToCityFlag").val(1); // 地区
        }


        $(".daily_title").hide().eq(index).show();


        //"-------------------专项---------------"
        $(".daily_title").eq(index).find("li:eq(0)").click();
        $(".contentbox").hide().eq(index).show();
        if (index >= 1) {
            $(".div_main_data").hide().eq(1).show();
            if (index == 2) {
                //加载审核材料（圈中的）
                $(".daily_title_tree_ver li.title_cur:eq(0)").click();
                //loadCityDetailToVer();
            }
            else if (index == 3) {
                //管理
                loadCityDetailToManage();
            }
            else {
                LoadTreeListToSearch();
            }
        }
        else {
            $(".div_main_data").hide().eq(0).show();
        }

        countLeft();
        countMiddle();
        countRight();
        visitedScroll();
    });

    //左侧表格点击（地区）
    $("#tabData").on('click', 'tr', function () {
    // $("#tabData tr").click(function () {
        $(this).addClass("tractive").siblings().removeClass("tractive");
        var cityID = $(this).data("city");
        var isReload = false;
        if ($("#hidCityID").val() != cityID) {
            isReload = true;
        }
        $("#hidCityID").val(cityID);


        var liObj = $(".uiTab1 li:eq(1)");
        $(liObj).find("a").text($(this).find("td:eq(0)").text());

        //切换到地区详细
        //$(liObj).click();

        if (isReload == true) {
            // loadCityDetail(); 加载地区材料 - 注释:使用AngularJS实现.
            LoadTreeListToSearch();//材料检索
            countLeft(); //左侧JS
            $("#hidType").val("1");//圈中的 不执行
        }
    });

    //起始页 切换至子库项 点击事件
    $(document).on("click", "#divTurnToMaterialList", function () {

        // 设置跳转地区标识(用于区分子库列表全国状态总计)
        $("#hidToCityFlag").val(1); // 地区

        var liObj = $(".uiTab1 li:eq(1)");
        $(liObj).click();
    });


    /*子库-全部-材料 Start*/
    //导航切换：已完善，未完善，待审核（材料）
    $(document).on("click",".daily_title_tree li",function(){
        $(".daily_title_tree li").removeClass("title_cur");
        $(this).addClass("title_cur");
        if($(this).attr("data-type")!="zhuan"){
            LoadTreeListToSearch();//材料检索
        }
        trchangecolor();
        //hujianghua 专项
        if($(this).attr("data-type")=="zhuan")
        {
            $(".contentbox").hide();
            $(".contentbox_zhuan").show().load(basePath+"/public-web/special/one-list",function(){
                countLeft();
                $(".div_main_data").hide();
                $("#divZhuanXiang").show().load(basePath+"/public-web/special/two-main",function(){

                });
            });

        }else{
            $(".contentbox").hide();
            $(".contentbox_tree").show();
        }
        countLeft();
        countMiddle();
        countRight();
    });
    //绑定一级分类
    BindTreeToDDL("#ddlTreeOne","");
    /*-----绑定 检索事件 start------*/
    $("#ddlTreeOne").change(function () {
        $("#ddlTreeTwo").html("<option value=''>二级类</option>");
        $("#ddlTreeThree").html("<option value=''>材料名称</option>");
        $("#ddlTreeFour").html("<option value=''>规格</option>");
        if ($(this).val() != "") {
            /*绑定二级分类*/
            BindTreeToDDL('#ddlTreeTwo', $(this).val());
        }

        LoadTreeListToSearch();//加载数据
    });
    $("#ddlTreeTwo").change(function () {
        $("#ddlTreeThree").html("<option value=''>材料名称</option>");
        $("#ddlTreeFour").html("<option value=''>规格</option>");
        if ($(this).val() != "") {
            /*绑定三级分类*/
            BindTreeToDDL('#ddlTreeThree', $(this).val());
        }
        LoadTreeListToSearch();//加载数据
    });
    $("#ddlTreeThree").change(function () {
        $("#ddlTreeFour").html("<option value=''>规格</option>");
        if ($(this).val() != "") {
            /*绑定规格分类*/
            BindTreeToDDL('#ddlTreeFour', $(this).val());
        }

        LoadTreeListToSearch();//加载数据
    });
    $("#ddlTreeFour").change(function () {
        LoadTreeListToSearch();//加载数据
    });
    $("#ddlTopMaterial").change(function () {
        LoadTreeListToSearch();//材料检索
    });

    //检索事件
    $(document).on("click", "#divStateStatistics p", function () {
        $("#hidMark").val($(this).data("mark"));
        trchangecolor();
        LoadTreeListToSearch();//材料检索
    });
    /*子库-全部-材料 End*/

    BindLeftEventToVer();//绑定：审核 【一段】 事件
    BindLeftEventToManage();//绑定：管理 【一段】 事件

    //默认先加载北京地区
    loadCityDetail();
    //loadCityDetailToVer();



});

/*子库-检验（圈中的）-材料*/
function BindLeftEventToVer() {
    //导航切换：已完善，未完善，待审核（材料）
    $(".daily_title_tree_ver li").click(function () {
        var type = $(this).data("type");
        if (type == 99) {
            //控制是否展示历史
            if ($(this).hasClass("title_cur")) {
                $(this).removeClass("title_cur");
            }
            else {
                $(this).addClass("title_cur");
            }

            //筛选
            LoadTreeListToSearchToVer();
        }
        else {
            //重新加载
            //$(this).addClass("title_cur").siblings().removeClass("title_cur");
            $(".daily_title_tree_ver li").removeClass("title_cur");
            $(this).addClass("title_cur");

            loadCityDetailToVer(type);
        }
    });
    //绑定一级分类
    BindTreeToDDL("#ddlTreeOneVer");
    /*-----绑定 检索事件 start------*/
    $("#ddlTreeOneVer").change(function () {
        $("#ddlTreeTwoVer").html("<option value=''>二级类</option>");
        $("#ddlTreeThreeVer").html("<option value=''>材料名称</option>");
        $("#ddlTreeFourVer").html("<option value=''>规格</option>");
        if ($(this).val() != "") {
            /*绑定二级分类*/
            BindTreeToDDL('#ddlTreeTwoVer', $(this).val());
        }

        LoadTreeListToSearchToVer();//加载数据
    });
    $("#ddlTreeTwoVer").change(function () {
        $("#ddlTreeThreeVer").html("<option value=''>材料名称</option>");
        $("#ddlTreeFourVer").html("<option value=''>规格</option>");
        if ($(this).val() != "") {
            /*绑定三级分类*/
            BindTreeToDDL('#ddlTreeThreeVer', $(this).val());
        }
        LoadTreeListToSearchToVer();//加载数据
    });
    $("#ddlTreeThreeVer").change(function () {
        $("#ddlTreeFourVer").html("<option value=''>规格</option>");
        if ($(this).val() != "") {
            /*绑定规格分类*/
            BindTreeToDDL('#ddlTreeFourVer', $(this).val());
        }

        LoadTreeListToSearchToVer();//加载数据
    });
    $("#ddlTreeFourVer").change(function () {
        LoadTreeListToSearchToVer();//加载数据
    });
    //检索事件
    $("#divStateStatisticsVer p").click(function () {
        $("#hidMark").val($(this).data("mark"));
        trchangecolor();
        LoadTreeListToSearchToVer();//材料检索
    });
}


/*子库-管理-材料*/
function BindLeftEventToManage() {
    //导航切换：第一段：全 A~Z
    $(".daily_title_tree_manage li").click(function () {

    });
    //绑定一级分类
    BindTreeToDDL("#ddlTreeOneManage");
    /*-----绑定 检索事件 start------*/
    $("#ddlTreeOneManage").change(function () {
        $("#ddlTreeTwoManage").html("<option value=''>二级类</option>");
        $("#ddlTreeThreeManage").html("<option value=''>材料名称</option>");
        $("#ddlTreeFourManage").html("<option value=''>规格</option>");
        if ($(this).val() != "") {
            /*绑定二级分类*/
            BindTreeToDDL('#ddlTreeTwoManage', $(this).val());
        }

        LoadTreeListToSearchToManage();//加载数据
    });
    $("#ddlTreeTwoManage").change(function () {
        $("#ddlTreeThreeManage").html("<option value=''>材料名称</option>");
        $("#ddlTreeFourManage").html("<option value=''>规格</option>");
        if ($(this).val() != "") {
            /*绑定三级分类*/
            BindTreeToDDL('#ddlTreeThreeManage', $(this).val());
        }
        LoadTreeListToSearchToManage();//加载数据
    });
    $("#ddlTreeThreeManage").change(function () {
        $("#ddlTreeFourManage").html("<option value=''>规格</option>");
        if ($(this).val() != "") {
            /*绑定规格分类*/
            BindTreeToDDL('#ddlTreeFourManage', $(this).val());
        }

        LoadTreeListToSearchToManage();//加载数据
    });
    $("#ddlTreeFourManage").change(function () {
        LoadTreeListToSearchToManage();//加载数据
    });
    //检索事件
    $("#divStateStatisticsManage p").click(function () {
        $("#hidMark").val($(this).data("mark"));
        trchangecolor();
        LoadTreeListToSearchToManage();//材料检索
    });
}