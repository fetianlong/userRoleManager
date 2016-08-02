<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %> --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
	$(document).ready(function() {
		$("#activity").validate();
		$("#activitySave").click(function() {
			$("#activity").submit();
		});
		
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
			    	$("#city").append("<option value=''>---------请选择--------</option>");
			    	for(var i=0; i<data.length; i++) {
			    		$("#city").append("<option value="+data[i].id+">"+data[i].name+"</option>");
			    	}
			    	$("#address").empty();
			    	var proviceParam = $("#province  option:selected").text();
			    	var provice = proviceParam.substr(1,proviceParam.length);
			    	$("#address").attr("value",provice);
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
			    	$("#area").append("<option value=''>---------请选择--------</option>");
			    	for(var i=0; i<data.length; i++) {
			    		$("#area").append("<option value="+data[i].id+">"+data[i].name+"</option>");
			    	}
			    	var proviceParam = $("#province  option:selected").text();
			    	var provice = proviceParam.substr(1,proviceParam.length);
			    	var cityParam = $("#city  option:selected").text();
			    	var city = cityParam.substr(1,cityParam.length);
			    	$("#address").attr("value",provice+city);
			     }
			});
		});
		
		$("#area").change(function(){
			var proviceParam = $("#province  option:selected").text();
	    	var provice = proviceParam.substr(1,proviceParam.length);
	    	var cityParam = $("#city  option:selected").text();
	    	var city = cityParam.substr(1,cityParam.length);
	    	var areaParam = $("#area  option:selected").text();
	    	var area = areaParam.substr(1,areaParam.length);
	    	$("#address").attr("value",provice+city+area);
		});

		$("#activityClose").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/activity/close/' + parentId;
		});
		$("#activityCloseButton").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/activity/close/' + parentId;
		});

	});
</script>
</head>
<body>
	<div class="hd-add hdpush">
		<h3>活动发布-新增</h3>
		<span><img class="hd-close" id="activityCloseButton" src="${ctx}/static/images/closeicon.png" /></span>
		<form id="activity" action="${ctx}/activity/addActivity" method="post" enctype="multipart/form-data">
		<table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td class="font">活动名称</td>
				<td><input class="name-inp required" type="text" name="activityName" value="${activity.activityName}" /><span>*</span></td>
			</tr>
			<tr>
				<td class="font">活动发布类别</td>
				<td><div id="radiopush">
						<label for="sjradio">个人</label><input id="sjradio" name="category" type="radio" value="0" <c:if test="${activity.category eq 0}">checked="checked"</c:if>/>
					</div>
					<div id="radiopush">
						<input id="sjradio1" name="category" type="radio" value="1" <c:if test="${activity.category eq 1}">checked="checked"</c:if>/><label
							for="sjradio1">商家</label>
					</div>
				</td>
			</tr>
			<tr>
				<td class="font">活动类型</td>
				<td><div id="radiopush">
						<input id="sjradio" name="activityType" type="radio" value="0" <c:if test="${activity.activityType eq 0}">checked="checked"</c:if>/><label
							for="sjradio">聚会</label>
					</div>
					<div id="radiopush">
						<input id="sjradio1" name="activityType" type="radio" value="1" <c:if test="${activity.activityType eq 1}">checked="checked"</c:if>/><label
							for="sjradio1">讲座</label>
					</div>
					<div id="radiopush">
						<input id="sjradio3" name="activityType" type="radio" value="2" <c:if test="${activity.activityType eq 2}">checked="checked"</c:if>/><label
							for="sjradio3">亲子游或者音乐</label>
					</div>
				</td>
			</tr>
			<tr>
				<td class="font">活动详情</td>
				<td><textarea class="area-inp required" style="margin-top: 10px;"
						name="details" cols="" rows="">${activity.details}</textarea><span>*</span></td>
			</tr>
			<tr>
				<td class="font">活动图片</td>
				<td><input type="file" class="in-t" id="file_input"  name="file"/></td>
			</tr>
			<tr>
				<td class="font">活动所在省</td>
				<td>
                    <select name="province" id="province" class="required">
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
				<td class="font">活动所在市</td>
				<td><select name="city" id="city" class="required"></select><span>*</span></td>
			</tr>
			<tr>
				<td class="font">活动所在区</td>
				<td><select name="area" id="area" class="required"></select><span>*</span></td>
			</tr>
			<tr>
				<td class="font">活动地址</td>
				<td><input class="name-inp required" type="text" id="address" name="address" value="${activity.address}" /><span>*</span></td>
			</tr>
			<tr>
				<td class="font">活动开始时间</td>
				<td><input class="date-inp required" id="startTime" value="${activity.startTime}" name="startTime" onClick="return Calendar('startTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('startTime');" src="${ctx}/static/images/rilicon.png"  /></a><span>*</span></td>
			</tr>
			<tr>
				<td class="font">活动结束时间</td>
				<td><input class="date-inp required" id="endTime" value="${activity.endTime}" name="endTime" onClick="return Calendar('endTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('endTime');" src="${ctx}/static/images/rilicon.png"  /></a><span>*</span></td>
			</tr>
		</table>
		 <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
         <input type="hidden" value="${activity.id}" name="id"/>
		<div class="actionbox-btnZ">
			<div class="hd-keepBtn" id="activitySave">保存</div>
			<div class="hd-closeBtn" id="activityClose">关闭</div>
		</div>
		</form>
	</div>
</body>
</html>