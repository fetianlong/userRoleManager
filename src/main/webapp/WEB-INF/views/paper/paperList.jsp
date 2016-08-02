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
	
	$("#updatePaper").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var paperId = $("input[type='checkbox']:checked").val();
			$("#updatePaper").attr("href",'${ctx}/paper/update/'+paperId+'?parentId=' + parentId);
		}
    });
	
	$("#deletePaper").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var paperId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除测试题吗?');
			if(boolconfirm) {
				$("#deletePaper").attr("href",'${ctx}/paper/delete/'+paperId+'?parentId=' + parentId);
			}
		}
    });
	

	$("#reset").click(function(){
		$("#title").val("");
		$("#type").val("");
	});
	
	$("#paperSubmit").click(function(){
		 $('#paper').submit();
	});
}); 
</script>
</head>
<body>
<div class="qzsj">
		<form class="sj-mana" id="paper" name="paperForm" action="">
			<label class="sj-name">标题</label> <input class="sj-inp" type="text" id="title" name="title" value="${title}"/>&nbsp;&nbsp;
			<label class="sj-name">类型</label> 
			 <select class="activity-sel"  name="type" id="type">
				<option value=""></option>
				<option value="1">孕早</option>
				<option value="2">孕中</option>
				<option value="3">孕晚</option>
			 </select>&nbsp;&nbsp; 
			<a id="paperSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
			<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;">试题列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/paper/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updatePaper"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deletePaper"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="3%"></td>
			<td width="10%">标题</td>
			<td width="20%">第一项</td>
			<td width="20%">第二项</td>
			<td width="20%">第三项</td>
			<td width="5%">类型</td>
			
		</tr>
	  <c:forEach items="${paperList}" var="paper">
	  	<tr>
			<td><input id="PaperChecked" name="" type="checkbox" value="${paper.id}" /></td>
			<td>${paper.title}</td>
			<td>${paper.first}</td>
			<td>${paper.second}</td>
			<td>${paper.third}</td>
			<td>
	             <c:if test="${paper.type eq 1}">孕早</c:if>
	             <c:if test="${paper.type eq 2}">孕中</c:if>
	             <c:if test="${paper.type eq 3}">孕晚</c:if>
			</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>