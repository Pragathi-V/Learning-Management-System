package upskill.casestudy.mentorService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import upskill.casestudy.mentorService.entity.Mentor;

import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Long> {

    Optional<Mentor> findByEmail(String email);
    
    Optional<Mentor> findByEmpId(Long empId);
    
    @Transactional
    String deleteByEmpId(Long empId);

}
