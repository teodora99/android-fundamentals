package ro.utcn.sd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.utcn.sd.entity.Skill;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private List<SkillDto> skills;
    private List<StudyDto> studies;
    private List<WorkDto> workExperience;
    private String birthDate;

}
