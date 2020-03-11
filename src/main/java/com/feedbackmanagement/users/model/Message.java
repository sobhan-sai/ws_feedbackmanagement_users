package com.feedbackmanagement.users.model;

import lombok.Data;

@Data
public class Message {
	
	private String Content;
	public Message() {
	}

	public Message(String content) {
		Content = content;
	}
}
