<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="../home/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
body {background-color: white;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦迪管理平台</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/filter.css">
<script type="text/JavaScript">
$(document).ready(function () {
	$("input[type='checkbox']").click(function() {
        if ($(this).prop("checked") == true) {
            $("input[type='checkbox']").attr("checked", false);
            $(this).prop("checked", true);
        }
    });
	
	$("#selectDelivery").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var url = "${ctx}/deliveryResource/select/";
			var deliveryId = $('#deliveryId').val();
			var resourceId = $("input[type='checkbox']:checked").val();
			var params={resourceId:resourceId,deliveryId:deliveryId};
			alert(deliveryId +"--" + resourceId);
			console.log(params);
			$.ajax({
				type: "get",
				url: url,
				data: params,
				cache: false,
				dataType: "json",
				success: function(responseText) {
					loadNoSelectResourceData();
				}
			});
		}
	});
	$("#deleteDeliveryResource").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var deliveryId = $('#deliveryId').val();
			var deliveryResourceId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除该关联的资源么?');
			if(boolconfirm) {
				$("#deleteDeliveryResource").attr("href",'${ctx}/deliveryResource/delete/'+deliveryResourceId+'?parentId=' + parentId+'&deliveryId='+deliveryId);
			}
		}
    });
	
	$("#returnDelivery").click(function() {
		var parentId = $('#parentId').val();
		var deliveryId = $("#deliveryId").val();
// 		<input type="hidden" name="connenctDeliveryName" value="${connenctDeliveryName}" />
// 		<input type="hidden" name="cmName" value="${cmName}" />
		$("#returnDelivery").attr("href",'${ctx}/deliveryResource/listSelectDeliveryResource/'+parentId+'?deliveryId=' + deliveryId + "&connenctDeliveryName=${connenctDeliveryName}&cmName=${cmName}");
    });
	// 加载品牌资源
	function loadBrandData() {
			var selectBrand = $('#selectBrand');
			selectBrand.empty();
			var json=[{"id":4,"name":"app-胎教"}];
			$.each(json, function(i, item) {
				try {
					var itemObj = $("<div class='filter-div-default' name='selectBrand_list' codeValue='" + item.id + "'>" + item.name + "</div>");
					itemObj.mouseover(function(){
						$(this).addClass("filter-div-hover");
					});
					itemObj.mouseout(function(){
						$(this).removeClass("filter-div-hover");
					});	
					itemObj.bind("click", function() {
						$("div[name='selectBrand_list']").each(function(){
							$(this).removeClass("filter-div-selected");
						});
						
						$(this).addClass("filter-div-selected");
						loadResourceTypeData();
					});
					selectBrand.append(itemObj);
				} catch(e) {
					alert(e.description);
				}
			});	// end
			$("div[name='selectBrand_list']").first().click();
	}	// end
	//加载资源分类数据
	function loadResourceTypeData(){
		var brandId =$("div[name='selectBrand_list'][class*='filter-div-selected']").attr("codeValue");
		console.log("brandId %s",brandId);
		var selectResourceType = $('#selectResourceType');
		selectResourceType.empty();
		var json=[{"id":1,"name":"音乐"},{"id":2,"name":"图片"},{"id":3,"name":"文档"},{"id":4,"name":"视频"}];
		$.each(json, function(i, item) {
			try {
				var itemObj = $("<div class='filter-div-default' name='selectResourceType_list' codeValue='" + item.id + "'>" + item.name + "</div>");
				itemObj.mouseover(function(){
					$(this).addClass("filter-div-hover");
				});
				itemObj.mouseout(function(){
					$(this).removeClass("filter-div-hover");
				});	
				itemObj.bind("click", function() {
					$("div[name='selectResourceType_list']").each(function(){
						$(this).removeClass("filter-div-selected");
					});
// 					alert(item.id);
					$(this).addClass("filter-div-selected");
					loadSeriesData();
				});
				selectResourceType.append(itemObj);
			} catch(e) {
				alert(e.description);
			}
		});	// end
		$("div[name='selectResourceType_list']").first().click();
	}
	//加载系列数据
	function loadSeriesData(){
		var brandId =$("div[name='selectBrand_list'][class*='filter-div-selected']").attr("codeValue");
		var resourceType =$("div[name='selectResourceType_list'][class*='filter-div-selected']").attr("codeValue");
		console.log("brandId %s resourceType %s",brandId,resourceType);
		$.getJSON("${ctx}/deliveryResource/selectSeriesByBrandResourceType",{brandId:brandId,resourceType:resourceType},function(json){
			console.log("series %o ",json);
			var selectSeries = $('#selectSeries');
			selectSeries.empty();
			$.each(json, function(i, item) {
				try {
					var itemObj = $("<div class='filter-div-default' name='selectSeries_list' codeValue='" + item.seriesId + "'>" + item.seriesName + "</div>");
					itemObj.mouseover(function(){
						$(this).addClass("filter-div-hover");
					});
					itemObj.mouseout(function(){
						$(this).removeClass("filter-div-hover");
					});	
					itemObj.bind("click", function() {
						$("div[name='selectSeries_list']").each(function(){
							$(this).removeClass("filter-div-selected");
						});
						
						$(this).addClass("filter-div-selected");
						loadNoSelectResourceData();
					}); 
					selectSeries.append(itemObj);
				} catch(e) {
					alert(e.description);
				}
			});	// end
			$("div[name='selectSeries_list']").first().click();
			
		});	// end
	}	// end
	// 加载没有选中资源
	function loadNoSelectResourceData() {
		var url = "${ctx}/deliveryResource/listNoSelectDeliveryResource/";
		var brandId =$("div[name='selectBrand_list'][class*='filter-div-selected']").attr("codeValue");
		var resourceType =$("div[name='selectResourceType_list'][class*='filter-div-selected']").attr("codeValue");
		var seriesId = $("div[name='selectSeries_list'][class*='filter-div-selected']").attr("codeValue");
		var deliveryId=$("#deliveryId").val();
		console.log("deliveryId %s",deliveryId);
		var params = {resourceType:resourceType, brandId:brandId, seriesId:seriesId,deliveryId:deliveryId};
// 		alert(params);
		$.ajax({
			type: "get",
			url: url,
			data: params,
			cache: false,
			dataType: "json",
			success: function(json) {
				var noSelectResource = $('#noSelectResource');
				noSelectResource.empty();
				console.log("%o",json);
					$.each(json, function(i, item) {
					var itemObj=$("<tr><td><input id='deliveryResourceChecked' type='checkbox' value="+item.id+" /></td>"
								  +'<td>'+item.resourceCode+'</td>'
								  +"<td>"+item.resourceName+"</td>"
								  +"<td>"+item.displayName+"</td>"
								  +"<td>"+item.tags+"</td></tr>");
					noSelectResource.append(itemObj);
				});
			}
		});
	}	// end
	loadBrandData();
	
}); 
</script>
</head>
<body>
	<div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span>资源关联列表</span>
    </div><!-- breadcrumbs -->
	<!--角色管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<table class="sj-tab" cellspacing="0" cellpadding="0">
				 <tr>
					<td width="20%">品牌</td>
					<td><div id="selectBrand"></div></td>
				</tr>
				
				<tr>
					<td width="20%">资源分类</td>
					<td><div id="selectResourceType"></div></td>
					
				</tr>
				<tr>
					<td width="20%">系列分类</td>
					<td><div id="selectSeries"></div></td>
				</tr>
				<%-- <tr style=" width:100%; height:35px;background:#f5f6f9;">
		           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
			           <h4 style="width:70px; float:left;"><a id="selectDelivery"><img class="addimg" src="${ctx}/static/images/addto.png"/>选择</a></h4>
			           <h4 style="width:70px; float:left;"><a id="returnDelivery"><img class="addimg" src="${ctx}/static/images/pencil.png" />返回</a></h4>
			           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			           <input type="hidden" value="${deliveryId}" id="deliveryId" name="deliveryId"/>
		           </td>
		        </tr> --%>
			</table>
	            <a id="returnDelivery" href="" class="iconlink2" style="float:right;margin-right:10px;"><span>返回</span></a>
	            <a id="selectDelivery" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>选择</span></a>
	            <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
	            <input type="hidden" value="${deliveryId}" id="deliveryId" name="deliveryId"/>
