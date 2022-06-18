/* StudentControllerTest.java
 Testing the student controller
 Author: Ali Mohamed (219113505)
 Date: 17 June 2022
*/
package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Student;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.StudentFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentControllerTest {
    //springboot selects any random, and use that as the port
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController controller;
    //Used to test our web services
    @Autowired private TestRestTemplate restTemplate;

    private Student student;
    private Name name;
    private String baseUrl;

    @BeforeEach
    void setUp(){
        name = NameFactory.createName("Ali", "", "Mohamed");
        student = StudentFactory.createStudent("1", "219113505@mycput.ac.za", name);
        baseUrl = "http://localhost:" + port + "/school-management/student/";
    }

    @Test
    @Order(1)
    void save() {
        String url = baseUrl + "save";
        ResponseEntity<Student> response = restTemplate.postForEntity(url, this.student, Student.class);
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
        String url = baseUrl + "find/" + this.student.getStudentId();
        ResponseEntity<Student> response = this.restTemplate.getForEntity(url, Student.class);
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
        String url = baseUrl + "delete/" + student.getStudentId();
        restTemplate.delete(url);
        System.out.println(url);
    }

    @Test
    @Order(5)
    void delete() {
        String url = baseUrl + "delete-student";
        restTemplate.delete(url);
        System.out.println(url);
    }

    @Test
    @Order(3)
    void findAll() {
        String url = baseUrl + "all";
        ResponseEntity<Student[]> response = restTemplate.getForEntity(url, Student[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                //length is the number of insertion done
                () -> assertTrue(response.getBody().length == 1)
        );
    }
}