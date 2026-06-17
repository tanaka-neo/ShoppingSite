package jp.co.aforce.servlet;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.Product;
import jp.co.aforce.dao.ProductDAO;
import tool.Action;

public class CartAddAction extends Action {

	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			return "/views/login-in.jsp";
		}

		//商品詳細画面から送信された商品IDを取得
		//hiddenタグで渡された値を受け取る
		String productId = request.getParameter("productId");
		// ProductDAOを生成
		ProductDAO dao = new ProductDAO();
		// 商品IDをもとにDBから商品情報を取得
		// 戻り値はProductオブジェクト
		Product product = dao.findById(productId);

		// セッションから現在のカート情報を取得
		// "cart"という名前で保存していたList<CartItem>を取り出す
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		// 初めてカートを利用する場合
		// セッションにcartが存在しないためnullになる
		if (cart == null) {
			//	 空のカートを作成
			cart = new ArrayList<CartItem>();
		}

		// 商品詳細画面で選択された数量を取得
		// request.getParameterはString型で取得するため、int型へ変換
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		// 同じ商品がカート内に存在するか確認するフラグ
		boolean exists = false;

		// カート内の商品を順番に確認
		for (CartItem item : cart) {

			// 同じ商品IDなら数量を加算
			if (item.getProduct().getProductId().equals(productId)) {
			
				item.setQuantity(item.getQuantity() + quantity);

				exists = true;
				break;
			}
		}

		if (!exists) {
			// カートへ追加する商品情報を格納するBeanを生成
			CartItem item = new CartItem();
			// CartItemに商品情報をセット、Productオブジェクトを（商品名、価格など）を保持
			item.setProduct(product);

			// 選択した購入数量をCartItemへセット
			item.setQuantity(quantity);
			// 作成したCartItemをカートへ追加
			cart.add(item);
		}

		// 更新したカート情報をセッションへ保存
		// この処理によって画面遷移後もカート情報が保持される
		session.setAttribute("cart", cart);
		// カート画面へ遷移
		 response.sendRedirect("CartList.action");
		    return null;
	}
}