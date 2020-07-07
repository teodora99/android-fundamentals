package ro.utcn.sd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InternshipEditDto {

    private String id;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String domain;
    private String dateUntil;
}
