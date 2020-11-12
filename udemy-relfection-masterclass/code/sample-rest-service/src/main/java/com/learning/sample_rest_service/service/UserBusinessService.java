package com.learning.sample_rest_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.sample_rest_service.dto.UserDTO;
import com.learning.sample_rest_service.entities.User;

@Service
public class UserBusinessService implements IUserBusinessService {

	@Autowired
	private IUserDAOService userDaoService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public UserDTO createUser(UserDTO user) {
		if(user.getId() != 0) {
			throw new RuntimeException("Id can not be passed while creating User");
		}
		User userDAO = objectMapper.convertValue(user, User.class);
		userDaoService.createUser(userDAO);
		return objectMapper.convertValue(userDAO, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		int id = user.getId();
		if(id == 0) {
			throw new RuntimeException("Id can not be empty while updating User");
		}
		if(!userDaoService.contains(id)) {
			throw new RuntimeException("Can not Update user as user is not present with id "+id);
		}
		User userDAO = objectMapper.convertValue(user, User.class);
		userDaoService.updateUser(userDAO);
		return objectMapper.convertValue(userDAO, UserDTO.class);
	}

	@Override
	public List<UserDTO> findAllUsers(int pageNumber, int sizePerPage) {
		if(pageNumber == -1 || sizePerPage == -1) {
			//show all users
			return userDaoService.findAllUsers()
					  .stream()
					  .map(user -> objectMapper.convertValue(user, UserDTO.class))
					  .collect(Collectors.toList());
		}else {
			//pagiantion scneario
			//no sorting feature as of now
			int skipNumber = pageNumber*sizePerPage;
			return userDaoService.findAllUsers()
					  .stream()
					  .skip(skipNumber)
					  .limit(sizePerPage)
					  .map(user -> objectMapper.convertValue(user, UserDTO.class))
					  .collect(Collectors.toList());
		}
		
	}

	@Override
	public UserDTO findUser(int id) {
		User userDAO = userDaoService.findUser(id);
		if(userDAO == null) {
			throw new RuntimeException("User not found with ID "+id);
		}
		return objectMapper.convertValue(userDAO, UserDTO.class);
	}


	@Override
	public void remove(int id) {
		if(StringUtils.isEmpty(id)) {
			throw new RuntimeException("Id can not be empty while removing User");
		}
		if(!userDaoService.contains(id)) {
			throw new RuntimeException("Can not remove user as user is not present with id "+id);
		}
		userDaoService.remove(id);
	}

}
