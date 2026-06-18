<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card admin-register-card">
		
		<h1>新規商品登録（入力）</h1>
		
		<form action="AdminProductRegisterConfirm.action" method="post" class="register-form">
			
			<div class="form-group">
				<label for="productName">商品名</label>
				<input type="text" id="productName" name="productName" required placeholder="例：完熟あまおう">
			</div>
			
			<div class="form-group">
				<label for="price">価格（円）</label>
				<input type="number" id="price" name="price" min="0" required placeholder="例：1200">
			</div>
			
			<div class="form-group">
				<label for="stock">初期在庫数（個）</label>
				<input type="number" id="stock" name="stock" min="0" required placeholder="例：50">
			</div>
			
			<div class="form-group">
				<label for="volume">内容量</label>
				<input type="text" id="volume" name="volume" required placeholder="例：1パック（約250g）">
			</div>
			
			<div class="form-group">
				<label for="origin">産地</label>
				<input type="text" id="origin" name="origin" required placeholder="例：福岡県産">
			</div>
			
			<div class="form-group">
				<label for="description">商品説明</label>
				<textarea id="description" name="description" rows="4" required placeholder="商品の特徴や詳細を入力してください"></textarea>
			</div>
			
			<div class="form-group">
				<label for="filePicker">商品画像を選択</label>
				<input type="file" id="filePicker" accept="image/*" class="file-input">
				
				<input type="hidden" id="imagePath" name="imagePath" required>
				
				<p id="fileNameDisplay" style="font-size: 14px; color: #ff4d6d; margin-top: 5px;"></p>
			</div>
			
			<input type="hidden" name="sweetness" value="5">
			<input type="hidden" name="sourness" value="3">
			<input type="hidden" name="berrySize" value="4">

			<div class="button-group">
				<button type="submit" class="button btn-submit">入力内容を確認する</button>
				<a href="AdminProductList.action" class="button button-secondary btn-cancel">戻る</a>
			</div>
			
		</form>
		
	</div>
</div>

<script>
// 「ファイルを選択」ボタンで画像が選択された際の処理
document.getElementById('filePicker').addEventListener('change', function(e) {
    const file = e.target.files[0];
    if (file) {
        // 選択されたファイルの名称を抽出し、Hiddenフィールドに設定
        document.getElementById('imagePath').value = file.name;
        
        // 画面上に選択されたファイル名を表示
        document.getElementById('fileNameDisplay').innerText = "選択済み: " + file.name;
    }
});
</script>