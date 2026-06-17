package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import tool.Action;

public class CartDeleteAction extends Action {

	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			return "/views/login-in.jsp";
		}
		//削除対象用の商品IDを取得
		String productId = request.getParameter("productId");

		// セッションから現在のカート情報を取得
		// "cart"という名前で保存していたList<CartItem>を取り出す
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		//	カート内の商品を検索

			// 商品IDが一致する商品を削除
			cart.removeIf(item ->
				item.getProduct().getProductId().equals(productId));
		
		session.setAttribute("cart", cart);
	    response.sendRedirect("CartList.action");
	    return null;

	}
}