<%@page contentType="text/html; charset=UTF-8"%>
<%
if (session.getAttribute("user") == null) {
	response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
	return;
}
%>

<%@ page import="jp.co.aforce.beans.Users" %>

<%
Users user = (Users)session.getAttribute("user");
%>

<%@include file="../header.jsp"%>
<div class="container">
	<div class="card">
	
		<% if (user != null) { %>
			<p class="message">ようこそ、<%= user.getLastName() %>さん！</p>
		<% } else { %>
			<p class="message">ようこそ、いちごECサイトへ！</p>
			<p>ログインすると購入や会員機能をご利用いただけます。</p>
		<% } %>

	</div>
</div>
<%@include file="../footer.jsp"%>