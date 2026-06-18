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

        // 1. セッションからユーザー情報を取得して、管理者チェック！
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        
        if (user == null || user.getRole() != 1) {
            return "/views/login-error.jsp";
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