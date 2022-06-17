package za.ac.cput.service.impl;
/*ADP 3 Assignment 2
Author : Sinovuyo Mlanjeni(219220387)
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.StudentAddress;
import za.ac.cput.repository.StudentAddressRepository;
import za.ac.cput.service.StudentAddressService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentAddressServiceImp implements StudentAddressService {
    private final StudentAddressRepository strepository;

    @Autowired
    public StudentAddressServiceImp(StudentAddressRepository strepository){

        this.strepository = strepository;
    }
    @Override
    public StudentAddress save(StudentAddress studentAddress) {
        return this.strepository.save(studentAddress);
    }
    @Override
    public Optional<StudentAddress> findById(String id) {

        return this.strepository.findById(id);
    }

    @Override
    public void delete(StudentAddress studentAddress) {

        this.strepository.delete(studentAddress);
    }

    @Override
    public List<StudentAddress> findAll() {
        return this.strepository.findAll();
    }

}
