package jp.co.aforce.servlet;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import tool.Action;

public class UpdateConfirmAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);
		Users user = (session != null) ? (Users) session.getAttribute("user") : null;
		//ログインしていない（ゲスト状態）なら、ログイン画面へ
		if (user == null) {
		    request.setAttribute("message", "この機能を利用するにはログインが必要です。");
		    return "/views/login-in.jsp"; 
		}

		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String address = request.getParameter("address");
		String mailAddress = request.getParameter("mailAddress");

		Users inputUser = new Users();
		inputUser.setMemberId(memberId);
		inputUser.setPassword(password);
		inputUser.setLastName(lastName);
		inputUser.setFirstName(firstName);
		inputUser.setAddress(address);
		inputUser.setMailAddress(mailAddress);

		List<String> errorList = new ArrayList<>();

		// 1. 必須（未入力）チェック
		if (memberId == null || memberId.isBlank() || password == null || password.isBlank() ||
				lastName == null || lastName.isBlank() || firstName == null || firstName.isBlank() ||
				address == null || address.isBlank() || mailAddress == null || mailAddress.isBlank()) {
			errorList.add("未入力の項目があります。");
		}

		// 必須チェックがOKなら詳細チェック
		if (errorList.isEmpty()) {
			// 2. パスワード形式チェック
			if (!password.matches("^[a-zA-Z0-9]+$")) {
				errorList.add("パスワードは半角英数字で入力してください。");
			}
			// 3. 文字数（長さ）制限チェック
			if (password.length() < 8 || password.length() > 32) {
				errorList.add("パスワードは8文字以上32文字以内で入力してください。");
			}
			if (lastName.length() > 32 || firstName.length() > 32) {
				errorList.add("お名前（名字・名前）はそれぞれ32文字以内で入力してください。");
			}
			if (address.length() > 128) {
				errorList.add("住所は100文字以内で入力してください。");
			}
			// 4. メールアドレスの形式チェック（不正文字ブロック）
			String emailPattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
			if (!mailAddress.matches(emailPattern)) {
				errorList.add("メールアドレスの形式が正しくありません。");
			}
		}

		// エラーがある場合は修正画面に戻す
		if (!errorList.isEmpty()) {
			request.setAttribute("errors", errorList);
			request.setAttribute("formUser", inputUser); // 入力内容を保持して戻す
			return "/views/update.jsp";
		}

		// 正常なら確認画面へ進む
		request.getSession().setAttribute("updateUser", inputUser);

		return "/views/update-confirm.jsp";
	}
}