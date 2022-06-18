package za.ac.cput.service;
/*
Author: Shuaib Allie (217148867)
 */

import za.ac.cput.domain.City;

import java.util.List;

public interface CityService extends IService<City, String>  {
    List<City> findAll();

    void deleteById(String s);
}
