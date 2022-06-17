/* StudentFactoryTest.java
 Factory Test for the Student class
 Author: Ali Mohamed (219113505)
 Date: 13 June 2022
*/
package za.ac.cput.factory;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentFactoryTest {

    Name name = NameFactory.createName("Ali", "", "Mohamed");
    private EmailValidator validator;
    String validEmail = "219113505@mycput.ac.za";
    String invalidEmail = "219113505@mycput.ac.z---a";

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
        assertFalse(validator.isValid(invalidEmail),"This test should fail if assertTrue");
    }

    @Test
    void notNull(){
        Student student = StudentFactory.createStudent("1", validEmail, name);
        assertNotNull(student);
    }
    @Test
    void idNullException(){
        assertThrows(IllegalArgumentException.class, () -> StudentFactory.createStudent(null, validEmail, name));
    }

    @Test
    void nameNullException(){
        assertThrows(IllegalArgumentException.class, () -> StudentFactory.createStudent("1", validEmail, null) );
    }

    @Test
    void emailNullException(){
        assertThrows(IllegalArgumentException.class, () -> StudentFactory.createStudent("1", null, name) );
    }
    @Test
    void allNull(){
        assertThrows(IllegalArgumentException.class, () -> StudentFactory.createStudent(null, null, null) );
    }

    @Test
    void invalidEmailException(){
        assertThrows(IllegalArgumentException.class, () -> StudentFactory.createStudent("1", invalidEmail, name) );
    }

}