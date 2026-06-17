package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import tool.Action;

public class PurchaseConfirmAction extends Action {

	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//セッション確認
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			return "/views/login-in.jsp";
		}


		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		//カートの中身がなかったらカート一覧に戻す
		if (cart == null || cart.isEmpty()) {
			return "/views/cart.jsp";
		}

		return "/views/purchase-confirm.jsp";
	}
}