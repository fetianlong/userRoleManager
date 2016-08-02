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
	
	
	$("#deleteDeliveryResource").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var deliveryId = $('#deliveryId').val();
			var deliveryResourceId = $("input[type='checkbox']:checked").val();
			var url = "${ctx}/deliveryResource/delete/"+deliveryResourceId+"?parentId=" + parentId+'&deliveryId='+deliveryId + "&connenctDeliveryName=${connenctDeliveryName}&cmName=${cmName}";
			var boolconfirm=confirm('确定删除该关联的资源么?');
			if(boolconfirm) {
				$("#deleteDeliveryResource").attr("href",url);
			}
		}
    });
	
	$("#returnDelivery").click(function() {
		var parentId = $('#parentId').val();
		var DeliveryResourceId = $("input[type='checkbox']:checked").val();
		$("#returnDelivery").attr("href",'${ctx}/delivery/list/'+parentId);
    });

}); 
</script>
</head>
<body>
    <div class="breadcrumbs">
    	<a href="#">${connenctDeliveryName}、${cmName}</a>
        <span>资源关联列表</span>
    </div><!-- breadcrumbs -->

    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z">
	        <div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
	            <a id="deleteDeliveryResource" class="button delete"><span>删除</span></a>
<!-- 	            <a id="back" class="button back" href="javascript:history.go(-1)"><span>返回</span></a> -->
<%-- 	            <a id="updateVideo" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>修改</span></a> --%>
	            <a href="${ctx}/deliveryResource/add?parentId=${parentId}&deliveryId=${deliveryId}&connenctDeliveryName=${connenctDeliveryName}&cmName=${cmName}" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>新增</span></a>     
	        </div><!--sTableOptions-->
	        <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			<input type="hidden" value="${deliveryId}" id="deliveryId" name="deliveryId"/>
	        <div id="listview" class="listview" style=" margin-left:16px;">       
	            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
	                <colgroup>
	                    <col class="head0" width="3%" />
	                    <col class="head1" width="10%" />
	                    <col class="head0" width="30%" />
	                    <col class="head1" width="30%" />
	                    <col class="head0" width="30%" />
	                </colgroup>
	                <tr>
	                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
	                    <td width="10%">编号</td>
	                    <td width="30%">内部名称</td>
						<td width="30%">外部名称</td>
						<td width="30%">标签</td>
	                </tr>
	            </table>
	         </div>
	         <div class="sTableWrapper" style=" margin-left:16px;">
				<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
					<colgroup>
						<col class="con0" width="3%" />
						<col class="con1" width="10%" />
						<col class="con0" width="30%" />
						<col class="con1" width="30%" />
						<col class="con0" width="30%" />
					</colgroup>
					<c:forEach items="${deliveryResourceSelectList}" var="deliveryResource">
					  	<tr>
							<td><input id="deliveryResourceChecked" name="" type="checkbox" value="${deliveryResource.id}" /></td>
							<td>${deliveryResource.resourceCode}</td>
							<td>${deliveryResource.resourceName}</td>
							<td>${deliveryResource.displayName}</td>
							<td>${deliveryResource.tags}</td>
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
           	   <h3 style="width:100px; float:left;">资源关联列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/deliveryResource/add?parentId=${parentId}&deliveryId=${deliveryId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="deleteDeliveryResource"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />删除</a></h4>
	           <h4 style="width:70px; float:left;"><a id="returnDelivery"><img class="addimg" src="${ctx}/static/images/pencil.png" />返回</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
	           <input type="hidden" value="${deliveryId}" id="deliveryId" name="deliveryId"/>
           </td>
        </tr>
		<tr>
			<td width="3%"></td>
			<td width="30%">编号</td>
			<td width="30%">内部名称</td>
			<td width="30%">外部名称</td>
			<td width="30%">标签</td>
			
		</tr>
	  <c:forEach items="${deliveryResourceSelectList}" var="deliveryResource">
	  	<tr>
			<td><input id="deliveryResourceChecked" name="" type="checkbox" value="${deliveryResource.id}" /></td>
			<td>${deliveryResource.resourceCode}</td>
			<td>${deliveryResource.resourceName}</td>
			<td>${deliveryResource.displayName}</td>
			<td>${deliveryResource.tags}</td>
		</tr>
	  </c:forEach>
	</table> --%>
</body>
</html>