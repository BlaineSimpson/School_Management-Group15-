package za.ac.cput.repository;
/*
Author: Blaine Simpson
Student Nr: 218020171
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.EmployeeAddress;



@Repository
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, String> {

}
