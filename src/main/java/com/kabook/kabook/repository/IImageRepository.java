package com.kabook.kabook.repository;

import com.kabook.kabook.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository<Image, Long> {
}
