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
	
	$("#updateActionManagerment").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var actionManagermentId = $("input[type='checkbox']:checked").val();
			$("#updateActionManagerment").attr("href",'${ctx}/actionManagerment/update/'+actionManagermentId+'?parentId=' + parentId);
		}
    });
	
	$("#deleteActionManagerment").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var actionManagermentId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除角色吗?');
			if(boolconfirm) {
				$("#deleteActionManagerment").attr("href",'${ctx}/actionManagerment/delete/'+actionManagermentId+'?parentId=' + parentId);
			}
		}
    });
	
	$("#reset").click(function(){
		$("#actionTitle").val("");
		$("#executorId").val("");
		$("#state").val("");
	});
	
	$("#actionmanagermentSubmit").click(function(){
		 $('#actionmanagerment').submit();
	});
}); 
</script>
</head>
<body>
	<div class="qzsj">
		<form class="sj-mana" id="actionmanagerment" name="actionmanagermentForm" action="">
			<label class="sj-name">行动主题</label> <input class="sj-inp" type="text" id="actionTitle" name="actionTitle" value="${actionTitle}"/>&nbsp;&nbsp;
			<label class="sj-name">执行人</label> 
			 <select class="activity-sel"  name="executorId" id="executorId">
				<option value=""></option>
				<c:set var="excutedId" value="${excutedId}" />
			  <c:forEach items="${userInfoList}" var="userInfo">
				<option value="${userInfo.id}" <c:if test="${userInfo.id eq excutedId}">selected="selected"</c:if>>${userInfo.userName}</option>
			  </c:forEach>
			 </select>&nbsp;&nbsp; 
			<label class="sj-name">状态</label>
			<select class="activity-sel required" style="width:268px" name="state">
               	<option value="">-----请选择-----</option>
               	<option value="1" <c:if test="${state eq 1}">selected="selected"</c:if>>未执行</option>
				<option value="2" <c:if test="${state eq 2}">selected="selected"</c:if>>执行中</option>
				<option value="3" <c:if test="${state eq 3}">selected="selected"</c:if>>已完成</option>
            </select>
			
			<a id="actionmanagermentSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
			<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;">行动管理列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/actionManagerment/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateActionManagerment"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deleteActionManagerment"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="3%"><!-- <input name="" type="checkbox" value="" /> --></td>
			<td width="8%">行动主题</td>
			<td width="4%">执行人</td>
			<td width="6%">联系人</td>
			<td width="8%">客户名称</td>
			<td width="4%">行动方式</td>
			<td width="5%">状态</td>
			<td width="8%">开始日期</td>
			<td width="8%">结束日期</td>
			<td width="15%">地点</td>
			<td width="17%">完成结果</td>
			<td width="10%">描述</td>
			<td width="4%">是否关闭</td>
		</tr>
	  <c:forEach items="${actionManagermentList}" var="actionManagerment">
	  	<tr>
			<td><input id="actionManagermentChecked" name="" type="checkbox" value="${actionManagerment.id}" /></td>
			<td>${actionManagerment.actionTitle}</td>
			<td>${actionManagerment.executorName}</td>
			<td>${actionManagerment.contacts}</td>
			<td>${actionManagerment.clientName}</td>
			<td>
				 <c:if test="${actionManagerment.actionType eq 1}">上门拜访</c:if>
	             <c:if test="${actionManagerment.actionType eq 2}">电话</c:if>
	             <c:if test="${actionManagerment.actionType eq 3}">微信</c:if>
	             <c:if test="${actionManagerment.actionType eq 4}">邮件</c:if>
			</td>
			<td>
				 <c:if test="${actionManagerment.state eq 1}">未执行</c:if>
	             <c:if test="${actionManagerment.state eq 2}">执行中</c:if>
	             <c:if test="${actionManagerment.state eq 3}">已完成</c:if>
			</td>
			<td>${actionManagerment.beginDateTime}</td>
			<td>${actionManagerment.endDateTime}</td>
			<td>${actionManagerment.location}</td>
			<td>${actionManagerment.accomplishResults}</td>
			<td>${actionManagerment.spec}</td>
			<td>
				 <c:if test="${actionManagerment.isClose eq 0}">否</c:if>
	             <c:if test="${actionManagerment.isClose eq 1}">是</c:if>
			</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>