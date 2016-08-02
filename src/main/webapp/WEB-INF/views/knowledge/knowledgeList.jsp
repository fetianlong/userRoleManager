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
	/* 
	$("input[type='checkbox']").click(function() {
        if ($(this).prop("checked") == true) {
            $("input[type='checkbox']").attr("checked", false);
            $(this).prop("checked", true);
        }
    });
	 */
	$("#updateKnowledge").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var knowledgeId = $("input[type='checkbox']:checked").val();
			$("#updateKnowledge").attr("href",'${ctx}/knowledge/update/'+knowledgeId+'?parentId=' + parentId + "&titleName=修改分娩知识");
		}
    });
	
	$("#deleteKnowledge").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var knowledgeId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除吗?');
			if(boolconfirm) {
				$("#deleteKnowledge").attr("href",'${ctx}/knowledge/delete/'+knowledgeId+'?parentId=' + parentId);
			}
		}
    });
	
	$("#knowledgeSubmit").click(function(){
		 $('#knowledge').submit();
	});
}); 
</script>
</head>
<body>
    <div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span>分娩知识管理</span>
    </div><!-- breadcrumbs -->
	<!--角色管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="role" name="roleForm" style="margin-top:23px; margin-left:16px;">
				<label>名称</label> <input class="sf" type="text" id="name" name="name" value="${name}" /> 
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="roleSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			</form> 
        <div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
            <a id="deleteKnowledge" class="button delete"><span>删除</span></a>
            <a id="updateKnowledge" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>修改</span></a>
            <a href="${ctx}/knowledge/add?titleName=新增分娩知识" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>新增</span></a>     
        </div><!--sTableOptions-->
        
        <div id="listview" class="sTableWrapper" style=" margin-left:16px;">       
            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
                <colgroup>
                    <col class="head0" width="3%" />
                    <col class="head1" width="10%" />
                    <col class="head1" width="10%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="70%" />
                </colgroup>
                <tr>
                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
                    <td width="10%">名称</td>
                    <td width="10%">图片</td>
                    <td width="10%">创建时间</td>
					<td width="80%">内容</td>
                </tr>
            </table>
         </div>
         <div class="sTableWrapper" style=" margin-left:16px;">
			<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
				<colgroup>
					<col class="con0" width="3%" />
					<col class="con1" width="10%" />
					<col class="con1" width="10%" />
					<col class="con1" width="10%" />
					<col class="con0" width="70%" />
				</colgroup>
			  <c:forEach items="${knowledgeList}" var="knowledge">
			  	<tr>
					<td><input id="knowledgeChecked" name="" type="checkbox" value="${knowledge.id}" /></td>
					<td>${knowledge.name}</td>
					<td align="center">
						<img alt="" width="20%" src="${imgResource}${knowledge.img}" />
<!-- 						<img alt="" src="D:/yuedi-resource/resources/picture/knowledge/1ffb9a5a-aaf7-4b95-8ff2-3f0648d98a43.jpg" /> -->
					</td>
					<td>
						<fmt:formatDate value="${knowledge.ctime}" type="date"/>
					</td>
					<td>
<%-- 					${knowledge.content} --%>
						<c:choose>  
				         <c:when test="${fn:length(knowledge.content) > 60}">
				             <c:out value="${fn:substring(knowledge.content, 0, 60)}..." />  
				         </c:when>  
				        <c:otherwise>  
				           <c:out value="${mynews.title}" />  
				         </c:otherwise>  
				     </c:choose>
					</td>
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