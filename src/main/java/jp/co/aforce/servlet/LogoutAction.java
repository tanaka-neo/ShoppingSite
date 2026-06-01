package jp.co.aforce.servlet;

import java.util.Set;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import tool.Action;

public class LogoutAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//既存のセッションを取得（存在しなければ作らない）
		HttpSession session = request.getSession(false);

		//ログアウト処理
		if (session != null) {

			//セッションからログイン中のユーザー情報を獲得する
			Users users = (Users) session.getAttribute("users");
			//アプリケーションスコープの取得
			ServletContext application = session.getServletContext();

			//ログイン中一覧取得
			Set<String> loginUsers = (Set<String>) application.getAttribute("loginUsers");

			//ログイン中のユーザー一覧と、セッションのユーザー情報が両方存在する場合のみ処理
			if (loginUsers != null && users != null) {

				loginUsers.remove(users.getMemberId());
				//更新したログイン中ユーザー一覧をアプリケーションスコープに再保存する。
				application.setAttribute("loginUsers", loginUsers);
			}
			//セッションを破棄
			session.invalidate();
		}

		return "/views/login-in.jsp";
	}
}