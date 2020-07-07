package ro.utcn.sd.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.config.HttpHeadersConfiguration;
import ro.utcn.sd.dto.InternshipDto;
import ro.utcn.sd.dto.InternshipEmailDto;
import ro.utcn.sd.dto.InternshipOverviewDto;
import ro.utcn.sd.factory.ResponseFactory;
import ro.utcn.sd.mapper.InternshipMapper;
import ro.utcn.sd.service.AdminService;
import ro.utcn.sd.service.InternshipService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private InternshipMapper internshipMapper;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private HttpHeadersConfiguration httpHeadersConfiguration;

    @PostMapping("/approve/{id}")
    public ResponseEntity applyToInternship(@RequestBody InternshipDto internshipDto, @PathVariable String id) {

        adminService.approveAddedInternship(internshipMapper.getInternshipFromInternshipDto(internshipDto),
                adminService.getAdminById(id));

        return ResponseFactory.createErrorMessage("You have have approved the internship", httpHeadersConfiguration.getHttpHeaders());
    }

    @GetMapping("/unapproved")
    public InternshipOverviewDto getUnapproved() {

        return internshipMapper.getWithoutFavourite(internshipService.getAllUnapproved());
    }

    @PostMapping("/delete")
    public ResponseEntity deleteInternship(@RequestBody InternshipEmailDto internshipEmailDto) {

        internshipService.deleteInternship(internshipMapper.getInternshipFromInternshipEmailDto(internshipEmailDto), internshipEmailDto.getMessage());

        return ResponseFactory.createErrorMessage("You have successfully deleted the internship", httpHeadersConfiguration.getHttpHeaders());
    }

    @GetMapping("/all/internship")
    public InternshipOverviewDto getAll() {

        return internshipMapper.getWithoutFavourite(internshipService.getAllApproved());
    }
}
