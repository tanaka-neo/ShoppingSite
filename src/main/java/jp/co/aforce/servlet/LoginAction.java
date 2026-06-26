package jp.co.aforce.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.Product;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.dao.UsersDAO;
import tool.Action;

public class LoginAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String memberId = request.getParameter("memberId");
        String password = request.getParameter("password");

        UsersDAO dao = new UsersDAO();
        Users dbUser = dao.search(memberId, password);

        // ログイン失敗時
        if (dbUser == null) {
            request.setAttribute("message", "IDまたはパスワードが違います");
            return "/views/login-error.jsp";
        }

        // ログイン成功時
        HttpSession session = request.getSession();
        ServletContext appScope = session.getServletContext();

        // アプリケーションスコープからログイン中マップを取得（なければ作成）
        @SuppressWarnings("unchecked")
        Map<String, HttpSession> loginUsersMap = (Map<String, HttpSession>) appScope.getAttribute("loginUsersMap");
        if (loginUsersMap == null) {
            loginUsersMap = new HashMap<>();
            appScope.setAttribute("loginUsersMap", loginUsersMap);
        }

        // 【二重ログイン防止】すでに同じIDが別の場所でログインしていたら古い方をキックアウト
        if (loginUsersMap.containsKey(memberId)) {
            HttpSession oldSession = loginUsersMap.remove(memberId);
            if (oldSession != null && !oldSession.getId().equals(session.getId())) {
                try {
                    oldSession.invalidate(); // 古いセッションを破棄
                    System.out.println("--- [キックアウト] 別端末の古いセッションを破棄しました: " + memberId);
                } catch (IllegalStateException e) {
                    // 既に切れている場合は無視
                }
            }
        }

        // 今回の新しいセッションをマップに登録して、ユーザー情報を保持
        loginUsersMap.put(memberId, session);
        session.setAttribute("user", dbUser);
     // ユーザー情報をセッションへ保存
        session.setAttribute("user", dbUser);

        // 保留中の商品を取得
        String pendingProductId =
            (String) session.getAttribute("pendingProductId");

        String pendingQuantity =
            (String) session.getAttribute("pendingQuantity");

        if (pendingProductId != null) {

            ProductDAO productDao = new ProductDAO();
            Product product = productDao.findById(pendingProductId);

            @SuppressWarnings("unchecked")
            List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

            if (cart == null) {
                cart = new ArrayList<>();
            }

            int quantity = Integer.parseInt(pendingQuantity);

            boolean exists = false;

            for (CartItem item : cart) {
                if (item.getProduct().getProductId()
                        .equals(pendingProductId)) {

                    item.setQuantity(
                        item.getQuantity() + quantity);

                    exists = true;
                    break;
                }
            }

            if (!exists) {
                CartItem item = new CartItem();
                item.setProduct(product);
                item.setQuantity(quantity);
                cart.add(item);
            }

            session.setAttribute("cart", cart);

            session.removeAttribute("pendingProductId");
            session.removeAttribute("pendingQuantity");

            return "/CartList.action";
        }
  
        if (dbUser.getRole() == 1) {
            return "/AdminProductList.action";
        }

        return "/views/user-menu.jsp";
    }
}