package ro.utcn.sd.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    private String id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String description;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private List<Skill> skills;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private List<Study> studies;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private List<WorkExperience> workExperiences;

    private Profile(ProfileBuilder profileBuilder){
        this.id = profileBuilder.id;
        this.firstName = profileBuilder.firstName;
        this.lastName = profileBuilder.lastName;
        this.password = profileBuilder.password;
        this.email = profileBuilder.email;
        this.username = profileBuilder.username;
        this.description = profileBuilder.description;
        this.dateOfBirth = profileBuilder.dateOfBirth;
        this.address = profileBuilder.address;
        this.phoneNumber = profileBuilder.phoneNumber;
    }

    public static class ProfileBuilder{

        private final String id;
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String username;
        private final String password;
        private User user;
        private String description;
        private List<Skill> skills;
        private List<Study> studies;
        private List<WorkExperience> workExperiences;
        private String phoneNumber;
        private String address;
        private LocalDate dateOfBirth;

        public ProfileBuilder(String id, String username, String email, String password, String firstName, String lastName){
            this.id = id;
            this.password = password;
            this.username = username;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public ProfileBuilder addPhoneNr(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ProfileBuilder addAddress(String address){
            this.address = address;
            return this;
        }

        public ProfileBuilder addDateOfBirth(LocalDate dateOfBirth){
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public ProfileBuilder addWorkExperience(List<WorkExperience> workExperiences){
            this.workExperiences = workExperiences;
            return this;
        }

        public ProfileBuilder addStudies(List<Study> studies){
            this.studies = studies;
            return this;
        }

        public ProfileBuilder addSkills(List<Skill> skills){
            this.skills = skills;
            return this;
        }

        public ProfileBuilder addDescription(String description){
            this.description = description;
            return this;
        }

        public ProfileBuilder addUser(User user) {
            this.user = user;
            return this;
        }

        public Profile build() {
            Profile profile = new Profile(this);
            return profile;
        }
    }
}
