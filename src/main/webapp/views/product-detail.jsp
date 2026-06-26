<%@ page import="jp.co.aforce.beans.Product"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>

<%
Product product = (Product) request.getAttribute("product");
%>

<%
String sort = (String) request.getAttribute("sort");
if (sort == null) {
	sort = "";
}
%>

<div class="container">

	<div class="product-detail">

		<h1><%=product.getProductName()%></h1>

		<img
			src="<%=request.getContextPath()%>/images/<%=product.getImagePath()%>">

		<div class="detail-row">
			<div class="label">価格：</div>
			<div class="value"><%=product.getPrice()%>円
			</div>
		</div>

		<div class="detail-row">
			<div class="label">在庫数：</div>
			<div class="value"><%=product.getStock()%></div>
		</div>

		<div class="detail-row">
			<div class="label">内容量：</div>
			<div class="value"><%=product.getVolume()%></div>
		</div>

		<div class="detail-row">
			<div class="label">産地：</div>
			<div class="value"><%=product.getOrigin()%></div>
		</div>

		<div class="detail-row">
			<div class="label">甘味：</div>
			<div class="value rating">
				<%
				for (int i = 0; i < product.getSweetness(); i++) {
				%>
				🍓
				<%
				}
				%>
				<%
				for (int i = product.getSweetness(); i < 5; i++) {
				%>
				🐝
				<%
				}
				%>
			</div>
		</div>

		<div class="detail-row">
			<div class="label">酸味：</div>
			<div class="value rating">
				<%
				for (int i = 0; i < product.getSourness(); i++) {
				%>
				🍓
				<%
				}
				%>
				<%
				for (int i = product.getSourness(); i < 5; i++) {
				%>
				🐝
				<%
				}
				%>
			</div>
		</div>

		<div class="detail-row">
			<div class="label">粒の大きさ：</div>
			<div class="value rating">
				<%
				for (int i = 0; i < product.getBerrySize(); i++) {
				%>
				🍓
				<%
				}
				%>
				<%
				for (int i = product.getBerrySize(); i < 5; i++) {
				%>
				🐝
				<%
				}
				%>
			</div>
		</div>

		<div class="detail-row">
			<div class="label">特徴：</div>
			<div class="value"><%=product.getDescription()%></div>
		</div>


		<hr>


		<h3>ご購入前の注意事項</h3>

		<ul>
			<li>生鮮食品のため返品・交換はお受けできません。</li>
			<li>粒の大きさや形には個体差があります。</li>
			<li>小さな傷や葉の付着が見られる場合があります。</li>
			<li>到着後は冷蔵保存し、お早めにお召し上がりください。</li>
		</ul>

		<form action="CartAdd.action" method="post">

			<input type="hidden" name="productId"
				value="<%=product.getProductId()%>">

			<p>数量：</p>
			<div class="quantity-area">
				<button type="button" class="btn-qty"
					onclick="changeQtyDetail(this, -1)">-</button>

				<input type="number" name="quantity" value="1" min="1" readonly
					style="width: 50px; text-align: center;">

				<button type="button" class="btn-qty"
					onclick="changeQtyDetail(this, 1)">+</button>
			</div>

			<input type="submit" value="カートに入れる" class="button">
		</form>

		<a href="ProductList.action?sort=<%=sort%>" class="button">一覧に戻る </a>

	</div>

</div>

<%@include file="../footer.jsp"%>