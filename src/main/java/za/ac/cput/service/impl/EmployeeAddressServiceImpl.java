package za.ac.cput.service.impl;

/*
Author: Blaine Simpson
Student Nr: 218020171
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.EmployeeAddress;
import za.ac.cput.repository.EmployeeAddressRepository;
import za.ac.cput.service.EmployeeAddressService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeAddressServiceImpl implements EmployeeAddressService{


    private final EmployeeAddressRepository eaRepository;
    @Autowired
    public EmployeeAddressServiceImpl(EmployeeAddressRepository eaRepository) { this.eaRepository = eaRepository; }


    @Override
    public List<EmployeeAddress> findAll() {
        return null;
    }

    @Override
    public EmployeeAddress save(EmployeeAddress employeeAddress) {
        return this.eaRepository.save(employeeAddress);
    }

    @Override
    public Optional<EmployeeAddress> findById(String s) {
        return this.eaRepository.findById(s);
    }

    @Override
    public void delete(EmployeeAddress employeeAddress) {
        this.eaRepository.delete(employeeAddress);
    }
}
