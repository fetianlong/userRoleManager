<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getNumber();
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
%>
<div class="sTableOptions" style="margin-left:16px;border:none; background:none;">
     <div class="pagination pgright" style=" margin-top:20px;margin-left:16px;">
     	<% if (page.hasPreviousPage()){%>
			<a href="?page=1&${searchParams}">首页</a>
			<a href="?page=${current-1}&${searchParams}">上一页</a>
         <%}else{%>
			<a class="first disabled" href="#">首页</a>
			<a class="prev disabled" href="#">上一页</a>
         <%} %>
         <c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                 <a class="current" href="?page=${i}&${searchParams}">${i}</a>
                </c:when>
                <c:otherwise>
                    <a href="?page=${i}&${searchParams}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if (page.hasNextPage()){%>
			<a class="next" href="?page=${current+1}&${searchParams}">下一页</a>
			<a class="last" href="?page=${page.totalPages}&${searchParams}">末页</a>
         <%}else{%>
			<a class="next disabled" href="#">下一页</a>
			<a class="last disabled" href="#">末页</a>
         <%} %>
         <!-- 
         <a class="first disabled">&laquo; First</a>
         <a class="prev disabled">&lsaquo; Prev</a>
         <a href="" class="current">1</a>
         <a href="">2</a>
         <a href="">3</a>
         <a href="">4</a>
         <a href="">5</a>
         <span>...</span>
         <a href="">18</a>
         <a href="" class="next">Next &rsaquo;</a>
         <a href="" class="last">Last &raquo;</a>
          -->
     </div>
</div>
<%-- 
<div class="pagination">
	<ul>
		 <% if (page.hasPreviousPage()){%>
               	<li><a href="?page=1&${searchParams}">首页</a></li>
                <li><a href="?page=${current-1}&${searchParams}">上一页</a></li>
         <%}else{%>
                <li class="page-cur"><a href="#">首页</a></li>
                <li><a href="#">上一页</a></li>
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <li class="active"><a href="?page=${i}&${searchParams}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="?page=${i}&${searchParams}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if (page.hasNextPage()){%>
               	<li><a href="?page=${current+1}&${searchParams}">下一页</a></li>
                <li><a href="?page=${page.totalPages}&${searchParams}">末页</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">下一页</a></li>
                <li class="disabled"><a href="#">末页</a></li>
         <%} %>

	</ul>
</div>
 --%>
