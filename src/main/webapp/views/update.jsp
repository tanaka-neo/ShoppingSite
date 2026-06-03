<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../header.jsp"%>

<div class="container">
    <div class="card">

        <h1>会員情報修正</h1>

        <form action="${pageContext.request.contextPath}/jp/co/aforce/servlet/Update.action"
              method="post">

<!--readonlyは値をを変えられないようにする-->
            <label>ID</label>
            <input type="text"
                   name="memberId"
                   value="${user.memberId}"
                   readonly>

            <label>パスワード</label>
            <input type="password"
                   name="password"
                   value="${user.password}">

            <label>名字</label>
            <input type="text"
                   name="lastName"
                   value="${user.lastName}">

            <label>名前</label>
            <input type="text"
                   name="firstName"
                   value="${user.firstName}">

            <label>住所</label>
            <input type="text"
                   name="address"
                   value="${user.address}">

            <label>メールアドレス</label>
            <input type="email"
                   name="mailAddress"
                   value="${user.mailAddress}">

            <input type="submit"
                   value="更新">

        </form>

    </div>
</div>

<%@include file="../footer.jsp"%>