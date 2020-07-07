package ro.utcn.sd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.config.HttpHeadersConfiguration;
import ro.utcn.sd.dto.ProfileDto;
import ro.utcn.sd.dto.SkillDto;
import ro.utcn.sd.dto.StudyDto;
import ro.utcn.sd.dto.WorkDto;
import ro.utcn.sd.factory.ResponseFactory;
import ro.utcn.sd.mapper.ProfileMapper;
import ro.utcn.sd.service.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private SkillService skillService;

    @Autowired
    private StudyService studyService;

    @Autowired
    private WorkService workService;

    @Autowired
    private HttpHeadersConfiguration httpHeadersConfiguration;

    @GetMapping("/user/{id}")
    public ProfileDto getProfile(@PathVariable String id){
        return profileMapper.getProfileDtoFromProfile(profileService.seeProfile(userService.getUserById(id)));
    }

    @PostMapping("/addSkill")
    public ResponseEntity addSkill(@RequestBody SkillDto skillDto){
            skillService.addSKill(profileMapper.getSkillFromSkillDto(skillDto));

            return ResponseFactory.createSuccessMessage("You have added a new skill", httpHeadersConfiguration.getHttpHeaders());

    }

    @PostMapping("/addStudy")
    public ResponseEntity addStudy(@RequestBody StudyDto studyDto){
        studyService.addStudy(profileMapper.getStudyFromStudyDto(studyDto));

        return ResponseFactory.createSuccessMessage("You have added a new study", httpHeadersConfiguration.getHttpHeaders());

    }

    @PostMapping("/addWork")
    public ResponseEntity addWork(@RequestBody WorkDto workDto){
        workService.addWork(profileMapper.getWorkFromWorkDto(workDto));

        return ResponseFactory.createSuccessMessage("You have added a new work experience", httpHeadersConfiguration.getHttpHeaders());

    }
}
