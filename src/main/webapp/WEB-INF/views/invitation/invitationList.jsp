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
<title>悦迪管理平台</title>
<script type="text/JavaScript">
$(document).ready(function () {
	$("#deleteKnowledge").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var knowledgeId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除吗?');
			if(boolconfirm) {
				$("#deleteKnowledge").attr("href",'${ctx}/invitation/delete/'+knowledgeId+'?parentId=' + parentId);
			}
		}
    });
	
	$("#addInvitation").click(function() {
// 		var url = "${ctx}/invitation/add";
		$.ajax({  
		    url:'${ctx}/invitation/add/',// 跳转到 action    
		    type:'post',    
		    cache:false,    
		    dataType:'json',
			success: function(data) {
		    	if(data=='1'){
		    		window.location.reload();
		    	}
		    }
	 	});
	});
	
	$("#invitationSubmit").click(function(){
		 $('#invitation').submit();
	});
	//查询重置
	$("#reset").click(function(){
		$("#invitationCode").val("");
	});
}); 
</script>
</head>
<body>
    <div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span>邀请码管理</span>
    </div><!-- breadcrumbs -->
	<!--角色管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="invitation" name="invitationForm" style="margin-top:23px; margin-left:16px;">
				<label>邀请码</label> <input class="sf" type="text" id="invitationCode" name="invitationCode" value="${invitationCode}" /> 
				<c:if test="${mypid == '3'}">
				<input type="hidden" name="mypid" value="${mypid }" />
				<label>申请机构</label>
				<c:set var="sellerPid" value="${pid}"></c:set>
				<select name="pid" id="pid" >
					<option value="">-----请选择-----</option>
				  <c:forEach items="${sellerList}" var="seller">
					<option value="${seller.id}" <c:if test="${seller.id eq sellerPid}">selected="selected"</c:if>>${seller.name}</option>
				  </c:forEach>
				</select>
				</c:if>
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="invitationSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			</form> 
        <div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
            <a id="deleteKnowledge" class="button delete"><span>删除</span></a>
            <a id="addInvitation" href="" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>单个新增</span></a>      
<%--             <a id="addInvitationBySeller" href="${ctx}/invitation/add?parentId=${parentId}&titleName=新增机构的邀请码数量" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>多个新增</span></a>       --%>
        </div><!--sTableOptions-->
        
        <div id="listview" class="sTableWrapper" style=" margin-left:16px;">       
            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
                <colgroup>
                    <col class="head0" width="3%" />
                    <col class="head1" width="8%" />
                    <col class="head0" width="8%" />
                    <col class="head1" width="20%" />
                    <col class="head0" width="15%" />
                    <col class="head1" width="5%" />
                    <col class="head0" width="5%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="15%" />
                    <col class="head1" width="17%" />
                </colgroup>
                <tr>
                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
                    <td width="8%">邀请码</td>
                    <td width="8%">申请人</td>
                    <td width="20%">申请机构</td>
					<td width="15%">申请时间</td>
					<td width="5%">是否有效</td>
					<td width="5%">使用人</td>
					<td width="10%">使用人电话</td>
					<td width="15%">使用时间</td>
					<td width="17%">备注</td>
                </tr>
            </table>
         </div>
         <div class="sTableWrapper" style=" margin-left:16px;">
			<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
				<colgroup>
					<col class="con0" width="3%" />
					<col class="con1" width="8%" />
					<col class="con1" width="8%" />
					<col class="con1" width="20%" />
					<col class="con0" width="15%" />
					<col class="con1" width="5%" />
					<col class="con0" width="5%" />
					<col class="con1" width="10%" />
					<col class="con0" width="15%" />
					<col class="con1" width="17%" />
				</colgroup>
			  <c:forEach items="${invitationList}" var="listvar">
			  	<tr>
					<td><input id="knowledgeChecked" name="" type="checkbox" value="${listvar.id}" /></td>
					<td>${listvar.invitationcode}</td>
					<td>${listvar.proposerName}</td>
					<td>${listvar.institutionName}</td>
					<td><fmt:formatDate value="${listvar.ctime}" type="both"/></td>
					<td>
						<c:if test="${listvar.iseffe eq 0}">否</c:if>
						<c:if test="${listvar.iseffe eq 1}">是</c:if>
					</td>
					<td>${listvar.userid}</td>
					<td>${listvar.tel}</td>
					<td><fmt:formatDate value="${listvar.btime}" type="both"/></td>
					<td>${listvar.remark}</td>
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