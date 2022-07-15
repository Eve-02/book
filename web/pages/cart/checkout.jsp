<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>结算页面</title>
	<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >
	<style>
		h1 {
			text-align: center;
			margin-top: 200px;
		}
	</style>
	<%@ include file="/pages/common/head.jsp"%>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="../../static/img/logo.gif" >
		<span class="wel_word">结算</span>
		<div>
			<%@ include file="/pages/common/login_sucess_menu.jsp"%>
		</div>
	</div>
	
	<div id="main">
		<h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>
	</div>

	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>