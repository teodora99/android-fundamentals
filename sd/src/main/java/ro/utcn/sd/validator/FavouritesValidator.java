package ro.utcn.sd.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.utcn.sd.entity.Internship;
import ro.utcn.sd.entity.User;
import ro.utcn.sd.repository.FavouriteInternshipRepository;

@Component
public class FavouritesValidator {

    @Autowired
    private FavouriteInternshipRepository favouriteInternshipRepository;

    /**
     *
     * @param internship the internship that wants to be added to the list
     * @param user the user that the list belomgs to
     * @return true if the internship can be added, and false if it already exists
     */
    public boolean validateInternshipToAddInFavourite(Internship internship, User user){
            if(favouriteInternshipRepository.findAllByStudentAndInternship(user, internship).isPresent())
                return false;
            return true;
    }

}
