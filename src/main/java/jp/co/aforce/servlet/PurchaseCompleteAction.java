package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.HistoryDAO;
import jp.co.aforce.dao.ProductDAO;
import tool.Action;

public class PurchaseCompleteAction extends Action {

	@SuppressWarnings("unchecked") // カートの型変換（キャスト）の警告を消すお守り
	@Override
	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		Users user = (session != null) ? (Users) session.getAttribute("user") : null;

		// ログインしていない（ゲスト状態）なら、ログイン画面へ
		if (user == null) {
			request.setAttribute("message", "この機能を利用するにはログインが必要です。");
			return "/views/login-in.jsp"; 
		}
		
		// 🛒 セッションから「カートのリスト」を取り出す
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		
		if (cart != null && !cart.isEmpty()) {
			
			ProductDAO productDao = new ProductDAO();
			HistoryDAO historyDao = new HistoryDAO();
			
			// ==========================================
			// ✨ 【新設】ステップ1：まずはカート内すべての商品の在庫チェックだけを行う
			// ==========================================
			for (CartItem item : cart) {
				String productId = item.getProduct().getProductId();
				int quantity = item.getQuantity(); // ユーザーが買おうとしている数
				
				// DBから「現在のリアルタイムな在庫数」を取得
				int currentStock = productDao.getStock(productId);
				
				// もし在庫が足りない商品が1つでもあれば、その時点で処理を中断してカート画面に戻す！
				if (currentStock < quantity) {
					
					request.setAttribute("cart", cart);
					
					request.setAttribute("errorMessage", 
						"申し訳ありません。「" + item.getProduct().getProductName() + "」の在庫が足りないため、注文を確定できませんでした。 " +
						"(現在の在庫: " + currentStock + "個 / ご希望数量: " + quantity + "個)");
			
					// 購入完了させずに、カート画面（または注文確認画面）のJSPへ戻す
					return "/views/cart.jsp"; 
				}
			}
			
			// ==========================================
			// 🚀 ステップ2：すべての商品の在庫が足りていることが確定したら、一気に購入処理を行う
			// ==========================================
			String memberId = user.getMemberId();
			
			for (CartItem item : cart) {
				String productId = item.getProduct().getProductId();
				int quantity = item.getQuantity();
				
				// ① 【購入履歴】履歴テーブルに保存
				historyDao.insertHistory(memberId, productId, quantity);
				
				// ② 【在庫管理】PRODUCTテーブルの在庫を引く（DAOに既存のメソッド）
				productDao.reduceStock(productId, quantity);
			}
		}
		
		// すべての商品の履歴保存と在庫引き算が終わったら、カートを空にする
		session.removeAttribute("cart");

		// 購入完了画面へ
		return "/views/purchase-complete.jsp";
	}
}