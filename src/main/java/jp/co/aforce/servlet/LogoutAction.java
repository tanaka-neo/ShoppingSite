package jp.co.aforce.servlet;

import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import tool.Action;

public class LogoutAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(false);

        if (session != null) {
            Users user = (Users) session.getAttribute("user");
            
            if (user != null) {
                ServletContext appScope = session.getServletContext();
                @SuppressWarnings("unchecked")
                Map<String, HttpSession> loginUsersMap = (Map<String, HttpSession>) appScope.getAttribute("loginUsersMap");
                
                // ★ログイン時と同じマップから、自分のユーザーIDのデータを消去する
                if (loginUsersMap != null) {
                    loginUsersMap.remove(user.getMemberId());
                }
            }
            // セッションを完全に無効化
            session.invalidate();
        }

        return "/views/login-in.jsp";
    }
}