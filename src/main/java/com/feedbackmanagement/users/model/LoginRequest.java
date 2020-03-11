package com.feedbackmanagement.users.model;

import java.io.Serializable;

public class LoginRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7149678955493704319L;
	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
