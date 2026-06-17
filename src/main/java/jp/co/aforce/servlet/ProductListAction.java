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

        // ProductDAOを生成
        ProductDAO dao = new ProductDAO();

        // 商品一覧を取得
        List<Product> list = dao.searchAll();

        
        // JSPへ渡す
        request.setAttribute("list", list);

        // 遷移先
        return "/views/product-list.jsp";
    }
}