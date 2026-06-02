package jp.co.aforce.beans;

public class Users implements java.io.Serializable{

	
	private String memberId;
	private String password;
	private String address;
	private String mailAddress;
	private String lastName;
	private String firstName;
	
	
	
	public String getMemberId() {
		return memberId;
	}
	public String getPassword() {
		return password;
	}
	public String getAddress() {
		return address;
	}			
	public String getMailAddress() {
		return mailAddress;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setMemberId(String memberId) {
		this.memberId=memberId;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public void setAddress(String address) {
		this.address=address;
	}	
	public void setMailAddress(String mailAddress) {
		this.mailAddress=mailAddress;
	}
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
}
