package com.amvb.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Message {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer messageId;
	
	private String message;
	
	private String user1;
	
	private String user2;
	
	private String sentBy;
	private LocalDateTime timeStamp;
	
}
