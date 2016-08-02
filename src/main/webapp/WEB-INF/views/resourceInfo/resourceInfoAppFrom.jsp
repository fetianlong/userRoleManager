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
	$("#resourceInfo").validate();
	$("#knowledgeSave").click(function(){
// 		alert();
		$("#resourceInfo").submit();
	});
	
});
function resourcetypeChange(value){
// 	alert(value);
	var brandId = $("#brandId").val();
// 	var url = "${ctx}/resourceInfo/getSeries";
	var url = "${ctx}/deliveryResource/selectSeriesByBrandResourceType";
	$.ajax({
		type: "get",
		url: url,
		data: {resourceType:value,brandId:brandId},
		cache: false,
		dataType: "json",
		success: function(json) {
			var opts = "<option value=''>--请选择系列--</option>";
			$.each(json, function(i, item) {
				var option = "<option value='"+item.seriesId+"'>"+item.seriesName+"</option>";
				opts = opts + option;
			});
			$("#seriesId").html(opts);
		}
	});
}
</script>
</head>
<body>
	<div class="breadcrumbs">
    	<a href="#">APP资源</a>
        <span>${param.titleName}</span>
    </div><!-- breadcrumbs -->
    <!--添加页面-->
    <div class="form_default" style="margin-left:16px; margin-top:20px;width: ">
    <form id="resourceInfo" action="${ctx}/resourceInfo/${action}" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>${param.titleName}</legend>
            <p>
                <label for="name" style="float:left;">选择文件</label>
                <input type="file" id="myFile" name="myFile" class="sf"/>
            </p>
            <p>
                <label for="name" style="float:left;">文件类型</label>
                <select name="resourcetype" onchange="resourcetypeChange(this.value)" class="sf required">
                	<option value="">--请选择文件类型--</option>
                	<option value="1" <c:if test="${resourceInfo.resourcetype eq 1}">selected="selected"</c:if>>音乐</option>
                	<option value="2" <c:if test="${resourceInfo.resourcetype eq 2}">selected="selected"</c:if>>图片</option>
                	<option value="3" <c:if test="${resourceInfo.resourcetype eq 3}">selected="selected"</c:if>>文档</option>
                	<option value="4" <c:if test="${resourceInfo.resourcetype eq 4}">selected="selected"</c:if>>视频</option>
                </select>
            </p>
            <p>
                <label for="name" style="float:left;">内部名称</label>
                <input type="text" class="sf required" name="resourcename" value="${resourceInfo.resourcename}" />
            </p>
            <p>
                <label for="name" style="float:left;">外部名称</label>
                <input type="text" class="sf required" name="displayname" value="${resourceInfo.displayname}" />
            </p>
            <p>
                <label for="name" style="float:left;">品牌</label>
                <select id="brandId" name="brandId">
                	<option value="4">APP-胎教</option>
                </select>
            </p>
            <p>
                <label for="name" style="float:left;">系列</label>
                <select id="seriesId" name="seriesId">
                	<option value="${resourceInfo.seriesId}">${resourceInfo.name}</option>
                </select>
            </p>
            <p>
                <label for="name" style="float:left;">标签</label>
                <input type="text" class="sf" name="tags" value="${resourceInfo.tags}" />
            </p>
            <p>
                <label for="email" style="float:left;">说明</label>
                <textarea rows="" cols="" class="mf" name="remark">${resourceInfo.remark}</textarea>
            </p>
            <p>
                <label for="email" style="float:left;">歌词</label>
                <textarea rows="" cols="" class="mf" name="lyrics">${resourceInfo.lyrics}</textarea>
            </p>
            <p>
                <label for="email" style="float:left;">适用场景</label>
                <textarea rows="" cols="" class="mf" name="usescene">${resourceInfo.usescene}</textarea>
            </p>
             <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
             <input type="hidden" value="${resourceInfo.id}" name="id"/>
            
            <p>
                <button id="knowledgeSave">提交</button>
                <input id="userClose" type="button" onclick="history.go(-1);" 
                style="margin-left:10px;background:#4b6592 url('${ctx}/demo//images/buttonbg3.png') repeat-x scroll left top;padding:7px 20px;color: #fff;cursor: pointer;border: 1px solid #395380;border-radius: 3px;" 
                value="返回" />
                <%-- 
                <c:if test="${param.titleName eq '修改用户'}">
                </c:if>
                <c:if test="${param.titleName != '修改用户'}">
                <button onclick="history.go(-1)" style="margin-left:10px;">返回</button>
                </c:if>
                 --%>
            </p>
            
        </fieldset>
        </form>
    </div>

	<!--添加页面-->

    <br clear="all" />
</body>
</html>