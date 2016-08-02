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
	
	$("#dispatchSaler").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var salerId = $("input[type='checkbox']:checked").val();
			$("#dispatchSaler").attr("href",'${ctx}/saler/dispatchSaler/'+salerId+'?parentId=' + parentId);
		}
    });
	
	$("#previewSaler").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var salerId = $("input[type='checkbox']:checked").val();
			$("#previewSaler").attr("href",'${ctx}/saler/preview/'+salerId+'?parentId=' + parentId);
		}
    });
	
	$("#reset").click(function(){
		$("#name").val("");
		$("#sellerName").val("");
		$("#state").val("");
	});
	
	$("#salerSubmit").click(function(){
		 $('#saler').submit();
	});
});
</script>
</head>
<body>
	<div class="qzsj">
		<form class="sj-mana" id="saler" name="salerForm" action="">
			<label class="sj-name">申请人姓名</label> <input class="sj-inp" type="text" id="name" name="name" value="${name}" /> 
			<label class="sj-name">商家名称</label> <input class="sj-inp" type="text" id="sellerName" name="sellerName" /> 
			<label class="activity-zxr">状态</label>
			<select id="category" name='state' class="activity-sel">  
				<option value='' selected></option>
				<option value='1' <c:if test="${state eq 1}">selected</c:if>>待审核</option>
				<option value='2' <c:if test="${state eq 2}">selected</c:if>>审核通过</option>
			</select>
			<a id="salerSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
			<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:130px; float:left;">二维码信息</h3>
	           <h4 style="width:100px; float:left;"><a id="dispatchSaler"><img class="addimg" src="${ctx}/static/images/addto.png"/>分配二维码</a></h4>
	           <h4 style="width:100px; float:left;"><a id="previewSaler"><%-- <img class="addimg" src="${ctx}/static/images/pencil.png" /> --%>查看二维码</a></h4>
           </td>
        </tr>
		<tr>
			<td width="5%"></td>
			<td width="20%">二维码编号</td>
			<td width="10%">公司名称</td>
			<td width="10%">使用人</td>
			<td width="10%">生成日期</td>
			<td width="10%">说明</td>
		</tr>
	  
	  <c:forEach items="${salerList}" var="saler">
	  	<tr>
			<td><input name="" type="checkbox" value="${saler.id}" /></td>
			<td>${saler.cardCode}</td>
			<td>${saler.sjname}</td>
			<td>${saler.name}</td>
			<td>${saler.createDateTimeString}</td>
			<td>${saler.spec}</td>
		</tr>
	  </c:forEach>
	</table>
	
</body>
</html>