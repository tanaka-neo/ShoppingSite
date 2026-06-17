<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.aforce.beans.Users" %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>shoppingsite</title>
</head>

<body>

	<header class="site-header">

		<div class="logo">🍓</div>

		<nav>
			<ul class="nav-menu">

				<li><a class="nav-button"
					href="${pageContext.request.contextPath}/views/user-menu.jsp">
						ホーム </a></li>

				<li><a class="nav-button"
					href="${pageContext.request.contextPath}/jp/co/aforce/servlet/ProductList.action">
						商品一覧 </a></li>

				<li><a class="nav-button"
					href="${pageContext.request.contextPath}/views/user-info.jsp">
						会員情報 </a></li>

				<li><a class="nav-button"
					href="${pageContext.request.contextPath}/jp/co/aforce/servlet/CartList.action">
						カート </a></li>

				<li><a class="nav-button logout"
					href="${pageContext.request.contextPath}/jp/co/aforce/servlet/Logout.action">
						ログアウト </a></li>

				<% Users loginUser=(Users)session.getAttribute("user");
                 if(loginUser !=null && loginUser.getRole()==1){
                  %>

				<li><a class="nav-button"
					href="${pageContext.request.contextPath}/jp/co/aforce/servlet/AdminProductList.action">
						商品管理</a></li>

				

				<%} %>
			</ul>
		</nav>

	</header>