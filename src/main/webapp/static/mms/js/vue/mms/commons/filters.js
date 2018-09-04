//**************************************时间格式化处理************************************/
function dateFtt(fmt, date) { //author: meizz
    var o = {
        "M+": date.getMonth() + 1,                 //月份
        "d+": date.getDate(),                    //日
        "h+": date.getHours(),                   //小时
        "m+": date.getMinutes(),                 //分
        "s+": date.getSeconds(),                 //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

// 时间格式化
Vue.filter("formatDateTime", function (date, fmt) {
    var resultDate = '';
    if (date != null) {
        resultDate = dateFtt(fmt, new Date(date));
    }
    return resultDate;
});
// 转化处理类型
Vue.filter("handleTypeChangeStrVue", function (vmhType) {
    var vmhTypeStr = "未";
    switch (vmhType) {
        case 1:
            vmhTypeStr = "正";
            break;
        case 2:
            vmhTypeStr = "异";
            break;
        case 3:
            vmhTypeStr = "问";
            break;
    }
    return vmhTypeStr;
});
Vue.filter("handleTypeChangeStrVue2", function (vmhType) {
    debugger
    var vmhTypeStr = "未处理";
    switch (vmhType) {
        case 1:
            vmhTypeStr = "正常";
            break;
        case 2:
            vmhTypeStr = "异常";
            break;
        case 3:
            vmhTypeStr = "问题";
            break;
    }
    return vmhTypeStr;
});

// 创建时间格式化显示
Vue.filter('timeFormattingVisit', function (time) {

    var curreDate = new Date();
    var curreYear = curreDate.getFullYear();

    var paramDate = new Date(time);
    var paramYear = paramDate.getFullYear();

    var resultDate;

    if (curreYear == paramYear) {
        resultDate = dateFtt("MM-dd hh:mm", paramDate);
    } else {
        resultDate = dateFtt("yyyy-MM-dd hh:mm", paramDate)
    }

    return resultDate;
});

//截取字符串(默认显示后四位) m > 0: 截取后n位, n > 0: 截取前n位, e: 超出显示什么符号
Vue.filter("cutOutStr", function (str, m, n, e) {
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
});

// 转换超长字符串 ,根据传入长限制长度.
Vue.filter('convertOverlengthStrings', function (str, len) {
    //判断字符串是否为空
    if (null != str) {
        //不为空,判断长度
        if (str.length > len)
        //如果当前长度 > 传入长度限制 = 截取要求长度的字符,拼接补充字段
            str = str.substring(0, len - 1) + '..';
    } else
    //为空赋值:'--'
        str = "--";
    return str;
});
// 空字符串转'--'
Vue.filter('isBlankToPlaceholder', function (str) {
    if (null == str || '' == str)
        str = '--';
    return str;
});
// 改变小项标题 入参:当前项档次标识
Vue.filter('changeItemTitle', function (levelFlag) {
    debugger
    if (levelFlag == null || levelFlag == '')
        levelFlag = 0;
    switch (levelFlag) {
        case 0:
            return '统一质量标准';
        case 1:
            return 'A档质量标准';
        case 2:
            return 'B档质量标准';
        case 4:
            return 'C档质量标准';
    }
});
// 档次标识变ABC
Vue.filter('levelFlagTOABC', function (levelFlag) {
    switch (levelFlag) {
        case 1:
            return 'A';
        case 2:
            return 'B';
        case 4:
            return 'C';
    }
});


// 保留两位小数不够则用0替补
Vue.filter('holdTwoDecimal', function (num) {
    var result = parseFloat(num);
    result = Math.round(num * 100) / 100;
    var s_x = result.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    return s_x;
});
// 阿拉伯数字转换为简写汉字
Vue.filter('numToStr', function (Num) {
    for (i = Num.length - 1; i >= 0; i--) {
        Num = Num.replace(",", "")//替换Num中的“,”
        Num = Num.replace(" ", "")//替换Num中的空格
    }
    if (isNaN(Num)) { //验证输入的字符是否为数字
        //alert("请检查小写金额是否正确");
        return;
    }
    //字符处理完毕后开始转换，采用前后两部分分别转换
    part = String(Num).split(".");
    newchar = "";
    //小数点前进行转化
    for (i = part[0].length - 1; i >= 0; i--) {
        if (part[0].length > 10) {
            //alert("位数过大，无法计算");
            return "";
        }//若数量超过拾亿单位，提示
        tmpnewchar = ""
        perchar = part[0].charAt(i);
        switch (perchar) {
            case "0":
                tmpnewchar = "零" + tmpnewchar;
                break;
            case "1":
                tmpnewchar = "一" + tmpnewchar;
                break;
            case "2":
                tmpnewchar = "二" + tmpnewchar;
                break;
            case "3":
                tmpnewchar = "三" + tmpnewchar;
                break;
            case "4":
                tmpnewchar = "四" + tmpnewchar;
                break;
            case "5":
                tmpnewchar = "五" + tmpnewchar;
                break;
            case "6":
                tmpnewchar = "六" + tmpnewchar;
                break;
            case "7":
                tmpnewchar = "七" + tmpnewchar;
                break;
            case "8":
                tmpnewchar = "八" + tmpnewchar;
                break;
            case "9":
                tmpnewchar = "九" + tmpnewchar;
                break;
        }
        switch (part[0].length - i - 1) {
            case 0:
                tmpnewchar = tmpnewchar;
                break;
            case 1:
                if (perchar != 0) tmpnewchar = tmpnewchar + "十";
                break;
            case 2:
                if (perchar != 0) tmpnewchar = tmpnewchar + "百";
                break;
            case 3:
                if (perchar != 0) tmpnewchar = tmpnewchar + "千";
                break;
            case 4:
                tmpnewchar = tmpnewchar + "万";
                break;
            case 5:
                if (perchar != 0) tmpnewchar = tmpnewchar + "十";
                break;
            case 6:
                if (perchar != 0) tmpnewchar = tmpnewchar + "百";
                break;
            case 7:
                if (perchar != 0) tmpnewchar = tmpnewchar + "千";
                break;
            case 8:
                tmpnewchar = tmpnewchar + "亿";
                break;
            case 9:
                tmpnewchar = tmpnewchar + "十";
                break;
        }
        newchar = tmpnewchar + newchar;
    }
    //替换所有无用汉字，直到没有此类无用的数字为止
    while (newchar.search("零零") != -1 || newchar.search("零亿") != -1 || newchar.search("亿万") != -1 || newchar.search("零万") != -1) {
        newchar = newchar.replace("零亿", "亿");
        newchar = newchar.replace("亿万", "亿");
        newchar = newchar.replace("零万", "万");
        newchar = newchar.replace("零零", "零");
    }
    //替换以“一十”开头的，为“十”
    if (newchar.indexOf("一十") == 0) {
        newchar = newchar.substr(1);
    }
    //替换以“零”结尾的，为“”
    if (newchar.lastIndexOf("零") == newchar.length - 1) {
        newchar = newchar.substr(0, newchar.length - 1);
    }
    return newchar;
});

// 入参: visitTime 回访毫秒,timeLimit 限期小时数.
Vue.filter("visitCountDown",function(visitTime,timeLimit){

    var curreTime = new Date().getTime();

    var countdownNumber;

    if (visitTime != null)
    {
        countdownNumber = parseInt(timeLimit - ((curreTime - visitTime) / 3600000));

        if (-999 >= countdownNumber) countdownNumber = -999;
    }
    else countdownNumber = -999;

    return countdownNumber;
});




