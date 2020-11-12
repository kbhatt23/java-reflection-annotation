package com.learning.sample_rest_service.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.sample_rest_service.dto.UserDTO;
import com.learning.sample_rest_service.service.IUserBusinessService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private IUserBusinessService userBusinessService;

	// ading paginatoin support y default as users can be huge in number
	@GetMapping(/* produces = "application/xml" */)
	public List<UserDTO> findAll(@RequestParam(required = false, name = "page", defaultValue = "-1") int pageNumber,
			@RequestParam(required = false, name = "size", defaultValue = "-1") int sizePerPage) {
		return userBusinessService.findAllUsers(pageNumber, sizePerPage);
	}

	@PostMapping
	public UserDTO createUser(@RequestBody UserDTO user) {
		return userBusinessService.createUser(user);
	}

	@PutMapping
	public UserDTO updateUser(@RequestBody UserDTO user) {
		return userBusinessService.updateUser(user);
	}

	@GetMapping("/{id}")
	public UserDTO findUSer(@PathVariable Integer id) {
		return userBusinessService.findUser(id);
	}

	@DeleteMapping("/{id}")
	public void removeUser(@PathVariable Integer id) {
		userBusinessService.remove(id);
	}

}
