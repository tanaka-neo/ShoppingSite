package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Product;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductDAO;
import tool.Action;

public class AdminProductUpdateInputAction extends Action {

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

		//編集ボタンから送られてきた商品IDを受け取る
		String productId = request.getParameter("productId");
		//DAOを使って、現在データベースに登録されている商品の詳細データを取得
		ProductDAO dao = new ProductDAO();
		Product product = dao.findById(productId);
		//商品データをリクエストにセットして商品入力画面へフォワード
		if (product != null) {
			request.setAttribute("product", product);
			return "/views/admin-product-update-input.jsp";
			// 商品が見つからなかった場合
		} else {
			request.setAttribute("message", "指定された商品が見つかりませんでした。");
			return "/views/admin-product-delete-complete.jsp"; // エラー表示用に完了画面を使い回し
		}
		
	}
}
