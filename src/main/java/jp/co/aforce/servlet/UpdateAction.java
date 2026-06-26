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

		HttpSession session = request.getSession(false);
		Users user = (session != null) ? (Users) session.getAttribute("user") : null;

		//ログインしていない（ゲスト状態）なら、ログイン画面へ
		if (user == null) {
		    request.setAttribute("message", "この機能を利用するにはログインが必要です。");
		    return "/views/login-in.jsp"; 
		}

		// 画面の入力フォームから送られてきた変更後の値を受け取る
		Users updatedUser = new Users();

		updatedUser.setMemberId(request.getParameter("memberId"));
		updatedUser.setPassword(request.getParameter("password"));
		updatedUser.setLastName(request.getParameter("lastName"));
		updatedUser.setFirstName(request.getParameter("firstName"));
		updatedUser.setAddress(request.getParameter("address"));
		updatedUser.setMailAddress(request.getParameter("mailAddress"));

		UsersDAO dao = new UsersDAO();
		//データベースの情報を新しい値に上書き更新（SQLのUPDATE）
		int line = dao.update(updatedUser);

		// データベースの更新が成功（1件以上変更）したとき
		if (line > 0) {
			//サーバーのセッション記憶も最新のデータに同期上書きする
			request.getSession()
					.setAttribute("user", updatedUser);

			return "/views/user-menu.jsp";
		}
		//更新に失敗したら修正画面に残す
		return "/views/update.jsp";
	}
}