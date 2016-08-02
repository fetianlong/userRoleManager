<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
	
	$("#updateMessage").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var messageId = $("input[type='checkbox']:checked").val();
			$.ajax({  
			    url:'${ctx}/message/getMessageByAjax',// 跳转到 action    
			    data:{    
			    	messageId : messageId
			    },    
			    type:'get',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	if(data.state == 2 || data.state ==4) {
			    		alert("该条信息已提交或已通过审核，不能编辑！");
			    	}else {
			    		$("#updateMessage").attr("href",'${ctx}/message/update/'+messageId+'?parentId=' + parentId);
			    	}
			     }
			});
		}
    });
	
	$("#dispatchMessage").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var messageId = $("input[type='checkbox']:checked").val();
			$.ajax({  
			    url:'${ctx}/message/getMessageByAjax',// 跳转到 action    
			    data:{    
			    	messageId : messageId
			    },    
			    type:'get',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	if(data.state == 2 || data.state ==4) {
			    		alert("该条信息已提交或已通过审核，不能分配！");
			    	}else {
			    		$("#dispatchMessage").attr("href",'${ctx}/message/dispatchMessage/'+messageId+'?parentId=' + parentId);
			    	}
			     }
			});
		}
    });
	
	$("#adoptMessage").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var messageId = $("input[type='checkbox']:checked").val();
			$.ajax({  
			    url:'${ctx}/message/getMessageByAjax',// 跳转到 action    
			    data:{    
			    	messageId : messageId
			    },    
			    type:'get',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	if(data.state != 2) {
			    		alert("只有待审核状态才可以审核！");
			    	}else if(data.state != 4){
			    		alert("该条信息已经审核通过，不能修改！");
			    	}else {
			    		$("#adoptMessage").attr("href",'${ctx}/message/adoptMessage/'+messageId+'?parentId=' + parentId);
			    	}
			     }
			});
		}
    });
	
	$("#applyMessage").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var messageId = $("input[type='checkbox']:checked").val();
			
			$.ajax({  
			    url:'${ctx}/message/getMessageByAjax',// 跳转到 action    
			    data:{    
			    	messageId : messageId
			    },    
			    type:'get',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	if(data.state == 2 || data.state ==4) {
			    		alert("该条信息已提交或已通过审核，不能申请！");
			    	}else {
			    		$.ajax({  
						    url:'${ctx}/message/getDistributedUserinfo2cListByAjax',// 跳转到 action    
						    data:{    
						    	messageId : messageId
						    },    
						    type:'get',    
						    cache:false,    
						    dataType:'json',    
						    success:function(data) {
						    	if(data == '') {
						    		alert("请先分配人员！");
						    	}else {
						    		$("#applyMessage").attr("href",'${ctx}/message/applyMessage/'+messageId+'?parentId=' + parentId);
						    	}
						     }
						});
			    	}
			     }
			});
		}
    });
	
	$("#cancelMessage").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var messageId = $("input[type='checkbox']:checked").val();
			$.ajax({  
			    url:'${ctx}/message/getMessageByAjax',// 跳转到 action    
			    data:{    
			    	messageId : messageId
			    },    
			    type:'get',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	if(data.state != 2) {
			    		alert("只有待审核状态才可以撤销！");
			    	}else {
			    		$("#cancelMessage").attr("href",'${ctx}/message/cancelMessage/'+messageId+'?parentId=' + parentId);
			    	}
			     }
			});
		}
    });
	
	$("#notgoMessage").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var messageId = $("input[type='checkbox']:checked").val();
			$.ajax({  
			    url:'${ctx}/message/getMessageByAjax',// 跳转到 action    
			    data:{    
			    	messageId : messageId
			    },    
			    type:'get',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	if(data.state != 2) {
			    		alert("只有待审核状态才可以审核！");
			    	}else {
			    		$("#notgoMessage").attr("href",'${ctx}/message/notgoReason/'+messageId+'?parentId=' + parentId);
			    	}
			     }
			});
		}
    });
	
	$("#deleteActivityLine").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var activityLineId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除角色吗?');
			if(boolconfirm) {
				$("#deleteActivityLine").attr("href",'${ctx}/activityLine/delete/'+activityLineId+'?parentId=' + parentId);
			}
		}
    });
	
	$("#reset").click(function(){
		$("#state").val("");
	});
	
	$("#messageSubmit").click(function(){
		 $('#message').submit();
	});
	
	
}); 
</script>
</head>
<body>
	<div class="qzsj">
		<form class="sj-mana" id="messageMoney" name="messageMoneyForm" action="">
			<label class="sj-name">机构</label>
			<c:if test="${sellerId eq 3}">
			 <select class="activity-sel"  name="state" id="state">
				<option value="">-------选择------</option>
				<option <c:if test="${state eq 2}">selected="selected"</c:if> value="2">待审核</option>
				<option <c:if test="${state eq 4}">selected="selected"</c:if> value="4">审核通过</option>
				<option <c:if test="${state eq 5}">selected="selected"</c:if> value="5">审核未通过</option>
			 </select>&nbsp;&nbsp;
			</c:if>
			<a id="messageSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
			<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;">短信充值列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/messageMoney/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="5%"><!-- <input name="" type="checkbox" value="" /> --></td>
			<td width="20%">机构</td>
			<td width="20%">充值人</td>
			<td width="20%">短信数</td>
			<td width="15%">金额</td>
			<td width="20%">时间</td>
		</tr>
	  <c:forEach items="${messageMoneyList}" var="messageMoney">
	  	<tr>
			<td><input id="messageMoneyChecked" name="" type="checkbox" value="${messageMoney.id}" /></td>
			<td>${messageMoney.sellerName}</td>
			<td>${messageMoney.createName}</td>
			<td>${messageMoney.messageCount}</td>
			<td>${messageMoney.money}</td>
			<td>${messageMoney.createDateTimeString}</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>