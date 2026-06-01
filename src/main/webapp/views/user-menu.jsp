<%@page contentType="text/html; charset=UTF-8"%>
<!--ログインチェック、セッションにユーザー情報があるか確認-->
<%
if (session.getAttribute("users") == null) {
	response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
	return;
}
%>
<%@include file="../header.html"%>
<div class="menu-container">
	<div class="menu-card">



		<p class="welcome-text">ようこそ、${users.lastName}さん！</p>

		<input type="button" value="修正"> <input type="button"
			value="削除">

		<form
			action="${pageContext.request.contextPath}/jp/co/aforce/servlet/Logout.action"
			method="post">
			<input type="submit" value="ログアウト">
		</form>

	</div>
</div>
<%@include file="../footer.html"%>