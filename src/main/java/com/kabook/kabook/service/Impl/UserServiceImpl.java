package com.kabook.kabook.service.Impl;

import com.kabook.kabook.model.*;
import com.kabook.kabook.model.DTO.AuthenticationRequest;
import com.kabook.kabook.model.DTO.AuthenticationResponse;
import com.kabook.kabook.model.DTO.UserDTO;
import com.kabook.kabook.repository.IUserRepository;
import com.kabook.kabook.service.ICityService;
import com.kabook.kabook.service.IRoleService;
import com.kabook.kabook.service.IUserService;
import com.kabook.kabook.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IRoleService roleService;

	@Autowired
	private MyUserDetailService myUserDetailService;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ICityService cityService;

	@Override
	public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) {
		try {
			// Trae el usuario
			UserDetails userDetails = myUserDetailService.loadUserByUsername(authenticationRequest.getEmail());
			// Genera JWT
			String jwt = jwtUtil.generateToken(userDetails);
			// Trae la información del usuario
			UserDTO userDTO = getUserDTOByEmail(authenticationRequest.getEmail());
			// Responde con ese JWT
			return ResponseEntity.ok(new AuthenticationResponse(jwt, userDTO, ""));
		} catch (Exception e) {
			return new ResponseEntity<>(
					new AuthenticationResponse(null, null, e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public UserDTO getUserDTOByEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		UserDTO userDTO = null;
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			userDTO = new UserDTO(
					user.getId(),
					user.getName(),
					user.getLastName(),
					user.getEmail(),
					user.getCity().getId(),
					user.getRole().getName()
			);
		}
		return userDTO;
	}

	@Override
	public ResponseEntity<AuthenticationResponse> createUser(UserDTO userDTO) {
		if (checkIfUserExists(userDTO)) {
			return new ResponseEntity<>(
					new AuthenticationResponse(null, null, "El usuario ya se encuentra registrado"),
					HttpStatus.CONFLICT);
		}
		User user = prepareUserToSave(userDTO);
		User newUser = userRepository.save(user);
		UserDTO createdUserDTO = new UserDTO(
				newUser.getId(),
				newUser.getName(),
				newUser.getLastName(),
				newUser.getEmail(),
				newUser.getCity().getId(),
				newUser.getRole().getName()
		);
		// Trae el usuario para generar token
		final UserDetails userDetails = myUserDetailService.loadUserByUsername(user.getEmail());
		final String jwt = jwtUtil.generateToken(userDetails);
		return new ResponseEntity<>(
				new AuthenticationResponse(jwt, createdUserDTO, ""),
				HttpStatus.CREATED
		);
	}

	private boolean checkIfUserExists(UserDTO userDTO) {
		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());
		return userExists.isPresent();
	}

	private User prepareUserToSave(UserDTO userDTO) {
		String pass;
		City city;
		Role role;
		pass = bCryptPasswordEncoder.encode(userDTO.getPassword());
		role = setUserRole(userDTO);
		city = cityService.getCityById(userDTO.getCityId());
		return new User(
				userDTO.getName(),
				userDTO.getLastName(),
				userDTO.getEmail(),
				pass,
				true,
				city,
				role
		);
	}

	private Role setUserRole(UserDTO userDTO) {
		if (userDTO.getRoleName() != null) {
			return roleService
					.getRoleByName(userDTO.getRoleName())
					.orElse(roleService.getRoleById(2L).get());
		}
		return roleService.getRoleById(2L).get();
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		return userOptional.orElse(null);
	}

	@Override
	public User findByEmail(String email){
		return userRepository.findByEmail(email).orElse(null);
	}
}
