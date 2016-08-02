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
	$("#dictionary").validate();
	$("#dictionarySave").click(function(){
		$("#dictionary").submit();
	});
	
	$("#dictionaryClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/dictionary/close/' + parentId;
	});
	$("#dictionaryCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/dictionary/close/' + parentId;
	});
	
}); 
</script>
</head>
<body>
<div class="franchisee">
        <h3>数据字典新增</h3>
        <span id="dictionaryCloseButton"><img class="writ-close" src="${ctx}/static/images/closeicon.png" /></span>
        <div class="writ-info">
        <form id="dictionary" action="${ctx}/dictionary/addDictionary" method="post">
          <table class="tab" width="100%" cellspacing="0" cellpadding="0">
              <tr>
                <td class="font">名称</td>
                <td><input class="name-inp required" type="text" name="name" value="${dictionary.name}" /><span>*</span></td>
              </tr>
              <tr>
                <td class="font">编码</td>
                <td><input class="name-inp required" type="text" name="code" value="${dictionary.code}" /><span>*</span></td>
              </tr>
              <tr>
                <td class="font">父编码</td>
                <c:set var="parentCode" value="${dictionary.code}" />
                <td>
                 <select class="activity-sel required" style="width:400px" name="parentCode">
                 	<option value="">-----请选择-----</option>
                 	<c:forEach items="${parentDictionaryList}" var="parentDictionary">
						<option value="${parentDictionary.code}" <c:if test="${parentDictionary.code eq parentCode}">selected="selected"</c:if>>${parentDictionary.code}</option>
					</c:forEach>
                 </select><span>*</span>
                </td>
              </tr>
              <tr>
                <td class="font">状态</td>
                <td>
	                <select class="activity-sel required" style="width:400px" name="isflag">
	                 	<option value="">-----请选择-----</option>
	                 	<option value="true" <c:if test="${dictionary.isflag eq true}">selected="selected"</c:if>>有效</option>
						<option value="false" <c:if test="${dictionary.isflag eq false}">selected="selected"</c:if>>无效</option>
		            </select><span>*</span>
                </td>
              </tr>
              <tr>
                <td class="font">说明</td>
                <td><textarea class="area-inp" style="margin-top:10px; width:80%; height:150px;" name="content" cols="" rows="">${dictionary.content}</textarea></td>
              </tr>
            </table>
            <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
            <input type="hidden" value="${dictionary.id}" name="id"/>
            <div class="btnZ">
                <div class="keepBtn" id="dictionarySave" style="cursor:pointer;">保存</div>
                <div class="closeBtn" id="dictionaryClose" style="cursor:pointer;">关闭</div>
            </div>  
          </form>
        </div>	
       
    </div>
</body>
</html>