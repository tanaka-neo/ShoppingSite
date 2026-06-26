<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@include file="../header.jsp"%>

<div class="container">
    <div class="card" style="max-width: 900px; margin: 0 auto;">
        <h1>【管理者用】全ユーザー購入履歴一覧</h1>
        <p>ショップ全体のすべてのお客様の購入履歴です。</p>
        <hr>

        <%
        // Actionから渡された管理者用の購入履歴リストを取り出す
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> adminHistoryList = (List<Map<String, Object>>) request.getAttribute("adminHistoryList");

        if (adminHistoryList == null || adminHistoryList.isEmpty()) {
        %>
            <p>まだ購入された履歴はありません。</p>
        <%
        } else {
        %>
            <table class="history-table" border="1" style="width:100%; border-collapse: collapse; text-align: left;">
                <thead>
                    <tr style="background-color: #e2e2e2; font-weight: bold;">
                        <th style="padding: 10px; width: 10%;">注文番号</th>
                        <th style="padding: 10px; width: 15%;">メンバーID</th> 
                        <th style="padding: 10px; width: 25%;">購入者氏名</th>
                        <th style="padding: 10px; width: 10%;">商品ID</th>
                        <th style="padding: 10px; width: 30%;">商品名</th>
                        <th style="padding: 10px; width: 15%;">数量</th>
                        <th style="padding: 10px; width: 20%;">購入日時</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    for (Map<String, Object> history : adminHistoryList) {
                    %>
                        <tr>
                            <td style="padding: 10px;"><%= history.get("historyId") %></td>
                            <td style="padding: 10px; color: #555; font-family: monospace;"><%= history.get("memberId") %></td>
                            <td style="padding: 10px; font-weight: bold;"><%= history.get("userName") %></td>
                            <td style="padding: 10px; color: #666;"><%= history.get("productId") %></td>
                            <td style="padding: 10px; color: #ff4d6d; font-weight: bold;"><%= history.get("productName") %></td>
                            <td style="padding: 10px;"><%= history.get("quantity") %> 個</td>
                            <td style="padding: 10px; color: #555;"><%= history.get("purchaseDate") %></td>
                        </tr>
                    <%
                    }
                    %>
                </tbody>
            </table>
        <%
        }
        %>

        <br>
        <a href="${pageContext.request.contextPath}/jp/co/aforce/servlet/AdminProductList.action" class="button">商品管理へ戻る</a>
    </div>
</div>

<%@include file="../footer.jsp"%>