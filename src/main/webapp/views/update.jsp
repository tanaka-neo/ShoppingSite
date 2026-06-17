<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>

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

		<form
			action="${pageContext.request.contextPath}/jp/co/aforce/servlet/UpdateConfirm.action"
			method="post" autocomplete="off">

			<label>ID</label> <input type="text" name="memberId"
				value="${user.memberId}" readonly> <label>パスワード
				(半角英数字8～32文字)</label>
			<div style="position: relative; margin-bottom: 20px;">
				<input type="password" id="password" name="password"
					value="${user.password}" style="margin-bottom: 5px;">

				<div style="text-align: left; font-size: 12px; color: #415a77;">
					<input type="checkbox" id="togglePassword"> パスワードを表示する
				</div>

				<span id="passwordError"
					style="color: #e07a5f; font-size: 12px; display: block; text-align: left; font-weight: bold; margin-top: 5px;"></span>
			</div>

			<label>名字 (32文字以内)</label> <input type="text" name="lastName"
				value="${user.lastName}"> <label>名前 (32文字以内)</label> <input
				type="text" name="firstName" value="${user.firstName}"> <label>住所
				(128文字以内)</label> <input type="text" name="address" value="${user.address}">

			<label>メールアドレス</label> <input type="email" name="mailAddress"
				value="${user.mailAddress}"> <input type="submit"
				class="button" value="確認画面へ">

		</form>

		<input type="button" class="button button-secondary" value="戻る"
			onclick="location.href='${pageContext.request.contextPath}/views/user-menu.jsp'">

	</div>
</div>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
<%@include file="../footer.jsp"%>