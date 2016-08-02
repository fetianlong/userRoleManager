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
	
	$("#loginlogSubmit").click(function(){
		 $('#loginlog').submit();
	});
	//查询重置
	$("#reset").click(function(){
		$("#beginDateTime").val("");
		$("#endDateTime").val("");
		$("#userName").val("");
		$("#seller").val("");
	});
}); 
</script>
</head>
<body>
    <div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span></span>
    </div><!-- breadcrumbs -->
	<!--角色管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="loginlog" name="loginlog" style="margin-top:23px; margin-left:16px;">
			    <label>用户名</label> 
			    <input class="sf" type="text" id="userName" name="userName" value="${userName}" /> 
			    <label>机构名</label> 
			    <input class="sf" type="text" id="seller" name="sellerName" value="${sellername}" /> 
				<label>时间</label>
				<input id="beginDateTime" name="beginDateTime" value="${beginDateTime}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" readonly="readonly" /> -
				<input id="endDateTime" name="endDateTime" value="${endDateTime}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" readonly="readonly" />
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="loginlogSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			</form> 
        <!--<div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
           <a id="deleteKnowledge" class="button delete"><span>删除</span></a>
            <a id="updateKnowledge" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>修改</span></a>
            <a id="addloginlog" href="" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>新增</span></a>      
        </div>sTableOptions-->
        
        <div id="listview" class="sTableWrapper" style=" margin-left:16px;">       
            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
                <colgroup>
                    <col class="head0" width="3%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="10%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="10%" />
                    <col class="head1" width="10%" />
                </colgroup>
                <tr>
                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
                    <td width="10%">登录用户</td>
                    <td width="10%">所属机构</td>
					<td width="10%">登录IP</td>
                    <td width="10%">登录时间</td>
                    <td width="10%">登录终端</td>
                </tr>
            </table>
         </div>
         <div class="sTableWrapper" style=" margin-left:16px;">
			<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
				<colgroup>
					<col class="con0" width="3%" />
					<col class="con1" width="10%" />
					<col class="con1" width="10%" />
					<col class="con1" width="10%" />
					<col class="con0" width="10%" />
					<col class="con1" width="10%" />
				</colgroup>
			  <c:forEach items="${listCount}" var="listvar">
			  	<tr>
					<td><input id="knowledgeChecked" name="" type="checkbox" value="${listvar.id}" /></td>
					<td>${listvar.userName}</td>
					<td>${listvar.sellerName}</td>
					<td>${listvar.loginIp}</td>
					<td>${listvar.loginTime}</td>
					<td>${listvar.loginSource}</td>
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