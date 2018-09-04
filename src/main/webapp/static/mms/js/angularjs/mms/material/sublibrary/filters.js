//==============自定义过滤器 开始
// 材料处理类型转文字
myApp.filter("handleTypeChangeStr",function(){
    return function (vmhType) {
        var vmhTypeStr = "未";
        switch (vmhType){
            case 1: vmhTypeStr = "正"; break;
            case 2: vmhTypeStr = "异"; break;
            case 3: vmhTypeStr = "问"; break;
        }
        return vmhTypeStr;
    }
});

// 判断 有无,非0即true true: siez + '项'返回;false: '无'
myApp.filter("judgeYesOrNo",function(){
    return function (size) {
       if (size)
           return size + '项';
       return '无';
    }
});

// 档次数字变字母
myApp.filter("levelNumChangeLetter",function(){
    return function (levelNum) {
        var letter;
        switch (levelNum){
            case 1: letter = 'A'; break;
            case 2: letter = 'B'; break;
            case 4: letter = 'C'; break;
        }
        return letter;
    }
});

// 改变超长字符串
// 入参:str 传入的字符串,len 要求展示的字符串长度
// 描述:转换超长（>len）字符串 ，超长放到span的title中
myApp.filter("changeOverlengthStr",function(){
    return function(str, len){
        //判断字符串是否为空
        if (null != str) {
            //不为空,判断长度
            if (str.length > len)
            //如果当前长度 > 传入长度限制 = 截取要求长度的字符,拼接补充字段
                str = str.substring(0,len-1)+'..';
        } else
        //为空赋值:'--'
            str = "--";
        return str;
    }
});

// 转换倒计时
// 入参: visitTime 回访毫秒,timeLimit 限期小时数.
myApp.filter("visitCountDown",function(){
    return function(visitTime, timeLimit){
        var curreTime = new Date().getTime();
        var countdownNumber;
        if (visitTime != null) {
            countdownNumber = parseInt(timeLimit - ((curreTime - visitTime) / 3600000));
            if (-999 >= countdownNumber)
                countdownNumber = -999;
        } else {
            countdownNumber = -999;
        }
        return countdownNumber;
    }
});

//截取字符串(默认显示后四位) m > 0: 截取后n位, n > 0: 截取前n位, e: 超出显示什么符号
myApp.filter("cutout", function () {
    return function (str, m, n, e) {
        if (str == null)
            return '';
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