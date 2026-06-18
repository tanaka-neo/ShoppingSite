<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.Product"%>

<%@include file="../header.jsp"%>

<%
List<Product> list = (List<Product>) request.getAttribute("list");
%>

<div class="container">

	<div class="card admin-card">

		<div class="admin-header">
			<h1>商品管理</h1>
			<p>
				<a href="${pageContext.request.contextPath}/jp/co/aforce/servlet/AdminProductRegister.action" class="button btn-register">
					新規商品登録
				</a>
			</p>
		</div>

		<% if (list == null || list.isEmpty()) { %>
			<div class="no-data">
				<p>登録されている商品がありません。</p>
			</div>
		<% } else { %>

			<table class="admin-table">
				<thead>
					<tr>
						<th>画像</th>
						<th>商品名</th>
						<th>価格</th>
						<th>内容量</th>
						<th>在庫</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<% for(Product p : list){ %>
						<tr>
							<td class="col-img">
								<img src="${pageContext.request.contextPath}/images/<%= p.getImagePath() %>" 
									 class="admin-product-img"
									 onerror="this.src='${pageContext.request.contextPath}/images/no-image.png';">
							</td>
							
							<td class="col-name">
								<%= p.getProductName() %>
							</td>
							
							<td class="col-price">
								<%= p.getPrice() %>円
							</td>
							
							<td class="col-volume">
								<%= p.getVolume() %>
							</td>
							
							<td class="col-stock">
								<% if (p.getStock() <= 0) { %>
									<span class="badge badge-soldout">売り切れ</span>
								<% } else if (p.getStock() <= 5) { %>
									<span class="badge badge-warning">残りわずか (<%= p.getStock() %>)</span>
								<% } else { %>
									<span class="badge badge-success"><%= p.getStock() %> 個</span>
								<% } %>
							</td>
							
							<td class="col-action">
								<a href="${pageContext.request.contextPath}/jp/co/aforce/servlet/AdminProductUpdateInput.action?productId=<%=p.getProductId()%>" class="button btn-edit">
									編集
								</a>
								<a href="${pageContext.request.contextPath}/jp/co/aforce/servlet/AdminProductDeleteConfirm.action?productId=<%=p.getProductId()%>" class="button button-danger btn-delete">
									削除
								</a>
							</td>
						</tr>
					<% } %>
				</tbody>
			</table>

		<% } %>

	</div>
</div>

<%@include file="../footer.jsp"%>