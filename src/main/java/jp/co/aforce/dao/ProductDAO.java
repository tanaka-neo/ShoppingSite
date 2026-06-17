package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Product;

public class ProductDAO extends DAO {
	//商品一覧を取得するメソッド
	public List<Product> searchAll() throws Exception {
		//商品情報を格納するリストを作成
		List<Product> list = new ArrayList<>();
		//Tomcatに登録されたjdbc/shoppingsite_tanakaneoという設定からDBへ接続
		Connection con = getConnection();
		//SQLを準備
		PreparedStatement st;
		st = con.prepareStatement("SELECT * FROM product");
		//SQLを実行し、検索結果を取得
		ResultSet rs = st.executeQuery();
		//検索結果がある間繰り返す
		while (rs.next()) {
			//商品一件分を格納するBeanを生成
			Product product = new Product();
			//DBの値をProductオブジェクト（Bean）に格納
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
			product.setOrigin(rs.getString("VOLUME"));
			//商品情報をリストへ追加
			list.add(product);
		}
		//SQL実行オブジェクトとDB接続を閉じる
		st.close();
		con.close();
		//商品一覧を返す
		return list;
	}

	//	一件取得
	public Product findById(String productId) throws Exception {
		//Tomcatに登録されたjdbc/shoppingsite_tanakaneoという設定からDBへ接続
		Connection con = getConnection();
		//SQLを実行するためのオブジェクト
		//入力値と一致する商品を検索するSqL
		PreparedStatement st = con.prepareStatement("SELECT * FROM product WHERE PRODUCT_ID=?");
		st.setString(1, productId);
		//SQLを実行して検索結果を取得
		ResultSet rs = st.executeQuery();

		Product product = null;

		if (rs.next()) {
			//Beanを生成
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
		}
		//リザルトセットとSQL実行オブジェクトとDB接続を閉じる
		rs.close();
		st.close();
		con.close();

		return product;
	}
}