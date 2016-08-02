<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/userInfo/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${user.id}"/>
		<fieldset>
			<legend><small>用户管理</small></legend>
			<div class="control-group">
				<label class="control-label">用户名:</label>
				<div class="controls">
					<input type="text" id="name" name="name" value="${user.userName}" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">姓名:</label>
				<div class="controls">
					<input type="text" id="realname" name="realname" value="${user.realname}" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">身份证:</label>
				<div class="controls">
					<input type="text" id="idnumber" name="idnumber" value="${user.idnumber}" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">联系电话:</label>
				<div class="controls">
					<input type="text" id="telephone" name="telephone" value="${user.telephone}" class="input-large required"/>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">MAC地址:</label>
				<div class="controls">
					<input type="text" id="maces" name="maces" value="${user.maces}" class="input-large required"/>
				</div>
			</div>
			
			
			<div class="control-group">
				<label class="control-label">注册日期:</label>
				<div class="controls">
					<span class="help-inline" style="padding:5px 0px">${user.createDateTime}</span>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
