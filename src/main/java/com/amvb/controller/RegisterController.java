package com.amvb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amvb.request.MessageRequest;
import com.amvb.request.UserLoginRequest;
import com.amvb.request.UserRequest;
import com.amvb.response.ConversationResponse;
import com.amvb.response.UserResponse;
import com.amvb.service.RegisterService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/chat")
public class RegisterController {

	@Autowired
	RegisterService service;
	@PostMapping("/register")
	public String register(@Valid @RequestBody UserRequest userRequest) {
		
		String result=service.registerUser(userRequest);
		
		return result;
	}
	
	@PostMapping("/login")
	public UserResponse login(@Valid @RequestBody UserLoginRequest userLoginRequest,HttpSession session) {
		
		UserResponse response=service.loginUser(userLoginRequest);
		if(response!=null) {
			session.setAttribute("username", response.getUserNameResponse());
			
		}
		return response;
	}
	
	@GetMapping("/session")
	public String sessionCheck(HttpSession session) {
		return (String) session.getAttribute("username");
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		
		return "Session removed and Logged out Successfully";
	}
	
	@PostMapping("/request")
	public String request(@RequestParam String userName,HttpSession session) {
		if(userName.equals(session.getAttribute("username"))) {
			return "Request Failed you connot send request to yourself";
		}
		String result=service.sendRequest(userName,session);
		
//		String myUserName=(String) session.getAttribute("username");
//		System.out.println(myUserName);
		return result;
	}
	
	@GetMapping("/get/requests")
	public List<UserResponse> getrequests(HttpSession session) {
		List<UserResponse> result=service.getRequests(session);
		return result;
	}
	
	@PutMapping("/accept")
	public String acceptRequest(@RequestParam String userName,HttpSession session) {
		
		String result=service.acceptRequest(userName,session);
		
		return result;
	}
	
	@GetMapping("/my/friends")
	public List<UserResponse> getMyFriends(HttpSession session) {
		List<UserResponse> friends=service.myFriends((String)session.getAttribute("username"));
		return friends;
	}
	
	
	@PostMapping("/msg/{userName}")
	public String messageFriend(@RequestBody MessageRequest messageRequest,@PathVariable String userName,HttpSession session) {
		
		String result=service.sendMessage(messageRequest,userName,(String)session.getAttribute("username"));
		
		return result;
	}
	
	
	@GetMapping("/conversation/{userName}")
	public List<ConversationResponse> getConversation(@PathVariable String userName,HttpSession session) {
		String myUserName=(String) session.getAttribute("username");
		List<ConversationResponse> conversation=service.getConversation(myUserName,userName);
		
		return conversation;
	}
	
	
	
}
