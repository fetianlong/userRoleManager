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
<script type="text/javascript" src="${ctx}/demo/js/plugins/jquery-1.7.min.js"></script>
<title>悦迪管理平台</title>
<script type="text/JavaScript">
	$(document).ready(function() {
		var provinceId = "";
		$("#province").change(function(){
			provinceId = $("#province").val();
			if(provinceId != '') {
				$.ajax({  
				    url:'${ctx}/seller/listCityByParentId',// 跳转到 action    
				    data:{    
				    	provinceId : provinceId
				    },    
				    type:'get',    
				    cache:false,    
				    dataType:'json',    
				    success:function(data) {
				    	$("#city").empty();
			    		$("#city").append("<option value=''></option>");
				    	for(var i=0; i<data.length; i++) {
				    		$("#city").append("<option value="+data[i].id+">"+data[i].name+"</option>");
				    	}
				     }
				});
			}else{
				$("#city").empty();
				$("#area").empty();
			}
		});
		
		
		if(provinceId != '') {
			$.ajax({  
			    url:'${ctx}/seller/listCityByParentId',// 跳转到 action    
			    data:{    
			    	provinceId : provinceId
			    },    
			    type:'get',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	for(var i=0; i<data.length; i++) {
			    		if(data[i].id == '${cityId}') {
			    			$("#city").append("<option selected='selected' value="+data[i].id+">"+data[i].name+"</option>");
			    		}
			    		$("#city").append("<option value="+data[i].id+">"+data[i].name+"</option>");
			    	}
			    	
			    	var cityId = $("#city").val();
			    	if(cityId != '') {
			    		$.ajax({  
						    url:'${ctx}/seller/listAreaByParentId',// 跳转到 action    
						    data:{    
						    	cityId : cityId
						    },    
						    type:'get',    
						    cache:false,    
						    dataType:'json',    
						    success:function(data) {
						    	alert();
						    	$("#area").empty();
						    	$("#area").append("<option value=''></option>");
						    	for(var i=0; i<data.length; i++) {
						    		if(data[i].id == '${areaId}') {
						    			$("#area").append("<option selected='selected' value="+data[i].id+">"+data[i].name+"</option>");
						    		}
						    		$("#area").append("<option value="+data[i].id+">"+data[i].name+"</option>");
						    	}
						     }
						});
			    	}
			     }
			});
		}
		
		
		$("#city").change(function(){
			var cityId = $("#city").val();
			$.ajax({  
			    url:'${ctx}/seller/listAreaByParentId',// 跳转到 action    
			    data:{    
			    	cityId : cityId
			    },    
			    type:'get',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	$("#area").empty();
			    	$("#area").append("<option value=''></option>");
			    	for(var i=0; i<data.length; i++) {
			    		$("#area").append("<option value="+data[i].id+">"+data[i].name+"</option>");
			    	}
			     }
			});
		});
		
		var result = document.getElementById("result");
		var input = document.getElementById("file_input");

		if(typeof FileReader === 'undefined'){
			result.innerHTML = "抱歉，你的浏览器不支持 FileReader";
			input.setAttribute('disabled','disabled');
		}else{
			input.addEventListener('change',readFile,false);
		}
					
		function readFile(){
			var file = this.files[0];
			if(!/image\/\w+/.test(file.type)){
				alert("请确保文件为图像类型");
				return false;
			}
			var reader = new FileReader();
			reader.readAsDataURL(file);
			reader.onload = function(e){
				result.innerHTML = '<img src="'+this.result+'" width="100" heigth="100" alt=""/>'
			}
		}
		
		$("#sellerSave").click(function(){
			$("#userInfo").submit();
		});
		
		$("#sellerClose").click(function(){
			var parentId = $('#parentId').val();
			var sellersign = $('#sellersign').val();
			var selledcnt = $('#selledcnt').val();
			document.location.href = '${ctx}/seller/close/' + parentId+'?sellersign='+sellersign+'&selledcnt='+selledcnt;
		});
		
		$("#sellerButtonClose").click(function(){
			var parentId = $('#parentId').val();
			var sellersign = $('#sellersign').val();
			var selledcnt = $('#selledcnt').val();
			document.location.href = '${ctx}/seller/close/' + parentId+'?sellersign='+sellersign+'&selledcnt='+selledcnt;
		});
		
		$("#contactPerson").validate();
		$("#contactPersonSave").click(function() {
			$("#contactPerson").submit();
		});

		$("#contactPersonClose").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/contactPerson/close/' + parentId;
		});
		$("#contactPersonCloseButton").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/contactPerson/close/' + parentId;
		});

	});
