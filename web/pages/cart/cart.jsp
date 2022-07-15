<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>购物车</title>
	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>
	<script>
		$(function () {
			// 给【删除】绑定点击事件
			$("a.deleteItem").click(function () {
				return confirm("你确定要删除【"+ $(this).parent().parent().find("td:first").text() + "】吗？");
			})
			// 给【清空购物车】绑定点击事件
			$("#clear").click(function () {
				return confirm("你确定要清空购物车吗?");
			})
			// 给输入框绑定内容改变事件
			$("input.update").change(function () {
				// 获取商品名称
				var name = $(this).parent().parent().find("td:first").text();
				// 获取商品数量
				var count = this.value;
				var id = $(this).attr("bookId");
				var boo = confirm("你确定要将【"+ name +"】商品数量修改为: "+ count + "吗？");
				if(boo){
					location.href = "http://localhost:8080/book/cartServlet?action=updateCount&count=" + count + "&id=" + id;
				}else{
					// dom对象的属性，默认的value属性值
					this.value = this.defaultValue;
				}
			})
		})
	</script>
</head>
<body>
	<div id="header">
		<span class="wel_word">购物车</span>
		<div>
			<%@ include file="/pages/common/login_sucess_menu.jsp"%>
		</div>
	</div>

	<div id="main">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<%-- 为空时提示 --%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">当前购物车为空，快去浏览商品吧~</a></td>
				</tr>
			</c:if>
			<%-- 不为空时遍历 --%>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="item" >
					<tr>
						<td>${item.value.name}</td>
						<td>
							<input class="update" style="width: 80px;text-align: center"
								  bookId="${item.value.id}" type="text" value="${item.value.count}">
						</td>
						<td>${item.value.price}</td>
						<td>${item.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<%-- 购物车非空显示 --%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clear" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>
	
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>