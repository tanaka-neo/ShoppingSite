package jp.co.aforce.servlet;

import java.util.HashSet;
import java.util.Set;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import tool.Action;

public class LoginAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ログイン成功時にユーザー情報を保存するためのセッションを取得
		HttpSession session = request.getSession();
		//入力値を取得
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		// DBからユーザー情報を検索するDAOを生成
		UsersDAO dao = new UsersDAO();
		//入力されたIDとパスワードでDB検索を行う。成功時はUsersオブジェクト、失敗はnullが返る
		Users user = dao.search(memberId, password);

		//ログイン成功時
		if (user != null) {

			//アプリケーションスコープ（サーバー全体で共有される領域）を取得する。これは「ログイン中ユーザー一覧」を管理するために使用する。
			ServletContext application = session.getServletContext();

			//ログイン中のユーザーID一覧を取得。ない場合はNULL 
			Set<String> loginUsers = (Set<String>) application.getAttribute("loginUsers");

			//初回ログイン時などでリストが存在しない場合に新しくSetする
			if (loginUsers == null) {
				loginUsers = new HashSet<String>();
				application.setAttribute("loginUsers", loginUsers);
			}

			//すでにログイン中かのチェック。別ブラウザからのログインもここで判断する。
			if (loginUsers.contains(memberId)) {
				request.setAttribute(
						
						"message",
						"このユーザーは既にログイン中です");
				return "/views/login-error.jsp";
			}

			// ログイン登録
			loginUsers.add(memberId);

			//更新したログイン中ユーザー情報をアプリケーションスコープ（サーバー全体で共有される領域）に保存する
			application.setAttribute("loginUsers", loginUsers);

			//セッションにユーザー情報保存
			session.setAttribute("user", user);

			return "/views/user-menu.jsp";
		}

		//ログイン失敗
		request.setAttribute(
				"message",
				"IDまたはパスワードが違います");

		return "/views/login-error.jsp";
	}
}