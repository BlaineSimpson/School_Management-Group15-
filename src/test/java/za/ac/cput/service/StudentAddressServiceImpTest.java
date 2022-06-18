package za.ac.cput.service;
/*ADP 3 Assignment 2
Author : Sinovuyo Mlanjeni(219220387)
 */


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.service.impl.StudentAddressServiceImpl;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class StudentAddressServiceImpTest {
    @Autowired
    private StudentAddressServiceImpl service;
    private final Country country = CountryFactory.createCountry("5A", "James");
    private final Country country2 = CountryFactory.createCountry("5A", "James2");
    private final City city = CityFactory.createCity("5A", "James", country);
    private final City city2 = CityFactory.createCity("5A", "James2", country);
    private final Address address = AddressFactory.createAddress("2", "ATB", "5", "John", 542,  city);
    private final Address address2 = AddressFactory.createAddress("2", "ATB", "5", "2John", 542,  city);
    private final StudentAddress studentAddress = StudentAddressFactory.createStudentAddress("Lee",address);
    private final StudentAddress studentAddress2 = StudentAddressFactory.createStudentAddress("Lee2",address);

    @Test
    @Order(1)
    void save() {
        StudentAddress create = this.service.save(this.studentAddress);
        StudentAddress create2 = this.service.save(this.studentAddress2);
        assertNotNull(create);
        assertNotNull(create2);
        System.out.println(create);
    }

    @Test
    @Order(2)
    void findById() {
        Optional<StudentAddress> read = this.service.findById(this.studentAddress.getStudentId()); //reads the first studentAddress only
        assertAll(
                () -> assertTrue(read.isPresent()),
                () -> assertEquals(this.studentAddress, read.get())
        );
        System.out.println(read);
    }

    @Test
    @Order(4)
    void delete() {
        service.delete(studentAddress); //deleted studentAddress 1
        List<StudentAddress> list = this.service.findAll();
        System.out.println(list);
    }
    @Test
    @Order(3)
    void findAll() {
        List<StudentAddress> list = this.service.findAll();
        System.out.println(list); //displays both students
    }

    @Test
    @Order(5)
    void deleteById() {
        service.deleteById("2"); //Deleted student 2 (List should be empty)
        List<StudentAddress> list = this.service.findAll();
        System.out.println(list);
    }

}