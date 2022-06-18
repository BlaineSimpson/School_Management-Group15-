package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Employee;
import za.ac.cput.domain.Name;
import za.ac.cput.service.EmployeeService;
import za.ac.cput.util.Helper;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("school-management/employee")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("save")
    public ResponseEntity<Employee> save(@Valid @RequestBody Employee employee) {
        log.info("Save Request: {}", employee); //logging using lombok
        Employee insert = employeeService.save(employee);
        return ResponseEntity.ok(insert);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable String id) {
        log.info("Read request: {}", id);
        Optional<Employee> find = employeeService.findById(id);
        return ResponseEntity.ok(find);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        log.info("Delete request: {}", id);
        this.employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete-employee")
    public ResponseEntity<Void> delete(Employee employee) {
        log.info("Delete request: {}", employee);
        this.employeeService.delete(employee);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employeeList = employeeService.findAll();
        return ResponseEntity.ok(employeeList);
    }

    //This is question5
    @GetMapping("find-by-email/{email}")
    public ResponseEntity<Name> findEmployeeByEmail(@PathVariable String email) {
        log.info("Find name by email request: {}", email);
        try {
            Helper.emailValid(email); //Check if email is valid
        } catch (IllegalArgumentException e) {
            log.info("Find name by email request error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Employee employee = this.employeeService.findEmployeeByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(employee.getName());
    }
}
