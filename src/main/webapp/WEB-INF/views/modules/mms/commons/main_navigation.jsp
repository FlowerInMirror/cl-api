<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!--主导航 开始-->
<%--根据角色ID,进行不同权限导航栏展示--%>
<c:choose>
	<%--材料 角色编码 902--%>
	<c:when test="${fns:getConfig('sys.material') eq user.juese}">
		<%@ include file="/WEB-INF/views/modules/mms/main/material/navigation.jsp" %>
	</c:when>
	<%--平台 角色编码 818--%>
	<c:otherwise>
		<%@ include file="/WEB-INF/views/modules/mms/main/admin/navigation.jsp" %>
	</c:otherwise>
</c:choose>
<!--主导航 结束-->