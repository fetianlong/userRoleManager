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
		$("#salerSave").click(function(){
			$("#saler").submit();
		});
		
		$("#salerClose").click(function(){
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/saler/close/' + parentId;
		});
		
		$("#saler").validate();

		$("#salerDeclareCloseButton").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/contactPerson/close/' + parentId;
		});

	});
</script>
</head>
<body>
	<div class="add-sj">
             <h3>二维码分配</h3>
           <%--  <span><img class="sj-close" id="salerDeclareCloseButton" src="${ctx}/static/images/closeicon.png" /></span> --%>
           <form id="saler" action="${ctx}/saler/editSaler" method="post" enctype="multipart/form-data">
            <table class="qzsj-tab" width="100%" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="font">业务员</td>
                    <td>
	                    <select name="userId" id="userId">
	                    	<option value=""></option>
						  <c:forEach items="${userInfoList}" var="userInfo">
							<option value="${userInfo.id}">${userInfo.userName}</option>
						  </c:forEach>
						</select>
                    <span>*</span>
                    </td>
                  </tr>
                 <tr>
                    <td class="font">备注</td>
                    <td><textarea class="area-inp" style=" margin-top:10px; " name="spec" cols="" rows=""></textarea></td>
                 </tr>
            </table>
            <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
            <input type="hidden" value="${saler.id}" name="sallerId" id="sallerId"/>
            <input type="hidden" value="${saler.cardCode}" name="cardCode" id="cardCode"/>
            <div class="qz-btnZ">
                <div class="qz-keepBtn" id="salerSave">保存</div>
                <div class="qz-closeBtn" id="salerClose">关闭</div>
             </div>
             </form>
         </div>
</body>
</html>