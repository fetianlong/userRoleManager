<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../home/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<!--      二级菜单 -->
	<ul>
   		<c:forEach items="${towMenuList}" var="towMenu" varStatus="status">
   			<li id="${towMenu.id}li" onclick="changeTable('${ctx}/${towMenu.newUrl}${towMenu.parentId}?titleName=${towMenu.name}','${towMenu.id}li')" ><a href="#" class="dashboard"><span>${towMenu.name}</span></a></li>
   		</c:forEach>
	</ul>
   <!-- tabmenu-->
    
</body>
<script type="text/javascript">
var pid = null;
function gotoWh(url,id){
// 	if(pid == null){
// 		jQuery("#firstLi").removeClass();
// 	}else{
// 		jQuery("#"+pid+"").removeClass();
// 	}
// 	pid = id;
// 	var obj = jQuery("#"+id+"");
// 	obj.addClass("current");

// 	window.open(url,'a_iframe');
	url = url + "?newCssClass="+id;
	window.location.href = url;
}
function getClass(){
// 	alert("${param.newCssClass}");
	if("${param.newCssClass}" != "firstLi"){
		jQuery("#firstLi").removeClass();
		jQuery("#${param.newCssClass}").addClass("current");
	}
}
// getClass();
function changeTable(url,id){
	alert(id);
	if(pid == null){
		jQuery("#firstLi").removeClass();
	}else{
		jQuery("#"+pid+"").removeClass();
	}
	pid = id;
	var obj = jQuery("#"+id+"");
	obj.addClass("current");
	jQuery("#"+id+"").addClass("current");
	window.location.href = url;
}


</script>
</html>