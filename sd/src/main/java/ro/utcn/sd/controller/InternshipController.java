package ro.utcn.sd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.config.HttpHeadersConfiguration;
import ro.utcn.sd.dto.*;
import ro.utcn.sd.factory.ResponseFactory;
import ro.utcn.sd.mapper.InternshipMapper;
import ro.utcn.sd.service.FavouriteInternshipService;
import ro.utcn.sd.service.InternshipService;
import ro.utcn.sd.service.UserService;

@RestController
@RequestMapping("/internship")
public class InternshipController {

    @Autowired
    private FavouriteInternshipService favouriteInternshipService;

    @Autowired
    private UserService userService;

    @Autowired
    private InternshipMapper internshipMapper;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private HttpHeadersConfiguration httpHeadersConfiguration;

    @PostMapping("/addFavorite/{id}")
    public ResponseEntity addToFavorite(@RequestBody InternshipDto internshipDto, @PathVariable String id) {

        favouriteInternshipService.addToFavourites(internshipMapper.getInternshipFromInternshipDto(internshipDto)
                , userService.getUserById(id));

        return ResponseFactory.createErrorMessage("You have added the internship to favourite", httpHeadersConfiguration.getHttpHeaders());
    }

    @PostMapping("/deleteFavorite/{id}")
    public ResponseEntity deleteFromFavorite(@RequestBody InternshipDto internshipDto, @PathVariable String id) {

        favouriteInternshipService.removeFromFavourites(internshipMapper.getInternshipFromInternshipDto(internshipDto)
                , userService.getUserById(id));

        return ResponseFactory.createErrorMessage("You have added the internship to favourite", httpHeadersConfiguration.getHttpHeaders());
    }

    @GetMapping("/all/{id}")
    public InternshipOverviewDto getAll(@PathVariable String id) {

        return internshipMapper.getInternshipOverviewDtoFroListInternship(internshipService.getAllApproved(), userService.getUserById(id));
    }

    @GetMapping("/favourite/{id}")
    public InternshipOverviewDto getFavourite(@PathVariable String id) {
             return  internshipMapper.getInternshipOverviewDtoFroListInternship(favouriteInternshipService.seeListOfFavourites(userService.getUserById(id)),userService.getUserById(id));
        }

    @GetMapping("/details/{id}")
    public InternshipDetailsDto getDetailedInternship(@PathVariable String id) {
        return  internshipMapper.getInternshipDetailDtoFromInternship(internshipService.findInternship(id));
    }

    @PostMapping("/add/{id}")
    public ResponseEntity addNewInternship(@RequestBody InternshipNewDto internshipDto, @PathVariable String id) {

        internshipService.createOrUpdateInternship(internshipMapper.getInternshipFromInternshipNewDto(internshipDto,
                userService.getUserById(id)));

        return ResponseFactory.createSuccessMessage("You have added a new internship", httpHeadersConfiguration.getHttpHeaders());
    }

    @GetMapping("/myInternships/{id}")
    public InternshipOverviewDto getMyInternships(@PathVariable String id) {

        return internshipMapper.getInternshipOverviewDtoFroListInternship(internshipService.getMyInternship(userService.getUserById(id)),
                userService.getUserById(id));
    }

    @PostMapping("/edit")
    public ResponseEntity editInternship(@RequestBody InternshipEditDto internshipEditDto) {

        internshipService.createOrUpdateInternship(internshipMapper.getInternshipFromInternshipEditDto(internshipEditDto));

        return ResponseFactory.createErrorMessage("You have edit the internship", httpHeadersConfiguration.getHttpHeaders());
    }

}
