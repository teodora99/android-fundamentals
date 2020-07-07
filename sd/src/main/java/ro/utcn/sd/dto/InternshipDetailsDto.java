package ro.utcn.sd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InternshipDetailsDto {
    private String id;
    private String title;
    private String description;
    private String company;
    private String period;
    private String domain;
}


