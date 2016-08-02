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
	$("#province").change(function(){
		var provinceId = $("#province").val();
		$.ajax({  
		    url:'${ctx}/seller/listCityByParentId',// 跳转到 action    
		    data:{    
		    	provinceId : provinceId
		    },    
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	$("#city").empty();
		    	for(var i=0; i<data.length; i++) {
		    		$("#city").append("<option value="+data[i].id+">"+data[i].name+"</option>");
		    	}
		     }
		});
	});
	
	$("#bornProvince").change(function(){
		var provinceId = $("#bornProvince").val();
		$.ajax({  
		    url:'${ctx}/seller/listCityByParentId',// 跳转到 action    
		    data:{    
		    	provinceId : provinceId
		    },    
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	$("#bornCity").empty();
		    	for(var i=0; i<data.length; i++) {
		    		$("#bornCity").append("<option value="+data[i].id+">"+data[i].name+"</option>");
		    	}
		     }
		});
	});
	
	$("#city").change(function(){
		var cityId = $("#city").val();
		$.ajax({  
		    url:'${ctx}/seller/listAreaByParentId',// 跳转到 action    
		    data:{    
		    	cityId : cityId
		    },    
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	$("#area").empty();
		    	for(var i=0; i<data.length; i++) {
		    		$("#area").append("<option value="+data[i].id+">"+data[i].name+"</option>");
		    	}
		     }
		});
	});
	
	$("#customerInfo").validate();
	$("#studentInfoSave").click(function(){
		$("#customerInfo").submit();
	});
	
	$("#studentInfoClose").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/studentInfo/close/' + parentId;
	});
	$("#studentInfoCloseButton").click(function(){
		var parentId = $('#parentId').val();
		document.location.href = '${ctx}/studentInfo/close/' + parentId;
	});
	
}); 
</script>
</head>
<body>
 <div class="xuey-dj">
       <h3>登记学员信息</h3>
       <span><img class="xy-close" id="studentInfoCloseButton" src="${ctx}/static/images/closeicon.png" /></span>
       <!--基本信息-->
       <form id="customerInfo" action="${ctx}/studentInfo/addStudentInfo" method="post">
       <div class="jbinfo">
		<h4>基本信息</h4>
           <div class="line1">  
               <label>姓名</label>
               <input  type="text" name="name" value="${studentInfoDto.name}" />
               <label>移动电话</label>
               <input  type="text" name="mobilePhone" value="${studentInfoDto.mobilePhone}" />
              <label>出生日期</label>
               <input class="date-inp" id="bornDate" value="${studentInfoDto.bornDate}" name="bornDate" onClick="return Calendar('bornDate');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('bornDate');" src="${ctx}/static/images/rilicon.png"  /></a>
           </div>
           
           <div class="line1">  
               <label>性别</label>
               <select name="sex">
	              <option value="">-----请选择-----</option>
                  <option value="1" <c:if test="${studentInfoDto.sex eq 1}">selected="selected"</c:if>>男</option>
				  <option value="2" <c:if test="${studentInfoDto.sex eq 2}">selected="selected"</c:if>>女</option>
               </select>
               <label>身份证</label>
               <input  type="text" name="cardCode" value="${studentInfoDto.cardCode}" />
               <label>邮箱</label>
               <input  type="text" name="email" value="${studentInfoDto.email}"/>
           </div>
            
            <div class="line1">  
               <label>固定电话</label>
               <input  type="text" name="fixedPhone" value="${studentInfoDto.email}" />
               <label>QQ</label>
               <input  type="text" name="qqAccount" value="${studentInfoDto.email}" />
               <label>微信</label>
               <input  type="text" name="microChartAccount" value="${studentInfoDto.email}" />
           </div>
            <div class="line1">  
               <label>入学日期</label>
               <input class="date-inp" id="entranceDate" name="entranceDate" value="${studentInfoDto.email}" onClick="return Calendar('entranceDate');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('entranceDate');" src="${ctx}/static/images/rilicon.png"  /></a>
               <label>推荐人</label>
               <select class="required" name="saler">
					<option value="">-----请选择-----</option>
					<c:set var="userInfoId" value="${studentInfoDto.saler}" />
				<c:forEach items="${userInfoList}" var="userInfo">
					<option value="${userInfo.id}" <c:if test="${userInfo.id eq userInfoId}">selected="selected"</c:if>>${userInfo.userName}</option>
				</c:forEach>
			  </select>
               <label>居住地址</label>
               <input  type="text" name="specificationAddress" value="${studentInfoDto.specificationAddress}" />
           </div>
            <div class="line1">  
               <label>居住省</label>
               <select class="activity-sel"  name="province" id="province">
					<option value=""></option>
					<c:set var="provinceId" value="${studentInfoDto.province}" />
				  <c:forEach items="${provinceList}" var="province">
					<option value="${province.id}" <c:if test="${province.id eq provinceId}">selected="selected"</c:if>>${province.name}</option>
				  </c:forEach>
			 	</select>
               <label>居住市</label>
               <select name="city" id="city">
               </select>
               <label>居住县</label>
               <select name="district" id="area">
               </select>
           </div>
            <div class="line1">  
               <label>联系人</label>
               <input  type="text" name="contacts" value="${studentInfoDto.contacts}" />
               <label>联系电话</label>
               <input  type="text" name="contactsPhone" value="" />
               <input type="hidden" name="id" value="${studentInfoDto.id}"/>
               <input type="hidden" name="customerInfoId" value="${studentInfoDto.id}"/>
               <input type="hidden" name="gestationInfoId" value="${studentInfoDto.gestationInfoId}"/>
           </div>
       </div>
		
       <!--基本信息-->
       
       <!--孕育信息-->
       <div class="jbinfo jbinfo2">
		<h4>孕育信息</h4>
           <div class="line1">  
               <label>最后月经日期</label>
               <input class="date-inp" id="lastMensesDate" name="lastMensesDate" value="${studentInfoDto.lastMensesDate}" onClick="return Calendar('lastMensesDate');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('lastMensesDate');" src="${ctx}/static/images/rilicon.png"  /></a>
               <label>预产期</label>
               <input  type="text" name="expectedDate" value="" />
               <input class="date-inp" id="expectedDate" name="expectedDate" value="${studentInfoDto.expectedDate}" onClick="return Calendar('expectedDate');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('expectedDate');" src="${ctx}/static/images/rilicon.png"  /></a>
               <label>月经周期</label>
               <input  type="text" name="mensesDays" value="" />
           </div>
           <div class="line1">  
               <label>实际生产日期</label>
               <input class="date-inp" id="rellyBornDate" name="rellyBornDate" value="${studentInfoDto.rellyBornDate}" onClick="return Calendar('rellyBornDate');"  type="text" placeholder="" readonly="readonly"/> <a><img class="dateBtn" value="" onClick="return Calendar('rellyBornDate');" src="${ctx}/static/images/rilicon.png"  /></a>
               <label>胎数</label>
               <input  type="text" name="bodySize" value="${studentInfoDto.bodySize}" />
               <label>生产方式</label>
                <select name="sex">
	              <option value="">-----请选择-----</option>
                  <option value="0">顺产</option>
				  <option value="1">刨宫产</option>
               </select>
           </div>
            
            <div class="line1">  
               <label>生产省</label>
               <select class="activity-sel"  name="bornProvince" id="bornProvince">
					<option value=""></option>
					<c:set var="provinceId" value="${studentInfoDto.bornProvince}" />
				  <c:forEach items="${provinceList}" var="province">
					<option value="${province.id}" <c:if test="${province.id eq provinceId}">selected="selected"</c:if>>${province.name}</option>
				  </c:forEach>
			 	</select>
               <label>生产市</label>
                <select name="bornCity" id="bornCity"></select>
               <label>生产医院</label>
               <input  type="text" name="bornHospital" value="" />
           </div>
           
       </div>
	   <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
       <!--孕育信息-->
         <div class="actionbox-btnZ jbinfo-btnZ">
           <div class="hd-keepBtn" id="studentInfoSave">保存</div>
           <div class="hd-closeBtn" id="studentInfoClose">关闭</div>
        </div>
        </form>
      </div>
      
</body>
</html>