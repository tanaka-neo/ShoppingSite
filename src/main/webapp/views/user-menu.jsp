<%@page contentType="text/html; charset=UTF-8"%>
<%@ page import="jp.co.aforce.beans.Users" %>
<%@include file="../header.jsp"%>

<div class="hero-section">
    <h2 class="hero-title">いちご図鑑ＳＨＯＰ</h2>
</div>

<div class="top-content-body">

    <!-- おすすめいちご -->
    <section>
        <h3 class="section-title">🍓 本日のおすすめいちご</h3>

        <div class="horizontal-slider">

            <a class="slider-link"
               href="${pageContext.request.contextPath}/jp/co/aforce/servlet/ProductDetail.action?productId=P001">
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/images/amaou2.png"
                         alt="あまおう"
                         class="slider-img">
                    <p class="slider-product-name">あまおう</p>
                    <p class="product-price">¥980</p>
                </div>
            </a>

            <a class="slider-link"
               href="${pageContext.request.contextPath}/jp/co/aforce/servlet/ProductDetail.action?productId=P005">
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/images/skyberry2.png"
                         alt="スカイベリー"
                         class="slider-img">
                    <p class="slider-product-name">スカイベリー</p>
                    <p class="product-price">¥1200</p>
                </div>
            </a>

            <a class="slider-link"
               href="${pageContext.request.contextPath}/jp/co/aforce/servlet/ProductDetail.action?productId=P1782132527189">
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/images/awayuki2.png"
                         alt="淡雪"
                         class="slider-img">
                    <p class="slider-product-name">淡雪</p>
                    <p class="product-price">¥1800</p>
                </div>
            </a>

            <a class="slider-link"
               href="${pageContext.request.contextPath}/jp/co/aforce/servlet/ProductDetail.action?productId=P003">
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/images/benihoppe2.png"
                         alt="紅ほっぺ"
                         class="slider-img">
                    <p class="slider-product-name">紅ほっぺ</p>
                    <p class="product-price">¥950</p>
                </div>
            </a>

        </div>
    </section>

    <!-- おすすめレシピ -->
    <section>
        <h3 class="section-title">🍳 おすすめいちごレシピ</h3>

        <div class="horizontal-slider">

            <a class="slider-link"
               href="${pageContext.request.contextPath}/views/recipe-tart.jsp">
                <div class="product-card recipe-card">
                    <img src="${pageContext.request.contextPath}/images/strawberrytalt.jpg"
                         alt="いちごタルト"
                         class="slider-img recipe-img">
                    <p class="recipe-text">サクサク贅沢タルト ➔</p>
                </div>
            </a>

            <a class="slider-link"
               href="${pageContext.request.contextPath}/views/recipe-cheesecake.jsp">
                <div class="product-card recipe-card">
                    <img src="${pageContext.request.contextPath}/images/strawberrycheesecake.jpg"
                         alt="いちごチーズケーキ"
                         class="slider-img recipe-img">
                    <p class="recipe-text">濃厚チーズケーキ ➔</p>
                </div>
            </a>

            <a class="slider-link"
               href="${pageContext.request.contextPath}/views/recipe-daifuku.jsp">
                <div class="product-card recipe-card">
                    <img src="${pageContext.request.contextPath}/images/strawberrydaifuku.jpg"
                         alt="いちご大福"
                         class="slider-img recipe-img">
                    <p class="recipe-text">もちもち和スイーツ ➔</p>
                </div>
            </a>

        </div>
    </section>

    <!-- ショップニュース -->
    <section>
        <h3 class="section-title">📢 ショップニュース</h3>

        <ul class="topics-list">
            <li class="topics-item">
                <span class="topics-date">2026.06.22</span>
                <a href="#" class="topics-link">
                    【重要】お中元・ギフト配送の受付を開始いたしました
                </a>
            </li>

            <li class="topics-item">
                <span class="topics-date">2026.06.15</span>
                <a href="#" class="topics-link">
                    メディア掲載：『厳選お取り寄せ手帖』に当店が紹介されました！
                </a>
            </li>
        </ul>

    </section>

</div>

<footer class="site-footer">

    <div class="footer-info">
        <strong>いちご図鑑ＳＨＯＰ（Strawberry Company）</strong><br>
        〒100-0005 東京都千代田区いちご町 1-5<br>
        TEL: 03-1515-1515 / Email: support@strawberrycompany.co.jp
    </div>

    <div class="copyright">
        &copy; 2026 strawberrycompany All Rights Reserved.
    </div>

</footer>