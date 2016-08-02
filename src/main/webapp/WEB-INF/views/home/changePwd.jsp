<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
$(document).ready(function () {
	$("#userInfo").validate();
	$("#userSave").click(function(){
		var newpassword = $("#newpassword").val();
		var repassword = $("#repassword").val();
		if(newpassword != repassword) {
			alert("重置密码必须和密码保持一致！");
		}else {
			var pwd = $("#pwd").val();
			$.ajax({  
			    url:'${ctx}/login/validatePwd',// 跳转到 action    
			    data:{    
			    	pwd : pwd
			    },    
			    type:'post',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	if(!data.success) {
			    		alert(data.message);
			    	}
			     }
			});
			$("#userInfo").submit();
		}
	});
	
	$("#userClose").click(function(){
		document.location.href = '${ctx}/login/close';
	});
	$("#userCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/userInfo/close/' + parentId;
	});
	
	$("#pwd").change(function(){
		var pwd = $("#pwd").val();
		$.ajax({  
		    url:'${ctx}/login/validatePwd',// 跳转到 action    
		    data:{    
		    	pwd : pwd
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	//alert(JSON.stringify(data));
		    	if(!data.success) {
		    		alert(data.message);
		    	}
		     }
		});
	});
	
}); 
</script>
</head>
<body>
<div class="franchisee">
        <h3>修改密码</h3>
        <span id="userCloseButton"><img class="writ-close" src="${ctx}/static/images/closeicon.png" /></span>
        <div class="writ-info">
        <form id="userInfo" action="${ctx}/login/changePwd" method="post">
          <table class="tab" width="100%" cellspacing="0" cellpadding="0">
              <tr>
                <td class="font">用户名</td>
                <td>${userInfo.userName}</td>
              </tr>
              <tr>
                <td class="font">密码</td>
                <td><input class="type-inp required" id="pwd" type="password" name="pwd"/><span>*</span></td>
              </tr>
              <tr>
                <td class="font">新密码</td>
                <td><input class="type-inp required"  type="password" name="newpassword" id="newpassword"/><span>*</span></td>
              </tr>
              <tr>
                <td class="font">重复新密码</td>
                <td><input class="type-inp required"  type="password" id="repassword"/><span>*</span></td>
              </tr>
            </table>
            <div class="btnZ">
                <div class="keepBtn" id="userSave" style="cursor:pointer;">保存</div>
                <div class="closeBtn" id="userClose" style="cursor:pointer;">关闭</div>
            </div>  
          </form>
        </div>	
       
    </div>
</body>
</html>