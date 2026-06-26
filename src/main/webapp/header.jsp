<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.aforce.beans.Users"%>

<%
// セッションからログインユーザーの情報を取得
Users loginUser = (Users) session.getAttribute("user");
%>

<header class="site-header">
<div class="logo">
<%
if (loginUser != null && loginUser.getRole() == 1) {
%>
    <a href="${pageContext.request.contextPath}/jp/co/aforce/servlet/AdminProductList.action"
       style="text-decoration: none;">
        いちご図鑑ＳＨＯＰ
    </a>
<%
} else {
%>
    <a href="${pageContext.request.contextPath}/views/user-menu.jsp"
       style="text-decoration: none;">
        いちご図鑑ＳＨＯＰ
    </a>
<%
}
%>
</div>
	<nav>
		<ul class="nav-menu">

			<li class="nav-welcome" style="font-weight: bold; margin-right: 10px;">
				<span class="nav-welcome" style="color: #4a373a; font-weight: bold; margin-right: 10px; display: flex; align-items: center; gap: 8px;">
					<!-- 🍓 いちごマン：今のCSSと調和するように縦横30pxを保証 -->
					<img src="${pageContext.request.contextPath}/images/ichigoman.png"
					alt="いちごマン" style="width: 30px; height: 30px; border-radius: 50%; object-fit: contain;">
					<%
					if (loginUser == null) {
					%> ようこそ、ゲストさん！ <%
					} else {
					%> ようこそ、<%=loginUser.getLastName()%>さん！
					<%
					}
					%>
				</span> <!-- 💡 抜けていた閉じタグを補完しました -->
			</li>

			<%
			if (loginUser != null && loginUser.getRole() == 1) {
			%>
			<li><a href="${pageContext.request.contextPath}/jp/co/aforce/servlet/AdminHistoryList.action" class="nav-button">購入履歴管理</a></li>
			<li><a
				href="${pageContext.request.contextPath}/jp/co/aforce/servlet/AdminProductList.action"
				class="nav-button">商品管理</a></li>
			<li><a
				href="${pageContext.request.contextPath}/jp/co/aforce/servlet/Logout.action"
				class="nav-button logout">ログアウト</a></li>

			<%
			} else {
			%>
			<%-- 🍓 一般ユーザー、または未ログイン（ゲスト）用のメニュー --%>
			<li><a
				href="${pageContext.request.contextPath}/jp/co/aforce/servlet/ProductList.action"
				class="nav-button">商品一覧</a></li>


			<%-- 💡 ログインしている一般ユーザーだけが「購入履歴」を見られるようにします --%>
			<%
			if (loginUser != null) {
			%>
			<li><a href="${pageContext.request.contextPath}/views/cart.jsp"
				class="nav-button">カート</a></li>
			<li><a
				href="${pageContext.request.contextPath}/views/user-info.jsp"
				class="nav-button">会員情報</a></li>

			<li><a
				href="${pageContext.request.contextPath}/jp/co/aforce/servlet/HistoryList.action"
				class="nav-button">購入履歴</a></li>
			<%
			}
			%>

			<%-- 🔐 ログイン・ログアウトボタンの切り替え --%>
			<%
			if (loginUser == null) {
			%>
			<li><a
				href="${pageContext.request.contextPath}/views/login-in.jsp"
				class="nav-button login">ログイン</a></li>
			<%
			} else {
			%>
			<li><a
				href="${pageContext.request.contextPath}/jp/co/aforce/servlet/Logout.action"
				class="nav-button logout">ログアウト</a></li>
			<%
			}
			%>

			<%
			}
			%>
		</ul>
	</nav>
</header>