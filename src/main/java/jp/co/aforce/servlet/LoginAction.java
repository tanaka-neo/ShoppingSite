package jp.co.aforce.servlet;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
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

        return "/views/user-menu.jsp";
    }
}