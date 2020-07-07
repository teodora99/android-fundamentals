package ro.utcn.sd.utils;

import org.springframework.stereotype.Component;
import ro.utcn.sd.entity.Application;

import java.time.LocalDate;

@Component
public class ApplicationUtils {

    public String templateAppliedInternship(Application application){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Good day " + application.getStudent().getProfile().getLastName() +" " + application.getStudent().getProfile().getFirstName()+ ", \n \n")
                .append("We thank you for using our platform in order to apply to the internship: " + application.getInternship().getTitle() + "\n")
                .append("This is the internship is offer form the company:  " + application.getInternship().getCreator().getProfile().getFirstName() + "\n \n")
                .append("Internship Details \n \n")
                .append("Title: " + application.getInternship().getTitle() + "\n")
                .append("Description: " + application.getInternship().getDescription() + "\n")
                .append("Domain: " + application.getInternship().getDomain().getDomain() + "\n")
                .append("Period: From: " + application.getInternship().getStartDate().toString() +"    To:  " + application.getInternship().getEndDate().toString() + "\n")
                .append("You have applied on : " + LocalDate.now().toString() + "\n \n \n");
        return stringBuilder.toString();
    }
}
