<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="../home/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
body {background-color: white;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${ctx}/demo/js/laydate/laydate.js"></script>
<title>悦迪管理平台</title>
<script type="text/JavaScript">
$(document).ready(function () {
	$("#roleSubmit").click(function(){
		 $('#roleForm').submit();
	});
	//查询重置
	$("#reset").click(function(){
		$("#userId").val("");
		$("#loginTime").val("");
	});
}); 
</script>
</head>
<body>
    <div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span>APP用户登录记录</span>
    </div><!-- breadcrumbs -->
	<!--角色管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="roleForm" name="roleForm" style="margin-top:23px; margin-left:16px;">
				<label>用户编号</label> <input class="sf" type="text" id="userId" name="userId" value="${userId}" />
				<label>登录日期</label>
				<input id="loginTime" name="loginTime" value="${loginTime}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" readonly="readonly" />
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="roleSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			</form> 
        <div id="listview" class="sTableWrapper" style=" margin-left:16px;">       
            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
                <colgroup>
                    <col class="head0" width="3%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="10%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="10%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="15%" />
                    <col class="head1" width="15%" />
                    <col class="head0" width="10%" />
                </colgroup>
                <tr>
                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
                    <td width="10%">用户编号</td>
                    <td width="10%">账号</td>
                    <td width="10%">昵称</td>
					<td width="10%">终端类别</td>
					<td width="10%">APP版本</td>
					<td width="10%">首次登陆</td>
					<td width="10%">最后登陆</td>
					<td width="10%">登陆日期</td>
                </tr>
            </table>
         </div>
         <div class="sTableWrapper" style=" margin-left:16px;">
			<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
				<colgroup>
					<col class="con0" width="3%" />
					<col class="con1" width="10%" />
					<col class="con0" width="10%" />
					<col class="con1" width="10%" />
					<col class="con0" width="10%" />
					<col class="con1" width="10%" />
					<col class="con0" width="15%" />
					<col class="con1" width="15%" />
					<col class="con0" width="10%" />
				</colgroup>
			  <c:forEach items="${loginRecordList}" var="knowledge">
			  	<tr>
					<td><input id="knowledgeChecked" name="" type="checkbox" value="${knowledge.id}" /></td>
					<td>${knowledge.userid}</td>
					<td align="center">${knowledge.userName}</td>
					<td align="center">${knowledge.nickName}</td>
					<td align="center">${knowledge.terminal}</td>
					<td align="center">${knowledge.version}</td>
					<td>
						<fmt:formatDate value="${knowledge.ctime}" type="both"/>
					</td>
					<td>
						<fmt:formatDate value="${knowledge.updatetime}" type="both"/>
					</td>
					<td>
						<fmt:formatDate value="${knowledge.loginday}" type="date"/>
					</td>
				</tr>
			  </c:forEach>
				
			</table>
        </div>
        <tags:pagination page="${pageData}" paginationSize="10"/>
    </div>
    
    <br clear="all" />
    
</div><!--maincontent-->
<br />
	
</body>
</html>