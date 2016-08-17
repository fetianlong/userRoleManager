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
jQuery(document).ready(function () {
	jQuery("#userInfo").validate({
		rules: {
			userName: {
				required: true,
				minlength: 3,
				maxlength: 10,
				remote : {
					url : "${ctx}/userInfo/checkExistByUserName",
					type : "get",
					cache:false,
					dataType : "json",
					data : {
						loginName: function() {
							return jQuery("#userName").val();
						}
					}
				}
			},
			pwd: {
				required: true,
				minlength: 6
			},
			plainPassword: {
				required: true,
				minlength: 6,
				equalTo: "#pwd"
			}
		},
		messages: {
			userName: {
				required: "用户名不能为空",
				minlength: "用户名长度不能小于3个字符",
				maxlength: "用户名长度不能大于10个字符",
				remote: "该用户名已经存在"
            },
			pwd: {
                required: "没有填写密码",
                minlength: "密码不能小于{0}个字符"
            },
            plainPassword: {
                required: "没有确认密码",
                minlength: "确认密码不能小于{0}个字符",
                equalTo: "两次输入密码不一致"
            }
		}
	});
// 	jQuery("#userInfo").validate();
	jQuery("#userSave").click(function(){
		var password = jQuery("#password").val();
		var repwd = jQuery("#repwd").val();
		if(password != repwd) {
// 			alert("重置密码必须和密码保持一致！");
		}else {
			jQuery("#userInfo").submit();
		}
	});
	/* jQuery("#userClose").click(function(){
		alert();
		var parentId = jQuery('#parentId').val();
		document.location.href = '${ctx}/userInfo/close/' + parentId;
	}); */
}); 
</script>
</head>
<body>
	<div class="breadcrumbs">
    	<a href="${ctx}/login/index">首页</a>
        <span>${param.titleName}</span>
    </div><!-- breadcrumbs -->
    <!--添加页面-->
    <div class="form_default" style="margin-left:16px; margin-top:20px;width: ">
    <form id="userInfo" action="${ctx}/userInfo/${action}" method="post">
        <fieldset>
            <legend>${param.titleName}</legend>
            
            <c:if test="${param.titleName != '修改用户'}">
            <p>
                <label for="userName" style="float:left;">用户名</label>
                <input type="text" id="userName" name="userName" value="${user.userName}" class="sf" />
            </p>
            <p>
                <label for="pwd" style="float:left;">密码</label>
                <input type="text" id="pwd" name="pwd" value="${user.pwd}" id="password" class="sf" />
            </p>
            <p>
                <label for="plainPassword" style="float:left;">重复密码</label>
                <input type="text" id="plainPassword" name="plainPassword" class="sf" />
            </p>
            </c:if>
            <c:if test="${param.titleName eq '修改用户'}">
            <p>
                <label for="name" style="float:left;">用户名</label>
                <input type="text" name="userName" value="${user.userName}" class="sf" readonly="readonly"/>
            </p>
            </c:if>
             <p>
                <label for="email" style="float:left;">MAC地址</label>
                <input type="text" name="maces" value="${user.maces}" class="sf" />
            </p>
             <p>
                <label for="email" style="float:left;">邮箱</label>
                <input type="text" name="email" value="${user.email}"  class="sf" />
            </p>
            <p>
                <label for="email" style="float:left;">角色</label>
                <select name="roleId" class="sf required">
                 	<option value="">-----请选择-----</option>
                 	<c:forEach items="${roleList}" var="role">
                 		<option value="${role.id}">${role.name}</option>
<%-- 						<option value="${role.id}" <c:if test="${role.id eq franchiseesId}">selected="selected"</c:if>>${role.name}</option> --%>
					</c:forEach>
                 </select>
            </p>
             <p>
                <label for="email" style="float:left;">状态</label>
                <select name="state" class="sf required">
                 	<option value="">-----请选择-----</option>
                 	<option value="1" <c:if test="${user.state eq 1}">selected="selected"</c:if>>正常</option>
					<option value="2" <c:if test="${user.state eq 2}">selected="selected"</c:if>>冻结</option>
                 </select>
            </p>
             <p>
                <label for="email" style="float:left;">说明</label>
                <textarea name="remark" class="mf" cols="" rows="">${user.remark}</textarea>
            </p>
          	<input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
            <input type="hidden" value="${user.id}" name="id"/>
            
            <p>
                <button id="userSave">提交</button>
<!--                 <button id="userClose" style="margin-left:10px;">返回</button> -->
                <input id="userClose" type="button" onclick="history.go(-1);" 
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