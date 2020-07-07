package project.client.internshipPlatform.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDTO {

    @SerializedName("usernameOrEmail")
    @Expose
    private String usernameOrEmail;

    @SerializedName("password")
    @Expose
    private String password;

    public LoginDTO(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public LoginDTO() {
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
