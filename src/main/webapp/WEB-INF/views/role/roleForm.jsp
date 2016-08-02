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
	$("#role").validate();
	$("#roleSave").click(function(){
		$("#role").submit();
	});
	
	$("#roleClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/role/close/' + parentId;
	});
	$("#roleCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/role/close/' + parentId;
	});
	
}); 
</script>
</head>
<body>
	<div class="breadcrumbs">
    	<a href="${ctx}/login/index">首页</a>
        <span>${param.titleName}</span>
    </div><!-- breadcrumbs -->
    <!--添加页面-->
    <div class="form_default" style="margin-left:16px; margin-top:20px;width: ">
    <form id="userInfo" action="${ctx}/role/addRole" method="post">
        <fieldset>
            <legend>${param.titleName}</legend>
            <p>
                <label for="name" style="float:left;">名称</label>
                <input type="text" name="name" value="${role.name}" class="sf" />
            </p>
             <p>
                <label for="email" style="float:left;">说明</label>
                <textarea name="remark" class="mf" cols="" rows="">${role.remark}</textarea>
            </p>
          	<input type="hidden" value="${parentId}" name="parentParamId" id="parentId"/>
            <input type="hidden" value="${role.id}" name="id"/>
            
            <p>
                <button id="menuSave">提交</button>
                <input id="userClose" type="button" onclick="history.go(-1);" 
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