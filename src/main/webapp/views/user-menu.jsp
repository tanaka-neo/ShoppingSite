<%@page contentType="text/html; charset=UTF-8"%>
<%
if (session.getAttribute("user") == null) {
	response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
	return;
}
%>
<%@include file="../header.jsp"%>
<div class="container">
	<div class="card">
		<p class="message">ようこそ、${user.lastName}さん！</p>

		<input type="button" value="修正" class="button"
			onclick="location.href='${pageContext.request.contextPath}/views/update.jsp'">

		<form
			action="${pageContext.request.contextPath}/jp/co/aforce/servlet/Delete.action"
			method="post">
			<!--			confirmはブラウザ標準の「OK」「キャンセル」付きの確認用ポップアップ画面を表示するJavaScriptの機能-->
			<input type="submit" value="削除" class="button button-danger"
				onclick="return confirm('本当に削除しますか？')">
		</form>

		<form
			action="${pageContext.request.contextPath}/jp/co/aforce/servlet/Logout.action"
			method="post">
			<input type="submit" value="ログアウト" class="button button-secondary">
		</form>
	</div>
</div>
<%@include file="../footer.jsp"%>