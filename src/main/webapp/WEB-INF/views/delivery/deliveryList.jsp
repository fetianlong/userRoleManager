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
	$("input[type='checkbox']").click(function() {
        if ($(this).prop("checked") == true) {
            $("input[type='checkbox']").attr("checked", false);
            $(this).prop("checked", true);
        }
    });
	
	$("#updateDelivery").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var DeliveryId = $("input[type='checkbox']:checked").val();
			$("#updateDelivery").attr("href",'${ctx}/delivery/update/'+DeliveryId+'?parentId=' + parentId + "&titleName=分娩音乐修改");
		}
    });
	
	$("#deleteDelivery").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var DeliveryId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除吗?');
			if(boolconfirm) {
				$("#deleteDelivery").attr("href",'${ctx}/delivery/delete/'+DeliveryId+'?parentId=' + parentId);
			}
		}
    });
	$("#connenctDelivery").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var deliveryId = $("input[type='checkbox']:checked").val();
			var connenctDeliveryValue = $("input[type='checkbox']:checked").attr("title");
			var connenctDeliveryName = null;
			if(connenctDeliveryValue == 1){
				connenctDeliveryName = "第一产程";
			} else if(connenctDeliveryValue == 2){
				connenctDeliveryName = "第二产程";
			} else if(connenctDeliveryValue == 3){
				connenctDeliveryName = "第三产程";
			} else if(connenctDeliveryValue == 4){
				connenctDeliveryName = "第四产程";
			}
			var cmValue = $("input[type='checkbox']:checked").attr("src");
			var cmName = null;
			if(cmValue == 0){
				cmName = "小于1 cm";
			} else if(cmValue == 1){
				cmName = "1-3 cm";
			} else if(cmValue == 2){
				cmName = "3-6 cm";
			} else if(cmValue == 3){
				cmName = "6-10 cm";
			}
			var cmValue = $("input[type='checkbox']:checked").attr("alt");
			var isPainValue = null;
			if(cmValue=="true"){
				cmName += "、疼痛"; 
			}else{
				cmName += "、不疼痛"; 
			}
			$("#connenctDelivery").attr("href",'${ctx}/deliveryResource/listSelectDeliveryResource/'+parentId+'?deliveryId='+deliveryId + "&connenctDeliveryName=" + connenctDeliveryName + "&cmName=" + cmName);
		}
    });
	
}); 
</script>
</head>
<body>
	<div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span>音乐镇痛分娩管理</span>
    </div><!-- breadcrumbs -->
	<!--角色管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="role" name="roleForm" style="margin-top:23px; margin-left:16px;">
				<label>名称</label> <input class="sf" type="text" id="name" name="name" value="${name}" /> 
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="videoSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
	            <input type="hidden" value="${deliveryId}" id="deliveryId" name="deliveryId"/>
			</form> 
	        <div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
	            <a id="deleteDelivery" class="button delete"><span>删除</span></a>
	            <a id="connenctDelivery" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/music.png" class="mgright5" alt=""> <span>关联资源</span></a>
	            <a id="updateDelivery" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>修改</span></a>
	            <a href="${ctx}/delivery/add?parentId=${parentId}&titleName=新增分娩音乐" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>新增</span></a>     
	        </div><!--sTableOptions-->
	        
	        <div id="listview" class="listview" style=" margin-left:16px;">       
	            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
	                <colgroup>
	                    <col class="head0" width="3%" />
	                    <col class="head1" width="20%" />
	                    <col class="head0" width="20%" />
	                    <col class="head1" width="20%" />
	                    <col class="head0" width="20%" />
	                    <col class="head1" width="10%" />
	                </colgroup>
	                <tr>
	                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
	                    <td width="20%">产程</td>
						<td width="20%">公分</td>
						<td width="20%">是否疼痛</td>
						<td width="20%">是否模拟训练</td>
						<td width="10%">周</td>
	                </tr>
	            </table>
	         </div>
	         <div class="sTableWrapper" style=" margin-left:16px;">
				<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
					<colgroup>
						<col class="con0" width="3%" />
						<col class="con1" width="20%" />
						<col class="con0" width="20%" />
						<col class="con1" width="20%" />
						<col class="con0" width="20%" />
						<col class="con1" width="10%" />
					</colgroup>
					<c:forEach items="${deliveryList}" var="delivery">
					  	<tr>
							<td><input id="deliveryChecked" name="" type="checkbox" value="${delivery.id}" title="${delivery.birthProcess}" src="${delivery.cm}" alt="${delivery.isPain}" /></td>
							<td>
							   	<c:if test="${delivery.birthProcess eq 1}">第一产程</c:if>
								<c:if test="${delivery.birthProcess eq 2}">第二产程</c:if>
								<c:if test="${delivery.birthProcess eq 3}">第三产程</c:if>
								<c:if test="${delivery.birthProcess eq 4}">第四产程</c:if>
							</td>
							<td>
								<c:if test="${delivery.cm eq 0}">小于1 cm</c:if>
								<c:if test="${delivery.cm eq 1}">1-3 cm</c:if>
								<c:if test="${delivery.cm eq 2}">3-6 cm</c:if>
								<c:if test="${delivery.cm eq 3}">6-10 cm</c:if>	 
							</td>
							<td>
								<c:if test="${delivery.isPain eq true}">疼痛</c:if>
								<c:if test="${delivery.isPain eq false}">不疼痛</c:if>
							</td>
							<td>
								<c:if test="${delivery.isSimulation eq true}">是</c:if>
								<c:if test="${delivery.isSimulation eq false}">否</c:if>
							</td>
							<td>${delivery.week}</td>
						</tr>
					  </c:forEach>
				</table>
	        </div>
	        <tags:pagination page="${pageData}" paginationSize="10"/>
	    </div>
    
    <br clear="all" />
    
	</div><!--maincontent-->
<br />
<%-- 
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;">音乐镇痛分娩</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/delivery/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateDelivery"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:100px; float:left;"><a id="connenctDelivery"><img class="addimg" src="${ctx}/static/images/pencil.png" />资源关联</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deleteDelivery"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
	           <input type="hidden" value="${deliveryId}" id="deliveryId" name="deliveryId"/>
           </td>
        </tr>
		<tr>
			<td width="3%"></td>
			<td width="30%">产程</td>
			<td width="30%">公分</td>
			<td width="30%">是否疼痛</td>
			
		</tr>
	  <c:forEach items="${deliveryList}" var="delivery">
	  	<tr>
			<td><input id="deliveryChecked" name="" type="checkbox" value="${delivery.id}" /></td>
			<td>
			   	<c:if test="${delivery.birthProcess eq 1}">第一产程</c:if>
				<c:if test="${delivery.birthProcess eq 2}">第二产程</c:if>
				<c:if test="${delivery.birthProcess eq 3}">第三产程</c:if>
			</td>
			<td>
				<c:if test="${delivery.cm eq 0}">2厘米以内</c:if>
				<c:if test="${delivery.cm eq 1}">2-3厘米</c:if>
				<c:if test="${delivery.cm eq 2}">3-6厘米</c:if>
				<c:if test="${delivery.cm eq 3}">6-10厘米</c:if>	 
			</td>
			<td>
				<c:if test="${delivery.isPain eq true}">疼痛</c:if>
				<c:if test="${delivery.isPain eq false}">不疼痛</c:if>
			</td>
		</tr>
	  </c:forEach>
	</table>
	 --%>
</body>
</html>