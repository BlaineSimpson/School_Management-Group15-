package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import za.ac.cput.domain.Country;
import za.ac.cput.domain.City;
import za.ac.cput.factory.CountryFactory;
import za.ac.cput.factory.CityFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CityController controller;
    @Autowired private TestRestTemplate restTemplate;

    private City city;
    private Country country;
    private String baseUrl;

    @BeforeEach
    void setUp(){
        country = CountryFactory.createCountry("ZA", "South Africa");
        city = CityFactory.createCity("1B", "Cape Town", country);
        baseUrl = "http://localhost:" + port + "/school-management/city/";
    }

    @Test
    @Order(1)
    void save() {
        String url = baseUrl + "save";
        ResponseEntity<City> response = restTemplate.postForEntity(url, this.city, City.class);
        assertAll(

                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),

                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(2)
    void findById() {
        String url = baseUrl + "find/" + this.city.getId();
        ResponseEntity<City> response = this.restTemplate.getForEntity(url, City.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
        System.out.println(url);
        System.out.println(response);
    }

    @Test
    @Order(3)
    void findAll() {
        String url = baseUrl + "all";
        ResponseEntity<City[]> response = restTemplate.getForEntity(url, City[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),

                () -> assertTrue(response.getBody().length == 1)
        );
    }
//Question 7
    @Test
    @Order(4)
    void findCitiesByCountry_Id(){
        String url = baseUrl + "find-cities-by-country/" + country.getId();
        ResponseEntity<City[]> response = restTemplate.getForEntity(url, City[].class);
        System.out.println(response);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),

                () -> assertTrue(response.getBody().length == 1)
        );
    }

    @Test
    @Order(5)
    void deleteById() {
        String url = baseUrl + "delete/" + city.getId();
        restTemplate.delete(url);
        System.out.println(url);
    }

    @Test
    @Order(6)
    void delete() {
        String url = baseUrl + "delete-city";
        restTemplate.delete(url);
        System.out.println(url);
    }
}
