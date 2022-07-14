package com.kabook.kabook.repository;

import com.kabook.kabook.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
	@Query
	Optional<Role> findByName(String name);
}
