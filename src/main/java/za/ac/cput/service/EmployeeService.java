package za.ac.cput.service;

/*
Author: Shuaib Allie (217148867)
 */

import za.ac.cput.domain.Employee;

import java.util.List;

public interface EmployeeService extends IService<Employee, String> {
    List<Employee> findAll();
}
