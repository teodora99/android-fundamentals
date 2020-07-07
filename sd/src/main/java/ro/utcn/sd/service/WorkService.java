package ro.utcn.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.WorkExperience;
import ro.utcn.sd.repository.WorkRepository;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    public void addWork(WorkExperience workExperience){
        workRepository.save(workExperience);
    }
}
