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

import javax.validation.constraints.Email;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private EmployeeController controller;

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

                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),

                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(3)
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
    @Order(5)
    void deleteById() {
        String url = baseUrl + "delete/" + employee.getStaffId();
        restTemplate.delete(url);
        System.out.println(url);
    }

    @Test
    @Order(6)
    void delete() {
        String url = baseUrl + "delete-employee";
        restTemplate.delete(url);
        System.out.println(url);
    }

    @Test
    @Order(4)
    void findAll() {
        String url = baseUrl + "all";
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(url, Employee[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),

                () -> assertTrue(response.getBody().length == 1)
        );
    }
    //Question 5
@Test
@Order(2)
void findByEmail(){
 String url = baseUrl + "find-by-email/" + this.employee.getEmail();
    ResponseEntity<Employee> response = this.restTemplate.getForEntity(url, Employee.class);
    assertAll(
            () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
            () -> assertNotNull(response.getBody())
    );
 System.out.println(url);


        }

    }

