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
	$("#deposit").validate({
		rules: {
			addCount: {
				required: true,
				min: 1
			}
		},
		messages: {
			addCount: {
				required: "数量必填且大于0",
				min: "请输入大于0的数字"
            }
		}
	});
	$("#invitationCountIdSave").click(function(e){
		e.preventDefault();
// 	    var postData = $.toJSON(form2js('deposit', '.', true));
		var p = {invitationAllPrice:$("#invitationAllPrice").val()};
		
	    $.ajax({
	      url : "${ctx}/alipayDirect/web/deposit?invitationAllPrice="+$("#invitationAllPrice").val()+"&parentId=${parentId}&addCount="+$("#addCount").val()+"&up=${invitationPrice}",
	      type : "POST",
// 	      data : p,
	      contentType : "application/json",
	      success : function(data) {
	        $("#deposit").append(data.sHtmlText);//后台会返回一个新的交易表单，并自动提交。所以随便放一个地方就OK
	      },
	      error : function() {
	      }
	    });
// 		$("#deposit").submit();
	});
	
// 	$("#invitationCountClose").click(function(){
// 		var parentId = $('#parentId').val();
// 		document.location.href = '${ctx}/invitation/list/' + parentId;
// 	});
}); 
	function getAllPrice(value){
		var allPrice = '${invitationPrice}' * value;
		$('#invitationAllPrice').val(allPrice);
		$("#allPrice").html(allPrice + "&nbsp;&nbsp;&nbsp;&nbsp;元");
	}
</script>
</head>
<body>
	<div class="breadcrumbs">
    	<a href="#">交易管理</a>
        <span>${param.titleName}</span>
    </div><!-- breadcrumbs -->
    <!--添加页面-->
    <div class="form_default" style="margin-left:16px; margin-top:20px;width: ">
    <form id="deposit" action="" method="post"  target="_blank">
        <fieldset>
            <legend>${param.titleName}</legend>
            <p>
            	<label for="email" style="float:left;">单价：</label>
            	<label for="email" >${invitationPrice}&nbsp;&nbsp;&nbsp;&nbsp;元</label>
<%--             	<label class="sf">${invitationPrice}元</label> --%>
            </p>
            <p>
                <label for="email" style="float:left;">购买数量</label>
                <input type="text" id="addCount" name="addCount" value="${invitationCount.addCount}" onblur="getAllPrice(this.value);" class="sf required" />
            </p>
            <p>
                <label for="email" style="float:left;">总价</label>
                <label id="allPrice" class="sf"></label>
                <input type="hidden" id="invitationAllPrice" name="invitationAllPrice" value="${invitationAllPrice}" />
            </p>
            
             <%-- <p>
                <label for="email" style="float:left;">备注</label>
                <textarea rows="" cols="" class="mf" name="remark">${invitationCount.remark}</textarea>
            </p> --%>
             <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
             <input type="hidden" value="${invitationCount.id}" name="id"/>
            
            <p>
                <button id="invitationCountIdSave">购买</button>
                <input id="invitationCountClose" type="button" onclick="history.go(-1);" 
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