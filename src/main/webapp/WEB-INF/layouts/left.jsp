<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <!--内容-->
    	<!--左半部分-->
    	<div class="cont_l">
        	<div class="nav">
            	<ul>
	                <c:forEach items="${menuList}" var="menu">
	                    <li <c:if test="${menu.name=='首页'}">class="cur"</c:if>  
	                    onclick="gotoWh('${ctx}/${menu.newUrl}<c:if test="${menu.name!='首页'}">${menu.id}</c:if>')">
	                    <img src="${ctx}/static/images/homeicon.png" alt="" />
	                    <a href="${ctx}/${menu.newUrl}<c:if test="${menu.name!='首页'}">${menu.id}</c:if>">${menu.name}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
		<script>
			function gotoWh(url){
				window.location.href = url;
				$(this).addClass("cur");
			}
		</script>