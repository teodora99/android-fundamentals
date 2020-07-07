package ro.utcn.sd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.dto.InternshipDetailsDto;
import ro.utcn.sd.dto.InternshipDto;
import ro.utcn.sd.entity.Internship;
import ro.utcn.sd.entity.User;
import ro.utcn.sd.repository.InternshipRepository;
import ro.utcn.sd.utils.EmailUtils;

import java.util.List;
import java.util.Optional;

@Service
public class InternshipService {

    private static final Logger log = LoggerFactory.getLogger(InternshipService.class);

     @Autowired
    private InternshipRepository internshipRepository;

     @Autowired
     private EmailUtils emailUtils;

    /**
     *
     * @param internship the internship that will be saved or updated
     */
    public void createOrUpdateInternship(Internship internship) {
        internshipRepository.save(internship);
        log.info("The internship " + internship.getTitle() + " has been saved");
    }

    /**
     *
     * @param id the id that belongs to the internship we want to find
     * @return the found internship
     */
    public Internship findInternship(String id){
        Optional<Internship> possibleInternship = internshipRepository.findById(id);
        if(possibleInternship.isPresent()){
            log.info("The internship with the id " + id + " has been found");
            return possibleInternship.get();
        }
        log.error("The internship with the id " + id + " has not been found");
        return null;
    }

    /**
     *
     * @return a list with all the unapproved internships
     */
    public List<Internship> getAllUnapproved() {
        log.info("The list with all the unapproved internships has been returned");
        return internshipRepository.findAllByStatusApproved(false);
    }

    /**
     *
     * @param internship the internships that wants to be deleted
     */
    public void deleteInternship(Internship internship, String message){
        emailUtils.sendMailDelete(internship.getCreator().getProfile().getEmail(), message);
        internshipRepository.delete(internship);
        log.info("The internship has been deleted");
    }


    /**
     *
     * @param search the searsh string that the user introduced
     * @return the list of internships found
     */
    public List<Internship> searchInternship(String search){
        List<Internship> possibleInternship = internshipRepository.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(search, search);
            return possibleInternship;

    }

    /**
     *
     * @param internship the internship that wants to be added
     * @param user the user that wants to add it, needs to be a company
     */
    public void addNewInternship(Internship internship, User user){
        if(user.getRole().equals("COMPANY")){
            internship.setStatusApproved(false);
            internship.setCreator(user);
            internshipRepository.save(internship);
        }
    }

    public List<Internship> getAllApproved() {
        log.info("The list with all the approved internships has been returned");
        return internshipRepository.findAllByStatusApproved(true);
    }

    public Internship getDetailInternship(InternshipDto internshipDto){
       return internshipRepository.findById(internshipDto.getId()).get();
    }

    public List<Internship> getMyInternship(User user){
        return internshipRepository.findAllByCreator(user);
    }

    public void deleteInternshipCompany(Internship internship){
        internshipRepository.delete(internship);
        log.info("The internship has been deleted");
    }
}
