package com.amvb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amvb.entity.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Integer> {

	public FriendRequest findByReqSenderAndReqReceiver(String reqSender,String reqReceiver);
	
	public FriendRequest findByReqReceiverAndReqSender(String reqSender,String reqReceiver);

//	public List<FriendRequest> findByStatusAndReqReceiverOrReqSender(int status,String reqSender,String reqReceiver);

//	public List<FriendReqResponse> findByReqReceiver(String reqReceiver);
	

}
