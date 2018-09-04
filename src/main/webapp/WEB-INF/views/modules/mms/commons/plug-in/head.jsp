<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
    <title>${fns:getConfig('productName')}</title>
    <meta charset="utf-8">
    <%-- 浏览器兼容模式方案,保证IE浏览器以最高版本运行--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <%-- 使用谷歌内核渲染--%>
    <meta name="renderer" content="webkit">
    <%-- Expires(期限) 设定网页的到期时间必须到服务器上重新调阅--%>
    <meta http-equiv="Expires" content="0">
    <%-- 指示请求或响应消息不能缓存 --%>
    <meta http-equiv="Cache-Control" content="no-store">
    <%-- 用于防止重要的信息被无意的发布。在请求消息中发送将使得请求和响应消息都不使用缓存。 --%>
    <meta http-equiv="Cache-Control" content="no-cache">
    <link href="${ctxStatic}/images/mms-ico.png?${verStatic}" rel="icon"/>
    <!--公共样式-->
    <link rel="stylesheet" href="${ctxStatic}/css/rxui1.0.2.min.css?${verStatic}">
    <link rel="stylesheet" href="${ctxStatic}/css/frame.min.css?${verStatic}">
    <link rel="stylesheet" href="${ctxStatic}/css/mend-msg.css?${verStatic}" type="text/css" >
    <script src="${ctxStatic}/js/jquery-1.11.3.min.js?${verStatic}" type="text/javascript" charset="utf-8"></script>
   <%--basePath: 项目基础路径, ctxStatic: 项目静态资源路径,gcApiSite: 工程接口服务站点 --%>
    <script type="text/javascript">var basePath = '${basePath}',ctxStatic='${ctxStatic}',gcApiSite = '${gcApiSite}',userCityID = '${user.u_diqu}';</script>