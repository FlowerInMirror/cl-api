//转换：配送等级信息
//<param name="deliveryType">此配送等级的权限：1，2...</param>
//<param name="nowLevel">当前的等级</param>
//<param name="entity"></param>
//<returns></returns>
//result.jsp   affairDetail.jsp
function tsUserInfoDeliveryState(deliveryType,nowLevel,suppliersInfo) {
    debugger
    var result = "";
    if(nowLevel>=deliveryType && suppliersInfo.zOne==3){
        if(nowLevel>=deliveryType+2){
            //OK
            result = '<span class="cGreen" title="J'+deliveryType+'阶段配送评价正常">评'+deliveryType+'</span>';
        }else{
            if(suppliersInfo.iasitems!=null && suppliersInfo.iasitems!=''&&suppliersInfo.iasitems!=undefined){
                var items = [];
                $.each(suppliersInfo.iasitems,function(i,item){
                    if(item.systemType==2&&item.infoType==deliveryType&&item.switchStatus==0){
                        items.push(item);
                    }
                });
                if(items!=null&&items.length>0){
                    result = '<span class="cRed" title="J'+deliveryType+'阶段配送评价异常，存在差评">评'+deliveryType+'</span>';
                }else{
                    result ='<span class="cGreen" title="J'+deliveryType+'阶段配送评价正常">评'+deliveryType+'</span>';
                }
            }else{
                result ='<span class="cGreen" title="J'+deliveryType+'阶段配送评价正常">评'+deliveryType+'</span>';
            }
        }
    }else{
        result = "<span class='' title='无权限'>评"+deliveryType+"</span>";
    }
    return result;
}
//转换：资料绑定信息
//affairDetail.jsp
function tsUserInfoDataState(zType,zState) {
    var result ="";
    var title;
    if(zState===3){
        title = "资料已完善";
        switch (zType){
            case 1:
                title="基础资料已完善";
                break;
            case 2:
                title="银行资料已完善";
                break;
            case 3:title="公司资料已完善";
                break;
            case 4:title="考察资料已完善";
                break;
            default:
                break
        }
        result = "<span class='cGreen' title='"+title+"'>Z"+zType+"</span>";
    }else{
        title = "资料未完善";
        switch (zType){
            case 1:
                title="基础资料未完善";
                break;
            case 2:
                title="银行资料未完善";
                break;
            case 3:title="公司资料未完善";
                break;
            case 4:title="考察资料未完善";
                break;
            default:
                break
        }
        result = "<span class='cRed' title='"+title+"'>Z"+zType+"</span>";
    }
    return result;
}

