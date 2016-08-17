<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ include file="../home/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" media="all" href="${ctx}/demo/css/plugins/lanrenzhijia.css" />
<title>悦迪云管理平台</title>
</head>
<body class="bodygrey">
<div class="headerspace"></div>
<div class="header">
    <!--logo-->
<%-- 	<a href=""><img src="${ctx}/demo/images/logo2.png" alt="Logo" /></a> --%>
	<a href=""><img src="${ctx}/static/images/dlyt_03.png" height="60px;" alt="Logo" /></a>
<!--      二级菜单 -->
    <div class="tabmenu">
    	<ul id="twoMenu">
    		<c:forEach items="${towMenuList}" var="towMenu" varStatus="status">
    			<li id="${towMenu.id}li" onclick="changeTable('${ctx}/${towMenu.newUrl}${towMenu.parentId}?titleName=${towMenu.name}','${towMenu.id}li')" ><a href="" class="dashboard"><span>${towMenu.name}</span></a></li>
    		</c:forEach>
        </ul>
    </div>
   <!-- tabmenu-->
    
    <div class="accountinfo">
    	<img src="${ctx}/demo/images/avatar.png" alt="Avatar" /> 
        <div class="info">
        	<h3><shiro:principal/>&nbsp;&nbsp;&nbsp;&nbsp;${sellerName}</h3>
            <small>您好！今天是<span class="land-time">${date}</span></small>
            <p>
<%--             	<a href="${ctx}/login/changePwdUI" title="修改密码">修改密码</a> <a>|</a> --%>
            	<a id="theme-login" href="javascript:;">修改密码</a> <a>|</a>
<!--             	<input type="button" onclick="changePWD()" value="修改密码" title="修改密码"></input> <a>|</a> -->
	            <a href="${ctx}/login/help" title="帮助">帮助</a> <a>|</a>
	            <a href="${ctx}/login/logout" title="安全退出">安全退出</a>
            </p>
        </div><!--info-->
    </div><!--accountinfo-->
</div><!--header-->

<div class="sidebar">
	<div id="accordion">
        <h3 class="open">主导航</h3>
        <div class="content" style="display: block;">
        	<ul class="leftmenu">
        		<li id="firstLi" class="current">
        			<a href="${ctx}/login/index" class="home">首页</a>
        		</li>
        		<c:forEach items="${menuList}" var="menu">
                    <li id="${menu.id}li" onclick="gotoWh('${ctx}/${menu.newUrl}${menu.id}?titleName=${menu.name}','${menu.id}','${menu.name}')">
                    	<a id="${menu.id}a" href="#">${menu.name}</a>
                    </li>
                 </c:forEach>
            </ul>
        </div>
        <h3 class="open"></h3>
	</div>
    <div id="center"></div>
</div><!--leftmenu-->
<div class="maincontent">
	<iframe name="leftmenu" id="leftmenu"  src="${ctx}/login/getIndex" marginwidth="0" marginheight="0" allowTransparency="true" scrolling="true"  frameborder="0" style="background-color: white;" width="100%" height="85%"></iframe>
</div>
<!-- <div class="footer footer_float">
	<div class="footerinner">
    	&copy; 2011. Mandy Lane Premium Template. All Rights Reserved.
    </div>
</div> -->
<div class="theme-buy">
<!-- <a class="btn btn-primary btn-large theme-login" href="javascript:;">点击查看效果</a> -->
</div>

<div class="theme-popover">
     <div class="theme-poptit">
          <a href="javascript:;" title="关闭" class="close">×</a>
          <h3>修改密码</h3>
     </div>
     <div class="theme-popbod dform">
           <form id="userInfo" class="theme-signin" name="loginform" action="${ctx}/login/changePwd" method="post">
                <ol>
                     <li><strong>初始密码：</strong><input class="ipt" id="pwd" type="password" name="pwd" size="20" /></li>
                     <li><strong>新密码：</strong><input class="ipt" type="password" name="newpassword" id="newpassword" size="20" /></li>
                     <li><strong>重复新密码：</strong><input class="ipt" type="password" id="repassword" size="20" /></li>
                     <li>
                     	<input class="btn btn-primary" id="userSave" type="button" value="保存" />
                     	<input class="btn btn-primary" id="userClose" type="button" value="关闭" />
                     </li>
                </ol>
           </form>
     </div>
</div>
</body>
<script type="text/javascript">
//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓修改密码↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
jQuery(document).ready(function($) {
	$('#theme-login').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
	})
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	})
	
	
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
			    	}else{
			    		$.ajax({  
						    url:'${ctx}/login/changePwd',// 跳转到 action    
						    data:{    
						    	pwd : pwd,
						    	newpassword : newpassword,
						    	plainPassword : repassword,
						    },    
						    type:'post',    
						    cache:false,    
						    dataType:'json',
						    success:function(json) {
						    	alert(json.message);
						    	if(data.success) {
						    		$('.theme-popover-mask').fadeOut(100);
						    		$('.theme-popover').slideUp(200);
						    	}
						    }
			    		})
			    	}
			     }
			});
// 			$("#userInfo").submit();
		}
	});
	
	$("#userClose").click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
// 		document.location.href = '${ctx}/login/close';
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
})
//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑修改密码↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓首页出来左边菜单栏项↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
var pid = null;
var twoPid = null;
function gotoWh(url,id,urlTitleName){
	if(pid == null){
		jQuery("#firstLi").removeClass();
	}else{
		jQuery("#"+pid+"li").removeClass();
	}
	pid = id;
	var obj = jQuery("#"+id+"li");
	obj.addClass("current");
	var newUrlToken = null;
	var html = "";
	var url1 = '${ctx}/login/getTwoMenu/'+id;
	jQuery.ajax({  
	    url:url1,// 跳转到 action    
	    type:'get',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	jQuery.each(data,function(index){
	    		if(index==0){
	    			url = "${ctx}/"+this.newUrl+this.parentId+"?titleName="+this.name;
	    			newUrlToken = "${ctx}/"+this.newUrl+this.parentId+"?titleName="+urlTitleName;
	    		}
	    		if(this.createrName == 'current'){
	    			twoPid = this.id;
	    		}
	    		html += "<li id='two"+this.id+"li' class='"+this.createrName+"' onclick='changeTable(&#39;${ctx}/"+this.newUrl+this.parentId+"?titleName="+this.name+"&#39;,&#39;"+this.id+"&#39;)'>"+
	    			"<a href='#' class='dashboard'><span>"+this.name+"</span></a></li>";
	    	});
			jQuery("#twoMenu").html(html);
			window.open(newUrlToken,'leftmenu');
	     } 
	});
}
function changeTable(url,id){
	jQuery("#two"+twoPid+"li").removeClass();
	jQuery("#two"+id+"li").addClass("current");
	twoPid = id;
	window.open(url,'leftmenu');
}
//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑首页出来左边菜单栏项↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

</script>
</html>