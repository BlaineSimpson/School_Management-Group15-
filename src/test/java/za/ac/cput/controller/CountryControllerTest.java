package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Country;
import za.ac.cput.factory.CountryFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CountryController controller;
    @Autowired
    private TestRestTemplate restTemplate;

    private Country country;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        country = CountryFactory.createCountry("Sw01", "Sweden");
        this.baseUrl = "http://localhost:" + this.port + "/school-management/country/";
        assertNotNull(controller);
    }

    @Order(1)
    @Test
    void save() {
        String url = baseUrl + "save";
        System.out.println(url);
        ResponseEntity<Country> response = this.restTemplate
                .postForEntity(url, this.country, Country.class);
        System.out.println(response);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody()));

    }

    @Order(2)
    @Test
    void findById() {
        String url = baseUrl + "find/" + this.country.getId();
        ResponseEntity<Country> response = this.restTemplate.getForEntity(url, Country.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
        System.out.println(url);
        System.out.println(response);

    }

    @Order(5)
    @Test
    void delete() {
        String url = baseUrl + "delete-student" + this.country.getId();
        System.out.println(url);
        this.restTemplate.delete(url);

    }

    @Order(3)
    @Test
    void findAll() {
        String url = baseUrl + "all";
        System.out.println(url);
        ResponseEntity<Country[]> response = this.restTemplate.getForEntity(url, Country[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(1, response.getBody().length)
        );

    }

    @Test
    @Order(5)
    @Disabled
    void deleteById() {
        String url = baseUrl + "deleteById/" + this.country.getId();
        ResponseEntity<Country> response = restTemplate.getForEntity(url, Country.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }
}

