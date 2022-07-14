package com.kabook.kabook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class Role {

	@Id
	@SequenceGenerator(name = "rol_sequence" , sequenceName = "rol_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public Role(String name) {
		this.name = name;
	}
}
