package com.kabook.kabook.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyUserDetails implements UserDetails {

	@Autowired
	private ObjectMapper objectMapper;

	private String username;

	private String password;

	private boolean active;

	private Set<GrantedAuthority> authorities;

	public MyUserDetails(User user) {
		this.username = user.getEmail();
		this.password = user.getPassword();
		this.active = true;

		// Se crea un Set de authorities a partir del rol del usuario , las authorities requieren el prefijo "ROLE_"
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getName());
		grantedAuthorities.add(grantedAuthority);

		this.authorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
