package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import tool.Action;

public class DeleteAction extends Action {

    public String execute(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

//    	既にログインしているセッションのみ取得
    	HttpSession session = request.getSession(false);
    	
    	// セッションがない、またはユーザー情報がない不正アクセスに対してログイン画面へ飛ばす
    	if (session == null || session.getAttribute("user") == null) {
    	    return "/views/login-in.jsp";
    	}
  
        Users user =
                (Users) session.getAttribute("user");

//        DAOを呼び出し、deleteメソッドを実行
        UsersDAO dao = new UsersDAO();
        dao.delete(user.getMemberId());

//        セッションの破棄
        session.invalidate();

        return "/views/login-in.jsp";
    }
}