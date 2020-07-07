package project.client.internshipPlatform.DTO;

public class InternshipDto {

    private String id;
    private String title;
    private String company;
    private String dateUntil;
    private boolean favourite;

    public InternshipDto(String id, String title, String company, String applyUntil, boolean favourite) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.dateUntil = applyUntil;
        this.favourite = favourite;
    }

    public InternshipDto(){
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

    public String isFavourite() {
        return String.valueOf(favourite);
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getApplyUntil() {
        return dateUntil;
    }

    public void setApplyUntil(String applyUntil) {
        this.dateUntil = applyUntil;
    }
}
