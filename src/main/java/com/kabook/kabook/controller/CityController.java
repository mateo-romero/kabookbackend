package com.kabook.kabook.controller;
import com.kabook.kabook.model.City;
import com.kabook.kabook.service.Impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@CrossOrigin
public class CityController {

    @Autowired


    private CityServiceImpl cityServiceImpl;


    @PostMapping
    public ResponseEntity<City> createCity (@RequestBody City city) {
        return ResponseEntity.ok(cityServiceImpl.createCity(city));
     }
    @GetMapping
    public ResponseEntity<List<City>> listAllTheCities(){
        return ResponseEntity.ok(cityServiceImpl.listAllTheCities());
    }
}
