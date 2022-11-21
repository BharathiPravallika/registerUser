package com.adduser.springbootproject.models;

public class UserData {
	private int userId;
	private String fullName;
	private String mobile;
	private String email;
	private String password;
	private String confirmPassword;
	
	
	
	@Override
	public String toString() {
		return "UserData [userId=" + userId + ", fullName=" + fullName + ", mobile=" + mobile + ", email=" + email
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
