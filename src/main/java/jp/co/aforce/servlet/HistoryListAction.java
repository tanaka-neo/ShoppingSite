package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.History;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.HistoryDAO;
import tool.Action;

public class HistoryListAction extends Action {

	@Override
	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);
		Users user = (session != null) ? (Users) session.getAttribute("user") : null;

		// ログインしていない（ゲスト状態）ならログイン画面へ
		if (user == null) {
			request.setAttribute("message", "購入履歴を見るにはログインが必要です。");
			return "/views/login-in.jsp";
		}

		// ログイン中のユーザーIDを取得
		String memberId = user.getMemberId();

		// DAOを使って、このユーザーの購入履歴をデータベースから全件取得
		HistoryDAO dao = new HistoryDAO();
		List<History> historyList = dao.findByMemberId(memberId);

		//取得したリストを「historyList」という名前でJSP（画面）に引き渡す
		request.setAttribute("historyList", historyList);

		// 購入履歴画面（JSP）へ進む
		return "/views/history-list.jsp";
	}
}
