package ro.utcn.sd.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.utcn.sd.dto.*;
import ro.utcn.sd.entity.Internship;
import ro.utcn.sd.entity.User;
import ro.utcn.sd.service.DomainService;
import ro.utcn.sd.service.FavouriteInternshipService;
import ro.utcn.sd.service.InternshipService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class InternshipMapper {

    @Autowired
    private FavouriteInternshipService favouriteInternshipService;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private DomainService domainService;

    public InternshipOverviewDto getInternshipOverviewDtoFroListInternship(List<Internship> internships, User user) {
        InternshipOverviewDto internshipOverviewDto = new InternshipOverviewDto();
        List<InternshipDto> internshipDtos = internships.stream()
                .map(e -> {
                    boolean favourite = favouriteInternshipService.isFavouriteInternship(user, e);
                     return new InternshipDto(e.getId(), e.getTitle(), e.getCreator().getProfile().getFirstName(), e.getApplyUntil().toString(), favourite, e.getStatusApproved().toString());
                })
                .collect(Collectors.toList());
        internshipOverviewDto.setInternshipDtoList(internshipDtos);
        return internshipOverviewDto;
    }

    public InternshipOverviewDto getWithoutFavourite(List<Internship> internships){
        InternshipOverviewDto internshipOverviewDto = new InternshipOverviewDto();
        List<InternshipDto> internshipDtos = internships.stream()
                .map(e -> new InternshipDto(e.getId(), e.getTitle(),e.getCreator().getProfile().getFirstName(), e.getApplyUntil().toString(), false, e.getStatusApproved().toString()))
                .collect(Collectors.toList());
        internshipOverviewDto.setInternshipDtoList(internshipDtos);
        return internshipOverviewDto;
    }

    public Internship getInternshipFromInternshipDto(InternshipDto internshipDto) {
        return internshipService.findInternship(internshipDto.getId());
    }

    public Internship getInternshipFromInternshipEmailDto(InternshipEmailDto internshipEmailDto) {
        return internshipService.findInternship(internshipEmailDto.getId());
    }

    public Internship getInternshipFromInternshipNewDto(InternshipNewDto internshipDto, User user) {
        Internship internship = new Internship();
        internship.setId(UUID.randomUUID().toString());
        internship.setTitle(internshipDto.getTitle());
        internship.setDescription(internshipDto.getDescription());
        internship.setDomain(domainService.getDomainByName(internshipDto.getDomain()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String date = internshipDto.getStartDate();
        LocalDate localDate = LocalDate.parse(date, formatter);
        internship.setStartDate(localDate);

        String dateEnd = internshipDto.getEndDate();
        LocalDate localDateEnd = LocalDate.parse(dateEnd, formatter);
        internship.setEndDate(localDateEnd);

        String date3 = internshipDto.getDateUntil();
        LocalDate localDate3 = LocalDate.parse(date3, formatter);
        internship.setApplyUntil(localDate3);

        internship.setCreator(user);
        internship.setStatusApproved(false);
        return internship;
    }

    public InternshipDetailsDto getInternshipDetailDtoFromInternship(Internship internship) {
        InternshipDetailsDto internshipDetailsDto = new InternshipDetailsDto();
        internshipDetailsDto.setId(internship.getId());
        internshipDetailsDto.setTitle(internship.getTitle());
        internshipDetailsDto.setDescription(internship.getDescription());
        internshipDetailsDto.setDomain(internship.getDomain().getDomain());
        internshipDetailsDto.setPeriod("From : " + internship.getStartDate() + "  To: " + internship.getEndDate());
        internshipDetailsDto.setCompany(internship.getCreator().getProfile().getFirstName());
        return internshipDetailsDto;
    }

    public Internship getInternshipFromInternshipEditDto(InternshipEditDto internshipEditDto) {

        Internship internship = internshipService.findInternship(internshipEditDto.getId());
        internship.setTitle(internshipEditDto.getTitle());
        internship.setDescription(internshipEditDto.getDescription());
        internship.setDomain(domainService.getDomainByName(internshipEditDto.getDomain()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = internshipEditDto.getStartDate();
        LocalDate localDate = LocalDate.parse(date, formatter);
        internship.setStartDate(localDate);

        String dateEnd = internshipEditDto.getEndDate();
        LocalDate localDateEnd = LocalDate.parse(dateEnd, formatter);
        internship.setEndDate(localDateEnd);

        String date3 = internshipEditDto.getDateUntil();
        System.out.println("Bla"+date3);
        LocalDate localDate3 = LocalDate.parse(date3, formatter);
        internship.setApplyUntil(localDate3);

        internship.setStatusApproved(false);
        return internship;
    }

    public InternshipNewDto getInternshipNewDtoFromInternship(Internship internship){
        InternshipNewDto internshipNewDto = new InternshipNewDto();
        internshipNewDto.setTitle(internship.getTitle());
        internshipNewDto.setDescription(internship.getDescription());
        internshipNewDto.setDomain(domainService.getDomainByName(internship.getDomain().getDomain()).getDomain());
        internshipNewDto.setStartDate(internship.getStartDate().toString());
        internshipNewDto.setEndDate(internship.getEndDate().toString());
        internshipNewDto.setDateUntil(internship.getApplyUntil().toString());

        return internshipNewDto;
    }

    public InternshipOverviewDto getInternshipOverviewDtoFromListOfUsers(List<InternshipDto> internshipDtos){
        InternshipOverviewDto internshipOverviewDto = new InternshipOverviewDto();
        internshipOverviewDto.setInternshipDtoList(internshipDtos);
        return internshipOverviewDto;
    }

}
