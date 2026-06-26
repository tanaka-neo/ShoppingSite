package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Product;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductDAO;
import tool.Action;

public class AdminProductUpdateAction extends Action {

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

        // 2. 確認画面(JSP)の hidden タグから送られてきた「確定データ」をすべて回収！
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

        // 3. 回収したデータを、DAOに渡すために再び Product Bean にパッケージング！
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

        // 4. いよいよDAOの出番！updateメソッドを呼び出してデータベースを書き換える
        ProductDAO dao = new ProductDAO();
        int result = dao.update(product); // 🍓 戻り値には「更新成功した行数(通常は1)」が返ってきます

        // 5. データベースの更新結果に応じて、完了画面へ渡すメッセージを切り替える
        if (result > 0) {
            // ⭕️ 更新成功！
            request.setAttribute("message", "商品「" + productName + "」の情報を正常に変更しました。");
        } else {
            // ❌ 更新失敗（対象の商品がすでにない場合など）
            request.setAttribute("message", "エラー：商品の変更に失敗しました。データが存在しない可能性があります。");
        }

        // 6. 最後に、新しく作成する「編集完了画面（JSP）」へフォワードする
        return "/views/admin-product-update-complete.jsp";
    }
}
