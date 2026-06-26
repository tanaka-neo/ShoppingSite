<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.CartItem"%>
<%@include file="../header.jsp"%>
<%
// JSP側でのセッションバリデーション
Users sessionUser = (Users) session.getAttribute("user");
if (sessionUser == null) {
    response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
    return;
}
%>

<%
List<CartItem> cart = (List<CartItem>) request.getAttribute("cart");
%>

<div class="container">

	<div class="card">

		<h1>カート</h1>

		<%-- 👇 【新設】在庫が足りなかった時のエラーメッセージ表示欄 --%>
		<%
		String errorMsg = (String) request.getAttribute("errorMessage");
		if (errorMsg != null) {
		%>
			<div style="background-color: #fff0f1; border: 1px solid #ffccd1; color: #ff4d6d; padding: 15px; border-radius: 5px; margin-bottom: 20px; font-weight: bold;">
				⚠️ <%= errorMsg %>
			</div>
		<%
		}
		%>
		<%-- 👆 ここまで追加 --%>

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

<%@include file="../footer.jsp"%>