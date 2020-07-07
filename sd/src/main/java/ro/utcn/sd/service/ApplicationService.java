package ro.utcn.sd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.dto.InternshipDto;
import ro.utcn.sd.dto.UserDto;
import ro.utcn.sd.entity.Application;
import ro.utcn.sd.entity.Internship;
import ro.utcn.sd.entity.User;
import ro.utcn.sd.messages.RootMessages;
import ro.utcn.sd.repository.ApplicationRepository;
import ro.utcn.sd.utils.EmailUtils;
import ro.utcn.sd.validator.UserValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private static final Logger log = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private EmailUtils emailUtils;

    /**
     *
     * @param internship the internship that the user wants to apply to
     * @param user the user that wants to apply to internship
     * @param additionalNote the extra information that the user wants to add to the resume
     * @return a message containing weather the operation was performed successfully or not
     */
    public String applyToInternship(Internship internship, User user, String additionalNote) {

        if(userValidator.validateResume(user)) {
            if(!applicationRepository.findAllByStudentAndInternship(user, internship).isPresent()) {
                if(applicationRepository.findAllByStudentAndDate(user, LocalDate.now()).size() < 3) {
                    Application appliedList = new Application(UUID.randomUUID().toString(), LocalDate.now(), additionalNote, user, internship);
                    applicationRepository.save(appliedList);
                    emailUtils.applicationConfirmation(user.getProfile().getEmail(), appliedList);
                    log.info("The user " + user.getProfile().getFirstName() + " has successfully applied to the internship");
                    return RootMessages.DEFAULT_ADD_SUCCESS;
                }else
                    return "You have applied to 3 internships already";
            }else {
                log.error("The user " + user.getProfile().getFirstName() + " has already applied to the internship");
                return RootMessages.DEFAULT_ADD_ALREADY;
            }
        }

        log.error("The user " + user.getProfile().getFirstName() + " has NOT applied to the internship");
        return RootMessages.DEFAULT_ADD_ERROR;
    }

    /**
     *
     * @param user the user that wants to see its applications list
     * @return a list of internships that the user has in the applications list
     */
    public List<InternshipDto> seeListOfApplied(User user) {
        List<InternshipDto> internships = new ArrayList<>();
        List<Application> applications = applicationRepository.findAllByStudent(user);
        internships =applications.stream()
                .map(e ->  new InternshipDto(e.getInternship().getId(), e.getInternship().getTitle(), e.getInternship().getCreator().getProfile().getFirstName(), e.getInternship().getApplyUntil().toString(), false, e.getInternship().getStatusApproved().toString(), e.getDate().toString())
                ).collect(Collectors.toList());

        log.info("The list of applied internships has been returned");
        return internships;
    }

    public List<UserDto> seeListOfApplicants(Internship internship) {
        List<UserDto> users = new ArrayList<>();
        List<Application> applications = applicationRepository.findAllByInternship(internship);
       users = applications.stream()
               .map(e -> new UserDto(e.getStudent().getProfile().getFirstName(),
                       e.getStudent().getProfile().getLastName(), e.getStudent().getId(), e.getDate().toString())).collect(Collectors.toList());


        log.info("The list of applicants has been returned");
        return users;
    }

}
