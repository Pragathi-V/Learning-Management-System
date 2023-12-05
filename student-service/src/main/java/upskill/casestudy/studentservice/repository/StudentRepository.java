package upskill.casestudy.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import upskill.casestudy.studentservice.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByEmail(String email);
    
    List<Student> findByBatchCode(String batchCode);
}
