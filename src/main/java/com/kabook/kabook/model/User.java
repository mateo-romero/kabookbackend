package com.kabook.kabook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_kabook")
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@SequenceGenerator(name = "user_kabook_sequence" , sequenceName = "user_kabook_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String lastName;

	@Column(unique = true)
	private String email;

	@JsonIgnore
	private String password;

	@JsonIgnore
	private Boolean active;

	// Muchos usuarios en una ciudad
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	// Muchos usuatios tienen un rol
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public User(String name, String lastName, String email, String password, boolean active, City city, Role role) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.city = city;
		this.role = role;
	}
}
