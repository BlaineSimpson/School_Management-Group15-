/*
CountryServiceImpl.java
Service for country
Author: Demi Farquhar (220322104)
Date: 15 June 2022
 */
package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Country;
import za.ac.cput.repository.ICountryRepository;
import za.ac.cput.service.ICountryService;

import java.util.List;
import java.util.Optional;


@Service
public class CountryServiceImpl implements ICountryService {

    private final ICountryRepository repository;

    @Autowired
    public CountryServiceImpl(ICountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Country save(Country country) {
        return this.repository.save(country);
    }

    @Override
    public Optional<Country> findById(String s) {
        return this.repository.findById(s);
    }

    @Override
    public List<Country> findAll() {
        return this.repository.findAll();
    }

    @Override
    public void delete(Country country) {
        this.repository.delete(country);

    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
        Optional<Country> country = findById(id);
        if (country.isPresent()) {
            delete(country.get());
        }
    }
}

