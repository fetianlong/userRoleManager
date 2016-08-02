<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
$(document).ready(function () {
	$("input[type='checkbox']").click(function() {
	    if ($(this).prop("checked") == true) {
	        $("input[type='checkbox']").attr("checked", false);
	        $(this).prop("checked", true);
	    }
	});
	
	$("#add").click(function() {
		if($("input[name='unassignPermissionChecked']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var roleId = $('#roleId').val();
			var permissionId = $("input[name='unassignPermissionChecked']:checked").val();
			var parentId = $('#parentId').val();
			$("#add").attr("href",'${ctx}/role/assignPermissionToRole/'+roleId+'?permissionId=' + permissionId + "&parentId="+parentId);
		}
    });
	
	$("#remove").click(function() {
		if($("input[name='assignPermissionChecked']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var roleId = $('#roleId').val();
			var rolePermissionId = $("input[name='assignPermissionChecked']:checked").val();
			var parentId = $('#parentId').val();
			$("#remove").attr("href",'${ctx}/role/removePermissionFromRole/'+rolePermissionId+'?roleId=' + roleId + "&parentId="+parentId);
		}
    });
	
	$("#close").click(function() {
		var parentId = $('#parentId').val();
		$("#close").attr("href",'${ctx}/role/close/'+parentId);
    });
});
</script>
</head>
<body>
	<div>
		<h3>分配按钮</h3>
		<div class="nodis">
			<h4>未分配按钮</h4>
			<div class="nodis-info"  style="overflow-x: auto; overflow-y: auto;">
			<input type="hidden" value="${roleId}" id="roleId"/>
			<input type="hidden" value="${parentId}" id="parentId"/>
				<table class="sj-tab" style="margin-left: 0px;margin-top: 0px;width: 100%" cellspacing="0" cellpadding="0">
					<tr>
						<td ><!-- <input name="" type="checkbox" value="" /> --></td>
						<td >用户名</td>
						<td >加盟商</td>
					</tr>
					<c:forEach items="${unassignPermissionList}" var="unassignPermission">
		  				<tr>
							<td><input  name="unassignPermissionChecked" type="checkbox" value="${unassignPermission.id}" /></td>
							<td>${unassignPermission.name}</td>
							<td>${unassignPermission.value}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="user-btn">
			<div class="addto"><a id="add">添加</a></div>
			<div class="remove"><a id="remove">移除</a></div>
			<div class="clo"><a id="close">关闭</a></div>

		</div>
		<div class="otherno" style="overflow-x: auto; overflow-y: auto;">
			<h4>已分配按钮</h4>
			<div class="otherno-info">
				<table class="sj-tab" style="margin-left: 0px;margin-top: 0px;width: 100%" cellspacing="0" cellpadding="0">
					<tr>
						<td ><!-- <input name="" type="checkbox" value="" /> --></td>
						<td >用户名</td>
						<td >加盟商</td>
					</tr>
					<c:forEach items="${assignPermissionList}" var="assignPermission">
		  				<tr>
							<td><input name="assignPermissionChecked" type="checkbox" value="${assignPermission.id}" /></td>
							<td>${assignPermission.name}</td>
							<td>${assignPermission.value}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>