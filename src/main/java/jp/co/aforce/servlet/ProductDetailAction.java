package jp.co.aforce.servlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Product;
import jp.co.aforce.dao.ProductDAO;
import tool.Action;

public class ProductDetailAction extends Action {

	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// URLから商品ID取得
		String productId = request.getParameter("productId");

		// ★追加：sortも受け取る
		String sort = request.getParameter("sort");

		ProductDAO dao = new ProductDAO();

		// 一件取得
		Product product = dao.findById(productId);

		// JSPへ渡す
		request.setAttribute("product", product);

		// ★追加：sortもJSPへ渡す
		request.setAttribute("sort", sort);

		return "/views/product-detail.jsp";
	}
}