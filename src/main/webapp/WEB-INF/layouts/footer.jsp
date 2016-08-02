<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
		<c:if test="${!empty pageData}">
               <tags:pagination page="${pageData}" paginationSize="15"/>
        </c:if>
    <!--内容结束-->    

