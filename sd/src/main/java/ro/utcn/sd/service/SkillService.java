package ro.utcn.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.Skill;
import ro.utcn.sd.repository.SkillRepository;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public void addSKill(Skill skill){
        skillRepository.save(skill);
    }
}
