package za.ac.cput.service;

import za.ac.cput.domain.StudentAddress;

import java.util.List;


public interface StudentAddressService extends IService<StudentAddress, String>{
    List<StudentAddress> findAll();
}
