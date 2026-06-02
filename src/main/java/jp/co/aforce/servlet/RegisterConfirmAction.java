package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import tool.Action;

public class RegisterConfirmAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Users users = new Users();
		
		users.setMemberId(request.getParameter("memberId"));
		users.setPassword(request.getParameter("password"));
		users.setLastName(request.getParameter("lastName"));
		users.setFirstName(request.getParameter("firstName"));
		users.setAddress(request.getParameter("address"));
		users.setMailAddress(request.getParameter("mailAddress"));

		HttpSession session = request.getSession();
		request.setAttribute("user", users);

		
		return "/views/register-confirm.jsp";
	}
}