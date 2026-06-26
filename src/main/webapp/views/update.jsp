<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>
<%
// JSP側でのセッションバリデーション
Users sessionUser = (Users) session.getAttribute("user");
if (sessionUser == null) {
    response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
    return;
}
%>
<div class="container">
	<div class="card">

		<h1>会員情報修正</h1>

		<%@ taglib prefix="c" uri="jakarta.tags.core"%>

		<c:if test="${not empty errors}">
			<div class="message message-error">
				<c:forEach var="error" items="${errors}">
					<p>${error}</p>
				</c:forEach>
			</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/jp/co/aforce/servlet/UpdateConfirm.action" method="post" autocomplete="off">

			<label>会員ID</label>
			<input type="text" name="memberId" value="${user.memberId}" readonly>

			<label for="password">パスワード</label>
			<input type="password" id="password" name="password" value="${user.password}" required placeholder="8文字以上32文字以下の半角英数字" style="margin-bottom: 5px;">

			<div style="text-align: left; font-size: 12px; color: #415a77; margin-bottom: 20px;">
				<input type="checkbox" id="togglePassword"> パスワードを表示する
			</div>

			<span id="passwordError" style="color: #e07a5f; font-size: 12px; display: block; text-align: left; font-weight: bold; margin-top: 5px;"></span>

			<label>名字 (32文字以内)</label>
			<input type="text" name="lastName" value="${user.lastName}" required placeholder="例：一護">

			<label>名前 (32文字以内)</label>
			<input type="text" name="firstName" value="${user.firstName}" required placeholder="例：太郎">

			<label>住所(128文字以内)</label>
			<input type="text" name="address" value="${user.address}" required placeholder="例：東京都千代田区飯田橋1-2-3">

			<label>メールアドレス</label>
			<input type="email" name="mailAddress" value="${user.mailAddress}" required placeholder="例：strawberry@company.com">

			<input type="submit" class="button" value="確認画面へ">

		</form>

		<input type="button" class="button button-secondary" value="戻る" onclick="location.href='${pageContext.request.contextPath}/views/user-menu.jsp'" style="margin-top: 10px;">


<%@include file="../footer.jsp"%>