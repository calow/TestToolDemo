<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>测试工具</title>
		<meta name="viewport" content="initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<%
			response.setContentType("text/html;charset=utf-8");
		%>
		<script type='text/javascript' src='${resourceUrl}/js/main.js'
				charset='utf-8'></script>
		<script type='text/javascript' src='${resourceUrl}/js/jquery-1.12.2.min.js'
				charset='utf-8'></script>
		<script type="text/javascript">
			var userAccount = '${userAccount}';
			var forwardUrl = '${forwardUrl}';
			var resourceUrl = '${resourceUrl}';
			var requestUrl = '${requestUrl}';
		</script>
	</head>

	<body>
		<br>
		<div>
			<button onclick="toolAction()">
				测试调用工具方法(返回ToolTest工具表的数据...)
			</button>
		</div>
		<br>
		<div>
			<button onclick="runTool4ward()">
				测试页面跳转...
			</button>
		</div>
	</body>
</html>
