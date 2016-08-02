<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../home/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
body {background-color: white;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
$(document).ready(function () {
	$("#invitationCountId").validate();
	$("#invitationCountIdSave").click(function(){
		$("#invitationCountId").submit();
	});
	
	$("#invitationCountClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/invitation/list/' + parentId;
	});
	
}); 
</script>
</head>
<body>
	<div class="breadcrumbs">
    	<a href="#">邀请码管理</a>
        <span>${param.titleName}</span>
    </div><!-- breadcrumbs -->
    <!--添加页面-->
    <div class="form_default" style="margin-left:16px; margin-top:20px;width: ">
    <form id="invitationCountId" action="${ctx}/invitation/${action}" method="post">
        <fieldset>
            <legend>${param.titleName}</legend>
            <p>
                <label for="email" style="float:left;">加盟商</label>
                <c:set var="sellerId" value="${invitationCount.sellerId}" />
                <select name="sellerId" class="sf required">
                 	<option value="">-----请选择-----</option>
                 	<c:forEach items="${sellerList}" var="seller">
						<option value="${seller.id}" <c:if test="${seller.id eq sellerId}">selected="selected"</c:if>>${seller.name}</option>
					</c:forEach>
                 </select>
            </p>
             <p>
                <label for="email" style="float:left;">邀请码数量</label>
                <input type="text" name="addCount" value="${invitationCount.addCount}" class="sf" />
            </p>
             <p>
                <label for="email" style="float:left;">备注</label>
                <textarea rows="" cols="" class="mf" name="remark">${invitationCount.remark}</textarea>
            </p>
             <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
             <input type="hidden" value="${invitationCount.id}" name="id"/>
            
            <p>
                <button id="invitationCountIdSave">提交</button>
                <input id="invitationCountClose" type="button" onclick="history.go(-1);" 
                style="margin-left:10px;background:#4b6592 url('${ctx}/demo//images/buttonbg3.png') repeat-x scroll left top;padding:7px 20px;color: #fff;cursor: pointer;border: 1px solid #395380;border-radius: 3px;" 
                value="返回" />
                <%-- 
                <c:if test="${param.titleName eq '修改用户'}">
                </c:if>
                <c:if test="${param.titleName != '修改用户'}">
                <button onclick="history.go(-1)" style="margin-left:10px;">返回</button>
                </c:if>
                 --%>
            </p>
            
        </fieldset>
        </form>
    </div>

	<!--添加页面-->

    <br clear="all" />
</body>
</html>