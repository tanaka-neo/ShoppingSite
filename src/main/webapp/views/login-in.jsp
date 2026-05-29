<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>

<div class="login-container">

	<div class="login-card">

		<h1>sign in</h1>

		<form
			action="${pageContext.request.contextPath}/jp/co/aforce/servlet/Login.action"
			method="post">
			<label>ID</label> 
			<input type="text" name="memberId"> 
			<label>パスワード</label>
			<input type="password" name="password" class="login-button"> 
			<input type="submit"value="ログイン"> 
			<input type="button" value="新規会員登録">
		</form>

	</div>
</div>
<%@include file="../footer.html"%>