<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<div class="container" style="padding-top: 20px;">
    <div class="card admin-card" style="text-align: left;">
        
        <h1 style="text-align: center;">濃厚いちごチーズケーキ</h1>
        
        <img src="${pageContext.request.contextPath}/images/strawberrycheesecake.jpg" 
             alt="チーズケーキ" 
             style="width: 100%; max-width: 500px; display: block; margin: 0 auto 30px; border-radius: 16px;">
        
        <div style="line-height: 1.8; color: #332224;">
            <p>いちごの甘酸っぱさとクリームチーズのコクが溶け合う、大人のチーズケーキです。冷蔵庫でしっかり冷やして召し上がれ！</p>
            
            <h3 style="color: #a6162e; border-bottom: 1px solid #f5cfd5; padding-bottom: 5px; margin-top: 30px;">📋 材料</h3>
            <ul>
                <li>クリームチーズ：200g</li>
                <li>いちごピューレ：100g</li>
                <li>生クリーム：100ml</li>
                <li>ビスケット（土台用）：適量</li>
            </ul>
            
            <h3 style="color: #a6162e; border-bottom: 1px solid #f5cfd5; padding-bottom: 5px; margin-top: 30px;">👨‍🍳 作り方</h3>
            <ol>
                <li>ビスケットを砕いて型に敷き詰めます。</li>
                <li>柔らかくしたチーズと他の材料を混ぜ合わせます。</li>
                <li>型に流し込み、冷蔵庫で3時間以上冷やし固めます。</li>
            </ol>
        </div>

        <div class="button-group">
            <a href="${pageContext.request.contextPath}/views/user-menu.jsp" class="button button-secondary">TOPへ戻る</a>
        </div>
    </div>
</div>
<%@include file="../footer.jsp"%>