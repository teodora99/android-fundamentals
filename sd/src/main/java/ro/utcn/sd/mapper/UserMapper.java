package ro.utcn.sd.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.utcn.sd.dto.RegisterDto;
import ro.utcn.sd.dto.UserDto;
import ro.utcn.sd.dto.UserOverviewDot;
import ro.utcn.sd.entity.Profile;
import ro.utcn.sd.entity.User;
import ro.utcn.sd.service.ProfileService;
import ro.utcn.sd.service.RoleService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Component
public class UserMapper {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ProfileService profileService;

    public User getUserFromRegisterDTO(RegisterDto registerDTO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = registerDTO.getDateOfBirth();
        LocalDate dateOfBirth = LocalDate.parse(date, formatter);

        Profile profile = new Profile.ProfileBuilder(UUID.randomUUID().toString(),
                registerDTO.getUsername(), registerDTO.getEmail(),
                registerDTO.getPassword(), registerDTO.getFirstName(),
                registerDTO.getLastName())
                .addAddress(registerDTO.getAddress())
                .addDateOfBirth(dateOfBirth)
                .addPhoneNr(registerDTO.getPhoneNumber()).build();
        profileService.createProfile(profile);

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setRole(roleService.findRole(registerDTO.getRole()));
        user.setProfile(profile);

        return user;
    }

    public UserOverviewDot getUserOverviewDtoFromListOfUsers(List<UserDto> users){
      UserOverviewDot userOverviewDot = new UserOverviewDot();
      userOverviewDot.setUsers(users);
      return userOverviewDot;
    }
}
