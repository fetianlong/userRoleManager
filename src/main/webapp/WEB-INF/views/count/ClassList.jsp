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
				<label>类型</label>
				<select name="status">
					<option value="0" <c:if test="${status eq 0}">selected="selected"</c:if> >未完成</option>
					<option value="1" <c:if test="${status eq 1}">selected="selected"</c:if>>完成一半</option>
					<option value="2" <c:if test="${status eq 2}">selected="selected"</c:if>>全部完成</option>
				</select>
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="loginlogSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
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
                    <col class="head1" width="10%" />
                    <col class="head0" width="10%" />
                </colgroup>
                <tr>
                    <td width="10%">课程名称</td>
                    <td width="10%">完成次数</td>

                </tr>
            </table>
         </div>
         <div class="sTableWrapper" style=" margin-left:16px;">
			<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
				<colgroup>
					<col class="con0" width="10%" />
					<col class="con1" width="10%" />
				</colgroup>
			  <c:forEach items="${listCount}" var="listvar">
			  	<tr>
					<td>${listvar.className}</td>
					<td>${listvar.num}</td>
				</tr>
			  </c:forEach>
				
			</table>
        </div>
      
    </div>
    
    <br clear="all" />
    
</div><!--maincontent-->
<br />
	
</body>
</html>