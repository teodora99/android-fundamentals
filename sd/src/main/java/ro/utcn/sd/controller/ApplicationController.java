package ro.utcn.sd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.config.HttpHeadersConfiguration;
import ro.utcn.sd.dto.ApplicationDto;
import ro.utcn.sd.dto.InternshipOverviewDto;
import ro.utcn.sd.dto.UserOverviewDot;
import ro.utcn.sd.factory.ResponseFactory;
import ro.utcn.sd.mapper.InternshipMapper;
import ro.utcn.sd.mapper.UserMapper;
import ro.utcn.sd.messages.RootMessages;
import ro.utcn.sd.service.ApplicationService;
import ro.utcn.sd.service.InternshipService;
import ro.utcn.sd.service.UserService;

@RestController
@RequestMapping("/apply")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private InternshipMapper internshipMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpHeadersConfiguration httpHeadersConfiguration;

    @PostMapping("/internship")
    public ResponseEntity applyToInternship(@RequestBody ApplicationDto applicationDto){

        String message = applicationService.applyToInternship(internshipService.findInternship(applicationDto.getIdInternship()),
                userService.getUserById(applicationDto.getIdUser()),
                applicationDto.getAdditionalNote());
        if(message.equals(RootMessages.DEFAULT_ADD_ERROR))
            return ResponseFactory.createErrorMessage(RootMessages.DEFAULT_ADD_ERROR, httpHeadersConfiguration.getHttpHeaders());
        else  if(message.equals(RootMessages.DEFAULT_ADD_SUCCESS))
            return ResponseFactory.createSuccessMessage(RootMessages.DEFAULT_ADD_SUCCESS, httpHeadersConfiguration.getHttpHeaders());
        else  if(message.equals(RootMessages.DEFAULT_ADD_ALREADY))
            return ResponseFactory.createErrorMessage(RootMessages.DEFAULT_ADD_ALREADY, httpHeadersConfiguration.getHttpHeaders());
       return ResponseFactory.createErrorMessage(RootMessages.DEFAULT_ALREADY3_ERROR, httpHeadersConfiguration.getHttpHeaders());
    }

    @GetMapping("/getApplications/{id}")
    public InternshipOverviewDto getApplications(@PathVariable String id) {
        return  internshipMapper.getInternshipOverviewDtoFromListOfUsers(applicationService.seeListOfApplied(userService.getUserById(id)));
    }

    @GetMapping("/getApplicants/{idInternship}")
    public UserOverviewDot getApplicants(@PathVariable String idInternship) {
        return  userMapper.getUserOverviewDtoFromListOfUsers(applicationService.seeListOfApplicants(internshipService.findInternship(idInternship)));
    }
}
