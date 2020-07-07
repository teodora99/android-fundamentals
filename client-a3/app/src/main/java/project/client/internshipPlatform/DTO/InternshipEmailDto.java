package project.client.internshipPlatform.DTO;

public class InternshipEmailDto {

    private String id;
    private String title;
    private String description;
    private boolean favourite;
    private String message;

    public InternshipEmailDto(String id, String title, String description, boolean favourite, String message) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.favourite = favourite;
        this.message = message;
    }

    public InternshipEmailDto(){}

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

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