// #region 获取材料商照片审核状态
/// <summary>
/// 获取材料商照片审核状态
/// </summary>
/// <param name="checkState">阶段状态：</param>
/// <param name="imgUrl">图片路径</param>
/// <param name="imgClass">图片默认样式</param>
/// <param name="msg">此项名称</param>
/// <param name="msgBack">打回名称</param>
/// <returns></returns>
//affairData.jsp
function getUserPhotoCheckSstateToHtml(checkState,imgUrl,imgClass,msg,msgBack) {
    var result;
    switch (checkState){
        case 3://通过
            imgClass = imgClass+" pass";
            break;
        case 2://打回
            var patt1 = new RegExp(msg);//匹配内容
            //if(msgBack.indexOf(msg)>=0){//对大小写敏感，相当于contains();
            if(patt1.test(msgBack)){//对大小写敏感，相当于contains();
                imgClass = imgClass+" nopass";
            }else{
                imgClass = imgClass +" wait";
            }
            break;
        case 1://申请
            imgClass = imgClass +" wait";
            break;
        default:
            if(imgUrl!=null && imgUrl!=undefined&& imgUrl!="" &&!isNaN(imgUrl)){
                imgClass = imgClass + " wait";
            }else{
                imgClass = imgClass;
            }
            break;
    }
    result = '<a href="javascript:" class="'+imgClass+' fl default_img"></a>';
    return result;
}
//截取字符串长度
//affairData.jsp
function tsLongStrToSpanByBytes(str,len) {
    var result = "";
    if(str!=""&&str!=null){
        if($.trim(str).length<=len){
            result = '<span>'+str+'</span>'
        }else{
            var temp =str.substr(0,len);
            result = '<span title="'+str+'">'+temp+'..</span>';
        }
    }
    return result;
}
//材料商类型转换
//affairData.jsp
function tsContractType(status) {
    var str;
    switch (status){
        case 1:
            str = "个人";
            break;
        case 2:
            str = "厂家";
            break;
        case 3:
            str = "代理商";
            break;
        default:
            str = "--";
            break;
    }
    return str;
}
//#region 转换：资料绑定信息（审核信息）
/// <summary>
/// 转换：资料绑定信息（审核信息）
/// </summary>
/// <param name="zState">0未完善，1完善，待审核，2打回，3通过</param>
/// <param name="pType">其他类型：1照片展示，2照片审核</param>
/// <param name="pNum">其他值</param>
/// <returns></returns>
//affairData.jsp
function tsUserInfoDataStateToCheck(zState,pType,pNum) {
    var result ="";
    if(pType == 1){
        //照片展示
        switch (zState){
            case 3:
                result = '<span class="fl false_default default_img selected ml5 mr5"></span>';
                break;
            case 1:
                result = '<span class="default_img ml5 fl ml5 mr5" zz-photo="'+pNum+'"></span>';
                break;
            default:break;
        }
    }else if(pType == 2){
        //照片审核
        switch (zState){
            case 3:
                result ='<div class="imgbox dis-il-block"><span class="false_default selected"></span></div>';
                break;
            case 1:
                result = '<div class="imgbox dis-il-block"><span class="false_default mr5"></span><span class="true_default"></span></div>';
                break;
            default:
                break;
        }
    }else{
        //正常
        switch (zState){
            case 3:
                result = "<span class=\"fl false_default default_img selected ml5 mr5\"></span>";
                break;
            case 1:
                result = "<span class=\"fl false_default default_img ml5 mr5\"></span><span class=\"fl true_default default_img ml5 mr5\"></span>";
                break;
            default:
                break;
        }
    }
    return result;
}
//获取等级细节结果   2018/06/14
//affairLevel.jsp
function getUserLevelDetails(suppliersInfo,suppliersOrderStatistics,levelItems) {
    var result = {};
    var orderItem = {};
    var moneyItem = {};
    var scoreItem = {};
    var workYearItem = {};
    //var upgradeItem = {};
    var score = 100;
    //工龄
    var curDate = new Date();
    var curYear = curDate.getFullYear();
    var curMonth = curDate.getMonth() + 1;
    var oldDate = new Date(suppliersInfo.inductionDate);
    var oldYear = oldDate.getFullYear();
    var oldMonth = oldDate.getMonth() + 1;
    var month = (curYear-oldYear)*12 +(curMonth-oldMonth);
    month = month <=0?1:month;

    //当前值
    orderItem.valueToActual = suppliersOrderStatistics.orderCount;
    moneyItem.valueToActual = Number(suppliersOrderStatistics.totalMoney/10000).toFixed(2);
    scoreItem.valueToActual = score;
    workYearItem.valueToActual = month;
    if(levelItems!=null &&levelItems!="" && levelItems!=undefined){
        //获取当前等级&标准   获取订单等级|标准  开始
        var temp =[];
        //获取 金额 等级 | 标准
        var moneyTemp = [];
        //获取 积分 等级 | 标准
        var levelTemp = [];
        //获取 工龄 等级 | 标准
        var workMonth = [];
        $.each(levelItems,function (i,item) {
            if(suppliersOrderStatistics.orderCount>=item.lsStandardOne){
                temp.push(item);
            }
            if(suppliersOrderStatistics.totalMoney>=(item.lsStandardTwo*10000)){
                moneyTemp.push(item);
            }
            if(score>=item.lsStandardThree){
                levelTemp.push(item);
            }
            if(month>=item.lsStandardFour){
                workMonth.push(item);
            }
        });
        //获取当前等级&标准   获取订单等级|标准  开始
        if(temp!=null&&temp!=""&&temp.length>0){
            var lastItem = temp[(temp.length-1)];
            //临时调级  开始
            if(suppliersInfo.tempSyntheticGrade>0 && suppliersInfo.tempSyntheticGrade>lastItem.lsLevel){
                var lastItemTemp = [];
                $.each(levelItems,function (i,item) {
                    if(lastItemTemp.lsLevel ==suppliersInfo.tempSyntheticGrade ){
                        lastItemTemp.push(item);
                    }
                });
                if(lastItemTemp!=null&&lastItemTemp!=""&&lastItemTemp.length>0){
                    lastItem = lastItemTemp[0];
                }
            }//临时调级 结束
            orderItem.levelToActurl = lastItem.lsLevel;//实际等级
            orderItem.valueToStandard = lastItem.lsStandardOne;//当前等级标准
            if(suppliersOrderStatistics.orderCount >= lastItem.lsStandardOne){
                //差值（当前）
                orderItem.valueToDiff = '<span class="cGreen">'+(suppliersOrderStatistics.orderCount-lastItem.lsStandardOne)+'</span>';
            }else{
                //差值（当前）
                orderItem.valueToDiff = '<span class="cRed">'+(suppliersOrderStatistics.orderCount-lastItem.lsStandardOne)+'</span>';
            }
        }
        //获取当前等级&标准   获取订单等级|标准  结束

        //获取 金额 等级 | 标准  开始
        if(moneyTemp!=null&&moneyTemp!=""&&moneyTemp.length>0){
            var lastItem = moneyTemp[(moneyTemp.length-1)];
            //临时调级  开始
            if(suppliersInfo.tempSyntheticGrade>0 && suppliersInfo.tempSyntheticGrade>lastItem.lsLevel){
                var lastItemTemp = [];
                $.each(levelItems,function (i,item) {
                    if(lastItemTemp.lsLevel ==suppliersInfo.tempSyntheticGrade ){
                        lastItemTemp.push(item);
                    }
                });
                if(lastItemTemp!=null&&lastItemTemp!=""&&lastItemTemp.length>0){
                    lastItem = lastItemTemp[0];
                }
            }//临时调级 结束
            moneyItem.levelToActurl = lastItem.lsLevel;//实际等级
            moneyItem.valueToStandard = lastItem.lsStandardTwo;//当前等级标准
            if(suppliersOrderStatistics.orderCount >= lastItem.lsStandardTwo){
                //差值（当前）
                moneyItem.valueToDiff = '<span class="cGreen">'+Number(((suppliersOrderStatistics.totalMoney/10000)-lastItem.lsStandardTwo)).toFixed(2)+'</span>';
            }else{
                //差值（当前）
                moneyItem.valueToDiff = '<span class="cRed">'+Number(((suppliersOrderStatistics.totalMoney/10000)-lastItem.lsStandardTwo)).toFixed(2)+'</span>';
            }
        }
        //获取 金额 等级 | 标准  结束
        debugger
        //获取 积分 等级 | 标准  开始
        if(levelTemp!=null&&levelTemp!=""&&levelTemp.length>0){
            var lastItem = levelTemp[(levelTemp.length-1)];
            //临时调级  开始
            if(suppliersInfo.tempSyntheticGrade>0 && suppliersInfo.tempSyntheticGrade>lastItem.lsLevel){
                var lastItemTemp = [];
                $.each(levelItems,function (i,item) {
                    if(lastItemTemp.lsLevel ==suppliersInfo.tempSyntheticGrade ){
                        lastItemTemp.push(item);
                    }
                });
                if(lastItemTemp!=null&&lastItemTemp!=""&&lastItemTemp.length>0){
                    lastItem = lastItemTemp[0];
                }
            }//临时调级 结束
            scoreItem.levelToActurl = lastItem.lsLevel;//实际等级
            scoreItem.valueToStandard = lastItem.lsStandardThree;//当前等级标准
            if(suppliersOrderStatistics.orderCount >= lastItem.lsStandardThree){
                //差值（当前）
                scoreItem.valueToDiff = '<span class="cGreen">'+(score-lastItem.lsStandardThree)+'</span>';
            }else{
                //差值（当前）
                scoreItem.valueToDiff = '<span class="cRed">'+(score-lastItem.lsStandardThree)+'</span>';
            }
        }
        //获取 积分 等级 | 标准  结束

        //获取 工龄 等级 | 标准  开始
        if(workMonth!=null&&workMonth!=""&&workMonth.length>0){
            var lastItem = workMonth[(workMonth.length-1)];
            //临时调级  开始
            if(suppliersInfo.tempSyntheticGrade>0 && suppliersInfo.tempSyntheticGrade>lastItem.lsLevel){
                var lastItemTemp = [];
                $.each(levelItems,function (i,item) {
                    if(lastItemTemp.lsLevel ==suppliersInfo.tempSyntheticGrade ){
                        lastItemTemp.push(item);
                    }
                });
                if(lastItemTemp!=null&&lastItemTemp!=""&&lastItemTemp.length>0){
                    lastItem = lastItemTemp[0];
                }
            }//临时调级 结束
            workYearItem.levelToActurl = lastItem.lsLevel;//实际等级
            workYearItem.valueToStandard = lastItem.lsStandardFour;//当前等级标准
            if(suppliersOrderStatistics.orderCount >= lastItem.lsStandardFour){
                //差值（当前）
                workYearItem.valueToDiff = '<span class="cGreen">'+(month-lastItem.lsStandardFour)+'</span>';
            }else{
                //差值（当前）
                workYearItem.valueToDiff = '<span class="cRed">'+(score-lastItem.lsStandardFour)+'</span>';
            }
        }
        //获取 工龄 等级 | 标准  结束

        //获取下一等级 &  标准  开始
        //获取 订单 等级 | 差值
        var nextLevel = orderItem.levelToActurl+1;
        var nextItems = [];
        $.each(levelItems,function (i,item) {
            if(item.lsLevel == nextLevel){
                nextItems.push(item);
            }
        });
        if(nextItems!=null&&nextItems!=""&&nextItems!=undefined){
            var nextItem = nextItems[0];
            if(nextItem!=null&&nextItem!=undefined&&nextItem.lsLevel>=1){
                orderItem.levelToNext = nextLevel;//下一个等级
                if(suppliersOrderStatistics.orderCount == nextItem.lsStandardOne){
                    orderItem.valueToDiffNext = "0";//差值（下一个 等级）
                }else{
                    orderItem.valueToDiffNext = (suppliersOrderStatistics.orderCount-nextItem.lsStandardOne).toString();//差值（下一个 等级）
                }
            }
        }
        //获取下一等级 &  标准
        //获取 订单 等级 | 差值  结束

        //获取 金额 等级 | 标准  开始
        var moneyNextLevel = moneyItem.levelToActurl+1;
        var moneyNextItems = [];
        $.each(levelItems,function (i,item) {
            if(item.lsLevel == moneyNextLevel){
                moneyNextItems.push(item);
            }
        });
        if(moneyNextItems!=null&&moneyNextItems!=""&&moneyNextItems!=undefined){
            var moneyNextItem = moneyNextItems[0];
            if(moneyNextItem!=null&&moneyNextItem!=undefined&&moneyNextItem.lsLevel>=1){
                moneyItem.levelToNext = moneyNextLevel;//下一个等级
                if(suppliersOrderStatistics.totalMoney == moneyNextItem.lsStandardTwo*10000){
                    moneyItem.valueToDiffNext = "0";//差值（下一个 等级）
                }else{
                    moneyItem.valueToDiffNext = Number((suppliersOrderStatistics.totalMoney/10000)-moneyNextItem.lsStandardTwo).toFixed(2);//差值（下一个 等级）
                }
            }
        }
        //获取 金额 等级 | 标准  结束

        //获取 积分 等级 | 标准  开始
        var scoreNextLevel = scoreItem.levelToActurl+1;
        var scoreNextItems = [];
        $.each(levelItems,function (i,item) {
            if(item.lsLevel == scoreNextLevel){
                scoreNextItems.push(item);
            }
        });
        if(scoreNextItems!=null&&scoreNextItems!=""&&scoreNextItems!=undefined){
            var scoreNextItem = scoreNextItems[0];
            if(scoreNextItem!=null&&scoreNextItem!=undefined&&scoreNextItem.lsLevel>=1){
                scoreItem.levelToNext = scoreNextLevel;//下一个等级
                if(score == scoreItem.lsStandardThree){
                    scoreItem.valueToDiffNext = "0";//差值（下一个 等级）
                }else{
                    scoreItem.valueToDiffNext = (score - scoreItem.lsStandardThree);//差值（下一个 等级）
                }
            }
        }
        //获取 积分 等级 | 标准  结束

        //获取 工龄 等级 | 标准  开始
        var workMonthNextLevel = workYearItem.levelToActurl+1;
        var workMonthNextItems = [];
        $.each(levelItems,function (i,item) {
            if(item.lsLevel == workMonthNextLevel){
                workMonthNextItems.push(item);
            }
        });
        if(workMonthNextItems!=null&&workMonthNextItems!=""&&workMonthNextItems!=undefined){
            var workMonthNextItem = workMonthNextItems[0];
            if(workMonthNextItem!=null&&workMonthNextItem!=undefined&&workMonthNextItem.lsLevel>=1){
                workYearItem.levelToNext = workMonthNextLevel;//下一个等级
                if(month == workMonthNextItem.lsStandardFour){
                    workYearItem.valueToDiffNext = "0";//差值（下一个 等级）
                }else{
                    workYearItem.valueToDiffNext = (month - workMonthNextItem.lsStandardFour);//差值（下一个 等级）
                }
            }
        }
        //获取 工龄 等级 | 标准  结束

        //获取等级
        var nowLevel = orderItem.levelToActurl<moneyItem.levelToActurl?orderItem.levelToActurl:moneyItem.levelToActurl;
        nowLevel = nowLevel < scoreItem.levelToActurl?nowLevel:scoreItem.levelToActurl;
        nowLevel = nowLevel < workYearItem.levelToActurl?nowLevel:workYearItem.levelToActurl;
        result.level = suppliersInfo.tempSyntheticGrade>0?suppliersInfo.tempSyntheticGrade:suppliersInfo.qualityLevel;

        //升级标准
        var nextLevelItems = [];
        $.each(levelItems,function (i,item) {
            if(item.lsLevel == (result.level+1)){
                nextLevelItems.push(item);
            }
        });
        if(nextLevelItems!=null&&nextLevelItems!=""&&nextLevelItems!=undefined){
            var nextLevelItem = nextLevelItems[0];
            if(nextLevelItem!=null&&nextLevelItem!=undefined&&nextLevelItem.lsLevel>=1){
                result.levelToNext = nextLevelItem.lsLevel;
                //订单
                if(suppliersOrderStatistics.orderCount>=nextLevelItem.lsStandardOne){
                    orderItem.valueToDiffToUpgrade = '<span class="cGreen">'+(suppliersOrderStatistics.orderCount-nextLevelItem.lsStandardOne)+'</span>';//差值 升级
                }else{
                    orderItem.valueToDiffToUpgrade = '<span class="cRed">'+(suppliersOrderStatistics.orderCount-nextLevelItem.lsStandardOne)+'</span>';//差值 升级
                }
                //金额
                if(suppliersOrderStatistics.totalMoney >= (nextLevelItem.lsStandardTwo*10000)){
                    moneyItem.valueToDiffToUpgrade = '<span class="cGreen">'+Number((suppliersOrderStatistics.totalMoney/10000)-nextLevelItem.lsStandardTwo).toFixed(2)+'</span>';//差值 升级
                }else{
                    moneyItem.valueToDiffToUpgrade = '<span class="cRed">'+Number((suppliersOrderStatistics.totalMoney/10000)-nextLevelItem.lsStandardTwo).toFixed(2)+'</span>';//差值 升级
                }
                //积分
                if(score>=nextLevelItem.lsStandardThree){
                    scoreItem.valueToDiffToUpgrade ='<span class="cGreen">'+(score-nextLevelItem.lsStandardThree)+'</span>';
                }else {
                    scoreItem.valueToDiffToUpgrade ='<span class="cRed">'+(score-nextLevelItem.lsStandardThree)+'</span>';
                }
                //工龄
                if(month>=nextLevelItem.lsStandardFour){
                    workYearItem.valueToDiffToUpgrade = '<span class="cGreen">'+(month-nextLevelItem.lsStandardFour)+'</span>';
                }else{
                    workYearItem.valueToDiffToUpgrade = '<span class="cRed">'+(month-nextLevelItem.lsStandardFour)+'</span>';
                }
            }else{
                result.levelToNext = 0;
            }
        }
    }
    result.orderItem =orderItem;
    result.moneyItem =moneyItem;
    result.scoreItem =scoreItem;
    result.workYearItem = workYearItem;
    return result;
}

