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
		//ログイン成功時にユーザー情報を保存するためのセッションを取得
		HttpSession session = request.getSession();

		//セッションの引き出し"user”から確認画面で預けた入力データを取得
		Users sessionUser = (Users) session.getAttribute("user");

		// セッションの中身がnullなら入力画面へ戻す
		if (sessionUser == null) {
			return "/views/register.jsp";
		}

		// DAO作成
		UsersDAO dao = new UsersDAO();

		// 入力されたIDが重複していないか検索する
		Users duplicateUser = dao.search(sessionUser.getMemberId());
		//重複している場合
		if (duplicateUser != null) {
			request.setAttribute("message", "このIDは既に使われています");
			return "/views/login-error.jsp";
		}

		//データベースへの登録（insert）を実行し登録できた件数を取得
		int insertCount = dao.insert(sessionUser);

		// 【後片付け】DB登録が済んだので、一時的にセッションに預けていた入力データを削除する
		// （ブラウザの戻るボタンによる二重登録の防止や、メモリのお掃除のため）
		session.removeAttribute("user");

		//　登録が成功（1件登録）した場合
		if (insertCount > 0) {
			return "/views/register-success.jsp";
		} else {
			//	DB側の制約（桁数オーバー、データ重複、接続切れなど）で登録できなかった場合
			request.setAttribute("message", "登録に失敗しました");
			return "/views/login-error.jsp";
		}
	}
}