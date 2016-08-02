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
	$("#orderType").validate();
	$("#orderTypeSave").click(function(){
		$("#orderType").submit();
	});
	
	$("#dictionaryClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/orderType/list/' + parentId;
	});
	$("#dictionaryCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/orderType/list/' + parentId;
	});
	
}); 
</script>
</head>
<body>
       <div class="hd-add actionbox">
                <h3>订制</h3>
                <span><img class="hd-close" src="${ctx}/static/images/closeicon.png" /></span>
                 <form id="orderType" action="${ctx}/orderType/addOrderType" method="post">
                <table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
                   
                        <td class="font">订制分类名称</td>
                        <td><input class="addr-inp"  type="text" name="name" value="${orderType.name}" /><span>*</span></td>
                      </tr>
                      <tr>
                        <td class="font">订制分类类别</td>
                        <td>
			                <select class="activity-sel required" style="width:268px" name="type">
			                 	<option value="">-----请选择-----</option>
								<option value="1" <c:if test="${orderType.type eq true}">selected="selected"</c:if>>情绪</option>
			                 	<option value="0" <c:if test="${orderType.type eq false}">selected="selected"</c:if>>生理</option>
				            </select><span>*</span>
		                </td>
                      </tr>
                </table>
                <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
                <input type="hidden" value="${orderType.id}" name="id"/>
                <div class="actionbox-btnZ">
                    <div class="hd-keepBtn" id="orderTypeSave">保存</div>
                    <div class="hd-closeBtn" id="dictionaryClose">关闭</div>
                 </div>
                 </form>
            </div>
</body>
</html>