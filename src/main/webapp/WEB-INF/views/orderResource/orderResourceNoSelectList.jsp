<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
	
	$("#selectOrderContent").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var url = "${ctx}/orderResource/select/";
			var orderId = $('#orderContentId').val();
			var resourceId = $("input[type='checkbox']:checked").val();
			var params={resourceId:resourceId,orderId:orderId};
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
	$("#deleteOrderResource").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var orderContentId = $('#orderContentId').val();
			var orderResourceId = $("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除该关联的资源么?');
			if(boolconfirm) {
				$("#deleteOrderResource").attr("href",'${ctx}/orderResource/delete/'+orderResourceId+'?parentId=' + parentId+'&orderContentId='+orderContentId);
			}
		}
    });
	
	$("#returnOrderContent").click(function() {
			var parentId = $('#parentId').val();
			var orderContentId = $("#orderContentId").val();
				$("#returnOrderContent").attr("href",'${ctx}/orderResource/listSelectOrderResource/'+parentId+'?orderContentId=' + orderContentId);
    });
	// 加载品牌资源
	function loadBrandData() {
			var selectBrand = $('#selectBrand');
			selectBrand.empty();
			var json=[{"id":1,"name":"孕期"},{"id":2,"name":"亲子课"},{"id":3,"name":"家庭课"},{"id":4,"name":"app-胎教"}];
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
		$.getJSON("${ctx}/orderResource/selectSeriesByBrandResourceType",{brandId:brandId,resourceType:resourceType},function(json){
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
		var url = "${ctx}/orderResource/listNoSelectOrderResource/";
		var brandId =$("div[name='selectBrand_list'][class*='filter-div-selected']").attr("codeValue");
		var resourceType =$("div[name='selectResourceType_list'][class*='filter-div-selected']").attr("codeValue");
		var seriesId = $("div[name='selectSeries_list'][class*='filter-div-selected']").attr("codeValue");
		var orderId=$("#orderContentId").val();
		console.log("orderContentId %s",orderContentId);
		var params = {resourceType:resourceType, brandId:brandId, seriesId:seriesId,orderId:orderId};
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
					var itemObj=$("<tr><td><input id='orderResourceChecked' type='checkbox' value="+item.id+" /></td>"
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
	           <h4 style="width:70px; float:left;"><a id="selectOrderContent"><img class="addimg" src="${ctx}/static/images/addto.png"/>选择</a></h4>
	           <h4 style="width:70px; float:left;"><a id="returnOrderContent"><img class="addimg" src="${ctx}/static/images/pencil.png" />返回</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
	           <input type="hidden" value="${orderContentId}" id="orderContentId" name="orderContentId"/>
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
	
	
</body>
</html>