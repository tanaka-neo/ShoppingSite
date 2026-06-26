package jp.co.aforce.servlet;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.HistoryDAO;
import tool.Action;

public class AdminHistoryListAction extends Action {

	@Override
	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 🔐 セッションチェック（管理者かどうか）
		HttpSession session = request.getSession(false);
		Users user = (session != null) ? (Users) session.getAttribute("user") : null;

		// ログインしていない、または一般ユーザー（role != 1）ならログイン画面やメニューへ弾く
		if (user == null || user.getRole() != 1) {
			request.setAttribute("message", "管理者専用の機能です。ログインしてください。");
			return "/views/login-in.jsp";
		}

		// 📜 DAOを使って、すべての人・すべての商品の購入履歴を全件取得
		HistoryDAO dao = new HistoryDAO();
		List<Map<String, Object>> adminHistoryList = dao.findAllWithDetails();

		// 🎁 取得した全件リストを「adminHistoryList」という名前でJSP（画面）に引き渡す
		request.setAttribute("adminHistoryList", adminHistoryList);

		// 管理者用の購入履歴画面（JSP）へ進む
		return "/views/admin-history-list.jsp";
	}
}