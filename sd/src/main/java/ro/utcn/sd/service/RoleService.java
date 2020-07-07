package ro.utcn.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.Role;
import ro.utcn.sd.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findRole(String role){
        return roleRepository.findByRole(role);
    }
}
