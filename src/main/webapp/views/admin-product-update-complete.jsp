<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card" style="padding: 40px; text-align: center;">

	<h1 style="color: #2c3e50; margin-bottom: 20px;">商品変更結果</h1>

	
		<div class="message-box" style="margin: 30px 0; font-size: 18px; font-weight: bold; color: #27ae60;">
			<p>${message}</p>
		</div>

		
		<div class="button-group" style="display: flex; flex-direction: column; gap: 15px; max-width: 300px; margin: 0 auto;">
			
			
			<input type="button" class="button" value="商品一覧へ戻る" style="width: 100%; padding: 10px; background-color: #27ae60;"
				onclick="location.href='${pageContext.request.contextPath}/AdminProductList.action'">

				
		</div>
	</div>
</div>

<%@include file="../footer.jsp"%>