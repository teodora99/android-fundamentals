package ro.utcn.sd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.*;
import ro.utcn.sd.messages.RootMessages;
import ro.utcn.sd.repository.AdminRepository;
import ro.utcn.sd.repository.ProfileRepository;
import ro.utcn.sd.repository.RoleRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService {

    private static final Logger log = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     *
     * @param internship the internship that wants to be approved
     * @return a message suggesting that the operation whether a success or not
     */
    public String approveAddedInternship(Internship internship, Admin admin){
        Internship internship1 = internshipService.findInternship(internship.getId());
        if(internship1.getStatusApproved() == false) {
            internship1.setStatusApproved(true);
            internship1.setAdmin(admin);
            internshipService.createOrUpdateInternship(internship1);
            log.info("You have approved the internship");
            return RootMessages.DEFAULT_SUCCESS;
        }else
        {
            log.error("The internship is already approved");
            return RootMessages.DEFAULT_APPROVE_ERROR;
        }
    }

    /**
     *
     * @param profile the profile that ve are looking for
     * @return the profile exits, we return the admin that the profile belong to, otherwise null
     */
    public Admin getByProfile(Profile profile){
        Optional<Admin> possibleAdmin = adminRepository.findByProfile(profileRepository.findById(profile.getId()).get());

        if(possibleAdmin.isPresent()){
            log.info("The admin having the profile" + profile.getId() + "has been found");
                return possibleAdmin.get();
            }
        log.error("The profile dose not belong to a admin");
        return null;
    }

    public void addAdmin(String firstName, String lastName, String email, String username, String password ){
        Admin admin = new Admin();
        Role role = roleRepository.findByRole("ADMIN");
        Profile profile = new Profile.ProfileBuilder(UUID.randomUUID().toString(), username, email, passwordEncoder.encode(password), firstName, lastName)
                .build();
        admin.setId(UUID.randomUUID().toString());
        admin.setProfile(profile);
        admin.setRole(role);
        adminRepository.save(admin);
        log.info("The admin has been saved");
    }

    public Admin getAdminById(String id){
        return adminRepository.findById(id).get();
    }

}
