<%@page contentType="text/html; charset=UTF-8"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
	
<!DOCTYPE html>
<html>
<head>
<title>shoppingsite</title>
</head>
<body>

<div class="container">

	<div class="card">

		<h1>ログイン</h1>

		<form
			action="${pageContext.request.contextPath}/jp/co/aforce/servlet/Login.action"
			method="post">
			
			<label>ID</label>
			<input type="text" name="memberId"> 
			
			<label>パスワード</label>
			<!--Htmlのtype=passwordの仕様で勝手に黒丸になる-->
			<input type="password" name="password"> 
			
			<input type="submit"value="ログイン" class="button">
		
		</form>

		<input type="button" value="新規会員登録" class="button button-secondary"
			onclick="location.href='${pageContext.request.contextPath}/views/register.jsp'">

	</div>
</div>
</body>
</html>