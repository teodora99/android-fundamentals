package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {


}
