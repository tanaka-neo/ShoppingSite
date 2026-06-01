package jp.co.aforce.dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	//	DB接続を保持するDatasource
	static DataSource ds;

	public Connection getConnection() throws Exception {

		//		初回のみTomcatに登録されたDB接続情報を取得
		if (ds == null) {
			// JNDIリソースを検索するためのコンテキストを生成
			InitialContext ic = new InitialContext();

			//Tomcatにjdbc/shoppingsite_tanakaneo手名前で尊くされてるDB情報をくださいって意味
			ds = (DataSource) ic.lookup("java:/comp/env/jdbc/shoppingsite_tanakaneo");

		}
		//DB接続を返す
		return ds.getConnection();
	}
}
