package project.client.internshipPlatform.DTO;

public class ApplicationDto {
    private String idInternship;
    private String idUser;
    private String additionalNote;

    public ApplicationDto(String idInternship, String idUser, String additionalNote) {
        this.idInternship = idInternship;
        this.idUser = idUser;
        this.additionalNote = additionalNote;
    }

    public ApplicationDto(){
    }

    public String getIdInternship() {
        return idInternship;
    }

    public void setIdInternship(String idInternship) {
        this.idInternship = idInternship;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }
}
