package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Country;
import za.ac.cput.domain.City;
import za.ac.cput.factory.CityFactory;
import za.ac.cput.factory.CountryFactory;
import za.ac.cput.service.impl.CityServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityServiceImplTest {

    @Autowired
    private CityServiceImpl service;
    private final Country country = CountryFactory.createCountry("Za", "South Africa");
    private final Country country2 = CountryFactory.createCountry("Dm", "Denmark");
    private final City city = CityFactory.createCity("1A", "Cape Town", country);
    private final City city2 = CityFactory.createCity("2A", "Prt", country2);
    private final City city3 = CityFactory.createCity("3A", "Jhb", country);
    private final City city4 = CityFactory.createCity("4A", "Kzn", country);
    private final City city5 = CityFactory.createCity("5A", "FS", country);


    @Test
    @Order(1)
    void save() {
        City create = this.service.save(this.city);
        City create2 = this.service.save(this.city2);
        City create3 = this.service.save(this.city3);
        City create4 = this.service.save(this.city4);
        City create5 = this.service.save(this.city5);
        assertNotNull(create);
        assertNotNull(create2);
        System.out.println(create);
    }

    @Test
    @Order(2)
    void findById() {
        Optional<City> read = this.service.findById(this.city.getId()); //reads the first employee only
        assertAll(
                () -> assertTrue(read.isPresent()),
                () -> assertEquals(this.city, read.get())
        );
        System.out.println(read);
    }

    @Test
    @Order(3)
    void findAll() {
        List<City> list = this.service.findAll();
        System.out.println(list);
    }

    @Test
    @Order(4)
    void findCitiesByCountry_Id() {
        List<City> list = this.service.findCitiesByCountry_Id(country.getId());
        System.out.println(list);
    }

    @Test
    @Order(5)
    void delete() {
        service.delete(city5);
        List<City> list = this.service.findAll();
        System.out.println(list);
    }

    @Test
    @Order(6)
    void deleteById() {
        service.deleteById("2A");
        List<City> list = this.service.findAll();
        System.out.println(list);
    }
}
