<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>

<div class="register-container">

	<div class="register-card">
	
	<h1>会員情報登録</h1>
	
	<form
		action="${pageContext.request.contextPath}/jp/co/aforce/servlet/RegisterConfirm.action"
			method="post">
	
	<label>ID</label> <input type="text" name="memberId">
	<label>パスワード</label> <input type="password" name="password">
	<label>名字</label> <input type="text" name="lastName">
	<label>名前</label> <input type="text" name="firstName">
	<label>住所</label> <input type="text" name="address">
	<label>メールアドレス</label> <input type="email" name="mailAddress">
	
	<input type="submit" value="確認画面へ">
	
	</form>
	
	
	</div>
</div>

<%@include file="../footer.html"%>