<%-- 	            <a href="${ctx}/delivery/add?parentId=${parentId}&titleName=新增分娩音乐" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>新增</span></a>      --%>
	        
	        <div id="listview" class="listview" style=" margin-left:16px;">       
	            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
	                <colgroup>
	                    <col class="head0" width="3%" />
	                    <col class="head1" width="30%" />
	                    <col class="head0" width="30%" />
	                    <col class="head1" width="30%" />
	                    <col class="head0" width="7%" />
	                </colgroup>
	                <tr>
	                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
	                    <td width="30%">编号</td>
						<td width="30%">内部名称</td>
						<td width="30%">外部名称</td>
						<td width="7%">标签</td>
	                </tr>
	            </table>
	         </div>
	         <div class="sTableWrapper" style=" margin-left:16px;">
				<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
					<colgroup>
						<col class="con0" width="3%" />
						<col class="con1" width="30%" />
						<col class="con0" width="30%" />
						<col class="con1" width="30%" />
						<col class="con0" width="7%" />
					</colgroup>
					<tbody id="noSelectResource"></tbody>
				</table>
	        </div>
	    </div>
    
    <br clear="all" />
    
	</div><!--maincontent-->
<br />
	<%-- 
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
	           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
	           	   <h3 style="width:100px; float:left;">资源关联列表</h3>
	           </td>
	        </tr>
		 <tr>
			<td width="20%">品牌</td>
			<td><div id="selectBrand"></div></td>
		</tr>
		
		<tr>
			<td width="20%">资源分类</td>
			<td><div id="selectResourceType"></div></td>
			
		</tr>
		<tr>
			<td width="20%">系列分类</td>
			<td><div id="selectSeries"></div></td>
		</tr>
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
	           <h4 style="width:70px; float:left;"><a id="selectDelivery"><img class="addimg" src="${ctx}/static/images/addto.png"/>选择</a></h4>
	           <h4 style="width:70px; float:left;"><a id="returnDelivery"><img class="addimg" src="${ctx}/static/images/pencil.png" />返回</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
	           <input type="hidden" value="${deliveryId}" id="deliveryId" name="deliveryId"/>
           </td>
        </tr>
	</table>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr>
			<td width="3%"></td>
			<td width="20">编号</td>
			<td width="20">内部名称</td>
			<td width="20">外部名称</td>
			<td width="20">标签</td>
		</tr>
		<tbody id="noSelectResource"></tbody>
	</table>
	 --%>
</body>
</html>