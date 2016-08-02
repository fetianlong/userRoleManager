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
	$("#customerInfo").validate();
	$("#customerInfoSave").click(function(){
		$("#customerInfo").submit();
	});
	
	$("#customerInfoClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/customerInfo/close/' + parentId;
	});
	$("#customerInfoCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/customerInfo/close/' + parentId;
	});
	
}); 
</script>
</head>
<body>
       <div class="hd-add actionbox">
                <h3>潜在学员新增</h3>
                <span><img class="hd-close" id="customerInfoCloseButton" src="${ctx}/static/images/closeicon.png" /></span>
                 <form id="customerInfo" action="${ctx}/customerInfo/addCustomerInfo" method="post">
                <table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="font">姓名</td>
                        <td><input class="name-inp" type="text" name="name" value="${customerInfo.name}" /><span>*</span></td>
                      </tr>
                      <tr>
                        <td class="font">性别</td>
                         <td>
			                <select class="activity-sel required" style="width:268px" name="sex">
			                 	<option value="">-----请选择-----</option>
			                 	<option value="1" <c:if test="${customerInfo.sex eq 1}">selected="selected"</c:if>>男</option>
								<option value="2" <c:if test="${customerInfo.sex eq 2}">selected="selected"</c:if>>女</option>
				            </select><span>*</span>
		                </td>
                      </tr>
                       <tr>
                        <td class="font">身份证</td>
                        <td><input class="name-inp" type="text" name="cardCode" value="${customerInfo.cardCode}" /><span>*</span></td>
                      </tr>
                       <tr>
                        <td class="font">移动电话</td>
                        <td><input class="name-inp" type="text" name="mobilePhone" value="${customerInfo.mobilePhone}" /><span>*</span></td>
                      </tr>
                      <tr>
                        <td class="font">邮箱</td>
                        <td><input class="name-inp" type="text" name="email" value="${customerInfo.email}" /><span>*</span></td>
                      </tr>
                       <tr>
                        <td class="font">QQ号</td>
                        <td><input class="name-inp" type="text" name="qq" value="${customerInfo.qq}" /><span>*</span></td>
                      </tr>
                       <tr>
                        <td class="font">微信号</td>
                        <td><input class="name-inp" type="text" name="weChat" value="${customerInfo.weChat}" /><span>*</span></td>
                      </tr>
                </table>
                <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
                <input type="hidden" value="${customerInfo.id}" name="id"/>
                <div class="actionbox-btnZ">
                    <div class="hd-keepBtn" id="customerInfoSave">保存</div>
                    <div class="hd-closeBtn" id="customerInfoClose">关闭</div>
                 </div>
                 </form>
            </div>
</body>
</html>