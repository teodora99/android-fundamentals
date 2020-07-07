package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.FavouriteInternship;
import ro.utcn.sd.entity.Internship;
import ro.utcn.sd.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteInternshipRepository extends JpaRepository<FavouriteInternship, String> {

    List<FavouriteInternship> findAllByStudent(User student);
    Optional<FavouriteInternship> findAllByStudentAndInternship(User student, Internship internship);
    Optional<FavouriteInternship> findByStudentAndInternship(User student, Internship internship);
}
