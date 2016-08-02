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
	$("input[type='checkbox']").click(function() {
        if ($(this).prop("checked") == true) {
            $("input[type='checkbox']").attr("checked", false);
            $(this).prop("checked", true);
        }
    });
	
	$("#updateStudentInfo").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var studentInfoId = $("input[type='checkbox']:checked").val();
			$("#updateStudentInfo").attr("href",'${ctx}/studentInfo/update/'+studentInfoId+'?parentId=' + parentId);
		}
    });
	
	
	$("#reset").click(function(){
		$("#name").val("");
		$("#qq").val("");
		$("#microChart").val("");
		$("#mobilePhone").val("");
		$("#startEntranceDate").val("");
		$("#endEntranceDate").val("");
	});
	
	$("#studentSubmit").click(function(){
		 $('#student').submit();
	});
}); 
</script>
</head>
<body>
	<div class="qzsj">
		<form class="sj-mana" id="student" name="arForm" action="">
			<label class="sj-name">姓名</label> <input class="sj-inp" type="text" id="name" name="name" value="${userName}"/>&nbsp;&nbsp;
			<label class="sj-name">QQ</label> <input class="sj-inp" type="text" id="qq" name="qq" value="${qq}"/>&nbsp;&nbsp;
			<label class="sj-name">微信</label> <input class="sj-inp" type="text" id="microChart" name="microChart" value="${microChart}"/>&nbsp;&nbsp;
			<label class="sj-name">移动电话</label> <input class="sj-inp" type="text" id="mobilePhone" name="mobilePhone" value="${mobilePhone}"/>&nbsp;&nbsp;
			<label class="sj-name">入学日期</label>
			<input class="date-inp" id="startEntranceDate" value="${startEntranceDate}" name="startEntranceDate" onClick="return Calendar('startEntranceDate');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('startEntranceDate');" src="${ctx}/static/images/rilicon.png"  /></a>
			-
			<input class="date-inp" id="endEntranceDate" value="${endEntranceDate}" name="bornDate" onClick="return Calendar('endEntranceDate');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('endEntranceDate');" src="${ctx}/static/images/rilicon.png"  /></a> 
			<a id="studentSubmit"><img class="sj-query" src="${ctx}/static/images/queryBtn.png" /></a>
			<a id="reset"><img class="sj-reset" src="${ctx}/static/images/resetBtn.png" /></a>
		</form>
	</div>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:130px; float:left;">学员列表</h3>
           	   <h4 style="width:70px; float:left;"><a href="${ctx}/studentInfo/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>登记</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateStudentInfo"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="5%"><!-- <input name="" type="checkbox" value="" /> --></td>
			<td width="8%">姓名</td>
			<td width="10%">身份证</td>
			<td width="8%">移动电话</td>
			<td width="10%">入学日期</td>
			<td width="10%">邮箱</td>
			<td width="8%">QQ</td>
			<td width="8%">微信</td>
			<td width="18%">地址</td>
			<td width="15%">所属</td>
		</tr>
	  <c:forEach items="${studentList}" var="student">
	  	<tr>
			<td><input id="studentChecked" name="" type="checkbox" value="${student.id}" /></td>
			<td>${student.name}</td>
	  		<td>${student.cardCode}</td>
	  		<td>${student.mobilePhone}</td>
	  		<td>${student.entranceDate}</td>
			<td>${student.email}</td>
			<td>${student.qqAccount}</td>
			<td>${student.microChartAccount}</td>
			<td>${student.specificationAddress}</td>
			<td>${student.sellerName}</td>
		</tr>
	  </c:forEach>
	</table>
</body>
</html>