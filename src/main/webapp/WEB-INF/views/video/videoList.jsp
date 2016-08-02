<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="../home/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
body {background-color: white;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
$(document).ready(function () {
	$("#updateVideo").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var videoId = $("input[type='checkbox']:checked").val();
			$("#updateVideo").attr("href",'${ctx}/video/update/'+videoId+'?parentId=' + parentId);
		}
    });
	
	$("#deleteVideo").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var videoId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除吗?');
			if(boolconfirm) {
				$("#deleteVideo").attr("href",'${ctx}/video/delete/'+videoId+'?parentId=' + parentId);
			}else{
				return false;
			}
		}
    });
	
	$("#videoSubmit").click(function(){
		 $('#video').submit();
	});
}); 
</script>
</head>
<body>
    <div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span>分娩视频管理</span>
    </div><!-- breadcrumbs -->
	<!--角色管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="role" name="roleForm" style="margin-top:23px; margin-left:16px;">
				<label>名称</label> <input class="sf" type="text" id="name" name="name" value="${name}" /> 
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="videoSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			</form> 
	        <div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
	            <a id="deleteVideo" class="button delete"><span>删除</span></a>
	            <a id="updateVideo" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>修改</span></a>
	            <a href="${ctx}/video/add?parentId=${parentId}&titleName=新增分娩视频" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>新增</span></a>     
	        </div><!--sTableOptions-->
	        
	        <div id="listview" class="listview" style=" margin-left:16px;">       
	            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
	                <colgroup>
	                    <col class="head0" width="3%" />
	                    <col class="head1" width="10%" />
	                    <col class="head0" width="10%" />
	                    <col class="head1" width="10%" />
	                    <col class="head0" width="10%" />
	                    <col class="head1" width="10%" />
	                    <col class="head0" width="10%" />
	                </colgroup>
	                <tr>
	                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
	                    <td width="10%">名称</td>
	                    <td width="10%">封面</td>
	                    <td width="10%">视频路径</td>
	                    <td width="10%">创建时间</td>
						<td width="10%">是否公开</td>
						<td width="10%">说明</td>
	                </tr>
	            </table>
	         </div>
	         <div class="sTableWrapper" style=" margin-left:16px;">
				<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
					<colgroup>
						<col class="con0" width="3%" />
						<col class="con1" width="10%" />
						<col class="con0" width="10%" />
						<col class="con1" width="10%" />
						<col class="con0" width="10%" />
						<col class="con1" width="10%" />
						<col class="con0" width="10%" />
					</colgroup>
					<c:forEach items="${videoList}" var="video">
					  	<tr>
							<td><input id="videoChecked" name="" type="checkbox" value="${video.id}" /></td>
							<td>${video.voidTitle}</td>
							<td align="center">
								<img width="20%" alt="" src="${imageUrl}${video.videoImg}" />
							</td>
							<td>${video.videoUrl}</td>
								<td>
								<fmt:formatDate value="${video.ctime}" type="date"/>
							</td>
							<td>
								<c:if test="${video.isOpen eq true}">公开</c:if>
								<c:if test="${video.isOpen eq false}">不公开</c:if>
							</td>
							<td>${video.videoInfo}</td>
						</tr>
					  </c:forEach>
				</table>
	        </div>
	        <tags:pagination page="${pageData}" paginationSize="10"/>
    	</div>
    
    	<br clear="all" />
    
	</div><!--maincontent-->
<br />
	
</body>
</html>