package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import tool.Action;

public class RegisterConfirmAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 入力値取得
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String address = request.getParameter("address");
		String mailAddress = request.getParameter("mailAddress");

		// 未入力チェック
		if (memberId == null || memberId.isBlank()
				|| password == null || password.isBlank()
				|| lastName == null || lastName.isBlank()
				|| firstName == null || firstName.isBlank()
				|| address == null || address.isBlank()
				|| mailAddress == null || mailAddress.isBlank()) {

			// エラーメッセージを設定
			request.setAttribute("message", "未入力の項目があります");

			// 入力された値を保持して入力画面に戻すための準備
			Users user = new Users();
			user.setMemberId(memberId);
			user.setPassword(password);
			user.setLastName(lastName);
			user.setFirstName(firstName);
			user.setAddress(address);
			user.setMailAddress(mailAddress);

			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			// エラー時は入力画面に戻す
			return "/views/register.jsp";
		}

		// 全ての入力が正常だった場合は確認画面へ進む
		Users user = new Users();
		user.setMemberId(memberId);
		user.setPassword(password);
		user.setLastName(lastName);
		user.setFirstName(firstName);
		user.setAddress(address);
		user.setMailAddress(mailAddress);

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		return "/views/register-confirm.jsp";
	}
}