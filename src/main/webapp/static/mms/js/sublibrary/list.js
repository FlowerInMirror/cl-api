// 子库列表: 设置材料列表回路总计
function setMatListLoopTotal(){
    // 材料名称 - 总计数量
    $("#matLoopListTotal .totalNm").html($(".dq_matLoopList").length);
    // 材料名称 - 圈中数量
    $("#matLoopListTotal .quanNm").html($(".dq_matLoopList[quan-flag != '0']").length);

    // 总状态 - 完
    $("#matLoopListTotal .loopTotalNmY").html($(".dq_matLoopList[loop-total = '1']").length);
    // 总状态 - 未
    $("#matLoopListTotal .loopTotalNmN").html($(".dq_matLoopList[loop-total = '0']").length);

    // 平台 - 完
    $("#matLoopListTotal .loopPlatformNmY").html($(".dq_matLoopList[loop-platform = '1']").length);
    // 平台 - 未
    $("#matLoopListTotal .loopPlatformNmN").html($(".dq_matLoopList[loop-platform = '0']").length);

    // A档 - 完
    $("#matLoopListTotal .loopAlevelNmY").html($(".dq_matLoopList[loop-alevel-notnm = '0']").length);
    // A档 - 未
    $("#matLoopListTotal .loopAlevelNmN").html($(".dq_matLoopList[loop-alevel-notnm  != '0']").length);

    // B档 - 完
    $("#matLoopListTotal .loopBlevelNmY").html($(".dq_matLoopList[loop-blevel-notnm  = '0']").length);
    // B档 - 未
    $("#matLoopListTotal .loopBlevelNmN").html($(".dq_matLoopList[loop-blevel-notnm  != '0']").length);

    // C档 - 完
    $("#matLoopListTotal .loopClevelNmY").html($(".dq_matLoopList[loop-clevel-notnm  = '0']").length);
    // C档 - 未
    $("#matLoopListTotal .loopClevelNmN").html($(".dq_matLoopList[loop-clevel-notnm  != '0']").length);

    // 处理状态 - 未
    $("#matLoopListTotal .handleTypeNot").html($(".dq_matLoopList[handle-type = '0']").length);
    // 处理状态 - 正
    $("#matLoopListTotal .handleTypeZ").html($(".dq_matLoopList[handle-type = '1']").length);
    // 处理状态 - 异
    $("#matLoopListTotal .handleTypeY").html($(".dq_matLoopList[handle-type = '2']").length);
    // 处理状态 - 问
    $("#matLoopListTotal .handleTypeW").html($(".dq_matLoopList[handle-type = '3']").length);
}

/*
 * 根据某个字段实现对json数组的排序
 *
 * @param   array  要排序的json数组对象
 * @param   field  排序字段（此参数必须为字符串）
 * @param   reverse 是否倒序（默认为false）
 * @return  array  返回排序后的json数组
*/
function jsonSort(array, field, reverse) {
    //数组长度小于2 或 没有指定排序字段 或 不是json格式数据
    if(array.length < 2 || !field || typeof array[0] !== "object") return array;
    //数字类型排序
    if(typeof array[0][field] === "number") {
        array.sort(function(x, y) { return x[field] - y[field]});
    }
    //字符串类型排序
    if(typeof array[0][field] === "string") {
        array.sort(function(x, y) { return x[field].localeCompare(y[field])});
    }
    //倒序
    if(reverse) {
        array.reverse();
    }
    return array;
}