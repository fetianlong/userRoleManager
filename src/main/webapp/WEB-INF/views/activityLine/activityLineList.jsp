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
	
	$("#updateActivityLine").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var activityLineId = $("input[type='checkbox']:checked").val();
			$("#updateActivityLine").attr("href",'${ctx}/activityLine/update/'+activityLineId+'?parentId=' + parentId);
		}
    });
	
	$("#deleteActivityLine").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var activityLineId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除角色吗?');
			if(boolconfirm) {
				$("#deleteActivityLine").attr("href",'${ctx}/activityLine/delete/'+activityLineId+'?parentId=' + parentId);
			}
		}
    });
	
	$("#reset").click(function(){
		$("#activityName").val("");
		$("#excutedName").val("");
		$("#beginDateTime").val("");
		$("#endDateTime").val("");
	});
	
	$("#activityLineSubmit").click(function(){
		 $('#activityLine').submit();
	});
}); 
</script>
</head>
<body>
	<div class="qzsj">
		<form class="sj-mana" id="activityLine" name="activityLineForm" action="">
			<label class="sj-name">名称</label> <input class="sj-inp" type="text" id="activityName" name="activityName" value="${activityName}"/>&nbsp;&nbsp;
			<label class="sj-name">执行人</label> 
			 <select class="activity-sel"  name="excutedId" id="excutedId">
				<option value=""></option>
				<c:set var="excutedId" value="${excutedId}" />
			  <c:forEach items="${userInfoList}" var="userInfo">
				<option value="${userInfo.id}" <c:if test="${userInfo.id eq excutedId}">selected="selected"</c:if>>${userInfo.userName}</option>
			  </c:forEach>
			 </select>&nbsp;&nbsp; 
			<label class="sj-name">时间</label>
			<input class="date-inp" id="beginDateTime" value="${beginDateTime}" name="beginDateTime" onClick="return Calendar('beginDateTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('beginDateTime');" src="${ctx}/static/images/rilicon.png"  /></a>
			-
			<input class="date-inp" id="endDateTime" value="${endDateTime}" name="endDateTime" onClick="return Calendar('endDateTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" onClick="return Calendar('endDateTime');" src="${ctx}/static/images/rilicon.png"  /></a>
			
			<a id="activityLineSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
			<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;">活动管理列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/activityLine/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateActivityLine"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deleteActivityLine"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="5%"><!-- <input name="" type="checkbox" value="" /> --></td>
			<td width="9%">名称</td>
			<td width="9%">类型</td>
			<td width="10%">地点</td>
			<td width="10%">目的</td>
			<td width="6%">开始日期</td>
			<td width="6%">结束日期</td>
			<td width="9%">执行人</td>
			<td width="9%">预计费用</td>
			<td width="9%">预计线索数</td>
			<td width="18%">描述</td>
		</tr>
	  <c:forEach items="${activityLineList}" var="activityLine">
	  	<tr>
			<td><input id="activityLineChecked" name="" type="checkbox" value="${activityLine.id}" /></td>
			<td>${activityLine.activityName}</td>
			<td>
				 <c:if test="${activityLine.activityType eq 1}">室内</c:if>
	             <c:if test="${activityLine.activityType eq 2}">户外</c:if>
			</td>
			<td>${activityLine.activityAddr}</td>
			<td>${activityLine.activityPurpose}</td>
			<td>${activityLine.beginDateTime}</td>
			<td>${activityLine.endDateTime}</td>
			<td>${activityLine.excutedName}</td>
			<td>${activityLine.expectedTotalCost}</td>
			<td>${activityLine.expectedCluesNumber}</td>
			<td>${activityLine.spec}</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>