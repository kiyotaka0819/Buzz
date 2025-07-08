package model;

public class Account{
	private String userId;
	private String pass;
	private String name;
	private String profile;
	
	public Account(String userId, String pass, String name, String profile) {
		this.userId = userId;
		this.pass = pass;
		this.name = name;
		this.profile = profile;
	}
	public String getUserId() {
		return userId;
	}
	public String getPass() {
		return pass;
	}
	public String getName() {
		return name;
	}
	public String getProfile() {
		return profile;
	}
}
