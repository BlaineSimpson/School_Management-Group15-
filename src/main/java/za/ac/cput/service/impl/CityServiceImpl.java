package za.ac.cput.service.impl;

/*
Author: Shuaib Allie (217148867)
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.CityRepository;
import za.ac.cput.service.CityService;

import java.util.List;
import java.util.Optional;


@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cRepository;
    @Autowired
    public CityServiceImpl(CityRepository cRepository){
        this.cRepository=cRepository;
    }
    @Override
    public City save(City city) {
        return this.cRepository.save(city);
    }

    @Override
    public Optional<City> findById(String s) {
        return this.cRepository.findById(s);
    }

    @Override
    public List<City> findAll() {
        return this.cRepository.findAll();
    }

    @Override
    public void delete(City city) {
        this.cRepository.delete(city);


    }

    @Override
    public void deleteById(String id) {
        Optional<City> city = findById(id);
        city.ifPresent(this::delete);
    }
}
