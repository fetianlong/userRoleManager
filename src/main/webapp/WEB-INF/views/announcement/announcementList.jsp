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
	
	$("#updateAnnouncement").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var announcementId = $("input[type='checkbox']:checked").val();
			$("#updateAnnouncement").attr("href",'${ctx}/announcement/update/'+announcementId+'?parentId=' + parentId);
		}
    });
	
	$("#deleteAnnouncement").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var announcementId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除公告吗?');
			if(boolconfirm) {
				$("#deleteAnnouncement").attr("href",'${ctx}/announcement/delete/'+announcementId+'?parentId=' + parentId);
			}
		}
    });
}); 
</script>
</head>
<body>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;">公告管理列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/announcement/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateAnnouncement"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deleteAnnouncement"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="3%"><!-- <input name="" type="checkbox" value="" /> --></td>
			<td width="10%">标题</td>
			<td width="40%">内容</td>
			<td width="10%">开始日期</td>
			<td width="10%">结束日期</td>
			<td width="10%">创建时间</td>
			<td width="10%">创建人</td>
			<td width="10%">是否发布</td>
		</tr>
	  <c:forEach items="${announcementList}" var="announcement">
	  	<tr>
			<td><input id="announcementChecked" name="" type="checkbox" value="${announcement.id}" /></td>
			<td>${announcement.title}</td>
			<td>${announcement.content}</td>
			<td>${announcement.startTime}</td>
			<td>${announcement.endTime}</td>
			<td>${announcement.createDateTime}</td>
			<td>${announcement.createrName}</td>
			<td>
				 
	             <c:if test="${announcement.isPublish eq 1}">是</c:if>
	             <c:if test="${announcement.isPublish eq 0}">否</c:if>
			</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>