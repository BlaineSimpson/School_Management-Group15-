package za.ac.cput.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Student;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.StudentFactory;
import za.ac.cput.service.impl.StudentServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentServiceImplTest {

    @Autowired
    private StudentServiceImpl service;
    private final Name name = NameFactory.createName("Ali", "", "Mohamed");
    private final Name name2 = NameFactory.createName("Ali2", "", "Mohamed2");
    private final Student student = StudentFactory.createStudent("1", "219113505@mycput.ac.za", name);
    private final Student student2 = StudentFactory.createStudent("2", "219113505@mycput.ac.za", name2);

    @Test
    @Order(1)
    void save() {
        Student create = this.service.save(this.student);
        Student create2 = this.service.save(this.student2);
        assertNotNull(create);
        assertNotNull(create2);
        System.out.println(create);
    }

    @Test
    @Order(2)
    void findById() {
        Optional<Student> read = this.service.findById(this.student.getStudentId()); //reads the first student only
        assertAll(
                () -> assertTrue(read.isPresent()),
                () -> assertEquals(this.student, read.get())
        );
        System.out.println(read);
    }

    @Test
    @Order(4)
    void delete() {
        service.delete(student); //deleted student 1
        List<Student> list = this.service.findAll();
        System.out.println(list);
    }

    @Test
    @Order(3)
    void findAll() {
        List<Student> list = this.service.findAll();
        System.out.println(list); //displays both students
    }

    @Test
    @Order(5)
    void deleteById() {
        service.deleteById("2"); //Deleted student 2 (List should be empty)
        List<Student> list = this.service.findAll();
        System.out.println(list);
    }
}