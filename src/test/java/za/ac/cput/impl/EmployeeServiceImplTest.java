package za.ac.cput.impl;

/*
Author: Shuaib Allie (217148867)
 */

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Employee;
import za.ac.cput.factory.EmployeeFactory;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.service.impl.EmployeeServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeServiceImpl service;
    private final Name name = NameFactory.createName("Shuaib", "", "Allie");
    private final Name name2 = NameFactory.createName("Shuaib2", "", "Allie2");
    private final Employee employee = EmployeeFactory.createEmployee("1A2", "217148867@mycput.ac.za", name);
    private final Employee employee2 = EmployeeFactory.createEmployee("2B3", "217148867@mycput.ac.za", name2);

    @Test
    @Order(1)
    void save() {
        Employee create = this.service.save(this.employee);
        Employee create2 = this.service.save(this.employee2);
        assertNotNull(create);
        assertNotNull(create2);
        System.out.println(create);
    }

    @Test
    @Order(2)
    void findById() {
        Optional<Employee> read = this.service.findById(this.employee.getStaffId()); //reads the first employee only
        assertAll(
                () -> assertTrue(read.isPresent()),
                () -> assertEquals(this.employee, read.get())
        );
        System.out.println(read);
    }

    @Test
    @Order(4)
    void delete() {
        service.delete(employee);
        List<Employee> list = this.service.findAll();
        System.out.println(list);
    }

    @Test
    @Order(3)
    void findAll() {
        List<Employee> list = this.service.findAll();
        System.out.println(list);
    }

    @Test
    @Order(5)
    void deleteById() {
        service.deleteById("2B3");
        List<Employee> list = this.service.findAll();
        System.out.println(list);
    }
}