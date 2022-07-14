package com.kabook.kabook.model.DTO;

import com.kabook.kabook.model.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
	private String jwt;
	private UserDTO userDTO;
	private String error;
}
