<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>

<div class="error-container">


	<div class="error-card">

		<p class="error-text" style="color: red;">${message}</p>

		<input type="button" value="ログイン画面へ戻る"
			onclick="location.href='${pageContext.request.contextPath}/views/login-in.jsp'">
	</div>
</div>

<%@include file="../footer.html"%>