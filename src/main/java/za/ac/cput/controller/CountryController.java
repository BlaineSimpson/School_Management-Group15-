/*
CountryController.java
Author: Demi Farquhar (220322104)
Date: 17 June 2022
 */
package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Country;
import za.ac.cput.service.ICountryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("school-management/country/")
@Slf4j
public class CountryController {
    private final ICountryService service;

    @Autowired
    public CountryController(ICountryService service){
        this.service=service;
    }
    @PostMapping("save")
    public ResponseEntity<Country> save(@Valid @RequestBody Country country){
        log.info("Save request: {}", country);//lombok
        Country save=service.save(country);
        return ResponseEntity.ok(save);
    }
    @GetMapping("find/{id}")
    public ResponseEntity<Country>findById(@PathVariable String id){
        log.info("Find by id request: {}",id);
        Country country=this.service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(country);
    }

    @DeleteMapping("delete-country")
    public ResponseEntity<Void>delete(Country country) {
        log.info("Delete request{}", country);
        this.service.delete(country);
        return ResponseEntity.noContent().build();

    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        log.info("Delete request: {}", id);
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("all")
    public ResponseEntity<List<Country>>findAll(){
        List<Country> countryList=this.service.findAll();
        return ResponseEntity.ok(countryList);

    }

}

