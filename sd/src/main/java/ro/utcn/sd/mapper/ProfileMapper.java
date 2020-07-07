package ro.utcn.sd.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.utcn.sd.dto.ProfileDto;
import ro.utcn.sd.dto.SkillDto;
import ro.utcn.sd.dto.StudyDto;
import ro.utcn.sd.dto.WorkDto;
import ro.utcn.sd.entity.Profile;
import ro.utcn.sd.entity.Skill;
import ro.utcn.sd.entity.Study;
import ro.utcn.sd.entity.WorkExperience;
import ro.utcn.sd.factory.SkillFactory;
import ro.utcn.sd.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class ProfileMapper {


    @Autowired
    private SkillFactory skillFactory;

    @Autowired
    private UserService userService;

    public ProfileDto getProfileDtoFromProfile(Profile profile){
        ProfileDto profileDto = new ProfileDto(profile.getUser().getId(),
                profile.getFirstName(), profile.getLastName(), profile.getEmail(),
                profile.getAddress(), profile.getPhoneNumber(), getSkillDtoFromSkill(profile.getSkills()),
                getStudyDtoFromStudy(profile.getStudies()),
                getWorkDtoFromWork(profile.getWorkExperiences()),
                profile.getDateOfBirth().toString());
        return profileDto;
    }

    public List<SkillDto> getSkillDtoFromSkill(List<Skill> skill){
        List<SkillDto> skillDtos = new ArrayList<>();

        for(Skill s : skill) {
            SkillDto skillDto = new SkillDto();
            skillDto.setId(s.getId());
            skillDto.setType(s.getClass().getSimpleName());
            skillDto.setDescription(s.getDescription());
            skillDtos.add(skillDto);
        }

        return skillDtos;
    }

    public List<StudyDto> getStudyDtoFromStudy(List<Study> studies){
        List<StudyDto> studyDtos = new ArrayList<>();

        for(Study s : studies) {
            StudyDto studyDto = new StudyDto();
            studyDto.setId(s.getId());
            studyDto.setCertification(s.getCertification());
            studyDto.setInstitution(s.getInstitution());
            studyDto.setYearBegin(s.getYearBegin().toString());
            studyDto.setYearEnd(s.getYearEnd().toString());
            studyDtos.add(studyDto);
        }

        return studyDtos;
    }

    public List<WorkDto> getWorkDtoFromWork(List<WorkExperience> workExperiences){
        List<WorkDto> workDtos = new ArrayList<>();

        for(WorkExperience workExperience : workExperiences) {
            WorkDto workDto = new WorkDto();
            workDto.setId(workExperience.getId());
            workDto.setCompany(workExperience.getCompany());
            workDto.setJobDescription(workExperience.getJobDescription());
            workDto.setJobTitle(workExperience.getJobTitle());
            workDto.setYearBegin(workExperience.getYearBegin().toString());
            workDto.setYearEnd(workExperience.getYearEnd().toString());
            workDtos.add(workDto);
        }

        return workDtos;
    }

    public Skill getSkillFromSkillDto(SkillDto skillDto){

       Skill skill = skillFactory.createSkill(skillDto.getType());
        skill.setId(UUID.randomUUID().toString());
        skill.setDescription(skillDto.getDescription());
        skill.setProfile(userService.getUserById(skillDto.getId()).getProfile());

        return skill;
    }

    public Study getStudyFromStudyDto(StudyDto studyDto){
      Study study = new Study();
      study.setId(UUID.randomUUID().toString());
      study.setCertification(studyDto.getCertification());
      study.setInstitution(studyDto.getInstitution());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String date = studyDto.getYearBegin();
        LocalDate localDate = LocalDate.parse(date, formatter);

        String dateEnd = studyDto.getYearEnd();
        LocalDate localDateEnd = LocalDate.parse(dateEnd, formatter);

      study.setYearBegin(localDate);
      study.setYearEnd(localDateEnd);
      study.setProfile(userService.getUserById(studyDto.getId()).getProfile());
        return study;
    }

    public WorkExperience getWorkFromWorkDto(WorkDto workDto){
        WorkExperience workExperience = new WorkExperience();

        workExperience.setId(UUID.randomUUID().toString());
        workExperience.setCompany(workDto.getCompany());
        workExperience.setJobDescription(workDto.getJobDescription());
        workExperience.setJobTitle(workDto.getJobTitle());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String date = workDto.getYearBegin();
        LocalDate localDate = LocalDate.parse(date, formatter);

        String dateEnd = workDto.getYearEnd();
        LocalDate localDateEnd = LocalDate.parse(dateEnd, formatter);


        workExperience.setYearBegin(localDate);
        workExperience.setYearEnd(localDateEnd);

        workExperience.setProfile(userService.getUserById(workDto.getId()).getProfile());
        return workExperience;
    }

}
