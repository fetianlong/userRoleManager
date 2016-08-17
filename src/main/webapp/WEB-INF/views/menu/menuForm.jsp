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
	$("#menu").validate();
	$("#menuSave").click(function(){
		$("#menu").submit();
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
    <form id="menu" action="${ctx}/menu/${action}" method="post">
        <fieldset>
            <legend>${param.titleName}</legend>
            <p>
                <label for="name" style="float:left;">名称</label>
                <input type="text" name="name" value="${menu.name}" class="sf" />
            </p>
             <p>
                <label for="email" style="float:left;">Url</label>
                <input type="text" name="newUrl" value="${menu.newUrl}" class="sf" />
            </p>
             <p>
                <label for="email" style="float:left;">上级菜单</label>
                <c:set var="parentMenuId" value="${menu.parentId}" />
                <select name="parentId">
                	<option value="">-----请选择-----</option>
                	<c:forEach items="${parentMenuList}" var="parentMenu">
					<option value="${parentMenu.id}" <c:if test="${parentMenu.id eq parentMenuId}">selected="selected"</c:if>>${parentMenu.name}</option>
				</c:forEach>
               	</select>
            </p>
             <p>
                <label for="email" style="float:left;">显示顺序</label>
                <input class="sf"  type="text" name="ordIndex" value="${menu.ordIndex}" />
            </p>
             <p>
                <label for="email" style="float:left;">状态</label>
                <select name="isDeleteFlag">
<!--                  	<option value="">-----请选择-----</option> -->
                 	<option value="false" <c:if test="${menu.isDeleteFlag eq false}">selected="selected"</c:if>>正常</option>
					<option value="true" <c:if test="${menu.isDeleteFlag eq true}">selected="selected"</c:if>>冻结</option>
                 </select>
            </p>
            <%-- 
            <p>
                <label for="email" style="float:left;">菜单类型</label>
                <select name="menuType">
                 	<option value="">-----请选择-----</option>
                 	<option value="1" <c:if test="${menu.menuType eq 0}">selected="selected"</c:if>>商家</option>
					<option value="2" <c:if test="${menu.menuType eq 1}">selected="selected"</c:if>>加盟商</option>
					<option value="12" <c:if test="${menu.menuType eq 12}">selected="selected"</c:if>>共有</option>
                 </select>
            </p>
            <p>
                <label for="email" style="float:left;">是否下发</label>
                <select name="issued">
                 	<option value="">-----请选择-----</option>
                 	<option value="0" <c:if test="${menu.issued eq 0}">selected="selected"</c:if>>是</option>
					<option value="1" <c:if test="${menu.issued eq 1}">selected="selected"</c:if>>否</option>
               </select>
            </p>
             --%>
          	<input type="hidden" value="${parentId}" name="parentParamId" id="parentId"/>
            <input type="hidden" value="${menu.id}" name="id"/>
            
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