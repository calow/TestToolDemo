<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>就业服务机构查询</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<%
	response.setContentType("text/html;charset=utf-8");
%>
<link rel="stylesheet" href="${resourceUrl}/css/sm.min.css">
<link rel="stylesheet" href="${resourceUrl}/css/common.css">
</head>

<body>
	<div class="page">
		<header class="bar bar-nav"> <a
			class="icon icon-left pull-left" style="color:white"
			onclick="plus.webview.currentWebview().parent()==null?plus.webview.currentWebview().close():plus.webview.currentWebview().parent().close();"></a>
		<a class="icon icon-search pull-right open-popup" style="color:white"
			data-popup=".popup-about"></a>
		<h1 class="title">就业服务机构查询</h1>
		</header>
		<div class="content" style="background-color:#EBEBEB;">
			<div class="list-block" style="margin:0">
				<ul>
					<li class="item-content">
						<div class="item-inner">
							<div class="item-title label">区域索引</div>
							<div class="item-input">
								<input type="text" placeholder="选择区域" id="picker" readonly="">
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div id="listOfArea">
				<div class="content-block-title">请选择区域索引</div>
			</div>

		</div>
	</div>

	<div class="popup popup-about" style="display: none;">
		<header class="bar bar-nav"> <a
			class="button button-link button-nav pull-right close-popup"> 取消
		</a>
		<h1 class="title">关键字搜索</h1>
		</header>
		<div class="content native-scroll" style="background-color:#EBEBEB;">
			<div class="content-padded" style="margin:0">
				<div class="searchbar row">
					<div class="search-input col-80">
						<label class="icon icon-search" for="search"></label> <input
							type="search" id='keyWords' placeholder='输入关键字...' />
					</div>
					<a class="button col-20 popup-button-success" onclick="keyWords()">搜索</a>
				</div>
			</div>
			<div id="listOfKeyWord">
				<div class="content-block-title">请输入关键字进行搜索</div>
			</div>
		</div>
	</div>
	<script type='text/javascript' src='${resourceUrl}/js/zepto.min.js'
		charset='utf-8'></script>
	<script type='text/javascript' src='${resourceUrl}/js/sm.min.js'
		charset='utf-8'></script>
	<script type='text/javascript' src='${resourceUrl}/js/main.js'
		charset='utf-8'></script>

	<script type="text/javascript">
		var userAccount = '${userAccount}';
		var requestUrl = '${requestUrl}';
		var resourceUrl = '${resourceUrl}';
		var forwardUrl = '${forwardUrl}';

		$("#picker")
				.picker(
						{
							toolbarTemplate : '<header class="bar bar-nav">\
<button class="button button-link pull-left close-picker" style="color:white">取消</button>\
<button class="button button-link pull-right close-picker" style="color:white" onclick="areaSearch()">确定</button>\
<h1 class="title" style="color:white">区域</h1>\
</header>',
							cols : [ {
								textAlign : 'center',
								values : [ '广东省', '广州市', '深圳市', '珠海市', '汕头市',
										'佛山市', '韶关市', '河源市', '梅州市', '惠州市',
										'汕尾市', '东莞市', '中山市', '江门市', '阳江市',
										'湛江市', '茂名市', '肇庆市', '清远市', '潮州市',
										'揭阳市', '云浮市' ]
							} ]
						});

		function areaSearch() {
			var value = document.getElementById("picker").value;
			switch (value) {
			case "广东省":
				area(1000, 1);
				break;
			case "广州市":
				area(1001, 2);
				break;
			case "深圳市":
				area(1002, 2);
				break;
			case "珠海市":
				area(1007, 2);
				break;
			case "汕头市":
				area(1009, 2);
				break;
			case "佛山市":
				area(1003, 2);
				break;
			case "韶关市":
				area(1013, 2);
				break;
			case "河源市":
				area(1016, 2);
				break;
			case "梅州市":
				area(1015, 2);
				break;
			case "惠州市":
				area(1006, 2);
				break;
			case "汕尾市":
				area(1021, 2);
				break;
			case "东莞市":
				area(1005, 2);
				break;
			case "中山市":
				area(1008, 2);
				break;
			case "江门市":
				area(1010, 2);
				break;
			case "阳江市":
				area(1017, 2);
				break;
			case "湛江市":
				area(1004, 2);
				break;
			case "茂名市":
				area(1011, 2);
				break;
			case "肇庆市":
				area(1014, 2);
				break;
			case "清远市":
				area(1012, 2);
				break;
			case "潮州市":
				area(1019, 2);
				break;
			case "揭阳市":
				area(1018, 2);
				break;
			case "云浮市":
				area(1020, 2);
				break;
			default:
				area(1000, 1);
			}
		};
	</script>
</body>
</html>
