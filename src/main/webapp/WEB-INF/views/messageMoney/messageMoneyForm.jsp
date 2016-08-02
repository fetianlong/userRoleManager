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
	$("#messageMoney").validate();
	$("#messageMoneySave").click(function(){
		$("#messageMoney").submit();
	});
	
	$("#messageMoneyCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/messageMoney/close/' + parentId;
	});
	$("#messageMoneyClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/messageMoney/close/' + parentId;
	});
}); 
</script>
</head>
<body>
       <div class="hd-add actionbox">
          <h3>短信充值</h3>
          <span><img class="hd-close" id="messageMoneyCloseButton" src="${ctx}/static/images/closeicon.png" /></span>
         <form id="messageMoney" action="${ctx}/messageMoney/addMessageMoney" method="post">
          <table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
          	 	<tr>
                 <td class="font">机构</td>
                 <td>
                 	<select name="sellerId" id="sellerId">
                    	<option value=""></option>
                    	<c:set var="sellerId" value="${sellerId}" />
					  <c:forEach items="${sellerList}" var="seller">
						<option value="${seller.id}" <c:if test="${seller.id eq sellerId}">selected="selected"</c:if>>${seller.name}</option>
					  </c:forEach>
					</select>
                    <span>*</span>
                 </td>
               </tr>
               <tr>
                 <td class="font">短信条数</td>
                 <td><input class="type-inp required"  type="text" name="messageCount" value="${messageMoney.messageCount}"/><span>*</span></td>
               </tr>
               <tr>
                 <td class="font">金额</td>
                 <td><input class="type-inp required"  type="text" name="money" value="${messageMoney.money}"/><span>*</span></td>
               </tr>
          </table>
          <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
          <input type="hidden" value="${message.id}" name="id"/>
          <div class="actionbox-btnZ">
              <div class="hd-keepBtn" id="messageMoneySave">保存</div>
              <div class="hd-closeBtn" id="messageMoneyClose">关闭</div>
          </div>
        </form>
      </div>
</body>
</html>