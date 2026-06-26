package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.Users;
import tool.Action;

public class PurchaseConfirmAction extends Action {

	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		Users user = (session != null) ? (Users) session.getAttribute("user") : null;

		//ログインしていない（ゲスト状態）なら、ログイン画面へ
		if (user == null) {
		    request.setAttribute("message", "この機能を利用するにはログインが必要です。");
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