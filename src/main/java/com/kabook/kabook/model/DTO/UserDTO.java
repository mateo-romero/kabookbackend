package com.kabook.kabook.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long id;
	private String name;
	private String lastName;
	private String email;
	private String password;
	private Long cityId;
	private String roleName;

	public UserDTO(String name, String lastName, String email, Long cityId) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.cityId = cityId;
	}

	public UserDTO(Long id, String name, String lastName, String email, Long cityId, String roleName) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.cityId = cityId;
		this.roleName = roleName;
	}
}


