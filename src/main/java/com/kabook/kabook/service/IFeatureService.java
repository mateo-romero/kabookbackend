package com.kabook.kabook.service;

import com.kabook.kabook.model.DTO.ProductDTO;
import com.kabook.kabook.model.Feature;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IFeatureService {
    List<Feature> listAllTheFeatures();
    Feature createFeature(Feature feature);
    boolean saveFeaturesFromProduct(ProductDTO productDTO);

	Optional<Feature> findById(Long featureId);
}
