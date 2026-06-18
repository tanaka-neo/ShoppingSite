package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Product;
import jp.co.aforce.dao.ProductDAO;
import tool.Action;

public class ProductListAction extends Action {

	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//画面の検索フォームから送られてきたパラメータを取得
		String keyword = request.getParameter("keyword");
		String sort = request.getParameter("sort");

		// データベース操作用のDAOインスタンス（ProductDAO）を生成
		ProductDAO dao = new ProductDAO();

		// 画面から受け取ったキーワードと並び替え条件を引数として渡し、絞り込んだ商品一覧を取得
		List<Product> list = dao.search(keyword, sort);

		// 取得した商品一覧(list)をリクエスト属性にセットしてJSPへ渡す
		request.setAttribute("list", list);
		//検索を実行した後も画面に文字や選択状態を維持させる（消えないようにする）ため、
		//受け取ったkeywordとsortをそのままリクエスト属性に乗せてJSPに送りかえす
		request.setAttribute("keyword", keyword);
		request.setAttribute("sort", sort);
		// 遷移先
		return "/views/product-list.jsp";
	}
}