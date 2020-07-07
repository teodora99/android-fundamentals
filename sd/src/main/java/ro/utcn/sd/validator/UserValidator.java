package ro.utcn.sd.validator;

import org.springframework.stereotype.Component;
import ro.utcn.sd.dto.RegisterDto;
import ro.utcn.sd.entity.User;

@Component
public class UserValidator {

    public boolean validateResume(User user){
        if(user.getProfile().getStudies().isEmpty()){
            return false;
        }
        if(user.getProfile().getWorkExperiences().isEmpty()){
            return false;
        }
            return true;
    }

    public boolean validateRegistration(RegisterDto registerDTO){
        return true;
    }
}
