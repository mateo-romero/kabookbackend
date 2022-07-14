package com.kabook.kabook.service.Impl;

import com.kabook.kabook.model.MyUserDetails;
import com.kabook.kabook.model.User;
import com.kabook.kabook.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
    IUserRepository userRepository;

	/*
	* Carga el usuario desde la base de datos por email
	*/
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		user.orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
		return user.map(MyUserDetails::new).get();
	}

}
