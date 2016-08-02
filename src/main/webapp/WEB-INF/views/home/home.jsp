<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../home/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <script type="text/javascript" src="${ctx}/demo/js/plugins/jquery-1.7.min.js"></script> --%>
<style type="text/css">
body{ background:white;}
</style>
</head>
<body>
   <div class="modular  mainav">
        <div class="mo1 clearfix">
            <div class="mo1-1">
              <h3><span>${seller.name}的信息</span></h3>
                <a href="home-Franchisee.html" class="writeimg"><img class="writeicon" src="${ctx}/demo/images/writeicon.png" /></a>
                <table class="franTab" >
					<tr>
						<td class="fontR">名称</td>
						<td class="fontM">${seller.name}</td>
					</tr>
					<tr>
						<td class="fontR">省市区	</td>
						<td class="fontM">${seller.areaName}${seller.areaName1}${seller.areaName2}</td>
					</tr>
					<tr>
						<td class="fontR">地址</td>
						<td class="fontM">${seller.officeAddr}</td>
					</tr>
					<tr>
						<td class="fontR">类型</td>
						<td class="fontM">
							<c:if test="${seller.sellersign eq 0}">商家</c:if>
							<c:if test="${seller.sellersign eq 1}">加盟商</c:if>
							<c:if test="${seller.sellersign eq 9}">总部</c:if>
						</td>
					</tr>
					<tr>
						<td class="fontR">营业执照</td>
						<td class="fontM">${seller.charterCode}</td>
					</tr>
					<tr>
						<td class="fontR">电话</td>
						<td class="fontM">${seller.tel}</td>
					</tr>
				</table>
            </div>

            <div class="mo1-2">
                <div class="input-date">
                    <div class="title">
						<h3><span>二维码报表信息</span></h3>
                      	<a href="${ctx}/sallerReport/list/104"><span class="more">更多</span></a>
                    </div>

                    <table cellpadding="0" cellspacing="0" class="sTable3" width="100%" style=" text-align:center">
                    	<c:choose>
							<c:when test="${salerUserinfoList ne '[]'}">
								<thead>
	                              <tr>
	                                <td>拥有人</td>
	                                <td>二维码标号</td>
	                                <td>分配日期</td>
	                                <td>注册用户数</td>
	                              </tr>
	                             </thead>
                        		<tbody>
	                             <c:forEach items="${salerUserinfoList}" var="salerUserinfo">
	                              <tr>
	                                <td>${salerUserinfo.userName}</td>
	                                <td>${salerUserinfo.cardCode}</td>
	                                <td>${salerUserinfo.createDateTimeString}</td>
	                                <td>${salerUserinfo.clientCount}</td>
	                              </tr>
	                             </c:forEach>
	                             </tbody>
                             </c:when>
                             <c:otherwise>
                             	<div align="center">还没有二维码报表信息</div>
                             </c:otherwise>
                            </c:choose>
                    </table>
                </div>

            </div>
            <div style="height:436px;"></div>
<!--             <div class="widgetbox"> -->
<!--             	<h3 style="height:45px;line-height:45px;padding-left:24px;border-top-left-radius: 10px; border-top-right-radius: 10px;"><span>行动管理</span></h3> -->
<!--                 <div class="content nopadding ohidden"> -->
				<div class="mo1-3">
            	<h3><span>行动管理&nbsp;<a class="more"  style="position:absolute;right:10%;top:440px; font-size:12px;color:#fff; cursor:pointer;" href="${ctx}/actionManagerment/list/105">更多</a></span></h3>
                <div class="content nopadding ohidden">
                	<table cellpadding="0" cellspacing="0" class="sTable3" width="100%" style=" text-align:center">
                		<c:choose>
							<c:when test="${actionManagermentList ne '[]'}">
	                          <thead>
	                              <tr>
	                                <td style="width:9%">行动主题</td>
	                                <td style="width:9%">执行人</td>
	                                <td style="width:9%">联系人</td>
	                                <td style="width:9%">客户名称</td>
	                                <td style="width:9%">行动方式</td>
	                                <td style="width:9%">状态</td>
	                                <td style="width:9%">开始日期</td>
	                                <td style="width:9%">结束日期</td>
	                                <td style="width:9%">地点</td>
	                                <td style="width:9%">完成结果</td>
	                                <td style="width:9%">是否关闭</td>
	                              </tr>
	                            </thead>
	                            <tbody>
	                            <c:forEach items="${actionManagermentList}" var="actionManagerment">
	                              <tr>
	                                 <td>${actionManagerment.actionTitle}</td>
	                                 <td>${actionManagerment.executorName}</td>
	                                 <td>${actionManagerment.contacts}</td>
	                                 <td>${actionManagerment.clientName}</td>
	                                 <td>
		                                 <c:if test="${actionManagerment.actionType eq 1}">上门拜访</c:if>
		                                 <c:if test="${actionManagerment.actionType eq 2}">电话</c:if>
		                                 <c:if test="${actionManagerment.actionType eq 3}">微信</c:if>
		                                 <c:if test="${actionManagerment.actionType eq 4}">邮件</c:if>
	                                 </td>
	                                 <td>
		                                 <c:if test="${actionManagerment.state eq 1}">未执行</c:if>
		                                 <c:if test="${actionManagerment.state eq 2}">执行中</c:if>
		                                 <c:if test="${actionManagerment.state eq 3}">已完成</c:if>
	                                 </td>
	                                 <td>${actionManagerment.beginDateTime}</td>
	                                 <td>${actionManagerment.endDateTime}</td>
	                                 <td>${actionManagerment.location}</td>
	                                 <td>${actionManagerment.accomplishResults}</td>
	                                 <td>
	                                 	<c:if test="${actionManagerment.isClose eq 0}">否</c:if>
	                                 	<c:if test="${actionManagerment.isClose eq 1}">是</c:if>
	                                 </td>
	                              </tr>
	                            </c:forEach>
	                            </tbody>
						</c:when>
                        <c:otherwise>
                        	<div align="center" style="height:240px;">还没有行动管理信息</div>
                        </c:otherwise>
                       </c:choose>
                    </table>
                </div><!-- content -->
            </div><!-- widgetbox2 -->           
        </div> 
                
     <!--modular1-->   
    </div>
    
</body>
</html>