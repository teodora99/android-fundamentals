package ro.utcn.sd.dto;

public class ResponseDto {

    private String message;
    private String severity;

    public ResponseDto(String message, String severity) {
        this.message = message;
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public String getSeverity() {
        return severity;
    }
}
