
package za.ac.cput.controller;

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
import za.ac.cput.domain.StudentAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.CityFactory;
import za.ac.cput.factory.CountryFactory;
import za.ac.cput.factory.StudentAddressFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


 @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class StudentAddressControllerTest {

        @LocalServerPort
        private int port;

        @Autowired
        private StudentAddressController controller;

        @Autowired
        private TestRestTemplate restTemplate;

        private StudentAddress studentAddress;
        private Address address;
        private String baseUrl;

        @BeforeEach
        void setUp() {
            assertNotNull(controller);
            Country country = CountryFactory.createCountry("012A", "Italy");
            City city = CityFactory.createCity("014", "New York", country);
            this.address = AddressFactory.createAddress("15",
                    "Carter","20", "Jones",4563, city);

            this.studentAddress = StudentAddressFactory.createStudentAddress("256", address);

            this.baseUrl = "http://localhost:" + this.port + "/school-management/student-address/";
        }

        @Order(1)
        @Test
        void save(){
            String url = baseUrl + "save";
            System.out.println(url);
            ResponseEntity<StudentAddress> response = this.restTemplate
                    .postForEntity(url, this.studentAddress, StudentAddress.class);
            System.out.println(response);
            assertAll(
                    () -> assertEquals(HttpStatus.OK,response.getStatusCode()),
                    () -> assertNotNull(response.getBody())
            );
        }

        @Order(2)
        @Test
        void read(){
            String url = baseUrl + "find-by-id/" + this.studentAddress.getStudentId();
            System.out.println(url);
            ResponseEntity<StudentAddress> response = this.restTemplate.getForEntity(url, StudentAddress.class);
            System.out.println(response);
            assertAll(
                    ()-> assertEquals(HttpStatus.OK, response.getStatusCode()),
                    ()-> assertNotNull(response.getBody())
            );
        }


        @Order(3)
        @Test
        void delete(){
            String url = baseUrl + "delete/" + this.studentAddress.getStudentId();
            System.out.println(url);
            this.restTemplate.delete(url);
        }


        @Order(4)
        @Test
        void findAll(){
            String url = baseUrl + "all";
            System.out.println(url);
            ResponseEntity<StudentAddress[]> response =
                    this.restTemplate.getForEntity(url, StudentAddress[].class);
            System.out.println(Arrays.asList(response.getBody()));
            assertAll(
                    () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                    () -> assertEquals(0, response.getBody().length)
            );
        }


    }