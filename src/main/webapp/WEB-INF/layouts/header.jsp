<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" media="screen" href="${ctx}/demo/css/style.css" />

<script type="text/javascript" src="${ctx}/demo/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="${ctx}/demo/js/custom/general.js"></script>
</head>
<body class="bodygrey">
<div class="headerspace"></div>
<div class="header">
    <!--logo-->
	<a href=""><img src="${ctx}/demo/images/logo2.png" alt="Logo" /></a>
    
<!--      二级菜单 -->
    <div class="tabmenu">
    	<ul>
    		<c:forEach items="${towMenuList}" var="towMenu" varStatus="status">
    			<li id="${towMenu.id}li" onclick="changeTable('${ctx}/${towMenu.newUrl}${towMenu.parentId}?titleName=${towMenu.name}','${towMenu.id}li')" ><a href="#" class="dashboard"><span>${towMenu.name}</span></a></li>
    		</c:forEach>
        </ul>
    </div>
   <!-- tabmenu-->
    
    <div class="accountinfo">
    	<img src="${ctx}/demo/images/avatar.png" alt="Avatar" />
        <div class="info">
        	<h3><shiro:principal/></h3>
            <small>您好！今天是<span class="land-time">${date}</span></small>
            <p>
            	<a href="${ctx}/login/changePwdUI" title="修改密码">修改密码</a>
	            <a href="${ctx}/login/help" title="帮助">帮助</a>
	            <a href="${ctx}/logout" title="安全退出">安全退出</a>
            </p>
        </div><!--info-->
    </div><!--accountinfo-->
</div><!--header-->

<div class="sidebar">
	<div id="accordion">
        <h3 class="open">主导航</h3>
        <div class="content" style="display: block;">
        	<ul class="leftmenu">
        		<li id="firstLi" class="current"><a href="${ctx}/login/index?newCssClass=firstLi" class="home">首页</a></li>
        		<c:forEach items="${menuList}" var="menu">
                    <li id="${menu.id}li" onclick="gotoWh('${ctx}/${menu.newUrl}${menu.id}','${menu.id}li')">
<%--                     	<a id="${menu.id}a" href="${ctx}/${menu.newUrl}${menu.id}" >${menu.name}</a> --%>
                    	<a id="${menu.id}a" href="#">${menu.name}</a>
                    </li>
                 </c:forEach>
            	<!--
            	<li class="current"><a href="dashboard.html" class="home">首页</a></li>
                <li><a href="forms.html" class="form">市场管理</a></li>
                <li><a href="tables.html" class="table">商家管理</a></li>
                <li><a href="gallery.html" class="gallery">商品管理</a></li>
                <li><a href="grid.html" class="grid">客户管理</a></li>
                <li><a href="calendar.html" class="calendar">售前管理</a></li>
                <li><a href="buttons.html" class="buttons">二维码信息管理</a></li>
                <li><a href="editor.html" class="editor">信息发布</a></li>
                <li><a href="filemanager.html" class="file">报表管理</a></li>
                <li><a href="invoice.html" class="form">公司信息管理</a></li>
                -->
            </ul>
        </div>
        <h3 class="open"></h3>
	</div>
    
</div><!--leftmenu-->

</body>
<script type="text/javascript">
var pid = null;
function gotoWh(url,id){
// 	if(pid == null){
// 		jQuery("#firstLi").removeClass();
// 	}else{
// 		jQuery("#"+pid+"").removeClass();
// 	}
// 	pid = id;
// 	var obj = jQuery("#"+id+"");
// 	obj.addClass("current");

	window.open(url,'a_iframe');
	url = url + "?newCssClass="+id;
	window.location.href = url;
}
function getClass(){
// 	alert("${param.newCssClass}");
	if("${param.newCssClass}" != "firstLi"){
		jQuery("#firstLi").removeClass();
		jQuery("#${param.newCssClass}").addClass("current");
	}
}
// getClass();
function changeTable(url,id){
	alert(id);
	if(pid == null){
		jQuery("#firstLi").removeClass();
	}else{
		jQuery("#"+pid+"").removeClass();
	}
	pid = id;
	var obj = jQuery("#"+id+"");
	obj.addClass("current");
	jQuery("#"+id+"").addClass("current");
	window.location.href = url;
}


</script>
</html>