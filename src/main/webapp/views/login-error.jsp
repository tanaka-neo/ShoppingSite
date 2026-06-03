<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">


	<div class="card">

		<p class="message message-error">${message}</p>

		<input type="button" value="ログイン画面へ戻る"
			onclick="location.href='${pageContext.request.contextPath}/views/login-in.jsp'">
	</div>
</div>

<%@include file="../footer.jsp"%>