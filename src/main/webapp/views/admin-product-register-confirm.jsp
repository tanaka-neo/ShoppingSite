<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- 💡 c:ifを使うためのお守りタグ（もし未記述なら必須です） --%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card admin-card">

		<h1>登録内容の確認</h1>

		<p class="confirm-intro-text">以下の内容で新しい商品を登録します。よろしいですか？</p>

		<form action="${pageContext.request.contextPath}/AdminProductRegisterComplete.action" method="post">

			<input type="hidden" name="productName" value="${productName}">
			<input type="hidden" name="price" value="${price}"> 
			<input type="hidden" name="stock" value="${stock}"> 
			<input type="hidden" name="description" value="${description}"> 
			<input type="hidden" name="imagePath" value="${imagePath}"> 
			<input type="hidden" name="sweetness" value="${sweetness}"> 
			<input type="hidden" name="sourness" value="${sourness}"> 
			<input type="hidden" name="berrySize" value="${berrySize}"> 
			<input type="hidden" name="origin" value="${origin}"> 
			<input type="hidden" name="volume" value="${volume}">

			<table class="confirm-table">
				<tr>
					<th>商品名</th>
					<td>${productName}</td>
				</tr>
				<tr>
					<th>価格</th>
					<td>${price}円</td>
				</tr>
				<tr>
					<th>在庫数</th>
					<td>${stock}パック</td>
				</tr>
				<tr>
					<th>商品説明</th>
					<td class="pre-wrap-text">${description}</td>
				</tr>
				<tr>
					<th>商品画像</th>
					<td>
						<c:if test="${not empty imagePath}">
							<img src="${pageContext.request.contextPath}/images/${imagePath}" alt="プレビュー" class="preview-img">
						</c:if> 
						<span class="file-path-text">${imagePath}</span>
					</td>
				</tr>
				<tr>
					<th>甘み (1〜5)</th>
					<td>${sweetness}</td>
				</tr>
				<tr>
					<th>酸味 (1〜5)</th>
					<td>${sourness}</td>
				</tr>
				<tr>
					<th>サイズ (1〜5)</th>
					<td>${berrySize}</td>
				</tr>
				<tr>
					<th>産地</th>
					<td>${origin}</td>
				</tr>
				<tr>
					<th>内容量</th>
					<td>${volume}</td>
				</tr>
			</table>

			<div class="button-group horizontal">
				<input type="submit" value="この内容で登録する" class="button">
				<input type="button" value="修正する（入力画面へ戻る）" class="button button-secondary" onclick="history.back()">
			</div>

		</form>
	</div>
</div>

<%@include file="../footer.jsp"%>