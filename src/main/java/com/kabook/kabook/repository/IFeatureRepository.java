package com.kabook.kabook.repository;

import com.kabook.kabook.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeatureRepository extends JpaRepository <Feature, Long> {
}
