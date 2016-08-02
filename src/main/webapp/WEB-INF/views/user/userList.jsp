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
jQuery(document).ready(function () {
	//查询
	jQuery("#userInfoSubmit").click(function(){
		jQuery('#userInfo').submit();
	});
	
	//查询重置
	jQuery("#reset").click(function(){
		jQuery("#userName").val("");
		jQuery("#franchiseesId").val("");
	});
	
// 	jQuery("input[type='checkbox']").click(function() {
//         if ($(this).prop("checked") == true) {
//             jQuery("input[type='checkbox']").attr("checked", false);
//             $(this).prop("checked", true);
//         }
//     });
	
	//修改
	jQuery("#updateUserInfo").click(function() {
		if(jQuery("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
			return false;
		}else {
			var parentId = jQuery('#parentId').val();
			var userInfoId = jQuery("input[type='checkbox']:checked").val();
			jQuery("#updateUserInfo").attr("href",'${ctx}/userInfo/update/'+userInfoId+'?parentId=' + parentId+'&titleName=修改用户');
		}
    });
	
	//冻结
	jQuery("#freezeUserInfo").click(function() {
		if(jQuery("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = jQuery('#parentId').val();
			var userInfoId = jQuery("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定冻结账户吗?');
			if(boolconfirm) {
				jQuery("#freezeUserInfo").attr("href",'${ctx}/userInfo/freeze/'+userInfoId+'?parentId=' + parentId);
			}
		}
    });
	
	//恢复
	jQuery("#recoverUserInfo").click(function() {
		if(jQuery("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = jQuery('#parentId').val();
			var userInfoId = jQuery("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定恢复该账户吗?');
			if(boolconfirm) {
				jQuery("#recoverUserInfo").attr("href",'${ctx}/userInfo/recover/'+userInfoId+'?parentId=' + parentId);
			}
		}
    });
	
	//重置密码
	jQuery("#resetPassword").click(function() {
		if(jQuery("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = jQuery('#parentId').val();
			var userInfoId = jQuery("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定重置密码为“123456”吗?');
			if(boolconfirm) {
				jQuery("#resetPassword").attr("href",'${ctx}/userInfo/resetPwd/'+userInfoId+'?parentId=' + parentId);
			}
		}
    });
	
	/* */
	//删除
	jQuery('.sTableOptions .delete').click(function(){
		var userInfoId = jQuery("input[type='checkbox']:checked").val();
	 	jQuery(this).parents('tr').fadeOut();
	 	$.ajax({  
		    url:'${ctx}/userInfo/deleteUser/'+userInfoId,// 跳转到 action    
		    type:'delete',    
		    cache:false,    
		    dataType:'json',    
	 	});
	});
	 
}); 
</script>
</head>
<body>
    <div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span>用户管理</span>
    </div><!-- breadcrumbs -->
	<!--人员管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="userInfo" name="userInfoForm" action="${ctx}/userInfo/list/${parentId}"  style="margin-top:23px; margin-left:16px;">
				<label class="sj-name">用户名</label> <input class="sf" type="text" id="userName" name="userName" value="${userName}" /> 
				<label for="occupation">加盟商</label>
				<select name="franchiseesId" id="franchiseesId" >
					<option value="">-----请选择-----</option>
				  <c:forEach items="${sellerList}" var="seller">
					<option value="${seller.id}" <c:if test="${seller.id eq franchiseesId}">selected="selected"</c:if>>${seller.name}</option>
				  </c:forEach>
				</select>
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="userInfoSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			</form> 
        <div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
<!--             <a id="deleteUser" class="button delete"><span>删除</span></a> -->
            <a id="resetPassword" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/tag.png" class="mgright5" alt=""> <span>重置密码</span></a>
            <a id="recoverUserInfo" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/check.png" class="mgright5" alt=""> <span>恢复</span></a>
            <a id="freezeUserInfo" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/close.png" class="mgright5" alt=""> <span>冻结</span></a>
            <a id="updateUserInfo" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>修改</span></a>
            <a href="${ctx}/userInfo/add?parentId=${parentId}&titleName=新增用户" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>新增</span></a>     
        </div><!--sTableOptions-->
        
        <div id="listview" class="sTableWrapper" style=" margin-left:16px;">       
            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
                <colgroup>
                    <col class="head0" width="3%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="10%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="10%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="15%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="20%" />
                </colgroup>
                <tr>
                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
                    <td width="10%">用户名</td>
					<td width="10%">MAC地址</td>
					<td width="10%">邮箱</td>
					<td width="10%">加盟商</td>
					<td width="10%">状态</td>
					<td width="15%">创建时间</td>
					<td width="10%">创建人</td>
					<td width="20%">说明</td>
                </tr>
            </table>
         </div>
         <div class="sTableWrapper" style=" margin-left:16px;">
			<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
				<colgroup>
					<col class="con0" width="3%" />
					<col class="con1" width="10%" />
					<col class="con0" width="10%" />
					<col class="con1" width="10%" />
					<col class="con0" width="10%" />
					<col class="con1" width="10%" />
					<col class="con0" width="15%" />
					<col class="con1" width="10%" />
					<col class="con0" width="20%" />
				</colgroup>
				<c:forEach items="${userList}" var="user">
			  	<tr>
					<td><input id="userInfoChecked" name="" type="checkbox" value="${user.id}" /></td>
					<td>${user.userName}</td>
					<td>${user.maces}</td>
					<td>${user.email}</td>
					<td>${user.sellerName}</td>
					<td>
					 <c:if test="${user.state eq 1}">有效</c:if>
		             <c:if test="${user.state eq 2}">冻结</c:if>
					</td>
					<td>${user.createDateTime}</td>
					<td>${user.createrName}</td>
					<td>${user.remark}</td>
				</tr>
			  </c:forEach>
				
			</table>
        </div>
        <tags:pagination page="${pageData}" paginationSize="10"/>
    </div>
    
    
    <!--商家管理-->
    <br clear="all" />
    
</div><!--maincontent-->
<br />
	
</body>
</html>