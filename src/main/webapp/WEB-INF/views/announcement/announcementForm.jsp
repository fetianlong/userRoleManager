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
$(document).ready(function () {
	$("#announcement").validate();
	$("#announcementSave").click(function(){
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		 var arr = startTime.split("-");
		    var stime = new Date(arr[0], arr[1], arr[2]);
		    var stimes = stime.getTime();
		    var arrs = endTime.split("-");
		    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
		    var lktimes = lktime.getTime();
		    if (stimes > lktimes) {
		        alert('开始时间大于结束时间，请重新选择');
		        return false;
		    }
		  
		$("#announcement").submit();
	});
	
	$("#dictionaryClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/announcement/list/' + parentId;
	});
	$("#dictionaryCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/announcement/list/' + parentId;
	});
	
}); 
</script>
</head>
<body>
       <div class="hd-add actionbox">
                <h3>公告</h3>
                <span><img class="hd-close" src="${ctx}/static/images/closeicon.png" /></span>
                 <form id="announcement" action="${ctx}/announcement/addAnnouncement" method="post">
                <table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="font">标题</td>
                        <td><input class="name-inp" type="text" name="title" value="${announcement.title}" /><span>*</span></td>
                      </tr>
                    <tr>
                        <td class="font">内容</td>
                        <td><input class="addr-inp"  type="text" name="content" value="${announcement.content}" /><span>*</span></td>
                      </tr>
                      <tr>
                        <td class="font">开始时间</td>
						<td>
						<input class="date-inp" id="startTime" value="${announcement.startTime}" name="startTime" onClick="return Calendar('startTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('startTime');" src="${ctx}/static/images/rilicon.png"  /></a>
						<span>*</span>
						</td>
                      </tr>
                      <tr>
                        <td class="font">结束时间</td>
                         <td>
							<input class="date-inp" id="endTime" value="${announcement.endTime}" name="endTime" onClick="return Calendar('endTime');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" onClick="return Calendar('endTime');" src="${ctx}/static/images/rilicon.png"  /></a>
							<span>*</span>
						</td>
                      </tr>
                      <tr>
                        <td class="font">是否发布</td>
                        <td>
			                <select class="activity-sel required" style="width:268px" name="isPublish">
			                 	<option value="">-----请选择-----</option>
								<option value="1" <c:if test="${announcement.isPublish eq 1}">selected="selected"</c:if>>是</option>
			                 	<option value="0" <c:if test="${announcement.isPublish eq 0}">selected="selected"</c:if>>否</option>
				            </select><span>*</span>
		                </td>
                      </tr>
                </table>
                <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
                <input type="hidden" value="${announcement.id}" name="id"/>
                <div class="actionbox-btnZ">
                    <div class="hd-keepBtn" id="announcementSave">保存</div>
                    <div class="hd-closeBtn" id="dictionaryClose">关闭</div>
                 </div>
                 </form>
            </div>
</body>
</html>