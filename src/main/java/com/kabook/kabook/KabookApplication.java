package com.kabook.kabook;

import com.kabook.kabook.repository.IUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = IUserRepository.class)
public class KabookApplication {

	public static void main(String[] args) {
		SpringApplication.run(KabookApplication.class, args);
	}

}
