package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Country;
import za.ac.cput.domain.City;
import za.ac.cput.factory.CountryFactory;
import za.ac.cput.factory.CityFactory;

import java.util.Arrays;

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
        country = CountryFactory.createCountry("1A", "South Africa");
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
    @Order(4)
    void deleteById() {
        String url = baseUrl + "delete/" + city.getId();
        restTemplate.delete(url);
        System.out.println(url);
    }

    @Test
    @Order(5)
    void delete() {
        String url = baseUrl + "delete-city";
        restTemplate.delete(url);
        System.out.println(url);
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
}
