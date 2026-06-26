package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Product;
import jp.co.aforce.beans.Users;
import tool.Action;

public class AdminProductUpdateConfirmAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession(false);
    	Users user = (session != null) ? (Users) session.getAttribute("user") : null;

    	//ログインしていない、または、管理者（role==1）じゃないならはじく
    	if (user == null || user.getRole() != 1) {
    	    request.setAttribute("message", "管理者権限が必要です。ログインし直してください。");
    	    return "/views/login-in.jsp"; 
    	}

        // 2. 編集入力画面（JSP）のフォームから送られてきた「新しい値」をすべて受け取る
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String description = request.getParameter("description");
        String imagePath = request.getParameter("imagePath");
        int sweetness = Integer.parseInt(request.getParameter("sweetness"));
        int sourness = Integer.parseInt(request.getParameter("sourness"));
        int berrySize = Integer.parseInt(request.getParameter("berrySize"));
        String origin = request.getParameter("origin");
        String volume = request.getParameter("volume");

        // 3. 受け取ったバラバラのデータを、1つの Product Beanオブジェクトに綺麗に詰め込む
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setPrice(price);
        product.setStock(stock);
        product.setDescription(description);
        product.setImagePath(imagePath);
        product.setSweetness(sweetness);
        product.setSourness(sourness);
        product.setBerrySize(berrySize);
        product.setOrigin(origin);
        product.setVolume(volume);

        // 4. 新しいデータが詰まったBeanを、確認画面で表示するためにリクエスト属性にセット
        request.setAttribute("product", product);

        // 5. 編集確認画面（JSP）へバトンタッチして画面を表示するにょん！
        return "/views/admin-product-update-confirm.jsp";
    }
}