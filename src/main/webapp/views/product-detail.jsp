<%@ page import="jp.co.aforce.beans.Product"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>

<%
Product product = (Product) request.getAttribute("product");
%>

<div class="container">

	<div class="product-detail">

		<h1><%=product.getProductName()%></h1>

		<img
			src="<%=request.getContextPath()%>/images/<%=product.getImagePath()%>">

		<p class="detail-price">
			価格：<%=product.getPrice()%>円
		</p>
		<p>在庫数：<%=product.getStock()%></p>
		<p>内容量：<%=product.getVolume()%></p>
		<p>産地：<%=product.getOrigin()%></p>

		<p>
			甘味： <span class="rating"> <%for (int i = 0; i < product.getSweetness(); i++) {%>
				🍓 <%
 }
 for (int i = product.getSweetness(); i < 5; i++) {
 %> ☆ <%}%>
			</span>
		</p>

		<p>
			酸味： <span class="rating"> <%for (int i = 0; i < product.getSourness(); i++) {%>
				🍓 <%
 }
 for (int i = product.getSourness(); i < 5; i++) {
 %> ☆ <%}%>
			</span>
		</p>


		<p>
			粒の大きさ： <span class="rating"> <%for (int i = 0; i < product.getBerrySize(); i++) {%>
				🍓 <%
 }
 for (int i = product.getBerrySize(); i < 5; i++) {
 %> ☆ <%}%>
			</span>
		</p>

		<p>
			特徴：<%=product.getDescription()%></p>

		<hr>
		<h3>おすすめレシピ</h3>
		<p>準備中</p>

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

		<a href="ProductList.action" class="button">一覧に戻る </a>

	</div>

</div>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
<%@include file="../footer.jsp"%>