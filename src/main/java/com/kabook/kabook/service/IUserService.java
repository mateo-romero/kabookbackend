package com.kabook.kabook.service;

import com.kabook.kabook.model.DTO.AuthenticationRequest;
import com.kabook.kabook.model.DTO.AuthenticationResponse;
import com.kabook.kabook.model.DTO.UserDTO;
import com.kabook.kabook.model.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {
	ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest);
	UserDTO getUserDTOByEmail(String email);
	ResponseEntity<AuthenticationResponse> createUser(UserDTO userDTO);
	User getUserById(Long id);
	User findByEmail(String email);
}
