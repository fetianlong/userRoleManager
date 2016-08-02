<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="cityId" value="${seller.city}" />
<c:set var="areaId" value="${seller.area}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
	$(document).ready(function() {
		
		$("#loginName").change(function(){
			var loginName = $("#loginName").val();
			$.ajax({  
			    url:'${ctx}/seller/checkExistByUserName',// 跳转到 action    
			    data:{    
			    	loginName : loginName
			    },    
			    type:'get',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	alert(data);
			    	if(!data) {
			    		alert("用户名已存在，请更换用户名！");
			    	}
			     }
			});
		});
		
		$("#sellerSave").click(function(){
			var loginName = $("#loginName").val();
			$.ajax({  
			    url:'${ctx}/seller/checkExistByUserName',// 跳转到 action    
			    data:{    
			    	loginName : loginName
			    },    
			    type:'get',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	if(data) {
			    		$("#seller").submit();
			    	}else{
			    		alert("用户名已存在，请更换用户名！");
			    	}
			     }
			});
		});
		
		$("#sellerClose").click(function(){
			var parentId = $('#parentId').val();
			var sellersign = $('#sellersign').val();
			var selledcnt = $('#selledcnt').val();
			document.location.href = '${ctx}/seller/close/' + parentId+'?sellersign='+sellersign+'&selledcnt='+selledcnt;
		});
		
		$("#seller").validate();
		
		$("#contactPersonCloseButton").click(function() {
			var parentId = $('#parentId').val();
			var sellersign = $('#sellersign').val();
			var selledcnt = $('#selledcnt').val();
			document.location.href = '${ctx}/seller/close/' + parentId+'?sellersign='+sellersign+'&selledcnt='+selledcnt;
		});

	});
</script>
</head>
<body>
	<div class="add-sj">
		    <c:if test="${sellersign eq 1 && selledcnt eq 0}">
               <h3>潜在加盟商转正</h3>
             </c:if>
           	 <c:if test="${sellersign eq 0 && selledcnt eq 0}">
           	   <h3>潜在商家转正</h3>
           	 </c:if>
            <span><img class="sj-close" src="${ctx}/static/images/closeicon.png" /></span>
           <form id="seller" action="${ctx}/seller/editSellerUserName" method="post" enctype="multipart/form-data">
            <table class="qzsj-tab" width="100%" cellspacing="0" cellpadding="0">
	                <tr>
	                    <td class="font">登陆用户名</td>
	                    <td><input class="obje-inp"  type="text" id="loginName" name="loginName" value=""/><span>*</span></td>
                  	</tr>
                  	<tr>
						<td class="font">登陆密码:</td>
						<td><font color="red">密码默认为电话</font></td>
					</tr>
            </table>
            <input type="hidden" id="sellersign" name="sellersign" value="${sellersign}" />
            <input type="hidden" id="selledcnt" name="selledcnt" value="${selledcnt}" />
            <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
            <input type="hidden" value="${seller.id}" name="id"/>
            <input type="hidden" id="tel" value="${seller.tel}" name="tel"/>
            <div class="qz-btnZ">
                <div class="qz-keepBtn" id="sellerSave">保存</div>
                <div class="qz-closeBtn" id="sellerClose">关闭</div>
             </div>
             </form>
         </div>
</body>
</html>