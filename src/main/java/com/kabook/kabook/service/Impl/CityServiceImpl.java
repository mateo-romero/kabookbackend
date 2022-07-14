package com.kabook.kabook.service.Impl;
import com.kabook.kabook.model.City;
import com.kabook.kabook.repository.ICityRepository;
import com.kabook.kabook.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private ICityRepository cityRepository;


    @Override
    public List<City> listAllTheCities() {
        return cityRepository.findAll();
    }

    @Override
    public City createCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }
}