//初始化字符串
//affair.jsp   affairLevel.jsp
function initEmptyToStr(str){

    var result;

    if(str!=null && str!=""&&str!=undefined){
        result = str;
    }else{
        result = "--";
    }
    return result;
}
//转换配送状态
//financeOrderRelevant.jsp
function tsOrderState(orderState) {
    var result = "";
    switch (orderState){
        case 1:
            result="待接单";
            break;
        case 2:
            result="待配送";
            break;
        case 4:
            result="配送中";
            break;
        case 8:
            result="待收货";
            break;
        case 16:
            result="待支付";
            break;
        case 32:
            result="支付中";
            break;
        case 64:
            result="已支付";
            break;
        default:
            break;
    }
    return result;
}

//获取材料价格超高的系数
//shopFixed.jsp
function getMaterialToHighPrice() {
    return 1.2;
}


//region 获取材料商加盟类型 根据加盟等级
/// <summary>
/// 获取材料商加盟类型 根据加盟等级
/// </summary>
/// <param name="joinLevel">加盟等级</param>
/// <returns></returns>
function getUserJoinTitleToJoinLevel(joinLevel) {
    var result = "";
    switch (joinLevel){
        case 1:
            result="品牌店";
            break;
        case 2:
            result="专卖店";
            break;
        case 3:
            result="旗舰店";
            break;
        default:
            result = "--";
            break;
    }
    return result;
}
//转换材料等级 shopFixed.jsp
function tsMaterialLevel(level) {
    var result;
    switch (level){
        case 1:
            result="A";
            break;
        case 2:
            result="B";
            break;
        case 4:
            result="C";
            break;
        default:
            result="-";
            break;
    }
    return result;
}

//时间格式转换 yyyy-MM-dd
function formatDateTime(inputTime) {
    if(inputTime==null||inputTime==undefined){
        return "--";
    }
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return y + '-' + m + '-' + d;
}

//时间格式转换 yyyy-MM-dd HH:mm:ss
function formatTime(inputTime) {
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var hh=date.getHours();
    var mm=date.getMinutes();
    var ss=date.getSeconds();
    return y + '-' + m + '-' + d  + hh+':'+mm+':'+ss;
}

//签单计算百分比,过程，结果二段用到
function percentNum(expectedOrderMoneyToDis, expectedOrderMoney) {
    //预计订单配送额，    预计订单总额
    if (expectedOrderMoney != null && expectedOrderMoney != 0) {
        return (Math.round(expectedOrderMoneyToDis / expectedOrderMoney * 10000) / 100.00 + "%"); //小数点后两位百分比
    } else {
        return "- -";
    }
}
//获取指定时间的前几天的某一个时间
function getPreviousCertainTime(pointTime, operate) {
    var d=new Date(pointTime);
    return new Date(d.getFullYear(),d.getMonth(),d.getDate()-operate);
}