<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container" style="padding-top: 20px;">
    <div class="card admin-card" style="text-align: left;">
        
        <h1 style="text-align: center;">もちもちいちご大福</h1>
        
        <img src="${pageContext.request.contextPath}/images/strawberrydaifuku.jpg" 
             alt="いちご大福" 
             style="width: 100%; max-width: 500px; display: block; margin: 0 auto 30px; border-radius: 16px;">
        
        <div style="line-height: 1.8; color: #332224;">
            <p>求肥（ぎゅうひ）の柔らかさといちごのジューシーさが楽しめる、春の和菓子。あんこはこしあんがおすすめです。</p>
            
            <h3 style="color: #a6162e; border-bottom: 1px solid #f5cfd5; padding-bottom: 5px; margin-top: 30px;">📋 材料</h3>
            <ul>
                <li>いちご：中サイズ8個</li>
                <li>白玉粉：100g</li>
                <li>砂糖：50g</li>
                <li>白あん：200g</li>
            </ul>
            
            <h3 style="color: #a6162e; border-bottom: 1px solid #f5cfd5; padding-bottom: 5px; margin-top: 30px;">👨‍🍳 作り方</h3>
            <ol>
                <li>いちごをあんこで薄く包みます。</li>
                <li>白玉粉・水・砂糖を混ぜてレンジで加熱し、求肥を作ります。</li>
                <li>求肥で1を包めば出来上がり！</li>
            </ol>
        </div>

        <div class="button-group">
            <a href="${pageContext.request.contextPath}/views/user-menu.jsp" class="button button-secondary">TOPへ戻る</a>
        </div>
    </div>
</div>
<%@include file="../footer.jsp"%>