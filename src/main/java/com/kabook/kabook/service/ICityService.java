package com.kabook.kabook.service;
import com.kabook.kabook.model.City;

import java.util.List;

public interface ICityService {
    List<City> listAllTheCities();
    City createCity(City city);
    City getCityById(Long id);

}
