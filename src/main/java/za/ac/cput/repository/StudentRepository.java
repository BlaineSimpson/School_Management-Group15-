/*
StudentRepository.java
Repository interface for student
Author: Ali Mohamed (219113505)
Date: 15 June 2022
 */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
