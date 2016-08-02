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
	
	$("#updateProduct").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var productId = $("input[type='checkbox']:checked").val();
			$("#updateProduct").attr("href",'${ctx}/product/update/'+productId+'?parentId=' + parentId);
		}
    });
	
	$("#removeProduct").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var productId = $("input[type='checkbox']:checked").val();
			$("#removeProduct").attr("href",'${ctx}/product/removeProduct/'+productId+'?parentId=' + parentId);
		}
    });
	
	$("#updateIsDeleteFlag").click(function() {
		if($("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = $('#parentId').val();
			var productId = $("input[type='checkbox']:checked").val();
			$("#updateIsDeleteFlag").attr("href",'${ctx}/product/updateIsDeleteFlag/'+productId+'?parentId=' + parentId);
		}
    });
	
}); 
</script>
</head>
<body>
	<table class="sj-tab" cellspacing="0" cellpadding="0">
		<tr style=" width:100%; height:35px;background:#f5f6f9;">
           <td colspan="9" width="90px" style="border:none;  border-left:1px solid #ced2d8;" >
           	   <h3 style="width:100px; float:left;">商品管理列表</h3>
	           <h4 style="width:70px; float:left;"><a href="${ctx}/product/add?parentId=${parentId}"><img class="addimg" src="${ctx}/static/images/addto.png"/>新增</a></h4>
	           <h4 style="width:70px; float:left;"><a id="updateProduct"><img class="addimg" src="${ctx}/static/images/pencil.png" />编辑</a></h4>
	           <h4 style="width:100px; float:left;"><a id="removeProduct"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />商品下线</a></h4>
	           <h4 style="width:100px; float:left;"><a id="updateIsDeleteFlag"><img class="addimg" src="${ctx}/static/images/edit_remove.png" />商品上线</a></h4>
	           <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
           </td>
        </tr>
		<tr>
			<td width="5%"><!-- <input name="" type="checkbox" value="" /> --></td>
			<td width="20%">商品</td>
			<td width="30%">描述</td>
			<td width="25%">原价</td>
			<td width="15%">现价</td>
			<td width="5%">状态</td>
		</tr>
	  <c:forEach items="${productlist}" var="product">
	  	<tr>
			<td><input id="productChecked" name="" type="checkbox" value="${product.productId}" /></td>
			<td>${product.productName}</td>
			<td>${product.productDesc}</td>
			<td>${product.price}</td>
			<td>${product.priceOld}</td>
			<td>
				 <c:if test="${product.isdeleteFlag eq 0}">上线</c:if>
	             <c:if test="${product.isdeleteFlag eq 1}">下线</c:if>
	             <c:if test="${product.isdeleteFlag eq 2}">通过</c:if>
			</td>
		</tr>
	  </c:forEach>
	</table>
	<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
</body>
</html>