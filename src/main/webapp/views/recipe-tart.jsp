<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container" style="padding-top: 20px;">
    <div class="card admin-card" style="text-align: left;">
        
        <h1 style="text-align: center;">サクサク贅沢いちごタルト</h1>
        
        <img src="${pageContext.request.contextPath}/images/strawberrytalt.jpg" 
             alt="いちごタルト" 
             style="width: 100%; max-width: 500px; display: block; margin: 0 auto 30px; border-radius: 16px;">
        
        <div style="line-height: 1.8; color: #332224;">
            <p>旬のいちごをふんだんに使用した、見た目も華やかなタルトです。サクサクのタルト生地と濃厚なクリームの相性は抜群！</p>
            
            <h3 style="color: #a6162e; border-bottom: 1px solid #f5cfd5; padding-bottom: 5px; margin-top: 30px;">📋 材料（直径18cm）</h3>
            <ul>
                <li>いちご：1パック</li>
                <li>タルト生地：1台分</li>
                <li>カスタードクリーム：適量</li>
            </ul>
            
            <h3 style="color: #a6162e; border-bottom: 1px solid #f5cfd5; padding-bottom: 5px; margin-top: 30px;">👨‍🍳 作り方</h3>
            <ol>
                <li>タルト生地を焼き、冷ましておきます。</li>
                <li>冷めた生地にカスタードクリームをたっぷり塗ります。</li>
                <li>いちごを綺麗に並べて完成！</li>
            </ol>
        </div>

        <div class="button-group">
            <a href="${pageContext.request.contextPath}/views/user-menu.jsp" class="button button-secondary">TOPへ戻る</a>
        </div>
        
    </div>
</div>
<%@include file="../footer.jsp"%>