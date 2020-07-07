package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByRole(String Role);
}
