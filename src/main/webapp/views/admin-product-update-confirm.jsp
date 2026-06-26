<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card" style="border: 2px solid #3498db;">
		<%-- 確認画面と分かりやすいように青系の枠線 --%>

		<h1>変更内容の確認</h1>

		<p style="text-align: center; margin-bottom: 20px; color: #555;">
			以下の内容で商品の情報を更新します。よろしいですか？</p>

		<form
			action="${pageContext.request.contextPath}/AdminProductUpdate.action"
			method="post">

			<%-- 
			  【★修正ポイント①】
			  valueの中身を ${product.xxx} から ${xxx} に変更しました。
			  これで「画面で入力された新しい値」を次のActionへ無傷で届けることができます！
			--%>
			<input type="hidden" name="productId" value="${product.productId}">
			<input type="hidden" name="productName" value="${product.productName}"> 
			<input type="hidden" name="price" value="${product.price}"> 
			<input type="hidden" name="stock" value="${product.stock}"> 
			<input type="hidden" name="description" value="${product.description}"> 
			<input type="hidden" name="imagePath" value="${product.imagePath}">
			<input type="hidden" name="sweetness" value="${product.sweetness}">
			<input type="hidden" name="sourness" value="${product.sourness}">
			<input type="hidden" name="berrySize" value="${product.berrySize}">
			<input type="hidden" name="origin" value="${product.origin}">
			<input type="hidden" name="volume" value="${product.volume}">

			<%-- 管理者が見て確認するための表示用テーブル（こちらも${xxx}に修正） --%>
			<table class="table" style="margin-bottom: 30px; width: 100%;">
				<tr>
					<th style="width: 30%;">商品ID</th>
					<td>${product.productId}</td>
				</tr>
				<tr>
					<th>商品名</th>
					<td>${product.productName}</td>
				</tr>
				<tr>
					<th>価格</th>
					<td>${product.price}円</td>
				</tr>
				<tr>
					<th>在庫数</th>
					<td>${product.stock}パック</td>
				</tr>
				<tr>
					<th>商品説明</th>
					<td style="white-space: pre-wrap;">${product.description}</td>
				</tr>
				<tr>
					<th>変更する画像</th>
					<td>
						<%-- 【おまけ】管理者が視覚的に確認できるように、画像もプレビュー表示させておくと親切です！ --%>
						<img src="<%=request.getContextPath()%>/images/${product.imagePath}" style="max-width: 150px; display: block; margin-bottom: 5px;">
						${product.imagePath}
					</td>
				</tr>
				<tr>
					<th>甘み (1〜5)</th>
					<td>${product.sweetness}</td>
				</tr>
				<tr>
					<th>酸味 (1〜5)</th>
					<td>${product.sourness}</td>
				</tr>
				<tr>
					<th>サイズ (1〜5)</th>
					<td>${product.berrySize}</td>
				</tr>
				<tr>
					<th>産地</th>
					<td>${product.origin}</td>
				</tr>
				<tr>
					<th>内容量</th>
					<td>${product.volume}</td>
				</tr>
			</table>

			<div class="button-group" style="display: flex; gap: 10px;">
				<%-- 確定ボタン：これを押すと、裏に隠されたhiddenデータが一斉に本番Actionへ送信されます --%>
				<input type="submit" value="この内容で確定する" class="button"
					style="background-color: #3498db; flex: 1;">

				<%-- 
				
				   新規会員登録と同じ歴史の history.back() に変更しました！
				   これで、前の画面のJavaScript（sessionStorage）と連動して、画像も変更された状態のまま戻れます。
				--%>
				<input type="button" value="修正する（入力画面へ戻る）"
					class="button button-secondary" style="flex: 1;"
					onclick="history.back()">
			</div>

		</form>
	</div>
</div>

<%@include file="../footer.jsp"%>