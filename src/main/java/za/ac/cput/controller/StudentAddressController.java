package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.StudentAddress;
import za.ac.cput.service.StudentAddressService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("school-management/student-address/")
@Slf4j

public class StudentAddressController {
    private final StudentAddressService service;

    @Autowired
    public StudentAddressController(StudentAddressService studentAddressService){
        this.service = studentAddressService;
    }
    @PostMapping("save")
    public ResponseEntity<StudentAddress> save(@Valid @RequestBody StudentAddress studentAddress){
        log.info("Save request: {}", studentAddress);
        StudentAddress save = service.save(studentAddress);
        return ResponseEntity.ok(save);
    }

    @GetMapping("find-by-id/{id}")
    public ResponseEntity<StudentAddress> read(@PathVariable String id){
        log.info("Read by id request: {}", id);
        StudentAddress studentAddress = this.service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(studentAddress);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        log.info("Delete request{}", id);
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("all")
    public ResponseEntity<List<StudentAddress>> findAll(){
        List<StudentAddress> studentAddressList = this.service.findAll();
        return ResponseEntity.ok(studentAddressList);
    }

}


