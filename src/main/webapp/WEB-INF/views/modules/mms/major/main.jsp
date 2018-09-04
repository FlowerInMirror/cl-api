<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<div class="tc-center fl">
    <%--二段导航--%>
    <ul class="clearfix uiTab9 j_uiTab9">
        <li class="uiTab9-active">类别</li>
    </ul>

    <%--二段内容开始--%>
    <div id="j-tc-center-content" class="pr10">
        <!--类别-->
        <div class="uiTab9Con" id="CategoryTwo"></div>
    </div>
</div>

<div class="tc-right relative">

    <!--项三段弹出层-->
    <div class="layerRtb CategoryAlert hide"></div>

    <div class="tc-righttop-tab j-tc-righttop-tab relative dailyrgt-top-goback">
        <%--<a href="javascript:" class="fr mt4" onclick="history.go(-1);"><i class="fl mr10"></i>返回</a>--%>
    </div>
    <div class="tc-right-top clearfix relative">
        <div class="fl needdealt">
            <ul>
                <%--<li class="bgRed">待办一</li>--%>
                <%--<li class="bgOrange">待办二</li>--%>
                <%--<li class="bgGreen">待办三</li>--%>
            </ul>
        </div>
        <div class="visitbox">
            <div class="visit thinScroll" id="j-visit">
            </div>
        </div>
    </div>

    <div class="tc-right-bottom pa10 relative">
        <div class="tc-right-bottom-left relative">
            <ul class="topdaily">
                <li>
                    <a class="topdailybtn" href="javascript:">预留</a>
                </li>
            </ul>
            <div class="dailyrgt-botradio">
                <div class="mb10">
                    <label class="uiRadio12 cGreen"><input value="1" type="radio" name="a" class="uiRadio12-input">正常</label> <label class="uiRadio12 cOrange"><input value="2" type="radio" name="a" class="uiRadio12-input">异常</label> <label class="uiRadio12 cRed"><input value="3" type="radio" name="a" class="uiRadio12-input">问题</label>
                </div>
            </div>
            <div class="visi-text-content hide">
                <textarea placeholder="请输入回访内容" class="hf-textarea mb10"></textarea>
                <input class="hf-submit hf-submitBtn" type="submit" value="提交">
            </div>
        </div>

        <div class="EventpenaltyDiv">
            <a href="javascrpit:;" class="EventpenaltyBtn">预留</a>
        </div>
    </div>

</div>