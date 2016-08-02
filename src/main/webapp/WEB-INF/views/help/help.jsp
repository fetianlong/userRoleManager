<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dic" uri="/WEB-INF/dictionary.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>教学系统帮助</title>
</head>
<body>
	<table>
		<tr>
			<td><a href="${ctx}/resources/soft/mac.bat" target="_blank">获取mac地址下载</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/resources/help/playMusiConfig.docx" target="_blank">课程播放设置说明</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/resources/help/yuediweb_OI.ppt" target="_blank">悦迪云平台操作手册</a></td>
		</tr>
		<%--
		<tr>
			<td><a href="${ctx}/resources/soft/ChromeStandaloneSetup.exe" target="_blank">谷歌浏览器下载</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/resources/soft/Firefox_V38.0.1.5611.exe" target="_blank">火狐浏览器下载</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/resources/soft/vlc-2.1.5-win32.exe" target="_blank">音乐播放软件下载</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/resources/help/yuedi-music-setting.docx" target="_blank">音乐播放设置帮助文档下载</a></td>
		</tr>  
		--%>
	</table>
</body>
</html>