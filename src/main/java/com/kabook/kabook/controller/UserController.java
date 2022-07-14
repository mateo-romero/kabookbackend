package com.kabook.kabook.controller;

import com.kabook.kabook.model.DTO.AuthenticationRequest;
import com.kabook.kabook.model.DTO.AuthenticationResponse;
import com.kabook.kabook.model.DTO.UserDTO;
import com.kabook.kabook.service.IUserService;
import com.kabook.kabook.service.Impl.MyUserDetailService;
import com.kabook.kabook.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailService myUserDetailService;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	IUserService userService;

	/**
	 * Hace el login del usuario.
	 *
	 * @param authenticationRequest recibe un objeto json con "email" y "password"
	 * @return AuthenticationResponse
	 */
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
		// Autentica al usuario o tira exception
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(
					new AuthenticationResponse(
							null,
							null,
							"Usuario no encontrado"),
					HttpStatus.FORBIDDEN
			);
		}
		return userService.login(authenticationRequest);
	}

	/**
	 * Crea un nuevo usuario.
	 *
	 * @param userDTO recibe un objeto json con "email" y "password"
	 * @return AuthenticationResponse
	 */
	@PostMapping("/sign-in")
	public ResponseEntity<AuthenticationResponse> signIn(@RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}

	/**
	 * Permite a usuario con rol ADMIN crear un nuevo usuario y asignarle un rol
	 * <p>
	 * Headers - KEY: Authorization, VALUE: Bearer JWT
	 *
	 * @param userDTO y en propiedad token lleva el JWT del ADMIN que realiza la operación.
	 * @return AuthenticationResponse
	 */
	@PostMapping("/admin-tools/create-user")
	public ResponseEntity<AuthenticationResponse> createUserByAdmin(@RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}

}
