package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import tool.Action;

public class UpdateAction extends Action {

    public String execute(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

//    	既存のセッションを取得
    	HttpSession session = request.getSession(false);
        // セッションが無い、またはログインしていなければ処理を通さない
        if (session == null || session.getAttribute("user") == null) {
            return "/views/login-in.jsp"; // ログイン画面へ戻す
        }
        
//        画面の入力フォームから送られてきた変更後の値を受け取る
        Users user = new Users();

        user.setMemberId(request.getParameter("memberId"));
        user.setPassword(request.getParameter("password"));
        user.setLastName(request.getParameter("lastName"));
        user.setFirstName(request.getParameter("firstName"));
        user.setAddress(request.getParameter("address"));
        user.setMailAddress(request.getParameter("mailAddress"));

        UsersDAO dao = new UsersDAO();
//データベースの情報を新しい値に上書き更新（SQLのUPDATE）
        int line = dao.update(user);

//        データベースの更新が成功（1件以上変更）したとき
        if (line > 0) {
//サーバーのセッション記憶も最新のデータに同期上書きする
            request.getSession()
                   .setAttribute("user", user);

            return "/views/user-menu.jsp";
        }
//更新に失敗したら修正画面に残す
        return "/views/update.jsp";
    }
}