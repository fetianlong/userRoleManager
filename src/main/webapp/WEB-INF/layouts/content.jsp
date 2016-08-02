<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <!--内容-->
        <!--右半部分-->
   		<c:if test="${!empty towMenuList}">
   		 <!--二级菜单-->
                <div class="two-menu">
                    <ul>
                     <c:forEach items="${towMenuList}" var="menu" varStatus="status">
                    	<li onclick="gotoWh('${ctx}/${menu.newUrl}${menu.parentId}')" <c:if test="${status.index eq 0}">class="on"</c:if>><a href="${ctx}/${menu.newUrl}${menu.parentId}">${menu.name}</a></li>
                     </c:forEach>
                    </ul>
                </div>
     	</c:if>
		<script>
			function gotoWh(url){
				window.location.href = url;
				$(this).addClass("cur");
			}
		</script>