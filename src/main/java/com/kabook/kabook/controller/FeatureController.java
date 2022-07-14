package com.kabook.kabook.controller;

import com.kabook.kabook.model.Feature;
import com.kabook.kabook.service.Impl.FeatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/features")
@CrossOrigin
public class FeatureController {
    @Autowired
    private FeatureServiceImpl featureServiceImpl;

    @PostMapping
    public ResponseEntity<Feature> createFeature(@RequestBody Feature feature) {
        return ResponseEntity.ok(featureServiceImpl.createFeature(feature));
    }
    @GetMapping
    public ResponseEntity<List<Feature>> listAllTheFeatures(){
        return ResponseEntity.ok(featureServiceImpl.listAllTheFeatures());
    }
}
