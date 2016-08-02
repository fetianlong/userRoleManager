<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %>
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
	
	$("#updateUserCode").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var userCodeId = $("input[type='checkbox']:checked").val();
			$("#updateuserCode").attr("href",'${ctx}/userCode/update/'+userCodeId+'?parentId=' + parentId);
		}
    });
	
	$("#deleteUserCode").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var userCodeId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除订制分类吗?');
			if(boolconfirm) {
				$("#deleteuserCode").attr("href",'${ctx}/userCode/delete/'+userCodeId+'?parentId=' + parentId);
			}
		}
    });
	

	$("#reset").click(function(){
		$("#name").val("");
		$("#type").val("");
	});
	
	$("#userCodeSubmit").click(function(){
		 $('#userCode').submit();
	});
}); 
</script>
</head>
<body>
<div class="qzsj">
		<form class="sj-mana" id="userCode" name="userCodeForm" action="">
			<label class="sj-name">用户名称</label> <input class="sj-inp" type="text" id="name" name="name" value="${name}"/>&nbsp;&nbsp;
			<label class="sj-name">状态</label> 
			 <select class="activity-sel"  name="type" id="type">
				<option value=""></option>
				<option value="1">失效</option>
				<option value="0">有效</option>
			 </select>&nbsp;&nbsp; 
			<a id="userCodeSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
			<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;"></h3>
	          <%--  <h4 style="width:70px; float:left;"><a href="${ctx}/userCode/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateUserCode"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deleteUserCode"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4> --%>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="3%"></td>
			<td width="30%">用户名</td>
			<td width="30%">邀请码</td>
			<td width="30%">状态</td>
			
		</tr>
	  <c:forEach items="${userCodeList}" var="userCode">
	  	<tr>
			<td><input id="userCodeChecked" name="" type="checkbox" value="${userCode.id}" /></td>
			<td>${userCode.userName}</td>
			<td>${userCode.code }</td>
			<td>
	             <c:if test="${userCode.state eq 0}">有效</c:if>
	             <c:if test="${userCode.state eq 1}">失效</c:if>
			</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>