</script>
</head>
<body class="bodygrey">


	<div class="maincontent">
	  
	    <div class="breadcrumbs">
			<a href="${ctx}/login/index">首页</a>
			<span>${titleName}</span>
	    </div><!-- breadcrumbs -->
	    <!--添加页面-->
	    <form id="userInfo" action="${ctx}/seller/addSeller" method="post" enctype="multipart/form-data">
		    <div class="form_default" style="margin-left:16px; margin-top:20px;">
		        <fieldset>
		        	<c:if test="${sellersign eq 1 && selledcnt eq 0}">
		               <legend>新增潜在加盟商</legend>
		             </c:if>
		             <c:if test="${sellersign eq 1 && selledcnt eq 1}">
		           	   <legend>新增加盟商</legend>
		           	 </c:if>
		           	 <c:if test="${sellersign eq 0 && selledcnt eq 0}">
		           	   <legend>新增潜在商家</legend>
		           	 </c:if>
		           	 <c:if test="${sellersign eq 0 && selledcnt eq 1}">
		           	   <legend>新增商家</legend>
		           	 </c:if>
		            
		            <p>
		                <label for="name" style="float:left;">商家名称</label>
		                <input type="text" value="${seller.name}" name="name" class="sf" />
		            </p>
		            
		            <p>
		                <label for="tel" style="float:left;">手机号码</label>
		                <input type="text" value="${seller.tel}" name="tel" class="sf" />
		            </p>
		             <p>
		                <label for="orgCode" style="float:left;">机构编号</label>
		                <input type="text" value="${seller.orgCode}" name="orgCode"  class="sf" />
		            </p>
		             <p>
		                <label for="charterCode" style="float:left;">营业执照编号</label>
		                <input type="text" value="${seller.charterCode}" name="charterCode"  class="sf" />
		            </p>
		             <p>
		                <label for="file" style="float:left;">商家图片</label>
		                <input type="file" id="file_input" name="file" class="sf" />
		            </p>
		            <c:if test="${!empty seller.img}">
		            <p>
	                    <label style="float:left;">商家图片</label>
						<img src="http://${ip}:${port}/yuedi-resource/resources/picture/${seller.img}" width="250" heigth="150" alt=""/>
	                </p>
	                </c:if>
	                <c:if test="${sellersign eq 1 && selledcnt eq 1}">
		                <p>
		                    <label style="float:left;">登陆用户名</label>
		                    <input class="sf"  type="text" id="loginName" name="loginName" value="${seller.loginName}"/>
	                  	</p>
	                  	<p>
							<label style="float:left;">登陆密码:</label>
							<label>密码默认为手机号</label>
						</p>
		            </c:if>
	             	<c:if test="${sellersign eq 0 && selledcnt eq 1}">
		                <p>
		                    <label style="float:left;">登陆用户名</label>
		                    <input class="sf"  type="text" id="loginName" name="loginName" value="${seller.loginName}"/>
	                  	</p>
	                  	<p>
							<label style="float:left;">登陆密码:</label>
							<span style="margin-top:5px;display:inline-block">密码默认为电话</span>
						</p>
		            </c:if>
		            <p>
		                <label for="province" style="float:left;">所在省</label>
		                <select name="province" id="province">
	                    	<option value=""></option>
	                    	<c:set var="provinceId" value="${seller.province}" />
						  <c:forEach items="${areasList}" var="areas">
							<option value="${areas.id}" <c:if test="${areas.id eq provinceId}">selected="selected"</c:if>>${areas.name}</option>
						  </c:forEach>
						</select>
		            </p>
		            <p>
		                <label for="city" style="float:left;">所在市</label>
		                <select name="city" id="city"></select>
		            </p>
		            <p>
		                <label for="area" style="float:left;">所在区</label>
		                <select name="area" id="area"></select>
		            </p>
		               
		            <p>
		                <label for="location" style="float:left;">地址所在街道</label>
		                <input type="text" name="officeAddr" value="${seller.officeAddr}" class="mf"/>
		            </p>
		            <p>
		                <label for="location" style="float:left;">说明</label>
		                <textarea name="remark" class="mf" cols="" rows="">${seller.remark}</textarea>
		            </p>
		             <p>
		                <label for="gender" class="nopadding" style="float:left;">商家类型</label>
		                <input id="sjradio" name="category" type="radio" value="2" <c:if test="${seller.category eq 2}">checked='checked'</c:if>/> 医疗健康 &nbsp;&nbsp;&nbsp;&nbsp; 
	                    <input id="sjradio1" name="category" type="radio" value="3" <c:if test="${seller.category eq 3}">checked='checked'</c:if>/> 母婴服务&nbsp;&nbsp;&nbsp;&nbsp; 
	                    <input id="sjradio3" name="category" type="radio" value="6" <c:if test="${seller.category eq 6}">checked='checked'</c:if>/> 教育培训
		            </p>
		  			<input type="hidden" id="sellersign" name="sellersign" value="${sellersign}" />
		            <input type="hidden" id="selledcnt" name="selledcnt" value="${selledcnt}" />
		            <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
		            <input type="hidden" value="${seller.id}" name="id"/>
		  			<p>
	                	<button id="sellerSave">提交</button>
<!-- 	                	<button id="sellerClose" style="margin-left:10px;">返回</button> -->
	                	<button onclick="history.back()" style="margin-left:10px;">返回</button>
	                </p>
		        </fieldset>
		    </div>
		</form>
	  <!--添加页面-->
	   
	
	    <br clear="all" />
	    
	</div><!--maincontent-->



<br />
	
</body>
</html>