package ro.utcn.sd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "internship")
public class Internship {

    @Id
    private String id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Boolean statusApproved;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private LocalDate applyUntil;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToMany(mappedBy = "internships", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<City> cities;

    @OneToMany(mappedBy = "internship", cascade = CascadeType.ALL)
    private List<FavouriteInternship> favouriteInternships;

    @OneToMany(mappedBy = "internship", cascade = CascadeType.ALL)
    private List<Application> applications;

}
