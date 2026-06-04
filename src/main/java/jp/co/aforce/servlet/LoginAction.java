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
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// ログイン成功時にユーザー情報を保存するためのセッションを取得
		HttpSession session = request.getSession();

		// 入力値を取得
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");

		// DBからユーザー情報を検索するDAOを生成
		UsersDAO dao = new UsersDAO();
		
		// 入力されたIDとパスワードでDB検索を行う。成功時はUsersオブジェクト、失敗はnullが返る
		Users dbUser = dao.search(memberId, password);

		// ログイン成功時
		if (dbUser != null) {

			// アプリケーションスコープ（サーバー全体で共有される領域）を取得
			ServletContext appScope = session.getServletContext();

			// ログイン中のユーザーIDとセッションのマップを取得
			@SuppressWarnings("unchecked")
			Map<String, HttpSession> loginUsersMap = (Map<String, HttpSession>) appScope.getAttribute("loginUsersMap");

			// 初回ログイン時などでマップが存在しない場合に新しく作成
			if (loginUsersMap == null) {
				loginUsersMap = new HashMap<String, HttpSession>();
				appScope.setAttribute("loginUsersMap", loginUsersMap);
			}

			// もし、すでに同じ会員IDがログインしていたら
			if (loginUsersMap.containsKey(memberId)) {
				// マップから古いデータを取り出しつつ消去する
				HttpSession oldSession = loginUsersMap.remove(memberId);
				
				// 過去のセッションが存在し、かつ「自分とは違う別ブラウザ」のときだけ爆破する
				if (oldSession != null && !oldSession.getId().equals(session.getId())) {
					try {
						// 古い別セッションを強制終了（キックアウト）させる！
						oldSession.invalidate();
						System.out.println("--- [ログ] 別の端末の古いセッションをキックアウトしました: " + memberId);
					} catch (IllegalStateException e) {
						// 既にセッションが切れている場合は無視してOK
					}
				}
			}

			// 今回新しくログインしたユーザーのIDと、現在のセッション本体をペアで登録する
			loginUsersMap.put(memberId, session);
			appScope.setAttribute("loginUsersMap", loginUsersMap);

			// セッションの引き出し"user"にDBから連れてきたユーザー情報保存
			session.setAttribute("user", dbUser);

			return "/views/user-menu.jsp";
		}

		// ログイン失敗時
		request.setAttribute("message", "IDまたはパスワードが違います");

		return "/views/login-error.jsp";
	}
}