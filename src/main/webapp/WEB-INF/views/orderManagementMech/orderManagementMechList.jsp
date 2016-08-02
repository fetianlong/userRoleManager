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
<script type="text/JavaScript">
jQuery(document).ready(function () {
	//查询
	jQuery("#userInfoSubmit").click(function(){
		jQuery('#userInfo').submit();
	});
	
	//查询重置
	$("#reset").click(function(){
		$("#orderId").val("");
		$("#pid").val("");
		$("#orderState").val("");
		$("#orderType").val("");
	});
	
// 	jQuery("input[type='checkbox']").click(function() {
//         if ($(this).prop("checked") == true) {
//             jQuery("input[type='checkbox']").attr("checked", false);
//             $(this).prop("checked", true);
//         }
//     });
	
	 
}); 
</script>
</head>
<body>
    <div class="breadcrumbs">
    	<a href="#">${titleName}</a>
        <span>订单查看</span>
    </div><!-- breadcrumbs -->
	<!--人员管理-->
    <div class="yaop-info mainav activity-gl ">
        <div class="menu2-info act-z"> 
			<form class="form_default" id="userInfo" name="userInfoForm" action="${ctx}/orderManagementMech/list/${parentId}"  style="margin-top:23px; margin-left:16px;">
				<label class="sj-name">订单号</label> <input class="sf" type="text" id="orderId" name="orderId" value="${orderId}" /> 
				<c:if test="${mypid == '3'}">
					<input type="hidden" name="mypid" value="${mypid }" />
					<label>加盟商</label>
					<c:set var="sellerPid" value="${pid}"></c:set>
					<select name="pid" id="pid" >
						<option value="">-----请选择-----</option>
					  <c:forEach items="${sellerList}" var="seller">
						<option value="${seller.id}" <c:if test="${seller.id eq sellerPid}">selected="selected"</c:if>>${seller.name}</option>
					  </c:forEach>
					</select>
				</c:if>
				<label class="sj-name">购买类型</label>
				<select id="orderType" name="orderType">
					<option value="">-----请选择-----</option>
					<option value="0" <c:if test="${orderType eq 0}">selected="selected"</c:if>>在线购买</option>
					<option value="1" <c:if test="${orderType eq 1}">selected="selected"</c:if>>悦迪代买</option>
				</select>
				<label class="sj-name">订单状态</label>
				<select id="orderState" name="orderState">
					<option value="">-----请选择-----</option>
					<option value="0" <c:if test="${orderState eq 0}">selected="selected"</c:if>>待支付</option>
					<option value="1" <c:if test="${orderState eq 1}">selected="selected"</c:if>>已支付</option>
				</select>
				<div class="sTableOptions" style="background:none; border:none; float:right; margin-right:25%; margin-top:-9px;">
					<a id="userInfoSubmit" class="button" style=" margin-right:10px;"><span>查询</span></a>
					<a id="reset" class="button"><span>重置</span></a>
				</div>
				<input type="hidden" value="${parentId}" id="parentId" name="parentId"/>
			</form> 
        <!--<div class="sTableOptions" style="margin-left:16px; margin-top:20px;">
            <a id="resetPassword" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/tag.png" class="mgright5" alt=""> <span>重置密码</span></a>
            <a id="recoverUserInfo" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/check.png" class="mgright5" alt=""> <span>恢复</span></a>
            <a id="freezeUserInfo" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/close.png" class="mgright5" alt=""> <span>冻结</span></a>
            <a id="updateUserInfo" href="" class="iconlink2" style="float:right;margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/edit.png" class="mgright5" alt=""> <span>修改</span></a>
            <a href="${ctx}/userInfo/add?parentId=${parentId}&titleName=新增用户" class="iconlink2" style="float:right; margin-right:10px;"><img src="${ctx}/demo/images/icons/small/black/plus.png" class="mgright5" alt=""> <span>新增</span></a>     
        </div>sTableOptions-->
        
        <div id="listview" class="sTableWrapper" style=" margin-left:16px;">       
            <table cellpadding="0" cellspacing="0" class="sTableHead" width="100%" style="text-align:center;">
                <colgroup>
                    <col class="head0" width="3%" />
                    <col class="head1" width="20%" />
                    <col class="head0" width="6%" />
                    <col class="head1" width="6%" />
                    <col class="head0" width="6%" />
                    <col class="head1" width="6%" />
                    <col class="head0" width="10%" />
                    <col class="head1" width="6%" />
                    <col class="head0" width="6%" />
                    <col class="head1" width="15%" />
                </colgroup>
                <tr>
                    <td width="3%" align="center"><input type="checkbox" class="checkall" /></td>
                    <td width="20%">订单号</td>
					<td width="6%">商品名称</td>
					<td width="6%">单价</td>
					<td width="6%">数量</td>
					<td width="6%">付款金额</td>
					<td width="10%">购买时间</td>
					<td width="6%">状态</td>
					<td width="6%">购买方式</td>
					<td width="15%">购买机构</td>
                </tr>
            </table>
         </div>
         <div class="sTableWrapper" style=" margin-left:16px;">
			<table cellpadding="0" cellspacing="0" class="sTable" width="100%" style=" text-align:center;">
				<colgroup>
					<col class="con0" width="3%" />
					<col class="con1" width="20%" />
					<col class="con0" width="6%" />
					<col class="con1" width="6%" />
					<col class="con0" width="6%" />
					<col class="con1" width="6%" />
					<col class="con0" width="10%" />
					<col class="con1" width="6%" />
					<col class="con0" width="6%" />
					<col class="con1" width="15%" />
				</colgroup>
				<c:forEach items="${orderManagementMechList}" var="orderM">
			  	<tr>
					<td><input id="userInfoChecked" name="" type="checkbox" value="${orderM.id}" /></td>
					<td>${orderM.orderId}</td>
					<td>${orderM.comName}</td>
					<td>${orderM.uPrice}</td>
					<td>${orderM.buyNum}</td>
					<td>${orderM.buyMoney}</td>
					<td><fmt:formatDate value="${orderM.buyTime}"/></td>
					<td>
					 <c:if test="${orderM.state eq 0}">待支付</c:if>
		             <c:if test="${orderM.state eq 1}">已支付</c:if>
					</td>
					<td>
						<c:if test="${orderM.pWay eq 0}">在线购买</c:if>
						<c:if test="${orderM.pWay eq 1}">悦迪代买</c:if>
					</td>
					<td>
						${orderM.sellerName}
					</td>
				</tr>
			  </c:forEach>
				
			</table>
        </div>
        <tags:pagination page="${pageData}" paginationSize="10"/>
    </div>
    
    
    <!--商家管理-->
    <br clear="all" />
    
</div><!--maincontent-->
<br />
	
</body>
</html>