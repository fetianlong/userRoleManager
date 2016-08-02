<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../home/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
body {background-color: white;}
</style>
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
		if($("input[name='unassignUserChecked']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var roleId = $('#roleId').val();
			var userInfoId = $("input[name='unassignUserChecked']:checked").val();
			$("#add").attr("href",'${ctx}/role/assignUserToRole/'+roleId+'?userInfoId=' + userInfoId + "&parentId=${parentId}");
		}
    });
	
	$("#remove").click(function() {
		if($("input[name='assignUserChecked']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var roleId = $('#roleId').val();
			var roleUserInfoId = $("input[name='assignUserChecked']:checked").val();
			$("#remove").attr("href",'${ctx}/role/removeUserFromRole/'+roleUserInfoId+'?roleId=' + roleId + "&parentId=${parentId}");
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
		<h3>分配用户</h3>
		<div class="nodis">
			<h4>未分配用户</h4>
			<div class="nodis-info"  style="overflow-x: auto; overflow-y: auto;">
			<input type="hidden" value="${roleId}" id="roleId"/>
			<input type="hidden" value="${parentId}" id="parentId"/>
				<table class="sj-tab" style="margin-left: 0px;margin-top: 0px;width: 100%" cellspacing="0" cellpadding="0">
					<tr>
						<td ><!-- <input name="" type="checkbox" value="" /> --></td>
						<td >用户名</td>
						<td >加盟商</td>
					</tr>
					<c:forEach items="${unassignUserList}" var="unassignUser">
		  				<tr>
							<td><input  name="unassignUserChecked" type="checkbox" value="${unassignUser.id}" /></td>
							<td>${unassignUser.userName}</td>
							<td>${unassignUser.franchiseesName}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="user-btn">
			<p>
	        <a id="add" class="iconlink" href=""><img alt="" class="mgright5" src="${ctx}/demo/images/icons/small/white/plus.png"> <span>添加</span></a>
	        <a id="remove" class="iconlink" href=""><img alt="" class="mgright5" src="${ctx}/demo/images/icons/small/white/minus.png"> <span>移除</span></a>
	    	<a id="close" class="iconlink" href=""><img alt="" class="mgright5" src="${ctx}/demo/images/icons/small/white/close.png"> <span>返回</span></a>
			</p>
			<!-- 
			<div class="addto"><a id="add">添加</a></div>
			<div class="remove"><a id="remove">移除</a></div>
			<div class="clo"><a id="close">关闭</a></div>
			 -->
		</div>
		<div class="otherno" style="overflow-x: auto; overflow-y: auto;">
			<h4>已分配用户</h4>
			<div class="otherno-info">
				<table class="sj-tab" style="margin-left: 0px;margin-top: 0px;width: 100%" cellspacing="0" cellpadding="0">
					<tr>
						<td ><!-- <input name="" type="checkbox" value="" /> --></td>
						<td >用户名</td>
						<td >加盟商</td>
					</tr>
					<c:forEach items="${assignUserList}" var="assignUser">
		  				<tr>
							<td><input name="assignUserChecked" type="checkbox" value="${assignUser.id}" /></td>
							<td>${assignUser.userName}</td>
							<td>${assignUser.franchiseesName}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>