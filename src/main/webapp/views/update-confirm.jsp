<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card">
		<h1>変更内容の確認</h1>
		<p style="margin-bottom: 35px; color: #415a77; font-size: 14px;">以下の内容で更新してもよろしいですか？</p>

		<table class="confirm-table">
			<tr><th>ID</th><td>${updateUser.memberId}</td></tr>
			<tr><th>パスワード</th><td>●●●●●●</td></tr>
			<tr><th>お名前</th><td>${updateUser.lastName} ${updateUser.firstName}</td></tr>
			<tr><th>住所</th><td>${updateUser.address}</td></tr>
			<tr><th>メール</th><td>${updateUser.mailAddress}</td></tr>
		</table>

		<div class="button-group">
			<form action="${pageContext.request.contextPath}/jp/co/aforce/servlet/Update.action" method="post">
				<input type="hidden" name="memberId" value="${updateUser.memberId}">
				<input type="hidden" name="password" value="${updateUser.password}">
				<input type="hidden" name="lastName" value="${updateUser.lastName}">
				<input type="hidden" name="firstName" value="${updateUser.firstName}">
				<input type="hidden" name="address" value="${updateUser.address}">
				<input type="hidden" name="mailAddress" value="${updateUser.mailAddress}" autocomplete="off">

				<input type="submit" value="更新する" class="button">
			</form>

			<input type="button" class="button button-secondary" value="修正する"
				onclick="history.back()">
		</div>
	</div>
</div>

<%@include file="../footer.jsp"%>