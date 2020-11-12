package com.learning.sample_rest_service.service;

import java.util.List;

import com.learning.sample_rest_service.entities.User;
//inmput and output will only be User dao entity and not DTO
public interface IUserDAOService {

	User createUser(User user);
	
	User updateUser(User user);
	
	List<User> findAllUsers();
	
	User findUser(int id);
	
	boolean contains(int id);
	
	void remove(int id);
}
