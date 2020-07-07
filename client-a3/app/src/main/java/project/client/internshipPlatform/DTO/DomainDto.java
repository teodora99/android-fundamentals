package project.client.internshipPlatform.DTO;

import java.util.ArrayList;
import java.util.List;

public class DomainDto {

    ArrayList<String> domains;

    public DomainDto(){
        domains = new ArrayList<>();
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(ArrayList<String> domains) {
        this.domains = domains;
    }
}
