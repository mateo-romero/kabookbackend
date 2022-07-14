package com.kabook.kabook.service;

import com.kabook.kabook.model.Role;

import java.util.Optional;

public interface IRoleService {
	Optional<Role> getRoleById(Long id);
	Optional<Role> getRoleByName(String name);
}
