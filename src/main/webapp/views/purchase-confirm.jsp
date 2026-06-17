<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.CartItem"%>
<%@include file="../header.jsp"%>

<%
List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
%>

<div class="container">

	<div class="card">

		<h1>購入確認</h1>


		<%	
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
			小計：
			<%=subtotal%>円
		</p>

		<hr>
		
		
		<%
		}
		%>
		
	     <h3>
			合計：
			<%=total%>円
		</h3>

		<form action="PurchaseComplete.action" method="post">
			<input type="submit" value="購入確定" class="button">
		</form>

		<a href="${pageContext.request.contextPath}/jp/co/aforce/servlet/CartList.action" class="button"> カートへ戻る </a>

	</div>

</div>

<%@include file="../footer.jsp"%>