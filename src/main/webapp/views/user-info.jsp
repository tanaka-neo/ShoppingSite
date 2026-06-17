<%@include file="../footer.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
if (session.getAttribute("user") == null) {
	response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
	return;
}
%>

<%@include file="../header.jsp"%>

<div class="container">

	<div class="card">

		<h1>会員情報</h1>

		<p><strong>会員ID</strong></p>
		<p>${user.memberId}</p>

		<p><strong>名字</strong></p>
		<p>${user.lastName}</p>

		<p><strong>名前</strong></p>
		<p>${user.firstName}</p>

		<p><strong>住所</strong></p>
		<p>${user.address}</p>

		<p><strong>メールアドレス</strong></p>
		<p>${user.mailAddress}</p>

		<input type="button"
			class="button"
			value="修正"
			onclick="location.href='${pageContext.request.contextPath}/views/update.jsp'">

		<form
			action="${pageContext.request.contextPath}/jp/co/aforce/servlet/Delete.action"
			method="post">

			<input type="submit"
				class="button button-danger"
				value="削除"
				onclick="return confirm('本当に削除しますか？')">

		</form>

	</div>

</div>

<%@include file="../footer.jsp"%>