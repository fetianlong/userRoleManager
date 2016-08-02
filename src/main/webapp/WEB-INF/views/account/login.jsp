<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${ctx}/static/styles/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<title>欢迎使用悦迪云管理平台</title>

</head>

<body class="right_bg2">
  <form id="loginForm" action="${ctx}/login" method="post">
	<div class="title3">
    	<div class="logo3"><img src="${ctx}/static/images/dlyt_03.png" /></div>
    </div>
	<div class="dlk">
	<%
	String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	if(error != null){
	%>
		<label style="font-size:9pt;color:#FF0000;text-align:center;">
				登录失败，请重试.&nbsp;
		</label>
	<%
	}
	%>
		<div class="srk2"><input id="username" name="username" type="text" class="srk required" placeholder="请输入用户名"/></div>
        <div class="srk3"><input id="password" name="password" type="password" class="srk required" placeholder="请输入密码"/></div>
    	<input type="submit" class="dlan" value="登录" style="cursor:pointer;"/>
        <!-- <p class="wjmm"><a href="#">忘记密码</a></p> -->
  	</div>
  </form>
<script>
	$(document).ready(function() {
		$("#loginForm").validate();
	});
</script>
</body>
</html>