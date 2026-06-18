package jp.co.aforce.servlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductDAO;
import tool.Action;

public class AdminProductDeleteCompleteAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // 1. セッションからユーザー情報を取得して、管理者チェック
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        
        if (user == null || user.getRole() != 1) {
            return "/views/login-error.jsp"; // 管理者じゃなければエラー画面へ
        }

        // 2. 画面から「どの商品を消したいか」の 商品ID（productId）を受け取る
        String productId = request.getParameter("productId");

        // 3. DAOを呼び出して、is_deleted を 1 に更新する
        ProductDAO dao = new ProductDAO();
        int result = dao.delete(productId);

        // 4. 結果に応じてメッセージをセット
        if (result > 0) {
            request.setAttribute("message", "商品を非表示（削除）にしました。");
        } else {
            request.setAttribute("message", "商品の削除に失敗しました。");
        }

       
        return "/views/admin-product-delete-complete.jsp";
    }
}