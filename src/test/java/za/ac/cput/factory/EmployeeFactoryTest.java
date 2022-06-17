package za.ac.cput.factory;

/*
Author: Shuaib Allie (217148867)
 */

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Employee;
import za.ac.cput.domain.Name;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFactoryTest {
    Name name = NameFactory.createName("Michael", "", "Jackson");
    private EmailValidator validator;
    String validEmail = "michael.jackson@gmail.com";
    String invalidEmail = "michael.jackson@.z---a";

    @BeforeEach
    public void setUp() {
        validator = EmailValidator.getInstance();
    }

    @Test
    void validEmail() {
        assertTrue(validator.isValid(validEmail));
    }

    @Test
    void invalidEmail() {
        assertFalse(validator.isValid(invalidEmail),"This test should be failed if assertTrue");
    }

    @Test
    void notNull(){
        Employee employee = EmployeeFactory.createEmployee("5", validEmail, name);
        assertNotNull(employee);
    }
    @Test
    void idNullException(){
        assertThrows(IllegalArgumentException.class, () -> EmployeeFactory.createEmployee(null, validEmail, name));
    }

    @Test
    void nameNullException(){
        assertThrows(IllegalArgumentException.class, () -> EmployeeFactory.createEmployee("5", validEmail, null) );
    }

    @Test
    void emailNullException(){
        assertThrows(IllegalArgumentException.class, () -> EmployeeFactory.createEmployee("5", null, name) );
    }
    @Test
    void allNull(){
        assertThrows(IllegalArgumentException.class, () -> EmployeeFactory.createEmployee(null, null, null) );
    }

    @Test
    void invalidEmailException(){
        assertThrows(IllegalArgumentException.class, () -> EmployeeFactory.createEmployee("5", invalidEmail, name) );
    }
}