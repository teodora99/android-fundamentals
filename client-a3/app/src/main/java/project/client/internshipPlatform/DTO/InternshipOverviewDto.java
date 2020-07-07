package project.client.internshipPlatform.DTO;

import java.util.ArrayList;
import java.util.List;

public class InternshipOverviewDto {

    private List<InternshipDto> internshipDtoList;

    public InternshipOverviewDto(){
        internshipDtoList = new ArrayList<>();
    }

    public List<InternshipDto> getInternshipDtoList() {
        return internshipDtoList;
    }

    public void setInternshipDtoList(List<InternshipDto> internshipDtoList) {
        this.internshipDtoList = internshipDtoList;
    }
}
