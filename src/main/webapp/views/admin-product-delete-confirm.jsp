<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card" style="border: 2px solid #ff4d4d;"> <h1>商品削除の確認</h1>
		
		<div class="alert-box" style="margin: 20px 0; background-color: #fff5f5; padding: 15px; border-radius: 5px; text-align: center; color: #ff4d4d; font-weight: bold;">
			<p>警告：この商品を非表示（削除）にします。よろしいですか？</p>
		</div>
		
		<table class="table" style="margin-bottom: 30px;">
			<tr>
				<th>商品ID</th>
				<td>${product.productId}</td>
			</tr>
			<tr>
				<th>商品名</th>
				<td>${product.productName}</td>
			</tr>
			<tr>
				<th>価格</th>
				<td>${product.price} 円</td>
			</tr>
		</table>
		
		<form action="${pageContext.request.contextPath}/AdminProductDeleteComplete.action" method="post">
			<input type="hidden" name="productId" value="${product.productId}">
			
			<div class="button-group" style="display: flex; gap: 10px;">
				<input type="submit" value="はい、削除します" class="button" style="background-color: #ff4d4d; flex: 1;">
				
				<input type="button" value="キャンセル（一覧へ戻る）" class="button button-secondary" style="flex: 1;"
					onclick="location.href='${pageContext.request.contextPath}/AdminProductList.action'">
			</div>
		</form>
		
	</div>
</div>

<%@include file="../footer.jsp"%>