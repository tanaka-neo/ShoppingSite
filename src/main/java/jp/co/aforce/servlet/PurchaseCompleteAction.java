package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import tool.Action;

public class PurchaseCompleteAction extends Action {

	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//セッション確認
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			return "/views/login-in.jsp";
		}
		// カートを空にする
		session.removeAttribute("cart");

		return "/views/purchase-complete.jsp";
	}
}