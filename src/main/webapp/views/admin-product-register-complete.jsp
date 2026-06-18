<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card">
		
		<h1>商品登録結果</h1>
		
		<div class="message-box" style="margin: 30px 0; font-size: 16px; text-align: center; font-weight: bold;">
			<p>${message}</p>
		</div>
		
		<div class="button-group" style="display: flex; flex-direction: column; gap: 10px;">
			
			<input type="button" value="続けて別の商品を登録する" class="button" 
				onclick="location.href='${pageContext.request.contextPath}/AdminProductRegister.action'">
				
			<input type="button" value="商品管理ページへ戻る" class="button button-secondary" 
				onclick="location.href='${pageContext.request.contextPath}/AdminProductList.action'">
				
		</div>
		
	</div>
</div>

<%@include file="../footer.jsp"%>