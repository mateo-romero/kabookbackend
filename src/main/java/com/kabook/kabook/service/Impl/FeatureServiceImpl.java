package com.kabook.kabook.service.Impl;

import com.kabook.kabook.model.DTO.ProductDTO;
import com.kabook.kabook.model.Feature;
import com.kabook.kabook.repository.IFeatureRepository;
import com.kabook.kabook.service.IFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FeatureServiceImpl implements IFeatureService {
    @Autowired
    private IFeatureRepository iFeatureRepository;


    @Override
    public List<Feature> listAllTheFeatures() {
        return iFeatureRepository.findAll();
    }

    @Override
    public Feature createFeature(Feature feature) {
        return iFeatureRepository.save(feature);
    }

    @Override
    public boolean saveFeaturesFromProduct(ProductDTO productDTO) {
        Set<Feature> featureSet = productDTO.getFeatures();
        if (featureSet != null && !featureSet.isEmpty()) {
            for (Feature feature : featureSet) {
                Feature newFeature = createFeature(feature);
                if (newFeature == null) {
                    return false;
                }
                featureSet.add(newFeature);
            }
        }
        productDTO.setFeatures(featureSet);
        return true;
    }

    public Optional<Feature> findById(Long featureId) {
        return iFeatureRepository.findById(featureId);
    }
}
