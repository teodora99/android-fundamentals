package ro.utcn.sd.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.Domain;
import ro.utcn.sd.repository.DomainRepository;

import java.util.List;

@Service
public class DomainService {

    @Autowired
    private DomainRepository domainRepository;

    public List<Domain> getDomains(){
        return domainRepository.findAll();
    }

    public Domain getDomainByName(String name){
        return domainRepository.findByDomain(name);
    }
}
