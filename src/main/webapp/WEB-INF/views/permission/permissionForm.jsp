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
	$("#permission").validate();
	$("#permissionSave").click(function(){
		$("#permission").submit();
	});
	
	$("#permissionClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/permission/close/' + parentId;
	});
	$("#permissionCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/permission/close/' + parentId;
	});
	
}); 
</script>
</head>
<body>
<div class="franchisee">
        <h3>按钮级权限新增</h3>
        <span id="permissionCloseButton"><img class="writ-close" src="${ctx}/static/images/closeicon.png" /></span>
        <div class="writ-info">
        <form id="permission" action="${ctx}/permission/addPermission" method="post">
          <table class="tab" width="100%" cellspacing="0" cellpadding="0">
              <tr>
                <td class="font">名称</td>
                <td><input class="name-inp required" type="text" name="name" value="${permission.name}" /><span>*</span></td>
              </tr>
              <tr>
                <td class="font">值</td>
                <td><input class="name-inp required" type="text" name="value" value="${permission.value}" /><span>*</span></td>
              </tr>
            </table>
            <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
            <input type="hidden" value="${permission.id}" name="id"/>
            <div class="btnZ">
                <div class="keepBtn" id="permissionSave" style="cursor:pointer;">保存</div>
                <div class="closeBtn" id="permissionClose" style="cursor:pointer;">关闭</div>
            </div>  
          </form>
        </div>	
    </div>
</body>
</html>