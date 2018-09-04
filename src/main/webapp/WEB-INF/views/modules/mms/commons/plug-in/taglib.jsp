<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"  %>
<%-- 引入jstl/fns/fn标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- basePath项目路径/verStatic插件版本域变量/resStatic静态资源路径/项目路径--%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="ctxStatic" value="${basePath}/static/mms"/>
<c:set var="verStatic" value="2018829144929123" />
<%--工程接口服务站点--%>
<c:set var="gcApiSite" value="${fns:getConfig('gc.api.site')}" />
<%--上传图片服务接口--%>
<c:set var="picUploadUrl" value="http://img9.rxjy.com:9120/?action=uploadimage&isAddTextWarterMark=False&isAddImageWaterMark=False&savePath=Engine/PMPhoto/&imageSpecifications=800X800;800X600;400X400;150X150;60X60;280X200;150X220;200X200;" />
