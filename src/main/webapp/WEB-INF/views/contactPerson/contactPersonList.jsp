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
	
	$("#updateContactPerson").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var contactPersonId = $("input[type='checkbox']:checked").val();
			$("#updateContactPerson").attr("href",'${ctx}/contactPerson/update/'+contactPersonId+'?parentId=' + parentId);
		}
    });
	
	$("#deleteContactPerson").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var contactPersonId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除角色吗?');
			if(boolconfirm) {
				$("#deleteContactPerson").attr("href",'${ctx}/contactPerson/delete/'+contactPersonId+'?parentId=' + parentId);
			}
		}
    });
	
	$("#contactPersonSubmit").click(function(){
		 $('#contactPerson').submit();
	});
	$("#reset").click(function(){
		$("#fullName").val("");
		$("#position").val("");
		$("#telephone").val("");
	});
}); 
</script>
</head>
<body>
<div class="qzsj">
		<form class="sj-mana" id="contactPerson" name="contactPersonForm" action="">
			<label class="sj-name">姓名</label> <input id="fullName" class="sj-inp" type="text" name="fullName" value="${fullName}" />
			<label class="sj-name">职务</label> <input id="position" class="sj-inp" type="text" name="position" value="${position}" />
			<label class="sj-name">联系电话</label> <input id="telephone" class="sj-inp" type="text" name="telephone" value="${telephone}" />
			
			<a id="contactPersonSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a><img id="reset" class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;">联系人列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/contactPerson/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateContactPerson"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deleteContactPerson"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="5%"><!-- <input name="" type="checkbox" value="" /> --></td>
			<td width="15%">姓名</td>
			<td width="5%">性别</td>
			<td width="5%">是否关键人</td>
			<td width="15%">商家类型</td>
			<td width="15%">所属商家</td>
			<td width="15%">部门</td>
			<td width="10%">职务</td>
			<td width="15%">联系电话</td>
		</tr>
	  <c:forEach items="${contactPersonList}" var="contactPerson">
	  	<tr>
			<td><input id="contactPersonChecked" name="" type="checkbox" value="${contactPerson.id}" /></td>
			<td>${contactPerson.fullName}</td>
			<td>
				<c:if test="${contactPerson.sex eq 1}">男</c:if>
	            <c:if test="${contactPerson.sex eq 0}">女</c:if>
            </td>
            <td>
				<c:if test="${contactPerson.keyPerson eq 1}">否</c:if>
	            <c:if test="${contactPerson.keyPerson eq 0}">是</c:if>
            </td>
            <td>
				<c:if test="${contactPerson.businessType eq 1}">潜在加盟商</c:if>
	            <c:if test="${contactPerson.businessType eq 2}">加盟商</c:if>
	            <c:if test="${contactPerson.businessType eq 3}">潜在商家</c:if>
	            <c:if test="${contactPerson.businessType eq 4}">商家</c:if>
            </td>
			<td>${contactPerson.sellerName}</td>
			<td>${contactPerson.branch}</td>
			<td>${contactPerson.position}</td>
			<td>${contactPerson.telephone}</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>