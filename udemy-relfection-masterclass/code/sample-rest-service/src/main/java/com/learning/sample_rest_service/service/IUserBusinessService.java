package com.learning.sample_rest_service.service;

import java.util.List;

import com.learning.sample_rest_service.dto.UserDTO;
//inmput and output will be DOT
//do transfomation to DTO, do error hadnling here
public interface IUserBusinessService {

	UserDTO createUser(UserDTO user);
	
	UserDTO updateUser(UserDTO user);
	
	List<UserDTO> findAllUsers(int pageNumber, int sizePerPage);
	
	UserDTO findUser(int id);
	
	//boolean contains(int id);
	
	void remove(int id);
}
