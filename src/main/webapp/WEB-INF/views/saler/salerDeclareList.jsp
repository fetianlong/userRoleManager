<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="provinceId" value="${province}" />
<c:set var="cityId" value="${city}" />
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
	
	$("#verifySalerSaler").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var salerId = $("input[type='checkbox']:checked").val();
			$("#verifySalerSaler").attr("href",'${ctx}/saler/verifySaler/'+salerId+'?parentId=' + parentId);
		}
    });
	
	$("#reset").click(function(){
		$("#name").val("");
		$("#province").val("");
		$("#city").val("");
		$("#category").val("");
	});
	
	$("#sellerSubmit").click(function(){
		 $('#seller').submit();; 
	});
});
</script>
</head>
<body>
	<div class="qzsj">
		<form class="sj-mana" id="seller" name="sellerForm" action="">
			<label class="sj-name">申请人姓名</label> <input class="sj-inp" type="text" id="name" name="name" value="${name}" /> 
			<label class="sj-name">商家名称</label> <input class="sj-inp" type="text" id="sellerName" name="sellerName" value="${sellerName}" /> 
			<label class="activity-zxr">状态</label>
			<select id="category" name='state' class="activity-sel">  
				<option value='' ></option>
				<option value='1' <c:if test="${state eq 1}">selected</c:if>>待审核</option>
				<option value='2' <c:if test="${state eq 2}">selected</c:if>>审核通过</option>
			</select>
			<a id="sellerSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
			<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:130px; float:left;">二维码申报信息</h3>
	           <h4 style="width:100px; float:left;"><a href="${ctx}/salerDeclare/applicationSaler?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>申请二维码</a></h4>
	           <h4 style="width:100px; float:left;"><a id="verifySalerSaler"><img class="addimg" src="${ctx}/static/images/pencil.png" />审核二维码</a></h4>
           </td>
        </tr>
		<tr>
			<td width="5%"><input name="" type="checkbox" value="" /></td>
			<td width="20%">公司名称</td>
			<td width="10%">申请人姓名</td>
			<td width="10%">申请个数</td>
			<td width="10%">状态</td>
			<td width="10%">描述</td>
		</tr>
	  
	  <c:forEach items="${salerDeclareList}" var="salerDeclare">
	  	<tr>
			<td><input name="" type="checkbox" value="${salerDeclare.id}" /></td>
			<td>${salerDeclare.name}</td>
			<td>${salerDeclare.declareName}</td>
			<td>${salerDeclare.declareNumber}</td>
			<td>
			 <c:if test="${salerDeclare.state eq 1}">待审核</c:if>
             <c:if test="${salerDeclare.state eq 2}">审核通过</c:if>
			</td>
			<td>${salerDeclare.spec}</td>
		</tr>
	  </c:forEach>
	</table>
	
</body>
</html>