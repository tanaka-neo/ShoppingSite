<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@include file="../mini-header.jsp"%>

<div class="container">
	<div class="card">
		<h1>新規会員登録</h1>

		<c:if test="${not empty errors}">
			<div class="message message-error">
				<c:forEach var="error" items="${errors}">
					<p>${error}</p>
				</c:forEach>
			</div>
		</c:if>

		<form
			action="${pageContext.request.contextPath}/jp/co/aforce/servlet/RegisterConfirm.action"
			method="post">

			<label>会員ID</label> <input type="text" name="memberId"
				value="${formUser.memberId}" required placeholder="半角英数字10文字以内">
			<label for="password">パスワード</label><input type="password"
				id="password" name="password" value="${formUser.password}" require
				placeholder="8文字以上32文字以下の半角英数字">
			<div style="text-align: left; font-size: 12px; color: #415a77;">

				<input type="checkbox" id="togglePassword"> パスワードを表示する
			</div>


			<label>名字</label> <input type="text" name="lastName"
				value="${formUser.lastName}" required placeholder="例：例：一護">
			<label>名前</label> <input type="text" name="firstName"
				value="${formUser.firstName}" required placeholder="例：太郎"> <label>住所</label>
			<input type="text" name="address" value="${formUser.address}"
				required placeholder="例：東京都千代田区飯田橋1-2-3"> <label>メールアドレス</label><input
				type="text" name="mailAddress" value="${formUser.mailAddress}"
				required placeholder="例：strawberry@company.com"> <input
				type="submit" class="button" value="確認画面へ">
		</form>

		<input type="button" class="button button-secondary" value="戻る"
			onclick="location.href='${pageContext.request.contextPath}/views/login-in.jsp'">

	</div>
</div>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
<%@include file="../footer.jsp"%>