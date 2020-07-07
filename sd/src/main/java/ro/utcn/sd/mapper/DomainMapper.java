package ro.utcn.sd.mapper;

import org.springframework.stereotype.Component;
import ro.utcn.sd.dto.DomainDto;
import ro.utcn.sd.entity.Domain;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DomainMapper {

    public DomainDto getDomainDtoFromListOfDomain(List<Domain> domain){
        DomainDto domainDto = new DomainDto();
        domainDto.setDomains(domain.stream().map(e -> e.getDomain()).collect(Collectors.toList()));
        return domainDto;
    }
}
