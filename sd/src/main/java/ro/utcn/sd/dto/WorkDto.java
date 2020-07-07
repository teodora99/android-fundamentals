package ro.utcn.sd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDto {

    private String id;
    private String yearBegin;
    private String yearEnd;
    private String company;
    private String jobTitle;
    private String jobDescription;
}
