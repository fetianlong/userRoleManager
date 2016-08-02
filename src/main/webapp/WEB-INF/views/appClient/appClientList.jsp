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
	
	$("#reset").click(function(){
		$("#userName").val("");
		$("#tel").val("");
		$("#birthday").val("");
		$("#registerStartTime").val("");
		$("#registerEndTime").val("");
		$("#sex").val("");
		$("#origin").val("");
		$("#hospital").val("");
		$("#seller").val("");
		$("#province").val("");
		$("#city").val("");
	});
	
	$("#arSubmit").click(function(){
		 $('#ar').submit();
	});
}); 
</script>
</head>
<body>
	<div class="qzsj">
		<form class="sj-mana" id="ar" name="arForm" action="">
			<label class="sj-name">用户名</label> <input class="sj-inp" type="text" id="userName" name="userName" value="${userName}"/>&nbsp;&nbsp;
			<label class="sj-name">手机号</label> <input class="sj-inp" type="text" id="tel" name="tel" value="${tel}"/>&nbsp;&nbsp;
			<label class="sj-name">预产期</label> <input class="sj-inp" type="text" id="birthday" name="birthday" value="${birthday}"/>&nbsp;&nbsp;
			<label class="sj-name">注册时间</label>
			<input class="date-inp" id="registerStartTime" value="${registerStartTime}" name="registerStartTime" onClick="return Calendar('registerStartTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('registerStartTime');" src="${ctx}/static/images/rilicon.png"  /></a>
			-
			<input class="date-inp" id="registerEndTime" value="${registerEndTime}" name="registerEndTime" onClick="return Calendar('registerEndTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" onClick="return Calendar('registerEndTime');" src="${ctx}/static/images/rilicon.png"  /></a>
			<label class="sj-name">性别</label>
			<select class="activity-sel required" style="width:268px" id="sex" name="sex">
               	<option value="">-----请选择-----</option>
               	<option value="1" <c:if test="${sex eq 1}">selected="selected"</c:if>>男</option>
				<option value="2" <c:if test="${sex eq 2}">selected="selected"</c:if>>女</option>
            </select>
            </br>
            <label class="sj-name">来源</label>
			<select class="activity-sel required" style="width:268px" id="origin" name="origin">
               	<option value="">-----请选择-----</option>
               	<option value="1" <c:if test="${origin eq 1}">selected="selected"</c:if>>二维码</option>
				<option value="2" <c:if test="${origin eq 2}">selected="selected"</c:if>>app注册</option>
            </select>
			<label class="sj-name">医院</label> 
			 <select class="activity-sel"  name="hospital" id="hospital">
				<option value=""></option>
				<c:set var="hospitalId" value="${hospitalId}" />
			  <c:forEach items="${hospitalList}" var="hospital">
				<option value="${hospital.id}" <c:if test="${hospital.id eq hospitalId}">selected="selected"</c:if>>${hospital.hospitalName}</option>
			  </c:forEach>
			 </select>&nbsp;&nbsp;
			 <label class="sj-name">商家</label> 
			 <select class="activity-sel"  name="seller" id="seller">
				<option value=""></option>
				<c:set var="seller" value="${seller}" />
			  <c:forEach items="${sellerList}" var="seller">
				<option value="${seller.id}" <c:if test="${seller.id eq seller}">selected="selected"</c:if>>${seller.name}</option>
			  </c:forEach>
			 </select>&nbsp;&nbsp;
			  <label class="sj-name">省</label> 
			 <select class="activity-sel"  name="provinceId" id="province">
				<option value=""></option>
				<c:set var="provinceId" value="${provinceId}" />
			  <c:forEach items="${provinceList}" var="province">
				<option value="${province.id}" <c:if test="${province.id eq provinceId}">selected="selected"</c:if>>${province.name}</option>
			  </c:forEach>
			 </select>&nbsp;&nbsp; 
			  <label class="sj-name">市</label> 
			 <select class="activity-sel"  name="cityId" id="city">
				<option value=""></option>
				<c:set var="cityId" value="${cityId}" />
			  <c:forEach items="${cityList}" var="city">
				<option value="${city.id}" <c:if test="${city.id eq cityId}">selected="selected"</c:if>>${city.name}</option>
			  </c:forEach>
			 </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<a id="arSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
			<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:130px; float:left;">App注册用户列表</h3>
           </td>
        </tr>
		<tr>
			<td width="5%"><!-- <input name="" type="checkbox" value="" /> --></td>
			<td width="8%">用户姓名</td>
			<td width="4%">性别</td>
			<td width="6%">二维码</td>
			<td width="8%">商家名称</td>
			<td width="6%">注册时间</td>
			<td width="5%">手机号</td>
			<td width="8%">预产期</td>
			<td width="8%">医院名称</td>
			<td width="15%">来源</td>
			<td width="17%">省</td>
			<td width="10%">市</td>
		</tr>
	  <c:forEach items="${arList}" var="ar">
	  	<tr>
			<td><input id="actionManagermentChecked" name="" type="checkbox" value="${ar.id}" /></td>
			<td>${ar.userName}</td>
			<td>
				 <c:if test="${ar.sex eq 1}">男</c:if>
	             <c:if test="${ar.sex eq 2}">女</c:if>
			</td>
			<td>${ar.salerId}</td>
			<td>${ar.sellerName}</td>
			<td>${ar.createDateTime}</td>
			<td>${ar.tel}</td>
			<td>${ar.birthday}</td>
			<td>${ar.hospitalName}</td>
			<td>
				 <c:if test="${ar.origin eq 1}">二维码</c:if>
	             <c:if test="${ar.origin eq 2}">app注册</c:if>
			</td>
			<td>${ar.provinceName}</td>
			<td>${ar.cityName}</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>