package ro.utcn.sd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.utcn.sd.messages.RootMessages;
import ro.utcn.sd.entity.Admin;
import ro.utcn.sd.entity.Profile;
import ro.utcn.sd.entity.User;
import ro.utcn.sd.repository.ProfileRepository;

import java.util.Optional;

@Service
public class LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProfileRepository profileRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     *
     * @param usernameOrEmail the username or the email of the user that tries to login.
     * @param password the password of the user that tries to login.
     * @return a message that specify the success or error of the operation.
     */
    public String login(String usernameOrEmail, String password){
        Optional<Profile> profile = profileRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if(profile.isPresent()) {
            log.info("The profile exists.");
            Admin possibleAdmin = adminService.getByProfile(profile.get());
            if (!(possibleAdmin == null)) {
                if (passwordEncoder.matches(password, possibleAdmin.getProfile().getPassword())) {
                    log.info("The profile belongs to an admin");
                    return possibleAdmin.getId() + " " + possibleAdmin.getRole().getRole();
                }
            }else{
                User possibleUser = userService.getByProfile(profile.get());
                if (!(possibleUser == null)) {
                    if (passwordEncoder.matches(password, possibleUser.getProfile().getPassword())) {
                        log.info("The profile belongs to an user");
                        return possibleUser.getId() + " " + possibleUser.getRole().getRole();
                    }
                }

            }
        }
        log.error("The profile dose not exist.");
        return null;
    }

}
