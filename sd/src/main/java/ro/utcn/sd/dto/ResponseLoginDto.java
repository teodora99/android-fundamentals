package ro.utcn.sd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseLoginDto {

    private String message;
    private String severity;
    private String role;
    private String userId;

}
