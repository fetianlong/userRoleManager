<%@ page contentType="text/html;charset=UTF-8"%>
<%-- <%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>   --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" media="screen" href="${ctx}/demo/css/style.css" />
<%--  --%>
<script type="text/javascript" src="${ctx}/demo/js/plugins/jquery-1.7.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/demo/js/plugins/jquery-ui-1.8.16.custom.min.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/demo/js/plugins/jquery.validate.min.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/demo/js/plugins/jquery.colorbox-min.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/demo/js/plugins/jquery.dataTables.min.js"></script> --%>
<%-- <script src="${ctx}/static/jquery/js.js" type="text/javascript"></script> --%>
<script type="text/javascript" src="${ctx}/demo/js/custom/general.js"></script>

<%--  
<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/showdate.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/js.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/general.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/datetime.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/jquery/general.js"></script>
--%>

<sitemesh:head/>
</head>

<body>
	<div class="container">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div id="content">
<!-- 			<frame name="myIframe" src="" frameborder="0" noresize="noresize"></frame> -->
<!-- 			<iframe name="a_iframe" id="'a_iframe'"  src="" marginwidth="0" marginheight="0" scrolling="no"  frameborder="0" WIDTH="100%" height="100%"></iframe> -->
			<sitemesh:body/>
		</div>
<!-- 		<iframe name="myIframe" src=""></iframe> -->
<%-- 		<%@ include file="/WEB-INF/layouts/footer.jsp"%> --%>
	</div>
</body>
</html>