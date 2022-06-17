package za.ac.cput.service;
/*
Author: Blaine Simpson
Student Nr: 218020171
 */

import za.ac.cput.domain.EmployeeAddress;

import java.util.List;

public interface EmployeeAddressService extends IService <EmployeeAddress, String> {

    List<EmployeeAddress> findAll();
}
