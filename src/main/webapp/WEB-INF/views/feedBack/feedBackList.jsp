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
	$("#invitationSubmit").click(function(){
		 $('#feedBack').submit();
	});
	//查询重置
	$("#reset").click(function(){
		$("#beginDateTime").val("");
		$("#endDateTime").val("");
		$("#fmType").val("");
		$("#painratio").val("");
	});
}); 
</script>
</head>
<body>
    <div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span>分娩结果</span>
    </div><!-- breadcrumbs -->
	<!--角色管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="feedBack" name="feedBackForm" style="margin-top:23px; margin-left:16px;">
				<label>分娩日期</label>
				<input id="beginDateTime" name="beginDateTime" value="${beginDateTime}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" readonly="readonly" /> -
				<input id="endDateTime" name="endDateTime" value="${endDateTime}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" readonly="readonly" />
				<label>分娩方式</label>
				<select name="fmType">
					<option value="">--选择--</option>
					<option value="1" <c:if test="${fmType eq 1}">selected="selected"</c:if>>顺产</option>
					<option value="2" <c:if test="${fmType eq 2}">selected="selected"</c:if>>顺剖</option>
					<option value="3" <c:if test="${fmType eq 3}">selected="selected"</c:if>>剖宫产</option>
				</select>
				<label>疼痛等级</label>
				<select name="painratio">
					<option value="">--选择--</option>
					<option value="1" <c:if test="${painratio eq 1}">selected="selected"</c:if>>1</option>
					<option value="2" <c:if test="${painratio eq 2}">selected="selected"</c:if>>2</option>
					<option value="3" <c:if test="${painratio eq 3}">selected="selected"</c:if>>3</option>
					<option value="4" <c:if test="${painratio eq 4}">selected="selected"</c:if>>4</option>
					<option value="5" <c:if test="${painratio eq 5}">selected="selected"</c:if>>5</option>
					<option value="6" <c:if test="${painratio eq 6}">selected="selected"</c:if>>6</option>
				</select>
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="invitationSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			</form> 
	        <div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
	<!--             <a id="deleteKnowledge" class="button delete"><span>删除</span></a> -->
	            <%-- <a id="updateKnowledge" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>修改</span></a> --%>
<%-- 	            <a id="addInvitation" href="" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>新增</span></a>      --%>
	        </div><!--sTableOptions-->
	        
	        <div id="listview" class="sTableWrapper" style=" margin-left:16px;">       
	            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
	                <colgroup>
	                    <col class="head0" width="3%" />
	                    <col class="head1" width="10%" />
	                    <col class="head0" width="10%" />
	                    <col class="head1" width="10%" />
	                    <col class="head0" width="10%" />
	                    <col class="head1" width="10%" />
	                    <col class="head0" width="10%" />
	                    <col class="head1" width="10%" />
	                    <col class="head0" width="10%" />
	<!--                     <col class="head1" width="17%" /> -->
	                </colgroup>
	                <tr>
	                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
	                    <td width="10%">用户名</td>
	                    <td width="10%">分娩日期</td>
	                    <td width="10%">预产期</td>
						<td width="10%">产程用时(秒)</td>
						<td width="10%">疼痛次数</td>
						<td width="10%">疼痛时间(秒)</td>
						<td width="10%">疼痛等级</td>
						<td width="10%">分娩方式</td>
	<!-- 					<td width="17%">备注</td> -->
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
						<col class="con0" width="10%" />
						<col class="con1" width="10%" />
						<col class="con0" width="10%" />
					</colgroup>
				  <c:forEach items="${feedBackList}" var="listvar">
				  	<tr>
						<td><input id="knowledgeChecked" name="" type="checkbox" value="${listvar.id}" /></td>
						<td>${listvar.userName}</td>
						<td><fmt:formatDate value="${listvar.fmTime}" type="both"/></td>
						<td>
							${fn:substring(listvar.birthday, 0, 10)}
						</td>
						<td>${listvar.processtime}</td>
						<td>
							${listvar.painnum}
						</td>
						<td>${listvar.paintime}</td>
						<td>${listvar.painratio}</td>
						<td>
							<c:if test="${listvar.type eq 1}">顺产</c:if>
							<c:if test="${listvar.type eq 2}">顺剖</c:if>
							<c:if test="${listvar.type eq 3}">剖宫产</c:if>
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