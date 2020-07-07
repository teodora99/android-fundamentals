package ro.utcn.sd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.sd.dto.DomainDto;
import ro.utcn.sd.mapper.DomainMapper;
import ro.utcn.sd.service.DomainService;

@RestController
@RequestMapping("/domain")
public class DomainController {

    @Autowired
    private DomainService domainService;

    @Autowired
    private DomainMapper domainMapper;

    @GetMapping("/all")
    public DomainDto getDomains(){
        return domainMapper.getDomainDtoFromListOfDomain(domainService.getDomains());
    }
}
