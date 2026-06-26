package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import tool.Action;

public class AdminProductRegisterConfirmAction extends Action {

	@Override
	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession(false);
    	Users user = (session != null) ? (Users) session.getAttribute("user") : null;

    	//ログインしていない、または、管理者（role==1）じゃないならはじく
    	if (user == null || user.getRole() != 1) {
    	    request.setAttribute("message", "管理者権限が必要です。ログインし直してください。");
    	    return "/views/login-in.jsp"; 
    	}

		// 1. 入力画面（Form）から送信されてきた商品情報をすべて取得
		String productName = request.getParameter("productName");
		String priceStr = request.getParameter("price");
		String stockStr = request.getParameter("stock");
		String volume = request.getParameter("volume");
		String origin = request.getParameter("origin");
		String description = request.getParameter("description");
		String imagePath = request.getParameter("imagePath");

		// パラメーター（隠しフィールドで送られてくる固定値）
		String sweetness = request.getParameter("sweetness");
		String sourness = request.getParameter("sourness");
		String berrySize = request.getParameter("berrySize");

		// 2. 取得した値を、次の「確認画面（JSP）」で表示・再利用するためにリクエスト属性にセット
		request.setAttribute("productName", productName);
		request.setAttribute("price", priceStr);
		request.setAttribute("stock", stockStr);
		request.setAttribute("volume", volume);
		request.setAttribute("origin", origin);
		request.setAttribute("description", description);
		request.setAttribute("imagePath", imagePath);

		request.setAttribute("sweetness", sweetness);
		request.setAttribute("sourness", sourness);
		request.setAttribute("berrySize", berrySize);

		// 3. 宛先：新しく作成する「商品登録確認画面」のJSPへフォワードします
		return "/views/admin-product-register-confirm.jsp";
	}
}