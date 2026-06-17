<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../mini-header.jsp"%>

<div class="container">
    <div class="card">
        <h1>登録内容確認画面</h1>

        <form action="${pageContext.request.contextPath}/jp/co/aforce/servlet/Register.action" method="post">
            <%-- 次のActionへ値を引き継ぐための hidden--%>
            <input type="hidden" name="memberId" value="${registerUser.memberId}">
            <input type="hidden" name="password" value="${registerUser.password}">
            <input type="hidden" name="lastName" value="${registerUser.lastName}">
            <input type="hidden" name="firstName" value="${registerUser.firstName}">
            <input type="hidden" name="address" value="${registerUser.address}">
            <input type="hidden" name="mailAddress" value="${registerUser.mailAddress}">
            
            <p>ID：${registerUser.memberId}</p>
            <p>パスワード：${registerUser.password}</p>
            <p>名字：${registerUser.lastName}</p>
            <p>名前：${registerUser.firstName}</p>
            <p>住所：${registerUser.address}</p>
            <p>メールアドレス：${registerUser.mailAddress}</p>
            

            <input type="submit" value="登録" class="button">
        </form>

  
        <input type="button"  value="入力画面へ戻る" class="button button-secondary" onclick="history.back()"> 
    </div>
</div>

<%@include file="../footer.jsp"%>