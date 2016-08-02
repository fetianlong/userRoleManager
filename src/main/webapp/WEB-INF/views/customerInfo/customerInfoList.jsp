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
	
	$("#updateCustomerInfo").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var customerInfoId = $("input[type='checkbox']:checked").val();
			$("#updateCustomerInfo").attr("href",'${ctx}/customerInfo/update/'+customerInfoId+'?parentId=' + parentId);
		}
    });
	
	$("#convertStudent").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var customerInfoId = $("input[type='checkbox']:checked").val();
			$("#convertStudent").attr("href",'${ctx}/customerInfo/convert/'+customerInfoId+'?parentId=' + parentId);
		}
    });
	
	$("#reset").click(function(){
		$("#userName").val("");
		$("#tel").val("");
		$("#birthday").val("");
		$("#registerStartTime").val("");
		$("#registerEndTime").val("");
		$("#sex").val("");
		$("#origin").val("");
		$("#hospital").val("");
		$("#seller").val("");
		$("#province").val("");
		$("#city").val("");
	});
	
	$("#arSubmit").click(function(){
		 $('#ar').submit();
	});
}); 
</script>
</head>
<body>
	<div class="qzsj">
		<form class="sj-mana" id="ar" name="arForm" action="">
			<label class="sj-name">姓名</label> <input class="sj-inp" type="text" id="userName" name="userName" value="${userName}"/>&nbsp;&nbsp;
			<a id="arSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:130px; float:left;">潜在学员列表</h3>
           	    <h4 style="width:70px; float:left;"><a href="${ctx}/customerInfo/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateCustomerInfo"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:100px; float:left;"><a id="convertStudent"><img class="addimg" src="${ctx}/static/images/pencil.png" />转成学员</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="5%"><!-- <input name="" type="checkbox" value="" /> --></td>
			<td width="15%">姓名</td>
			<td width="5%">性别</td>
			<td width="15%">身份证</td>
			<td width="15%">电话</td>
			<td width="15%">邮箱</td>
			<td width="15%">创建日期</td>
			<td width="15%">所属</td>
		</tr>
	  <c:forEach items="${customerInfoList}" var="customerInfo">
	  	<tr>
			<td><input id="customerInfoChecked" name="" type="checkbox" value="${customerInfo.id}" /></td>
			<td>${customerInfo.name}</td>
			<td>
				 <c:if test="${customerInfo.sex eq 1}">男</c:if>
	             <c:if test="${customerInfo.sex eq 0}">女</c:if>
			</td>
			<td>${customerInfo.cardCode}</td>
			<td>${customerInfo.mobilePhone}</td>
			<td>${customerInfo.email}</td>
			<td>${customerInfo.createDateTimeString}</td>
			<td>${customerInfo.sellerName}</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>