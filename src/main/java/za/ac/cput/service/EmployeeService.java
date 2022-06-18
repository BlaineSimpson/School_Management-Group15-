package za.ac.cput.service;

/*
Author: Shuaib Allie (217148867)
 */

import za.ac.cput.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends IService<Employee, String> {
    List<Employee> findAll();

    void deleteById(String id);

    Optional<Employee> findEmployeeByEmail(String email);//Question 5 validate email
}
