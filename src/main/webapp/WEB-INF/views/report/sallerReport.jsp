<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="provinceId" value="${province}" />
<c:set var="cityId" value="${city}" />
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
	
	$("#reset").click(function(){
		$("#beginDateTime").val("");
		$("#endDateTime").val("");
	});
	
	$("#sallerReportSubmit").click(function(){
		 $('#sallerReport').submit();
	});
	
	var beginDateTime = $("#beginDateTime").val();
	var endDateTime = $("#endDateTime").val();
	if(beginDateTime != '' && endDateTime != '') {
		$.ajax({  
		    url:'${ctx}/sallerReport/listchart',// 跳转到 action    
		    data:{    
		    	beginDateTime : beginDateTime,
		    	endDateTime : endDateTime
		    },    
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	$("#chartdiv").empty();
				var chartData = loadAcharms(data);
		        chart = new AmCharts.AmPieChart();
		        chart.dataProvider = chartData;
		        chart.titleField = "country";
		        chart.valueField = "value";
		        chart.outlineColor = "#FFFFFF";
		        chart.outlineAlpha = 0.8;
		        chart.outlineThickness = 2;
		        // this makes the chart 3D
		        chart.depth3D = 15;
		        chart.angle = 30;

		        chart.write("chartdiv");
		     }
		});
	}else {
		alert("时间不能为空！");
	}
	
	function loadAcharms(obj) {
		var chartData = [];
		for(var i=0;i<obj.length;i++) {
			var jsonObj = {country: "", value: 10};
			jsonObj.country = obj[i].userName;
			jsonObj.value = obj[i].clientCount;
			chartData.push(jsonObj);
		}
		return chartData;
	}
});
</script>
</head>
<body>
	<div class="qzsj">
		<form class="sj-mana" id="sallerReport" name="sallerReportForm" action="">
			<label class="sj-name">时间</label> 
			<input class="date-inp" id="beginDateTime" value="${beginDateTimeString}" name="beginDateTime" onClick="return Calendar('beginDateTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('beginDateTime');" src="${ctx}/static/images/rilicon.png"  /></a>
			-
			<input class="date-inp" id="endDateTime" value="${endDateTimeString}" name="endDateTime" onClick="return Calendar('endDateTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" onClick="return Calendar('endDateTime');" src="${ctx}/static/images/rilicon.png"  /></a>
			<a id="sallerReportSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="5" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:300px; float:left;">业务人员二维码扫码注册统计注册用户数</h3>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="5%"></td>
			<td width="30%">拥有人</td>
			<td width="30%">二维码编号</td>
			<td width="20%">分配日期</td>
			<td width="15%">注册用户数</td>
		</tr>
	  
	  <c:forEach items="${salerUserinfoList}" var="salerUserinfo">
	  	<tr>
			<td><input name="" type="checkbox" value="${salerUserinfo.id}" /></td>
			<td>${salerUserinfo.userName}</td>
			<td>${salerUserinfo.cardCode}</td>
			<td>${salerUserinfo.createDateTimeString}</td>
			<td>${salerUserinfo.clientCount}</td>
		</tr>
	  </c:forEach>
	</table>
	<div id="chartdiv" style="width: 100%; height: 400px;"></div>
</body>
</html>