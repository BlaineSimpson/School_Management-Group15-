package za.ac.cput.controller;
/*
This is term two group assignment
Author: Sinovuyo Mlanjeni (219220387)
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import za.ac.cput.domain.StudentAddress;
import za.ac.cput.service.StudentAddressService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("school-management/student")
@Slf4j
public class StudentAddressController {

    private final StudentAddressService studentAddressService;

    @Autowired
    public StudentAddressController(StudentAddressService studentAddressService) {
        this.studentAddressService = studentAddressService;
    }
    //@Valid checks that the data that is sent to the method is valid or not
    @PostMapping("save")
    public ResponseEntity<StudentAddress> save(@Valid @RequestBody StudentAddress studentAddress){
        log.info("Save Request: {}", studentAddress); //logging using lombok
        StudentAddress insert = studentAddressService.save(studentAddress);
        return ResponseEntity.ok(insert);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Optional<StudentAddress>> findById(@PathVariable String id){
        log.info("Read request: {}", id);
        Optional<StudentAddress> find = studentAddressService.findById(id);
        return ResponseEntity.ok(find);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        log.info("Delete request: {}", id);
        this.studentAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete-student")
    public ResponseEntity<Void> delete(StudentAddress studentAddress){
        log.info("Delete request: {}", studentAddress);
        this.studentAddressService.delete(studentAddress);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<StudentAddress>> findAll(){
        List<StudentAddress> studentAddressList = studentAddressService.findAll();
        return ResponseEntity.ok(studentAddressList);
    }


}
