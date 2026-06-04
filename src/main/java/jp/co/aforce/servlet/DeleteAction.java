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

		// 既にログインしているセッションのみ取得
		HttpSession session = request.getSession(false);
		// セッションが無い、またはログインしていなければ処理を通さない
		if (session == null || session.getAttribute("user") == null) {
			return "/views/login-in.jsp";
		}

		// セッションから現在ログインしているユーザーの情報を取得（誰が操作しているかを特定するため）
		Users loginUser = (Users) session.getAttribute("user");

		//  DAOを呼び出し、sqlのdeleteメソッドを実行
		UsersDAO dao = new UsersDAO();
		dao.delete(loginUser.getMemberId());

		//  セッションの破棄
		session.invalidate();

		return "/views/login-in.jsp";
	}
}