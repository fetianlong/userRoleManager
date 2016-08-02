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
	
	$("#updateOrderType").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var orderTypeId = $("input[type='checkbox']:checked").val();
			$("#updateOrderType").attr("href",'${ctx}/orderType/update/'+orderTypeId+'?parentId=' + parentId);
		}
    });
	
	$("#deleteOrderType").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var orderTypeId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除订制分类吗?');
			if(boolconfirm) {
				$("#deleteOrderType").attr("href",'${ctx}/orderType/delete/'+orderTypeId+'?parentId=' + parentId);
			}
		}
    });
	

	$("#reset").click(function(){
		$("#name").val("");
		$("#type").val("");
	});
	
	$("#orderTypeSubmit").click(function(){
		 $('#orderType').submit();
	});
}); 
</script>
</head>
<body>
<div class="qzsj">
		<form class="sj-mana" id="orderType" name="orderTypeForm" action="">
			<label class="sj-name">分类名称</label> <input class="sj-inp" type="text" id="name" name="name" value="${name}"/>&nbsp;&nbsp;
			<label class="sj-name">类型</label> 
			 <select class="activity-sel"  name="type" id="type">
				<option value=""></option>
				<option value="1">情绪</option>
				<option value="0">生理</option>
			 </select>&nbsp;&nbsp; 
			<a id="orderTypeSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
			<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;">定制分类列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/orderType/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateOrderType"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deleteOrderType"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="3%"></td>
			<td width="30%">订制分类名称</td>
			<td width="30%">类型</td>
			
		</tr>
	  <c:forEach items="${orderTypeList}" var="orderType">
	  	<tr>
			<td><input id="orderTypeChecked" name="" type="checkbox" value="${orderType.id}" /></td>
			<td>${orderType.name}</td>
			
			<td>
	             <c:if test="${orderType.type eq true}">情绪</c:if>
	             <c:if test="${orderType.type eq false}">生理</c:if>
			</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>