<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>尚硅谷会员注册页面</title>
	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>
	<style>
		h1 {
			text-align: center;
			margin-top: 200px;
		}

		h1 a {
			color:red;
		}
	</style>
</head>
<body>
	<div id="header">
		<span class="wel_word"></span>
		<div>
			<%@ include file="/pages/common/login_sucess_menu.jsp"%>
		</div>
	</div>

	<div id="main">

		<h1>注册成功! <a href="index.jsp">转到主页</a></h1>

	</div>

	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>