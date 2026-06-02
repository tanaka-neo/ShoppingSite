package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.Users;

public class UsersDAO extends DAO {

	//	ログイン用
	public Users search(String memberId, String password)
			throws Exception {
		//	検索結果を格納するUsersオブジェクト。ユーザーが見つからない場合はnullのまま
		Users users = null;

		//	Tomcatに登録されたjdbc/shoppingsite_tanakaneoという設定からDBへ接続
		Connection con = getConnection();

		//	SQLを実行するためのオブジェクト
		PreparedStatement st;
		//	入力値と一致するユーザを検索するSqL
		st = con.prepareStatement(
				"select * from users where MEMBER_ID=? and PASSWORD=?");
		st.setString(1, memberId);
		st.setString(2, password);
		//	SQLを実行して検索結果を取得
		ResultSet rs = st.executeQuery();

		//	成功時の実行
		while (rs.next()) {
			//	　　Beanを生成
			users = new Users();
			//		Usersオブジェクトに格納
			users.setMemberId(rs.getString("MEMBER_ID"));
			//		ログイン後もパスワードを持ち続ける必要はないかも？
			users.setPassword(rs.getString("PASSWORD"));
			users.setAddress(rs.getString("ADDRESS"));
			users.setMailAddress(rs.getString("MAIL_ADDRESS"));
			users.setLastName(rs.getString("LAST_NAME"));
			users.setFirstName(rs.getString("FIRST_NAME"));
		}
		st.close();
		con.close();
		return users;
	}

	//ID重複チェック用
	public Users search(String memberId)
			throws Exception {
		//	検索結果を格納するUsersオブジェクト。ユーザーが見つからない場合はnullのまま
		Users users = null;

		//	Tomcatに登録されたjdbc/shoppingsite_tanakaneoという設定からDBへ接続
		Connection con = getConnection();

		//	SQLを実行するためのオブジェクト
		PreparedStatement st;
		//	入力値と一致するユーザを検索するSqL
		st = con.prepareStatement(
				"select * from users where MEMBER_ID=?");
		st.setString(1, memberId);

		//	SQLを実行して検索結果を取得
		ResultSet rs = st.executeQuery();

		//	成功時の実行
		while (rs.next()) {
			//	　　Beanを生成
			users = new Users();
			//		Usersオブジェクトに格納
		}
		st.close();
		con.close();
		return users;
	}

	//新規登録用
	public int insert(Users users) throws Exception {
		// Tomcatに登録されたjdbc/shoppingsite_tanakaneoという設定からDBへ接続
		Connection con = getConnection();

		// INSERT文を準備（? は後で値を入れる「プレースホルダ」）
		PreparedStatement st = con.prepareStatement(
				"INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)");

		// 1番目の ? に memberId をセット
		st.setString(1, users.getMemberId());

		// 2番目の ? に password をセット
		st.setString(2, users.getPassword());

		// 3番目の ? に lastName（名字）をセット
		st.setString(3, users.getLastName());

		// 4番目の ? に firstName（名前）をセット
		st.setString(4, users.getFirstName());

		// 5番目の ? に address（住所）をセット
		st.setString(5, users.getAddress());

		// 6番目の ? に mailAddress（メールアドレス）をセット
		st.setString(6, users.getMailAddress());

		// SQLを実行（INSERTなので executeUpdate）
		// 戻り値は「何件登録されたか」
		int line = st.executeUpdate();

		// SQL実行用オブジェクトを閉じる（メモリ解放）
		st.close();

		// DB接続を閉じる
		con.close();

		// 登録成功件数を返す（通常は1）
		return line;
	}

}
