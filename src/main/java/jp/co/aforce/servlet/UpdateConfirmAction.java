package jp.co.aforce.servlet;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import tool.Action;

public class UpdateConfirmAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 変更画面の入力フォームから送信された値を取得（IDは変更不可の場合が多いですが一応取得）
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String address = request.getParameter("address");
		String mailAddress = request.getParameter("mailAddress");

		List<String> errorList = new ArrayList<>();

		// 1. 未入力チェック
		if (memberId == null || memberId.isBlank()
				|| password == null || password.isBlank()
				|| lastName == null || lastName.isBlank()
				|| firstName == null || firstName.isBlank()
				|| address == null || address.isBlank()
				|| mailAddress == null || mailAddress.isBlank()) {
			
			errorList.add("未入力の項目があります");
		}

		// 2. 正規表現チェック（
		if (errorList.isEmpty()) {
			String alphanumericPattern = "^[a-zA-Z0-9]+$";

			if (!password.matches(alphanumericPattern)) {
				errorList.add("パスワードは半角英数字で入力してください");
			}
		}

		// 3. エラーがあれば、変更入力画面に戻す
		if (!errorList.isEmpty()) {
			String fullMessage = String.join("<br>", errorList);
			request.setAttribute("message", fullMessage);

			// 入力値を保持して戻す
			Users inputUser = new Users();
			inputUser.setMemberId(memberId);
			inputUser.setPassword(password);
			inputUser.setLastName(lastName);
			inputUser.setFirstName(firstName);
			inputUser.setAddress(address);
			inputUser.setMailAddress(mailAddress);
			
			HttpSession session = request.getSession();
			session.setAttribute("updateUser", inputUser); // 更新用はupdateUserという名前に

			return "/views/update.jsp"; 
		}

		// 4. 正常ならセッションに保存して、更新確認画面へ進む
		Users confirmedUser = new Users();
		confirmedUser.setMemberId(memberId);
		confirmedUser.setPassword(password);
		confirmedUser.setLastName(lastName);
		confirmedUser.setFirstName(firstName);
		confirmedUser.setAddress(address);
		confirmedUser.setMailAddress(mailAddress);

		HttpSession session = request.getSession();
		session.setAttribute("updateUser", confirmedUser);
		
		return "/views/profile-update-confirm.jsp"; 
	}
}