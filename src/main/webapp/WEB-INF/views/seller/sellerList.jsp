<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="provinceId" value="${province}" />
<c:set var="cityId" value="${city}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%-- <script type="text/javascript" src="${ctx}/demo/js/plugins/jquery-1.7.min.js"></script> --%>
<%-- <script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script> --%>
<title>悦迪管理平台</title>
<script type="text/JavaScript">
jQuery(document).ready(function(){
	jQuery("#province").change(function(){
		var provinceId = jQuery("#province").val();
		if(null != provinceId && provinceId != ''){
			jQuery.ajax({  
			    url:'${ctx}/seller/listCityByParentId',// 跳转到 action    
			    data:{    
			    	provinceId : provinceId
			    },    
			    type:'get',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	jQuery("#city").empty();
			    	for(var i=0; i<data.length; i++) {
			    		jQuery("#city").append("<option value="+data[i].id+">"+data[i].name+"</option>");
			    	}
			     }
			});
		}else{
			jQuery("#city").empty();
		}
	});
	
	/* jQuery("input[type='checkbox']").click(function() {
        if ($(this).prop("checked") == true) {
            jQuery("input[type='checkbox']").attr("checked", false);
            $(this).prop("checked", true);
        }
    }); */
	
	jQuery("#updateSeller").click(function() {
		if(jQuery("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = jQuery('#parentId').val();
			var sellersign = jQuery('#sellersign').val();
			var selledcnt = jQuery('#selledcnt').val();
			var sellerId = jQuery("input[type='checkbox']:checked").val();
			jQuery("#updateSeller").attr("href",'${ctx}/seller/update/'+sellerId+'?parentId=' + parentId+'&sellersign='+sellersign+'&selledcnt='+selledcnt);
		}
    });
	
	/* jQuery("#examineMessage").click(function() {
		if(jQuery("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = jQuery('#parentId').val();
			var sellersign = jQuery('#sellersign').val();
			var selledcnt = jQuery('#selledcnt').val();
			var sellerId = jQuery("input[type='checkbox']:checked").val();
			jQuery("#examineMessage").attr("href",'${ctx}/seller/examineMessage/'+sellerId+'?parentId=' + parentId+'&sellersign='+sellersign+'&selledcnt='+selledcnt);
		}
    }); 
	
    jQuery("#deleteContactPerson").click(function() {
		if(jQuery("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = jQuery('#parentId').val();
			var contactPersonId = jQuery("input[type='checkbox']:checked").val();
			var boolconfirm=confirm('确定删除角色吗?');
			if(boolconfirm) {
				jQuery("#deleteContactPerson").attr("href",'${ctx}/contactPerson/delete/'+contactPersonId+'?parentId=' + parentId);
			}
		}
    });
    */
    jQuery("#reset").click(function(){
		jQuery("#name").val("");
		jQuery("#province").val("");
		jQuery("#city").val("");
		jQuery("#category").val("");
	});
	
    jQuery("#sellerSubmit").click(function(){
    	var sname = jQuery("#name").val();
    	var sprovince = jQuery("#province").val();
    	var scity = jQuery("#city").val();
    	var scategory = jQuery("#category").val();
    	if(sname == '' && sprovince == '' && scity ==null && scategory == ''){
    		
    	}else{
    		jQuery('#seller').submit();
    	}
	});
	
	/*
	var provinceId = jQuery("#province").val();
	jQuery.ajax({ 
	    url:'${ctx}/seller/listCityByParentId',// 跳转到 action    
	    data:{    
	    	provinceId : provinceId
	    },    
	    type:'get',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	for(var i=0; i<data.length; i++) {
	    		if(data[i].id == '${cityId}') {
	    			jQuery("#city").append("<option ></option>");
	    			jQuery("#city").append("<option selected='selected' value="+data[i].id+">"+data[i].name+"</option>");
	    		}
	    	}
	     }
	});
	*/
	jQuery("#allianceBusinessFormatal").click(function() {
		if(jQuery("input[type='checkbox']:checked").val() == undefined){
			alert("请选择数据！");
		}else {
			var parentId = jQuery('#parentId').val();
			var sellersign = jQuery('#sellersign').val();
			var selledcnt = jQuery('#selledcnt').val();
			var sellerId = jQuery("input[type='checkbox']:checked").val();
			jQuery("#allianceBusinessFormatal").attr("href",'${ctx}/seller/convertFormatalSeller/'+sellerId+'?parentId=' + parentId+'&sellersign='+sellersign+'&selledcnt='+selledcnt);
		}
    });
});
</script>
</head>
<body>
	<div class="maincontent">
    <div class="breadcrumbs">
    	<a href="${ctx}/login/index">首页</a>
        <span>${titleName}</span>
    </div><!-- breadcrumbs -->
	<!--商家管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="seller" name="sellerForm" action=""  style="margin-top:23px; margin-left:16px;">
				<label class="sj-name">按商家名查询</label> <input class="sf" type="text" id="name" name="name" value="${name}" /> 
				<label for="occupation">按省查询</label>
				<select name="province" id="province" >
