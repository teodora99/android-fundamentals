package ro.utcn.sd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyDto {

    private String id;
    private String yearBegin;
    private String yearEnd;
    private String institution;
    private String certification;

}
