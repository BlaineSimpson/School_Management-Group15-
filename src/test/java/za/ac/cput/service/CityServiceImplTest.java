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
    private final Country country = CountryFactory.createCountry("1A", "South Africa");
    private final Country country2 = CountryFactory.createCountry("1B", "Denmark");
    private final City city = CityFactory.createCity("1A", "Cape Town", country);
    private final City city2 = CityFactory.createCity("2A", "Copenhagen", country2);


    @Test
    @Order(1)
    void save() {
        City create = this.service.save(this.city);
        City create2 = this.service.save(this.city2);
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
    @Order(4)
    void delete() {
        service.delete(city);
        List<City> list = this.service.findAll();
        System.out.println(list);
    }

    @Test
    @Order(3)
    void findAll() {
        List<City> list = this.service.findAll();
        System.out.println(list);
    }

    @Test
    @Order(5)
    void deleteById() {
        service.deleteById("2A");
        List<City> list = this.service.findAll();
        System.out.println(list);
    }
}
