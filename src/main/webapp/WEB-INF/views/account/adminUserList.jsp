<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div class="row">
		<div class="span4 offset7">
			<a href=""></a>
			<form class="form-search" action="#">
				<label>名称：</label> <input type="text" name="search_LIKE_title" class="input-medium" value="${param.search_LIKE_title}"> 
				<button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
		   <a href="${ctx}/role">角色管理</a>
	    </div>
	    <tags:sort/>
	</div>
	<dic:select parentCode="sex"/>
	<dic:write code="man"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>用户名</th><th>MAC地址</th><th>所属</th><th>注册时间</th><th>状态</th><th>管理</th></tr></thead>
		<tbody>
		<c:forEach items="${userList}" var="user">
			<tr>
				<td><a href="${ctx}/userInfo/update/${user.id}">${user.userName}</a></td>
				<td>${user.maces}
					
				</td>
				<td>${user.sellerName}</td>
				<td>
					${user.createDateTime}
<%-- 					<fmt:formatDate value="${user.createDateTime}" pattern="yyyy年MM月dd日  HH时mm分ss秒" /> --%>
				</td>
				<td>
					<c:if test="${user.state eq 1}">有效</c:if>
					<c:if test="${user.state eq 2}">冻结</c:if>
				</td>
				<shiro:hasPermission name="student:delete">
					<td><a href="${ctx}/userInfo/delete/${user.id}">删除</a></td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${pageData}" paginationSize="15"/>
	
</body>
</html>
