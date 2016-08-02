<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %> --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
$(document).ready(function () {
	$("#message").validate();
	$("#messageSave").click(function(){
		$("#message").submit();
	});
	
	$("#messageClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/message/close/' + parentId;
	});
	$("#messageCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/message/close/' + parentId;
	});
}); 
</script>
</head>
<body>
       <div class="hd-add actionbox">
          <h3>短信审核</h3>
          <span><img class="hd-close" id="messageCloseButton" src="${ctx}/static/images/closeicon.png" /></span>
         <form id="message" action="${ctx}/message/notgoMessage" method="post">
          <table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
             <tr>
               <td class="font">原因</td>
               <td><textarea class="area-inp" style=" margin-top:10px;" name="notgoReason" cols="" rows="">${message.notgoReason}</textarea></td>
             </tr>
          </table>
         <input type="text" value="${parentId}" name="parentId" id="parentId"/>
         <input type="text" value="${messageId}" name="id"/>
          <div class="actionbox-btnZ">
              <div class="hd-keepBtn" id="messageSave">保存</div>
              <div class="hd-closeBtn" id="messageClose">关闭</div>
          </div>
        </form>
      </div>
</body>
</html>