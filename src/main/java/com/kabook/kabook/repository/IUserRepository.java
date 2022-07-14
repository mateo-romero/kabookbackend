package com.kabook.kabook.repository;

import com.kabook.kabook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	@Query
	Optional<User> findByEmail(String email);

}
