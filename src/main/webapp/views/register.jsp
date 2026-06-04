<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card">
		<h1>会員情報登録</h1>

        <% if (request.getAttribute("message") != null) { %>
            <div class="message message-error" style="text-align: left; margin-bottom: 20px;">
                <p style="margin: 5px 0;">⚠️ ${message}</p>
            </div>
        <% } %>

		<form
			action="${pageContext.request.contextPath}/jp/co/aforce/servlet/RegisterConfirm.action"
			method="post">
			<label>ID</label>
			<!--            requiredはブラウザ上で自動的に未入力を防いでくれる-->
			<input type="text" name="memberId" required> <label>パスワード</label>
			<input type="password" name="password" required> <label>名字</label>
			<input type="text" name="lastName" required> <label>名前</label>
			<input type="text" name="firstName" required> <label>住所</label>
			<input type="text" name="address" required> <label>メールアドレス</label>
			<input type="email" name="mailAddress" required> <input
				type="submit" value="確認画面へ" class="button">

		</form>

		<input type="button" class="button button-secondary" value="ログイン画面へ戻る"
			onclick="location.href='${pageContext.request.contextPath}/views/login-in.jsp'">
	</div>
</div>

<%@include file="../footer.jsp"%>