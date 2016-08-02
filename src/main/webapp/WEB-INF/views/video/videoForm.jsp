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
// 	$("#video").validate();
	if('${action}'=='addVideo'){
		jQuery("#video").validate({
			rules: {
				videoImgs: {
					required: true,
					checkVideoImgs: true
				},
				videoFile: {
					required: true,
					checkVideoType: true
				}
			},
			messages: {
				videoImgs: {
	                required: "请上传图片",
	                checkVideoImgs: "图片限于bmp,png,gif,jpeg,jpg格式"
	            },
	            videoFile: {
	                required: "请上传视频",
	                checkVideoType: "视频仅限于MP4格式"
	            }
			}
		});
		jQuery.validator.addMethod("checkVideoImgs", function(value, element) {
		    var filepath=$("#videoImgs").val();
		    //获得上传文件名
		    var fileArr=filepath.split("\\");
		    var fileTArr=fileArr[fileArr.length-1].toLowerCase().split(".");
		    var filetype=fileTArr[fileTArr.length-1];
		    //切割出后缀文件名
		    if(filetype != "jpg" && filetype != "bmp" && filetype != "png" && filetype != "jpeg"){
		        return false;
		    }else{
		        return true;
		    }
		}, "图片限于bmp,png,gif,jpeg,jpg格式");
		
		jQuery.validator.addMethod("checkVideoType", function(value, element) {
		    var filepath=$("#videoFile").val();
		    //获得上传文件名
		    var fileArr=filepath.split("\\");
		    var fileTArr=fileArr[fileArr.length-1].toLowerCase().split(".");
		    var filetype1=fileTArr[fileTArr.length-1];
		    //切割出后缀文件名
	// 	    alert(filetype1);
		    if(filetype1 != "MP4" && filetype1 != "mp4"){
		        return false;
		    }else{
		        return true;
		    }
		}, "视频仅限于MP4格式");
	}
	$("#videoSave").click(function(){
		$("#video").submit();
	});
	
	$("#dictionaryClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/video/list/' + parentId;
	});
	$("#dictionaryCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/video/list/' + parentId;
	});
	
}); 
</script>
</head>
<body>
	<div class="breadcrumbs">
    	<a href="#">分娩视频管理</a>
        <span>${param.titleName}</span>
    </div><!-- breadcrumbs -->
    <!--添加页面-->
    <div class="form_default" style="margin-left:16px; margin-top:20px;width: ">
    <form id="video" action="${ctx}/video/${action}" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>${param.titleName}</legend>
            <p>
                <label for="voidTitle" style="float:left;">名称</label>
                <input type="text" name="voidTitle" value="${video.voidTitle}" class="sf required" />
            </p>
             <p>
                <label for="isOpen" style="float:left;">是否公开</label>
                <select name="isOpen" class="sf required">
                 	<option value="">-----请选择-----</option>
					<option value="1" <c:if test="${video.isOpen eq true}">selected="selected"</c:if>>公开</option>
                 	<option value="0" <c:if test="${video.isOpen eq false}">selected="selected"</c:if>>不公开</option>
	            </select>
            </p>
            <p>
                <label for="videoImgs" style="float:left;">封面</label>
                <input  class="sf" type="file" id="videoImgs" name="videoImgs" />
                
            </p>
            <c:if test="${video.videoImg != '' && video.videoImg != null}">
            <p>
				<label for="email" style="float:left;"></label>
            	<img src="${imageUrl}${video.videoImg}" alt="" width="10%" />
            </p>
            </c:if>
            <p>
                <label for="videoFile" style="float:left;">视频</label>
                <input class="sf" type="file" id="videoFile" name="videoFile" />
            </p>
            <p>
             	<c:if test="${video.id > 0}">
               	<label for="email" style="float:left;">视频路径</label>
                ${videolUrl}${video.videoUrl}
                </c:if>
            </p>
             <p>
                <label for="email" style="float:left;">说明</label>
                <textarea rows="" cols="" class="sf" name="videoInfo" >${video.videoInfo}</textarea>
            </p>
             <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
             <input type="hidden" value="${video.id}" name="id"/>
             <input type="hidden" value="${video.videoUrl}" name="videoMp4"/>
             <input type="hidden" value="${video.videoImg}" name="videoImg"/>
            
            <p>
                <button id="videoSave">提交</button>
                <input type="button" onclick="history.go(-1);" 
                style="margin-left:10px;background:#4b6592 url('${ctx}/demo//images/buttonbg3.png') repeat-x scroll left top;padding:7px 20px;color: #fff;cursor: pointer;border: 1px solid #395380;border-radius: 3px;" 
                value="返回" />
            </p>
            
        </fieldset>
        </form>
    </div>

	<!--添加页面-->

    <br clear="all" />
</body>
</html>