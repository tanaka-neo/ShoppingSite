<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container">
    <div class="card">
        <h1>登録内容確認画面</h1>

        <form action="${pageContext.request.contextPath}/jp/co/aforce/servlet/Register.action" method="post">
            <%-- 次のActionへ値を引き継ぐための hidden--%>
            <input type="hidden" name="memberId" value="${user.memberId}">
            <input type="hidden" name="password" value="${user.password}">
            <input type="hidden" name="lastName" value="${user.lastName}">
            <input type="hidden" name="firstName" value="${user.firstName}">
            <input type="hidden" name="address" value="${user.address}">
            <input type="hidden" name="mailAddress" value="${user.mailAddress}">
            
            <p>ID：${user.memberId}</p>
            <p>パスワード：${user.password}</p>
            <p>名字：${user.lastName}</p>
            <p>名前：${user.firstName}</p>
            <p>住所：${user.address}</p>
            <p>メールアドレス：${user.mailAddress}</p>
            

            <input type="submit" value="登録" class="button">
        </form>

  
        <input type="button"  value="入力画面へ戻る" class="button button-secondary" onclick="history.back()"> 
    </div>
</div>

<%@include file="../footer.jsp"%>