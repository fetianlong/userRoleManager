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
	$(document).ready(function() {
		$("#paper").validate();
		$("#paperSave").click(function() {
			$("#paper").submit();
		});

		$("#dictionaryClose").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/paper/list/' + parentId;
		});
		$("#dictionaryCloseButton").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/paper/list/' + parentId;
		});

	});
</script>
</head>
<body>
	<div class="hd-add actionbox">
		<h3>试题</h3>
		<span><img class="hd-close"
			src="${ctx}/static/images/closeicon.png" /></span>
		<form id="paper" action="${ctx}/paper/addPaper" method="post">
			<table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td class="font">标题</td>
					<td><input class="addr-inp" type="text" name="title"
						value="${paper.title}" /><span>*</span></td>
				</tr>
				<tr>
					<td class="font">第一项</td>
					<td><textarea class="addr-inp" style="width:300px;height:100px" rows="50" cols="50" name="first" >${paper.first}</textarea><span>*</span></td>
				</tr>
				<tr>
					<td class="font">第二项</td>
					<td><textarea class="addr-inp" style="width:300px;height:100px" rows="50" cols="50" name="second" >${paper.second}</textarea><span>*</span></td>
				</tr>
				<tr>
					<td class="font">第三项</td>
					<td><textarea class="addr-inp" style="width:300px;height:100px" rows="50" cols="50" name="third" >${paper.third}</textarea><span>*</span></td>
				</tr>
				<tr>
					<td class="font">类别</td>
					<td><select class="activity-sel required" style="width: 268px"
						name="type">
							<option value="">-----请选择-----</option>
							<option value="1"
								<c:if test="${paper.type eq 1}">selected="selected"</c:if>>孕早</option>
							<option value="2"
								<c:if test="${paper.type eq 2}">selected="selected"</c:if>>孕中</option>
							<option value="3"
								<c:if test="${paper.type eq 3}">selected="selected"</c:if>>孕晚</option>
					</select><span>*</span></td>
				</tr>
				<tr>
					<td class="font">订制分类</td>
					<td><select style="width: 268px" name="orderTypeId">
							<c:set var="orderTypeId" value="${paper.orderTypeId}" />
							<c:forEach items="${orderTypeList}" var="orderType">
								<option value="${orderType.id}"
									<c:if test="${orderTypeId eq orderType.id}">selected="selected"</c:if>>${orderType.name}</option>
							</c:forEach>
						</select><span>*</span></td>
				</tr>
			</table>
			<input type="hidden" value="${parentId}" name="parentId"
				id="parentId" /> <input type="hidden" value="${paper.id}" name="id" />
			<div class="actionbox-btnZ">
				<div class="hd-keepBtn" id="paperSave">保存</div>
				<div class="hd-closeBtn" id="dictionaryClose">关闭</div>
			</div>
		</form>
	</div>
</body>
</html>