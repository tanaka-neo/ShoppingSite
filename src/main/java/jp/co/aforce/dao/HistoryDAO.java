package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO extends DAO {

	// 購入履歴を新しく登録するメソッド
	public int insertHistory(String memberId, String productId, int quantity) throws Exception {
		// Tomcatに登録されたjdbc/shoppingsite_tanakaneoという設定からDBへ接続
		Connection con = getConnection();

		// PURCHASE_DATEはMySQLが自動で今の日時を入れる（DEFAULT CURRENT_TIMESTAMP）ので、指定しなくてOK！
		String sql = "INSERT INTO purchase_history (MEMBER_ID, PRODUCT_ID, QUANTITY) VALUES (?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);

		// SQL文の「？」の部分に、引数で受け取ったデータを順番にセット
		st.setString(1, memberId);
		st.setString(2, productId);
		st.setInt(3, quantity);

		// SQLを実行し、追加された行数を取得
		int line = st.executeUpdate();

		// SQL実行オブジェクトとDB接続を閉じる
		st.close();
		con.close();

		// 追加された行数を返す（成功なら1）
		return line;
	}
	// 👤 ログイン中のユーザーの購入履歴を一括で取得するメソッド（商品名付き！）
		public List<jp.co.aforce.beans.History> findByMemberId(String memberId) throws Exception {
			List<jp.co.aforce.beans.History> list = new ArrayList<>();
			Connection con = getConnection();

			// 💡SQLポイント：INNER JOINを使って、購入履歴(h)と商品テーブル(p)を商品IDでガッチャンコ！
			// これで、商品名（PRODUCT_NAME）も一緒にセットで取得できるようになります
			String sql = "SELECT h.HISTORY_ID, h.MEMBER_ID, h.PRODUCT_ID, p.PRODUCT_NAME, h.QUANTITY, h.PURCHASE_DATE " +
			             "FROM purchase_history h " +
			             "INNER JOIN product p ON h.PRODUCT_ID = p.PRODUCT_ID " +
			             "WHERE h.MEMBER_ID = ? " +
			             "ORDER BY h.PURCHASE_DATE DESC";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, memberId);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				jp.co.aforce.beans.History history = new jp.co.aforce.beans.History();
				history.setHistoryId(rs.getInt("HISTORY_ID"));
				history.setMemberId(rs.getString("MEMBER_ID"));
				history.setProductId(rs.getString("PRODUCT_ID"));
				
				// ✨ さっきHistory.javaに作ったポケットに、DBから取ってきた商品名をセット！
				history.setProductName(rs.getString("PRODUCT_NAME"));
				
				history.setQuantity(rs.getInt("QUANTITY"));
				history.setPurchaseDate(rs.getTimestamp("PURCHASE_DATE"));
				
				list.add(history);
			}

			rs.close();
			st.close();
			con.close();

			return list;
		}
		// 👑 管理者用：すべての人、すべての商品の購入履歴を「氏名・商品名付き」で一括取得するメソッド
		public List<java.util.Map<String, Object>> findAllWithDetails() throws Exception {
			List<java.util.Map<String, Object>> list = new ArrayList<>();
			Connection con = getConnection();

			
			String sql = "SELECT h.HISTORY_ID, h.MEMBER_ID, h.PRODUCT_ID, " +
			             "CONCAT(u.LAST_NAME, ' ', u.FIRST_NAME) AS USER_NAME, " +
			             "p.PRODUCT_NAME, h.QUANTITY, h.PURCHASE_DATE " +
			             "FROM purchase_history h " +
			             "INNER JOIN product p ON h.PRODUCT_ID = p.PRODUCT_ID " +
			             "INNER JOIN users u ON h.MEMBER_ID = u.MEMBER_ID " +
			             "ORDER BY h.PURCHASE_DATE DESC";
			
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				java.util.Map<String, Object> map = new java.util.HashMap<>();
				map.put("historyId", rs.getInt("HISTORY_ID"));
				
				// データベースから取ってきたIDを箱に詰める処理
				map.put("memberId", rs.getString("MEMBER_ID"));
				map.put("productId", rs.getString("PRODUCT_ID"));
				map.put("userName", rs.getString("USER_NAME"));
				map.put("productName", rs.getString("PRODUCT_NAME"));
				map.put("quantity", rs.getInt("QUANTITY"));
				map.put("purchaseDate", rs.getTimestamp("PURCHASE_DATE"));
				
				list.add(map);
			}

			rs.close();
			st.close();
			con.close();

			return list;
		}
}