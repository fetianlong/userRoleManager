<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
	$(document).ready(function() {
		$("#contactPerson").validate();
		$("#contactPersonSave").click(function() {
			$("#contactPerson").submit();
		});

		$("#contactPersonClose").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/contactPerson/close/' + parentId;
		});
		$("#contactPersonCloseButton").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/contactPerson/close/' + parentId;
		});

	});
</script>
</head>
<body>
	<div class="newadd-peo">
		<h3>联系人新增</h3>
		<span id="contactPersonCloseButton"><img class="newsj-close"
			src="${ctx}/static/images/closeicon.png" /></span>
		<div class="writ-info">
			<form id="contactPerson" action="${ctx}/contactPerson/addContactPerson"
				method="post">
				<table class="newqzsj-tab" width="100%" cellspacing="0"
					cellpadding="0">
					<tr>
						<td class="font">商家类型</td>
						<td><select class="required" name="businessType">
								<option value="">-----请选择-----</option>
								<!-- <option value="1">潜在加盟商</option>
						<option value="2">加盟商</option> -->
								<option value="3"
									<c:if test="${contactPerson.businessType eq 3}">selected="selected"</c:if>>潜在商家</option>
								<option value="4"
									<c:if test="${contactPerson.businessType eq 4}">selected="selected"</c:if>>商家</option>
						</select><span>*</span></td>
					</tr>
					<tr>
						<td class="font">所属商家</td>
						<td><select class="required" name="sellerId">
								<option value="">-----请选择-----</option>
								<c:set var="sellerId" value="${contactPerson.sellerId}" />
								<c:forEach items="${sellerList}" var="seller">
									<option value="${seller.id}"
										<c:if test="${seller.id eq sellerId}">selected="selected"</c:if>>${seller.name}</option>
								</c:forEach>
						</select><span>*</span></td>
					</tr>
					<tr>
						<td class="font">姓名</td>
						<td><input class="addr-inp" type="text" name="fullName"
							value="${contactPerson.fullName}" /><span>*</span></td>
					</tr>
					<tr>
						<td class="font">联系电话</td>
						<td><input class="obje-inp" type="text" name="telephone"
							value="${contactPerson.telephone}" /><span>*</span></td>
					</tr>
					<tr>
						<td class="font">性别${contactPerson.sex}</td>
						<td>
							<div id="radioboxg">
								<input id="sjradio" name="sex" type="radio" value="1" <c:if test="${contactPerson.sex eq 1}">checked="checked"</c:if>/><label
									for="sjradio">男</label>
							</div>
							<div id="radioboxg">
								<input id="sjradio1" name="sex" type="radio" value="0" <c:if test="${contactPerson.sex eq 0}">checked="checked"</c:if>/><label
									for="sjradio1">女</label>
							</div>
						</td>
					</tr>
					<tr>
						<td class="font">出生日期</td>
						<td>
						<!-- <input class="obje-inp" type="text" value="" /> -->
						<input class="date-inp" id="time" value="${contactPerson.birthday}" name="birthday" onClick="return Calendar('time');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('time');" src="${ctx}/static/images/rilicon.png"  /></a>
						<span>*</span>
						</td>
					</tr>
					<tr>
						<td class="font">是否关键人</td>
						<td><div id="radiobox" style="width: 65px;">
								<input id="sjradio" name="keyPerson" type="radio" value="0" <c:if test="${contactPerson.keyPerson eq 0}">checked="checked"</c:if>/><label
									for="sjradio">关键人</label>
							</div>
							<div id="radiobox">
								<input id="sjradio1" name="keyPerson" type="radio" value="1" <c:if test="${contactPerson.keyPerson eq 1}">checked="checked"</c:if>/><label
									for="sjradio1">非关键人</label>
							</div></td>
					</tr>
 					<tr>
						<td class="font">部门</td>
						<td><input class="name-inp" type="text" name="branch" value="${contactPerson.branch}"/><span>*</span></td>
					</tr>
					<tr>
						<td class="font">职位</td>
						<td><input class="name-inp" type="text" name="position" value="${contactPerson.position}"/><span>*</span></td>
					</tr>
					<tr>
						<td class="font">QQ</td>
						<td><input class="name-inp" type="text" name="instantMessage" value="${contactPerson.instantMessage}"/><span>*</span></td>
					</tr>
					<tr>
						<td class="font">微信</td>
						<td><input class="name-inp" type="text" name="personalWebsite" value="${contactPerson.personalWebsite}"/><span>*</span></td>
					</tr>
					<tr>
						<td class="font">emall</td>
						<td><input class="name-inp" type="text" name="email" value="${contactPerson.email}"/><span>*</span></td>
					</tr>

					<tr>
						<td class="font">邮编</td>
						<td><input class="name-inp" type="text" name="postalCode" value="${contactPerson.postalCode}"/><span>*</span></td>
					</tr>
					<tr>
						<td class="font">地址</td>
						<td><input class="addr-inp" type="text" name="address" value="${contactPerson.address}"/><span>*</span></td>
					</tr>
					<tr>
						<td class="font">爱好</td>
						<td><input class="obje-inp" type="text" name="hobby" value="${contactPerson.hobby}"/><span>*</span></td>
					</tr>
					<tr>
						<td class="font">备注</td>
						<td>
						<textarea class="obje-inp" style="margin-top:10px; width:80%; height:150px;" name="remark" cols="" rows="">${contactPerson.remark}</textarea>
						</td>
					</tr>
				</table>
				<input type="hidden" value="${parentId}" name="parentId"
					id="parentId" /> <input type="hidden" value="${contactPerson.id}"
					name="id" />
				<div class="btnZ">
					<div class="keepBtn" id="contactPersonSave" style="cursor: pointer;">保存</div>
					<div class="closeBtn" id="contactPersonClose" style="cursor: pointer;">关闭</div>
				</div>
			</form>
		</div>

	</div>
</body>
</html>