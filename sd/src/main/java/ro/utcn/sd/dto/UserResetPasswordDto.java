package ro.utcn.sd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResetPasswordDto {

    private String email;
    private String code;
    private String password;
    private String confirmPassword;
}