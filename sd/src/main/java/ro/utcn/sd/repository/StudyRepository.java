package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.Skill;
import ro.utcn.sd.entity.Study;

@Repository
public interface StudyRepository extends JpaRepository<Study, String> {
}
