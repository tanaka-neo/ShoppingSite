package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.Users;

public class UsersDAO extends DAO {
public Users search(String memberId,String password)
throws Exception {
	Users users=null;
	
	Connection con=getConnection();
	
	PreparedStatement st;
	st=con.prepareStatement(
			"select * from users where MEMBER_ID=? and PASSWORD=?");
	st.setString(1,memberId);
	st.setString(2,password);
	ResultSet rs=st.executeQuery();
	
	while (rs.next()) {
		users=new Users();
		users.setMemberId(rs.getString("MEMBER_ID"));
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
