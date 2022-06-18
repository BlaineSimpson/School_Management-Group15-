package za.ac.cput.controller;
/*ADP 3 Assignment 2
Author : Sinovuyo Mlanjeni(219220387)
 */


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentAddressControllerTest {
    //springboot selects any random, and use that as the port
    @LocalServerPort
    private int port;

    @Autowired
    private StudentAddressController controller;
    //Used to test our web services
    @Autowired private TestRestTemplate restTemplate;

    private StudentAddress studentAddress;
    private Address address;
    private String baseUrl;

    @BeforeEach
    void setUp(){
        Country country = CountryFactory.createCountry("5A", "James");
        City city = CityFactory.createCity("5A", "James", country);
        address = AddressFactory.createAddress("2", "ATB", "5", "John", 542,  city);
        baseUrl = "http://localhost:" + port + "/school-management/student/";
    }
    @Test
    @Order(1)
    void save() {
        String url = baseUrl + "save";
        ResponseEntity<StudentAddress> response = restTemplate.postForEntity(url, this.studentAddress, StudentAddress.class);
        assertAll(
                //checking to see that the statusCode for response matches OK (that it is successful)
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                //checking that response has a body
                () -> assertNotNull(response.getBody())
        );
    }
    @Test
    @Order(2)
    void findById() {
        String url = baseUrl + "find/" + this.studentAddress.getStudentId();
        ResponseEntity<StudentAddress> response = this.restTemplate.getForEntity(url, StudentAddress.class);
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
        String url = baseUrl + "delete/" + studentAddress.getStudentId();
        restTemplate.delete(url);
        System.out.println(url);
    }

    @Test
    @Order(5)
    void delete() {
        String url = baseUrl + "delete-studentAddress";
        restTemplate.delete(url);
        System.out.println(url);
    }

    @Test
    @Order(3)
    void findAll() {
        String url = baseUrl + "all";
        ResponseEntity<StudentAddress[]> response = restTemplate.getForEntity(url, StudentAddress[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                //length is the number of insertion done
                () -> assertTrue(response.getBody().length == 1)
        );
    }


}