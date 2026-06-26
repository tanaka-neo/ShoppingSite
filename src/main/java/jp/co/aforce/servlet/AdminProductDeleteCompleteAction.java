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

    	HttpSession session = request.getSession(false);
    	Users user = (session != null) ? (Users) session.getAttribute("user") : null;

    	//ログインしていない、または、管理者（role==1）じゃないならはじく
    	if (user == null || user.getRole() != 1) {
    	    request.setAttribute("message", "管理者権限が必要です。ログインし直してください。");
    	    return "/views/login-in.jsp"; 
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