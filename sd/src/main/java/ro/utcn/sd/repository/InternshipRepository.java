package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.Internship;
import ro.utcn.sd.entity.User;

import java.util.List;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, String> {

    List<Internship> findAllByStatusApproved(Boolean statusApproved);
    List<Internship> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(String title, String description);
    List<Internship> findAllByCreator(User user);
}
