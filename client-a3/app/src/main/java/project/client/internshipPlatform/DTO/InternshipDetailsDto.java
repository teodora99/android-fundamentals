package project.client.internshipPlatform.DTO;

public class InternshipDetailsDto {

    private String id;
    private String title;
    private String description;
    private String company;
    private String period;
    private String domain;

    public InternshipDetailsDto(String id, String title, String description, String company, String period, String domain) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company = company;
        this.period = period;
        this.domain = domain;
    }


    public InternshipDetailsDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
