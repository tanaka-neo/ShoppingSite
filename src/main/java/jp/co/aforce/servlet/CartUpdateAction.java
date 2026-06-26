package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.Users;
import tool.Action;

public class CartUpdateAction extends Action {

	@Override
	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		HttpSession session = request.getSession(false);
		Users user = (session != null) ? (Users) session.getAttribute("user") : null;

		//ログインしていない（ゲスト状態）なら、ログイン画面へ
		if (user == null) {
		    return "/views/login-in.jsp"; 
		}
		
		// パラメータ取得
		String productId = request.getParameter("productId");
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		// カート取得
		List<CartItem> cart =
				(List<CartItem>) session.getAttribute("cart");

		if (cart != null) {

			for (CartItem item : cart) {

				if (item.getProduct().getProductId().equals(productId)) {

					// 数量更新
					item.setQuantity(quantity);

					break;
				}
			}

			// セッション更新
			session.setAttribute("cart", cart);
		}

		// PRGパターン
		response.sendRedirect("CartList.action");
		return null;
	}
}