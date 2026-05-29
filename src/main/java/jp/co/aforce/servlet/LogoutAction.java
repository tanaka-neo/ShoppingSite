package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}


		return "/views/login-in.jsp";
		

	}
}