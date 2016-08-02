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
		$("#province").change(function(){
			var provinceId = $("#province").val();
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
			    	for(var i=0; i<data.length; i++) {
			    		$("#city").append("<option value="+data[i].id+">"+data[i].name+"</option>");
			    	}
			     }
			});
		});
		
		var provinceId = $("#province").val();
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
<body>
	<div class="add-sj">
            <h3>商家编辑</h3>
            <span><img class="sj-close" id="sellerButtonClose" src="${ctx}/static/images/closeicon.png" /></span>
           <form id="userInfo" action="${ctx}/seller/addSeller" method="post" enctype="multipart/form-data">
            <table class="qzsj-tab" width="100%" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="font">商家名称</td>
                    <td><input class="name-inp"  type="text" value="${seller.name}" name="name"/><span>*</span></td>
                  </tr>
                  <tr>
                    <td class="font">商家电话</td>
                    <td><input class="name-inp"  type="text" value="${seller.tel}" name="tel"/><span>*</span></td>
                  </tr>
                  <tr>
                    <td class="font">机构编号</td>
                    <td><input class="addr-inp"  type="text" value="${seller.orgCode}" name="orgCode"/><span>*</span></td>
                  </tr>
                  <tr>
                    <td class="font">营业执照编号</td>
                    <td><input class="obje-inp"  type="text" value="" value="${seller.charterCode}" name="charterCode"/><span>*</span></td>
                  </tr>
                  <tr>
                    <td class="font">商家图片</td>
                    <td><input type="file" class="in-t" id="file_input"  name="file"/><!-- <a href="#" title="" class="like-file">浏览图片</a> --><span>*</span></td>
                  </tr>
                  <tr>
                    <td class="formLable"></td>
					<td id="result"><c:if test="${!empty seller.img}"><img src="http://${ip}:${port}/yuedi-resource/resources/picture/${seller.img}" width="100" heigth="100" alt=""/></c:if></td>
                  </tr>
                  <tr>
                    <td class="font">所在省</td>
                    <td>
                    <select name="province" id="province">
                    	<option value=""></option>
                    	<c:set var="provinceId" value="${seller.province}" />
					  <c:forEach items="${areasList}" var="areas">
						<option value="${areas.id}" <c:if test="${areas.id eq provinceId}">selected="selected"</c:if>>${areas.name}</option>
					  </c:forEach>
					</select>
                    <span>*</span>
                    </td>
                  </tr>
                  <tr>
                    <td class="font">所在市</td>
                    <td><select name="city" id="city"></select><span>*</span></td>
                  </tr>
                  <tr>
                    <td class="font">所在区</td>
                    <td><select name="area" id="area"></select><span>*</span></td>
                  </tr>
                  <tr>
                    <td class="font">所在街道地址</td>
                    <td><input class="obje-inp"  type="text" name="officeAddr" value="${seller.officeAddr}" /><span>*</span></td>
                  </tr>
                  <tr>
                    <td class="font">类型</td>
                    <td>
                    <div id="radiobox">
                    <input id="sjradio" name="category" type="radio" value="2" <c:if test="${seller.category eq 2}">checked='checked'</c:if>/><label for="sjradio">医疗健康</label></div><div id="radiobox">
                    <input id="sjradio1" name="category" type="radio" value="3" <c:if test="${seller.category eq 3}">checked='checked'</c:if>/><label for="sjradio1">母婴服务</label></div><div id="radiobox">
                    <input id="sjradio3" name="category" type="radio" value="6" <c:if test="${seller.category eq 6}">checked='checked'</c:if>/><label for="sjradio3">教育培训</label></div></td>
                  </tr>
                 <tr>
                    <td class="font">说明</td>
                    <td><textarea class="area-inp" style=" margin-top:10px; " name="remark" cols="" rows="">${seller.remark}</textarea></td>
                 </tr>
            </table>
            <input type="hidden" id="sellersign" name="sellersign" value="${sellersign}" />
            <input type="hidden" id="selledcnt" name="selledcnt" value="${selledcnt}" />
            <input type="hidden" value="${seller.id}" name="id"/>
            <div class="qz-btnZ">
                <div class="qz-keepBtn" id="sellerSave">保存</div>
                <div class="qz-closeBtn" id="sellerClose">关闭</div>
             </div>
             </form>
         </div>
</body>
</html>