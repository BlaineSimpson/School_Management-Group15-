package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Student;
import za.ac.cput.service.StudentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("school-management/student")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    //@Valid checks that the data that is sent to the method is valid or not
    @PostMapping("save")
    public ResponseEntity<Student> save(@Valid @RequestBody Student student){
        log.info("Save Request: {}", student); //logging using lombok
        Student insert = studentService.save(student);
        return ResponseEntity.ok(insert);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Optional<Student>> findById(@PathVariable String id){
        log.info("Read request: {}", id);
        Optional<Student> find = studentService.findById(id);
        return ResponseEntity.ok(find);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        log.info("Delete request: {}", id);
        this.studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete-student")
    public ResponseEntity<Void> delete(Student student){
        log.info("Delete request: {}", student);
        this.studentService.delete(student);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<Student>> findAll(){
        List<Student> studentList = studentService.findAll();
        return ResponseEntity.ok(studentList);
    }
}
