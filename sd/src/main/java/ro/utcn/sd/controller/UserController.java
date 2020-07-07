package ro.utcn.sd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.config.HttpHeadersConfiguration;
import ro.utcn.sd.dto.*;
import ro.utcn.sd.factory.ResponseFactory;
import ro.utcn.sd.mapper.InternshipMapper;
import ro.utcn.sd.mapper.UserMapper;
import ro.utcn.sd.messages.RootMessages;
import ro.utcn.sd.service.InternshipService;
import ro.utcn.sd.service.LoginService;
import ro.utcn.sd.service.UserService;
import ro.utcn.sd.validator.UserValidator;


@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private InternshipMapper internshipMapper;

    @Autowired
    private HttpHeadersConfiguration httpHeadersConfiguration;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDTO) {

        String id = loginService.login(loginDTO.getUsernameOrEmail(), loginDTO.getPassword());

        if (id != null) {
            String[] splited = id.split("\\s+");
            httpHeadersConfiguration.generateToken(splited[1]);
            return ResponseFactory.createSuccessLoginMessage(RootMessages.DEFAULT_LOGIN_SUCCESS, splited[1], splited[0], httpHeadersConfiguration.getHttpHeaders());
        } else
            return ResponseFactory.createErrorMessage(RootMessages.DEFAULT_LOGIN_ERROR, httpHeadersConfiguration.getHttpHeaders());

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto registerDTO) {
        if (userValidator.validateRegistration(registerDTO)) {
            userService.addUser(userMapper.getUserFromRegisterDTO(registerDTO));
            return ResponseFactory.createSuccessMessage(RootMessages.DEFAULT_SUCCESS, httpHeadersConfiguration.getHttpHeaders());
        } else
            return ResponseFactory.createErrorMessage(RootMessages.DEFAULT_ERROR, httpHeadersConfiguration.getHttpHeaders());

    }

    @PostMapping("/delete")
    public ResponseEntity deleteInternship(@RequestBody InternshipDto internshipDto) {

        internshipService.deleteInternshipCompany(internshipMapper.getInternshipFromInternshipDto(internshipDto));

        return ResponseFactory.createSuccessMessage("You have successfully deleted the internship", httpHeadersConfiguration.getHttpHeaders());
    }

    @GetMapping("/details/{id}")
    public InternshipNewDto getDetailedInternship(@PathVariable String id) {
        return internshipMapper.getInternshipNewDtoFromInternship(internshipService.findInternship(id));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity resetPassword(@RequestBody UserResetPasswordDto userResetPasswordDTO) {
        Boolean correctCode = userService.checkResetCode(userResetPasswordDTO.getEmail(), userResetPasswordDTO.getCode());

        if (correctCode) {
            System.out.println("The email is: " + userResetPasswordDTO.getEmail());
            userService.changePassword(userService.getUserByEmail(userResetPasswordDTO.getEmail()), userResetPasswordDTO.getPassword());
            return ResponseFactory.createSuccessMessage("You have successfully reset your password", httpHeadersConfiguration.getHttpHeaders());
        } else {
            return ResponseFactory.createErrorMessage("The code is not correct", httpHeadersConfiguration.getHttpHeaders());
        }

    }

    @PostMapping("/sendCode")
    public ResponseEntity sendCode(@RequestBody UserResetPasswordDto userResetPasswordDTO) {
            if(userService.getUserByEmail(userResetPasswordDTO.getEmail()) == null){
                return ResponseFactory.createErrorMessage("The email is not valid", httpHeadersConfiguration.getHttpHeaders());

            }else{
                userService.resetPassword(userResetPasswordDTO.getEmail());
                return ResponseFactory.createSuccessMessage("The code has been send", httpHeadersConfiguration.getHttpHeaders());

            }
    }


}
