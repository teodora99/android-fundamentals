package ro.utcn.sd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;

    @Column
    private String resetCode;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.REMOVE)
    private List<Internship> internships;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<FavouriteInternship> favouriteInternships;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Application> applications;

}