<!-- 				<select name="province" id="province" onchange="changeProvince();"> -->
					<option value=""></option>
				  <c:forEach items="${areasList}" var="areas">
					<option value="${areas.id}" <c:if test="${areas.id eq provinceId}">selected="selected"</c:if>>${areas.name}</option>
				  </c:forEach>
				</select>
				<label class="occupation">按市查询</label> 
				<select name="city" id="city" >
<!-- 				<select name="city" id="city" onchange="changeCity();"> -->
				</select> 
				<label class="occupation">按商家类型查询</label>
				<select id="category" name="category"  >
					<option value='' selected></option>
					<option value='1'>母婴商超</option>
					<option value='2'>医疗健康</option>
					<option value='3'>母婴服务</option>
					<option value='4'>游泳游乐</option>
					<option value='5'>孕期及儿童摄影</option>
					<option value='6'>教育培训</option>
				</select>
				<%-- <dic:select parentCode="category"/> --%>
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="sellerSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${sellersign}" id="sellersign" name="sellersign"/>
				<input type="hidden" value="${selledcnt}" id="selledcnt" name="selledcnt"/>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			</form> 
        <div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
            <a class="button delete"><span>删除</span></a>
            <a id="" href="" class="iconlink2" style="float:right;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>修改</span></a>
            <a href="${ctx}/seller/add?parentId=${parentId}&sellersign=${sellersign}&selledcnt=${selledcnt}&titleName=${titleName}" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>添加</span></a>     
        </div><!--sTableOptions-->
        
        <div id="listview" class="listview" style=" margin-left:16px;">       
            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
                <colgroup>
                    <col class="head0" width="3%" />
                    <col class="head1" width="20%" />
                    <col class="head0" width="10%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="10%" />
                    <col class="head1" width="10%" />
                    <col class="head0" width="10%" />
                    <col class="head1" width="25%" />
                </colgroup>
                <tr>
                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
                    <td width="20%">商家名</td>
					<td width="10%">商家电话</td>
					<td width="10%">商家类型</td>
					<td width="10%">所在省</td>
					<td width="10%">所在市</td>
					<td width="10%">所在区县</td>
					<td width="25%">描述</td>
                </tr>
            </table>
         </div>
         <div class="sTableWrapper" style=" margin-left:16px;">
			<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
				<colgroup>
					<col class="con0" width="3%" />
					<col class="con1" width="20%" />
					<col class="con0" width="10%" />
					<col class="con1" width="10%" />
					<col class="con0" width="10%" />
					<col class="con1" width="10%" />
					<col class="con0" width="10%" />
					<col class="con1" width="25%" />
				</colgroup>
				<c:forEach items="${sellerList}" var="seller">
				<tr>
					<td align="center" style="vertical-align:middle;"><input name="" type="checkbox" value="${seller.id}" /></td>
					<td>${seller.name}</td>
					<td>${seller.tel}</td>
					<td>
					<c:if test="${seller.category eq 1}">母婴商超</c:if>
					<c:if test="${seller.category eq 2}">医疗健康</c:if>
					<c:if test="${seller.category eq 3}">母婴服务</c:if>
					<c:if test="${seller.category eq 4}">游泳游乐</c:if>
					<c:if test="${seller.category eq 5}">孕期及儿童摄影</c:if>
					<c:if test="${seller.category eq 6}">教育培训</c:if>
					</td>
					<td>${seller.areaName}</td>
					<td>${seller.areaName1}</td>
					<td>${seller.areaName2}</td>
					<td>${seller.remark}</td>
				</tr>
				</c:forEach>
				
			</table>
        </div>
        <tags:pagination page="${pageData}" paginationSize="10"/>
    </div>
    
    
    <!--商家管理-->
    <br clear="all" />
    
</div><!--maincontent-->
</div>
<br />
	
</body>
</html>