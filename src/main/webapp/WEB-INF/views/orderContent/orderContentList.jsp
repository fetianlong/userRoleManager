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
	
	$("#updateOrderContent").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var orderContentId = $("input[type='checkbox']:checked").val();
			$("#updateOrderContent").attr("href",'${ctx}/orderContent/update/'+orderContentId+'?parentId=' + parentId);
		}
    });
	
	$("#deleteOrderContent").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var orderContentId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除订制內容吗?');
			if(boolconfirm) {
				$("#deleteOrderContent").attr("href",'${ctx}/orderContent/delete/'+orderContentId+'?parentId=' + parentId);
			}
		}
    });
	$("#connenctOrderContent").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var orderContentId = $("input[type='checkbox']:checked").val();
				$("#connenctOrderContent").attr("href",'${ctx}/orderResource/listSelectOrderResource/'+parentId+'?orderContentId=' + orderContentId);
		}
    });
	

	$("#reset").click(function(){
		$("#illustrate").val("");
		$("#orderTypeId").val("");
	});
	
	$("#orderContentSubmit").click(function(){
		 $('#orderContent').submit();
	});
}); 
</script>
</head>
<body>
	 <div class="qzsj">
		<form class="sj-mana" id="orderContent" name="orderContentForm" action="">
			<label class="sj-name">说明</label> <input class="sj-inp" type="text" id="illustrate" name="illustrate" value="${illustrate}"/>&nbsp;&nbsp;
			<label class="sj-name">订制分类</label> 
			 <select class="activity-sel"  name="orderTypeId" id="orderTypeId">
				<option value=""></option>
				<c:set var="orderTypeId" value="${orderTypeId}" />
							<c:forEach items="${orderTypeList}" var="orderType">
								<option value="${orderType.id}"
									<c:if test="${orderTypeId eq orderType.id}">selected="selected"</c:if>>${orderType.name}</option>
							</c:forEach>
			 </select>&nbsp;&nbsp; 
			<a id="orderContentSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
			<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
		</form> 
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;">定制内容列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/orderContent/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateOrderContent"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:100px; float:left;"><a id="connenctOrderContent"><img class="addimg" src="${ctx}/static/images/pencil.png" />资源关联</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deleteOrderContent"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
	           <input type="hidden" value="${orderContentId}" id="orderContentId" name="orderContentId"/>
           </td>
        </tr>
		<tr>
			<td width="3%"></td>
			<td width="50%">订制内容说明</td>
			<td width="30%">订制内容分类</td>
			
		</tr>
	  <c:forEach items="${orderContentList}" var="orderContent">
	  	<tr>
			<td><input id="orderContentChecked" name="" type="checkbox" value="${orderContent.id}" /></td>
			<td>${orderContent.illustrate}</td>
			<td>${orderContent.orderTypeName}</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>