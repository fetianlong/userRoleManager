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
	$(document).ready(function() {
		$("#product").validate();
		$("#productSave").click(function() {
			$("#product").submit();
		});
		
		$("#productClose").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/product/close/' + parentId;
		});
		$("#productCloseButton").click(function() {
			var parentId = $('#parentId').val();
			document.location.href = '${ctx}/product/close/' + parentId;
		});

	});
</script>
</head>
<body>
	 <div class="spfb">
        <h3>商品新建发布</h3>
        <span><img class="spfb-close" id="productCloseButton" src="images/closeicon.png" /></span>
        <form id="product" action="${ctx}/product/addProduct" method="post" enctype="multipart/form-data">
        <table class="hd-tab" width="100%" cellspacing="0" cellpadding="0">
              <tr>
                <td class="font">商品名称</td>
                <td><input class="name-inp"  type="text" name="productName" value="${product.productName}" /><span>*</span></td>
              </tr>
              <tr>
                <td class="font">商品原价</td>
                <td><input class="name-inp"  type="text" name="priceOld" value="${product.priceOld}" /><span>*</span></td>
              </tr>
              <tr>
                <td class="font">商品现价</td>
                <td><input class="addr-inp"  type="text" name="price" value="${product.price}" /><span>*</span></td>
              </tr>
              
              <tr>
                <td class="font">商品图片</td>
                <td><input type="file" name="file" value="${product.image}"/></td>
              </tr>
            
              <tr>
                <td class="font">商品描述</td>
                <td><textarea class="area-inp" style=" margin-top:10px;" name="productDesc" cols="" rows="">${product.productDesc}</textarea></td>
              </tr>
        </table>
        <input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
        <div class="sp-btnZ">
            <div class="hd-keepBtn" id="productSave">保持</div>
            <div class="hd-closeBtn" id="productClose">关闭</div>
         </div>  
    </form>
    </div>

</body>
</html>