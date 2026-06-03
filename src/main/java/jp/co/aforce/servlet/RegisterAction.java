package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import tool.Action;

public class RegisterAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		Users user = (Users) session.getAttribute("user");

		if (user == null) {
			return "/views/register.jsp";
		}

		UsersDAO dao = new UsersDAO();

		// 重複チェック
		Users existing = dao.search(user.getMemberId());

		if (existing != null) {
			request.setAttribute("message", "このIDは既に使われています");
			return "/views/login-error.jsp";
		}

		int line = dao.insert(user);

		// 登録後セッション削除
//		桁数オーバーや接続が切れたときに出る
		session.removeAttribute("user");

		if (line > 0) {
			return "/views/register-success.jsp";
		} else {
			request.setAttribute("message", "登録に失敗しました");
			return "/views/login-error.jsp";
		}
	}
}