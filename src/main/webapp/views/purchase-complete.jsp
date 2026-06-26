<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card">

		<p class="message message-success">ご注文ありがとうございました！</p>
		<p>ご購入を受け付けました。</p>
		<input type="button" value="トップページへ" class="button"
			onclick="location.href='${pageContext.request.contextPath}/views/user-menu.jsp'">

	</div>
</div>

<%@include file="../footer.jsp"%>