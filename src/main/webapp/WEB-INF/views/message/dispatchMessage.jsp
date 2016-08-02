<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
$(document).ready(function () {
	/* $("input[type='checkbox']").click(function() {
	    if ($(this).prop("checked") == true) {
	        $("input[type='checkbox']").attr("checked", false);
	        $(this).prop("checked", true);
	    }
	}); */
	
	$("#undistributedUserinfo2c").click(function() {
      if(this.checked){/*对默认是否为选中进行判断*/  
          $('[name=undistributedUserinfo2cChecked]:checkbox').prop("checked", true);/*checked为true时为默认显示的状态,为true实现全选功能*/  
      }else{  
          $('[name=undistributedUserinfo2cChecked]:checkbox').prop("checked", false);/*false为反选即为全部选功能*/  
      }  
	});
	
	$("#distributedUserinfo2c").click(function() {
	      if(this.checked){/*对默认是否为选中进行判断*/  
	          $('[name=distributedUserinfo2cChecked]:checkbox').prop("checked", true);/*checked为true时为默认显示的状态,为true实现全选功能*/  
	      }else{  
	          $('[name=distributedUserinfo2cChecked]:checkbox').prop("checked", false);/*false为反选即为全部选功能*/  
	      }  
		});
	
	$("#add").click(function() {
		if($("input[name='undistributedUserinfo2cChecked']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var messageId = $('#messageId').val();
			var valArr = new Array;  
		    $("input[name='undistributedUserinfo2cChecked']:checked").each(function(i){  
		        valArr[i] = $(this).val();  
		    });  
		    var vals = valArr.join(',');//数组转换以逗号隔开的字符串 
		    $("#userinfo2cName").attr("value",vals);
		    $("#distributedUserinfo2cForm").attr("action","${ctx}/message/distributedUserinfo2cForm");
			$("#distributedUserinfo2cForm").submit();
			//$("#add").attr("href",'${ctx}/role/assignUserToRole/'+roleId+'?userInfoId=' + userInfoId);
		}
    });
	
	$("#remove").click(function() {
		if($("input[name='distributedUserinfo2cChecked']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var messageId = $('#messageId').val();
			var valArr = new Array;  
		    $("input[name='distributedUserinfo2cChecked']:checked").each(function(i){  
		        valArr[i] = $(this).val();  
		    });  
		    var vals = valArr.join(',');//数组转换以逗号隔开的字符串 
		    $("#userinfo2cName").attr("value",vals);
		    $("#distributedUserinfo2cForm").attr("action","${ctx}/message/removeUserinfo2c");
			$("#distributedUserinfo2cForm").submit();
		}
    });
	
	$("#close").click(function() {
		var parentId = $('#parentId').val();
		$("#close").attr("href",'${ctx}/message/close/'+parentId);
    });
});
</script>
</head>
<body>
	<div>
		<h3>分配用户</h3>
		<div class="nodis">
			<h4>未分配用户</h4>
			<div class="nodis-info"  style="overflow-x: auto; overflow-y: auto;">
			<form id="distributedUserinfo2cForm" action="${ctx}/message/distributedUserinfo2cForm" method="post">
				<input type="hidden" name="messageId" value="${messageId}" id="messageId"/>
				<input type="hidden" name="userinfo2cName" value="" id="userinfo2cName"/>
				<input type="hidden" name="parentId" value="${parentId}" id="parentId"/>
				
			</form>
			<table class="sj-tab" style="margin-left: 0px;margin-top: 0px;width: 100%" cellspacing="0" cellpadding="0">
				<tr>
					<td><input name="" id="undistributedUserinfo2c" type="checkbox" value="" /></td>
					<td>用户名</td>
				</tr>
				<c:forEach items="${undistributedUserinfo2cList}" var="undistributedUserinfo2c">
	  				<tr>
						<td><input  name="undistributedUserinfo2cChecked" type="checkbox" value="${undistributedUserinfo2c.userinfo2cName}" /></td>
						<td>${undistributedUserinfo2c.userinfo2cName}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
		<div class="user-btn">
			<div class="addto"><a id="add">添加</a></div>
			<div class="remove"><a id="remove">移除</a></div>
			<div class="clo"><a id="close">关闭</a></div>

		</div>
		<div class="otherno" style="overflow-x: auto; overflow-y: auto;">
			<h4>已分配用户</h4>
			<div class="otherno-info">
				<table class="sj-tab" style="margin-left: 0px;margin-top: 0px;width: 100%" cellspacing="0" cellpadding="0">
					<tr>
						<td><input name="" id="distributedUserinfo2c" type="checkbox" value="" /></td>
						<td>用户名</td>
					</tr>
					<c:forEach items="${distributedUserinfo2cList}" var="distributedUserinfo2c">
		  				<tr>
							<td><input name="distributedUserinfo2cChecked" type="checkbox" value="${distributedUserinfo2c.userinfo2cName}" /></td>
							<td>${distributedUserinfo2c.userinfo2cName}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>