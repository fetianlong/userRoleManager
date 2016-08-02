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
	$("#userInfoSubmit").click(function(){
		 $('#userInfo').submit();
	});
	$("#reset").click(function(){
		$("#userName").val("");
		$("#franchiseesId").val("");
	});
	
// 	$("input[type='checkbox']").click(function() {
//         if ($(this).prop("checked") == true) {
//             $("input[type='checkbox']").attr("checked", false);
//             $(this).prop("checked", true);
//         }
//     });
	
	$("#updateMenu").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var menuId = $("input[type='checkbox']:checked").val();
			$("#updateMenu").attr("href",'${ctx}/menu/update/'+menuId+'?parentId=' + parentId + "&titleName=编辑菜单");
		}
    });
	
	$("#deleteMenu").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var menuId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除该菜单吗?');
			if(boolconfirm) {
				$("#deleteMenu").attr("href",'${ctx}/menu/delete/'+menuId+'?parentId=' + parentId);
			}
		}
    });
	
	$("#menuSubmit").click(function(){
		 $('#menu').submit();
	});
	
	$("#reset").click(function(){
		$("#parentMenuId").val("");
		$("#name").val("");
	});
}); 
</script>
</head>
<body>
    <div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span>菜单管理</span>
    </div><!-- breadcrumbs -->
	<!--菜单管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="menu" name="menuForm" style="margin-top:23px; margin-left:16px;">
				<label for="occupation">父菜单名称</label>
				<c:set var="parentMenuId" value="${parentMenuId}" />
				<select name="parentMenuId" id="parentMenuId">
					<option value="">-----请选择-----</option>
				  <c:forEach items="${parentMenuList}" var="parentMenu">
					<option value="${parentMenu.id}" <c:if test="${parentMenu.id eq parentMenuId}">selected="selected"</c:if>>${parentMenu.name}</option>
				  </c:forEach>
				</select>
<%-- 				<label class="sj-name">菜单名称</label> <input class="sf" type="text" id="name" name="name" value="${name}" />  --%>
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="menuSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			</form> 
        <div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
            <a id="deleteMenu" class="button delete"><span>删除</span></a>
<%--             <a id="resetPassword" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/tag.png" class="mgright5" alt=""> <span>重置密码</span></a> --%>
<%--             <a id="recoverUserInfo" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/check.png" class="mgright5" alt=""> <span>恢复</span></a> --%>
<%--             <a id="freezeUserInfo" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/close.png" class="mgright5" alt=""> <span>冻结</span></a> --%>
            <a id="updateMenu" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>修改</span></a>
            <a href="${ctx}/menu/add?parentId=${parentId}&titleName=新增菜单" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>新增</span></a>     
        </div><!--sTableOptions-->
        
        <div id="listview" class="sTableWrapper" style=" margin-left:16px;">       
            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
                <colgroup>
                    <col class="head0" width="3%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="20%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="10%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="15%" />
                </colgroup>
                <tr>
                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
                    <td width="10%">名称</td>
					<td width="20%">URL</td>
					<td width="10%">顺序</td>
					<td width="10%">状态</td>
					<td width="10%">所属</td>
					<td width="15%">是否下发</td>
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
					<col class="con1" width="10%" />
					<col class="con0" width="15%" />
				</colgroup>
			  <c:forEach items="${menusList}" var="menu">
			  	<tr>
					<td><input id="menuChecked" name="" type="checkbox" value="${menu.id}" /></td>
					<td align="left">${menu.name}</td>
					<td>${menu.newUrl}</td>
					<td>${menu.ordIndex}</td>
					<td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
					 <c:if test="${menu.isDeleteFlag eq false}">有效</c:if>
		             <c:if test="${menu.isDeleteFlag eq true}">冻结</c:if>
					</td>
					<td>
					 <c:if test="${menu.menuType eq 0}">商家</c:if>
		             <c:if test="${menu.menuType eq 1}">加盟商</c:if>
		             <c:if test="${menu.menuType eq 12}">加盟商/商家</c:if>
					</td>
					<td>
					 <c:if test="${menu.issued eq 0}">是</c:if>
		             <c:if test="${menu.issued eq 1}">否</c:if>
					</td>
				</tr>
			  </c:forEach>
				
			</table>
        </div>
    </div>
    
    <br clear="all" />
    
</div><!--maincontent-->
<br />
	
</body>
</html>