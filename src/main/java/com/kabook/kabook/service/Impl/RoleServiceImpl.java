package com.kabook.kabook.service.Impl;

import com.kabook.kabook.model.Role;
import com.kabook.kabook.repository.IRoleRepository;
import com.kabook.kabook.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	IRoleRepository roleRepository;

	@Override
	public Optional<Role> getRoleById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		return roleRepository.findByName(name);
	}
}
