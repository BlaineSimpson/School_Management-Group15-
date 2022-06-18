package za.ac.cput.service;
/*ADP 3 Assignment 2
Author : Sinovuyo Mlanjeni(219220387)
 */


import za.ac.cput.domain.StudentAddress;

import java.util.List;


public interface StudentAddressService extends IService<StudentAddress, String>{
    List<StudentAddress> findAll();

    void deleteById(String id);
}
