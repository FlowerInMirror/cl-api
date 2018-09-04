<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/head.jsp" %>
    <%--获取被过滤的页面head里面的内容--%>
    <sitemesh:write property='head' />
</head>
<body class="nav-vertical logo-c000000 bar-cffffff nav-black" data-class="nav-vertical">
    <%--置顶导航--%>
    <%@ include file="/WEB-INF/views/modules/mms/commons/roof_navigation.jsp" %>
    <%-- 主内容区--%>
    <div id="main_box">
    	<%--主导航 --%>
    	<%@ include file="/WEB-INF/views/modules/mms/commons/main_navigation.jsp" %>
        <!--主内容 begin -->
        <div id="main_content" class="relative aaa" style="margin-left: 180px;">
            <%--获取被过滤的页面body里面的内容--%>
            <sitemesh:write property='body'/>
        </div>
    </div>
    <%--基础脚本--%>
    <%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/script.jsp" %>
    <%--获取被过滤的页面footer-script里面的内容--%>
    <sitemesh:write property='footer-script'/>
</body>
</html>