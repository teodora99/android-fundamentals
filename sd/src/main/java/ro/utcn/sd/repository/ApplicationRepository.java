package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.Application;
import ro.utcn.sd.entity.Internship;
import ro.utcn.sd.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String> {

    List<Application> findAllByStudent(User student);
    Optional<Application> findAllByStudentAndInternship(User student, Internship internship);
    List<Application> findAllByInternship(Internship internship);
    List<Application> findAllByStudentAndDate(User student, LocalDate date);
}
