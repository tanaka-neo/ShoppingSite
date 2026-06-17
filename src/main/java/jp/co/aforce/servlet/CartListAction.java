package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import tool.Action;

public class CartListAction extends Action {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "/views/login-in.jsp";
		}
		// カート取得
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

		request.setAttribute("cart", cart);

		return "/views/cart.jsp";
	}
}