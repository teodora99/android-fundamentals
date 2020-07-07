package project.client.internshipPlatform.DTO;

public class ResponseLoginDto {

    private String message;
    private String severity;
    private String role;
    private String userId;

    public ResponseLoginDto(String message, String severity, String role, String userId) {
        this.message = message;
        this.severity = severity;
        this.role = role;
        this.userId = userId;
    }

    public ResponseLoginDto(){
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

