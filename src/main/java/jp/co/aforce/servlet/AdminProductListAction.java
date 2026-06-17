package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Product;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductDAO;
import tool.Action;

public class AdminProductListAction extends Action {

	@Override
	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");

		//管理者以外はアクセス禁止
		if (user == null || user.getRole() != 1) {
			return "/views/login-error.jsp";
		}

		//商品一覧
		ProductDAO dao = new ProductDAO();
		List<Product> list = dao.searchAll();

		//JSPへ渡す
		request.setAttribute("list", list);

		//管理者用商品一覧へ
		return "/views/admin-productlist.jsp";
	}
}