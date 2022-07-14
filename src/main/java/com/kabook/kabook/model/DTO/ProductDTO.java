package com.kabook.kabook.model.DTO;

import com.kabook.kabook.model.*;
import com.kabook.kabook.repository.IFeatureRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	@Autowired
	private IFeatureRepository iFeatureRepository;

	private Long id;
	private String name;
	private String description;
	private String location;
	private String latitude;
	private String longitude;
	private String rating;
	private Boolean isActive;
	// Id de la entidad elegida
	private Long categoryId;
	private Long cityId;
	private Set<Long> featuresIds;
	// Nueva entidad a crear
	private Set<Feature> features;
	private Set<Policy> policies;
	private Set<Image> images;
	private String errorMessage;
}
