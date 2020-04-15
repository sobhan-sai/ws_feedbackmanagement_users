package com.feedbackmanagement.users.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthResponse {

	private String token;
	
	public AuthResponse() {}

	public AuthResponse(String token) {
		super();
		this.token = token;
	}
	

}
