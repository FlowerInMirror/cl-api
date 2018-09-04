var urlSave = '';
var dataSave = [];
var tabIdSave = '';
var _scopeid;
//added by pyf 2015-12-21
var successFunSave = undefined;

var PageSize=12;
//end of added.
/**
 *分页查询
 *@pageIndex:页索引
 *@url:Url请求地址
 *@data:Url请求提交的参数数据，Json对象
 *@tabId:页面Dom对象TabId
 */
function NewPagingQuery(pageIndex, url, data, tabId, successFun) {
    urlSave = url;
    dataSave = data;
    tabIdSave = tabId;
    //PageSize = pageSize;
    //added by pyf 2015-12-21
    successFunSave = successFun;
    //end of added.
    $.ajax({
        url: url + '?pageIndex=' + (parseInt(pageIndex) + 1),
        type: 'POST',
        dataType: 'html',
        data: data,
        success: function (result) {
            //1.加载分页数据
            $("#" + tabId).html("");
            $("#" + tabId).html(result);
            //2.设置行背景颜色

            //3.底部分页组件
            var totalRecord = $("#TotalCount").val();
            $("#Pagination").pagination(totalRecord, {
                'items_per_page': PageSize,//每页显示的条目数
                'num_display_entries': 6, //连续分页主体部分显示的分页条目数
                'num_edge_entries': 3,    //两侧显示的首尾分页的条目数
                'prev_text': "< 上一页",
                'next_text': "下一页 >",
                'first_text': "",
                'last_text': "",
                'current_page': pageIndex,// bindingInfo.pi - 1,
                'goto_text': "到第",
                'callback': NewPageSelectCallback
            });
            //4.绑定页跳转
            //绑定页的跳转
            $("#Pagination #btnJumpPage").unbind("click").bind("click", function () {
                var p = $("#Pagination #jumpPage").val();
                if (Number(p) > 0) {
                    p = Number(p) - 1;
                    if (p >= Number($(".pageInfo_total").text())) {
                        alert("页索引超出最大范围!");
                        $("#Pagination #jumpPage").val("");
                    } else {
                        NewPageSelectCallback(p);
                    }
                }
            });
            //5.重置Table宽度
            //6.弹出方法
            //7、执行其他方法
            if (successFun && typeof (successFun) == "function") {
                successFun();
            }
        },
        error: function (error) {
            //alert("数据获取失败，请稍后再试!");
        }
    });
}

/**
 *分页查询
 *@pageIndex:页索引
 *@url:Url请求地址
 *@data:Url请求提交的参数数据，Json对象
 *@tabId:页面Dom对象TabId
 */
function NewPagingQuerySub(scopeid, pageIndex, url, data, tabId, successFun, pageSize, num_display_entries, num_edge_entries) {
    if (!pageSize)
        pageSize = PageSize;
    if (!num_display_entries)
        num_display_entries = 6;
    if (!num_edge_entries)
        num_edge_entries = 3;


    $.ajax({
        url: url + '?pageIndex=' + (parseInt(pageIndex) + 1),
        type: 'POST',
        dataType: 'html',
        data: data,
        success: function (result) {
            //1.加载分页数据
            $("#" + tabId, $("#" + scopeid)).html("");
            $("#" + tabId, $("#" + scopeid)).html(result);
            //2.设置行背景颜色

            //3.底部分页组件
            var totalRecord = $("#TotalCount", $("#" + scopeid)).val();
            $("#Pagination", $("#" + scopeid)).pagination(totalRecord, {
                'items_per_page': pageSize,//每页显示的条目数
                'num_display_entries': num_display_entries, //连续分页主体部分显示的分页条目数
                'num_edge_entries': num_edge_entries,    //两侧显示的首尾分页的条目数
                'prev_text': "< 上一页",
                'next_text': "下一页 >",
                'first_text': "",
                'last_text': "",
                'current_page': pageIndex,// bindingInfo.pi - 1,
                'goto_text': "到第",
                'scopeid': scopeid,
                'url': url,
                'data': data,
                'tabId': tabId,
                'successFun': successFun,
                'callback': NewPageSelectCallbackSub
            });
            //4.绑定页跳转
            //绑定页的跳转
            $("#Pagination #btnJumpPage", $("#" + scopeid)).unbind("click").bind("click", function () {
                var p = $("#Pagination #jumpPage", $("#" + scopeid)).val();
                if (Number(p) > 0) {
                    p = Number(p) - 1;
                    if (p >= Number($("#Pagination .pageInfo_total", $("#" + scopeid)).text())) {
                        alert("页索引超出最大范围!");
                        $("#Pagination #jumpPage", $("#" + scopeid)).val("");
                    } else {
                        NewPagingQuerySub(scopeid, p, url, data, tabId, successFun, pageSize, num_display_entries, num_edge_entries);
                    }
                }
            });
            //5.重置Table宽度
            //6.弹出方法
            //7、执行其他方法
            if (successFun && typeof (successFun) == "function") {
                successFun();
            }
        },
        error: function (error) {
            //alert("数据获取失败，请稍后再试!");
        }
    });
}

/**
 *根据页索引查询
*/
//modify by pyf 2015-12-21 old code is:
/*function PageSelectCallback(pageIndex) {
    PagingQuery(pageIndex, urlSave, dataSave, tabIdSave);
}*/

function NewPageSelectCallback(pageIndex) {
    NewPagingQuery(pageIndex, urlSave, dataSave, tabIdSave, successFunSave);
}
function NewPageSelectCallbackSub(pageIndex) {
    NewPagingQuerySub(this.scopeid, pageIndex, this.url, this.data, this.tabId, this.successFun, this.items_per_page, this.num_display_entries, this.num_edge_entries);
}
//end of modify.
