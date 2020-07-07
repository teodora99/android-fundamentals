package ro.utcn.sd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InternshipEmailDto {

    private String id;
    private String title;
    private String description;
    private boolean favourite;
    private String message;
}
