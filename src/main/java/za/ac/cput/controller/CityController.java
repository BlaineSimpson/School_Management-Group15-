package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.City;
import za.ac.cput.service.CityService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("school-management/city")
@Slf4j
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("save")
    public ResponseEntity<City> save(@Valid @RequestBody City city){
        log.info("Save Request: {}", city); //logging using lombok
        City insert = cityService.save(city);
        return ResponseEntity.ok(insert);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Optional<City>> findById(@PathVariable String id){
        log.info("Read request: {}", id);
        Optional<City> find = cityService.findById(id);
        return ResponseEntity.ok(find);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        log.info("Delete request: {}", id);
        this.cityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete-city")
    public ResponseEntity<Void> delete(City city){
        log.info("Delete request: {}", city);
        this.cityService.delete(city);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<City>> findAll(){
        List<City> cityList = cityService.findAll();
        return ResponseEntity.ok(cityList);
    }
//Question 7
    @GetMapping("find-cities-by-country/{id}")
    public ResponseEntity<List<City>> findCitiesByCountry_Id(@PathVariable String id){
        List<City> cityByCountryList = cityService.findCitiesByCountry_Id(id);
        return ResponseEntity.ok(cityByCountryList);
    }
}
