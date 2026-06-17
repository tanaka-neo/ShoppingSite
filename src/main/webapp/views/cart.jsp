<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.CartItem"%>
<%@include file="../header.jsp"%>

<%
List<CartItem> cart = (List<CartItem>) request.getAttribute("cart");
%>

<div class="container">

	<div class="card">

		<h1>カート</h1>

		<%
		if (cart == null || cart.isEmpty()) {
		%>

		<p>カートに商品がありません。</p>

		<%
		} else {

		int total = 0;

		for (CartItem item : cart) {

			int subtotal = item.getProduct().getPrice()
			* item.getQuantity();

			total += subtotal;
		%>

		<p>
			<%=item.getProduct().getProductName()%>
			×
			<%=item.getQuantity()%>個
		</p>

		<p>
			小計：<%=subtotal%>円
		</p>

		<form action="${pageContext.request.contextPath}/jp/co/aforce/servlet/CartUpdate.action" method="post">

			<input type="hidden" name="productId"
				value="<%=item.getProduct().getProductId()%>">

			<button type="button" onclick="changeQty(this,-1)">-</button>

			<input type="number" name="quantity" value="<%=item.getQuantity()%>"
				min="1" onchange="submitForm(this)">

			<button type="button" onclick="changeQty(this,1)">+</button>

		</form>

		<form action="${pageContext.request.contextPath}/jp/co/aforce/servlet/CartDelete.action" method="post">

			<input type="hidden" name="productId"
				value="<%=item.getProduct().getProductId()%>"> <input
				type="submit" value="削除" class="button button-danger">

		</form>

		<hr>

		<%
		}
		%>

		<h3>
			合計：<%=total%>円
		</h3>

		<%
		}
		%>

		<form action="${pageContext.request.contextPath}/jp/co/aforce/servlet/PurchaseConfirm.action" method="post">
			<input type="submit" value="購入手続きへ" class="button">
		</form>

		<a href="${pageContext.request.contextPath}/jp/co/aforce/servlet/ProductList.action" class="button"> 商品一覧へ戻る </a>

	</div>

</div>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
<%@include file="../footer.jsp"%>