package ro.utcn.sd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private String firstName;
    private String lastName;
    private String id;
    private String applicationDate;



}
