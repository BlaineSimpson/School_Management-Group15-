package za.ac.cput.controller;
/*
Author: Blaine Simpson
Student Nr: 218020171
 */
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;
import za.ac.cput.domain.EmployeeAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.CityFactory;
import za.ac.cput.factory.CountryFactory;
import za.ac.cput.factory.EmployeeAddressFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeAddressControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private EmployeeAddressController controller;
    @Autowired private TestRestTemplate restTemplate;

    private EmployeeAddress employeeAddress;
    private Address address;
    private Country country;
    private City city;
    private String baseUrl;

    @BeforeEach
    void setUp(){
        country = CountryFactory.createCountry("BDS", "Sout Africa");
        city = CityFactory.createCity( "A", "Lee",country);
        address = AddressFactory.createAddress("4", "skyway","5","AfricaSTR",6850,city);
        employeeAddress = EmployeeAddressFactory.createEmployeeAddress("1", address );
        baseUrl = "http://localhost:" + port +"/school-management/employeeAddress/";
    }


    @Test
    @Order(1)
    void save() {
        String url = baseUrl + "save";
        ResponseEntity<EmployeeAddress> response = restTemplate.postForEntity(url, this.employeeAddress, EmployeeAddress.class);
        assertAll(

                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(2)
    void findByid() {
    String url = baseUrl +"find/" + employeeAddress.getStaffId();
    ResponseEntity<EmployeeAddress> response = restTemplate.getForEntity(url, EmployeeAddress.class);
    assertAll(
            ()-> assertEquals(HttpStatus.OK, response.getStatusCode()),
            ()-> assertNotNull(response.getBody())

    );

    }

    @Test
    @Order(4)
    void delete() {
        String url = baseUrl +"delete-student";
        restTemplate.delete(url);
        System.out.println(url);
    }

    @Test
    @Order(3)
    void findAll() {
        String url = baseUrl + "all";
        ResponseEntity <EmployeeAddress[]> response =restTemplate.getForEntity(url, EmployeeAddress[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals (HttpStatus.OK, response.getStatusCode()),
                () ->assertTrue (response.getBody().length == 1)



        );


    }
}