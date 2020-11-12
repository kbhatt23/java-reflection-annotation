package com.learning.sample_rest_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.learning.sample_rest_service.entities.User;

//This is to handle data only and not business logic
//will use ponly DAO layerde entities and not DTOs
//this class wont have errpor handlking, that has to be implemented in business service layer
@Service
public class UserDAOService implements IUserDAOService{
	
	private int indexId = 0;
	//using map for better and fast perfoamnce O(1) time complexity
	private static Map<Integer, User> USERS_DATA=new HashMap<Integer, User>();
	@Override
	public User createUser(User user) {
		indexId++;
		user.setId(indexId);
		USERS_DATA.put(indexId, user);
		return user;
	}
	@Override
	public User updateUser(User user) {
		USERS_DATA.put(indexId, user);
		return user;
	}
	@Override
	public List<User> findAllUsers() {
		return USERS_DATA.entrySet()
				  .stream()
				  .map(Entry::getValue)
				  .collect(Collectors.toList());
	}
	@Override
	public User findUser(int id) {
		return USERS_DATA.get(id);
	}
	@Override
	public boolean contains(int id) {
		return USERS_DATA.containsKey(id);
	}
	@Override
	public void remove(int id) {
		USERS_DATA.remove(id);
		
	}
	
	
}
