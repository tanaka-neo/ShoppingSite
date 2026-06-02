<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>

<div class="register-container">

	<div class="register-card">

		<h1>登録内容確認画面</h1>


		<form
			action="${pageContext.request.contextPath}/jp/co/aforce/servlet/Register.action"
			method="post">
			
			<p>ID：${user.memberId}</p>
	

			<p>パスワード：${user.password}</p>
			<input type="hidden" name="password" value="${user.password}">
			<p>名字：${user.lastName}</p>
			<p>名前：${user.firstName}</p>
			<p>住所：${user.address}</p>
			<p>メールアドレス：${user.mailAddress}</p>
			<input type="submit" value="登録">
		</form>

		<input type="button" value="入力画面へ戻る" onclick="history.back()">
<!--			onclick="location.href='${pageContext.request.contextPath}/views/register.jsp'"-->
			
	</div>
</div>




<%@include file="../footer.html"%>