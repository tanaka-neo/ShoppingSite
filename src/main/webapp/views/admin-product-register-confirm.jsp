<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card">
		
		<h1>新規商品登録（確認）</h1>
		<p>以下の内容で登録します。よろしければ「確定する」ボタンを押してください。</p>
		
		<form action="${pageContext.request.contextPath}/AdminProductRegisterComplete.action" method="post">
			
			<input type="hidden" name="productName" value="${productName}">
			<input type="hidden" name="price" value="${price}">
			<input type="hidden" name="stock" value="${stock}">
			<input type="hidden" name="volume" value="${volume}">
			<input type="hidden" name="origin" value="${origin}">
			<input type="hidden" name="description" value="${description}">
			<input type="hidden" name="imagePath" value="${imagePath}">
			
			<input type="hidden" name="sweetness" value="${sweetness}">
			<input type="hidden" name="sourness" value="${sourness}">
			<input type="hidden" name="berrySize" value="${berrySize}">
			
			<p>商品名：${productName}</p>
			<p>価格：${price} 円</p>
			<p>初期在庫数：${stock} 個</p>
			<p>内容量：${volume}</p>
			<p>産地：${origin}</p>
			<p>商品説明：${description}</p>
			<p>商品画像ファイル名：${imagePath}</p>

			<input type="submit" value="確定する" class="button">
		</form>
		
		<input type="button" value="修正する" class="button button-secondary" onclick="history.back()">
		
	</div>
</div>

<%@include file="../footer.jsp"%>