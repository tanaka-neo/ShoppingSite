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

		Users users = (Users) session.getAttribute("user");

		if (users == null) {
			return "/register.jsp";
		}

		UsersDAO dao = new UsersDAO();

		// 重複チェック
		Users existing = dao.search(users.getMemberId());

		if (existing != null) {
			request.setAttribute("error", "このIDは既に使われています");
			return "/register.jsp";
		}

		int line = dao.insert(users);

		// 登録後セッション削除
		session.removeAttribute("user");

		if (line > 0) {
			return "/views/register-success.jsp";
		} else {
			request.setAttribute("error", "登録に失敗しました");
			return "/register.jsp";
		}
	}
}