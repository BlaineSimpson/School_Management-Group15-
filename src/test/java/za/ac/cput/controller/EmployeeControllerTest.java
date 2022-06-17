package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Employee;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.EmployeeFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {
    //springboot selects any random, and use that as the port
    @LocalServerPort
    private int port;

    @Autowired
    private EmployeeController controller;
    //Used to test our web services
    @Autowired private TestRestTemplate restTemplate;

    private Employee employee;
    private Name name;
    private String baseUrl;

    @BeforeEach
    void setUp(){
        name = NameFactory.createName("Shuaib", "", "Allie");
        employee = EmployeeFactory.createEmployee("1", "219113505@mycput.ac.za", name);
        baseUrl = "http://localhost:" + port + "/school-management/employee/";
    }

    @Test
    @Order(1)
    void save() {
        String url = baseUrl + "save";
        ResponseEntity<Employee> response = restTemplate.postForEntity(url, this.employee, Employee.class);
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
        String url = baseUrl + "find/" + this.employee.getStaffId();
        ResponseEntity<Employee> response = this.restTemplate.getForEntity(url, Employee.class);
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
        String url = baseUrl + "delete/" + employee.getStaffId();
        restTemplate.delete(url);
        System.out.println(url);
    }

    @Test
    @Order(5)
    void delete() {
        String url = baseUrl + "delete-employee";
        restTemplate.delete(url);
        System.out.println(url);
    }

    @Test
    @Order(3)
    void findAll() {
        String url = baseUrl + "all";
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(url, Employee[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                //length is the number of insertion done
                () -> assertTrue(response.getBody().length == 1)
        );
    }
}
