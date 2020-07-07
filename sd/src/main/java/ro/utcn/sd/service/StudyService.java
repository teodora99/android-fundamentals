package ro.utcn.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.Study;
import ro.utcn.sd.repository.StudyRepository;

@Service
public class StudyService {

    @Autowired
    private StudyRepository studyRepository;

    public void addStudy(Study study){
        studyRepository.save(study);
    }
}
