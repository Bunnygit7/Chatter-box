package com.amvb.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ConversationResponse {

	private String message;
	private String sentBy;
	private LocalDateTime time;
	
}
