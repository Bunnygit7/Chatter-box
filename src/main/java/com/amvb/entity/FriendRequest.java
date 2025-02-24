package com.amvb.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FriendRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer requestId;
	@Column(length = 25)
	private String reqSender;
	@Column(length = 25)
	private String reqReceiver;
	@Column(length = 25)
	private LocalDateTime reqDate;
	private int status;

}
