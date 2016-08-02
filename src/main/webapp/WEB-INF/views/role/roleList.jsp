<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
	
	$("#roleSubmit").click(function(){
		 $('#role').submit();
	});
// 	$("input[type='checkbox']").click(function() {
//         if ($(this).prop("checked") == true) {
//             $("input[type='checkbox']").attr("checked", false);
//             $(this).prop("checked", true);
//         }
//     });
	
	$("#updateRole").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var roleId = $("input[type='checkbox']:checked").val();
			$("#updateRole").attr("href",'${ctx}/role/update/'+roleId+'?parentId=' + parentId + "&titleName=编辑角色");
		}
    });
	
	$("#deleteRole").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var roleId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除角色吗?');
			if(boolconfirm) {
				$("#deleteRole").attr("href",'${ctx}/role/delete/'+roleId+'?parentId=' + parentId);
			}
		}
    });
	
	$("#assignUserInfo").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var roleId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定分配用户吗?');
			if(boolconfirm) {
				$("#assignUserInfo").attr("href",'${ctx}/role/assignUserInfo/'+roleId+'?parentId=' + parentId);
			}
		}
    });
	
	$("#assignButton").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var roleId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定分配按钮吗?');
			if(boolconfirm) {
				$("#assignButton").attr("href",'${ctx}/role/assignButton/'+roleId+'?parentId=' + parentId + "&titleName=编辑按钮权限");
			}
		}
    });
	
	$("#assignMenu").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var roleId = $("input[type='checkbox']:checked").val();
			var roleName = $("input[type='checkbox']:checked").attr("title");
// 			var boolconfirm=confirm('确定分配菜单吗?');
			if(confirm('确定分配菜单吗?')) {
				$("#assignMenu").attr("href",'${ctx}/role/assignMenu/'+roleId+'?parentId=' + parentId + "&titleName=给 "+roleName+" 角色分配菜单");
			}
		}
    });
}); 
</script>
</head>
<body>
    <div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span>角色管理</span>
    </div><!-- breadcrumbs -->
	<!--角色管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="role" name="roleForm" style="margin-top:23px; margin-left:16px;">
				<label>名称</label> <input class="sf" type="text" id="name" name="name" value="${name}" /> 
				<label for="occupation">状态</label>
				<c:set var="parentMenuId" value="${parentMenuId}" />
				<select name="state" id="state">
					<option value="">-----请选择-----</option>
					<option value="0" <c:if test="${state eq '0'}">selected="selected"</c:if>>有效</option>
					<option value="1" <c:if test="${state eq '1'}">selected="selected"</c:if>>无效</option>
				</select>
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="roleSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			</form> 
        <div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
            <a id="deleteRole" class="button delete"><span>删除</span></a>
            <a id="assignButton" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/tag.png" class="mgright5" alt=""> <span>分配按钮</span></a>
            <a id="assignMenu" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/check.png" class="mgright5" alt=""> <span>分配菜单</span></a>
            <a id="assignUserInfo" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/check.png" class="mgright5" alt=""> <span>分配用户</span></a>
            <a id="updateRole" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>修改</span></a>
            <a href="${ctx}/role/add?titleName=新增角色" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>新增</span></a>     
        </div><!--sTableOptions-->
        
        <div id="listview" class="sTableWrapper" style=" margin-left:16px;">       
            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
                <colgroup>
                    <col class="head0" width="3%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="20%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="10%" />
                </colgroup>
                <tr>
                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
                    <td width="10%">名称</td>
					<td width="20%">创建人</td>
					<td width="10%">说明</td>
					<td width="10%">状态</td>
                </tr>
            </table>
         </div>
         <div class="sTableWrapper" style=" margin-left:16px;">
			<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
				<colgroup>
					<col class="con0" width="3%" />
					<col class="con1" width="10%" />
					<col class="con0" width="20%" />
					<col class="con1" width="10%" />
					<col class="con0" width="10%" />
				</colgroup>
			  <c:forEach items="${roleList}" var="role">
			  	<tr>
					<td><input id="roleChecked" name="" type="checkbox" value="${role.id}" title="${role.name}"/></td>
					<td>${role.name}</td>
					<td>${role.createrName}</td>
					<td>${role.remark}</td>
					<td>
					 <c:if test="${role.isDeleteFlag eq false}">有效</c:if>
		             <c:if test="${role.isDeleteFlag eq true}">无效</c:if>
					</td>
				</tr>
			  </c:forEach>
				
			</table>
        </div>
        <tags:pagination page="${pageData}" paginationSize="10"/>
    </div>
    
    <br clear="all" />
    
</div><!--maincontent-->
<br />
	
</body>
</html>