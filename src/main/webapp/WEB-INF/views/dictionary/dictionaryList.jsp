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
	
	$("#updateDictionary").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var dictionaryId = $("input[type='checkbox']:checked").val();
			$("#updateDictionary").attr("href",'${ctx}/dictionary/update/'+dictionaryId+'?parentId=' + parentId);
		}
    });
	
	$("#deleteDictionary").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var dictionaryId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除角色吗?');
			if(boolconfirm) {
				$("#deleteDictionary").attr("href",'${ctx}/dictionary/delete/'+dictionaryId+'?parentId=' + parentId);
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
           	   <h3 style="width:100px; float:left;">数据字典列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/dictionary/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateDictionary"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deleteDictionary"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="5%"><!-- <input name="" type="checkbox" value="" /> --></td>
			<td width="20%">名称</td>
			<td width="15%">编码</td>
			<td width="15%">父编码</td>
			<td width="35%">说明</td>
			<td width="10%">状态</td>
		</tr>
	  <c:forEach items="${dictionaryList}" var="dictionary">
	  	<tr>
			<td><input id="roleChecked" name="" type="checkbox" value="${dictionary.id}" /></td>
			<td>${dictionary.name}</td>
			<td>${dictionary.code}</td>
			<td>${dictionary.parentCode}</td>
			<td>${dictionary.content}</td>
			<td>
			 <c:if test="${dictionary.isflag eq true}">有效</c:if>
             <c:if test="${dictionary.isflag eq false}">无效</c:if>
			</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>