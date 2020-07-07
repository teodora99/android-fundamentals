package ro.utcn.sd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.FavouriteInternship;
import ro.utcn.sd.entity.Internship;
import ro.utcn.sd.entity.User;
import ro.utcn.sd.messages.RootMessages;
import ro.utcn.sd.repository.FavouriteInternshipRepository;
import ro.utcn.sd.validator.FavouritesValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FavouriteInternshipService {

    private static final Logger log = LoggerFactory.getLogger(FavouriteInternshipService.class);

    @Autowired
    private FavouriteInternshipRepository favouriteInternshipRepository;

    @Autowired
    private FavouritesValidator favouritesValidator;

    /**
     *
     * @param internship the internship that we want to add to favourite
     * @param user the user that adds the internship
     * @return a message suggesting that the operation whether a success or not
     */
    public String addToFavourites(Internship internship, User user) {
        if(favouritesValidator.validateInternshipToAddInFavourite(internship, user)) {
            FavouriteInternship favouriteList = new FavouriteInternship(UUID.randomUUID().toString(), user, internship);
            favouriteInternshipRepository.save(favouriteList);

            log.info("The internship " + internship.getTitle() + " has been successfully added to the list");
            return RootMessages.DEFAULT_INSERT_SUCCESS;
        }else{
            log.error("The internship " + internship.getTitle() + " has been already added to the list");
            return RootMessages.DEFAULT_FAVOURITE_ERROR;
        }
    }

    /**
     *
     * @param user the user that wants to see its favourite list
     * @return a list of internships that the user has in the favourite list
     */
    public List<Internship> seeListOfFavourites(User user) {
       List<Internship> internships = new ArrayList<>();
        List<FavouriteInternship> favouriteInternships = favouriteInternshipRepository.findAllByStudent(user);
        for (FavouriteInternship favouriteInternship : favouriteInternships) {
        if(favouriteInternship.getInternship().getStatusApproved())
            internships.add(favouriteInternship.getInternship());
        }

        log.info("The list of favourite internships has been returned");
        return internships;
    }


    /**
     *
     * @param internship the internship that wants to be removed from favourites
     * @param user the user that the list belongs to
     */
    public void removeFromFavourites(Internship internship, User user) {
        FavouriteInternship favouriteList = favouriteInternshipRepository.findAllByStudentAndInternship(user, internship).get();
        favouriteInternshipRepository.delete(favouriteList);
        log.info("The internship " + internship.getTitle() + " has been removed from favourites");
    }

    public boolean isFavouriteInternship(User user, Internship internship){
        Optional<FavouriteInternship> favouriteInternship
                = favouriteInternshipRepository.findByStudentAndInternship(user, internship);
        if(favouriteInternship.isPresent())
            return true;
        return false;
    }
}
