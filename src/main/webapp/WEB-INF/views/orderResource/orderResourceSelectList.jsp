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
	
	
	$("#deleteOrderResource").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var orderContentId = $('#orderContentId').val();
			var orderResourceId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除该关联的资源么?');
			if(boolconfirm) {
				$("#deleteOrderResource").attr("href",'${ctx}/orderResource/delete/'+orderResourceId+'?parentId=' + parentId+'&orderContentId='+orderContentId);
			}
		}
    });
	
	$("#returnOrderContent").click(function() {
			var parentId = $('#parentId').val();
			var orderResourceId = $("input[type='checkbox']:checked").val();
				$("#returnOrderContent").attr("href",'${ctx}/orderContent/list/'+parentId);
    });

}); 
</script>
</head>
<body>
	
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;">资源关联列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/orderResource/add?parentId=${parentId}&orderContentId=${orderContentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deleteOrderResource"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4>
	           <h4 style="width:70px; float:left;"><a id="returnOrderContent"><img class="addimg" src="${ctx}/static/images/pencil.png" />返回</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
	           <input type="hidden" value="${orderContentId}" id="orderContentId" name="orderContentId"/>
           </td>
        </tr>
		<tr>
			<td width="3%"></td>
			<td width="30%">编号</td>
			<td width="30%">内部名称</td>
			<td width="30%">外部名称</td>
			<td width="30%">标签</td>
			
		</tr>
	  <c:forEach items="${orderResourceSelectList}" var="orderResource">
	  	<tr>
			<td><input id="orderResourceChecked" name="" type="checkbox" value="${orderResource.id}" /></td>
			<td>${orderResource.resourceCode}</td>
			<td>${orderResource.resourceName}</td>
			<td>${orderResource.displayName}</td>
			<td>${orderResource.tags}</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>