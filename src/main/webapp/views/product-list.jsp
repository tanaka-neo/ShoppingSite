<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.Product"%>
<%@include file="../header.jsp"%>

<%
// Actionクラスから渡された、絞り込み済みの商品一覧リストを取得
List<Product> list = (List<Product>) request.getAttribute("list");

// Actionクラスから送り返された検索キーワードと並び替え状態を取得
// 初回アクセス時などで null の場合は、エラーや不自然な表示を防ぐために空文字に変換する
String keyword = (String) request.getAttribute("keyword");
if (keyword == null) {
	keyword = "";
}

String sort = (String) request.getAttribute("sort");
if (sort == null) {
	sort = "";
}
%>

<h1 class="page-title">商品一覧</h1>

<div class="search-container">
	<form action="ProductList.action" method="get" class="search-form">

		<div class="form-group">
			<label for="keyword" class="search-label">商品検索：</label> <input
				type="text" id="keyword" name="keyword" value="<%=keyword%>"
				placeholder="商品名・特徴・産地を入力" class="search-input">
		</div>

		<div class="form-group">
			<label for="sort" class="search-label">並び替え：</label> <select
				id="sort" name="sort" class="sort-select">
				<option value="price_asc"
					<%=sort.equals("price_asc") ? "selected" : ""%>>価格の安い順</option>
				<option value="price_desc"
					<%=sort.equals("price_desc") ? "selected" : ""%>>価格の高い順</option>
				<option value="name_asc"
					<%=sort.equals("name_asc") ? "selected" : ""%>>商品名順</option>
				<option value="sweet_desc"
					<%=sort.equals("sweet_desc") ? "selected" : ""%>>甘味が強い順</option>
				<option value="sour_desc"
					<%=sort.equals("sour_desc") ? "selected" : ""%>>酸味が強い順</option>
			</select>
		</div>

		<input type="submit" value="検索・並び替え" class="button btn-search">
	</form>
</div>

<div class="container">
	<div class="product-list">

		<%
		// リストが空でないことを確認してループ処理を開始
		if (list != null) {
			for (Product p : list) {
		%>

		<div class="product-card">

			<a href="ProductDetail.action?productId=<%=p.getProductId()%>&sort=<%=sort%>"> <img
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
				<input type="hidden" name="quantity" value="1"> <input
					type="submit" value="カートに入れる" class="button">
			</form>

		</div>

		<%
		}
		}
		%>

	</div>
</div>

<%@include file="../footer.jsp"%>