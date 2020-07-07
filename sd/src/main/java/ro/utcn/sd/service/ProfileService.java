package ro.utcn.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.*;
import ro.utcn.sd.repository.ProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile seeProfile(User user){
        Optional<Profile> profile = profileRepository.findByUser(user);
        if(profile.isPresent()){
            return profile.get();
        }
        return null;
    }

    public void updateSkills(List<Skill> skills, List<Study> studies, List<WorkExperience> workExperiences, User user){
        Optional<Profile> profile = profileRepository.findByUser(user);
        Profile newProfile = new Profile.ProfileBuilder(profile.get().getId(), profile.get().getUsername(),profile.get().getEmail(), profile.get().getPassword(), profile.get().getFirstName(), profile.get().getLastName())
                .addSkills(skills)
                .addStudies(studies)
                .addWorkExperience(workExperiences)
                .addUser(user)
                .build();
    }

    public void createProfile(Profile profile){
        profileRepository.save(profile);
    }


}
