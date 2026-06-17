<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.Product"%>

<%@include file="../header.jsp"%>

<%
List<Product> list =
	(List<Product>) request.getAttribute("list");
%>

<div class="container">

	<div class="card">

		<h1>商品管理</h1>

		<p>
			<a href="AdminProductRegister.action" class="button">
				商品登録
			</a>
		</p>

		<table>

			<tr>
				<th>商品名</th>
				<th>価格</th>
				<th>操作</th>
			</tr>

			<%
			for(Product p : list){
			%>

			<tr>
				<td><%= p.getProductName() %></td>

				<td><%= p.getPrice() %>円</td>

				<td>
					<a href="AdminProductEdit.action?productId=<%=p.getProductId()%>">
						編集
					</a>

					<a href="AdminProductDelete.action?productId=<%=p.getProductId()%>">
						削除
					</a>
				</td>
			</tr>

			<%
			}
			%>

		</table>

	</div>

</div>

<%@include file="../footer.jsp"%>