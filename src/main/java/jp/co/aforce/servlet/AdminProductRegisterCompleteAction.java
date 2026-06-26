package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Product;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductDAO;
import tool.Action;

public class AdminProductRegisterCompleteAction extends Action {

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

        // 1. 確認画面の隠しフィールド（hidden）から送信されたデータを取得
        // ※もし商品ID（productId）も画面から送る場合はここを有効にしてください
        String productId = request.getParameter("productId"); 
        String productName = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String description = request.getParameter("description");
        String imagePath = request.getParameter("imagePath");
        
        
        System.out.println("--- [デバッグ] 完了画面が受け取った商品名: " + productName);
        System.out.println("--- [デバッグ] 完了画面が受け取った画像パス: " + imagePath);
        
        
        
        
        // 隠しフィールドの固定パラメーター
        int sweetness = Integer.parseInt(request.getParameter("sweetness"));
        int sourness = Integer.parseInt(request.getParameter("sourness"));
        int berrySize = Integer.parseInt(request.getParameter("berrySize"));
        String origin = request.getParameter("origin");
        String volume = request.getParameter("volume");
        // 2. データを1つにまとめるため、Product型（Bean）のオブジェクトを作成してセット
        Product product = new Product();
        
//        ここで商品ID（product_id）を自動生成してセット
//        P + 現在時刻のミリ秒（13桁の数字）を組み合わせる
        String generatedId = "P" + System.currentTimeMillis(); 
        product.setProductId(generatedId); 
        
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

        //  DAOを呼び出してデータベースに保存を実行
        ProductDAO dao = new ProductDAO();
        int result = dao.insert(product);

        // 実行結果に応じて、画面に表示するメッセージを切り替える
        if (result > 0) {
            request.setAttribute("message", "商品の登録が正常に完了しました。");
        } else {
            request.setAttribute("message", "商品の登録に失敗しました。もう一度やり直してください。");
        }

        // 宛先：新しく作成する「登録完了画面（JSP）」へフォワード
        return "/views/admin-product-register-complete.jsp";
    }
}