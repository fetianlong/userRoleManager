<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../home/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
body {background-color: white;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<script type="text/JavaScript">
$(document).ready(function () {
	$("#delivery").validate();
	$("#deliverySave").click(function(){
		$("#delivery").submit();
	});
	
	$("#dictionaryClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/delivery/list/' + parentId;
	});
	$("#dictionaryCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/delivery/list/' + parentId;
	});
	
}); 
</script>
</head>
<body>
	<div class="breadcrumbs">
    	<a href="#">音乐镇痛分娩管理</a>
        <span>${param.titleName}</span>
    </div><!-- breadcrumbs -->
    <!--添加页面-->
    <div class="form_default" style="margin-left:16px; margin-top:20px;width: ">
    <form id="delivery" action="${ctx}/delivery/addDelivery" method="post">
        <fieldset>
            <legend>${param.titleName}</legend>
            <p>
                <label for="birthProcess" style="float:left;">产程</label>
                <select class="sf required" name="birthProcess">
                 	<option value="">-----请选择-----</option>
					<option value="1" <c:if test="${delivery.birthProcess eq 1}">selected="selected"</c:if>>第一产程</option>
                 	<option value="2" <c:if test="${delivery.birthProcess eq 2}">selected="selected"</c:if>>第二产程</option>
                 	<option value="3" <c:if test="${delivery.birthProcess eq 3}">selected="selected"</c:if>>第三产程</option>
                 	<option value="4" <c:if test="${delivery.birthProcess eq 4}">selected="selected"</c:if>>第四产程</option>
	            </select>
            </p>
             <p>
                <label for="cm" style="float:left;">公分</label>
                <select class="sf required" name="cm">
					<option value="0" <c:if test="${delivery.cm eq 0}">selected="selected"</c:if>>小于1cm</option>
					<option value="1" <c:if test="${delivery.cm eq 1}">selected="selected"</c:if>>1-3cm</option>
					<option value="2" <c:if test="${delivery.cm eq 2}">selected="selected"</c:if>>3-6cm</option>
					<option value="3" <c:if test="${delivery.cm eq 3}">selected="selected"</c:if>>6-10cm</option>
				</select>
            </p>
            <p>
                <label for="isPain" style="float:left;">是否疼痛</label>
                <select class="sf required" name="isPain">
                 	<option value="">-----请选择-----</option>
					<option value="1" <c:if test="${delivery.isPain eq true}">selected="selected"</c:if>>疼痛</option>
                 	<option value="0" <c:if test="${delivery.isPain eq false}">selected="selected"</c:if>>不疼痛</option>
	            </select>
            </p>
            <p>
                <label for="isSimulation" style="float:left;">是否模拟训练</label>
                <select class="sf required" name="isSimulation">
                 	<option value="">-----请选择-----</option>
					<option value="1" <c:if test="${delivery.isSimulation eq true}"> selected="selected"</c:if>>是</option>
                 	<option value="0" <c:if test="${delivery.isSimulation eq false}"> selected="selected"</c:if>>否</option>
	            </select>
            </p>
            <p>
                <label for="isSimulation" style="float:left;">所属周</label>
                <input type="text" class="sf" id="week" name="week" value="${delivery.week}" />
            </p>
            <input type="hidden" value="${parentId}" name="parentId" id="parentId" /> 
			<input type="hidden" value="${delivery.id}" name="id" />
            <p>
                <button id="deliverySave">提交</button>
                <input type="button" onclick="history.go(-1);" 
                style="margin-left:10px;background:#4b6592 url('${ctx}/demo//images/buttonbg3.png') repeat-x scroll left top;padding:7px 20px;color: #fff;cursor: pointer;border: 1px solid #395380;border-radius: 3px;" 
                value="返回" />
            </p>
            
        </fieldset>
        </form>
    </div>

	<!--添加页面-->

    <br clear="all" />
    <%-- 
	<div class="hd-add actionbox">
		<h3>音乐镇痛分娩</h3>
		<span><img class="hd-close"
			src="${ctx}/static/images/closeicon.png" /></span>
		<form id="delivery" action="${ctx}/delivery/addDelivery"
			method="post">
			<table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
			
					<tr>
                        <td class="font">产程</td>
                        <td>
			                <select class="activity-sel required" style="width:268px" name="birthProcess">
			                 	<option value="">-----请选择-----</option>
								<option value="1" <c:if test="${delivery.birthProcess eq 1}">selected="selected"</c:if>>第一产程</option>
			                 	<option value="2" <c:if test="${delivery.birthProcess eq 2}">selected="selected"</c:if>>第二产程</option>
			                 	<option value="3" <c:if test="${delivery.birthProcess eq 3}">selected="selected"</c:if>>第三产程</option>
				            </select><span>*</span>
		                </td>
                      </tr>
				<tr>
                        <td class="font">公分</td>
                        <td>
			                <select  style="width:268px" name="cm">
			                 	<option value="0"><c:if test="${delivery.cm eq 0}">selected="selected"</c:if>2厘米以内</option>
								<option value="1" <c:if test="${delivery.cm eq 1}">selected="selected"</c:if>>2-3厘米</option>
			                 	<option value="2" <c:if test="${delivery.cm eq 2}">selected="selected"</c:if>>3-6厘米</option>
			                 	<option value="3" <c:if test="${delivery.cm eq 3}">selected="selected"</c:if>>6-10厘米</option>
				            </select>
		                </td>
                      </tr>
				<tr>
					<td class="font">是否疼痛</td>
                        <td>
			                <select class="activity-sel required" style="width:268px" name="isPain">
			                 	<option value="">-----请选择-----</option>
								<option value="1" <c:if test="${delivery.isPain eq true}">selected="selected"</c:if>>疼痛</option>
			                 	<option value="0" <c:if test="${delivery.isPain eq false}">selected="selected"</c:if>>不疼痛</option>
				            </select><span>*</span>
		                </td>
				</tr>
			</table>
			<input type="hidden" value="${parentId}" name="parentId" id="parentId" /> 
			<input type="hidden" value="${delivery.id}" name="id" />
			<div class="actionbox-btnZ">
				<div class="hd-keepBtn" id="deliverySave">保存</div>
				<div class="hd-closeBtn" id="dictionaryClose">关闭</div>
			</div>
		</form>
	</div>
	 --%>
</body>
</html>