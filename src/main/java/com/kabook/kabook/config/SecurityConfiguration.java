package com.kabook.kabook.config;

import com.kabook.kabook.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	/*
	 * Spring security nos pide que haya un encoder
	 */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	JwtRequestFilter jwtRequestFilter;

	/*
	* Se configura para que se valide la autenticación desde la base de datos
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	/*
	* Se configuran los permisos de los endpoints
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors().and().csrf().disable();
		http
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/products/save").hasAuthority("ROLE_ADMIN")
				.antMatchers(HttpMethod.DELETE, "/products/delete/{id}").hasAuthority("ROLE_ADMIN")
				.antMatchers(HttpMethod.POST, "/bookings/product-booking/{id}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
				.antMatchers("/bookings/user/{userEmail}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
				.antMatchers("/",
						"/categories/{id}",
						"/categories",
						"/category/*",
						"/cities",
						"/features",
						"/images",
						"/products",
						"/products/{id}",
						"/products/city/{cityId}",
						"/products/category/{categoryId}",
						"/products/productsDisordered",
						"/products/by-city-and-date",
						"/bookings/product/{id}",
						"/login",
						"/sign-in",
						"/policies").permitAll()
				.anyRequest().authenticated();
		http
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}


	/*
	* Requerido para versiones nuevas de Spring boot (2.0)
	*/
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
