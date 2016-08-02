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
	$("#knowledge").validate();
	$("#knowledgeSave").click(function(){
		$("#knowledge").submit();
	});
	
	$("#dictionaryClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/knowledge/list/' + parentId;
	});
	$("#dictionaryCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/knowledge/list/' + parentId;
	});
	
}); 
</script>
</head>
<body>
	<div class="breadcrumbs">
    	<a href="#">分娩知识</a>
        <span>${param.titleName}</span>
    </div><!-- breadcrumbs -->
    <!--添加页面-->
    <div class="form_default" style="margin-left:16px; margin-top:20px;width: ">
    <form id="knowledge" action="${ctx}/knowledge/addKnowledge" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>${param.titleName}</legend>
            <p>
                <label for="name" style="float:left;">名称</label>
                <input type="text" name="name" value="${knowledge.name}" class="sf" />
            </p>
             <p>
                <label for="email" style="float:left;">内容</label>
                <textarea rows="" cols="" class="mf" name="content">${knowledge.content}</textarea>
            </p>
             <p>
                <label for="email" style="float:left;">图片</label>
                <input class="sf " type="file" name="imgFile" />
            </p>
             <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
             <input type="hidden" value="${knowledge.id}" name="id"/>
             <input type="hidden" value="${knowledge.img}" name="image"/>
            
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