<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %> --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
$(document).ready(function () {
	$("#orderContent").validate();
	$("#orderContentSave").click(function(){
		$("#orderContent").submit();
	});
	
	$("#dictionaryClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/orderContent/list/' + parentId;
	});
	$("#dictionaryCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/orderContent/list/' + parentId;
	});
	
}); 
</script>
</head>
<body>
	<div class="hd-add actionbox">
		<h3>订制</h3>
		<span><img class="hd-close"
			src="${ctx}/static/images/closeicon.png" /></span>
		<form id="orderContent" action="${ctx}/orderContent/addOrderContent"
			method="post">
			<table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
				<td class="font">订制內容說明</td>
				<td><textarea class="addr-inp" name="illustrate" style="width:300px;height:100px" rows="50" cols="50">${orderContent.illustrate}</textarea>
					<span>*</span></td>
				</tr>
				<tr>
					<td class="font">订制分类</td>
					<td><select style="width: 268px" name="orderTypeId">
							<c:set var="orderTypeId" value="${orderContent.orderTypeId}" />
							<c:forEach items="${orderTypeList}" var="orderType">
								<option value="${orderType.id}"
									<c:if test="${orderTypeId eq orderType.id}">selected="selected"</c:if>>${orderType.name}</option>
							</c:forEach>
						</select><span>*</span></td>
				</tr>
			</table>
			<input type="hidden" value="${parentId}" name="parentId"
				id="parentId" /> <input type="hidden" value="${orderContent.id}"
				name="id" />
			<div class="actionbox-btnZ">
				<div class="hd-keepBtn" id="orderContentSave">保存</div>
				<div class="hd-closeBtn" id="dictionaryClose">关闭</div>
			</div>
		</form>
	</div>
</body>
</html>