package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.Users;

public class UsersDAO extends DAO {
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
			users.setMailAddress(rs.getString("MAIL_ADDRESS"));
			users.setLastName(rs.getString("LAST_NAME"));
			users.setFirstName(rs.getString("FIRST_NAME"));
		}
		st.close();
		con.close();
		return users;
	}
}
