package com.amvb.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.amvb.response.ConversationResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ChatMessageRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public String sendMessage(Integer messageId,String messageText, String user1, String user2, String sentBy, LocalDateTime time,String tableName) {
		
		String sql="INSERT INTO " + tableName + " (messageId,message, user1, user2, sentBy, time) VALUES (:messageId,:messageText, :user1, :user2, :sentBy, :time)";
		entityManager.createNativeQuery(sql)
						 .setParameter("messageId", messageId)
						 .setParameter("messageText", messageText)
				         .setParameter("user1", user1)
				         .setParameter("user2", user2)
				         .setParameter("sentBy", sentBy)
				         .setParameter("time", time)
				         .executeUpdate();
		return "Message Sent!";
	}
	
	
	public List<ConversationResponse> conversation(String tableName){
		
		String sql="SELECT message,sentBy,time FROM "+tableName+" ORDER BY time DESC";
		@SuppressWarnings("unchecked")
		List<ConversationResponse> conversation=entityManager.createNativeQuery(sql).getResultList();
		
		return conversation;
	}
}
