package ro.utcn.sd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class InternshipDto {

    private String id;
    private String title;
    private String company;
    private String dateUntil;
    private boolean favourite;
    private String status;

    private String appliedOn;

    public InternshipDto(String id, String title, String company, String dateUntil, boolean favourite, String status) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.dateUntil = dateUntil;
        this.favourite = favourite;
        this.status = status;
    }
}
