<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.History"%>
<%@include file="../header.jsp"%>

<div class="container">
	<div class="card">
		<h1>マイ購入履歴</h1>
		<p>これまでに購入した商品の一覧です。</p>
		<hr>

		<%
		// Actionから渡された購入履歴リストを取り出す
		@SuppressWarnings("unchecked")
		List<History> historyList = (List<History>) request.getAttribute("historyList");

		if (historyList == null || historyList.isEmpty()) {
		%>
		<p>まだ購入履歴はありません。</p>
		<%
		} else {
		%>
		<table class="history-table" border="1"
			style="width: 100%; border-collapse: collapse; text-align: left;">
			<thead>
				<tr style="background-color: #f2f2f2;">
					<th style="padding: 10px;">注文番号</th>
					<th style="padding: 10px;">商品名</th>
					<th style="padding: 10px;">商品ID</th>
					<th style="padding: 10px;">購入数量</th>
					<th style="padding: 10px;">購入日時</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (History history : historyList) {
				%>
				<tr>
					<td style="padding: 10px;"><%=history.getHistoryId()%></td>
					<td style="padding: 10px; font-weight: bold; color: #ff4d6d;"><%=history.getProductName()%></td>
					<td style="padding: 10px; color: #666;"><%=history.getProductId()%></td>
					<td style="padding: 10px;"><%=history.getQuantity()%> 個</td>
					<td style="padding: 10px;"><%=history.getPurchaseDate()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		}
		%>

		<br> <a
			href="${pageContext.request.contextPath}/views/user-menu.jsp"
			class="button">メニューへ戻る</a>
	</div>
</div>

<%@include file="../footer.jsp"%>