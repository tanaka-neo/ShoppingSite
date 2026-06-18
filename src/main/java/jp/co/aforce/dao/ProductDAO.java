package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Product;

public class ProductDAO extends DAO {
	
	//論理削除（非表示化）を行うメソッド
	public int delete(String productId) throws Exception {
		Connection con = getConnection();

		// 完全削除ではなく、is_deletedフラグを1(非表示)に更新するSQL
		PreparedStatement st = con.prepareStatement(
			"UPDATE product SET is_deleted = 1 WHERE product_id = ?"
		);
		st.setString(1, productId);

		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}
	
	// 新しく商品を登録するメソッド
	public int insert(Product product) throws Exception {
        Connection con = getConnection();

        String sql = "INSERT INTO product (product_id, product_name, price, stock, description, image_path, sweetness, sourness, berry_size, origin, volume) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);

        // SQL文の「？」の部分に、Beanから取り出したデータを順番にセット
        st.setString(1, product.getProductId());
        st.setString(2, product.getProductName());
        st.setInt(3, product.getPrice());
        st.setInt(4, product.getStock());
        st.setString(5, product.getDescription());
        st.setString(6, product.getImagePath());
        st.setInt(7, product.getSweetness());
        st.setInt(8, product.getSourness());
        st.setInt(9, product.getBerrySize());
        st.setString(10, product.getOrigin());
        st.setString(11, product.getVolume());

        // SQLを実行
        int line = st.executeUpdate();

        st.close();
        con.close();

        return line;
    }


	// 検索キーワードと並び替え条件（昇順・降順）で検索するメソッド
	public List<Product> search(String keyword, String sort) throws Exception {
		List<Product> list = new ArrayList<>();
		Connection con = getConnection();
		
		// 「WHERE 1=1」は、このあとに「AND 〜」を条件によってくっつけやすくするため
		String sql = "SELECT * FROM product WHERE is_deleted = 0";
		
		// キーワードが入力されている場合、SQL文に条件を追加する
		if (keyword != null && !keyword.trim().isEmpty()) {
			// 商品名（PRODUCT_NAME）、または商品説明（DESCRIPTION）のどちらかに部分一致するか
			sql += " AND (PRODUCT_NAME LIKE ? OR DESCRIPTION LIKE ?)";
		}
		
		// 並び替え条件（sort）に合わせて、SQLに ORDER BY を追加
		if (sort != null) {
			if (sort.equals("price_asc")) {
				sql += " ORDER BY PRICE ASC"; // 価格の安い順（昇順）
			} else if (sort.equals("price_desc")) {
				sql += " ORDER BY PRICE DESC"; // 価格の高い順（降順）
			}
		}
		
		PreparedStatement st = con.prepareStatement(sql);
		
		// 検索キーワードがあった場合のみ、? に値をセットする（両方の ? に同じキーワードを当てはめる）
		if (keyword != null && !keyword.trim().isEmpty()) {
			// 「%キーワード%」にすることで、前後に文字があってもヒットする（あいまい検索）にする
			st.setString(1, "%" + keyword + "%");
			st.setString(2, "%" + keyword + "%");
		}
		
		// SQLを実行して結果セットを取得
		ResultSet rs = st.executeQuery();
		
		// ループを回してデータベースから取得したデータをBeanに(Product)につめかえる
		while (rs.next()) {
			Product product = new Product();
			product.setProductId(rs.getString("PRODUCT_ID"));
			product.setProductName(rs.getString("PRODUCT_NAME"));
			product.setPrice(rs.getInt("PRICE"));
			product.setStock(rs.getInt("STOCK"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setImagePath(rs.getString("IMAGE_PATH"));
			product.setSweetness(rs.getInt("SWEETNESS"));
			product.setSourness(rs.getInt("SOURNESS"));
			product.setBerrySize(rs.getInt("BERRY_SIZE"));
			product.setOrigin(rs.getString("ORIGIN"));
			product.setVolume(rs.getString("VOLUME"));
			product.setIsDeleted(rs.getInt("IS_DELETED"));
			// 詰めたデータをリストに追加
			list.add(product);
		}
		
		// リソース解放
		rs.close();
		st.close();
		con.close();
		
		return list;
	}
	
	// 商品一覧を取得するメソッド
	public List<Product> searchAll() throws Exception {
		// 商品情報を格納するリストを作成
		List<Product> list = new ArrayList<>();
		// Tomcatに登録されたjdbc/shoppingsite_tanakaneoという設定からDBへ接続
		Connection con = getConnection();
		// SQLを準備
		PreparedStatement st;
		st = con.prepareStatement("SELECT * FROM product WHERE is_deleted = 0");
		// SQLを実行し、検索結果を取得
		ResultSet rs = st.executeQuery();
		// 検索結果がある間繰り返す
		while (rs.next()) {
			// 商品一件分を格納するBeanを生成
			Product product = new Product();
			// DBの値をProductオブジェクト（Bean）に格納
			product.setProductId(rs.getString("PRODUCT_ID"));
			product.setProductName(rs.getString("PRODUCT_NAME"));
			product.setPrice(rs.getInt("PRICE"));
			product.setStock(rs.getInt("STOCK"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setImagePath(rs.getString("IMAGE_PATH"));
			product.setSweetness(rs.getInt("SWEETNESS"));
			product.setSourness(rs.getInt("SOURNESS"));
			product.setBerrySize(rs.getInt("BERRY_SIZE"));
			product.setOrigin(rs.getString("ORIGIN"));
			product.setVolume(rs.getString("VOLUME"));
			product.setIsDeleted(rs.getInt("IS_DELETED"));
			// 商品情報をリストへ追加
			list.add(product);
		}
		// SQL実行オブジェクトとDB接続を閉じる
		st.close();
		con.close();
		// 商品一覧を返す
		return list;
	}

	// 一件取得
	public Product findById(String productId) throws Exception {
		// Tomcatに登録されたjdbc/shoppingsite_tanakaneoという設定からDBへ接続
		Connection con = getConnection();
		// SQLを実行するためのオブジェクト
		// 入力値と一致する商品を検索するSQL
		PreparedStatement st = con.prepareStatement("SELECT * FROM product WHERE PRODUCT_ID=?");
		st.setString(1, productId);
		// SQLを実行して検索結果を取得
		ResultSet rs = st.executeQuery();

		Product product = null;

		if (rs.next()) {
			// Beanを生成
			product = new Product();

			product.setProductId(rs.getString("PRODUCT_ID"));
			product.setProductName(rs.getString("PRODUCT_NAME"));
			product.setPrice(rs.getInt("PRICE"));
			product.setStock(rs.getInt("STOCK"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setImagePath(rs.getString("IMAGE_PATH"));
			product.setSweetness(rs.getInt("SWEETNESS"));
			product.setSourness(rs.getInt("SOURNESS"));
			product.setBerrySize(rs.getInt("BERRY_SIZE"));
			product.setOrigin(rs.getString("ORIGIN"));
			product.setVolume(rs.getString("VOLUME"));
			product.setIsDeleted(rs.getInt("IS_DELETED"));
		}
		// リザルトセットとSQL実行オブジェクトとDB接続を閉じる
		rs.close();
		st.close();
		con.close();

		return product;
	}


}