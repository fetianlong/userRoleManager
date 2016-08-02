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
	$("#actionManagerment").validate();
	$("#actionManagementSave").click(function(){
		$("#actionManagerment").submit();
	});
	
	$("#actionManagermentClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/actionManagerment/close/' + parentId;
	});
	$("#actionManagermentCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/actionManagerment/close/' + parentId;
	});
	
}); 
</script>
</head>
<body>
       <div class="hd-add actionbox">
                <h3>行动新增</h3>
                <span><img class="hd-close" id="actionManagermentCloseButton" src="${ctx}/static/images/closeicon.png" /></span>
                 <form id="actionManagerment" action="${ctx}/actionManagerment/addActionManagerment" method="post">
                <table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="font">行动主题</td>
                        <td><input class="name-inp" type="text" name="actionTitle" value="${actionManagerment.actionTitle}" /><span>*</span></td>
                      </tr>
                      <tr>
                        <td class="font">客户名称</td>
                        <td><input class="addr-inp"  type="text" name="clientName" value="${actionManagerment.clientName}" /><span>*</span></td>
                      </tr>
                      <tr>
                        <td class="font">联系人</td>
                        <td><input class="addr-inp"  type="text" name="contacts" value="${actionManagerment.contacts}" /><span>*</span></td>
                      </tr>
                      <tr>
                        <td class="font">行动方式</td>
                         <td>
			                <select class="activity-sel required" style="width:268px" name="actionType">
			                 	<option value="">-----请选择-----</option>
			                 	<option value="1" <c:if test="${actionManagerment.actionType eq 1}">selected="selected"</c:if>>上门拜访</option>
								<option value="2" <c:if test="${actionManagerment.actionType eq 2}">selected="selected"</c:if>>电话</option>
								<option value="3" <c:if test="${actionManagerment.actionType eq 3}">selected="selected"</c:if>>微信</option>
								<option value="4" <c:if test="${actionManagerment.actionType eq 4}">selected="selected"</c:if>>邮件</option>
				            </select><span>*</span>
		                </td>
                      </tr>
                      <tr>
                        <td class="font">状态</td>
                         <td>
			                <select class="activity-sel required" style="width:268px" name="state">
			                 	<option value="">-----请选择-----</option>
			                 	<option value="1" <c:if test="${actionManagerment.state eq 1}">selected="selected"</c:if>>未执行</option>
								<option value="2" <c:if test="${actionManagerment.state eq 2}">selected="selected"</c:if>>执行中</option>
								<option value="3" <c:if test="${actionManagerment.state eq 3}">selected="selected"</c:if>>已完成</option>
				            </select><span>*</span>
		                </td>
                      </tr>
                      <tr>
                        <td class="font">开始时间</td>
						<td>
						<input class="date-inp" id="beginDateTime" value="${actionManagerment.beginDateTime}" name="beginDateTime" onClick="return Calendar('beginDateTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('beginDateTime');" src="${ctx}/static/images/rilicon.png"  /></a>
						<span>*</span>
						</td>
                      </tr>
                      <tr>
                        <td class="font">结束时间</td>
                         <td>
							<input class="date-inp" id="endDateTime" value="${actionManagerment.endDateTime}" name="endDateTime" onClick="return Calendar('endDateTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" onClick="return Calendar('endDateTime');" src="${ctx}/static/images/rilicon.png"  /></a>
							<span>*</span>
						</td>
                      </tr>
                      <tr>
                        <td class="font">是否关闭</td>
                        <td>
			                <select class="activity-sel required" style="width:268px" name="isClose">
			                 	<option value="">-----请选择-----</option>
			                 	<option value="0" <c:if test="${actionManagerment.state eq 1}">selected="selected"</c:if>>否</option>
								<option value="1" <c:if test="${actionManagerment.state eq 2}">selected="selected"</c:if>>是</option>
				            </select><span>*</span>
		                </td>
                      </tr>
                      <tr>
                        <td class="font">地点</td>
                        <td><textarea class="area-inp" style=" margin-top:10px;" name="location" cols="" rows="">${actionManagerment.location}</textarea></td>
                      </tr>
                      <tr>
                        <td class="font">完成结果</td>
                        <td><textarea class="area-inp" style=" margin-top:10px;" name="accomplishResults" cols="" rows="">${actionManagerment.accomplishResults}</textarea></td>
                      </tr>
                      <tr>
                        <td class="font">描述</td>
                        <td><textarea class="area-inp" style=" margin-top:10px;" name="spec" cols="" rows="">${actionManagerment.spec}</textarea></td>
                      </tr>
                </table>
                <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
                <input type="hidden" value="${actionManagerment.id}" name="id"/>
                <div class="actionbox-btnZ">
                    <div class="hd-keepBtn" id="actionManagementSave">保存</div>
                    <div class="hd-closeBtn" id="actionManagermentClose">关闭</div>
                 </div>
                 </form>
            </div>
</body>
</html>