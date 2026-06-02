<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>

<div class="success-container">


	<div class="success-card">

		<p>登録が完了しました</p>

		<input type="button" value="ログイン画面へ戻る"
			onclick="location.href='${pageContext.request.contextPath}/views/login-in.jsp'">
	</div>
</div>

<%@include file="../footer.html"%>