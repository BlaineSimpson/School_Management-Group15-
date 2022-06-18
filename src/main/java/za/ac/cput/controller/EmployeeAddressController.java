package za.ac.cput.controller;
/*
Author: Blaine Simpson
Student Nr: 218020171
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.EmployeeAddress;
import za.ac.cput.service.EmployeeAddressService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("school-management/employeeAddress")
@Slf4j
public class EmployeeAddressController {

    private final EmployeeAddressService employeeAddressService;

    @Autowired
    public EmployeeAddressController(EmployeeAddressService employeeAddressService){
     this.employeeAddressService = employeeAddressService;
     //Valid check
    }
    @PostMapping("save")
    public ResponseEntity<EmployeeAddress> save(@Valid @RequestBody EmployeeAddress employeeAddress){
      log.info("Save Request: {}",employeeAddress);
      EmployeeAddress insert = employeeAddressService.save(employeeAddress);
      return ResponseEntity.ok(insert);

    }
    @GetMapping("find/id")
    public ResponseEntity<Optional<EmployeeAddress>> findByid(@PathVariable String id){
        log.info("Read request: {}", id);
        Optional<EmployeeAddress> find =employeeAddressService.findById(id);
        return ResponseEntity.ok(find);

    }
    @DeleteMapping("delete_EmployeeAddress")
    public ResponseEntity<Void> delete(EmployeeAddress employeeAddress){
        log.info ("Delete request: {}", employeeAddress);
        this.employeeAddressService.delete(employeeAddress);
        return ResponseEntity.noContent().build();


    }
    @GetMapping("all")
    public ResponseEntity <List<EmployeeAddress>> findAll(){
        List<EmployeeAddress> employeeAddressList =employeeAddressService.findAll();
        return ResponseEntity.ok(employeeAddressList);
    }

}
