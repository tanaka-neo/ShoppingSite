package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import tool.Action;

public class LoginAction extends Action{
	public String execute(
			HttpServletRequest request,HttpServletResponse response
			) throws Exception {
		
		HttpSession session=request.getSession();
		String memberId=request.getParameter("memberId");
		String password=request.getParameter("password");
		UsersDAO dao=new UsersDAO();
		Users users=dao.search(memberId,password);
		
		if(users!=null) {
			session.setAttribute("users",users);
			return "/views/user-menu.jsp";
		}
		return"/views/login-error.jsp";
	}

}
