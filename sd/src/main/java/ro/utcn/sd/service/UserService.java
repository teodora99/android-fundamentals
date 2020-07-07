package ro.utcn.sd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.Profile;
import ro.utcn.sd.entity.User;
import ro.utcn.sd.repository.ProfileRepository;
import ro.utcn.sd.repository.RoleRepository;
import ro.utcn.sd.repository.UserRepository;
import ro.utcn.sd.utils.EmailUtils;
import ro.utcn.sd.utils.UserUtils;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private EmailUtils emailUtils;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     *
     * @param profile the profile that ve are looking for
     * @return the profile exits, we return the user that the profile belong to, otherwise null
     */
    public User getByProfile(Profile profile){
        Optional<User> possibleUser = userRepository.findByProfile(profileRepository.findById(profile.getId()).get());
        if(possibleUser.isPresent()){
            log.info("The user having the profile " + profile.getId() + " has been found");
            return possibleUser.get();
        }
        log.error("The profile dose not belong to a user");
        return null;
    }

    /**
     *
     * @param user the user that wants to register
     */
    public void addUser(User user){
        user.getProfile().setPassword(passwordEncoder.encode(user.getProfile().getPassword()));
        userRepository.save(user);
        user.getProfile().setUser(user);
        profileRepository.save(user.getProfile());
        log.info("The user has been saved");
    }

    public User getUserById(String id){
         return userRepository.findById(id).get();
    }


    /**
     * This method will send an email with the reset code
     * @param email the email on which the reset code will be send
     */
    public void resetPassword(String email){
        String resetCode = userUtils.generateRandomCode();
        emailUtils.resetPassword(email, resetCode);
        User user = profileRepository.findByEmail(email).get().getUser();
        user.setResetCode(resetCode);
        userRepository.save(user);
    }

    /**
     * This method will check if the code is ok or not
     * @param email the email of the user that wants to change its password
     * @param codeGiven the code that the user adds
     * @return if the code that was enter by the user is equal to the one on the email
     */
    public boolean checkResetCode(String email, String codeGiven){
        if(profileRepository.findByEmail(email).get().getUser().getResetCode().equals(codeGiven))
            return true;
        else
            return false;
    }

    /**
     * This method will change the password
     * @param user the user that wants to change the password
     * @param newPassword the new password
     */
    public void changePassword(User user, String newPassword){
        user.getProfile().setPassword(passwordEncoder.encode(newPassword));
        profileRepository.save(user.getProfile());
    }

    public User getUserByEmail(String email){
        if(profileRepository.findByEmail(email).isPresent())
            return profileRepository.findByEmail(email).get().getUser();
        else
            return null;
    }
}
