package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Product;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductDAO;
import tool.Action;

public class AdminProductDeleteConfirmAction extends Action {

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

        // 2. 一覧画面から送られてきた商品IDを受け取る
        String productId = request.getParameter("productId");

        // 3. すでにDAOにある「findById」メソッドを使って、削除対象の商品の詳細データを取得
        ProductDAO dao = new ProductDAO();
        Product product = dao.findById(productId);

        // 4. 商品データをリクエスト属性にセットして確認画面へ引き渡す
        if (product != null) {
            request.setAttribute("product", product);
            return "/views/admin-product-delete-confirm.jsp"; // 
        } else {
            request.setAttribute("message", "指定された商品が見つかりませんでした。");
            return "/views/admin-product-delete-complete.jsp"; // エラー時もメッセージを出して完了画面へ
        }
    }
}