package com.kabook.kabook.repository;

import com.kabook.kabook.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository <City, Long> {
}
