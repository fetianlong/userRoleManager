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
	$("#activityLine").validate();
	$("#activityLineSave").click(function(){
		$("#activityLine").submit();
	});
	
	$("#activityLineClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/activityLine/close/' + parentId;
	});
	$("#dictionaryCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/activityLine/close/' + parentId;
	});
	
}); 
</script>
</head>
<body>
       <div class="hd-add actionbox">
                <h3>活动新增</h3>
                <span><img class="hd-close" id="dictionaryCloseButton" src="${ctx}/static/images/closeicon.png" /></span>
                 <form id="activityLine" action="${ctx}/activityLine/addActivityLine" method="post">
                <table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="font">名称</td>
                        <td><input class="name-inp required" type="text" name="activityName" value="${activityLine.activityName}" /><span>*</span></td>
                      </tr>
                      <tr>
                        <td class="font">类型</td>
                         <td>
			                <select class="activity-sel required" style="width:268px" name="activityType">
			                 	<option value="">-----请选择-----</option>
			                 	<option value="1" <c:if test="${activityLine.activityType eq 1}">selected="selected"</c:if>>室内</option>
								<option value="2" <c:if test="${activityLine.activityType eq 2}">selected="selected"</c:if>>户外</option>
				            </select><span>*</span>
		                </td>
                      </tr>
                       <tr>
                        <td class="font">地点</td>
                        <td><input class="name-inp required" type="text" name="activityAddr" value="${activityLine.activityAddr}" /><span>*</span></td>
                      </tr>
                       <tr>
                        <td class="font">目的</td>
                        <td><input class="name-inp required" type="text" name="activityPurpose" value="${activityLine.activityPurpose}" /><span>*</span></td>
                      </tr>
                      <tr>
                        <td class="font">预计总费用</td>
                        <td><input class="name-inp required number" type="text" name="expectedTotalCost" value="${activityLine.expectedTotalCost}" /><span>*</span></td>
                      </tr>
                       <tr>
                        <td class="font">预计线索数</td>
                        <td><input class="name-inp required number" type="text" name="expectedCluesNumber" value="${activityLine.expectedCluesNumber}" /><span>*</span></td>
                      </tr>
                      <tr>
                        <td class="font">开始时间</td>
						<td>
						<input class="date-inp required" id="beginDateTime" value="${activityLine.beginDateTime}" name="beginDateTime" onClick="return Calendar('beginDateTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('beginDateTime');" src="${ctx}/static/images/rilicon.png"  /></a>
						<span>*</span>
						</td>
                      </tr>
                      <tr>
                        <td class="font">结束时间</td>
                         <td>
							<input class="date-inp required" id="endDateTime" value="${activityLine.endDateTime}" name="endDateTime" onClick="return Calendar('endDateTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" onClick="return Calendar('endDateTime');" src="${ctx}/static/images/rilicon.png"  /></a>
							<span>*</span>
						</td>
                      </tr>
                      <tr>
                        <td class="font">描述</td>
                        <td><textarea class="area-inp" style=" margin-top:10px;" name="spec" cols="" rows="">${activityLine.spec}</textarea></td>
                      </tr>
                </table>
                <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
                <input type="hidden" value="${activityLine.id}" name="id"/>
                <div class="actionbox-btnZ">
                    <div class="hd-keepBtn" id="activityLineSave">保存</div>
                    <div class="hd-closeBtn" id="activityLineClose">关闭</div>
                 </div>
                 </form>
            </div>
</body>
</html>