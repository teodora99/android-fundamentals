package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.WorkExperience;

@Repository
public interface WorkRepository extends JpaRepository<WorkExperience, String> {
}
