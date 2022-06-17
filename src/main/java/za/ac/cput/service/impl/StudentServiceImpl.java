/* StudentServiceImpl.java
 Implementation for student service
 Author: Ali Mohamed (219113505)
 Date: 15 June 2022
*/
package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.StudentRepository;
import za.ac.cput.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository){
        this.repository = repository;
    }

    @Override
    public Student save(Student student) {
        return this.repository.save(student);
    }

    @Override
    public Optional<Student> findById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(Student student) {
        this.repository.delete(student);
    }

    @Override
    public List<Student> findAll() {
        return this.repository.findAll();
    }

    @Override
    public void deleteById(String id) {
        Optional<Student> student = findById(id);
        student.ifPresent(this::delete);
    }
}
