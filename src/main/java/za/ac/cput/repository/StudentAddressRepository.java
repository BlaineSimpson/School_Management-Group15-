package za.ac.cput.repository;
/*ADP 3 Assignment 2
Author : Sinovuyo Mlanjeni(219220387)
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.StudentAddress;

@Repository
public interface StudentAddressRepository extends JpaRepository<StudentAddress,String>{
}
