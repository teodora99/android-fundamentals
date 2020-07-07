package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.Domain;

@Repository
public interface DomainRepository extends JpaRepository<Domain, String> {

    Domain findByDomain(String domain);

}
