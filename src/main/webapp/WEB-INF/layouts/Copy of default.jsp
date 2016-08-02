<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<%-- <link href="${ctx}/static/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" /> --%>
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
 <%-- <link href="${ctx}/static/styles/default.css" type="text/css" rel="stylesheet" /> --%>
<link href="${ctx}/static/styles/index.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/styles/zTreeStyle.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/amcharts/css/style.css">

<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/showdate.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/js.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/datetime.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<%-- <script src="${ctx}/static/bootstrap/2.3.2/js/bootstrap.min.js" type="text/javascript"></script> --%>

<script type="text/javascript" src="${ctx}/static/amcharts/js/amcharts.js"></script>
<script type="text/javascript" src="${ctx}/static/amcharts/js/raphael.js"></script>


<sitemesh:head/>
</head>

<body>
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<%-- <div class="header">
    		<div class="header_r">
	            <a href="${ctx}/login/changePwdUI" class="account-a" title="修改密码"></a>
	            <a href="${ctx}/login/help" class="i-a" title="帮助"></a>
	            <a href="${ctx}/logout" class="back-a" title="安全退出"></a>
			</div>	
	        <div class="logo">
					<h1><img src="${ctx}/static/images/header-logo.png" alt="" title="" width="38" height="38" /></h1>
				</div>	
			<div class="header_l">
	         <div class="land-info">
	             <span class="user-name"><shiro:principal/></span>您好！今天是<span class="land-time">${date}</span> 
	         </div>
			</div>
	        
		</div>
		
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
        <c:if test="${!empty towMenuList}">
           <div class="two-menu">
               <ul>
                <c:forEach items="${towMenuList}" var="menu" varStatus="status">
               		<li onclick="gotoWh('${ctx}/${menu.newUrl}${menu.parentId}')" <c:if test="${status.index eq 0}">class="on"</c:if>><a href="${ctx}/${menu.newUrl}${menu.parentId}">${menu.name}</a></li>
                </c:forEach>
               </ul>
           </div>
     	</c:if>
     	<div class="menu2-info"> --%>


<%-- 		<%@ include file="/WEB-INF/layouts/left.jsp"%> --%>
<%-- 		<%@ include file="/WEB-INF/layouts/content.jsp"%> --%>
		<div id="content">
			<sitemesh:body/>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
<script type="text/javascript">
function gotoWh(url){
	window.location.href = url;
	$(this).addClass("cur");
}
</script>
</html>