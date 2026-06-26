<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card admin-register-card">
		
		<h1>商品情報の編集（入力）</h1>
		
		<form action="${pageContext.request.contextPath}/AdminProductUpdateConfirm.action" method="post" class="register-form">
			
			<input type="hidden" name="productId" value="${product.productId}">
			
			<div class="form-group">
				<label>商品ID（変更不可）</label>
				<p class="static-product-id">${product.productId}</p>
			</div>
			
			<div class="form-group">
				<label for="productName">商品名</label>
				<input type="text" id="productName" name="productName" value="${product.productName}" required placeholder="例：完熟あまおう">
			</div>
			
			<div class="form-group">
				<label for="price">価格（円）</label>
				<input type="number" id="price" name="price" value="${product.price}" min="0" required placeholder="例：1200">
			</div>
			
			<div class="form-group">
				<label for="stock">在庫数（個）</label>
				<input type="number" id="stock" name="stock" value="${product.stock}" min="0" required placeholder="例：50">
			</div>
			
			<div class="form-group">
				<label for="volume">内容量</label>
				<input type="text" id="volume" name="volume" value="${product.volume}" required placeholder="例：1パック（約250g）">
			</div>
			
			<div class="form-group">
				<label for="origin">産地</label>
				<input type="text" id="origin" name="origin" value="${product.origin}" required placeholder="例：福岡県産">
			</div>
			
			<div class="form-group">
				<label for="sweetness">甘み (1〜5)</label>
				<input type="number" id="sweetness" name="sweetness" value="${product.sweetness}" min="1" max="5" required>
			</div>
			
			<div class="form-group">
				<label for="sourness">酸味 (1〜5)</label>
				<input type="number" id="sourness" name="sourness" value="${product.sourness}" min="1" max="5" required>
			</div>
			
			<div class="form-group">
				<label for="berrySize">サイズ (1〜5)</label>
				<input type="number" id="berrySize" name="berrySize" value="${product.berrySize}" min="1" max="5" required>
			</div>
			
			<div class="form-group">
				<label for="description">商品説明</label>
				<textarea id="description" name="description" rows="4" required placeholder="商品の特徴や詳細を入力してください">${product.description}</textarea>
			</div>
			
			<div class="form-group">
				<label for="filePicker">商品画像を選択</label>
				<input type="file" id="filePicker" accept="image/*" class="file-input">
				
				<input type="hidden" id="imagePath" name="imagePath" value="${product.imagePath}" required>
				
				<p id="fileNameDisplay" class="file-name-display">現在の画像: ${product.imagePath}</p>
			</div>
			
			<div class="button-group">
				<button type="submit" class="button btn-submit">変更内容を確認する</button>
				<a href="${pageContext.request.contextPath}/AdminProductList.action" class="button button-secondary btn-cancel">戻る</a>
			</div>
			
		</form>
		
	</div>
</div>
<%@include file="../footer.jsp"%>
