<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
	$(document).ready(function() {
		$("#salerDeclareSave").click(function(){
			$("#salerDeclare").submit();
		});
		
		$("#salerDeclareClose").click(function(){
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/salerDeclare/close/' + parentId;
		});
		
		$("#salerDeclare").validate();

		$("#salerDeclareCloseButton").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/salerDeclare/close/' + parentId;
		});

	});
</script>
</head>
<body>
	<div class="add-sj">
             <h3>二维码申报</h3>
            <span><img class="sj-close" id="salerDeclareCloseButton" src="${ctx}/static/images/closeicon.png" /></span>
           <form id="salerDeclare" action="${ctx}/salerDeclare/addSalerDeclare" method="post" enctype="multipart/form-data">
            <table class="qzsj-tab" width="100%" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="font">申请个数</td>
                    <td><input class="name-inp"  type="text" value="" name="declareNumber"/><span>*</span></td>
                  </tr>
                 <tr>
                    <td class="font">说明</td>
                    <td><textarea class="area-inp" style=" margin-top:10px; " name="spec" cols="" rows=""></textarea></td>
                 </tr>
            </table>
            <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
            <div class="qz-btnZ">
                <div class="qz-keepBtn" id="salerDeclareSave">保存</div>
                <div class="qz-closeBtn" id="salerDeclareClose">关闭</div>
             </div>
             </form>
         </div>
</body>
</html>