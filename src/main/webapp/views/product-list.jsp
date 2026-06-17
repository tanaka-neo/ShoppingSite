<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.Product"%>
<%@include file="../header.jsp"%>

<%
List<Product> list = (List<Product>) request.getAttribute("list");
%>

<h1 class="page-title">商品一覧</h1>

<div class="container">
	<div class="product-list">

		<%
		for (Product p : list) {
		%>

		<div class="product-card">

			<a href="ProductDetail.action?productId=<%=p.getProductId()%>"> <img
				class="product-image"
				src="<%=request.getContextPath()%>/images/<%=p.getImagePath()%>">

				<div class="product-name">
					<%=p.getProductName()%>
				</div>
			</a>

			<div class="product-price">
				<%=p.getPrice()%>円
			</div>

			<div class="product-origin">
				<%=p.getOrigin()%>
			</div>

			<form action="CartAdd.action" method="post">
				<input type="hidden" name="productId" value="<%=p.getProductId()%>">
				<input type="hidden" name="quantity" value="1"> 
				<input type="submit" value="カートに入れる" class="button">
			</form>

		</div>

		<%
}
%>

	</div>
</div>

<%@include file="../footer.jsp"%>