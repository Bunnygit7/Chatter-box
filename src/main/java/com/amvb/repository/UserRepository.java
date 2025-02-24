package com.amvb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amvb.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	public User findByUserNameAndPassword(String UserName,String Password);

	@Query(value = "select * from chat_users where user_name in (select req_sender from friend_request where req_receiver=:username and status=0)",nativeQuery = true)
	public List<User> getRequests(String username);
	
	@Query(value = "SELECT * FROM chat_users WHERE user_name IN " +
            "(SELECT req_receiver FROM friend_request WHERE status = 1 AND (req_sender = :userName OR req_receiver = :userName) " +
            " UNION " +
            " SELECT req_sender FROM friend_request WHERE status = 1 AND (req_sender = :userName OR req_receiver = :userName)) AND user_name !=:userName",
    nativeQuery = true)
	public List<User> findMyFriends(String userName);

}
