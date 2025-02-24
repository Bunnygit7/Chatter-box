package com.amvb.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amvb.entity.FriendRequest;
import com.amvb.entity.Message;
import com.amvb.entity.User;
import com.amvb.repository.ChatMessageRepository;
import com.amvb.repository.FriendRequestRepository;
import com.amvb.repository.UserRepository;
import com.amvb.request.MessageRequest;
import com.amvb.request.UserLoginRequest;
import com.amvb.request.UserRequest;
import com.amvb.response.ConversationResponse;
import com.amvb.response.UserResponse;

import jakarta.servlet.http.HttpSession;

@Service
public class RegisterService {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	FriendRequestRepository friendRequestRepository;
//	@Autowired
//	MessageRepository msgRepo;
	@Autowired
	ChatTableService chatTableService;
	@Autowired
	ChatMessageRepository chatMessageRepository;
	public String registerUser( UserRequest userRequest) {
		
		User user=new User();
		Optional<User> userExistCheck=userRepo.findById(userRequest.getUserNameRequest());
		if(userExistCheck.isEmpty()) {
			user.setUserName(userRequest.getUserNameRequest());
			user.setPassword(userRequest.getPasswordRequest());
			user.setName(userRequest.getNameRequest());
			user.setMobileNumber(userRequest.getMobileNumberRequest());
			user.setEmailId(userRequest.getEmailIdRequest());
			user.setDOB(userRequest.getDOBRequest());
			
			userRepo.save(user);
			
			return "Your Account has been created Successfully!";
		}
		
		return "Something went wrong please try again or username already exists";
		
	}

	public UserResponse loginUser(UserLoginRequest userLoginRequest) {
		UserResponse userResponse=new UserResponse();
		User user=userRepo.findByUserNameAndPassword(userLoginRequest.getUserName(),userLoginRequest.getPassword());
		if(user!=null) {
			userResponse.setUserNameResponse(user.getUserName());
			userResponse.setNameResponse(user.getName());
			userResponse.setMobileNumberResponse(user.getMobileNumber());
			userResponse.setEmailIdResponse(user.getEmailId());
			userResponse.setDOBResponse(user.getDOB());
			
			return userResponse;
		}
		return null;
	}

	public String sendRequest(String userName,HttpSession session) {
		
		LocalDateTime timeStamp=LocalDateTime.now();
		
		String myUserName=(String)session.getAttribute("username");
		
		if(friendRequestRepository.findByReqSenderAndReqReceiver(myUserName, userName)==null && friendRequestRepository.findByReqReceiverAndReqSender(myUserName, userName)==null && userRepo.findById(userName).isPresent()) {
			FriendRequest friendRequest=new FriendRequest();
			
			friendRequest.setReqSender(myUserName);
			friendRequest.setReqReceiver(userName);
			friendRequest.setReqDate(timeStamp);
			
			
			friendRequestRepository.save(friendRequest);
			
			return "Request Sent Succesfully";
		}
		
		return "Request Failed or Request Already present";
		
		
	}
	
	public List<UserResponse> getRequests(HttpSession session) {
		
//		friendRequestRepository.findByReqReceiver();
		String username=(String) session.getAttribute("username");
		List<User> requests=userRepo.getRequests(username);
//		FriendsRequestsResponse requestsResponse=new FriendsRequestsResponse();
		List<UserResponse> friendsRequests=requests.stream().map(req -> new UserResponse(req.getUserName(),req.getEmailId(),req.getName(),req.getDOB(),req.getMobileNumber())).toList();
		return friendsRequests;
	}

	public String acceptRequest(String userName, HttpSession session) {
		
		FriendRequest friendRequest=friendRequestRepository.findByReqSenderAndReqReceiver(userName, (String)session.getAttribute("username"));
		if(friendRequest!=null) {
			friendRequest.setStatus(1);
			
			friendRequestRepository.save(friendRequest);
//			msgRepo.createTableForFriends(userName,(String)session.getAttribute("username"),tableName);
			String chatTable=chatTableService.createTableForFriends(userName, (String)session.getAttribute("username"));
			System.out.println(chatTable);
			return "Request Accepted Successflly";
		}
		
		return "Request unavailable";
		
	}


	public String sendMessage(MessageRequest messageRequest, String userName, String myUserName) {
		
		Message message=new Message();
		LocalDateTime dateTime=LocalDateTime.now();
		message.setMessageId(messageRequest.getMessageId());
		message.setMessage(messageRequest.getMessage());
		message.setSentBy(myUserName);
		message.setTimeStamp(dateTime);
		message.setUser1(myUserName);
		message.setUser2(userName);
		
		
		
		List<String> users=Arrays.asList(userName,myUserName);
		Collections.sort(users);
		String tableName="CHAT_"+users.get(0).replaceAll("[^a-zA-Z0-9]", "")+"_AND_"+users.get(1).replaceAll("[^a-zA-Z0-9]", "");
		
//		msgRepo.sendMessage(message.getMessage(),message.getUser1(),message.getUser2(),message.getSentBy(),message.getTimeStamp(),tableName);
		String result=chatMessageRepository.sendMessage(message.getMessageId(),message.getMessage(),message.getUser1(),message.getUser2(),message.getSentBy(),message.getTimeStamp(),tableName);
		
		return result;
	}

	public List<UserResponse> myFriends(String userName) {
		
//		List<FriendRequest> friends=friendRequestRepository.findByStatusAndReqReceiverOrReqSender(1,userName,userName);
		
		List<User> friends=userRepo.findMyFriends(userName);
		
		List<UserResponse> myFriends=friends.stream().map(frnd->new UserResponse(frnd.getUserName(),frnd.getName(),frnd.getMobileNumber(),frnd.getEmailId(),frnd.getDOB())).toList();
		
		return myFriends;
		
	}

	public List<ConversationResponse> getConversation(String myUserName, String userName) {
		
		List<String> users=Arrays.asList(userName,myUserName);
		Collections.sort(users);
		String tableName="CHAT_"+users.get(0).replaceAll("[^a-zA-Z0-9]", "")+"_AND_"+users.get(1).replaceAll("[^a-zA-Z0-9]", "");
		
		List<ConversationResponse> conversationResponse=chatMessageRepository.conversation(tableName);
		
		return conversationResponse;
	}

}
